package com.ably.pre_task.service;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import javax.servlet.http.HttpSession;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class LoginServiceTest {

    @InjectMocks
    private LoginSessionService loginSessionService;

    @Mock
    private HttpSession httpSession;

    public final String ACCOUNT_USER_ID = "ACCOUNT_USER_ID";

    @DisplayName("account 로그인 테스트")
    @Test
    void accountLoginTest() {
        //given
        final long accountId = 1L;

        doNothing().when(httpSession).setAttribute(ACCOUNT_USER_ID, accountId);

        //when
        loginSessionService.accountLogin(accountId);

        //then
        verify(httpSession).setAttribute(ACCOUNT_USER_ID, accountId);
    }

    @DisplayName("account 로그아웃 테스트")
    @Test
    void accountLogoutTest() {
        //given
        final long accountId = 1L;

        httpSession.setAttribute(ACCOUNT_USER_ID, accountId);

        //when
        loginSessionService.accountLogout();

        //then
        verify(httpSession).removeAttribute(ACCOUNT_USER_ID);
    }

}
