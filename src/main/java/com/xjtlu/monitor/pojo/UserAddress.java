package com.xjtlu.monitor.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserAddress {
    public Integer id;
    public String address;
    public String comment;
}
