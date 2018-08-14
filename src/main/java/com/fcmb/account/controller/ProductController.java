/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcmb.account.controller;
import com.fcmb.account.model.Product;
import com.fcmb.account.model.ProductCheckBox;
import com.fcmb.account.model.Role;
import com.fcmb.account.model.User;
import com.fcmb.account.model.Product_role;
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

/**
 *
 * @author BELLO
 */
@Controller
public class ProductController {
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    private RoleService roleService;
    
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;
    
    final Logger logger = LoggerFactory.getLogger(getClass());
    
       @RequestMapping(value = "/mapProductToRole", method = RequestMethod.GET)
       public String listGameCategory(ModelMap model, HttpServletRequest request) {
        
       Product_role product_role = new Product_role();
       model.addAttribute("product_role", product_role);
       // request.
       //roleService.listAllRole().get(0).
       model.addAttribute("loggedinuser", getPrincipal());
       model.addAttribute("roles", roleService.listAllRole());
       //roles.
       //  System.out.println("Logged in user Roles :: " + Integer.toString(roles.size()) );
       // logger.info("Inside ProductController :: " + iterator.next());  
        
      // logger.info("User Role :: " +  securityService.findLoggedInUsername()); 

        return "productrolemapping2";
          
      }
       
       @RequestMapping(value = "/productMappingRole-{id}", method = RequestMethod.GET)
       public String productMappingRole(@PathVariable int id, ModelMap model, HttpServletRequest request) {
           
       logger.info("Inside Product Controller Role/productMappingRole role id ::" + id );
       
       List<String> productSelectedList = new ArrayList<>(); // to track product already mapped to role
       List<Product> productListMapToRole; // list all product mapped to selected role id
       List<Product> productListAll ;    //list all product
        
       String roleName = roleService.getRoleById(id).getName();
       productListAll = productService.findAll();
       productListMapToRole = productService.listProductMapToRole(id);
       
       
       ProductCheckBox productCheckBox = new ProductCheckBox();
       
       String [] productListStringValue = new String[productListAll.size()];
       String [] productListMapToRoleStringValue = new String[productListMapToRole.size()];
       
       int j = 0;
       if (!productListMapToRole.isEmpty()) {
       for(int i=0; i < productListAll.size(); i++ ){//iterate through all product list
            logger.info("Product List Map to Role Length ::" + productListMapToRole.size() );  
       if(Objects.equals(productListAll.get(i).getId(), productListMapToRole.get(j).getId())){
      // if(Long.toString(productListAll.get(i).getId()) == null ? Long.toString(productListMapToRole.get(j).getId()) == null : Long.toString(productListAll.get(i).getId()).equals(Long.toString(productListMapToRole.get(j).getId()))){
               
        logger.info("Product List All Iteration Value ::" + Long.toString(productListAll.get(i).getId()));
        logger.info("Product List Map to Role Iteration Value ::" + Long.toString(productListMapToRole.get(j).getId()));
           if(j < productListMapToRole.size()-1) {  //make sure the array is not exceeded while incrementing j
               
                j++;
             }     
              productSelectedList.add(i, "true");
               logger.info("Value of J ::" + j);
               
            }else{
                
               productSelectedList.add("false");
            }
           
          // productSelectedList.add("true");   
           
        }
       }else{
           
            productSelectedList.add("Non available");
       }
       logger.info("Product List All ::" + productListAll.size());
       logger.info("Product List Map to Role Length ::" + productListMapToRole.size() );
       logger.info("Product Selected List Map Already Mapped To Role Length ::" + productSelectedList.size() );
       
       
       for(int i = 0; i < productListAll.size(); i++){
            
          productListStringValue[i] = productListAll.get(i).getProductname();//List all products for checkbox value
      
         }
       
       for(int i = 0; i < productListMapToRole.size(); i++){
            
         productListMapToRoleStringValue[i] =  productListMapToRole.get(i).getProductname();//list product mapped to
         //role already as checked on checkbox list
           
       }
       
       for(int i = 0; i < productListMapToRole.size(); i++){
            
         logger.info("Product List Map to Role Product Name String Value ::" + productListMapToRoleStringValue[i] );
           
       }
       
       productCheckBox.setproductListMapToRoleCheckBox(productListMapToRoleStringValue);// assign assigned role to productCheck
       //box list as checked
       
       
     //  model.addAttribute("productListMapToRole", productListMapToRole); // checked check
      // model.addAttribute("productSelectedList", productSelectedList);
       model.addAttribute("productCheckBox", productCheckBox);  //form model of type product
       model.addAttribute("productListStringValue", productListStringValue);
       model.addAttribute("roleName", roleName);
      // model.addAttribute("roleID", String.valueOf(id));
       model.addAttribute("roleID", id );
       return "productrolemappingtwo2";   
       }
       
       
    
    @RequestMapping(value = "/productMappingRole-{id}", method = RequestMethod.POST)
    public String registration2(@ModelAttribute("productCheckBox") ProductCheckBox productCheckBox, BindingResult bindingResult, Model model) {
        
    String [] productListCheckedValue =  productCheckBox.getproductListMapToRoleCheckBox();
    productService.mapProductToRole(productListCheckedValue, String.valueOf(productCheckBox.getroleID()));//update product
    //role table in database with returned product array and role id
    logger.info("Role ID ::" + String.valueOf(productCheckBox.getroleID()) );
    String roleName = roleService.getRoleById(productCheckBox.getroleID()).getName();
    // List<Product> productListMapToRole; // list all product mapped to selected role id
    List<Product> productListAll ;    //list all product
    productListAll = productService.findAll();
    //productListMapToRole = productService.listProductMapToRole(id);
    String [] productListStringValue = new String[productListAll.size()];
        
    for(int i = 0; i < productListAll.size(); i++){
            
          productListStringValue[i] = productListAll.get(i).getProductname();//List all products for checkbox value
      
         }
    model.addAttribute("productCheckBox", productCheckBox);  //form model of type product
    model.addAttribute("productListStringValue", productListStringValue);
    model.addAttribute("roleName", roleName);    
    return "productrolemappingtwo2";
        
    }
    
       
       
      
       private String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
            
        } else {
            userName = principal.toString();
        }
        //((UserDetails) principal).getAuthorities().
        System.out.println("Logged in user :: " + userName);
              // System.out.println("Logged in user Roles :: " +
            // userService.findByUsername(userName).getRoles().toString());
         // Set<Role> roles = userService.findByUsername(userName).getRoles();
          
          //System.out.println("Logged in user Roles :: " + Integer.toString(roles.size())  );
        return userName;
    }
    
    
}
