package ru.drdrapp.restjwt.controllers;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/data")
public class AuthorizationController {

    @GetMapping
    public ResponseEntity<String> getAuthorizedData(Response response) {
        return ResponseEntity.ok(">>>> Authorized data <<<<");
    }

}
