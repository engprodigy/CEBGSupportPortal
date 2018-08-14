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
public class ProductCheckBox {
    
      boolean receiveNewsletter = true; //checked it
	//String [] favLanguages;
      String [] productListMapToRoleCheckBox;
      private int roleID;

    public String[] getproductListMapToRoleCheckBox() {
        return productListMapToRoleCheckBox;
    }

    public void setproductListMapToRoleCheckBox(String[] productListMapToRoleCheckBox) {
        this.productListMapToRoleCheckBox = productListMapToRoleCheckBox;
    }
    
    public int getroleID() {
        return roleID;
    }

    public void setroleID(int roleID) {
        this.roleID = roleID;
    }
    
}
