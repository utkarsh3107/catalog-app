/**
 * 
 */
package com.interview.catalog.controller;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.interview.catalog.bean.ProductBean;
import com.interview.catalog.bean.UserBean;
import com.interview.catalog.dao.DAOHandler;
import com.interview.catalog.exception.CatalogException;
import com.interview.catalog.utils.LoggerUtility;

/**
 * @author Utkarsh Thusoo
 *
 */
@Controller
public class ProductHandlerController {

	private static final Logger logger = Logger.getLogger(ProductHandlerController.class);
	
	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	public ModelAndView getProducts(@SessionAttribute("result") String name,HttpServletRequest request, HttpServletResponse response) throws CatalogException{
		LoggerUtility.logDebug(logger, "Enter getProducts");
		UserBean user = new UserBean(name, "");
		ProductBean bean = new ProductBean();
		bean.setProductId(UUID.randomUUID().toString());
		bean.setProductName(request.getParameter("pname"));
		bean.setProductDesc(request.getParameter("pdesc"));
		bean.setRelatedProd(Arrays.asList(request.getParameter("relatedproc")));
		DAOHandler.addInventoryForUser(user, bean);
		
		ModelAndView modView = new ModelAndView("home");
		modView.setViewName("home");
		modView.addObject("msg", "Data Added Successfully!");
		LoggerUtility.logDebug(logger, "Exit getProducts");
		return modView;
	}
	
	@RequestMapping(value = "/productDelete/{id}", method = RequestMethod.GET)
	public ModelAndView removeProduct(@SessionAttribute("result") String name,@PathVariable String id) throws CatalogException{
		LoggerUtility.logDebug(logger, "Enter removeProduct");
		UserBean user = new UserBean(name, "");
		
		DAOHandler.removeInventory(user, id);
		ModelAndView modView = new ModelAndView();
		modView.setViewName("redirect:/reloadHome");
		modView.addObject("msg", "Data Removed Successfully!");
		//return new ModelAndView("home");  
		LoggerUtility.logDebug(logger, "Exit removeProduct");
		return modView;  
	}

	@RequestMapping(value = "/productEdit/{id}", method = RequestMethod.GET)
	public ModelAndView updateProduct(@SessionAttribute("result") String name,@PathVariable String id) throws CatalogException{
		LoggerUtility.logDebug(logger, "Enter updateProduct");
		UserBean user = new UserBean(name, "");
		
		ProductBean bean = DAOHandler.retriveInventory(user, id);
		ModelAndView modView = new ModelAndView("editProduct", "command", bean);
		modView.addObject("prodID", id);
		LoggerUtility.logDebug(logger, "Exit updateProduct");
		return modView;
	}
	
	@RequestMapping(value="/updateProduct",method = RequestMethod.POST)
	public ModelAndView updateProductValues(@SessionAttribute("result") String name, @ModelAttribute("product") ProductBean product) throws CatalogException{
		LoggerUtility.logDebug(logger, "Enter updateProductValues");
		UserBean user = new UserBean(name, "");
		
		DAOHandler.updateInventoryForUser(user, product);
		ModelAndView modView = new ModelAndView();
		modView.setViewName("home");
		modView.addObject("msg", "Data Updated Successfully!");
		//return new ModelAndView("home");  
		
		//return new ModelAndView("redirect:/reloadHome"); 
		LoggerUtility.logDebug(logger, "Exit updateProductValues");
		return modView; 
	}
	
    @RequestMapping("/reloadHome")  
    public ModelAndView viewemp(@SessionAttribute("result") String name) throws CatalogException{  
    	UserBean user = new UserBean(name, "");
        return new ModelAndView("home", "productList", DAOHandler.getAllProducts(user));
    }
    
    @RequestMapping("/reloadProdHome")  
    public @ResponseBody List<ProductBean> producthome(@SessionAttribute("result") String name) throws CatalogException{ 
    	LoggerUtility.logDebug(logger, "Enter producthome");
    	UserBean user = new UserBean(name, "");
    	LoggerUtility.logDebug(logger, "Exit producthome");
    	return DAOHandler.getAllProducts(user);
    }
}
