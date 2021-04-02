package org.innovativeapps.mongohoteldemo.service;

import org.innovativeapps.mongohoteldemo.manager.ReviewManagerLocal;
import org.innovativeapps.mongohoteldemo.model.Review;
import org.innovativeapps.mongohoteldemo.model.ReviewList;
import org.innovativeapps.mongohoteldemo.util.exception.GeneralAppException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServlet;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.ws.spi.http.HttpContext;

@Stateless
@Path("/v1/userreviews")
public class ReviewService {

    @Context
    HttpServlet request;

    @EJB
    ReviewManagerLocal reviewManager;

    @Path("/reviews")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllReviews() throws GeneralAppException {
        ReviewList reviewList = reviewManager.getAllReviews();

        return Response.ok(reviewList).build();
    }

    @Path("/reviews")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addReview(@FormParam("user-name")String userName, @FormParam("rating")String rating,
                              @FormParam("user-id")String userId,
                              @FormParam("hotel-id")String hotelId) throws GeneralAppException {
        Review review = reviewManager.addReview(userName, userId, rating, hotelId);

        return Response.ok(review).build();
    }
}
