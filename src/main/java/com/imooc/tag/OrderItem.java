package com.imooc.tag;

public class OrderItem {
	private String skuId;
	private Integer count;
	private Integer unitPrice;
	
	public String getSkuId() {
		return skuId;
	}
	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Integer unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	@Override
	public String toString() {
		return "OrderItem [skuId=" + skuId + ", count=" + count + ", unitPrice=" + unitPrice + "]";
	}
}
