package Practise_Java_Concepts;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GetPreviousAndLaterDatesFromSystem {

	public static void main(String[] args) {
		Date d = new Date();
		// Current_Date
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		String Current_Date = sim.format(d);
		// Previous_Date
		Calendar cal = sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, -30);
		String Previous_Date = sim.format(cal.getTime());
		// Next_Date
		// Calendar cal1=sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH,30);
		String Next_Date = sim.format(cal.getTime());
		System.out.println("Current_Date  : " + Current_Date);
		System.out.println("Previous_Date : " + Previous_Date);
		System.out.println("Next_Date     : " + Next_Date);
	}

}
