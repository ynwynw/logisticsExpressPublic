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

import com.cl.entity.YuyuequjianEntity;
import com.cl.entity.view.YuyuequjianView;

import com.cl.service.YuyuequjianService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.EncryptUtil;
import com.cl.utils.MPUtil;
import com.cl.utils.MapUtils;
import com.cl.utils.CommonUtil;

/**
 * 预约取件
 * 后端接口
 * @author 
 * @email 
 * @date 2025-05-15 21:38:20
 */
@RestController
@RequestMapping("/yuyuequjian")
public class YuyuequjianController {
    @Autowired
    private YuyuequjianService yuyuequjianService;









    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,YuyuequjianEntity yuyuequjian,
                                                                                                                                                                                            HttpServletRequest request){
                                    EntityWrapper<YuyuequjianEntity> ew = new EntityWrapper<YuyuequjianEntity>();
                                                                                                                                                                                                                                                                                                        
        
        
        PageUtils page = yuyuequjianService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yuyuequjian), params), params));
        return R.ok().put("data", page);
    }







    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,YuyuequjianEntity yuyuequjian,
		HttpServletRequest request){
        EntityWrapper<YuyuequjianEntity> ew = new EntityWrapper<YuyuequjianEntity>();

		PageUtils page = yuyuequjianService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yuyuequjian), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( YuyuequjianEntity yuyuequjian){
       	EntityWrapper<YuyuequjianEntity> ew = new EntityWrapper<YuyuequjianEntity>();
      	ew.allEq(MPUtil.allEQMapPre( yuyuequjian, "yuyuequjian")); 
        return R.ok().put("data", yuyuequjianService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(YuyuequjianEntity yuyuequjian){
        EntityWrapper< YuyuequjianEntity> ew = new EntityWrapper< YuyuequjianEntity>();
 		ew.allEq(MPUtil.allEQMapPre( yuyuequjian, "yuyuequjian")); 
		YuyuequjianView yuyuequjianView =  yuyuequjianService.selectView(ew);
		return R.ok("查询预约取件成功").put("data", yuyuequjianView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        YuyuequjianEntity yuyuequjian = yuyuequjianService.selectById(id);
		yuyuequjian = yuyuequjianService.selectView(new EntityWrapper<YuyuequjianEntity>().eq("id", id));
        return R.ok().put("data", yuyuequjian);
    }

    /**
     * 前端详情
     */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        YuyuequjianEntity yuyuequjian = yuyuequjianService.selectById(id);
		yuyuequjian = yuyuequjianService.selectView(new EntityWrapper<YuyuequjianEntity>().eq("id", id));
        return R.ok().put("data", yuyuequjian);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    @SysLog("新增预约取件")
    public R save(@RequestBody YuyuequjianEntity yuyuequjian, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(yuyuequjian);
        yuyuequjianService.insert(yuyuequjian);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @SysLog("新增预约取件")
    @RequestMapping("/add")
    public R add(@RequestBody YuyuequjianEntity yuyuequjian, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(yuyuequjian);
        yuyuequjianService.insert(yuyuequjian);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改预约取件")
    public R update(@RequestBody YuyuequjianEntity yuyuequjian, HttpServletRequest request){
        //ValidatorUtils.validateEntity(yuyuequjian);
        yuyuequjianService.updateById(yuyuequjian);//全部更新
        return R.ok();
    }

    /**
     * 审核
     */
    @RequestMapping("/shBatch")
    @Transactional
    @SysLog("审核预约取件")
    public R update(@RequestBody Long[] ids, @RequestParam String sfsh, @RequestParam String shhf){
        List<YuyuequjianEntity> list = new ArrayList<YuyuequjianEntity>();
        for(Long id : ids) {
            YuyuequjianEntity yuyuequjian = yuyuequjianService.selectById(id);
            yuyuequjian.setSfsh(sfsh);
            yuyuequjian.setShhf(shhf);
            list.add(yuyuequjian);
        }
        yuyuequjianService.updateBatchById(list);
        return R.ok();
    }


    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除预约取件")
    public R delete(@RequestBody Long[] ids){
        yuyuequjianService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	









}
