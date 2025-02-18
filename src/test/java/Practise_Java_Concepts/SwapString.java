package Practise_Java_Concepts;

public class SwapString {

	public static void main(String[] args) {
		String d1="DAD";
		String d2="MOM";
		System.out.println(d1+"  "+d2);
		d1=d1+d2;
		d2=d1.substring(0, d1.length()-d2.length());
		d1=d1.substring(d2.length());
		System.out.println();
		System.out.println(d1+"  "+d2);
		
		
		
	}

}
