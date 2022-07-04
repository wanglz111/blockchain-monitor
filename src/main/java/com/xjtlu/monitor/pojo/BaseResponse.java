package com.xjtlu.monitor.pojo;

import com.xjtlu.monitor.pojo.DTO.ApiScanResultDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse {

    private String status;
    private String message;
    private List<ApiScanResultDTO> result;

}
