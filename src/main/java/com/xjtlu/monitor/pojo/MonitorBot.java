package com.xjtlu.monitor.pojo;

import com.xjtlu.monitor.constant.ApiToken;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


import static com.xjtlu.monitor.constant.ApiToken.TG_BOT_TOKEN;

public class MonitorBot extends TelegramLongPollingBot {
    public MonitorBot(DefaultBotOptions options) {
        super(options);
    }

    @Override
    public String getBotUsername() {
        return "Monitor-Bot";
    }

    @Override
    public String getBotToken() {
        return TG_BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
            message.setChatId(update.getMessage().getChatId().toString());
            message.setText(update.getMessage().getText());

            try {
                execute(message);// Call method to send the message
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean sendTGMonitorMessage(String messageText) {
        SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
        message.setChatId(ApiToken.USER_CHAT_ID);
        message.setText(messageText);
        try {
            execute(message);// Call method to send the message
            return true;
        } catch (TelegramApiException e) {
            e.printStackTrace();
            return false;
        }
    }


}
