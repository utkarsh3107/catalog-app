package com.interview.catalog.utill;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.interview.catalog.bean.ProductBean;
import com.interview.catalog.bean.UserBean;

public class ValueInitializer {

	public static List<ProductBean> getProdList(int size){
		List<ProductBean> list = new ArrayList<>();
		for(int i = 0 ; i < size ; i++) {
			list.add(getUniqueProduct());
		}
		return list;
	}
	
	public static ProductBean getUniqueProduct() {
		ProductBean bean = new ProductBean(UUID.randomUUID().toString(), "name" , "desc" , Arrays.asList(new String[] {"item1" , "item2"}));
		return bean;
	}
	
	public static ProductBean getSingleProduct() {
		ProductBean bean = new ProductBean("id", "name" , "desc" , Arrays.asList(new String[] {"item1" , "item2"}));
		return bean;
	}
	
	public static UserBean getValidUser() {
		return new UserBean("rahul", "password");
	}
	
	public static UserBean getInValidUser() {
		return new UserBean("someone", "password");
	}
}
