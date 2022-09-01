package com.ably.pre_task.controller;

import com.ably.pre_task.aop.LoginCheck;
import com.ably.pre_task.dto.request.AccountAddRequest;
import com.ably.pre_task.dto.request.AccountChangePasswordRequest;
import com.ably.pre_task.dto.response.AccountInfoResponse;
import com.ably.pre_task.service.AccountService;
import com.ably.pre_task.util.LoginUserId;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "유저 추가 API", notes = "유저 정보를 이용하여 유저 추가 성공 시 200 code 리턴")
    @PostMapping("/signUp")
    public ResponseEntity<Void> addAccount(@RequestBody @Valid AccountAddRequest request) {
        accountService.add(request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "유저 정보 조회 API", notes = "로그인 상태에서 유저 정보 조회 성공 시 유저 정보 리턴")
    @LoginCheck
    @GetMapping("/myInfo")
    public ResponseEntity<AccountInfoResponse> myInfo(@LoginUserId Long accountId) {
        AccountInfoResponse response = accountService.myInfo(accountId);

        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "유저 비밀번호 변경 조회 API", notes = "휴대폰 정보로 회원을 찾은 뒤 비밀번호 변경")
    @PutMapping("/change-password")
    public ResponseEntity<Void> changePassword(@RequestBody @Valid AccountChangePasswordRequest request) {
        accountService.changePassword(request);

        return ResponseEntity.noContent().build();
    }

}
