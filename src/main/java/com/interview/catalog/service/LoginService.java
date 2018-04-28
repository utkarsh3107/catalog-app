/**
 * 
 */
package com.interview.catalog.service;

import org.apache.log4j.Logger;

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

	private static final Logger logger = Logger.getLogger(LoginService.class);
	
	public boolean isValidUser(String userName, String passWord) throws CatalogException{
		LoggerUtility.logDebug(logger, "Enter isValidUser");
		LoggerUtility.logDebug(logger,"username: " + userName);
		LoggerUtility.logDebug(logger, "password: " + passWord);
		
		boolean flag = false;
		UserBean expected = new UserBean(userName,passWord);		
		
		UserBean bean = DAOHandler.getUser(expected);
		LoggerUtility.logDebug(logger, "UserBean value from dao: " + bean);
		if(!Utils.isEmpty(bean)) {
			flag = true;
		}
		LoggerUtility.logDebug(logger, "Exit isValidUser flag:" + flag);
		return flag;
	}
	
}
