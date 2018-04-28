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
import com.interview.catalog.constants.MessageConstants;
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

	// Static block used to add dummy users to the application.
	static {
		inventory = new HashMap<>();
		FileReader.manualLoad();
	}

	/**
	 * Get list of all users
	 * 
	 * @return
	 * @throws CatalogException
	 */
	public static Set<UserBean> getUsers() throws CatalogException {
		LoggerUtility.logDebug(logger, "Entered getUsers");
		LoggerUtility.logDebug(logger, inventory.keySet());
		LoggerUtility.logDebug(logger, "Exit getUsers");
		return inventory.keySet();
	}

	/**
	 * Add new users to the application
	 * 
	 * @return
	 * @throws CatalogException
	 */
	public static void addUser(UserBean user) throws CatalogException {
		LoggerUtility.logDebug(logger, "Entered addUser");
		LoggerUtility.logDebug(logger, "UserBean name: " + user.getUsername() + " password:" + user.getPassword());
		if (inventory.containsKey(user)) {
			throw new CatalogException(MessageConstants.USER_ALREADY_EXISTS);
		}

		inventory.put(user, new HashMap<>());
		LoggerUtility.logDebug(logger, "Exit addUser");
	}

	/**
	 * Get user based on username and password
	 * 
	 * @param user
	 * @return
	 * @throws CatalogException
	 */
	public static UserBean getUser(UserBean user) throws CatalogException {
		LoggerUtility.logDebug(logger, "Entered getUser");
		LoggerUtility.logDebug(logger, "UserBean name: " + user.getUsername() + " password:" + user.getPassword());
		if (Utils.isEmpty(user)) {
			throw new CatalogException(MessageConstants.USER_NOT_FOUND);
		}

		if (inventory.containsKey(user)) {
			for (UserBean bean : inventory.keySet()) {
				if (bean.getPassword().equals(user.getPassword()) && bean.getUsername().equals(user.getUsername()))
					LoggerUtility.logDebug(logger, "getUser Successfull");
				return user;
			}
		}

		LoggerUtility.logDebug(logger, "Exit getUser");
		return null;
	}

	/**
	 * Add new Product in DB for particular user.
	 * 
	 * @param user
	 * @param product
	 * @throws CatalogException
	 */
	public static void addInventoryForUser(UserBean user, ProductBean product) throws CatalogException {
		LoggerUtility.logDebug(logger, "Entered addUpdateInventoryForUser");
		LoggerUtility.logDebug(logger, "UserBean name: " + user.getUsername() + " password:" + user.getPassword());
		LoggerUtility.logDebug(logger,
				"ProductBean id: " + product.getProductId() + " name:" + product.getProductName());
		if (Utils.isEmpty(user)) {
			throw new CatalogException(MessageConstants.USER_NOT_FOUND);
		}

		if (Utils.isEmpty(product)) {
			throw new CatalogException(MessageConstants.PRODUCT_NOT_FOUND);
		}

		inventory.get(user).put(product.getProductId(), product);
		LoggerUtility.logDebug(logger, "Exit addUpdateInventoryForUser");
	}

	/**
	 * Update the product details for a user.
	 * 
	 * @param user
	 * @param product
	 * @throws CatalogException
	 */
	public static void updateInventoryForUser(UserBean user, ProductBean product) throws CatalogException {
		LoggerUtility.logDebug(logger, "Entered updateInventoryForUser");
		LoggerUtility.logDebug(logger, "UserBean name: " + user.getUsername() + " password:" + user.getPassword());
		LoggerUtility.logDebug(logger,
				"ProductBean id: " + product.getProductId() + " name:" + product.getProductName());
		if (Utils.isEmpty(user)) {
			throw new CatalogException(MessageConstants.USER_NOT_FOUND);
		}

		if (Utils.isEmpty(product)) {
			throw new CatalogException(MessageConstants.PRODUCT_NOT_FOUND);
		}

		Map<String, ProductBean> prodMap = inventory.get(user);

		if (prodMap.containsKey(product.getProductId())) {
			prodMap.put(product.getProductId(), product);
		}
		LoggerUtility.logDebug(logger, "Exit updateInventoryForUser");
	}

	/**
	 * Retrieves count of products for particular user.
	 * 
	 * @param user
	 * @return
	 * @throws CatalogException
	 */
	public static long getInventorySizeForUser(UserBean user) throws CatalogException {
		LoggerUtility.logDebug(logger, "Entered getInventorySizeForUser");
		LoggerUtility.logDebug(logger, "UserBean name: " + user.getUsername() + " password:" + user.getPassword());
		if (Utils.isEmpty(user)) {
			throw new CatalogException(MessageConstants.USER_NOT_FOUND);
		}

		int size = inventory.get(user).size();
		LoggerUtility.logDebug(logger, "Exit getInventorySizeForUser return size: " + size);
		return size;
	}

	/**
	 * Retrieves list of products for particular user.
	 * 
	 * @param user
	 * @return
	 * @throws CatalogException
	 */
	public static List<ProductBean> getAllProducts(UserBean user) throws CatalogException {
		List<ProductBean> prodList = new ArrayList<>();

		if (Utils.isEmpty(user)) {
			throw new CatalogException(MessageConstants.USER_NOT_FOUND);
		}

		if (inventory.containsKey(user)) {
			Map<String, ProductBean> map = inventory.get(user);
			for (String prodId : map.keySet()) {
				prodList.add(map.get(prodId));
			}
		}

		return prodList;
	}

	/**
	 * Retrieve particular product details for a user.
	 * 
	 * @param user
	 * @param prodID
	 * @return
	 * @throws CatalogException
	 */
	public static ProductBean retriveInventory(UserBean user, String prodID) throws CatalogException {
		LoggerUtility.logDebug(logger, "Entered retriveInventory");
		LoggerUtility.logDebug(logger, "UserBean name: " + user.getUsername() + " password:" + user.getPassword());
		LoggerUtility.logDebug(logger, "Product ID: " + prodID);
		ProductBean bean = null;
		if (Utils.isEmpty(user)) {
			throw new CatalogException(MessageConstants.USER_NOT_FOUND);
		}

		if (Utils.isEmpty(prodID)) {
			throw new CatalogException(MessageConstants.PRODUCT_NOT_FOUND);
		}

		Map<String, ProductBean> prodMap = inventory.get(user);

		if (prodMap.containsKey(prodID)) {
			bean = prodMap.get(prodID);
		}
		LoggerUtility.logDebug(logger, "Found bean: " + bean);
		LoggerUtility.logDebug(logger, "Exit retriveInventory");
		return bean;
	}

	/**
	 * Remove particular product for a user.
	 * 
	 * @param user
	 * @param prodID
	 * @return
	 * @throws CatalogException
	 */
	public static boolean removeInventory(UserBean user, String prodID) throws CatalogException {
		LoggerUtility.logDebug(logger, "Entered retriveInventory");
		LoggerUtility.logDebug(logger, "UserBean name: " + user.getUsername() + " password:" + user.getPassword());
		LoggerUtility.logDebug(logger, "Product ID: " + prodID);
		if (Utils.isEmpty(user)) {
			throw new CatalogException(MessageConstants.USER_NOT_FOUND);
		}

		if (Utils.isEmpty(prodID)) {
			throw new CatalogException(MessageConstants.PRODUCT_NOT_FOUND);
		}

		Map<String, ProductBean> prodMap = inventory.get(user);

		if (prodMap.containsKey(prodID)) {
			prodMap.remove(prodID);
			LoggerUtility.logDebug(logger, "Removed Bean id: " + prodID);
			return true;
		}

		LoggerUtility.logDebug(logger, "Exit retriveInventory");
		return false;

	}

	/**
	 * Clear all products from the map
	 */
	public static void clearData() {
		inventory.replaceAll((k, v) -> new HashMap<>());
	}

}
