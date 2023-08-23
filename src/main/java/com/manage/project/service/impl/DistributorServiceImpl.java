package com.manage.project.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.manage.project.mapper.DistributorMapper;
import com.manage.project.model.Distributor;
import com.manage.project.service.DistributorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DistributorServiceImpl implements DistributorService {

    @Resource
    private DistributorMapper distributorMapper;

    @Override
    public PageInfo<Distributor> getDistributorList(String cellphone, String address,Integer pageNum, Integer pageSize) {
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
