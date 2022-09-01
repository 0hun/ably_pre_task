package com.ably.pre_task.account.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.ably.pre_task.account.dto.request.AccountAddRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AccountTest {

    @DisplayName("accountAddRequest를 이용하여 account 객체를 생성 테스트")
    @Test
    void accountAddTest() {
        //given
        final AccountAddRequest request = AccountAddRequest.builder()
                .email("whdudgns2654@gmail.com")
                .nickname("ddd")
                .password("asdkjlsad1123!@")
                .name("jgsds")
                .phone("010-1234-1234")
                .build();

        //when
        Account account = request.toEntity();

        //then
        assertThat(account.getName()).isNotNull();
    }

    @DisplayName("account 객체 비밀번호 변경 테스트")
    @Test
    void accountChangePasswordTest() {
        //given
        final AccountAddRequest request = AccountAddRequest.builder()
                .email("whdudgns2654@gmail.com")
                .nickname("ddd")
                .password("asdkjlsad1123!@")
                .name("jgsds")
                .phone("010-1234-1234")
                .build();

        Account account = request.toEntity();
        final String newPassword = "xzxczxcsa1234@";

        //when
        account.changePassword(newPassword);

        //then
        assertThat(account.getPassword()).isEqualTo(newPassword);
    }

}