package com.light.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.light.dto.ImageHolder;
import com.light.dto.ShopExecution;
import com.light.entity.Area;
import com.light.entity.PersonInfo;
import com.light.entity.Shop;
import com.light.entity.ShopCategory;
import com.light.enums.ShopStateEnum;
import com.light.exceptions.ShopOperationException;
import com.light.service.AreaService;
import com.light.service.ShopService;
import com.light.util.HttpServletRequestUtil;


@Controller
@RequestMapping("/shopadmin")
public class ShopManagementController {
	@Autowired
	private ShopService shopService;

//	@RequestMapping(value = "/getshopmanagementinfo", method = RequestMethod.GET)
//	@ResponseBody
//	private Map<String, Object> getShopManagementInfo(HttpServletRequest request) {
//		Map<String, Object> modelMap = new HashMap<String, Object>();
//		long shopId = HttpServletRequestUtil.getLong(request, "shopId");
//		if (shopId <= 0) {
//			Object currentShopObj = request.getSession().getAttribute("currentShop");
//			if (currentShopObj == null) {
//				modelMap.put("redirect", true);
//				modelMap.put("url", "/o2o/shopadmin/shoplist");
//			} else {
//				Shop currentShop = (Shop) currentShopObj;
//				modelMap.put("redirect", false);
//				modelMap.put("shopId", currentShop.getShopId());
//			}
//		} else {
//			Shop currentShop = new Shop();
//			currentShop.setShopId(shopId);
//			request.getSession().setAttribute("currentShop", currentShop);
//			modelMap.put("redirect", false);
//		}
//		return modelMap;
//	}

	@RequestMapping(value = "/getshoplist", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getShopList(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		PersonInfo user = (PersonInfo) request.getSession().getAttribute("user");
		try {
			Shop shopCondition = new Shop();
			shopCondition.setOwner(user);
			ShopExecution se = shopService.getShopList(shopCondition, 0, 100);
			modelMap.put("shopList", se.getShopList());
			// 列出店铺成功之后，将店铺放入session中作为权限验证依据，即该帐号只能操作它自己的店铺
			request.getSession().setAttribute("shopList", se.getShopList());
			modelMap.put("user", user);
			modelMap.put("success", true);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}

//	@RequestMapping(value = "/getshopbyid", method = RequestMethod.GET)
//	@ResponseBody
//	private Map<String, Object> getShopById(HttpServletRequest request) {
//		Map<String, Object> modelMap = new HashMap<String, Object>();
//		Long shopId = HttpServletRequestUtil.getLong(request, "shopId");
//		if (shopId > -1) {
//			try {
//				Shop shop = shopService.getByShopId(shopId);
//				List<Area> areaList = areaService.getAreaList();
//				modelMap.put("shop", shop);
//				modelMap.put("areaList", areaList);
//				modelMap.put("success", true);
//			} catch (Exception e) {
//				modelMap.put("success", false);
//				modelMap.put("errMsg", e.toString());
//			}
//		} else {
//			modelMap.put("success", false);
//			modelMap.put("errMsg", "empty shopId");
//		}
//		return modelMap;
//	}

//	@RequestMapping(value = "/getshopinitinfo", method = RequestMethod.GET)
//	@ResponseBody
//	private Map<String, Object> getShopInitInfo() {
//		Map<String, Object> modelMap = new HashMap<String, Object>();
//		List<ShopCategory> shopCategoryList = new ArrayList<ShopCategory>();
//		List<Area> areaList = new ArrayList<Area>();
//		try {
//			shopCategoryList = shopCategoryService.getShopCategoryList(new ShopCategory());
//			areaList = areaService.getAreaList();
//			modelMap.put("shopCategoryList", shopCategoryList);
//			modelMap.put("areaList", areaList);
//			modelMap.put("success", true);
//		} catch (Exception e) {
//			modelMap.put("success", false);
//			modelMap.put("errMsg", e.getMessage());
//		}
//		return modelMap;
//	}

	@RequestMapping(value = "/registershop", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> registerShop(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();

		// 1.接收并转化相应的参数，包括店铺信息以及图片信息
		String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
		ObjectMapper mapper = new ObjectMapper();
		Shop shop = null;
		try {
			shop = mapper.readValue(shopStr, Shop.class);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		CommonsMultipartFile shopImg = null;
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		if (commonsMultipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
			shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "上传图片不能为空");
			return modelMap;
		}
		// 2.注册店铺
		if (shop != null && shopImg != null) {
			PersonInfo owner = (PersonInfo) request.getSession().getAttribute("user");
			shop.setOwner(owner);
			ShopExecution se;
			try {
				ImageHolder imageHolder = new ImageHolder(shopImg.getOriginalFilename(), shopImg.getInputStream());
				se = shopService.addShop(shop, imageHolder);
				if (se.getState() == ShopStateEnum.CHECK.getState()) {
					modelMap.put("success", true);
					// 该用户可以操作的店铺列表
					@SuppressWarnings("unchecked")
					List<Shop> shopList = (List<Shop>) request.getSession().getAttribute("shopList");
					if (shopList == null || shopList.size() == 0) {
						shopList = new ArrayList<Shop>();
					}
					shopList.add(se.getShop());
					request.getSession().setAttribute("shopList", shopList);
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", se.getStateInfo());
				}
			} catch (ShopOperationException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			} catch (IOException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			}
			return modelMap;
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入店铺信息");
			return modelMap;
		}
	}

	private static void inputStreamToFile(InputStream ins, File file) {
		OutputStream os = null;
		try {
			os = new FileOutputStream(file);
			int bytesRead = 0;
			byte[] buffer = new byte[1024];
			while((bytesRead= ins.read(buffer)) != -1)
			{
				os.write(buffer, 0, bytesRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				
			} catch (Exception e1) {
				// TODO: handle exception
			}
		}
	}
//	@RequestMapping(value = "/modifyshop", method = RequestMethod.POST)
//	@ResponseBody
//	private Map<String, Object> modifyShop(HttpServletRequest request){
//		Map<String, Object> modelMap = new HashMap<String, Object>();
//		if (!CodeUtil.checkVerifyCode(request)) {
//			modelMap.put("success", false);
//			modelMap.put("errMsg", "输入了错误的验证码");
//			return modelMap;
//		}
//		// 1.接收并转化相应的参数，包括店铺信息以及图片信息
//		String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
//		ObjectMapper mapper = new ObjectMapper();
//		Shop shop = null;
//		try {
//			shop = mapper.readValue(shopStr, Shop.class);
//		} catch (Exception e) {
//			modelMap.put("success", false);
//			modelMap.put("errMsg", e.getMessage());
//			return modelMap;
//		}
//		CommonsMultipartFile shopImg = null;
//		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
//				request.getSession().getServletContext());
//		if (commonsMultipartResolver.isMultipart(request)) {
//			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
//			shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
//		}
//		// 2.修改店铺信息
//		if (shop != null && shop.getShopId() != null) {
//			ShopExecution se;
//			try {
//				if (shopImg == null) {
//					se = shopService.modifyShop(shop, null);
//				} else {
//					ImageHolder imageHolder = new ImageHolder(shopImg.getOriginalFilename(), shopImg.getInputStream());
//					se = shopService.modifyShop(shop, imageHolder);
//				}
//				if (se.getState() == ShopStateEnum.SUCCESS.getState()) {
//					modelMap.put("success", true);
//				} else {
//					modelMap.put("success", false);
//					modelMap.put("errMsg", se.getStateInfo());
//				}
//			} catch (ShopOperationException e) {
//				modelMap.put("success", false);
//				modelMap.put("errMsg", e.getMessage());
//			} catch (IOException e) {
//				modelMap.put("success", false);
//				modelMap.put("errMsg", e.getMessage());
//			}
//			return modelMap;
//		} else {
//			modelMap.put("success", false);
//			modelMap.put("errMsg", "请输入店铺Id");
//			return modelMap;
//		}
//	}
}
