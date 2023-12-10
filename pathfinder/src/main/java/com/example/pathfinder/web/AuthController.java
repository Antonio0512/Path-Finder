package com.example.pathfinder.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.pathfinder.models.dto.UserRegistrationDTO;

@Controller
public class AuthController {

    @GetMapping("/register")
    public String register(Model model) {
        UserRegistrationDTO dto = new UserRegistrationDTO();
        dto.setUsername("Pesho");
        model.addAttribute("userRegistrationDTO", dto);
        return "register";        
    }

    @PostMapping("/register")
    public String doRegister(UserRegistrationDTO userRegistrationDTO) {
        System.out.println(userRegistrationDTO.toString());

        return "redirect:/login";
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
