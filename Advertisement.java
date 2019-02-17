package main_package;

public class Advertisement {
	
	private String firm;
	private String 	description;
	private String price;
	private Integer duration;//seconds
	
	
	Advertisement(String firm,String description,String price,Integer duration){
		this.setFirm(firm);
		this.setDescription(description);
		this.setPrice(price);
		this.setDuration(duration);
		
	}


	public String getFirm() {
		return firm;
	}


	public void setFirm(String firm) {
		this.firm = firm;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getPrice() {
		return price;
	}


	public void setPrice(String price) {
		this.price = price;
	}


	public Integer getDuration() {
		return duration;
	}


	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	
	
	
	

}
