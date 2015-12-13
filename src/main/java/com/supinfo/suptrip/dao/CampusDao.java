package com.supinfo.suptrip.dao;

import com.supinfo.suptrip.entity.Campus;

import java.util.List;

public interface CampusDao {
    /**
     *
     * @param campus
     */
    void addCampus(Campus campus);

    /**
     * Update a campus
     */
    void updateCampus(Campus campus);

    /**
     * Find a campus by id
     */
    Campus findCampusById(Integer id);

    /**
     * Find a campus by name
     */
    Campus findCampusByName(String name);

    /**
     * @return an unmodifiable list of all campuses stored in memory
     */
    List<Campus> getAllCampuses();

    /**
     * Remove a campus from the memory
     */
    void removeCampus(Campus campus);

    /**
     * Remove a campus from the memory by ID
     */
    void removeCampusById(Integer id);

    /**
     * Return the number of students by campus
     */
    Integer getStudentsNumber(Campus campus);

    /**
     * Return the number of campus
     */
    Integer getCampusNumber();
}
