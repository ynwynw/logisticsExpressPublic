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

import com.cl.entity.ZhandianxinxiEntity;
import com.cl.entity.view.ZhandianxinxiView;

import com.cl.service.ZhandianxinxiService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.EncryptUtil;
import com.cl.utils.MPUtil;
import com.cl.utils.MapUtils;
import com.cl.utils.CommonUtil;
import com.cl.service.StoreupService;
import com.cl.entity.StoreupEntity;

/**
 * 站点信息
 * 后端接口
 * @author 
 * @email 
 * @date 2025-05-15 21:38:20
 */
@RestController
@RequestMapping("/zhandianxinxi")
public class ZhandianxinxiController {
    @Autowired
    private ZhandianxinxiService zhandianxinxiService;

    @Autowired
    private StoreupService storeupService;








    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ZhandianxinxiEntity zhandianxinxi,
                                                                                                                                                                    HttpServletRequest request){
                    String tableName = request.getSession().getAttribute("tableName").toString();
                                                                                                                                                                                                                    if(tableName.equals("yuangong")) {
                    zhandianxinxi.setYuangongzhanghao((String)request.getSession().getAttribute("username"));
                                    }
                                                                                                                                                                                EntityWrapper<ZhandianxinxiEntity> ew = new EntityWrapper<ZhandianxinxiEntity>();
                                                                                                                                                                                                                                                                
        
        
        PageUtils page = zhandianxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zhandianxinxi), params), params));
        return R.ok().put("data", page);
    }







    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,ZhandianxinxiEntity zhandianxinxi,
		HttpServletRequest request){
        EntityWrapper<ZhandianxinxiEntity> ew = new EntityWrapper<ZhandianxinxiEntity>();

		PageUtils page = zhandianxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zhandianxinxi), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ZhandianxinxiEntity zhandianxinxi){
       	EntityWrapper<ZhandianxinxiEntity> ew = new EntityWrapper<ZhandianxinxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( zhandianxinxi, "zhandianxinxi")); 
        return R.ok().put("data", zhandianxinxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ZhandianxinxiEntity zhandianxinxi){
        EntityWrapper< ZhandianxinxiEntity> ew = new EntityWrapper< ZhandianxinxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( zhandianxinxi, "zhandianxinxi")); 
		ZhandianxinxiView zhandianxinxiView =  zhandianxinxiService.selectView(ew);
		return R.ok("查询站点信息成功").put("data", zhandianxinxiView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ZhandianxinxiEntity zhandianxinxi = zhandianxinxiService.selectById(id);
		zhandianxinxi = zhandianxinxiService.selectView(new EntityWrapper<ZhandianxinxiEntity>().eq("id", id));
        return R.ok().put("data", zhandianxinxi);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ZhandianxinxiEntity zhandianxinxi = zhandianxinxiService.selectById(id);
		zhandianxinxi = zhandianxinxiService.selectView(new EntityWrapper<ZhandianxinxiEntity>().eq("id", id));
        return R.ok().put("data", zhandianxinxi);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    @SysLog("新增站点信息")
    public R save(@RequestBody ZhandianxinxiEntity zhandianxinxi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(zhandianxinxi);
        zhandianxinxiService.insert(zhandianxinxi);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @SysLog("新增站点信息")
    @RequestMapping("/add")
    public R add(@RequestBody ZhandianxinxiEntity zhandianxinxi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(zhandianxinxi);
        zhandianxinxiService.insert(zhandianxinxi);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改站点信息")
    public R update(@RequestBody ZhandianxinxiEntity zhandianxinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(zhandianxinxi);
        zhandianxinxiService.updateById(zhandianxinxi);//全部更新
        return R.ok();
    }

    /**
     * 审核
     */
    @RequestMapping("/shBatch")
    @Transactional
    @SysLog("审核站点信息")
    public R update(@RequestBody Long[] ids, @RequestParam String sfsh, @RequestParam String shhf){
        List<ZhandianxinxiEntity> list = new ArrayList<ZhandianxinxiEntity>();
        for(Long id : ids) {
            ZhandianxinxiEntity zhandianxinxi = zhandianxinxiService.selectById(id);
            zhandianxinxi.setSfsh(sfsh);
            zhandianxinxi.setShhf(shhf);
            list.add(zhandianxinxi);
        }
        zhandianxinxiService.updateBatchById(list);
        return R.ok();
    }


    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除站点信息")
    public R delete(@RequestBody Long[] ids){
        zhandianxinxiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	









}
