/**
 * 
 */
package com.interview.catalog.service;

import com.interview.catalog.bean.UserBean;
import com.interview.catalog.dao.DAOHandler;
import com.interview.catalog.exception.CatalogException;
import com.interview.catalog.utils.LoggerUtility;
import com.interview.catalog.utils.Utils;

/**
 * @author lenov
 *
 */
public class LoginService {

	public boolean isValidUser(String userName, String passWord) throws CatalogException{
		LoggerUtility.log("Enter isValidUser");
		LoggerUtility.log("username: " + userName);
		LoggerUtility.log("password: " + passWord);
		
		boolean flag = false;
		UserBean expected = new UserBean(userName,passWord);		
		
		UserBean bean = DAOHandler.getUser(expected);
		LoggerUtility.log("UserBean value from dao: " + bean);
		if(!Utils.isEmpty(bean)) {
			flag = true;
		}
		LoggerUtility.log("Exit isValidUser flag:" + flag);
		return flag;
	}
	
}
