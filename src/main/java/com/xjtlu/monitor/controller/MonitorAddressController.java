package com.xjtlu.monitor.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xjtlu.monitor.pojo.MonitorAddress;
import com.xjtlu.monitor.service.MonitorAddressService;
import com.xjtlu.monitor.util.MessageCode;
import com.xjtlu.monitor.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MonitorAddressController {

    @Autowired
    private MonitorAddressService monitorAddressService;

    @GetMapping("/monitor/address")
    public Result<List<MonitorAddress>> getMonitorAddress() {
        return Result.success(monitorAddressService.list());
    }

    @GetMapping("/monitor/add")
    public Result<MonitorAddress> addMonitorAddress(@RequestParam("monitorAddress") String address, @RequestParam("comment") String comment) {
        QueryWrapper<MonitorAddress> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("address", address);
        if (monitorAddressService.count(queryWrapper) > 0) {
            return Result.error(MessageCode.EXIST_ERROR);
        }
        MonitorAddress monitorAddress = new MonitorAddress();
        monitorAddress.setAddress(address);
        monitorAddress.setComment(comment);
        boolean save = monitorAddressService.save(monitorAddress);
        if (save) {
            return Result.success(monitorAddress);
        } else {
            return Result.error(MessageCode.SERVER_ERROR);
        }
    }

    @GetMapping("/monitor/delete")
    public Result<MonitorAddress> deleteMonitorAddress(@RequestParam("id") String id) {
        MonitorAddress monitorAddress = monitorAddressService.getById(Integer.getInteger(id));
        boolean remove = monitorAddressService.removeById(id);
        if (remove) {
            return Result.success(monitorAddress);
        } else {
            return Result.error(MessageCode.SERVER_ERROR);
        }
    }
}
