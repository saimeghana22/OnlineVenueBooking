package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.form.Booking;
import com.example.demo.model.User;
import com.example.demo.model.Venue;
import com.example.demo.model.VenueBooking;
import com.example.demo.repository.VenueBookingRepository;

@Service
public class VenueBookingService {

	@Autowired
	private VenueBookingRepository repo;
	
	@Autowired
	private UserService userservice;
	
	@Autowired
	private VenueService venueservice;
	
	public VenueBooking save(VenueBooking venueBooking)
	{
		return repo.save(venueBooking);
	}
	
	public List<VenueBooking> findAll()
	{
		return repo.findAll();
	}
	
	public List<Booking> getAllBookings()
	{
		List<Booking> bookings=new ArrayList<Booking>();
		
		for(VenueBooking venueBooking: repo.findAll())
		{
			User user=userservice.findByUserName(venueBooking.getUsername());
			Venue venue=venueservice.findById(venueBooking.getVid());
			
			Booking booking=new Booking();
			
			booking.setVenuename(venue.getName());
			booking.setEventname(venueBooking.getEventname());
			booking.setDate(venueBooking.getDate());
			booking.setCateringtype(venueBooking.getCateringtype());
			booking.setCateringcount(venueBooking.getCateringcount());
			booking.setServices(venueBooking.getServices());
			booking.setUsername(venueBooking.getUsername());
			booking.setCustomername(user.getName());
			booking.setMobile(user.getMobile());
			booking.setAddress(user.getAddress());
			
			int price=0;
			
			for(String service : venueBooking.getServices().split(","))
			{
				if(service.equals("decoration"))
				{
					price=price+Integer.parseInt(venue.getDecorationprice());
				}
				
				if(service.equals("mackup"))
				{
					price=price+Integer.parseInt(venue.getMackupprice());
				}
				
				if(service.equals("photographer"))
				{
					price=price+Integer.parseInt(venue.getPhotographerprice());
				}
				
				price=price+venue.getPrice();
			}
			
			if(venueBooking.getCateringtype().equals("veg"))
			{
				price=price+(Integer.parseInt(venue.getVegfoodprice())*Integer.parseInt(venueBooking.getCateringcount()));
			}
			else if(venueBooking.getCateringtype().equals("nonveg"))
			{
				price=price+(Integer.parseInt(venue.getNonvegfoodprice())*Integer.parseInt(venueBooking.getCateringcount()));
			}
			
			booking.setPrice(price);
			
			bookings.add(booking);
		}
		
		return bookings;
	}
	
	public List<Booking> getUserBookings(String username)
	{
		List<Booking> bookings=new ArrayList<Booking>();
		
		for(VenueBooking venueBooking: repo.findAll())
		{
			if(venueBooking.getUsername().equals(username))
			{
				User user=userservice.findByUserName(venueBooking.getUsername());
				Venue venue=venueservice.findById(venueBooking.getVid());
				
				Booking booking=new Booking();
				
				booking.setVenuename(venue.getName());
				booking.setEventname(venueBooking.getEventname());
				booking.setDate(venueBooking.getDate());
				booking.setCateringtype(venueBooking.getCateringtype());
				booking.setCateringcount(venueBooking.getCateringcount());
				booking.setServices(venueBooking.getServices());
				booking.setUsername(venueBooking.getUsername());
				booking.setCustomername(user.getName());
				booking.setMobile(user.getMobile());
				booking.setAddress(user.getAddress());
				
				int price=0;
				
				for(String service : venueBooking.getServices().split(","))
				{
					if(service.equals("decoration"))
					{
						price=price+Integer.parseInt(venue.getDecorationprice());
					}
					
					if(service.equals("mackup"))
					{
						price=price+Integer.parseInt(venue.getMackupprice());
					}
					
					if(service.equals("photographer"))
					{
						price=price+Integer.parseInt(venue.getPhotographerprice());
					}
					
					price=price+venue.getPrice();
				}
				
				if(venueBooking.getCateringtype().equals("veg"))
				{
					price=price+(Integer.parseInt(venue.getVegfoodprice())*Integer.parseInt(venueBooking.getCateringcount()));
				}
				else if(venueBooking.getCateringtype().equals("nonveg"))
				{
					price=price+(Integer.parseInt(venue.getNonvegfoodprice())*Integer.parseInt(venueBooking.getCateringcount()));
				}
				
				booking.setPrice(price);
				
				bookings.add(booking);
			}
		}
		
		return bookings;
	}
	
	public List<Booking> getVenueBookings(int venueid)
	{
		List<Booking> bookings=new ArrayList<Booking>();
		
		for(VenueBooking venueBooking: repo.findAll())
		{
			if(venueBooking.getVid()==venueid)
			{
				User user=userservice.findByUserName(venueBooking.getUsername());
				Venue venue=venueservice.findById(venueBooking.getVid());
				
				Booking booking=new Booking();
				
				booking.setVenuename(venue.getName());
				booking.setEventname(venueBooking.getEventname());
				booking.setDate(venueBooking.getDate());
				booking.setCateringtype(venueBooking.getCateringtype());
				booking.setCateringcount(venueBooking.getCateringcount());
				booking.setServices(venueBooking.getServices());
				booking.setUsername(venueBooking.getUsername());
				booking.setCustomername(user.getName());
				booking.setMobile(user.getMobile());
				booking.setAddress(user.getAddress());
				
				int price=0;
				
				for(String service : venueBooking.getServices().split(","))
				{
					if(service.equals("decoration"))
					{
						price=price+Integer.parseInt(venue.getDecorationprice());
					}
					
					if(service.equals("mackup"))
					{
						price=price+Integer.parseInt(venue.getMackupprice());
					}
					
					if(service.equals("photographer"))
					{
						price=price+Integer.parseInt(venue.getPhotographerprice());
					}
					
					price=price+venue.getPrice();
				}
				
				if(venueBooking.getCateringtype().equals("veg"))
				{
					price=price+(Integer.parseInt(venue.getVegfoodprice())*Integer.parseInt(venueBooking.getCateringcount()));
				}
				else if(venueBooking.getCateringtype().equals("nonveg"))
				{
					price=price+(Integer.parseInt(venue.getNonvegfoodprice())*Integer.parseInt(venueBooking.getCateringcount()));
				}
				
				booking.setPrice(price);
				
				bookings.add(booking);
			}
		}
		
		return bookings;
	}
	
	public Booking getById(int id)
	{
		VenueBooking venueBooking=repo.findById(id).get();
		
		User user=userservice.findByUserName(venueBooking.getUsername());
		Venue venue=venueservice.findById(venueBooking.getVid());
		
		Booking booking=new Booking();
		
		booking.setVenuename(venue.getName());
		booking.setEventname(venueBooking.getEventname());
		booking.setDate(venueBooking.getDate());
		booking.setCateringtype(venueBooking.getCateringtype());
		booking.setCateringcount(venueBooking.getCateringcount());
		booking.setServices(venueBooking.getServices());
		booking.setUsername(venueBooking.getUsername());
		booking.setCustomername(user.getName());
		booking.setMobile(user.getMobile());
		booking.setAddress(user.getAddress());
		
		int price=0;
		
		for(String service : venueBooking.getServices().split(","))
		{
			if(service.equals("decoration"))
			{
				price=price+Integer.parseInt(venue.getDecorationprice());
			}
			
			if(service.equals("mackup"))
			{
				price=price+Integer.parseInt(venue.getMackupprice());
			}
			
			if(service.equals("photographer"))
			{
				price=price+Integer.parseInt(venue.getPhotographerprice());
			}
			
			price=price+venue.getPrice();
		}
		
		if(venueBooking.getCateringtype().equals("veg"))
		{
			price=price+(Integer.parseInt(venue.getVegfoodprice())*Integer.parseInt(venueBooking.getCateringcount()));
		}
		else if(venueBooking.getCateringtype().equals("nonveg"))
		{
			price=price+(Integer.parseInt(venue.getNonvegfoodprice())*Integer.parseInt(venueBooking.getCateringcount()));
		}
		
		booking.setPrice(price);
		
		return booking;
	}
	
	public VenueBooking findById(int id)
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
