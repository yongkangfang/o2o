package com.light.dao;

import com.light.entity.Shop;

public interface ShopDao {
	/*
	 * 新增店铺
	 * @param Shop
	 */
	int insertShop(Shop shop);
	
	/*
	 * 更新店铺
	 * @param Shop
	 */
	int updateShop(Shop shop);
}
