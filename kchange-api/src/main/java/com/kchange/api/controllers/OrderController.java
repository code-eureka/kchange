package com.kchange.api.controllers;

import com.kchange.services.OrderService;
import com.kchange.services.beans.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @CrossOrigin
    @PostMapping(value= "/OrderDetail", consumes = "application/json", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createOrderDetail(@RequestBody OrderDetail orderDetails) throws Exception {

        boolean status = orderService.createOrderDetail(orderDetails);
        return ResponseEntity.ok("Order Detail Created Successfully");
    }



    @PostMapping(path="/OrderDetail/upload/{imageId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> uploadOrderImage(@PathVariable String imageId,
                                                   @RequestParam MultipartFile file) throws Exception {
        OrderService orderService = new OrderService();
        boolean status = orderService.uploadImage(UUID.fromString(imageId), file);
        return ResponseEntity.ok("Image Uploaded  Successfully");
    }
}
