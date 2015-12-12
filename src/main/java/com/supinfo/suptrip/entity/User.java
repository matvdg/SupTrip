package com.supinfo.suptrip.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.apache.commons.codec.digest.DigestUtils;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements Serializable {

    // properties
    @Id
    private int idBooster;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean admin;

    @ManyToOne
    @JsonManagedReference
    private Campus campus;

    @ManyToMany
    @JsonManagedReference
    @JoinTable(
            name="trips_users",
            joinColumns={@JoinColumn(name="users_id", referencedColumnName="idBooster")},
            inverseJoinColumns={@JoinColumn(name="trips_id", referencedColumnName="idTrip")})
    private List<Trip> trips;


    //constructors
    public User() {

    }

    public User(int id, String firstName, String lastName, String email, String password, boolean admin, Campus campus) {
        this.idBooster = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = DigestUtils.shaHex(password);
        this.admin = admin;
        this.campus = campus;
    }


    //getters & setters
    public int getId() {
        return idBooster;
    }

    public void setId(int id) {
        this.idBooster = id;
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
        this.password = DigestUtils.shaHex(password);;
    }

    public Campus getCampus() {
        return campus;
    }

    public void setCampus(Campus campus) {
        this.campus = campus;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }
}