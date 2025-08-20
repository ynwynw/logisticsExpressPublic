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


import com.cl.dao.JijianxinxiDao;
import com.cl.entity.JijianxinxiEntity;
import com.cl.service.JijianxinxiService;
import com.cl.entity.view.JijianxinxiView;

@Service("jijianxinxiService")
public class JijianxinxiServiceImpl extends ServiceImpl<JijianxinxiDao, JijianxinxiEntity> implements JijianxinxiService {

    	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<JijianxinxiEntity> page = this.selectPage(
                new Query<JijianxinxiEntity>(params).getPage(),
                new EntityWrapper<JijianxinxiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<JijianxinxiEntity> wrapper) {
		  Page<JijianxinxiView> page =new Query<JijianxinxiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<JijianxinxiView> selectListView(Wrapper<JijianxinxiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public JijianxinxiView selectView(Wrapper<JijianxinxiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}
	
	


}
