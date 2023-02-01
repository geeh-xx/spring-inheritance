package com.interview.spring.codelitt.webprovider.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "countryCurrency", url = "https://restcountries.com/v3.1/name")
public interface CountryCurrencyClient {


    @GetMapping(value = "/{country}")
    ResponseEntity<String> getCurrencyByCountry(@PathVariable("country") String country);

}
