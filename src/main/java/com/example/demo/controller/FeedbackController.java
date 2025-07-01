package com.example.demo.controller;

import java.util.Date;
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

import com.example.demo.model.Feedback;
import com.example.demo.service.FeedbackService;

@Controller
public class FeedbackController {

	@Autowired
	private FeedbackService service;
	
	@RequestMapping("/viewfeedbacks")
	public String viewfeedbacks(Model model)
	{
		List<Feedback> feedbacks=service.findAll();
		model.addAttribute("feedbacks",feedbacks);
		return "viewfeedbacks";
	}
	
	@RequestMapping("/viewmyFeedbacks")
	public String viewmyFeedbacks(Model model,HttpSession session)
	{
		List<Feedback> feedbacks=service.getUserFeedbacks((String)session.getAttribute("username"));
		model.addAttribute("feedbacks",feedbacks);
		return "viewfeedbacks";
	}
	
	@RequestMapping("/viewfeedback")
	public String viewFeedback(@RequestParam int id,Model model)
	{
		model.addAttribute("feedbacks",service.findById(id));
		return "viewfeedbacks";
	}

	@RequestMapping("/addfeedback")
	public String addFeedback(Model model)
	{
		model.addAttribute("feedback",new Feedback());
		return "addfeedback";
	}

	@RequestMapping(value = "/savefeedback",method = RequestMethod.POST)
	public String saveFeedback(HttpSession session, Model model,@ModelAttribute("feedback") Feedback feedback,HttpServletRequest request)
	{
		feedback.setDatetime(new Date().toString());
		feedback.setSender((String)request.getSession().getAttribute("username"));
		
		service.save(feedback);

		model.addAttribute("message","Your Feedback Posted Successfully");
		return "addfeedback";
	}

	@RequestMapping("/deletefeedback")
	public String deleteFeedback(@RequestParam int id)
	{
		service.delete(id);
		return "redirect:/viewfeedbacks";
	}
}

