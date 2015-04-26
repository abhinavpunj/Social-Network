package com.social.network;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.social.network.dao.FriendsDao;
import com.social.network.dao.UserDao;
import com.social.network.model.Friends;
import com.social.network.model.UserAccount;

@Controller
public class SearchController {

private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private FriendsDao friendDao;
	
	@RequestMapping(value = "/search", method = RequestMethod.GET, produces="application/text")
	public @ResponseBody String search(@RequestParam("search") String value, HttpServletRequest request)
	{
		if(value!=null || !value.isEmpty())
		{
			HttpSession session = request.getSession();
			UserAccount user = (UserAccount) session.getAttribute("user");
			ArrayList<Friends> friendList = friendDao.getFriendsandRequests(user);
			String html = userDao.searchAll(user, value, friendList);
			return html;
		}
		
		return " ";
	}
}
