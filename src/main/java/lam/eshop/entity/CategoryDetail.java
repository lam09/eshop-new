package lam.eshop.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by a.lam.tuan on 29. 5. 2018.
 */
@Entity
@Table(name = "CATEGORY_DETAILS")
public class CategoryDetail  implements Serializable {

    private static final long serialVersionUID = 2054386655979281969L;

    @Id
    @Column(name = "ID", length = 50, nullable = false)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID", nullable = false, //
            foreignKey = @ForeignKey(name = "CATEGORY_DETAIL_CATE_FK"))
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID", nullable = false, //
            foreignKey = @ForeignKey(name = "CATEGORY_DETAIL_PROD_FK"))
    private Product product;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}