package com.interview.spring.codelitt.usecase;

import com.interview.spring.codelitt.dataprovider.entities.EmployeeEntity;
import com.interview.spring.codelitt.dataprovider.entities.InformationEntity;
import com.interview.spring.codelitt.dataprovider.entities.inheritance.MemberEntity;
import com.interview.spring.codelitt.dataprovider.repository.EmployeeRepository;
import com.interview.spring.codelitt.entrypoint.dto.MemberDTO;
import com.interview.spring.codelitt.enums.MemberTypeEnum;
import com.interview.spring.codelitt.infrastructure.exception.MemberValidationException;
import com.interview.spring.codelitt.usecase.mapper.MemberMapper;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeUseCaseTest {

    @Mock
    private EmployeeRepository repository;
    @Mock
    private MemberMapper mapper;

    @Mock
    private InformationUseCase informationUseCase;

    @Mock
    private MessageSource messageSource;

    @Captor
    private ArgumentCaptor<EmployeeEntity> employeeCaptor;

    private PodamFactory mockFactory;

    private EmployeeUseCase useCase;

    @BeforeEach
    public void setUp(){
        useCase =  new EmployeeUseCase(repository, informationUseCase ,mapper, messageSource);
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
        InformationEntity informationEntity =  mockFactory.manufacturePojo(InformationEntity.class);


        when(informationUseCase.buildInformation(any())).thenReturn(informationEntity);
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
    void testFindById(){
        //given
        Random rand = new Random();
        Long id = rand.nextLong();

        mockFactory = new PodamFactoryImpl();
        EmployeeEntity entity = mockFactory.manufacturePojo(EmployeeEntity.class);
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

    @Test
    void checkParticularity(){
        //given

        //when
        Throwable thrown = assertThrows(MemberValidationException.class, () -> {
            useCase.checkParticularity(MemberTypeEnum.EMPLOYEE);
        });

        //then
        assertEquals("Error in Role", thrown.getMessage());

    }

}
