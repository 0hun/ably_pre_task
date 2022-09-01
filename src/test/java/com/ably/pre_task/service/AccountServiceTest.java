package com.ably.pre_task.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import com.ably.pre_task.domain.Account;
import com.ably.pre_task.dto.request.AccountAddRequest;
import com.ably.pre_task.dto.request.AccountChangePasswordRequest;
import com.ably.pre_task.dto.response.AccountInfoResponse;
import com.ably.pre_task.exception.AccountNotFoundException;
import com.ably.pre_task.exception.SmsNumberNotMatchException;
import com.ably.pre_task.repository.AccountRepository;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @InjectMocks
    private AccountService accountService;

    @Mock
    private ConsoleSmsService consoleSmsService;

    @Mock
    AccountRepository accountRepository;
    @Mock
    PasswordEncoder passwordEncoder;

    @DisplayName("account 추가 테스트")
    @Test
    void addAccountTest() {
        //given
        final AccountAddRequest request = AccountAddRequest.builder()
                .email("whdudgns2654@gmail.com")
                .nickname("ddd")
                .password("asdkjlsad1123!@")
                .name("jgsds")
                .phone("010-1234-1234")
                .build();

        given(accountRepository.existsByEmail(request.getEmail())).willReturn(false);
        given(passwordEncoder.encode(request.getPassword())).willReturn("asdasd");

        //when
        accountService.add(request);

        //then
        verify(accountRepository).save(any());
    }

    @DisplayName("account 비밀번호 변경 테스트")
    @Test
    void accountChangePasswordTest() {
        //given
        final AccountAddRequest accountAddRequest = AccountAddRequest.builder()
                .email("whdudgns2654@gmail.com")
                .nickname("ddd")
                .password("asdkjlsad1123!@")
                .name("jgsds")
                .phone("010-1234-1234")
                .build();

        Account account = accountAddRequest.toEntity();

        final AccountChangePasswordRequest request = AccountChangePasswordRequest.builder()
                .newPassword("asdkjlsad114!@")
                .phone("010-1234-1234")
                .build();

        given(accountRepository.findByPhone(request.getPhone()))
                .willReturn(Optional.of(account));

        given(passwordEncoder.encode(request.getNewPassword()))
                .willReturn("asdasd");

        doNothing().when(consoleSmsService).sendSms(request.getPhone());

        //when
        //then
        accountService.changePassword(request);
    }

    @DisplayName("account 비밀번호 변경 시 - sms 인증 실패 테스트")
    @Test
    void accountChangePasswordWithSmsFailTest() {
        //given
        final AccountAddRequest accountAddRequest = AccountAddRequest.builder()
                .email("whdudgns2654@gmail.com")
                .nickname("ddd")
                .password("asdkjlsad1123!@")
                .name("jgsds")
                .phone("010-1234-1234")
                .build();

        Account account = accountAddRequest.toEntity();

        final AccountChangePasswordRequest request = AccountChangePasswordRequest.builder()
                .newPassword("asdkjlsad114!@")
                .phone("010-1234-1234")
                .build();

        given(accountRepository.findByPhone(request.getPhone()))
                .willReturn(Optional.of(account));

        doThrow(SmsNumberNotMatchException.exception)
                .when(consoleSmsService)
                .sendSms(request.getPhone());

        //when
        Throwable thrown = catchThrowable(() -> accountService.changePassword(request));

        //then
        assertThat(thrown).isInstanceOf(SmsNumberNotMatchException.class);
    }

    @DisplayName("account 정보 조회 테스트")
    @Test
    void accountInfoTest() {
        //given
        final AccountAddRequest request = AccountAddRequest.builder()
                .email("whdudgns2654@gmail.com")
                .nickname("ddd")
                .password("asdkjlsad1123!@")
                .name("jgsds")
                .phone("010-1234-1234")
                .build();

        given(accountRepository.findById(1L)).willReturn(Optional.of(request.toEntity()));

        //when
        AccountInfoResponse response = accountService.myInfo(1L);

        //then
        assertThat(response.getEmail()).isEqualTo(request.getEmail());
    }

    @DisplayName("account 정보 조회 실패 테스트")
    @Test
    void accountInfoFailTest() {
        //given
        given(accountRepository.findById(1L)).willThrow(AccountNotFoundException.exception);

        //when
        Throwable thrown = catchThrowable(() -> accountService.myInfo(1L));

        //then
        assertThat(thrown).isInstanceOf(AccountNotFoundException.class);
    }

}
