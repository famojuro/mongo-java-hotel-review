package org.innovativeapps.mongohoteldemo.manager;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.innovativeapps.mongohoteldemo.data.manager.DataManagerLocal;
import org.innovativeapps.mongohoteldemo.model.Address;
import org.innovativeapps.mongohoteldemo.model.AddressConstant;
import org.innovativeapps.mongohoteldemo.model.AddressList;
import org.innovativeapps.mongohoteldemo.util.exception.GeneralAppException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

@Stateless
public class AddressManager implements AddressManagerLocal{

    @EJB
    DataManagerLocal dataManager;

    @Override
    public AddressList getAllAddresses() throws GeneralAppException {
        AddressList addressList = new AddressList();
        MongoCollection<Document> addresses = dataManager.getCollection("addresses");

        List<Address> prepareAllAddresses = prepareAllAddresses(addresses);
        addressList.setAllAddresses(prepareAllAddresses);

        return addressList;
    }

    private List<Address> prepareAllAddresses(MongoCollection<Document> addresses) throws GeneralAppException{
        List<Address> addressList = new ArrayList<>();
        FindIterable<Document> iterable = addresses.find();

        MongoCursor<Document> cursor = iterable.iterator();
        try {
            while (cursor.hasNext()) {
                Document address = cursor.next();
                Address preparedAddress = mapAddressFromDocument(address);
                addressList.add(preparedAddress);
            }
        } finally {
            cursor.close();
        }

        return addressList;
    }

    private Address mapAddressFromDocument(Document address) throws GeneralAppException {
        Address mappedAddress = new Address();
        if (address != null) {
            mappedAddress.setId(address.getObjectId(AddressConstant.ID).toString());
            mappedAddress.setCity(address.getString(AddressConstant.CITY));
            mappedAddress.setState(address.getString(AddressConstant.STATE));
        }

        return mappedAddress;
    }

    @Override
    public Address addAddress(String city, String state) throws GeneralAppException {

        Address address = new Address();
        address.setCity(city);
        address.setState(state);
        addAddress(address);

        return address;
    }

    @Override
    public Address addAddress(Address address) throws GeneralAppException {

        MongoCollection<Document> dbAddresses = dataManager.getCollection("addresses");
        FindIterable<Document> iterable = null;

        MongoCursor<Document> cursor = null;
        try {
            iterable = dbAddresses.find();
            cursor = iterable.iterator();
            Document newAddress = new Document();
            newAddress.put("_id", new ObjectId());
            newAddress.put("city", address.getCity());
            newAddress.put("state", address.getState());
            dbAddresses.insertOne(newAddress);
        } finally {
            cursor.close();
        }

        return address;
    }

    @Override
    public Address getAddress(String addressId) throws GeneralAppException {
        AddressList addressList = getAllAddresses();
        Address address = getAddress(addressId, addressList);

        return address;
    }

    @Override
    public Address getAddress(String addressId, AddressList addressList) throws GeneralAppException {
        for (Address address: addressList.getAllAddresses()) {
            if (address != null && address.getId() != null && address.getId().equals(addressId)) {
                return address;
            }
        }

        return null;
    }

    @Override
    public void removeAddress(String addressId) throws GeneralAppException {
        MongoCollection<Document> dbAddresses = dataManager.getCollection("addresses");
        Bson filter = eq("_id", new ObjectId(addressId));
        dbAddresses.deleteOne(filter);
    }
}
