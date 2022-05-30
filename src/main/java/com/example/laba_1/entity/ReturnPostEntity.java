package com.example.laba_1.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.collections4.map.LRUMap;

public class ReturnPostEntity {
    @JsonProperty
    private LRUMap<Integer, BinaryDigit> returnMaplist;
    @JsonProperty
    private int max;
    @JsonProperty
    private int min;

    public void setReturnMaplist(LRUMap<Integer, BinaryDigit> returnMaplist) {
        this.returnMaplist = returnMaplist;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void setMin(int min) {
        this.min = min;
    }
}