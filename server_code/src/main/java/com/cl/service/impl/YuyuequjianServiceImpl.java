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


import com.cl.dao.YuyuequjianDao;
import com.cl.entity.YuyuequjianEntity;
import com.cl.service.YuyuequjianService;
import com.cl.entity.view.YuyuequjianView;

@Service("yuyuequjianService")
public class YuyuequjianServiceImpl extends ServiceImpl<YuyuequjianDao, YuyuequjianEntity> implements YuyuequjianService {

    	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<YuyuequjianEntity> page = this.selectPage(
                new Query<YuyuequjianEntity>(params).getPage(),
                new EntityWrapper<YuyuequjianEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<YuyuequjianEntity> wrapper) {
		  Page<YuyuequjianView> page =new Query<YuyuequjianView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<YuyuequjianView> selectListView(Wrapper<YuyuequjianEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public YuyuequjianView selectView(Wrapper<YuyuequjianEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}
	
	


}
