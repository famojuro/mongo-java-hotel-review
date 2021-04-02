package org.innovativeapps.mongohoteldemo.data.manager;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.innovativeapps.mongohoteldemo.data.provider.DbConnectionLocal;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class DataManager implements DataManagerLocal {

    @EJB
    private DbConnectionLocal dbConnection;

    @Override
    public MongoCollection<Document> getCollection(String collectionName) {
        MongoCollection<Document> documents = dbConnection.getDbConnection().getCollection(collectionName);

        return documents;
    }
}
