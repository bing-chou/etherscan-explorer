package me.bing.web3j.app.web.model;

import java.util.List;

/**
 * @author bing on 2018/7/18
 */
public class ListAccountByPageVo extends ResponseVo<ListAccountByPageVo.AccountByPage> {

    public static class AccountByPage {

        private List<AccountVo.Account> addressList;
        private long totalAccountsCnt;
        private String totalBalance;
        private int page;
        private int totalPage;

        private AccountByPage(Builder builder) {
            setAddressList(builder.addressList);
            setTotalAccountsCnt(builder.totalAccountsCnt);
            setTotalBalance(builder.totalBalance);
            setPage(builder.page);
            setTotalPage(builder.totalPage);
        }

        public List<AccountVo.Account> getAddressList() {
            return addressList;
        }

        public void setAddressList(List<AccountVo.Account> addressList) {
            this.addressList = addressList;
        }

        public long getTotalAccountsCnt() {
            return totalAccountsCnt;
        }

        public void setTotalAccountsCnt(long totalAccountsCnt) {
            this.totalAccountsCnt = totalAccountsCnt;
        }

        public String getTotalBalance() {
            return totalBalance;
        }

        public void setTotalBalance(String totalBalance) {
            this.totalBalance = totalBalance;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public static final class Builder {

            private List<AccountVo.Account> addressList;
            private long totalAccountsCnt;
            private String totalBalance;
            private int page;
            private int totalPage;

            public Builder() {
            }

            public Builder addressList(List<AccountVo.Account> val) {
                addressList = val;
                return this;
            }

            public Builder totalAccountsCnt(long val) {
                totalAccountsCnt = val;
                return this;
            }

            public Builder totalBalance(String val) {
                totalBalance = val;
                return this;
            }

            public Builder page(int val) {
                page = val;
                return this;
            }

            public Builder totalPage(int val) {
                totalPage = val;
                return this;
            }

            public AccountByPage build() {
                return new AccountByPage(this);
            }
        }
    }
}
