package com.xjtlu.monitor.service;

import com.xjtlu.monitor.pojo.MonitorBot;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

//@Service
public class SendMessageService {

    public MonitorBot monitorBot;

    public SendMessageService() throws TelegramApiException {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        DefaultBotOptions botOptions = new DefaultBotOptions();
        botOptions.setProxyHost("localhost");
        botOptions.setProxyPort(7890);
        botOptions.setProxyType(DefaultBotOptions.ProxyType.SOCKS5);

        try {
            botsApi.registerBot(new MonitorBot(botOptions));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SendMessageService sendMessageService = null;
        try {
            sendMessageService = new SendMessageService();
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        assert sendMessageService != null;
        sendMessageService.monitorBot.sendTGMonitorMessage("test");

    }
}
