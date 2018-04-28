/**
 * 
 */
package com.interview.catalog.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

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

	private static final Logger logger = Logger.getLogger(DAOHandler.class);
	
	private static Map<UserBean, Map<String, ProductBean>> inventory = null;

	static {
		inventory = new HashMap<>();
		FileReader.manualLoad();
	}

	public static Set<UserBean> getUsers() throws CatalogException {
		LoggerUtility.logDebug(logger,"Entered getUsers");
		LoggerUtility.logDebug(logger,inventory.keySet());
		LoggerUtility.logDebug(logger,"Exit getUsers");
		return inventory.keySet();
	}

	public static void addUser(UserBean user) throws CatalogException {
		LoggerUtility.logDebug(logger,"Entered addUser");
		LoggerUtility.logDebug(logger,"UserBean name: " + user.getUsername() + " password:" + user.getPassword());
		if (inventory.containsKey(user)) {
			throw new CatalogException(ExceptionConstants.USER_ALREADY_EXISTS);
		}

		inventory.put(user, new HashMap<>());
		LoggerUtility.logDebug(logger,"Exit addUser");
	}

	public static UserBean getUser(UserBean user) throws CatalogException {
		LoggerUtility.logDebug(logger,"Entered getUser");
		LoggerUtility.logDebug(logger,"UserBean name: " + user.getUsername() + " password:" + user.getPassword());
		if (Utils.isEmpty(user)) {
			throw new CatalogException(ExceptionConstants.USER_NOT_FOUND);
		}

		if (inventory.containsKey(user)) {
			for (UserBean bean : inventory.keySet()) {
				if (bean.getPassword().equals(user.getPassword()) && bean.getUsername().equals(user.getUsername()))
					LoggerUtility.logDebug(logger,"getUser Successfull");
				return user;
			}
		}

		LoggerUtility.logDebug(logger,"Exit getUser");
		return null;
	}

	public static void addInventoryForUser(UserBean user, ProductBean product) throws CatalogException {
		LoggerUtility.logDebug(logger,"Entered addUpdateInventoryForUser");
		LoggerUtility.logDebug(logger,"UserBean name: " + user.getUsername() + " password:" + user.getPassword());
		LoggerUtility.logDebug(logger,"ProductBean id: " + product.getProductId() + " name:" + product.getProductName());
		if (Utils.isEmpty(user)) {
			throw new CatalogException(ExceptionConstants.USER_NOT_FOUND);
		}

		if (Utils.isEmpty(product)) {
			throw new CatalogException(ExceptionConstants.PRODUCT_NOT_FOUND);
		}

		inventory.get(user).put(product.getProductId(), product);
		LoggerUtility.logDebug(logger,"Exit addUpdateInventoryForUser");
	}

	public static void updateInventoryForUser(UserBean user, ProductBean product) throws CatalogException {
		LoggerUtility.logDebug(logger,"Entered updateInventoryForUser");
		LoggerUtility.logDebug(logger,"UserBean name: " + user.getUsername() + " password:" + user.getPassword());
		LoggerUtility.logDebug(logger,"ProductBean id: " + product.getProductId() + " name:" + product.getProductName());
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
		LoggerUtility.logDebug(logger,"Exit updateInventoryForUser");
	}

	public static long getInventorySizeForUser(UserBean user) throws CatalogException {
		LoggerUtility.logDebug(logger,"Entered getInventorySizeForUser");
		LoggerUtility.logDebug(logger,"UserBean name: " + user.getUsername() + " password:" + user.getPassword());
		if (Utils.isEmpty(user)) {
			throw new CatalogException(ExceptionConstants.USER_NOT_FOUND);
		}

		int size = inventory.get(user).size();
		LoggerUtility.logDebug(logger,"Exit getInventorySizeForUser return size: " + size);
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
		LoggerUtility.logDebug(logger,"Entered retriveInventory");
		LoggerUtility.logDebug(logger,"UserBean name: " + user.getUsername() + " password:" + user.getPassword());
		LoggerUtility.logDebug(logger,"Product ID: " + prodID);
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
		LoggerUtility.logDebug(logger,"Found bean: " + bean);
		LoggerUtility.logDebug(logger,"Exit retriveInventory");
		return bean;
	}

	public static boolean removeInventory(UserBean user, String prodID) throws CatalogException {
		LoggerUtility.logDebug(logger,"Entered retriveInventory");
		LoggerUtility.logDebug(logger,"UserBean name: " + user.getUsername() + " password:" + user.getPassword());
		LoggerUtility.logDebug(logger,"Product ID: " + prodID);
		if (Utils.isEmpty(user)) {
			throw new CatalogException(ExceptionConstants.USER_NOT_FOUND);
		}

		if (Utils.isEmpty(prodID)) {
			throw new CatalogException(ExceptionConstants.PRODUCT_NOT_FOUND);
		}

		Map<String, ProductBean> prodMap = inventory.get(user);

		if (prodMap.containsKey(prodID)) {
			prodMap.remove(prodID);
			LoggerUtility.logDebug(logger,"Removed Bean id: " + prodID);
			return true;
		}

		LoggerUtility.logDebug(logger,"Exit retriveInventory");
		return false;

	}

	public static void clearData() {
		inventory.replaceAll((k, v) -> new HashMap<>());
	}

}
