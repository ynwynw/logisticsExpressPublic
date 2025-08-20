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

import com.cl.entity.YunshuxinxiEntity;
import com.cl.entity.view.YunshuxinxiView;

import com.cl.service.YunshuxinxiService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.EncryptUtil;
import com.cl.utils.MPUtil;
import com.cl.utils.MapUtils;
import com.cl.utils.CommonUtil;

/**
 * 运输信息
 * 后端接口
 * @author 
 * @email 
 * @date 2025-05-15 21:38:20
 */
@RestController
@RequestMapping("/yunshuxinxi")
public class YunshuxinxiController {
    @Autowired
    private YunshuxinxiService yunshuxinxiService;









    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,YunshuxinxiEntity yunshuxinxi,
                                                                                                                                                                                                                            HttpServletRequest request){
                    String tableName = request.getSession().getAttribute("tableName").toString();
                                                                                                                                                                                                                    if(tableName.equals("yonghu")) {
                    yunshuxinxi.setYonghuzhanghao((String)request.getSession().getAttribute("username"));
                                    }
                                                                                                                                                                    if(tableName.equals("yuangong")) {
                    yunshuxinxi.setYuangongzhanghao((String)request.getSession().getAttribute("username"));
                                    }
                                                                                                                                                    EntityWrapper<YunshuxinxiEntity> ew = new EntityWrapper<YunshuxinxiEntity>();
                                                                                                                                                                                                                                                                                                                                                        
        
        
        PageUtils page = yunshuxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yunshuxinxi), params), params));
        return R.ok().put("data", page);
    }







    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,YunshuxinxiEntity yunshuxinxi,
		HttpServletRequest request){
        EntityWrapper<YunshuxinxiEntity> ew = new EntityWrapper<YunshuxinxiEntity>();

		PageUtils page = yunshuxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yunshuxinxi), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( YunshuxinxiEntity yunshuxinxi){
       	EntityWrapper<YunshuxinxiEntity> ew = new EntityWrapper<YunshuxinxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( yunshuxinxi, "yunshuxinxi")); 
        return R.ok().put("data", yunshuxinxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(YunshuxinxiEntity yunshuxinxi){
        EntityWrapper< YunshuxinxiEntity> ew = new EntityWrapper< YunshuxinxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( yunshuxinxi, "yunshuxinxi")); 
		YunshuxinxiView yunshuxinxiView =  yunshuxinxiService.selectView(ew);
		return R.ok("查询运输信息成功").put("data", yunshuxinxiView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        YunshuxinxiEntity yunshuxinxi = yunshuxinxiService.selectById(id);
		yunshuxinxi = yunshuxinxiService.selectView(new EntityWrapper<YunshuxinxiEntity>().eq("id", id));
        return R.ok().put("data", yunshuxinxi);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        YunshuxinxiEntity yunshuxinxi = yunshuxinxiService.selectById(id);
		yunshuxinxi = yunshuxinxiService.selectView(new EntityWrapper<YunshuxinxiEntity>().eq("id", id));
        return R.ok().put("data", yunshuxinxi);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    @SysLog("新增运输信息")
    public R save(@RequestBody YunshuxinxiEntity yunshuxinxi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(yunshuxinxi);
        yunshuxinxiService.insert(yunshuxinxi);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @SysLog("新增运输信息")
    @RequestMapping("/add")
    public R add(@RequestBody YunshuxinxiEntity yunshuxinxi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(yunshuxinxi);
        yunshuxinxiService.insert(yunshuxinxi);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改运输信息")
    public R update(@RequestBody YunshuxinxiEntity yunshuxinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(yunshuxinxi);
        yunshuxinxiService.updateById(yunshuxinxi);//全部更新
        return R.ok();
    }

    /**
     * 审核
     */
    @RequestMapping("/shBatch")
    @Transactional
    @SysLog("审核运输信息")
    public R update(@RequestBody Long[] ids, @RequestParam String sfsh, @RequestParam String shhf){
        List<YunshuxinxiEntity> list = new ArrayList<YunshuxinxiEntity>();
        for(Long id : ids) {
            YunshuxinxiEntity yunshuxinxi = yunshuxinxiService.selectById(id);
            yunshuxinxi.setSfsh(sfsh);
            yunshuxinxi.setShhf(shhf);
            list.add(yunshuxinxi);
        }
        yunshuxinxiService.updateBatchById(list);
        return R.ok();
    }


    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除运输信息")
    public R delete(@RequestBody Long[] ids){
        yunshuxinxiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	









}
