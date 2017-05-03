package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	private SimpleDateFormat sdf;
	
	public String timeStampToDateStr(String time, String format) {
		sdf = new SimpleDateFormat(format);
		String dateStr = sdf.format(new Date(Long.valueOf(time)));
		System.out.println(dateStr);
		return dateStr;
	}
	
	public String dateStrToTimeStamp(String dateStr, String format) throws Exception {
		sdf = new SimpleDateFormat(format);
		String timeStamp = String.valueOf(sdf.parse(dateStr).getTime());
		return timeStamp;
	}
}
