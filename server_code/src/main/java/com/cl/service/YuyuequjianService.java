package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.YuyuequjianEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.YuyuequjianView;


/**
 * 预约取件
 *
 * @author 
 * @email 
 * @date 2025-05-15 21:38:20
 */
public interface YuyuequjianService extends IService<YuyuequjianEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<YuyuequjianView> selectListView(Wrapper<YuyuequjianEntity> wrapper);
   	
   	YuyuequjianView selectView(@Param("ew") Wrapper<YuyuequjianEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<YuyuequjianEntity> wrapper);
   	
   
}

