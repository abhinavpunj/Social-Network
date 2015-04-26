package com.social.network.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name = "UserAccount")
public class UserAccount {

	@Id	@GeneratedValue(generator="newGenerator") //name of primary key generator
	//create uid as foreign key
	@GenericGenerator(name="newGenerator", strategy="foreign",parameters = { @Parameter(value= "person", name="property")})
	@Column(name="PersonId")
	private int uId;
	
	@NotEmpty(message="Username cannot be null")
	@Column(name="username")
	private String username;
	
	@NotEmpty(message="password cannot be null")
	@Column(name="password")
	private String password;
	
	//bidirectional one to one mapping
	@OneToOne(cascade = CascadeType.ALL) 
	//CascadeType.ALL performs actions on parent class when child class is changed automatically
	@JoinColumn(name="PersonId")
	private Person person;
	
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	
	//Many to one mapping
	@ManyToOne(cascade = CascadeType.ALL)
	private UserRole role;
	
	public UserRole getRole() {
		return role;
	}
	public void setRole(UserRole role) {
		this.role = role;
	}
	public int getuId() {
		return uId;
	}
	public void setuId(int uId) {
		this.uId = uId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
