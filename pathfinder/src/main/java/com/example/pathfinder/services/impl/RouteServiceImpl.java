package com.example.pathfinder.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.pathfinder.models.Route;
import com.example.pathfinder.models.dto.CreateRouteDTO;
import com.example.pathfinder.repositories.RouteRepository;
import com.example.pathfinder.services.RouteService;

@Service
public class RouteServiceImpl implements RouteService {
    private final RouteRepository routeRepository;
    private final ModelMapper modelMapper;

    public RouteServiceImpl(RouteRepository routeRepository, ModelMapper modelMapper) {
        this.routeRepository = routeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addRoute(CreateRouteDTO createRouteDTO) {
        Route route = modelMapper.map(createRouteDTO, Route.class);

        routeRepository.save(route);
    }
}
