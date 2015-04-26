package com.social.network.dao;

import java.util.ArrayList;
import org.apache.commons.lang.StringEscapeUtils;
import java.util.Date;

import org.apache.taglibs.standard.tag.common.core.ForEachSupport;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.social.network.model.Comments;
import com.social.network.model.Notification;
import com.social.network.model.Person;
import com.social.network.model.Posts;
import com.social.network.model.UserAccount;

public class PostsDao extends DAO {
	
	private static int counter;
	private static ArrayList<Integer> friends;
	
	public Posts getPost(UserAccount user, int postId)
	{
		Query q = getSession().createQuery("from Posts where postid = :postId and personid = :personId");
		q.setInteger("postId", postId);
		q.setInteger("personId", user.getuId());
		Posts post = (Posts) q.uniqueResult();
		
		return post;
				
	}
	public ArrayList<Posts> getFriendPosts(UserAccount user, ArrayList<Integer> friendList)
	{
		friendList.add(user.getuId());
		Query query = getSession().createQuery("from Posts where personid in (:personid) order by dateofpost desc").setMaxResults(2);
		query.setParameterList("personid", friendList);
		ArrayList<Posts> postList = (ArrayList<Posts>) query.list();
		counter = 2;
		friends = friendList;
		return postList;
	}
	
	public String getInfinitePosts(UserAccount user)
	{
		
		Query query = getSession().createQuery("from Posts where personid in (:personid) order by dateofpost desc").setFirstResult(counter).setMaxResults(2);
		query.setParameterList("personid", friends);
		counter += 2;
		StringBuilder html = new StringBuilder();
		StringBuilder comments;
		
		ArrayList<Posts> postList = (ArrayList<Posts>) query.list();
		for(Posts post : postList)
		{
			comments = new StringBuilder();
			for(Comments c : post.getComments())
			{
				comments.append("<a href='profile?personId="+ c.getPerson().getpId() + "'><img src='" + c.getPerson().getProfilePicPath() + "' width=20px class='img-circle pull-left' />" +
	                      "<h5>&nbsp; " + c.getPerson().getFirstName() + " " + c.getPerson().getLastName() + "</a></h5>" +
	                      "<p>" + c.getComment() + "</p>");
			}
			
			html.append("<div class='row mtpost'>" +
	                    "<div class='form-panel'>" +
	                      "<a href='profile.html'><img src='" + post.getPerson().getProfilePicPath() + "' width=40px class='img-circle pull-left' />" +
	                      "<h4>&nbsp; " + post.getPerson().getFirstName() + " " + post.getPerson().getLastName() +"</a></h4><br/>" +
	                      "<p>" + post.getStatus() + "</p>" +
	                      "<p><button class='submitLink addLike'><i class='fa fa-thumbs-up'></i> Like</button><span class='like'>" + post.getLikes() + "</span></p>" +
	                    "<div class='postEnd commentSection'>" +
	                      comments +
	                    "</div>" + 
	                          "<div class='form-group'>" + 
	                              "<input type='text' class='form-control form-post comment' name='comment' placeholder='Comment' />" +
	                          "</div>" +
	                          "<button type='submit' class='addComment btn btn-theme btn-xs'>Comment</button> <hidden name= 'hidden' value='" + post.getPostId() +"' />" +
	                    "</div><!-- /col-lg-9 -->" +
	            "</div><input type='hidden' id='hiddenId' value='" + post.getPostId() +"' /><!-- /row -->");
		}
		
		return html.toString();
	}
	
	public ArrayList<Posts> getMyPosts(int personId)
	{
		Query q = getSession().createQuery("from Posts where personid = :personId");
		q.setInteger("personId", personId);
		ArrayList<Posts> postList = (ArrayList<Posts>) q.list();
		
		return postList;
	}
	
	public void addPost(Posts post)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		try
		{
			Date today = new Date();
			post.setDateOfPost(today);
			Transaction transaction = session.beginTransaction();
			session.save(post);
			transaction.commit();
		}
		catch(Exception e)
		{
			System.out.println("Hello " + e.getMessage());
		}
		finally
		{
			session.close();
		}
	}
	
	public int addLikes(int postId, UserAccount user)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from Posts where postid = :postId");
		query.setInteger("postId", postId);
		Posts post = (Posts) query.uniqueResult();
		int likes = post.getLikes() + 1;
		try
		{
			Transaction transaction = session.beginTransaction();
			post.setLikes(likes);
			session.update(post);
			transaction.commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			session.close();
		}
		
		
		String notification = user.getPerson().getFirstName() + " " + user.getPerson().getLastName() + " liked your post";
		addNotification(user, notification, post);
		
		return likes;
	}
	
	public String addComments(int postId, String comment, UserAccount user)
	{
		String safe_comment = StringEscapeUtils.escapeHtml(comment);
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query q = session.createQuery("from Posts where postid = :postId");
		q.setInteger("postId", postId);
		Posts post = (Posts) q.uniqueResult();
		try
		{
			Comments c = new Comments();
			c.setComment(safe_comment);
			c.setPerson(user.getPerson());
			c.setDateOfComment(new Date());
			c.setPosts(post);
			post.getComments().add(c);
			
			Transaction transaction = session.beginTransaction();
			
			session.update(post);
			
			transaction.commit();
		}
		catch(Exception e)
		{
			System.out.println("Could not PostDao");
			e.printStackTrace();
		}
		finally
		{
			session.close();
		}
		
		String notification = user.getPerson().getFirstName() + " " + user.getPerson().getLastName() + " commented on your post";
		addNotification(user, notification, post);
		
		String html = "<a href='profile.html'><img src='" + user.getPerson().getProfilePicPath() + "' width=20px class='img-circle pull-left' />" +
	                      "<h5>&nbsp; " + user.getPerson().getFirstName() + " " + user.getPerson().getLastName() + "</a></h5>" +
	                      "<p>" + comment + "</p>";
		return html;
	}
	
	public void addNotification(UserAccount user, String notification, Posts post)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		try
		{
			Notification n = new Notification();
			n.setFromUser(user.getPerson());
			n.setToUser(post.getPerson());
			n.setNotification(notification);
			n.setSeen(false);
			n.setPosts(post);
			post.getNotifications().add(n);
			
			Transaction transaction = session.beginTransaction();
			session.save(n);
			transaction.commit();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			session.close();
		}
		
	}

}
