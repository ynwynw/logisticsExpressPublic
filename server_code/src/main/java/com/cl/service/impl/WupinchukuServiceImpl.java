package com.cl.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cl.utils.PageUtils;
import com.cl.utils.Query;


import com.cl.dao.WupinchukuDao;
import com.cl.entity.WupinchukuEntity;
import com.cl.service.WupinchukuService;
import com.cl.entity.view.WupinchukuView;

@Service("wupinchukuService")
public class WupinchukuServiceImpl extends ServiceImpl<WupinchukuDao, WupinchukuEntity> implements WupinchukuService {

    	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<WupinchukuEntity> page = this.selectPage(
                new Query<WupinchukuEntity>(params).getPage(),
                new EntityWrapper<WupinchukuEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<WupinchukuEntity> wrapper) {
		  Page<WupinchukuView> page =new Query<WupinchukuView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<WupinchukuView> selectListView(Wrapper<WupinchukuEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public WupinchukuView selectView(Wrapper<WupinchukuEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}
	
	


}
