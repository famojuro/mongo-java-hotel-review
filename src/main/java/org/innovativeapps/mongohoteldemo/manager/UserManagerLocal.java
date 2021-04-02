package org.innovativeapps.mongohoteldemo.manager;

import org.innovativeapps.mongohoteldemo.model.User;
import org.innovativeapps.mongohoteldemo.model.UserList;
import org.innovativeapps.mongohoteldemo.util.exception.GeneralAppException;

import javax.ejb.Local;

@Local
public interface UserManagerLocal {

    UserList getAllUsers() throws GeneralAppException;

    User addUser(User user) throws GeneralAppException;

    User addUser(String userName, String lastName) throws GeneralAppException;

    User getUser(String userId, UserList userList) throws GeneralAppException;

    User getUser(String userId) throws GeneralAppException;

    void removeUser(String userId);
}
