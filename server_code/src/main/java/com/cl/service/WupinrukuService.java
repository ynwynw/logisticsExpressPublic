package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.WupinrukuEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.WupinrukuView;


/**
 * 物品入库
 *
 * @author 
 * @email 
 * @date 2025-05-15 21:38:20
 */
public interface WupinrukuService extends IService<WupinrukuEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<WupinrukuView> selectListView(Wrapper<WupinrukuEntity> wrapper);
   	
   	WupinrukuView selectView(@Param("ew") Wrapper<WupinrukuEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<WupinrukuEntity> wrapper);
   	
   
}

