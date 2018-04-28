/**
 * 
 */
package com.interview.catalog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.interview.catalog.bean.UserBean;
import com.interview.catalog.constants.MessageConstants;
import com.interview.catalog.dao.DAOHandler;
import com.interview.catalog.exception.CatalogException;
import com.interview.catalog.service.LoginService;
import com.interview.catalog.utils.LoggerUtility;

/**
 * @author lenov
 *
 */
@Controller
@SessionAttributes("result")
public class LoginController {

	private static final Logger logger = Logger.getLogger(LoginController.class);

	LoginService service = new LoginService();

	/**
	 * Takes care of logging into the application.
	 * 
	 * @param model
	 *            - Used to store name value of the user in the session.
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/login")
	public ModelAndView hello(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		String pass = request.getParameter("password");
		LoggerUtility.logDebug(logger, "Enter isValidUser");

		ModelAndView modView = new ModelAndView();
		modView.setViewName("login");
		model.put("errMsg", MessageConstants.INVALID_USER);
		try {
			if (service.isValidUser(name, pass)) {
				model.put("result", name);
				modView = new ModelAndView("home", "productList", DAOHandler.getAllProducts(new UserBean(name, pass)));
			}
		} catch (CatalogException ex) {
			ex.printStackTrace();
		}

		return modView;
	}

	/**
	 * Takes care of logging out from the application.
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/logout")
	public ModelAndView logout(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		model.put("result", null);
		LoggerUtility.logDebug(logger, "Enter logout");

		ModelAndView modView = new ModelAndView();
		modView.setViewName("login");
		LoggerUtility.logDebug(logger, "Exit logout");
		return modView;
	}
}
