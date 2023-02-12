package com.mywed.model;


import jakarta.persistence.*;
@Entity
@Table(name = "categories")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private int price;
    @Column(name = "description")
    private String description;
    @Column(name = "Information")
    private String Information;
    @Column(name = "motor")
    private String motor;
    @Column(name = "specifications")
    private String specifications;
    @Column(name = "category")
    private String category;
    @Column(name = "Picture")
    private String picture;
    @Column(name = "cylinder")
    private Integer cylinder;

    public Products() {}

    public Products(int id, String name, int price, String description, String information,
                    String motor, String specifications, String category, String picture) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        Information = information;
        this.motor = motor;
        this.specifications = specifications;
        this.category = category;
        this.picture = picture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInformation() {
        return Information;
    }

    public void setInformation(String information) {
        Information = information;
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getCylinder() {
        return cylinder;
    }

    public void setCylinder(Integer cylinder) {
        this.cylinder = cylinder;
    }
}

