package Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "record")
public class Record {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "user_id")
	private int userID;
	@Column(name = "book_id")
	private int bookID;
	@Column(name = "take_time")
	private String takeTime;
	@Column(name = "give_time")
	private String giveTime;
	@Column(name = "give_status")
	private int giveStatus;
	
	public Record(int userID, int bookID, String takeTime, String giveTime, int giveStatus) {
		super();
		this.userID = userID;
		this.bookID = bookID;
		this.takeTime = takeTime;
		this.giveTime = giveTime;
		this.giveStatus = giveStatus;
	}

	public Record() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getBookID() {
		return bookID;
	}

	public void setBookID(int bookID) {
		this.bookID = bookID;
	}

	public String getTakeTime() {
		return takeTime;
	}

	public void setTakeTime(String takeTime) {
		this.takeTime = takeTime;
	}

	public String getGiveTime() {
		return giveTime;
	}

	public void setGiveTime(String giveTime) {
		this.giveTime = giveTime;
	}

	public int getGiveStatus() {
		return giveStatus;
	}

	public void setGiveStatus(int giveStatus) {
		this.giveStatus = giveStatus;
	}

	@Override
	public String toString() {
		return "Record [id=" + id + ", userID=" + userID + ", bookID=" + bookID + ", takeTime=" + takeTime
				+ ", giveTime=" + giveTime + ", giveStatus=" + giveStatus + "]";
	}
	
}
