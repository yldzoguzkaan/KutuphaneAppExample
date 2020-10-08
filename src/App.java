import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import Dao.RecordDao;
import Model.Record;

public class App {
	public static void main(String[] args) {
		String pattern = "dd.MM.yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());
		System.out.println(date);

	}
}
