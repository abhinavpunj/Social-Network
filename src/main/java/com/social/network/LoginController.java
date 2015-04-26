package com.social.network;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.social.network.dao.FriendsDao;
import com.social.network.dao.InboxDao;
import com.social.network.dao.NotificationDao;
import com.social.network.dao.PostsDao;
import com.social.network.dao.UserDao;
import com.social.network.model.Friends;
import com.social.network.model.Notification;
import com.social.network.model.Person;
import com.social.network.model.Posts;
import com.social.network.model.UserAccount;


/**
 * Handles requests for the application home page.
 */
@Controller
public class LoginController {
	
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
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		UserAccount userAccount = new UserAccount();
		model.addAttribute("userAccount", userAccount);
		return "login";
	}

	@RequestMapping(value = "/index", method = RequestMethod.POST)
	public String login(Model model,@Valid UserAccount user, BindingResult result, HttpServletRequest request)
	{
		if(result.hasErrors()){
			return "login";
			
		}
		else{
		try {
			UserAccount ua = userDao.queryUserByNameAndPassword(user.getUsername(), user.getPassword());
			if(ua != null)
			{
				model.addAttribute("userAccount", ua);
				HttpSession session = request.getSession();
				session.setAttribute("user", ua);
				
				ArrayList<Integer> friendList = friendDao.getAllFriends(ua);
				ArrayList<Posts> postList = postDao.getFriendPosts(ua, friendList);
				model.addAttribute("posts", postList);
				
				ArrayList<Notification> notificationList = notificationDao.getUnseenNotifications(ua);
				
				session.setAttribute("notificationCount", notificationList.size());
				model.addAttribute("notificationCount",notificationList.size());
				session.setAttribute("notificationList", notificationList);				
				model.addAttribute("notificationList",notificationList);
				ArrayList<Integer> personIds = inboxDao.getUnreadMessages(ua);
				ArrayList<Person> personList = userDao.getPersonByIds(personIds);
				session.setAttribute("messageCount", String.valueOf(personList.size()));
				session.setAttribute("messageList", personList);
				
				return "index";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "login";
		}
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String openIndex(Model model, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		UserAccount ua = (UserAccount) session.getAttribute("user");
		
		ArrayList<Integer> friendList = friendDao.getAllFriends(ua);
		ArrayList<Posts> postList = postDao.getFriendPosts(ua, friendList);
		model.addAttribute("posts", postList);
		
		ArrayList<Notification> notificationList = notificationDao.getUnseenNotifications(ua);
		session.setAttribute("notificationCount", String.valueOf(notificationList.size()));
		session.setAttribute("notificationList", notificationList);
		
		ArrayList<Integer> personIds = inboxDao.getUnreadMessages(ua);
		ArrayList<Person> personList = userDao.getPersonByIds(personIds);
		session.setAttribute("messageCount", String.valueOf(personList.size()));
		session.setAttribute("messageList", personList);
		
		return "index";
	}
	
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerNewUsers(Model model, UserAccount user, Person person, HttpServletRequest request)
	{
		user.setPerson(person);
		userDao.registerNewUsers(user);
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		return "redirect:/index";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping(value = "/uniqueUsername", method = RequestMethod.GET, produces = "application/text")
	public @ResponseBody String uniqueUsername(@RequestParam("username") String username)
	{
		UserAccount user = userDao.loadUserByUserName(username);
		if(user != null)
		{
			return "false";
		}
		return "true";
	}
	
}
