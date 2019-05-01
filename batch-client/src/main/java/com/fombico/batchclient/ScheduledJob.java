package com.fombico.batchclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;

@Slf4j
@Component
public class ScheduledJob {

    private static final int THIRTY_SECONDS = 30_000;
    private final RestOperations restOperations;

    public ScheduledJob(RestOperations restOperations) {
        this.restOperations = restOperations;
    }

    @Scheduled(fixedRate = THIRTY_SECONDS)
    void scheduled() {
        String cookies = restOperations.getForObject("http://localhost:9000/api/cookies", String.class);
        log.info("Found cookies: {}", cookies);
    }
}
