package com.ably.pre_task.controller;

import com.ably.pre_task.dto.request.LoginRequest;
import com.ably.pre_task.service.AccountService;
import com.ably.pre_task.service.LoginService;
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

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest loginRequest, HttpSession session) {
        long accountId = accountService.findId(loginRequest);

        loginService.accountLogin(session, accountId);

        return ResponseEntity.ok().build();
    }

}
