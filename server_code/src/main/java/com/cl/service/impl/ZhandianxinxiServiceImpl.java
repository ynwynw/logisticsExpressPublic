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


import com.cl.dao.ZhandianxinxiDao;
import com.cl.entity.ZhandianxinxiEntity;
import com.cl.service.ZhandianxinxiService;
import com.cl.entity.view.ZhandianxinxiView;

@Service("zhandianxinxiService")
public class ZhandianxinxiServiceImpl extends ServiceImpl<ZhandianxinxiDao, ZhandianxinxiEntity> implements ZhandianxinxiService {

    	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ZhandianxinxiEntity> page = this.selectPage(
                new Query<ZhandianxinxiEntity>(params).getPage(),
                new EntityWrapper<ZhandianxinxiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<ZhandianxinxiEntity> wrapper) {
		  Page<ZhandianxinxiView> page =new Query<ZhandianxinxiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<ZhandianxinxiView> selectListView(Wrapper<ZhandianxinxiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ZhandianxinxiView selectView(Wrapper<ZhandianxinxiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}
	
	


}
