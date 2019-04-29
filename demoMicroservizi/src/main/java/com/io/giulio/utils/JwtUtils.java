package com.io.giulio.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
//import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * In caso di Oggetto statico si può eliminare l'annotazione @Component perché l'oggetto non va iniettato ma va  solo utilizzato il metodo stastico.
 * */
//@Component
public class JwtUtils  {
	
	private static final Logger log = LoggerFactory.getLogger(JwtUtils.class);
	
	/**
	 * Questa funzione genera il token per inviarlo al client
	 * 
	 * @param subject
	 * @param date
	 * @param name
	 * @param scope
	 * @return
	 * @throws java.io.UnsupportedEncodingException
	 */
	public static String generateJwt(String subject, Date date, String name, String scope) throws java.io.UnsupportedEncodingException {
		
		String jwt = Jwts.builder()
						 .setSubject(subject)
						 .setExpiration(date)
						 .claim(name,scope)
						 .signWith(SignatureAlgorithm.HS256,"my.SecretEncryption-Key{BlaBla1234".getBytes("UTF-8"))
						 .compact();
		return jwt;
	}
	
	
	/**
	 * questo metodo converte il token in una mappa di userData controllandone la validità.
	 * 
	 * @param jwt
	 * @return
	 * @throws java.io.UnsupportedEncodingException
	 * @throws ExpiredJwtException
	 */
	public static Map<String , Object> jwt2Map(String jwt) throws java.io.UnsupportedEncodingException, ExpiredJwtException {
		
		Jws<Claims> claim = Jwts.parser().setSigningKey("my.SecretEncryption-Key{BlaBla1234".getBytes("UTF-8")).parseClaimsJws(jwt);
		String name = claim.getBody().get("name",String.class);
		String scope = (String) claim.getBody().get("scope");
		Date expDate = claim.getBody().getExpiration();
		String subj = claim.getBody().getSubject();
		
		Map<String, Object> userData = new HashMap<String, Object>();
		userData.put("name", name);
		userData.put("scope", scope);
		userData.put("exp_date", expDate);
		userData.put("subject", subj);
		
		Date now = new Date();
		if(now.after(expDate)) {
			throw new ExpiredJwtException(null, null, "Sessione persa!!! ");
		}
		return userData;
	}
	
	/**
	 * questo metodo estrae il jwt dall'intestazione o dal cookie nella richiesta http
	 * @param request
	 * @return jwt
	 */
	public static String getJwtFromHttpRequest(HttpServletRequest request) {
		
		String jwt = null;
		
	
		if(request.getHeader("jwt") != null)  {
			jwt = request.getHeader("jwt"); //eventuale presenza di token nell'header
		} else if (request.getCookies() != null) {
			Cookie[] cookies = request.getCookies();
			
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals("jwt")) {
					jwt = cookie.getValue();
				}
			}
		}
		
		return jwt;
	}
	
	
	
	
	
	
}
