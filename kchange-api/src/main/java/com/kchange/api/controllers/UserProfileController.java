package com.kchange.api.controllers;

import com.kchange.services.UserProfileService;
import com.kchange.services.beans.UserProfile;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value="/UserProfile", description="User Profile", produces ="application/json")
@RestController
public class UserProfileController {

    @ApiImplicitParams(value = {@ApiImplicitParam(name = "userProfile", value = "", dataType = "com.kchange.services.beans.UserProfile")})
    @ApiOperation(value = "Create UserProfile", response = UserProfile.class)
    @PostMapping(value = "/UserProfile", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createUserProfile(@RequestBody UserProfile userProfile) throws Exception {
        UserProfileService userProfileService = new UserProfileService();
        boolean status = userProfileService.createUserProfile(userProfile);
        return ResponseEntity.ok("Customer Profile Created Successfully");
    }


    @ApiImplicitParams(value = {@ApiImplicitParam(name = "userProfile", value = "", dataType = "com.kchange.services.beans.UserProfile")})
    @ApiOperation(value = "Get UserProfile", response = UserProfile.class)
    @GetMapping(value = "/UserProfile", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getUserProfile(@RequestParam String userId) throws Exception {
        UserProfileService userProfileService = new UserProfileService();
        boolean status = userProfileService.getUserProfile(userId);
        return ResponseEntity.ok("Customer Profile Returned Successfully");
    }
}
