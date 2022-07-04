package com.ironhack.MidtermBankingSystem.controller.dto;

public class AddressDTO {

    private String street;
    private int number;
    private int floor;
    private String door;
    private int zipCode;
    private String city;
    private String country;


    public AddressDTO() {
    }

    public AddressDTO(String street, int number, int floor, String door, int zipCode,
                   String city, String country) {
        this.street = street;
        this.number = number;
        this.floor = floor;
        this.door = door;
        this.zipCode = zipCode;
        this.city = city;
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getDoor() {
        return door;
    }

    public void setDoor(String door) {
        this.door = door;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
