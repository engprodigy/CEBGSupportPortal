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
import com.fcmb.account.model.Product;
import com.fcmb.account.model.User;
import com.fcmb.account.model.Role;
import com.fcmb.account.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import javax.persistence.TypedQuery;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Service

@Repository
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {
    
    @PersistenceContext private EntityManager em;
    
    @Autowired
    private RoleRepository roleRepository;
    
     @Override
    public void save(Role role) {
        
        roleRepository.save(role);
        
     
    }

    @Override
    public List<Product> findProductByRole(String roleName) {   //access from product_role table
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   
    }

    @Override
    public List<Role> listAllRole() {
        
        //HashSet<Role> roleset = new HashSet<>();
      
        List<Role> roleList =  roleRepository.findAll();
        
        // roleList.
        
        return roleList;
        
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   
    }

    @Override
    public Role getRoleById(int id) {
          
         Role roleValue = roleRepository.getOne(Long.valueOf(id));
         List<Role> role2 = em.createNativeQuery("select * from role where "
         // + "name =:name", Role.class).setParameter("name", "ROLE_ADMIN").getResultList(); 
             + "id =:id", Role.class).setParameter("id", id).getResultList();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
          return roleValue;
    }
    
}
