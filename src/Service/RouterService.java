package Service;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import Controller.Controller;



@Path("/l")
public class RouterService {
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/login")
	public Response addUser(
		@FormParam("username") String username,
		@FormParam("password") String password) {
		
		System.out.println("username: "+username + " password : "+password);
		Controller c = new Controller();
		int ret = c.login(username, password);
		return Response.status(200).entity(ret).build();
		
	}
}
