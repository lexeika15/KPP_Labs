package com.example.laba_1.entity;

public class ReturnEntity {
    private BinaryDigit digit;
    private int count;

    public ReturnEntity(BinaryDigit digit, int count) {
        this.digit = digit;
        this.count = count;
    }

    public BinaryDigit getDigit() {
        return digit;
    }

    public int getCount() {
        return count;
    }
}
