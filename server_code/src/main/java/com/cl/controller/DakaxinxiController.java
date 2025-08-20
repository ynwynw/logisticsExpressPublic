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

import com.cl.entity.DakaxinxiEntity;
import com.cl.entity.view.DakaxinxiView;

import com.cl.service.DakaxinxiService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.EncryptUtil;
import com.cl.utils.MPUtil;
import com.cl.utils.MapUtils;
import com.cl.utils.CommonUtil;

/**
 * 打卡信息
 * 后端接口
 * @author 
 * @email 
 * @date 2025-05-15 21:38:20
 */
@RestController
@RequestMapping("/dakaxinxi")
public class DakaxinxiController {
    @Autowired
    private DakaxinxiService dakaxinxiService;









    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,DakaxinxiEntity dakaxinxi,
                                                                                                                            HttpServletRequest request){
                    String tableName = request.getSession().getAttribute("tableName").toString();
                                            if(tableName.equals("yuangong")) {
                    dakaxinxi.setYuangongzhanghao((String)request.getSession().getAttribute("username"));
                                    }
                                                                                                                                                                                                            EntityWrapper<DakaxinxiEntity> ew = new EntityWrapper<DakaxinxiEntity>();
                                                                                                                                                                                        
        
        
        PageUtils page = dakaxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, dakaxinxi), params), params));
        return R.ok().put("data", page);
    }







    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,DakaxinxiEntity dakaxinxi,
		HttpServletRequest request){
        EntityWrapper<DakaxinxiEntity> ew = new EntityWrapper<DakaxinxiEntity>();

		PageUtils page = dakaxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, dakaxinxi), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( DakaxinxiEntity dakaxinxi){
       	EntityWrapper<DakaxinxiEntity> ew = new EntityWrapper<DakaxinxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( dakaxinxi, "dakaxinxi")); 
        return R.ok().put("data", dakaxinxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(DakaxinxiEntity dakaxinxi){
        EntityWrapper< DakaxinxiEntity> ew = new EntityWrapper< DakaxinxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( dakaxinxi, "dakaxinxi")); 
		DakaxinxiView dakaxinxiView =  dakaxinxiService.selectView(ew);
		return R.ok("查询打卡信息成功").put("data", dakaxinxiView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        DakaxinxiEntity dakaxinxi = dakaxinxiService.selectById(id);
		dakaxinxi = dakaxinxiService.selectView(new EntityWrapper<DakaxinxiEntity>().eq("id", id));
        return R.ok().put("data", dakaxinxi);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        DakaxinxiEntity dakaxinxi = dakaxinxiService.selectById(id);
		dakaxinxi = dakaxinxiService.selectView(new EntityWrapper<DakaxinxiEntity>().eq("id", id));
        return R.ok().put("data", dakaxinxi);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    @SysLog("新增打卡信息")
    public R save(@RequestBody DakaxinxiEntity dakaxinxi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(dakaxinxi);
        dakaxinxiService.insert(dakaxinxi);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @SysLog("新增打卡信息")
    @RequestMapping("/add")
    public R add(@RequestBody DakaxinxiEntity dakaxinxi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(dakaxinxi);
        dakaxinxiService.insert(dakaxinxi);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改打卡信息")
    public R update(@RequestBody DakaxinxiEntity dakaxinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(dakaxinxi);
        dakaxinxiService.updateById(dakaxinxi);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除打卡信息")
    public R delete(@RequestBody Long[] ids){
        dakaxinxiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	









}
