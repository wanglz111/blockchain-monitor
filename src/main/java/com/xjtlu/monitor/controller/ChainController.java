package com.xjtlu.monitor.controller;

import com.xjtlu.monitor.pojo.Chain;
import com.xjtlu.monitor.service.ChainService;
import com.xjtlu.monitor.util.MessageCode;
import com.xjtlu.monitor.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RestController
public class ChainController {

    @Autowired
    private ChainService chainService;

    @GetMapping("/chainLists")
    public Result<Object> index() {

        List<Chain> chains = chainService.list();
        return Result.success(chains);
    }
}
