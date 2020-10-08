package Service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import Controller.Controller;
import Model.User;

@Path("/user")
public class UserService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/listUsersGet")
	public List<User> listUser() {
		Controller result = new Controller();
		List<User> ul = result.listUsers();
		return ul;
	}

}