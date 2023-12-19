package com.example.pathfinder.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.example.pathfinder.models.dto.UserLoginDTO;
import com.example.pathfinder.models.dto.UserRegistrationDTO;
import com.example.pathfinder.services.AuthService;
import com.example.pathfinder.services.session.LoggedUser;


@Controller
@RequestMapping("/users")
public class AuthController {
    private final AuthService authService;
    private final LoggedUser loggedUser;

    public AuthController(AuthService authService, LoggedUser loggedUser) {
        this.authService = authService;
        this.loggedUser = loggedUser;
    }

    @GetMapping("/register")
    public ModelAndView register() {
        return new ModelAndView("register");        
    }

    @PostMapping("/register")
    public ModelAndView register(UserRegistrationDTO userRegistrationDTO) {
        authService.register(userRegistrationDTO);
        return new ModelAndView("redirect:login");                      
    }


    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @PostMapping("/login")
    public ModelAndView login(UserLoginDTO userLoginDTO) {
        boolean isLogged = authService.login(userLoginDTO, loggedUser);
        if (isLogged) {
            return new ModelAndView("redirect:/");
        }

        return new ModelAndView("redirect:login");
    }

    @PostMapping("/logout")
    public ModelAndView logout() {
        authService.logout(loggedUser);

        return new ModelAndView("redirect:/");
    }
}

