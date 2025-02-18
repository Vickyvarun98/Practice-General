package Practise_Java_Concepts;

import java.util.Random;

public class Generate_AlphaNumerical_ {
	public static void main(String[] args) {
		int n = 20;
		String AlphaNumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZ012345689abcdefghijklmnopqrstuvwxyz";
		// int length = AlphaNumeric.length();// 62
		// Object for String Builder
		StringBuilder sb = new StringBuilder(n);// 20
		StringBuilder sb1 = new StringBuilder(n);// 20
		Random r = new Random();

		for (int i = 0; i < n; i++) {

			int ran = r.nextInt(AlphaNumeric.length());// within this range to fetch random Number
			int ran1 = (int) (AlphaNumeric.length() * Math.random());
			sb.append(AlphaNumeric.charAt(ran));// add each character based on random index
			sb1.append(AlphaNumeric.charAt(ran1));// add each character based on random index

		}
		System.out.println(sb);
		System.out.println();
		System.out.println(sb1);
	}
}