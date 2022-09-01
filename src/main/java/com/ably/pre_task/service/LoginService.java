package com.ably.pre_task.service;


import java.util.Optional;
import javax.servlet.http.HttpSession;

public interface LoginService {

  void accountLogin(HttpSession session, Long accountId);

  void accountLogout(HttpSession session);

  boolean isLoginAccount(HttpSession session);

  Optional<Long> getLoginAccountId(HttpSession session);

}
