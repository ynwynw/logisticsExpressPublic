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


import com.cl.dao.KuaidipeisongDao;
import com.cl.entity.KuaidipeisongEntity;
import com.cl.service.KuaidipeisongService;
import com.cl.entity.view.KuaidipeisongView;

@Service("kuaidipeisongService")
public class KuaidipeisongServiceImpl extends ServiceImpl<KuaidipeisongDao, KuaidipeisongEntity> implements KuaidipeisongService {

    	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<KuaidipeisongEntity> page = this.selectPage(
                new Query<KuaidipeisongEntity>(params).getPage(),
                new EntityWrapper<KuaidipeisongEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<KuaidipeisongEntity> wrapper) {
		  Page<KuaidipeisongView> page =new Query<KuaidipeisongView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<KuaidipeisongView> selectListView(Wrapper<KuaidipeisongEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public KuaidipeisongView selectView(Wrapper<KuaidipeisongEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}
	
	


}
