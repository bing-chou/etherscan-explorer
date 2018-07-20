package me.bing.web3j.app.mapper.model;

import java.util.Date;

/**
 * @author bing on 2018/7/4
 */
public class Transaction {

    private String hash;
    private String blockHash;
    private long blockNumber;
    private String from;
    private String to;
    private long value;
    private int status;
    private Date timestamp;
    private int nonce;
    private int transactionIndex;
    private int type;
    private String data;
    private long gasPrice;
    private int gasLimit;
    private int gasUsed;

    public Transaction() {}

    private Transaction(Builder builder) {
        setHash(builder.hash);
        setBlockHash(builder.blockHash);
        setBlockNumber(builder.blockNumber);
        setFrom(builder.from);
        setTo(builder.to);
        setValue(builder.value);
        setStatus(builder.status);
        setTimestamp(builder.timestamp);
        setNonce(builder.nonce);
        setTransactionIndex(builder.transactionIndex);
        setType(builder.type);
        setData(builder.data);
        setGasPrice(builder.gasPrice);
        setGasLimit(builder.gasLimit);
        setGasUsed(builder.gasUsed);
    }

    public boolean pending() {
        return status == TransactionStatusEnum.PENDING.getValue();
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getBlockHash() {
        return blockHash;
    }

    public void setBlockHash(String blockHash) {
        this.blockHash = blockHash;
    }

    public long getBlockNumber() {
        return blockNumber;
    }

    public void setBlockNumber(long blockNumber) {
        this.blockNumber = blockNumber;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public int getNonce() {
        return nonce;
    }

    public void setNonce(int nonce) {
        this.nonce = nonce;
    }

    public int getTransactionIndex() {
        return transactionIndex;
    }

    public void setTransactionIndex(int transactionIndex) {
        this.transactionIndex = transactionIndex;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public long getGasPrice() {
        return gasPrice;
    }

    public void setGasPrice(long gasPrice) {
        this.gasPrice = gasPrice;
    }

    public int getGasLimit() {
        return gasLimit;
    }

    public void setGasLimit(int gasLimit) {
        this.gasLimit = gasLimit;
    }

    public int getGasUsed() {
        return gasUsed;
    }

    public void setGasUsed(int gasUsed) {
        this.gasUsed = gasUsed;
    }

    public static final class Builder {

        private String hash;
        private String blockHash;
        private long blockNumber;
        private String from;
        private String to;
        private long value;
        private int status;
        private Date timestamp;
        private int nonce;
        private int transactionIndex;
        private int type;
        private String data;
        private long gasPrice;
        private int gasLimit;
        private int gasUsed;

        public Builder() {
        }

        public Builder hash(String val) {
            hash = val;
            return this;
        }

        public Builder blockHash(String val) {
            blockHash = val;
            return this;
        }

        public Builder blockNumber(long val) {
            blockNumber = val;
            return this;
        }

        public Builder from(String val) {
            from = val;
            return this;
        }

        public Builder to(String val) {
            to = val;
            return this;
        }

        public Builder value(long val) {
            value = val;
            return this;
        }

        public Builder status(int val) {
            status = val;
            return this;
        }

        public Builder timestamp(Date val) {
            timestamp = val;
            return this;
        }

        public Builder nonce(int val) {
            nonce = val;
            return this;
        }

        public Builder transactionIndex(int val) {
            transactionIndex = val;
            return this;
        }

        public Builder type(int val) {
            type = val;
            return this;
        }

        public Builder data(String val) {
            data = val;
            return this;
        }

        public Builder gasPrice(long val) {
            gasPrice = val;
            return this;
        }

        public Builder gasLimit(int val) {
            gasLimit = val;
            return this;
        }

        public Builder gasUsed(int val) {
            gasUsed = val;
            return this;
        }

        public Transaction build() {
            return new Transaction(this);
        }
    }
}