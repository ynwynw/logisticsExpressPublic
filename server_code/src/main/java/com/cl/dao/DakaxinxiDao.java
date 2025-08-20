package com.cl.dao;

import com.cl.entity.DakaxinxiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.DakaxinxiView;


/**
 * 打卡信息
 * 
 * @author 
 * @email 
 * @date 2025-05-15 21:38:20
 */
public interface DakaxinxiDao extends BaseMapper<DakaxinxiEntity> {
	
	List<DakaxinxiView> selectListView(@Param("ew") Wrapper<DakaxinxiEntity> wrapper);

	List<DakaxinxiView> selectListView(Pagination page,@Param("ew") Wrapper<DakaxinxiEntity> wrapper);
	
	DakaxinxiView selectView(@Param("ew") Wrapper<DakaxinxiEntity> wrapper);


}
