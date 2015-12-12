package com.supinfo.suptrip.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.supinfo.suptrip.dao.DaoFactory;
import com.supinfo.suptrip.entity.User;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/users")
public class UserResource {
    @GET
    @Produces("application/json")
    public Response getCampuses() {
        List<User> users = DaoFactory.getUserDao().getAllUsers();
        if (users == null || users.isEmpty()) {
            return Response.status(404).build();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return Response.status(200).entity(objectMapper.writeValueAsString(users)).build();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return Response.status(400).build();
        }

    }
}