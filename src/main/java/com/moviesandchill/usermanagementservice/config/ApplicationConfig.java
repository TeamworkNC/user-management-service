package com.moviesandchill.usermanagementservice.config;


import com.moviesandchill.usermanagementservice.service.EsService;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@Configuration
public class ApplicationConfig {

    String usernameElastic;
    String passwordElastic;
    String hostElastic;
    Integer portElastic;

    @Autowired
    @Lazy
    private EsService esService;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public RestHighLevelClient esClient(){
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(usernameElastic, passwordElastic));
        RestHighLevelClient restClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost(hostElastic, portElastic,"https"))
                        .setHttpClientConfigCallback(httpAsyncClientBuilder ->
                                httpAsyncClientBuilder.setDefaultCredentialsProvider(credentialsProvider)
                                        .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())));
        return restClient;
    }

    @PostConstruct
    public void loadIndexUsers() throws Exception {
        esService.loadIndexUsers();
    }
}
