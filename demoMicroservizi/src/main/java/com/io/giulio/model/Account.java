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

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account")
public class Account {
	
	@Id
	@Column(name = "ID")
	@NotEmpty	@NotNull @NotBlank
	@Getter	@Setter
	private String id;
	
	@Column(name = "FK_USER")
	@NotEmpty	@NotNull @NotBlank
	@Getter	@Setter	
	private String fkUser;
	
	@Column(name = "TOTAL")
	@NotEmpty	@NotNull	@NotBlank
	@Getter	@Setter
	private Double total;


//	public Account(@NotEmpty @NotNull @NotBlank Integer idaccount, @NotEmpty @NotNull @NotBlank String fkUser,
//			@NotEmpty @NotNull @NotBlank Double total) {
//		super();
//		this.idaccount = idaccount;
//		this.fkUser = fkUser;
//		this.total = total;
//	}
//new Account(  "user1","bnfgli84m23b963z"   2.500)
//	public Account(final String id, final String fkUser, @NotEmpty @NotNull @NotBlank Double total) {
//		super();
//		this.fkUser = fkUser;
//		this.total = total;
//	}
//
//	public String getIdaccount() {
//		return idaccount;
//	}
//
//	public void setIdaccount(String idaccount) {
//		this.idaccount = idaccount;
//	}
//
//	public String getFkUser() {
//		return fkUser;
//	}
//
//	public void setFkUser(String fkUser) {
//		this.fkUser = fkUser;
//	}
//
//	public Double getTotal() {
//		return total;
//	}
//
//	public void setTotal(Double total) {
//		this.total = total;
//	}

//	@Override
//	public String toString() {
//		return "Account [getIdaccount()=" + getIdaccount() + ", getFkUser()=" + getFkUser() + ", getTotal()="
//				+ getTotal() + "]";
//	}
//	@Override
//	public String toString() {
//		return "Account \nIdaccount= " + idaccount + ", FkUser= " + fkUser + ", Total= "+ total;
//	}

}
