package com.interview.spring.codelitt.entrypoint;

import com.interview.spring.codelitt.entrypoint.dto.MemberDTO;
import com.interview.spring.codelitt.gateway.MemberGateway;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MemberController {

    private final MemberGateway memberGateway;

    @PostMapping(value = "/member")
    public ResponseEntity<MemberDTO> createMember(@RequestBody @Valid MemberDTO memberDto){
         return new ResponseEntity<>(memberGateway.createMember(memberDto), CREATED);
    }

    @GetMapping(value = "/member")
    public ResponseEntity<List<MemberDTO>> getAllMembers(@RequestParam(value ="pageNumber", required = false) Integer pageNumber,
                                                         @RequestParam(value ="pagesize", required = false) Integer pagesize){
        return new ResponseEntity<>(memberGateway.findAll(pageNumber, pagesize), OK);
    }

    @GetMapping(value = "/member/{idMember}")
    public ResponseEntity<MemberDTO> getMemberByid(@PathVariable("idMember") Long idMember){
        return new ResponseEntity<>(memberGateway.findMemberById(idMember), OK);
    }

}
