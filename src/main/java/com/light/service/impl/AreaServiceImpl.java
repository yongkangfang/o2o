package com.light.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.light.dao.AreaDao;
import com.light.entity.Area;
import com.light.service.AreaService;

@Service
public class AreaServiceImpl implements AreaService{

	@Autowired
	private AreaDao areaDao;
	
	
	@Override
	public List<Area> getAreaList() {
		return areaDao.queryArea();
	}
	
}
