package com.example.simulate.controller;

import com.example.simulate.bean.BigObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.ConcurrentHashMap;

@Controller
@RequestMapping("memory")
public class MemoryController {

    private static final Logger log = LoggerFactory.getLogger(MemoryController.class);

    private static ConcurrentHashMap<Integer,BigObject> map = new ConcurrentHashMap();

    @GetMapping("bigObject")
    public void bigObject(int size){
        log.info("bigObject start");
        if (size == 0) {
            size = 1024;
        }
        BigObject bigObject = new BigObject(size);
        map.put(size, bigObject);

        log.info("bigObject end");
    }

}
