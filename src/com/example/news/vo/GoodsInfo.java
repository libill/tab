package com.example.news.vo;

import java.io.Serializable;

/**
 * Bill
 *
 */
public class GoodsInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -613603922765665169L;
	private String iid = "";
	private String click_url = "";
	private String seller_url = "";
	private String title = "";
	private String sid = "";
	private String seller_name = "";
	private String cid = "";
	private String pic_url = "";
	private String price = "";
	private String cash_ondelivery = "";
	private String freeshipment = "";
	private String installment = "";
	
	private String has_invoice = "";
	private String modified = "";
	private String price_reduction = "";
	private String price_decreases = "";
	
	private String original_price = "";
	private String cashback_scope = "";
	private String popular = "";
	private String original_pic_url = "";
	public GoodsInfo() {
		super();
	}
	
	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getSeller_name() {
		return seller_name;
	}

	public void setSeller_name(String seller_name) {
		this.seller_name = seller_name;
	}

	public String getIid() {
		return iid;
	}

	public void setIid(String iid) {
		this.iid = iid;
	}

	public String getSeller_url() {
		return seller_url;
	}

	public void setSeller_url(String seller_url) {
		this.seller_url = seller_url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getPic_url() {
		return pic_url;
	}

	public void setPic_url(String pic_url) {
		this.pic_url = pic_url;
	}

	public String getCash_ondelivery() {
		return cash_ondelivery;
	}

	public void setCash_ondelivery(String cash_ondelivery) {
		this.cash_ondelivery = cash_ondelivery;
	}

	public String getHas_invoice() {
		return has_invoice;
	}

	public void setHas_invoice(String has_invoice) {
		this.has_invoice = has_invoice;
	}

	public String getPrice_reduction() {
		return price_reduction;
	}

	public void setPrice_reduction(String price_reduction) {
		this.price_reduction = price_reduction;
	}

	public String getClick_url() {
		return click_url;
	}

	public void setClick_url(String click_url) {
		this.click_url = click_url;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getFreeshipment() {
		return freeshipment;
	}

	public void setFreeshipment(String freeshipment) {
		this.freeshipment = freeshipment;
	}

	public String getInstallment() {
		return installment;
	}

	public void setInstallment(String installment) {
		this.installment = installment;
	}

	public String getModified() {
		return modified;
	}

	public void setModified(String modified) {
		this.modified = modified;
	}

	public String getPrice_decreases() {
		return price_decreases;
	}

	public void setPrice_decreases(String price_decreases) {
		this.price_decreases = price_decreases;
	}

	public String getOriginal_price() {
		return original_price;
	}

	public void setOriginal_price(String original_price) {
		this.original_price = original_price;
	}

	public String getCashback_scope() {
		return cashback_scope;
	}

	public void setCashback_scope(String cashback_scope) {
		this.cashback_scope = cashback_scope;
	}

	public String getPopular() {
		return popular;
	}

	public void setPopular(String popular) {
		this.popular = popular;
	}

	public String getOriginal_pic_url() {
		return original_pic_url;
	}

	public void setOriginal_pic_url(String original_pic_url) {
		this.original_pic_url = original_pic_url;
	}

	@Override
	public String toString() {
		return "GoodsInfo [iid=" + iid + ", click_url=" + click_url
				+ ", seller_url=" + seller_url + ", title=" + title
				+ ", sid=" + sid + ", seller_name=" + seller_name
				+ ", cid=" + cid 
				+ ", pic_url=" + pic_url + ", price=" + price
				+ ", cash_ondelivery=" + cash_ondelivery + ", freeshipment=" + freeshipment
				+ ", installment=" + installment + ", has_invoice=" + has_invoice
				+ ", modified=" + modified + ", price_reduction=" + price_reduction
				+ ", price_decreases=" + price_decreases + ", original_price=" + original_price
				+ ", cashback_scope=" + cashback_scope 
				+ ", popular=" + popular + ", original_pic_url=" + original_pic_url
				+ "]";
	}
	
}
