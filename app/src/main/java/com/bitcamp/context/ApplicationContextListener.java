package com.bitcamp.context;

import java.util.Map;

public interface ApplicationContextListener {
	void contextInitialized(Map<String,Object> params);
  void contextDestroyed(Map<String,Object> params); 
	
}
