package com.cl.dao;

import com.cl.entity.WupinrukuEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.WupinrukuView;


/**
 * 物品入库
 * 
 * @author 
 * @email 
 * @date 2025-05-15 21:38:20
 */
public interface WupinrukuDao extends BaseMapper<WupinrukuEntity> {
	
	List<WupinrukuView> selectListView(@Param("ew") Wrapper<WupinrukuEntity> wrapper);

	List<WupinrukuView> selectListView(Pagination page,@Param("ew") Wrapper<WupinrukuEntity> wrapper);
	
	WupinrukuView selectView(@Param("ew") Wrapper<WupinrukuEntity> wrapper);


}
