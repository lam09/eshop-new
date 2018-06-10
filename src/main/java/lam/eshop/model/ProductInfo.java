package lam.eshop.model;

import lam.eshop.entity.Product;
import lam.eshop.entity.ProductImage;

import java.util.Set;

/**
 * Created by a.lam.tuan on 23. 5. 2018.
 */

public class ProductInfo {
    private String code;
    private String name;
    private String description;

    private double price;

    private Set<ProductImage> gallery;
    public ProductInfo() {
    }


    public ProductInfo(Product product) {
        this.code = product.getCode();
        this.name = product.getName();
        this.price = product.getPrice();
        this.description=product.getDescription();
        this.gallery=product.getGallery();
    }

    // Sử dụng trong JPA/Hibernate query
    public ProductInfo(String code, String name, double price) {
        this.code = code;
        this.name = name;
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Set<ProductImage> getGallery() {
        return gallery;
    }

    public void setGallery(Set<ProductImage> gallery) {
        this.gallery = gallery;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}