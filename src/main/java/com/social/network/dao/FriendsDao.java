package com.social.network.dao;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.social.network.model.Friends;
import com.social.network.model.Person;
import com.social.network.model.UserAccount;

public class FriendsDao extends DAO {

	public ArrayList<Integer> getAllFriends(UserAccount user)
	{
		Query q = getSession().createQuery("from Friends where (fromUser = :personid or toUser = :personid) and isAccepted = 1");
		q.setInteger("personid", user.getuId());
		ArrayList<Friends> friendList = (ArrayList<Friends>) q.list();
		ArrayList<Integer> friendIds = new ArrayList<Integer>();
		
		for(Friends f : friendList)
		{
			if(f.getFromUser() != user.getuId())
			{
				friendIds.add(f.getFromUser());
			}
			if(f.getToUser() != user.getuId())
			{
				friendIds.add(f.getToUser());
			}
		}
		
		return friendIds;
	}
	
	public ArrayList<Friends> getFriendsandRequests(UserAccount user)
	{
		Query q = getSession().createQuery("from Friends where (fromUser = :personid or toUser = :personid)");
		q.setInteger("personid", user.getuId());
		ArrayList<Friends> friendList = (ArrayList<Friends>) q.list();
		ArrayList<Friends> friends = new ArrayList<Friends>();
		
		for(Friends f : friendList)
		{
			if(f.getFromUser() != user.getuId())
			{
				friends.add(f);
			}
			if(f.getToUser() != user.getuId())
			{
				friends.add(f);
			}
		}
		
		return friends;
	}
	
	public ArrayList<Integer> getFriendRequests(UserAccount user)
	{
		Query q = getSession().createQuery("select fromUser from Friends where toUser = :personid and isAccepted = 0");
		q.setInteger("personid", user.getuId());
		ArrayList<Integer> friendRequests = (ArrayList<Integer>) q.list();
		
		return friendRequests;
	}
	
	public ArrayList<Integer> getPendingRequests(UserAccount user)
	{
		Query q = getSession().createQuery("select toUser from Friends where fromUser = :personid and isAccepted = 0");
		q.setInteger("personid", user.getuId());
		ArrayList<Integer> pendingRequests = (ArrayList<Integer>) q.list();
		
		return pendingRequests;
	}
	
	public void sendFriendRequest(UserAccount user, int personId)
	{
		Friends friend = new Friends();
		friend.setFromUser(user.getuId());
		friend.setToUser(personId);
		friend.setAccepted(false);
		Session session = HibernateUtil.getSessionFactory().openSession();
		try
		{
			Transaction transaction = session.beginTransaction();
			session.save(friend);
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
	
	public void acceptRequest(UserAccount user, int personId)
	{
		Query q = getSession().createQuery("from Friends where fromUser = :personId and toUser = :userId");
		q.setInteger("personId", personId);
		q.setInteger("userId", user.getuId());
		Friends friend = (Friends) q.uniqueResult();
		friend.setAccepted(true);
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.update(friend);
		transaction.commit();
		session.close();
	}
	
	public void unFriend(UserAccount user, int personId)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		try
		{
			Transaction transaction = session.beginTransaction();
			Query q = session.createQuery("delete from Friends where (toUser = :userid or fromUser = :userid) and (toUser = :personid or fromUser = :personid)");
			q.setInteger("userid", user.getuId());
			q.setInteger("personid", personId);
			int result = q.executeUpdate();
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
	
	public boolean checkIfFriend(UserAccount user, int personId)
	{
		ArrayList<Integer> friendIds = getAllFriends(user);
		for(int f : friendIds)
		{
			if(personId == f)
				return true;
		}
		
		return false;
	}
}
