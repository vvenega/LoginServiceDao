package com.example.demo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.structure.util.empty.EmptyGraph;

public class JanusGraphAdapter{
	
	
	private static JanusGraphAdapter instance;
	
	private static Graph graph;
	private static GraphTraversalSource g;
	
	static {
		instance=new JanusGraphAdapter();
	}
	
	
	private JanusGraphAdapter() {
		try {
		graph = EmptyGraph.instance();
	    g = graph.traversal().withRemote("remote-graph.properties");
	   
		}catch(Exception e) {
			System.err.println("Error while connecting JanusGraph..");
			System.err.println(e.getLocalizedMessage());
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		
		try {
			
			
			LoginDao dao =JanusGraphAdapter.loginUser("owner160", "gpv3n3ga5");
	    //Graph graph = EmptyGraph.instance();
	    //GraphTraversalSource g = graph.traversal().withRemote("remote-graph.properties");
		List<String> list = new ArrayList<String>();
	    String user = "owner160";
		String pwd="gpv3n3ga5";
		String organization="Daimler";
		String role="operator";
		String name="Andrea Hernandez";
		
		
		UserDao userdao = new UserDao();
		userdao.setUsername(user);
		userdao.setOrganization(organization);
		userdao.setName(name);
		userdao.setPwd(PasswordEncoder.encode(pwd));
		userdao.setRole(role);
		list.add("Daimler");
		list.add("General");
		userdao.setEcommunity(list);
		
		String id = JanusGraphAdapter.addUser(userdao);
		
		System.out.println("Resultado:"+id);
		
		user = "owner3759";
		pwd="gpv3n3ga5";
		organization="Navistar";
		role="operator";
		name="Sandra Munoz";
		list.clear();
		list.add("Navistar");
		list.add("General");
		
		userdao.setUsername(user);
		userdao.setOrganization(organization);
		userdao.setName(name);
		userdao.setPwd(PasswordEncoder.encode(pwd));
		userdao.setRole(role);
		userdao.setEcommunity(list);
		
		id = JanusGraphAdapter.addUser(userdao);
		
		System.out.println("Resultado:"+id);
		
		user = "owner8511";
		pwd="gpv3n3ga5";
		organization="Navistar";
		role="admin";
		name="Pedro Garcia";
		list.clear();
		list.add("Navistar");
		
		userdao.setUsername(user);
		userdao.setOrganization(organization);
		userdao.setName(name);
		userdao.setPwd(PasswordEncoder.encode(pwd));
		userdao.setRole(role);
		userdao.setEcommunity(list);
		
		id = JanusGraphAdapter.addUser(userdao);
		
	    System.out.println("Resultado:"+id);
	    
	    user = "owner4796";
		pwd="gpv3n3ga5";
		organization="Daimler";
		role="admin";
		name="Carlos Gatica";
		list.clear();
		list.add("Daimler");
		
		userdao.setUsername(user);
		userdao.setOrganization(organization);
		userdao.setName(name);
		userdao.setPwd(PasswordEncoder.encode(pwd));
		userdao.setRole(role);
		userdao.setEcommunity(list);
		
		id = JanusGraphAdapter.addUser(userdao);
		
	    System.out.println("Resultado:"+id);
	    
	    user = "vvenega";
		pwd="gpv3n3ga5";
		organization="Ecommunities";
		role="admin";
		name="Victor Venegas";
		list.clear();
		list.add("General");
		
		userdao.setUsername(user);
		userdao.setOrganization(organization);
		userdao.setName(name);
		userdao.setPwd(PasswordEncoder.encode(pwd));
		userdao.setRole(role);
		userdao.setEcommunity(list);
		
		id = JanusGraphAdapter.addUser(userdao);
		
	    System.out.println("Resultado:"+id);
	    
		}catch(Exception e) {
			e.printStackTrace();
		}
	    
	    
	}
	
public static boolean existsUser(UserDao user) {
		
		boolean exists = false;
		
		try {

	    
	    exists = g.V().hasLabel("User").has("username",user.getUsername()).hasNext();
	   
	    
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return exists;
		
	}


public static List<Object> existsRole(UserDao user) {
	
	boolean exists = false;
	List<Object> list = new ArrayList<Object>();
	
	try {

    //List<List<Vertex>> v = g.V().has("Organization","name",user.getOrganization()).fold().toList();
    //System.out.println("Size:"+v.size());
    
	GraphTraversal<Vertex, Vertex> tv=g.V().hasLabel("Role").has("name",user.getRole());
    exists = tv.hasNext();
	list.add(exists);
	
	if(exists) {
		Vertex vertex = tv.next();
		list.add(vertex);
	}
   
    
	}catch(Exception e) {
		e.printStackTrace();
	}
	
	
	return list;
	
}

public static List<Vertex> getEcommunities(UserDao user) {
	
	boolean exists = false;
	List<Vertex> list = new ArrayList<Vertex>();
	Vertex vertex=null;
	
	try {

		Iterator<String> itr = user.getEcommunity();
		
		while(itr.hasNext()) {
			String ecommunity = itr.next();
			GraphTraversal<Vertex, Vertex> tv = g.V().hasLabel("Ecommunity").has("name",ecommunity);
			exists =tv.hasNext();
			
			if(exists) {
				vertex = tv.next();
				list.add(vertex);
			}else {
				vertex = addEcommunity(ecommunity);
				list.add(vertex);
			}
		}
		
	}catch(Exception e) {
		e.printStackTrace();
	}
	
	
	return list;
	
}


  

	
	public static List<Object> existsOrganization(UserDao user) {
		
		boolean exists = false;
		List<Object> list = new ArrayList<Object>();
		
		try {

	    //List<List<Vertex>> v = g.V().has("Organization","name",user.getOrganization()).fold().toList();
	    //System.out.println("Size:"+v.size());
	    
		GraphTraversal<Vertex, Vertex> tv=g.V().hasLabel("Organization").has("name",user.getOrganization());
	    exists = tv.hasNext();
		list.add(exists);
		
		if(exists) {
			Vertex vertex = tv.next();
			list.add(vertex);
		}
	   
	    
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return list;
		
	}
	
	public static Vertex addOrganization(String organization) {
		Vertex org;
		org = g.addV("Organization").property("name", organization).next();
		return org;
		
	}
	
	public static Vertex addEcommunity(String ecommunity) {
		Vertex org;
		org = g.addV("Ecommunity").property("name", ecommunity).next();
		return org;
		
	}
	
	public static Vertex addRole(String role) {
		Vertex org;
		org = g.addV("Role").property("name", role).next();
		return org;
		
	}
	
	public static String addUser(UserDao user) {
		
		String result = "N/A";
		try {
	    
			Vertex organization;
			Vertex ecommunity;
			Vertex role;
			List<Object> listOrganization = existsOrganization(user);
			List<Vertex> listEcommunity = getEcommunities(user);
			List<Object> listRole = existsRole(user);
			boolean existsOrganization = (boolean)listOrganization.get(0);
			boolean existsRole = (boolean)listRole.get(0);
			
			if(existsOrganization)
				organization = (Vertex)listOrganization.get(1);
			else
				organization = addOrganization(user.getOrganization());
			
			if(existsRole)
				role = (Vertex)listRole.get(1);
			else
				role = addRole(user.getRole());
			
			
			if(!existsUser(user)) {
               Vertex janususer = g.addV("User").property("username", user.getUsername()).property("pwd", user.getPwd()).property("name", user.getName()).next();
               g.addE("belongsto").from(janususer).to(organization).next();
               g.addE("isrole").from(janususer).to(role).next();
               
               Iterator<Vertex> itr2 = listEcommunity.iterator();
               
               while(itr2.hasNext()) {
            	   ecommunity = itr2.next();
            	   g.addE("iscommunity").from(janususer).to(ecommunity).next();
               }
               
               
               result = janususer.id().toString();
			}
	    
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	public static LoginDao  loginUser(String username, String pwd) {
		
		LoginDao dao = new LoginDao();
		
		try {
		Map<Object,Object> user = g.V().has("User","username",username).as("usr").valueMap().next();
		List pwdGraph =(ArrayList)user.get("pwd");
		//System.out.println(pwdGraph.get(0));
		
		boolean result = PasswordEncoder.userPasswordCheck(pwd, (String)pwdGraph.get(0));
		
		if(result) {
			String name=((ArrayList<String>)user.get("name")).get(0);
			dao.setValid(true);
			dao.setName(name);
			dao.setUsername(username);
			
			List<Object> list = g.V().has("User","username",username)
					.as("usr").out("belongsto").values("name").toList();
					
			
			Iterator<Object> itr = list.iterator();
			
			if(itr.hasNext()) {
				String organization = (String)itr.next();
				
				dao.setOrganization(organization);
				
			}
			
		   list = g.V().has("User","username",username)
					.as("usr").out("iscommunity").values("name").toList();
					
			
			 itr = list.iterator();
			
			while(itr.hasNext()) {
				String community = (String)itr.next();
				dao.addCommunity(community);
				
			}
			
			 list = g.V().has("User","username",username)
					.as("usr").out("isrole").values("name").toList();
					
			
			 itr = list.iterator();
			
			if(itr.hasNext()) {
				String role = (String)itr.next();
				
				dao.setRole(role);
				
			}
			
			List<String> types=new ArrayList<String>();
			types.add("Listing");
			types.add("Donation");
			types.add("Exchange");
			types.add("Rent");
			
			dao.setTypes(types);
			
			
		}else
			dao.setValid(false);
		
		}catch(Exception e) {
			e.printStackTrace();
			dao=new LoginDao();
			dao.setValid(false);
		}
		
		return dao;
	
	}

}
