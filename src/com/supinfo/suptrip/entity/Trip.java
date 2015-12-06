package com.supinfo.suptrip.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "trips")
public class Trip implements Serializable {

    // properties
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private Date departureTime;
    private Date arrivalTime;

    @ManyToOne(targetEntity = Campus.class)
    @JoinColumn(name = "id_origin")
    private Campus origin;

    @ManyToOne(targetEntity = Campus.class)
    @JoinColumn(name = "id_destination")
    private Campus destination;

    @ManyToMany(targetEntity = User.class)
    @JoinTable(name="trips_users")
    private List<User> travellers;


    //constructor
    public Trip() {

    }

    //getters & setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

}

