package com.interview.catalog.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Set;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import com.interview.catalog.bean.ProductBean;
import com.interview.catalog.bean.UserBean;
import com.interview.catalog.dao.DAOHandler;
import com.interview.catalog.exception.CatalogException;
import com.interview.catalog.utill.ValueInitializer;
import com.interview.catalog.utils.FileReader;

/**
 * @author lenov
 *
 */
public class DAOHandlerTest {

	@BeforeClass
	public static void beforeClass() {
		FileReader.manualLoad();
	}
	
	@After
	public void after() {
		DAOHandler.clearData();
	}

	@Test
	public void checkUserPopulated() throws CatalogException {
		Set<UserBean> list = DAOHandler.getUsers();
		assertNotNull(list);		
	}
	
	@Test
	public void checkUserExists() throws CatalogException {
		UserBean expectedbean = ValueInitializer.getValidUser();
		UserBean actualBean = DAOHandler.getUser(expectedbean);
		assertEquals(expectedbean, actualBean);	
	}
	
	@Test
	public void checkUserExistsNegative() throws CatalogException {
		UserBean expectedbean = ValueInitializer.getInValidUser();
		UserBean actualBean = DAOHandler.getUser(expectedbean);
		assertNull(actualBean);
	}
	
	@Test
	public void addInvetory() throws CatalogException{
		ProductBean pBean = ValueInitializer.getSingleProduct();
		UserBean uBean = ValueInitializer.getValidUser();
		
		DAOHandler.addInventoryForUser(uBean, pBean);
		
		assertEquals(DAOHandler.getInventorySizeForUser(uBean), 1L);
		
	}
	
	@Test
	public void removeInvetory() throws CatalogException{
		ProductBean pBean = ValueInitializer.getSingleProduct();
		UserBean uBean = ValueInitializer.getValidUser();
		DAOHandler.addInventoryForUser(uBean, pBean);
		assertEquals(1L , DAOHandler.getInventorySizeForUser(uBean));
		
		DAOHandler.removeInventory(uBean, pBean.getProductId());
		
		assertEquals(DAOHandler.getInventorySizeForUser(uBean), 0L);
		
	}
	
	@Test
	public void retrieveInvetory() throws CatalogException{
		ProductBean pBean = ValueInitializer.getSingleProduct();
		UserBean uBean = ValueInitializer.getValidUser();
		DAOHandler.addInventoryForUser(uBean, pBean);
		assertEquals(DAOHandler.getInventorySizeForUser(uBean), 1L);
		
		ProductBean expectedBean = DAOHandler.retriveInventory(uBean, pBean.getProductId());
		
		assertEquals(expectedBean,pBean);
		
	}
	
	@Test
	public void updateInventory() throws CatalogException{
		ProductBean pBean = ValueInitializer.getSingleProduct();
		UserBean uBean = ValueInitializer.getValidUser();
		DAOHandler.addInventoryForUser(uBean, pBean);
		assertEquals(DAOHandler.getInventorySizeForUser(uBean), 1L);
		
		pBean.setProductDesc("new Desc");
		pBean.setProductName("new Name");
		
		DAOHandler.updateInventoryForUser(uBean, pBean);
		
		ProductBean expectedBean = DAOHandler.retriveInventory(uBean, pBean.getProductId());
		assertEquals("new Name",expectedBean.getProductName());
		assertEquals("new Desc",expectedBean.getProductDesc());
	}
	

}
