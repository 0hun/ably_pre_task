package com.ably.pre_task.aop;

import com.ably.pre_task.service.LoginService;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
@RequiredArgsConstructor
public class LoginCheckAop {

    private final LoginService loginService;

    /**
     * 회원 로그인 체크 aop
     * <p>
     * LoginCheck가 적용된 메소드에 접근시 로그인 여부를 체크한다. 로그인이 안되어 있을 경우, 권한이 없음을 의미하는 401 code return.
     *
     * @throws HttpClientErrorException
     */
    @Before("@annotation(LoginCheck) && @annotation(loginCheck)")
    public void loginCheck(LoginCheck loginCheck) throws HttpClientErrorException {
        HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest().getSession();

        userLoginCheck(session);
    }

    public void userLoginCheck(HttpSession session) {
        boolean isLogin = loginService.isLoginAccount(session);

        if (!isLogin) {
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
        }
    }

}
