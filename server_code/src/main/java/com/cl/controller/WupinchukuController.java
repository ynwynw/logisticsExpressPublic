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

import com.cl.entity.WupinchukuEntity;
import com.cl.entity.view.WupinchukuView;

import com.cl.service.WupinchukuService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.EncryptUtil;
import com.cl.utils.MPUtil;
import com.cl.utils.MapUtils;
import com.cl.utils.CommonUtil;

/**
 * 物品出库
 * 后端接口
 * @author 
 * @email 
 * @date 2025-05-15 21:38:20
 */
@RestController
@RequestMapping("/wupinchuku")
public class WupinchukuController {
    @Autowired
    private WupinchukuService wupinchukuService;









    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,WupinchukuEntity wupinchuku,
                                                                                                                                                                                                        HttpServletRequest request){
                    String tableName = request.getSession().getAttribute("tableName").toString();
                                            if(tableName.equals("yonghu")) {
                    wupinchuku.setYonghuzhanghao((String)request.getSession().getAttribute("username"));
                                    }
                                                                                                                                                                                                                                                                                                                                                                        if(tableName.equals("yuangong")) {
                    wupinchuku.setYuangongzhanghao((String)request.getSession().getAttribute("username"));
                                    }
                                                                                                                        EntityWrapper<WupinchukuEntity> ew = new EntityWrapper<WupinchukuEntity>();
                                                                                                                                                                                                                                                                                                                            
        
        
        PageUtils page = wupinchukuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, wupinchuku), params), params));
        return R.ok().put("data", page);
    }







    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,WupinchukuEntity wupinchuku,
		HttpServletRequest request){
        EntityWrapper<WupinchukuEntity> ew = new EntityWrapper<WupinchukuEntity>();

		PageUtils page = wupinchukuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, wupinchuku), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( WupinchukuEntity wupinchuku){
       	EntityWrapper<WupinchukuEntity> ew = new EntityWrapper<WupinchukuEntity>();
      	ew.allEq(MPUtil.allEQMapPre( wupinchuku, "wupinchuku")); 
        return R.ok().put("data", wupinchukuService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(WupinchukuEntity wupinchuku){
        EntityWrapper< WupinchukuEntity> ew = new EntityWrapper< WupinchukuEntity>();
 		ew.allEq(MPUtil.allEQMapPre( wupinchuku, "wupinchuku")); 
		WupinchukuView wupinchukuView =  wupinchukuService.selectView(ew);
		return R.ok("查询物品出库成功").put("data", wupinchukuView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        WupinchukuEntity wupinchuku = wupinchukuService.selectById(id);
		wupinchuku = wupinchukuService.selectView(new EntityWrapper<WupinchukuEntity>().eq("id", id));
        return R.ok().put("data", wupinchuku);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        WupinchukuEntity wupinchuku = wupinchukuService.selectById(id);
		wupinchuku = wupinchukuService.selectView(new EntityWrapper<WupinchukuEntity>().eq("id", id));
        return R.ok().put("data", wupinchuku);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    @SysLog("新增物品出库")
    public R save(@RequestBody WupinchukuEntity wupinchuku, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(wupinchuku);
        wupinchukuService.insert(wupinchuku);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @SysLog("新增物品出库")
    @RequestMapping("/add")
    public R add(@RequestBody WupinchukuEntity wupinchuku, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(wupinchuku);
        wupinchukuService.insert(wupinchuku);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改物品出库")
    public R update(@RequestBody WupinchukuEntity wupinchuku, HttpServletRequest request){
        //ValidatorUtils.validateEntity(wupinchuku);
        wupinchukuService.updateById(wupinchuku);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除物品出库")
    public R delete(@RequestBody Long[] ids){
        wupinchukuService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	









}
