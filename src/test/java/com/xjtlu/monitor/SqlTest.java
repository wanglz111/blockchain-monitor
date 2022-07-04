package com.xjtlu.monitor;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xjtlu.monitor.pojo.ApiScanResult;
import com.xjtlu.monitor.pojo.BaseResponse;
import com.xjtlu.monitor.pojo.Chain;
import com.xjtlu.monitor.pojo.MonitorAddress;
import com.xjtlu.monitor.service.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import static com.xjtlu.monitor.constant.ApiToken.*;

@SpringBootTest
@Slf4j
public class SqlTest {

    @Autowired
    private ChainService chainService;

    @Autowired
    private MonitorAddressService monitorAddressService;

    @Autowired
    private ApiScanResultService apiScanResultService;

    @Autowired
    private MethodService methodService;

    private static final RequestService requestService = new RequestService();

    @Test
    public void testInsert() {
        //            "Ethereum",
//            "https://api.etherscan.io/api?",
//            ETH_API_TOKEN,
//            "https://debank.com/profile/",
//            "https://etherscan.io/tx/",
//            "https://etherscan.io/address/"
        Chain ETH = new Chain(1, "ETH", "https://api.etherscan.io/api", ETH_API_TOKEN, "https://debank.com/profile/", "https://etherscan.io/tx/", "https://etherscan.io/address/");
        Chain BSC = new Chain(56, "BSC", "https://api.bscscan.com/api", BSC_API_TOKEN, "https://debank.com/profile/", "https://bscscan.com/tx/", "https://bscscan.com/address/");
        Chain FTM = new Chain(250, "FTM", "https://api.ftmscan.com/api", FTM_API_TOKEN, "https://debank.com/profile/", "https://ftmscan.com/tx/", "https://ftmscan.com/address/");
        Chain MATIC = new Chain(137, "MATIC", "https://api.polygonscan.com/api", MATIC_API_TOKEN, "https://debank.com/profile/", "https://polygonscan.com/tx/", "https://polygonscan.com/address/");
        Chain AVAX = new Chain(138, "AVAX", "https://api.snowtrace.io/api", AVAX_API_TOKEN, "https://debank.com/profile/", "https://snowtrace.io/tx/", "https://snowtrace.io/address/");
        Chain[] chains = {ETH, BSC, FTM, MATIC, AVAX};
//        chainService.saveBatch(Arrays.asList(chains));
    }

    @Test
    public void testSelect() {
        List<Chain> chains = chainService.list();
        for (Chain chain : chains) {
            log.info("{}", chain);
        }
    }

    @Test
    public void apiResultTest() throws IOException {
        List<MonitorAddress> addresses = monitorAddressService.list();
        for (MonitorAddress address : addresses) {
            String userAddress = address.getAddress();
            for (Chain chain : chainService.list()) {
                String url = requestService.buildUrl(userAddress, chain);
                try {
                    BaseResponse response = requestService.getBaseResponse(url);
                    response.getResult().forEach(result -> {
                        // 判断hash是否存在
                        QueryWrapper<ApiScanResult> query = new QueryWrapper<ApiScanResult>().eq("hash", result.getHash());
                        List<ApiScanResult> list = apiScanResultService.list(query);
                        if (list.size() == 0) {
                            ApiScanResult apiScanResult;
                            {
                                apiScanResult = new ApiScanResult();
                                apiScanResult.setChainName(chain.getName());
                                apiScanResult.setChainId(chain.getId());
                                apiScanResult.setFromAddress(result.getFrom());
                                apiScanResult.setToAddress(result.getTo());
                                apiScanResult.setHash(result.getHash());
                                apiScanResult.setValue(result.getValue().divide(new BigInteger("1000000000000000000")).doubleValue());
                                apiScanResult.setTimeStamp(result.getTimeStamp());
                                apiScanResult.setBlockNumber(result.getBlockNumber());
                                apiScanResult.setInput(result.getInput());
                            }
                            apiScanResultService.save(apiScanResult);
                            log.info("Add one record: {}", result.getTo());
                        }
                    });
                } catch (Exception e) {
                    log.error("{}", e);
                }
            }
        }
    }

    @Test
    public void methodTest() {
        System.out.println(methodService.list());
    }

}
