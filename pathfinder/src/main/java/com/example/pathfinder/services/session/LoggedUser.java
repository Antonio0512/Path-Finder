package com.example.pathfinder.services.session;

import org.springframework.stereotype.Component;

@Component
public class LoggedUser {
    private String username;
    private String email;
    private String fullName;
    private boolean isLogged;

    public boolean isLogged() {
        return isLogged;
    }
    public void setLogged(boolean isLogged) {
        this.isLogged = isLogged;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void logout() {
        setUsername(null);
        setEmail(null);
        setFullName(null);
        setLogged(false);
    }

}
