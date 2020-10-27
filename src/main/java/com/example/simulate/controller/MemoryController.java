package com.example.simulate.controller;

import com.example.simulate.bean.BigObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@RequestMapping("memory")
public class MemoryController {

    private static final Logger log = LoggerFactory.getLogger(MemoryController.class);

    private static List<BigObject> list = new ArrayList<>();

    @GetMapping("bigObject")
    @ResponseBody
    public String bigObject(int size){
        log.info("bigObject start size = {}", size);
        if (size == 0) {
            size = 1024;
        }
        BigObject bigObject = new BigObject(size);
        list.add(bigObject);

        log.info("bigObject end");
        return "ok";
    }

}
