package com.mahditaz.contactsmanager;

public class UserManualItem {
    int imgRef;
    String title;
    String description;

    public UserManualItem() {
    }

    public UserManualItem(int imgRef, String title, String description) {
        this.imgRef = imgRef;
        this.title = title;
        this.description = description;
    }

    public int getImgRef() {
        return imgRef;
    }

    public void setImgRef(int imgRef) {
        this.imgRef = imgRef;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
