package com.yildirimog.springtodo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TodoRequest {
    @NotBlank(message = "Başlık zorunludur")
    @Size(max = 100, message = "Başlık 100 karakterden uzun olamaz")
    private String title;

    @Size(max = 500, message = "Açıklama 500 karakterden uzun olamaz")
    private String description;

    // Constructors
    public TodoRequest() {
    }

    public TodoRequest(String title, String description) {
        this.title = title;
        this.description = description;
    }

    // Getters and Setters
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

