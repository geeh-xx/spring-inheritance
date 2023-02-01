package com.interview.spring.codelitt.usecase;

import com.interview.spring.codelitt.dataprovider.entities.EmployeeEntity;
import com.interview.spring.codelitt.dataprovider.repository.EmployeeRepository;
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
public class EmployeeUseCaseTest {

    @Mock
    private EmployeeRepository repository;
    @Mock
    private MemberMapper mapper;

    @Mock
    private CountryCurrencyClient client;

    @Mock
    private MessageSource messageSource;

    @Captor
    private ArgumentCaptor<EmployeeEntity> employeeCaptor;

    private PodamFactory mockFactory;

    private EmployeeUseCase useCase;

    @BeforeEach
    public void setUp(){
        useCase =  new EmployeeUseCase(repository, mapper, client, messageSource);
    }

    @Test
    void testCreateContracor(){

        //given
        mockFactory = new PodamFactoryImpl();
        MemberDTO payload = mockFactory.manufacturePojo(MemberDTO.class);
        EmployeeEntity employeeEntity = mockFactory.manufacturePojo(EmployeeEntity.class);
        employeeEntity.setName(payload.getName());
        MemberEntity memberEntity = mockFactory.manufacturePojo(MemberEntity.class);
        memberEntity.setName(payload.getName());
        String currency = "[{\"currencies\": {\"BRL\": {\"name\": \"Brazilian real\",\"symbol\": \"R$\"}}}]";

        when(client.getCurrencyByCountry(anyString())).thenReturn(currency);
        when(mapper.dtoToEntity(any(MemberDTO.class))).thenReturn(memberEntity);
        when(mapper.entityToDto(any(MemberEntity.class))).thenReturn(payload);
        when(repository.save(any(EmployeeEntity.class))).thenReturn(employeeEntity);

        //when
        MemberDTO memberDTO = useCase.create(payload);

        //then
        assertNotNull(memberDTO);
        assertNotNull(memberDTO.getIdMember());
        verify(repository, times(1)).save(employeeCaptor.capture());

        EmployeeEntity entityCaptor = employeeCaptor.getAllValues().stream().findFirst().get();
        assertEquals(entityCaptor.getName(), payload.getName());
    }

    @Test
    void testFailContractTime(){

        //given
        mockFactory = new PodamFactoryImpl();
        MemberDTO payload = mockFactory.manufacturePojo(MemberDTO.class);
        payload.setRole(null);

        //when
        Throwable thrown = assertThrows(MemberValidationException.class, () -> {
            useCase.create(payload);
        });

        //then
        assertEquals("Error in Role", thrown.getMessage());
        verify(repository, times(0)).save(employeeCaptor.capture());

    }

    @Test
    void testFailInRequest(){

        //given
        mockFactory = new PodamFactoryImpl();
        MemberDTO payload = mockFactory.manufacturePojo(MemberDTO.class);
        String currency = "fail";

        when(client.getCurrencyByCountry(anyString())).thenReturn(currency);


        //when
        Throwable thrown = assertThrows(ExternalDependencyException.class, () -> {
            useCase.create(payload);
        });

        //then
        assertEquals("Error getting information about the inserted Country", thrown.getMessage());
        verify(repository, times(0)).save(employeeCaptor.capture());

    }

}
