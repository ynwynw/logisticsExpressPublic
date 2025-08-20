package com.cl.dao;

import com.cl.entity.ZhandianxinxiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.ZhandianxinxiView;


/**
 * 站点信息
 * 
 * @author 
 * @email 
 * @date 2025-05-15 21:38:20
 */
public interface ZhandianxinxiDao extends BaseMapper<ZhandianxinxiEntity> {
	
	List<ZhandianxinxiView> selectListView(@Param("ew") Wrapper<ZhandianxinxiEntity> wrapper);

	List<ZhandianxinxiView> selectListView(Pagination page,@Param("ew") Wrapper<ZhandianxinxiEntity> wrapper);
	
	ZhandianxinxiView selectView(@Param("ew") Wrapper<ZhandianxinxiEntity> wrapper);


}
