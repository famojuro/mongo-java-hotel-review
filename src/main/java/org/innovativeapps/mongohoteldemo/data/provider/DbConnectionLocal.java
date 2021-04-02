package org.innovativeapps.mongohoteldemo.data.provider;

import com.mongodb.client.MongoDatabase;

import javax.ejb.Local;

@Local
public interface DbConnectionLocal {

    MongoDatabase getDbConnection();
}
