package org.innovativeapps.mongohoteldemo.data.provider;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import javax.ejb.Stateless;

@Stateless
public class DbConnection implements DbConnectionLocal {

    public static MongoDatabase dbConnection = null;
    private static final String MONGO_CREDENTIALS = "mongodb://famojuro:CR20110672@127.0.0.1:27017/?authSource=admin&&ssl=false";

    @Override
    public MongoDatabase getDbConnection() {
        if (dbConnection == null) {
            MongoClient mongoClient = MongoClients.create(MONGO_CREDENTIALS);
            dbConnection = mongoClient.getDatabase("javaguides");
        }

        return dbConnection;
    }
}
