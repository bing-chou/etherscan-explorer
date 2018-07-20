package me.bing.web3j.app.mapper.model;

import java.util.Date;

/**
 * @author bing on 2018/7/4
 */
public class Block {

    private long number;
    private String hash;
    private Date timestamp;
    private String miner;
    private long difficulty;
    private long totalDifficulty;
    private int size;
    private int gasUsed;
    private int gasLimit;
    private String nonce;
    private String extraData;
    private String uncleHash;
    private String parentHash;
    private String stateRoot;
    private String receiptsRoot;
    private String transactionsRoot;

    public Block() {}

    private Block(Builder builder) {
        setNumber(builder.number);
        setHash(builder.hash);
        setTimestamp(builder.timestamp);
        setMiner(builder.miner);
        setDifficulty(builder.difficulty);
        setTotalDifficulty(builder.totalDifficulty);
        setSize(builder.size);
        setGasUsed(builder.gasUsed);
        setGasLimit(builder.gasLimit);
        setNonce(builder.nonce);
        setExtraData(builder.extraData);
        setParentHash(builder.parentHash);
        setUncleHash(builder.uncleHash);
        setStateRoot(builder.stateRoot);
        setReceiptsRoot(builder.receiptsRoot);
        setTransactionsRoot(builder.transactionsRoot);
    }

    public long getNumber() {

        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMiner() {
        return miner;
    }

    public void setMiner(String miner) {
        this.miner = miner;
    }

    public long getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(long difficulty) {
        this.difficulty = difficulty;
    }

    public long getTotalDifficulty() {
        return totalDifficulty;
    }

    public void setTotalDifficulty(long totalDifficulty) {
        this.totalDifficulty = totalDifficulty;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getGasUsed() {
        return gasUsed;
    }

    public void setGasUsed(int gasUsed) {
        this.gasUsed = gasUsed;
    }

    public int getGasLimit() {
        return gasLimit;
    }

    public void setGasLimit(int gasLimit) {
        this.gasLimit = gasLimit;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getExtraData() {
        return extraData;
    }

    public void setExtraData(String extraData) {
        this.extraData = extraData;
    }

    public String getParentHash() {
        return parentHash;
    }

    public void setParentHash(String parentHash) {
        this.parentHash = parentHash;
    }

    public String getUncleHash() {
        return uncleHash;
    }

    public void setUncleHash(String uncleHash) {
        this.uncleHash = uncleHash;
    }

    public String getStateRoot() {
        return stateRoot;
    }

    public void setStateRoot(String stateRoot) {
        this.stateRoot = stateRoot;
    }

    public String getReceiptsRoot() {
        return receiptsRoot;
    }

    public void setReceiptsRoot(String receiptsRoot) {
        this.receiptsRoot = receiptsRoot;
    }

    public String getTransactionsRoot() {
        return transactionsRoot;
    }

    public void setTransactionsRoot(String transactionsRoot) {
        this.transactionsRoot = transactionsRoot;
    }

    public static final class Builder {

        private long number;
        private String hash;
        private Date timestamp;
        private String miner;
        private long difficulty;
        private long totalDifficulty;
        private int size;
        private int gasUsed;
        private int gasLimit;
        private String nonce;
        private String extraData;
        private String parentHash;
        private String uncleHash;
        private String stateRoot;
        private String receiptsRoot;
        private String transactionsRoot;

        public Builder() {
        }

        public Builder number(long val) {
            number = val;
            return this;
        }

        public Builder hash(String val) {
            hash = val;
            return this;
        }

        public Builder timestamp(Date val) {
            timestamp = val;
            return this;
        }

        public Builder miner(String val) {
            miner = val;
            return this;
        }

        public Builder difficulty(long val) {
            difficulty = val;
            return this;
        }

        public Builder totalDifficulty(long val) {
            totalDifficulty = val;
            return this;
        }

        public Builder size(int val) {
            size = val;
            return this;
        }

        public Builder gasUsed(int val) {
            gasUsed = val;
            return this;
        }

        public Builder gasLimit(int val) {
            gasLimit = val;
            return this;
        }

        public Builder nonce(String val) {
            nonce = val;
            return this;
        }

        public Builder extraData(String val) {
            extraData = val;
            return this;
        }

        public Builder parentHash(String val) {
            parentHash = val;
            return this;
        }

        public Builder uncleHash(String val) {
            uncleHash = val;
            return this;
        }

        public Builder stateRoot(String val) {
            stateRoot = val;
            return this;
        }

        public Builder receiptsRoot(String val) {
            receiptsRoot = val;
            return this;
        }

        public Builder transactionsRoot(String val) {
            transactionsRoot = val;
            return this;
        }

        public Block build() {
            return new Block(this);
        }
    }
}
