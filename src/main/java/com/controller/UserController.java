package com.controller;

import com.model.UserDTO;
import com.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class UserController {

  @Autowired
  private UserServiceImpl userService;

  @GetMapping
  public String showRegistrationForm(@ModelAttribute("user") UserDTO userDTO) {
    return "registration";
  }

  @PostMapping
  public String registerUserAccount(@ModelAttribute("user") UserDTO UserDTO) {
    userService.createUser(UserDTO);
    return "redirect:/registration?success";
  }

  @GetMapping("/login")
  public String login() {
    return "login";
  }

  @PostMapping("/login")
  public String login(@ModelAttribute LoginRequest request) {
    if (userService.loginAuthentication(request)) {
      return "redirect:/students";
    }
    return "Error";
  }
}
