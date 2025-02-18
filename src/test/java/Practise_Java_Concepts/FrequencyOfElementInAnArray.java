package Practise_Java_Concepts;

import java.util.LinkedHashMap;

public class FrequencyOfElementInAnArray {

	public static void main(String[] args) {
		int a[] = { 1, 2, 3, 4, 1, 5, 6, 4, 1 };
		LinkedHashMap<Integer, Integer>l=new LinkedHashMap<Integer, Integer>();
		

		for (int i = 0; i < a.length; i++) {

			int count = 1;
		//	int[] b = new int[a.length];

			for (int j = i + 1; j < a.length; j++) {
				//b[i] = a[i];

				if (a[i] == a[j]) {
					count++;

				}
			}
			if(!l.containsKey(a[i]))
			{
				l.put(a[i], count);
			}
		}
		
		System.out.println(l);
	}

}
