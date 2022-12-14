package com.ably.pre_task.dto.request;

import com.ably.pre_task.domain.Account;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Getter
public class AccountAddRequest {

    @NotBlank(message = "이메일 주소를 입력해주세요.")
    @Email(message = "올바른 이메일 주소를 입력해주세요.")
    @ApiModelProperty(value = "이메일", dataType = "string", required = true)
    private String email;

    @NotBlank(message = "닉네임을 입력해주세요.")
    @Size(min = 2, max = 10, message = "닉네임은 2자 이상 10자 이하로 입력해주세요.")
    @ApiModelProperty(value = "닉네임", dataType = "string", required = true)
    private String nickname;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하로 입력해주세요.")
    @ApiModelProperty(value = "비밀번호", dataType = "string", required = true)
    private String password;

    @NotBlank(message = "휴대폰 번호를 입력해주세요.")
    @Pattern(regexp = "(01[016789])(\\d{3,4})(\\d{4})", message = "올바른 휴대폰 번호를 입력해주세요.")
    @ApiModelProperty(value = "휴대폰 번호", dataType = "string", required = true)
    private String phone;

    @NotBlank(message = "이름을 입력해주세요.")
    @ApiModelProperty(value = "이름", dataType = "string", required = true)
    private String name;

    public AccountAddRequest() {
    }

    @Builder
    public AccountAddRequest(String email, String nickname, String password, String phone,
            String name) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.phone = phone;
        this.name = name;
    }

    public Account toEntity() {
        return Account.builder()
                .email(this.email)
                .nickname(this.nickname)
                .password(this.password)
                .phone(this.phone)
                .name(this.name)
                .build();
    }

    public Account passwordEncodeCopyAccount(String encodePassword) {
        return Account.builder()
                .email(this.email)
                .nickname(this.nickname)
                .password(encodePassword)
                .phone(this.phone)
                .name(this.name)
                .build();
    }
}
