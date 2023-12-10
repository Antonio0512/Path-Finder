package com.example.pathfinder.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.pathfinder.models.Route;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
    @Query("SELECT r FROM Route r ORDER BY SIZE(r.comments) DESC")
    List<Route> findMostCommented();
}