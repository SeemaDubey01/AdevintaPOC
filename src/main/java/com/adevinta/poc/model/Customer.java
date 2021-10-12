package com.adevinta.poc.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String name;
	
	private String address;
	
	private String postcode;
	
	private String phoneNu;
	
	private Date birthDate;
	
	private double creditLimit;

	public Customer() {
		super();
	}

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getPhoneNu() {
		return phoneNu;
	}

	public void setPhoneNu(String phoneNu) {
		this.phoneNu = phoneNu;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public double getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(double creditLimit) {
		this.creditLimit = creditLimit;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", address=" + address + ", postcode=" + postcode
				+ ", phoneNu=" + phoneNu + ", birthDate=" + birthDate + ", creditLimit=" + creditLimit + "]";
	}

	public Customer(String name, String address, String postcode, String phoneNu, Date birthDate, double creditLimit) {
		super();
		this.name = name;
		this.address = address;
		this.postcode = postcode;
		this.phoneNu = phoneNu;
		this.birthDate = birthDate;
		this.creditLimit = creditLimit;
	}
	

	
	

}
