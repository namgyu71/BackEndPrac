package com.example.demo.domain;

import java.sql.Timestamp;

public class User {
    private int userId;
    private String email;
    private String name;
    private String password;
    private Timestamp timestamp;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
        public Timestamp getRegDate() {
        return timestamp;
    }

    public void setRegDate(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
