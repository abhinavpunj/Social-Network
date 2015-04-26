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
import com.social.network.dao.PostsDao;
import com.social.network.dao.UserDao;
import com.social.network.model.Person;
import com.social.network.model.Posts;
import com.social.network.model.UserAccount;

@Controller
public class IndexController {

	@Autowired
	UserDao userDao;
	
	@Autowired
	PostsDao postDao;
	
	@Autowired
	FriendsDao friendDao;
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String openProfile(@RequestParam("personId") int personId, Model model, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		UserAccount user = (UserAccount) session.getAttribute("user");
		boolean checkIfFriend = friendDao.checkIfFriend(user, personId);
		Person person = userDao.getPersonByIds(personId);
		ArrayList<Posts> postList = postDao.getMyPosts(personId);
		model.addAttribute("posts", postList);
		model.addAttribute("person", person);
		model.addAttribute("checkIfFriend", checkIfFriend);
		return "profile";
	}
	
	@RequestMapping(value = "/infinite", method = RequestMethod.GET, produces = "application/text")
	public @ResponseBody String getInfiniteContent(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		UserAccount user = (UserAccount) session.getAttribute("user");
		String html = postDao.getInfinitePosts(user);
		
		return html;
	}

}
