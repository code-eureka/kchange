package com.kchange.services.beans;

import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

public class ProductProfile {
    String productProfileId;
    String userId;
    String linkedOrderId;
    String name;
    String description;
    List<String> keyWords;
    Category category;
    List<String> searchTags;

    public ProductProfile(String name, String description, List<String> keyWords, Category category) {
        this.name = name;
        this.description = description;
        this.keyWords = keyWords;
        this.category = category;
        this.productProfileId = UUID.randomUUID().toString();
        searchTags = new ArrayList<String>();
        searchTags.addAll(Arrays.asList(name.split(" ")));
        searchTags.addAll(keyWords.stream().filter(p-> !StringUtils.isEmpty(p)).filter(p->!searchTags.contains(p)).collect(Collectors.toList()));
        searchTags.addAll(Arrays.stream(name.split(" ")).filter(p-> !StringUtils.isEmpty(p)).filter(p->!searchTags.contains(p)).collect(Collectors.toList()));
        // searchTags.addAll(Arrays.stream(description.split(" ")).filter(p-> !StringUtils.isEmpty(p)).filter(p->!searchTags.contains(p)).collect(Collectors.toList()));
    }

    public String getProductProfileId() {
        return productProfileId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(List<String> keyWords) {
        this.keyWords = keyWords;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List getSearchTags() {
        return searchTags;
    }

    public String getLinkedOrderId() {
        return linkedOrderId;
    }

    public void setLinkedOrderId(String linkedOrderId) {
        this.linkedOrderId = linkedOrderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
