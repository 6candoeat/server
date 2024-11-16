package com._candoit.drfood.controller;

import com._candoit.drfood.domain.Member;
import com._candoit.drfood.req.LoginRequest;
import com._candoit.drfood.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/register")
    public ResponseEntity<?> registerMember(@RequestBody Member member) {
        memberService.calculateAndSaveMember(member);
        return new ResponseEntity<>("Member successfully registered!", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginMember(@RequestBody LoginRequest loginRequest) {
        try {
            Member member = memberService.login(loginRequest.getLoginId(), loginRequest.getPassword());
            return new ResponseEntity<>(member, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }
}
