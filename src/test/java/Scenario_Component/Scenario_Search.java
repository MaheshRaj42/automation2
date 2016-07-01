package Scenario_Component;

import java.io.IOException;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.log4testng.Logger;

import Generic_Component.Base_Class;
import PageObject_Component.PageObject_Search;

public class Scenario_Search extends Base_Class{
	
	static Logger log= Logger.getLogger(Scenario_Search.class);
	SoftAssert sAssert = new SoftAssert();
	
	//Invalid Search
	
	@Test(dataProvider="dp_InvalidSearch",dataProviderClass=DataProvider_Component.DataProvider_Search.class)
	public void testInvalidsearch(String TC_ID, String Order, String Search_Item, String Exp_Result) throws IOException, InterruptedException
	{
		log.info("Executing the Test Case " +TC_ID+ "Order is " +Order);
		
		Start_Server();
		Init_App();
		
		PageObject_Search BS_POB = new PageObject_Search(driver);
		
		Explicit_wait(BS_POB.Search_btn, 25);
		BS_POB.Click_btn();
		
		Explicit_wait(BS_POB.Searchview_txtbox, 25);
		BS_POB.Enter_Search(Search_Item);
		
		Explicit_wait(BS_POB.Invalid_msg, 25);
		String Actual_Result = BS_POB.getInvalidmsg();
		
		if(Actual_Result.equals(Exp_Result))
		{
			log.info("Passed as Expected is "+Exp_Result + "Actual result is "+Actual_Result);
			Snapshot_screen(TC_ID, Order);
		}
		else
		{
			log.info("Failed as Expected is"+Exp_Result + "Actual Result is "+Actual_Result);
			Snapshot_screen(TC_ID, Order);
		}
		
		sAssert.assertAll();
		Stop_Server();
	}
	
	
	@Test(dataProvider="dp_ValidSearch",dataProviderClass=DataProvider_Component.DataProvider_Search.class)
	public void testValidsearch(String TC_ID, String Order, String Search_Item, String Exp_Result) throws IOException, InterruptedException
	{
		log.info("Executing the Test Case " +TC_ID+ "Order is " +Order);
		
		Start_Server();
		Init_App();
		
		PageObject_Search BS_POB = new PageObject_Search(driver);
		
		Explicit_wait(BS_POB.Search_btn, 25);
		BS_POB.Click_btn();
		
		Explicit_wait(BS_POB.Searchview_txtbox, 25);
		BS_POB.Enter_Search(Search_Item);
		
		Explicit_wait(BS_POB.Valid_msg, 25);
		String Output = BS_POB.getValidmsg();
		String Actual_Result = Output.replace(" products", "");
		
		if(Actual_Result.equals(Exp_Result))
		{
			log.info("Passed as Expected is "+Exp_Result + "Actual result is "+Actual_Result);
			Snapshot_screen(TC_ID, Order);
		}
		else
		{
			log.info("Failed as Expected is"+Exp_Result + "Actual Result is "+Actual_Result);
			Snapshot_screen(TC_ID, Order);
		}
		
		sAssert.assertAll();
		Stop_Server();
	}
	
	

}
