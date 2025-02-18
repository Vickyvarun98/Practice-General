package Practise_Java_Concepts;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GetTheCurrentDateFromSystem {

	public static void main(String[] args) throws InterruptedException {
		
//		for (int i = 0; i < 10; i++) {
//			
//			Thread.sleep(1000);
//			Date date = new Date();
//			System.out.println(date.toString());
//		}
		
		
		Date date=new Date();
		String Curr_Sys_Date = date.toString();
		System.out.println(Curr_Sys_Date);
		System.out.println();
		String[] sp = Curr_Sys_Date.split(" ");
		String Day = sp[0];
		String Month = sp[1];
		String Date = sp[2];
		String Time = sp[3].replace(':', '-');
		String Standard = sp[4];
		String Year = sp[5];
		
		
		System.out.println(Year+ '-'+Month+'-'+Date);
		System.out.println(Time);
		System.out.println(Day);
		System.out.println();
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");//mm --------> Minutes MM-----> Month
		String Current_Date = sim.format(date);
		System.out.println(Current_Date);
		
		
	}

}
