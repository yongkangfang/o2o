package com.light.dao;

import java.util.List;

import com.light.entity.Area;

public interface AreaDao {
	/*
	 * 列出列表区域
	 * @return areaList
	 */
	
	List<Area> queryArea();
	
	/*
	 * 插入区域信息
	 * @return
	 */
	
	int insertArea();
	
	/*
	 * 更新区域信息
	 * @return
	 */
	
	int updateArea();
	
	/*
	 * 删除区域信息
	 * @return
	 */
	
	int deleteArea();
	
	/*
	 * 批量删除区域列表
	 * @param areaIdList
	 */
	int batchDeleteArea(List<Long> areaIdList);
}
