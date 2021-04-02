package org.innovativeapps.mongohoteldemo.service;

import org.innovativeapps.mongohoteldemo.manager.AddressManagerLocal;
import org.innovativeapps.mongohoteldemo.model.Address;
import org.innovativeapps.mongohoteldemo.model.AddressList;
import org.innovativeapps.mongohoteldemo.util.exception.GeneralAppException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("v1/hotelsdemo")
public class AddressService {

    @Context
    HttpServletRequest request;

    @EJB
    AddressManagerLocal addressManager;

    @Path("/addresses")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAddresses() throws GeneralAppException {
        AddressList addressList = addressManager.getAllAddresses();

        return Response.ok(addressList).build();
    }

    @Path("/addresses")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addAddress(@FormParam("city") String city, @FormParam("state") String state) throws GeneralAppException {
        Address address = addressManager.addAddress(city, state);

        return Response.ok(address).build();
    }

    @Path("/addresses/{address-id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAddress(@PathParam("address-id") String addressId) throws GeneralAppException {
        Address address = addressManager.getAddress(addressId);

        return Response.ok(address).build();
    }
}
