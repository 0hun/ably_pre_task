package com.ably.pre_task.service;

import com.ably.pre_task.domain.Account;
import com.ably.pre_task.dto.request.AccountAddRequest;
import com.ably.pre_task.dto.request.AccountChangePasswordRequest;
import com.ably.pre_task.dto.request.LoginRequest;
import com.ably.pre_task.dto.response.AccountInfoResponse;
import com.ably.pre_task.exception.AccountDuplicatedException;
import com.ably.pre_task.exception.AccountNotFoundException;
import com.ably.pre_task.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final SmsService smsService;

    public Long findId(LoginRequest request) {
        Account account = accountRepository.findByEmailAndPassword(request.getEmail(), request.getPassword())
                .orElseThrow(() -> AccountNotFoundException.exception);

        return account.getId();
    }

    @Transactional
    public void add(AccountAddRequest request) {
        boolean existsAccount = accountRepository.existsByEmail(request.getEmail());

        if (existsAccount) {
            throw AccountDuplicatedException.exception;
        }

        String encodePassword = passwordEncoder.encode(request.getPassword());
        Account account = request.passwordEncodeCopyAccount(encodePassword);

        accountRepository.save(account);
    }

    @Transactional
    public void changePassword(AccountChangePasswordRequest request) {
        Account account = accountRepository.findByPhone(request.getPhone())
                .orElseThrow(() -> AccountNotFoundException.exception);

        smsService.sendSms(request.getPhone());

        String encodePassword = passwordEncoder.encode(request.getNewPassword());
        account.changePassword(encodePassword);
    }

    public AccountInfoResponse myInfo(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> AccountNotFoundException.exception);

        return AccountInfoResponse.of(account);
    }

}
