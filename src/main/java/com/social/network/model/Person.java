package com.social.network.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.web.multipart.commons.CommonsMultipartFile;


@Entity
@Table(name = "Person")
public class Person {

	@Id	@GeneratedValue
	@Column(name = "PersonId")
	private int pId;
	
	@Column(name = "FirstName")
	private String firstName;
	
	@Column(name = "LastName")
	private String lastName;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "DateOfBirth")
	private Date dob;
	
	@Column(name = "Gender")
	private String gender;
	
	@Column(name = "Email")
	private String email;
	
	private CommonsMultipartFile profilePic;
	
	@Column(name = "ProfilePicPath")
	private String profilePicPath;
	
	public String getProfilePicPath() {
		return profilePicPath;
	}
	public void setProfilePicPath(String profilePicPath) {
		this.profilePicPath = profilePicPath;
	}
	public CommonsMultipartFile getProfilePic() {
		return profilePic;
	}
	public void setProfilePic(CommonsMultipartFile profilePic) {
		this.profilePic = profilePic;
	}
	
	//bidirectional one to one mapping
	@OneToOne(cascade = CascadeType.ALL) 
	//CascadeType.ALL performs actions on parent class when child class is changed automatically
	@JoinColumn(name="PersonId")
	private UserAccount userAccount;
	
	public UserAccount getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}
	
	
	/*@OneToMany(fetch = FetchType.EAGER)
	private List<Posts> posts = new ArrayList<Posts>();
	
	public List<Posts> getPosts() {
		return posts;
	}
	public void setPosts(List<Posts> posts) {
		this.posts = posts;
	}
	
	@OneToMany(fetch = FetchType.EAGER)
	private List<Notification> notifications = new ArrayList<Notification>();

	public List<Notification> getNotifications() {
		return notifications;
	}
	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}*/

	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDob() {
		return dob;
	}
	public String getDobString() {
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		String date = df.format(dob);
		return date;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
