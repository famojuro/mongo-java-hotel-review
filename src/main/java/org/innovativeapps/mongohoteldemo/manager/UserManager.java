package org.innovativeapps.mongohoteldemo.manager;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.set;

import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.innovativeapps.mongohoteldemo.data.manager.DataManagerLocal;
import org.innovativeapps.mongohoteldemo.model.User;
import org.innovativeapps.mongohoteldemo.model.UserConstant;
import org.innovativeapps.mongohoteldemo.model.UserList;
import org.innovativeapps.mongohoteldemo.util.exception.GeneralAppException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class UserManager implements UserManagerLocal{

    @EJB
    DataManagerLocal dataManager;

    @Override
    public UserList getAllUsers() throws GeneralAppException {
        UserList userList = new UserList();
        MongoCollection<Document> users = dataManager.getCollection("users");

        List<User> prepareAllUsers = prepareAllUsers(users);
        userList.setAllUsers(prepareAllUsers);

        return userList;
    }

    private List<User> prepareAllUsers(MongoCollection<Document> users) throws GeneralAppException {
        List<User> userList = new ArrayList<>();
        FindIterable<Document> iterable = users.find();

        MongoCursor<Document> cursor = iterable.iterator();
        try {
            while (cursor.hasNext()) {
                Document user = cursor.next();
                User preparedUser = mapUserFromDocument(user);
                userList.add(preparedUser);
            }
        } finally {
            cursor.close();
        }

        return userList;
    }

    private User mapUserFromDocument(Document user) throws GeneralAppException {
        User mappedUser = new User();
        if (user != null) {
            mappedUser.setId(user.getObjectId(UserConstant.ID).toString());
            mappedUser.setUserName(user.getString(UserConstant.USER_NAME));
            mappedUser.setLastName(user.getString(UserConstant.LAST_NAME));
        }

        return mappedUser;
    }

    @Override
    public User addUser(String userName, String lastName) throws GeneralAppException {

        User user = new User();
        user.setUserName(userName);
        user.setLastName(lastName);
        addUser(user);

        return user;
    }

    @Override
    public User addUser(User user) throws GeneralAppException {

        MongoCollection<Document> dbUsers = dataManager.getCollection("users");
        FindIterable<Document> iterable = null;

        MongoCursor<Document> cursor = null;
        try {
            iterable = dbUsers.find();
            cursor = iterable.iterator();
            Document newUser = new Document();
            newUser.put("_id", new ObjectId());
            newUser.put("userName", user.getUserName());
            newUser.put("lastName", user.getLastName());
            dbUsers.insertOne(newUser);
        } finally {
            cursor.close();
        }

        return user;
    }

    @Override
    public User getUser(String userId, UserList userList) throws GeneralAppException {
        for (User user : userList.getAllUsers()) {
            if (user != null && user.getId() != null && user.getId().equals(userId)) {
                return user;
            }
        }

        return null;
    }

    @Override
    public User getUser(String userId) throws GeneralAppException {
        UserList userList = getAllUsers();
        User user = getUser(userId, userList);

        return user;
    }

    @Override
    public void removeUser(String userId) {
        MongoCollection<Document> dbUsers = dataManager.getCollection("users");
        Bson filter = eq("_id", new ObjectId(userId));
        dbUsers.deleteOne(filter);

    }
}
