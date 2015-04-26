package com.social.network;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.social.network.dao.UserDao;
import com.social.network.model.Person;
import com.social.network.model.UserAccount;

@Controller
public class ProfileController {

private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value = "/editDetails", method = RequestMethod.POST)
	public String editDetails(Model model, UserAccount user, Person person, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		UserAccount ua = (UserAccount) session.getAttribute("user");
		ua.setUsername(user.getUsername());
		ua.setPerson(person);
		userDao.editDetails(ua);
		return "redirect:/profile?personId=" + ua.getuId();
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateProfilePic(Model model, Person person, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		UserAccount ua = (UserAccount) session.getAttribute("user");
		System.out.println("11111");
		File localFile = new File("C:\\Users\\Abhinav\\Documents\\workspace-sts-3.6.4.RELEASE\\Hoppipolla\\src\\main\\webapp\\resources\\assets\\img\\profile\\", person.getProfilePic().getOriginalFilename());
		try {
			person.getProfilePic().transferTo(localFile);
			ua.getPerson().setProfilePicPath("resources/assets/img/profile/" + person.getProfilePic().getOriginalFilename());
			ua.setPerson(ua.getPerson());
			userDao.updateImage(ua.getuId(), ua.getPerson().getProfilePicPath());
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/profile?personId=" + ua.getuId();
	}
	
	
}
