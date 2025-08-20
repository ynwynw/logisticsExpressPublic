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


import com.cl.dao.QianshouxinxiDao;
import com.cl.entity.QianshouxinxiEntity;
import com.cl.service.QianshouxinxiService;
import com.cl.entity.view.QianshouxinxiView;

@Service("qianshouxinxiService")
public class QianshouxinxiServiceImpl extends ServiceImpl<QianshouxinxiDao, QianshouxinxiEntity> implements QianshouxinxiService {

    	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<QianshouxinxiEntity> page = this.selectPage(
                new Query<QianshouxinxiEntity>(params).getPage(),
                new EntityWrapper<QianshouxinxiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<QianshouxinxiEntity> wrapper) {
		  Page<QianshouxinxiView> page =new Query<QianshouxinxiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<QianshouxinxiView> selectListView(Wrapper<QianshouxinxiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public QianshouxinxiView selectView(Wrapper<QianshouxinxiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}
	
	


}
