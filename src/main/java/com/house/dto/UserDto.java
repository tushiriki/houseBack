package com.house.dto;

import lombok.Data;

@Data
public class UserDto {
private Integer id;
private String username;
private String lastname;
private String email;
private String password;
private String tel;
private String sex;
private String dateCreation;
private String roles;
private Integer acces;
private String token;
//private boolean activite;

public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}

public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getLastname() {
	return lastname;
}
public void setLastname(String lastname) {
	this.lastname = lastname;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getTel() {
	return tel;
}
public void setTel(String tel) {
	this.tel = tel;
}
public String getSex() {
	return sex;
}
public void setSex(String sex) {
	this.sex = sex;
}
public String getDateCreation() {
	return dateCreation;
}
public void setDateCreation(String dateCreation) {
	this.dateCreation = dateCreation;
}
public String getRoles() {
	return roles;
}
public void setRoles(String roles) {
	this.roles = roles;
}

public Integer getAcces() {
	return acces;
}

public void setAcces(Integer acces) {
	this.acces=acces;
}

// public boolean getActivite() {
// 	return activite;
// }

// public void setActivite(boolean activite) {
// 	this.activite=activite;
// }
}
