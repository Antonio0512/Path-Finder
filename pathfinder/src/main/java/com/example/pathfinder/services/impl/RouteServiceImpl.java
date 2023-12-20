package com.example.pathfinder.services.impl;

import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.pathfinder.models.Category;
import com.example.pathfinder.models.Route;
import com.example.pathfinder.models.User;
import com.example.pathfinder.models.dto.CreateRouteDTO;
import com.example.pathfinder.models.dto.RouteGetAllViewModel;
import com.example.pathfinder.repositories.AuthRepository;
import com.example.pathfinder.repositories.CategoryRepository;
import com.example.pathfinder.repositories.RouteRepository;
import com.example.pathfinder.services.RouteService;
import com.example.pathfinder.services.session.LoggedUser;

@Service
public class RouteServiceImpl implements RouteService {
    private final RouteRepository routeRepository;
    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;
    private final AuthRepository authRepository;

    public RouteServiceImpl(RouteRepository routeRepository, ModelMapper modelMapper, CategoryRepository categoryRepository, AuthRepository authRepository) {
        this.routeRepository = routeRepository;
        this.modelMapper = modelMapper;
        this.categoryRepository = categoryRepository;
        this.authRepository = authRepository;
    }

    @Override
    public void addRoute(CreateRouteDTO createRouteDTO, LoggedUser loggedUser) {
        Route route = modelMapper.map(createRouteDTO, Route.class);
        route.getCategories().clear();

        Set<Category> categories = categoryRepository.findByNameIn(createRouteDTO.getCategories());
        route.addCategories(categories);
        
        User user = authRepository.findByUsername(loggedUser.getUsername()).get();
        route.setAuthor(user);
        
        routeRepository.save(route);
    }

    @Override
    public List<RouteGetAllViewModel> getAll() {
        return routeRepository.findAll().stream()
              .map(route -> modelMapper.map(route, RouteGetAllViewModel.class))
              .toList();
    }
}
