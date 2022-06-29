package com.ironhack.MidtermBankingSystem.auxiliary;

import org.hibernate.annotations.ValueGenerationType;

import javax.persistence.*;

/**
 * Name of the road, the number of the house, floor, door, zip code and name of the town where a person
 * lives or works, and where letters can be sent.
 */
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String street;
    @Column(name = "building_number")
    private short number;
    private byte floor;
    private char door;
    private int zipCode; // regex¿?
    private String city;
    private String country;


}
