package org.jeecg.modules.buss.service.impl;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;

import org.jeecg.modules.buss.entity.BussBill;
import org.jeecg.modules.buss.mapper.BussBillMapper;
import org.jeecg.modules.buss.service.IBussBillService;

import org.springframework.stereotype.Service;


@Service
@Slf4j
@DS("jetendb")
public class BussBillServiceImpl extends ServiceImpl<BussBillMapper, BussBill> implements IBussBillService {
}