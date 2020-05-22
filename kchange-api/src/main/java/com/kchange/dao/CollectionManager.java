package com.kchange.dao;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.push;


public class CollectionManager  {


    public static boolean saveUserProfile(String userProfile) throws Exception{
        MongoClient client = DBAccess.getConnection();
        MongoDatabase database = client.getDatabase("kchange");
        database.getCollection("UserProfile").insertOne(Document.parse(userProfile));
        return true;
    }

    public static boolean findUserProfile(String userId) throws Exception{
        MongoClient client = DBAccess.getConnection();
        MongoDatabase database = client.getDatabase("kchange");
        FindIterable<Document> findIterable = database.getCollection("UserProfile").find(eq("userId", "userId"));
        return true;
    }

    public static boolean saveOrderDetail(String orderDetail) throws Exception{
        MongoClient client = DBAccess.getConnection();
        MongoDatabase database = client.getDatabase("kchange");
        database.getCollection("OrderDetail").insertOne(Document.parse(orderDetail));
        return true;
    }
    public static boolean createAvailableProduct(String availableProduct) {
        MongoClient client = DBAccess.getConnection();
        MongoDatabase database = client.getDatabase("kchange");
        database.getCollection("AvailableProducts").insertOne(Document.parse(availableProduct));
        return true;
    }

    public static boolean createRequiredProduct(String requiredProducts) {
        MongoClient client = DBAccess.getConnection();
        MongoDatabase database = client.getDatabase("kchange");
        database.getCollection("RequiredProducts").insertOne(Document.parse(requiredProducts));
        return true;
    }

    public static boolean findMatchingProductAndUpdateOrder(String product, String orderId) {
        MongoClient client = DBAccess.getConnection();
        MongoDatabase database = client.getDatabase("kchange");
        Block<Document> printBlock = new Block<Document>() {
            @Override
            public void apply(final Document document) {
                    database.getCollection("OrderDetail").updateOne(
                        eq("orderId", "36ddc207-7077-4fbf-8352-6a5860532604"),push("matchedProducts",document));
            }
        };

        FindIterable<Document> findIterable = database.getCollection("RequiredProducts").find(eq("category", "BOOKS"));
        findIterable.forEach(printBlock);
        return true;
    }

    public static List<String> findMatchingProduct(List<String> categoryList, List<String> searchTags, String collectionName) {
        MongoClient client = DBAccess.getConnection();
        MongoDatabase database = client.getDatabase("kchange");
        List<String> matchingAvailableProducts = new ArrayList<>();
        FindIterable<Document> findIterable = database.getCollection(collectionName).find(and(in("category",categoryList),in("searchTags", searchTags)));
        findIterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                matchingAvailableProducts.add(document.toJson());
            }
        });
        return matchingAvailableProducts;
    }

    public static List<String> findMatchingProductIds(List<String> categoryList, List<String> searchTags, String collectionName) {
        MongoClient client = DBAccess.getConnection();
        MongoDatabase database = client.getDatabase("kchange");
        List<String> matchingAvailableProducts = new ArrayList<>();
        FindIterable<Document> findIterable = database.getCollection(collectionName).find(and(in("category",categoryList),in("searchTags", searchTags)));
        findIterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                matchingAvailableProducts.add(document.getString("productProfileId"));
            }
        });
        return matchingAvailableProducts;
    }


    public static void findMatchingOrdersAndUpdate(String availableProductJson, List<String> linkedOrderIds, String userId) {
        MongoClient client = DBAccess.getConnection();
        MongoDatabase database = client.getDatabase("kchange");
        database.getCollection("OrderDetail").updateMany(and(in("orderId", linkedOrderIds), ne("userId", userId)),push("matchedProducts", Document.parse(availableProductJson)));
    }

    public static List<String> findOrdersWithNoMatches() {
        MongoClient client = DBAccess.getConnection();
        MongoDatabase database = client.getDatabase("kchange");
        List<String> unMatchedOrders = new ArrayList<>();
        FindIterable<Document> findIterable = database.getCollection("OrderDetail").
                find(exists("matchedProducts", false));
        findIterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                unMatchedOrders.add(document.getString("orderId"));
            }
        });
        return unMatchedOrders;
    }
}
