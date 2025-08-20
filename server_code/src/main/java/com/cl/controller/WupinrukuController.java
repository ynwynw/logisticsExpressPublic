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

import com.cl.entity.WupinrukuEntity;
import com.cl.entity.view.WupinrukuView;

import com.cl.service.WupinrukuService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.EncryptUtil;
import com.cl.utils.MPUtil;
import com.cl.utils.MapUtils;
import com.cl.utils.CommonUtil;

/**
 * 物品入库
 * 后端接口
 * @author 
 * @email 
 * @date 2025-05-15 21:38:20
 */
@RestController
@RequestMapping("/wupinruku")
public class WupinrukuController {
    @Autowired
    private WupinrukuService wupinrukuService;









    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,WupinrukuEntity wupinruku,
                                                                                                                                                                                                        HttpServletRequest request){
                    String tableName = request.getSession().getAttribute("tableName").toString();
                                            if(tableName.equals("yonghu")) {
                    wupinruku.setYonghuzhanghao((String)request.getSession().getAttribute("username"));
                                    }
                                                                                                                                                                                                                                                                                                                                                                                                    if(tableName.equals("yuangong")) {
                    wupinruku.setYuangongzhanghao((String)request.getSession().getAttribute("username"));
                                    }
                                                                                            EntityWrapper<WupinrukuEntity> ew = new EntityWrapper<WupinrukuEntity>();
                                                                                                                                                                                                                                                                                                                            
        
        
        PageUtils page = wupinrukuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, wupinruku), params), params));
        return R.ok().put("data", page);
    }







    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,WupinrukuEntity wupinruku,
		HttpServletRequest request){
        EntityWrapper<WupinrukuEntity> ew = new EntityWrapper<WupinrukuEntity>();

		PageUtils page = wupinrukuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, wupinruku), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( WupinrukuEntity wupinruku){
       	EntityWrapper<WupinrukuEntity> ew = new EntityWrapper<WupinrukuEntity>();
      	ew.allEq(MPUtil.allEQMapPre( wupinruku, "wupinruku")); 
        return R.ok().put("data", wupinrukuService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(WupinrukuEntity wupinruku){
        EntityWrapper< WupinrukuEntity> ew = new EntityWrapper< WupinrukuEntity>();
 		ew.allEq(MPUtil.allEQMapPre( wupinruku, "wupinruku")); 
		WupinrukuView wupinrukuView =  wupinrukuService.selectView(ew);
		return R.ok("查询物品入库成功").put("data", wupinrukuView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        WupinrukuEntity wupinruku = wupinrukuService.selectById(id);
		wupinruku = wupinrukuService.selectView(new EntityWrapper<WupinrukuEntity>().eq("id", id));
        return R.ok().put("data", wupinruku);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        WupinrukuEntity wupinruku = wupinrukuService.selectById(id);
		wupinruku = wupinrukuService.selectView(new EntityWrapper<WupinrukuEntity>().eq("id", id));
        return R.ok().put("data", wupinruku);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    @SysLog("新增物品入库")
    public R save(@RequestBody WupinrukuEntity wupinruku, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(wupinruku);
        wupinrukuService.insert(wupinruku);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @SysLog("新增物品入库")
    @RequestMapping("/add")
    public R add(@RequestBody WupinrukuEntity wupinruku, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(wupinruku);
        wupinrukuService.insert(wupinruku);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改物品入库")
    public R update(@RequestBody WupinrukuEntity wupinruku, HttpServletRequest request){
        //ValidatorUtils.validateEntity(wupinruku);
        wupinrukuService.updateById(wupinruku);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除物品入库")
    public R delete(@RequestBody Long[] ids){
        wupinrukuService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	









}
