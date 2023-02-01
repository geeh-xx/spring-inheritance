package com.interview.spring.codelitt.webprovider;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.interview.spring.codelitt.dataprovider.entities.InformationEntity;
import com.interview.spring.codelitt.infrastructure.exception.ExternalDependencyException;
import com.interview.spring.codelitt.webprovider.client.CountryCurrencyClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
public abstract class InformationWebProvider {

    private String currency;
    private ObjectMapper mapper;
    private JsonNode node;
    private final CountryCurrencyClient client;
    private final MessageSource messageSource;

    public InformationEntity buildInformationByCountry(String country){

        try {
            ResponseEntity<String> currencyResponse = client.getCurrencyByCountry(country);

            if (currencyResponse.getStatusCode().is2xxSuccessful()){
                mapper = new ObjectMapper();
                node = mapper.readTree(currencyResponse.getBody());
                currency = node.findPath("currencies").fieldNames().next();
            }

            InformationEntity informationEntity = InformationEntity.builder().country(country).currency(currency).build();
            return informationEntity;
        }catch (Exception error){
            log.error(error.getMessage());
            throw new ExternalDependencyException("Error getting information about the inserted Country");
        }

    }

}
