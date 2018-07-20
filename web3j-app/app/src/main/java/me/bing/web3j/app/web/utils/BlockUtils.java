package me.bing.web3j.app.web.utils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import me.bing.web3j.app.common.Constant;
import me.bing.web3j.app.mapper.model.BlockTransactionCount;
import me.bing.web3j.app.mapper.model.Transaction;
import me.bing.web3j.app.web.model.BlockVo;

/**
 * @author bing on 2018/7/19
 */
public class BlockUtils {

    private BlockUtils() {
    }

    public static List<Long> blockNumbers(List<BlockVo.Block> blocks) {
        return blocks.stream().map(BlockVo.Block::getHeight).collect(Collectors.toList());
    }

    public static List<BlockVo.Block> blocksWithTxCnt(List<BlockVo.Block> blocks,
        List<BlockTransactionCount> blockTxCounts) {
        Map<Long, Long> blockTxCountMap = blockTxCounts.stream()
            .collect(Collectors.toMap(BlockTransactionCount::getBlock, BlockTransactionCount::getCount));
        for (BlockVo.Block block : blocks) {
            Long count = blockTxCountMap.get(block.getHeight());
            block.setTxnCnt(null == count ? 0 : count);
        }
        return blocks;
    }

    public static List<BlockVo.Block> blocksWithTx(List<BlockVo.Block> blocks, List<Transaction> transactions) {
        Map<Long, List<Transaction>> blockTransactionMap = new HashMap<>();
        if (null != transactions) {
            for (Transaction transaction : transactions) {
                blockTransactionMap.putIfAbsent(transaction.getBlockNumber(), new ArrayList<>());
                blockTransactionMap.get(transaction.getBlockNumber()).add(transaction);
            }
        }

        for (BlockVo.Block block : blocks) {
            List<Transaction> txs = blockTransactionMap.get(block.getHeight());
            blockWithTx(block, txs);
        }

        return blocks;
    }

    public static BlockVo.Block
    blockWithTx(BlockVo.Block block, List<Transaction> transactions) {
        if (null != transactions) {
            int count = transactions.size();

            long sumGasPrice = 0;
            long sumGasReward = 0;
            for (Transaction tx : transactions) {
                long gasPrice = tx.getGasPrice();
                int gasUsed = tx.getGasUsed();
                sumGasPrice += gasPrice;
                sumGasReward += gasUsed * gasPrice;
            }
            block.setTxnCnt((long) count);
            block.setGasReward(Constant.MineReward.add(BigInteger.valueOf(sumGasReward)).toString());
            if (0 == count) {
                count = 1;
            }
            block.setAvgGasPrice(String.valueOf(sumGasPrice / count));
        } else {
            block.setTxnCnt(0L);
            block.setAvgGasPrice("0");
            block.setGasReward(Constant.MineReward.toString());
        }

        return block;
    }
}
