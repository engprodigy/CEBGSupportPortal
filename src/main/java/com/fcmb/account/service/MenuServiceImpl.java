/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcmb.account.service;

import com.fcmb.account.model.Menu;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author BELLO
 */

@Repository
@Transactional(readOnly = true)
public class MenuServiceImpl implements MenuService {
    
    final Logger logger = LoggerFactory.getLogger(getClass());
    @PersistenceContext private EntityManager em;

    @Override
    public Menu findById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Menu save(Menu product) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Menu> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Menu> findAll(int page, int pageSize) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Menu> listMenuMapToProduct(int productID) {
        
        //String role_id = Long.toString(roleID);
      // TypedQuery<Product> query
       List<Object[]> menuList = em.createNativeQuery("select * from product_menu_mapping  where "
          + "product_id =:product_id").setParameter("product_id", productID).getResultList();
       
       List<Integer> menuList2 = new ArrayList<>();
       List<Menu> menuListAll = new ArrayList<>() ;
         
         menuList.forEach((m) -> {
         menuList2.add((Integer) m[1]);
          List<Menu> menuList3 = em.createNativeQuery("select * from menu where "
          + "id =:menu_id", Menu.class).setParameter("menu_id", Integer.toString((Integer) m[1])).getResultList();
           //  Menu menuList3 = new Menu();
            // menuList3 = menuList2.get(0);
          logger.info("Menu List ::" + menuList3.get(0).getMenuname());
             menuListAll.add(menuList3.get(0));
          
        });
         
         return  menuListAll;
  
       // return menuList;
        
        
    }

    @Override
    public List<Integer> listMenuProductGroupMapToRole(int roleID, int product_id) {
        
        
         List<Object[]> menuList = em.createNativeQuery("select * from rolemenumapping  where "
          + "role_id =:role_id and product_id =:product_id")
         .setParameter("role_id", roleID)
         .setParameter("product_id", product_id)

         .getResultList();
         
         List<Integer> menuList2 = new ArrayList<>();
         
         menuList.forEach((m) -> {
            
             menuList2.add((Integer) m[3]);
        });
         
         return  menuList2;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Menu listMenuFromId(int product_menu_link_id, int product_id) {
       
     logger.info("product_menu_link_id ::" + Integer.toString(product_menu_link_id));
     List<Object[]> menuList = em.createNativeQuery("select * from product_menu_mapping  where "
     + "product_menu_link_id =:product_menu_link_id and product_id =:product_id")
      .setParameter("product_menu_link_id", product_menu_link_id)
      .setParameter("product_id", product_id).getResultList();

     
     List<Integer> menuListIntList = new ArrayList<>();
     List<Menu> menuListAll = new ArrayList<>() ;
     // menuName = new Menu();
     Menu menuName ;

      
        
     
       // if(!menuList.isEmpty()){
         menuList.forEach((m) -> {
             
            // menuListIntList.add((Integer) m[1]);
          logger.info("Menu id ::" + Integer.toString((Integer) m[1]));
          List<Menu> menuList2 = em.createNativeQuery("select * from menu where "
          + "id =:menu_id", Menu.class).setParameter("menu_id", Integer.toString((Integer) m[1])).getResultList();
           //  Menu menuList3 = new Menu();
            // menuList3 = menuList2.get(0);
          logger.info("Menu Name ::" + menuList2.get(0).getMenuname());
            menuListAll.add(menuList2.get(0));
         });
          
         menuName = menuListAll.get(0);
         return menuName;
      /*  }else{
           
           menuName.setMenuname(" ");
           return menuName;
        
        }*/
    }

    @Override
    public void updateRoleProductMenu(int roleID, String productName, String[] menu) {
     
     List<Object[]> productList = em.createNativeQuery("select * from product  where "
     + "productname =:productname")
      .setParameter("productname", productName).getResultList();
     
      ArrayList<Integer> productIdArray  = new ArrayList <>();
      productList.forEach((m) -> {
          
      logger.info("product id ::" + Integer.toString((Integer) m[0])); 
      productIdArray.add((Integer) m[0]);
       
      });
     // else{
            
        javax.persistence.Query query2 = em.createNativeQuery("delete from rolemenumapping where "
         + "role_id=:role_id and product_id=:product_id");
        query2.setParameter("role_id", roleID);
        query2.setParameter("product_id", productIdArray.get(0));
        query2.executeUpdate(); 
      //  }
      logger.info("product id ::" + productIdArray.get(0));
      
        ArrayList<Integer> menuIdArray  = new ArrayList <>();
        if(menu.length != 0){
        for (String menu1 : menu) {
        List<Object[]> menuList = em.createNativeQuery("select * from menu  where "
               + "menuname =:menuname")
             .setParameter("menuname", menu1).getResultList();
         menuList.forEach((m1) -> {
          
         logger.info("menu id ::" + Integer.toString((Integer) m1[0])); 
         menuIdArray.add((Integer) m1[0]);
       
                });
         }
     
        logger.info("menu id ::" + menuIdArray.get(0));
        
        ArrayList<Integer> productMenuLinkIdArray  = new ArrayList <>();
        
        if(!menuIdArray.isEmpty() ) {
            
         for (Integer menuIdArray1 : menuIdArray) {
          
            List<Object[]> menuList = em.createNativeQuery("select * from product_menu_mapping where "
               + "menu_id =:menu_id and product_id =:product_id")
             .setParameter("menu_id", menuIdArray1)
             .setParameter("product_id", productIdArray.get(0)).getResultList();
         menuList.forEach((m2) -> {
          
         logger.info("product_menu_link_id ::" + Integer.toString((Integer) m2[0])); 
         productMenuLinkIdArray.add((Integer) m2[0]);
       
                }); 
             
             }
         //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
        logger.info("product_menu_link_id ::" + productMenuLinkIdArray.get(0));
        
        if(!productMenuLinkIdArray.isEmpty() ) {
            
        /*javax.persistence.Query query3 = em.createNativeQuery("delete from rolemenumapping where "
         + "role_id=:role_id and product_id=:product_id");
        query3.setParameter("role_id", roleID);
        query3.setParameter("product_id", productIdArray.get(0));
        query3.executeUpdate(); */
            
        for(Integer productMenuLinkIdArray1 : productMenuLinkIdArray) {
    
        javax.persistence.Query query= em.createNativeQuery("insert into rolemenumapping (role_id, product_id, product_menu_link_id)"
         + " values (:role_id,:product_id,:product_menu_link_id)");
        query.setParameter("role_id", roleID);    
        query.setParameter("product_id", productIdArray.get(0));
        query.setParameter("product_menu_link_id", productMenuLinkIdArray1);
        query.executeUpdate();  
         //    }
             
             }
            
         }
       }
    }
    
}
