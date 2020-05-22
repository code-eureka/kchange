package com.kchange.services;

import com.google.gson.Gson;
import com.kchange.dao.CollectionManager;
import com.kchange.services.beans.UserProfile;

public class UserProfileService {

    public boolean createUserProfile(UserProfile customer) throws Exception{
        Gson gsonParser = new Gson();
        String customerProfile = gsonParser.toJson(customer);
        return CollectionManager.saveUserProfile(customerProfile);
    }

    public boolean getUserProfile(String userId) throws Exception{
        Gson gsonParser = new Gson();
        return CollectionManager.findUserProfile(userId);
    }
}
