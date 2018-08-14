package com.fcmb.account.service;

import com.fcmb.account.model.Product;
import com.fcmb.account.model.User;
import com.fcmb.account.model.Role;
import com.fcmb.account.model.Product_role;
import com.fcmb.account.repository.RoleRepository;
import com.fcmb.account.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import javax.persistence.TypedQuery;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Service

@Repository
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    
     @PersistenceContext private EntityManager em;
    
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private Object entityManager;

    @Override
    public void save(User user, Role role) {
         user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
         /*HashSet<Role> roleset = new HashSet<>();
         Role role1 = new Role();
         long i = 1;
         role1.setId(i);
         role1.setName("ROLE_USER");
         roleset.add(role1);
         roleset.add(role);*/
         //user.setRoles(roleset);
         //user.setRoles(new HashSet<>(roleRepository.findAll()));
         userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    @Override
   //  @Query(value = "SELECT * FROM USERS WHERE EMAIL_ADDRESS = ?1", nativeQuery = true)
    public List<Product> findByUserRoleProduct(String User)
    {
        
     List<Role> role2 = em.createNativeQuery("select * from role where "
         // + "name =:name", Role.class).setParameter("name", "ROLE_ADMIN").getResultList(); 
             + "name =:name", Role.class).setParameter("name", User).getResultList();
     
     //role2.get(0).getId()
        
        
    String role_id = Long.toString(role2.get(0).getId());
  // TypedQuery<Product> query
  List<Product> product = em.createNativeQuery("select * from Product p join product_role R ON p.id = R.product_id where "
          + "R.role_id =:role_id", Product.class).setParameter("role_id", role_id).getResultList();
  
        return product;
    }
    
    
    @Override
    public boolean UpdateUserRole(String rolename, String Username){
        
        
        
        List<Role> role2 = em.createNativeQuery("select * from role where "
             + "name =:name", Role.class).setParameter("name", rolename).getResultList();//get role id
        
        List<User> user2 = em.createNativeQuery("select * from users where "
             + "username =:name", User.class).setParameter("name", Username).getResultList();//get user id
        
       // List<Object[]> persons = session.createSQLQuery("SELECT id, name FROM person" ).list();

            //int result 
            if("ROLE_ADMIN".equals(rolename)){
                
               //for admin user assign role-user privilege to access login page and landing page
                
             javax.persistence.Query query= em.createNativeQuery("insert into user_role (user_id, role_id)"
         + " values (:user_id,:role_id)");
        query.setParameter("user_id", user2.get(0).getId());
        query.setParameter("role_id", 1);
        query.executeUpdate();//update user id and role id to user_role table in database
        
        //assign admin role for admin user in user_role table
        javax.persistence.Query query2 = em.createNativeQuery("insert into user_role (user_id, role_id)"
         + " values (:user_id,:role_id)");
        query2.setParameter("user_id", user2.get(0).getId());
        query2.setParameter("role_id", role2.get(0).getId());
        query2.executeUpdate();
        
            
        }else{
            
          javax.persistence.Query query= em.createNativeQuery("insert into user_role (user_id, role_id)"
         + " values (:user_id,:role_id)");
        query.setParameter("user_id", user2.get(0).getId());
        query.setParameter("role_id", role2.get(0).getId());
        query.executeUpdate();//update user id and role id to user_role tabele in database      
        
        javax.persistence.Query query2= em.createNativeQuery("insert into user_role (user_id, role_id)"
         + " values (:user_id,:role_id)");
        query2.setParameter("user_id", user2.get(0).getId());
        query2.setParameter("role_id", 1);
        query2.executeUpdate();//update user id and role id to user_role table in database
                
        }
       
          
        
        
        return true;
    }
}
