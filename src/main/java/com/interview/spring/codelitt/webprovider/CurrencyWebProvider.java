package com.interview.spring.codelitt.webprovider;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.interview.spring.codelitt.dataprovider.entities.InformationEntity;
import com.interview.spring.codelitt.infrastructure.exception.ExternalDependencyException;
import com.interview.spring.codelitt.webprovider.client.CountryCurrencyClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CurrencyWebProvider {

    private ObjectMapper mapper;
    private JsonNode node;
    private final CountryCurrencyClient client;
    private final MessageSource messageSource;
    private static final String FINDINODE = "currencies";

    public String getCurrency(String country){

        try {
            String currencyResponse = client.getCurrencyByCountry(country);

            mapper = new ObjectMapper();
            node = mapper.readTree(currencyResponse);
            return node.findPath(FINDINODE).fieldNames().next();

        }catch (Exception error){
            log.error(error.getMessage());
            throw new ExternalDependencyException("Error getting information about the inserted Country");
        }

    }
}
