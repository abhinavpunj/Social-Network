package com.social.home;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.social.network.dao.HibernateUtil;
import com.social.network.model.Friends;
import com.social.network.model.Inbox;
import com.social.network.model.Person;
import com.social.network.model.UserAccount;
import com.social.network.model.UserRole;


public class TestMain {

	public static void main(String args[])
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		Transaction t = session.beginTransaction();
		
		//*******************create user role*************************
				UserRole role = new UserRole();
				role.setRole("user");		
				
				//*******************Create person*******************************
				Person abhinav = new Person();
				abhinav.setFirstName("Abhinav");
				abhinav.setLastName("Punj");
				abhinav.setGender("Male");
				abhinav.setEmail("abhinavpunj@gmail.com");
				abhinav.setDob(new Date(1990-10-04));
				
				//*******************Create useraccount*******************************
				UserAccount userAbhinav = new UserAccount();
				userAbhinav.setUsername("abhinavpunj");
				userAbhinav.setPassword("123");
				userAbhinav.setPerson(abhinav);
				userAbhinav.setRole(role);
				
				
				
				//*******************Create person*******************************
				Person apoorva = new Person();
				apoorva.setFirstName("Apoorva");
				apoorva.setLastName("Joshi");
				apoorva.setGender("Female");
				apoorva.setEmail("apoorvajoshi@yahoo.com");
				apoorva.setDob(new Date(1991-04-12));
				
				//*******************Create useraccount*******************************
				UserAccount userApoorva = new UserAccount();
				userApoorva.setUsername("apoorvajoshi");
				userApoorva.setPassword("123");
				userApoorva.setPerson(apoorva);
				userApoorva.setRole(role);
				
				
				
				//*******************Create person*******************************
				Person harshit = new Person();
				harshit.setFirstName("Harshit");
				harshit.setLastName("Shah");
				harshit.setGender("Male");
				harshit.setEmail("harshitshah@gmail.com");
				harshit.setDob(new Date(1991-02-27));
				
				//*******************Create useraccount*******************************
				UserAccount userHarshit = new UserAccount();
				userHarshit.setUsername("harshitshah");
				userHarshit.setPassword("123");
				userHarshit.setPerson(harshit);
				userHarshit.setRole(role);
				
				
				//*******************Create person*******************************
				Person emily = new Person();
				emily.setFirstName("Emily");
				emily.setLastName("Perry");
				emily.setGender("Female");
				emily.setEmail("emilyperry@yahoo.com");
				emily.setDob(new Date(1988-12-26));
				
				//*******************Create useraccount*******************************
				UserAccount userEmily = new UserAccount();
				userEmily.setUsername("emilyperry");
				userEmily.setPassword("123");
				userEmily.setPerson(emily);
				userEmily.setRole(role);
				
				
				
				//*******************Create person*******************************
				Person john = new Person();
				john.setFirstName("John");
				john.setLastName("Bing");
				john.setGender("Male");
				john.setEmail("johnbing@yahoo.com");
				john.setDob(new Date(1985-11-11));
				
				//*******************Create useraccount*******************************
				UserAccount userJohn = new UserAccount();
				userJohn.setUsername("johnbing");
				userJohn.setPassword("123");
				userJohn.setPerson(john);
				userJohn.setRole(role);
				
				session.save(role);
		        session.save(userAbhinav);
		        session.save(userApoorva);
		        session.save(userHarshit);
		        session.save(userJohn);
		        session.save(userEmily);
		
		t.commit();
		session.close();
		//after close session detach state
		System.out.println("success");
	}
}
