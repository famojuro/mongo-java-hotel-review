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
public class HotelManager implements HotelManagerLocal{

    @EJB
    DataManagerLocal dataManager;

    @EJB
    UserManagerLocal userManager;

    @EJB
    AddressManagerLocal addressManager;

    @EJB
    ReviewManagerLocal reviewManager;

    @Override
    public HotelList getAllHotels() throws GeneralAppException {
        HotelList hotelList = new HotelList();
        MongoCollection<Document> hotels = dataManager.getCollection("hotels");

        List<Hotel> prepareAllHotels = prepareAllHotels(hotels);
        hotelList.setAllHotels(prepareAllHotels);

        return hotelList;
    }

    private List<Hotel> prepareAllHotels(MongoCollection<Document> hotels) throws GeneralAppException {
        List<Hotel> hotelList = new ArrayList<>();
        FindIterable<Document> iterable = hotels.find();

        MongoCursor<Document> cursor = iterable.iterator();
        try {
            while (cursor.hasNext()) {
                Document hotel = cursor.next();
                Hotel preparedHotel = mapHotelFromDocument(hotel);
                hotelList.add(preparedHotel);
            }
        }finally {
            cursor.close();
        }

        return hotelList;
    }

    private Hotel mapHotelFromDocument(Document hotel) throws GeneralAppException {
        Hotel mappedHotel = new Hotel();
        if (hotel != null) {
            mappedHotel.setId(hotel.getObjectId(HotelConstant.ID).toString());
            mappedHotel.setName(hotel.getString(HotelConstant.NAME));
            mappedHotel.setAddress((Address) hotel.get("address",Address.class));
            mappedHotel.setPricePerNight(hotel.getString(HotelConstant.PRICE_PER_NIGHT));
            mappedHotel.setReviews((ReviewList) hotel.get("reviews",ReviewList.class));
        }

        return mappedHotel;
    }

    @Override
    public Hotel addHotel(Hotel hotel) throws GeneralAppException {
        MongoCollection<Document> dbHotels = dataManager.getCollection("hotels");
        FindIterable<Document> iterable = null;

        MongoCursor<Document> cursor = null;
        try {
            iterable = dbHotels.find();
            cursor = iterable.iterator();
            Document newHotel = new Document();
            newHotel.put("_id", new ObjectId());
            newHotel.put("name", hotel.getName());
            newHotel.put("pricePerNight", hotel.getPricePerNight());
            newHotel.put("address", hotel.getAddress());
            newHotel.put("reviews", hotel.getReviews());
            dbHotels.insertOne(newHotel);
        } finally {
            cursor.close();
        }

        return hotel;
    }

    @Override
    public Hotel addHotel(String name, String pricePerNight, String addressId) throws GeneralAppException {
        Hotel hotel = new Hotel();
        hotel.setName(name);
        hotel.setPricePerNight(pricePerNight);

        AddressList addressList = addressManager.getAllAddresses();
        for (Address address: addressList.getAllAddresses()) {
            if (address.getId().equals(addressId)) {
                hotel.setAddress(address);
            }
        }
        hotel.setReviews(null);
        addHotel(hotel);

        return hotel;
    }

    @Override
    public Hotel addHotelReview(Hotel hotel, String reviewId) throws GeneralAppException {
        HotelList hotelList = getAllHotels();
        ReviewList reviewList = reviewManager.getAllReviews();
        for (Hotel hotels: hotelList.getAllHotels()) {
            for (Review review: reviewList.getAllReviews()) {
                if (hotels.getId().equals(hotel.getId()) && review.getHotelId().equals(hotel.getId())) {
                    MongoCollection<Document> dbHotels = dataManager.getCollection("hotels");
                    FindIterable<Document> iterable = null;

                    MongoCursor<Document> cursor = null;
                    try {
                         iterable = dbHotels.find(eq("_id", new ObjectId(hotel.getId())));
                         cursor = iterable.iterator();
                         Bson filter = eq("_id", new ObjectId(hotel.getId()));
                         Bson updateOperation = set("reviews", hotel.getReviews());
                         dbHotels.updateOne(filter, updateOperation);
                    } finally {
                        cursor.close();
                    }
                }
            }
        }

        return hotel;
    }

    @Override
    public Hotel addHotelReview(String hotelId, String reviewId) throws GeneralAppException {
        Hotel hotel = getHotel(hotelId);
        Review review = reviewManager.getReview(reviewId);
          if (review.getId().equals(reviewId) && hotel.getId().equals(hotelId)) {
                    addHotelReview(hotel, reviewId);
                    return hotel;
                }

          return null;
    }

    @Override
    public Hotel updateHotelReviews(String hotelId, Review review) throws GeneralAppException {
        return null;
    }

    @Override
    public Hotel updateHotel(String hotelId, String name, String pricePerNight, Address address) throws GeneralAppException {
        return null;
    }

    @Override
    public Hotel getHotel(String hotelId) throws GeneralAppException {
        HotelList hotelList = getAllHotels();
        Hotel hotel = getHotel(hotelId, hotelList);

        return hotel;
    }

    @Override
    public Hotel getHotel(String hotelId, HotelList hotelList) throws GeneralAppException {
        for (Hotel hotel: hotelList.getAllHotels()) {
            if (hotel != null && hotel.getId() != null && hotel.getId().equals(hotelId)) {
                return hotel;
            }
        }

        return null;
    }

    @Override
    public void removeHotel(String hotelId) {

    }
}
