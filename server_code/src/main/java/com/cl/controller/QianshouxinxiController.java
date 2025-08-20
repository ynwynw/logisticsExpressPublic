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

import com.cl.entity.QianshouxinxiEntity;
import com.cl.entity.view.QianshouxinxiView;

import com.cl.service.QianshouxinxiService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.EncryptUtil;
import com.cl.utils.MPUtil;
import com.cl.utils.MapUtils;
import com.cl.utils.CommonUtil;

/**
 * 签收信息
 * 后端接口
 * @author 
 * @email 
 * @date 2025-05-15 21:38:20
 */
@RestController
@RequestMapping("/qianshouxinxi")
public class QianshouxinxiController {
    @Autowired
    private QianshouxinxiService qianshouxinxiService;









    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,QianshouxinxiEntity qianshouxinxi,
                                                                                                                                                                                                                    @RequestParam(required = false) Double shoujiStart,
                    @RequestParam(required = false) Double shoujiEnd,
                                                                                                        HttpServletRequest request){
                    String tableName = request.getSession().getAttribute("tableName").toString();
                                                                        if(tableName.equals("yonghu")) {
                    qianshouxinxi.setYonghuzhanghao((String)request.getSession().getAttribute("username"));
                                    }
                                                                                                                                                                                                                            if(tableName.equals("yuangong")) {
                    qianshouxinxi.setYuangongzhanghao((String)request.getSession().getAttribute("username"));
                                    }
                                                                                                                                                                                                                                                                                                EntityWrapper<QianshouxinxiEntity> ew = new EntityWrapper<QianshouxinxiEntity>();
                                                                                                                                                                                                                                                                                                                                if(shoujiStart!=null) ew.ge("shouji", shoujiStart);
                    if(shoujiEnd!=null) ew.le("shouji", shoujiEnd);
                                                                                                                                                            
        
        
        PageUtils page = qianshouxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, qianshouxinxi), params), params));
        return R.ok().put("data", page);
    }







    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,QianshouxinxiEntity qianshouxinxi,
                @RequestParam(required = false) Double shoujiStart,
                @RequestParam(required = false) Double shoujiEnd,
		HttpServletRequest request){
        EntityWrapper<QianshouxinxiEntity> ew = new EntityWrapper<QianshouxinxiEntity>();
                if(shoujiStart!=null) ew.ge("shouji", shoujiStart);
                if(shoujiEnd!=null) ew.le("shouji", shoujiEnd);

		PageUtils page = qianshouxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, qianshouxinxi), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( QianshouxinxiEntity qianshouxinxi){
       	EntityWrapper<QianshouxinxiEntity> ew = new EntityWrapper<QianshouxinxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( qianshouxinxi, "qianshouxinxi")); 
        return R.ok().put("data", qianshouxinxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(QianshouxinxiEntity qianshouxinxi){
        EntityWrapper< QianshouxinxiEntity> ew = new EntityWrapper< QianshouxinxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( qianshouxinxi, "qianshouxinxi")); 
		QianshouxinxiView qianshouxinxiView =  qianshouxinxiService.selectView(ew);
		return R.ok("查询签收信息成功").put("data", qianshouxinxiView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        QianshouxinxiEntity qianshouxinxi = qianshouxinxiService.selectById(id);
		qianshouxinxi = qianshouxinxiService.selectView(new EntityWrapper<QianshouxinxiEntity>().eq("id", id));
        return R.ok().put("data", qianshouxinxi);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        QianshouxinxiEntity qianshouxinxi = qianshouxinxiService.selectById(id);
		qianshouxinxi = qianshouxinxiService.selectView(new EntityWrapper<QianshouxinxiEntity>().eq("id", id));
        return R.ok().put("data", qianshouxinxi);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    @SysLog("新增签收信息")
    public R save(@RequestBody QianshouxinxiEntity qianshouxinxi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(qianshouxinxi);
        qianshouxinxiService.insert(qianshouxinxi);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @SysLog("新增签收信息")
    @RequestMapping("/add")
    public R add(@RequestBody QianshouxinxiEntity qianshouxinxi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(qianshouxinxi);
        qianshouxinxiService.insert(qianshouxinxi);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改签收信息")
    public R update(@RequestBody QianshouxinxiEntity qianshouxinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(qianshouxinxi);
        qianshouxinxiService.updateById(qianshouxinxi);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除签收信息")
    public R delete(@RequestBody Long[] ids){
        qianshouxinxiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	









}
