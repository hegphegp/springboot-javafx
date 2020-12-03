package com.codingfly.model;

import java.util.ArrayList;
import java.util.List;

public class MenuModel {
    private String name;
    private List<MenuModel> children = new ArrayList();
    public MenuModel() { }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MenuModel> getChildren() {
        return children;
    }

    public void setChildren(List<MenuModel> children) {
        this.children = children;
    }
}
