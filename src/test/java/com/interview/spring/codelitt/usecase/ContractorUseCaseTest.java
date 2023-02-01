package com.interview.spring.codelitt.usecase;

import com.interview.spring.codelitt.dataprovider.MemberRepository;
import com.interview.spring.codelitt.dataprovider.entities.ContractorEntity;
import com.interview.spring.codelitt.dataprovider.entities.inheritance.MemberEntity;
import com.interview.spring.codelitt.entrypoint.dto.MemberDTO;
import com.interview.spring.codelitt.infrastructure.exception.ExternalDependencyException;
import com.interview.spring.codelitt.infrastructure.exception.MemberValidationException;
import com.interview.spring.codelitt.usecase.mapper.MemberMapper;
import com.interview.spring.codelitt.webprovider.client.CountryCurrencyClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ContractorUseCaseTest {

    @Mock
    private  MemberRepository repository;
    @Mock
    private  MemberMapper mapper;

    @Mock
    private CountryCurrencyClient client;

    @Mock
    private MessageSource messageSource;

    @Captor
    ArgumentCaptor<ContractorEntity> contractorCaptor;

    private PodamFactory mockFactory;

    private ContractorUseCase useCase;

    @BeforeEach
    public void setUp(){
        useCase =  new ContractorUseCase(client, messageSource, repository, mapper);
    }

    @Test
     void testCreateContracor(){

        //given
        mockFactory = new PodamFactoryImpl();
        MemberDTO payload = mockFactory.manufacturePojo(MemberDTO.class);
        ContractorEntity contractorEntity = mockFactory.manufacturePojo(ContractorEntity.class);
        contractorEntity.setName(payload.getName());
        MemberEntity memberEntity = mockFactory.manufacturePojo(MemberEntity.class);
        memberEntity.setName(payload.getName());
        String currency = "[{\"currencies\": {\"BRL\": {\"name\": \"Brazilian real\",\"symbol\": \"R$\"}}}]";

        when(client.getCurrencyByCountry(anyString())).thenReturn(currency);
        when(mapper.dtoToEntity(any(MemberDTO.class))).thenReturn(memberEntity);
        when(mapper.entityToDto(any(MemberEntity.class))).thenReturn(payload);
        when(repository.save(any(ContractorEntity.class))).thenReturn(contractorEntity);

        //when
        MemberDTO memberDTO = useCase.create(payload);

        //then
        assertNotNull(memberDTO);
        assertNotNull(memberDTO.getIdMember());
        verify(repository, times(1)).save(contractorCaptor.capture());

        ContractorEntity entityCaptor = contractorCaptor.getAllValues().stream().findFirst().get();
        assertEquals(entityCaptor.getName(), payload.getName());
    }

    @Test
     void testFailContractTime(){

        //given
        mockFactory = new PodamFactoryImpl();
        MemberDTO payload = mockFactory.manufacturePojo(MemberDTO.class);
        payload.setContractDuration(0);

        //when
        Throwable thrown = assertThrows(MemberValidationException.class, () -> {
            useCase.create(payload);
        });

        //then
        assertEquals("Error in a contract duration", thrown.getMessage());
        verify(repository, times(0)).save(contractorCaptor.capture());

    }

    @Test
    void testFailInRequest(){

        //given
        mockFactory = new PodamFactoryImpl();
        MemberDTO payload = mockFactory.manufacturePojo(MemberDTO.class);
        String currency = "error";

        when(client.getCurrencyByCountry(anyString())).thenReturn(currency);


        //when
        Throwable thrown = assertThrows(ExternalDependencyException.class, () -> {
            useCase.create(payload);
        });

        //then
        assertEquals("Error getting information about the inserted Country", thrown.getMessage());
        verify(repository, times(0)).save(contractorCaptor.capture());

    }

}
