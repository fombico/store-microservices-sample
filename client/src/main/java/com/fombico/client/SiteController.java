package com.fombico.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestOperations;

@Slf4j
@Controller
public class SiteController {

    private final RestOperations restOperations;

    public SiteController(RestOperations restOperations) {
        this.restOperations = restOperations;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/hello")
    public String secured(Model model) {
        log.info("home");
        String user = restOperations.getForObject("http://localhost:9000/user", String.class);
        log.info("user: {}", user);
        String cookies = restOperations.getForObject("http://localhost:9000/api/cookies", String.class);
//        model.addAttribute("user", user);
        log.info("cookies: {}", cookies);

        model.addAttribute("cookies", "hello " + cookies);
        return "hello";
    }

}
