package me.bing.web3j.app.mapper.model;

/**
 * @author bing on 2018/7/18
 */
public class BlockTransactionCount {

    private Long block;
    private Long count;

    public BlockTransactionCount(){}

    public BlockTransactionCount(Long block, Long count) {
        this.block = block;
        this.count = count;
    }

    public Long getBlock() {
        return block;
    }

    public void setBlock(Long block) {
        this.block = block;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
