package Practise_Java_Concepts;

import org.testng.annotations.Test;

public class Class_B {
	
	@Test
	public void executeTest() {
		Class_A ref=new Class_A();
		String data = ref.datas("output");
		System.out.println(data);
	}

}
