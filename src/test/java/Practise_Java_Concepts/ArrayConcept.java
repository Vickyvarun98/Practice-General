package Practise_Java_Concepts;

public class ArrayConcept {

	public static void main(String[] args) {
		int[] a= {1,2,3,4,5};
		int []b=new int[a.length];
		 int temp=a[0];
		for(int i=0;i<a.length-1;i++) {
			
			b[i]=a[i+1];
		}
			b[b.length-1]=temp;
		
		for(int i=0;i<b.length;i++) {
			System.out.print(b[i]+" ");
		}
		
	}

}
