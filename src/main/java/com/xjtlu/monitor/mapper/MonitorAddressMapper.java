package com.xjtlu.monitor.mapper;

import com.xjtlu.monitor.pojo.MonitorAddress;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
* @author wangluzhi
* @description 针对表【monitor_address】的数据库操作Mapper
* @createDate 2022-07-02 11:14:54
* @Entity com.xjtlu.monitor.pojo.MonitorAddress
*/
public interface MonitorAddressMapper extends BaseMapper<MonitorAddress> {

}




