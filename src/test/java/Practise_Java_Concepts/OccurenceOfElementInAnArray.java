package Practise_Java_Concepts;

public class OccurenceOfElementInAnArray {

	public static void main(String[] args) {
		int a[] = { 1, 2, 3, 4, 1, 5, 6, 4, 1 };
		int b[] = new int[a.length];
		int variable = -01;
		for (int i = 0; i < a.length; i++) {

			int count = 1;

			for (int j = i + 1; j < a.length; j++) {

				if (a[i] == a[j]) {
					count++;
					b[j] = variable;

				}
			}
			if (b[i] != variable) {
				b[i] = count;
			}

		}
		for (int i = 0; i < b.length; i++) {

			if (b[i] != variable) {
				System.out.println(a[i] + "------->" + b[i]);
			}
		}

	}

}
