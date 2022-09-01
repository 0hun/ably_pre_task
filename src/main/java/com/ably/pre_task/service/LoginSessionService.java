package com.ably.pre_task.service;

import java.util.Optional;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginSessionService implements LoginService {

    public static final String ACCOUNT_USER_ID = "ACCOUNT_USER_ID";

    @Override
    public void accountLogin(HttpSession session, Long accountId) {
        session.setAttribute(ACCOUNT_USER_ID, accountId);
    }

    @Override
    public void accountLogout(HttpSession session) {
        session.removeAttribute(ACCOUNT_USER_ID);
    }

    @Override
    public boolean isLoginAccount(HttpSession session) {
        Long accountId = (Long) session.getAttribute(ACCOUNT_USER_ID);
        return accountId != null;
    }

    @Override
    public Optional<Long> getLoginAccountId(HttpSession session) {
        return Optional.ofNullable(session.getAttribute(ACCOUNT_USER_ID))
                .map(String::valueOf)
                .map(Long::valueOf);
    }

}
