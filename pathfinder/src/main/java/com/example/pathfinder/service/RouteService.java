package com.example.pathfinder.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.pathfinder.models.Route;
import com.example.pathfinder.repositories.RouteRepository;

@Service
public class RouteService {
    
    private RouteRepository routeRepository;

    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    public List<Route> getMostCommented() {
        return routeRepository.findMostCommented();
    }
}
