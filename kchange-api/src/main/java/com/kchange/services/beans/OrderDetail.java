package com.kchange.services.beans;

import org.joda.time.DateTime;

import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

public class OrderDetail {
    String orderId;
    UserProfile userProfile;
    ProductProfile availableProduct;
    ProductProfile [] requiredProducts;
    ProductProfile [] matchedProducts;
    String searchCity;
    String [] searchZipCodes;
    String searchRadius;
    Date creationTimeStamp;
    Date modifiedTimeStamp;
    Status status;

    public OrderDetail(UserProfile userProfile, ProductProfile availableProduct, ProductProfile[] requiredProducts) {
        this.userProfile = userProfile;
        this.availableProduct = availableProduct;
        this.requiredProducts = requiredProducts;
        orderId = UUID.randomUUID().toString();
        availableProduct.setLinkedOrderId(orderId);
        Arrays.stream(requiredProducts).forEach(e->e.setLinkedOrderId(orderId));
        creationTimeStamp = DateTime.now().toDate();
        modifiedTimeStamp = DateTime.now().toDate();
        status = Status.ACTIVE;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public ProductProfile getAvailableProduct() {
        return availableProduct;
    }

    public void setAvailableProduct(ProductProfile availableProduct) {
        this.availableProduct = availableProduct;
    }

    public ProductProfile[] getRequiredProducts() {
        return requiredProducts;
    }

    public void setRequiredProducts(ProductProfile[] requiredProducts) {
        this.requiredProducts = requiredProducts;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public ProductProfile[] getMatchedProducts() {
        return matchedProducts;
    }

    public void setMatchedProducts(ProductProfile[] matchedProducts) {
        this.matchedProducts = matchedProducts;
    }


    public Date getCreationTimeStamp() {
        return creationTimeStamp;
    }

    public Date getModifiedTimeStamp() {
        return modifiedTimeStamp;
    }

    public Status getStatus() {
        return status;
    }

    public String getSearchCity() {
        return searchCity;
    }

    public void setSearchCity(String searchCity) {
        this.searchCity = searchCity;
    }

    public String[] getSearchZipCodes() {
        return searchZipCodes;
    }

    public void setSearchZipCodes(String[] searchZipCodes) {
        this.searchZipCodes = searchZipCodes;
    }

    public String getSearchRadius() {
        return searchRadius;
    }

    public void setSearchRadius(String searchRadius) {
        this.searchRadius = searchRadius;
    }
}
