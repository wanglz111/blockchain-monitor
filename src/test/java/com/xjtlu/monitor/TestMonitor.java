package com.xjtlu.monitor;

import com.xjtlu.monitor.controller.TransactionService;
import com.xjtlu.monitor.service.SendMessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@SpringBootTest
public class TestMonitor {

    @Autowired
    TransactionService transactionService;

    @Autowired
    SendMessageService sendMessageService;

    @Test
    public void test() {
        transactionService.doMonitor();
    }

    @Test
    public void test2() {

        sendMessageService.monitorBot.sendTGMonitorMessage("test");
    }


}
