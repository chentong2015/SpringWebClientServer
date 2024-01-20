package com.ctong.main.controller.sessionAttribute.model;

import java.io.Serializable;
import java.util.List;

public class VisitorData implements Serializable {

    private String currentVisitorName;
    private String currentVisitorEmail;
    private List<Visitor> visitors;

    public VisitorData() {

    }

    public VisitorData(String currentVisitorName, String currentVisitorEmail, List<Visitor> visitors) {
        this.currentVisitorName = currentVisitorName;
        this.currentVisitorEmail = currentVisitorEmail;
        this.visitors = visitors;
    }

    public String getCurrentVisitorName() {
        return currentVisitorName;
    }

    public void setCurrentVisitorName(String currentVisitorName) {
        this.currentVisitorName = currentVisitorName;
    }

    public String getCurrentVisitorEmail() {
        return currentVisitorEmail;
    }

    public void setCurrentVisitorEmail(String currentVisitorEmail) {
        this.currentVisitorEmail = currentVisitorEmail;
    }

    public List<Visitor> getVisitors() {
        return visitors;
    }

    public void setVisitors(List<Visitor> visitors) {
        this.visitors = visitors;
    }

    @Override
    public String toString() {
        return "VisitorData{" +
                "currentVisitorName='" + currentVisitorName + '\'' +
                ", currentVisitorEmail='" + currentVisitorEmail + '\'' +
                '}';
    }
}
