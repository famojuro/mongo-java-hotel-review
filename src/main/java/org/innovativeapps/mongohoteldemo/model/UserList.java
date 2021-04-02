package org.innovativeapps.mongohoteldemo.model;

import java.util.ArrayList;
import java.util.List;

public class UserList {

    private List<User> allUsers;

    public UserList() { allUsers = new ArrayList<>(); }

    public List<User> getAllUsers() { return allUsers; }

    public void setAllUsers(List<User> allUsers) { this.allUsers.addAll(allUsers); }
}
