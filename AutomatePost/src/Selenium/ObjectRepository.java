package Selenium;

import org.openqa.selenium.By;

public class ObjectRepository {

	public final static By CUSTNAME  =  By.name("custname");
	public final static By TELEPHONE =  By.name("custtel");
	public final static By EMAIL     =  By.xpath("html/body/form/p[3]/label/input");
	public final static By PSIZE     =  By.name("size");
	public final static By PTOPPINGS =  By.name("topping");
	public final static By DELTIME   =  By.xpath("html/body/form/p[4]/label/input");
	public final static By DELINST   =  By.xpath("html/body/form/p[5]/label/textarea");
	public final static By SUBMIT    =  By.xpath("html/body/form/p[6]/button");
			
}

