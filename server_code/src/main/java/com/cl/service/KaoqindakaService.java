package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.KaoqindakaEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.KaoqindakaView;


/**
 * 考勤打卡
 *
 * @author 
 * @email 
 * @date 2025-05-15 21:38:20
 */
public interface KaoqindakaService extends IService<KaoqindakaEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<KaoqindakaView> selectListView(Wrapper<KaoqindakaEntity> wrapper);
   	
   	KaoqindakaView selectView(@Param("ew") Wrapper<KaoqindakaEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<KaoqindakaEntity> wrapper);
   	
   
}

