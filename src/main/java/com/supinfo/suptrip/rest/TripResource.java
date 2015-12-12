package com.supinfo.suptrip.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.supinfo.suptrip.dao.DaoFactory;
import com.supinfo.suptrip.entity.Campus;
import com.supinfo.suptrip.entity.Trip;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/trip")
public class TripResource {
    @GET
    @Produces("application/json")
    public Response getTrips() {
        List<Trip> trips = DaoFactory.getTripDao().getAllTrips();
        System.out.println(trips);
        if (trips == null || trips.isEmpty()) {
            return Response.status(404).build();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return Response.status(200).entity(objectMapper.writeValueAsString(trips)).build();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return Response.status(400).build();
        }

    }

    @GET
    @Path("/{campusName}")
    @Produces("application/json")
    public Response getTrips(@PathParam("campusName") String campusName) {
        Campus campus = DaoFactory.getCampusDao().findCampusByName(campusName);

        if (campus == null) {
            return Response.status(400).build();
        }

        List<Trip> trips = DaoFactory.getTripDao().getTripsByCampus(campus);

        if (trips == null || trips.isEmpty()) {
            return Response.status(400).build();
        }

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return Response.status(200).entity(objectMapper.writeValueAsString(trips)).build();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return Response.status(400).build();
    }
}
