package Practise_Java_Concepts;

public class ReverseTheString {

	public static void main(String[] args) {
		String st="I LOVE INDIA";
		String s="";
		for(int i=st.length()-1;i>=0;i--) {
			
			s+=st.charAt(i);
			
		}
		System.out.println(st);
		System.out.println(s);
		String[] split = s.split(" ");
		String s1="";
		
		for (int i = 0; i < split.length; i++) {
			s1+=split[i];
		}
		System.out.println();
		System.out.println(s1);
		
		
	}

}
