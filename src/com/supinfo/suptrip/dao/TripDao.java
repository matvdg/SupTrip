package com.supinfo.suptrip.dao;

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
    Trip findTripById(int id);

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
    int travellersNumberById(Trip trip);

    /**
     * Remove a trip from the memory by ID
     */
    void removeTripById(int id);
}
