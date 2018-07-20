package me.bing.web3j.app.mapper.model;

import java.util.Objects;

/**
 * @author bing on 2018/7/4
 */
public class Account {

    private String hash;
    private int type;
    private long balance;
    private int nonce;

    public Account() {}

    private Account(Builder builder) {
        setHash(builder.hash);
        setType(builder.type);
        setBalance(builder.balance);
        setNonce(builder.nonce);
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public int getNonce() {
        return nonce;
    }

    public void setNonce(int nonce) {
        this.nonce = nonce;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return Objects.equals(hash, account.hash);
    }

    @Override
    public int hashCode() {

        return Objects.hash(hash);
    }

    public static final class Builder {

        private String hash;
        private int type;
        private long balance;
        private int nonce;

        public Builder() {
        }

        public Builder hash(String val) {
            hash = val;
            return this;
        }

        public Builder type(int val) {
            type = val;
            return this;
        }

        public Builder balance(long val) {
            balance = val;
            return this;
        }

        public Builder nonce(int val) {
            nonce = val;
            return this;
        }

        public Account build() {
            return new Account(this);
        }
    }
}
