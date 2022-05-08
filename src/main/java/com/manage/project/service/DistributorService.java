package com.manage.project.service;

import com.github.pagehelper.PageInfo;
import com.manage.project.model.Distributor;

public interface DistributorService {

    PageInfo<Distributor> getDistributorList(String cellphone,String address,Integer pageNum, Integer pageSize);

    int saveDistributor(Distributor distributor);
}
