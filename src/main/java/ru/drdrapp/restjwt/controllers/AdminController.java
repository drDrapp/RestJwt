package ru.drdrapp.restjwt.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @GetMapping("/data")
    public ResponseEntity<String> dataAdminData() {
        return ResponseEntity.ok(">>>> Admin data <<<<");
    }

}