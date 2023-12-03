package com.pantrypalbackend.pantrypalbackend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.Response;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class HealthCheckController {
    @GetMapping
    public Response healthCheck() {
        return Response.ok().build();
    }
}
