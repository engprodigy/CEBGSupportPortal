/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcmb.account.service;

import com.fcmb.account.model.Product;
import java.util.List;


/**
 *
 * @author BELLO
 */
public interface ProductService {
    
   /**
	 * Returns the {@link Product} with the given id or {@literal null} if no {@link Product} with the given id was
	 * found.
	 * 
	 * @param id
	 * @return
	 */
	Product findById(Long id);

	/**
	 * Saves the given {@link Product}.
	 * 
	 * @param product
	 * @return
	 */
	Product save(Product product);

	/**
	 * Returns all products.
	 * 
	 * @return
	 */
	List<Product> findAll();

	/**
	 * Returns the page of {@link Product}s with the given index of the given size.
	 * 
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List<Product> findAll(int page, int pageSize);

	/**
	 * Returns the page of {@link Product}s with the given lastname and the given page index and page size.
	 * 
	 * @param productname
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List<Product> findByProductname(String productname, int page, int pageSize);
        
        List<Product> listProductMapToRole(int roleID);
        
        void mapProductToRole(String[] rolesStringArray, String roleID);
}
