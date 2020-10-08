package Service;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import Controller.Controller;

@Path("/time")
public class TimeService {
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/skipTime")
	public Response addUser(
		@FormParam("time") String time ){
		
		Controller result = new Controller();
		int ret = result.skipTime(time);
		
		if(ret == 100 ) {
			return Response.status(200).entity("100").build();
		}else {
			return Response.status(200).entity("150").build();	
		}
		
	}
}	
