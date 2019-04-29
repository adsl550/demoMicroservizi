package com.io.giulio.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.io.giulio.model.Operation;
import com.io.giulio.model.User;
import com.io.giulio.services.LoginService;
import com.io.giulio.services.OperationService;
import com.io.giulio.utils.UserNotLoggedException;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Controller
@RestController
public class Controllers {

	private static final Logger log = LoggerFactory.getLogger(Controllers.class);
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	OperationService operationService; 
	
//	@RequestMapping("/hello")
//	@ResponseBody
//	public String sayhHello() {
//		return "ciao giulio";
//	}
	
	@RequestMapping("/hello")
	public String sayhHello() {
		return "ciao giulio";
	}
	
//	@RequestMapping("/newuser1")
//	public String addUser(User user) {
//		return "new user aggiunto "+ user;
//	}
//	@RequestMapping("/newuser2")
//	public String addUserValid( @Valid User user) {
//		return "new user Valid aggiunto "+ user.toString();
//	}
//			
//	/**
//	 * @param user
//	 * @param result
//	 * @return
//	 */
//	@RequestMapping("/newuser3")
//	public String addUserValidandBilding(@Valid User user, BindingResult result ) {
//		if(result.hasErrors()) {
//			return result.toString();
//		}
//			return "user aggiunto correttamente  " + user + " bindingResult " + result.getObjectName();
//	}
//
//	@RequestMapping("/newuser4")
//	public String addUserValidate(User user, BindingResult result) {
//		UserValidator userValidator = new UserValidator();
//		userValidator.validate(user, result);
//		if(result.hasErrors()) {
//			return result.toString();
//		}
//		
//		return "userValidate aggiunto "+ user.toString();
//	}
/***************************************************************************************************************************************************************/	
	
	/**
	 * verifica se l'utente è presente nel db.
	 * Incaso di esito positivo genera il file jwt
	 * 
	 * @param iduser
	 * @param username
	 * @param password
	 * @param permission
	 * @return
	 */
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public ResponseEntity<JsonResopnseBody> loginUser(@RequestParam(value = "id") String id, @RequestParam(value = "username")String username, 
													  @RequestParam(value = "password") String password , @RequestParam(value = "permission") String permission) {
	
		try {
				Optional<User> userr = loginService.getUserFromDbAndVerifyPassword(id, password);
				if(userr.isPresent()) {
					User user = userr.get();		
					String jwt = loginService.createJwt(user.getId(), user.getUsername(), user.getPermission(), new Date());
					return ResponseEntity.status(HttpStatus.OK).header("jwt",jwt).body(new JsonResopnseBody(HttpStatus.OK.value(),"Utente riconosciuto perfettamente"));
				}
		} catch (UserNotLoggedException e) {
			
		       	e.printStackTrace();
		       	return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new JsonResopnseBody(HttpStatus.OK.value(),"Utente fallito "+e.toString()));
		}catch (UnsupportedEncodingException e1) {
			   	e1.printStackTrace();
			   	return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new JsonResopnseBody(HttpStatus.OK.value(),"Utente FORBIDDEN "+e1.toString()));
	}
		
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new JsonResopnseBody(HttpStatus.OK.value(),"Mancata corrispondenza dell'utente "));
	}
	
	/**
	 * 
	 * @param request
	 * @param account uitlizzo PathVariable per importare il valore  
	 * @return
	 */
	@RequestMapping("/operations/account/{account}")
	public ResponseEntity<JsonResopnseBody> fetchAllOperationAccount(HttpServletRequest request, @PathVariable(name = "account")String account) {
		
		try {
			loginService.verifyJwtAndGetData(request);
			log.info("utente valido");
			return ResponseEntity.status(HttpStatus.OK).body(new JsonResopnseBody(HttpStatus.OK.value(),operationService.getAllOperationToAccount(account)));	
		} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new JsonResopnseBody(HttpStatus.FORBIDDEN.value(),"UnsupportedEncodingException FORBIDDEN "+e.toString()));
		} catch (UserNotLoggedException e1) {
				e1.printStackTrace();
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new JsonResopnseBody(HttpStatus.FORBIDDEN.value(),"UserNotLoggedException FORBIDDEN "+e1.toString()));
		} catch(ExpiredJwtException e2) {
				return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(new JsonResopnseBody(HttpStatus.GATEWAY_TIMEOUT.value(),"GATEWAY_TIMEOUT FORBIDDEN "+e2.toString()));
		}
		
//		return ResponseEntity.status(HttpStatus.OK).body(new JsonResopnseBody(HttpStatus.OK.value(),operationService.getAllOperationToAccount(account)));
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/accounts/user",method = RequestMethod.POST)
	public ResponseEntity<JsonResopnseBody> fetchAllAccountsToUser(HttpServletRequest request) {  
		
		try {
			Map<String,Object> userData= loginService.verifyJwtAndGetData(request);
			return ResponseEntity.status(HttpStatus.OK).body( new JsonResopnseBody(HttpStatus.OK.value(), operationService.getAllAccountToUser((String) userData.get("subject"))));	
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResopnseBody(HttpStatus.BAD_REQUEST.value(),"UserNotLoggedException BAD_REQUEST "+e.toString()));
		} catch (UserNotLoggedException e1) {
			e1.printStackTrace();
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new JsonResopnseBody(HttpStatus.FORBIDDEN.value(),"UserNotLoggedException FORBIDDEN "+e1.toString()));
		} catch(ExpiredJwtException e2) {
			return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(new JsonResopnseBody(HttpStatus.GATEWAY_TIMEOUT.value(),"UserNotLoggedException FORBIDDEN "+e2.toString()));
		}		
	}
	
	/**
	 * 
	 * @param request
	 * @param operation
	 * @param bindingResult
	 * @return
	 */
	@RequestMapping(value = "/operations/add",method = RequestMethod.POST)
	public ResponseEntity<JsonResopnseBody> addOperation(HttpServletRequest request, @Valid Operation operation, BindingResult bindingResult ) {
		
		if (bindingResult.hasErrors()) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new JsonResopnseBody(HttpStatus.FORBIDDEN.value(),"Operation not valid" ) );
		}
		try {
			loginService.verifyJwtAndGetData(request);
			return ResponseEntity.status(HttpStatus.OK).body( new JsonResopnseBody(HttpStatus.OK.value(),operationService.saveOperation(operation) ));	
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResopnseBody(HttpStatus.BAD_REQUEST.value(),"UserNotLoggedException BAD_REQUEST "+e.toString()));
		} catch (UserNotLoggedException e1) {
			e1.printStackTrace();
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new JsonResopnseBody(HttpStatus.FORBIDDEN.value(),"UserNotLoggedException FORBIDDEN "+e1.toString()));
		} catch(ExpiredJwtException e2) {
			return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(new JsonResopnseBody(HttpStatus.GATEWAY_TIMEOUT.value(),"UserNotLoggedException FORBIDDEN "+e2.toString()));
		}		
		
	
	}
	
	
	
	
	
	
	
	
/**********************************Inner Class******************************************************************************************************************/
	public class UserValidator implements org.springframework.validation.Validator {

		@Override
		public boolean supports(Class<?> clazz) {
			// TODO Auto-generated method stub
			return User.class.equals(clazz);
		}

		@Override
		public void validate(Object target, Errors errors) {
			User user =(User) target;
			if(user.getPassword().length()<8) {
				errors.reject("password","scemo la password deve essere lunga almeno 8 caratteri!!!");
			}
			if(user.getUsername().isEmpty() || user.getUsername().equals(" ") ) {
				errors.reject("username","scemo inserisci la username!!!");
			}
			
		}
		
		
	}
/************************************************************************************************************************************************************/
	
/********************************************Inner Class JsonResopnseBody **********************************************************************************/	

	@AllArgsConstructor
	@NoArgsConstructor
	public class JsonResopnseBody {
		
		@Getter
		@Setter
		private int server;
		
		@Getter
		@Setter
		private Object response;
		
				

//		public JsonResopnseBody(Integer server, Object response) {
//				this.server=server;
//				this.response=response;
//		}

//		public JsonResopnseBody(int server, List<Operation> allOperationToAccount) {
//			
//		}
//
//		public JsonResopnseBody(int value, String string, List<Account> allAccountToUser) {
//			// TODO Auto-generated constructor stub
//		}
//
//		public int getServer() {
//			return server;
//		}
//
//		public void setServer(int server) {
//			this.server = server;
//		}
//
//		public Object getResponse() {
//			return response;
//		}
//
//		public void setResponse(Object response) {
//			this.response = response;
//		}
		
		
		
		
		
		
	}
/************************************************************************************************************************************************************/	
}
