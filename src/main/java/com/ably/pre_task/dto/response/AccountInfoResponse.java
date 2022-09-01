package com.ably.pre_task.dto.response;

import com.ably.pre_task.domain.Account;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
public class AccountInfoResponse {

    @ApiModelProperty(value = "이메일", dataType = "string", required = true)
    private final String email;

    @ApiModelProperty(value = "닉네임", dataType = "string", required = true)
    private final String nickname;

    @ApiModelProperty(value = "이름", dataType = "string", required = true)
    private final String name;

    @ApiModelProperty(value = "휴대폰 번호", dataType = "string", required = true)
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
