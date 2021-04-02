package org.innovativeapps.mongohoteldemo.manager;

import org.innovativeapps.mongohoteldemo.model.Address;
import org.innovativeapps.mongohoteldemo.model.Hotel;
import org.innovativeapps.mongohoteldemo.model.HotelList;
import org.innovativeapps.mongohoteldemo.model.Review;
import org.innovativeapps.mongohoteldemo.util.exception.GeneralAppException;

import javax.ejb.Local;

@Local
public interface HotelManagerLocal {

    HotelList getAllHotels() throws GeneralAppException;

    Hotel addHotel(Hotel hotel) throws GeneralAppException;

    Hotel addHotel(String name, String pricePerNight, String addressId) throws GeneralAppException;

    Hotel addHotelReview(Hotel hotel, String reviewId) throws GeneralAppException;

    Hotel addHotelReview(String hotelId, String reviewId) throws GeneralAppException;

    Hotel updateHotelReviews(String hotelId, Review review) throws GeneralAppException;

    Hotel updateHotel(String hotelId, String name, String pricePerNight, Address address) throws GeneralAppException;

    Hotel getHotel(String hotelId) throws GeneralAppException;

    Hotel getHotel(String hotelId, HotelList hotelList) throws GeneralAppException;

    void removeHotel(String hotelId);
}
