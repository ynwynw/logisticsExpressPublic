package com.cl.dao;

import com.cl.entity.JijianxinxiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.JijianxinxiView;


/**
 * 寄件信息
 * 
 * @author 
 * @email 
 * @date 2025-05-15 21:38:20
 */
public interface JijianxinxiDao extends BaseMapper<JijianxinxiEntity> {
	
	List<JijianxinxiView> selectListView(@Param("ew") Wrapper<JijianxinxiEntity> wrapper);

	List<JijianxinxiView> selectListView(Pagination page,@Param("ew") Wrapper<JijianxinxiEntity> wrapper);
	
	JijianxinxiView selectView(@Param("ew") Wrapper<JijianxinxiEntity> wrapper);


}
