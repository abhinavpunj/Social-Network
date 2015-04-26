package com.social.network.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
@Table(name = "Posts")
public class Posts {
	
	@Id	@GeneratedValue
	@Column(name = "PostId")
	private int postId;
	
	@Column(name = "Status")
	private String status;
	
	@Column(name = "Likes")
	private int likes;
	
	@Column(name = "DateOfPost")
	private Date dateOfPost;

	//Person-->Posts
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PersonId")
	private Person person;
		
	public Person getPerson() {
			return person;
		}
		public void setPerson(Person person) {
			this.person = person;
		}
	
		//Posts-->Comments
		//bidirectional mapping
		@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
		private List<Comments> comments = new ArrayList<Comments>();

		//Posts-->Notifications		
		@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
		private List<Notification> notifications = new ArrayList<Notification>();
		
		
	public List<Notification> getNotifications() {
			return notifications;
		}
		public void setNotifications(List<Notification> notifications) {
			this.notifications = notifications;
		}
	public List<Comments> getComments() {
			return comments;
		}
		public void setComments(List<Comments> comments) {
			this.comments = comments;
		}
		
		
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public Date getDateOfPost() {
		return dateOfPost;
	}
	public void setDateOfPost(Date dateOfPost) {
		this.dateOfPost = dateOfPost;
	}

}
