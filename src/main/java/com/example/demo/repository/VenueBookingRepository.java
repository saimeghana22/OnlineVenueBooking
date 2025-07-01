package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.VenueBooking;

public interface VenueBookingRepository extends JpaRepository<VenueBooking,Integer>{

}
