package com.example.pathfinder.services;

import com.example.pathfinder.models.dto.UserLoginDTO;
import com.example.pathfinder.models.dto.UserProfileViewModel;
import com.example.pathfinder.models.dto.UserRegistrationDTO;
import com.example.pathfinder.services.session.LoggedUser;

public interface AuthService {
    void register(UserRegistrationDTO userRegistrationDTO);
    boolean login(UserLoginDTO userLoginDTO, LoggedUser loggedUser);
    void logout(LoggedUser loggedUser);
    UserProfileViewModel getUserProfile(LoggedUser loggedUser);
}