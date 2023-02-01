package com.interview.spring.codelitt.entrypoint;

import com.interview.spring.codelitt.entrypoint.dto.MemberDTO;
import com.interview.spring.codelitt.gateway.MemberGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import static org.springframework.http.HttpStatus.CREATED;

@ExtendWith(MockitoExtension.class)
public class MemberControllerTest {

    @Mock
    private MemberGateway memberGateway;

    private MemberController controller;
    private PodamFactory factory;

    @BeforeEach
    public void setUp(){
        controller = new MemberController(memberGateway);
    }

    @Test
    public void testCreateMember(){
        //given
        factory = new PodamFactoryImpl();
        MemberDTO memberDTO = factory.manufacturePojo(MemberDTO.class);
        Mockito.when(memberGateway.createMember(Mockito.any(MemberDTO.class))).thenReturn(memberDTO);

        //when
        ResponseEntity<MemberDTO> response = controller.createMember(memberDTO);

        //then
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(CREATED, response.getStatusCode());
    }
}
