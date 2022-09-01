package com.ably.pre_task.controller;

import com.ably.pre_task.aop.LoginCheck;
import com.ably.pre_task.dto.request.AccountAddRequest;
import com.ably.pre_task.dto.request.AccountChangePasswordRequest;
import com.ably.pre_task.dto.response.AccountInfoResponse;
import com.ably.pre_task.service.AccountService;
import com.ably.pre_task.util.LoginUserId;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/signUp")
    public ResponseEntity<Void> addAccount(@RequestBody @Valid AccountAddRequest request) {
        accountService.add(request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @LoginCheck
    @GetMapping("/myInfo")
    public ResponseEntity<AccountInfoResponse> myInfo(@LoginUserId Long accountId) {
        AccountInfoResponse response = accountService.myInfo(accountId);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/change-password")
    public ResponseEntity<Void> changePassword(AccountChangePasswordRequest request) {
        accountService.changePassword(request);

        return ResponseEntity.noContent().build();
    }

}
