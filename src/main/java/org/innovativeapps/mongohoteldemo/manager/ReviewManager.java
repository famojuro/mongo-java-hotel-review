package org.innovativeapps.mongohoteldemo.manager;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.innovativeapps.mongohoteldemo.data.manager.DataManagerLocal;
import org.innovativeapps.mongohoteldemo.model.*;
import org.innovativeapps.mongohoteldemo.util.exception.GeneralAppException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.set;

@Stateless
public class ReviewManager implements ReviewManagerLocal{

    @EJB
    DataManagerLocal dataManager;
    
    @EJB
    HotelManagerLocal hotelManager;

    @EJB
    UserManagerLocal userManager;

    @EJB
    ExceptionThrowerManagerLocal exceptionManager;

    private final String REVIEWS_PATH = "/userreviews/reviews";
    private final String HOTELS_PATH = "/hotels";

    @Override
    public ReviewList getAllReviews() throws GeneralAppException {
        ReviewList reviewList = new ReviewList();
        MongoCollection<Document> reviews = dataManager.getCollection("reviews");

        List<Review> prepareAllReviews = prepareAllReviews(reviews);
        reviewList.setAllReviews(prepareAllReviews);

        return reviewList;
    }

    private List<Review> prepareAllReviews(MongoCollection<Document> reviews) throws GeneralAppException {
        List<Review> reviewList = new ArrayList<>();
        FindIterable<Document> iterable = reviews.find();

        MongoCursor<Document> cursor = iterable.iterator();
        try {
            while (cursor.hasNext()) {
                Document review = cursor.next();
                Review preparedReview = mapReviewFromDocument(review);
                reviewList.add(preparedReview);
            }
        } finally {
            cursor.close();
        }

        return reviewList;
    }

    private Review mapReviewFromDocument(Document review) throws GeneralAppException {
        Review mappedReview = new Review();
        if (review != null) {
            mappedReview.setId(review.getObjectId(ReviewConstant.ID).toString());
            mappedReview.setUserName(review.getString(ReviewConstant.USER_NAME));
            mappedReview.setRating(review.getString(ReviewConstant.RATING));
            mappedReview.setHotelId(review.getString(ReviewConstant.HOTEL_ID));
        }

        return mappedReview;
    }

    @Override
    public Review addReview(Review review) throws GeneralAppException {
        MongoCollection<Document> dbReviews = dataManager.getCollection("reviews");
        FindIterable<Document> iterable = null;

        MongoCursor<Document> cursor = null;
        try {
            iterable = dbReviews.find();
            cursor = iterable.iterator();
            Document newReview = new Document();
            newReview.put("_id", new ObjectId());
            newReview.put("userName", review.getUserName());
            newReview.put("rating", review.getRating());
            newReview.put("hotelId", review.getHotelId());
            dbReviews.insertOne(newReview);
        } finally {
            cursor.close();
        }

        return review;
    }

    @Override
    public Review addReview(String userName, String userId, String rating, String hotelId) throws GeneralAppException {

        Review review = new Review();
        review.setUserName(userName);
        User user = userManager.getUser(userId);
        if (user == null) {
            exceptionManager.throwUserNotFoundException(REVIEWS_PATH);
        } else {
            review.setUserName(userName);
        }
        review.setRating(rating);
        Hotel hotel = hotelManager.getHotel(hotelId);
        
        if (hotel == null) {
            exceptionManager.throwHotelNotFoundException(HOTELS_PATH);
        } else {
            review.setHotelId(hotelId);
        }
        addReview(review);

        return review;
    }

    @Override
    public Review getReview(String reviewId) throws GeneralAppException {
        ReviewList reviewList = getAllReviews();
        Review review = getReview(reviewId, reviewList);

        return review;
    }

    @Override
    public Review getReview(String reviewId, ReviewList reviewList) throws GeneralAppException {
        for (Review review : reviewList.getAllReviews()) {
            if (review != null && review.getId() != null && review.getId().equals(reviewId)) {
                return review;
            }
        }

        return null;
    }

    @Override
    public Review updateReview(String reviewId, String userName, String rating) throws GeneralAppException {
        MongoCollection<Document> dbReviews = dataManager.getCollection("reviews");
        Bson filter = eq("_id", new ObjectId(reviewId));
        Bson updateOperation = set("rating", rating);
        dbReviews.updateOne(filter, updateOperation);

        return null;
    }
}
