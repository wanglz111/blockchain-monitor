package com.xjtlu.monitor.pojo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiScanResultDTO {
    private Integer blockNumber;
    private Integer timeStamp;
    private String hash;
    private String from;
    private String to;
    private BigInteger value;
    private String input;
}
