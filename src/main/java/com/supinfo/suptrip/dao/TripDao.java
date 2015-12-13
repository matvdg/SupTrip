package com.supinfo.suptrip.dao;

import com.supinfo.suptrip.entity.Campus;
import com.supinfo.suptrip.entity.Trip;

import java.util.List;

public interface TripDao {
    /**
     *
     * @param trip
     */
    void addTrip(Trip trip);

    /**
     * Update a trip
     */
    void updateTrip(Trip trip);

    /**
     * Find a trip by id
     */
    Trip findTripById(Integer id);

    /**
     * @return an unmodifiable list of all trips stored in memory filtered by Campus
     */
    List<Trip> getTripsByCampus(Campus campus);


    /**
     * @return an unmodifiable list of all trips stored in memory
     */
    List<Trip> getAllTrips();

    /**
     * Remove a trip from the memory
     */
    void removeTrip(Trip trip);


    /**
     * Return the number of traveller by trip
     */
    Integer getTravellersNumber(Trip trip);

    /**
     * Remove a trip from the memory by ID
     */
    void removeTripById(Integer id);
}
