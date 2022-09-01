package com.ably.pre_task.dto.response;

import com.ably.pre_task.domain.Account;
import lombok.Builder;
import lombok.Getter;

@Getter
public class AccountInfoResponse {

    private final String email;

    private final String nickname;

    private final String name;

    private final String phone;

    @Builder
    public AccountInfoResponse(String email, String nickname, String name, String phone) {
        this.email = email;
        this.nickname = nickname;
        this.name = name;
        this.phone = phone;
    }

    public static AccountInfoResponse of(Account account) {
        return AccountInfoResponse.builder()
                .email(account.getEmail())
                .nickname(account.getNickname())
                .name(account.getName())
                .phone(account.getPhone())
                .build();
    }
}
