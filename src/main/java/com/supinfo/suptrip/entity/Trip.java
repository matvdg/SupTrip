package com.supinfo.suptrip.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "trips")
public class Trip implements Serializable {

    // properties
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idTrip;

    private Date departureTime;
    private Date arrivalTime;
    private float price;

    @ManyToOne
    @JsonManagedReference
    private Campus origin;

    @ManyToOne
    @JsonManagedReference
    private Campus destination;

    @ManyToMany
    @JsonBackReference
    @JoinTable(
            name="trips_users",
            joinColumns={@JoinColumn(name="trips_id", referencedColumnName="idTrip")},
            inverseJoinColumns={@JoinColumn(name="users_id", referencedColumnName="idBooster")})
    private List<User> travellers;


    //constructor
    public Trip() {

    }

    //getters & setters
    public int getId() {
        return idTrip;
    }

    public void setId(int id) {
        this.idTrip = id;
    }

    public Campus getOrigin() {
        return origin;
    }

    public void setOrigin(Campus origin) {
        this.origin = origin;
    }

    public Campus getDestination() {
        return destination;
    }

    public void setDestination(Campus destination) {
        this.destination = destination;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public List<User> getTravellers() {
        return travellers;
    }

    public void setTravellers(List<User> travellers) {
        this.travellers = travellers;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

}

