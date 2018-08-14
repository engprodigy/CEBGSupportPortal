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
public class ProductRoleMenuCheckBox {
    
    
      String [] productList;
      String productName;
      String [] menuListCheckBox;
      String [] allMenuListCheckBox;
      private int roleID;

    public String[] getproductList() {
        return productList;
    }

    public void setproductList(String[] productList) {
        this.productList = productList;
    }
    public String getproductName() {
        return productName;
    }

    public void setproductName(String productName) {
        this.productName = productName;
    }
    
    public String[] getmenuListCheckBox() {
        return menuListCheckBox;
    }

    public void setmenuListCheckBox(String[] menuListCheckBox) {
        this.menuListCheckBox = menuListCheckBox;
    }
    
    public String[] getAllMenuListCheckBox() {
        return allMenuListCheckBox;
    }

    public void setAllMenuListCheckBox(String[] allMenuListCheckBox) {
        this.allMenuListCheckBox = allMenuListCheckBox;
    }
    
    public int getroleID() {
        return roleID;
    }

    public void setroleID(int roleID) {
        this.roleID = roleID;
    }
    
    
    
    
    
}
