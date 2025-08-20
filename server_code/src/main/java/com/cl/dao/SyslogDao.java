package com.cl.dao;

import com.cl.entity.SyslogEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.SyslogView;


/**
 * 操作日志
 * 
 * @author 
 * @email 
 * @date 2025-05-15 21:38:20
 */
public interface SyslogDao extends BaseMapper<SyslogEntity> {
	
	List<SyslogView> selectListView(@Param("ew") Wrapper<SyslogEntity> wrapper);

	List<SyslogView> selectListView(Pagination page,@Param("ew") Wrapper<SyslogEntity> wrapper);
	
	SyslogView selectView(@Param("ew") Wrapper<SyslogEntity> wrapper);


}
