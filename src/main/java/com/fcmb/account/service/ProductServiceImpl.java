/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcmb.account.service;

import com.fcmb.account.model.Product;
import com.fcmb.account.model.Role;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import static org.hibernate.annotations.common.util.impl.LoggerFactory.logger;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author BELLO
 */

//@Service("productService")
@Repository
@Transactional(readOnly = true)

public class ProductServiceImpl implements ProductService {
    
    final Logger logger = LoggerFactory.getLogger(getClass());
    @PersistenceContext private EntityManager em;

	/*
	 * (non-Javadoc)
	 * @see example.springdata.jpa.showcase.before.ProductService#findById(java.lang.Long)
	 */
	@Override
	public Product findById(Long id) {
		return em.find(Product.class, id);
	}

	/*
	 * (non-Javadoc)
	 * @see example.springdata.jpa.showcase.before.ProductService#findAll()
	 */
	@Override
	public List<Product> findAll() {
		return em.createQuery("select p from Product p", Product.class).getResultList();
	}

	/*
	 * (non-Javadoc)
	 * @see example.springdata.jpa.showcase.before.ProductService#findAll(int, int)
	 */
	@Override
	public List<Product> findAll(int page, int pageSize) {

		TypedQuery<Product> query = em.createQuery("select c from Product c", Product.class);

		query.setFirstResult(page * pageSize);
		query.setMaxResults(pageSize);

		return query.getResultList();
	}

	/*
	 * (non-Javadoc)
	 * @see example.springdata.jpa.showcase.before.ProductService#save(example.springdata.jpa.showcase.core.Product)
	 */
	@Override
	@Transactional
	public Product save(Product product) {

		// Is new?
		if (product.getId() == null) {
			em.persist(product);
			return product;
		} else {
			return em.merge(product);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see example.springdata.jpa.showcase.before.ProductService#findByLastname(java.lang.String, int, int)
	 */
	@Override
	public List<Product> findByProductname(String productname, int page, int pageSize) {

		TypedQuery<Product> query = em.createQuery("select c from Product c where c.productname = ?1", Product.class);

		query.setParameter(1, productname);
		query.setFirstResult(page * pageSize);
		query.setMaxResults(pageSize);

		return query.getResultList();
	}

    @Override
    public List<Product> listProductMapToRole(int roleID) {
        
        
         String role_id = Long.toString(roleID);
      // TypedQuery<Product> query
       List<Product> product = em.createNativeQuery("select * from Product p join product_role R ON p.id = R.product_id where "
          + "R.role_id =:role_id", Product.class).setParameter("role_id", role_id).getResultList();
  
        return product;
        
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mapProductToRole(String[] productStringArray, String roleID) {
        
        javax.persistence.Query query2 = em.createNativeQuery("delete from product_role where "
         + "role_id=:role_id");
        query2.setParameter("role_id", roleID);
        query2.executeUpdate();
        
        for (String productsStringArray : productStringArray) {
            
             List<Product> product = em.createNativeQuery("select * from product where "
         // + "name =:name", Role.class).setParameter("name", "ROLE_ADMIN").getResultList(); 
             + "productname =:name", Product.class).setParameter("name", productsStringArray).getResultList();
            
             String product_id = Long.toString(product.get(0).getId());
             
         javax.persistence.Query query= em.createNativeQuery("insert into product_role (product_id, role_id)"
         + " values (:product_id,:role_id)");
        query.setParameter("product_id", product_id);
        query.setParameter("role_id", roleID);
        query.executeUpdate();
            
            
            logger.info("Inside MapProductToRole Function of ProductService :: " );
// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
    
}
