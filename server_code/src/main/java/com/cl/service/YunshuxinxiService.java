package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.YunshuxinxiEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.YunshuxinxiView;


/**
 * 运输信息
 *
 * @author 
 * @email 
 * @date 2025-05-15 21:38:20
 */
public interface YunshuxinxiService extends IService<YunshuxinxiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<YunshuxinxiView> selectListView(Wrapper<YunshuxinxiEntity> wrapper);
   	
   	YunshuxinxiView selectView(@Param("ew") Wrapper<YunshuxinxiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<YunshuxinxiEntity> wrapper);
   	
   
}

