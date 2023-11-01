package com.house.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "users")
public class UserEntity implements Serializable {
private static final long serialVersionUID = 1L;


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id", length = 11)
private Integer id;
@Column(name = "username", length = 50)
private String username;
@Column(name = "prenom")
private String lastname;
@Column(name = "matri")
private String matri;
@Email
@Column(name = "email", length = 50)
private String email;
@Column(name = "password")
private String password;
@Column(name = "tel")
private String tel;
@Column(name = "sex")
private String sex;
// @Column(name = "activite")
// private boolean activite;
@Column(name = "date_creation")
@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
private Date dateCreation;

private String roles;
private Integer acces;

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

public Date getDateCreation() {
	return dateCreation;
}

public void setDateCreation(Date dateCreation) {
	this.dateCreation = dateCreation;
}

public String getRoles() {
	return roles;
}

public void setRoles(String roles) {
	this.roles = roles;
}

public static long getSerialversionuid() {
	return serialVersionUID;
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
