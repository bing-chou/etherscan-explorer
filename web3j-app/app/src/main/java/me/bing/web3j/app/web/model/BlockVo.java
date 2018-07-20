package me.bing.web3j.app.web.model;

import java.util.Date;
import me.bing.web3j.app.web.model.AccountVo.Account;
import me.bing.web3j.app.web.model.BlockVo.Block;

public class BlockVo extends ResponseVo<Block> {

    @Override
    public void setData(Block data) {
        super.setData(data);
    }

    public static class Block {

        private String hash;
        private Long height;
        private Date timestamp;
        private String parentHash;
        private Account miner;
        private Long txnCnt = 0L;
        private Long gasLimit;
        private String avgGasPrice; //sum(tx.gasPrice)/count(tx)
        private String gasReward; //sum(tx.gasUsed * tx.gasPrice)
        private Date currentTimestamp;
        private Long timeDiff;
        private Long maxHeight;

        private Block(Builder builder) {
            setHash(builder.hash);
            setHeight(builder.height);
            setTimestamp(builder.timestamp);
            setParentHash(builder.parentHash);
            setMiner(builder.miner);
            setTxnCnt(builder.txnCnt);
            setGasLimit(builder.gasLimit);
            setAvgGasPrice(builder.avgGasPrice);
            setGasReward(builder.gasReward);
            setCurrentTimestamp(builder.currentTimestamp);
            setTimeDiff(builder.timeDiff);
            setMaxHeight(builder.maxHeight);
        }

        public Long getTxCnt() {
            return txnCnt;
        }

        public String getCoinbase() {
            if (null != miner) {
                return miner.getHash();
            }
            return "";
        }

        public Long getMaxHeight() {
            return maxHeight;
        }

        public void setMaxHeight(Long maxHeight) {
            this.maxHeight = maxHeight;
        }

        public String getHash() {
            return hash;
        }

        public void setHash(String hash) {
            this.hash = hash;
        }

        public Long getHeight() {
            return height;
        }

        public void setHeight(Long height) {
            this.height = height;
        }

        public Date getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(Date timestamp) {
            this.timestamp = timestamp;
        }

        public String getParentHash() {
            return parentHash;
        }

        public void setParentHash(String parentHash) {
            this.parentHash = parentHash;
        }

        public Account getMiner() {
            return miner;
        }

        public void setMiner(Account miner) {
            this.miner = miner;
        }

        public Long getTxnCnt() {
            return txnCnt;
        }

        public void setTxnCnt(Long txnCnt) {
            this.txnCnt = txnCnt;
        }

        public Long getGasLimit() {
            return gasLimit;
        }

        public void setGasLimit(Long gasLimit) {
            this.gasLimit = gasLimit;
        }

        public String getAvgGasPrice() {
            return avgGasPrice;
        }

        public void setAvgGasPrice(String avgGasPrice) {
            this.avgGasPrice = avgGasPrice;
        }

        public String getGasReward() {
            return gasReward;
        }

        public void setGasReward(String gasReward) {
            this.gasReward = gasReward;
        }

        public Date getCurrentTimestamp() {
            return currentTimestamp;
        }

        public void setCurrentTimestamp(Date currentTimestamp) {
            this.currentTimestamp = currentTimestamp;
        }

        public Long getTimeDiff() {
            return timeDiff;
        }

        public void setTimeDiff(Long timeDiff) {
            this.timeDiff = timeDiff;
        }

        public static final class Builder {

            private String hash;
            private Long height;
            private Date timestamp;
            private String parentHash;
            private Account miner;
            private Long txnCnt;
            private Long gasLimit;
            private String avgGasPrice;
            private String gasReward;
            private Date currentTimestamp;
            private Long timeDiff;
            private Long maxHeight;

            public Builder() {
            }

            public Builder hash(String val) {
                hash = val;
                return this;
            }

            public Builder height(Long val) {
                height = val;
                return this;
            }

            public Builder timestamp(Date val) {
                timestamp = val;
                return this;
            }

            public Builder parentHash(String val) {
                parentHash = val;
                return this;
            }

            public Builder miner(Account val) {
                miner = val;
                return this;
            }

            public Builder txnCnt(Long val) {
                txnCnt = val;
                return this;
            }

            public Builder gasLimit(Long val) {
                gasLimit = val;
                return this;
            }

            public Builder avgGasPrice(String val) {
                avgGasPrice = val;
                return this;
            }

            public Builder gasReward(String val) {
                gasReward = val;
                return this;
            }

            public Builder currentTimestamp(Date val) {
                currentTimestamp = val;
                return this;
            }

            public Builder timeDiff(Long val) {
                timeDiff = val;
                return this;
            }

            public Builder maxHeight(Long val) {
                maxHeight = val;
                return this;
            }

            public Block build() {
                return new Block(this);
            }
        }
    }
}
