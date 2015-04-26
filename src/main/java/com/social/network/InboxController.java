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
import org.springframework.web.bind.annotation.ResponseBody;

import com.social.network.dao.FriendsDao;
import com.social.network.dao.InboxDao;
import com.social.network.dao.PostsDao;
import com.social.network.dao.UserDao;
import com.social.network.model.Inbox;
import com.social.network.model.Person;
import com.social.network.model.UserAccount;

@Controller
public class InboxController {
private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PostsDao postDao;
	
	@Autowired
	private FriendsDao friendDao;
	
	@Autowired
	private InboxDao inboxDao;
	
	@RequestMapping(value = "/inbox", method = RequestMethod.GET)
	public String getInbox(Model model, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		UserAccount user = (UserAccount) session.getAttribute("user");
		ArrayList<Integer> personIds = inboxDao.getAllMessageList(user);
		ArrayList<Person> personList = userDao.getPersonByIds(personIds);
		model.addAttribute("personList", personList);
		
		return "inbox";
	}
	
	@RequestMapping(value = "/getMessage", method = RequestMethod.GET)
	public String getMessages(@RequestParam("personId") int personId, Model model, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		UserAccount user = (UserAccount) session.getAttribute("user");
		ArrayList<Inbox> messages = inboxDao.getPersonMessages(user, personId);
		Person person = userDao.getPersonByIds(personId);
		model.addAttribute("messages", messages);
		model.addAttribute("person", person);
		return "messages";
	}
	
	@RequestMapping(value = "/sendMessage", method = RequestMethod.GET, produces = "application/text")
	public @ResponseBody String sendMessage(@RequestParam("message") String message, @RequestParam("personId") String personId, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		UserAccount user = (UserAccount) session.getAttribute("user");
		Person person = userDao.getPersonByIds(Integer.parseInt(personId));
		String html = inboxDao.sendMessage(user, person, message);
		return html;
	}
	
	@RequestMapping(value = "/markMessageRead", method = RequestMethod.GET, produces = "application/text")
	public @ResponseBody String markMessageRead(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		UserAccount user = (UserAccount) session.getAttribute("user");
		inboxDao.markAsRead(user);
		session.setAttribute("messageCount", "0");
		return "0";
	}
}
