package org.innovativeapps.mongohoteldemo.model;

public class Hotel {
    private String id;
    private String name;
    private String pricePerNight;
    private Address address;
    private ReviewList reviews;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(String pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public ReviewList getReviews() {
        return reviews;
    }

    public void setReviews(ReviewList reviews) {
        this.reviews = reviews;
    }
}
