package com.fcmb.account.service;

import com.fcmb.account.model.User;
import com.fcmb.account.model.Role;
import com.fcmb.account.model.Product;
import java.util.List;

public interface UserService {
    void save(User user, Role role);

    User findByUsername(String username);
    
    List<Product> findByUserRoleProduct(String user);     //Find product accessible to a user by role assigned
    
     boolean UpdateUserRole(String rolename, String userName);     //Find product accessible to a user by role assigned
}
