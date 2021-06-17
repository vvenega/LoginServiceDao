package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class LoginDao {
	
	private String username;
	private String organization;
	private String name;
	private boolean valid;
	private String role;
	private List<String> ecommunities;
	private List<String> types;
	
	
	public LoginDao() {
		ecommunities=new ArrayList<String>();
	}
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public List<String> getEcommunities() {
		return ecommunities;
	}
	public void setEcommunities(List<String> ecommunities) {
		this.ecommunities = ecommunities;
	}
	
	public void addCommunity(String ecommunity) {
		ecommunities.add(ecommunity);
	}


	public List<String> getTypes() {
		return types;
	}


	public void setTypes(List<String> types) {
		this.types = types;
	}
	
	
	

}
