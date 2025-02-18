package Practise_Java_Concepts;

import java.util.Random;

import org.testng.annotations.Test;

public class GenerateRandomNumber {

	
	public static void main(String[] args) {
		Random r = new Random();
		int n=10;
		int min=1000;
		int max=9999;
		int range=max-min+1;
	
		for (int i = 0; i <n; i++) {	
		int Random1 = r.nextInt(range);
		System.out.println(Random1+min);
		}

	}

}
