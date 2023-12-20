package com.example.pathfinder.services.impl;

import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.pathfinder.exceptions.UserNotFoundException;
import com.example.pathfinder.models.Role;
import com.example.pathfinder.models.User;
import com.example.pathfinder.models.dto.UserLoginDTO;
import com.example.pathfinder.models.dto.UserProfileViewModel;
import com.example.pathfinder.models.dto.UserRegistrationDTO;
import com.example.pathfinder.models.enums.Level;
import com.example.pathfinder.models.enums.UserRoles;
import com.example.pathfinder.repositories.AuthRepository;
import com.example.pathfinder.repositories.RoleRepository;
import com.example.pathfinder.services.AuthService;
import com.example.pathfinder.services.session.LoggedUser;

@Service
public class AuthServiceImpl implements AuthService {
    private final AuthRepository authRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(AuthRepository authRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.authRepository = authRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public void register(UserRegistrationDTO registrationDTO) {
        User user = modelMapper.map(registrationDTO, User.class);
        Role role = roleRepository.findRoleByName(UserRoles.USER);

        user.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
        user.setLevel(Level.BEGINNER);
        user.setRoles(Set.of(role));
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

    @Override
    public UserProfileViewModel getUserProfile(LoggedUser loggedUser) {
        String username = loggedUser.getUsername();
        User user = authRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User with username: " + username + "not found!"));


        return modelMapper.map(user, UserProfileViewModel.class);
    }
}