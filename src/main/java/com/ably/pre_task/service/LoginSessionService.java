package com.ably.pre_task.service;

import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginSessionService implements LoginService {

    public static final String ACCOUNT_USER_ID = "ACCOUNT_USER_ID";

    public final HttpSession session;

    @Override
    public void accountLogin(Long accountId) {
        session.setAttribute(ACCOUNT_USER_ID, accountId);
    }

    @Override
    public void accountLogout() {
        session.removeAttribute(ACCOUNT_USER_ID);
    }

    @Override
    public boolean isLoginAccount() {
        Long accountId = (Long) session.getAttribute(ACCOUNT_USER_ID);
        return accountId != null;
    }

}
