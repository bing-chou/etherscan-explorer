package me.bing.web3j.app.web.model;

import me.bing.web3j.app.web.model.AccountVo.Account;

public class AccountVo extends ResponseVo<Account> {

    @Override
    public void setData(Account data) {
        super.setData(data);
    }

    public static class Account {

        private Integer rank;
        private String hash;
        private String alias;
        private String balance;
        private String percentage;
        private int txCnt;
        private int nonce;
        private Integer type;

        private Account(Builder builder) {
            setRank(builder.rank);
            setHash(builder.hash);
            setAlias(builder.alias);
            setBalance(builder.balance);
            setPercentage(builder.percentage);
            setTxCnt(builder.txCnt);
            setNonce(builder.nonce);
            setType(builder.type);
        }


        public int getNonce() {
            return nonce;
        }

        public void setNonce(int nonce) {
            this.nonce = nonce;
        }

        public Integer getRank() {
            return rank;
        }

        public void setRank(Integer rank) {
            this.rank = rank;
        }

        public String getHash() {
            return hash;
        }

        public void setHash(String hash) {
            this.hash = hash;
        }

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getPercentage() {
            return percentage;
        }

        public void setPercentage(String percentage) {
            this.percentage = percentage;
        }

        public int getTxCnt() {
            return txCnt;
        }

        public void setTxCnt(int txCnt) {
            this.txCnt = txCnt;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public static final class Builder {

            private Integer rank;
            private String hash;
            private String alias;
            private String balance;
            private String percentage;
            private int txCnt;
            private int nonce;
            private Integer type;

            public Builder() {
            }

            public Builder rank(Integer val) {
                rank = val;
                return this;
            }

            public Builder hash(String val) {
                hash = val;
                return this;
            }

            public Builder alias(String val) {
                alias = val;
                return this;
            }

            public Builder balance(String val) {
                balance = val;
                return this;
            }

            public Builder percentage(String val) {
                percentage = val;
                return this;
            }

            public Builder txCnt(int val) {
                txCnt = val;
                return this;
            }

            public Builder nonce(int val) {
                nonce = val;
                return this;
            }

            public Builder type(Integer val) {
                type = val;
                return this;
            }

            public Account build() {
                return new Account(this);
            }
        }
    }
}
