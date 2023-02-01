package com.interview.spring.codelitt.usecase;

import com.interview.spring.codelitt.dataprovider.entities.ContractorEntity;
import com.interview.spring.codelitt.dataprovider.entities.InformationEntity;
import com.interview.spring.codelitt.dataprovider.entities.inheritance.MemberEntity;
import com.interview.spring.codelitt.dataprovider.repository.InformationRepository;
import com.interview.spring.codelitt.webprovider.CurrencyWebProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.Optional;

import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InformationUseCaseTest {

    @Mock
    private InformationRepository repository;
    @Mock
    private CurrencyWebProvider webProvider;

    @Captor
    ArgumentCaptor<MemberEntity> memberCaptor;

    private PodamFactory mockFactory;
    private InformationUseCase useCase;


    @BeforeEach
    public void setUp(){
        useCase = new InformationUseCase(repository, webProvider);
    }

    @Test
    void testeBuildInformation(){

        //given
        mockFactory = new PodamFactoryImpl();
        MemberEntity entity = mockFactory.manufacturePojo(MemberEntity.class);
        String currency = "BRL";
        InformationEntity informationEntity = mockFactory.manufacturePojo(InformationEntity.class);
        informationEntity.setCurrency(currency);

        when(webProvider.getCurrency(anyString())).thenReturn(currency);
        when(repository.findByMember(any(MemberEntity.class))).thenReturn(of(informationEntity));

        //when
        InformationEntity infoSaved = useCase.buildInformation(entity);

        //then
        assertEquals(infoSaved.getCurrency(), currency);

    }
}
