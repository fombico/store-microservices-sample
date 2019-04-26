package com.fombico.resourceserver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Slf4j
@RestController
public class UserController {

    @RequestMapping("/user")
    public Principal user(Principal user) {
        log.info("User request {}", user);
        return user;
    }

    @RequestMapping("/api/cookies")
    public String cookies() {
        log.info("Getting cookies");
        return "chips-a-hoy!";
    }

}

