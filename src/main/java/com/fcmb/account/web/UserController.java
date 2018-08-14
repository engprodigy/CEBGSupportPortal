package com.fcmb.account.web;

import com.fcmb.account.model.User;
import com.fcmb.account.model.Role;
import com.fcmb.account.model.Product;
import com.fcmb.account.service.ProductService;
import com.fcmb.account.service.SecurityService;
import com.fcmb.account.service.UserService;
import com.fcmb.account.service.CustomerService;
import com.fcmb.account.service.RoleService;
import com.fcmb.account.validator.UserValidator;
import com.fcmb.account.service.UserDetailsServiceImpl;
import com.fcmb.account.service.ProductService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("Role")



public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private RoleService roleService;
    
   // @Autowired
   // private UserDetailsServiceImpl userDetailsServiceImpl;
    
    final Logger logger = LoggerFactory.getLogger(getClass());
    private UserDetailsServiceImpl userDetailsServiceImpl;
    
   // @Autowired
   // private Role role;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }
        Role role = new Role();
        long i = 1;
        role.setId(i);
        role.setName("Merchant");
          
        userService.save(userForm, role);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        //userDetailsServiceImpl.loadUserByUsername(userForm.getUsername());
        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/welcome";
    }
    
    
    @RequestMapping(value = "/registration2", method = RequestMethod.GET)
   // public String registration2(Model model) {
       public String registrationget(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
       List<String> roleList = new ArrayList<>();
       
          List<Role> role = roleService.listAllRole();
        // role.size()
        role.forEach((role1) -> {
            roleList.add(role1.getName());
        });
       
      /* Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         Set<String> roles = authentication.getAuthorities().stream().map(r -> r.getAuthority()).collect(Collectors.toSet());
         Iterator iterator = roles.iterator();
       
         String roleTracker ;   //track all roles available to display in role drop down in user registration form
         while(iterator.hasNext()) {
           roleTracker = (String) iterator.next();
           if (roleTracker != null){
              roleList.add(roleTracker);
           }
          System.out.print("Role List Length" + roleList.size() );
            }*/
       
       model.addAttribute("loggedinuser", getPrincipal());
       model.addAttribute("roleList", roleList);

        return "registrationnew";
    }
    
    
    
    @RequestMapping(value = "/registration2", method = RequestMethod.POST)
    public String registration2(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registrationnew";
        }
        
       logger.info("Inside Registration :: " + userForm.getUserSelected());// displays role selected while creating user
        
        Role role = new Role();
        long i = 2;
        role.setId(i);
        role.setName("ROLE_ADMIN");
          
        userService.save(userForm, role);
        boolean roleUpdateStatus = false;
        
        //securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());
        
        if (userService.findByUsername(userForm.getUsername()).getUsername() != null ){
        
        roleUpdateStatus = userService.UpdateUserRole(userForm.getUserSelected(), userForm.getUsername());// link user
        //role to user name in user_role table
        
        }
        
        logger.info("Role Update Status :: " + roleUpdateStatus);

        return "redirect:/welcome";
        
       // return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }
    
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        System.out.println("Inside UserController :: ");
        logger.info("Inside UserController :: ");  
        //logger.info("User Role :: " +  userService.findByUsername(securityService.findLoggedInUsername()).getRoles().toString());
      // logger.info("User Role :: " +  securityService.findLoggedInUsername());       
       //model.addAttribute("customerList", productService.findAll());
       
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       Set<String> roles = authentication.getAuthorities().stream().map(r -> r.getAuthority()).collect(Collectors.toSet());
       Iterator iterator = roles.iterator();
       model.addAttribute("loggedinuser", getPrincipal());
       //roles.
       System.out.println("Logged in user Roles :: " + Integer.toString(roles.size()) );
       List<Product> myproduct;
       String rolevalue;
       if(roles.size() > 1){
       
       logger.info("Inside ProductController :: " + iterator.next()); // iterate to first default row
       rolevalue = iterator.next().toString(); // assing role string value
       myproduct = userService.findByUserRoleProduct(rolevalue);
       logger.info("Showing Product Map to Role :: " + myproduct.get(0).getProductname());
       model.addAttribute("productList", myproduct);
       model.addAttribute("Role", rolevalue);
       //model.addAttribute("userRole", rolevalue);
       }else if(roles.size() == 1) {
       //logger.info("Inside ProductController :: " + iterator.next()); 
       
       // Role role = new Role();
      //  long i = 2;
      //  role.setId(i);
      //  
          
        //userService.save(userForm, role);
        rolevalue = iterator.next().toString(); 
        myproduct = userService.findByUserRoleProduct(rolevalue);
        logger.info("Showing Product Map to Role :: " + myproduct.get(0).getProductname());
        model.addAttribute("productList", myproduct);
        model.addAttribute("Role", rolevalue);
       // model.addAttribute("userRole", rolevalue);
               }else{
           
           model.addAttribute("productList", "No Product Mapped To Role Yet");
       }
        //myproduct.get(0).getProductname()
        
       
        // model.addAttribute("productList", productService.findAll());
        
       
       if(productService.findAll().size() > 0){
             
            // return "welcome";
              return "widgets";
         }else{
         //role.getUsers();
        //return "welcome";
        return "widgets";
         }
    }
    
    @RequestMapping(value = { "/templateview"}, method = RequestMethod.GET)
    public String templateview(Model model) {
       System.out.println("Inside UserController :: ");
       logger.info("Inside UserController :: ");  
       
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       Set<String> roles = authentication.getAuthorities().stream().map(r -> r.getAuthority()).collect(Collectors.toSet());
       Iterator iterator = roles.iterator();
       model.addAttribute("loggedinuser", getPrincipal());
       //roles.
       System.out.println("Logged in user Roles :: " + Integer.toString(roles.size()) );
       List<Product> myproduct;
       String rolevalue;
       if(roles.size() > 1){
       
       logger.info("Inside ProductController :: " + iterator.next()); // iterate to first default row
       rolevalue = iterator.next().toString(); // assing role string value
       myproduct = userService.findByUserRoleProduct(rolevalue);
       logger.info("Showing Product Map to Role :: " + myproduct.get(0).getProductname());
       model.addAttribute("productList", myproduct);
       model.addAttribute("Role", rolevalue);
       }else if(roles.size() == 1) {
       
        rolevalue = iterator.next().toString(); 
        myproduct = userService.findByUserRoleProduct(rolevalue);
        logger.info("Showing Product Map to Role :: " + myproduct.get(0).getProductname());
        model.addAttribute("productList", myproduct);
        model.addAttribute("Role", rolevalue);
               }else{
           
           model.addAttribute("productList", "No Product Mapped To Role Yet");
       }
       return "widgets";
    }
   /*  @RequestMapping(value = {"/newlogin"}, method = RequestMethod.GET)
    public String loginNewPage(Model model) {
        return "login_new";
    }*/
    
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
