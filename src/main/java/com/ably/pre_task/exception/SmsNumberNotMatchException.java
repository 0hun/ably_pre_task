package com.ably.pre_task.exception;

public class SmsNumberNotMatchException extends RuntimeException {

    public static final SmsNumberNotMatchException exception = new SmsNumberNotMatchException("sms 인증번호가 일치하지 않습니다.");

    public SmsNumberNotMatchException(String message) {
        super(message);
    }
}
