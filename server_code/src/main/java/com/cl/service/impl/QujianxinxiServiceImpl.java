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


import com.cl.dao.QujianxinxiDao;
import com.cl.entity.QujianxinxiEntity;
import com.cl.service.QujianxinxiService;
import com.cl.entity.view.QujianxinxiView;

@Service("qujianxinxiService")
public class QujianxinxiServiceImpl extends ServiceImpl<QujianxinxiDao, QujianxinxiEntity> implements QujianxinxiService {

    	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<QujianxinxiEntity> page = this.selectPage(
                new Query<QujianxinxiEntity>(params).getPage(),
                new EntityWrapper<QujianxinxiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<QujianxinxiEntity> wrapper) {
		  Page<QujianxinxiView> page =new Query<QujianxinxiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<QujianxinxiView> selectListView(Wrapper<QujianxinxiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public QujianxinxiView selectView(Wrapper<QujianxinxiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}
	
	


}
