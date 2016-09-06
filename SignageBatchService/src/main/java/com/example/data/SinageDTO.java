package com.example.data;

public class SinageDTO {

	
	private Integer priceId;
	private String storeId;
	private String productId;
	
	private String promotionId;
	private Double promoPrice;
	
	private String templateId;
	private String EPISYSTemplateId;
	private String dynamicCentralTemplateID;
	
	
	public Integer getPriceId() {
		return priceId;
	}
	public void setPriceId(Integer priceId) {
		this.priceId = priceId;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getPromotionId() {
		return promotionId;
	}
	public void setPromotionId(String promotionId) {
		this.promotionId = promotionId;
	}
	public Double getPromoPrice() {
		return promoPrice;
	}
	public void setPromoPrice(Double promoPrice) {
		this.promoPrice = promoPrice;
	}
	public String getTemplateId() {
		return templateId;
	}
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
	public String getEPISYSTemplateId() {
		return EPISYSTemplateId;
	}
	public void setEPISYSTemplateId(String ePISYSTemplateId) {
		EPISYSTemplateId = ePISYSTemplateId;
	}
	public String getDynamicCentralTemplateID() {
		return dynamicCentralTemplateID;
	}
	public void setDynamicCentralTemplateID(String dynamicCentralTemplateID) {
		this.dynamicCentralTemplateID = dynamicCentralTemplateID;
	}
	
}
