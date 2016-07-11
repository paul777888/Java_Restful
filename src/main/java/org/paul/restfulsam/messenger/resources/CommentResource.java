package org.paul.restfulsam.messenger.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/")
public class CommentResource {
	
	@GET
	public String test(){
		System.out.println("comment resource starts...");
		return "new sub resource";
	}
	
	
	
}
