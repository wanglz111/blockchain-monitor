package com.xjtlu.monitor;

import com.xjtlu.monitor.controller.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class TestMonitor {

    @Autowired
    TransactionService transactionService;

//    @Autowired
//    SendMessageService sendMessageService;

    @Test
    public void test() {
        transactionService.doMonitor();
    }

    @Test
    public void test2() {

//        sendMessageService.monitorBot.sendTGMonitorMessage("test");
    }


}
