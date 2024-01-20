package com.ctong.main.controller.sessionAttribute.model;

public class Visitor {

    private String name;
    private String email;

    public Visitor(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Visitor{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
