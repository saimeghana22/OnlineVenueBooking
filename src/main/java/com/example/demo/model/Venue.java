package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
public class Venue {

	private int id;
	private String name;
	private String landmark;
	private String area;
	private String description;
	private MultipartFile image;
	private String imageName;
	private int price;
	private String vegfoodprice;
	private String nonvegfoodprice;
	private String decorationprice;
	private String mackupprice;
	private String photographerprice;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLandmark() {
		return landmark;
	}
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Transient
	public MultipartFile getImage() {
		return image;
	}
	public void setImage(MultipartFile image) {
		this.image = image;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public String getDecorationprice() {
		return decorationprice;
	}
	public void setDecorationprice(String decorationprice) {
		this.decorationprice = decorationprice;
	}
	public String getMackupprice() {
		return mackupprice;
	}
	public void setMackupprice(String mackupprice) {
		this.mackupprice = mackupprice;
	}
	public String getPhotographerprice() {
		return photographerprice;
	}
	public void setPhotographerprice(String photographerprice) {
		this.photographerprice = photographerprice;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getVegfoodprice() {
		return vegfoodprice;
	}
	public void setVegfoodprice(String vegfoodprice) {
		this.vegfoodprice = vegfoodprice;
	}
	public String getNonvegfoodprice() {
		return nonvegfoodprice;
	}
	public void setNonvegfoodprice(String nonvegfoodprice) {
		this.nonvegfoodprice = nonvegfoodprice;
	}
	@Override
	public String toString() {
		return "Venue [id=" + id + ", name=" + name + ", landmark=" + landmark + ", area=" + area + ", description="
				+ description + ", image=" + image + ", imageName=" + imageName + ", price=" + price + ", vegfoodprice="
				+ vegfoodprice + ", nonvegfoodprice=" + nonvegfoodprice + ", decorationprice=" + decorationprice
				+ ", mackupprice=" + mackupprice + ", photographerprice=" + photographerprice + "]";
	}
}
