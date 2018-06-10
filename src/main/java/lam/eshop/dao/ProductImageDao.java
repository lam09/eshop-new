package lam.eshop.dao;

import lam.eshop.entity.ProductImage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;

/**
 * Created by a.lam.tuan on 8. 6. 2018.
 */
@Transactional
@Repository
public class ProductImageDao {

    @Autowired
    private SessionFactory sessionFactory;

    public ProductImage findProductImage(String id) {
        try {
            String sql = "Select e from " + ProductImage.class.getName() + " e Where e.id =:id ";

            Session session = this.sessionFactory.getCurrentSession();
            Query<ProductImage> query = session.createQuery(sql, ProductImage.class);
            query.setParameter("id", id);
            return (ProductImage) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public void deleteProductImage(String id){
        System.out.println("delete image id " + id);
    }
}
