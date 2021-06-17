package com.example.demo;

import java.util.Iterator;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

@RestController
public class LoginServiceDaoController {
	
	/*private final static  String CASSANDRA_SERVER="127.0.0.1";
	private final static int CASSANDRA_PORT=9042;*/
	
	@GetMapping("/ValidatePasswordDao/{user}/{password}")
	public LoginDao validatePassword(@PathVariable String user,@PathVariable String password) {
		
		boolean result=false;
		
		LoginDao dao = new LoginDao();
		
		dao = JanusGraphAdapter.loginUser(user, password);
		
		/*CassandraConnector client = new CassandraConnector();
		  client.connect(CASSANDRA_SERVER, CASSANDRA_PORT);
		  Session session = client.getSession();
		  
		  PreparedStatement prepared = session.prepare(
  			  "select username, name, organization, role,pwd from circulareconomy.users "+
		  " where username=?");

		        
		  BoundStatement bound = prepared.bind(user);
  			ResultSet rset = session.execute(bound);
  			if(rset!=null) {
  				
  				Iterator<Row> itr =rset.iterator();
  				
  				
  				if(itr.hasNext()) {
  					Row row = itr.next();
  					String username = row.getString(0);
  					String name = row.getString(1);
  					String organization = row.getString(2);
  					String role = row.getString(3);
  					String encodedPassword = row.getString(4);
  					result = PasswordEncoder.userPasswordCheck(password, encodedPassword);
  					
  					dao.setUsername(username);
  					dao.setName(name);
  					dao.setOrganization(organization);
  					dao.setValid(result);
  					dao.setRole(role);
  					
  				}
  			}else {
  				dao.setUsername(user);
  				dao.setValid(result);
  			}
  			
  			session.close();
		      client.close();*/
		
		
		return dao;
	}

}
