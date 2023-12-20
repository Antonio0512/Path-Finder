package com.example.pathfinder.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.pathfinder.models.dto.CreateRouteDTO;
import com.example.pathfinder.models.dto.RouteGetAllViewModel;
import com.example.pathfinder.models.enums.Level;
import com.example.pathfinder.models.enums.RouteCategory;
import com.example.pathfinder.services.RouteService;
import com.example.pathfinder.services.session.LoggedUser;

@Controller
@RequestMapping("/routes")
public class RouteController {
    private final RouteService routeService;
    private final LoggedUser loggedUser;

    public RouteController(RouteService routeService, LoggedUser loggedUser) {
        this.routeService = routeService;
        this.loggedUser = loggedUser;
    }


    @GetMapping
    public ModelAndView getRoutes() {
        List<RouteGetAllViewModel> routes = routeService.getAll();

        ModelAndView modelAndView = new ModelAndView("routes");
        modelAndView.addObject("routes", routes);

        return modelAndView;
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
        routeService.addRoute(createRouteDTO, loggedUser);

        return new ModelAndView("redirect:/");
    }

}
