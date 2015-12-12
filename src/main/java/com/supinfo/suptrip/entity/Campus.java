package com.supinfo.suptrip.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "campuses")
public class Campus implements Serializable {

    // properties
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idCampus;
    private String name;

    @OneToMany(mappedBy = "campus", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<User> users;

    @OneToMany(mappedBy = "origin", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Trip> originTrips;

    @OneToMany(mappedBy = "destination", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Trip> destinationTrips;


    //constructor
    public Campus() {

    }

    //getters & setters
    public int getId() {
        return idCampus;
    }

    public void setId(int id) {
        this.idCampus = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Trip> getOriginTrips() {
        return originTrips;
    }

    public void setOriginTrips(List<Trip> originTrips) {
        this.originTrips = originTrips;
    }

    public List<Trip> getDestinationTrips() {
        return destinationTrips;
    }

    public void setDestinationTrips(List<Trip> destinationTrips) {
        this.destinationTrips = destinationTrips;
    }

}