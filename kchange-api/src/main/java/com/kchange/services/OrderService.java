package com.kchange.services;

import com.google.gson.Gson;
import com.kchange.dao.CollectionManager;

import com.kchange.services.beans.OrderDetail;
import com.kchange.services.beans.ProductProfile;
import com.kchange.services.kafka.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    Producer kafkaProducer ;

    private final Logger logger = LoggerFactory.getLogger(OrderService.class);


    public boolean createOrderDetail(OrderDetail orderDetail) throws Exception{
        Gson gsonParser = new Gson();
        List<String> categoryList = new ArrayList<>();
        List<String> searchTagList = new ArrayList<>();
        Arrays.stream(orderDetail.getRequiredProducts()).forEach(p->{
            if (!categoryList.contains(p.getCategory().name())) {
                categoryList.add(p.getCategory().name());
            }
            searchTagList.addAll(p.getSearchTags());
        });
        List<String> matchingAvailableProducts = CollectionManager.
                findMatchingProduct( categoryList, searchTagList,"AvailableProducts");
        if (matchingAvailableProducts!= null && !matchingAvailableProducts.isEmpty()) {
            ProductProfile[] productProfileArr = new ProductProfile[1];
            productProfileArr = matchingAvailableProducts.stream().map(prd -> gsonParser.fromJson(prd, ProductProfile.class)).collect(Collectors.toList()).toArray(productProfileArr);
            orderDetail.setMatchedProducts(productProfileArr);
        }




        String orderDetailJson = gsonParser.toJson(orderDetail);
        if (CollectionManager.saveOrderDetail(orderDetailJson)) {
            orderDetail.getAvailableProduct().setUserId(orderDetail.getUserProfile().getUserId());
            CollectionManager.createAvailableProduct(gsonParser.toJson(orderDetail.getAvailableProduct()));
            Arrays.stream(orderDetail.getRequiredProducts()).map(e-> {
                e.setUserId(orderDetail.getUserProfile().getUserId());
                return e;
            }).map(e->gsonParser.toJson(e)).forEach(
                    e-> CollectionManager.createRequiredProduct(e)
            );
        }
        new Thread(()->{notifyNewAvailableProduct(orderDetail.getAvailableProduct());}).start();
        return true;
    }

    public void notifyNewAvailableProduct(ProductProfile availableProduct) {
        Gson gsonParser = new Gson();
        kafkaProducer.sendMessage("available_products", gsonParser.toJson(availableProduct));
    }

    @KafkaListener(topics = "available_products", groupId = "available_product_group")
    public void consume(String message){
        Gson gsonParser = new Gson();
        ProductProfile availableProduct = gsonParser.fromJson(message,ProductProfile.class);
        List<String> availableCategoryList = new ArrayList<>();
        List<String> availableSearchTagList = new ArrayList<>();
        availableCategoryList.add(availableProduct.getCategory().name());
        availableSearchTagList.addAll(availableProduct.getSearchTags());

        List<String> matchingRequiredProducts = CollectionManager.
                findMatchingProduct( availableCategoryList, availableSearchTagList,"RequiredProducts");
        List<String> linkedOrderIds = matchingRequiredProducts.stream().map(prd->gsonParser.fromJson(prd, ProductProfile.class)).map(e->e.getLinkedOrderId()).collect(Collectors.toList());
        //Update MatchedRequiredProducts
        if (!linkedOrderIds.isEmpty()) {
            CollectionManager.findMatchingOrdersAndUpdate(gsonParser.toJson(availableProduct), linkedOrderIds, availableProduct.getUserId());
        }
    }
    public boolean uploadImage(UUID imageId, MultipartFile mpfile) {
        return true;
    }

    public List<String> fetchOrdersWithNoMatches() {
        return CollectionManager.findOrdersWithNoMatches();
    }
}
