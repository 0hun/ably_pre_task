package com.ably.pre_task.util;

import com.ably.pre_task.service.LoginService;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.server.Http2;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * LoginUserId 어노테이션을 파라미터에 사용시 값을 바인딩하는 클래스
 * <p>HandlerMethodArgumentResolver는 특정 조건에 맞는 파라미터가 있을때,
 * 원하는 값을 바인딩 해주는 인터페이스</p>
 */
@Component
@RequiredArgsConstructor
public class LoginUserIdResolver implements HandlerMethodArgumentResolver {

  public static final String ACCOUNT_USER_ID = "ACCOUNT_USER_ID";
  private final LoginService loginService;

  /**
   * LoginUserId 어노테이션 파라미터로 등록하는 메소드
   *
   * @param parameter
   * @return boolean
   */
  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    return parameter.hasMethodAnnotation(LoginUserId.class);
  }

  /**
   * LoginUserId 어노테이션 파라미터에 사용시, 사용자의 아이디를 리턴하는 메소드
   *
   * @return Object(사용자의 아이디)
   */
  @Override
  public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
      NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
    HttpSession session = (HttpSession) webRequest.getAttribute("ACCOUNT_USER_ID", WebRequest.SCOPE_SESSION);
      return loginService.getLoginAccountId(session);
  }
}
