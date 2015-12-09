package com.supinfo.suptrip.entity;

import org.apache.commons.codec.digest.DigestUtils;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class User implements Serializable {

    // properties
    @Id
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean admin;

    @ManyToOne
    @JoinColumn(name = "id_campus")

    private Campus campus;

/*
    @ManyToMany(mappedBy = "users")
    private List<Trip> trips;

*/
    //constructor

    public User() {

    }

    public User(int id, String firstName, String lastName, String email, String password, boolean admin, Campus campus) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = DigestUtils.sha1Hex(password);
        this.admin = admin;
        this.campus = campus;
    }


    //getters & setters
    /*
    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }
*/
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public Campus getCampus() {
        return campus;
    }

    public void setCampus(Campus campus) {
        this.campus = campus;
    }


}