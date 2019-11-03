package com.example.todoList.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DaysTillDue {
	
	public static int daysTillDue(String stringDate) {
		SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
		long diff = 0;

		try {
		    Date date = myFormat.parse(stringDate);
		    String now = myFormat.format(new Date());
		    Date dNow = myFormat.parse(now);
		    diff = date.getTime() - dNow.getTime();
		} catch (ParseException e) {
		    e.printStackTrace();
		}
			
		return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		
	}

}
