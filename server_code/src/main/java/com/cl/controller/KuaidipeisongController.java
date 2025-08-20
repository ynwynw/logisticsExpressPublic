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

import com.cl.entity.KuaidipeisongEntity;
import com.cl.entity.view.KuaidipeisongView;

import com.cl.service.KuaidipeisongService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.EncryptUtil;
import com.cl.utils.MPUtil;
import com.cl.utils.MapUtils;
import com.cl.utils.CommonUtil;

/**
 * 快递配送
 * 后端接口
 * @author 
 * @email 
 * @date 2025-05-15 21:38:20
 */
@RestController
@RequestMapping("/kuaidipeisong")
public class KuaidipeisongController {
    @Autowired
    private KuaidipeisongService kuaidipeisongService;









    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,KuaidipeisongEntity kuaidipeisong,
                                                                                                                                                                                                                                    HttpServletRequest request){
                    String tableName = request.getSession().getAttribute("tableName").toString();
                                                                        if(tableName.equals("yonghu")) {
                    kuaidipeisong.setYonghuzhanghao((String)request.getSession().getAttribute("username"));
                                    }
                                                                                                                                                                                                                                                        if(tableName.equals("yuangong")) {
                    kuaidipeisong.setYuangongzhanghao((String)request.getSession().getAttribute("username"));
                                    }
                                                                                                                                                                                EntityWrapper<KuaidipeisongEntity> ew = new EntityWrapper<KuaidipeisongEntity>();
                                                                                                                                                                                                                                                                                                                                                                
        
        
        PageUtils page = kuaidipeisongService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, kuaidipeisong), params), params));
        return R.ok().put("data", page);
    }







    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,KuaidipeisongEntity kuaidipeisong,
		HttpServletRequest request){
        EntityWrapper<KuaidipeisongEntity> ew = new EntityWrapper<KuaidipeisongEntity>();

		PageUtils page = kuaidipeisongService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, kuaidipeisong), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( KuaidipeisongEntity kuaidipeisong){
       	EntityWrapper<KuaidipeisongEntity> ew = new EntityWrapper<KuaidipeisongEntity>();
      	ew.allEq(MPUtil.allEQMapPre( kuaidipeisong, "kuaidipeisong")); 
        return R.ok().put("data", kuaidipeisongService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(KuaidipeisongEntity kuaidipeisong){
        EntityWrapper< KuaidipeisongEntity> ew = new EntityWrapper< KuaidipeisongEntity>();
 		ew.allEq(MPUtil.allEQMapPre( kuaidipeisong, "kuaidipeisong")); 
		KuaidipeisongView kuaidipeisongView =  kuaidipeisongService.selectView(ew);
		return R.ok("查询快递配送成功").put("data", kuaidipeisongView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        KuaidipeisongEntity kuaidipeisong = kuaidipeisongService.selectById(id);
		kuaidipeisong = kuaidipeisongService.selectView(new EntityWrapper<KuaidipeisongEntity>().eq("id", id));
        return R.ok().put("data", kuaidipeisong);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        KuaidipeisongEntity kuaidipeisong = kuaidipeisongService.selectById(id);
		kuaidipeisong = kuaidipeisongService.selectView(new EntityWrapper<KuaidipeisongEntity>().eq("id", id));
        return R.ok().put("data", kuaidipeisong);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    @SysLog("新增快递配送")
    public R save(@RequestBody KuaidipeisongEntity kuaidipeisong, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(kuaidipeisong);
        kuaidipeisongService.insert(kuaidipeisong);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @SysLog("新增快递配送")
    @RequestMapping("/add")
    public R add(@RequestBody KuaidipeisongEntity kuaidipeisong, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(kuaidipeisong);
        kuaidipeisongService.insert(kuaidipeisong);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改快递配送")
    public R update(@RequestBody KuaidipeisongEntity kuaidipeisong, HttpServletRequest request){
        //ValidatorUtils.validateEntity(kuaidipeisong);
        kuaidipeisongService.updateById(kuaidipeisong);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除快递配送")
    public R delete(@RequestBody Long[] ids){
        kuaidipeisongService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	









}
