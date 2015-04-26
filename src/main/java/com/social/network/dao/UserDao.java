package com.social.network.dao;


import java.util.ArrayList;

import org.apache.commons.lang.StringEscapeUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.social.network.model.Friends;
import com.social.network.model.Person;
import com.social.network.model.UserAccount;



public class UserDao extends DAO {

	   /*
	    * Since its a query, it is not necessary to wrap code
	    * inside the begin transaction and commit part, unlike
	    * update, create, and delete. Since there is no object
	    * in the session yet, nothing will be committed.  After
	    * Adding these, "transaction is not successfully started'
	    * Exception will be thrown.
	    */
	
		public UserAccount loadUserByUserName(String username)
		{
			UserAccount user = null;
			try
			{
				Query q = getSession().createQuery("from UserAccount where username = :username");
		        q.setString("username", username);
		        user = (UserAccount) q.uniqueResult();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
	        return user;

		}
		public UserAccount queryUserByNameAndPassword(String username, String password)
	            throws Exception {
	        try 
	        {
	        	Query q = getSession().createQuery("from UserAccount where username = :username1 and password = :password1");
		            
		        q.setString("username1", username);
		        q.setString("password1", password);
		        UserAccount user = (UserAccount) q.uniqueResult();
		        System.out.println(user.getuId());
	
		        return user;
	        } 
	        catch (HibernateException e) 
	        {
	            throw new Exception("Could not get user " + username, e);
	        }
	    }
		
		
		public void registerNewUsers(UserAccount user)
		{
			Session session = HibernateUtil.getSessionFactory().openSession();
			try
			{	
				Transaction transaction = session.beginTransaction();
				
				String username = StringEscapeUtils.escapeHtml(user.getUsername());
				String firstname = StringEscapeUtils.escapeHtml(user.getPerson().getFirstName());
				String lastname = StringEscapeUtils.escapeHtml(user.getPerson().getLastName());
				String email = StringEscapeUtils.escapeHtml(user.getPerson().getEmail());
				String password = 	StringEscapeUtils.escapeHtml(user.getPassword());
				
				user.setUsername(username);
				user.getPerson().setFirstName(firstname);
				user.getPerson().setLastName(lastname);
				user.getPerson().setEmail(email);
				user.setPassword(password);
				
				session.save(user);
				transaction.commit();
				
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			finally
			{
				session.close();
			}
		}
		
		public void editDetails(UserAccount user)
		{
			Session session = HibernateUtil.getSessionFactory().openSession();
			try 
			{	
				Transaction transaction = session.beginTransaction();
				
				String username = StringEscapeUtils.escapeHtml(user.getUsername());
				String firstname = StringEscapeUtils.escapeHtml(user.getPerson().getFirstName());
				String lastname = StringEscapeUtils.escapeHtml(user.getPerson().getLastName());
				String email = StringEscapeUtils.escapeHtml(user.getPerson().getEmail());
								
				user.setUsername(username);
				user.getPerson().setFirstName(firstname);
				user.getPerson().setLastName(lastname);
				user.getPerson().setEmail(email);

				
				session.update(user);
				transaction.commit();				
			}
			catch(Exception e)
			{
				System.out.println("Could not Update user details");
				e.printStackTrace();
			}
			finally
			{
				session.close();
			}
		}
		
		public String searchAll(UserAccount user, String value, ArrayList<Friends> friendList)
		{
			Query q = getSession().createQuery("from Person where (firstname LIKE :value OR lastname LIKE :value) and personid != :personid");
			q.setString("value", value + "%");
			q.setInteger("personid", user.getuId());
			ArrayList<Person> result = (ArrayList<Person>) q.list();
			StringBuilder html = new StringBuilder();
			

			for(Person p : result)
			{
				String action = "sendRequest";
				String btnFriend= "<button type='submit' class='pull-right btn btn-primary btn-sm'>Add Friend</button></h3></form>";
				String decline = "";
				
				for(Friends f : friendList)
				{
					if( ((p.getpId() == f.getToUser()) || (p.getpId() == f.getFromUser())) && f.isAccepted())
					{
						decline = "<a href='getMessage?personId=" + p.getpId() + "' class='pull-right btn btn-warning btn-sm'>Send Message</a></h3>" +
								"</form>";
						action = "unFriend";
						btnFriend = "<button type='submit' class='pull-right btn btn-danger btn-sm'>UnFriend</button></form>";
						break;
					}
					else if( (p.getpId() == f.getToUser()) && !f.isAccepted())
					{
						decline = "";
						action = "profile";
						btnFriend = "<button type='submit' disabled class='pull-right btn btn-primary btn-sm'>Friend Request Sent</button></h3></form>";
						break;
					}
					else if( (p.getpId() == f.getFromUser()) && !f.isAccepted())
					{
						action = "acceptRequest";
						btnFriend = "<button type='submit' class='pull-right btn btn-primary btn-sm'>Accept</button></form>";
						decline = "<form method='POST' class='pull-right' action='unFriend'>" +
								"<button type='submit' name='submitDecline' class='pull-right btn btn-danger btn-sm'>Decline</button></h3>" +
								"<input type='hidden' name='personId' value='" + p.getpId() + "' />" +
								"</form>";
						break;
					}
				}
				
					html.append("<div class='row mtpost'>" +
						"<div class='form-panel'>" +
						"<form method='POST' action='"+ action +"'>"
						+ ""
						+ "<input type='hidden' name='personId' value='"+ p.getpId() +"'/>" +
						"<h3><i class='fa fa-angle-right'></i><a href='profile?personId="+ p.getpId() +"'><img src='" + p.getProfilePicPath() + "' width=150 height=150 class='img-circle' /> " + p.getFirstName() + " " + p.getLastName() +
						"</a>" + btnFriend + decline + "</div></div>");
				
			}
			return html.toString();
		}
		
		
		public ArrayList<Person> getPersonByIds(ArrayList<Integer> personIds)
		{
			ArrayList<Person> persons;
		
			if(personIds.size() > 0)
			{
				Query q = getSession().createQuery("from Person where personid in (:personIds)");
				q.setParameterList("personIds", personIds);
				persons = (ArrayList<Person>) q.list();
			}
			else
				persons = new ArrayList<Person>();
			
			return persons;
		}
		
		public Person getPersonByIds(int personId)
		{
			Query q = getSession().createQuery("from Person where personid = :personId");
			q.setInteger("personId", personId);
			Person person = (Person) q.uniqueResult();
			return person;
		}
		
		public void updateImage(int personId, String path)
		{
			Session session = HibernateUtil.getSessionFactory().openSession();
			try
			{
				Transaction transaction = session.beginTransaction();
				Query q = session.createQuery("update Person set profilepicpath = :path where personid = :personid");
				q.setString("path", path);
				q.setInteger("personid", personId);
				q.executeUpdate();
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
