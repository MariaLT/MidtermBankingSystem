package com.ironhack.MidtermBankingSystem.auxiliary;

import org.hibernate.annotations.ValueGenerationType;

import javax.persistence.*;

/**
 * Name of the road, the number of the house, floor, door, zip code and name of the town where a person
 * lives or works, and where letters can be sent.
 */
@Embeddable
public class Address {
    private String street;
    private short number;
    private byte floor;
    private char door;
    private int zipCode; // regex¿?
    private String city;
    private String country;


    public Address() {
    }

    public Address(String street, short number, byte floor, char door, int zipCode, String city, String country) {
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

    public short getNumber() {
        return number;
    }

    public void setNumber(short number) {
        this.number = number;
    }

    public byte getFloor() {
        return floor;
    }

    public void setFloor(byte floor) {
        this.floor = floor;
    }

    public char getDoor() {
        return door;
    }

    public void setDoor(char door) {
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
