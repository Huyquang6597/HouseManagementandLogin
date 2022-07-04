package com.example.airbnb.model;


import org.hibernate.validator.constraints.Range;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Component
@Entity
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "not null")
    private String name;

    @NotBlank(message = "not null")
    private String address;

    @Range(min = 1 , max = 10, message = "Out of room")
    private int numberOfRoom;

    @Range(min = 1, max = 3, message = "Out of room")
    private int numberOfBedroom;

    private double pricePerDay;

    @Pattern(regexp = ".*((.png)|(.jpg))" , message = "Chỉ được upfile png hoặc jpg")
    private String image;

    public House() {
    }

    public House(Long id, String name, String address, int numberOfRoom, int numberOfBedroom, double pricePerDay, String image) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.numberOfRoom = numberOfRoom;
        this.numberOfBedroom = numberOfBedroom;
        this.pricePerDay = pricePerDay;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNumberOfRoom() {
        return numberOfRoom;
    }

    public void setNumberOfRoom(int numberOfRoom) {
        this.numberOfRoom = numberOfRoom;
    }

    public int getNumberOfBedroom() {
        return numberOfBedroom;
    }

    public void setNumberOfBedroom(int numberOfBedroom) {
        this.numberOfBedroom = numberOfBedroom;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}