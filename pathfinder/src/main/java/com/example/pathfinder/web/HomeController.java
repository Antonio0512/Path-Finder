package com.example.pathfinder.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.pathfinder.models.Route;
import com.example.pathfinder.service.RouteService;

@Controller
public class HomeController {
    private final RouteService routeService;

    public HomeController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Route> routes = routeService.getMostCommented(); 

        model.addAttribute("mostCommented", routes.get(0));

        return "index";
    }
}