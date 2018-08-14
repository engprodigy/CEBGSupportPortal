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
import com.fcmb.account.model.User;
import com.fcmb.account.model.Role;
import com.fcmb.account.model.Product;
import java.util.List;
public interface RoleService {
    
    void save(Role role);
    
    List<Role> listAllRole();
    
    List<Product> findProductByRole(String role);     //Find products assigned to roles 
    
    Role getRoleById (int id);
    
}
