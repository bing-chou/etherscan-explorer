package me.bing.web3j.app.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import me.bing.web3j.app.common.Constant;
import me.bing.web3j.app.mapper.AccountMapper;
import me.bing.web3j.app.mapper.BlockMapper;
import me.bing.web3j.app.mapper.TransactionMapper;
import me.bing.web3j.app.mapper.model.Account;
import me.bing.web3j.app.mapper.model.Block;
import me.bing.web3j.app.mapper.model.Transaction;
import me.bing.web3j.app.service.utils.ConverterFunctionUtil;
import me.bing.web3j.app.service.utils.IpcWeb3j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

/**
 * @author bing on 2018/7/5
 */
@Component
public class SyncToDatabaseTask {

    private static final Logger log = LoggerFactory.getLogger(SyncToDatabaseTask.class);

    private Web3j web3j = IpcWeb3j.INSTANCE.get();
    private BlockMapper blockMapper;
    private TransactionMapper transactionMapper;
    private AccountMapper accountMapper;

    public SyncToDatabaseTask(BlockMapper blockMapper, TransactionMapper transactionMapper,
        AccountMapper accountMapper) {
        this.blockMapper = blockMapper;
        this.transactionMapper = transactionMapper;
        this.accountMapper = accountMapper;
    }

    @Scheduled(fixedRate = 5000)
    public void run() throws Exception {
        Long latest = blockMapper.latestNumber();
        long ethLatest = web3j.ethBlockNumber().send().getBlockNumber().longValue();
        if (null == latest) {
            latest = 0L;
        }

        for (long i = latest + 1; i < ethLatest + 1; i++) {
            EthBlock.Block blk = web3j.ethGetBlockByNumber(
                DefaultBlockParameter.valueOf(BigInteger.valueOf(i)), true)
                .send().getBlock();

            Block block = ConverterFunctionUtil.toDbBlock.apply(blk);
            List<Transaction> transactions = ConverterFunctionUtil.toTransactions.apply(blk);
            List<Account> accounts = ConverterFunctionUtil.toAccounts.apply(blk);

            Map<String, TransactionReceipt> transactionReceiptMap = updateTransactions(transactions);
            List<Account> allAccounts = getAllAddresses(accounts, transactionReceiptMap);
            updateAccounts(allAccounts);

            if (!allAccounts.isEmpty()) {
                accountMapper.update(allAccounts);
            }
            if (!transactions.isEmpty()) {
                transactionMapper.insert(transactions);
            }
            blockMapper.insert(block);

            log.info("block {} sync succeeded", i);
        }

    }

    private Map<String, TransactionReceipt> updateTransactions(List<Transaction> transactions) throws Exception {
        Map<String, TransactionReceipt> result = new ConcurrentHashMap<>();

        List<CompletableFuture> futures = new ArrayList<>();

        for (Transaction tx : transactions) {
            futures.add(web3j.ethGetTransactionReceipt(tx.getHash()).sendAsync()
                .thenApply(receipt -> {
                    TransactionReceipt r = receipt.getTransactionReceipt().get();
                    tx.setType(null == r.getContractAddress() ? 0 : 1);
                    tx.setGasUsed(r.getGasUsed().intValue());

                    result.putIfAbsent(tx.getHash(), r);
                    return receipt;
                })
            );
        }

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()])).get();

        return result;
    }

    private List<Account> getAllAddresses(List<Account> accounts, Map<String, TransactionReceipt> transactionReceiptMap) {
        Set<Account> result = new HashSet<>();

        for(TransactionReceipt receipt : transactionReceiptMap.values()) {
            if(null != receipt.getContractAddress()) {
                result.add(new Account.Builder()
                    .hash(receipt.getContractAddress())
                    .type(1)
                    .build());
            }
        }
        result.addAll(accounts);

        return new ArrayList<>(result);
    }

    private void updateAccounts(List<Account> accounts) throws Exception {
        List<CompletableFuture> futures = new ArrayList<>();

        for (Account account : accounts) {
            futures.add(web3j
                .ethGetBalance(account.getHash(), DefaultBlockParameter.valueOf(Constant.LatestBlockNumberKey))
                .sendAsync()
                .thenApply(balance -> {
                    account.setBalance(balance.getBalance().divide(Constant.GWeiFactor).longValue());
                    return balance;
                })
            );
            futures.add(web3j
                .ethGetTransactionCount(account.getHash(), DefaultBlockParameter.valueOf(Constant.LatestBlockNumberKey))
                .sendAsync()
                .thenApply(count -> {
                    account.setNonce(count.getTransactionCount().intValue());
                    return count;
                })
            );
        }

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()])).get();
    }
}
