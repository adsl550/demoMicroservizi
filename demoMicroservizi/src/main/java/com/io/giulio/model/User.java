package com.io.giulio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//import org.hibernate.validator.constraints.NotBlank;
//import org.hibernate.validator.constraints.NotEmpty;;

//JSR-303
/**
 * le annotazioni sono dette attributi di uno stato da parte del pojo 
 */

@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@Id 
	@Column(name = "ID")
	@NotEmpty @NotBlank	@NotNull 
	@Getter	@Setter
	private String id;
	
	@Column(name ="USERNAME")
	@NotEmpty @NotBlank	@NotNull 
	@Getter	@Setter
	private String username;
	
	@Column(name ="PASSWORD")
	@NotEmpty @NotBlank	@NotNull 
	@Getter  @Setter
	private String password;
	
	@Column(name = "PERMISSION")
	@NotEmpty @NotBlank	@NotNull 
	@Getter 	@Setter
	private String permission;

	//User("bnfgli84m23b963z","giulio",encryptioned,"user")
//	public User(@NotEmpty @NotNull @NotBlank String id, @NotEmpty @NotNull @NotBlank String username, @NotEmpty @NotNull @NotBlank String password,	@NotEmpty @NotNull @NotEmpty String permission) {
//		super();
//		this.username = username;
//		this.password = password;
//		this.permission = permission;
//	}


//	public String getUsername() {
//		return username.trim();
//	}
//
//	public void setUsername(String username) {
//		this.username = username.trim();
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//	public String getPermission() {
//		return permission;
//	}
//
//	public void setPermission(String permission) {
//		this.permission = permission;
//	}
//	
//	public void seiId(String id) {
//		this.id =id;
//	}
//	
//	public String getId() {
//		return id;
//	}
	
//	@Override
//	public String toString() {
//		return "User\nidUser = " + id + ", Username = " + username + ", Password="	+ password + ", Permission= " + permission ;
//	}	
	
	
//	@Override
//	public String toString() {
//		return "User [getIduser()=" + getIduser() + ", getUsername()=" + getUsername() + ", getPassword()="	+ getPassword() + ", getPermission()=" + getPermission() + "]";
//	}

}
