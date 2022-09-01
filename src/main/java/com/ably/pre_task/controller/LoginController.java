package com.ably.pre_task.controller;

import com.ably.pre_task.dto.request.LoginRequest;
import com.ably.pre_task.service.AccountService;
import com.ably.pre_task.service.LoginService;
import io.swagger.annotations.ApiOperation;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private final AccountService accountService;

    @ApiOperation(value = "유저 로그인 조회 API", notes = "유저 이메일 및 비밀번호를 이용하여 로그인하여 세션에 로그인 정보 저장")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest loginRequest, HttpSession session) {
        long accountId = accountService.findId(loginRequest);

        loginService.accountLogin(session, accountId);

        return ResponseEntity.ok().build();
    }

}
