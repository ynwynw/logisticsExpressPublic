package com.cl.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import com.cl.utils.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cl.annotation.IgnoreAuth;
import com.cl.annotation.SysLog;

import com.cl.entity.KaoqindakaEntity;
import com.cl.entity.view.KaoqindakaView;

import com.cl.service.KaoqindakaService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.EncryptUtil;
import com.cl.utils.MPUtil;
import com.cl.utils.MapUtils;
import com.cl.utils.CommonUtil;

/**
 * 考勤打卡
 * 后端接口
 * @author 
 * @email 
 * @date 2025-05-15 21:38:20
 */
@RestController
@RequestMapping("/kaoqindaka")
public class KaoqindakaController {
    @Autowired
    private KaoqindakaService kaoqindakaService;









    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,KaoqindakaEntity kaoqindaka,
                                                                                                                            HttpServletRequest request){
                    String tableName = request.getSession().getAttribute("tableName").toString();
                                            if(tableName.equals("yuangong")) {
                    kaoqindaka.setYuangongzhanghao((String)request.getSession().getAttribute("username"));
                                    }
                                                                                                                                                                                                            EntityWrapper<KaoqindakaEntity> ew = new EntityWrapper<KaoqindakaEntity>();
                                                                                                                                                                                        
        
        
        PageUtils page = kaoqindakaService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, kaoqindaka), params), params));
        return R.ok().put("data", page);
    }







    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,KaoqindakaEntity kaoqindaka,
		HttpServletRequest request){
        EntityWrapper<KaoqindakaEntity> ew = new EntityWrapper<KaoqindakaEntity>();

		PageUtils page = kaoqindakaService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, kaoqindaka), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( KaoqindakaEntity kaoqindaka){
       	EntityWrapper<KaoqindakaEntity> ew = new EntityWrapper<KaoqindakaEntity>();
      	ew.allEq(MPUtil.allEQMapPre( kaoqindaka, "kaoqindaka")); 
        return R.ok().put("data", kaoqindakaService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(KaoqindakaEntity kaoqindaka){
        EntityWrapper< KaoqindakaEntity> ew = new EntityWrapper< KaoqindakaEntity>();
 		ew.allEq(MPUtil.allEQMapPre( kaoqindaka, "kaoqindaka")); 
		KaoqindakaView kaoqindakaView =  kaoqindakaService.selectView(ew);
		return R.ok("查询考勤打卡成功").put("data", kaoqindakaView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        KaoqindakaEntity kaoqindaka = kaoqindakaService.selectById(id);
		kaoqindaka = kaoqindakaService.selectView(new EntityWrapper<KaoqindakaEntity>().eq("id", id));
        return R.ok().put("data", kaoqindaka);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        KaoqindakaEntity kaoqindaka = kaoqindakaService.selectById(id);
		kaoqindaka = kaoqindakaService.selectView(new EntityWrapper<KaoqindakaEntity>().eq("id", id));
        return R.ok().put("data", kaoqindaka);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    @SysLog("新增考勤打卡")
    public R save(@RequestBody KaoqindakaEntity kaoqindaka, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(kaoqindaka);
        kaoqindakaService.insert(kaoqindaka);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @SysLog("新增考勤打卡")
    @RequestMapping("/add")
    public R add(@RequestBody KaoqindakaEntity kaoqindaka, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(kaoqindaka);
        kaoqindakaService.insert(kaoqindaka);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改考勤打卡")
    public R update(@RequestBody KaoqindakaEntity kaoqindaka, HttpServletRequest request){
        //ValidatorUtils.validateEntity(kaoqindaka);
        kaoqindakaService.updateById(kaoqindaka);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除考勤打卡")
    public R delete(@RequestBody Long[] ids){
        kaoqindakaService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	









}
