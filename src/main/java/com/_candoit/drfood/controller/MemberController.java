package com._candoit.drfood.controller;

import com._candoit.drfood.domain.Member;
import com._candoit.drfood.domain.Risk;
import com._candoit.drfood.enums.UserDisease;
import com._candoit.drfood.req.LoginRequest;
import com._candoit.drfood.service.MemberService;
import com._candoit.drfood.service.RiskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    private final RiskService riskService;

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

    @PostMapping("/save-risk")
    public ResponseEntity<?> saveRisk(@RequestParam Long userId) {
        try {
             riskService.create(userId);
             return new ResponseEntity<>("Risk successfully saved!", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/login-info")
    public ResponseEntity<?> getLoginInfo(@RequestParam String loginId, @RequestParam String password) {
        try {
            Member member = memberService.getMemberInfo(loginId, password);
            return new ResponseEntity<>(member, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/saveDisease")
    public ResponseEntity<?> saveDisease(@RequestParam Long userId, @RequestParam UserDisease disease) {
        try {
            memberService.updateDisease(userId, disease);
            return new ResponseEntity<>("저장되었습니다!", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
