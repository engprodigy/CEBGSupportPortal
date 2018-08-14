/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcmb.account.repository;

import com.fcmb.account.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author BELLO
 */
public interface ProductRepository extends CrudRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    
     /**
	 * Returns a page of {@link Customer}s with the given productname.
	 * 
	 * @param productname
	 * @param pageable
	 * @return
	 */
	Page<Product> findByProductname(String productname, Pageable pageable);
     
}
