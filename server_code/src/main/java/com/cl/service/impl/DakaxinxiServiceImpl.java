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


import com.cl.dao.DakaxinxiDao;
import com.cl.entity.DakaxinxiEntity;
import com.cl.service.DakaxinxiService;
import com.cl.entity.view.DakaxinxiView;

@Service("dakaxinxiService")
public class DakaxinxiServiceImpl extends ServiceImpl<DakaxinxiDao, DakaxinxiEntity> implements DakaxinxiService {

    	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DakaxinxiEntity> page = this.selectPage(
                new Query<DakaxinxiEntity>(params).getPage(),
                new EntityWrapper<DakaxinxiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<DakaxinxiEntity> wrapper) {
		  Page<DakaxinxiView> page =new Query<DakaxinxiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<DakaxinxiView> selectListView(Wrapper<DakaxinxiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public DakaxinxiView selectView(Wrapper<DakaxinxiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}
	
	


}
