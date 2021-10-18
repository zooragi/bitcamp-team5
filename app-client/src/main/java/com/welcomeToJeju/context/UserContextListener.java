package com.welcomeToJeju.context;

import java.util.Map;

public interface UserContextListener {
  void contextLogin(Map<String,Object> params);
  void contextLogout(Map<String,Object> params); 
}