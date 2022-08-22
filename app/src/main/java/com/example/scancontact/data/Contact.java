package com.example.scancontact.data;

public class Contact {
	private int id;
	private String name=null;
	private String phone=null;
	private String mobile=null;
	private String email=null;
	private String company=null;
	private String address=null;
	private String birthday=null;
	private String notes=null;

	public Contact(){
		id=0;
		name="";
		phone="";
		mobile="";
		email="";
		company="";
		address="";
		birthday="";
		notes="";
	}

	public void setId(int id){
		this.id = id;
	}
	public int getId(){
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
}
