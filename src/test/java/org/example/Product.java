package org.example;

public class Product {
    private int id;
    private String name;
    private String description;
    private double price;
    private int category_id;
    private String category_name;

    // constructor for Product by default
    public Product(){}

    // another constructor for Product with parameters
    public Product(String category_name, String description){
        setCategory_name(category_name);
        setDescription(description);
    }

    // getters and setters
    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
