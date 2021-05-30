package com.moviesandchill.usermanagementservice.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

@TestConfiguration
public class TestConfig {
    @MockBean
    private RestTemplate restTemplate;
}
