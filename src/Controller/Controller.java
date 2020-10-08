package Controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import Dao.BookDao;
import Dao.RecordDao;
import Dao.TimeDao;
import Dao.UserDao;
import Model.Book;
import Model.Record;
import Model.Time;
import Model.User;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class Controller {
	public int login(String username, String password) {
		UserDao ud = new UserDao();
		List<User> ul = ud.getUsers();
		for (User user : ul) {
			if(user.getUsername().equals(username) && user.getPassword().equals(password)) {
				return user.getId();
			}else {
				return 150;
			}
		}
		return 150;
	}
	
	public String imageSet(String imageName) {
		Tesseract tesseract = new Tesseract();
		System.out.println(imageName);
		String path = "D:/Workspaca2020/Kutuphane/ImgRepo/"+imageName;
		try {
			tesseract.setDatapath("D:/Workspaca2020/Kutuphane/Tess4J/tessdata");
			//tesseract.setDatapath("Tess4J/tessdata");
			tesseract.setLanguage("tur");
			// the path of your tess data folder
			// inside the extracted file
			String text = tesseract.doOCR(new File(path));
			// path of your image file
			// System.out.print(text);
			textParser(text,imageName);
			return text;
		} catch (TesseractException e) {
			e.printStackTrace();
			return "resim okunurken hata aldi!";
		}
	}
	
	public void textParser(String isbn, String imgName) {
		//yukarıdaki texti parcala
		int start = isbn.indexOf(" ");
		String ISBN = isbn.substring(start+1, isbn.length() -1 ); 
		int st = imgName.indexOf(".");
		String bookName = imgName.substring(0, st);
		BookDao bd = new BookDao();
		Book book = new Book(ISBN, bookName);
		bd.saveBook(book);
	}
	public int giveBook(String imgName) {
		//yukarıdaki texti parcala
		int st = imgName.indexOf(".");
		String bookName = imgName.substring(0, st);
		Book book = getBook(bookName);
		RecordDao rd = new RecordDao();
		List<Record> rl = rd.getRecords();
		for (Record record : rl) {
			if(record.getBookID() == book.getId()) {
				record.setGiveStatus(0);
				rd.updateRecord(record);
				return 100;
			}
		}
		return 150;
	}
	public int takeBook(String isbn,String userId) {
		//yukarıdaki texti parcala
		Book book = getBook(isbn);
		Record r = new Record();
		r.setBookID(book.getId());
		r.setUserID(Integer.parseInt(userId));
		String pattern = "dd.MM.yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());
		r.setTakeTime(date);
		r.setGiveTime(date);
		r.setGiveStatus(1);
		RecordDao rd = new RecordDao();
		rd.saveRecord(r);
		return 100;
	}
	
	public int writeToFile(InputStream uploadedInputStream,
			String uploadedFileLocation) {

			try {
				OutputStream out = new FileOutputStream(new File(
						uploadedFileLocation));
				int read = 0;
				byte[] bytes = new byte[1024];

				out = new FileOutputStream(new File(uploadedFileLocation));
				while ((read = uploadedInputStream.read(bytes)) != -1) {
					out.write(bytes, 0, read);
				}
				out.flush();
				out.close();
				return 100;
			} catch (IOException e) {
				e.printStackTrace();
				return 150;
			}
		}
	
	public int skipTime(String time) {
		Time t = null;
		TimeDao td = new TimeDao();
		t = td.getTime();
		t.setDate(time);
		int ret = td.updateTime(t);
		if(ret == 100) {
			return 100;
		}else {
			return 150;
		}
	}
	public List<User> listUsers() {
		UserDao ud = new UserDao();
		 return ud.getUsers();
	}
	
	public List<Book> listBooks() {
		BookDao bd = new BookDao();
		 return bd.getBooks();
	}
	
	public Book getBook(String wantedBook) {
		BookDao result = new BookDao();
		List<Book> bl = result.getBooks();
		for (Book book : bl) {
			if(book.getName().equalsIgnoreCase(wantedBook) || book.getIsbn().equalsIgnoreCase(wantedBook)) {
				return book;
			}
		}
		return new Book();
	}
}
