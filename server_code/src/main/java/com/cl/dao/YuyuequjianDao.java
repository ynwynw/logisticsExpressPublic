package com.cl.dao;

import com.cl.entity.YuyuequjianEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.YuyuequjianView;


/**
 * 预约取件
 * 
 * @author 
 * @email 
 * @date 2025-05-15 21:38:20
 */
public interface YuyuequjianDao extends BaseMapper<YuyuequjianEntity> {
	
	List<YuyuequjianView> selectListView(@Param("ew") Wrapper<YuyuequjianEntity> wrapper);

	List<YuyuequjianView> selectListView(Pagination page,@Param("ew") Wrapper<YuyuequjianEntity> wrapper);
	
	YuyuequjianView selectView(@Param("ew") Wrapper<YuyuequjianEntity> wrapper);


}
