package com.example.pathfinder.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pathfinder.models.Category;
import com.example.pathfinder.models.enums.RouteCategory;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Set<Category> findByNameIn(Set<RouteCategory> categories);
}
