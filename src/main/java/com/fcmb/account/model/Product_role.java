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
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "product_role")
public class Product_role {
    
    @Id
    private int product_id;
    private int role_id;
    
    
    public int getproduct_id() {
        return product_id;
    }

    public void setproduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getrole_id() {
        return role_id;
    }

    public void setrole_id(int role_id) {
        this.role_id = role_id;
    }

   
    
}
