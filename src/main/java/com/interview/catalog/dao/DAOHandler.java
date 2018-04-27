/**
 * 
 */
package com.interview.catalog.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.interview.catalog.bean.ProductBean;
import com.interview.catalog.bean.UserBean;
import com.interview.catalog.constants.ExceptionConstants;
import com.interview.catalog.exception.CatalogException;
import com.interview.catalog.utils.FileReader;
import com.interview.catalog.utils.LoggerUtility;
import com.interview.catalog.utils.Utils;

/**
 * @author lenov
 *
 */
public class DAOHandler {

	private static Map<UserBean, Map<String, ProductBean>> inventory = null;

	static {
		inventory = new HashMap<>();
		FileReader.manualLoad();
	}

	public static Set<UserBean> getUsers() throws CatalogException {
		LoggerUtility.log("Entered getUsers");
		LoggerUtility.log(inventory.keySet());
		LoggerUtility.log("Exit getUsers");
		return inventory.keySet();
	}

	public static void addUser(UserBean user) throws CatalogException {
		LoggerUtility.log("Entered addUser");
		LoggerUtility.log("UserBean name: " + user.getUsername() + " password:" + user.getPassword());
		if (inventory.containsKey(user)) {
			throw new CatalogException(ExceptionConstants.USER_ALREADY_EXISTS);
		}

		inventory.put(user, new HashMap<>());
		LoggerUtility.log("Exit addUser");
	}

	public static UserBean getUser(UserBean user) throws CatalogException {
		LoggerUtility.log("Entered getUser");
		LoggerUtility.log("UserBean name: " + user.getUsername() + " password:" + user.getPassword());
		if (Utils.isEmpty(user)) {
			throw new CatalogException(ExceptionConstants.USER_NOT_FOUND);
		}

		if (inventory.containsKey(user)) {
			for (UserBean bean : inventory.keySet()) {
				if (bean.getPassword().equals(user.getPassword()) && bean.getUsername().equals(user.getUsername()))
					LoggerUtility.log("getUser Successfull");
				return user;
			}
		}

		LoggerUtility.log("Exit getUser");
		return null;
	}

	public static void addInventoryForUser(UserBean user, ProductBean product) throws CatalogException {
		LoggerUtility.log("Entered addUpdateInventoryForUser");
		LoggerUtility.log("UserBean name: " + user.getUsername() + " password:" + user.getPassword());
		LoggerUtility.log("ProductBean id: " + product.getProductId() + " name:" + product.getProductName());
		if (Utils.isEmpty(user)) {
			throw new CatalogException(ExceptionConstants.USER_NOT_FOUND);
		}

		if (Utils.isEmpty(product)) {
			throw new CatalogException(ExceptionConstants.PRODUCT_NOT_FOUND);
		}

		inventory.get(user).put(product.getProductId(), product);
		LoggerUtility.log("Exit addUpdateInventoryForUser");
	}

	public static void updateInventoryForUser(UserBean user, ProductBean product) throws CatalogException {
		LoggerUtility.log("Entered updateInventoryForUser");
		LoggerUtility.log("UserBean name: " + user.getUsername() + " password:" + user.getPassword());
		LoggerUtility.log("ProductBean id: " + product.getProductId() + " name:" + product.getProductName());
		if (Utils.isEmpty(user)) {
			throw new CatalogException(ExceptionConstants.USER_NOT_FOUND);
		}

		if (Utils.isEmpty(product)) {
			throw new CatalogException(ExceptionConstants.PRODUCT_NOT_FOUND);
		}

		Map<String, ProductBean> prodMap = inventory.get(user);

		if (prodMap.containsKey(product.getProductId())) {
			prodMap.put(product.getProductId(), product);
		}
		LoggerUtility.log("Exit updateInventoryForUser");
	}

	public static long getInventorySizeForUser(UserBean user) throws CatalogException {
		LoggerUtility.log("Entered getInventorySizeForUser");
		LoggerUtility.log("UserBean name: " + user.getUsername() + " password:" + user.getPassword());
		if (Utils.isEmpty(user)) {
			throw new CatalogException(ExceptionConstants.USER_NOT_FOUND);
		}

		int size = inventory.get(user).size();
		LoggerUtility.log("Exit getInventorySizeForUser return size: " + size);
		return size;
	}

	public static List<ProductBean> getAllProducts(UserBean user) throws CatalogException {
		List<ProductBean> prodList = new ArrayList<>();

		if (Utils.isEmpty(user)) {
			throw new CatalogException(ExceptionConstants.USER_NOT_FOUND);
		}

		if (inventory.containsKey(user)) {
			Map<String, ProductBean> map = inventory.get(user);
			for (String prodId : map.keySet()) {
				prodList.add(map.get(prodId));
			}
		}

		return prodList;
	}

	public static ProductBean retriveInventory(UserBean user, String prodID) throws CatalogException {
		LoggerUtility.log("Entered retriveInventory");
		LoggerUtility.log("UserBean name: " + user.getUsername() + " password:" + user.getPassword());
		LoggerUtility.log("Product ID: " + prodID);
		ProductBean bean = null;
		if (Utils.isEmpty(user)) {
			throw new CatalogException(ExceptionConstants.USER_NOT_FOUND);
		}

		if (Utils.isEmpty(prodID)) {
			throw new CatalogException(ExceptionConstants.PRODUCT_NOT_FOUND);
		}

		Map<String, ProductBean> prodMap = inventory.get(user);

		if (prodMap.containsKey(prodID)) {
			bean = prodMap.get(prodID);
		}
		LoggerUtility.log("Found bean: " + bean);
		LoggerUtility.log("Exit retriveInventory");
		return bean;
	}

	public static boolean removeInventory(UserBean user, String prodID) throws CatalogException {
		LoggerUtility.log("Entered retriveInventory");
		LoggerUtility.log("UserBean name: " + user.getUsername() + " password:" + user.getPassword());
		LoggerUtility.log("Product ID: " + prodID);
		if (Utils.isEmpty(user)) {
			throw new CatalogException(ExceptionConstants.USER_NOT_FOUND);
		}

		if (Utils.isEmpty(prodID)) {
			throw new CatalogException(ExceptionConstants.PRODUCT_NOT_FOUND);
		}

		Map<String, ProductBean> prodMap = inventory.get(user);

		if (prodMap.containsKey(prodID)) {
			prodMap.remove(prodID);
			LoggerUtility.log("Removed Bean id: " + prodID);
			return true;
		}

		LoggerUtility.log("Exit retriveInventory");
		return false;

	}

	public static void clearData() {
		inventory.replaceAll((k, v) -> new HashMap<>());
	}

}
