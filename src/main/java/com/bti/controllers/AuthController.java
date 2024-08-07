//package com.bti.controllers;
//
//import com.bti.model.User;
//import com.bti.services.UserService;
//import org.springframework.login.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//
//@Controller
//public class AuthController {
//
//    @Autowired
//    private UserService userService;
//
////    @Autowired
////    private PasswordEncoder passwordEncoder;
//
//    @GetMapping("/login")
//    public String login() {
//        return "/sec/login.xthml";
//    }
//
//    @GetMapping("/register")
//    public String register(Model model) {
//        model.addAttribute("user", new User());
//        return "/sec/register.xthml";
//    }
//
//    @PostMapping("/register")
//    public String registerUser(@ModelAttribute User user, Model model) {
//        if (userService.getByUsername(user.getUsername()).isPresent()) {
//            model.addAttribute("error", "Username already exists");
//            return "/sec/register.xhtml";
//        }
////        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        userService.saveUser(user);
//        return "redirect:/login";
//    }
//}
