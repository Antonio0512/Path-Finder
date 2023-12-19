package com.example.pathfinder.models.dto;

import java.util.Set;

import com.example.pathfinder.models.enums.Level;
import com.example.pathfinder.models.enums.RouteCategory;

public class CreateRouteDTO {
    private String name;
    private String description;
    private Level level;
    private String videoUrl;
    private Set<RouteCategory> categories;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Level getLevel() {
        return level;
    }
    public void setLevel(Level level) {
        this.level = level;
    }
    public String getVideoUrl() {
        return videoUrl;
    }
    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
    public Set<RouteCategory> getCategories() {
        return categories;
    }
    public void setCategories(Set<RouteCategory> categories) {
        this.categories = categories;
    }
    
}
