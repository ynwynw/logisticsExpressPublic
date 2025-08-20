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

import com.cl.entity.JijianxinxiEntity;
import com.cl.entity.view.JijianxinxiView;

import com.cl.service.JijianxinxiService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.EncryptUtil;
import com.cl.utils.MPUtil;
import com.cl.utils.MapUtils;
import com.cl.utils.CommonUtil;

/**
 * 寄件信息
 * 后端接口
 * @author 
 * @email 
 * @date 2025-05-15 21:38:20
 */
@RestController
@RequestMapping("/jijianxinxi")
public class JijianxinxiController {
    @Autowired
    private JijianxinxiService jijianxinxiService;









    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,JijianxinxiEntity jijianxinxi,
                                                                                                                                                                                                                                                                        HttpServletRequest request){
                                    EntityWrapper<JijianxinxiEntity> ew = new EntityWrapper<JijianxinxiEntity>();
                                                                                                                                                                                                                                                                                                                                                                                                                            
                    String tableName = request.getSession().getAttribute("tableName").toString();
            ew.andNew();
                                                                                        if(tableName.equals("yonghu")) {
                                                ew.eq("yonghuzhanghao", (String)request.getSession().getAttribute("username"));
                    }
                                                                                                                                                                                                                                                                                                                                                        if(tableName.equals("yonghu")) {
                                                    ew.or();
                                                ew.eq("jijianzhanghao", (String)request.getSession().getAttribute("username"));
                    }
                                                                                                                            ew.andNew("1=1");
        
        
        PageUtils page = jijianxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jijianxinxi), params), params));
        return R.ok().put("data", page);
    }







    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,JijianxinxiEntity jijianxinxi,
		HttpServletRequest request){
        EntityWrapper<JijianxinxiEntity> ew = new EntityWrapper<JijianxinxiEntity>();

		PageUtils page = jijianxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jijianxinxi), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( JijianxinxiEntity jijianxinxi){
       	EntityWrapper<JijianxinxiEntity> ew = new EntityWrapper<JijianxinxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( jijianxinxi, "jijianxinxi")); 
        return R.ok().put("data", jijianxinxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(JijianxinxiEntity jijianxinxi){
        EntityWrapper< JijianxinxiEntity> ew = new EntityWrapper< JijianxinxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( jijianxinxi, "jijianxinxi")); 
		JijianxinxiView jijianxinxiView =  jijianxinxiService.selectView(ew);
		return R.ok("查询寄件信息成功").put("data", jijianxinxiView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        JijianxinxiEntity jijianxinxi = jijianxinxiService.selectById(id);
		jijianxinxi = jijianxinxiService.selectView(new EntityWrapper<JijianxinxiEntity>().eq("id", id));
        return R.ok().put("data", jijianxinxi);
    }

    /**
     * 前端详情
     */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        JijianxinxiEntity jijianxinxi = jijianxinxiService.selectById(id);
		jijianxinxi = jijianxinxiService.selectView(new EntityWrapper<JijianxinxiEntity>().eq("id", id));
        return R.ok().put("data", jijianxinxi);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    @SysLog("新增寄件信息")
    public R save(@RequestBody JijianxinxiEntity jijianxinxi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(jijianxinxi);
        jijianxinxiService.insert(jijianxinxi);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @SysLog("新增寄件信息")
    @RequestMapping("/add")
    public R add(@RequestBody JijianxinxiEntity jijianxinxi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(jijianxinxi);
        jijianxinxiService.insert(jijianxinxi);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改寄件信息")
    public R update(@RequestBody JijianxinxiEntity jijianxinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(jijianxinxi);
        jijianxinxiService.updateById(jijianxinxi);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除寄件信息")
    public R delete(@RequestBody Long[] ids){
        jijianxinxiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	









}
