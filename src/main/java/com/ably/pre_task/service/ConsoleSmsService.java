package com.ably.pre_task.service;

import com.ably.pre_task.exception.SmsNumberNotMatchException;
import com.ably.pre_task.util.MessageUtil;
import com.ably.pre_task.util.RandomNumber;
import java.util.Scanner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ConsoleSmsService implements SmsService {

    private static final Scanner SCANNER = new Scanner(System.in);

    @Override
    public void sendSms(String phone) {
        String randomNumber = RandomNumber.generate();

        log.info(MessageUtil.INPUT_SMS_NUMBER +  randomNumber);

        String inputRandomNumber = SCANNER.next();

        if (!inputRandomNumber.equals(randomNumber)) {
            throw SmsNumberNotMatchException.exception;
        }
    }
}
