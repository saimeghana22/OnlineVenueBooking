package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.User;
import com.example.demo.service.MailService;
import com.example.demo.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService service;

	@RequestMapping("/login")
	public String login(Model model)
	{
		return "login";
	}

	@RequestMapping("/loginaction")
	public String loginAction(@RequestParam String username,@RequestParam String password,Model model,HttpSession session)
	{
		User user=service.isValidUser(username, password);
		
		if(user!=null)
		{
			session.setAttribute("username",username);
			session.setAttribute("role",user.getRole());
			
			return "redirect:/viewvenues";
		}
		else
		{
			model.addAttribute("message","Invalid Login Credentials");
			return "login";
		}
	}

	@RequestMapping(value = "/register")
	public String register(Model model)
	{
		model.addAttribute("user",new User());
		return "register";
	}
	
	@RequestMapping(value = "/regaction",method = RequestMethod.POST)
	public String regAction(@ModelAttribute("user") User user,Model model)
	{
		if(!service.isExist(user.getUsername()))
		{
			user.setRole("user");
			service.save(user);
			return "login";
		}
		else
		{
			model.addAttribute("message","User Allready Exist");
			return "register";
		}
	}
	
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session,Model model)
	{
		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping("/forgotpassword")
	public String login()
	{
		return "forgotpassword";
	}
	
	@RequestMapping("/getpassword")
	public String getPassword(@RequestParam String username,Model model)
	{
		String message="";
		
		User user=service.findByUserName(username);
		
		if(user!=null)
		{
			try {
				
				MailService.mailsend("Your Farmmatrix Account Password",user.getPassword(),user.getUsername());
				
				message="Password Sent to Your Registered Email ID";
				
			} catch (Exception e) {
				e.printStackTrace();
				message="Mail Sending Failed";
			}
		}
		else
		{
			message="Your Account Not Found";
		}
		
		model.addAttribute("message",message);
		return "forgotpassword";
	}
}

