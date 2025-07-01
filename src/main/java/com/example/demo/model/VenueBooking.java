package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class VenueBooking {

	private int id;
	private String eventname;
	private String username;
	private int vid;
	private String date;
	private String cateringtype;
	private String cateringcount;
	private String services;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEventname() {
		return eventname;
	}
	public void setEventname(String eventname) {
		this.eventname = eventname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getVid() {
		return vid;
	}
	public void setVid(int vid) {
		this.vid = vid;
	}
	public String getCateringtype() {
		return cateringtype;
	}
	public void setCateringtype(String cateringtype) {
		this.cateringtype = cateringtype;
	}
	public String getCateringcount() {
		return cateringcount;
	}
	public void setCateringcount(String cateringcount) {
		this.cateringcount = cateringcount;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getServices() {
		return services;
	}
	public void setServices(String services) {
		this.services = services;
	}
}
