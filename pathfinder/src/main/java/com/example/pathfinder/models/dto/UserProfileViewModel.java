package com.example.pathfinder.models.dto;

import com.example.pathfinder.models.enums.Level;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class UserProfileViewModel {
    private String username;
    private String fullName;
    private int age; 
    private Level level;
}
