/**
 * 
 */
package com.interview.catalog.service;

import com.interview.catalog.bean.UserBean;
import com.interview.catalog.dao.DAOHandler;
import com.interview.catalog.exception.CatalogException;
import com.interview.catalog.utils.Utils;

/**
 * @author lenov
 *
 */
public class LoginService {

	public static boolean isValidUser(String userName, String passWord) throws CatalogException{
		boolean flag = false;
		UserBean expected = new UserBean(userName,passWord);		
		
		UserBean bean = DAOHandler.getUser(expected);
		
		if(!Utils.isEmpty(bean)) {
			flag = true;
		}
		
		return flag;
	}
	
}
