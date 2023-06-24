package com.codebrewers.startupinvestmentportal;

public class Ideas {
    private int id;
    private String shortDescription;
    private String longDescription;
    private String email;

    @Override
    public String toString() {
        return "Ideas{" +
                "id=" + id +
                ", shortDescription='" + shortDescription + '\'' +
                ", longDescription='" + longDescription + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public Ideas(int id, String shortDescription, String longDescription, String email) {
        this.id = id;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
