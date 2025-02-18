package AdvanceSelenium;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class ToLearnTestNG {
	
	@Test(priority=-1,invocationCount = 1,threadPoolSize = 1)
	public void cricbuzz() {
		Reporter.log("Cricbuzz got Executed",true);
	}
	@Test(priority=0)
	public void baskinRobbins() {
		Reporter.log("BaskinRobbins got Executed",true);
	}
	 @Test(priority=1)
	 public void amazon() {
		 Reporter.log("Amazon got Executed",true);
	 }

}
