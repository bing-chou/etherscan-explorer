package me.bing.web3j.app.web.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import me.bing.web3j.app.common.Constant;
import me.bing.web3j.app.common.DateUtils;
import me.bing.web3j.app.mapper.model.Account;
import me.bing.web3j.app.mapper.model.Block;
import me.bing.web3j.app.mapper.model.Transaction;
import me.bing.web3j.app.service.MarketCapitalizationService.Capitalization;
import me.bing.web3j.app.web.model.AccountVo;
import me.bing.web3j.app.web.model.BlockVo;
import me.bing.web3j.app.web.model.CapitalizationVo;
import me.bing.web3j.app.web.model.TransactionVo;

/**
 * @author bing on 2018/7/6
 */
public interface ConverterFunctionUtil {
    Function<Capitalization, CapitalizationVo.Capitalization> toVoCapitalization
        = cap -> new CapitalizationVo.Capitalization.Builder()
        .id(0L)
        .marketCap(new BigDecimal(cap.getCapUsd()))
        .volume24h(new BigDecimal(cap.getVolumeUsd24h()))
        .change24h(new BigDecimal(cap.getPercentChange24h()).abs())
        .price(new BigDecimal(cap.getPriceUsd()).setScale(2, BigDecimal.ROUND_DOWN))
        .priceUnit("USD")
        .trends(new BigDecimal(cap.getPercentChange24h()).compareTo(BigDecimal.ZERO) > 0 ? 1 : 0)
        .createdAt(new Date(Constant.TimestampFactor * Long.valueOf(cap.getLastUpdated())))
        .build();

    Function<Block, BlockVo.Block> toVoBlock
        = blk -> new BlockVo.Block.Builder()
        .hash(blk.getHash())
        .height(blk.getNumber())
        .timestamp(blk.getTimestamp())
        .parentHash(blk.getParentHash())
        .miner(new AccountVo.Account.Builder().hash(blk.getMiner()).build())
        .gasLimit((long) blk.getGasLimit())
        .currentTimestamp(DateUtils.now())
        .timeDiff((DateUtils.now().getTime() - blk.getTimestamp().getTime()) / Constant.TimestampFactor)
        .build();

    Function<List<Block>, List<BlockVo.Block>> toVoBlocks
        = blks -> blks.stream()
        .map(toVoBlock)
        .collect(Collectors.toList());

    Function<Transaction, TransactionVo.Transaction> toVoTransaction
        = tx -> new TransactionVo.Transaction.Builder()
        .hash(tx.getHash())
        .block(new BlockVo.Block.Builder()
            .height(tx.getBlockNumber())
            .hash(tx.getBlockHash())
            .build())
        .from(new AccountVo.Account.Builder()
            .hash(tx.getFrom())
            .build())
        .to(new AccountVo.Account.Builder()
            .hash(tx.getTo())
            .build())
        .status(tx.getStatus())
        .value(String.valueOf(Constant.GWeiFactor.multiply(BigInteger.valueOf(tx.getValue()))))
        .nonce((long) tx.getNonce())
        .timestamp(tx.getTimestamp())
        .type(String.valueOf(tx.getType()))
        .gasPrice(String.valueOf(tx.getGasPrice()))
        .gasLimit(String.valueOf(tx.getGasLimit()))
        .gasUsed(String.valueOf(tx.getGasUsed()))
        .createdAt(tx.getTimestamp())
        .data(tx.getData())
        .currentTimestamp(DateUtils.now())
        .timeDiff(null == tx.getTimestamp() ? 0 : (DateUtils.now().getTime() - tx.getTimestamp().getTime()) / Constant.TimestampFactor)
        .build();

    Function<List<Transaction>, List<TransactionVo.Transaction>> toVoTransactions
        = txs -> txs.stream()
        .map(toVoTransaction)
        .collect(Collectors.toList());

    Function<Account, AccountVo.Account> toVoAccount
        = acc -> new AccountVo.Account.Builder()
        .hash(acc.getHash())
        .balance(String.valueOf(Constant.GWeiFactor.multiply(BigInteger.valueOf(acc.getBalance()))))
        .nonce(acc.getNonce())
        .txCnt(acc.getNonce())
        .type(acc.getType())
        .build();

    Function<List<Account>, List<AccountVo.Account>> toVoAccounts
        = accs -> accs.stream()
        .map(toVoAccount)
        .collect(Collectors.toList());
}
