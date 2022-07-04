package com.xjtlu.monitor.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xjtlu.monitor.pojo.*;
import com.xjtlu.monitor.pojo.DTO.ApiScanResultDTO;
import com.xjtlu.monitor.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
public class TransactionService {

    @Autowired
    private ApiScanResultService apiScanResultService;

    @Autowired
    private MonitorAddressService monitorAddressService;

    @Autowired
    private ChainService chainService;

    @Autowired
    private MethodService methodService;

    @Autowired
    private ContractAddressService contractAddressService;

    public void doMonitor() {
        HashMap<String, String> aliasMap = new HashMap<>();
        List<Method> methods = methodService.list();
        for (Method method : methods) {
            aliasMap.put(method.getMethodInput(), method.getMethodName());
        }

        List<ContractAddress> contractAddresses = contractAddressService.list();
        for (ContractAddress contractAddress : contractAddresses) {
            aliasMap.put(contractAddress.getAddress(), contractAddress.getContractName());
        }

        List<MonitorAddress> monitorAddresses = monitorAddressService.list();
        for (MonitorAddress monitorAddress : monitorAddresses) {
            aliasMap.put(monitorAddress.getAddress(), monitorAddress.getComment());
        }

        List<Chain> chains = chainService.list();

        RequestService requestService = new RequestService();

        for (MonitorAddress monitorAddress : monitorAddresses) {
            for (Chain chain : chains) {
                String url = requestService.buildUrl(monitorAddress.getAddress(), chain);
                try {
                    BaseResponse response = requestService.getBaseResponse(url);
                    if ("1".equals(response.getStatus()) && response.getResult().size() > 0) {
                        List<ApiScanResultDTO> apiScanResultDTOList = response.getResult();
                        apiScanResultDTOList = getValidApiScanResultsDTOList(apiScanResultDTOList);
                        if (apiScanResultDTOList.size() == 0) {
                            continue;
                        }
                        for (ApiScanResultDTO apiScanResultDTO : apiScanResultDTOList) {
                            ApiScanResult apiScanResult;
                            {
                                apiScanResult = new ApiScanResult();
                                apiScanResult.setChainName(chain.getName());
                                apiScanResult.setChainId(chain.getId());
                                apiScanResult.setFromAddress(apiScanResultDTO.getFrom());
                                apiScanResult.setToAddress(apiScanResultDTO.getTo());
                                apiScanResult.setHash(apiScanResultDTO.getHash());
                                apiScanResult.setValue(apiScanResultDTO.getValue().divide(new BigInteger("1000000000000000000")).doubleValue());
                                apiScanResult.setTimeStamp(apiScanResultDTO.getTimeStamp().longValue());
                                apiScanResult.setBlockNumber(apiScanResultDTO.getBlockNumber());
                                apiScanResult.setInput(apiScanResultDTO.getInput());
                            }
                            apiScanResultService.save(apiScanResult);
                            sendTGMessage(apiScanResult, aliasMap);
                        }
                    }
                } catch (IOException e) {
                    log.info("createCache error: " + e.getMessage());
                }
            }
        }
    }

    public List<ApiScanResultDTO> getValidApiScanResultsDTOList(List<ApiScanResultDTO> apiScanResultDTOList) {
        ArrayList<ApiScanResultDTO> validApiScanResultDTOList = new ArrayList<>();
        for (ApiScanResultDTO apiScanResultDTO : apiScanResultDTOList) {
            // 判断是否有时间戳且时间戳在一小时内
            if ((apiScanResultDTO.getTimeStamp() == null) || (apiScanResultDTO.getTimeStamp() < (System.currentTimeMillis() / 1000 - 3600))) {
                return validApiScanResultDTOList;
            }
            // 判断是否已经存在
            QueryWrapper<ApiScanResult> query = new QueryWrapper<ApiScanResult>().eq("hash", apiScanResultDTO.getHash());
            List<ApiScanResult> list = apiScanResultService.list(query);
            if (list.size() == 0) {
                validApiScanResultDTOList.add(apiScanResultDTO);
            }
        }
        return validApiScanResultDTOList;
    }

    public void sendTGMessage(ApiScanResult apiScanResult, HashMap<String, String> aliasMap) {
        apiScanResult.setFromAddress(
                aliasMap.containsKey(apiScanResult.getFromAddress())
                ?aliasMap.get(apiScanResult.getFromAddress())
                :apiScanResult.getFromAddress());
        apiScanResult.setToAddress(
                aliasMap.containsKey(apiScanResult.getToAddress())
                ?aliasMap.get(apiScanResult.getToAddress())
                :apiScanResult.getToAddress());
        apiScanResult.setInput(
                aliasMap.containsKey(apiScanResult.getInput())
                ?aliasMap.get(apiScanResult.getInput())
                :apiScanResult.getInput());
        // timestamp to local time
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(new Date((Long) apiScanResult.getTimeStamp() * 1000L));
        apiScanResult.setTimeStamp(time);

        log.info("sendTGMessage: " + apiScanResult.toString());
    }
}
