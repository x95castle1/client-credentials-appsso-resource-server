package com.tappsso.resourceserver.web;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tappsso.resourceserver.model.Message;

/**
 * Handles requests to "/api" endpoints.
 * @see com.tappsso.resourceserver.security.SecurityConfig to see how these endpoints are protected.
 */
@RestController
@RequestMapping(path = "api", produces = MediaType.APPLICATION_JSON_VALUE)
// For simplicity of this sample, allow all origins. Real applications should configure CORS for their use case.
@CrossOrigin(origins = "*")
public class APIController {

    @GetMapping(value = "/public")
    public Message publicEndpoint(){
        return new Message("All good. You DO NOT need to be authenticated to call /api/public.");
    }

    @GetMapping(value = "/private")
    public Message privateEndpoint() {
        return new Message("All good. You can see this because you are Authenticated.");
    }

    @GetMapping(value = "/private-read")
    public Message privateReadScopedEndpoint() {
        return new Message("All good. You can see this because you are Authenticated with a Token granted the 'developer.read' scope");
    }

    @GetMapping(value = "/private-write")
    public Message privateWriteScopedEndpoint() {
        return new Message("All good. You can see this because you are Authenticated with a Token granted the 'developer.write' scope");
    }

    @GetMapping(value = "/private-admin")
    public Message privateAdminScopedEndpoint() {
        return new Message("All good. You can see this because you are Authenticated with a Token granted the 'developer.admin' scope");
    }
}
