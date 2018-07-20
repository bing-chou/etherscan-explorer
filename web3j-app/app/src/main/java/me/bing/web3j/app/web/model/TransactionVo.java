package me.bing.web3j.app.web.model;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Date;
import me.bing.web3j.app.mapper.model.TransactionStatusEnum;
import me.bing.web3j.app.web.model.AccountVo.Account;
import me.bing.web3j.app.web.model.BlockVo.Block;
import me.bing.web3j.app.web.model.TransactionVo.Transaction;
import org.apache.commons.lang3.StringUtils;

public class TransactionVo extends ResponseVo<Transaction> {

    private static final Decoder DECODER = Base64.getDecoder();

    @Override
    public void setData(Transaction data) {
        super.setData(data);
    }

    public static class Transaction {

        private String hash;
        private Block block;
        private Account from;
        private Account to;
        private Integer status;
        private String value;
        private Long nonce;
        private Date timestamp;
        private String type;
        private String gasPrice;
        private String gasLimit;
        private String gasUsed;
        private Date createdAt;
        private String data;
        private Date currentTimestamp;
        private Long timeDiff;
        private String contractAddress;
        private String executeError;

        private Transaction(Builder builder) {
            setHash(builder.hash);
            setBlock(builder.block);
            setFrom(builder.from);
            setTo(builder.to);
            setStatus(builder.status);
            setValue(builder.value);
            setNonce(builder.nonce);
            setTimestamp(builder.timestamp);
            setType(builder.type);
            setGasPrice(builder.gasPrice);
            setGasLimit(builder.gasLimit);
            setGasUsed(builder.gasUsed);
            setCreatedAt(builder.createdAt);
            setData(builder.data);
            setCurrentTimestamp(builder.currentTimestamp);
            setTimeDiff(builder.timeDiff);
            setContractAddress(builder.contractAddress);
            setExecuteError(builder.executeError);
        }

        public boolean pending() {
            return TransactionStatusEnum.PENDING.getValue() == getStatus();
        }

        public String getTxFee() {
            if (StringUtils.isEmpty(gasUsed) || StringUtils.isEmpty(gasPrice)) {
                return "";
            }
            return new BigDecimal(gasUsed).multiply(new BigDecimal(gasPrice)).stripTrailingZeros().toPlainString();
        }

        public Block getBlock() {
            return block;
        }

        public void setBlock(Block block) {
            this.block = block;
        }

        public Account getFrom() {
            return from;
        }

        public void setFrom(Account from) {
            this.from = from;
        }

        public Account getTo() {
            return to;
        }

        public void setTo(Account to) {
            this.to = to;
        }

        public String getHash() {
            return hash;
        }

        public void setHash(String hash) {
            this.hash = hash;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public Long getNonce() {
            return nonce;
        }

        public void setNonce(Long nonce) {
            this.nonce = nonce;
        }

        public Date getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(Date timestamp) {
            this.timestamp = timestamp;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getGasPrice() {
            return gasPrice;
        }

        public void setGasPrice(String gasPrice) {
            this.gasPrice = gasPrice;
        }

        public String getGasLimit() {
            return gasLimit;
        }

        public void setGasLimit(String gasLimit) {
            this.gasLimit = gasLimit;
        }

        public String getGasUsed() {
            return gasUsed;
        }

        public void setGasUsed(String gasUsed) {
            this.gasUsed = gasUsed;
        }

        public Date getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(Date createdAt) {
            this.createdAt = createdAt;
        }

        public String getData() throws UnsupportedEncodingException {
            //return StringUtils.isNotEmpty(this.data) ? new String(DECODER.decode(this.data), "UTF-8") : "";
            return this.data;
        }

        public void setData(String data) {
            this.data = data;
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

        public String getContractAddress() {
            return contractAddress;
        }

        public void setContractAddress(String contractAddress) {
            this.contractAddress = contractAddress;
        }

        public String getExecuteError() {
            return executeError;
        }

        public void setExecuteError(String executeError) {
            this.executeError = executeError;
        }

        public static final class Builder {

            private String hash;
            private Block block;
            private Account from;
            private Account to;
            private Integer status;
            private String value;
            private Long nonce;
            private Date timestamp;
            private String type;
            private String gasPrice;
            private String gasLimit;
            private String gasUsed;
            private Date createdAt;
            private String data;
            private Date currentTimestamp;
            private Long timeDiff;
            private String contractAddress;
            private String executeError;

            public Builder() {
            }

            public Builder hash(String val) {
                hash = val;
                return this;
            }

            public Builder block(Block val) {
                block = val;
                return this;
            }

            public Builder from(Account val) {
                from = val;
                return this;
            }

            public Builder to(Account val) {
                to = val;
                return this;
            }

            public Builder status(Integer val) {
                status = val;
                return this;
            }

            public Builder value(String val) {
                value = val;
                return this;
            }

            public Builder nonce(Long val) {
                nonce = val;
                return this;
            }

            public Builder timestamp(Date val) {
                timestamp = val;
                return this;
            }

            public Builder type(String val) {
                type = val;
                return this;
            }

            public Builder gasPrice(String val) {
                gasPrice = val;
                return this;
            }

            public Builder gasLimit(String val) {
                gasLimit = val;
                return this;
            }

            public Builder gasUsed(String val) {
                gasUsed = val;
                return this;
            }

            public Builder createdAt(Date val) {
                createdAt = val;
                return this;
            }

            public Builder data(String val) {
                data = val;
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

            public Builder contractAddress(String val) {
                contractAddress = val;
                return this;
            }

            public Builder executeError(String val) {
                executeError = val;
                return this;
            }

            public Transaction build() {
                return new Transaction(this);
            }
        }
    }
}
