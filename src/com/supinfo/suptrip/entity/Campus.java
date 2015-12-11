package com.supinfo.suptrip.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "campuses")
@XmlRootElement
public class Campus implements Serializable {

    // properties
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    @OneToMany(cascade = {CascadeType.REMOVE}, mappedBy = "campus", fetch=FetchType.EAGER )
    private List<User> users;

    @OneToMany(cascade = {CascadeType.REMOVE}, mappedBy = "origin", fetch=FetchType.LAZY )
    private List<Trip> originTrips;

    @OneToMany(cascade = {CascadeType.REMOVE}, mappedBy = "destination", fetch=FetchType.LAZY )
    private List<Trip> destinationTrips;


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