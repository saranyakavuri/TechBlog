package com.tech.blog.model;

public class Contact {

    private String fullName;
    private String address;
    private String email;
    private String phoneNumber;
    private String role;
    private String imageData;

    public Contact() {

    }

    public Contact(String fullName, String address, String email, String phoneNumber, String role, String imageData) {
        this.fullName = fullName;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.imageData = imageData;
    }

    public String getFullName() {
        return fullName;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getRole() {
        return role;
    }

    public String getImageData() {
        return imageData;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "fullName='" + fullName + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", role='" + role + '\'' +
                ", imageData='" + imageData + '\'' +
                '}';
    }
}
