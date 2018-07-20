package me.bing.web3j.app.mapper.model;

public enum TransactionStatusEnum {
    FAIL(0),
    SUCCESS(1),
    PENDING(2);

    int value;

    TransactionStatusEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
