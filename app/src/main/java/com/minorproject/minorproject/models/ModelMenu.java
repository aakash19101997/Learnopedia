package com.minorproject.minorproject.models;

public class ModelMenu {
    public String menuName,id,icon;
    public boolean hasChildren, isGroup;

    public ModelMenu(String menuName, String id,String icon, boolean hasChildren, boolean isGroup) {
        this.menuName = menuName;
        this.id = id;
        this.icon = icon;
        this.hasChildren = hasChildren;
        this.isGroup = isGroup;
    }
}
