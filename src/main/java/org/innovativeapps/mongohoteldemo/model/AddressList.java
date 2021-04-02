package org.innovativeapps.mongohoteldemo.model;

import java.util.ArrayList;
import java.util.List;

public class AddressList {
    private List<Address> allAddresses;

    public AddressList() { allAddresses = new ArrayList<>(); }

    public List<Address> getAllAddresses() { return allAddresses; }

    public void setAllAddresses(List<Address> allAddresses) { this.allAddresses.addAll(allAddresses); }
}
