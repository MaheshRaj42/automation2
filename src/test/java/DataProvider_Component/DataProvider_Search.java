package DataProvider_Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.testng.annotations.DataProvider;

import Generic_Component.ExcelReadWrite;

public class DataProvider_Search {
	
	@DataProvider(name="dp_InvalidSearch")
	
	public static Iterator<String[]> getInvalidSearchdata() throws IOException
	{
		List<String[]> obj = flagRowValue("Invalid_Search");
		return obj.iterator();
	}

	@DataProvider(name="dp_ValidSearch")
	public static Iterator<String[]> getValidSearchdata() throws IOException
	{
		List<String[]> obj = flagRowValue("Valid_Search");
		return obj.iterator();
	}
	
	//Common method to add the data to string array and list
	public static List<String[]> flagRowValue(String scriptname) throws IOException {
		// TODO Auto-generated method stub
		ExcelReadWrite xl = new ExcelReadWrite("C:\\workspace\\Eclipse\\Projects\\Appium_frm\\BB_Project\\Test_Data\\Test_Data.xls");
		HSSFSheet Search_Scenario = xl.Setsheet("Search_Scenario");
		
		int RowCount = xl.getrowcount(Search_Scenario);
		
		//Create a list
		List<String[]> list_search = new ArrayList<String[]>();
		
		
		for (int i=1; i<=RowCount; i++)
		{
			String Execute_Flag = xl.Readvalue(Search_Scenario, i, "Execute_Flag");
			String Script_name = xl.Readvalue(Search_Scenario, i, "Script_name");
			
			
			if((Execute_Flag.equals("Y")) && (Script_name.equals(scriptname)))
			{
				String[] arr_search = new String[4];
				
				arr_search[0]=xl.Readvalue(Search_Scenario, i, "TC_ID");
				arr_search[1]=xl.Readvalue(Search_Scenario, i, "Order");
				arr_search[2]=xl.Readvalue(Search_Scenario, i, "Search_Item");
				arr_search[3]=xl.Readvalue(Search_Scenario, i, "Exp_Result");
				
				arr_search[3]=arr_search[3].replace(".0", "");
				list_search.add(arr_search);
				
			}
			
			
		}
		
		return list_search;
	}
	
	

}
