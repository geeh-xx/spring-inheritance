package com.interview.spring.codelitt.entrypoint;

import com.interview.spring.codelitt.entrypoint.dto.MemberDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("api/v1")
public class MemberController {


    @PostMapping(name = "/member")
    public ResponseEntity<?> createMember(@RequestBody MemberDTO memberDto){
        return null;
    }

}
