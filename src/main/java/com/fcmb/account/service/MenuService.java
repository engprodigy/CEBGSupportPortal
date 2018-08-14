/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcmb.account.service;

import com.fcmb.account.model.Menu;
import java.util.List;
/**
 *
 * @author BELLO
 */
public interface MenuService {
    
    /**
	 * Returns the {@link Menu} with the given id or {@literal null} if no {@link Menu} with the given id was
	 * found.
	 * 
	 * @param id
	 * @return
	 */
	Menu findById(Long id);

	/**
	 * Saves the given {@link Menu}.
	 * 
	 * @param product
	 * @return
	 */
	Menu save(Menu product);

	/**
	 * Returns all products.
	 * 
	 * @return
	 */
	List<Menu> findAll();

	/**
	 * Returns the page of {@link Menu}s with the given index of the given size.
	 * 
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List<Menu> findAll(int page, int pageSize);

	/**
	 * Returns the page of {@link Menu}s with the given lastname and the given page index and page size.
	 * 
     * @param productID
	 * @return
	 */
    
        List<Menu> listMenuMapToProduct(int productID); //return a list of menu mapped to product
        
        List<Integer> listMenuProductGroupMapToRole(int roleID, int product_id); //list menu product collection mapped to role id
        
        Menu listMenuFromId (int product_menu_link_id, int product_id );// return menu list by join query from 
        //product_menu_mapping and menu table
        
        void updateRoleProductMenu(int roleID, String productName, String [] menu);
}
