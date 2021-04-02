package org.innovativeapps.mongohoteldemo.service;

import org.innovativeapps.mongohoteldemo.manager.UserManagerLocal;
import org.innovativeapps.mongohoteldemo.model.User;
import org.innovativeapps.mongohoteldemo.model.UserList;
import org.innovativeapps.mongohoteldemo.util.exception.GeneralAppException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("/v1/hr/reviews")
public class UserService {

    @Context
    HttpServletRequest request;

    @EJB
    UserManagerLocal userManager;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUser(@FormParam("first-name") String firstName, @FormParam("last-name") String lastName) throws GeneralAppException{
        User user = userManager.addUser(firstName, lastName);

        return Response.ok(user).build();
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() throws GeneralAppException {
        UserList userList = userManager.getAllUsers();

        return Response.ok(userList).build();
    }

    @Path("/users")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeUser(@QueryParam("userId") String userId) throws GeneralAppException{
         userManager.removeUser(userId);

        return Response.ok().build();
    }


}
