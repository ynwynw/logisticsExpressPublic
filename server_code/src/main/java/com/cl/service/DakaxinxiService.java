package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.DakaxinxiEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.DakaxinxiView;


/**
 * 打卡信息
 *
 * @author 
 * @email 
 * @date 2025-05-15 21:38:20
 */
public interface DakaxinxiService extends IService<DakaxinxiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DakaxinxiView> selectListView(Wrapper<DakaxinxiEntity> wrapper);
   	
   	DakaxinxiView selectView(@Param("ew") Wrapper<DakaxinxiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DakaxinxiEntity> wrapper);
   	
   
}

