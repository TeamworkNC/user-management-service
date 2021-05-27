package com.moviesandchill.usermanagementservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moviesandchill.usermanagementservice.dto.user.UserDto;
import com.moviesandchill.usermanagementservice.exception.user.UserNotFoundException;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RegexpQueryBuilder;
import org.elasticsearch.index.query.TermsQueryBuilder;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.index.reindex.DeleteByQueryRequestBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class EsService {

    private RestHighLevelClient esClient;
    UserService userService;

    @Autowired
    public EsService(RestHighLevelClient esClient, UserService userService) {
        this.esClient = esClient;
        this.userService = userService;
    }

    public void loadIndexUsers() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        List<UserDto> userList = userService.getAllUsers();
        if(esClient.indices().exists(new GetIndexRequest("users"), RequestOptions.DEFAULT)){
            DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("users");
            esClient.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
        }
        esClient.indices().create(new CreateIndexRequest("users"),RequestOptions.DEFAULT);
        for(UserDto user : userList){
            IndexRequest indexRequest = new IndexRequest("users");
            indexRequest.id(UUID.randomUUID().toString());
            indexRequest.source(objectMapper.writeValueAsString(user), XContentType.JSON);
            esClient.index(indexRequest, RequestOptions.DEFAULT);
        }
    }

    public void loadUser(Long userId) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        UserDto user = userService.getUser(userId);
        IndexRequest indexRequest = new IndexRequest("users");
        indexRequest.id(UUID.randomUUID().toString());
        indexRequest.source(objectMapper.writeValueAsString(user), XContentType.JSON);
        esClient.index(indexRequest, RequestOptions.DEFAULT);
    }

    public void deleteUser(Long userId) throws Exception {
        SearchRequest searchRequest = new SearchRequest("users");;
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        queryBuilder.must(QueryBuilders.multiMatchQuery(userId,"userId"));
        searchRequest.source(new SearchSourceBuilder().query(queryBuilder));
        SearchResponse response = esClient.search(searchRequest, RequestOptions.DEFAULT);
        for(SearchHit hit : response.getHits()){
            String id = hit.getId();
            esClient.deleteAsync(new DeleteRequest("users",id),RequestOptions.DEFAULT,null);
        }
    }

    public List<UserDto> search(String searchString) throws IOException, UserNotFoundException {
        SearchRequest searchRequest = new SearchRequest("users");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder qb = QueryBuilders.boolQuery()
                .should(new RegexpQueryBuilder("login",searchString+ ".*"))
                .should(QueryBuilders.multiMatchQuery(searchString,"login")
                        .fuzziness(Fuzziness.AUTO));
        sourceBuilder.query(qb);

        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = esClient.search(searchRequest, RequestOptions.DEFAULT);
        List<UserDto> userDtoList = new ArrayList<>();
        for(SearchHit searchHit : searchResponse.getHits().getHits()){
            Map<String,Object> sourse = searchHit.getSourceAsMap();
            UserDto userDto = userService.getUser(Long.valueOf((int) sourse.get("userId")));
            userDtoList.add(userDto);
        }
        return userDtoList;
    }
}
