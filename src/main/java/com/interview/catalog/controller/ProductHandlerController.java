/**
 * 
 */
package com.interview.catalog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.interview.catalog.bean.ProductBean;
import com.interview.catalog.bean.UserBean;
import com.interview.catalog.dao.DAOHandler;
import com.interview.catalog.exception.CatalogException;

/**
 * @author Utkarsh Thusoo
 *
 */
@Controller
public class ProductHandlerController {

	@RequestMapping(value = "/addProduct", method = RequestMethod.GET)
	public ModelAndView getProducts(@SessionAttribute("result") String name,HttpServletRequest request, HttpServletResponse response) throws CatalogException{
		UserBean user = new UserBean(name, "");
		ProductBean bean = new ProductBean();
		bean.setProductId(request.getParameter("productId"));
		bean.setProductName(request.getParameter("productName"));
		bean.setProductDesc(request.getParameter("productDesc"));
		
		DAOHandler.addInventoryForUser(user, bean);
		return new ModelAndView("home", "productList", DAOHandler.getAllProducts(user));
	}
	
	@RequestMapping(value = "/productDelete/{id}", method = RequestMethod.GET)
	public ModelAndView removeProduct(@SessionAttribute("result") String name,@PathVariable String id) throws CatalogException{
		UserBean user = new UserBean(name, "");
		
		DAOHandler.removeInventory(user, id);
		return new ModelAndView("redirect:/reloadHome");  
		//return new ModelAndView("home", "productList", DAOHandler.getAllProducts(user));
	}
	
	@RequestMapping(value = "/productEdit/{id}", method = RequestMethod.GET)
	public ModelAndView updateProduct(@SessionAttribute("result") String name,@PathVariable String id) throws CatalogException{
		UserBean user = new UserBean(name, "");
		
		ProductBean bean = DAOHandler.retriveInventory(user, id);
		return new ModelAndView("editProduct", "command", bean);
	}
	
	@RequestMapping(value="/updateProduct",method = RequestMethod.POST)
	public ModelAndView updateProductValues(@SessionAttribute("result") String name, @ModelAttribute("product") ProductBean product) throws CatalogException{
		UserBean user = new UserBean(name, "");
		
		DAOHandler.updateInventoryForUser(user, product);
		return new ModelAndView("redirect:/reloadHome");  
	}
	
    @RequestMapping("/reloadHome")  
    public ModelAndView viewemp(@SessionAttribute("result") String name) throws CatalogException{  
    	UserBean user = new UserBean(name, "");
        return new ModelAndView("home", "productList", DAOHandler.getAllProducts(user));
    }
}