package com.ably.pre_task.account.exception;

public class UserNotFoundException extends RuntimeException {

    public static final UserNotFoundException exception = new UserNotFoundException("유저를 찾을 수 없습니다.");

    public UserNotFoundException(String message) {
        super(message);
    }
}
