package com.clay.dubbo.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("/user")
public interface UserService {

    @GET
    @Path("/sayHello")
    String sayHello(@QueryParam("name") String name);

}
