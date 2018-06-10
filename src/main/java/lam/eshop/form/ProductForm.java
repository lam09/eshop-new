package lam.eshop.form;

/**
 * Created by a.lam.tuan on 23. 5. 2018.
 */
import lam.eshop.entity.Product;
import lam.eshop.entity.ProductImage;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ProductForm {
    private String code;
    private String name;
    private double price;
    private String description;

    private boolean newProduct = false;


    public ArrayList<ProductImage> getGallery() {
        return gallery;
    }

    public void setGallery(ArrayList<ProductImage> gallery) {
        this.gallery = gallery;
    }

    private ArrayList<ProductImage> gallery;
    // Upload file.
    private MultipartFile profilFile; //profil photo


    private MultipartFile[] fileData; //photo gallery

    public ProductForm() {
        this.newProduct= true;
    }

    public ProductForm(Product product) {
        this.code = product.getCode();
        this.name = product.getName();
        this.price = product.getPrice();
        this.description=product.getDescription();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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



    public boolean isNewProduct() {
        return newProduct;
    }

    public void setNewProduct(boolean newProduct) {
        this.newProduct = newProduct;
    }

    public MultipartFile getProfilFile() {
        return profilFile;
    }

    public void setProfilFile(MultipartFile profilFile) {
        this.profilFile = profilFile;
    }

    public MultipartFile[] getFileData() {
        return fileData;
    }

    public void setFileData(MultipartFile[] fileData) {
        this.fileData = fileData;
    }

}