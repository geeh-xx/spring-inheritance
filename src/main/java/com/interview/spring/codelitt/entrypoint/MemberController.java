package com.interview.spring.codelitt.entrypoint;

import com.interview.spring.codelitt.entrypoint.dto.MemberDTO;
import com.interview.spring.codelitt.gateway.MemberGateway;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/member/{idMember}")
    public ResponseEntity<MemberDTO> getMemberByid(@PathVariable("idMember") Long idMember){
        return new ResponseEntity<>(memberGateway.findMemberById(idMember), OK);
    }

    @PutMapping(value = "/member")
    public ResponseEntity<MemberDTO> updateMember(@RequestBody @Valid MemberDTO memberDto){
        return new ResponseEntity<>(memberGateway.updateMember(memberDto), OK);
    }

    @DeleteMapping(value = "/member/{idMember}")
    public ResponseEntity<Void> deleteMember(@PathVariable("idMember") Long idMember){
        return ResponseEntity.ok().build();
    }

}
