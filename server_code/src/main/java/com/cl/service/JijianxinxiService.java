package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.JijianxinxiEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.JijianxinxiView;


/**
 * 寄件信息
 *
 * @author 
 * @email 
 * @date 2025-05-15 21:38:20
 */
public interface JijianxinxiService extends IService<JijianxinxiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<JijianxinxiView> selectListView(Wrapper<JijianxinxiEntity> wrapper);
   	
   	JijianxinxiView selectView(@Param("ew") Wrapper<JijianxinxiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<JijianxinxiEntity> wrapper);
   	
   
}

