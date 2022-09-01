package com.ably.pre_task.exception;

public class AccountDuplicatedException extends RuntimeException {

    public static final AccountDuplicatedException exception = new AccountDuplicatedException("이미 존재하는 유저입니다.");

    public AccountDuplicatedException(String message) {
        super(message);
    }
}
