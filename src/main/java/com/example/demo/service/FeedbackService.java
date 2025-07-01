package com.example.demo.service;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Feedback;
import com.example.demo.repository.FeedbackRepository;

@Service
public class FeedbackService {

	@Autowired
	private FeedbackRepository repo;
	
	public Feedback save(Feedback feedback)
	{
		return repo.save(feedback);
	}
	
	public List<Feedback> findAll()
	{
		return repo.findAll();
	}
	
	public Feedback findById(int id)
	{
		return repo.findById(id).get();
	}
	
	public List<Feedback> getUserFeedbacks(String username)
	{
		List<Feedback> feedbacks=new ArrayList<Feedback>();
		
		for(Feedback feedback: repo.findAll())
		{
			if(feedback.getSender().equals(username))
			{
				feedbacks.add(feedback);
			}
		}
		
		return feedbacks;
	}
	
	public void delete(int id)
	{
		repo.deleteById(id);
	}	
	
	public boolean isExist(int id)
	{
		return repo.existsById(id);
	}
}
