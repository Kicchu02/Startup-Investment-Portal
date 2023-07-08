package com.codebrewers.startupinvestmentportal;

import android.media.Image;

import java.util.Arrays;

public class Ideas {
    private String title;
    private byte[] image;
    private String shortDescription;
    private String longDescription;
    private String email;
    private long phone;

    public Ideas(String title, byte[] image, String shortDescription, String longDescription, String email, long phone) {
        this.title = title;
        this.image = image;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.email = email;
        this.phone = phone;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Ideas{" +
                "title='" + title + '\'' +
                ", image=" + Arrays.toString(image) +
                ", shortDescription='" + shortDescription + '\'' +
                ", longDescription='" + longDescription + '\'' +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                '}';
    }
}
