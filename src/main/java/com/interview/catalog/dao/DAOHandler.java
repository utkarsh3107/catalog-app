/**
 * 
 */
package com.interview.catalog.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.interview.catalog.bean.ProductBean;
import com.interview.catalog.bean.UserBean;
import com.interview.catalog.constants.ExceptionConstants;
import com.interview.catalog.exception.CatalogException;
import com.interview.catalog.utils.Utils;

/**
 * @author lenov
 *
 */
public class DAOHandler {

	private static Map<UserBean, Map<String,ProductBean>> inventory = new HashMap<>();

	public static Set<UserBean> getUsers() throws CatalogException{
		 return inventory.keySet();
	}
	
	public static void addUser(UserBean user) throws CatalogException {
		if (inventory.containsKey(user)) {
			throw new CatalogException(ExceptionConstants.USER_ALREADY_EXISTS);
		}

		inventory.put(user, new HashMap<>());
	}
	
	public static UserBean getUser(UserBean user) throws CatalogException{
		if (Utils.isEmpty(user)) {
			throw new CatalogException(ExceptionConstants.USER_NOT_FOUND);
		}
		
		if(inventory.containsKey(user)) {
			for(UserBean bean : inventory.keySet()) {
				if(bean.getPassword().equals(user.getPassword()) && bean.getUsername().equals(user.getUsername()))
					return user;
			}
		}
		
		return null;
	}

	public static void addUpdateInventoryForUser(UserBean user, ProductBean product) throws CatalogException {
		if (Utils.isEmpty(user)) {
			throw new CatalogException(ExceptionConstants.USER_NOT_FOUND);
		}

		if (Utils.isEmpty(product)) {
			throw new CatalogException(ExceptionConstants.PRODUCT_NOT_FOUND);
		}

		inventory.get(user).put(product.getProductId(), product);
	}
	
	public static void updateInventoryForUser(UserBean user, ProductBean product)  throws CatalogException{
		if (Utils.isEmpty(user)) {
			throw new CatalogException(ExceptionConstants.USER_NOT_FOUND);
		}

		if (Utils.isEmpty(product)) {
			throw new CatalogException(ExceptionConstants.PRODUCT_NOT_FOUND);
		}

		Map<String,ProductBean> prodMap = inventory.get(user);
		
		if(prodMap.containsKey(product.getProductId())) {
			prodMap.put(product.getProductId(), product);
		}
		
	}
	
	public static long getInventorySizeForUser(UserBean user) throws CatalogException {
		if (Utils.isEmpty(user)) {
			throw new CatalogException(ExceptionConstants.USER_NOT_FOUND);
		}
		
		return inventory.get(user).size();
	}

	public static ProductBean retriveInventory(UserBean user, String prodID) throws CatalogException {
		ProductBean bean = null;
		if (Utils.isEmpty(user)) {
			throw new CatalogException(ExceptionConstants.USER_NOT_FOUND);
		}

		if (Utils.isEmpty(prodID)) {
			throw new CatalogException(ExceptionConstants.PRODUCT_NOT_FOUND);
		}

		Map<String,ProductBean> prodMap = inventory.get(user);

		if(prodMap.containsKey(prodID)) {
			bean = prodMap.get(prodID);
		}
		
		return bean;
	}

	public static boolean removeInventory(UserBean user, String prodID) throws CatalogException {
		if (Utils.isEmpty(user)) {
			throw new CatalogException(ExceptionConstants.USER_NOT_FOUND);
		}

		if (Utils.isEmpty(prodID)) {
			throw new CatalogException(ExceptionConstants.PRODUCT_NOT_FOUND);
		}

		Map<String,ProductBean> prodMap = inventory.get(user);
		
		if(prodMap.containsKey(prodID)) {
			prodMap.remove(prodID);
			return true;
		}
		
		return false;
	}
	
	public static void clearData() {
		inventory.replaceAll((k, v) -> new HashMap<>());
	}

}
