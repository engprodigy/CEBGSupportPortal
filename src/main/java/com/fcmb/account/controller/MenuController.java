/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcmb.account.controller;
import com.fcmb.account.model.Menu;
import com.fcmb.account.model.Product;
import com.fcmb.account.model.ProductCheckBox;
import com.fcmb.account.model.ProductRoleMenuCheckBox;
import com.fcmb.account.model.Role;
import com.fcmb.account.model.User;
import com.fcmb.account.model.Product_role;
import com.fcmb.account.service.MenuService;
import com.fcmb.account.service.RoleService;
import com.fcmb.account.service.ProductService;
import com.fcmb.account.service.SecurityService;
import com.fcmb.account.service.UserService;
import com.fcmb.account.validator.UserValidator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author BELLO
 */
@Controller

/**
 *
 * @author BELLO
 */
public class MenuController {
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    private RoleService roleService;
    
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;
    
    @Autowired
    private MenuService menuService;

    @Autowired
    private UserValidator userValidator;
    
    final Logger logger = LoggerFactory.getLogger(getClass());
    
       @RequestMapping(value = "/mapMenuToRole-{id}", method = RequestMethod.GET)
       public String addNewRoleGet(@PathVariable int id, ModelMap model, HttpServletRequest request) {
        
       List<ProductRoleMenuCheckBox> ProductRoleMenuCheckBoxList = new ArrayList<>();
        
       List<ProductRoleMenuCheckBox> ProductRoleMenuCheckBoxListFormModel = new ArrayList<>();
           
       List<Product> productListMapToRole; // list all product mapped to selected role id
       
       ProductRoleMenuCheckBox productRoleMenuCheckBox = new ProductRoleMenuCheckBox();//the form model
       
       productListMapToRole = productService.listProductMapToRole(id);//list all product mapped to selected role id
       //productListMapToRole.get(0).getId();
       
       String [] productNameArray = new String[productListMapToRole.size()];
       for(int i = 0; i < productListMapToRole.size(); i++) {
       
       ProductRoleMenuCheckBox productRoleMenuCheckBoxForm = new ProductRoleMenuCheckBox();
       ProductRoleMenuCheckBox productListMapToRoleArray = new ProductRoleMenuCheckBox();
       
       int productid = (int)(long)productListMapToRole.get(i).getId();
       String productName =   productListMapToRole.get(i).getProductname();
       productListMapToRoleArray.setproductName(productName);
       productNameArray[i] = productName;
       List<Menu> menuListMapToProduct = menuService.listMenuMapToProduct(productid);//list all menu mapped to product
       //from prouduct_menu mapping
      
       
       String [] menuListStringArray = new String[menuListMapToProduct.size()];
       
       for(int j = 0; j < menuListMapToProduct.size(); j++ ){
      // menuListMapToproduct.forEach((_item) -> {
          menuListStringArray[j] =   menuListMapToProduct.get(j).getMenuname();
          logger.info("Menu List Map To Product ::" + menuListMapToProduct.get(j).getMenuname());
             }
    
       productListMapToRoleArray.setAllMenuListCheckBox(menuListStringArray);
       productRoleMenuCheckBoxForm.setAllMenuListCheckBox(menuListStringArray);
       productRoleMenuCheckBoxForm.setmenuListCheckBox(menuListStringArray);
       List<Integer> menuProductGroupListMapToRole = menuService.listMenuProductGroupMapToRole(id, productid);//list id of
       //product_menu_link_id mapped to role id from rolemenumapping table
       //logger.info("product_menu_link_id List ::" + Integer.toString(menuProductGroupListMapToRole.get(0)));
       logger.info("product_menu_link_id List Length ::" + Integer.toString(menuProductGroupListMapToRole.size()));
       
       
       
       String [] mappedMenuListStringArray = new String[menuProductGroupListMapToRole.size()]; //assign returned
       //mapped role menu list to a string array
       
       for(int k = 0; k < menuProductGroupListMapToRole.size(); k++ ){
       Menu menuList = menuService.listMenuFromId(menuProductGroupListMapToRole.get(k), productid);//list all 
         //menu name from product_menu_mapping table based on returned product_menu_link_id returned from 
         //menuService.listMenuProductGroupMapToRole function  above 
       mappedMenuListStringArray[k] =   menuList.getMenuname();
       logger.info("Menu List ::" + menuList.getMenuname());
             }
       productListMapToRoleArray.setmenuListCheckBox(mappedMenuListStringArray);
       
       ProductRoleMenuCheckBoxList.add(productListMapToRoleArray);
       ProductRoleMenuCheckBoxListFormModel.add(productRoleMenuCheckBoxForm);
       
       }
       //ProductRoleMenuCheckBox productRoleMenuCheckBox = new ProductRoleMenuCheckBox();
       // productListMapToRole.size()
       /*String [] productListStringArray = new String[productListMapToRole.size()];
       for(int i = 0; i < productListMapToRole.size(); i++ ){
           
           productListStringArray[i] =   productListMapToRole.get(i).getProductname();//productListMapToRole.get(0).getProductname();
           logger.info("Product Name ::" + productListMapToRole.get(i).getProductname());
          
           }*/
      // productRoleMenuCheckBox.setproductList(productListStringArray);
      // productRoleMenuCheckBox.setmenuListCheckBox(mappedMenuListStringArray);
       
       
        //ProductRoleMenuCheckBox productListMapToRoleArray = new ProductRoleMenuCheckBox();
        
       // productListMapToRoleArray.setproductList(productListStringArray);
        
        //productListMapToRoleArray.setproductName("Multichoice Support Platform");
        
        //ProductRoleMenuCheckBoxList.add(productListMapToRoleArray);
       // ProductRoleMenuCheckBoxList.add(productListMapToRoleArray);
       
        
       model.addAttribute("productRoleMenuForm", productRoleMenuCheckBox);
       // model.addAttribute("ProductRoleMenuCheckBoxListFormModel", ProductRoleMenuCheckBoxListFormModel);
       //productNameArray
       model.addAttribute("productListMapToRole", productListMapToRole);
       model.addAttribute("productNameArray", productNameArray);
       model.addAttribute("roleID", id);
      // model.addAttribute("roleID", Integer.toString(id));
       //model.addAttribute("mappedMenuListStringArray", mappedMenuListStringArray);
       // model.addAttribute("productListStringArray", productListStringArray);
       // model.addAttribute("menuListStringArray", menuListStringArray);
       model.addAttribute("ProductRoleMenuCheckBoxList", ProductRoleMenuCheckBoxList);
       //model.addAttribute("roles", roleService.listAllRole());
       return "productrolemenumapping2";
    
    
        }
       
   @RequestMapping(value = "/mapMenuToRole-{id}", method = RequestMethod.POST)
   public String addNewRoleGet(@ModelAttribute("productRoleMenuForm") ProductRoleMenuCheckBox productRoleMenuCheckBox2, BindingResult bindingResult, Model model) {
          
   String [] menuListCheckedValue = productRoleMenuCheckBox2.getAllMenuListCheckBox();
   String productName2 =  productRoleMenuCheckBox2.getproductName();
   int roleID =  productRoleMenuCheckBox2.getroleID();
   int id = roleID;
   logger.info("Product name returned from form ::" + productName2);
   logger.info("role id returned from form ::" + roleID);
   
   menuService.updateRoleProductMenu(roleID, productName2, menuListCheckedValue);
   
   List<ProductRoleMenuCheckBox> ProductRoleMenuCheckBoxList = new ArrayList<>();
        
       List<ProductRoleMenuCheckBox> ProductRoleMenuCheckBoxListFormModel = new ArrayList<>();
           
       List<Product> productListMapToRole; // list all product mapped to selected role id
       
       ProductRoleMenuCheckBox productRoleMenuCheckBox = new ProductRoleMenuCheckBox();//the form model
       
       productListMapToRole = productService.listProductMapToRole(id);//list all product mapped to selected role id
       //productListMapToRole.get(0).getId();
       
       String [] productNameArray = new String[productListMapToRole.size()];
       for(int i = 0; i < productListMapToRole.size(); i++) {
       
       ProductRoleMenuCheckBox productRoleMenuCheckBoxForm = new ProductRoleMenuCheckBox();
       ProductRoleMenuCheckBox productListMapToRoleArray = new ProductRoleMenuCheckBox();
       
       int productid = (int)(long)productListMapToRole.get(i).getId();
       String productName =   productListMapToRole.get(i).getProductname();
       productListMapToRoleArray.setproductName(productName);
       productNameArray[i] = productName;
       List<Menu> menuListMapToProduct = menuService.listMenuMapToProduct(productid);//list all menu mapped to product
       //from prouduct_menu mapping
      
       
       String [] menuListStringArray = new String[menuListMapToProduct.size()];
       
       for(int j = 0; j < menuListMapToProduct.size(); j++ ){
      // menuListMapToproduct.forEach((_item) -> {
          menuListStringArray[j] =   menuListMapToProduct.get(j).getMenuname();
          logger.info("Menu List Map To Product ::" + menuListMapToProduct.get(j).getMenuname());
             }
    
       productListMapToRoleArray.setAllMenuListCheckBox(menuListStringArray);
       productRoleMenuCheckBoxForm.setAllMenuListCheckBox(menuListStringArray);
       productRoleMenuCheckBoxForm.setmenuListCheckBox(menuListStringArray);
       List<Integer> menuProductGroupListMapToRole = menuService.listMenuProductGroupMapToRole(id, productid);//list id of
       //product_menu_link_id mapped to role id from rolemenumapping table
      
       logger.info("product_menu_link_id List Length ::" + Integer.toString(menuProductGroupListMapToRole.size()));
        
       String [] mappedMenuListStringArray = new String[menuProductGroupListMapToRole.size()]; //assign returned
       //mapped role menu list to a string array
       
       for(int k = 0; k < menuProductGroupListMapToRole.size(); k++ ){
       Menu menuList = menuService.listMenuFromId(menuProductGroupListMapToRole.get(k), productid);//list all 
        
       mappedMenuListStringArray[k] =   menuList.getMenuname();
       logger.info("Menu List ::" + menuList.getMenuname());
             }
       productListMapToRoleArray.setmenuListCheckBox(mappedMenuListStringArray);
       
       ProductRoleMenuCheckBoxList.add(productListMapToRoleArray);
       ProductRoleMenuCheckBoxListFormModel.add(productRoleMenuCheckBoxForm);
       
       }
       
       model.addAttribute("productRoleMenuForm", productRoleMenuCheckBox);
       model.addAttribute("productListMapToRole", productListMapToRole);
       model.addAttribute("productNameArray", productNameArray);
       model.addAttribute("roleID", id);
       model.addAttribute("ProductRoleMenuCheckBoxList", ProductRoleMenuCheckBoxList);
   
       return "productrolemenumapping2";
       }
    
}
