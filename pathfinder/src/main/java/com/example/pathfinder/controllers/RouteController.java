package com.example.pathfinder.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.pathfinder.models.dto.CreateRouteDTO;
import com.example.pathfinder.models.enums.Level;
import com.example.pathfinder.models.enums.RouteCategory;
import com.example.pathfinder.services.RouteService;

@Controller
@RequestMapping("/routes")
public class RouteController {
    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping("/add")
    public ModelAndView addRoute() {
        ModelAndView modelAndView = new ModelAndView("add-route");

        modelAndView.addObject("levels", Level.values());
        modelAndView.addObject("categories", RouteCategory.values());

        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView addRoute(CreateRouteDTO createRouteDTO) {        
        routeService.addRoute(createRouteDTO);

        return new ModelAndView("redirect:/");
    }

}
