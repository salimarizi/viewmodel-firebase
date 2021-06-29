package com.example.modelviewfirebase.Entity;

import java.io.Serializable;

public class Student implements Serializable {
    private String id;
    private String firstName;
    private String lastName;
    private String address;

    public Student() {}

    public Student(String id, String firstName, String lastName, String address) {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setAddress(address);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
