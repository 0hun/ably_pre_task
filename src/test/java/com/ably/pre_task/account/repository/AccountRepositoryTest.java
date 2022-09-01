package com.ably.pre_task.account.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

import com.ably.pre_task.account.domain.Account;
import com.ably.pre_task.account.dto.request.AccountAddRequest;
import com.ably.pre_task.account.exception.UserNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AccountRepositoryTest {

    @Autowired
    AccountRepository accountRepository;

    @DisplayName("account 객체 저장 테스트")
    @Test
    void saveAccountTest() {
        //given
        final AccountAddRequest request = AccountAddRequest.builder()
                .email("whdudgns2654@gmail.com")
                .nickname("ddd")
                .password("asdkjlsad1123!@")
                .name("jgsds")
                .phone("010-1234-1234")
                .build();

        Account account = request.toEntity();

        //when
        accountRepository.save(account);

        Account savedAccount = accountRepository.findByEmailAndPassword(request.getEmail(), request.getPassword())
                .orElseThrow(() -> UserNotFoundException.exception);

        //then
        assertThat(savedAccount.getEmail()).isEqualTo(request.getEmail());
    }

    @DisplayName("account 객체 저장 실패 테스트")
    @Test
    void saveAccountFailTest() {
        //given
        final AccountAddRequest request = AccountAddRequest.builder()
                .nickname("ddd")
                .password("asdkjlsad1123!@")
                .name("jgsds")
                .phone("010-1234-1234")
                .build();

        Account account = request.toEntity();

        //when
        Throwable thrown = catchThrowable(() -> {
            accountRepository.save(account);
        });

        //then
        assertThat(thrown.getCause()).isInstanceOf(ConstraintViolationException.class);
    }

    @DisplayName("account 객체 조회 실패 테스트")
    @Test
    void findAccountFailTest() {
        //given
        final String email = "whdudgns2654@gmail.com";
        final String password = "asdkjlsad1123!@";

        // when
        Throwable thrown = catchThrowable(() -> {
            accountRepository.findByEmailAndPassword(email, password)
                    .orElseThrow(() -> UserNotFoundException.exception);
        });

        //then
        assertThat(thrown).isInstanceOf(UserNotFoundException.class);
    }

    @DisplayName("account 객체 비밀번호 변경 테스트")
    @Test
    void changePasswordTest() {
        //given
        final AccountAddRequest request = AccountAddRequest.builder()
                .email("whdudgns2654@gmail.com")
                .nickname("ddd")
                .password("asdkjlsad1123!@")
                .name("jgsds")
                .phone("010-1234-1234")
                .build();

        Account account = request.toEntity();

        accountRepository.save(account);

        Account savedAccount = accountRepository.findByEmailAndPassword(request.getEmail(), request.getPassword())
                .orElseThrow(() -> UserNotFoundException.exception);

        //when
        final String newPassword = "xzxczxcsa1234@";
        savedAccount.changePassword(newPassword);


        //then
        assertThat(savedAccount.getPassword()).isEqualTo(newPassword);
    }

}
