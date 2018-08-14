/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcmb.account.service;

import com.fcmb.account.model.Customer;
import java.util.List;

/**
 *
 * @author BELLO
 */
/**
 * Service interface for {@link Customer}s.
 * 
 * @author Oliver Gierke
 */
public interface CustomerService {

	/**
	 * Returns the {@link Customer} with the given id or {@literal null} if no {@link Customer} with the given id was
	 * found.
	 * 
	 * @param id
	 * @return
	 */
	Customer findById(Long id);

	/**
	 * Saves the given {@link Customer}.
	 * 
	 * @param customer
	 * @return
	 */
	Customer save(Customer customer);

	/**
	 * Returns all customers.
	 * 
	 * @return
	 */
	List<Customer> findAll();

	/**
	 * Returns the page of {@link Customer}s with the given index of the given size.
	 * 
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List<Customer> findAll(int page, int pageSize);

	/**
	 * Returns the page of {@link Customer}s with the given lastname and the given page index and page size.
	 * 
	 * @param lastname
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List<Customer> findByLastname(String lastname, int page, int pageSize);
}
