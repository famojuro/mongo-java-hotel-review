package org.innovativeapps.mongohoteldemo.model;

import java.util.ArrayList;
import java.util.List;

public class ReviewList {
    private List<Review> allReviews;

    public ReviewList() { allReviews = new ArrayList<>(); }

    public List<Review> getAllReviews() { return allReviews; }

    public void setAllReviews(List<Review> allReviews) { this.allReviews.addAll(allReviews); }
}
