package com.example.pathfinder.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class RouteGetAllViewModel {
    private long id;
    private String imageUrl;
    private String name;
    private String description;
}
