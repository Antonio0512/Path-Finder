package com.example.pathfinder.services.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.pathfinder.models.User;
import com.example.pathfinder.models.dto.UserLoginDTO;
import com.example.pathfinder.models.dto.UserRegistrationDTO;
import com.example.pathfinder.repositories.AuthRepository;
import com.example.pathfinder.services.AuthService;
import com.example.pathfinder.services.session.LoggedUser;

@Service
public class AuthServiceImpl implements AuthService {
    private final AuthRepository authRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(AuthRepository authRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.authRepository = authRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void register(UserRegistrationDTO registrationDTO) {
        User user = modelMapper.map(registrationDTO, User.class);
        user.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
        authRepository.save(user);
    }

    @Override
    public boolean login(UserLoginDTO userLoginDTO, LoggedUser loggedUser) {
        String username = userLoginDTO.getUsername();
        Optional<User> byUsername = authRepository.findByUsername(username);

        if (byUsername == null) {
            throw new IllegalArgumentException("User with username: " + username + "is not present");
        }

        User user = byUsername.get();

        boolean passwordsMatch = passwordEncoder.matches(userLoginDTO.getPassword(), user.getPassword());

        if (!passwordsMatch) {
            throw new IllegalArgumentException("User entered incorrect password");
        }

        loggedUser.setUsername(user.getUsername());
        loggedUser.setEmail(user.getEmail());
        loggedUser.setFullName(user.getFullname());
        loggedUser.setLogged(true);

        return passwordsMatch;
    }

    @Override
    public void logout(LoggedUser loggedUser) {
        loggedUser.logout();
    }
}