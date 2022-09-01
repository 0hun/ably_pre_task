package com.ably.pre_task.account.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
@Table(name = "account")
public class Account {

    // 아이디(pk)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 이메일
    @Column(name = "email", nullable = false)
    private String email;

    // 닉네임
    @Column(name = "nick_name", nullable = false)
    private String nickname;

    // 패스워드
    @Column(name = "password", nullable = false)
    private String password;

    // 이름
    @Column(name = "name", nullable = false)
    private String name;

    // 핸드폰 번호
    @Column(name = "phone", nullable = false)
    private String phone;

    protected Account() {
    }

    @Builder
    public Account(Long id, String email, String nickname, String password, String name,
            String phone) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.name = name;
        this.phone = phone;
    }

    public void changePassword(String newPassword) {
        this.password = newPassword;
    }
}
