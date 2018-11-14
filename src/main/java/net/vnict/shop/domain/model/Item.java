package net.vnict.shop.domain.model;

import java.io.Serializable;

public class Item implements Serializable {

	private static final long serialVersionUID = 1L;

	private int productId;
    private String productName;
    private int productPrice;
    private int quantity;

    public Item() {
    }

    public Item(int productId, String productImage, String productName, int productPrice, int quantity) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
