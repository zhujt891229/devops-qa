package com.zjt.qas.service;

import com.github.pagehelper.PageInfo;
import com.zjt.qas.model.entity.Distributor;

public interface DistributorService {

    PageInfo<Distributor> getDistributorList(String cellphone, String address, Integer pageNum, Integer pageSize);

    int saveDistributor(Distributor distributor);
}
