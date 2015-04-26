package com.social.network.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringEscapeUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.social.network.model.Inbox;
import com.social.network.model.Person;
import com.social.network.model.UserAccount;

public class InboxDao extends DAO {

	public ArrayList<Integer> getUnreadMessages(UserAccount user)
	{
		Query q = getSession().createQuery("from Inbox where toUser = :userid and isRead = 0");
		q.setInteger("userid", user.getuId());
		ArrayList<Inbox> messageList = (ArrayList<Inbox>) q.list();
		HashSet<Integer> personIds = new HashSet<Integer>();
		
		for(Inbox i : messageList)
		{
			personIds.add(i.getFromUser().getpId());
			personIds.add(i.getToUser().getpId());
		}
		
		personIds.remove(user.getuId());
		
		ArrayList<Integer> personList = new ArrayList<Integer>(personIds);
		return personList;
	}
	
	public void markAsRead(UserAccount user)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		try
		{
			Query q = session.createQuery("from Inbox where toUser = :userid and isRead = 0");
			q.setInteger("userid", user.getuId());
			ArrayList<Inbox> messageList = (ArrayList<Inbox>) q.list();
			Transaction transaction = session.beginTransaction();
			
			for(Inbox i : messageList)
			{
				i.setRead(true);
				session.update(i);
			}
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
	
	public ArrayList<Integer> getAllMessageList(UserAccount user)
	{
		Query q = getSession().createQuery("from Inbox where toUser = :userid or fromUser = :userid");
		q.setInteger("userid", user.getuId());
		ArrayList<Inbox> messages = (ArrayList<Inbox>) q.list();
		HashSet<Integer> personIds = new HashSet<Integer>();
		
		for(Inbox i : messages)
		{
			personIds.add(i.getFromUser().getpId());
			personIds.add(i.getToUser().getpId());
		}
		
		personIds.remove(user.getuId());
		
		ArrayList<Integer> personList = new ArrayList<Integer>(personIds);
		return personList;
		
	}
	
	public String sendMessage(UserAccount user, Person person, String message)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		try
		{
			String safe_message = StringEscapeUtils.escapeHtml(message);
			Inbox inbox = new Inbox();
			inbox.setDateOfMessage(new Date());
			inbox.setFromUser(user.getPerson());
			inbox.setToUser(person);
			inbox.setMessage(safe_message);
			inbox.setRead(false);
			Transaction transaction = session.beginTransaction();
			session.save(inbox);
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
		
		String html = "<div style='background-color:#68dff0;' class='message form-panel pull-right'>" +
                        "<img src='" + user.getPerson().getProfilePicPath() + "' width=20px class='img-circle pull-left' />" +
                        "<h6>&nbsp; " + user.getPerson().getFirstName() + " " + user.getPerson().getLastName() +"</a></h6>" +
                        "<p>" + message + "</p>" +
                     "</div>";

        return html;
	}
	
	public ArrayList<Inbox> getPersonMessages(UserAccount user, int personId)
	{
		Query q = getSession().createQuery("from Inbox where (toUser = :userid and fromUser = :personid) or (toUser = :personid and fromUser = :userid) order by dateOfMessage");
		q.setInteger("userid", user.getuId());
		q.setInteger("personid", personId);
		ArrayList<Inbox> messages = (ArrayList<Inbox>) q.list();
		
		return messages;
	}
}
