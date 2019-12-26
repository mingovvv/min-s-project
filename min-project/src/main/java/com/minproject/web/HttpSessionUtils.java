package com.minproject.web;

import javax.servlet.http.HttpSession;

import com.minproject.domain.User;

public class HttpSessionUtils {
	public static final String USER_SESSION_KEY = "sessionedUser";
	
	public static boolean isLoginUser(HttpSession session) {
		if(session.getAttribute(USER_SESSION_KEY) == null) {
			return false;
		}else {
			return true;
		}
	}
	
	public static User getUserFromSession(HttpSession session) {
		if(!isLoginUser(session)) {
			return null;
		}
		return (User) session.getAttribute(USER_SESSION_KEY);
	}
}
