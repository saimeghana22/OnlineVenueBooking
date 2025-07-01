package com.example.demo.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Venue;
import com.example.demo.service.VenueService;

@Controller
public class VenueController {

	@Autowired
	private VenueService service;
	
	@RequestMapping("/viewvenues")
	public String viewvenues(Model model,HttpServletRequest request)
	{
		List<Venue> venues=service.findAll();
		model.addAttribute("venues",venues);
		return "viewvenues";
	}
	
	@RequestMapping("/viewvenue")
	public String viewVenue(@RequestParam int id,Model model)
	{
		Venue venue=service.findById(id);
		System.out.println(venue);
		model.addAttribute("venue",venue);
		return "viewvenue";
	}

	@RequestMapping("/addvenue")
	public String addVenue(Model model)
	{
		model.addAttribute("venue",new Venue());
		return "addvenue";
	}

	@RequestMapping(value = "/savevenue",method = RequestMethod.POST)
	public String saveVenue(HttpServletRequest request, Model model,@ModelAttribute("venue") Venue venue)
	{
		// Root Directory.
		String uploadRootPath = "C:\\Users\\R.SAI MEGHANA\\OneDrive\\Desktop\\OnlineVenueBooking\\src\\main\\resources\\static\\venueimages";
		System.out.println("uploadRootPath=" + uploadRootPath);

		File uploadRootDir = new File(uploadRootPath);
		// Create directory if it not exists.
		if (!uploadRootDir.exists()) {
			uploadRootDir.mkdirs();
		}
		
		System.out.println("Real Path:"+request.getServletContext().getRealPath(""));

		MultipartFile image =venue.getImage();
		
		String name = image.getOriginalFilename();
		System.out.println("Client File Name = " + name);

		if (name != null && name.length() > 0) {
			try {
				// Create the file at server
				File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + name);

				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(image.getBytes());
				stream.close();
				
			} catch (Exception e) {
				System.out.println("Error Write file: " + name);
			}
		}
		
		venue.setImageName(name);
		
		service.save(venue);
		return "redirect:/viewvenues";
	}

	@RequestMapping("/deletevenue")
	public String deleteVenue(@RequestParam int id)
	{
		service.delete(id);
		return "redirect:/viewvenues";
	}
}

