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
import javax.persistence.Table;

@Entity
@Table(name="UserRole")
public class UserRole {

	@Id	@GeneratedValue
	@Column(name = "role_id")
	private Integer userRoleId;
	
	@Column(name = "role")
	private String role;
	
/*	@OneToMany(cascade = CascadeType.ALL, mappedBy="UserRole")
	private Set<UserAccount> userAccount = new HashSet<UserAccount>(0);
 
	public Set<UserAccount> getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(Set<UserAccount> userAccount) {
		this.userAccount = userAccount;
	}
*/
	public Integer getUserRoleId() {
		return this.userRoleId;
	}
 
	public void setUserRoleId(Integer userRoleId) {
		this.userRoleId = userRoleId;
	}

	public String getRole() {
		return this.role;
	}
 
	public void setRole(String role) {
		this.role = role;
	}
	
}