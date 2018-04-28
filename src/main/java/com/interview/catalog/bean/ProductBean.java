/**
 * 
 */
package com.interview.catalog.bean;

import java.util.List;

/**
 * Product Bean to display product information
 *
 */
public class ProductBean {

	private String productId;
	
	private String productName;
	
	private String productDesc;
	
	private List<String> relatedProd;
	
	public ProductBean() {
		super();
	}

	public ProductBean(String productId, String productName, String productDesc, List<String> relatedProd) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productDesc = productDesc;
		this.relatedProd = relatedProd;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public List<String> getRelatedProd() {
		return relatedProd;
	}

	public void setRelatedProd(List<String> relatedProd) {
		this.relatedProd = relatedProd;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((productId == null) ? 0 : productId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductBean other = (ProductBean) obj;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		return true;
	}
	
	
	

}
