package com.supinfo.suptrip.rest;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/trips")
public class TripResource {


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllTrips()  {
        String json = "";
        return json;
    }

    @GET
    @Path("/{productId}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getProductInJson(@PathParam("productId") Long productId){
        String products = "";
        return products;
    }
}
