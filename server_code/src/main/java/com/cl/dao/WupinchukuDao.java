package com.cl.dao;

import com.cl.entity.WupinchukuEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.WupinchukuView;


/**
 * 物品出库
 * 
 * @author 
 * @email 
 * @date 2025-05-15 21:38:20
 */
public interface WupinchukuDao extends BaseMapper<WupinchukuEntity> {
	
	List<WupinchukuView> selectListView(@Param("ew") Wrapper<WupinchukuEntity> wrapper);

	List<WupinchukuView> selectListView(Pagination page,@Param("ew") Wrapper<WupinchukuEntity> wrapper);
	
	WupinchukuView selectView(@Param("ew") Wrapper<WupinchukuEntity> wrapper);


}
