package org.jeecg.modules.buss.controller;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.constant.CommonConstant;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.PasswordUtil;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.buss.entity.BussBill;
import org.jeecg.modules.buss.mapper.BussBillMapper;
import org.jeecg.modules.buss.service.IBussBillService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @Author land
 * @since 2020-01-06
 */
@RestController
@RequestMapping("/buss/bill")
@Slf4j
public class BussBillController {


	@Autowired
	private IBussBillService service;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Result<IPage<BussBill>> queryPageList(BussBill bill, @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
												 @RequestParam(name="pageSize", defaultValue="10") Integer pageSize, HttpServletRequest req) {
		Result<IPage<BussBill>> result = new Result<IPage<BussBill>>();
		QueryWrapper<BussBill> queryWrapper = QueryGenerator.initQueryWrapper(bill, req.getParameterMap());
		Page<BussBill> page = new Page<BussBill>(pageNo, pageSize);
		IPage<BussBill> pageList = service.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}


	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Result<BussBill> add(@RequestBody JSONObject jsonObject) {
		Result<BussBill> result = new Result<BussBill>();
		try {
			BussBill bill = JSON.parseObject(jsonObject.toJSONString(), BussBill.class);
			bill.setCreateTime(DateTime.now().toString("yyyyMMddHHmmssSSS"));//设置创建时间
			bill.setDelFlag("0");
			service.save(bill);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.error500("操作失败");
		}
		return result;
	}


  @RequestMapping(value = "/edit", method = RequestMethod.PUT)
  public Result<BussBill> edit(@RequestBody JSONObject jsonObject) {
    Result<BussBill> result = new Result<BussBill>();
    try {
      BussBill bill = JSON.parseObject(jsonObject.toJSONString(), BussBill.class);
      service.saveOrUpdate(bill);
      result.success("修改成功!");

    } catch (Exception e) {
      log.error(e.getMessage(), e);
      result.error500("操作失败");
    }
    return result;
  }
}