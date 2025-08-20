package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.QujianxinxiEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.QujianxinxiView;


/**
 * 取件信息
 *
 * @author 
 * @email 
 * @date 2025-05-15 21:38:20
 */
public interface QujianxinxiService extends IService<QujianxinxiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<QujianxinxiView> selectListView(Wrapper<QujianxinxiEntity> wrapper);
   	
   	QujianxinxiView selectView(@Param("ew") Wrapper<QujianxinxiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<QujianxinxiEntity> wrapper);
   	
   
}

