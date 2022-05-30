package com.example.laba_1.controller;

import com.example.laba_1.cache.CrunchifyInMemoryCache;
import com.example.laba_1.entity.BinaryDigit;
import com.example.laba_1.entity.ReturnEntity;
import com.example.laba_1.entity.ReturnPostEntity;
import com.example.laba_1.service.Counter;
import com.example.laba_1.service.Logic;
import org.apache.commons.collections4.map.LRUMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.OptionalInt;

@RestController
public class ConverterController {

    private final CrunchifyInMemoryCache<Integer, BinaryDigit> cacheCheck = new CrunchifyInMemoryCache<Integer, BinaryDigit>(2000, 5000, 6);

    @Autowired
    private Counter counter;

    @GetMapping("/convert")
    public ResponseEntity<ReturnEntity> converterJson(@RequestParam int number) {
        BinaryDigit digit = cacheCheck.get(number);
        if (digit != null) {
            return ResponseEntity.ok(new ReturnEntity(digit, counter.getCount()));
        }
        else
        {
            digit = new BinaryDigit(Logic.convert(number));
            cacheCheck.put(number, digit);
            return ResponseEntity.ok(new ReturnEntity(digit, counter.getCount()));
        }
    }

    @PostMapping("/postList")
    public ResponseEntity<?> binaryBulkParams(@RequestBody List<Integer> bodyList) {
        ReturnPostEntity retObj = new ReturnPostEntity();
        if(bodyList.isEmpty()){
            return new ResponseEntity<>(retObj, HttpStatus.OK);
        }
        LRUMap<Integer, BinaryDigit> returnMap = new LRUMap<>(bodyList.size());
        bodyList.forEach((currentElement) -> {
                returnMap.put(currentElement, new BinaryDigit(Logic.convert(currentElement)));
        });
        retObj.setReturnMaplist(returnMap);
        OptionalInt max = bodyList.stream().mapToInt(Integer::intValue).max();
        if(max.isPresent()) {
            retObj.setMax(max.getAsInt());
        }
        OptionalInt min = bodyList.stream().mapToInt(Integer::intValue).min();
        if(min.isPresent()) {
            retObj.setMin(min.getAsInt());
        }

        return new ResponseEntity<>(retObj, HttpStatus.OK);
    }

}