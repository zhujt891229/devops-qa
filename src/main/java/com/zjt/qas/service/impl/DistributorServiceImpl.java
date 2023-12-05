package com.zjt.qas.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zjt.qas.mapper.DistributorMapper;
import com.zjt.qas.model.entity.Distributor;
import com.zjt.qas.service.DistributorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DistributorServiceImpl implements DistributorService {

    @Resource
    private DistributorMapper distributorMapper;

    @Override
    public PageInfo<Distributor> getDistributorList(String cellphone, String address, Integer pageNum, Integer pageSize) {
        Page<Distributor> page = PageHelper.startPage(pageNum, pageSize)
                .doSelectPage(() -> distributorMapper.selectByParams(cellphone,address));
        return new PageInfo<>(page);
    }

    @Override
    public int saveDistributor(Distributor distributor) {
        Integer id = distributor.getId();
        int affected;
        if(null==id){
            affected = distributorMapper.insert(distributor);
        }else{
            affected = distributorMapper.update(distributor);
        }
        return affected;
    }
}
