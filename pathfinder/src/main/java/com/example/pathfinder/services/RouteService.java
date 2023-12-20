package com.example.pathfinder.services;

import com.example.pathfinder.models.dto.CreateRouteDTO;
import com.example.pathfinder.services.session.LoggedUser;

public interface RouteService {
    void addRoute(CreateRouteDTO createRouteDTO, LoggedUser loggedUser);
}
