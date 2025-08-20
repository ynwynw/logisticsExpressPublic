package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.WupinchukuEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.WupinchukuView;


/**
 * 物品出库
 *
 * @author 
 * @email 
 * @date 2025-05-15 21:38:20
 */
public interface WupinchukuService extends IService<WupinchukuEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<WupinchukuView> selectListView(Wrapper<WupinchukuEntity> wrapper);
   	
   	WupinchukuView selectView(@Param("ew") Wrapper<WupinchukuEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<WupinchukuEntity> wrapper);
   	
   
}

