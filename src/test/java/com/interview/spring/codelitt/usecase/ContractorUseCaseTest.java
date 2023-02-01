package com.interview.spring.codelitt.usecase;

import com.interview.spring.codelitt.dataprovider.entities.ContractorEntity;
import com.interview.spring.codelitt.dataprovider.entities.InformationEntity;
import com.interview.spring.codelitt.dataprovider.entities.inheritance.MemberEntity;
import com.interview.spring.codelitt.dataprovider.repository.ContractorRepository;
import com.interview.spring.codelitt.entrypoint.dto.MemberDTO;
import com.interview.spring.codelitt.infrastructure.exception.MemberValidationException;
import com.interview.spring.codelitt.usecase.mapper.MemberMapper;
import com.interview.spring.codelitt.webprovider.CurrencyWebProvider;
import jakarta.persistence.EntityNotFoundException;
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

import java.util.Random;

import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ContractorUseCaseTest {

    @Mock
    private ContractorRepository repository;
    @Mock
    private  MemberMapper mapper;

    @Mock
    private CurrencyWebProvider webProvider;

    @Mock
    private MessageSource messageSource;

    @Captor
    ArgumentCaptor<ContractorEntity> contractorCaptor;

    private PodamFactory mockFactory;

    private ContractorUseCase useCase;

    @BeforeEach
    public void setUp(){
        useCase =  new ContractorUseCase(repository,mapper, webProvider, messageSource);
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
        InformationEntity informationEntity =  mockFactory.manufacturePojo(InformationEntity.class);

        when(webProvider.buildInformationByCountry(anyString())).thenReturn(informationEntity);
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
    void testFindById(){
        //given
        Random rand = new Random();
        Long id = rand.nextLong();

        mockFactory = new PodamFactoryImpl();
        ContractorEntity entity = mockFactory.manufacturePojo(ContractorEntity.class);
        entity.setIdMember(id);
        MemberDTO memberDTO = mockFactory.manufacturePojo(MemberDTO.class);
        memberDTO.setIdMember(id);


        when(repository.findById(anyLong())).thenReturn(of(entity));
        when(mapper.entityToDto(any(MemberEntity.class))).thenReturn(memberDTO);
        //when

        MemberDTO response = useCase.findById(id);

        //then

        assertNotNull(response);
        assertEquals(id, response.getIdMember());
        verify(repository, times(1)).findById(id);

    }

    @Test
    void testIdNotFound(){
        //given
        Random rand = new Random();
        Long id = rand.nextLong();

        //when
        Throwable thrown = assertThrows(EntityNotFoundException.class, () -> {
            useCase.findById(id);
        });

        //then
        assertEquals("Id not found", thrown.getMessage());
        verify(repository, times(1)).findById(id);
    }


}
