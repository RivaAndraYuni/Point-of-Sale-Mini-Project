package com.miniproject.backend.dto;

public class ProductsDTO {
	
	private Integer id;
    private String title;
    private Integer price;
    private String image;
    private Integer categories;
    
    
	public Integer getId() {
		return id;
	}
	public Integer getCategories() {
		return categories;
	}
	public void setCategories(Integer categories) {
		this.categories = categories;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
    
    
    
}
