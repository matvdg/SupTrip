package com.supinfo.suptrip.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "campuses")
public class Campus implements Serializable {

    // properties
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    @OneToMany(cascade = {CascadeType.REMOVE}, mappedBy = "campus")
    private List<User> users;

    /*@OneToMany(mappedBy = "campus")
    private List<Trip> originTrips;

    @OneToMany(mappedBy = "campus")
    private List<Trip> destinationTrips;*/


    //constructor
    public Campus() {

    }

    //getters & setters
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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
/*
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
*/
}