package com.example.modelviewfirebase.Entity;

import java.io.Serializable;

public class Portfolio implements Serializable {
    private String student;
    private String title;
    private String description;

    public Portfolio() {}

    public Portfolio(String student, String title, String description) {
        setStudent(student);
        setTitle(title);
        setDescription(description);
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
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
