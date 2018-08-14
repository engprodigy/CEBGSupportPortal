/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcmb.account.controller;

/**
 *
 * @author BELLO
 */
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
public class RoleController {
    
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
    
       @RequestMapping(value = "/addNewRole", method = RequestMethod.GET)
       public String addNewRoleGet(ModelMap model, HttpServletRequest request) {
        
       model.addAttribute("roleForm", new Role());
       //model.addAttribute("roles", roleService.listAllRole());
       return "addNewRolenew";
    
    
        }
       
       @RequestMapping(value = "/addNewRole", method = RequestMethod.POST)
       public String addNewRolePOST(@ModelAttribute("roleForm") Role roleForm, ModelMap model, HttpServletRequest request) {
        
       // roleForm.getName();
       roleService.save(roleForm);
       
     
       model.addAttribute("loggedinuser", getPrincipal());
       model.addAttribute("roles", roleService.listAllRole());
     

       return "productrolemapping2";
    
    
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


