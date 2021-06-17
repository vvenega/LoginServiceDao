package com.example.demo;

import java.util.Iterator;
import java.util.List;

public class UserDao {
	
	private String username;
	private String pwd;
	private String organization;
	private String name;
	private List<String> ecommunity;
	private String role;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
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
	public Iterator<String> getEcommunity() {
		return ecommunity.iterator();
	}
	public void setEcommunity(List<String> ecommunity) {
		this.ecommunity = ecommunity;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
	
}
