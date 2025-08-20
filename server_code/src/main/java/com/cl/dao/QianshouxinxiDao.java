package com.cl.dao;

import com.cl.entity.QianshouxinxiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.QianshouxinxiView;


/**
 * 签收信息
 * 
 * @author 
 * @email 
 * @date 2025-05-15 21:38:20
 */
public interface QianshouxinxiDao extends BaseMapper<QianshouxinxiEntity> {
	
	List<QianshouxinxiView> selectListView(@Param("ew") Wrapper<QianshouxinxiEntity> wrapper);

	List<QianshouxinxiView> selectListView(Pagination page,@Param("ew") Wrapper<QianshouxinxiEntity> wrapper);
	
	QianshouxinxiView selectView(@Param("ew") Wrapper<QianshouxinxiEntity> wrapper);


}
