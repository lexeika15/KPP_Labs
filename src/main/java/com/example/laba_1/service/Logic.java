package com.example.laba_1.service;

import org.springframework.stereotype.Service;

@Service
public class Logic {

    public static String convert(int number)
    {
        if (number < 0)
        {
            return "-" + Integer.toBinaryString(-number);
        }
        return Integer.toBinaryString(number);
    }
}
