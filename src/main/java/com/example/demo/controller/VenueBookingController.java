package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.form.Booking;
import com.example.demo.model.VenueBooking;
import com.example.demo.service.VenueBookingService;

@Controller
public class VenueBookingController {

	@Autowired
	private VenueBookingService service;
	
	@RequestMapping("/viewvenueBookings")
	public String viewvenueBookings(Model model,@RequestParam int venueid)
	{
		List<Booking> bookings=service.getVenueBookings(venueid);
		model.addAttribute("bookings",bookings);
		return "viewbookings";
	}
	
	@RequestMapping("/viewuserBookings")
	public String viewuserBookings(Model model,@RequestParam String username)
	{
		List<Booking> bookings=service.getUserBookings(username);
		model.addAttribute("bookings",bookings);
		return "viewbookings";
	}
	
	@RequestMapping("/viewmyBookings")
	public String viewmyBookings(Model model,HttpSession session)
	{
		List<Booking> bookings=service.getUserBookings((String)session.getAttribute("username"));
		model.addAttribute("bookings",bookings);
		return "viewbookings";
	}
	
	@RequestMapping("/viewvenueBooking")
	public String viewVenueBooking(@RequestParam int id,Model model)
	{
		model.addAttribute("bookings",service.getById(id));
		return "viewbookings";
	}

	@RequestMapping("/addvenueBooking")
	public String addVenueBooking(Model model,@RequestParam int venueid,HttpSession session)
	{
		model.addAttribute("venueBooking",new VenueBooking());
		session.setAttribute("venueid",venueid);
		return "addvenuebooking";
	}

	@RequestMapping(value = "/savevenueBooking",method = RequestMethod.POST)
	public String saveVenueBooking(HttpSession session, Model model,@ModelAttribute("venueBooking") VenueBooking venueBooking)
	{
		int venueid=(int)session.getAttribute("venueid");
	
		boolean isBooked=false;
		
		for(VenueBooking vb : service.findAll())
		{
			if(vb.getVid()==venueid && vb.getDate().equals(venueBooking.getDate()))
			{
				isBooked=true;
			}
		}
		
		if(!isBooked)
		{
			venueBooking.setVid(venueid);
			venueBooking.setUsername((String)session.getAttribute("username"));
			
			service.save(venueBooking);
			return "redirect:/viewmyBookings";
		}
		else
		{
			return "redirect:/viewvenues";
		}	
	}

	@RequestMapping("/deletevenueBooking")
	public String deleteVenueBooking(@RequestParam int id)
	{
		VenueBooking booking=service.findById(id);
		
		service.delete(id);
		return "redirect:/viewvenueBookings?venueid="+booking.getVid();
	}
}

