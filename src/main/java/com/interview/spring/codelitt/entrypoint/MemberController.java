package com.interview.spring.codelitt.entrypoint;

import com.interview.spring.codelitt.entrypoint.dto.MemberDTO;
import com.interview.spring.codelitt.gateway.MemberGateway;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MemberController {

    private final MemberGateway memberGateway;

    @PostMapping(value = "/member")
    public ResponseEntity<MemberDTO> createMember(@RequestBody @Valid MemberDTO memberDto){
         return new ResponseEntity<>(memberGateway.createMember(memberDto), CREATED);
    }

}
