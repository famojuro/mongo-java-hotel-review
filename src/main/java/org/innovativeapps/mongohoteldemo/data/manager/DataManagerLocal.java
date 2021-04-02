package org.innovativeapps.mongohoteldemo.data.manager;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

import javax.ejb.Local;

@Local
public interface DataManagerLocal {

    MongoCollection<Document> getCollection(String collectionName);
}
