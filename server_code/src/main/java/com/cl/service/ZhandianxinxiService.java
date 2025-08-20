package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.ZhandianxinxiEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.ZhandianxinxiView;


/**
 * 站点信息
 *
 * @author 
 * @email 
 * @date 2025-05-15 21:38:20
 */
public interface ZhandianxinxiService extends IService<ZhandianxinxiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ZhandianxinxiView> selectListView(Wrapper<ZhandianxinxiEntity> wrapper);
   	
   	ZhandianxinxiView selectView(@Param("ew") Wrapper<ZhandianxinxiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<ZhandianxinxiEntity> wrapper);
   	
   
}

