package com.ably.pre_task.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Getter
public class AccountChangePasswordRequest {

    @NotBlank(message = "휴대폰 번호를 입력해주세요.")
    @Pattern(regexp = "(01[016789])(\\d{3,4})(\\d{4})", message = "올바른 휴대폰 번호를 입력해주세요.")
    private String phone;

    @NotBlank(message = "새로운 비밀번호를 입력해주세요.")
    @Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하로 입력해주세요.")
    private String newPassword;

    public AccountChangePasswordRequest() {
    }

    @Builder
    public AccountChangePasswordRequest(String phone, String newPassword) {
        this.phone = phone;
        this.newPassword = newPassword;
    }
}
