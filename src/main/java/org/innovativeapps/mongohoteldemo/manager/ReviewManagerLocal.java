package org.innovativeapps.mongohoteldemo.manager;

import org.innovativeapps.mongohoteldemo.model.Review;
import org.innovativeapps.mongohoteldemo.model.ReviewList;
import org.innovativeapps.mongohoteldemo.util.exception.GeneralAppException;

import javax.ejb.Local;

@Local
public interface ReviewManagerLocal {

    ReviewList getAllReviews() throws GeneralAppException;

    Review addReview(Review review) throws GeneralAppException;

    Review addReview(String userName, String userId, String rating, String hotelId) throws GeneralAppException;

    Review updateReview(String reviewId, String userName, String rating) throws GeneralAppException;

    Review getReview(String reviewId) throws GeneralAppException;

    Review getReview(String reviewId, ReviewList reviewList) throws GeneralAppException;

}
