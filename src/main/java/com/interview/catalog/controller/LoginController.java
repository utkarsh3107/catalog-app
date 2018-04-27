/**
 * 
 */
package com.interview.catalog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.interview.catalog.exception.CatalogException;
import com.interview.catalog.service.LoginService;

/**
 * @author lenov
 *
 */
public class LoginController {

	LoginService service = new LoginService();
	
	@RequestMapping("/login")
	public ModelAndView hello(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("uname");
		String pass = request.getParameter("pass");
		
		ModelAndView modView = new ModelAndView();
		modView.setViewName("error.jsp");
		modView.addObject("result", name);
		try {
			if(service.isValidUser(name, pass)) {
				modView.setViewName("index.jsp");
				modView.addObject("result", name);
			}
		}catch(CatalogException ex) {
			ex.printStackTrace();
		}
	
		return modView;
	}
}
