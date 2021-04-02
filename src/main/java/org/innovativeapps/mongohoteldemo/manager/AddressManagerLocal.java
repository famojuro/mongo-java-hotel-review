package org.innovativeapps.mongohoteldemo.manager;

import org.innovativeapps.mongohoteldemo.model.Address;
import org.innovativeapps.mongohoteldemo.model.AddressList;
import org.innovativeapps.mongohoteldemo.util.exception.GeneralAppException;

import javax.ejb.Local;

@Local
public interface AddressManagerLocal {

    AddressList getAllAddresses() throws GeneralAppException;

    Address addAddress(Address address) throws GeneralAppException;

    Address addAddress(String city, String state) throws GeneralAppException;

    Address getAddress(String addressId) throws GeneralAppException;

    Address getAddress(String addressId, AddressList addressList) throws GeneralAppException;

    void removeAddress(String addressId) throws GeneralAppException;

}
