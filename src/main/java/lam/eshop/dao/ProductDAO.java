package lam.eshop.dao;

/**
 * Created by a.lam.tuan on 23. 5. 2018.
 */
import java.io.IOException;
import java.util.*;

import javax.persistence.NoResultException;

import lam.eshop.entity.OrderDetail;
import lam.eshop.entity.Product;
import lam.eshop.entity.ProductImage;
import lam.eshop.form.ProductForm;
import lam.eshop.model.OrderDetailInfo;
import lam.eshop.model.ProductInfo;
import lam.eshop.pagination.PaginationResult;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Transactional
@Repository
public class ProductDAO {

    @Autowired
    private SessionFactory sessionFactory;


    public Product findProduct(String code) {
        try {
            String sql = "Select e from " + Product.class.getName() + " e Where e.code =:code ";

            Session session = this.sessionFactory.getCurrentSession();
            Query<Product> query = session.createQuery(sql, Product.class);
            query.setParameter("code", code);
            return (Product) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public ProductInfo findProductInfo(String code) {
        Product product = this.findProduct(code);
        if (product == null) {
            return null;
        }
        return new ProductInfo(product.getCode(), product.getName(), product.getPrice());
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void save(ProductForm productForm) {

        Session session = this.sessionFactory.getCurrentSession();
        String code = productForm.getCode();

        Product product = null;

        boolean isNew = false;
        if (code != null) {
            product = this.findProduct(code);
        }
        if (product == null) {
            isNew = true;
            product = new Product();
            product.setCreateDate(new Date());
        }
        product.setCode(code);
        product.setName(productForm.getName());
        product.setPrice(productForm.getPrice());
        product.setDescription(productForm.getDescription());
        //save profil image
        if(productForm.getProfilFile()!=null){
            byte[] image = null;
            try {
                    image=productForm.getProfilFile().getBytes();
                    if (image != null && image.length > 0) {
                        product.setImage(image);
                    }
            } catch (IOException e) {
            }
        }
        session.persist(product);

        // save galery
        if (productForm.getFileData() != null) {
            byte[] image = null;
            try {
                for (MultipartFile f : productForm.getFileData())
                {
                    image=f.getBytes();
                    if (image != null && image.length > 0) {
                        ProductImage productImage=new ProductImage();
                        productImage.setId(UUID.randomUUID().toString());
                        productImage.setImage(image);
                        productImage.setProduct(product);
                        session.persist(productImage);
                    }
                }
                    //image = productForm.getFileData().getBytes();
            } catch (IOException e) {
            }
        }
        if (isNew) {
            session.persist(product);
        }
        // Nếu có lỗi tại DB, ngoại lệ sẽ ném ra ngay lập tức
        session.flush();
    }

    public PaginationResult<ProductInfo> queryProducts(int page, int maxResult, int maxNavigationPage,
                                                       String likeName) {
        String sql = "Select new " + ProductInfo.class.getName() //
                + "(p.code, p.name, p.price) " + " from "//
                + Product.class.getName() + " p ";
        if (likeName != null && likeName.length() > 0) {
            sql += " Where lower(p.name) like :likeName ";
        }
        sql += " order by p.createDate desc ";
        //
        Session session = this.sessionFactory.getCurrentSession();
        Query<ProductInfo> query = session.createQuery(sql, ProductInfo.class);

        if (likeName != null && likeName.length() > 0) {
            query.setParameter("likeName", "%" + likeName.toLowerCase() + "%");
        }
        return new PaginationResult<ProductInfo>(query, page, maxResult, maxNavigationPage);
    }

    public PaginationResult<ProductInfo> queryProducts(int page, int maxResult, int maxNavigationPage) {
        return queryProducts(page, maxResult, maxNavigationPage, null);
    }

    public ArrayList<ProductImage> getGalleryByProduct(String productId){
        try {
            String sql = "Select e from " + ProductImage.class.getName() + " e Where e.product.code =:code ";
            org.hibernate.Session session = this.sessionFactory.getCurrentSession();
            Query<ProductImage> query = session.createQuery(sql, ProductImage.class);
            query.setParameter("code", productId);
//            return (Set<ProductImage>) query.getSingleResult();
            return (ArrayList<ProductImage>) query.getResultList();

        } catch (NoResultException e) {
            return null;
        }
    }

}