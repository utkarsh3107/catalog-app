/**
 * 
 */
package com.interview.catalog.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.interview.catalog.bean.UserBean;
import com.interview.catalog.controller.ProductHandlerController;
import com.interview.catalog.dao.DAOHandler;
import com.interview.catalog.exception.CatalogException;

/**
 * @author lenov
 *
 */
public class FileReader {

	private static final Logger logger = Logger.getLogger(FileReader.class);
	
	private static Resource resource = new ClassPathResource("user.properties");
	
	public static void manualLoad() {
		LoggerUtility.logDebug(logger, "Enter manualLoad");
		Properties prop = new Properties();
		try (InputStream input = resource.getInputStream();) {

			// load a properties file
			prop.load(input);

			String name1 = prop.getProperty("user1");
			String name2 = prop.getProperty("user2");
			String pass1 = prop.getProperty("password1");
			String pass2 = prop.getProperty("password2");

			UserBean user1 = new UserBean(name1, pass1);
			UserBean user2 = new UserBean(name2, pass2);

			LoggerUtility.logDebug(logger,"User 1" + user1);
			LoggerUtility.logDebug(logger, "User 2" + user2);
			
			DAOHandler.addUser(user1);
			DAOHandler.addUser(user2);

		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (CatalogException ex) {
			ex.printStackTrace();
		}
		LoggerUtility.logDebug(logger,"Exit manualLoad");
	}
	

}
