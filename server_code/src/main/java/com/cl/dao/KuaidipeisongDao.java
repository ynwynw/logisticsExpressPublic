package com.cl.dao;

import com.cl.entity.KuaidipeisongEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.KuaidipeisongView;


/**
 * 快递配送
 * 
 * @author 
 * @email 
 * @date 2025-05-15 21:38:20
 */
public interface KuaidipeisongDao extends BaseMapper<KuaidipeisongEntity> {
	
	List<KuaidipeisongView> selectListView(@Param("ew") Wrapper<KuaidipeisongEntity> wrapper);

	List<KuaidipeisongView> selectListView(Pagination page,@Param("ew") Wrapper<KuaidipeisongEntity> wrapper);
	
	KuaidipeisongView selectView(@Param("ew") Wrapper<KuaidipeisongEntity> wrapper);


}
