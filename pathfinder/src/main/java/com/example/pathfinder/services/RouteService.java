package com.example.pathfinder.services;

import java.util.List;

import com.example.pathfinder.models.dto.CreateRouteDTO;
import com.example.pathfinder.models.dto.RouteGetAllViewModel;
import com.example.pathfinder.services.session.LoggedUser;

public interface RouteService {
    void addRoute(CreateRouteDTO createRouteDTO, LoggedUser loggedUser);
    List<RouteGetAllViewModel> getAll();
}
