package com.dev.graylien.image;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "image")
public class ImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID generation strategy
    private Integer id;

    @NotEmpty
    private String title;

    @Enumerated(EnumType.STRING) // Ensures category is stored as a string
    private ImageCategory category;

    private String url;

    // Default constructor (required by JPA)
    public ImageEntity() {
    }

    // Constructor to initialize fields
    public ImageEntity(String title, ImageCategory category, String url) {
        this.title = title;
        this.category = category;
        this.url = url;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ImageCategory getCategory() {
        return category;
    }

    public void setCategory(ImageCategory category) {
        this.category = category;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // Optionally, you can override toString, equals, and hashCode if needed
    @Override
    public String toString() {
        return "Image{" +
               "id=" + id +
               ", title='" + title + '\'' +
               ", category=" + category +
               ", url='" + url + '\'' +
               '}';
    }
}


