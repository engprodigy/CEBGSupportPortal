/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcmb.account.service;

/**
 *
 * @author BELLO
 */

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.fcmb.account.model.Customer;

@Repository
@Transactional(readOnly = true)
public class CustomerServiceImpl implements CustomerService {

	@PersistenceContext private EntityManager em;

	/*
	 * (non-Javadoc)
	 * @see example.springdata.jpa.showcase.before.CustomerService#findById(java.lang.Long)
	 */
	@Override
	public Customer findById(Long id) {
		return em.find(Customer.class, id);
	}

	/*
	 * (non-Javadoc)
	 * @see example.springdata.jpa.showcase.before.CustomerService#findAll()
	 */
	@Override
	public List<Customer> findAll() {
		return em.createQuery("select c from Customer c", Customer.class).getResultList();
	}

	/*
	 * (non-Javadoc)
	 * @see example.springdata.jpa.showcase.before.CustomerService#findAll(int, int)
	 */
	@Override
	public List<Customer> findAll(int page, int pageSize) {

		TypedQuery<Customer> query = em.createQuery("select c from Customer c", Customer.class);

		query.setFirstResult(page * pageSize);
		query.setMaxResults(pageSize);

		return query.getResultList();
	}

	/*
	 * (non-Javadoc)
	 * @see example.springdata.jpa.showcase.before.CustomerService#save(example.springdata.jpa.showcase.core.Customer)
	 */
	@Override
	@Transactional
	public Customer save(Customer customer) {

		// Is new?
		if (customer.getId() == null) {
			em.persist(customer);
			return customer;
		} else {
			return em.merge(customer);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see example.springdata.jpa.showcase.before.CustomerService#findByLastname(java.lang.String, int, int)
	 */
	@Override
	public List<Customer> findByLastname(String lastname, int page, int pageSize) {

		TypedQuery<Customer> query = em.createQuery("select c from Customer c where c.lastname = ?1", Customer.class);

		query.setParameter(1, lastname);
		query.setFirstResult(page * pageSize);
		query.setMaxResults(pageSize);

		return query.getResultList();
	}
}
