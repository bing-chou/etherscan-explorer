package me.bing.web3j.app.service.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import me.bing.web3j.app.common.Constant;
import me.bing.web3j.app.mapper.model.Account;
import me.bing.web3j.app.mapper.model.Block;
import me.bing.web3j.app.mapper.model.Transaction;
import org.web3j.protocol.core.methods.response.EthBlock;

/**
 * @author bing on 2018/7/5
 */
public interface ConverterFunctionUtil {
    Function<EthBlock.Block, Block> toDbBlock
        = blk -> new Block.Builder()
            .number(blk.getNumber().longValue())
            .hash(blk.getHash())
            .timestamp(new Date(blk.getTimestamp().longValue() * Constant.TimestampFactor))
            .miner(blk.getMiner())
            .difficulty(blk.getDifficulty().intValue())
            .totalDifficulty(blk.getTotalDifficulty().intValue())
            .size(blk.getSize().intValue())
            .gasUsed(blk.getGasUsed().intValue())
            .gasLimit(blk.getGasLimit().intValue())
            .nonce(blk.getNonceRaw())
            .extraData(blk.getExtraData())
            .uncleHash(blk.getSha3Uncles())
            .parentHash(blk.getParentHash())
            .stateRoot(blk.getStateRoot())
            .receiptsRoot(blk.getReceiptsRoot())
            .transactionsRoot(blk.getTransactionsRoot())
            .build();

    Function<EthBlock.Block, List<Transaction>> toTransactions
        = blk -> blk.getTransactions().stream()
            .map(txResult -> {
                EthBlock.TransactionObject tx = (EthBlock.TransactionObject) txResult;
                return new Transaction.Builder()
                    .hash(tx.getHash())
                    .blockHash(tx.getBlockHash())
                    .blockNumber(tx.getBlockNumber().longValue())
                    .from(tx.getFrom())
                    .to(tx.getTo())
                    .value(tx.getValue().divide(Constant.GWeiFactor).longValue())
                    .timestamp(new Date(blk.getTimestamp().longValue() * Constant.TimestampFactor))
                    .nonce(tx.getNonce().intValue())
                    .transactionIndex(tx.getTransactionIndex().intValue())
                    .data(tx.getInput())
                    .gasPrice(tx.getGasPrice().longValue())
                    .gasLimit(tx.getGas().intValue())
                    .build();
            })
            .collect(Collectors.toList());

    Function<EthBlock.Block, List<Account>> toAccounts
        = blk -> {
            Set<Account> accountSet = new HashSet<>();
            accountSet.add(new Account.Builder().hash(blk.getMiner()).build());
            for (EthBlock.TransactionResult txResult : blk.getTransactions()) {

                EthBlock.TransactionObject tx = (EthBlock.TransactionObject) txResult;
                if (null != tx.getTo()) {
                    accountSet.add(new Account.Builder().hash(tx.getTo()).build());
                }
            }
            return new ArrayList<>(accountSet);
        };

    Function<List<org.web3j.protocol.core.methods.response.Transaction>, List<Transaction>> toPendingTransaction
        = txs -> txs.stream()
        .map(tx -> new Transaction.Builder()
            .hash(tx.getHash())
            .blockHash(tx.getBlockHash())
            .blockNumber(tx.getBlockNumber().longValue())
            .from(tx.getFrom())
            .to(tx.getTo())
            .value(tx.getValue().divide(Constant.GWeiFactor).longValue())
            .nonce(tx.getNonce().intValue())
            .transactionIndex(tx.getTransactionIndex().intValue())
            .data(tx.getInput())
            .gasPrice(tx.getGasPrice().intValue())
            .gasLimit(tx.getGas().intValue())
            .build())
        .collect(Collectors.toList());
}
