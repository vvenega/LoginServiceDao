package com.example.demo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;

public class PasswordEncoder {

public static String encode(String plainPassword) {

	    

	    BCryptPasswordEncoder pbkdf2PasswordEncoder =
	        new BCryptPasswordEncoder();
	    return pbkdf2PasswordEncoder.encode(plainPassword);
	  }
	
	
	public static boolean userPasswordCheck(String password, String encodedPassword) {

		BCryptPasswordEncoder passencoder = new BCryptPasswordEncoder();
	    return passencoder.matches(password, encodedPassword);
	}
	
	
	public static void main(String[] args) {
		
		
		//owner3759,owner8511,owner4796,vvenega, owner160
		
		String user = "owner3759";
		String pwd="gpv3n3ga5";
		String organization="organization1";
		String role="admin";
		String name="owner3759";
		String Community="Community1";
		
		
		
		save(user,PasswordEncoder.encode(pwd),organization, role,name,Community);
		
		
		user = "owner8511";
		pwd="gpv3n3ga5";
		organization="organization2";
		role="admin";
		name="owner8511";
		save(user,PasswordEncoder.encode(pwd),organization, role,name,Community);
		
		
		user = "owner4796";
		pwd="gpv3n3ga5";
		organization="organization3";
		role="admin";
		name="owner4796";
		save(user,PasswordEncoder.encode(pwd),organization, role,name,Community);
		
		user = "vvenega";
		pwd="gpv3n3ga5";
		organization="organization4";
		role="admin";
		name="vvenega";
		save(user,PasswordEncoder.encode(pwd),organization, role,name,Community);
		
		user = "owner160";
		pwd="gpv3n3ga5";
		organization="organization5";
		role="admin";
		name="owner160";
		save(user,PasswordEncoder.encode(pwd),organization, role,name,Community);
		
		
		/**********************************************************************************/
		
		 user = "owner3759_1";
		 pwd="gpv3n3ga5";
		 organization="organization1";
		 role="admin";
		 name="owner3759";
		 Community="Community2";
		
		
		
		save(user,PasswordEncoder.encode(pwd),organization, role,name,Community);
		
		
		user = "owner8511_1";
		pwd="gpv3n3ga5";
		organization="organization2";
		role="admin";
		name="owner8511";
		save(user,PasswordEncoder.encode(pwd),organization, role,name,Community);
		
		
		user = "owner4796_1";
		pwd="gpv3n3ga5";
		organization="organization3";
		role="admin";
		name="owner4796";
		save(user,PasswordEncoder.encode(pwd),organization, role,name,Community);
		
		user = "vvenega_1";
		pwd="gpv3n3ga5";
		organization="organization4";
		role="admin";
		name="vvenega";
		save(user,PasswordEncoder.encode(pwd),organization, role,name,Community);
		
		user = "owner160_1";
		pwd="gpv3n3ga5";
		organization="organization5";
		role="admin";
		name="owner160";
		save(user,PasswordEncoder.encode(pwd),organization, role,name,Community);
		
		
	}
	
	private static void save(String user,String pwd,String organization, String role,String name,
			String community) {
		CassandraConnector client = new CassandraConnector();
		  client.connect("127.0.0.1", 9042);
		  Session session = client.getSession();
		  
		  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
      LocalDateTime now = LocalDateTime.now();
      
		  
		  PreparedStatement prepared = session.prepare(
			  "Insert into circulareconomy.users(username,pwd,organization,"+
		  "role,name,community)values(?,?,?,?,?,?)");
		  
	  BoundStatement bound = prepared.bind(user,pwd,organization,role,name,community);
			session.execute(bound);
			
			
			
			session.close();
		      client.close();
	
	}
}
