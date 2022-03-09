package com.spot.task.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spot.task.services.response.BinListResponse;
import com.spot.task.services.response.Country;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.lang.reflect.Field;
import java.util.function.Function;

@Service
public class MyService {

    private final WebClient webClient;

    public MyService(WebClient.Builder webClient) {
        this.webClient = webClient.baseUrl("https://lookup.binlist.net").build();
    }

    public Integer getCountryCode(String number){
        Mono<BinListResponse> binListResponseMono = this.webClient.get().uri("/" + number).retrieve().bodyToMono(BinListResponse.class);
        BinListResponse binListResponse = binListResponseMono.block();
        Country country = binListResponse.getCountry();
        if (country != null) {
            String numeric = country.getNumeric();
            return Integer.valueOf(numeric);
        }
        return null;
    }
}
