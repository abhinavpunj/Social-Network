
package com.social.network;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.social.network.dao.FriendsDao;
import com.social.network.dao.InboxDao;
import com.social.network.dao.NotificationDao;
import com.social.network.dao.PostsDao;
import com.social.network.dao.UserDao;
import com.social.network.model.Notification;
import com.social.network.model.Person;
import com.social.network.model.UserAccount;

@Controller
public class FriendsController {

private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PostsDao postDao;
	
	@Autowired
	private FriendsDao friendDao;
	
	@Autowired
	private NotificationDao notificationDao;
	
	@Autowired
	private InboxDao inboxDao;
	
	
	@RequestMapping(value = "/friends", method = RequestMethod.GET)
	public String friends(Model model, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		UserAccount user = (UserAccount) session.getAttribute("user");
		model.addAttribute("user", user);
		ArrayList<Integer> friendIds = friendDao.getAllFriends(user); 
		
		ArrayList<Notification> notificationList = notificationDao.getUnseenNotifications(user);
		session.setAttribute("notificationCount", notificationList.size());
		model.addAttribute("notificationCount",notificationList.size());
		session.setAttribute("notificationList", notificationList);				
		model.addAttribute("notificationList",notificationList);
		
		ArrayList<Integer> personIds = inboxDao.getUnreadMessages(user);
		ArrayList<Person> personList1 = userDao.getPersonByIds(personIds);
		session.setAttribute("messageCount", String.valueOf(personList1.size()));
		model.addAttribute("messageCount", String.valueOf(personList1.size()));
		session.setAttribute("messageList", personList1);
		model.addAttribute("messageList", personList1);
		
		if(friendIds.size() > 0)
		{
			ArrayList<Person> personList = userDao.getPersonByIds(friendIds);
			model.addAttribute("personList", personList);
		}
		return "friends";
	}
	
	@RequestMapping(value = "/friendRequests", method = RequestMethod.GET)
	public String friendRequests(Model model, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		UserAccount user = (UserAccount) session.getAttribute("user");
		ArrayList<Integer> friendRequests = friendDao.getFriendRequests(user); 
		if(friendRequests.size() > 0)
		{
			ArrayList<Person> personList = userDao.getPersonByIds(friendRequests);
			model.addAttribute("personList", personList);
		}
		return "friendRequests";
	}
	
	@RequestMapping(value = "/pendingRequests", method = RequestMethod.GET)
	public String pendingRequests(Model model, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		UserAccount user = (UserAccount) session.getAttribute("user");
		ArrayList<Integer> pendingRequests = friendDao.getPendingRequests(user); 
		
		ArrayList<Notification> notificationList = notificationDao.getUnseenNotifications(user);
		session.setAttribute("notificationCount", notificationList.size());
		model.addAttribute("notificationCount",notificationList.size());
		session.setAttribute("notificationList", notificationList);				
		model.addAttribute("notificationList",notificationList);
		
		ArrayList<Integer> personIds = inboxDao.getUnreadMessages(user);
		ArrayList<Person> personList1 = userDao.getPersonByIds(personIds);
		session.setAttribute("messageCount", String.valueOf(personList1.size()));
		model.addAttribute("messageCount", String.valueOf(personList1.size()));
		session.setAttribute("messageList", personList1);
		model.addAttribute("messageList", personList1);
		
		
		if(pendingRequests.size() > 0)
		{
			ArrayList<Person> personList = userDao.getPersonByIds(pendingRequests);
			model.addAttribute("personList", personList);
		}
		return "pendingRequests";
	}
	
	@RequestMapping(value = "/sendRequest", method = RequestMethod.POST)
	public String sendRequest(Model model, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		UserAccount user = (UserAccount) session.getAttribute("user");
		String personId = (String) request.getParameter("personId");
		friendDao.sendFriendRequest(user, Integer.parseInt(personId));
		return "redirect:/index";
	}
	
	@RequestMapping(value = "/acceptRequest", method = RequestMethod.POST)
	public String acceptRequest(@RequestParam("personId") String personId, Model model, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		UserAccount user = (UserAccount) session.getAttribute("user");
		friendDao.acceptRequest(user, Integer.parseInt(personId));
		return "redirect:/index";
	}
	
	@RequestMapping(value = "/unFriend", method = RequestMethod.POST)
	public String delete(@RequestParam("personId") String personId, Model model, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		UserAccount user = (UserAccount) session.getAttribute("user");
		friendDao.unFriend(user, Integer.parseInt(personId));
		return "redirect:/index";
	}
}
