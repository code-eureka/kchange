package com.kchange.api.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
	
	@GetMapping(value= "/login", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> login() {
	    return ResponseEntity.ok("Logged in Successfully");
    }

}
