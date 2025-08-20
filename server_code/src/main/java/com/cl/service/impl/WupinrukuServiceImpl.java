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


import com.cl.dao.WupinrukuDao;
import com.cl.entity.WupinrukuEntity;
import com.cl.service.WupinrukuService;
import com.cl.entity.view.WupinrukuView;

@Service("wupinrukuService")
public class WupinrukuServiceImpl extends ServiceImpl<WupinrukuDao, WupinrukuEntity> implements WupinrukuService {

    	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<WupinrukuEntity> page = this.selectPage(
                new Query<WupinrukuEntity>(params).getPage(),
                new EntityWrapper<WupinrukuEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<WupinrukuEntity> wrapper) {
		  Page<WupinrukuView> page =new Query<WupinrukuView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<WupinrukuView> selectListView(Wrapper<WupinrukuEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public WupinrukuView selectView(Wrapper<WupinrukuEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}
	
	


}
