/**
 * 
 */
package com.interview.catalog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.interview.catalog.bean.UserBean;
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

	LoginService service = new LoginService();
	
	@RequestMapping("/login")
	public ModelAndView hello(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		String pass = request.getParameter("password");
		LoggerUtility.log("Enter isValidUser");
		
		ModelAndView modView = new ModelAndView();
		modView.setViewName("login");
		model.put("errMsg", "Invalid User!");
		try {
			if(service.isValidUser(name, pass)) {
				model.put("result", name);
				modView =  new ModelAndView("home", "productList", DAOHandler.getAllProducts(new UserBean(name,pass)));
			}
		}catch(CatalogException ex) {
			ex.printStackTrace();
		}

		return modView;
	}
}
