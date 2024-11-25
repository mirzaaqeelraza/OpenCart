package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

	WebDriver driver;

	public BasePage(WebDriver driver) { // Constructor
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	 
	
	
	
	/*
	String empName;
	int empID;

	ParentClass(String empName, int empID){
		this.empName = empName;
		this.empID = empID;
		
	} 
	
      int empAge;
	
	ChildClass(int empID, String empName, int empAge){
		super(empName, empID);
		this.empAge = empAge;
		
	} 
	  */
}
