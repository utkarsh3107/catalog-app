/**
 * 
 */
package com.interview.catalog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.interview.catalog.service.HelloService;

/**
 * @author lenov
 *
 */
@Controller
public class HelloController {
	
	HelloService service = new HelloService();
	
	@RequestMapping("/hello")
	public ModelAndView hello(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		name = service.hello(name);
		
		ModelAndView modView = new ModelAndView();
		modView.setViewName("index.jsp");
		modView.addObject("result", name);
		return modView;
	}
}
