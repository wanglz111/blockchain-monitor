package com.xjtlu.monitor.service;

import com.xjtlu.monitor.constant.ApiToken;
import okhttp3.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SendMessageService {

    @Autowired
    RequestService requestService;

    public void sendMessage(String message) {
        String url = "https://api.telegram.org/bot" + ApiToken.TG_BOT_TOKEN + "/sendMessage?";
        url += "chat_id=" + ApiToken.USER_CHAT_ID;
        url += "&parse_mode=MarkdownV2";
        url += "&text=" + message;
        Request request = requestService.getRequest(url);
//        String s = request.body().toString();
        System.out.println(request.toString());
    }



}
