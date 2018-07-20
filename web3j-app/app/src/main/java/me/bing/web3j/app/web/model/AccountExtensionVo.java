package me.bing.web3j.app.web.model;

import java.util.ArrayList;
import java.util.List;
import me.bing.web3j.app.web.model.AccountExtensionVo.AccountExtension;
import me.bing.web3j.app.web.model.AccountVo.Account;
import me.bing.web3j.app.web.model.BlockVo.Block;
import me.bing.web3j.app.web.model.TransactionVo.Transaction;

/**
 * @author bing on 2018/7/18
 */
public class AccountExtensionVo extends ResponseVo<AccountExtension> {

    public static class AccountExtension {

        private Account address;
        private long pendingTxCnt;
        private long txCnt;
        private List<Transaction> txList;
        private long mintedBlkCnt = 0;
        private List<Block> mintedBlkList = new ArrayList<>();

        private AccountExtension(Builder builder) {
            setAddress(builder.address);
            setPendingTxCnt(builder.pendingTxCnt);
            setTxCnt(builder.txCnt);
            setTxList(builder.txList);
            setMintedBlkCnt(builder.mintedBlkCnt);
            setMintedBlkList(builder.mintedBlkList);
        }

        public Account getAddress() {
            return address;
        }

        public void setAddress(Account address) {
            this.address = address;
        }

        public long getPendingTxCnt() {
            return pendingTxCnt;
        }

        public void setPendingTxCnt(long pendingTxCnt) {
            this.pendingTxCnt = pendingTxCnt;
        }

        public long getTxCnt() {
            return txCnt;
        }

        public void setTxCnt(long txCnt) {
            this.txCnt = txCnt;
        }

        public List<Transaction> getTxList() {
            return txList;
        }

        public void setTxList(List<Transaction> txList) {
            this.txList = txList;
        }

        public long getMintedBlkCnt() {
            return mintedBlkCnt;
        }

        public void setMintedBlkCnt(long mintedBlkCnt) {
            this.mintedBlkCnt = mintedBlkCnt;
        }

        public List<Block> getMintedBlkList() {
            return mintedBlkList;
        }

        public void setMintedBlkList(List<Block> mintedBlkList) {
            this.mintedBlkList = mintedBlkList;
        }

        public static final class Builder {

            private Account address;
            private long pendingTxCnt;
            private long txCnt;
            private List<Transaction> txList;
            private long mintedBlkCnt;
            private List<Block> mintedBlkList;

            public Builder() {
            }

            public Builder address(Account val) {
                address = val;
                return this;
            }

            public Builder pendingTxCnt(long val) {
                pendingTxCnt = val;
                return this;
            }

            public Builder txCnt(long val) {
                txCnt = val;
                return this;
            }

            public Builder txList(List<Transaction> val) {
                txList = val;
                return this;
            }

            public Builder mintedBlkCnt(long val) {
                mintedBlkCnt = val;
                return this;
            }

            public Builder mintedBlkList(List<Block> val) {
                mintedBlkList = val;
                return this;
            }

            public AccountExtension build() {
                return new AccountExtension(this);
            }
        }
    }
}
