package com.cl.dao;

import com.cl.entity.KaoqindakaEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.KaoqindakaView;


/**
 * 考勤打卡
 * 
 * @author 
 * @email 
 * @date 2025-05-15 21:38:20
 */
public interface KaoqindakaDao extends BaseMapper<KaoqindakaEntity> {
	
	List<KaoqindakaView> selectListView(@Param("ew") Wrapper<KaoqindakaEntity> wrapper);

	List<KaoqindakaView> selectListView(Pagination page,@Param("ew") Wrapper<KaoqindakaEntity> wrapper);
	
	KaoqindakaView selectView(@Param("ew") Wrapper<KaoqindakaEntity> wrapper);


}
