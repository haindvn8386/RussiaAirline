package rediscache.russiaairline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import rediscache.russiaairline.service.SessionService;

@RestController
@RequestMapping("/api/v1/sessions")
public class SessionController {
    @Autowired
    private SessionService sessionService;

    @PostMapping("/{userId}")
    public String saveSession(@PathVariable String userId, @RequestParam String token)
    {
        sessionService.saveSession(userId, token);
        return "Saved session for user: " + userId;
    }

    @GetMapping("/{userId}")
    public String getSession(@PathVariable String userId)
    {
        String token = sessionService.getSession(userId);
        return token!=null?token:"No session found for user: " + userId;
    }
}
