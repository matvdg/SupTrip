package com.supinfo.suptrip.rest;


import com.supinfo.suptrip.dao.DaoFactory;
import com.supinfo.suptrip.entity.Campus;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/campus")
public class CampusResource {

    @GET @Produces(MediaType.APPLICATION_JSON)
    public Campus getAllCampuses() {
        System.out.println(DaoFactory.getCampusDao().findCampusById(1));
        Campus campus = DaoFactory.getCampusDao().findCampusById(1);


        return campus;
    }

}
