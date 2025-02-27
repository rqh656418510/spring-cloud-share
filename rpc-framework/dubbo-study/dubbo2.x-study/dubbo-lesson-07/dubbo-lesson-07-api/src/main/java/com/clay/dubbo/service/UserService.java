package com.clay.dubbo.service;

import com.clay.dubbo.domain.User;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/user")
public interface UserService {

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)       // 接收 JSON 格式的数据
    @Produces({MediaType.APPLICATION_JSON})     // 返回 JSON 格式的数据
    Boolean add(User user);

    @GET
    @Path("/getById/{id}")
    @Produces({MediaType.APPLICATION_JSON})     // 返回 JSON 格式的数据
    User getById(@PathParam("id") Long id);

}