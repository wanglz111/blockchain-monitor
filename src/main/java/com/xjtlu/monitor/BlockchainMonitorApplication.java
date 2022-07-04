package com.xjtlu.monitor;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.xjtlu.monitor"})
@MapperScan("com.xjtlu.monitor.mapper")
public class BlockchainMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlockchainMonitorApplication.class, args);
    }

}
