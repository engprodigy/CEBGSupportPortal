/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcmb.account.model;

/**
 *
 * @author BELLO
 */
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Oliver Gierke
 */
@Entity
public class Customer {

	@Id @GeneratedValue(strategy = GenerationType.AUTO) private Long id;

	private String firstname;
	private String lastname;

	public Long getId() {
		return id;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}
}
