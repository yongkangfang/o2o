package com.light.service.impl;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.light.dao.ShopDao;
import com.light.dto.ImageHolder;
import com.light.dto.ShopExecution;
import com.light.entity.Shop;
import com.light.enums.ShopStateEnum;
import com.light.exceptions.ShopOperationException;
import com.light.service.ShopService;
import com.light.util.ImageUtil;
import com.light.util.PathUtil;

@Service
public class ShopServiceImpl implements ShopService{

	@Autowired
	private ShopDao shopDao;
	

	@Override
	public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) {
		return null;
	}


	@Override
	public Shop getByShopId(long shopId) {
		return null;
	}


	@Override
	public ShopExecution modifyShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException {
		return null;
	}


	@Override
	@Transactional
	public ShopExecution addShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException {
		// 空值判断
		if (shop == null) {
			return new ShopExecution(ShopStateEnum.NULL_SHOP);
		}
		try {
			// 给店铺信息赋初始值
			shop.setEnableStatus(0);
			shop.setCreateTime(new Date());
			shop.setLastEditTime(new Date());
			// 添加店铺信息
			int effectedNum = shopDao.insertShop(shop);
			if (effectedNum <= 0) {
				throw new ShopOperationException("店铺创建失败");
			} else {
				if (thumbnail.getImage() != null) {
					// 存储图片
					try {
						System.out.println("thumbnail is null = " + thumbnail == null);
						addShopImg(shop, thumbnail);
					} catch (Exception e) {
						e.printStackTrace();
						throw new ShopOperationException("addShopImg error:" + e.getMessage());
					}
					// 更新店铺的图片地址
					effectedNum = shopDao.updateShop(shop);
					if (effectedNum <= 0) {
						throw new ShopOperationException("更新图片地址失败");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ShopOperationException("addShop error:" + e.getMessage());
		}
		return new ShopExecution(ShopStateEnum.CHECK, shop);
	}


	private void addShopImg(Shop shop, ImageHolder thumbnail) {
		// 获取shop图片目录的相对值路径
		String dest = PathUtil.getShopImagePath(shop.getShopId());
		String shopImgAddr = ImageUtil.generateThumbnail(thumbnail, dest);
		shop.setShopImg(shopImgAddr);
	}
	
}
