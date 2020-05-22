package com.kchange.dao;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;


import java.util.concurrent.TimeUnit;

public class DBAccess {
    private static MongoClient mongoClient;
    private static DBAccess dbAccess;
    static Config config;
    static {
        config = ConfigProvider.getConfig();
    }



    private DBAccess() {
        String connectString = config.getValue("mongoConnectionLocalString", String.class);
        MongoClientSettings settings =
                MongoClientSettings.builder()
                        .applyToConnectionPoolSettings(builder ->
                                builder.maxSize(15).minSize(10).maxConnectionIdleTime(6000, TimeUnit.SECONDS))
                        .applyToClusterSettings(builder ->
                                builder.applyConnectionString(new ConnectionString(connectString)))
                        // .applyToSslSettings(builder -> builder.enabled(true))
                        .applyToSocketSettings(builder -> {
                            builder.connectTimeout(100, TimeUnit.SECONDS);
                            builder.readTimeout(100, TimeUnit.SECONDS);
                        })
                                        .build();
                        mongoClient = MongoClients.create(settings);
    }

    public static MongoClient getConnection() {

        if (dbAccess == null) {
            dbAccess = new DBAccess();
        }

        return mongoClient;
    }
    public static void closeDatabase() {
        mongoClient.close();
    }

}
