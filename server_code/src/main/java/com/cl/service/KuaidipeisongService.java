package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.KuaidipeisongEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.KuaidipeisongView;


/**
 * 快递配送
 *
 * @author 
 * @email 
 * @date 2025-05-15 21:38:20
 */
public interface KuaidipeisongService extends IService<KuaidipeisongEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<KuaidipeisongView> selectListView(Wrapper<KuaidipeisongEntity> wrapper);
   	
   	KuaidipeisongView selectView(@Param("ew") Wrapper<KuaidipeisongEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<KuaidipeisongEntity> wrapper);
   	
   
}

