package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Venue;
import com.example.demo.repository.VenueRepository;

@Service
public class VenueService {

	@Autowired
	private VenueRepository repo;
	
	public Venue save(Venue venue)
	{
		return repo.save(venue);
	}
	
	public List<Venue> findAll()
	{
		return repo.findAll();
	}
	
	public Venue findById(int id)
	{
		return repo.findById(id).get();
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
