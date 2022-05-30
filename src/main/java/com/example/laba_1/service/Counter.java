package com.example.laba_1.service;

import org.springframework.stereotype.Service;

@Service
public class Counter {
    private int value;

    public synchronized int getCount() {
        return ++value;
    }
}
