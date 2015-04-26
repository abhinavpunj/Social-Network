package com.social.network.dao;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.social.network.model.Notification;
import com.social.network.model.Posts;
import com.social.network.model.UserAccount;

public class NotificationDao extends DAO {

	
	public ArrayList<Notification> getUnseenNotifications(UserAccount user)
	{
		Query q = getSession().createQuery("from Notification where toUser = :personid and isseen = 0");
		q.setInteger("personid", user.getuId());
		ArrayList<Notification> notificationList = (ArrayList<Notification>) q.list();
		return notificationList;
	}
	
	public void markAsRead(UserAccount user)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query q = session.createQuery("from Notification where toUser = :personid and isseen = 0");
		q.setInteger("personid", user.getuId());
		ArrayList<Notification> notificationList = (ArrayList<Notification>) q.list();
		try
		{
			Transaction transaction = session.beginTransaction();
			for(Notification n : notificationList)
			{
				n.setSeen(true);
				session.update(n);
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

}
