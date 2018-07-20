package me.bing.web3j.app.web.model;

import java.util.List;
import me.bing.web3j.app.web.model.ListTransactionByPageVo.TransactionByPage;
import me.bing.web3j.app.web.model.TransactionVo.Transaction;

/**
 * @author bing on 2018/7/18
 */
public class ListTransactionByPageVo extends ResponseVo<TransactionByPage> {

    public static class TransactionByPage {

        private List<Transaction> txnList;
        private String type;
        private long txnCnt;
        private int currentPage;
        private int totalPage;
        private long maxDisplayCnt;

        private TransactionByPage(Builder builder) {
            setTxnList(builder.txnList);
            setType(builder.type);
            setTxnCnt(builder.txnCnt);
            setCurrentPage(builder.currentPage);
            setTotalPage(builder.totalPage);
            setMaxDisplayCnt(builder.maxDisplayCnt);
        }

        public List<Transaction> getTxnList() {
            return txnList;
        }

        public void setTxnList(List<Transaction> txnList) {
            this.txnList = txnList;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public long getTxnCnt() {
            return txnCnt;
        }

        public void setTxnCnt(long txnCnt) {
            this.txnCnt = txnCnt;
        }

        public int getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public long getMaxDisplayCnt() {
            return maxDisplayCnt;
        }

        public void setMaxDisplayCnt(long maxDisplayCnt) {
            this.maxDisplayCnt = maxDisplayCnt;
        }

        public static final class Builder {

            private List<Transaction> txnList;
            private String type;
            private long txnCnt;
            private int currentPage;
            private int totalPage;
            private long maxDisplayCnt;

            public Builder() {
            }

            public Builder txnList(List<Transaction> val) {
                txnList = val;
                return this;
            }

            public Builder type(String val) {
                type = val;
                return this;
            }

            public Builder txnCnt(long val) {
                txnCnt = val;
                return this;
            }

            public Builder currentPage(int val) {
                currentPage = val;
                return this;
            }

            public Builder totalPage(int val) {
                totalPage = val;
                return this;
            }

            public Builder maxDisplayCnt(long val) {
                maxDisplayCnt = val;
                return this;
            }

            public TransactionByPage build() {
                return new TransactionByPage(this);
            }
        }
    }
}
