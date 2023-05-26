package ru.drdrapp.restjwt.controllers;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthorizationController {

    @GetMapping("/data")
    public ResponseEntity<String> getAuthorizedData() {
        return ResponseEntity.ok(">>>> Authorized data <<<<");
    }

    @GetMapping("/admin")
    public ResponseEntity<String> dataAdminData() {
        return ResponseEntity.ok(">>>> Admin data <<<<");
    }

}
