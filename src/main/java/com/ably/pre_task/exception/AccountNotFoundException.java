package com.ably.pre_task.exception;

public class AccountNotFoundException extends RuntimeException {

    public static final AccountNotFoundException exception = new AccountNotFoundException("유저를 찾을 수 없습니다.");

    public AccountNotFoundException(String message) {
        super(message);
    }
}
