package com.yildirimog.springtodo;

import jakarta.validation.constraints.Size;

public class TodoUpdateRequest {
    @Size(max = 100, message = "Başlık 100 karakterden uzun olamaz")
    private String title;

    @Size(max = 500, message = "Açıklama 500 karakterden uzun olamaz")
    private String description;

    private Boolean completed;

    // Constructors
    public TodoUpdateRequest() {}

    // Getters and Setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Boolean getCompleted() { return completed; }
    public void setCompleted(Boolean completed) { this.completed = completed; }

    // Helper methods
    public boolean hasTitleUpdate() { return title != null && !title.trim().isEmpty(); }
    public boolean hasDescriptionUpdate() { return description != null; }
    public boolean hasCompletedUpdate() { return completed != null; }
}
