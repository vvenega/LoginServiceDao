package com.example.demo;


import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LoginServiceDaoController {
	

	
	
	@GetMapping("/ValidatePasswordDao/{user}/{password}")
	public LoginDao validatePassword(@PathVariable String user,@PathVariable String password) {
		
		boolean result=false;
		
		LoginDao dao = new LoginDao();
		
		dao = JanusGraphAdapter.loginUser(user, password);
		

		return dao;
	}
	
	@GetMapping("/CreateUser/{user}/{password}/{organization}/{role}/{name}/{ecommunities}")
	public String createUser(@PathVariable String user,@PathVariable String password,
			@PathVariable String organization, @PathVariable String role, @PathVariable
			String name, @PathVariable List<String> ecommunities ) {
		String response = "N/A";
		try {
			
			UserDao userdao = new UserDao();
			userdao.setUsername(user);
			userdao.setOrganization(organization);
			userdao.setName(name);
			userdao.setPwd(PasswordEncoder.encode(password));
			userdao.setRole(role);
			
			userdao.setEcommunity(ecommunities);
			
			response = JanusGraphAdapter.addUser(userdao);

			
		}catch(Exception e) {
			System.err.println(e.getMessage());
			response = "ERR:"+e.getLocalizedMessage();
		}
		
		return response;
	}
	
	

}
