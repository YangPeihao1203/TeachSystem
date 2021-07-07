package org.wumbuk.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wumbuk.dao.DepatureMapper;
import org.wumbuk.entity.Depature;
import org.wumbuk.service.DepatureService;

import java.util.List;

@Service("depatureService")
public class DepatureServiceImpl implements DepatureService {


    @Autowired
    DepatureMapper depatureMapper;


    @Override
    public List<Depature> getAlldapatures() {
        return depatureMapper.selectAll();
    }
}
