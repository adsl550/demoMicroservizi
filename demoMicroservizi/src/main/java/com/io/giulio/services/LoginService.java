package com.io.giulio.services;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.io.giulio.model.User;
import com.io.giulio.utils.UserNotLoggedException;

public interface LoginService {
	
	Optional<User> getUserFromDbAndVerifyPassword(String id, String password) throws UserNotLoggedException ;
	
	String createJwt(String subject, String name, String permission, Date date) throws UnsupportedEncodingException;
	
	Map<String,Object> verifyJwtAndGetData(HttpServletRequest request) throws UserNotLoggedException, UnsupportedEncodingException;

}
