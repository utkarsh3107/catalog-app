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
import com.interview.catalog.constants.MessageConstants;
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

	/**
	 * Adds a new product.
	 * 
	 * @param name
	 *            - username from session
	 * @param request
	 * @param response
	 * @return
	 * @throws CatalogException
	 */
	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	public ModelAndView getProducts(@SessionAttribute("result") String name, HttpServletRequest request,
			HttpServletResponse response) throws CatalogException {
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
		modView.addObject("msg", MessageConstants.DATA_ADDED_SUCCSS);
		LoggerUtility.logDebug(logger, "Exit getProducts");
		return modView;
	}

	/**
	 * Delete a particular from based on its ID.
	 * 
	 * @param name
	 * @param id
	 * @return
	 * @throws CatalogException
	 */
	@RequestMapping(value = "/productDelete/{id}", method = RequestMethod.GET)
	public ModelAndView removeProduct(@SessionAttribute("result") String name, @PathVariable String id)
			throws CatalogException {
		LoggerUtility.logDebug(logger, "Enter removeProduct");
		UserBean user = new UserBean(name, "");

		DAOHandler.removeInventory(user, id);
		ModelAndView modView = new ModelAndView();
		modView.setViewName("redirect:/reloadHome");
		modView.addObject("msg", MessageConstants.DATA_REMOVED_SUCCSS);
		// return new ModelAndView("home");
		LoggerUtility.logDebug(logger, "Exit removeProduct");
		return modView;
	}

	/**
	 * Edit a product based on its ID.
	 * 
	 * @param name
	 * @param id
	 * @return
	 * @throws CatalogException
	 */
	@RequestMapping(value = "/productEdit/{id}", method = RequestMethod.GET)
	public ModelAndView updateProduct(@SessionAttribute("result") String name, @PathVariable String id)
			throws CatalogException {
		LoggerUtility.logDebug(logger, "Enter updateProduct");
		UserBean user = new UserBean(name, "");

		ProductBean bean = DAOHandler.retriveInventory(user, id);
		ModelAndView modView = new ModelAndView("editProduct", "command", bean);
		modView.addObject("prodID", id);
		LoggerUtility.logDebug(logger, "Exit updateProduct");
		return modView;
	}

	/**
	 * Update a product based.
	 * 
	 * @param name
	 * @param product
	 * @return
	 * @throws CatalogException
	 */
	@RequestMapping(value = "/updateProduct", method = RequestMethod.POST)
	public ModelAndView updateProductValues(@SessionAttribute("result") String name,
			@ModelAttribute("product") ProductBean product) throws CatalogException {
		LoggerUtility.logDebug(logger, "Enter updateProductValues");
		UserBean user = new UserBean(name, "");

		DAOHandler.updateInventoryForUser(user, product);
		ModelAndView modView = new ModelAndView();
		modView.setViewName("home");
		modView.addObject("msg", MessageConstants.DATA_UPDATED_SUCCSS);
		// return new ModelAndView("home");

		// return new ModelAndView("redirect:/reloadHome");
		LoggerUtility.logDebug(logger, "Exit updateProductValues");
		return modView;
	}

	/**
	 * Internal redirection for one JSP page to another for edit.
	 * 
	 * @param name
	 * @return
	 * @throws CatalogException
	 */
	@RequestMapping("/reloadHome")
	public ModelAndView viewemp(@SessionAttribute("result") String name) throws CatalogException {
		return new ModelAndView("home");
	}

	/**
	 * Called at reload of body of home page. Fetches all the products from the
	 * DB.
	 * 
	 * @param name
	 * @return
	 * @throws CatalogException
	 */
	@RequestMapping("/reloadProdHome")
	public @ResponseBody List<ProductBean> producthome(@SessionAttribute("result") String name)
			throws CatalogException {
		LoggerUtility.logDebug(logger, "Enter producthome");
		UserBean user = new UserBean(name, "");
		LoggerUtility.logDebug(logger, "Exit producthome");
		return DAOHandler.getAllProducts(user);
	}
}
