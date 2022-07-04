package com.xjtlu.monitor;


import com.xjtlu.monitor.pojo.ApiScanResult;
import com.xjtlu.monitor.pojo.BaseResponse;
import com.xjtlu.monitor.pojo.Chain;
import com.xjtlu.monitor.pojo.DTO.ApiScanResultDTO;
import com.xjtlu.monitor.service.ChainService;
import com.xjtlu.monitor.service.RequestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@SpringBootTest
public class TestHttpRequest {

    private final RequestService requestService = new RequestService();


    @Autowired
    private ChainService chainService;

    @Test
    public void test() {
        List<Chain> chains = chainService.list();
        String address = "0x6030e32063685f2001305ffA69dFB790472906dA";

        for (Chain chain : chains) {
            String url = requestService.buildUrl(address, chain);

            try {
                BaseResponse apiResult = requestService.getBaseResponse(url);
                System.out.println(apiResult);
                List<ApiScanResultDTO> results = apiResult.getResult();

                if (results.size() != 0) {
                    System.out.println(results.get(0));
                } else {
                    System.out.println(chain.getName().concat(" not found"));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//        try {
//            String url = "https://api.polygonscan.com/api?&endblock=99999999&address=0x6030e32063685f2001305ffA69dFB790472906dA&apikey=NFCZK6BYTMYGN7J7DPKCJU4REA1WRN52PQ&module=account&action=txlist&startblock=0&sort=asc";
//            BaseResponse response = requestService.getBaseResponse(url);
//            List<ApiScanResult> results = response.getApiScanResult();
//            for (ApiScanResult result : results) {
//                System.out.println(result.getFrom());
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }






    }
}
