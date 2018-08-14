/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcmb.account.repository;

import com.fcmb.account.model.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author BELLO
 */
public interface MenuRepository extends CrudRepository<Menu, Long>, JpaSpecificationExecutor<Menu> {

    
     /**
	 * Returns a page of {@link Customer}s with the given menuname.
	 * 
	 * @param menuname
	 * @param pageable
	 * @return
	 */
	Page<Menu> findByMenuname(String menuname, Pageable pageable);
     
}
