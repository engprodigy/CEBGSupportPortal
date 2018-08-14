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

import java.io.Serializable;
//import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")

public class Product implements Serializable {
    
    
     private Long id;
     private String productname;
     //List<Product> productListMapToRole;
     

    
     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }
    
    
    
}
