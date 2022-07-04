package com.xjtlu.monitor;

import com.xjtlu.monitor.pojo.MonitorAddress;
import com.xjtlu.monitor.service.MonitorAddressService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class BlockchainMonitorApplicationTests {

    @Resource
    private MonitorAddressService monitorAddressService;
    @Test
    void contextLoads() {
        List<MonitorAddress> addresses = monitorAddressService.list();
        for (MonitorAddress address : addresses) {
            System.out.println(address);
        }
    }

}
