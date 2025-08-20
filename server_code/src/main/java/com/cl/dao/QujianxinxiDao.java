package com.cl.dao;

import com.cl.entity.QujianxinxiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.QujianxinxiView;


/**
 * 取件信息
 * 
 * @author 
 * @email 
 * @date 2025-05-15 21:38:20
 */
public interface QujianxinxiDao extends BaseMapper<QujianxinxiEntity> {
	
	List<QujianxinxiView> selectListView(@Param("ew") Wrapper<QujianxinxiEntity> wrapper);

	List<QujianxinxiView> selectListView(Pagination page,@Param("ew") Wrapper<QujianxinxiEntity> wrapper);
	
	QujianxinxiView selectView(@Param("ew") Wrapper<QujianxinxiEntity> wrapper);


}
