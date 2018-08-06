package me.bing.web3j.app.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import me.bing.web3j.app.mapper.AccountMapper;
import me.bing.web3j.app.mapper.BlockMapper;
import me.bing.web3j.app.mapper.TransactionMapper;
import me.bing.web3j.app.mapper.model.Account;
import me.bing.web3j.app.mapper.model.Block;
import me.bing.web3j.app.mapper.model.BlockTransactionCount;
import me.bing.web3j.app.mapper.model.DayCount;
import me.bing.web3j.app.mapper.model.Transaction;
import me.bing.web3j.app.service.utils.ConverterFunctionUtil;
import me.bing.web3j.app.service.utils.IpcWeb3j;
import me.bing.web3j.app.service.utils.PageIterator;
import me.bing.web3j.app.common.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.web3j.protocol.Web3j;

/**
 * @author bing on 2018/6/29
 */
@Component
public class EthService {

    private BlockMapper blockMapper;
    private TransactionMapper transactionMapper;
    private AccountMapper accountMapper;
    private final Web3j web3j = IpcWeb3j.INSTANCE.get();

    public EthService(BlockMapper blockMapper, TransactionMapper transactionMapper, AccountMapper accountMapper) {
        this.blockMapper = blockMapper;
        this.transactionMapper = transactionMapper;
        this.accountMapper = accountMapper;
    }

    public Long blockNumber() {
        return blockMapper.latestNumber();
    }

    public List<Block> latestBlocks(int page, int pageSize) {
        Long lastNum = blockMapper.latestNumber();
        List<Block> blocks = new ArrayList<>();
        if (null != lastNum) {
            long endNum = lastNum - (page - 1) * pageSize;
            long startNum = lastNum - page * pageSize;
            if (startNum < 0) {
                startNum = 0;
            }
            if (endNum < startNum) {
                endNum = startNum;
            }

            List<Block> blks = blockMapper.topByNumber(startNum, endNum);
            if (null != blks) {
                blocks.addAll(blks);
            }
        }
        return blocks;
    }

    public PageIterator<Block> listBlocks(int page, int pageSize, String miner) {
        long count = blockMapper.count(miner);
        PageIterator<Block> pageIterator = PageIterator.create(page, pageSize, count);
        if (count > 0) {
            List<Block> blocks = blockMapper.list(pageIterator.getOffset(), pageIterator.getPageSize(), miner);
            if (null != blocks) {
                pageIterator.getData().addAll(blocks);
            }
        }
        return pageIterator;
    }

    public Block loadBlock(String key) {
        if (StringUtils.isNumeric(key)) {
            return blockMapper.load(Long.valueOf(key));
        } else {
            return blockMapper.loadByHash(key);
        }
    }

    public Long countBlockByMiner(String miner) {
        return blockMapper.count(miner);
    }

    public List<Transaction> latestTransactions(int page, int pageSize) {
        return transactionMapper.top((page - 1) * pageSize, pageSize);
    }

    public long countTransaction(String address, Long blockNumber) {
        return transactionMapper.countByBlockAndAddress(address, blockNumber);
    }

    public List<Transaction> transactions(int page, int pageSize, String address, Long blockNumber) {
        return transactionMapper.list((page - 1) * pageSize, pageSize, address, blockNumber);
    }

    public PageIterator<Transaction> pendingTransactions(int page, int pageSize, String address) throws IOException {
        List<Transaction> txs
            = ConverterFunctionUtil.toPendingTransaction.apply(web3j.txPoolContent().send().getPendingTransactions());

        PageIterator<Transaction> result = PageIterator.create(page, pageSize, txs.size());

        if (StringUtils.isNotEmpty(address)) {
            txs = txs.stream()
                .filter(tx -> (tx.getFrom().equals(address) || tx.getTo().equals(address)))
                .collect(Collectors.toList());
        }

        result.setData(txs.subList(Math.max(0, (page - 1) * pageSize), Math.min(txs.size(), page * pageSize)));

        return result;
    }

    public Transaction loadTransaction(String hash) {
        return transactionMapper.load(hash);
    }

    public Transaction loadPendingTransaction(String hash) throws IOException {
        List<Transaction> txs
            = ConverterFunctionUtil.toPendingTransaction.apply(web3j.txPoolContent().send().getPendingTransactions());
        txs = txs.stream().filter(tx -> tx.getHash().equals(hash)).collect(Collectors.toList());
        if (!txs.isEmpty()) {
            return txs.get(0);
        }
        return null;
    }

    public List<DayCount> countTransactionByTimestamp(Date from, Date to) {
        return transactionMapper.countByTimestamp(DateUtils.toDay(from), DateUtils.toDay(to));
    }

    public Long totalBalance() {
        return accountMapper.sumBalance();
    }

    public List<Account> listAccount(int page, int pageSize) {
        return accountMapper.topByBalance((page - 1) * pageSize, pageSize);
    }

    public Long countAccount() {
        return accountMapper.count();
    }

    public Account loadAccount(String hash) {
        return accountMapper.load(hash);
    }

    public List<BlockTransactionCount> countTransactionInBlocks(List<Long> blockNumbers) {
        return CollectionUtils.isEmpty(blockNumbers) ? new ArrayList<>() : transactionMapper.countInBlocks(blockNumbers);
    }

    public List<Transaction> listTransactionInBlocks(List<Long> blockNumbers) {
        return CollectionUtils.isEmpty(blockNumbers) ? new ArrayList<>() : transactionMapper.listByBlocks(blockNumbers);
    }

    public List<Transaction> listTransactionInBlock(Long blockNumber) {
        return null == blockNumber? new ArrayList<>() : transactionMapper.listByBlock(blockNumber);
    }
}
