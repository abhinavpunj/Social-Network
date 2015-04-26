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
import com.social.network.dao.NotificationDao;
import com.social.network.dao.PostsDao;
import com.social.network.dao.UserDao;
import com.social.network.model.Notification;
import com.social.network.model.Posts;
import com.social.network.model.UserAccount;

@Controller
public class PostController {

private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PostsDao postDao;
	
	@Autowired
	private FriendsDao friendDao;
	
	@Autowired
	private NotificationDao notificationDao;
	
	@RequestMapping(value = "/addPosts", method = RequestMethod.POST)
	public String sharePosts(Model model, Posts post, HttpServletRequest request)
	{
		if(post!=null || post.getStatus() != null || !post.getStatus().isEmpty())
		{
			HttpSession session = request.getSession();
			UserAccount user = (UserAccount) session.getAttribute("user");
			post.setPerson(user.getPerson());
			postDao.addPost(post);
			ArrayList<Integer> friendList = friendDao.getAllFriends(user);
			ArrayList<Posts> postList = postDao.getFriendPosts(user, friendList);
			model.addAttribute("posts", postList);
		}
		return "redirect:/index";
	}
	
	@RequestMapping(value = "/addLike", method = RequestMethod.GET, produces = "application/text")
	public @ResponseBody String like(@RequestParam("id") String id, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		UserAccount user = (UserAccount) session.getAttribute("user");
		int postId = Integer.parseInt(id);
		int likes = postDao.addLikes(postId, user);
		return String.valueOf(likes);
	}
	
	@RequestMapping(value = "/addComment", method = RequestMethod.GET, produces = "application/text")
	public @ResponseBody String comment(@RequestParam("id") String id, @RequestParam("comment") String comment, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		UserAccount user = (UserAccount) session.getAttribute("user");
		int postId = Integer.parseInt(id);
		String html = postDao.addComments(postId, comment, user);
		return html;
	}
	
	@RequestMapping(value = "/markRead", method = RequestMethod.GET, produces = "application/text")
	public @ResponseBody String markRead(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		UserAccount user = (UserAccount) session.getAttribute("user");
		notificationDao.markAsRead(user);
		return "0";
	}
	
	@RequestMapping(value = "/post", method = RequestMethod.GET)
	public String getPost(Model model, @RequestParam("postId") String postId, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		UserAccount user = (UserAccount) session.getAttribute("user");
		Posts post = postDao.getPost(user, Integer.parseInt(postId));
		
		ArrayList<Notification> notificationList = notificationDao.getUnseenNotifications(user);
		session.setAttribute("notificationCount", String.valueOf(notificationList.size()));
		session.setAttribute("notificationList", notificationList);
		
		model.addAttribute("post", post);
		
		return "post";
	}
}
