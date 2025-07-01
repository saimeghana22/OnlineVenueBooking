package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Venue;

public interface VenueRepository extends JpaRepository<Venue,Integer>{

}