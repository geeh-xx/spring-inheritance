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

import java.util.Random;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@ExtendWith(MockitoExtension.class)
class MemberControllerTest {

    @Mock
    private MemberGateway memberGateway;

    private MemberController controller;
    private PodamFactory factory;

    @BeforeEach
    public void setUp(){
        controller = new MemberController(memberGateway);
    }

    @Test
    void testCreateMember(){
        //given
        factory = new PodamFactoryImpl();
        MemberDTO memberDTO = factory.manufacturePojo(MemberDTO.class);
        Mockito.when(memberGateway.createMember(any(MemberDTO.class))).thenReturn(memberDTO);

        //when
        ResponseEntity<MemberDTO> response = controller.createMember(memberDTO);

        //then
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(CREATED, response.getStatusCode());
    }

    @Test
    void testGetMemberByid(){
        //given
        Random random = new Random();
        Long id = random.nextLong();

        factory = new PodamFactoryImpl();
        MemberDTO memberDTO = factory.manufacturePojo(MemberDTO.class);
        Mockito.when(memberGateway.findMemberById(anyLong())).thenReturn(memberDTO);

        //when
        ResponseEntity<MemberDTO> response = controller.getMemberByid(id);

        //then
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(OK, response.getStatusCode());
    }

    @Test
    void testUpdateMember(){
        //given
        factory = new PodamFactoryImpl();
        MemberDTO memberDTO = factory.manufacturePojo(MemberDTO.class);
        Mockito.when(memberGateway.updateMember(any(MemberDTO.class))).thenReturn(memberDTO);

        //when
        ResponseEntity<MemberDTO> response = controller.updateMember(memberDTO);

        //then
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(OK, response.getStatusCode());
    }

    @Test
    void testDeleteMember(){
        //given
        Random random = new Random();
        Long id = random.nextLong();

        //when
        ResponseEntity<Void> responseEntity = controller.deleteMember(id);

        //then
        Mockito.verify(memberGateway, Mockito.times(1)).deleteMemberById(id);
        Assertions.assertEquals(OK, responseEntity.getStatusCode());
    }
}
