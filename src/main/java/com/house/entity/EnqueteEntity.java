package com.house.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "enquete")
public class EnqueteEntity  implements Serializable {

private static final long serialVersionUID = 1L;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id", length = 11)
private Integer id;
@Column(name = "id_user", length = 50)
private Integer idUser;

private Date dateCreation;
private String province;

private String commune;
private String zone;
private String territoire;
//private Boolean constant;

@ManyToOne(fetch = FetchType.LAZY)
@JsonIgnore
@NotFound(action = NotFoundAction.IGNORE)
@JoinColumn(name = "id_user", referencedColumnName = "id", insertable = false, updatable = false)
private UserEntity user;


public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public Integer getIdUser() {
	return idUser;
}

public void setIdUser(Integer idUser) {
	this.idUser = idUser;
}

public Date getDateCreation() {
	return dateCreation;
}

public void setDateCreation(Date dateCreation) {
	this.dateCreation = dateCreation;
}

public String getProvince() {
	return province;
}

public void setProvince(String province) {
	this.province = province;
}

public String getCommune() {
	return commune;
}

public void setCommune(String commune) {
	this.commune = commune;
}

public String getZone() {
	return zone;
}

public void setZone(String zone) {
	this.zone = zone;
}

public String getTerritoire() {
	return territoire;
}

public void setTerritoire(String territoire) {
	this.territoire = territoire;
}


	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public static long getSerialversionuid() {
	return serialVersionUID;
}



}
