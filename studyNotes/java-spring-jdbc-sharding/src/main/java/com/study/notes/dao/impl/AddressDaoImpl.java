package com.study.notes.dao.impl;

import com.study.notes.example.Address;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class AddressDaoImpl {
    private Integer shardsNumber;

    public void setShardsNumber(Integer shardsNumber) {
        this.shardsNumber = shardsNumber;
    }

    public void addAdress(Address address) {
        //获取数据库位置
        DataSourceUtils.setPin4DivideDB(address.getPin());
        //获取表的位置
        Map<String, Object> params = DaoUtils.getTableShards(address.getPin(), shardsNumber);
        params.put("address", address);
        //TODO insert
    }
}
