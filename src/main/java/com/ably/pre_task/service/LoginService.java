package com.ably.pre_task.service;


public interface LoginService {

  void accountLogin(Long accountId);

  void accountLogout();

  boolean isLoginAccount();

}
