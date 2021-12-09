package com.example.fantasticshop;

public class ItemModel {
    private int id;
    private String image_location;
    private String name;
    private String description;
    private String category;
    private String price;

    // constructors

    public ItemModel(int id, String image_location, String name, String description, String category, String price) {
        this.id = id;
        this.image_location = image_location;
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
    }

    public ItemModel() {
    }

    // toString to print out item objects

    @Override
    public String toString() {
        return "ItemModel{" +
                "id=" + id +
                ", image_location='" + image_location + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", price='" + price + '\'' +
                '}';
    }


    // getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage_location() {
        return image_location;
    }

    public void setImage_location(String image_location) {
        this.image_location = image_location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
