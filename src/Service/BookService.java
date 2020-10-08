package Service;

import java.io.InputStream;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

import Controller.Controller;
import Model.Book;

@Path("/book")
public class BookService {
	@POST
	@Path("/bookUpload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(
		@FormDataParam("file") InputStream uploadedInputStream,
		@FormDataParam("file") FormDataContentDisposition fileDetail) {

		String uploadedFileLocation = "D:\\Workspaca2020\\Kutuphane\\ImgRepo\\" + fileDetail.getFileName();
		// save it
		Controller result = new Controller();
		int ret = result.writeToFile(uploadedInputStream, uploadedFileLocation);
		result.imageSet(fileDetail.getFileName());
		//String output = "File uploaded to : " + uploadedFileLocation;
		if(ret == 100) {
			return Response.status(200).entity("100").build();
		}else {
			return Response.status(200).entity("150").build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/listBooksGet")
	public List<Book> listUser() {
		Controller result = new Controller();
		List<Book> bl = result.listBooks();
		return bl;
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/searchBook")
	public Book getBook( 
			@FormParam("searchBook") String searchBook)
	{
		Controller result = new Controller();
		Book book = result.getBook(searchBook);
		System.out.println(book.toString());
		return book;
	}
	@POST
	@Path("/giveBook")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response giveBook(
		@FormDataParam("file") InputStream uploadedInputStream,
		@FormDataParam("file") FormDataContentDisposition fileDetail) {

		String uploadedFileLocation = "D:\\Workspaca2020\\Kutuphane\\ImgRepo\\" + fileDetail.getFileName();
		// save it
		Controller result = new Controller();
		int ret = result.writeToFile(uploadedInputStream, uploadedFileLocation);
		if(ret == 100) {
			int ret2= result.giveBook(fileDetail.getFileName());
			//String output = "File uploaded to : " + uploadedFileLocation;
			if(ret2 == 100) {
				return Response.status(200).entity("100").build();
			}else {
				return Response.status(200).entity("150").build();
			}
		}else {
			return Response.status(200).entity("150").build();
		}
		
	}
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/takeBook")
	public int takeBook(
			@FormParam("takeBookIsbn") String takeBookIsbn,
			@FormParam("takeBookUserId") String takeBookUserId) {
		
		System.out.println("isbn:"+takeBookIsbn + "user:" + takeBookUserId);
		
		Controller result = new Controller();
		Book book = result.getBook(takeBookIsbn);
		int ret =  result.takeBook(takeBookIsbn, takeBookUserId);
		return ret;
	}
	
}
