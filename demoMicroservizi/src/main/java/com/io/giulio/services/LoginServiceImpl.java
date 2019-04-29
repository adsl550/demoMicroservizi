package com.io.giulio.services;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.io.giulio.daos.UserDao;
import com.io.giulio.model.User;
import com.io.giulio.utils.EncryptionUtils;
import com.io.giulio.utils.JwtUtils;
import com.io.giulio.utils.UserNotLoggedException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service 
@Transactional
public class LoginServiceImpl implements LoginService {

	private static final Logger log = LoggerFactory.getLogger(LoginServiceImpl.class);
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	EncryptionUtils encryptionUtils;
	
	@Override
	public Optional<User> getUserFromDbAndVerifyPassword(String id, String password) throws UserNotLoggedException {
		/**ottengo i valori dal database con l'oggetto Optional*/
		Optional<User> userr = userDao.findById(id);
		if(userr.isPresent()) {
			User user = userr.get();
			if ( encryptionUtils.decryption(user.getPassword()).equals(password)) {
				log.info("Username and password ok ");
			}else {
				log.info("Not Password ");
				throw new UserNotLoggedException("Utente non loggato correttamente");
			}
		}
			
		return userr;
	}

	@Override
	public String createJwt(String subject, String name, String permission, Date datenow) throws UnsupportedEncodingException {
		Date expDate = datenow;
		expDate.setTime(datenow.getTime()+(300*1000));
		log.info("JWT CREATION TIME "+ expDate.getTime());
		String token = JwtUtils.generateJwt(subject,expDate, name, permission);
		
		log.info("TOKEN "+ token);
		
		return token;
	}

	@Override
	public Map<String, Object> verifyJwtAndGetData(HttpServletRequest request) throws UserNotLoggedException, UnsupportedEncodingException {
		String jwt =JwtUtils.getJwtFromHttpRequest(request);
		if(jwt==null) {
			throw new UserNotLoggedException("Autenticazione del token non è stata trovata dal request ");
		}
		Map<String , Object> userData= JwtUtils.jwt2Map(jwt);
		return userData;
	}

}
