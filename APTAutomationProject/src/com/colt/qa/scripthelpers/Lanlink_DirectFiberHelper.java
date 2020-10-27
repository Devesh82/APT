 package com.colt.qa.scripthelpers;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.dom4j.DocumentException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;
import com.colt.qa.driverlibrary.DriverHelper;
import com.colt.qa.driverlibrary.DriverTestcase;
import com.colt.qa.driverlibrary.Log;
import com.colt.qa.driverlibrary.XMLReader;
import com.colt.qa.reporter.ExtentTestManager;

public class Lanlink_DirectFiberHelper extends DriverHelper {
	
	public Lanlink_DirectFiberHelper(WebDriver dr) {
		super(dr);
		// TODO Auto-generated constructor stub
	}

WebElement ChooseCustomer_Select, Next_Button, CreateOrderService_Text;
	
	XMLReader xml = new XMLReader("src\\com\\colt\\qa\\pagerepository\\Lanlink_DirectFiber.xml");

	boolean orderdopdown, serviceTypedropdown, modularmspCheckbox, autocreateservicecheckbox, interfacespeeddropdown,
			servicesubtypesdropdown, availablecircuitsdropdown, nextbutton, A_EndTechnolnogy, B_Endtechnolnogy;
	SoftAssert sa = new SoftAssert();


	
	public void navigateToManageCustomerServicePage() throws InterruptedException, DocumentException {
		Clickon(getwebelement(xml.getlocator("//locators/ManageCustomerServiceLink")));
		Thread.sleep(2000);
		Log.info("=== MCS page navigated ===");
		Thread.sleep(2000);
	}

	public void navigateToCreateOrderServicePage(String application) throws InterruptedException, DocumentException {

		navigateToManageCustomerServicePage();

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/CreateOrderServiceLink")));
		Thread.sleep(2000);
		Log.info("=== Create Order/Service navigated ===");
	}
	
	
	public void CreateCustomer(String application, String name, String maindomain, String country, String ocn,
			String reference, String tcn, String type, String email, String phone, String fax)
					throws Exception {

		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying create New Customer Functionality");
		
		Moveon(getwebelement(xml.getlocator("//locators/" + application + "/ManageCustomerServiceLink")));
		Thread.sleep(1000);
		Log.info("Mouser hovered on Manage Customer's Service");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Mouser hovered on 'Manage Customers Service' menu item");

		click_commonMethod(application, "create customer link", "createcustomerlink", xml);
		Thread.sleep(2000);
		compareText(application, "create customer page header", "createcustomer_header", "Create Customer", xml);
		scrolltoend();
		click_commonMethod(application, "Ok", "okbutton", xml);
		//Warning msg check
		warningMessage_commonMethod(application, "customernamewarngmsg", "Customer Name", xml);
		warningMessage_commonMethod(application, "countrywarngmsg", "Country" , xml);
		warningMessage_commonMethod(application, "ocnwarngmsg", "OCN", xml);
		warningMessage_commonMethod(application, "typewarngmsg", "Type", xml);
		warningMessage_commonMethod(application, "emailwarngmsg", "Email" , xml);

		//Clear customer info
		EnterTextValue(application, name, "Customer Name", "nametextfield");
		EnterTextValue(application, maindomain, "Main Domain", "maindomaintextfield");
		EnterTextValue(application, ocn, "OCN", "ocntextfield");
		EnterTextValue(application, reference, "Reference", "referencetextfield");
		EnterTextValue(application, tcn, "Technical Contact Name", "technicalcontactnametextfield");
		EnterTextValue(application, email, "Email", "emailtextfield");
		EnterTextValue(application, phone, "Phone", "phonetextfield");
		EnterTextValue(application, fax, "Fax", "faxtextfield");
		scrolltoend();
		click_commonMethod(application, "Reset", "resetButton", xml);
		ExtentTestManager.getTest().log(LogStatus.PASS, "All text field values are cleared");

		//Create customer by providing all info
		cleartext(application, "Customer Name", "nametextfield");
		EnterTextValue(application, name, "Customer Name", "nametextfield");
		cleartext(application, "Main Domain", "maindomaintextfield");
		EnterTextValue(application, maindomain, "Main Domain", "maindomaintextfield");
		addDropdownValues_commonMethod(application, "Country", "country", country, xml);
		cleartext(application, "OCN", "ocntextfield");
		EnterTextValue(application, ocn, "OCN", "ocntextfield");
		cleartext(application, "Reference", "referencetextfield");
		EnterTextValue(application, reference, "Reference", "referencetextfield");
		cleartext(application, "Technical Contact Name", "technicalcontactnametextfield");
		EnterTextValue(application, tcn, "Technical Contact Name", "technicalcontactnametextfield");
		addDropdownValues_commonMethod(application, "Type", "typedropdown", type, xml);
		cleartext(application, "Email", "emailtextfield");
		EnterTextValue(application, email, "Email", "emailtextfield");
		cleartext(application, "Phone", "phonetextfield");
		EnterTextValue(application, phone, "Phone", "phonetextfield");
		cleartext(application, "Fax", "faxtextfield");
		EnterTextValue(application, fax, "Fax", "faxtextfield");
		scrolltoend();
		click_commonMethod(application, "Ok", "okbutton", xml);
		compareText(application, "create customer success message", "customercreationsuccessmsg", "Customer successfully created.", xml);
		sa.assertAll();	
	}
	
	
	public void selectCustomertocreateOrder(String application, String ChooseCustomerToBeSelected)
			throws InterruptedException, DocumentException, IOException {

		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying Select Existing Customer Functionality");

		Moveon(getwebelement(xml.getlocator("//locators/" + application + "/ManageCustomerServiceLink")));
		Thread.sleep(1000);
		Log.info("Mouser hovered on Manage Customer's Service");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Mouser hovered on 'Manage Customers Service' menu item");

		click_commonMethod(application, "Create Order/Service Link", "CreateOrderServiceLink", xml);	
		Log.info("=== Create Order/Service navigated ===");

		//click_commonMethod on Next button to check mandatory messages	
			click_commonMethod(application, "Next", "nextbutton", xml);

		//Customer Error message	
			warningMessage_commonMethod(application, "customer_createorderpage_warngmsg", "Choose a customer", xml);

		//Entering Customer name
			addtextFields_commonMethod(application, "Customer Name", "entercustomernamefield", ChooseCustomerToBeSelected, xml);
		
		 
		waitforPagetobeenable();
		Thread.sleep(3000);
		EnterTextValue(application, "*", "Customer Name", "entercustomernamefield");	

		//Select Customer from dropdown
			addDropdownValues_commonMethod(application, "Choose a customer", "choosecustomerdropdown", ChooseCustomerToBeSelected, xml);
			click_commonMethod(application, "Next", "nextbutton", xml);

	}
	
	
	public void VerifyUsersPanel(String application, String Username, String Firstname, String Surname, String Postaladdress, String Email, String Phone, String EditUsername, 
			String EditFirstname, String EditSurname, String EditPostaladdress, String EditEmail, String EditPhone, String IPGuardianAccountGroup,String ColtOnlineUser, 
			String GeneratePassword, String RolesToBeSelected,String HideRouterToolsIPv6CommandsCisco_ToBeSelected, String HideRouterToolsIPv4CommandsHuiwai_ToBeSelected, 
			String HideRouterToolsIPv4CommandsCisco_ToBeSelected,String HideServicesToBeSelected,String HideSiteOrderToBeSelected, String editRolesToBeSelected, 
			String edit_RoleToBeHidden, String RouterToolsIPv6CommandsCisco_ToBeAvailable, String RouterToolsIPv6CommandsCisco_ToBeHidden, String RouterToolsIPv4CommandsHuiwai_ToBeAvailable, 
			String HideRouterToolsIPv4CommandsHuiwai_ToBeHidden, String HideRouterToolsIPv4CommandsCisco_ToBeAvailable, String HideRouterToolsIPv4CommandsCisco_ToBeHidden,
			String Services_ToBeAvailable, String Services_ToBeHidden, String SiteOrders_ToBeAvailable, String SiteOrders_ToBeHidden, String editIPGuardianAccountGroup, String editColtOnlineUser) 
					throws Exception {

		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying User Panel in 'View Service' page");
		
		String[] rolestobeSelectedList=RolesToBeSelected.split(",");
		String[] routerToolIPv4CiscoTobeSelectedList = HideRouterToolsIPv4CommandsCisco_ToBeSelected.split(",");
		String[] routerToolIPv4HuaweiTobeSelectedList =  HideRouterToolsIPv4CommandsHuiwai_ToBeSelected.split(",");
		String[] ServicesTobeSelectedlist= HideServicesToBeSelected.split(",");
		String[] siteOrdersToBeselectedList = HideSiteOrderToBeSelected.split(",");

		String[] rolestobeAvailableList=editRolesToBeSelected.split(",");
		String[] rolestobeHiddenList=edit_RoleToBeHidden.split(",");

		String[] routerToolIPv4CiscoTobeAvailableList = HideRouterToolsIPv4CommandsCisco_ToBeAvailable.split(",");
		String[] routerToolIPv4CiscoTobeHiddenList = HideRouterToolsIPv4CommandsCisco_ToBeHidden.split(",");

		String[] routerToolIPv4HuaweiTobeAvailableList =  RouterToolsIPv4CommandsHuiwai_ToBeAvailable.split(",");
		String[] routerToolIPv4HuaweiTobeHiddenList =  HideRouterToolsIPv4CommandsHuiwai_ToBeHidden.split(",");


		String[] ServicesTobeAvailablelist= Services_ToBeAvailable.split(",");
		String[] ServicesTobeHiddenlist= Services_ToBeHidden.split(",");

		String[] siteOrdersToBeAvailableList = SiteOrders_ToBeAvailable.split(",");
		String[] siteOrdersToBeHiddenList = SiteOrders_ToBeHidden.split(",");

		ScrolltoElement(application, "customerdetailsheader", xml);
		WebElement UserGridCheck= getwebelement("(//div[text()='Users']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div)[1]");
		String UserGrid= UserGridCheck.getAttribute("style");

			//Cancel User
			click_commonMethod(application, "Action dropdown", "UserActionDropdown", xml);
			click_commonMethod(application, "Add", "AddLink", xml);
			compareText(application, "Create User Header", "CreateUserHeader", "Create User", xml);
			scrolltoend();
			click_commonMethod(application, "Cancel", "cancelbutton", xml);
			compareText(application, "User panel Header", "userspanel_header", "Users", xml);

			//Create User
			ScrolltoElement(application, "customerdetailsheader", xml);
			click_commonMethod(application, "Action dropdown", "UserActionDropdown", xml);
			click_commonMethod(application, "Add", "AddLink", xml);
			compareText(application, "Create User Header", "CreateUserHeader", "Create User", xml);

			//Warning messages verify
			scrolltoend();
			Thread.sleep(1000);
			click_commonMethod(application, "OK", "OK_button", xml);
			scrollToTop();
			warningMessage_commonMethod(application, "warningmsg_username", "User Name", xml);
			warningMessage_commonMethod(application, "warningmsg_firstname", "First Name", xml);
			warningMessage_commonMethod(application, "warningmsg_surname", "Surname", xml);
			warningMessage_commonMethod(application, "warningmsg_postaladdress", "Postal Address", xml);
			warningMessage_commonMethod(application, "warningmsg_useremail", "Email", xml);
			warningMessage_commonMethod(application, "warningmsg_userphone", "Phone", xml);
			warningMessage_commonMethod(application, "warningmsg_userpassword", "Password", xml);

			addtextFields_commonMethod(application, "User Name", "UserName", Username, xml);
			addtextFields_commonMethod(application, "First Name", "FirstName", Firstname, xml);
			addtextFields_commonMethod(application, "SurName", "SurName", Surname, xml);
			addtextFields_commonMethod(application, "Postal Address", "PostalAddress", Postaladdress, xml);
			addtextFields_commonMethod(application, "Email", "Email", Email, xml);
			addtextFields_commonMethod(application, "Phone", "Phone", Phone, xml);
			addtextFields_commonMethod(application, "IPGuardian Account Group" , "IPGuardianAccountGroup" , IPGuardianAccountGroup, xml);
			addtextFields_commonMethod(application, "Colt Online User", "ColtOnlineUser", ColtOnlineUser, xml);
			click_commonMethod(application, "Generate Password", "GeneratePassword", xml);
			String  password=getwebelement(xml.getlocator("//locators/"+application+"/Password_Textfield")).getAttribute("value");
			Log.info("Generated Password is : "+password);

			if(password.isEmpty()) {

				ExtentTestManager.getTest().log(LogStatus.PASS, "Password Field is empty. No values displaying after clicked on 'Generate password link");

				SendKeys(getwebelement(xml.getlocator("//locators/"+application+"/Password_Textfield")), GeneratePassword);	
				Thread.sleep(1000);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Password entered manually not automatically generated :  "+GeneratePassword);
				Log.info("===Password entered manually not automatically generated ===");

			}else {
				Log.info("Automatically generated Password value is : "+ password);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Password generated and the value is displaying as :  "+password);
			}

			ScrolltoElement(application, "Email", xml);
			Thread.sleep(2000);

			//Role	
			selectAndAddValueFromLeftDropdown(application, "Role", "roleDropdown_available" , rolestobeSelectedList, "roleDropdown_addButton");
			verifySelectedValuesInsideRightDropdown(application, "Role", "roleDropdown_selectedValues");


			//Hide Service
			selectAndAddValueFromLeftDropdown(application, "Hide Service", "HideService_Available", ServicesTobeSelectedlist, "HideService_addButton");
			verifySelectedValuesInsideRightDropdown(application, "Hide Services", "HideServicesDropdown_selectedValues");


			//Hide Site Order
			selectAndAddValueFromLeftDropdown(application, "Hide Site Order", "HideSiteOrder_Available" , siteOrdersToBeselectedList , "hideSiteOrder_addbutton");
			verifySelectedValuesInsideRightDropdown(application, "Hide Site Order" , "HideSiteOrderDropdown_selectedValues");

			scrolltoend();
			Thread.sleep(1000);

			//Hide Router Tool IPv4 Commands(Cisco)
			selectAndAddValueFromLeftDropdown(application, "Hide Router Tool IPv4 Commands(Cisco)", "hideRouterToolIPv4_Cisco_Available", routerToolIPv4CiscoTobeSelectedList, "hideRouterToolIPv4_Cisco_addButton");
			verifySelectedValuesInsideRightDropdown(application, "Hide Router Tool IPv4 Commands(Cisco)", "hideRouterToolIpv4_Cisco_selectedvalues");


			//Hide Router Tool IPv4 Commands(Huawei)
			selectAndAddValueFromLeftDropdown(application, "Hide Router Tool IPv4 Commands(Huawei)" , "hideRouterToolIPv4_Huawei_available" , routerToolIPv4HuaweiTobeSelectedList, "hideRouterToolIPv4__Huawei_addButton");
			verifySelectedValuesInsideRightDropdown(application, "Hide Router Tool IPv4 Commands(Huawei)" , "hideRouterToolIpv4_Huawei_selectedvalues");


			//		//Hide Router Tool IPv6 Commands(Cisco)	
			//			selectAndAddValueFromLeftDropdown(application, "Hide Router Tool IPv6 Commands(Cisco)" , "HideRouterToolIPv6_Cisco_Available" , selectValue, xpathForAddButton);
			//			verifySelectedValuesInsideRightDropdown(application, "Hide Router Tool IPv6 Commands(Cisco)" , xpath);

			scrolltoend();
			Thread.sleep(1000);
			click_commonMethod(application, "OK", "OK_button", xml);
			Thread.sleep(2000);
			
			compareText(application, "Create User success message", "successmsg", "User successfully created", xml);
			ExtentTestManager.getTest().log(LogStatus.PASS, "User added successfully");
			Log.info("User added successfully");

			//Edit User
			ScrolltoElement(application, "customerdetailsheader", xml);
			List<WebElement> ExistingUsers= getwebelements(xml.getlocator("//locators/"+application+"/checkExistingUserUnderUserPanel"));
			int NoOfUsers = ExistingUsers.size();
			Log.info("Total users:"+ NoOfUsers);

			if(NoOfUsers==1 || NoOfUsers>1)
			{
				click_commonMethod(application, "UserNamefilter", "userPanelFilterButton", xml);
				Thread.sleep(1000);
				
				SendKeys(getwebelement(xml.getlocator("//locators/"+application+"/userNamefilterTextField")), Username);
				Thread.sleep(1000);
				clickOnBankPage();
				
				ScrolltoElement(application, "customerdetailsheader", xml);
				Thread.sleep(1000);
				
				WebElement AddedUser = getwebelement(xml.getlocator("//locators/"+application+"/selectuncheckedCheckbox_UserPanel").replace("value", Username));
				AddedUser.click();
				ExtentTestManager.getTest().log(LogStatus.PASS, "clicked on Existing user radio button");
				Log.info("clicked on Existing user radio button");

				click_commonMethod(application, "Action dropdown", "UserActionDropdown", xml);
				click_commonMethod(application, "Edit", "edit", xml);
				Thread.sleep(2000);
				compareText(application, "Edit User Header", "edituser_header", "Edit User", xml);
				scrollToTop();
				edittextFields_commonMethod(application, "User Name", "UserName" , EditUsername, xml);
				edittextFields_commonMethod(application, "First Name", "FirstName" , EditFirstname, xml);
				edittextFields_commonMethod(application, "Sur Name", "SurName" , EditSurname, xml);
				edittextFields_commonMethod(application, "Postal Address", "PostalAddress" , EditPostaladdress, xml);
				edittextFields_commonMethod(application, "Email", "Email" , EditEmail, xml);
				edittextFields_commonMethod(application, "Phone", "Phone" , EditPhone, xml);
				edittextFields_commonMethod(application, "IPGuardian Account Group" , "IPGuardianAccountGroup" , editIPGuardianAccountGroup, xml);
				edittextFields_commonMethod(application, "Colt Online User", "ColtOnlineUser", editColtOnlineUser, xml);

				String editpassword=getwebelement(xml.getlocator("//locators/"+application+"/Password")).getAttribute("value");
				Log.info("Generated Password is : "+editpassword);

				if(editpassword.isEmpty()) {

					ExtentTestManager.getTest().log(LogStatus.FAIL, "Password Field is empty. No values displaying under'Generate password link");

					click_commonMethod(application, "Generate Password", "GeneratePassword", xml);

				}else {
					Log.info("Automatically generated Password value is : "+ editpassword);
					ExtentTestManager.getTest().log(LogStatus.PASS, "Password value is displaying as :  "+editpassword);
				}

				ScrolltoElement(application, "Email", xml);

				//Role	
				selectAndRemoveValueFromRightDropdown(application, "Roles_Hidden", "roleDropdown_selectedValues", rolestobeAvailableList, "roleDropdown_removeButton");
				selectAndAddValueFromLeftDropdown(application, "Role_Available", "roleDropdown_available" , rolestobeHiddenList, "roleDropdown_addButton");
				verifySelectedValuesInsideRightDropdown(application, "Role_Hidden", "roleDropdown_selectedValues");

				//Hide Service
				selectAndRemoveValueFromRightDropdown(application, "Service_Hidden" , "HideServicesDropdown_selectedValues" , ServicesTobeAvailablelist, "HideService_removeButton");
				selectAndAddValueFromLeftDropdown(application, "Service_Available", "HideService_Available", ServicesTobeHiddenlist, "HideService_addButton");
				verifySelectedValuesInsideRightDropdown(application, "Hidden Services", "HideServicesDropdown_selectedValues");

				//Hide Site Order
				selectAndRemoveValueFromRightDropdown(application, "SiteOrder_Hidden" , "HideSiteOrderDropdown_selectedValues", siteOrdersToBeAvailableList, "hideSiteOrder_removeButton");
				selectAndAddValueFromLeftDropdown(application, "SiteOrder_Available", "HideSiteOrder_Available" , siteOrdersToBeHiddenList , "hideSiteOrder_addbutton");
				verifySelectedValuesInsideRightDropdown(application, "Hiden Site Orders" , "HideSiteOrderDropdown_selectedValues");

				scrolltoend();
				Thread.sleep(1000);

				//Hide Router Tool IPv4 Commands(Cisco)
				selectAndRemoveValueFromRightDropdown(application, "Router Tool IPv4 Commands(Cisco)_Available", "hideRouterToolIpv4_Cisco_selectedvalues", routerToolIPv4CiscoTobeAvailableList, "hideRouterToolIPv4_Cisco_removeButton");
				selectAndAddValueFromLeftDropdown(application, "Router Tool IPv4 Commands(Cisco)_Hidden", "hideRouterToolIPv4_Cisco_Available", routerToolIPv4CiscoTobeHiddenList, "hideRouterToolIPv4_Cisco_addButton");
				verifySelectedValuesInsideRightDropdown(application, "Hiden Router Tool IPv4 Commands(Cisco)", "hideRouterToolIpv4_Cisco_selectedvalues");

				//Hide Router Tool IPv4 Commands(Huawei)
				selectAndRemoveValueFromRightDropdown(application, "Router Tool IPv4 Commands(Huawei)_Hidden", "hideRouterToolIpv4_Huawei_selectedvalues", routerToolIPv4HuaweiTobeAvailableList, "hideRouterToolIPv4_Huawei_removeButton");
				selectAndAddValueFromLeftDropdown(application, "Router Tool IPv4 Commands(Huawei)_Available" , "hideRouterToolIPv4_Huawei_available" , routerToolIPv4HuaweiTobeHiddenList, "hideRouterToolIPv4__Huawei_addButton");
				verifySelectedValuesInsideRightDropdown(application, "Hideen Router Tool IPv4 Commands(Huawei)" , "hideRouterToolIpv4_Huawei_selectedvalues");

				scrolltoend();
				Thread.sleep(1000);
				click_commonMethod(application, "OK", "OK_button", xml);
				Thread.sleep(2000);
				
				 
				waitforPagetobeenable();
				
				clickOnBankPage();
				Thread.sleep(2000);
				
				scrollToTop();
				compareText(application, "User update success message", "successmsg", "User successfully updated", xml);
			}
			else
			{
				ExtentTestManager.getTest().log(LogStatus.FAIL, "No users displayed");
				Log.info("No users displayed");
			}

			
		//View User
			String UserNametoSelect=null;
			ScrolltoElement(application, "customerdetailsheader", xml);
			List<WebElement> ExistingUsers1= getwebelements(xml.getlocator("//locators/"+application+"/checkExistingUserUnderUserPanel"));
			int NoOfUsers1 = ExistingUsers1.size();
			Log.info("Total users:"+ NoOfUsers1);
			if(NoOfUsers1==1 || NoOfUsers1>1)
			{
				if(!EditUsername.equalsIgnoreCase("null")) {
					UserNametoSelect = Username;
				}
				else {
					UserNametoSelect = EditUsername;
				}
				click_commonMethod(application, "UserNamefilter", "userPanelFilterButton", xml);
				Thread.sleep(1000);
				
				SendKeys(getwebelement(xml.getlocator("//locators/"+application+"/userNamefilterTextField")), Username);
				Thread.sleep(1000);
				clickOnBankPage();
				
				ScrolltoElement(application, "customerdetailsheader", xml);
				Thread.sleep(1000);
				
				WebElement AddedUser = getwebelement(xml.getlocator("//locators/"+application+"/selectuncheckedCheckbox_UserPanel").replace("value", UserNametoSelect));
				AddedUser.click();
				ExtentTestManager.getTest().log(LogStatus.PASS, "clicked on Existing user radio button");
				Log.info("clicked on Existing user radio button");
				Thread.sleep(2000);
				
				click_commonMethod(application, "Action dropdown", "UserActionDropdown", xml);
				click_commonMethod(application, "view", "view", xml);
				scrollToTop();
				
			//Username	
				if(EditUsername.equalsIgnoreCase("null")) {
					compareText(application, "User Name", "usernamevalue", Username, xml);
				}else {
					compareText(application, "User Name", "usernamevalue", EditUsername, xml);
				}
				
			//Firstname	
				if(EditFirstname.equalsIgnoreCase("null")) {
					compareText(application, "First Name", "firstnamevalue", Firstname, xml);
				}else {
					compareText(application, "First Name", "firstnamevalue", EditFirstname, xml);
				}
				
			//Surname	
				if(EditSurname.equalsIgnoreCase("null")) {
					compareText(application, "SurName", "surnamevalue", Surname, xml);
				}else {
					compareText(application, "SurName", "surnamevalue", EditSurname, xml);
				}
				
			//postal Address
				if(EditPostaladdress.equalsIgnoreCase("null")) {
					compareText(application, "Postal Address", "postaladdressvalue", Postaladdress, xml);
				}else {
					compareText(application, "Postal Address", "postaladdressvalue", EditPostaladdress, xml);
				}
				
			//Email
				if(EditPostaladdress.equalsIgnoreCase("null")) {
					compareText(application, "Email", "emailvalue", Email, xml);
				}else {
					compareText(application, "Email", "emailvalue", EditEmail, xml);
				}
				
			//Phone
				if(EditPostaladdress.equalsIgnoreCase("null")) {
					compareText(application, "Phone", "phonevalue", Phone, xml);
				}else {
					compareText(application, "Phone", "phonevalue", EditPhone, xml);
				}	
				

				//IP Guardian Accouunt Group
				GetText(application, "IPGuardian Account Group", "IPGuardianAccountGroup_viewpage");

				//Colt Online User
				GetText(application, "Colt Online User", "coltonlineuser_viewpage");

				ScrolltoElement(application, "usernamevalue", xml);
				Thread.sleep(1000);

				//Roles
				//compareTextForViewUserPage(application, labelname, ExpectedText, xml);


				//Hidden Router Tools IPv4 (Cisco)
				List<WebElement> HRcisco = getwebelements(xml.getlocator("//locators/"+application+"/viewUser_HiddenRouterToolIPv4Cisco"));	

				for(WebElement listofHiddenCiscoValues : HRcisco) {
					Log.info("list of values in Hide router Tool Command IPv4(Cisco) are: "+listofHiddenCiscoValues.getText());
					ExtentTestManager.getTest().log(LogStatus.PASS, "List of Hidden Router Tool IPv4 Commands(Cisco) are: " + listofHiddenCiscoValues.getText());
				}

				scrolltoend();
				Thread.sleep(2000);

				//Hidden Router Tool IPv4 (Huawei)
				List<WebElement> Ipv4CommandHuawei = getwebelements(xml.getlocator("//locators/"+application+"/viewUser_HiddenRouterToolCommandIPv4Huawei"));	

				for(WebElement listofHuaweiValues : Ipv4CommandHuawei) {
					Log.info("list of values in Hide router Tool Command (Cisco) are: "+listofHuaweiValues.getText());
					ExtentTestManager.getTest().log(LogStatus.PASS, "List of Hidden Router Tool IPv4 Commands(Huawei) are: "+ listofHuaweiValues.getText());
				}	


				//Hidden Router Tools IPv6 (Cisco)
				List<WebElement> HiddenIPv6cisco = getwebelements(xml.getlocator("//locators/"+application+"/viewUser_HiddenRouterToolCommandIPv6Cisco"));	

				for(WebElement listofHiddenIPv6CiscoValues : HiddenIPv6cisco) {
					Log.info("list of values in Hide router Tool Command IPv6 (Cisco) are: "+listofHiddenIPv6CiscoValues.getText());
					ExtentTestManager.getTest().log(LogStatus.PASS, "List of Hidden Router Tool IPv6 Commands(Cisco) are: " + listofHiddenIPv6CiscoValues.getText());
				}			

				scrolltoend();
				Thread.sleep(2000);
				click_commonMethod(application, "Back", "viewpage_backbutton", xml);
				Log.info("------ View User successful ------");
			}
			else
			{
				ExtentTestManager.getTest().log(LogStatus.FAIL, "No users displayed");
				Log.info("No users displayed");
			}


			//Delete User
			ScrolltoElement(application, "customerdetailsheader", xml);
			List<WebElement> ExistingUsers2= getwebelements(xml.getlocator("//locators/"+application+"/checkExistingUserUnderUserPanel"));
			int NoOfUsers2 = ExistingUsers2.size();
			Log.info("Total users:"+ NoOfUsers2);
			if(NoOfUsers2==1 || NoOfUsers2>1)
			{
				if(!EditUsername.equalsIgnoreCase("null")) {
					UserNametoSelect = Username;
				}
				else {
					UserNametoSelect = EditUsername;
				}
				click_commonMethod(application, "UserNamefilter", "userPanelFilterButton", xml);
				Thread.sleep(1000);
				
				SendKeys(getwebelement(xml.getlocator("//locators/"+application+"/userNamefilterTextField")), Username);
				Thread.sleep(1000);
				clickOnBankPage();
				
				ScrolltoElement(application, "customerdetailsheader", xml);
				Thread.sleep(1000);
				
				WebElement AddedUser = getwebelement(xml.getlocator("//locators/"+application+"/selectuncheckedCheckbox_UserPanel").replace("value", UserNametoSelect));
				AddedUser.click();
				ExtentTestManager.getTest().log(LogStatus.PASS, "clicked on Existing user radio button");
				Log.info("clicked on Existing user radio button");
				Thread.sleep(2000);

				click_commonMethod(application, "Action dropdown", "UserActionDropdown", xml);
				click_commonMethod(application, "Delete", "delete", xml);
				Thread.sleep(2000);
				
				Alert alert= driver.switchTo().alert ();
				alert.accept();
				
			}
			else
			{
				ExtentTestManager.getTest().log(LogStatus.PASS, "No users displayed");
				Log.info("No users displayed");
			}
		}
	

	public void verifyorderpanel_editorder(String application, String editorderno, String editvoicelineno, String editOrderSelection) 
			throws InterruptedException, DocumentException, IOException {

		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying 'Edit Order' Functionality");
		
		ScrolltoElement(application, "userspanel_header", xml);

		if(editOrderSelection.equalsIgnoreCase("no")) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "Edit Order is not performed");
			Log.info("Edit Order is not performed");
		}
		else if(editOrderSelection.equalsIgnoreCase("Yes")) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "Performing Edit Order Functionality");
		
		//Cancel Edit order in Order panel
		click_commonMethod(application, "Action dropdown", "orderactionbutton", xml);
		click_commonMethod(application, "Edit Order", "editorderlink", xml);
		compareText(application, "Edit Order", "editorderheader", "Edit Order", xml);
		Thread.sleep(1000);

		WebElement EditOrderNo= getwebelement(xml.getlocator("//locators/" + application + "/editorderno"));
		click_commonMethod(application, "Order Number", "editorderno", xml);
		Thread.sleep(2000);
		Clear(EditOrderNo);
		Thread.sleep(2000);
		addtextFields_commonMethod(application, "Order Number", "editorderno", editorderno, xml);

		WebElement EditVoiceLineNo= getwebelement(xml.getlocator("//locators/" + application + "/editvoicelineno"));
		click_commonMethod(application, "RFI Voice Line Number", "editvoicelineno", xml);
		Thread.sleep(2000);
		Clear(EditVoiceLineNo);
		Thread.sleep(2000);
		addtextFields_commonMethod(application, "RFI Voiceline Number", "editvoicelineno", editvoicelineno, xml);
		click_commonMethod(application, "Cancel", "cancelbutton", xml);

		//Edit Order
		Thread.sleep(1000);
		ScrolltoElement(application, "userspanel_header", xml);
		Thread.sleep(1000);
		click_commonMethod(application, "Action dropdown", "orderactionbutton", xml);
		click_commonMethod(application, "Edit Order", "editorderlink", xml);
		compareText(application, "Edit Order Header", "editorderheader", "Edit Order", xml);
		Thread.sleep(1000);
		click_commonMethod(application, "Order Number", "editorderno", xml);
		Thread.sleep(2000);
		cleartext(application, "Order Number", "editorderno");
		Thread.sleep(2000);
		addtextFields_commonMethod(application, "Order Number", "editorderno", editorderno, xml);
		click_commonMethod(application, "RFI Voice Line Number", "editvoicelineno", xml);
		Thread.sleep(2000);
		cleartext(application, "RFI Voice Line Number", "editvoicelineno");
		Thread.sleep(2000);
		addtextFields_commonMethod(application, "RFI Voice Line Number", "editvoicelineno", editvoicelineno, xml);
		click_commonMethod(application, "OK", "editorder_okbutton", xml);
		Thread.sleep(1000);
		ScrolltoElement(application, "userspanel_header", xml);
		Thread.sleep(1000);
//		compareText(application, "Order Header", "orderpanelheader", "Order", xml);
//		Log.info("Navigated to order panel in view service page");
//		ExtentTestManager.getTest().log(LogStatus.PASS, "Navigated to order panel in view service page");

		if(editorderno.equalsIgnoreCase("Null")) {
			
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Order/Contract Number (Parent SID)' field is not edited");
			Log.info("'Order/Contract Number (Parent SID)' field is not edited");
		}else {
			compareText(application, "Order Number", "ordernumbervalue", editorderno, xml);
		}
		
		if(editvoicelineno.equalsIgnoreCase("Null")) {
			ExtentTestManager.getTest().log(LogStatus.PASS,"'RFI/RFQ/IP Voice Line Number' field is not edited");
			Log.info("'RFI/RFQ/IP Voice Line Number' field is not edited");
		}else {
			compareText(application, "RFI Voice Line Number", "ordervoicelinenumbervalue", editvoicelineno, xml);
		}
		Log.info("------ Edit Order is successful ------");
		}

	}

	public void verifyorderpanel_changeorder(String application, String ChangeOrder_newOrderNumber, String changevoicelineno, String changeOrderSelection_newOrder,
			String changeOrderSelection_existingOrder, String ChangeOrder_existingOrderNumber) throws InterruptedException, 
	DocumentException, IOException {

		ScrolltoElement(application, "userspanel_header", xml);
				
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying 'Change Order' Functionality");
		
		if((changeOrderSelection_newOrder.equalsIgnoreCase("No")) && (changeOrderSelection_existingOrder.equalsIgnoreCase("No"))) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "Change Order is not performed");
			Log.info("Change Order is not performed");
		}
		else if(changeOrderSelection_newOrder.equalsIgnoreCase("Yes")) {
			
			//Change Order
			click_commonMethod(application, "Action dropdown", "orderactionbutton", xml);
			click_commonMethod(application, "Change Order", "changeorderlink", xml);
			compareText(application, "Change Order header", "changeorderheader", "Change Order", xml);
			Thread.sleep(1000);
			click_commonMethod(application, "Select order switch", "changeorder_selectorderswitch", xml);
			click_commonMethod(application, "Order Number", "changeordernumber", xml);
			Thread.sleep(2000);
			addtextFields_commonMethod(application, "Order Number", "changeordernumber", ChangeOrder_newOrderNumber, xml);
			click_commonMethod(application, "RFI Voice Line Number", "changeordervoicelinenumber", xml);
			Thread.sleep(3000);
			addtextFields_commonMethod(application, "RFI Voice Line Number", "changeordervoicelinenumber", changevoicelineno, xml);
			click_commonMethod(application, "Create Order", "createorder_button", xml);
			Thread.sleep(1000);
			ScrolltoElement(application, "userspanel_header", xml);
			Thread.sleep(1000);
			compareText(application, "Order Number", "ordernumbervalue", ChangeOrder_newOrderNumber, xml);
			compareText(application, "RFI Voice Line Number", "ordervoicelinenumbervalue", changevoicelineno, xml);
			Log.info("------ Change Order is successful ------");
		}
		else if(changeOrderSelection_existingOrder.equalsIgnoreCase("yes")) 
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Performing Change Order functionality");
			
			ScrolltoElement(application, "userspanel_header", xml);
			Thread.sleep(1000);
			click_commonMethod(application, "Action dropdown", "orderactionbutton", xml);
			click_commonMethod(application, "Change Order", "changeorderlink", xml);
			compareText(application, "Change Order header", "changeorderheader", "Change Order", xml);
			Thread.sleep(1000);
			
				addDropdownValues_commonMethod(application, "Order/Contract Number (Parent SID)", "changeOrder_orderDropdown", ChangeOrder_existingOrderNumber, xml);
				
				click_commonMethod(application, "OK", "changeorder_okbutton", xml);
				Thread.sleep(1000);
				ScrolltoElement(application, "userspanel_header", xml);
				Thread.sleep(1000);
				compareText(application, "Order Number", "ordernumbervalue", ChangeOrder_existingOrderNumber, xml);
				compareText(application, "RFI Voice Line Number", "ordervoicelinenumbervalue", changevoicelineno, xml);
				Log.info("------ Change Order is successful ------");
	
		}
		
	}



	
	public void selectCustomertocreateOrderfromleftpane(String application, String ChooseCustomerToBeSelected)
			throws InterruptedException, DocumentException, IOException {
		

		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying Select Existing Customer Functionality");

		Moveon(getwebelement(xml.getlocator("//locators/" + application + "/ManageCustomerServiceLink")));
		Thread.sleep(3000);
		Log.info("Mouser hovered on Manage Customer's Service");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Mouse hovered on 'Manage Customers Service' menu item");

		click_commonMethod(application, "Create Order/Service Link", "CreateOrderServiceLink", xml);	
		Log.info("=== Create Order/Service navigated ===");


		//Entering Customer name
			addtextFields_commonMethod(application, "Customer Name", "entercustomernamefield", ChooseCustomerToBeSelected, xml);
		
		 
		waitforPagetobeenable();
		Thread.sleep(3000);
		EnterTextValue(application, "*", "Customer Name", "entercustomernamefield");
		
		 
		waitforPagetobeenable();
		Thread.sleep(2000);

		//Select Customer from dropdown
			addDropdownValues_commonMethod(application, "Choose a customer", "chooseCustomerdropdown", ChooseCustomerToBeSelected, xml);
			click_commonMethod(application, "Next", "nextbutton", xml);
		
	}


	public void verifyCreateOrderServiceFields(String application)
			throws InterruptedException, DocumentException, IOException {
		navigateToCreateOrderServicePage("CreateOrderService");

		CreateOrderService_Text = getwebelement(
				xml.getlocator("//locators/" + application + "/CreateOrderService_Text"));
		sa.assertTrue(CreateOrderService_Text.isDisplayed(), "CreateOrderService_Text  is not displayed");
//		sa.assertTrue(CreateOrderService_Text.isEnabled(), "CreateOrderService_Text is not disabled");
		Log.info("=== Create Order Service Text is displayed ===");

		ChooseCustomer_Select = getwebelement(xml.getlocator("//locators/" + application + "/ChooseCustomer_Select"));
		sa.assertTrue(ChooseCustomer_Select.isDisplayed(), "ChooseCustomer_Select dropdown is not displayed");
//		sa.assertTrue(ChooseCustomer_Select.isEnabled(), "ChooseCustomer_Select dropdown is not disabled");
		Log.info("=== Choose Customer dropdown is displayed ===");

		Next_Button = getwebelement(xml.getlocator("//locators/" + application + "/Next_Button"));
		sa.assertTrue(Next_Button.isDisplayed(), "Next_Button  is not displayed");
//		sa.assertTrue(Next_Button.isEnabled(), "Next_Button is not disabled");
		Log.info("=== Next_Button  is displayed ===");

		Log.info("=== Create Order/Service all fields Verified ===");
		sa.assertAll();
	}

	public void verifyCreateOrderDetailsInformation(String application)
			throws InterruptedException, DocumentException, IOException {

		// verify Name information
		String Name_Text = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/Name_Text")));
		String Name_Value = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/Name_Value")));
		Log.info(Name_Text + " : TextField value is displayed as : " + Name_Value);
		Log.info(Name_Text + " : " + Name_Value);

		// verify MainDomain information
		String MainDomain_Text = Gettext(
				getwebelement(xml.getlocator("//locators/" + application + "/MainDomain_Text")));
		String MainDomain_Value = Gettext(
				getwebelement(xml.getlocator("//locators/" + application + "/MainDomain_Value")));
		Log.info(MainDomain_Text + " : TextField value is displayed as : " + MainDomain_Value);
		Log.info(MainDomain_Text + "  " + MainDomain_Value);

		// verify Country information
		String Country_Text = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/Country_Text")));
		String Country_Value = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/Country_Value")));
		Log.info(Country_Text + " : TextField value is displayed as : " + Country_Value);
		Log.info(Country_Text + " : " + Country_Value);

		// verify OCN information
		String OCN_Text = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/OCN_Text")));
		String OCN_Value = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/OCN_Value")));
		Log.info(OCN_Text + " : TextField value is displayed as : " + OCN_Value);
		Log.info(OCN_Text + " : " + OCN_Value);

		// verify Reference information
		String Reference_Text = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/Reference_Text")));
		String Reference_Value = Gettext(
				getwebelement(xml.getlocator("//locators/" + application + "/Reference_Value")));
		Log.info(Reference_Text + " : TextField value is displayed as : " + Reference_Value);
		Log.info(Reference_Text + " : " + Reference_Value);

		// verify Type information
		String Type_Text = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/Type_Text")));
		String Type_Value = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/Type_Value")));
		Log.info(Type_Text + " : TextField value is displayed as : " + Type_Value);
		Log.info(Type_Text + " : " + Type_Value);

		// verify Technical Contact Name information
		String TechnicalContactName_Text = Gettext(
				getwebelement(xml.getlocator("//locators/" + application + "/TechnicalContactName_Text")));
		String TechnicalContactName_Value = Gettext(
				getwebelement(xml.getlocator("//locators/" + application + "/TechnicalContactName_Value")));
		Log.info(TechnicalContactName_Text + " : TextField value is displayed as : " + TechnicalContactName_Value);
		Log.info(TechnicalContactName_Text + " : " + TechnicalContactName_Value);

		// verify Name2 information
		String Name2_Text = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/Name2_Text")));
		String Name2_Value = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/Name2_Value")));
		Log.info(Name2_Text + " : TextField value is displayed as : " + Name2_Value);
		Log.info(Name2_Text + " : " + Name2_Value);

		// verify Email information
		String Email_Text = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/Email_Text")));
		String Email_Value = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/Email_Value")));
		Log.info(Email_Text + " : TextField value is displayed as : " + Email_Value);
		Log.info(Email_Text + " : " + Email_Value);

		// verify Phone information
		String Phone_Text = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/Phone_Text")));
		String Phone_Value = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/Phone_Value")));
		Log.info(Phone_Text + " : TextField value is displayed as : " + Phone_Value);
		Log.info(Phone_Text + " : " + Phone_Value);

		// verify Fax information
		String Fax_Text = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/Fax_Text")));
		String Fax_Value = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/Fax_Value")));
		Log.info(Fax_Text + " : TextField value is displayed as : " + Fax_Value);
		Log.info(Fax_Text + " : " + Fax_Value);

		Log.info("=== Create Order Detail all fields Verified ===");
		sa.assertAll();
	}

	public void select_ChooseCustomer(String application, String ChooseCustomerToBeSelected)
			throws IOException, InterruptedException, DocumentException {
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/ChooseCustomer_Select")));
		Thread.sleep(1000);
		WebElement el1 = driver.findElement(By.xpath("//div[contains(text(),'" + ChooseCustomerToBeSelected + "')]"));
		el1.click();
		Log.info("=== Choose Customer selected===");
	}


	


public static String newordernumber, newVoiceLineNumber, SelectOrderNumber;
	
	public void createorderservice(String application, String neworderSelection, String neworderno, String newrfireqno, String existingorderSelection,
			String existingordernumber)
			throws InterruptedException, IOException, DocumentException {

	//Create New order or select Existing Order	
		scrolltoend();
		if (neworderSelection.equalsIgnoreCase("YES")) {

			WebElement CreateOrder_Header= getwebelement(xml.getlocator("//locators/" + application + "/createOrderORService"));
			scrolltoview(CreateOrder_Header);
			Thread.sleep(2000);
			
				
			EnterTextValue(application, neworderno, "Order/Contract Number", "newordertextfield");
			EnterTextValue(application, newrfireqno, "RFI Voice line Number", "newrfireqtextfield");
			click_commonMethod(application, "create order", "createorderbutton", xml);
			
			scrollToTop();
			Thread.sleep(1000);
			compareText(application, "create order success message", "OrderCreatedSuccessMsg", "Order created successfully", xml);			
			scrolltoview(CreateOrder_Header);
		} 
		
		else if (existingorderSelection.equalsIgnoreCase("YES")) {
			click_commonMethod(application, "select order switch", "selectorderswitch", xml);
			addDropdownValues_commonMethod(application, "Order/Contract Number(Parent SID)", "existingorderdropdown", existingordernumber , xml);
			Log.info("=== Order Contract Number selected===");

			Thread.sleep(3000);

		} else {
			Log.info("Order not selected");
			ExtentTestManager.getTest().log(LogStatus.INFO, "Order not selected");
		}
			
	}
	
	
	public void selectOrder(String application, String neworderSelection, String neworderno, String newrfireqno, String existingorderSelection,
			String existingordernumber)
			throws InterruptedException, IOException, DocumentException {

		 
		waitforPagetobeenable();
		
	//Create New order or select Existing Order	
		scrolltoend();
		if (neworderSelection.equalsIgnoreCase("YES")) {
			scrolltoend();
			Thread.sleep(1000);
			
			click_commonMethod(application, "select order switch", "selectorderswitch", xml);
			addDropdownValues_commonMethod(application, "Order/Contract Number(Parent SID)", "existingorderdropdown", neworderno , xml);
		} 
		
		else if (existingorderSelection.equalsIgnoreCase("YES")) {
			
			scrolltoend();
			click_commonMethod(application, "select order switch", "selectorderswitch", xml);
			addDropdownValues_commonMethod(application, "Order/Contract Number(Parent SID)", "existingorderdropdown", existingordernumber , xml);
			Log.info("=== Order Contract Number selected===");

			Thread.sleep(3000);

		} else {
			Log.info("Order not selected");
			ExtentTestManager.getTest().log(LogStatus.INFO, "Order not selected");
		}
	}

	
	public void selectServiceType(String application, String serviceTypeToBeSelected) throws InterruptedException, DocumentException {
		
		//select Service Type
		scrolltoend();
		Thread.sleep(3000);
		
		addDropdownValues_commonMethod(application, "Service Type", "servicetypedropdowntoclick", serviceTypeToBeSelected, xml);
		
	}
	

	public void  selectsubtypeunderServiceTypeSelected(String application, String SelectSubService, String Interfacespeed,
			String modularmsp, String autoCreateService, String A_Endtechnologydropdown, String B_Endtechnologydropdown)
			throws InterruptedException, DocumentException, IOException {
		
		
		scrolltoend();
		Thread.sleep(1000);


		if (modularmsp.equalsIgnoreCase("no") && autoCreateService.equalsIgnoreCase("no")) {
			
			ExtentTestManager.getTest().log(LogStatus.PASS,"when'Modular msp' and 'Autocreate service' are not selected,   'Interface speed' value and 'Service subtype' value should be selected as mandatory ");

			// Select interface speed
				addDropdownValues_commonMethod(application, "Interface Speed", "InterfaceSpeed", Interfacespeed, xml);
	
	
	// select service sub type
	boolean serviceSubTypeAvailability=false;
	serviceSubTypeAvailability=	getwebelement(xml.getlocator("//locators/" + application + "/ServiceSubtype")).isDisplayed();

	if(serviceSubTypeAvailability) {
		ExtentTestManager.getTest().log(LogStatus.PASS, " 'Service subtype mandatory dropdown is displaying as expected");
		Log.info(" 'Service subtype mandatory dropdown is displaying as expected");
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/ServiceSubtype")));
		Thread.sleep(3000);
		Log.info("clicked on srvice type");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Service subtype dropdown has been selected");
		
		if((SelectSubService.equals("LANLink International")) || (SelectSubService.equals("LANLink Metro")) || SelectSubService.equals("LANLink National") ||
		         SelectSubService.equals("OLO - (GCR/EU)") || (SelectSubService.equals("Direct Fiber")) || (SelectSubService.equals("LANLink Outband Management"))){

		WebElement el2 = getwebelement("//div[contains(text(),'" + SelectSubService + "')]");
		el2.click();
		Log.info("=== Service sub Type selected===");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Service sub type" +SelectSubService +" has been selected");
		}
		else{
			Log.info(SelectSubService+ " is not available under Service subtype dropdown when Modular msp is selected");
			
			ExtentTestManager.getTest().log(LogStatus.FAIL,SelectSubService+ " is not available when Modular msp is selected."
					+ "                      The list of sub services types under LANLINK when moduler msp is selected are:"
					+ "    1) Direct Fiber"
					+ "    2) LANLink International"
					+ "    3) LANLink Metro"
					+ "    4) LANLink National"
					+ "    5) Lanlink Outband management"
					+ "    6) OLO - (GCR/EU)");
//			driver.close();
		}

	}else {
		ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Service subtype mandatory dropdown is not displaying");
	}

			
//			 clickon(getwebelement(xml.getlocator("//locators/"+application+"/AvailableCircuits")));

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Next")));
			Thread.sleep(3000);

			Log.info("Page has to be selected based on service and its subtype selected");

		}

		else if (modularmsp.equalsIgnoreCase("yes") && autoCreateService.equalsIgnoreCase("no")) {
			
			ExtentTestManager.getTest().log(LogStatus.INFO,"when 'Modular msp' is selected and 'Autocreateservice' is not selected, 'Service subtype' value should be selected as it is mandatory field ");
			
          try {
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/modularmspcheckbox")));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Modular msp checkbox has been selected");
          }catch(Exception e) {
        	  e.printStackTrace();
        	  ExtentTestManager.getTest().log(LogStatus.FAIL, "Modular msp check box is not available");
        	  
          }

	// select service sub type
      	boolean serviceSubTypeAvailability=false;
		serviceSubTypeAvailability=	getwebelement(xml.getlocator("//locators/" + application + "/ServiceSubtype")).isDisplayed();
	
		if(serviceSubTypeAvailability) {
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Service subtype mandatory dropdown is displaying as expected");
			Log.info(" 'Service subtype mandatory dropdown is displaying as expected");
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/ServiceSubtype")));
			Thread.sleep(3000);
			Log.info("clicked on srvice type");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Service subtype dropdown has been selected");
			
			if(SelectSubService.equals("LANLink International") || SelectSubService.equals("LANLink Metro") || SelectSubService.equals("LANLink National") ||
			         SelectSubService.equals("OLO - (GCR/EU)")){

			WebElement el2 = getwebelement("//div[contains(text(),'" + SelectSubService + "')]");
			el2.click();
			Log.info("=== Service sub Type selected===");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Service sub type" +SelectSubService +" has been selected");
			}
			else{
				Log.info(SelectSubService+ " is not available under Service subtype dropdown when Modular msp is selected");
				
				ExtentTestManager.getTest().log(LogStatus.FAIL,SelectSubService+ " is not available when Modular msp is selected."
						+ "                      The list of sub services types under LANLINK when moduler msp is selected are:"
						+ "    1) LANLink International"
						+ "    2) LANLink Metro"
						+ "    3) LANLink National"
						+ "    4) OLO - (GCR/EU)");
//				driver.close();
			}

		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Service subtype mandatory dropdown is not displaying");
		}

//			 SendKeys(getwebelement(xml.getlocator("//locators/"+Application+"/AvailableCircuits")),
//			 Availablecircuits);

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Next")));
			Thread.sleep(3000);

			Log.info("Page has to be selected based on service and its subtype selected");

		}

		if (modularmsp.equalsIgnoreCase("no") && autoCreateService.equalsIgnoreCase("yes")) {
			
			
			ExtentTestManager.getTest().log(LogStatus.INFO, " 'Service subtype' should be selected as mandatory when 'AutocreateService' is selected, 'Modular msp' not selected");
			
			Log.info("Only auto creta check box is selected");
			
			try {
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AutocreateServicecheckbox")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Autocreateservice' checkbox is selected ");
			}catch(Exception e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Auto create service' checkbox is not available under 'Service' panel ");
			}

	//A end technology	
		addDropdownValues_commonMethod(application, "A-End Technology", "A_Endtechnology", A_Endtechnologydropdown, xml);
		
	//B end technology	
		addDropdownValues_commonMethod(application, "B-End Technology", "B_Endtechnology", B_Endtechnologydropdown, xml);
		
	//Interface speed
		addDropdownValues_commonMethod(application, "Interface Speed", "InterfaceSpeed", Interfacespeed, xml);
		

	// select service sub type
		
		boolean serviceSubTypeAvailability=false;
		serviceSubTypeAvailability=	getwebelement(xml.getlocator("//locators/" + application + "/ServiceSubtype")).isDisplayed();
	
		if(serviceSubTypeAvailability) {
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Service subtype mandatory dropdown is displaying as expected");
			Log.info(" 'Service subtype mandatory dropdown is displaying as expected");
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/ServiceSubtype")));
			Thread.sleep(1000);
			Log.info("clicked on srvice type");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Service subtype dropdown has been selected");
			
			if((SelectSubService.equals("LANLink International")) || (SelectSubService.equals("LANLink Metro")) || SelectSubService.equals("LANLink National") ||
			         SelectSubService.equals("OLO - (GCR/EU)") || (SelectSubService.equals("Direct Fiber")) || (SelectSubService.equals("LANLink Outband Management"))){

			WebElement el2 = getwebelement("//div[contains(text(),'" + SelectSubService + "')]");
			el2.click();
			Log.info("=== Service sub Type selected===");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Service sub type " +SelectSubService +" has been selected");
			}
			else{
				Log.info(SelectSubService+ " is not available under Service subtype dropdown when Modular msp is selected");
				
				ExtentTestManager.getTest().log(LogStatus.FAIL,SelectSubService+ " is not available when Modular msp is selected."
						+ "                      The list of sub services types under LANLINK when moduler msp is selected are:"
						+ "    1) Direct Fiber"
						+ "    2) LANLink International"
						+ "    3) LANLink Metro"
						+ "    4) LANLink National"
						+ "    5) Lanlink Outband management"
						+ "    6) OLO - (GCR/EU)");
				
//				driver.close();
			}

		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Service subtype mandatory dropdown is not displaying");
		}
		scrolltoend();

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Next")));
			Thread.sleep(3000);

			Log.info("Page has to be selected based on service and its subtype selected");

		}

		if (modularmsp.equalsIgnoreCase("yes") && autoCreateService.equalsIgnoreCase("yes")) {
			
			ExtentTestManager.getTest().log(LogStatus.INFO, " 'Service subtype' is mandatory when 'modular msp' and 'Autocreateservices' are selected");

			
		//modular msp	
			 try {
					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/modularmspcheckbox")));
					ExtentTestManager.getTest().log(LogStatus.PASS, "Modular msp checkbox has been selected");
	              }catch(Exception e) {
	            	  e.printStackTrace();
	            	  ExtentTestManager.getTest().log(LogStatus.FAIL, "Modular msp check box is not available under 'Service' panel");
	            	  
	              }

		//Auto create service	 
			 try {
					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AutocreateServicecheckbox")));
					ExtentTestManager.getTest().log(LogStatus.PASS, " 'Autocreateservice' checkbox is selected ");
					}catch(Exception e) {
						e.printStackTrace();
						ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Auto create service' checkbox is not available under 'Service' panel ");
					}

			//A end technology	
				addDropdownValues_commonMethod(application, "A-End Technology", "A_Endtechnology", A_Endtechnologydropdown, xml);
				
			//B end technology	
				addDropdownValues_commonMethod(application, "B-End Technology", "B_Endtechnology", B_Endtechnologydropdown, xml);
				
			
		// select service sub type
			boolean serviceSubTypeAvailability=false;
			serviceSubTypeAvailability=	getwebelement(xml.getlocator("//locators/" + application + "/ServiceSubtype")).isDisplayed();
		
			if(serviceSubTypeAvailability) {
				ExtentTestManager.getTest().log(LogStatus.PASS, " 'Service subtype mandatory dropdown is displaying as expected");
				Log.info(" 'Service subtype mandatory dropdown is displaying as expected");
				
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/ServiceSubtype")));
				Thread.sleep(3000);
				Log.info("clicked on srvice type");
				ExtentTestManager.getTest().log(LogStatus.PASS, "Service subtype dropdown has been selected");
				
				if(SelectSubService.equals("LANLink International") || SelectSubService.equals("LANLink Metro") || SelectSubService.equals("LANLink National") ||
				         SelectSubService.equals("OLO - (GCR/EU)")){

				WebElement el2 = getwebelement("//div[contains(text(),'" + SelectSubService + "')]");
				el2.click();
				Log.info("=== Service sub Type selected===");
				ExtentTestManager.getTest().log(LogStatus.PASS, "Service sub type" +SelectSubService +" has been selected");
				}
				else{
					Log.info(SelectSubService+ " is not available under Service subtype dropdown when Modular msp is selected");
					
					ExtentTestManager.getTest().log(LogStatus.FAIL,SelectSubService+ " is not available when Modular msp is selected."
							+ "                      The list of sub services types under LANLINK when moduler msp is selected are:"
							+ "    1) LANLink International"
							+ "    2) LANLink Metro"
							+ "    3) LANLink National"
							+ "    4) OLO - (GCR/EU)");
//					driver.close();
				}

			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Service subtype mandatory dropdown is not displaying");
			}
			
scrolltoend();
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Next")));
			Thread.sleep(3000);

			Log.info("Page has to be selected based on service and its subtype selected");

		}

	}

	public void VerifyFieldsForServiceSubTypeSelected(String application, String serviceType, String SelectSubService,
			String Interfacespeed, String proActivemonitoring, String vpntopology, String modularmsp) throws InterruptedException, DocumentException, IOException {

		
		
	if(modularmsp.equalsIgnoreCase("no"))	{		
		if (Interfacespeed.equalsIgnoreCase("10GigE")) {

			Fieldvalidation_DirectFibre10G(application, serviceType, SelectSubService, Interfacespeed, vpntopology);

		}
		else if (Interfacespeed.equalsIgnoreCase("1GigE")) {
			Fieldvalidation_DirectFibre1G(application, serviceType, SelectSubService, Interfacespeed,proActivemonitoring, vpntopology);
		}

	}
	
	else if(modularmsp.equalsIgnoreCase("yes"))	{	
		
		ExtentTestManager.getTest().log(LogStatus.FAIL, "Direct Fiber service will not occur when 'Modular Msp'checkbox is selected is selected");
	}	
	
		
}

	
	
	

	
//Scroll to particular webelement
	public void ScrolltoElement(WebElement Element) {
		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",(Element));
		
	}

	public void Verifyfields(String application, String ServiceTypeToBeSelected, String modularMSP,
				String autoCreateService) throws InterruptedException, DocumentException {
	
	ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying list of fields under 'Create Order/ Service' Page");
	
	 
	waitforPagetobeenable();
	
	clickOnBankPage();
	
	Thread.sleep(3000);
	scrolltoend();
	
	click_commonMethod(application, "Next", "Next_Button", xml);
	Log.info("clicked on next button to verify the mandatory fields error messages");
	
	
	//Create Order/Contract Number Error message
		warningMessage_commonMethod(application, "order_contractnumberErrmsg", "Order/Contract Number", xml);
	
	//Service Type Error message
		warningMessage_commonMethod(application, "servicetypeerrmsg", "Service Type", xml);
				
		
			String[] Servicetypelists = { "BM (Broadcast Media)", "Domain Management", "DSL", "FAX to Mail", "HSS",
					"IP Access (On-net/Offnet/EoS)", "IP Access Bundle", "IP Transit", "IP VPN", "IP Web/Mail", "LANLink",
					"MDF/MVF/DI", "NGIN", "Number Hosting", "Transmission Link", "Voice Line (V)", "VOIP Access",
					"Wholesale SIP Trunking" };
	
			Log.info("order dropdown");
			
		//check whether Order dropdown is displayed	
			orderdopdown = getwebelement(xml.getlocator("//locators/" + application + "/orderdropdown")).isDisplayed();
			sa.assertTrue(orderdopdown, "Order/Contract Number dropdown is not displayed");
			Log.info("order dropdown field is verified");
			
			
		//Select value under 'Service Type' dropdown
			addDropdownValues_commonMethod(application, "Service Type", "servicetypedropdowntoclick", ServiceTypeToBeSelected, xml);
			
			
			scrolltoend();
			Thread.sleep(5000);
			
		//Click on next button to check mandatory messages
			click_commonMethod(application, "Next", "Next_Button", xml);
			Thread.sleep(3000);
			ExtentTestManager.getTest().log(LogStatus.PASS,
					"clicked on next button to verify the mandatory fields error messages");
			Log.info("clicked on next button to verify the mandatory fields error messages");
	
			
		//Interface Speed Error message	
			warningMessage_commonMethod(application, "interfaceSpeedErrmsg", "Interface Speed", xml);
			
			
		//Service Sub Type Error message
			warningMessage_commonMethod(application, "servicesubtypeErrMsg", "Service Subtype", xml);
	
			
		//Modular msp checkbox	
			modularmspCheckbox = getwebelement(xml.getlocator("//locators/" + application + "/modularmspcheckbox"))
					.isDisplayed();
			sa.assertTrue(modularmspCheckbox, "modularmsp checkbox is displayed");
	
		//AutoCreate checkbox	
			autocreateservicecheckbox = getwebelement(
					xml.getlocator("//locators/" + application + "/AutocreateServicecheckbox")).isDisplayed();
			sa.assertTrue(autocreateservicecheckbox, "Auto create check box is not displayed");
			
		
		for(int i=0; i<4; i++) {
			
			if(i==0) {
				
				ExtentTestManager.getTest().log(LogStatus.INFO, "When btoh Modular msp and Autocreate Service is not selected, list of fields should occur:"
						+ "1) Interface speed dropdown"
						+ "2) Service Subtype dropdown"
						+ "3) Available circuit dropdown");
					
					verifyinterfaceSpeeddropdown(application);
	
					verifyservicesubtypesdropdownwhenMSPandAutoCreatenotslected(application);
	
					verifyavailablecircuitdropdown(application);
	
			}
		
			
			else if(i==1) {
				
				ExtentTestManager.getTest().log(LogStatus.INFO, "When 'Modular msp' is selected but 'Autocreate Service' is not selected, list of fields should occur:"
						+ "1) Service Subtype dropdown"
						+ "2) Available circuit dropdown");
	
					click_commonMethod(application, "Modular MSP checkbox", "modularmspcheckbox", xml);
	
					verifyservicesubtypesdropdownwhenMSPaloneselected(application);
	
					verifyavailablecircuitdropdown(application);
				
			}
	
	
			else if(i==2) {
				
				ExtentTestManager.getTest().log(LogStatus.INFO, "When 'Modular msp' is not selected but 'Autocreate Service' is selected, list of fields should occur: "
						+ "1) A_End technology dropdown"
						+ "2) B_End technology dropdown"
						+ "3) Interface speeed dropdown"
						+ "4) Service Subtype dropdown"
						+ "5) Available circuit dropdown");
					
					click_commonMethod(application, "Modular MSP checkbox", "modularmspcheckbox", xml);
					Thread.sleep(2000);
					
					click_commonMethod(application, "AutoCreate Service", "AutocreateServicecheckbox", xml);
					Thread.sleep(1000);
	
					verifyA_Endtechnologydropdown(application);
	
					verifyB_Endtechnologydropdowb(application);
	
					verifyinterfaceSpeeddropdown(application);
	
					verifyservicesubtypesdropdownwgenAutoCreatealoneselected(application);
	
					verifyavailablecircuitdropdown(application);
	
			}
			else if(i==3) {
				
				ExtentTestManager.getTest().log(LogStatus.INFO, "When both 'Modular msp' and 'Autocreate Service' is selected, list of fields should occur:"
						+ "1) A_End technology"
						+ "2) B_End technology "
						+ "1) Service Subtype dropdown"
						+ "2) Available circuit dropdown");
				
					click_commonMethod(application, "Modular MSP checkbox", "modularmspcheckbox", xml);
					Thread.sleep(1000);
					
					verifyA_Endtechnologydropdown(application);
	
					verifyB_Endtechnologydropdowb(application);
	
					verifyservicesubtypesdropdownwhenMSPandAutoCreateselected(application);
	
					verifyavailablecircuitdropdown(application);
			}
		}
		
			
		}

	public void verifyinterfaceSpeeddropdown(String application) throws InterruptedException, DocumentException {
		// verify the list of interfaceSpeed
		try {

		String[] interfacelist = { "1GigE", "10GigE" };

		interfacespeeddropdown = getwebelement(xml.getlocator("//locators/" + application + "/InterfaceSpeed"))
				.isDisplayed();
		sa.assertTrue(interfacespeeddropdown, "Interface speed dropdown is not displayed");

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/InterfaceSpeed")));

		List<WebElement> listofinterfacespeed = driver
				.findElements(By.xpath("//div[@class='sc-ifAKCX oLlzc']"));
		for (WebElement interfacespeed : listofinterfacespeed) {

			boolean match = false;
			for (int i = 0; i < interfacelist.length; i++) {
				if (interfacespeed.getText().equals(interfacelist[i])) {
					match = true;
					Log.info("interface speeds : " + interfacespeed.getText());
					ExtentTestManager.getTest().log(LogStatus.PASS,"interface speeds : " + interfacespeed.getText());
					sa.assertTrue(match);
				}
			}
			
		}
		
		} catch (AssertionError error) {
           
			error.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Interface speed' dropdown under Create order detail page is not available");
			
        }catch(Exception e) {
			Log.info("dropdowns values in Interface speed are mismiatching under service type");
			Log.info("dropdowns values in Interface speed are mismiatching under service type");
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Interface speed dropdown values are not displaying as expected ");
		}

	}

	public void verifyservicesubtypesdropdownwhenMSPandAutoCreatenotslected(String application) throws InterruptedException, DocumentException {

		String[] servicesubtypelist = { "Direct Fiber", "LANLink International", "LANLink Metro", "LANLink National","LANLink Outband Management", "OLO - (GCR/EU)" };

		try {
		servicesubtypesdropdown = getwebelement(xml.getlocator("//locators/" + application + "/ServiceSubtype"))
				.isDisplayed();
		sa.assertTrue(servicesubtypesdropdown, "Service sub type dropdown is not displayed");
		
		// verify the list of service sub types
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/ServiceSubtype")));
		
		List<WebElement> listofServicesubtypes = driver
				.findElements(By.xpath("//div[@class='sc-ifAKCX oLlzc']"));
		for (WebElement servicesubtypes : listofServicesubtypes) {

			boolean match = false;
			for (int i = 0; i < servicesubtypelist.length; i++) {
				if (servicesubtypes.getText().equals(servicesubtypelist[i])) {
					match = true;
					Log.info("service sub types : " + servicesubtypes.getText());
					ExtentTestManager.getTest().log(LogStatus.PASS,"service sub types : " + servicesubtypes.getText());
					sa.assertTrue(match);
				}
			}
			

		}
		
		} catch (AssertionError error) {

			error.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL," 'Service subtypes' dropdown is not available");
			
        }catch(Exception e) {
			Log.info("Dropdown values in Service subtypes are mismatching");
			Log.info("Dropdown values in Service subtypes are mismatching");
			ExtentTestManager.getTest().log(LogStatus.FAIL,"Dropdown values in Service subtypes are mismatching");
		}

	}

	

	public void verifyservicesubtypesdropdownwhenMSPaloneselected(String application) throws InterruptedException, DocumentException {

		try {
		String[] servicesubtypelist = { "LANLink International", "LANLink Metro", "LANLink National", "OLO - (GCR/EU)" };

		servicesubtypesdropdown = getwebelement(xml.getlocator("//locators/" + application + "/ServiceSubtype"))
				.isDisplayed();
		sa.assertTrue(servicesubtypesdropdown, "Service sub type dropdown is not displayed");
		
		// verify the list of service sub types
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/ServiceSubtype")));
		
		List<WebElement> listofServicesubtypes = driver
				.findElements(By.xpath("//div[@class='sc-ifAKCX oLlzc']"));
		for (WebElement servicesubtypes : listofServicesubtypes) {

			boolean match = false;
			for (int i = 0; i < servicesubtypelist.length; i++) {
				if (servicesubtypes.getText().equals(servicesubtypelist[i])) {
					match = true;
					Log.info("service sub types : " + servicesubtypes.getText());
					ExtentTestManager.getTest().log(LogStatus.PASS,"service sub types : " + servicesubtypes.getText());
					sa.assertTrue(match);
				}
			}
			

		}
		} catch (AssertionError error) {
           
			error.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL," 'Service subtype' dropdown under create order detail page is not available");
			
		}catch (Exception e) {

		Log.info("Dropdown values inside service subtypes are mismatching");
		Log.info("Dropdown values inside service subtypes are mismatching");
		ExtentTestManager.getTest().log(LogStatus.FAIL,"Dropdown values inside service subtypes are mismatching");
		}

	}
	
	
	public void verifyservicesubtypesdropdownwgenAutoCreatealoneselected(String application) throws InterruptedException, DocumentException {

		try {
		String[] servicesubtypelist = { "Direct Fiber", "LANLink International", "LANLink Metro", "LANLink National","LANLink Outband Management", "OLO - (GCR/EU)" };

		servicesubtypesdropdown = getwebelement(xml.getlocator("//locators/" + application + "/ServiceSubtype"))
				.isDisplayed();
		sa.assertTrue(servicesubtypesdropdown, "Service sub type dropdown is not displayed");

		// verify the list of service sub types
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/ServiceSubtype")));

		List<WebElement> listofServicesubtypes = driver
				.findElements(By.xpath("//div[@class='sc-ifAKCX oLlzc']"));
		for (WebElement servicesubtypes : listofServicesubtypes) {

			boolean match = false;
			for (int i = 0; i < servicesubtypelist.length; i++) {
				if (servicesubtypes.getText().equals(servicesubtypelist[i])) {
					match = true;
					Log.info("service sub types : " + servicesubtypes.getText());
					ExtentTestManager.getTest().log(LogStatus.PASS,"service sub types : " + servicesubtypes.getText());
					sa.assertTrue(match);
				}
			}
			

		}
		} catch (AssertionError error) {

			  error.printStackTrace();
			  ExtentTestManager.getTest().log(LogStatus.FAIL,"'Service subtype' dropdown under create order detail page is not available");
			
		}catch(Exception e) {
			Log.info("Dropdown values inside service subtypes are mismatching");
			Log.info("Dropdwon values inside service subtypes are mismatching");
			ExtentTestManager.getTest().log(LogStatus.FAIL,"Dropdwon values inside service subtypes are mismatching");
		}

	}
	
	
	
	public void verifyservicesubtypesdropdownwhenMSPandAutoCreateselected(String application) throws InterruptedException, DocumentException {

		try {
		String[] servicesubtypelist = { "LANLink International", "LANLink Metro", "LANLink National", "OLO - (GCR/EU)" };

		servicesubtypesdropdown = getwebelement(xml.getlocator("//locators/" + application + "/ServiceSubtype"))
				.isDisplayed();
		sa.assertTrue(servicesubtypesdropdown, "Service sub type dropdown is not displayed");

		
		// verify the list of service sub types
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/ServiceSubtype")));
		

		List<WebElement> listofServicesubtypes = driver
				.findElements(By.xpath("//div[@class='sc-ifAKCX oLlzc']"));
		for (WebElement servicesubtypes : listofServicesubtypes) {

			boolean match = false;
			for (int i = 0; i < servicesubtypelist.length; i++) {
				if (servicesubtypes.getText().equals(servicesubtypelist[i])) {
					match = true;
					Log.info("service sub types : " + servicesubtypes.getText());
					ExtentTestManager.getTest().log(LogStatus.PASS,"service sub types are : " + servicesubtypes.getText());
					sa.assertTrue(match);
				}
			}
			

		}
		
	} catch (AssertionError error) {

		  error.printStackTrace();
		  ExtentTestManager.getTest().log(LogStatus.FAIL,"'Service subtype' dropdown under create order detail page is not available");
		  
	}catch(Exception e){
    	 Log.info("Dropdwon values inside Service subtypes are mismatching");
    	 Log.info("Dropdwon values inside Service subtypes are mismatching");
    	 ExtentTestManager.getTest().log(LogStatus.FAIL,"Dropdwon values inside Service subtypes are mismatching");
     }

	}



	
	
	public void verifyavailablecircuitdropdown(String application) throws InterruptedException, DocumentException {
try {
		availablecircuitsdropdown = getwebelement(xml.getlocator("//locators/" + application + "/AvailableCircuits"))
				.isDisplayed();
		
		sa.assertTrue(availablecircuitsdropdown, "available circuit dropdown is not displayed");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Available circuit dropdown is displayed");
		
}catch(AssertionError e) {
	Log.info("Available circuit dropdown under servicetype got failed");
	Log.info("Available circuit dropdown under servicetype got failed");
	ExtentTestManager.getTest().log(LogStatus.FAIL, "Available circuit dropdown is not available under create order detail page");
}
}

	public void verifyA_Endtechnologydropdown(String application) throws InterruptedException, DocumentException {

		try {
		String[] A_endTechnolnogylist = { "Atrica", "MMSP", "Ethernet over Fibre" };

		A_EndTechnolnogy = getwebelement(xml.getlocator("//locators/" + application + "/A_Endtechnology"))
				.isDisplayed();
		sa.assertTrue(A_EndTechnolnogy, "A-End technolnogy dropdown is not displayed");

		// verify the list of A-End technolnogies
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/A_Endtechnology")));

		List<WebElement> listofA_endTechnologies = driver
				.findElements(By.xpath("//div[@class='sc-ifAKCX oLlzc']"));
		for (WebElement A_endTechnolnogies : listofA_endTechnologies) {

			boolean match = false;
			for (int i = 0; i < A_endTechnolnogylist.length; i++) {
				if (A_endTechnolnogies.getText().equals(A_endTechnolnogylist[i])) {
					match = true;
					Log.info("A end technology values : " + A_endTechnolnogies.getText());
					ExtentTestManager.getTest().log(LogStatus.PASS,"A end technology are : " + A_endTechnolnogies.getText());
					sa.assertTrue(match);
				}
			}
			

		}
		} catch (AssertionError error) {

		  error.printStackTrace();
		  ExtentTestManager.getTest().log(LogStatus.FAIL," A-end technology  dropdown under create order detail page is not available");
		
		}catch(Exception e) {
			Log.info("Dropdwon values inside A-end technology are mismatching");
			Log.info("Dropdwon values inside A-end technology are mismatching");
			ExtentTestManager.getTest().log(LogStatus.FAIL,"Dropdwon values inside A-end technology are mismatching");
		}

	}

	public void verifyB_Endtechnologydropdowb(String application) throws InterruptedException, DocumentException {

		try {
		String[] B_endTechnolnogylist = { "Atrica", "MMSP", "Ethernet over Fibre" };

		B_Endtechnolnogy = getwebelement(xml.getlocator("//locators/" + application + "/B_Endtechnology"))
				.isDisplayed();
		sa.assertTrue(B_Endtechnolnogy, "B-End technolnogy dropdown is not displayed");

		// verify the list of A-End technolnogies
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/B_Endtechnology")));

		List<WebElement> listofB_endTechnologies = driver
				.findElements(By.xpath("//div[@class='sc-ifAKCX oLlzc']"));
		for (WebElement B_endTechnolnogies : listofB_endTechnologies) {

			boolean match = false;
			for (int i = 0; i < B_endTechnolnogylist.length; i++) {
				if (B_endTechnolnogies.getText().equals(B_endTechnolnogylist[i])) {
					match = true;
					Log.info("B end technology values : " + B_endTechnolnogies.getText());
					ExtentTestManager.getTest().log(LogStatus.PASS, "B end technologies are : " + B_endTechnolnogies.getText());
					sa.assertTrue(match);
				}
			}
			

		}
		} catch (AssertionError error) {

			  error.printStackTrace();
			  ExtentTestManager.getTest().log(LogStatus.FAIL," 'B-end technology' dropdown under create order detail page is not available");
			
		}catch(Exception e) {
			Log.info("Dropdwon values inside B-end technology are mismatching");
			Log.info("Dropdwon values inside B-end technology are mismatching");
			ExtentTestManager.getTest().log(LogStatus.FAIL,"Dropdwon values inside B-end technology are mismatching");
		}
	}

	public void DirectFibre_10G(String application, String ServiceIdentificationNumber, String SelectSubService,
			String Interfacespeed, String EndpointCPE, String Email, String PhoneContact, String remark,
			String PerformanceReporting, String ProactiveMonitoring, String deliveryChannel, String ManagementOrder,
			String vpnTopology, String intermediateTechnology, String CircuitReference, String CircuitType, String notificationManagement)
			throws InterruptedException, IOException, DocumentException {
		


		scrollToTop();
		Thread.sleep(3000);
		
			//Service Identification Number	
			createService_ServiceIdentification(application, ServiceIdentificationNumber);

				
			//End point CPE	
			createService_singleEndPointCPE(application, EndpointCPE);
				
			
			//Email	
			createSerivce_email(application, Email);

		
			//Phone Contact	
			createService_phone(application, PhoneContact);

				
			//Remark	
			createService_remark(application, remark);	
	
				WebElement configurationOptionPanel= getwebelement(xml.getlocator("//locators/" + application + "/configurtoinptionpanel-webelementToScroll"));
				ScrolltoElement(configurationOptionPanel);
		
	//Performance Reporting
				createService_performancereporting_10G(application, PerformanceReporting);


		//Pro active Monitoring	
				createService_proactivemonitoring(application, ProactiveMonitoring, notificationManagement);
			
			//Delivery Channel	
				createService_deliveryChannel(application, deliveryChannel);

				
			//Management Order	
				createService_managementOptions(application, ManagementOrder);

				
		scrolltoend();
		Thread.sleep(3000);
		
			//VPN topology	
				boolean vpNTopology = getwebelement(xml.getlocator("//locators/" + application + "/VPNtopology")).isDisplayed();
				if(vpNTopology) {
					ExtentTestManager.getTest().log(LogStatus.PASS, " 'VPN Topology' dropdown is displaying as expected");
				if (!vpnTopology.equalsIgnoreCase("null")) {

					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/VPNtopology_xbutton")));
					Thread.sleep(3000);
					
					
					if(vpnTopology.equals("Point-to-Point")) {
						
						Clickon(getwebelement("//div[text()='"+ vpnTopology + "']"));
						Thread.sleep(3000);
						ExtentTestManager.getTest().log(LogStatus.PASS, vpnTopology + " is selected under 'VPN topology' dropdown");
						
						
					//Intermediate technology	
						createService_intermediateTechnology(application, intermediateTechnology);
						
					//Circuit Reference	
						createService_circuitreference(application, CircuitReference);

					//Circuit Type	
						createSerivce_circuitType(application, CircuitType);
					}
					
					else if(vpnTopology.equals("Hub&Spoke") || vpnTopology.equals("E-PN (Any-to-Any)")) {
						
						Clickon(getwebelement("//div[text()='"+ vpnTopology + "']"));
						Thread.sleep(3000);
						ExtentTestManager.getTest().log(LogStatus.PASS, vpnTopology + " is selected under 'VPN topology' dropdown");

					//Circuit Reference	
						createService_circuitreference(application, CircuitReference);
					}
				else {
						
						Log.info(vpnTopology+ " is not available under VPN topoloy dropdown");
						Log.info(vpnTopology+ " is not available inside the VPN topoloy dropdown");
						ExtentTestManager.getTest().log(LogStatus.FAIL,vpnTopology+ " is not available under VPN topoloy dropdown");

				}
				}else {
					ExtentTestManager.getTest().log(LogStatus.PASS, " No value provided for 'VPN topology' dropdown. 'point -to -point' is selected by default");
					
					//Intermediate technology	
						createService_intermediateTechnology(application, intermediateTechnology);
						
					//Circuit Reference	
						createService_circuitreference(application, CircuitReference);

					//Circuit Type	
						createSerivce_circuitType(application, CircuitType);
				}
				}else {
					 ExtentTestManager.getTest().log(LogStatus.FAIL, " 'VPN topology' dropdown is not available under 'Configuration Options' panel in 'Create Service' page");
				 }
	

	
	click_commonMethod(application, "OK", "okbutton", xml);

}
	
	
	
	public void DirectFibre_1G(String application, String ServiceIdentificationNumber, String SelectSubService,
			String Interfacespeed, String EndpointCPE, String Email, String PhoneContact, String remark,
			String PerformanceReporting, String ProactiveMonitoring, String deliveryChannel, String ManagementOrder,
			String vpnTopology, String intermediateTechnology, String CircuitReference, String CircuitType, String notificationManagement,
			String perCocPerfrmReprt, String actelsBased, String standrdCir, String standrdEir, String prmiumCir, String prmiumEir)
			throws InterruptedException, IOException, DocumentException {
		

scrollToTop();
Thread.sleep(3000);
		
	//Service Identification
		createService_ServiceIdentification(application, ServiceIdentificationNumber);
		
		
	//End point CPE	
		createService_singleEndPointCPE(application, EndpointCPE);

		
	//Email	
		createSerivce_email(application, Email);

		
	//Phone Contact	
		createService_phone(application, PhoneContact);

		
	//Remark	
		createService_remark(application, remark);
		
		WebElement configurationOptionPanel= getwebelement(xml.getlocator("//locators/" + application + "/configurtoinptionpanel-webelementToScroll"));
		ScrolltoElement(configurationOptionPanel);
		
	//Performance Reporting	
		if(!PerformanceReporting.equalsIgnoreCase("null")) {
			
			if (PerformanceReporting.equalsIgnoreCase("yes")) {
				
				boolean perfrmReprtFieldcheck=getwebelement(xml.getlocator("//locators/" + application + "/performanceReportingcheckbox")).isDisplayed();
				if(perfrmReprtFieldcheck) {
					ExtentTestManager.getTest().log(LogStatus.PASS, " 'Performance reporting' checkbox is displaying under 'Manage ment options' panel in 'Create Service' page as exepcted");
					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/performanceReportingcheckbox")));
					Thread.sleep(5000);
					
					boolean prfrmReportselection=getwebelement(xml.getlocator("//locators/" + application + "/performanceReportingcheckbox")).isSelected();
					if(prfrmReportselection) {
						Log.info("performance monitoring check box is selected");
						ExtentTestManager.getTest().log(LogStatus.PASS, "Performance Reporting checkbox is selected as expected");
					}else {
						ExtentTestManager.getTest().log(LogStatus.FAIL, "Performance Reporting checkbox is not selected");
					}
					
					
				//Per CoS Performance Reporting chekcbox	
					boolean perCoSPrfrmReprtFieldcheck=getwebelement(xml.getlocator("//locators/" + application + "/perCoSperformncereport")).isDisplayed();
					if(perCoSPrfrmReprtFieldcheck) {
						ExtentTestManager.getTest().log(LogStatus.PASS, " 'Per CoS Performance Reporting' checkbox is displaying, when 'Performance reporting' checkbox is selected");
						if(perCocPerfrmReprt.equalsIgnoreCase("Yes")){
							Clickon(getwebelement(xml.getlocator("//locators/" + application + "/perCoSperformncereport")));
							Thread.sleep(3000);
							
							boolean perCoSSelection=getwebelement(xml.getlocator("//locators/" + application + "/perCoSperformncereport")).isSelected();
							if(perCoSSelection) {
								ExtentTestManager.getTest().log(LogStatus.PASS, "Per CoS Performance Reporting checkbox is selected as expected");
							}else {
								ExtentTestManager.getTest().log(LogStatus.FAIL, "Per CoS Performance Reporting checkbox is not selected");
							}
							
							
						//Actelis Based	
							boolean actelisbasedFieldcheck=getwebelement(xml.getlocator("//locators/" + application + "/ActelisBasedcheckbox")).isDisplayed();
							if(actelisbasedFieldcheck) {
								ExtentTestManager.getTest().log(LogStatus.PASS, " 'Actelis Based' checkbox is displaying, when 'Per CoS Perfoemance Reporting' checkbox is selected");
								if(actelsBased.equalsIgnoreCase("Yes")) {
									Clickon(getwebelement(xml.getlocator("//locators/" + application + "/ActelisBasedcheckbox")));
									Thread.sleep(3000);
									
									boolean actelisSelection= getwebelement(xml.getlocator("//locators/" + application + "/ActelisBasedcheckbox")).isSelected();
									if(actelisSelection) {
										ExtentTestManager.getTest().log(LogStatus.PASS, " 'Actelis Based' checkbox is selected as expected");
									}else {
										ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Actelis Based' checkbox is not selected" );
									}
									
								//Standard CIR Text Field
									createService_standardCIR(application, standrdCir);
									
								//Standard EIR Text Field
									createService_standardEIR(application, standrdEir);
									
									
								//Premium CIR Text Field
									createService_premiumCIR(application, prmiumCir);
									
								//Premium EIR Text Field
									createService_premiumEIR(application, prmiumEir);		
									
									
								}else {
									ExtentTestManager.getTest().log(LogStatus.PASS, " 'Actelis Based' checkbox is not selected as expected");
								}
								
								
							}else {
								ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Actelis Based' checkbox is not displaying, when 'Per CoS Perfoemance Reporting' checkbox is selected");
							}
							
							
						}else {
							ExtentTestManager.getTest().log(LogStatus.PASS, " 'Per CoS Performance Reporting' checkbox is not selected as exepected");
						}
						
					
					}else {
						ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Per CoS Performance Rpeorting' checkbox is not displaying, when 'Performance reporting' checkbox is selected");
					}
					
				}else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Performance Reporting' checkbox is not available");
				}
				
				
				
			}
			else {

				Log.info("Performance Repoting is not selected");
				ExtentTestManager.getTest().log(LogStatus.PASS,"performance Reporting checkbox is not selected as expected");
				
			}
		}


	//Pro active Monitoring	
		createService_proactivemonitoring(application, ProactiveMonitoring, notificationManagement);
	
	//Delivery Channel	
		createService_deliveryChannel(application, deliveryChannel);

	//management order	
		createService_managementOptions(application, ManagementOrder);

		
		scrolltoend();
		Thread.sleep(3000);
		
	//VPN topology	
		boolean vpNTopology = getwebelement(xml.getlocator("//locators/" + application + "/VPNtopology")).isDisplayed();
		if(vpNTopology) {
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'VPN Topology' dropdown is dsiplaying as expected");
		if (!vpnTopology.equalsIgnoreCase("null")) {

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/VPNtopology_xbutton")));
			Thread.sleep(3000);
			
			
			if(vpnTopology.equals("Point-to-Point")) {
				
				Clickon(getwebelement("//div[text()='"+ vpnTopology +"']"));
				Thread.sleep(3000);
				
				ExtentTestManager.getTest().log(LogStatus.PASS, vpnTopology + " is selected under 'VPN topology' dropdown");
				
			//Intermediate technology	
				createService_intermediateTechnology(application, intermediateTechnology);
				
			//Circuit Reference	
				createService_circuitreference(application, CircuitReference);

			//Circuit Type	
				createSerivce_circuitType(application, CircuitType);
			}
			
			else if(vpnTopology.equals("Hub&Spoke") || vpnTopology.equals("E-PN (Any-to-Any)")) {
				
				Clickon(getwebelement("(//div[text()='"+ vpnTopology + "'])"));
				Thread.sleep(3000);
				
				ExtentTestManager.getTest().log(LogStatus.PASS, vpnTopology + " is selected under 'VPN topology' dropdown");

			//Circuit Reference	
				createService_circuitreference(application, CircuitReference);
				
			}
			
			else {
				
				Log.info(vpnTopology+ " is not available under VPN topoloy dropdown");
				Log.info(vpnTopology+ " is not available inside the VPN topoloy dropdown");
				ExtentTestManager.getTest().log(LogStatus.FAIL,vpnTopology+ " is not available under VPN topoloy dropdown");
			}

		}else {
			ExtentTestManager.getTest().log(LogStatus.PASS, " No value provided for 'VPN topology' dropdown. 'point -to -point' is selected by default");
			
			//Intermediate technology	
				createService_intermediateTechnology(application, intermediateTechnology);
				
			//Circuit Reference	
				createService_circuitreference(application, CircuitReference);

			//Circuit Type	
				createSerivce_circuitType(application, CircuitType);
		}
		}else {
			 ExtentTestManager.getTest().log(LogStatus.FAIL, " 'VPN topology' dropdown is not available under 'Configuration Options' panel in 'Create Service' page");
			 Log.info("'VPN topology' dropdown is not available under 'Configuration Options' panel in 'Create Service' page");
		}
	

	
	click_commonMethod(application, "OK", "okbutton", xml);

}

	public void verifydataEntered_DirectFibre10G(String application, String serviceype,
			String ServiceIdentificationNumber, String SelectSubService, String Interfacespeed, String EndpointCPE,
			String email, String PhoneContact, String remark, String PerformanceMonitoring, String ProactiveMonitoring,
			String deliveryChannel, String ManagementOrder, String vpnTopology, String intermediateTechnology,
			String CircuitReference, String CircuitType, String modularMSP, String notificationManagement) throws InterruptedException, IOException, DocumentException { 

		
		
		WebElement orderPanel= getwebelement(xml.getlocator("//locators/" + application + "/viewServicepage_OrderPanel"));
		ScrolltoElement(orderPanel);
		Thread.sleep(3000);
		try {
		
	/**
	 * Service Panel	
	 */
		//Service Identification
		verifyEnteredvalues("Service Identification", ServiceIdentificationNumber);
		
		//Service type
		verifyEnteredvalues("Service Type", serviceype);
		
		//Service Subtype
		verifyEnteredvalues("Service Subtype", SelectSubService);
		
		//Interface Speed
		verifyEnteredvalues("Interface Speed", Interfacespeed);
		
		//Single Endpoint CPE
		if(vpnTopology.equals("Point-to-Point")){
			verifyEnteredvalues("Single Endpoint CPE", EndpointCPE);
		}else {
			verifyEnteredvalues("Single Endpoint CPE", "No");
		}
		
		//Email
		verifyEnteredvalueForEmail_serviceCreationpage("Email", email);
		
		//Phone Contact
		verifyEnteredvalues("Phone Contact", PhoneContact);
		
		//Modular MSP
		verifyEnteredvalues("Modular MSP", modularMSP);
		
		//Remark
		compareText(application, "Remark", "remark_viewPage", remark, xml);
	
		sa.assertAll();
		}catch(AssertionError err) {
			err.printStackTrace();
//			ExtentTestManager.getTest().log(LogStatus.FAIL, label + " value is not displaying as expected ");
		}
		
		
		WebElement servicePanel= getwebelement(xml.getlocator("//locators/" + application + "/viewServicepage_Servicepanel"));
		ScrolltoElement(servicePanel);
		Thread.sleep(3000);
		
	/**
	 * Management Options panel	
	 */
		
		//Delivery Channel
		verifyEnteredvalues("Delivery Channel", deliveryChannel);
		
		//Management Order
		verifyEnteredvalues("Management Order", ManagementOrder);
		
		//Proactive Monitoring
		verifyEnteredvalues("Proactive Monitoring", ProactiveMonitoring);
		
		if(ProactiveMonitoring.equalsIgnoreCase("yes")) {
			verifyEnteredvalues("Notification Management Team", notificationManagement);
		}
		
		//Performance Reporting
		verifyEnteredvalues("Performance Reporting", PerformanceMonitoring);
		
		

//		WebElement managementOptionsPanel= getwebelement(xml.getlocator("//locators/" + application + "/viewServicepage_ManagementOptionsPanel"));
//		ScrolltoElement(managementOptionsPanel);
//		Thread.sleep(3000);
		
		scrolltoend();
		Thread.sleep(2000);
		
		
	/**
	 * Configuration options panel	
	 */
		
		//VPN Topology
		verifyEnteredvalues("VPN Topology", vpnTopology);
		
		if(vpnTopology.equals("Point-to-Point")) {
			
			//Circuit Reference
			verifyEnteredvalues("Circuit Reference", CircuitReference);
			
			//Circuit Type
			verifyEnteredvalues("Circuit Type", CircuitType);
		}
		
		
		if(vpnTopology.equals("Hub&Spoke")) {
			
			Log.info("Only vpn topology vaue displays under 'hub&Spoke'");
			ExtentTestManager.getTest().log(LogStatus.PASS, " Only VPN topology field displays under 'Configuration' panel, when 'Hub&Spoke' is selected");
		}
		
		if(vpnTopology.equals("E-PN (Any-to-Any)")) {
			
			//Circuit Reference
			verifyEnteredvalues("Circuit Reference", CircuitReference);
		}
	
	}

	
	public void verifydataEntered_DirectFibre1G(String application, String serviceype,
			String ServiceIdentificationNumber, String SelectSubService, String Interfacespeed, String EndpointCPE,
			String Email, String PhoneContact, String remark, String PerformanceMonitoring, String ProactiveMonitoring,
			String deliveryChannel, String ManagementOrder, String vpnTopology, String intermediateTechnology,
			String CircuitReference, String CircuitType, String modularMSP,String perCocPerfrmReprt, String actelsBased, String standrdCir,
			String standrdEir, String prmiumCir, String prmiumEir, String notificationManagement) throws InterruptedException, IOException, DocumentException {

		WebElement orderPanel= getwebelement(xml.getlocator("//locators/" + application + "/viewServicepage_OrderPanel"));
		ScrolltoElement(orderPanel);
		Thread.sleep(3000);
		
		
		/**
		 * Service Panel	
		 */
			//Service Identification
			verifyEnteredvalues("Service Identification", ServiceIdentificationNumber);
			
			//Service type
			verifyEnteredvalues("Service Type", serviceype);
			
			//Service Subtype
			verifyEnteredvalues("Service Subtype", SelectSubService);
			
			//Interface Speed
			verifyEnteredvalues("Interface Speed", Interfacespeed);
			
			//Single Endpoint CPE
			if(vpnTopology.equals("Point-to-Point")){
				verifyEnteredvalues("Single Endpoint CPE", EndpointCPE);
			}else {
				verifyEnteredvalues("Single Endpoint CPE", "No");
			}
			
			//Email
			verifyEnteredvalueForEmail_serviceCreationpage("Email", Email);
			
			//Phone Contact
			verifyEnteredvalues("Phone Contact", PhoneContact);
			
			//Modular MSP
			verifyEnteredvalues("Modular MSP", modularMSP);
			
			//Remark
			compareText(application, "Remark", "remark_viewPage", remark, xml);
		
			WebElement servicePanel= getwebelement(xml.getlocator("//locators/" + application + "/viewServicepage_Servicepanel"));
			ScrolltoElement(servicePanel);
			Thread.sleep(3000);
			
			
		/**
		 * Management Options panel	
		 */
			
			//Delivery Channel
			verifyEnteredvalues("Delivery Channel", deliveryChannel);
			
			//Management Order
			verifyEnteredvalues("Management Order", ManagementOrder);
			
			//Proactive Monitoring
			verifyEnteredvalues("Proactive Monitoring", ProactiveMonitoring);
			
			if(ProactiveMonitoring.equalsIgnoreCase("yes")) {
				verifyEnteredvalues("Notification Management Team", notificationManagement);
			}
			
			WebElement managementOptionsPanel= getwebelement(xml.getlocator("//locators/" + application + "/viewServicepage_ManagementOptionsPanel"));
			ScrolltoElement(managementOptionsPanel);
			Thread.sleep(3000);
			
			//Performance Reporting
			if(PerformanceMonitoring.equalsIgnoreCase("no")){
			
				verifyEnteredvalues("Performance Reporting", PerformanceMonitoring);
			}
			
			if(vpnTopology.equals("Point-to-Point")) {
				
				if((PerformanceMonitoring.equalsIgnoreCase("yes")) & (perCocPerfrmReprt.equalsIgnoreCase("no")) & (actelsBased.equalsIgnoreCase("no"))) {
					
					verifyEnteredvalues("Performance Reporting", PerformanceMonitoring);
					
					verifyEnteredvalues("Per CoS Performance Reporting", perCocPerfrmReprt);
					
				}
				
				
				else if((PerformanceMonitoring.equalsIgnoreCase("yes")) & (perCocPerfrmReprt.equalsIgnoreCase("yes")) & (actelsBased.equalsIgnoreCase("no"))) {
					
					verifyEnteredvalues("Performance Reporting", PerformanceMonitoring);
					
					verifyEnteredvalues("Per CoS Performance Reporting", perCocPerfrmReprt);
					
					verifyEnteredvalues("Actelis Based", actelsBased);
				}
				
				
				else if((PerformanceMonitoring.equalsIgnoreCase("yes")) & (perCocPerfrmReprt.equalsIgnoreCase("yes")) & (actelsBased.equalsIgnoreCase("yes"))) {
					
					verifyEnteredvalues("Performance Reporting", PerformanceMonitoring);
					
					verifyEnteredvalues("Per CoS Performance Reporting", perCocPerfrmReprt);
					
					verifyEnteredvalues("Actelis Based", actelsBased);
					
				//Standard 	
					compareText(application, "Header Name for 1st row", "standardHeaderName_viewPage", "Standard", xml);
					
					compareText(application, "Standard CIR", "standardCIRvalue_viewPage", standrdCir, xml);
					
					compareText(application, "Standard EIR", "standardEIRvalue_viewPage", standrdEir, xml);
					
				//Premium
					compareText(application, "Header Name for 2nd row", "PremisumHeaderName_viewPage", "Premium", xml);
					
					compareText(application, "Premium CIR", "premiumCIRvalue_viewPage", prmiumCir, xml);
					
					compareText(application, "Premium EIR", "premiumEIRvalue_viewPage", prmiumEir, xml);
					
					
				}
			}
			else {
				
				if((PerformanceMonitoring.equalsIgnoreCase("yes")) & (perCocPerfrmReprt.equalsIgnoreCase("no")) & (actelsBased.equalsIgnoreCase("no"))) {
					
					verifyEnteredvalues("Performance Reporting", PerformanceMonitoring);
					
					verifyEnteredvalues("Per CoS Performance Reporting", perCocPerfrmReprt);
					
				}
				
				
				else if((PerformanceMonitoring.equalsIgnoreCase("yes")) & (perCocPerfrmReprt.equalsIgnoreCase("yes")) & (actelsBased.equalsIgnoreCase("no"))) {
					
					verifyEnteredvalues("Performance Reporting", PerformanceMonitoring);
					
					verifyEnteredvalues("Per CoS Performance Reporting", perCocPerfrmReprt);
					
					verifyEnteredvalues("Actelis Based", actelsBased);
				}
				
				
				else if((PerformanceMonitoring.equalsIgnoreCase("yes")) & (perCocPerfrmReprt.equalsIgnoreCase("yes")) & (actelsBased.equalsIgnoreCase("yes"))) {
					
					actelsBased="No";
					
					verifyEnteredvalues("Performance Reporting", PerformanceMonitoring);
					
					verifyEnteredvalues("Per CoS Performance Reporting", perCocPerfrmReprt);
					
					verifyEnteredvalues("Actelis Based", actelsBased);
				}
				
			}
				
			
//			WebElement managementOptionsPanel= getwebelement(xml.getlocator("//locators/" + application + "/viewServicepage_ManagementOptionsPanel"));
//			ScrolltoElement(managementOptionsPanel);
//			Thread.sleep(3000);
			
			scrolltoend();
			Thread.sleep(2000);

			
		/**
		 * Configuration options panel	
		 */
			
			//VPN Topology
			verifyEnteredvalues("VPN Topology", vpnTopology);
			
			if(vpnTopology.equals("Point-to-Point")) {
				
				//Circuit Reference
				verifyEnteredvalues("Circuit Reference", CircuitReference);
				
				//Circuit Type
				verifyEnteredvalues("Circuit Type", CircuitType);
			}
			
			
			if(vpnTopology.equals("Hub&Spoke")) {
				
			Log.info("only vpn topology displays under view Service page");
			ExtentTestManager.getTest().log(LogStatus.PASS, " Only VPN topology field displays under 'Configuration' panel, when 'Hub&Spoke' is selected");
				
			}
			
			if(vpnTopology.equals("E-PN (Any-to-Any)")) {
				
				//Circuit Reference
				verifyEnteredvalues("Circuit Reference", CircuitReference);
				
			}
		}
	
	
	public void Fieldvalidation_DirectFibre10G(String application, String serviceType, String SelectSubService,
			String Interfacespeed,String vpntopology) throws InterruptedException, DocumentException, IOException {

		
		String[] deliverychannel = { "--", "Retail", "Reseller" , "WLC", "WLEC", "CES Solutions"};

		String[] VPNtopology = { "Point-to-Point", "Hub&Spoke", "E-PN (Any-to-Any)" };

		String[] notifyManagement= {"DNA"}; 
		 

		boolean serviceIdentificationField, ServiceType, ServiceSubtype,interfacespeedvalue, singleendpointCPE, email,
				phone, remark, performancereoprting, deliveryChanel, proactiveMonitor, Managementorder, vpnTopology,
				intermediateTechnology, circuitref, circuitType, okButton, cancelButton;
  
		try {	
		
		click_commonMethod(application, "OK", "obutton_spanTag", xml);
		Thread.sleep(2000);
			
		//Circuit Reference Error message	
			warningMessage_commonMethod(application, "circuitreferenceErrmsg", "Circuit Reference", xml);
			
			
		scrollToTop();
		Thread.sleep(3000);
		//Service Identification Error message	
			warningMessage_commonMethod(application, "serviceIdentificationerrmsg", "Service Identification", xml);
		

			
	//service Identification	
		serviceIdentificationField = getwebelement(
				xml.getlocator("//locators/" + application + "/ServiceIdentification")).isDisplayed();
		sa.assertTrue(serviceIdentificationField, "Service identification field is not displayed");
		if(serviceIdentificationField) {
			ExtentTestManager.getTest().log(LogStatus.PASS, " ' Service Identfication' mandatory field is displaynig under 'Add Service' page as expected");
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " ' Service Identfication' mandatory field is not available under 'Add Service' page");
		}
    	   

     //Service type  
		ServiceType = getwebelement(xml.getlocator("//locators/" + application + "/ServiceType")).isDisplayed();
		Thread.sleep(3000);
		sa.assertTrue(ServiceType, "Service type is not displayed");
		if(ServiceType) {
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'LANLink' is displying under 'Service type' as expected");
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'LANLink' is not displying under 'Service type'");
		}
		
	//Service subtype
		ServiceSubtype = getwebelement("//div[contains(text(),'" + SelectSubService + "')]").isDisplayed();
		sa.assertTrue(ServiceSubtype, "Service subtype is not displayed");
		if(ServiceSubtype) {
			ExtentTestManager.getTest().log(LogStatus.PASS, SelectSubService + " is displying under 'Service Sub type' as expected");
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, SelectSubService + " is not displying under 'Service Sub type'");
		}
		
	
	//Interface speed 	
	    interfacespeedvalue=getwebelement("//div[contains(text(),'" + Interfacespeed + "')]").isDisplayed();
		sa.assertTrue(interfacespeedvalue, "Interface speed dropdown is not displaying as expected");
		if(interfacespeedvalue) {
			ExtentTestManager.getTest().log(LogStatus.PASS, Interfacespeed + " is displying under 'Interface Speed' as expected");
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, Interfacespeed + " is not displying under 'Interface Speed'");
		}
	
	//Single endpoint cpe	
		try {
		singleendpointCPE = getwebelement(xml.getlocator("//locators/" + application + "/EndpointCPE")).isDisplayed();
		sa.assertTrue(singleendpointCPE, "single End point CPE checkbox is disabled by default");
		if(singleendpointCPE) {
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Single endpoint cpe' checkbox is displying under 'Create Service'page as expected");
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL,  " 'Single endpoint cpe' checkbox is not available under 'Create Service' page");
		}
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'Single endpoint cpe' checkbox is not available under 'Create Service' page");
		}
	
		
	//Email	
		try {
		email = getwebelement(xml.getlocator("//locators/" + application + "/Email")).isDisplayed();
		sa.assertTrue(email, "email field is not displayed");
		if(email) {
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Email' field is displying under 'Create Service'page as expected");
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Email' field is not available under 'Create Service' pag");
		}
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'Email' field is not available under 'Create Service' page");
		}
	
		
	//phone	
		try {
		phone = getwebelement(xml.getlocator("//locators/" + application + "/PhoneContact")).isDisplayed();
		sa.assertTrue(phone, "phone contact field is not displayed");
		if(phone) {
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'phone' field is displying under 'Create Service'page as expected");
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'phone' field is not available under 'Create Service' page");
		}
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'Phone Contact' field is not available under 'Create Service' page");
		}
		
  //remark
		try {
		remark = getwebelement(xml.getlocator("//locators/" + application + "/Remark")).isDisplayed();
		sa.assertTrue(remark, "remark field is not displayed");
		if(remark) {
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Remark' field is displying under 'Create Service'page as expected");
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Remark' field is not available under 'Create Service' page");
		}
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'Remark' field is not available under 'Create Service' page");
		}
		
		
		WebElement configurationOptionPanel= getwebelement(xml.getlocator("//locators/" + application + "/configurtoinptionpanel-webelementToScroll"));
		ScrolltoElement(configurationOptionPanel);
	//performance Reporting	
	 try {	
		performancereoprting = getwebelement(xml.getlocator("//locators/" + application + "/performanceReportingcheckbox"))
				.isDisplayed();
		sa.assertTrue(performancereoprting,
				"performance monitoring checbox is not displayed and by default not selected as expected");
		if(performancereoprting) {
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'performance Reporting' checkbox is displying under 'Create Service'page as expected");
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'performance Reporting' checkbox is not available under 'Create Service' page");
		}
		
		boolean performancereoprtingselection = getwebelement(xml.getlocator("//locators/" + application + "/performanceReportingcheckbox")).isSelected();
		if(performancereoprtingselection) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Performance Reporting' checkbox is selected under 'Management Options' panel in 'Create Service page'"); 
		}else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "performance Reporting' checkbox is not selected under 'Management Options' panel in 'Create Service page as expected");
		}
	 }catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'Performance reporting' checkbox is not available under 'Create Service' page");
		}	
		
		
	//proactive monitoring	
		proactiveMonitor = getwebelement(xml.getlocator("//locators/" + application + "/proactiveMonitoring"))
				.isDisplayed();
		sa.assertTrue(proactiveMonitor,
				"pro active monitoring checkbox is not displayed");
		if(proactiveMonitor) {
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'proactive monitoring' checkbox is displying under 'Create Service'page as expected");
		
		
		boolean proactiveMonitorselection = getwebelement(xml.getlocator("//locators/" + application + "/proactiveMonitoring")).isSelected();
		if(proactiveMonitorselection) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'proactive monitoring' checkbox is selected under 'Management Options' panel in 'Create Service page'"); 
		}else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "proactive monitoring' checkbox is not selected under 'Management Options' panel in 'Create Service page as expected");
		
	
	//Notification Management Dropdown	
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/proactiveMonitoring")));
		Log.info("Pro active monitoring check box is selected");
		Thread.sleep(3000);
	
	boolean notificationManagement=getwebelement(xml.getlocator("//locators/" + application + "/notificationmanagement"))
	.isDisplayed();
	sa.assertTrue(notificationManagement, "Notification management dropdown is not displayed when proactive monitoring is selected");
	Log.info("Notification management dropdown gets displayed when proactive monitoring is selected");
	
	if(notificationManagement) {
		ExtentTestManager.getTest().log(LogStatus.PASS, " 'Notification Management' dropdown is displaying under 'Management Options' panel when 'proactive Monitoring' checkbox is selected");
	
	Clickon(getwebelement(xml.getlocator("//locators/" + application + "/notificationmanagement")));
	try {
		List<WebElement> listofnotificationmanagement = driver
				.findElements(By.xpath("//div[@class='sc-ifAKCX oLlzc']"));
		for (WebElement notificationmanagementtypes : listofnotificationmanagement) {

			boolean match = false;
			for (int i = 0; i < notifyManagement.length; i++) {
				if (notificationmanagementtypes.getText().equals(notifyManagement[i])) {
					match = true;
					Log.info("list of notification management are : " + notificationmanagementtypes.getText());
					ExtentTestManager.getTest().log(LogStatus.PASS,"list of notification management are : " + notificationmanagementtypes.getText());
				}
			}
			sa.assertTrue(match);
			
		}
      }catch(Exception e) {
    	  Log.info("Notification Management dropdown values are mismatching");
    	  e.printStackTrace();
    	  ExtentTestManager.getTest().log(LogStatus.FAIL,"  values in Notification management dropdown under Direct Fiber service subtype is not displaying as expected");
      }
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Notification Management' dropdown is not available under 'Management Options' panel when 'proactive Monitoring' checkbox is selected");
		}
		}
	}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'proactive monitoring' checkbox is not available under 'Create Service' page");
		}
	
	
//delivery channel
		try {
	deliveryChanel = getwebelement(xml.getlocator("//locators/" + application + "/deliverychannel_withclasskey")).isDisplayed();
		sa.assertTrue(deliveryChanel, "delivery channel dropdown is not displayed");
		if(deliveryChanel) {
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Delivery Channel' dropdown is displaying under 'Management options' panel in 'Create Service' page as expected");
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/deliverychannel_withclasskey")));
    try {
		List<WebElement> listofdeliverychannel = driver
				.findElements(By.xpath("//div[@class='sc-ifAKCX oLlzc']"));
		for (WebElement deliverychanneltypes : listofdeliverychannel) {

			boolean match = false;
			for (int i = 0; i < deliverychannel.length; i++) {
				if (deliverychanneltypes.getText().equals(deliverychannel[i])) {
					match = true;
					Log.info("list of delivery channel are : " + deliverychanneltypes.getText());
					ExtentTestManager.getTest().log(LogStatus.INFO," List of Delivery channel dropdown values under Direct Fiber service subtype are: "+deliverychanneltypes.getText());	
					
				}
			}
			sa.assertTrue(match);
		}
	}catch(Exception e) {
    	e.printStackTrace();
    	Log.info("delivery channel dropdown values are mismatching");
    	ExtentTestManager.getTest().log(LogStatus.FAIL,"  values in delivery channel dropdowns under Direct Fiber service subtype are not displaying as expected");
    }
		} else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Delivery Channel' dropdown is not avilable under 'Management options' panel in 'Create Service' page");
		}
		
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'Delivery Channel' dropdown is not available under 'Create Service' page");
		}

		
    //Management Order
		try {
		Managementorder = getwebelement(xml.getlocator("//locators/" + application + "/managementOrder")).isDisplayed();
		sa.assertTrue(Managementorder, "Management order field is not displayed");
		if(Managementorder) {
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Management Order' dropdown is displaying under 'Management options' panel in 'Create Service' page as expected");
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/managementOrder")));
		
		try {
		List<WebElement> listofmanagementOrder = getwebelements(xml.getlocator("//locators/" + application + "/ClassNameForDropdowns"));
		for (WebElement mnaagementOrdertypes : listofmanagementOrder) {
			
			Log.info("Available Management Order name is : " + mnaagementOrdertypes.getText().toString());
			ExtentTestManager.getTest().log(LogStatus.PASS,
					"Available Management Order name is : " + mnaagementOrdertypes.getText().toString());
			Log.info("Available Management Order name is :" + mnaagementOrdertypes.getText().toString());
			
		}
		}catch(Exception e) {
			e.printStackTrace();
			Log.info(" 'Management Order' dropdown values are mismatching");
		}
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Management Order' dropdown is not available under 'Management options' panel in 'Create Service' page");
		}
		
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'Management Order' dropdown is not available under 'Create Service' page");
		}	
		
		
	scrolltoend();
	Thread.sleep(3000);
	
	
    //VPN topology
		vpnTopology = getwebelement(xml.getlocator("//locators/" + application + "/VPNtopology")).isDisplayed();
		sa.assertTrue(vpnTopology, "vpn topology dropdown is not displayed");
		if(vpnTopology) {
			ExtentTestManager.getTest().log(LogStatus.PASS, " VPN Topology' dropdown is displaying under 'Configuration Options' panel in 'Create Service' page as expected");
		
	//Check default values present inside VPN Topology dropdown		
		boolean defaultTOpologValues=getwebelement("//span[contains(text(),'Point-to-Point')]").isDisplayed();
		if(defaultTOpologValues) {
			ExtentTestManager.getTest().log(LogStatus.PASS, " Under 'VPN Topology' dropdown, 'Point-to-Point' is displaying by default as expected");
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Under 'VPN Topology' dropdown, 'Point-to-Point' is not displaying by default");
		}
		

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/IntermediateTechnology")));
		Thread.sleep(1000);
		
		scrolltoend();
		Thread.sleep(2000);
		
	//fine list of values under VPN topology dropdown	
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/VPNtopology_xbutton")));
		
		try {
		List<WebElement> listofvpntopology = getwebelements(xml.getlocator("//locators/" + application + "/ClassNameForDropdowns"));
		for (WebElement vpntopologytyeps : listofvpntopology) {

			boolean match = false;
			for (int i = 0; i < VPNtopology.length; i++) {
				if (vpntopologytyeps.getText().equals(VPNtopology[i])) {
					match = true;
					Log.info("list of vpn topologies are : " + vpntopologytyeps.getText());
					Log.info("list of vpn topologies: "+vpntopologytyeps.getText());
					ExtentTestManager.getTest().log(LogStatus.PASS,"list of vpn topologies: "+vpntopologytyeps.getText());
					
				}
			}
			sa.assertTrue(match);
		}
		}catch(Exception e) {
			e.printStackTrace();
			Log.info("vpn topology dropdown values are mismatching");
		}
		
		
	for(int i=0; i<VPNtopology.length;i++) {
		
		if(VPNtopology[i].equals("E-PN (Any-to-Any)")) {
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/VPNtopology")));
			Thread.sleep(3000);
			Clickon(getwebelement("//div[contains(text(),'"+VPNtopology[i] +"')]"));
			Thread.sleep(3000);
			
			Log.info("Under 'VPN Topology', When 'E-PN (Any-to-Any)' is selected, 'Circuit type' and 'Intermediate technology' should get disapper"
					+ "only Circuit reference field should occur ");
			
			ExtentTestManager.getTest().log(LogStatus.INFO,"Under 'VPN Topology', When'E-PN (Any-to-Any)' is selected, 'Circuit type' and 'Intermediate technology' should get disapper"
					+ "Only circuit reference should display ");
			
			circuitref = getwebelement(xml.getlocator("//locators/" + application + "/circuitReference")).isDisplayed();
			sa.assertTrue(circuitref, "circuit reference field is not displayed");
			if(circuitref) {
				ExtentTestManager.getTest().log(LogStatus.PASS, " 'Circuit Reference' field is displaying under 'Configuration Options' panel in 'Create Service' page as expected,  when 'VPN Topology' selected as 'E-PN (Any-to-Any)'");
			
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/circuitReference")));
			
			boolean CircuitReferencepopupalertmsg=getwebelement(xml.getlocator("//locators/" + application + "/circuitreferencealertmessage")).isDisplayed();
			
		    while(CircuitReferencepopupalertmsg)	{
			String text=Gettext(getwebelement(xml.getlocator("//locators/" + application + "/circuitreferencealertmessage")));
			Log.info("The alert popup when circuit reference field is clicked is: " + text);
			ExtentTestManager.getTest().log(LogStatus.PASS,"on clicking circuit reference field, alert popup message displays as: "+text);
			
			CircuitReferencepopupalertmsg=false;
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/xButton")));
			Thread.sleep(3000);
		    }	
		} else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Circuit Reference' field is not available under 'Configuration Options' panel in 'Create Service' page,  when 'VPN Topology' selected as 'E-PN (Any-to-Any)'");
		}
				    
		}
		
		else if(VPNtopology[i].equals("Hub&Spoke")) {

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/VPNtopology")));
			Thread.sleep(3000);
			Clickon(getwebelement("//div[contains(text(),'"+VPNtopology[i] +"')]"));
			Thread.sleep(3000);
			
			Log.info("Under 'VPN Topology', When 'Hub&Spoke'is selected, 'Circuit type' and 'Intermediate technology' should get disapper"
					+ "only Circuit reference field should occur ");
			
			ExtentTestManager.getTest().log(LogStatus.INFO,"Under 'VPN Topology', When 'Hub&Spoke' is selected, 'Circuit type' and 'Intermediate technology' should get disapper"
					+ "Only circuit reference should display ");
			
			circuitref = getwebelement(xml.getlocator("//locators/" + application + "/circuitReference")).isDisplayed();
			sa.assertTrue(circuitref, "circuit reference field is not displayed");
			if(circuitref) {
				ExtentTestManager.getTest().log(LogStatus.PASS, " 'Circuit Reference' field is displaying under 'Configuration Options' panel in 'Create Service' page as expected,  when 'VPN Topology' selected as 'HUb & Spoke'");
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/circuitReference")));
			
			boolean CircuitReferencepopupalertmsg=getwebelement(xml.getlocator("//locators/" + application + "/circuitreferencealertmessage")).isDisplayed();
			
		    while(CircuitReferencepopupalertmsg)	{
			String text=Gettext(getwebelement(xml.getlocator("//locators/" + application + "/circuitreferencealertmessage")));
			Log.info("The alert popup when circuit reference field is clicked is: " + text);
			ExtentTestManager.getTest().log(LogStatus.PASS,"on clicking circuit reference field, alert popup message displays as: "+text);
			
			CircuitReferencepopupalertmsg=false;
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/xButton")));
			Thread.sleep(3000);
		    }	 
		} else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Circuit Reference' field is not available under 'Configuration Options' panel in 'Create Service' page,  when 'VPN Topology' selected as 'HUb & Spoke'");
		}		    
		
		}
	else if(VPNtopology[i].equals("Point-to-Point")) {
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/VPNtopology")));
		Thread.sleep(3000);
		
		Clickon(getwebelement("//div[contains(text(),'"+VPNtopology[i] +"')]"));
		Thread.sleep(3000);
		 
		ExtentTestManager.getTest().log(LogStatus.INFO,"under VPN topology dropdown for Direct Fiber service subtype"
					+ " When'Point-to-Point' is selected,'Circuit type' , 'Intermediate technology', 'Circuit Reference' should get displayed");

		
		 
	//Intermediate technology field	
	try {
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/IntermediateTechnology")));
			intermediateTechnology = getwebelement(xml.getlocator("//locators/" + application + "/IntermediateTechnology"))
					.isDisplayed();
			sa.assertTrue(intermediateTechnology, "intermediate technology field is not displayed");
			if(intermediateTechnology) {
				ExtentTestManager.getTest().log(LogStatus.PASS, " 'Intermediate technology' field is displaying under 'Configuration Options' panel in 'Create Service' page, when 'VPN Topology' selected as 'Point-to-point'");
			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Intermediate technology' field is not available under 'Configuration Options' panel in 'Create Service' page when 'VPN Topology' selected as 'Point-to-point'");
			}
	}catch(Exception e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, "'Intermediate technology' field is not available under 'Configuration Options' panel in 'Create Service' page when 'VPN Topology' selected as 'Point-to-point'");
	}		
			
		//Circuit Reference 	
			circuitref = getwebelement(xml.getlocator("//locators/" + application + "/circuitReference")).isDisplayed();
			sa.assertTrue(circuitref, "circuit reference field is not displayed");
			if(circuitref) {
				ExtentTestManager.getTest().log(LogStatus.PASS, " 'Circuit Reference' field is displaying under 'Configuration Options' panel in 'Create Service' page as expected, when 'VPN Topology' selected as 'point-to-point'");
				
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/circuitReference")));
			
			boolean CircuitReferencepopupalertmsg=getwebelement(xml.getlocator("//locators/" + application + "/circuitreferencealertmessage")).isDisplayed();
			
			
		    while(CircuitReferencepopupalertmsg)	{
			String text=Gettext(getwebelement(xml.getlocator("//locators/" + application + "/circuitreferencealertmessage")));
			Log.info("The alert popup on clicking circuit reference field is : " + text);
			ExtentTestManager.getTest().log(LogStatus.PASS," on clicking 'Circuit reference' , alert emssage popup as : "+ text);
			
			CircuitReferencepopupalertmsg=false;
		    }
		    
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/xButton")));
			Thread.sleep(3000);
	}else {
		ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Circuit Reference' field is not available under 'Configuration Options' panel in 'Create Service' page,  when 'VPN Topology' selected as 'Point-to-Point'");
	}			
			
		
	//Circuit type field		
		try {
		List<WebElement> listofcircuittypes = driver
				.findElements(By.xpath("//div[@class='div-border div-margin container']//div[@class='row'][3]//span"));
		
		for (WebElement CircuitTypes : listofcircuittypes) {
			
				Log.info("list of circuit types are : " + CircuitTypes.getText());
				ExtentTestManager.getTest().log(LogStatus.PASS,"list of circuit types displaying are:  "+CircuitTypes.getText());

		}
		}catch(Exception e) {
			e.printStackTrace();
			Log.info("Circuit type values are mismatching");
			ExtentTestManager.getTest().log(LogStatus.FAIL,"list of circuit type values are mismatching");
			
		}
	}
		}
		
		}else {
			 ExtentTestManager.getTest().log(LogStatus.FAIL, " 'VPN topology' dropdown is not available under 'Configuration Options' panel in 'Create Service' page");
		 }
		
		okButton = getwebelement(xml.getlocator("//locators/" + application + "/okbutton")).isDisplayed();
		sa.assertTrue(okButton, "OK button is not displayed");
		
		
		cancelButton = getwebelement(xml.getlocator("//locators/" + application + "/cancelButton")).isDisplayed();
		sa.assertTrue(cancelButton, "Cancel button is not displayed");
		
		scrolltoend();
		Thread.sleep(3000);
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/cancelButton")));
		Thread.sleep(3000);
		
		sa.assertAll();
		ExtentTestManager.getTest().log(LogStatus.PASS," Fields under Direct Fiber service subtype is verified");
       }catch(AssertionError e) {
    	 e.printStackTrace();
//    	 ExtentTestManager.getTest().log(LogStatus.FAIL, "validation failure for Direct Fiber service subtype");
       }	
		

	
	
	}
	
	
	public void Fieldvalidation_DirectFibre1G(String application, String serviceType, String SelectSubService,
			String Interfacespeed,String proActivemonitoring, String vpntopology) throws InterruptedException, DocumentException, IOException {

		
		String[] deliverychannel = { "--", "Retail", "Reseller" , "WLC", "WLEC", "CES Solutions"};

		String[] VPNtopology = { "Point-to-Point", "Hub&Spoke", "E-PN (Any-to-Any)" };

		String[] notifyManagement= {"DNA"}; 
		 

		boolean serviceIdentificationField, ServiceType, ServiceSubtype,interfacespeedvalue, singleendpointCPE, email,
				phone, remark, performancereoprting, deliveryChanel, proactiveMonitor, Managementorder, vpnTopology,
				intermediateTechnology, circuitref, circuitType, okButton, cancelButton;
  
		try {	
		
		click_commonMethod(application, "OK", "obutton_spanTag", xml);
		Thread.sleep(3000);
		
		
			
		//Circuit Reference Error message
		warningMessage_commonMethod(application, "circuitreferenceErrmsg", "Circuit Reference", xml);
			
			scrollToTop();
			Thread.sleep(2000);
			
			
		//Service Identification Error message	
			warningMessage_commonMethod(application, "serviceIdentificationerrmsg", "Service Identification", xml);
			

			
	//service Identification	
		serviceIdentificationField = getwebelement(
				xml.getlocator("//locators/" + application + "/ServiceIdentification")).isDisplayed();
		sa.assertTrue(serviceIdentificationField, "Service identification field is not displayed");
		if(serviceIdentificationField) {
			ExtentTestManager.getTest().log(LogStatus.PASS, " ' Service Identfication' mandatory field is displaynig under 'Add Service' page as expected");
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " ' Service Identfication' mandatory field is not available under 'Add Service' page");
		}
    	   

     //Service type  
		ServiceType = getwebelement(xml.getlocator("//locators/" + application + "/ServiceType")).isDisplayed();
		sa.assertTrue(ServiceType, "Service type is not displayed");
		if(ServiceType) {
			ExtentTestManager.getTest().log(LogStatus.PASS,  " 'LANLink' is displying under 'Service type' as expected");
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'LANKLink' is not displying under 'Service type'");
		}
		
	//Service subtype
		ServiceSubtype = getwebelement("//div[contains(text(),'" + SelectSubService + "')]").isDisplayed();
		sa.assertTrue(ServiceSubtype, "Service subtype is not displayed");
		if(ServiceSubtype) {
			ExtentTestManager.getTest().log(LogStatus.PASS, SelectSubService + " is displying under 'Service Sub type' as expected");
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, SelectSubService + " is not displying under 'Service Sub type'");
		}
		
	
	//Interface speed 	
	    interfacespeedvalue=getwebelement("//div[contains(text(),'" + Interfacespeed + "')]").isDisplayed();
		sa.assertTrue(interfacespeedvalue, "Interface speed dropdown is not displaying as expected");
		if(interfacespeedvalue) {
			ExtentTestManager.getTest().log(LogStatus.PASS, Interfacespeed + " is displying under 'Interface Speed' as expected");
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, Interfacespeed + " is not displying under 'Interface Speed'");
		}
	
	//Single endpoint cpe
		try {
		singleendpointCPE = getwebelement(xml.getlocator("//locators/" + application + "/EndpointCPE")).isDisplayed();
		sa.assertTrue(singleendpointCPE, "single End point CPE checkbox is disabled by default");
		if(singleendpointCPE) {
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Single endpoint cpe' field is displying under 'Create Service'page as expected");
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL,  " 'Single endpoint cpe' field is not available under 'Create Service' pag");
		}
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'Single Endpoint CPE' checkbox is not available under 'Create Service' page");
		}
	
		
	//Email	
	try {	
		email = getwebelement(xml.getlocator("//locators/" + application + "/Email")).isDisplayed();
		sa.assertTrue(email, "email field is not displayed");
		if(email) {
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Email' field is displying under 'Create Service'page as expected");
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Email' field is not available under 'Create Service' pag");
		}
	}catch(Exception e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, "'Email' field is not available under 'Create Service' page");
	}	
		
	//phone	
	try {
		phone = getwebelement(xml.getlocator("//locators/" + application + "/PhoneContact")).isDisplayed();
		sa.assertTrue(phone, "phone contact field is not displayed");
		if(phone) {
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'phone' field is displying under 'Create Service'page as expected");
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'phone' field is not available under 'Create Service' page");
		}
	}catch(Exception e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, "'Phone' field is not available under 'Create Service' page");
	}	
		
  //remark
try {	
		remark = getwebelement(xml.getlocator("//locators/" + application + "/Remark")).isDisplayed();
		sa.assertTrue(remark, "remark field is not displayed");
		if(remark) {
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Remark' field is displying under 'Create Service'page as expected");
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Remark' field is not available under 'Create Service' page");
		}
}catch(Exception e) {
	e.printStackTrace();
	ExtentTestManager.getTest().log(LogStatus.FAIL, "'Remark' field is not available under 'Create Service' page");
}	
		

WebElement configurationOptionPanel= getwebelement(xml.getlocator("//locators/" + application + "/configurtoinptionpanel-webelementToScroll"));
ScrolltoElement(configurationOptionPanel);

Thread.sleep(3000);
//performance Reporting			
		performancereoprting = getwebelement(xml.getlocator("//locators/" + application + "/performanceReportingcheckbox"))
				.isDisplayed();
		sa.assertTrue(performancereoprting,
				"performance reporting checbox is not displayed under 'Create Service' page");
		if(performancereoprting) {
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'performance Reporting' checkbox is displying under 'Create Service'page as expected");
		
		
		boolean performancereoprtingselection = getwebelement(xml.getlocator("//locators/" + application + "/performanceReportingcheckbox")).isSelected();
		if(performancereoprtingselection) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Performance Reporting' checkbox is selected by default under 'Management Options' panel in 'Create Service page'"); 
		}else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "performance Reporting' checkbox is not selected by default under 'Management Options' panel in 'Create Service page as expected");
	
			
		//Per CoS Performance Reporting
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/performanceReportingcheckbox")));
			Thread.sleep(3000);
			
			boolean perCosreopt=getwebelement(xml.getlocator("//locators/" + application + "/perCoSperformncereport")).isDisplayed();
			sa.assertTrue(perCosreopt,
					"Per CoS Performance Reporting checbox is not displayed under 'Create Service' page, when 'Performance Reporting' checkbox is selected");
			if(perCosreopt) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "Per CoS Performance Reporting checbox is displayed under 'Management Options' panel in 'Create Service' page, when 'Performance Reporting' checkbox is selected");
			
				boolean perCoSselection=getwebelement(xml.getlocator("//locators/" + application + "/perCoSperformncereport")).isSelected();
				if(perCoSselection) {
					ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Per CoS Performance Reporting' checkbox is selected by default under 'Management Options' panel in 'Create Service page'"); 
				}else {
					ExtentTestManager.getTest().log(LogStatus.PASS, "Per CoS Performance Reporting' checkbox is not selected by default under 'Management Options' panel in 'Create Service page as expected");
			
			//Actelis Based    
					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/perCoSperformncereport")));	
					Thread.sleep(3000);
					boolean actelisBasedcheckbox=getwebelement(xml.getlocator("//locators/" + application + "/ActelisBasedcheckbox")).isDisplayed();
					sa.assertTrue(actelisBasedcheckbox,
							" 'Atelis Based' checbox is not displayed under 'Create Service' page, when 'Per CoS Performance Reporting' checkbox is selected");
					if(actelisBasedcheckbox) {
						ExtentTestManager.getTest().log(LogStatus.PASS, " 'Actelis Based' checbox is displayed under 'Management Options' panel in 'Create Service' page, when 'Performance Reporting' checkbox and 'Per CoS Performance Reporting' checkbox is selected");
					
					boolean actelisBasedselection=getwebelement(xml.getlocator("//locators/" + application + "/ActelisBasedcheckbox")).isSelected();
					if(actelisBasedselection) {
						ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Actelis Based' checkbox is selected by default under 'Management Options' panel in 'Create Service page'"); 
					}else {
						ExtentTestManager.getTest().log(LogStatus.PASS, "Actelis Based' checkbox is not selected by default under 'Management Options' panel in 'Create Service page as expected");
					
						
				//Bandwidth Options table
					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/ActelisBasedcheckbox")));	
					Thread.sleep(3000);
					
					//Standard CIR text field
					boolean standardCIR=getwebelement(xml.getlocator("//locators/" + application + "/standardCIRtextfield")).isDisplayed();
					sa.assertTrue(standardCIR,
							" 'Standard CIR' text field is not displayed under 'Create Service' page, when 'Performance Reporting'checkbox, 'Per CoS Performance Reporting' checkbox and 'Actelis Based' checkboxes are selected");  
					if(standardCIR) {
						ExtentTestManager.getTest().log(LogStatus.PASS," 'Standard CIR' text field is displayed under 'Management Options' panel in 'Create Service' page, when 'Performance Reporting'checkbox, 'Per CoS Performance Reporting' checkbox and 'Actelis Based' checkbox are selected");
					}else {
						ExtentTestManager.getTest().log(LogStatus.PASS," 'Standard CIR' text field is not displayed under 'Management Options' panel in 'Create Service' page, when 'Performance Reporting'checkbox, 'Per CoS Performance Reporting' checkbox and 'Actelis Based' checkbox are selected");
					}
					
					
					//Standard EIR Text field
					boolean standardEIR=getwebelement(xml.getlocator("//locators/" + application + "/standardEIRtextfield")).isDisplayed();
					sa.assertTrue(standardEIR,
							" 'Standard EIR' text field is not displayed under 'Create Service' page, when 'Performance Reporting'checkbox, 'Per CoS Performance Reporting' checkbox and 'Actelis Based' checkboxes are selected");  
					if(standardEIR) {
						ExtentTestManager.getTest().log(LogStatus.PASS," 'Standard EIR' text field is displayed under 'Management Options' panel in 'Create Service' page, when 'Performance Reporting'checkbox, 'Per CoS Performance Reporting' checkbox and 'Actelis Based' checkbox are selected");
					}else {
						ExtentTestManager.getTest().log(LogStatus.PASS," 'Standard EIR' text field is not displayed under 'Management Options' panel in 'Create Service' page, when 'Performance Reporting'checkbox, 'Per CoS Performance Reporting' checkbox and 'Actelis Based' checkbox are selected");
					}
					
					
					//Premium CIR text field
					boolean premiumCIR=getwebelement(xml.getlocator("//locators/" + application + "/premiumCIRtextfield")).isDisplayed();
					sa.assertTrue(premiumCIR,
							" 'Premium CIR' text field is not displayed under 'Create Service' page, when 'Performance Reporting'checkbox, 'Per CoS Performance Reporting' checkbox and 'Actelis Based' checkboxes are selected");  
					if(premiumCIR) {
						ExtentTestManager.getTest().log(LogStatus.PASS," 'Premium CIR' text field is displayed under 'Management Options' panel in 'Create Service' page, when 'Performance Reporting'checkbox, 'Per CoS Performance Reporting' checkbox and 'Actelis Based' checkbox are selected");
					}else {
						ExtentTestManager.getTest().log(LogStatus.PASS," 'Premium CIR' text field is not displayed under 'Management Options' panel in 'Create Service' page, when 'Performance Reporting'checkbox, 'Per CoS Performance Reporting' checkbox and 'Actelis Based' checkbox are selected");
					}
					
					
					//Premium EIR Text field
					boolean premiumEIR=getwebelement(xml.getlocator("//locators/" + application + "/premiumEIRtextfield")).isDisplayed();
					sa.assertTrue(premiumEIR,
							" 'Premium EIR' text field is not displayed under 'Create Service' page, when 'Performance Reporting'checkbox, 'Per CoS Performance Reporting' checkbox and 'Actelis Based' checkboxes are selected");  
					if(premiumEIR) {
						ExtentTestManager.getTest().log(LogStatus.PASS," 'Premium EIR' text field is displayed under 'Management Options' panel in 'Create Service' page, when 'Performance Reporting'checkbox, 'Per CoS Performance Reporting' checkbox and 'Actelis Based' checkbox are selected");
					}else {
						ExtentTestManager.getTest().log(LogStatus.PASS," 'Premium EIR' text field is not displayed under 'Management Options' panel in 'Create Service' page, when 'Performance Reporting'checkbox, 'Per CoS Performance Reporting' checkbox and 'Actelis Based' checkbox are selected");
					}
					
			 }
			}else {
				ExtentTestManager.getTest().log(LogStatus.PASS, " 'Actelis Based' checbox is not displayed under 'Management Options' panel in 'Create Service' page, when 'Performance Reporting' checkbox and 'Per CoS Performance Reporting' checkbox is selected");
			}
		 }
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Per CoS Performance Reporting checbox is not displayed under 'Management Options' panel in 'Create Service' page, when 'Performance Reporting' checkbox is selected");
    	 }
		}
	}else {
		ExtentTestManager.getTest().log(LogStatus.FAIL, " 'performance Reporting' checkbox is not available under 'Create Service' page");		
		}
		
		
	//proactive monitoring			
		proactiveMonitor = getwebelement(xml.getlocator("//locators/" + application + "/proactiveMonitoring"))
				.isDisplayed();
		sa.assertTrue(proactiveMonitor,
				"pro active monitoring checkbox is not displayed");
		if(proactiveMonitor) {
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'proactive monitoring' checkbox is displying under 'Create Service'page as expected");
		
		
		boolean proactiveMonitorselection = getwebelement(xml.getlocator("//locators/" + application + "/proactiveMonitoring")).isSelected();
		if(proactiveMonitorselection) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'proactive monitoring' checkbox is selected under 'Management Options' panel in 'Create Service page'"); 
		}else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "proactive monitoring' checkbox is not selected under 'Management Options' panel in 'Create Service page as expected");
		
	
	//Notification Management Dropdown	
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/proactiveMonitoring")));
		Log.info("Pro active monitoring check box is selected");
		Thread.sleep(3000);
	
	boolean notificationManagement=getwebelement(xml.getlocator("//locators/" + application + "/notificationmanagement"))
	.isDisplayed();
	sa.assertTrue(notificationManagement, "Notification management dropdown is not displayed when proactive monitoring is selected");
	Log.info("Notification management dropdown gets displayed when proactive monitoring is selected");
	if(notificationManagement) {
		ExtentTestManager.getTest().log(LogStatus.PASS, " 'Notification Management' dropdown is displaying under 'Management Options' panel when 'proactive Monitoring' checkbox is selected");
	
	Clickon(getwebelement(xml.getlocator("//locators/" + application + "/notificationmanagement")));
	try {
		List<WebElement> listofnotificationmanagement = driver
				.findElements(By.xpath("//div[@class='sc-ifAKCX oLlzc']"));
		for (WebElement notificationmanagementtypes : listofnotificationmanagement) {

			boolean match = false;
			for (int i = 0; i < notifyManagement.length; i++) {
				if (notificationmanagementtypes.getText().equals(notifyManagement[i])) {
					match = true;
					Log.info("list of notification management are : " + notificationmanagementtypes.getText());
					ExtentTestManager.getTest().log(LogStatus.PASS,"list of notification management are : " + notificationmanagementtypes.getText());
				}
			}
			sa.assertTrue(match);
			
		}
      }catch(Exception e) {
    	  Log.info("Notification Management dropdown values are mismatching");
    	  e.printStackTrace();
    	  ExtentTestManager.getTest().log(LogStatus.FAIL,"  values in Notification management dropdown under Direct Fiber service subtype is not displaying as expected");
      }
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Notification Management' dropdown is not available under 'Management Options' panel when 'proactive Monitoring' checkbox is selected");
		}
		}
	}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'proactive monitoring' checkbox is not available under 'Create Service' page");
		}
	
	
//delivery channel
	try {	
	deliveryChanel = getwebelement(xml.getlocator("//locators/" + application + "/deliverychannel_withclasskey")).isDisplayed();
		sa.assertTrue(deliveryChanel, "delivery channel dropdown is not displayed");
		if(deliveryChanel) {
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Delivery Channel' dropdown is displaying under 'Management options' panel in 'Create Service' page as expected");
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/deliverychannel_withclasskey")));
    try {
		List<WebElement> listofdeliverychannel = driver
				.findElements(By.xpath("//div[@class='sc-ifAKCX oLlzc']"));
		for (WebElement deliverychanneltypes : listofdeliverychannel) {

			boolean match = false;
			for (int i = 0; i < deliverychannel.length; i++) {
				if (deliverychanneltypes.getText().equals(deliverychannel[i])) {
					match = true;
					Log.info("list of delivery channel are : " + deliverychanneltypes.getText());
					ExtentTestManager.getTest().log(LogStatus.INFO," List of Delivery channel dropdown values under Direct Fiber service subtype are: "+deliverychanneltypes.getText());	
					
				}
			}
			sa.assertTrue(match);
		}
	}catch(Exception e) {
    	e.printStackTrace();
    	Log.info("delivery channel dropdown values are mismatching");
    	ExtentTestManager.getTest().log(LogStatus.FAIL,"  values in delivery channel dropdowns under Direct Fiber service subtype are not displaying as expected");
    }
		} else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Delivery Channel' dropdown is not avilable under 'Management options' panel in 'Create Service' page");
		}
	}catch(Exception e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, "'Delivery Channel' dropdown is not available under 'Create Service' page");
	}	

		
    //Management Order
try {	
		Managementorder = getwebelement(xml.getlocator("//locators/" + application + "/managementOrder")).isDisplayed();
		sa.assertTrue(Managementorder, "Management order field is not displayed");
		if(Managementorder) {
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Management Order' dropdown is displaying under 'Management options' panel in 'Create Service' page as expected");
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/managementOrder")));
		
		try {
		List<WebElement> listofmanagementOrder = getwebelements(xml.getlocator("//locators/" + application + "/ClassNameForDropdowns"));
		for (WebElement mnaagementOrdertypes : listofmanagementOrder) {
			
			Log.info("Available Management Order name is : " + mnaagementOrdertypes.getText().toString());
			ExtentTestManager.getTest().log(LogStatus.PASS,
					"Available Management Order name is : " + mnaagementOrdertypes.getText().toString());
			Log.info("Available Management Order name is :" + mnaagementOrdertypes.getText().toString());
			
		}
		}catch(Exception e) {
			e.printStackTrace();
			Log.info(" 'Management Order' dropdown values are mismatching");
		}
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Management Order' dropdown is not available under 'Management options' panel in 'Create Service' page");
		}
}catch(Exception e) {
	e.printStackTrace();
	ExtentTestManager.getTest().log(LogStatus.FAIL, "'Management Order' dropdown is not available under 'Create Service' page");
}	
		
		
		scrolltoend();
		Thread.sleep(3000);
		
    //VPN topology
		vpnTopology = getwebelement(xml.getlocator("//locators/" + application + "/VPNtopology")).isDisplayed();
		sa.assertTrue(vpnTopology, "vpn topology dropdown is not displayed");
		if(vpnTopology) {
			ExtentTestManager.getTest().log(LogStatus.PASS, " VPN Topology' dropdown is displaying under 'Configuration Options' panel in 'Create Service' page as expected");
			Log.info("VPN topology dropdown is displaying as expected");
			
		//Check default values present inside VPN Topology dropdown		
			boolean defaultTOpologValues=getwebelement("//span[contains(text(),'Point-to-Point')]").isDisplayed();
			if(defaultTOpologValues) {
				ExtentTestManager.getTest().log(LogStatus.PASS, " Under 'VPN Topology' dropdown, 'Point-to-Point' is displaying by default as expected");
				Log.info("The default topology value is displaying as :"+defaultTOpologValues);
			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, " Under 'VPN Topology' dropdown, 'Point-to-Point' is not displaying by default");
				Log.info(" Under 'VPN Topology' dropdown, 'Point-to-Point' is not displaying by default");
			}
	
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/IntermediateTechnology")));
			Thread.sleep(1000);
			
			scrolltoend();
			Thread.sleep(1000);
			
	//find list of values under VPN topology dropdown	
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/VPNtopology_xbutton")));
		
		try {
		List<WebElement> listofvpntopology = getwebelements(xml.getlocator("//locators/" + application + "/ClassNameForDropdowns"));
		for (WebElement vpntopologytyeps : listofvpntopology) {

			boolean match = false;
			for (int i = 0; i < VPNtopology.length; i++) {
				if (vpntopologytyeps.getText().equals(VPNtopology[i])) {
					match = true;
					Log.info("list of vpn topologies are : " + vpntopologytyeps.getText());
					Log.info("list of vpn topologies: "+vpntopologytyeps.getText());
					ExtentTestManager.getTest().log(LogStatus.PASS,"list of vpn topologies: "+vpntopologytyeps.getText());
					
				}
			}
			sa.assertTrue(match);
		}
		}catch(Exception e) {
			e.printStackTrace();
			Log.info("vpn topology dropdown values are mismatching");
		}
		
		
	for(int i=0; i<VPNtopology.length;i++) {

		scrolltoend();
		Thread.sleep(3000);
	
		Log.info("VPN Toplogy length is: "+ VPNtopology.length);
		Log.info(VPNtopology[i]+ " is the values going to pass inside vpn topology dropdown");
		
		
		if(VPNtopology[i].equals("E-PN (Any-to-Any)")) {
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/VPNtopology")));
			Thread.sleep(3000);
			Clickon(getwebelement("//div[contains(text(),'"+VPNtopology[i] +"')]"));
			Thread.sleep(3000);
			
			Log.info("Under 'VPN Topology', When 'E-PN (Any-to-Any)' is selected, 'Circuit type' and 'Intermediate technology' should get disapper"
					+ "only Circuit reference field should occur ");
			
			ExtentTestManager.getTest().log(LogStatus.INFO,"Under 'VPN Topology', When'E-PN (Any-to-Any)' is selected, 'Circuit type' and 'Intermediate technology' should get disapper"
					+ "Only circuit reference should display ");
			
			circuitref = getwebelement(xml.getlocator("//locators/" + application + "/circuitReference")).isDisplayed();
			sa.assertTrue(circuitref, "circuit reference field is not displayed");
			if(circuitref) {
				ExtentTestManager.getTest().log(LogStatus.PASS, " 'Circuit Reference' field is displaying under 'Configuration Options' panel in 'Create Service' page as expected,  when 'VPN Topology' selected as 'E-PN (Any-to-Any)'");
			
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/circuitReference")));
			
			boolean CircuitReferencepopupalertmsg=getwebelement(xml.getlocator("//locators/" + application + "/circuitreferencealertmessage")).isDisplayed();
			
		    while(CircuitReferencepopupalertmsg)	{
			String text=Gettext(getwebelement(xml.getlocator("//locators/" + application + "/circuitreferencealertmessage")));
			Log.info("The alert popup when circuit reference field is clicked is: " + text);
			ExtentTestManager.getTest().log(LogStatus.PASS,"on clicking circuit reference field, alert popup message displays as: "+text);
			
			CircuitReferencepopupalertmsg=false;
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/xButton")));
			Thread.sleep(3000);
		    }	
		} else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Circuit Reference' field is not available under 'Configuration Options' panel in 'Create Service' page,  when 'VPN Topology' selected as 'E-PN (Any-to-Any)'");
		}
				    
		}
		
		else if(VPNtopology[i].equals("Hub&Spoke")) {

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/VPNtopology")));
			Thread.sleep(3000);
			Clickon(getwebelement("//div[contains(text(),'"+VPNtopology[i] +"')]"));
			Thread.sleep(3000);
			
			Log.info("Under 'VPN Topology', When 'Hub&Spoke'is selected, 'Circuit type' and 'Intermediate technology' should get disapper"
					+ "only Circuit reference field should occur ");
			
			ExtentTestManager.getTest().log(LogStatus.INFO,"Under 'VPN Topology', When 'Hub&Spoke' is selected, 'Circuit type' and 'Intermediate technology' should get disapper"
					+ "Only circuit reference should display ");
			
			circuitref = getwebelement(xml.getlocator("//locators/" + application + "/circuitReference")).isDisplayed();
			sa.assertTrue(circuitref, "circuit reference field is not displayed");
			if(circuitref) {
				ExtentTestManager.getTest().log(LogStatus.PASS, " 'Circuit Reference' field is displaying under 'Configuration Options' panel in 'Create Service' page as expected,  when 'VPN Topology' selected as 'HUb & Spoke'");
			
			
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/circuitReference")));
			
			boolean CircuitReferencepopupalertmsg=getwebelement(xml.getlocator("//locators/" + application + "/circuitreferencealertmessage")).isDisplayed();
			
		    while(CircuitReferencepopupalertmsg)	{
			String text=Gettext(getwebelement(xml.getlocator("//locators/" + application + "/circuitreferencealertmessage")));
			Log.info("The alert popup when circuit reference field is clicked is: " + text);
			ExtentTestManager.getTest().log(LogStatus.PASS,"on clicking circuit reference field, alert popup message displays as: "+text);
			
			CircuitReferencepopupalertmsg=false;
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/xButton")));
			Thread.sleep(3000);
		    }	 
		} else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Circuit Reference' field is not available under 'Configuration Options' panel in 'Create Service' page,  when 'VPN Topology' selected as 'HUb & Spoke'");
		}		    
		
		}
	else if(VPNtopology[i].equals("Point-to-Point")) {
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/VPNtopology")));
		Thread.sleep(3000);
		Clickon(getwebelement("//div[text()='"+ VPNtopology[i] + "']"));
		Thread.sleep(3000);
		
		
		 
		ExtentTestManager.getTest().log(LogStatus.INFO,"under VPN topology dropdown for Direct Fiber service subtype"
					+ " When'Point-to-Point' is selected,'Circuit type' , 'Intermediate technology', 'Circuit Reference' should get displayed");

		
	
	//Intermediate technology field	
      try {	
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/IntermediateTechnology")));
			intermediateTechnology = getwebelement(xml.getlocator("//locators/" + application + "/IntermediateTechnology"))
					.isDisplayed();
			sa.assertTrue(intermediateTechnology, "intermediate technology field is not displayed");
			if(intermediateTechnology) {
				ExtentTestManager.getTest().log(LogStatus.PASS, " 'Intermediate technology' field is displaying under 'Configuration Options' panel in 'Create Service' page, when 'VPN Topology' selected as 'Point-to-point'");
			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Intermediate technology' field is not available under 'Configuration Options' panel in 'Create Service' page when 'VPN Topology' selected as 'Point-to-point'");
			}
      }catch(Exception e) {
    	  e.printStackTrace();
    	  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Intermediate technology' field is not available under 'Configuration Options' panel in 'Create Service' page when 'VPN Topology' selected as 'Point-to-point'");
      }
			
		//Circuit Reference 	
			circuitref = getwebelement(xml.getlocator("//locators/" + application + "/circuitReference")).isDisplayed();
			sa.assertTrue(circuitref, "circuit reference field is not displayed");
			if(circuitref) {
				ExtentTestManager.getTest().log(LogStatus.PASS, " 'Circuit Reference' field is displaying under 'Configuration Options' panel in 'Create Service' page as expected, when 'VPN Topology' selected as 'point-to-point'");
				
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/circuitReference")));
			
			boolean CircuitReferencepopupalertmsg=getwebelement(xml.getlocator("//locators/" + application + "/circuitreferencealertmessage")).isDisplayed();
			
			
		    while(CircuitReferencepopupalertmsg)	{
			String text=Gettext(getwebelement(xml.getlocator("//locators/" + application + "/circuitreferencealertmessage")));
			Log.info("The alert popup on clicking circuit reference field is : " + text);
			ExtentTestManager.getTest().log(LogStatus.PASS," on clicking 'Circuit reference' , alert emssage popup as : "+ text);
			
			CircuitReferencepopupalertmsg=false;
		    }
		    
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/xButton")));
			Thread.sleep(3000);
	}else {
		ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Circuit Reference' field is not available under 'Configuration Options' panel in 'Create Service' page,  when 'VPN Topology' selected as 'Point-to-Point'");
	}			
			
		
		
	//Circuit type field		
		try {
		List<WebElement> listofcircuittypes = driver
				.findElements(By.xpath("//div[@class='div-border div-margin container']//div[@class='row'][3]//span"));
		
		for (WebElement CircuitTypes : listofcircuittypes) {
			
				Log.info("list of circuit types are : " + CircuitTypes.getText());
				ExtentTestManager.getTest().log(LogStatus.PASS,"list of circuit types displaying are:  "+CircuitTypes.getText());
				
				Log.info("list of circuit types displaying are:  "+ CircuitTypes.getText());

		}
		}catch(Exception e) {
			e.printStackTrace();
			Log.info("Circuit type values are mismatching");
			ExtentTestManager.getTest().log(LogStatus.FAIL,"list of circuit type values are mismatching");
			
		}
	}
		}
		
		}else {
			 ExtentTestManager.getTest().log(LogStatus.FAIL, " 'VPN topology' dropdown is not available under 'Configuration Options' panel in 'Create Service' page");
		 }
		
		okButton = getwebelement(xml.getlocator("//locators/" + application + "/okbutton")).isDisplayed();
		sa.assertTrue(okButton, "OK button is not displayed");
		
		
		cancelButton = getwebelement(xml.getlocator("//locators/" + application + "/cancelButton")).isDisplayed();
		sa.assertTrue(cancelButton, "Cancel button is not displayed");
		
		scrolltoend();
		Thread.sleep(3000);
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/cancelButton")));
		Thread.sleep(3000);
		
		sa.assertAll();
		ExtentTestManager.getTest().log(LogStatus.PASS," Fields under Direct Fiber service subtype is verified");
       }catch(AssertionError e) {
    	 e.printStackTrace();
//    	 ExtentTestManager.getTest().log(LogStatus.FAIL, "validation failure for Direct Fiber service subtype");
       }	
		

	
	}
	
	
		
	public void dataToBeEnteredOncesubservicesselected(String application, String SelectSubService, String Interfacespeed,
			String ServiceIdentificationNumber, String EndpointCPE, String Email, String PhoneContact, String remark,
			String PerformanceReporting, String ProactiveMonitoring, String deliveryChannel, String ManagementOrder,
			String vpnTopology, String intermediateTechnology, String CircuitReference, String CircuitType,
			String DeliveryChannelForselecttag, String modularmsp, String autoCreateService,
			String ENNIcheckBox, String Manageconnectiondropdown, String A_Endtechnologydropdown,
			String B_Endtechnologydropdown, String notificationManagement, String Performancereporting,
			String perCocPerfrmReprt, String actelsBased, String standrdCir, String standrdEir, String prmiumCir, String prmiumEir) throws InterruptedException, IOException, DocumentException {

		Thread.sleep(5000);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Enter value in all fields for creating Service");

	if(modularmsp.equalsIgnoreCase("no")) {	
		
		if (Interfacespeed.equalsIgnoreCase("10GigE")) {

			DirectFibre_10G(application, ServiceIdentificationNumber, SelectSubService, Interfacespeed, EndpointCPE, Email,
					PhoneContact, remark, PerformanceReporting, ProactiveMonitoring, deliveryChannel, ManagementOrder,
					vpnTopology, intermediateTechnology, CircuitReference, CircuitType,notificationManagement);

		}
		
		else if (Interfacespeed.equalsIgnoreCase("1GigE")) {

			DirectFibre_1G(application, ServiceIdentificationNumber, SelectSubService, Interfacespeed, EndpointCPE, Email,
					PhoneContact, remark, PerformanceReporting, ProactiveMonitoring, deliveryChannel, ManagementOrder,
					vpnTopology, intermediateTechnology, CircuitReference, CircuitType,notificationManagement,perCocPerfrmReprt,actelsBased,
					standrdCir, standrdEir, prmiumCir,prmiumEir);

		}
	}
	
	

	else if(modularmsp.equalsIgnoreCase("yes")) {	
		

	 ExtentTestManager.getTest().log(LogStatus.FAIL, "When Modular MSP checkbox is selected, Direct Fiber service will not display");
		Thread.sleep(3000);
	
		
	}
}
	
	
	
	
	public void EditTheservicesselected(String application, String SelectSubService, String Interfacespeed,
			String ServiceIdentificationNumber, String EndpointCPE, String Email, String PhoneContact, String remark,
			String PerformanceReporting, String ProactiveMonitoring, String deliveryChannel, String ManagementOrder,
			String vpnTopology, String intermediateTechnology, String CircuitReference, String CircuitType,
			String DeliveryChannelForselecttag, String modularmsp, String autoCreateService,
			String ENNIcheckBox, String Manageconnectiondropdown, String A_Endtechnologydropdown,
			String B_Endtechnologydropdown, String notificationManagement,
			String perCoSperformanceReport, String actelisBased, String standardCIR, String standardEIR, String premiumCIR, String premiumEIR, String circuitTypeUsedInServiceCreation) throws InterruptedException, IOException, DocumentException {


		WebElement orderPanel= getwebelement(xml.getlocator("//locators/" + application + "/viewServicepage_OrderPanel"));
		ScrolltoElement(orderPanel);
		Thread.sleep(3000);
		
	click_commonMethod(application, "Action", "Editservice_actiondropdown", xml);
	Log.info("Action dropdown is working");
	Thread.sleep(3000);

	click_commonMethod(application, "Edit link", "Editservice_Editlink", xml);
	Thread.sleep(3000);
	

if(modularmsp.equalsIgnoreCase("no")) {	
	
	if (Interfacespeed.equalsIgnoreCase("10GigE")) {

		Edit_DirectFibre10G(application, ServiceIdentificationNumber, SelectSubService, Interfacespeed, EndpointCPE, Email,
				PhoneContact, remark, PerformanceReporting, ProactiveMonitoring, deliveryChannel, ManagementOrder,
				vpnTopology, intermediateTechnology, CircuitReference, circuitTypeUsedInServiceCreation,notificationManagement);

	}
	
	else if (Interfacespeed.equalsIgnoreCase("1GigE")) {


		Edit_DirectFibre1G(application, ServiceIdentificationNumber, SelectSubService, Interfacespeed, EndpointCPE, Email,
				PhoneContact, remark, PerformanceReporting, ProactiveMonitoring, deliveryChannel, ManagementOrder,
				vpnTopology, intermediateTechnology, CircuitReference, circuitTypeUsedInServiceCreation,notificationManagement,
				perCoSperformanceReport, actelisBased, standardCIR, standardEIR, premiumCIR, premiumEIR);
		

		ExtentTestManager.getTest().log(LogStatus.PASS,"Values has been Edited for Direct Fiber subtype under lanlink Service");
	
		
	}
}else {
	ExtentTestManager.getTest().log(LogStatus.FAIL, "Edit Service for Direct Fiber cannot be performed as 'modular MSP' checkbox is selected");
}

	Thread.sleep(3000);

}
	
	

	/**
	 * success Message common method
	 * @param application
	 * @throws InterruptedException
	 */
	public void verifysuccessmessage(String application, String expected) throws InterruptedException {
		
		waitforPagetobeenable();
		
		scrollToTop();
		Thread.sleep(1000);
		try {	
			
			boolean successMsg=getwebelement(xml.getlocator("//locators/" + application + "/serivceAlert")).isDisplayed();

			if(successMsg) {
				
				String alrtmsg=getwebelement(xml.getlocator("//locators/" + application + "/AlertForServiceCreationSuccessMessage")).getText();
				
				if(expected.contains(alrtmsg)) {
					
					ExtentTestManager.getTest().log(LogStatus.PASS,"Message is verified. It is displaying as: "+alrtmsg);
					Log.info("Message is verified. It is displaying as: "+alrtmsg);
					successScreenshot(application);
				}else if(expected.equals(alrtmsg)){
					
					ExtentTestManager.getTest().log(LogStatus.PASS,"Message is verified. It is displaying as: "+alrtmsg);
					Log.info("Message is verified. It is displaying as: "+alrtmsg);
					successScreenshot(application);
					
				}else {
					
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Message is displaying and it gets mismatches. It is displaying as: "+ alrtmsg +" .The Expected value is: "+ expected);
					Log.info("Message is displaying and it gets mismatches. It is displaying as: "+ alrtmsg);
					successScreenshot(application);
				}
				
			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, " Success Message is not displaying");
				Log.info(" Success Message is not displaying");
			}
			
			Thread.sleep(2000);
			
		}catch(Exception e) {
			Log.info("failure in fetching success message ");
			ExtentTestManager.getTest().log(LogStatus.FAIL, expected+ " Message is not displaying");
			Log.info(expected+ " message is not getting dislpayed");
			Thread.sleep(2000);
		}

	}


	public void VerifydatenteredForServiceSubTypeSelected(String application, String serviceType,
			String SelectSubService, String Interfacespeed, String ServiceIdentificationNumber, String EndpointCPE,
			String Email, String PhoneContact, String remark, String PerformanceMonitoring, String ProactiveMonitoring,
			String deliveryChannel, String ManagementOrder, String vpnTopology, String intermediateTechnology,
			String CircuitReference, String CircuitType, String AggregateTraffic, String DeliveryChannelForselecttag,
			String modularmsp, String autoCreateService, String ENNIcheckBox, String Manageconnectiondropdown,
			String A_Endtechnologydropdown, String B_Endtechnologydropdown, String Performancereporting,
			String perCocPerfrmReprt, String actelsBased, String standrdCir, String standrdEir, String prmiumCir, String prmiumEir, String notificationManagement)
			throws InterruptedException, IOException, DocumentException {

		Thread.sleep(2000);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verify entered values in view page");
		
		try {	
		
		if(modularmsp.equalsIgnoreCase("no")) {	
			
			if (Interfacespeed.equalsIgnoreCase("10GigE")) {

				verifydataEntered_DirectFibre10G(application, serviceType, ServiceIdentificationNumber, SelectSubService,
						Interfacespeed, EndpointCPE, Email, PhoneContact, remark, PerformanceMonitoring,
						ProactiveMonitoring, deliveryChannel, ManagementOrder, vpnTopology, intermediateTechnology,
						CircuitReference, CircuitType, modularmsp,notificationManagement);


			}
			
			else if (Interfacespeed.equalsIgnoreCase("1GigE")) {

				verifydataEntered_DirectFibre1G(application, serviceType, ServiceIdentificationNumber, SelectSubService,
						Interfacespeed, EndpointCPE, Email, PhoneContact, remark, PerformanceMonitoring,
						ProactiveMonitoring, deliveryChannel, ManagementOrder, vpnTopology, intermediateTechnology,
						CircuitReference, CircuitType, modularmsp,perCocPerfrmReprt, actelsBased, standrdCir, standrdEir, prmiumCir, prmiumEir, notificationManagement);

			}
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Direct Fiber' service will not occur when 'modular MSP' is selected");
		}
			
		sa.assertAll();
		
		}catch(AssertionError e) {
			Log.info("validation failed for verify Direct Fiber service subtype page ");
			e.printStackTrace();
		}
	}

	public void addsiteorder(String application, String country, String city, String CSR_Name, String site,
			String performReport, String ProactiveMonitor, String smartmonitor, String technology, String siteallias,
			String VLANid, String DCAenabledsite, String cloudserviceprovider, String sitevalue, String remark,
			String xngcityname, String xngcitycode,String devicename, String nonterminatepoinr, String Protected, String newcityselection, String existingcityselection,
			String existingsiteselection, String newsiteselection, String interfaceSpeed)throws InterruptedException, DocumentException, IOException {



	//Existing Country
		if(country.equalsIgnoreCase("null")) {
			
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Country is a mandatory field and the value is not provided ");
		}else {
		  Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Country")));
		  Clickon(getwebelement("//div[text()='"+ country +"']"));
		  ExtentTestManager.getTest().log(LogStatus.PASS,country+ " has been selected under 'Country' dropdown");
		}
	

	//Existing City	
		if(existingcityselection.equalsIgnoreCase("yes")) {

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_City")));
			Clickon(getwebelement("//div[text()='" + city + "']"));
			
			ExtentTestManager.getTest().log(LogStatus.PASS, city+ " is selected under Device Xng City dropdown");
		}else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "Existing City is not selected");
		}
		
		
	//new City	
		if(newcityselection.equalsIgnoreCase("yes")) {
			
			 Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Citytogglebutton")));
			 Thread.sleep(3000);
			 
			//City name 
			 if(xngcityname.equalsIgnoreCase("null")) {
				 ExtentTestManager.getTest().log(LogStatus.FAIL, "City name field is a mandatory field and the value is not provided");
			 }else {
			 SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_xngcityname")), xngcityname);
			 ExtentTestManager.getTest().log(LogStatus.PASS, xngcityname+ " is entered in City name field");
			 Thread.sleep(3000);
			 }
			 
			 //City code
			 if(xngcitycode.equalsIgnoreCase("null")) {
				 ExtentTestManager.getTest().log(LogStatus.FAIL, "City Code field is a mandatory field and the value is not provided");
			 }else {
			 SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_XNGcitycode")), xngcitycode);
			 Thread.sleep(3000);
			 ExtentTestManager.getTest().log(LogStatus.PASS, xngcitycode+" is entered in City Code field" );
			 }
			 
			 ExtentTestManager.getTest().log(LogStatus.PASS, "New City is created");
	
		}else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "New city is not created");
		}
		
		
	//Existing Site	
		if(existingsiteselection.equalsIgnoreCase("yes")) {
			
			click_commonMethod(application, "Site Order Toggle button", "Addsiteorder_Sitetogglebutton", xml);
			Thread.sleep(3000);
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_sitedropdown")));
			
			if(sitevalue.equalsIgnoreCase("null")) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Physical Site field is mandatory and no values are provided" );
			}else {
			Clickon(getwebelement("//div[text()='" + sitevalue + "']"));
			Thread.sleep(3000);
			ExtentTestManager.getTest().log(LogStatus.PASS, sitevalue+  " is selected under Physical Site dropdown");
			}
		}else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "Existing Site is not selected");
		}
		
		
	//New Site	
		if (newsiteselection.equalsIgnoreCase("yes")) {
			
			if(CSR_Name.equalsIgnoreCase("null")){
				ExtentTestManager.getTest().log(LogStatus.FAIL, "CSR name field is mandatory and no values are provided");
				
			}else {
				
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_CSRname")), CSR_Name);
			Thread.sleep(3000);
			ExtentTestManager.getTest().log(LogStatus.PASS, CSR_Name+ " is entered under CSR Name field");
			}

		} else {

			ExtentTestManager.getTest().log(LogStatus.PASS, "CSR name is not created");

		}
		
	//Perfomance Reporting	
		if(performReport.equalsIgnoreCase("null")) {
			
			ExtentTestManager.getTest().log(LogStatus.PASS, "Performance reporting value is not provided. 'Follow Service' is selected by default");
		}else {

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_performancereporting")));
		Thread.sleep(3000);
		Clickon(getwebelement("//div[text()='" + performReport + "']"));
		ExtentTestManager.getTest().log(LogStatus.PASS, performReport + " is selected under Performance reporting dropdown");
		}

	//Pro active monitoring	
		if(ProactiveMonitor.equalsIgnoreCase("null")) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "Pro active monitoring value is not provided. 'Follow Service' is selected by default");
			
		}else {
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_proactivemonitoring")));
		Thread.sleep(3000);
		Clickon(getwebelement("//div[text()='" + ProactiveMonitor + "']"));
		Thread.sleep(3000);
		ExtentTestManager.getTest().log(LogStatus.PASS, ProactiveMonitor+ " is selected under proactive monitoring dropdown");
		}

		
	//Smart monitoring
		if(smartmonitor.equalsIgnoreCase("null")) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "Smart monitoring value is not provided. 'Follow Service' is selected by default");
		}else {
			
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_smartmonitoring")));
		Thread.sleep(3000);
		Clickon(getwebelement("//div[text()='" + smartmonitor + "']"));
		Thread.sleep(3000);
		ExtentTestManager.getTest().log(LogStatus.PASS, smartmonitor+ " is selected under Smart monitoring dropdown");
		}
	
		
		//Site Allias
				if(siteallias.equalsIgnoreCase("null")) {
					ExtentTestManager.getTest().log(LogStatus.PASS, "No values entered for 'Site Alias' field");
				}else {
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_sitealias")), siteallias);
				Thread.sleep(3000);
				ExtentTestManager.getTest().log(LogStatus.PASS, siteallias+ " is entered under 'Site Alias' field");
				}

			//Vlan id
				if(VLANid.equalsIgnoreCase("null")) {
					ExtentTestManager.getTest().log(LogStatus.PASS, "No values entered for 'Vlan id' field");
				}else {
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Vlanid")), VLANid);
				Thread.sleep(3000);
				ExtentTestManager.getTest().log(LogStatus.PASS, VLANid + " is entered under Vlan id field");
				}

			//DCA Enabled Site	
				if (DCAenabledsite.equalsIgnoreCase("yes")) {

					Clickon(getwebelement(
							xml.getlocator("//locators/" + application + "/Addsiteorder_DCAenabledsitecheckbox")));
					ExtentTestManager.getTest().log(LogStatus.PASS, "DCA enabled checkbox is selected");
					
				 //Cloud Service provider
					if(cloudserviceprovider.equalsIgnoreCase("null")) {
						ExtentTestManager.getTest().log(LogStatus.FAIL, "DCA clous service provider dropdown is mandatory. No values are provided");
					}else {
					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_cloudserviceProvider")));
					Thread.sleep(3000);
					Clickon(getwebelement("//div[text()='" + cloudserviceprovider + "']"));
					Thread.sleep(3000);
					ExtentTestManager.getTest().log(LogStatus.PASS, cloudserviceprovider +  " is selected under 'cloud service provider' dropdown");
					}

				} else {
					Log.info("DCA site is not selected");
					ExtentTestManager.getTest().log(LogStatus.PASS, "DCA enabled checkbox is not selected");

				}
				
			//Remark
				if(remark.equalsIgnoreCase("null")) {
					ExtentTestManager.getTest().log(LogStatus.PASS, "No values entered under remark ");
				}else {
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_remark")), remark);
				Thread.sleep(3000);
				ExtentTestManager.getTest().log(LogStatus.PASS, remark + " is entered under 'remark' field");
				}
	
				

		
			

	//Technology
		if(technology.equalsIgnoreCase("null")) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Technology dropdown is a mandatory field and no values are provided"); 
		}else {
		
			if(interfaceSpeed.equals("1GigE")) {	
			
		if(technology.equals("Actelis") || technology.equals("Atrica") || technology.equals("Overture") || technology.equals("Accedian-1G") || technology.equals("Cyan" ) || technology.equals("Alu"))	{
			
			click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
			Thread.sleep(3000);
			WebElement technologySelected = getwebelement(xml.getlocator("//locators/" + application + "/selectValueUnderTechnologyDropdown").replace("value", technology)); clickonTechnology(technologySelected, technology);
			Thread.sleep(3000);
			ExtentTestManager.getTest().log(LogStatus.PASS, technology + " is selected under technology dropdown");
			
			
			
			if(technology.equals("Actelis")) {	
				
			     Log.info("No additional fields displays");
			}
			

			else if(technology.equals("Atrica")) {
				
				
			//Device name
				if(devicename.equalsIgnoreCase("null")) {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "device name field is mandatory. No values entered under 'device name' field");
				}else {
				
					SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Devicenamefield")), devicename);
					Thread.sleep(3000);
					ExtentTestManager.getTest().log(LogStatus.PASS, devicename + " is entered under 'device name' field");
				}
			
				
			//Non- termination point	
				if(nonterminatepoinr.equalsIgnoreCase("yes")) {
					
					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_nonterminationpoint")));
					Thread.sleep(3000);
					ExtentTestManager.getTest().log(LogStatus.PASS, "Non-termination point checkbox is selected");
				}else {
					Log.info("Non termination point checkbox is not selected as expected");
					ExtentTestManager.getTest().log(LogStatus.PASS, "Non-termination point chekbox is not selected");
				}
				
				
			//Protected	
				if(Protected.equalsIgnoreCase("yes")) {
					
					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_protected")));
					Thread.sleep(3000);
					ExtentTestManager.getTest().log(LogStatus.PASS, "Protected checkbox is selected");
				}else {
					Log.info("Protected checkbox is not selecetd as expected");
					ExtentTestManager.getTest().log(LogStatus.PASS, "Protected checkbox is not selected");
				}

			}
			
			

			else if(technology.equals("Overture") || technology.equals("Accedian-1G")) {	

			
						
			//Non- termination point	
				if(nonterminatepoinr.equalsIgnoreCase("yes")) {
					
					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_nonterminationpoint")));
					Thread.sleep(3000);
					ExtentTestManager.getTest().log(LogStatus.PASS, "Non-termination point checkbox is selected");
				}else {
					Log.info("Non termination point checkbox is not selected as expected");
					ExtentTestManager.getTest().log(LogStatus.PASS, "Non-termination point chekbox is not selected");
				}
				
				
			//Protected	
				if(Protected.equalsIgnoreCase("yes")) {
					
					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_protected")));
					Thread.sleep(3000);
					ExtentTestManager.getTest().log(LogStatus.PASS, "Protected checkbox is selected");
				}else {
					Log.info("Protected checkbox is not selecetd as expected");
					ExtentTestManager.getTest().log(LogStatus.PASS, "Protected checkbox is not selected");
				}
				

			}
			

			else if(technology.equals("Cyan")) {	

						
			//Non- termination point	
				if(nonterminatepoinr.equalsIgnoreCase("yes")) {
					
					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_nonterminationpoint")));
					Thread.sleep(3000);
					ExtentTestManager.getTest().log(LogStatus.PASS, "Non-termination point checkbox is selected");
				}else {
					Log.info("Non termination point checkbox is not selected as expected");
					ExtentTestManager.getTest().log(LogStatus.PASS, "Non-termination point chekbox is not selected");
				}
				
			
			}
			
			
			else if(technology.equals("Alu")) {
				
				//Device name
						if(devicename.equalsIgnoreCase("null")) {
							ExtentTestManager.getTest().log(LogStatus.FAIL, "device name field is mandatory. No values entered under 'device name' field");
						}else {
						
							SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Devicenamefield")), devicename);
							Thread.sleep(3000);
							ExtentTestManager.getTest().log(LogStatus.PASS, devicename + " is entered under 'device name' field");
						}
				
		       	}

			
			
		}
	}
			
			
			if(interfaceSpeed.equals("10GigE")) {	
				
				if(technology.equals("Accedian"))	{
					
					click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
					Thread.sleep(3000);
					WebElement technologySelected = getwebelement(xml.getlocator("//locators/" + application + "/selectValueUnderTechnologyDropdown").replace("value", technology)); clickonTechnology(technologySelected, technology);
					Thread.sleep(3000);
					ExtentTestManager.getTest().log(LogStatus.PASS, technology + " is selected under technology dropdown");
						
						//Non- termination point	
								if(nonterminatepoinr.equalsIgnoreCase("yes")) {
									
									Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_nonterminationpoint")));
									Thread.sleep(3000);
									ExtentTestManager.getTest().log(LogStatus.PASS, "Non-termination point checkbox is selected");
								}else {
									Log.info("Non termination point checkbox is not selected as expected");
									ExtentTestManager.getTest().log(LogStatus.PASS, "Non-termination point chekbox is not selected");
								}
								
								
							//Protected	
								if(Protected.equalsIgnoreCase("yes")) {
									
									Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_protected")));
									Thread.sleep(3000);
									ExtentTestManager.getTest().log(LogStatus.PASS, "Protected checkbox is selected");
								}else {
									Log.info("Protected checkbox is not selecetd as expected");
									ExtentTestManager.getTest().log(LogStatus.PASS, "Protected checkbox is not selected");
								}
						}else {
							ExtentTestManager.getTest().log(LogStatus.FAIL, technology + "is not available under 'Technology' dropdown for '10GigE' interface speed");
						}
					
				}
			}	
	
	
	ExtentTestManager.getTest().log(LogStatus.PASS, "Data has been entered for add site order");
	
		click_commonMethod(application, "OK", "okbutton", xml);

	}

	public void editSiteOrder(String application, String interfaceSpeed, String VPNTopology, String performReport, String ProactiveMonitor, String smartmonitor,
			String siteallias, String VLANid, String DCAenabledsite, String cloudserviceprovider, String technology, String nontermination, String Protected, String devicename, String remark, String IVreference,
			String siteOrderNumber, String Circuitreference, String GCRoloType, String VLAN, String VlanEtherType, String primaryVLAN, String primaryVlanEtherType, String offnetCreatedValue, String editOffnetvalue,
			String editEPNoffnetvalue, String mappingMode, String portBased, String vlanBased, String maapingModeAddedValue, String EPnEosDH, String circuitType, String sitePreferenceType)
			throws InterruptedException, DocumentException, IOException {

		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying 'Edit Site Order'");
		
		  click_commonMethod(application, "Edit Site order link", "EditsideOrderlink", xml);
		  Thread.sleep(6000);

		  scrollToTop();
		  Thread.sleep(3000);
	//Point to Point	  
		  if(VPNTopology.equals("Point-to-Point") &&  (circuitType.equals("Default"))) {
			  
			  if(interfaceSpeed.equals("1GigE"))
			  {
				  editSiteOrder_P2P_1G(application, performReport, ProactiveMonitor, smartmonitor, siteallias, VLANid, DCAenabledsite, cloudserviceprovider, technology, nontermination, Protected, devicename, remark);
			  }
			  
			  else  if(interfaceSpeed.equals("10GigE"))
			  {
				  editSiteOrder_P2P_10G(application, performReport, ProactiveMonitor, smartmonitor, siteallias, VLANid, DCAenabledsite, cloudserviceprovider, technology, nontermination, Protected, remark);
			  }
			  
		  }
		  
		  
		  if(VPNTopology.equals("Point-to-Point") &&  (circuitType.equals("Extended Circuit"))) {
			  
			  if(interfaceSpeed.equals("1GigE"))
			  {
				  editSiteOrder_P2P_1G_extendedCircuit(application, performReport, ProactiveMonitor, smartmonitor, siteallias, VLANid,
						  DCAenabledsite, cloudserviceprovider, technology, nontermination, Protected, devicename, remark, sitePreferenceType);
			  }
			  
			  else  if(interfaceSpeed.equals("10GigE"))
			  {
				  editSiteOrder_P2P_10G_extendedCircuits(application, performReport, ProactiveMonitor, smartmonitor, siteallias, VLANid, 
						  DCAenabledsite, cloudserviceprovider, technology, nontermination, Protected, remark, sitePreferenceType);
			  }
			  
		  }
		  
		  
	//HUb & Spoke	  
		  if(VPNTopology.equals("Hub&Spoke")) {
			  
			  if(interfaceSpeed.equals("1GigE")) {
				  
				  if(IVreference.equals("Primary")) {
					  
					  editSiteOrder_HubAndSpoke_1G_IVRefPrimary(application, performReport, ProactiveMonitor, smartmonitor, siteallias, VLANid, DCAenabledsite, 
							  cloudserviceprovider, technology, nontermination, Protected, devicename, remark, siteOrderNumber, IVreference, Circuitreference,editOffnetvalue);
					  
				  }
				  
				  else if(IVreference.equals("Access")) {
					  
					  editSiteOrder_HubAndSpoke_1G_IVRefAccess(application, performReport, ProactiveMonitor, smartmonitor, siteallias, VLANid, DCAenabledsite, cloudserviceprovider,
							  technology, nontermination, Protected, devicename, remark, siteOrderNumber, IVreference, Circuitreference, GCRoloType, VLAN, VlanEtherType, primaryVLAN,
							  primaryVlanEtherType, editOffnetvalue);
					  
				  }
			  }
			  
			  else  if(interfaceSpeed.equals("10GigE")) {
				  
				  editSiteOrder_HubAndSpoke_10G(application, performReport, ProactiveMonitor, smartmonitor, siteallias, VLANid, DCAenabledsite, cloudserviceprovider, technology,
						  nontermination, Protected, devicename, remark, siteOrderNumber, IVreference, Circuitreference,editOffnetvalue);
			  }
		  }
		  
		  
	//E-PN (Any to Any)	  
		  if(VPNTopology.equals("E-PN (Any-to-Any)")) {
			  
			  if(interfaceSpeed.equals("1GigE")) {
				  
				  if(IVreference.equals("Primary")) {
					  
					  editSiteOrder_EPN_1G_IVRefPrimary(application, performReport, ProactiveMonitor, smartmonitor, siteallias, VLANid, DCAenabledsite, 
							  cloudserviceprovider, technology, nontermination, Protected, devicename, remark, siteOrderNumber, IVreference, Circuitreference,editEPNoffnetvalue, EPnEosDH);
					  
				  }
				  
				  else if(IVreference.equals("Access")) {
					  
					  editSiteOrder_EPN_1G_IVRefAccess(application, performReport, ProactiveMonitor, smartmonitor, siteallias, VLANid, DCAenabledsite, cloudserviceprovider,
							  technology, nontermination, Protected, devicename, remark, siteOrderNumber, IVreference, Circuitreference, GCRoloType, VLAN, VlanEtherType, primaryVLAN,
							  primaryVlanEtherType, editEPNoffnetvalue, mappingMode, portBased, vlanBased, maapingModeAddedValue);
					  
				  }
			  }
			  
			  else  if(interfaceSpeed.equals("10GigE")) {
				  
				  editSiteOrder_EPN_10G(application, performReport, ProactiveMonitor, smartmonitor, siteallias, VLANid, DCAenabledsite, cloudserviceprovider, technology,
						  nontermination, Protected, devicename, remark, siteOrderNumber, IVreference, Circuitreference,editEPNoffnetvalue);
			  }
			  
		  }

	}
	
	public void editSiteOrder_P2P_10G(String application, String performReport, String ProactiveMonitor, String smartmonitor,
			String siteallias, String VLANid, String DCAenabledsite, String cloudserviceprovider, String technology, String nontermination, String Protected, String remark) throws InterruptedException, DocumentException, IOException {
		
			//Performance Reporting
				editSiteOrder_Performancereporting(application, performReport);
				
				 
			//Pro active Monitoring 
				editSiteOrder_proactiveMonitoring(application, ProactiveMonitor);

				 
			//Smart Monitoring
				editSiteOrder_smartMonitoring(application, smartmonitor);

			//Vlan id
				editsiteOrder_vlanid(application, VLANid);
				 
			//Site alias
				editsiteorder_sitealias(application, siteallias);
				
				scrolltoend();
				Thread.sleep(3000);
				 
			//DCA Enabled Site 
				editesiteOrder_DcaEnabled(application, DCAenabledsite, cloudserviceprovider);
		
				
			//Remark
				editsiteOrder_remark(application, remark);
				
			//Technology
				editSiteOrder_technology(application,technology);
				 
				
				if(technology.equalsIgnoreCase("Accedian")) {
					 
					//Non-termination point
					 editsiteorder_NonterminationPoint(application, nontermination); 
					
					//Protected 
					 editsiteOrder_protected(application, Protected);
					 
				 }
				
				scrolltoend();
				Thread.sleep(3000);
				
				click_commonMethod(application, "OK", "okbutton", xml);
				Thread.sleep(2000);
		
	}

	
	public void editSiteOrder_P2P_10G_extendedCircuits(String application, String performReport, String ProactiveMonitor, String smartmonitor,
			String siteallias, String VLANid, String DCAenabledsite, String cloudserviceprovider, String technology, String nontermination,
			String Protected, String remark, String siteOrder_sitePreferenceType) throws InterruptedException, DocumentException, IOException {
		
			//Performance Reporting
				editSiteOrder_Performancereporting(application, performReport);
				
				 
			//Pro active Monitoring 
				editSiteOrder_proactiveMonitoring(application, ProactiveMonitor);

				 
			//Smart Monitoring
				editSiteOrder_smartMonitoring(application, smartmonitor);

			//Vlan id
				editsiteOrder_vlanid(application, VLANid);
				
				
			//Site Preference Type	
				addDropdownValues_commonMethod(application, "Site Preference Type" , "AddSiteOrder_sitePreferenceType_Dropodwn" , siteOrder_sitePreferenceType, xml);
				 
			//Site alias
				editsiteorder_sitealias(application, siteallias);
				
				scrolltoend();
				Thread.sleep(3000);
				 
			//DCA Enabled Site 
				editesiteOrder_DcaEnabled(application, DCAenabledsite, cloudserviceprovider);
		
				
			//Remark
				editsiteOrder_remark(application, remark);
				
			//Technology
				editSiteOrder_technology(application,technology);
				 
				
				if(technology.equalsIgnoreCase("Accedian")) {
					 
					//Non-termination point
					 editsiteorder_NonterminationPoint(application, nontermination); 
					
					//Protected 
					 editsiteOrder_protected(application, Protected);
					 
				 }
				
				scrolltoend();
				Thread.sleep(3000);
				
				click_commonMethod(application, "OK", "okbutton", xml);
				Thread.sleep(2000);
		
	}
	
	
	
	public void editSiteOrder_P2P_1G(String application, String performReport, String ProactiveMonitor, String smartmonitor,
			String siteallias, String VLANid, String DCAenabledsite, String cloudserviceprovider, String technology, String nontermination, String Protected, String devicename, String remark)
			throws InterruptedException, DocumentException, IOException {
		
	//Performance Reporting
		editSiteOrder_Performancereporting(application, performReport);
		
		 
	//Pro active Monitoring 
		editSiteOrder_proactiveMonitoring(application, ProactiveMonitor);

		 
	//Smart Monitoring
		editSiteOrder_smartMonitoring(application, smartmonitor);

	//Vlan id
		editsiteOrder_vlanid(application, VLANid);
		 
	//Site alias
		editsiteorder_sitealias(application, siteallias);
		
		scrolltoend();
		Thread.sleep(3000);
		
	//DCA Enabled Site 
		editesiteOrder_DcaEnabled(application, DCAenabledsite, cloudserviceprovider);
	
		
	//Remark
		editsiteOrder_remark(application, remark);
		
	//Technology
		editSiteOrder_technology(application,technology);
		 
		if(technology.equalsIgnoreCase("Actelis")) {
			Log.info(" NO additional fields display for technology Actelis");
		}
		
		
		if(technology.equalsIgnoreCase("Atrica")) {
			 
			//Non-termination point
			 editsiteorder_NonterminationPoint(application, nontermination); 
			
			//Protected 
			 editsiteOrder_protected(application, Protected);
			 
			 //Device Name
			 editSiteOrder_deviceName(application, devicename);
			
		 }
		
		if(technology.equalsIgnoreCase("Overture")) {
			
			//Non-termination point
			 editsiteorder_NonterminationPoint(application, nontermination); 
			
			//Protected 
			 editsiteOrder_protected(application, Protected);
			 
		}
		
		
		if(technology.equalsIgnoreCase("Alu")) {
			
			 //Device Name
			 editSiteOrder_deviceName(application, devicename);
			
		}
		
		if(technology.equalsIgnoreCase("Accedian-1G")) {
			
			//Non-termination point
			 editsiteorder_NonterminationPoint(application, nontermination); 
			
			//Protected 
			 editsiteOrder_protected(application, Protected);
			
		}
		
		if(technology.equalsIgnoreCase("Cyan")) {
			
			//Non-termination point
			 editsiteorder_NonterminationPoint(application, nontermination); 
			
		}
		
		scrolltoend();
		Thread.sleep(3000);
		
		click_commonMethod(application, "OK", "okbutton", xml);
		Thread.sleep(2000);
		
		
	}
	
	
	public void editSiteOrder_P2P_1G_extendedCircuit(String application, String performReport, String ProactiveMonitor, String smartmonitor,
			String siteallias, String VLANid, String DCAenabledsite, String cloudserviceprovider, String technology, String nontermination, 
			String Protected, String devicename, String remark, String siteOrder_sitePreferenceType)
			throws InterruptedException, DocumentException, IOException {
		
	//Performance Reporting
		editSiteOrder_Performancereporting(application, performReport);
		
		 
	//Pro active Monitoring 
		editSiteOrder_proactiveMonitoring(application, ProactiveMonitor);

		 
	//Smart Monitoring
		editSiteOrder_smartMonitoring(application, smartmonitor);

	//Vlan id
		editsiteOrder_vlanid(application, VLANid);
		
	//Site Preference Type	
		addDropdownValues_commonMethod(application, "Site Preference Type" , "AddSiteOrder_sitePreferenceType_Dropodwn" , siteOrder_sitePreferenceType, xml);
		 
	//Site alias
		editsiteorder_sitealias(application, siteallias);
		
		scrolltoend();
		Thread.sleep(3000);
		
	//DCA Enabled Site 
		editesiteOrder_DcaEnabled(application, DCAenabledsite, cloudserviceprovider);
	
		
	//Remark
		editsiteOrder_remark(application, remark);
		
	//Technology
		editSiteOrder_technology(application,technology);
		 
		if(technology.equalsIgnoreCase("Actelis")) {
			Log.info(" NO additional fields display for technology Actelis");
		}
		
		
		if(technology.equalsIgnoreCase("Atrica")) {
			 
			//Non-termination point
			 editsiteorder_NonterminationPoint(application, nontermination); 
			
			//Protected 
			 editsiteOrder_protected(application, Protected);
			 
			 //Device Name
			 editSiteOrder_deviceName(application, devicename);
			
		 }
		
		if(technology.equalsIgnoreCase("Overture")) {
			
			//Non-termination point
			 editsiteorder_NonterminationPoint(application, nontermination); 
			
			//Protected 
			 editsiteOrder_protected(application, Protected);
			 
		}
		
		
		if(technology.equalsIgnoreCase("Alu")) {
			
			 //Device Name
			 editSiteOrder_deviceName(application, devicename);
			
		}
		
		if(technology.equalsIgnoreCase("Accedian-1G")) {
			
			//Non-termination point
			 editsiteorder_NonterminationPoint(application, nontermination); 
			
			//Protected 
			 editsiteOrder_protected(application, Protected);
			
		}
		
		if(technology.equalsIgnoreCase("Cyan")) {
			
			//Non-termination point
			 editsiteorder_NonterminationPoint(application, nontermination); 
			
		}
		
		scrolltoend();
		Thread.sleep(3000);
		
		click_commonMethod(application, "OK", "okbutton", xml);
		
		Thread.sleep(2000);
		
		
	}
	
	
	
	public void editSiteOrder_HubAndSpoke_1G_IVRefAccess(String application, String performReport, String ProactiveMonitor, String smartmonitor,
			String siteallias, String VLANid, String DCAenabledsite, String cloudserviceprovider, String technology, String nontermination, String Protected, 
			String devicename, String remark, String siteOrderNumber, String IVReference, String circuitReference, String GCRoloType, String VLAN, String VlanEtherType,
			String primaryVLAN, String primaryVlanEtherType, String offnet)
			throws InterruptedException, DocumentException, IOException {
	
		//Performance Reporting
				editSiteOrder_Performancereporting(application, performReport);
			
				
		
	//IV Reference
		editSiteOrder_IVReference(application, IVReference);
	
		
		//Site Order Number (Siebel Service ID)
				editSiteOrder_siteOrderNumber(application, siteOrderNumber);	
		
		 
	//Pro active Monitoring 
		editSiteOrder_proactiveMonitoring(application, ProactiveMonitor);

		 
	//Smart Monitoring
		editSiteOrder_smartMonitoring(application, smartmonitor);

		 
	//Site alias
		editsiteorder_sitealias(application, siteallias);
		
	//Offnet
		editSiteorder_Offnet(application, offnet);
	
		
		scrolltoend();
		Thread.sleep(3000);
		
	//Remark
		editsiteOrder_remark(application, remark);
		
	// Circuit Reference
		editsiteorder_circuitReference(application, circuitReference);
		
	//Technology
		editSiteOrder_technology(application,technology);
		 
		if(technology.equalsIgnoreCase("Actelis")) {
			Log.info(" NO additional fields display for technology Actelis");
		}
		
		
		if(technology.equalsIgnoreCase("Atrica")) {
			 
			//Non-termination point
			 editsiteorder_NonterminationPoint(application, nontermination); 
			
			//Protected 
			 editsiteOrder_protected(application, Protected);
			 
		 }
		
		if(technology.equalsIgnoreCase("Overture")) {
			
			//Non-termination point
			 editsiteorder_NonterminationPoint(application, nontermination); 
			
			//Protected 
			 editsiteOrder_protected(application, Protected);
			 
			//GCR OLO Type
			 editSiteOrder_GCRoloType(application, GCRoloType);
			 
			//VLAN
			 editsiteorder_VLAN(application, VLAN);
			 
		    //VLAN Ether Type	
			 editSiteOrder_VLANEtherType(application, VlanEtherType);
			 
		    //Primary VLAN
			 editsiteorder_primaryVLAN(application, primaryVLAN);
			 
		    //Primary VLAN Ether type	
			 editSiteOrder_primaryVLANEtherType(application, primaryVlanEtherType);
			 
		}
		
		
		if(technology.equalsIgnoreCase("Alu")) {
			
			Log.info("No additional fields display for Alu Technology");
		}
		
		if((technology.equals("Accedian")) || (technology.equals("Accedian-1G"))) {
			
			//Non-termination point
			 editsiteorder_NonterminationPoint(application, nontermination); 
			
			//Protected 
			 editsiteOrder_protected(application, Protected);
			
		}
		
		if(technology.equalsIgnoreCase("Cyan")) {
			
			//Non-termination point
			 editsiteorder_NonterminationPoint(application, nontermination); 
			
		}
		
		scrolltoend();
		Thread.sleep(3000);
		
		click_commonMethod(application, "OK", "okbutton", xml);
		Thread.sleep(2000);
		
	}
	
	
	public void editSiteOrder_EPN_1G_IVRefAccess(String application, String performReport, String ProactiveMonitor, String smartmonitor,
			String siteallias, String VLANid, String DCAenabledsite, String cloudserviceprovider, String technology, String nontermination, String Protected, 
			String devicename, String remark, String siteOrderNumber, String IVReference, String circuitReference, String GCRoloType, String VLAN, String VlanEtherType,
			String primaryVLAN, String primaryVlanEtherType, String offnet, String mappingMode, String portBased, String vlanBased, String maapingModeAddedValue)
			throws InterruptedException, DocumentException, IOException {
	
		
	//Site Order Number (Siebel Service ID)
		editSiteOrder_siteOrderNumber(application, siteOrderNumber);
		
	//IV Reference
		editSiteOrder_IVReference(application, IVReference);
	
	//Performance Reporting
		editSiteOrder_Performancereporting(application, performReport);
		
		 
	//Pro active Monitoring 
		editSiteOrder_proactiveMonitoring(application, ProactiveMonitor);

		 
	//Smart Monitoring
		editSiteOrder_smartMonitoring(application, smartmonitor);

		 
	//Site alias
		editsiteorder_sitealias(application, siteallias);
	
		scrolltoend();
		Thread.sleep(3000);

	//Remark
		editsiteOrder_remark(application, remark);
		
	// Circuit Reference
		editsiteorder_circuitReference(application, circuitReference);
		
	//Technology
		editSiteOrder_technology(application,technology);
		 
		if(technology.equalsIgnoreCase("Actelis")) {
			Log.info(" NO additional fields display for technology Actelis");
		}
		
		
		if(technology.equalsIgnoreCase("Atrica")) {
			 
			//Non-termination point
			 editsiteorder_NonterminationPoint(application, nontermination); 
			
			//Protected 
			 editsiteOrder_protected(application, Protected);
			 
			//Device Name
			 editSiteOrder_deviceName(application, devicename);
			 
			//Mapping Mode 
			 editSiteOrder_mappingMode(application, mappingMode, portBased, vlanBased);
		
		//validation --> sometime mapping mode dropdown will not edited, port or Vlan fields will be edited	 
			 if((maapingModeAddedValue.equalsIgnoreCase("Port Based")) && (mappingMode.equalsIgnoreCase("null"))) {
				 
				 edittextFields_commonMethod(application, "Port Name" , "portname_textField", portBased);
					
			 }
			 else  if((maapingModeAddedValue.equalsIgnoreCase("Vlan Based")) && (mappingMode.equalsIgnoreCase("null"))) {
				 
				 edittextFields_commonMethod(application, "VLAN Id", "vlanid_textfield", vlanBased);
			 }
		 }
		
		if(technology.equalsIgnoreCase("Overture")) {
			
			//Non-termination point
			 editsiteorder_NonterminationPoint(application, nontermination); 
			 
			//Device Name
			 editSiteOrder_deviceName(application, devicename);	 
			
			//Protected 
			 editsiteOrder_protected(application, Protected);
			 
			//GCR OLO Type
			 editSiteOrder_GCRoloType(application, GCRoloType);
			 
			//VLAN
			 editsiteorder_VLAN(application, VLAN);
			 
		    //VLAN Ether Type	
			 editSiteOrder_VLANEtherType(application, VlanEtherType);
			 
		}
		
		
		if(technology.equalsIgnoreCase("Alu")) {
			
			 //Device Name
			 editSiteOrder_deviceName(application, devicename);
			 
			//Mapping Mode
			 editSiteOrder_mappingMode(application, mappingMode, portBased, vlanBased);
			 
			//validation --> sometime mapping mode dropdown will not edited, port or Vlan fields will be edited	 
			 if((maapingModeAddedValue.equalsIgnoreCase("Port Based")) && (mappingMode.equalsIgnoreCase("null"))) {
				 
				 edittextFields_commonMethod(application, "Port Name" , "portname_textField", portBased);
					
			 }
			 else  if((maapingModeAddedValue.equalsIgnoreCase("Vlan Based")) && (mappingMode.equalsIgnoreCase("null"))) {
				 
				 edittextFields_commonMethod(application, "VLAN Id", "vlanid_textfield", vlanBased);
			 }
			 
		}
		
		if((technology.equalsIgnoreCase("Accedian")) || (technology.equalsIgnoreCase("Accedian-1G")) ) {
			
			//Non-termination point
			 editsiteorder_NonterminationPoint(application, nontermination); 
			 
			//Device Name
			 editSiteOrder_deviceName(application, devicename); 
			
			//Protected 
			 editsiteOrder_protected(application, Protected);
			
		}
		
		if(technology.equalsIgnoreCase("Cyan")) {
			
			//Non-termination point
			 editsiteorder_NonterminationPoint(application, nontermination); 
			
		}
		
		
		scrolltoend();
		Thread.sleep(3000);
		
		click_commonMethod(application, "OK", "okbutton", xml);
		Thread.sleep(2000);
		
		
	}
	
	
	
	
	public void editSiteOrder_HubAndSpoke_1G_IVRefPrimary(String application, String performReport, String ProactiveMonitor, String smartmonitor,
			String siteallias, String VLANid, String DCAenabledsite, String cloudserviceprovider, String technology, String nontermination, String Protected, String devicename, String remark, 
			String siteOrderNumber, String IVReference, String CircuitReference, String offnet)
			throws InterruptedException, DocumentException, IOException {
	
		
	//Site Order Number (Siebel Service ID)
		editSiteOrder_siteOrderNumber(application, siteOrderNumber);
		
	
	//IV Reference
		editSiteOrder_IVReference(application, IVReference);
	
	//Performance Reporting
		editSiteOrder_Performancereporting(application, performReport);
		
		 
	//Pro active Monitoring 
		editSiteOrder_proactiveMonitoring(application, ProactiveMonitor);

		 
	//Smart Monitoring
		editSiteOrder_smartMonitoring(application, smartmonitor);

		 
	//Site alias
		editsiteorder_sitealias(application, siteallias);
		
	//Offnet 
		editSiteorder_Offnet(application, offnet);

		scrolltoend();
		Thread.sleep(3000);
		
	//Remark
		editsiteOrder_remark(application, remark);
		
	//Circuit Reference
		editsiteorder_circuitReference(application, CircuitReference);
		
	//Technology
		editSiteOrder_technology(application,technology);
		 
		if(technology.equalsIgnoreCase("Actelis")) {
			Log.info(" NO additional fields display for technology Actelis");
		}
		
		
		if(technology.equalsIgnoreCase("Atrica")) {
			 
			//Non-termination point
			 editsiteorder_NonterminationPoint(application, nontermination); 
			
			//Protected 
			 editsiteOrder_protected(application, Protected);
			 
			 //Device Name
			 editSiteOrder_deviceName(application, devicename);
			
		 }
		
		if(technology.equalsIgnoreCase("Overture")) {
			
			//Non-termination point
			 editsiteorder_NonterminationPoint(application, nontermination); 
			
			//Protected 
			 editsiteOrder_protected(application, Protected);
			 
		}
		
		
		if(technology.equalsIgnoreCase("Alu")) {
			
			 //Device Name
			 editSiteOrder_deviceName(application, devicename);
			
		}
		
		if((technology.equals("Accedian-1G")) || (technology.equals("Accedian"))) {
			
			//Non-termination point
			 editsiteorder_NonterminationPoint(application, nontermination); 
			
			//Protected 
			 editsiteOrder_protected(application, Protected);
			
		}
		
		if(technology.equalsIgnoreCase("Cyan")) {
			
			//Non-termination point
			 editsiteorder_NonterminationPoint(application, nontermination); 
			
		}
		
		scrolltoend();
		Thread.sleep(3000);
		
		click_commonMethod(application, "OK", "okbutton", xml);
		Thread.sleep(2000);
		
	}
	
	
	public void editSiteOrder_EPN_1G_IVRefPrimary(String application, String performReport, String ProactiveMonitor, String smartmonitor,
			String siteallias, String VLANid, String DCAenabledsite, String cloudserviceprovider, String technology, String nontermination, 
			String Protected, String devicename, String remark, 
			String siteOrderNumber, String IVReference, String CircuitReference, String EPNoffnet, String EPnEosDH)
			throws InterruptedException, DocumentException, IOException {
	
		
	//Site Order Number (Siebel Service ID) 
		editSiteOrder_siteOrderNumber(application, siteOrderNumber);
		
	
	//IV Reference
		editSiteOrder_IVReference(application, IVReference);
	
	//Performance Reporting
		editSiteOrder_Performancereporting(application, performReport);
		
		 
	//Pro active Monitoring 
		editSiteOrder_proactiveMonitoring(application, ProactiveMonitor);

		 
	//Smart Monitoring
		editSiteOrder_smartMonitoring(application, smartmonitor);

		 
	//Site alias
		editsiteorder_sitealias(application, siteallias);
	
		scrolltoend();
		Thread.sleep(3000);
		
	//Remark
		editsiteOrder_remark(application, remark);
		
	//Circuit Reference
		editsiteorder_circuitReference(application, CircuitReference);
		
	//Technology
		editSiteOrder_technology(application,technology);
		 
		if(technology.equalsIgnoreCase("Actelis")) {
			Log.info(" NO additional fields display for technology Actelis");
		}
		
		
		else if(technology.equalsIgnoreCase("Atrica")) {
			 
			//Non-termination point
			 editsiteorder_NonterminationPoint(application, nontermination); 
			
			//Protected 
			 editsiteOrder_protected(application, Protected);
			 
			 //Device Name
			 editSiteOrder_deviceName(application, devicename);
			
		 }
		
		else if(technology.equalsIgnoreCase("Overture")) {
			
			//Non-termination point
			 editsiteorder_NonterminationPoint(application, nontermination); 
			
			//Protected 
			 editsiteOrder_protected(application, Protected);
			 
			//Device Name
			 editSiteOrder_deviceName(application, devicename);
			 
		}
		
		
		else if(technology.equalsIgnoreCase("Alu")) {
			
			 //Device Name
			 editSiteOrder_deviceName(application, devicename);
			
		}
		
		else if((technology.equals("Accedian-1G")) || (technology.equals("Accedian"))) {
			
			//Non-termination point
			 editsiteorder_NonterminationPoint(application, nontermination); 
			
			//Protected 
			 editsiteOrder_protected(application, Protected);
			 
			//Device Name
			 editSiteOrder_deviceName(application, devicename); 
			
		}
		
		else if(technology.equalsIgnoreCase("Cyan")) {
			
			//Non-termination point
			 editsiteorder_NonterminationPoint(application, nontermination); 
			
		}
		
		
		//EPNoffnet
		editcheckbox_commonMethod(application, EPNoffnet , "Addsiteorder_EPNoffnetCheckbox", "EPN Offnet", xml);
			
	 //EPN EOSDH
			editcheckbox_commonMethod(application, EPnEosDH, "Addsiteorder_EPNEOSDHCheckbox", "EPN EOSDH", xml);
			
			scrolltoend();
			Thread.sleep(3000);
			
			click_commonMethod(application, "OK", "okbutton", xml);
			Thread.sleep(2000);
			
	}
	
	
	public void editSiteOrder_HubAndSpoke_10G(String application, String performReport, String ProactiveMonitor, String smartmonitor,
			String siteallias, String VLANid, String DCAenabledsite, String cloudserviceprovider, String technology, String nontermination, String Protected, String devicename, String remark, 
			String siteOrderNumber, String IVReference, String CircuitReference, String offnet)
			throws InterruptedException, DocumentException, IOException {
	
		
	//Site Order Number (Siebel Service ID)
		editSiteOrder_siteOrderNumber(application, siteOrderNumber);
		
	
	//IV Reference
		editSiteOrder_IVReference(application, IVReference);
	
	//Performance Reporting
		editSiteOrder_Performancereporting(application, performReport);
		
		 
	//Pro active Monitoring 
		editSiteOrder_proactiveMonitoring(application, ProactiveMonitor);

		 
	//Smarts Monitoring
		editSiteOrder_smartMonitoring(application, smartmonitor);

		 
	//Site alias
		editsiteorder_sitealias(application, siteallias);
		
	//offnet
		editSiteorder_Offnet(application, offnet);
		
		scrolltoend();
		Thread.sleep(3000);
		 
	//Remark
		editsiteOrder_remark(application, remark);
		
	//Circuit Reference
		editsiteorder_circuitReference(application, CircuitReference);
		
	//Technology
		editSiteOrder_technology(application,technology);
		 
		
	//Non-termination point
			 editsiteorder_NonterminationPoint(application, nontermination); 
			
	//Protected 
			 editsiteOrder_protected(application, Protected);
			 
			 
			 scrolltoend();
				Thread.sleep(3000);
				
				click_commonMethod(application, "OK", "okbutton", xml);
		
	}
	

	public void editSiteOrder_EPN_10G(String application, String performReport, String ProactiveMonitor, String smartmonitor,
			String siteallias, String VLANid, String DCAenabledsite, String cloudserviceprovider, String technology, String nontermination, String Protected, String devicename, String remark, 
			String siteOrderNumber, String IVReference, String CircuitReference, String EPNoffnet)
			throws InterruptedException, DocumentException, IOException {
	
		
	//Site Order Number (Siebel Service ID)
		editSiteOrder_siteOrderNumber(application, siteOrderNumber);
		
	
	//IV Reference
		editSiteOrder_IVReference(application, IVReference);
	
	//Performance Reporting
		editSiteOrder_Performancereporting(application, performReport);
		
		 
	//Pro active Monitoring 
		editSiteOrder_proactiveMonitoring(application, ProactiveMonitor);

		 
	//Smarts Monitoring
		editSiteOrder_smartMonitoring(application, smartmonitor);

		 
	//Site alias
		editsiteorder_sitealias(application, siteallias);
		
		//EPNoffnet
				editcheckbox_commonMethod(application, EPNoffnet , "Addsiteorder_EPNoffnetCheckbox", "EPN Offnet", xml);
		
		scrolltoend();
		Thread.sleep(3000);
		 
	//Remark
		editsiteOrder_remark(application, remark);
		
	//Circuit Reference
		editsiteorder_circuitReference(application, CircuitReference);
		
	//Technology
		editSiteOrder_technology(application,technology);
		 
		
	//Non-termination point
			 editsiteorder_NonterminationPoint(application, nontermination); 
			
	//Protected 
			 editsiteOrder_protected(application, Protected);
			 
			 
			 scrolltoend();
				Thread.sleep(3000);
				
				click_commonMethod(application, "OK", "okbutton", xml);
				Thread.sleep(2000);
		
	}

	
	
	
	public void editSiteOrder_Performancereporting(String application, String performReport) throws InterruptedException, DocumentException {
		
		//Performance Reporting
		boolean perfrmReportAvailability = false;
	try {	
		perfrmReportAvailability=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_performancereporting")).isDisplayed();
	
		if(perfrmReportAvailability) {
		 if(performReport.equalsIgnoreCase("null")) {
			 ExtentTestManager.getTest().log(LogStatus.PASS, "Performance reporting dropdown is not edited");
			 Log.info( "Performance reporting dropdown is not edited");
		 }else {
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_performancereporting_xbutton")));
		Thread.sleep(3000);

		Clickon(getwebelement("//div[label[text()='Performance Reporting']]//div[text()='"+ performReport +"']"));
		
		Thread.sleep(3000);
		Log.info("perform reporting selected");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Edited value for 'Performance reporting' dropdown is: "+performReport);
		Log.info("Edited value for 'Performance reporting' dropdown is: "+performReport);
		
		 }
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Performance Reporting' dropdown is not available under 'Edit Site Order' page");
		}
	}catch(NoSuchElementException e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, " Performance Reporting' dropdown is not available under 'Edit Site Order' page");
		Log.info(" Performance Reporting' dropdown is not available under 'Edit Site Order' page");
	}catch(Exception err) {
		err.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to select value under 'Performance reporting' checkbox ");
		Log.info(" Not able to select value under 'Performance reporting' checkbox ");
	}
	
	}
	
	
	public void editSiteOrder_GCRoloType(String application, String GCRoloType) throws InterruptedException, DocumentException {
		
		boolean GCRoloTypeAvailability = false;
	try {	
		GCRoloTypeAvailability=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_GCRoloTypeDropdown")).isDisplayed();
	
		if(GCRoloTypeAvailability) {
		 if(GCRoloType.equalsIgnoreCase("null")) {
			 ExtentTestManager.getTest().log(LogStatus.PASS, "'GCR OLO Type' dropdown is not edited");
		 }else {
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_GCRoloTypeDropdown_xbutton")));
		Thread.sleep(3000);
		Clickon(getwebelement("//div[text()='" + GCRoloType + "']"));
		Thread.sleep(3000);
		Log.info("'GCR OLO Type' dropdown selected");
		
		String actualvalue=getwebelement("//div[label[text()='GCR OLO Type']]//span").getText();
		ExtentTestManager.getTest().log(LogStatus.PASS, "Edited value for 'GCR OLO Type' dropdown is: "+actualvalue);
		 }
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'GCR OLO Type' dropdown is not available under 'Edit Site Order' page");
		}
		
	}catch(NoSuchElementException e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, " 'GCR OLO Type' dropdown is not available under 'Edit Site Order' page");
		Log.info( " 'GCR OLO Type' dropdown is not available under 'Edit Site Order' page");
	}catch(Exception ee) {
		ee.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, GCRoloType +  " not available under 'GCR OLO type' dropdown");
		Log.info(GCRoloType +  " not available under 'GCR OLO type' dropdown");
	}
	
	}
	
	
public void editSiteOrder_VLANEtherType(String application, String VlanEtherType) throws InterruptedException, DocumentException {
		
		boolean VLANEtherTypeAvailability = false;
	try {	
		VLANEtherTypeAvailability=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_VLANEtherTypeDropdown")).isDisplayed();
	
		if(VLANEtherTypeAvailability) {
		 if(VlanEtherType.equalsIgnoreCase("null")) {
			 ExtentTestManager.getTest().log(LogStatus.PASS, "'VLAN Ether Type' dropdown value is not edited");
		 }else {
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_VLAnEtheryTypeDropdown_xbutton")));
		Thread.sleep(3000);
		Clickon(getwebelement("//div[label[text()='VLAN Ether Type']]//div[text()='"+ VlanEtherType +"']"));
		Thread.sleep(3000);
		Log.info("'VLAN Ether Type' dropdown selected");
		
		String actualValue=getwebelement("//div[label[text()='VLAN Ether Type']]//span").getText();
		ExtentTestManager.getTest().log(LogStatus.PASS, "Edited value for 'VLAN Ether Type' dropdown is: "+actualValue);
		
		 }
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'VLAN Ether Type' dropdown is not available under 'Edit Site Order' page");
		}
	}catch(NoSuchElementException e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, " 'VLAN Ether Type' dropdown is not available under 'Edit Site Order' page");
		Log.info(" 'VLAN Ether Type' dropdown is not available under 'Edit Site Order' page");
	}catch(Exception ee) {
		ee.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, VlanEtherType + " is not selected under 'VLAN Ether type' dropdown");
		Log.info(VlanEtherType + " is not edited under 'VLAN Ether type' dropdown");
	}
	
	}


public void editSiteOrder_primaryVLANEtherType(String application, String primaryVlanEtherType) throws InterruptedException, DocumentException {
	
	boolean primaryVLANEtherTypeAvailability = false;
try {	
	primaryVLANEtherTypeAvailability=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_primaryVLANEtherTypeDropdown")).isDisplayed();


	if(primaryVLANEtherTypeAvailability) {
	 if(primaryVlanEtherType.equalsIgnoreCase("null")) {
		 ExtentTestManager.getTest().log(LogStatus.PASS, "'Primary VLAN Ether Type' dropdown value is not edited");
	 }else {
	Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_primaryVLANEtherTypeDropdown_xbutton")));
	Thread.sleep(3000);
	Clickon(getwebelement("//div[label[text()='Primary VLAN Ether Type']]//div[text()='"+ primaryVlanEtherType +"']"));
	Thread.sleep(3000);
	Log.info("'Primary VLAN Ether Type' dropdown selected");
	
	String actualValue=getwebelement("//div[label[text()='Primary VLAN Ether Type']]//span").getText();
	ExtentTestManager.getTest().log(LogStatus.PASS, "Edited value for 'Primary VLAN Ether Type' dropdown is: "+primaryVlanEtherType);
	
	 }
	}else {
		ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Primary VLAN Ether Type' dropdown is not available under 'Edit Site Order' page");
	}
}catch(NoSuchElementException e) {
	e.printStackTrace();
	ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Primary VLAN Ether Type' dropdown is not available under 'Edit Site Order' page");
	Log.info(" 'Primary VLAN Ether Type' dropdown is not available under 'Edit Site Order' page");
}catch(Exception ee) {
	ee.printStackTrace();
	ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to edit value under 'Primary Vlan Ether type' dropdown");
	Log.info(" Not able to edit value under 'Primary Vlan Ether type' dropdown");
}
}


	

	public void editSiteOrder_technology(String application, String technology) throws InterruptedException {
		
		boolean techValue=false;
		
		try {
		techValue=getwebelement("//div[contains(text(),'"+ technology + "')]").isDisplayed();
		
		if(techValue) {
			
			ExtentTestManager.getTest().log(LogStatus.PASS, " Technology value is displaying as: "+technology +" as expected");
			
		}else {
			String actualValue=getwebelement("//div[div[label[contains(text(),'Technology')]]]/div[2]").getText();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Technology value is not displaying as expected"
					+ "   Actual value displaying is: "+actualValue 
					+"  Expected value for Technology is: "+technology);
		}
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Technology value is not displaying as expected");
			Log.info(" Technology value is not displaying as expected");
		}
	}
	
	
	public void editSiteOrder_siteOrderNumber(String application, String siteOrderNumber) throws InterruptedException {
		
		boolean siteOrderValue=false;
		try {
		siteOrderValue=getwebelement("//div[text()='"+siteOrderNumber +"']").isDisplayed();
		
		if(siteOrderValue) {
			
			ExtentTestManager.getTest().log(LogStatus.PASS, " Site Order Number (Siebel Service ID) value is displaying as: "+siteOrderNumber +" as expected");
			Log.info( " Site Order Number (Siebel Service ID) value is displaying as: "+siteOrderNumber +" as expected");
		}else {
			String actualValue=getwebelement("//div[div[label[contains(text(),'Site Order Number (Siebel Service ID)')]]]/div[2]").getText();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Site Order Number (Siebel Service ID) value is not displaying"
					+ "   Actual value displaying is: "+actualValue 
					+"  Expected value for 'Site Order Number (Siebel Service ID)' is: "+siteOrderNumber);
			
			
			Log.info(" Site Order Number (Siebel Service ID) value is not displaying"
					+ "   Actual value displaying is: "+actualValue 
					+"  Expected value for 'Site Order Number (Siebel Service ID)' is: "+siteOrderNumber);
		}
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Site Order NUmber' value is not displaying as expected");
			Log.info(" 'Site Order NUmber' value is not displaying as expected");
		}catch(Exception err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Site Order NUmber' value is not displaying as expected");
			Log.info(" 'Site Order NUmber' value is not displaying as expected");
		}
		
	}
	
	
	public void editSiteOrder_IVReference(String application, String IVReference) throws InterruptedException {
		
		boolean IVrefValue=false;
		
		try {
			IVrefValue=getwebelement("//div[text()='"+IVReference +"']").isDisplayed();
		
		if(IVrefValue) {
			
			ExtentTestManager.getTest().log(LogStatus.PASS, " IV reference value is displaying as: "+IVReference +" as expected");
			Log.info( " IV reference value is displaying as: "+IVReference +" as expected");
			
		}else {
			String actualValue=getwebelement("//div[div[label[contains(text(),'IV Reference')]]]/div[2]").getText();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " IV Reference) value is not displaying as expected"
					+ "   Actual value displaying is: "+actualValue 
					+"  Expected value for 'IV Reference' is: "+IVReference);
			
			Log.info(" IV Reference) value is not displaying as expected"
					+ "   Actual value displaying is: "+actualValue 
					+"  Expected value for 'IV Reference' is: "+IVReference);
		}
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'IV Reference' value is not dispaying as expected");
			Log.info(" 'IV Reference' value is not dispaying as expected");
		}
	}


	public void editSiteOrder_proactiveMonitoring(String application, String ProactiveMonitor) throws InterruptedException, DocumentException {
		
		boolean proactiveMonitorAvilability=false;
		try {
		proactiveMonitorAvilability=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_proactivemonitoring")).isDisplayed();
		
		if(proactiveMonitorAvilability) {
			ExtentTestManager.getTest().log(LogStatus.PASS, " Proactive Monitoring' dropdown is displaying under 'Edit Site Order' page as expected");
			Log.info(" Proactive Monitoring' dropdown is displaying under 'Edit Site Order' page as expected");
			
		if(ProactiveMonitor.equalsIgnoreCase("null")) {
			 ExtentTestManager.getTest().log(LogStatus.PASS, "Proactive monitoring' dropdown value is not edited");
			 Log.info( "Proactive monitoring' dropdown value is not edited");
		 }else {
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsitorder_proactivemonitoring_xbutton")));
		Thread.sleep(3000);
		
		
		Clickon(getwebelement("//div[label[text()='Proactive Monitoring']]//div[text()='"+ ProactiveMonitor +"']"));
		
		Thread.sleep(3000);
		Log.info("proa ctive monitorin selected");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Edited value for 'Pro active Monitoring' dropdown is: "+ProactiveMonitor);
		Log.info("Edited value for 'Pro active Monitoring' dropdown is: "+ProactiveMonitor);
	 }
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Pro active Monitoring' dropdown is not available under 'Edit Site Order' page");
			Log.info(" Pro active Monitoring' dropdown is not available under 'Edit Site Order' page");
		}
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Pro active Monitoring' dropdown is not available under 'Edit Site Order' page");
			Log.info(" Pro active Monitoring' dropdown is not available under 'Edit Site Order' page");
		}catch(Exception err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to enter value under 'pro active monitoring' dropodwn");
			Log.info(" Not able to enter value under 'pro active monitoring' dropodwn");
		}
	}
	
	
	public void editSiteOrder_smartMonitoring(String application, String smartmonitor) throws InterruptedException, DocumentException {
		
		boolean smartmonitorAvailability=false;
		try {
			smartmonitorAvailability=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_smartmonitoring")).isDisplayed();
		
		
		if(smartmonitorAvailability) {
		
		 if(smartmonitor.equalsIgnoreCase("null")) {
			 ExtentTestManager.getTest().log(LogStatus.PASS, "Smarts Monitoring dropdown value is not edited");
			 Log.info( "Smarts Monitoring dropdown value is not edited");
		 }else {
	    Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_smartmonitoring_xbutton")));
	    Thread.sleep(3000);
	    Clickon(getwebelement("//div[label[text()='Smarts Monitoring']]//div[text()='"+ smartmonitor +"']"));
		Thread.sleep(3000);
	  
		Log.info("smarts monitoring is selected");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Edited value for 'Smarts Monitoring' dropdown is: "+smartmonitor);
		Log.info("Edited value for 'Smarts Monitoring' dropdown is: "+smartmonitor);
		 }
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Smart Monitoring dropdown is not available under 'Edit Site Order' page");
			Log.info("Smart Monitoring dropdown is not available under 'Edit Site Order' page");
		}
		
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Smart Monitoring dropdown is not available under 'Edit Site Order' page");
			Log.info("Smart Monitoring dropdown is not available under 'Edit Site Order' page");
		}catch(Exception err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " NOt able to select value under 'Smart Montoring' checkbox");
			Log.info(" NOt able to select value under 'Smart Montoring' checkbox");
		}
		 
	}
	
	
	public void editSiteOrder_deviceName(String application, String devicename) throws InterruptedException, DocumentException, IOException {
		
		boolean devicenameAvailability=false;
		try {
		
			devicenameAvailability=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Devicenamefield")).isDisplayed();
			
		if(devicenameAvailability) {
			
			if(devicename.equalsIgnoreCase("Null")) {
				
				ExtentTestManager.getTest().log(LogStatus.PASS, " NO changes made for 'Device Name' field");
			}else {
				
				getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Devicenamefield")).clear();
				
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Devicenamefield")), devicename);
				Thread.sleep(3000);
				
				String actualvalue=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Devicenamefield")).getAttribute("value");
				ExtentTestManager.getTest().log(LogStatus.PASS, " Device Name value has been edited and the edited value is: "+ actualvalue);
			}
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Device name text field is not displaying under 'Edit Site Order' page");
		}
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Device name text field is not displaying under 'Edit Site Order' page");
			Log.info(" Device name text field is not displaying under 'Edit Site Order' page");
		}catch(Exception ee) {
			ee.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to edit 'device name' field");
			Log.info( " Not able to edit 'device name' field");
		}
	}
			
	
	public void editSiteOrder_mappingMode(String application, String mappingmode, String portBased, String vlanBased) throws InterruptedException, DocumentException {
		
		boolean mappingModeAvailability=false;
	try {	
		mappingModeAvailability=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_mappingModedropdown")).isDisplayed();
		if(mappingModeAvailability) {
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Mapping Mode' dropdown is displaying in 'Edit Site Order' page as expected");
			Log.info(" 'Mapping Mode' dropdown is displaying in 'Edit Site Order' page as expected");
			
			if(mappingmode.equalsIgnoreCase("null")) {
				ExtentTestManager.getTest().log(LogStatus.PASS, " 'mapping Mode' dropdown is not edited");
				Log.info(" 'mapping Mode' dropdown is not edited");
				
				
			}else {
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_mappingModedropdown_xbutton")));
				Thread.sleep(3000);
				Clickon(getwebelement("//div[text()='"+ mappingmode +"']"));
				Thread.sleep(3000);
				
				String actualValue=getwebelement("//div[label[text()='Mapping Mode']]//span").getText();
				ExtentTestManager.getTest().log(LogStatus.PASS, " 'Mapping mode' dropdown is edited as: "+ actualValue);
				
				if(actualValue.equalsIgnoreCase("Port Based")) {
					
					edittextFields_commonMethod(application, "Port Name" , "portname_textField", portBased);
					
				}else if(actualValue.equalsIgnoreCase("Vlan Based")) {
					
					edittextFields_commonMethod(application, "VLAN Id", "vlanid_textfield", vlanBased);
				}
				
			}
		}else {
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Mapping mode' dropdown is not displaying under 'Edit Site order' page");
		}
	}catch(NoSuchElementException e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Mapping mode' dropdown is not displaying under 'Edit Site order' page");
		Log.info(" 'Mapping mode' dropdown is not displaying under 'Edit Site order' page");
	}catch(Exception ee) {
		ee.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to edit value under 'Mapping Mode' dropdown");
		Log.info(" Not able to edit value under 'Mapping Mode' dropdown");
	}
	}
	
	
	
	public void editsiteOrder_vlanid(String application, String VLANid) throws InterruptedException, DocumentException, IOException {
		
		boolean vlanAvailability = false;
		
	try {	
		vlanAvailability=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Vlanid")).isDisplayed();
	
		if(vlanAvailability) {
		 if(VLANid.equalsIgnoreCase("null")) {
			 ExtentTestManager.getTest().log(LogStatus.PASS, "Vlanid field value is not edited");
			 Log.info("Vlanid field value is not edited");
		 }else {
			 getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Vlanid")).clear();
			 Thread.sleep(3000);
			 
			 SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Vlanid")), VLANid);
			 Thread.sleep(3000);
			 
			String VLANidValue= getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Vlanid")).getAttribute("value");
			 ExtentTestManager.getTest().log(LogStatus.PASS, "Edited value for 'Vlan id' field is: "+ VLANidValue);
			 Log.info( "Edited value for 'Vlan id' field is: "+ VLANidValue);
		 }
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "VLAN Id field is not available under 'Edit Site Order' page");
			Log.info("VLAN Id field is not available under 'Edit Site Order' page");
		}
	}catch(NoSuchElementException e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, "VLAN Id field is not available under 'Edit Site Order' page");
		Log.info("VLAN Id field is not available under 'Edit Site Order' page");
	}catch(Exception ee) {
		ee.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to edit 'VLAn Id' text field");
		Log.info(" not able to edit 'VLAN ID' text field");
	}
	}
	
	
public void editsiteOrder_remark(String application, String remark) throws InterruptedException, DocumentException, IOException {
		
		boolean remarkAvailability = false;
		scrolltoend();
		Thread.sleep(3000);
	try {	
		remarkAvailability=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_remark")).isDisplayed();
	
		if(remarkAvailability) {
		 if(remark.equalsIgnoreCase("null")) {
			 ExtentTestManager.getTest().log(LogStatus.PASS, "Remark text field value is not edited");
		 }else {
			 getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_remark")).clear();
			 Thread.sleep(3000);
			 
			 SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_remark")), remark);
			 Thread.sleep(3000);
			 
			String remarkValue= getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_remark")).getAttribute("value");
			 ExtentTestManager.getTest().log(LogStatus.PASS, "Edited value for 'Remark' field is: "+ remarkValue);
		 }
		 
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Remark text field is not available under 'Edit Site Order' page");
		}
	}catch(NoSuchElementException e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, "Remark text field is not available under 'Edit Site Order' page");
		Log.info("Remark text field is not available under 'Edit Site Order' page");
	}catch(Exception err) {
		err.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to enter value under 'remark' field");
		Log.info(" Not able to enter value under 'remark' field");
	}
	
	}
	
	
	public void editsiteorder_sitealias(String application, String siteallias) throws InterruptedException, DocumentException, IOException {
		boolean siteAliasAvilability= false;
	try {	
		siteAliasAvilability=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_sitealias")).isDisplayed();
	
		if(siteAliasAvilability) {
		 if(siteallias.equalsIgnoreCase("null")) {
			 ExtentTestManager.getTest().log(LogStatus.PASS, "Site Alias field value is not edited");
			 Log.info("Site Alias field value is not edited");
		 }else {
			 getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_sitealias")).clear();
			 Thread.sleep(3000);
			 SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_sitealias")), siteallias);
			 Thread.sleep(3000);
			 String siteAliasvalue=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_sitealias")).getAttribute("value");
			 ExtentTestManager.getTest().log(LogStatus.PASS, "Edited value for 'Site Alias' field is: "+siteAliasvalue);
			 Log.info("Edited value for 'Site Alias' field is: "+siteAliasvalue);
		 }
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Site Alias field is not available under 'Edit Site Order' page");
		}
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Site Alias field is not available under 'Edit Site Order' page");
			Log.info(" Site Alias field is not available under 'Edit Site Order' page");
		}catch(Exception err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to enter value under 'Site Alias' field" );
			Log.info(" Not able to enter value under 'Site Alias' field");
		}
		
	
	}
	
	
	 public void editSiteorder_Offnet(String application, String offnet) throws InterruptedException, DocumentException {
		 
		 boolean offnetAvailability=false;
		 
		 try {
		 offnetAvailability=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_offnetCheckbox")).isDisplayed();
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		 
		if(offnetAvailability) {
			
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Offnet' checkbox is displaying under 'Edit Site ordeer' page as exepected");
		 if(!offnet.equalsIgnoreCase("null")) {
				
				boolean offnetselection=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_offnetCheckbox")).isSelected();
				Thread.sleep(2000);
				
				if (offnet.equalsIgnoreCase("yes")) {

					if(offnetselection) {
						
						ExtentTestManager.getTest().log(LogStatus.PASS, "Offnet checkbox is not edited and it is already Selected while creating");
						
					}else {
						
						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_offnetCheckbox")));
						Log.info("Offnet check box is selected");
						ExtentTestManager.getTest().log(LogStatus.PASS,"Offnet is edited and gets selected as expected");
					}
					
					 
				}

				else if (offnet.equalsIgnoreCase("no")) {
					
					if(offnetselection) {
						
						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_offnetCheckbox")));
						Log.info("Offnet check box is unselected");
						ExtentTestManager.getTest().log(LogStatus.PASS,"offnet is edited and gets unselected");
						
					}else {
						ExtentTestManager.getTest().log(LogStatus.PASS, "Offnet is not edited and it remains unselected");
					}
					
				}
			}else {
				ExtentTestManager.getTest().log(LogStatus.PASS,"No changes made for Offnet chekbox");
			}
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Offnet' checkbox is not available under 'Edit Site Order' page");
		}
	 }
	
	
	public void editsiteorder_circuitReference(String application, String circuitRef) throws InterruptedException, DocumentException, IOException {
		
		boolean circuitRefAvilability= false;
	try {	
		circuitRefAvilability=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_circuitReferenceField")).isDisplayed();

		if(circuitRefAvilability) {
		 if(circuitRef.equalsIgnoreCase("null")) {
			 
			 ExtentTestManager.getTest().log(LogStatus.PASS, "Circuit Reference field value is not edited");
			 
		 }else {
			 getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_circuitReferenceField")).clear();
			 Thread.sleep(3000);
			 SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_circuitReferenceField")), circuitRef);
			 Thread.sleep(3000);
			 String circuitRefvalue=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_circuitReferenceField")).getAttribute("value");
			 ExtentTestManager.getTest().log(LogStatus.PASS, "Edited value for 'Circuit Reference' field is: "+circuitRefvalue);
		 }
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Circuit Reference field is not available under 'Edit Site Order' page");
		}
	}catch(NoSuchElementException e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, " Circuit Reference field is not available under 'Edit Site Order' page");
		Log.info(" Circuit Reference field is not available under 'Edit Site Order' page");
	}catch(Exception err) {
		err.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to enter value under 'Circuit reference' field");
		Log.info(" Not able to enter value under 'Circuit reference' field");
	}
	}
	
	
public void editsiteorder_VLAN(String application, String VLAN) throws InterruptedException, DocumentException, IOException {
		
		boolean VLANAvilability= false;
	try {	
		VLANAvilability=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_VLAN")).isDisplayed();
	
		if(VLANAvilability) {
		 if(VLAN.equalsIgnoreCase("null")) {
			 
			 ExtentTestManager.getTest().log(LogStatus.PASS, "VLAN text field value is not edited");
			 
		 }else {
			 getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_VLAN")).clear();
			 Thread.sleep(3000);
			 SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_VLAN")), VLAN);
			 Thread.sleep(3000);
			 
			 String VLANactualvalue=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_VLAN")).getAttribute("value");
			 ExtentTestManager.getTest().log(LogStatus.PASS, "Edited value for 'VLAN' text field is: "+VLANactualvalue);
		 }
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " VLAN text field is not available under 'Edit Site Order' page");
		}
	}catch(NoSuchElementException e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, " VLAN text field is not available under 'Edit Site Order' page");
		Log.info(" VLAN text field is not available under 'Edit Site Order' page");
	}catch(Exception ee) {
		ee.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to edit value under 'VLAN' text field");
		Log.info(" Not able to edit value under 'VLAN' text field");
	}
	
	}



public void editsiteorder_primaryVLAN(String application, String primaryVLAN) throws InterruptedException, DocumentException, IOException {
	
	boolean primaryVLANAvilability= false;
try {	
	primaryVLANAvilability=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_PrimaryVLAN")).isDisplayed();

	if(primaryVLANAvilability) {
	 if(primaryVLAN.equalsIgnoreCase("null")) {
		 
		 ExtentTestManager.getTest().log(LogStatus.PASS, " Primary VLAN text field value is not edited");
		 
	 }else {
		 getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_PrimaryVLAN")).clear();
		 Thread.sleep(3000);
		 SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_PrimaryVLAN")), primaryVLAN);
		 Thread.sleep(3000);
		 String primaryVLANactualvalue=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_PrimaryVLAN")).getAttribute("value");
		 ExtentTestManager.getTest().log(LogStatus.PASS, "Edited value for 'Primary VLAN' text field is: "+primaryVLANactualvalue);
	 }
	}else {
		ExtentTestManager.getTest().log(LogStatus.FAIL, " Primary VLAN text field is not available under 'Edit Site Order' page");
	}
}catch(NoSuchElementException e) {
	e.printStackTrace();
	ExtentTestManager.getTest().log(LogStatus.FAIL, " Primary VLAN text field is not available under 'Edit Site Order' page");
	Log.info(" Primary VLAN text field is not available under 'Edit Site Order' page");
}catch(Exception ee) {
	ee.printStackTrace();
	ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to edit 'Primary VLAn Type' text field");
	Log.info( " Not able to edit 'Primary VLAn Type' text field");
}

}
                  
	
	public void editesiteOrder_DcaEnabled(String application, String DCAenabledsite, String cloudserviceprovider) 
			throws InterruptedException, DocumentException {
		


		boolean DCAavailability = false;

		try {
			DCAavailability = getwebelement(
					xml.getlocator("//locators/" + application + "/Addsiteorder_DCAenabledsitecheckbox")).isDisplayed();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (DCAavailability) {

			if (!DCAenabledsite.equalsIgnoreCase("null")) {

				boolean dcaenabled = getwebelement(
						xml.getlocator("//locators/" + application + "/Addsiteorder_DCAenabledsitecheckbox"))
								.isSelected();
				Thread.sleep(2000);

				if (DCAenabledsite.equalsIgnoreCase("yes")) {

					if (dcaenabled) {

						ExtentTestManager.getTest().log(LogStatus.PASS,
								"DCA Enabled Site is already Selected while creating");

						if (cloudserviceprovider.equalsIgnoreCase("null")) {

							ExtentTestManager.getTest().log(LogStatus.PASS, "No changes made to Cloud Service Provider");

						} else {

							addDropdownValues_commonMethod(application, "Cloud Service Provider", "Addsiteorder_cloudserviceProvider", cloudserviceprovider, xml);
						}

					} else {

						Clickon(getwebelement(
								xml.getlocator("//locators/" + application + "/Addsiteorder_DCAenabledsitecheckbox")));
						Log.info("DCA Enabled Site check box is selected");
						ExtentTestManager.getTest().log(LogStatus.PASS, "DCA Enabled Site checkbox is selected");

						if (cloudserviceprovider.equalsIgnoreCase("null")) {

							ExtentTestManager.getTest().log(LogStatus.PASS, "No changes made to Cloud Service Provider");

						} else {

							addDropdownValues_commonMethod(application, "Cloud Service Provider", "Addsiteorder_cloudserviceProvider", cloudserviceprovider, xml);
						}
					}

				}

				else if (DCAenabledsite.equalsIgnoreCase("no")) {

					if (dcaenabled) {

						Clickon(getwebelement(
								xml.getlocator("//locators/" + application + "/Addsiteorder_DCAenabledsitecheckbox")));
						Log.info("DCA Enabled Site check box is unselected");
						ExtentTestManager.getTest().log(LogStatus.PASS, "DCA Enabled Site is unselected as Expected");

					} else {
						ExtentTestManager.getTest().log(LogStatus.PASS,
								"DCA Enabled Site was not selected during service creation and it remains unselected as expected");
					}

				}
			} else {
				ExtentTestManager.getTest().log(LogStatus.PASS, "No changes made for DCAenabled site chekbox as expected");
			}
		} else {
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					"DCA Enabled Site checkbox is not displaying under 'Edit Site Order' page");
		}
	
	}
	
	
	public void editsiteorder_NonterminationPoint(String application, String nontermination) throws InterruptedException, DocumentException {
		
		boolean NonTerminationPointAvailability=false;
		
		try {
			NonTerminationPointAvailability=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_nonterminationpoint")).isDisplayed();
		
		if(NonTerminationPointAvailability) {
		
      if(!nontermination.equalsIgnoreCase("null")) {
			
    	  boolean nonterminatepoint=false;
    	 try { 
    	  nonterminatepoint=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_nonterminationpoint")).isSelected();
    	 }catch(Exception e) {
    		 e.printStackTrace();
    	 }
			Thread.sleep(2000);
			
			if (nontermination.equalsIgnoreCase("yes")) {

				if(nonterminatepoint) {
					
					ExtentTestManager.getTest().log(LogStatus.PASS, " 'Non-Termination point' checkbox is already Selected while creating");
					
				}else {
					
					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_nonterminationpoint")));
					Log.info("'Non-Termination point' check box is selected");
					ExtentTestManager.getTest().log(LogStatus.PASS,"'Non-Termination point' is edited and gets selected");
				}
				
				
			}

			else if (nontermination.equalsIgnoreCase("no")) {
				
				if(nonterminatepoint) {
					
					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_nonterminationpoint")));
					Log.info("'Non-Termination point' check box is unselected");
					ExtentTestManager.getTest().log(LogStatus.PASS,"'Non-Termination point' is edited and gets unselected");
					
				}else {
					ExtentTestManager.getTest().log(LogStatus.PASS, "'Non-Termination point' was not selected during service creation and it remains unselected as expected");
				}
				
			}
		}else {
			ExtentTestManager.getTest().log(LogStatus.PASS,"No changes made for 'Non-Termination point' chekbox as expected");
		}
	}else {
		ExtentTestManager.getTest().log(LogStatus.FAIL, " Non-Termination Point checkbox is not available under 'Edit Site order' page");
	}
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Non-Termination Point checkbox is not available under 'Edit Site order' page");
			Log.info(" Non-Termination Point checkbox is not available under 'Edit Site order' page");
		}catch(Exception err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to click on 'Non-termination point' checkbox ");
			Log.info(" Not able to click on 'Non-termination point' checkbox ");
		}
	}
	
	
	
	public void editsiteOrder_protected(String application, String Protected) throws InterruptedException, DocumentException {
		
		boolean prtctedAvailability= false;
		
		try {
			prtctedAvailability=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_protected")).isDisplayed();
		
		if(prtctedAvailability) {
          if(!Protected.equalsIgnoreCase("null")) {
				
        	  boolean prtcted=false;
        	 try { 
        	  prtcted=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_protected")).isSelected();
        	 }catch(Exception e) {
        		 e.printStackTrace();
        	 }
				Thread.sleep(2000);
				
				if (Protected.equalsIgnoreCase("yes")) {
					if(prtcted) {
						ExtentTestManager.getTest().log(LogStatus.PASS, " 'Protected' checkbox is already Selected while creating");
					}else {
						
						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_protected")));
						Log.info("'Non-Termination point' check box is selected");
						ExtentTestManager.getTest().log(LogStatus.PASS,"'Protected' is edited and gets selected");
					}
				}

				else if (Protected.equalsIgnoreCase("no")) {
					if(prtcted) {
						
						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_protected")));
						Log.info("'Non-Termination point' check box is unselected");
						ExtentTestManager.getTest().log(LogStatus.PASS,"'Protected' is edited and gets unselected");
						
					}else {
						ExtentTestManager.getTest().log(LogStatus.PASS, "'Protected' was not selected during service creation and it remains unselected as expected");
					}
				}
			}else {
				ExtentTestManager.getTest().log(LogStatus.PASS,"No changes made for 'Protected' chekbox as expected");
			}
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Protected checkbox is not displaying under 'Edit Site order' page");
		}
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Protected checkbox is not displaying under 'Edit Site order' page");
			Log.info(" Protected checkbox is not displaying under 'Edit Site order' page");
		}catch(Exception err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to select 'protected' checkbox");
			Log.info(" Not able to select 'protected' checkbox");
		
		}
		
	}
	
	
	public void editsiteOrder_cloudserviceprovider(String application, String cloudserviceprovider) throws InterruptedException, DocumentException {
		if(cloudserviceprovider.equalsIgnoreCase("null")) {
			
			ExtentTestManager.getTest().log(LogStatus.PASS,"No changes made to Cloud Service Provider");
			
		}else {
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/notigymanagement_xbutton")));
			Thread.sleep(4000);
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_cloudserviceProvider")));
			Clickon(getwebelement("//div[text()='" + cloudserviceprovider + "']"));
			ExtentTestManager.getTest().log(LogStatus.PASS,"Edited value for 'Cloud Service provider' dropdown is: "+cloudserviceprovider);
		}
	}

	public void Enteraddsiteorder(String application) throws InterruptedException, DocumentException {

		Thread.sleep(3000);
		
		scrolltoend();
		Thread.sleep(3000);

		click_commonMethod(application, "Action", "Actiondropdown_siteorder", xml);
		Thread.sleep(5000);

		click_commonMethod(application, "Site Order link", "Addsiteorderlink", xml);
	}

	public void clickonEditwithoutselectingrow(String application)
			throws InterruptedException, DocumentException, IOException {

		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(3000);

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Actiondropdown_siteorder")));
		Thread.sleep(2000);

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/EditsideOrderlink")));
		Thread.sleep(2000);

		String popupmessage = Gettext(getwebelement("//div[text()='Please select a row to edit']"));
		Log.info("Edit popup message before selecting row: " + popupmessage);
		Log.info("Edit popup message before selecting row: \"+popupmessage");

		Clickon(getwebelement("//div[@class='modal-header']//div[contains(text(),'�')]"));

	}

	public void clickondeletewithoutselectingrow(String application)
			throws InterruptedException, DocumentException, IOException {

		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Backbutton")));
		Thread.sleep(5000);

		Log.info("Deleting site order without selecting row");
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(3000);

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Actiondropdown_siteorder")));
		Thread.sleep(2000);

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/deletesiteorderlink")));
		Thread.sleep(2000);

		String popupmessage = Gettext(getwebelement("//div[text()='Please select a row to delete']"));
		Log.info("Delete popup message before selecting row: " + popupmessage);
		Log.info("Delete popup message before selecting row: \"+popupmessage");

		Clickon(getwebelement("//div[@class='modal-header']//div[contains(text(),'�')]"));

	}

	public void deletesiteorderdetails(String application) throws InterruptedException, DocumentException, IOException {

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/deletesiteorderlink")));
		Thread.sleep(2000);

		String deletemessage = Gettext(
				getwebelement("//div[text()='Are you sure that you want to delete this item?']"));
		Log.info("delete popup displays message as : " + deletemessage);
		Log.info("delete popup displays message as : \"+deletemessage");

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Deletesiteorderrbutton")));
		Thread.sleep(2000);

	}

	public void clickonviewewithoutselectingrow(String application)
			throws InterruptedException, DocumentException, IOException {

//		Log.info("have to navigate back");
//		driver.navigate().back();
//		Thread.sleep(3000);
//		Log.info("got navigated back");

		Log.info("View site order without selecting row");
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(3000);

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Actiondropdown_siteorder")));
		Thread.sleep(2000);

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/viewsiteorderlink")));
		Thread.sleep(2000);

		String popupmessage = Gettext(getwebelement("//div[text()='Please select a row to view']"));
		Log.info("popup message before selecting row for viewing occurs as: " + popupmessage);
		Log.info(" popup message before selecting row for viewing occcurs as: " + popupmessage);

		Clickon(getwebelement("//div[@class='modal-header']//div[contains(text(),'�')]"));

	}

	public void viewsiteorderlink(String application) throws InterruptedException, DocumentException, IOException {

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/viewsiteorderlink")));
		Thread.sleep(2000);
		Log.info("Entered View site order page");
		
		ExtentTestManager.getTest().log(LogStatus.PASS, "Entered view site order page");

	}

	public void verifyEditSiteOrder(String application, String country, String city, String CSR_Name, String site,
			String performReport, String ProactiveMonitor, String smartmonitor, String technology, String siteallias,
			String VLANid, String DCAenabledsite, String cloudserviceprovider, String sitevalue)
			throws InterruptedException, DocumentException, IOException {
    try {

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/EditsideOrderlink")));
		Thread.sleep(3000);

		Log.info("Entered edit siteorder page");

		String fetchedvalue_country = Gettext(
				getwebelement(xml.getlocator("//locators/" + application + "/Editsiteorder_country")));
		sa.assertEquals(fetchedvalue_country, country, "Country field is not displaying same Entered value while creating");

		Log.info("country value is: " + fetchedvalue_country);

		String fetchedvalue_city = Gettext(
				getwebelement(xml.getlocator("//locators/" + application + "/Editsiteorder_city")));
		sa.assertEquals(fetchedvalue_city, city, "City field is not displaying same Entered value while creating");

		Log.info("city value is: " + fetchedvalue_city);

		String fetchedvalue_csrname = Gettext(
				getwebelement(xml.getlocator("//locators/" + application + "/Editsiteorder_CSRname")));
		sa.assertEquals(fetchedvalue_csrname, CSR_Name,
				"CSR name field isnot  displaying same Entered value while creating");

//		  String fetchedvalue_proactivemonitorin=Gettext(getwebelement("//div[label[contains(text(),'Procative Monitoring')]]//span[contains(text(),'"+ ProactiveMonitor +"')]"));

		String fetchedvalue_proactivemonitorin = Gettext(
				getwebelement(xml.getlocator("//locators/" + application + "/Editsiteorder_proactivemonitor")));
		sa.assertEquals(fetchedvalue_proactivemonitorin, ProactiveMonitor,
				"Pro active monitoring dropdowmn is not displaying same Entered value while creating");

		Log.info("proactive monitroing value is: " + fetchedvalue_proactivemonitorin);

//		  String fetchedvalue_performreporting=Gettext(getwebelement("//div[label[contains(text(),'Performance Reporting')]]//span[contains(text(),'"+ performReport +"')]"));

		String fetchedvalue_performreporting = Gettext(
				getwebelement(xml.getlocator("//locators/" + application + "/Editsiteorder_performreport")));
		sa.assertEquals(fetchedvalue_performreporting, performReport,
				"Perormance reporting dropdown is not displaying same Entered value while creating");

//		  String fetchedvalue_smartsmonitor=Gettext(getwebelement("//div[label[contains(text(),'Smarts Monitoring')]]//span[contains(text(),'"+ smartmonitor +"')]"));

		String fetchedvalue_smartsmonitor = Gettext(
				getwebelement(xml.getlocator("//locators/" + application + "/Editsiteorder_smartmonitor")));
		sa.assertEquals(fetchedvalue_smartsmonitor, smartmonitor,
				"smarts monitoring dropdown is not displaying same Entered value while creating");

		String fetchedvalue_technology = Gettext(
				getwebelement(xml.getlocator("//locators/" + application + "/Editsiteorder_technology")));
		sa.assertEquals(fetchedvalue_technology, technology,
				"Technology field is not displaying same Entered value while creating");
		Log.info("technology is: " + fetchedvalue_technology);

		String fetchedvalue_sitealias = Getattribute(
				getwebelement(xml.getlocator("//locators/" + application + "/Editsiteorder_sitealias")), "value");
		sa.assertEquals(fetchedvalue_sitealias, siteallias,
				"Site alias field is not displaying same Entered value while creating");

		Log.info("site alias value: " + fetchedvalue_sitealias);

		String fetchedvalue_vlanid = Getattribute(
				getwebelement(xml.getlocator("//locators/" + application + "/Editsiteorder_VLANid")), "value");
		sa.assertEquals(fetchedvalue_vlanid, VLANid, "VLAN id field is not displaying same Entered value while creating");

		boolean fetchedvalue_DCAenabledsite = getwebelement(
				xml.getlocator("//locators/" + application + "/Editsiteorder_DCAenabledsite")).isSelected();
		sa.assertFalse(fetchedvalue_DCAenabledsite, "DCA enabled is not selected as expected");
		Log.info("DCA enables is: " + fetchedvalue_DCAenabledsite);

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/cancelButton")));
		Thread.sleep(3000);
		
		sa.assertAll();
    }catch(AssertionError e) {
    	e.printStackTrace();
    }

	}

	public void verifyAddsiteorderFields(String application, String interfaceSpeed, String VPNtopology, String circuitType,
			String offnetSelection,String EPNoffnet,String EPNEOSDHselection) throws InterruptedException, DocumentException, IOException {
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying 'add Site Order' fields");
		
		if( (VPNtopology.equals("Point-to-Point")) && (circuitType.equals("Default"))) {
			
			verifySiteOrderForPoint_to_point(application, interfaceSpeed);
			
		}
		if( (VPNtopology.equals("Point-to-Point")) && (circuitType.equals("Extended Circuit"))) {
			
			verifySiteOrderForPoint_to_point_ExtendedCircuit(application, interfaceSpeed);
			
		}
		
		else if(VPNtopology.equals("Hub&Spoke")) {
			
			if(offnetSelection.equalsIgnoreCase("No")) {
			
			verifySiteOrderForHubAndSpoke(application, interfaceSpeed);
		}
			
			else if(offnetSelection.equalsIgnoreCase("Yes")) {
				
				verifySiteOrderForHubAndSpoke_offnetSelected(application, interfaceSpeed);
				
			}
			
			
		}else if(VPNtopology.equals("E-PN (Any-to-Any)")) {
			
			if(EPNEOSDHselection.equalsIgnoreCase("No")) {
			
			verifySiteOrderForE_PN(application, interfaceSpeed);
			
			}
			else if(EPNEOSDHselection.equalsIgnoreCase("yes")) {
				
				verifySiteOrderForEPN_EOSDHselected(application, interfaceSpeed);
				
				}
		}
	}
	
	
	public void addsiteorder(String application, String interfaceSpeed, String VPNtopology, String circuitType,
			String country, String city, String CSR_Name, String site,
			String performReport, String ProactiveMonitor, String smartmonitor, String technology, String siteallias,
			String VLANid, String DCAenabledsite, String cloudserviceprovider, String sitevalue, String remark,
			String xngcityname, String xngcitycode,String devicename, String nonterminatepoinr, String Protected, String newcityselection, String existingcityselection,
			String existingsiteselection, String newsiteselection,	String siteOrderNumber, String circuitref, String offnetSelection, String IVReference,
  			String GCRolo, String Vlan, String Vlanether, String primaryVlan, String primaryVlanether,String EPNoffnet,String EPNEOSDH,
  			String mappingmode, String portBased, String vlanBased, String sitePreferenceType) throws InterruptedException, DocumentException, IOException {
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "create Site Order");
		
		scrollToTop();
		Thread.sleep(5000);
		 
		if( (VPNtopology.equals("Point-to-Point")) && (circuitType.equals("Default"))) {
			
			addSiteOrderValues_point2point(application, interfaceSpeed, country,  city,  CSR_Name,  site,
					 performReport,  ProactiveMonitor,  smartmonitor,  technology,  siteallias,
					 VLANid,  DCAenabledsite,  cloudserviceprovider,  sitevalue,  remark,
					 xngcityname,  xngcitycode, devicename,  nonterminatepoinr,  Protected,  newcityselection,  existingcityselection,
					 existingsiteselection,  newsiteselection);
			
		}
		else if( (VPNtopology.equals("Point-to-Point")) && (circuitType.equals("Extended Circuit"))) {
			
			addSiteOrderValues_point2point_extendedCircuit(application, interfaceSpeed, country,  city,  CSR_Name,  site,
					 performReport,  ProactiveMonitor,  smartmonitor,  technology,  siteallias,
					 VLANid,  DCAenabledsite,  cloudserviceprovider,  sitevalue,  remark,
					 xngcityname,  xngcitycode, devicename,  nonterminatepoinr,  Protected,  newcityselection,  existingcityselection,
					 existingsiteselection,  newsiteselection, sitePreferenceType);
			
		}
		else if(VPNtopology.equals("Hub&Spoke")) {
			
			if(offnetSelection.equalsIgnoreCase("No")) {
			
			addSiteOrderValues_HubAndSPoke(application, interfaceSpeed, country, city, CSR_Name, newsiteselection, 
					performReport, ProactiveMonitor, smartmonitor, technology, siteallias, VLANid, DCAenabledsite,cloudserviceprovider, sitevalue, 
					remark, xngcityname, xngcitycode, devicename, nonterminatepoinr, Protected, newcityselection,existingcityselection, existingsiteselection, 
					newsiteselection, siteOrderNumber, circuitref, offnetSelection, IVReference,
		  			GCRolo, Vlan, Vlanether, primaryVlan, primaryVlanether);
			
			}
			
			if(offnetSelection.equalsIgnoreCase("Yes")) {
				
				addSiteOrderValues_HubAndSPoke_OffnetSelected(application, interfaceSpeed, country, city, CSR_Name, newsiteselection, 
						performReport, ProactiveMonitor, smartmonitor, technology, siteallias, VLANid, DCAenabledsite,cloudserviceprovider, sitevalue, 
						remark, xngcityname, xngcitycode, devicename, nonterminatepoinr, Protected, newcityselection,existingcityselection, existingsiteselection, 
						newsiteselection, siteOrderNumber, circuitref, offnetSelection, IVReference,
			  			GCRolo, Vlan, Vlanether, primaryVlan, primaryVlanether);
				}
			
			
		}else if(VPNtopology.equals("E-PN (Any-to-Any)")) {
			
			addSiteOrderValues_EPN(application, interfaceSpeed, country, city, CSR_Name, newsiteselection, 
					performReport, ProactiveMonitor, smartmonitor, technology, siteallias, VLANid, DCAenabledsite,cloudserviceprovider, sitevalue, 
					remark, xngcityname, xngcitycode, devicename, nonterminatepoinr, Protected, newcityselection,existingcityselection, existingsiteselection, 
					newsiteselection, siteOrderNumber, circuitref, offnetSelection, IVReference,
		  			GCRolo, Vlan, Vlanether, EPNoffnet, EPNEOSDH, mappingmode, portBased, vlanBased);
			
		}
		
	}
	


public void verifySiteOrderForPoint_to_point(String application, String interfaceSpeed) throws InterruptedException, DocumentException, IOException{
		
		ExtentTestManager.getTest().log(LogStatus.INFO, " Site order functions will be performed for 'VPN Topology' --> Point to Point");
		
		try {
			scrolltoend();
			Thread.sleep(2000);
				click_commonMethod(application, "OK", "okbutton", xml);
				Thread.sleep(3000);
					
				scrollToTop();
				Thread.sleep(2000);
				//Country Error message	
					warningMessage_commonMethod(application, "Addsiteorder_countryerrmsg" , "Country", xml);
					
				//City Error message	
					warningMessage_commonMethod(application, "Addsiteorder_devciexngCityErrmsg", "City", xml);
					
				//CSR name Error message	
					warningMessage_commonMethod(application, "Addsiteorder_csrnameErrmsg" , "CSR Name", xml);
					
				//Technology Error message	
					warningMessage_commonMethod(application, "Addsitieorder_technologyErrmsg ", "Technology", xml);
				
		scrollToTop();
		Thread.sleep(3000);
					
		//Validate Country dropdown
				Log.info("validate Country dropdown");
				validateCountry_AddSiteorder(application);
				
					
		//Validate City Fields
				Log.info("Validate city fields");
				validateCity_AddSiteOrder(application);
				
		//Validate Site/CSR field
				Log.info("validate Site Fields");
				validateSite_AddSiteOrder(application);
	
				scrolltoend();
				Thread.sleep(3000);
				
		// Validate performance reporting dropdown
				Log.info("validate performance reporting checkbox");
				validatePerformancereporting_AddSiteOrder(application);
				
				
		//validate proactive Monitoring dropdown
				Log.info("validate proactive monitoring checkbox");
				validateProactiveMonitoring_AddSiteOrder(application);
				
				
		//Validate Smarts monitoring dropdown
				Log.info("validate Smarts monitoring checkbox");
				validateSmartsMOnitoring_AddSiteOrder(application);
				
				
		//Validate Site Alias field
				Log.info("validate Site Alias fields");
				validateSiteAlias_AddSiteOrder(application);
				
		
		//Validate VLAN Id field
				Log.info("validate VLAn Id fields");
				validateVlanID_AddSiteOrder(application);
				
				
		//Validate DCA Enabled Site and Cloud Service Provider dropdown
				Log.info("validate DCA enabled site checkbox");
				valiadateDCAEnabledsite_AddSieOrder(application);
				
		
		//Verify Remark field
				Log.info("validate Remark fields");
				validateRemark_AddSiteOrder(application);
				
	if(interfaceSpeed.equals("1GigE"))	{
		
		technologyDropdownFor1GigE(application);
	}	
			
	else if(interfaceSpeed.equals("10GigE"))	{
		
		technologyDropdownFor10GigE(application);
	}
			
	//Validate OK button
		OKbutton_AddSiteOrder(application);
				
	//Validate Cancel button
		cancelbutton_AddSiteOrder(application);

							Thread.sleep(3000);
							scrolltoend();
							Thread.sleep(3000);
		click_commonMethod(application, "Cancel", "Addsiteorder_cancel", xml);
		
		Thread.sleep(3000);
		
		sa.assertAll();
		}catch(AssertionError e) {
		   e.printStackTrace();
			}
	}

	
public void verifySiteOrderForPoint_to_point_ExtendedCircuit(String application, String interfaceSpeed) throws InterruptedException, DocumentException, IOException{
	
	ExtentTestManager.getTest().log(LogStatus.INFO, " Site order functions will be performed for 'VPN Topology' --> Point to Point");
	
	try {
		scrolltoend();
		Thread.sleep(2000);
			click_commonMethod(application, "OK", "okbutton", xml);
			Thread.sleep(3000);
				
			scrollToTop();
			Thread.sleep(2000);
			//Country Error message	
				warningMessage_commonMethod(application, "Addsiteorder_countryerrmsg" , "Country", xml);
				
			//City Error message	
				warningMessage_commonMethod(application, "Addsiteorder_devciexngCityErrmsg", "City", xml);
				
			//CSR name Error message	
				warningMessage_commonMethod(application, "Addsiteorder_csrnameErrmsg" , "CSR Name", xml);
				
			//Technology Error message	
				warningMessage_commonMethod(application, "Addsitieorder_technologyErrmsg ", "Technology", xml);
			
	scrollToTop();
	Thread.sleep(3000);
				
	//Validate Country dropdown
			Log.info("validate Country dropdown");
			validateCountry_AddSiteorder(application);
			
				
	//Validate City Fields
			Log.info("Validate city fields");
			validateCity_AddSiteOrder(application);
			
	//Validate Site/CSR field
			Log.info("validate Site Fields");
			validateSite_AddSiteOrder(application);

			scrolltoend();
			Thread.sleep(3000);
			
	// Validate performance reporting dropdown
			Log.info("validate performance reporting checkbox");
			validatePerformancereporting_AddSiteOrder(application);
			
			
	//validate proactive Monitoring dropdown
			Log.info("validate proactive monitoring checkbox");
			validateProactiveMonitoring_AddSiteOrder(application);
			
			
	//Validate Smarts monitoring dropdown
			Log.info("validate Smarts monitoring checkbox");
			validateSmartsMOnitoring_AddSiteOrder(application);
			
			
	//Validate Site Alias field
			Log.info("validate Site Alias fields");
			validateSiteAlias_AddSiteOrder(application);
			
	
	//Validate VLAN Id field
			Log.info("validate VLAn Id fields");
			validateVlanID_AddSiteOrder(application);
			
			
	//Validate DCA Enabled Site and Cloud Service Provider dropdown
			Log.info("validate DCA enabled site checkbox");
			valiadateDCAEnabledsite_AddSieOrder(application);
			
	
	//Verify Remark field
			Log.info("validate Remark fields");
			validateRemark_AddSiteOrder(application);
			
if(interfaceSpeed.equals("1GigE"))	{
	
	technologyDropdownFor1GigE(application);
}	
		
else if(interfaceSpeed.equals("10GigE"))	{
	
	technologyDropdownFor10GigE(application);
}
		
//Validate OK button
	OKbutton_AddSiteOrder(application);
			
//Validate Cancel button
	cancelbutton_AddSiteOrder(application);

						Thread.sleep(1000);
						scrolltoend();
						Thread.sleep(1000);
	click_commonMethod(application, "Cancel", "Addsiteorder_cancel", xml);
	
	Thread.sleep(1000);
	    waitforPagetobeenable();
	
	sa.assertAll();
	}catch(AssertionError e) {
	   e.printStackTrace();
		}
}

	
	
public void verifySiteOrderForHubAndSpoke(String application, String interfaceSpeed) throws InterruptedException, DocumentException, IOException{
		
	ExtentTestManager.getTest().log(LogStatus.INFO, " Site order functions will be performed for 'VPN Topology' --> Hub&Spoke");

		try {
				
				String[] IVrefrence = { "Primary", "Access" };
				
				scrolltoend();
				Thread.sleep(2000);

				click_commonMethod(application, "OK", "obutton_spanTag", xml);
				Thread.sleep(2000);
				
				//Circuit Reference Error Message
				warningMessage_commonMethod(application, "Addsiteorder_circuitReferenceErrmsg", "Circuit Reference", xml);
					
				//IV Reference Error Messages
				warningMessage_commonMethod(application, "Addsiteorder_IVReferenceErrmsg", "IV Reference", xml);
					
				//Technology Error message	
					warningMessage_commonMethod(application, "Addsitieorder_technologyErrmsg" , "Technology", xml);
					
				//CSR name Error message	
					warningMessage_commonMethod(application, "Addsiteorder_csrnameErrmsg" , "Physical Site: CSR Name", xml);
					
			scrollToTop();
			Thread.sleep(3000);
			
				//Site Order Number Error Message	
					warningMessage_commonMethod(application, "Addsiteorder_sitOrderNumberErrmsg" , "Site Order Number", xml);
					
				//Country Error message	
					warningMessage_commonMethod(application, "Addsiteorder_countryerrmsg", "Country", xml);
					
				//City Error message	
					warningMessage_commonMethod(application, "Addsiteorder_devciexngCityErrmsg", "City", xml);
					
					
					//Validate site order Number Field
					 		validatesiteOrderNumber_AddSiteOrder(application);

					
					//Validate Country dropdown
							validateCountry_AddSiteorder(application);
							
								
					//Validate City Fields
							validateCity_AddSiteOrder(application);
							
					//Validate Site/CSR field
							validateSite_AddSiteOrder(application);
							
				scrolltoend();
				Thread.sleep(3000);
				
					//Validate performance reporting dropdown
							validatePerformancereporting_AddSiteOrder(application);
							
							
					//validate proactive Monitoring dropdown
							validateProactiveMonitoring_AddSiteOrder(application);
							
							
					//Validate Smarts monitoring dropdown
							validateSmartsMOnitoring_AddSiteOrder(application);
							
						
					//Validate IV Reference dropdown
							validateIVReference_AddSiteorder(application);

					//Validate Circuit reference Field
							validateCircuitreference_AddSiteOrder(application);

					//Spoke ID
							validatespokeId_AddSiteOrder(application);

							
					//Validate Site Alias field
							validateSiteAlias_AddSiteOrder(application);

									
					//Verify Remark field
							validateRemark_AddSiteOrder(application);
							
					//Validate Offnet checkbox
							validateoffnet_AddSiteOrder(application);
					
							scrolltoend();
							Thread.sleep(3000);
		
	if( (interfaceSpeed.equals("1GigE")))	{
		
		for(int i=0; i<IVrefrence.length; i++) {
			if(IVrefrence[i].equals("Primary")) {
				
				
				clickOnBankPage();
				Thread.sleep(2000);
				
				scrolltoend();
				Thread.sleep(1000);
				
				click_commonMethod(application, "IV Reference", "Addsiteorder_IVreferencedropdown", xml);
				
				click_commonMethod(application, "Primary", "Addsiteorder_IVreference_Primary", xml);
				
				technologyDropdownFor1GigE_HubAndSpoke_Primary(application);
				
			}
			
			else if(IVrefrence[i].equals("Access")) {
				
				
				clickOnBankPage();
				Thread.sleep(2000);
				
				scrolltoend();
				Thread.sleep(1000);
				
				click_commonMethod(application, "IV Reference", "Addsiteorder_IVreferencedropdown", xml);
				
				click_commonMethod(application, "Access", "Addsiteorder_IVreference_Access", xml);
				
				technologyDropdownFor1GigE_HubAndSpoke_Access(application);
				
			}
		}
	}	
			
	else if(interfaceSpeed.equals("10GigE"))	{
		
		for(int i=0; i<IVrefrence.length; i++) {
			if(IVrefrence[i].equals("Primary")) {
				
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_IVreferencedropdown")));
				Thread.sleep(3000);
				
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_IVreference_Primary")));
				Thread.sleep(3000);
				
				technologyDropdownFor10GigE_HubAndSpoke_primary(application);
				
			}
			
			else if(IVrefrence[i].equals("Access")) {
				
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_IVreferencedropdown")));
				Thread.sleep(3000);
				
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_IVreference_Access")));
				Thread.sleep(3000);
				
				technologyDropdownFor10GigE_HubAndSpoke_Access(application);
				
			}
		}
	}
		
	scrolltoend();
	Thread.sleep(3000);

	//Validate OK button
				OKbutton_AddSiteOrder(application);

		//validate cancel button
				cancelbutton_AddSiteOrder(application);

		Thread.sleep(3000);

		
click_commonMethod(application, "cancel", "Addsiteorder_cancel", xml);
Thread.sleep(3000);

sa.assertAll();

		}catch(AssertionError e) {
		   e.printStackTrace();
			}
	
	}


public void verifySiteOrderForHubAndSpoke_offnetSelected(String application, String interfaceSpeed) throws InterruptedException, DocumentException, IOException{
	
	ExtentTestManager.getTest().log(LogStatus.INFO, " Site order functions will be performed for 'VPN Topology' --> Hub&Spoke");

		try {
				
				String[] IVrefrence = { "Primary", "Access" };

				scrolltoend();
				Thread.sleep(2000);
				
				click_commonMethod(application, "OK", "obutton_spanTag", xml);
				Thread.sleep(2000);
				
				//Circuit Reference Error Message
				warningMessage_commonMethod(application, "Addsiteorder_circuitReferenceErrmsg", "Circuit Reference", xml);	
				
				//IV Reference Error Messages
				warningMessage_commonMethod(application, "Addsiteorder_IVReferenceErrmsg", "IV Reference", xml);
					
				//CSR name Error message	
					warningMessage_commonMethod(application, "Addsiteorder_csrnameErrmsg", "CSR Name", xml);
					
				//Technology Error message	
					warningMessage_commonMethod(application, "Addsitieorder_technologyErrmsg", "Technology", xml);
					
			scrollToTop();
			Thread.sleep(3000);
			
				//Site Order Number Error Message	
				warningMessage_commonMethod(application, "Addsiteorder_sitOrderNumberErrmsg", "Site Order Number", xml);	
					
				//Country Error message	
				warningMessage_commonMethod(application, "Addsiteorder_countryerrmsg", "Country", xml);
				
				//City Error message	
				warningMessage_commonMethod(application, "Addsiteorder_devciexngCityErrmsg", "City", xml);	
					
		
					
					//Validate site order Number Field
					 		validatesiteOrderNumber_AddSiteOrder(application);
					
					//Validate Country dropdown
							validateCountry_AddSiteorder(application);
								
					//Validate City Fields
							validateCity_AddSiteOrder(application);
							
					//Validate Site/CSR field
							validateSite_AddSiteOrder(application);
							
			scrolltoend();
			Thread.sleep(3000);
			
					//Validate performance reporting dropdown
							validatePerformancereporting_AddSiteOrder(application);
							
							
					//validate proactive Monitoring dropdown
							validateProactiveMonitoring_AddSiteOrder(application);
							
							
					//Validate Smarts monitoring dropdown
							validateSmartsMOnitoring_AddSiteOrder(application);
							
						
					//Validate IV Reference dropdown
							validateIVReference_AddSiteorder(application);

					//Validate Circuit reference Field
							validateCircuitreference_AddSiteOrder(application);

					//Spoke ID
							validatespokeId_AddSiteOrder(application);

							
					//Validate Site Alias field
							validateSiteAlias_AddSiteOrder(application);

									
					//Verify Remark field
							validateRemark_AddSiteOrder(application);
							
					//Validate Offnet checkbox
							validateoffnet_AddSiteOrder(application);		

			
		
	if( (interfaceSpeed.equals("1GigE")))	{
		
		for(int i=0; i<IVrefrence.length; i++) {
			if(IVrefrence[i].equals("Primary")) {
				
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_offnetCheckbox")));
				
				clickOnBankPage();
				Thread.sleep(2000);
				
				scrolltoend();
				Thread.sleep(1000);
				
				click_commonMethod(application, "IV Reference", "Addsiteorder_IVreferencedropdown", xml);
				
				click_commonMethod(application, "Primary", "Addsiteorder_IVreference_Primary", xml);
				
				technologyDropdownFor1GigE_HubAndSpoke_Primary_offnetselected(application, "Primary");
				
			}
			
			else if(IVrefrence[i].equals("Access")) {
				
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_offnetCheckbox")));
				
				clickOnBankPage();
				Thread.sleep(2000);
				
				scrolltoend();
				Thread.sleep(1000);
				
				click_commonMethod(application, "IV Reference", "Addsiteorder_IVreferencedropdown", xml);
				
				click_commonMethod(application, "Access", "Addsiteorder_IVreference_Access", xml);
				
				technologyDropdownFor1GigE_HubAndSpoke_Access_offnetselected(application, "Access");
				
			}
		}
	}	
			
	else if(interfaceSpeed.equals("10GigE"))	{
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_offnetCheckbox")));
		
		ExtentTestManager.getTest().log(LogStatus.INFO, " If 'Offnet' is selected for '10GigE', 'No value displays under 'Technology' dropdown");
		Log.info(" If 'Offnet' is selected for '10GigE', 'No value displays under 'Technology' dropdown");
	}
			
			
	
		//Validate OK button
				OKbutton_AddSiteOrder(application);

		//validate cancel button
				cancelbutton_AddSiteOrder(application);

				
		Thread.sleep(3000);

	scrolltoend();
	Thread.sleep(2000);
	
		click_commonMethod(application, "Cancel", "Addsiteorder_cancel", xml);
		Thread.sleep(3000);
		
		sa.assertAll();
		
		}catch(AssertionError e) {
		   e.printStackTrace();
			}
	}




public void verifySiteOrderForE_PN(String application, String interfaceSpeed) throws InterruptedException, DocumentException, IOException{
	
	ExtentTestManager.getTest().log(LogStatus.INFO, " Site order functions will be performed for 'VPN Topology' --> E-PN (Any to Any)");

	try {
			
			String[] IVrefrence = { "Primary", "Access" };
			
			scrolltoend();
			Thread.sleep(2000);

			click_commonMethod(application, "OK", "obutton_spanTag", xml);
			Thread.sleep(2000);
			
			
		//Circuit Reference Error Message
			warningMessage_commonMethod(application, "Addsiteorder_circuitReferenceErrmsg" , "Site Order Number", xml);
		
			
		//IV Reference Error Messages
			warningMessage_commonMethod(application, "Addsiteorder_IVReferenceErrmsg", "IV Reference", xml);
				
			
		//CSR name Error message	
			warningMessage_commonMethod(application, "Addsiteorder_csrnameErrmsg" , "CSR Name", xml);
			
			
		//Technology Error message	
			warningMessage_commonMethod(application, "Addsitieorder_technologyErrmsg" , "Technology", xml);
			
	scrollToTop();
	Thread.sleep(3000);
	
		//Site Order Number Error Message	
			warningMessage_commonMethod(application, "Addsiteorder_sitOrderNumberErrmsg" , "Site Order Number", xml);
			
		//Country Error message	
			warningMessage_commonMethod(application, "Addsiteorder_countryerrmsg", "Country", xml);
			
		//City Error message	
			warningMessage_commonMethod(application, "Addsiteorder_devciexngCityErrmsg", "City", xml);
			
				
				
				//Validate site order Number Field
				 		validatesiteOrderNumber_AddSiteOrder(application);

				
				//Validate Country dropdown
						validateCountry_AddSiteorder(application);
						
							
				//Validate City Fields
						validateCity_AddSiteOrder(application);
						
				//Validate Site/CSR field
						validateSite_AddSiteOrder(application);
						
			scrolltoend();
			Thread.sleep(3000);
			
				//Validate performance reporting dropdown
						validatePerformancereporting_AddSiteOrder(application);
						
						
				//validate proactive Monitoring dropdown
						validateProactiveMonitoring_AddSiteOrder(application);
						
						
				//Validate Smarts monitoring dropdown
						validateSmartsMOnitoring_AddSiteOrder(application);
						
					
				//Validate IV Reference dropdown
						validateIVReference_AddSiteorder(application);

				//Validate Circuit reference Field
						validateCircuitreference_AddSiteOrder(application);

				//Validate EPN Offnet checkbox
					
						validateEPNoffnet_AddSiteOrder(application);
					
						
				//validate EPN EOSDH checkbox
				if(interfaceSpeed.equals("1GigE")) {
					validateEPNEOSDH_AddSiteOrder(application);
				}else {
					Log.info(" 'EPN EOSDH' checkbix does not display for 10G interface speed");
					ExtentTestManager.getTest().log(LogStatus.PASS, " 'EPN EOSDH' checkbix does not display for 10G interface speed");
				}

				//Validate Site Alias field
						validateSiteAlias_AddSiteOrder(application);

								
						
				//Verify Remark field
						validateRemark_AddSiteOrder(application);

		
	
if( (interfaceSpeed.equals("1GigE")))	{
	
	for(int i=0; i<IVrefrence.length; i++) {
		if(IVrefrence[i].equals("Primary")) {
			

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_IVreferencedropdown")));
			Thread.sleep(3000);
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_IVreference_Primary")));
			Thread.sleep(3000);
			
			technologyDropdownFor1GigE_EPN_Primary(application);
			
		}
		
		else if(IVrefrence[i].equals("Access")) {
			

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_IVreferencedropdown")));
			Thread.sleep(3000);
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_IVreference_Access")));
			Thread.sleep(3000);
			
			technologyDropdownFor1GigE_EPN_Access(application);
			
		}
	}
}	
		
else if(interfaceSpeed.equals("10GigE"))	{
	
	for(int i=0; i<IVrefrence.length; i++) {
		if(IVrefrence[i].equals("Primary")) {
			

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_IVreferencedropdown")));
			Thread.sleep(3000);
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_IVreference_Primary")));
			Thread.sleep(3000);
			
			technologyDropdownFor10GigE_HubAndSpoke_primary(application);
			
		}
		
		else if(IVrefrence[i].equals("Access")) {
			

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_IVreferencedropdown")));
			Thread.sleep(3000);
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_IVreference_Access")));
			Thread.sleep(3000);
			
			technologyDropdownFor10GigE_HubAndSpoke_Access(application);
			
		}
	}
	
}
	//Validate OK button
			OKbutton_AddSiteOrder(application);

	//validate cancel button
			cancelbutton_AddSiteOrder(application);

			
	Thread.sleep(3000);
	scrolltoend();
	
	click_commonMethod(application, "Cancel", "Addsiteorder_cancel", xml);
	Thread.sleep(3000);
	
	sa.assertAll();
	
	}catch(AssertionError e) {
	   e.printStackTrace();
		}
}


public void verifySiteOrderForEPN_EOSDHselected(String application, String interfaceSpeed) throws InterruptedException, DocumentException, IOException{
	
	ExtentTestManager.getTest().log(LogStatus.INFO, " Site order functions will be performed for 'VPN Topology' --> E-PN (Any to Any)");

	try {
			
			String[] IVrefrence = { "Primary", "Access" };

			scrolltoend();
			Thread.sleep(2000);
			
			click_commonMethod(application, "OK", "obutton_spanTag", xml);
			Thread.sleep(2000);
			
			//Circuit Reference Error Message
			warningMessage_commonMethod(application, "Addsiteorder_circuitReferenceErrmsg" , "Circuit Reference", xml);
		
			
			//IV Reference Error Messages
			warningMessage_commonMethod(application, "Addsiteorder_IVReferenceErrmsg" , "IV Reference", xml);
				
			
			//CSR name Error message	
				warningMessage_commonMethod(application, "Addsiteorder_csrnameErrmsg" , "CSR Name", xml);
				
				
			//Technology Error message	
				warningMessage_commonMethod(application, "Addsitieorder_technologyErrmsg" , "Technology", xml);
				
		scrollToTop();
		Thread.sleep(3000);
		
			//Site Order Number Error Message	
				warningMessage_commonMethod(application, "Addsiteorder_sitOrderNumberErrmsg" , "Site Order Number", xml);
				
			//Country Error message	
				warningMessage_commonMethod(application, "Addsiteorder_countryerrmsg", "Country", xml);
				
			//City Error message	
				warningMessage_commonMethod(application, "Addsiteorder_devciexngCityErrmsg", "City", xml);
				
			
				
				//Validate site order Number Field
				 		validatesiteOrderNumber_AddSiteOrder(application);

				//Validate Country dropdown
						validateCountry_AddSiteorder(application);
						
				//Validate City Fields
						validateCity_AddSiteOrder(application);
						
				//Validate Site/CSR field
						validateSite_AddSiteOrder(application);
						
			scrolltoend();
			Thread.sleep(3000);
			
				//Validate performance reporting dropdown
						validatePerformancereporting_AddSiteOrder(application);
						
						
				//validate proactive Monitoring dropdown
						validateProactiveMonitoring_AddSiteOrder(application);
						
						
				//Validate Smarts monitoring dropdown
						validateSmartsMOnitoring_AddSiteOrder(application);
						
				//Validate IV Reference dropdown
						validateIVReference_AddSiteorder(application);

				//Validate Circuit reference Field
						validateCircuitreference_AddSiteOrder(application);

				//Validate EPN Offnet checkbox
						validateEPNoffnet_AddSiteOrder(application);
					
						
				//validate EPN EOSDH checkbox
				if(interfaceSpeed.equals("1GigE")) {
					validateEPNEOSDH_AddSiteOrder(application);
				}else {
					Log.info(" 'EPN EOSDH' checkbix does not display for 10G interface speed");
					ExtentTestManager.getTest().log(LogStatus.PASS, " 'EPN EOSDH' checkbix does not display for 10G interface speed");
				}

				//Validate Site Alias field
						validateSiteAlias_AddSiteOrder(application);
						
				//Verify Remark field
						validateRemark_AddSiteOrder(application);

		
if( (interfaceSpeed.equals("1GigE")))	{
	
	for(int i=0; i<IVrefrence.length; i++) {
		if(IVrefrence[i].equals("Primary")) {
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_EPNEOSDHCheckbox")));
			Thread.sleep(3000);

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_IVreferencedropdown")));
			Thread.sleep(3000);
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_IVreference_Primary")));
			Thread.sleep(3000);
			
			technologyDropdownFor1GigE_EPNEOSDHselected_Primary(application);
			
		}
		
		else if(IVrefrence[i].equals("Access")) {
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_EPNEOSDHCheckbox")));
			Thread.sleep(3000);
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_IVreferencedropdown")));
			Thread.sleep(3000);
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_IVreference_Access")));
			Thread.sleep(3000);
			
			technologyDropdownFor1GigE_EPNEOSDHselected_Access(application);
			
		}
	}
}	
		
	//Validate OK button
			OKbutton_AddSiteOrder(application);

	//validate cancel button
			cancelbutton_AddSiteOrder(application);

			
	Thread.sleep(3000);
scrolltoend();
	click_commonMethod(application, "Cancel", "Addsiteorder_cancel", xml);
	Thread.sleep(3000);
	
	sa.assertAll();
	
	}catch(AssertionError e) {
	   e.printStackTrace();
		}
}


	
	public void verifySiteOrderFields_NonterminationField(String application) throws InterruptedException, DocumentException {
		boolean Nonterminationpointcheckbox=false;
		try {		
			Nonterminationpointcheckbox=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_nonterminationpoint")).isDisplayed();
			sa.assertTrue(Nonterminationpointcheckbox, "On selecting 'Overture' under Technology, Non termination point checkbox is not available");
			if(Nonterminationpointcheckbox) {
				ExtentTestManager.getTest().log(LogStatus.PASS, " 'Non Termination Point' checkbox is displayed under 'Add Site order' page as expected");
			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Non Termination Point' checkbox is not Available under 'Add Site order' page");
			}
			
			boolean nonTerminaionpointselection=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_nonterminationpoint")).isSelected();
			sa.assertFalse(nonTerminaionpointselection,"Non-termination point checbox under Add site is selected by default");
			if(nonTerminaionpointselection) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Non-Termination Point' checkbox is selected by default");
			}else {
				ExtentTestManager.getTest().log(LogStatus.PASS, " 'Non-Termination Point' checkbox is not selected by default as expected");
			}
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Non Termination Point' checkbox is not Available under 'Add Site order' page");
			Log.info(" 'Non Termination Point' checkbox is not Available under 'Add Site order' page");
		}catch(Exception ee) {
			ee.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Non-Termination Point' checkbox is selected by default");
			Log.info( " 'Non-Termination Point' checkbox is selected by default");
		}
	}
	
	
	public void verifySiteOrderFields_protected(String application) throws InterruptedException, DocumentException {
		boolean portectedcheckbox=false;
		try {	
			portectedcheckbox=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_protected")).isDisplayed();
		
			sa.assertTrue(portectedcheckbox, "On selecting Atrica under Technology, protected checkbox is not available");
			if(portectedcheckbox) {
				ExtentTestManager.getTest().log(LogStatus.PASS, " 'Protected' checkbox is displayed under 'Add Site order' page as expected");
			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Protected' checkbox is not available under 'Add Site order' page");
			}
		
			boolean protectedtselection=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_protected")).isSelected();
			sa.assertFalse(protectedtselection,"Protected checbox under Add site is selected by default");
			if(protectedtselection) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Protected' checkbox is selected by default");
			}else {
				ExtentTestManager.getTest().log(LogStatus.PASS, " 'Protected' checkbox is not selected by default as expected");
			}
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Protected' checkbox is not Available under 'Add Site order' page");
			Log.info(" 'Protected' checkbox is not Available under 'Add Site order' page");
		}catch(Exception ee) {
			ee.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Protected' checkbox is selected by default");
			Log.info( " 'Protected' checkbox is selected by default");
		}
	}
	
	
	public void verifySiteOrderField_deviceName(String application) throws InterruptedException, DocumentException {
		boolean devicename=false;
	try {	
		devicename=	getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Devicenamefield")).isDisplayed();
		sa.assertTrue(devicename, "On selecting Atrica under Technology, Device name is not available");
		if(devicename) {
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Device Name' field is displaying under 'Add Site Order' page as expected");
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Device Name' field is not displaying under 'Add Site Order' page");
	
		}
	}catch(Exception e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Device Name' field is not displaying under 'Add Site Order' page");
		Log.info(" 'Device Name' field is not displaying under 'Add Site Order' page");
	}
	}
	
	
	public void verifySiteorderFields_mappingMode(String application) throws InterruptedException, DocumentException {
		
		String[] mappingMode=new String[2];
		int i=0;
		boolean MappingdropdownAvailability=false;
		try {
			MappingdropdownAvailability=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_mappingModedropdown")).isDisplayed();
			
			if(MappingdropdownAvailability) {
				ExtentTestManager.getTest().log(LogStatus.PASS, " 'mapping mode' dropdown is displaying in 'Add Site Order' page as expected");
				Log.info(" 'mapping mode' dropdown is displaying in 'Add Site Order' page as expected");
				
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_mappingModedropdown")));
				Thread.sleep(3000);
				
				List<WebElement> listofMappingMode = getwebelements(xml.getlocator("//locators/" + application + "/ClassNameForDropdowns"));
				
				if(listofMappingMode.size()>=1) {	
					for (WebElement mappingModetypes : listofMappingMode) {

								Log.info("list of Mapping modes are : " + mappingModetypes.getText());
								ExtentTestManager.getTest().log(LogStatus.PASS,"The list of Mapping modes  inside dropdown is: "+mappingModetypes.getText());
								Log.info("The list of mapping Modes  inside dropdown is: "+mappingModetypes.getText());
							 mappingMode[i]=	mappingModetypes.getText();
							 i++;
								
							}
				}			
				
		
			for(int j=0; j<mappingMode.length; j++) {
				
				if(mappingMode[j].equalsIgnoreCase("Port Based")) {
					Clickon(getwebelement("//div[text()='"+ mappingMode[j] +"']"));
					Thread.sleep(3000);
				
					boolean portname=false;
				try {	
					portname=getwebelement(xml.getlocator("//locators/" + application + "/portname_textField")).isDisplayed();
					Thread.sleep(3000);
					
					if(portname) {
						ExtentTestManager.getTest().log(LogStatus.PASS, " 'Port name' text field is displaying as expected");
						Log.info(" 'Port name' text field is displayig");
					}else {
						ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Port name' text field is not displaying");
						Log.info(" 'Port name' text field is not displaying");
					}
				}catch(NoSuchElementException e) {
					e.printStackTrace();
					ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Port name' text field is not displaying");
					Log.info(" 'Port name' text field is not displaying");
				}
					
					
				}
				else if(mappingMode[j].equalsIgnoreCase("Vlan Based")) {
					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_mappingModedropdown")));
					Thread.sleep(3000);
					
					Clickon(getwebelement("//div[text()='"+ mappingMode[j] +"']"));
					Thread.sleep(3000);
					
					boolean vlanName=false;
					try {	
						vlanName=getwebelement(xml.getlocator("//locators/" + application + "/vlanid_textfield")).isDisplayed();
						Thread.sleep(3000);
						
						if(vlanName) {
							ExtentTestManager.getTest().log(LogStatus.PASS, " 'VLAN Id' text field is displaying as expected");
							Log.info(" 'VLAN Id' text field is displayig");
						}else {
							ExtentTestManager.getTest().log(LogStatus.FAIL, " 'VLAN Id' text field is not displaying");
							Log.info(" 'VLAN Id' text field is not displaying");
						}
					}catch(Exception e) {
						e.printStackTrace();
						ExtentTestManager.getTest().log(LogStatus.FAIL, " 'VLAN Id' text field is not displaying");
						Log.info(" 'VLAN Id' text field is not displaying");
					}

			}
			}				
			}else {
				ExtentTestManager.getTest().log(LogStatus.PASS, " 'mapping mode' dropdown is not displaying in 'Add Site Order' page");
				Log.info(" 'mapping mode' dropdown is not displaying in 'Add Site Order' page");
			}
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'mapping mode' dropdown is not displaying in 'Add Site Order' page");
			Log.info(" 'mapping mode' dropdown is not displaying in 'Add Site Order' page");
		}
	}

	
	public void technologyDropdownFor1GigE(String application) throws InterruptedException, DocumentException {
		
		String[] Technology = { "Actelis", "Atrica", "Overture","Alu", "Accedian-1G", "Cyan" };

		boolean technology, devicename, Nonterminationpointcheckbox, portectedcheckbox;
		
		// Technology dropdown
				technology = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology"))
						.isDisplayed();
				sa.assertTrue(technology, "Technology dropdown is not displayed");

				click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
				List<WebElement> listoftechnology = getwebelements(xml.getlocator("//locators/" + application + "/ClassNameForDropdowns"));
				
			if(listoftechnology.size()>=1) {	
				for (WebElement technologytypes : listoftechnology) {

							Log.info("list of technology are : " + technologytypes.getText());
							ExtentTestManager.getTest().log(LogStatus.PASS,"The list of technology  inside dropdown while  adding site order is: "+technologytypes.getText());
							Log.info("The list of technology  inside dropdown while  adding site order is: "+technologytypes.getText());
							String technologyValue=technologytypes.getText();
				}
				
		
				for(int k=0;k<Technology.length;k++) {	
					//Actelis	
							if(Technology[k].equalsIgnoreCase("Actelis")) {
								ExtentTestManager.getTest().log(LogStatus.INFO, "when technology 'Actelis' is selected under Technology"
										+ "no additional fields displays");
								
								click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
								
								
								WebElement technologySelected = getwebelement(xml.getlocator("//locators/" + application + "/selectValueUnderTechnologyDropdown").replace("value", Technology[k]));  clickonTechnology(technologySelected, Technology[k]);
									
							}
							
						//Atrica	
							else if(Technology[k].equalsIgnoreCase("Atrica")) {
								
								ExtentTestManager.getTest().log(LogStatus.INFO, "when technology 'Atrica' is selected under Technology"
										+ "list of fields should occur: "
										+ "Device name - Mandatory field"
										+ "Non Termination point checkbox"
										+ "Protected checkbox");
								
								click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
								WebElement technologySelected = getwebelement(xml.getlocator("//locators/" + application + "/selectValueUnderTechnologyDropdown").replace("value", Technology[k]));  clickonTechnology(technologySelected, Technology[k]);
								
								
						//Device Name	
							verifySiteOrderField_deviceName(application);
							
						//Non Termination Point	
							verifySiteOrderFields_NonterminationField(application);
							
						//Protected checkbox	
							verifySiteOrderFields_protected(application);
						}
						
						//Overture	
							else if(Technology[k].equalsIgnoreCase("Overture")) {
								ExtentTestManager.getTest().log(LogStatus.INFO, "when technology 'Overture' is selected under Technology"
										+ "list of fields should occur: "
										+ "Non Termination point checkbox"
										+ "Protected checkbox");
								
								click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
								WebElement technologySelected = getwebelement(xml.getlocator("//locators/" + application + "/selectValueUnderTechnologyDropdown").replace("value", Technology[k]));  clickonTechnology(technologySelected, Technology[k]);
								
							//Non Termination Point	
								verifySiteOrderFields_NonterminationField(application);
								
							//Protected checkbox	
								verifySiteOrderFields_protected(application);
				
							}
					
						//Alu	
							else if(Technology[k].equalsIgnoreCase("Alu")) {
								
								ExtentTestManager.getTest().log(LogStatus.INFO, "when technology 'Alu' is selected under Technology"
										+ "list of fields should occur: "
										+ "Device name - Mandatory field");
								
								click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
								WebElement technologySelected = getwebelement(xml.getlocator("//locators/" + application + "/selectValueUnderTechnologyDropdown").replace("value", Technology[k]));  clickonTechnology(technologySelected, Technology[k]);
								
							//Device Name	
								verifySiteOrderField_deviceName(application);
							}
							
							
					//Accedian
							else if((Technology[k].equalsIgnoreCase("Accedian-1G"))) {
								
								ExtentTestManager.getTest().log(LogStatus.INFO, "when technology 'Accedian' is selected under Technology list of field should occur: "
										+ "Non Termination point checkbox"
										+ "Protected checkbox");
								
								addDropdownValues_commonMethod(application, "Technology", "Addsiteorder_Technology", Technology[k], xml);
//								click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
//								WebElement technologySelected = getwebelement(xml.getlocator("//locators/" + application + "/selectValueUnderTechnologyDropdown").replace("value", Technology[k]));  clickonTechnology(technologySelected, Technology[k]);

							//Non Termination Point	
								verifySiteOrderFields_NonterminationField(application);

								
							//Protected checkbox	
								verifySiteOrderFields_protected(application);			
									
							}	
						
					//Cyan		
							else if(Technology[k].equalsIgnoreCase("Cyan")) {
								
								ExtentTestManager.getTest().log(LogStatus.INFO, "when technology 'Cyan' is selected under Technology"
										+ "list of fields should occur: "
										+ "Non Termination point checkbox");
								
								
								click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
								WebElement technologySelected = getwebelement(xml.getlocator("//locators/" + application + "/selectValueUnderTechnologyDropdown").replace("value", Technology[k]));  clickonTechnology(technologySelected, Technology[k]);

							//Non Termination Point	
								verifySiteOrderFields_NonterminationField(application);
								
							}	
				}
			}else {
				
				Log.info("no values are available inside technology dropdown for Add site order");
				ExtentTestManager.getTest().log(LogStatus.FAIL,"no values are available inside technology dropdown for Add site order");
			}
		}
	
	
public void technologyDropdownFor10GigE(String application) throws InterruptedException, DocumentException {
		
		String Technology = "Accedian";

		boolean technology, Nonterminationpointcheckbox, portectedcheckbox;
		
		// Technology dropdown
				technology = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology"))
						.isDisplayed();
				sa.assertTrue(technology, "Technology dropdown is not displayed");

				click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
				List<WebElement> listoftechnology = getwebelements(xml.getlocator("//locators/" + application + "/ClassNameForDropdowns"));
				
			if(listoftechnology.size()>=1) {	
				for (WebElement technologytypes : listoftechnology) {

							Log.info("list of technology are : " + technologytypes.getText());
							Log.info("list of technology are : " + technologytypes.getText());
							ExtentTestManager.getTest().log(LogStatus.PASS,"The list of technology  inside dropdown while  adding site order is: "+technologytypes.getText());
				}
			}else {
				
				Log.info("no values are available inside technology dropdown for Add site order");
				ExtentTestManager.getTest().log(LogStatus.FAIL,"no values are available inside technology dropdown for Add site order");
			}
			
		ExtentTestManager.getTest().log(LogStatus.INFO, "when technology 'Accedian' is selected under Technology"
						+ "list of fields should occur: "
						+ "Non Termination point checkbox"
						+ "Protected checkbox");
				
				click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
				WebElement technologySelected = getwebelement(xml.getlocator("//locators/" + application + "/selectValueUnderTechnologyDropdown").replace("value", Technology));  clickonTechnology(technologySelected, Technology);
				
				
				
			//Non Termination Point	
				verifySiteOrderFields_NonterminationField(application);

				
			//Protected checkbox	
				verifySiteOrderFields_protected(application);

			
			}	

	
public void technologyDropdownFor1GigE_HubAndSpoke_Access(String application) throws InterruptedException, DocumentException {
	
	String[] Technology = { "Actelis", "Atrica", "Overture","Alu", "Accedian-1G", "Cyan" };
	
	String[] GCROLOType = { "2A3", "1A4", "1A3", "2A4/1A4" };
	
	String[] VLANEtherType = { "S-VLAN(88A8)", "C-VLAN(8100)" };
	
	String[] primaryVLANEtherType = { "S-VLAN(88A8)", "C-VLAN(8100)" };
			

	boolean technology, devicename, Nonterminationpointcheckbox, portectedcheckbox, GCRoloField, Vlan, VlanetherType, PrimaryVlan, primaryVlanetherType;
	
	// Technology dropdown
			technology = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology"))
					.isDisplayed();
			sa.assertTrue(technology, "Technology dropdown is not displayed");

			click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
			List<WebElement> listoftechnology = getwebelements(xml.getlocator("//locators/" + application + "/ClassNameForDropdowns"));
			
		if(listoftechnology.size()>=1) {	
			for (WebElement technologytypes : listoftechnology) {

						Log.info("list of technology are : " + technologytypes.getText());
						Log.info("The list of technology  inside dropdown while  adding site order is: "+technologytypes.getText());
						ExtentTestManager.getTest().log(LogStatus.PASS,"The list of technology  inside dropdown while  adding site order is: "+technologytypes.getText());
						String technologyValue=technologytypes.getText();
			}
			
			for(int k=0;k<Technology.length;k++) {
				
				//Actelis
						if(Technology[k].equalsIgnoreCase("Actelis")) {
							ExtentTestManager.getTest().log(LogStatus.INFO, "when technology 'Actelis' is selected under Technology"
									+ "no additional fields displays");
							
							click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
							WebElement technologySelected = getwebelement(xml.getlocator("//locators/" + application + "/selectValueUnderTechnologyDropdown").replace("value", Technology[k]));  clickonTechnology(technologySelected, Technology[k]);
								
						}
						
						
					//Atrica	
						else if(Technology[k].equalsIgnoreCase("Atrica")) {
							
							ExtentTestManager.getTest().log(LogStatus.INFO, "when technology 'Atrica' is selected under Technology"
									+ "list of fields should occur: "
									+ "Non Termination point checkbox"
									+ "Protected checkbox");
							
							click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
							WebElement technologySelected = getwebelement(xml.getlocator("//locators/" + application + "/selectValueUnderTechnologyDropdown").replace("value", Technology[k]));  clickonTechnology(technologySelected, Technology[k]);
							
						//Non Termination Point	
							verifySiteOrderFields_NonterminationField(application);

							
						//Protected checkbox	
							verifySiteOrderFields_protected(application);
						}

				//Overture
						else if(Technology[k].equalsIgnoreCase("Overture")) {
							
							ExtentTestManager.getTest().log(LogStatus.INFO, "when technology 'Overture' is selected under Technology"
									+ "list of fields should occur: "
									+ "Non Termination point checkbox"
									+ "Protected checkbox"
									+ "GCR OLO Type dropdown"
									+ "VLAN text field"
									+ "VLAN Ether Type dropdown"
									+ "Primary VLAN Text Field"
									+ "Primary VLAN Ether Type dropdown");
							
							click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
							WebElement technologySelected = getwebelement(xml.getlocator("//locators/" + application + "/selectValueUnderTechnologyDropdown").replace("value", Technology[k]));  clickonTechnology(technologySelected, Technology[k]);
							
						//Non Termination Point	
							verifySiteOrderFields_NonterminationField(application);

							
						//Protected checkbox	
							verifySiteOrderFields_protected(application);		
					
					
						
					//GCR OLO Type dropdown
					try {
						GCRoloField = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_GCRoloTypeDropdown")).isDisplayed();
						sa.assertTrue(GCRoloField, "GCR OLO Type dropdown is not Available");
						if(GCRoloField) {
						ExtentTestManager.getTest().log(LogStatus.PASS, " 'GCR OLO Type' drodpown field is displaying under 'Add Site Order' page as expected");
							
						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_GCRoloTypeDropdown")));
						List<WebElement> listofGcrOLOtype = getwebelements(xml.getlocator("//locators/" + application + "/ClassNameForDropdowns"));

						if(listofGcrOLOtype.size()>=1) {
						for (WebElement GCROlotypes : listofGcrOLOtype) {
								boolean match = false;
							for (int i = 0; i < GCROLOType.length; i++) {
								if (GCROlotypes.getText().equals(GCROLOType[i])) {
									match = true;
									Log.info("list of 'GCR OLO Type' are : " + GCROlotypes.getText());
									ExtentTestManager.getTest().log(LogStatus.PASS,"The list of 'GCR OLO Type' inside dropdown is: "+GCROlotypes.getText());
									
								}
								}
								sa.assertTrue(match);
								
						}
					}else {
						Log.info("no values are available inside 'GCR OLO Type' dropdown for Add site order");
						ExtentTestManager.getTest().log(LogStatus.FAIL,"no values are available inside 'GCR OLO Type' dropdown for Add site order");
				}
					}else {
						ExtentTestManager.getTest().log(LogStatus.FAIL, " 'GCR OLO Type' dropdown is not available under 'Add Site order' page");
					}	
					}catch(NoSuchElementException e) {
						e.printStackTrace();
						ExtentTestManager.getTest().log(LogStatus.FAIL, " 'GCR OLO Type' dropdown is not Available under 'Add Site order' page");
						Log.info(" 'GCR OLO Type' dropdown is not Available under 'Add Site order' page");
					}catch(Exception ee) {
						ee.printStackTrace();
						ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to enter value under 'GCR OLO type' dropdown");
						Log.info( " Not able to enter value under 'GCR OLO type' dropdown");
					}
					
							
							
					//VLAN Text field
					try {
						Vlan=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_VLAN")).isDisplayed();
						sa.assertTrue(Vlan, " 'VLAN' text field is not available");
						if(Vlan) {
							ExtentTestManager.getTest().log(LogStatus.PASS, " 'VLAN' field is displaying as expected, when 'Technology' is selected as 'Overture' and 'IV Reference' selected as 'Access'");
						}else {
							ExtentTestManager.getTest().log(LogStatus.FAIL, " 'VLAN' field is not displaying, when 'Technology' is selected as 'Overture' and 'IV Reference' selected as 'Access'");
						}
					}catch(Exception e) {
						e.printStackTrace();
						ExtentTestManager.getTest().log(LogStatus.FAIL, " 'VLAN' text field is not Available under 'Add Site order' page");
						Log.info(" 'VLAN' text field is not Available under 'Add Site order' page");
					}
				
						
						
					//VLAN Ether Type
					try {
						VlanetherType = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_VLANEtherTypeDropdown")).isDisplayed();
						sa.assertTrue(VlanetherType, " 'VLAN Ether Type' dropdown is not Available");
						if(VlanetherType) {
						ExtentTestManager.getTest().log(LogStatus.PASS, " 'VLAN Ether Type' drodpown field is displaying under 'Add Site Order' page as expected");
							
						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_VLANEtherTypeDropdown")));
						List<WebElement> listofVLANethertype = getwebelements(xml.getlocator("//locators/" + application + "/ClassNameForDropdowns"));

						if(listofVLANethertype.size()>=1) {
						for (WebElement VLANEthertypes : listofVLANethertype) {
								boolean match = false;
							for (int i = 0; i < VLANEtherType.length; i++) {
								if (VLANEthertypes.getText().equals(VLANEtherType[i])) {
									match = true;
									Log.info("list of 'VLAN Ether Type' are : " + VLANEthertypes.getText());
									ExtentTestManager.getTest().log(LogStatus.PASS,"The list of 'VLAN Ether Type' inside dropdown is: "+VLANEthertypes.getText());
									
								}
								}
							sa.assertTrue(match);
								
						}
					}else {
						Log.info("no values are available inside 'VLAN Ether Type' dropdown for Add site order");
						ExtentTestManager.getTest().log(LogStatus.FAIL,"no values are available inside 'VLAN Ether Type' dropdown for Add site order");
				}
					}else {
						ExtentTestManager.getTest().log(LogStatus.FAIL, " 'VLAN Ether Type' dropdown is not available under 'Add Site order' page");
					}	
					}catch(Exception e) {
						e.printStackTrace();
						ExtentTestManager.getTest().log(LogStatus.FAIL, " 'VLAN Ether type' dropdown is not Available under 'Add Site order' page");
					}
					
						
						
					//Primary VLAN Text field
					try {
						PrimaryVlan=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_PrimaryVLAN")).isDisplayed();
						sa.assertTrue(PrimaryVlan, " 'Primary VLAN' text field is not available");
						if(PrimaryVlan) {
							ExtentTestManager.getTest().log(LogStatus.PASS, " 'Primary VLAN' field is displaying as expected, when 'Technology' is selected as 'Overture' and 'IV Reference' selected as 'Access'");
						}else {
							ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Primary VLAN' field is not displaying, when 'Technology' is selected as 'Overture' and 'IV Reference' selected as 'Access'");
						}
					}catch(Exception e) {
						e.printStackTrace();
						ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Primary VLAN' text field is not Available under 'Add Site order' page");
						Log.info(" 'Primary VLAN' text field is not Available under 'Add Site order' page");
					}
				
						
					//Primary VLAN Ether Type	
					try {
						primaryVlanetherType = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_primaryVLANEtherTypeDropdown")).isDisplayed();
						sa.assertTrue(primaryVlanetherType, " 'Primary VLAN Ether Type' dropdown is not Available");
						if(primaryVlanetherType) {
						ExtentTestManager.getTest().log(LogStatus.PASS, " 'Primary VLAN Ether Type' drodpown field is displaying under 'Add Site Order' page as expected");
							
						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_primaryVLANEtherTypeDropdown")));
						List<WebElement> listofprimaryVLANethertype = getwebelements(xml.getlocator("//locators/" + application + "/ClassNameForDropdowns"));

						if(listofprimaryVLANethertype.size()>=1) {
						for (WebElement primaryVLANEthertypes : listofprimaryVLANethertype) {
								boolean match = false;
							for (int i = 0; i < primaryVLANEtherType.length; i++) {
								if (primaryVLANEthertypes.getText().equals(primaryVLANEtherType[i])) {
									match = true;
									Log.info("list of 'Primary VLAN Ether Type' are : " + primaryVLANEthertypes.getText());
									ExtentTestManager.getTest().log(LogStatus.PASS,"The list of 'Primary VLAN Ether Type' inside dropdown is: "+primaryVLANEthertypes.getText());
									
								}
								}
							sa.assertTrue(match);
								
						}
					}else {
						Log.info("no values are available inside 'Primary VLAN Ether Type' dropdown for Add site order");
						ExtentTestManager.getTest().log(LogStatus.FAIL,"no values are available inside 'Primary VLAN Ether Type' dropdown for Add site order");
				}
					}else {
						ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Primary VLAN Ether Type' dropdown is not available under 'Add Site order' page");
					}	
					}catch(Exception e) {
						e.printStackTrace();
						ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Primary VLAN Ether type' dropdown is not Available under 'Add Site order' page");
					}
				}		

						
			//Alu
				else if(Technology[k].equalsIgnoreCase("Alu")) {
					ExtentTestManager.getTest().log(LogStatus.INFO, "when technology 'Alu' is selected under Technology"
							+ "no additional fields display");
					
					click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
					WebElement technologySelected = getwebelement(xml.getlocator("//locators/" + application + "/selectValueUnderTechnologyDropdown").replace("value", Technology[k]));  clickonTechnology(technologySelected, Technology[k]);
				}	
				

				//Accedian-1G		
					else if((Technology[k].equalsIgnoreCase("Accedian-1G"))) {
					
					ExtentTestManager.getTest().log(LogStatus.INFO, "when technology 'Accedian' is selected under Technology"
							+ "Non Termination point checkbox"
							+ "Protected checkbox");
					
					click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
					WebElement technologySelected = getwebelement(xml.getlocator("//locators/" + application + "/selectValueUnderTechnologyDropdown").replace("value", Technology[k]));  clickonTechnology(technologySelected, Technology[k]);
					
				//Non Termination Point	
					verifySiteOrderFields_NonterminationField(application);
					
				//Protected checkbox	
					verifySiteOrderFields_protected(application);
				}	

				
			  //Cyan
					else if(Technology[k].equalsIgnoreCase("Cyan")) {
						
						ExtentTestManager.getTest().log(LogStatus.INFO, "when technology 'Cyan' is selected under Technology"
								+ "list of fields should occur: "
								+ "Non Termination point checkbox");
						
						click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
						WebElement technologySelected = getwebelement(xml.getlocator("//locators/" + application + "/selectValueUnderTechnologyDropdown").replace("value", Technology[k]));  clickonTechnology(technologySelected, Technology[k]);
						
						
					//Non Termination Point	
						verifySiteOrderFields_NonterminationField(application);
					}
			
			}
		}else {
			
			Log.info("no values are available inside technology dropdown for Add site order");
			ExtentTestManager.getTest().log(LogStatus.FAIL,"no values are available inside technology dropdown for Add site order");
		}
		
}



public void technologyDropdownFor1GigE_HubAndSpoke_Access_offnetselected(String application, String IVReferenceSelection) throws InterruptedException, DocumentException {
	
	String[] Technology = { "Overture", "Accedian-1G" };
	
	String[] GCROLOType = { "2A3", "1A4", "1A3", "2A4/1A4" };
	
	String[] VLANEtherType = { "S-VLAN(88A8)", "C-VLAN(8100)" };
	
	String[] primaryVLANEtherType = { "S-VLAN(88A8)", "C-VLAN(8100)" };
			

	boolean technology, devicename, Nonterminationpointcheckbox, portectedcheckbox, GCRoloField, Vlan, VlanetherType, PrimaryVlan, primaryVlanetherType;
	
	
	//Select "IV Reference" as "Primary
	Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_IVreferencedropdown")));
	Thread.sleep(3000);
	
	Clickon(getwebelement("//div[contains(text(),'"+ IVReferenceSelection+"')]"));
	Thread.sleep(3000);
	
	
//Select "Offnet" checkbox
	Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_offnetCheckbox")));
	Thread.sleep(3000);
	
	// Technology dropdown
			technology = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology"))
					.isDisplayed();
			sa.assertTrue(technology, "Technology dropdown is not displayed");

			click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
			List<WebElement> listoftechnology = getwebelements(xml.getlocator("//locators/" + application + "/ClassNameForDropdowns"));
			
			
			
		if(listoftechnology.size()>=1) {	
			for (WebElement technologytypes : listoftechnology) {

						Log.info("list of technology are : " + technologytypes.getText());
						ExtentTestManager.getTest().log(LogStatus.PASS,"The list of technology  inside dropdown while  adding site order is: "+technologytypes.getText());

			}
		}else {
			
			Log.info("no values are available inside technology dropdown for Add site order");
			ExtentTestManager.getTest().log(LogStatus.FAIL,"no values are available inside technology dropdown for Add site order");
		}
		
	
		for(int k=0;k<Technology.length;k++) {
			
		if(Technology[k].equalsIgnoreCase("Overture")) {
				
				ExtentTestManager.getTest().log(LogStatus.INFO, "when technology 'Overture' is selected under Technology"
						+ "list of fields should occur: "
						+ "Non Termination point checkbox"
						+ "Protected checkbox"
						+ "GCR OLO Type dropdown"
						+ "VLAN text field"
						+ "VLAN Ether Type dropdown"
						+ "Primary VLAN Text Field"
						+ "Primary VLAN Ether Type dropdown");
				
				click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
				WebElement technologySelected = getwebelement(xml.getlocator("//locators/" + application + "/selectValueUnderTechnologyDropdown").replace("value", Technology[k]));  clickonTechnology(technologySelected, Technology[k]);
				
				
		//Non Termination Point	
		verifySiteOrderFields_NonterminationField(application);
			
		//Protected checkbox	
		verifySiteOrderFields_protected(application);
	
			
		//GCR OLO Type dropdown
		try {
			GCRoloField = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_GCRoloTypeDropdown")).isDisplayed();
			sa.assertTrue(GCRoloField, "GCR OLO Type dropdown is not Available");
			if(GCRoloField) {
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'GCR OLO Type' drodpown field is displaying under 'Add Site Order' page as expected");
				
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_GCRoloTypeDropdown")));
			List<WebElement> listofGcrOLOtype = getwebelements(xml.getlocator("//locators/" + application + "/ClassNameForDropdowns"));

			if(listofGcrOLOtype.size()>=1) {
			for (WebElement GCROlotypes : listofGcrOLOtype) {
					boolean match = false;
				for (int i = 0; i < GCROLOType.length; i++) {
					if (GCROlotypes.getText().equals(GCROLOType[i])) {
						match = true;
						Log.info("list of 'GCR OLO Type' are : " + GCROlotypes.getText());
						ExtentTestManager.getTest().log(LogStatus.PASS,"The list of 'GCR OLO Type' inside dropdown is: "+GCROlotypes.getText());
						
					}
					}
					sa.assertTrue(match);
					
			}
		}else {
			Log.info("no values are available inside 'GCR OLO Type' dropdown for Add site order");
			ExtentTestManager.getTest().log(LogStatus.FAIL,"no values are available inside 'GCR OLO Type' dropdown for Add site order");
	}
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'GCR OLO Type' dropdown is not available under 'Add Site order' page");
		}	
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'GCR OLO Type' dropdown is not Available under 'Add Site order' page");
		}
		
				
				
		//VLAN Text field
		try {
			Vlan=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_VLAN")).isDisplayed();
			sa.assertTrue(Vlan, " 'VLAN' text field is not available");
			if(Vlan) {
				ExtentTestManager.getTest().log(LogStatus.PASS, " 'VLAN' field is displaying as expected, when 'Technology' is selected as 'Overture' and 'IV Reference' selected as 'Access'");
			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, " 'VLAN' field is not displaying, when 'Technology' is selected as 'Overture' and 'IV Reference' selected as 'Access'");
			}
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'VLAN' text field is not Available under 'Add Site order' page");
		}
	
			
			
		//VLAN Ether Type
		try {
			VlanetherType = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_VLANEtherTypeDropdown")).isDisplayed();
			sa.assertTrue(VlanetherType, " 'VLAN Ether Type' dropdown is not Available");
			if(VlanetherType) {
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'VLAN Ether Type' drodpown field is displaying under 'Add Site Order' page as expected");
				
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_VLANEtherTypeDropdown")));
			List<WebElement> listofVLANethertype = getwebelements(xml.getlocator("//locators/" + application + "/ClassNameForDropdowns"));

			if(listofVLANethertype.size()>=1) {
			for (WebElement VLANEthertypes : listofVLANethertype) {
					boolean match = false;
				for (int i = 0; i < VLANEtherType.length; i++) {
					if (VLANEthertypes.getText().equals(VLANEtherType[i])) {
						match = true;
						Log.info("list of 'VLAN Ether Type' are : " + VLANEthertypes.getText());
						ExtentTestManager.getTest().log(LogStatus.PASS,"The list of 'VLAN Ether Type' inside dropdown is: "+VLANEthertypes.getText());
						
					}
					}
				sa.assertTrue(match);
					
			}
		}else {
			Log.info("no values are available inside 'VLAN Ether Type' dropdown for Add site order");
			ExtentTestManager.getTest().log(LogStatus.FAIL,"no values are available inside 'VLAN Ether Type' dropdown for Add site order");
	}
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'VLAN Ether Type' dropdown is not available under 'Add Site order' page");
		}	
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'VLAN Ether type' dropdown is not Available under 'Add Site order' page");
		}
		
			
			
			
		//Primary VLAN Text field
		try {
			PrimaryVlan=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_PrimaryVLAN")).isDisplayed();
			sa.assertTrue(PrimaryVlan, " 'Primary VLAN' text field is not available");
			if(PrimaryVlan) {
				ExtentTestManager.getTest().log(LogStatus.PASS, " 'Primary VLAN' field is displaying as expected, when 'Technology' is selected as 'Overture' and 'IV Reference' selected as 'Access'");
			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Primary VLAN' field is not displaying, when 'Technology' is selected as 'Overture' and 'IV Reference' selected as 'Access'");
			}
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Primary VLAN' text field is not Available under 'Add Site order' page");
		}
	
			
		//Primary VLAN Ether Type	
		try {
			primaryVlanetherType = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_primaryVLANEtherTypeDropdown")).isDisplayed();
			sa.assertTrue(primaryVlanetherType, " 'Primary VLAN Ether Type' dropdown is not Available");
			if(primaryVlanetherType) {
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Primary VLAN Ether Type' drodpown field is displaying under 'Add Site Order' page as expected");
				
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_primaryVLANEtherTypeDropdown")));
			List<WebElement> listofprimaryVLANethertype = getwebelements(xml.getlocator("//locators/" + application + "/ClassNameForDropdowns"));

			if(listofprimaryVLANethertype.size()>=1) {
			for (WebElement primaryVLANEthertypes : listofprimaryVLANethertype) {
					boolean match = false;
				for (int i = 0; i < primaryVLANEtherType.length; i++) {
					if (primaryVLANEthertypes.getText().equals(primaryVLANEtherType[i])) {
						match = true;
						Log.info("list of 'Primary VLAN Ether Type' are : " + primaryVLANEthertypes.getText());
						ExtentTestManager.getTest().log(LogStatus.PASS,"The list of 'Primary VLAN Ether Type' inside dropdown is: "+primaryVLANEthertypes.getText());
						
					}
					}
				sa.assertTrue(match);
					
			}
		}else {
			Log.info("no values are available inside 'Primary VLAN Ether Type' dropdown for Add site order");
			ExtentTestManager.getTest().log(LogStatus.FAIL,"no values are available inside 'Primary VLAN Ether Type' dropdown for Add site order");
	}
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Primary VLAN Ether Type' dropdown is not available under 'Add Site order' page");
		}	
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Primary VLAN Ether type' dropdown is not Available under 'Add Site order' page");
		}
		}		
			
		//Accedian-1G
			else if((Technology[k].equalsIgnoreCase("Accedian-1G"))  ) {
				
				ExtentTestManager.getTest().log(LogStatus.INFO, "when technology 'Accedian' is selected under Technology"
						+ "Non Termination point checkbox"
						+ "Protected checkbox");
				
				click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
				WebElement technologySelected = getwebelement(xml.getlocator("//locators/" + application + "/selectValueUnderTechnologyDropdown").replace("value", Technology[k]));  clickonTechnology(technologySelected, Technology[k]);
				
			//Non Termination Point	
				verifySiteOrderFields_NonterminationField(application);
	
			//Protected checkbox
			 verifySiteOrderFields_protected(application);
					
			}	
			
		}
}


public void technologyDropdownFor1GigE_HubAndSpoke_Primary(String application) throws InterruptedException, DocumentException {
	
	String[] Technology = { "Actelis", "Atrica", "Overture","Alu", "Accedian-1G", "Cyan" };
		

	boolean technology, devicename, Nonterminationpointcheckbox, portectedcheckbox;
	
	// Technology dropdown
			technology = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology"))
					.isDisplayed();
			sa.assertTrue(technology, "Technology dropdown is not displayed");

			click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
			List<WebElement> listoftechnology = getwebelements(xml.getlocator("//locators/" + application + "/ClassNameForDropdowns"));
			Log.info("Number of technology sizes: "+listoftechnology.size());
			
			for (WebElement technologytypesSample : listoftechnology ) {
				Log.info(" list of technologies are: "+ technologytypesSample.getText());
			}
			
	try {		
		if(listoftechnology.size()>=1) {	
			for (WebElement technologytypes : listoftechnology ) {
				
				  driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
	
				  Log.info("tech value to be found: "+technologytypes.getText());
						ExtentTestManager.getTest().log(LogStatus.PASS,"The list of technology  inside dropdown while  adding site order is: "+technologytypes.getText());
						Log.info("The list of technology  inside dropdown while  adding site order is: "+technologytypes.getText());
						String technologyValue=technologytypes.getText();
			}
			

			for(int k=0;k<Technology.length;k++) {
				
				
					//Actelis	
						if(Technology[k].equalsIgnoreCase("Actelis")) {
							ExtentTestManager.getTest().log(LogStatus.INFO, "when technology 'Actelis' is selected under Technology"
									+ "no additional fields displays");
							
							click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
							WebElement technologySelected = getwebelement(xml.getlocator("//locators/" + application + "/selectValueUnderTechnologyDropdown").replace("value", Technology[k]));  clickonTechnology(technologySelected, Technology[k]);
						}
						
					//Atrica	
						else if(Technology[k].equalsIgnoreCase("Atrica")) {
							
							ExtentTestManager.getTest().log(LogStatus.INFO, "when technology 'Atrica' is selected under Technology"
									+ "list of fields should occur: "
									+ "Non Termination point checkbox"
									+ "Protected checkbox"
									+ " Device name field");
							
							click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
							WebElement technologySelected = getwebelement(xml.getlocator("//locators/" + application + "/selectValueUnderTechnologyDropdown").replace("value", Technology[k]));  clickonTechnology(technologySelected, Technology[k]);
							
						//Non Termination Point	
							verifySiteOrderFields_NonterminationField(application);

						//Protected checkbox	
							verifySiteOrderFields_protected(application);
				
//						//Device Name	
							verifySiteOrderField_deviceName(application);
					}
					
						
					//Overture	
						else if(Technology[k].equalsIgnoreCase("Overture")) {
							
							ExtentTestManager.getTest().log(LogStatus.INFO, "when technology 'Overture' is selected under Technology"
									+ "list of fields should occur: "
									+ "Non Termination point checkbox"
									+ "Protected checkbox");
							
							click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
							WebElement technologySelected = getwebelement(xml.getlocator("//locators/" + application + "/selectValueUnderTechnologyDropdown").replace("value", Technology[k]));  clickonTechnology(technologySelected, Technology[k]);
							
						//Non Termination Point	
							verifySiteOrderFields_NonterminationField(application);

						//Protected checkbox	
							verifySiteOrderFields_protected(application);
					}	
						
						
				//Alu		
						else if(Technology[k].equalsIgnoreCase("Alu")) {
							
							ExtentTestManager.getTest().log(LogStatus.INFO, "when technology 'Alu' is selected under Technology"
									+ "'Device Name' Text field should display");
							
							click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
							WebElement technologySelected = getwebelement(xml.getlocator("//locators/" + application + "/selectValueUnderTechnologyDropdown").replace("value", Technology[k]));  clickonTechnology(technologySelected, Technology[k]);
							
						//Device Name	
								verifySiteOrderField_deviceName(application);
						
						}
						
						
					//Accedian-1G
						else if((Technology[k].equalsIgnoreCase("Accedian-1G"))) {
							
							ExtentTestManager.getTest().log(LogStatus.INFO, "when technology 'Accedian' is selected under Technology"
									+ "Non Termination point checkbox"
									+ "Protected checkbox");
							
							click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
							WebElement technologySelected = getwebelement(xml.getlocator("//locators/" + application + "/selectValueUnderTechnologyDropdown").replace("value", Technology[k]));  clickonTechnology(technologySelected, Technology[k]);
							
						//Non Termination Point	
							verifySiteOrderFields_NonterminationField(application);

						//Protected checkbox	
							verifySiteOrderFields_protected(application);
						}
				

					//Cyan
						else if(Technology[k].equalsIgnoreCase("Cyan")) {
							
							ExtentTestManager.getTest().log(LogStatus.INFO, "when technology 'Cyan' is selected under Technology"
									+ "list of fields should occur: "
									+ "Non Termination point checkbox");
							
							click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
							WebElement technologySelected = getwebelement(xml.getlocator("//locators/" + application + "/selectValueUnderTechnologyDropdown").replace("value", Technology[k]));  clickonTechnology(technologySelected, Technology[k]);
							
						//Non Termination Point	
							verifySiteOrderFields_NonterminationField(application);

						}
						
			}
		}else {
			
			Log.info("no values are available inside technology dropdown for Add site order");
			ExtentTestManager.getTest().log(LogStatus.FAIL,"no values are available inside technology dropdown for Add site order");
		}
	}catch(StaleElementReferenceException e){

	    e.printStackTrace();

	  }	
	
}



public void technologyDropdownFor1GigE_HubAndSpoke_Primary_offnetselected(String application, String IVReferenceSelection) throws InterruptedException, DocumentException {
	
	String[] Technology = { "Overture","Accedian-1G" };
		
	//Select "IV Reference" as "Primary
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_IVreferencedropdown")));
			Thread.sleep(3000);
			
			Clickon(getwebelement("//div[contains(text(),'"+ IVReferenceSelection+"')]"));
			Thread.sleep(3000);
			
			
	//Select "Offnet" checkbox
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_offnetCheckbox")));
			Thread.sleep(3000);
			
			
	
	boolean technology, Nonterminationpointcheckbox, portectedcheckbox;
	
	// Technology dropdown
			technology = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology"))
					.isDisplayed();
			sa.assertTrue(technology, "Technology dropdown is not displayed");

			click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
			List<WebElement> listoftechnology = getwebelements(xml.getlocator("//locators/" + application + "/ClassNameForDropdowns"));
			
		if(listoftechnology.size()>=1) {	
			for (WebElement technologytypes : listoftechnology) {

						Log.info("list of technology are : " + technologytypes.getText());
						Log.info("The list of technology  inside dropdown while  adding site order is: "+technologytypes.getText());
						ExtentTestManager.getTest().log(LogStatus.PASS,"The list of technology  inside dropdown while  adding site order is: "+technologytypes.getText());
			}
		}else {
			
			Log.info("no values are available inside technology dropdown for Add site order");
			ExtentTestManager.getTest().log(LogStatus.FAIL,"no values are available inside technology dropdown for Add site order");
		}
		
	
		for(int k=0;k<Technology.length;k++) {
			
			
			if(Technology[k].equalsIgnoreCase("Overture")) {
				
				ExtentTestManager.getTest().log(LogStatus.INFO, "when technology 'Overture' is selected under Technology"
						+ "list of fields should occur: "
						+ "Non Termination point checkbox"
						+ "Protected checkbox");
				
				click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
				WebElement technologySelected = getwebelement(xml.getlocator("//locators/" + application + "/selectValueUnderTechnologyDropdown").replace("value", Technology[k]));  clickonTechnology(technologySelected, Technology[k]);
				
			//Non Termination Point	
				verifySiteOrderFields_NonterminationField(application);

				
			//Protected checkbox	
				verifySiteOrderFields_protected(application);
	
		}		
			
			
			else if((Technology[k].equalsIgnoreCase("Accedian")) || (Technology[k].equalsIgnoreCase("Accedian-1G"))) {
				
				ExtentTestManager.getTest().log(LogStatus.INFO, "when technology 'Accedian' is selected under Technology"
						+ "Non Termination point checkbox"
						+ "Protected checkbox");
				
				click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
				WebElement technologySelected = getwebelement(xml.getlocator("//locators/" + application + "/selectValueUnderTechnologyDropdown").replace("value", Technology[k]));  clickonTechnology(technologySelected, Technology[k]);
				
			
			//Non Termination Point	
				verifySiteOrderFields_NonterminationField(application);

				
			//Protected checkbox	
				verifySiteOrderFields_protected(application);
			
	
			}	
			
		}
}



public void technologyDropdownFor10GigE_HubAndSpoke_primary(String application) throws InterruptedException, DocumentException {
	
	String Technology = "Accedian";

	boolean technology;
	
	// Technology dropdown
			technology = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology"))
					.isDisplayed();
			sa.assertTrue(technology, "Technology dropdown is not displayed");

			click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
			List<WebElement> listoftechnology = getwebelements(xml.getlocator("//locators/" + application + "/ClassNameForDropdowns"));
			
		if(listoftechnology.size()>=1) {	
			for (WebElement technologytypes : listoftechnology) {

				Log.info("list of technology are : " + technologytypes.getText());
				Log.info("list of technology are : " + technologytypes.getText());
						ExtentTestManager.getTest().log(LogStatus.PASS,"The list of technology  inside dropdown while  adding site order is: "+technologytypes.getText());
					}
		}else {
			
			Log.info("no values are available inside technology dropdown for Add site order");
			ExtentTestManager.getTest().log(LogStatus.FAIL,"no values are available inside technology dropdown for Add site order");
		}
		
	ExtentTestManager.getTest().log(LogStatus.INFO, "when technology 'Accedian' is selected under Technology"
					+ "list of fields should occur: "
					+ "Non Termination point checkbox"
					+ "Protected checkbox");
			
			click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
			Log.info("site order to be selected is: "+Technology);
			WebElement technologySelected = getwebelement(xml.getlocator("//locators/" + application + "/selectValueUnderTechnologyDropdown").replace("value", Technology));  clickonTechnology(technologySelected, Technology);
			
		
		//Non Termination Point	
			verifySiteOrderFields_NonterminationField(application);

			
		//Protected checkbox	
			verifySiteOrderFields_protected(application);

		}

public void technologyDropdownFor10GigE_HubAndSpoke_Access(String application) throws InterruptedException, DocumentException {
	
	String Technology = "Accedian";

	boolean technology;
	
	// Technology dropdown
			technology = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology"))
					.isDisplayed();
			sa.assertTrue(technology, "Technology dropdown is not displayed");

			click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
			List<WebElement> listoftechnology = getwebelements(xml.getlocator("//locators/" + application + "/ClassNameForDropdowns"));
			
		if(listoftechnology.size()>=1) {	
			for (WebElement technologytypes : listoftechnology) {

				Log.info("list of technology are : " + technologytypes.getText());
				Log.info("list of technology are : " + technologytypes.getText());
						ExtentTestManager.getTest().log(LogStatus.PASS,"The list of technology  inside dropdown while  adding site order is: "+technologytypes.getText());
					}
		}else {
			
			Log.info("no values are available inside technology dropdown for Add site order");
			ExtentTestManager.getTest().log(LogStatus.FAIL,"no values are available inside technology dropdown for Add site order");
		}
		
	ExtentTestManager.getTest().log(LogStatus.INFO, "when technology 'Accedian' is selected under Technology"
					+ "list of fields should occur: "
					+ "Non Termination point checkbox"
					+ "Protected checkbox");
			
			click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
			Log.info("site order to be selected is: "+Technology);
			Clickon(getwebelement("//div[text()='"+ Technology +"']"));
			
		
		//Non Termination Point	
			verifySiteOrderFields_NonterminationField(application);

			
		//Protected checkbox	
			verifySiteOrderFields_protected(application);

		}

public void technologyDropdownFor1GigE_EPN_Access(String application) throws InterruptedException, DocumentException {
	
	String[] Technology = { "Actelis", "Atrica", "Overture","Alu", "Accedian-1G", "Cyan" };
	
	String[] GCROLOType = { "2A3", "1A4", "1A3", "2A4/1A4" };
	
	String[] VLANEtherType = { "S-VLAN(88A8)", "C-VLAN(8100)" };
	

	boolean technology, devicename, Nonterminationpointcheckbox, portectedcheckbox, GCRoloField, Vlan, VlanetherType;
	
	// Technology dropdown
			technology = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology"))
					.isDisplayed();
			sa.assertTrue(technology, "Technology dropdown is not displayed");

			click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
			List<WebElement> listoftechnology = getwebelements(xml.getlocator("//locators/" + application + "/ClassNameForDropdowns"));
			
		if(listoftechnology.size()>=1) {	
			for (WebElement technologytypes : listoftechnology) {

						Log.info("list of technology are : " + technologytypes.getText());
						ExtentTestManager.getTest().log(LogStatus.PASS,"The list of technology  inside dropdown while  adding site order is: "+technologytypes.getText());
						Log.info("The list of technology  inside dropdown while  adding site order is: "+technologytypes.getText());
						String technologyValue=technologytypes.getText();
			}
			
			
			for(int k=0;k<Technology.length;k++) {				
					//Actelis
						if(Technology[k].equalsIgnoreCase("Actelis")) {
							ExtentTestManager.getTest().log(LogStatus.INFO, "when technology 'Actelis' is selected under Technology"
									+ "no additional fields displays");
							
							click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
							WebElement technologySelected = getwebelement(xml.getlocator("//locators/" + application + "/selectValueUnderTechnologyDropdown").replace("value", Technology[k]));  clickonTechnology(technologySelected, Technology[k]);
						}
						
						
				//Atrica
						else if(Technology[k].equalsIgnoreCase("Atrica")) {
							
							ExtentTestManager.getTest().log(LogStatus.INFO, "when technology 'Atrica' is selected under Technology"
									+ "list of fields should occur: "
									+ " Non Termination point checkbox"
									+ " Protected checkbox"
									+ " Mapping Mode drodpown"
									+ " Device Name text field");
							
							click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
							WebElement technologySelected = getwebelement(xml.getlocator("//locators/" + application + "/selectValueUnderTechnologyDropdown").replace("value", Technology[k]));  clickonTechnology(technologySelected, Technology[k]);
							
						//Non Termination Point	
							verifySiteOrderFields_NonterminationField(application);

						//Protected checkbox	
							verifySiteOrderFields_protected(application);
							
						//Mapping Mode
							verifySiteorderFields_mappingMode(application);
							
						//Device Name Text Field
							verifySiteOrderField_deviceName(application);
						}
						
						
				//Overture
						else if(Technology[k].equalsIgnoreCase("Overture")) {
							
							ExtentTestManager.getTest().log(LogStatus.INFO, "when technology 'Overture' is selected under Technology"
									+ "list of fields should occur: "
									+ "Non Termination point checkbox"
									+ "Device Name text Field"
									+ "Protected checkbox"
									+ "GCR OLO Type dropdown"
									+ "VLAN text field"
									+ "VLAN Ether Type dropdown");
							
							click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
							WebElement technologySelected = getwebelement(xml.getlocator("//locators/" + application + "/selectValueUnderTechnologyDropdown").replace("value", Technology[k]));  clickonTechnology(technologySelected, Technology[k]);
							
							
						//Non Termination Point	
							verifySiteOrderFields_NonterminationField(application);

							
						//Protected checkbox	
							verifySiteOrderFields_protected(application);

					//Device Name	
							verifySiteOrderField_deviceName(application);
				
						
					//GCR OLO Type dropdown
							try {
						GCRoloField = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_GCRoloTypeDropdown")).isDisplayed();
						sa.assertTrue(GCRoloField, "GCR OLO Type dropdown is not Available");
						if(GCRoloField) {
						ExtentTestManager.getTest().log(LogStatus.PASS, " 'GCR OLO Type' drodpown field is displaying under 'Add Site Order' page as expected");
							
						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_GCRoloTypeDropdown")));
						List<WebElement> listofGcrOLOtype = getwebelements(xml.getlocator("//locators/" + application + "/ClassNameForDropdowns"));

						if(listofGcrOLOtype.size()>=1) {
						for (WebElement GCROlotypes : listofGcrOLOtype) {
								boolean match = false;
							for (int i = 0; i < GCROLOType.length; i++) {
								if (GCROlotypes.getText().equals(GCROLOType[i])) {
									match = true;
									Log.info("list of 'GCR OLO Type' are : " + GCROlotypes.getText());
									ExtentTestManager.getTest().log(LogStatus.PASS,"The list of 'GCR OLO Type' inside dropdown is: "+GCROlotypes.getText());
									
								}
								}
								sa.assertTrue(match);
								
						}
					}else {
						Log.info("no values are available inside 'GCR OLO Type' dropdown for Add site order");
						ExtentTestManager.getTest().log(LogStatus.FAIL,"no values are available inside 'GCR OLO Type' dropdown for Add site order");
				}
					}else {
						ExtentTestManager.getTest().log(LogStatus.FAIL, " 'GCR OLO Type' dropdown is not available under 'Add Site order' page");
					}
							}catch(Exception e) {
								e.printStackTrace();
								ExtentTestManager.getTest().log(LogStatus.FAIL, " 'GCR OLO Type' dropdown is not Available under 'Add Site order' page");
							}	
							
							
					//VLAN Text field
							try {
						Vlan=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_VLAN")).isDisplayed();
						sa.assertTrue(Vlan, " 'VLAN' text field is not available");
						if(Vlan) {
							ExtentTestManager.getTest().log(LogStatus.PASS, " 'VLAN' field is displaying as expected, when 'Technology' is selected as 'Overture' and 'IV Reference' selected as 'Access'");
						}else {
							ExtentTestManager.getTest().log(LogStatus.FAIL, " 'VLAN' field is not displaying, when 'Technology' is selected as 'Overture' and 'IV Reference' selected as 'Access'");
						}
							}catch(Exception e) {
								e.printStackTrace();
								ExtentTestManager.getTest().log(LogStatus.FAIL, " 'VLAN' text field is not Available under 'Add Site order' page");
							}
						
						
					//VLAN Ether Type
							try {
						VlanetherType = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_VLANEtherTypeDropdown")).isDisplayed();
						sa.assertTrue(VlanetherType, " 'VLAN Ether Type' dropdown is not Available");
						if(VlanetherType) {
						ExtentTestManager.getTest().log(LogStatus.PASS, " 'VLAN Ether Type' drodpown field is displaying under 'Add Site Order' page as expected");
							
						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_VLANEtherTypeDropdown")));
						List<WebElement> listofVLANethertype = getwebelements(xml.getlocator("//locators/" + application + "/ClassNameForDropdowns"));

						if(listofVLANethertype.size()>=1) {
						for (WebElement VLANEthertypes : listofVLANethertype) {
								boolean match = false;
							for (int i = 0; i < VLANEtherType.length; i++) {
								if (VLANEthertypes.getText().equals(VLANEtherType[i])) {
									match = true;
									Log.info("list of 'VLAN Ether Type' are : " + VLANEthertypes.getText());
									ExtentTestManager.getTest().log(LogStatus.PASS,"The list of 'VLAN Ether Type' inside dropdown is: "+VLANEthertypes.getText());
									
								}
								}
							sa.assertTrue(match);
								
						}
					}else {
						Log.info("no values are available inside 'VLAN Ether Type' dropdown for Add site order");
						ExtentTestManager.getTest().log(LogStatus.FAIL,"no values are available inside 'VLAN Ether Type' dropdown for Add site order");
				}
					}else {
						ExtentTestManager.getTest().log(LogStatus.FAIL, " 'VLAN Ether Type' dropdown is not available under 'Add Site order' page");
					}	
							}catch(Exception e) {
								e.printStackTrace();
								ExtentTestManager.getTest().log(LogStatus.FAIL, " 'VLAN Ether type' dropdown is not Available under 'Add Site order' page");
							}
				}		

						
					//Alu
						else if(Technology[k].equalsIgnoreCase("Alu")) {
							
							ExtentTestManager.getTest().log(LogStatus.INFO, "when technology 'Alu' is selected under Technology"
									+ " Mapping Mode dropdown "
									+ " Device Name Text Field should display");
							
							click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
							WebElement technologySelected = getwebelement(xml.getlocator("//locators/" + application + "/selectValueUnderTechnologyDropdown").replace("value", Technology[k]));  clickonTechnology(technologySelected, Technology[k]);
							
						//Mapping Mode
							verifySiteorderFields_mappingMode(application);

						//Device Name	
								verifySiteOrderField_deviceName(application);
											
						}	
						
						
				//Accedian
						else if((Technology[k].equalsIgnoreCase("Accedian-1G"))  ||  (Technology[k].equalsIgnoreCase("Accedian"))) {
							
							ExtentTestManager.getTest().log(LogStatus.INFO, "when technology 'Accedian' is selected under Technology"
									+ " Non Termination point checkbox"
									+ " Protected checkbox"
									+ " Device Name text Field should display");
							
							click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
							WebElement technologySelected = getwebelement(xml.getlocator("//locators/" + application + "/selectValueUnderTechnologyDropdown").replace("value", Technology[k]));  clickonTechnology(technologySelected, Technology[k]);
							
						//Non Termination Point	
							verifySiteOrderFields_NonterminationField(application);
							
						//Protected checkbox	
							verifySiteOrderFields_protected(application);
				

						//Device Name	
								verifySiteOrderField_deviceName(application);		
						}	
						
						
				//Cyan		
						else if(Technology[k].equalsIgnoreCase("Cyan")) {
							
							ExtentTestManager.getTest().log(LogStatus.INFO, "when technology 'Cyan' is selected under Technology"
									+ "list of fields should occur: "
									+ "Non Termination point checkbox");
							
							click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
							WebElement technologySelected = getwebelement(xml.getlocator("//locators/" + application + "/selectValueUnderTechnologyDropdown").replace("value", Technology[k]));  clickonTechnology(technologySelected, Technology[k]);
							
						//Non Termination Point	
							verifySiteOrderFields_NonterminationField(application);
							
						}
						
			}
		}else {
			
			Log.info("no values are available inside technology dropdown for Add site order");
			ExtentTestManager.getTest().log(LogStatus.FAIL,"no values are available inside technology dropdown for Add site order");
		}
		
}



public void technologyDropdownFor1GigE_EPNEOSDHselected_Access(String application) throws InterruptedException, DocumentException {
	
	String[] Technology = { "Actelis", "Atrica", "Overture","Alu", "Accedian-1G", "Cyan" };
	
	String[] GCROLOType = { "2A3", "1A4", "1A3", "2A4/1A4" };
	
	String[] VLANEtherType = { "S-VLAN(88A8)", "C-VLAN(8100)" };
	

	boolean technology, devicename, Nonterminationpointcheckbox, portectedcheckbox, GCRoloField, Vlan, VlanetherType;
	
	// Technology dropdown
			technology = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology"))
					.isDisplayed();
			sa.assertTrue(technology, "Technology dropdown is not displayed");

			click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
			List<WebElement> listoftechnology = getwebelements(xml.getlocator("//locators/" + application + "/ClassNameForDropdowns"));
			
		if(listoftechnology.size()>=1) {	
			for (WebElement technologytypes : listoftechnology) {

						Log.info("list of technology are : " + technologytypes.getText());
						ExtentTestManager.getTest().log(LogStatus.PASS,"The list of technology  inside dropdown while  adding site order is: "+technologytypes.getText());
						Log.info("The list of technology  inside dropdown while  adding site order is: "+technologytypes.getText());
						String technologyValue=technologytypes.getText();
				}
			}
			
			
			for(int k=0;k<Technology.length;k++) {				
					//Actelis
						if(Technology[k].equalsIgnoreCase("Actelis")) {
							ExtentTestManager.getTest().log(LogStatus.INFO, "when technology 'Actelis' is selected under Technology"
									+ "no additional fields displays");
							
							click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
							WebElement technologySelected = getwebelement(xml.getlocator("//locators/" + application + "/selectValueUnderTechnologyDropdown").replace("value", Technology[k]));  clickonTechnology(technologySelected, Technology[k]);
						}
						
						
				//Atrica
						else if(Technology[k].equalsIgnoreCase("Atrica")) {
							
							ExtentTestManager.getTest().log(LogStatus.INFO, "when technology 'Atrica' is selected under Technology"
									+ "list of fields should occur: "
									+ " Non Termination point checkbox"
									+ " Protected checkbox"
									+ " Mapping Mode drodpown"
									+ " Device Name text field");
							
							click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
							WebElement technologySelected = getwebelement(xml.getlocator("//locators/" + application + "/selectValueUnderTechnologyDropdown").replace("value", Technology[k]));  clickonTechnology(technologySelected, Technology[k]);
							
						//Non Termination Point	
							verifySiteOrderFields_NonterminationField(application);

						//Protected checkbox	
							verifySiteOrderFields_protected(application);
							
						//Mapping Mode
							verifySiteorderFields_mappingMode(application);
							
						//Device Name Text Field
							verifySiteOrderField_deviceName(application);
						}
						
						
				//Overture
						else if(Technology[k].equalsIgnoreCase("Overture")) {
							
							ExtentTestManager.getTest().log(LogStatus.INFO, "when technology 'Overture' is selected under Technology"
									+ "list of fields should occur: "
									+ "Non Termination point checkbox"
									+ "Device Name text Field"
									+ "Protected checkbox"
									+ "GCR OLO Type dropdown"
									+ "VLAN text field"
									+ "VLAN Ether Type dropdown");
							
							click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
							WebElement technologySelected = getwebelement(xml.getlocator("//locators/" + application + "/selectValueUnderTechnologyDropdown").replace("value", Technology[k]));  clickonTechnology(technologySelected, Technology[k]);
							
							
						//Non Termination Point	
							verifySiteOrderFields_NonterminationField(application);

							
						//Protected checkbox	
							verifySiteOrderFields_protected(application);

					//Device Name	
							verifySiteOrderField_deviceName(application);
				
						
					//GCR OLO Type dropdown
							try {
						GCRoloField = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_GCRoloTypeDropdown")).isDisplayed();
						sa.assertTrue(GCRoloField, "GCR OLO Type dropdown is not Available");
						if(GCRoloField) {
						ExtentTestManager.getTest().log(LogStatus.PASS, " 'GCR OLO Type' drodpown field is displaying under 'Add Site Order' page as expected");
							
						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_GCRoloTypeDropdown")));
						List<WebElement> listofGcrOLOtype = getwebelements(xml.getlocator("//locators/" + application + "/ClassNameForDropdowns"));

						if(listofGcrOLOtype.size()>=1) {
						for (WebElement GCROlotypes : listofGcrOLOtype) {
								boolean match = false;
							for (int i = 0; i < GCROLOType.length; i++) {
								if (GCROlotypes.getText().equals(GCROLOType[i])) {
									match = true;
									Log.info("list of 'GCR OLO Type' are : " + GCROlotypes.getText());
									ExtentTestManager.getTest().log(LogStatus.PASS,"The list of 'GCR OLO Type' inside dropdown is: "+GCROlotypes.getText());
									
								}
								}
								sa.assertTrue(match);
								
						}
					}else {
						Log.info("no values are available inside 'GCR OLO Type' dropdown for Add site order");
						ExtentTestManager.getTest().log(LogStatus.FAIL,"no values are available inside 'GCR OLO Type' dropdown for Add site order");
				}
					}else {
						ExtentTestManager.getTest().log(LogStatus.FAIL, " 'GCR OLO Type' dropdown is not available under 'Add Site order' page");
					}
							}catch(Exception e) {
								e.printStackTrace();
								ExtentTestManager.getTest().log(LogStatus.FAIL, " 'GCR OLO Type' dropdown is not Available under 'Add Site order' page");
							}	
							
							
					//VLAN Text field
							try {
						Vlan=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_VLAN")).isDisplayed();
						sa.assertTrue(Vlan, " 'VLAN' text field is not available");
						if(Vlan) {
							ExtentTestManager.getTest().log(LogStatus.PASS, " 'VLAN' field is displaying as expected, when 'Technology' is selected as 'Overture' and 'IV Reference' selected as 'Access'");
						}else {
							ExtentTestManager.getTest().log(LogStatus.FAIL, " 'VLAN' field is not displaying, when 'Technology' is selected as 'Overture' and 'IV Reference' selected as 'Access'");
						}
							}catch(Exception e) {
								e.printStackTrace();
								ExtentTestManager.getTest().log(LogStatus.FAIL, " 'VLAN' text field is not Available under 'Add Site order' page");
							}
						
						
					//VLAN Ether Type
							try {
						VlanetherType = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_VLANEtherTypeDropdown")).isDisplayed();
						sa.assertTrue(VlanetherType, " 'VLAN Ether Type' dropdown is not Available");
						if(VlanetherType) {
						ExtentTestManager.getTest().log(LogStatus.PASS, " 'VLAN Ether Type' drodpown field is displaying under 'Add Site Order' page as expected");
							
						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_VLANEtherTypeDropdown")));
						List<WebElement> listofVLANethertype = getwebelements(xml.getlocator("//locators/" + application + "/ClassNameForDropdowns"));

						if(listofVLANethertype.size()>=1) {
						for (WebElement VLANEthertypes : listofVLANethertype) {
								boolean match = false;
							for (int i = 0; i < VLANEtherType.length; i++) {
								if (VLANEthertypes.getText().equals(VLANEtherType[i])) {
									match = true;
									Log.info("list of 'VLAN Ether Type' are : " + VLANEthertypes.getText());
									ExtentTestManager.getTest().log(LogStatus.PASS,"The list of 'VLAN Ether Type' inside dropdown is: "+VLANEthertypes.getText());
									
								}
								}
							sa.assertTrue(match);
								
						}
					}else {
						Log.info("no values are available inside 'VLAN Ether Type' dropdown for Add site order");
						ExtentTestManager.getTest().log(LogStatus.FAIL,"no values are available inside 'VLAN Ether Type' dropdown for Add site order");
				}
					}else {
						ExtentTestManager.getTest().log(LogStatus.FAIL, " 'VLAN Ether Type' dropdown is not available under 'Add Site order' page");
					}	
							}catch(Exception e) {
								e.printStackTrace();
								ExtentTestManager.getTest().log(LogStatus.FAIL, " 'VLAN Ether type' dropdown is not Available under 'Add Site order' page");
							}
				}		
						
					//Alu
						else if(Technology[k].equalsIgnoreCase("Alu")) {
							
							ExtentTestManager.getTest().log(LogStatus.INFO, "when technology 'Alu' is selected under Technology"
									+ " Mapping Mode dropdown "
									+ " Device Name Text Field should display");
							
							click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
							WebElement technologySelected = getwebelement(xml.getlocator("//locators/" + application + "/selectValueUnderTechnologyDropdown").replace("value", Technology[k]));  clickonTechnology(technologySelected, Technology[k]);
							
						//Mapping Mode
							verifySiteorderFields_mappingMode(application);

						//Device Name	
								verifySiteOrderField_deviceName(application);
											
						}	
						
						
				//Accedian
						else if((Technology[k].equalsIgnoreCase("Accedian-1G"))  ||  (Technology[k].equalsIgnoreCase("Accedian"))) {
							
							ExtentTestManager.getTest().log(LogStatus.INFO, "when technology 'Accedian' is selected under Technology"
									+ " Non Termination point checkbox"
									+ " Protected checkbox"
									+ " Device Name text Field should display");
							
							click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
							WebElement technologySelected = getwebelement(xml.getlocator("//locators/" + application + "/selectValueUnderTechnologyDropdown").replace("value", Technology[k]));  clickonTechnology(technologySelected, Technology[k]);
							
						//Non Termination Point	
							verifySiteOrderFields_NonterminationField(application);
							
						//Protected checkbox	
							verifySiteOrderFields_protected(application);
				
						//Device Name	
								verifySiteOrderField_deviceName(application);		
						}	
						
					//Cyan		
						else if(Technology[k].equalsIgnoreCase("Cyan")) {
							
							ExtentTestManager.getTest().log(LogStatus.INFO, "when technology 'Cyan' is selected under Technology"
									+ "list of fields should occur: "
									+ "Non Termination point checkbox");
							
							click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
							WebElement technologySelected = getwebelement(xml.getlocator("//locators/" + application + "/selectValueUnderTechnologyDropdown").replace("value", Technology[k]));  clickonTechnology(technologySelected, Technology[k]);
							
						//Non Termination Point	
							verifySiteOrderFields_NonterminationField(application);
						}
		}
		
}


public void technologyDropdownFor1GigE_EPN_Primary(String application) throws InterruptedException, DocumentException {
	
	String[] Technology = { "Actelis", "Atrica", "Overture","Alu", "Accedian-1G", "Cyan" };
	
	boolean technology, devicename, Nonterminationpointcheckbox, portectedcheckbox, GCRoloField, Vlan, VlanetherType;
	
	
	// Technology dropdown
			technology = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology"))
					.isDisplayed();
			sa.assertTrue(technology, "Technology dropdown is not displayed");

			click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
			List<WebElement> listoftechnology = getwebelements(xml.getlocator("//locators/" + application + "/ClassNameForDropdowns"));
			
		if(listoftechnology.size()>=1) {	
			for (WebElement technologytypes : listoftechnology) {

						Log.info("list of technology are : " + technologytypes.getText());
						ExtentTestManager.getTest().log(LogStatus.PASS,"The list of technology  inside dropdown while  adding site order is: "+technologytypes.getText());
						Log.info("The list of technology  inside dropdown while  adding site order is: "+technologytypes.getText());
						String technologyValue=technologytypes.getText();
			}
			
			for(int k=0;k<Technology.length;k++) {
						
				//Actelis
						if(Technology[k].equalsIgnoreCase("Actelis")) {
							ExtentTestManager.getTest().log(LogStatus.INFO, "when technology 'Actelis' is selected under Technology"
									+ "no additional fields displays");
							
							click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
							WebElement technologySelected = getwebelement(xml.getlocator("//locators/" + application + "/selectValueUnderTechnologyDropdown").replace("value", Technology[k]));  clickonTechnology(technologySelected, Technology[k]);
						}
						
						
				//Atrica		
						else if(Technology[k].equalsIgnoreCase("Atrica")) {
							
							ExtentTestManager.getTest().log(LogStatus.INFO, "when technology 'Atrica' is selected under Technology"
									+ "list of fields should occur: "
									+ " Non Termination point checkbox"
									+ " Protected checkbox"
									+ " Device Name text field");
							
							click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
							WebElement technologySelected = getwebelement(xml.getlocator("//locators/" + application + "/selectValueUnderTechnologyDropdown").replace("value", Technology[k]));  clickonTechnology(technologySelected, Technology[k]);
							
						
						//Non Termination Point	
							verifySiteOrderFields_NonterminationField(application);

							
						//Protected checkbox	
							verifySiteOrderFields_protected(application);
							
						
						//Device Name Text Field
							verifySiteOrderField_deviceName(application);
						
						}
						
						
					//Overture	
						else if(Technology[k].equalsIgnoreCase("Overture")) {
							
							ExtentTestManager.getTest().log(LogStatus.INFO, "when technology 'Overture' is selected under Technology"
									+ "list of fields should occur: "
									+ "Non Termination point checkbox"
									+ "Device Name text Field"
									+ "Protected checkbox"
									+  " should display");
							
							click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
							WebElement technologySelected = getwebelement(xml.getlocator("//locators/" + application + "/selectValueUnderTechnologyDropdown").replace("value", Technology[k]));  clickonTechnology(technologySelected, Technology[k]);
							
						//Non Termination Point	
							verifySiteOrderFields_NonterminationField(application);
							
						//Protected checkbox	
							verifySiteOrderFields_protected(application);

					//Device Name	
						verifySiteOrderField_deviceName(application);
				}		
						
						
					//Alu	
						else if(Technology[k].equals("Alu")) {
							
							ExtentTestManager.getTest().log(LogStatus.INFO, "when technology 'Alu' is selected under Technology"
									+ " Device Name Text Field should display");
							
							click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
							WebElement technologySelected = getwebelement(xml.getlocator("//locators/" + application + "/selectValueUnderTechnologyDropdown").replace("value", Technology[k]));  clickonTechnology(technologySelected, Technology[k]);
					
						//Device Name	
								verifySiteOrderField_deviceName(application);
					
						}	
						
						
				//Accedian		
						else if((Technology[k].equalsIgnoreCase("Accedian-1G"))  ||  (Technology[k].equalsIgnoreCase("Accedian"))) {
							
							ExtentTestManager.getTest().log(LogStatus.INFO, "when technology 'Accedian' is selected under Technology"
									+ " Non Termination point checkbox"
									+ " Protected checkbox"
									+ " Device Name text Field should display");
							
							click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
							WebElement technologySelected = getwebelement(xml.getlocator("//locators/" + application + "/selectValueUnderTechnologyDropdown").replace("value", Technology[k]));  clickonTechnology(technologySelected, Technology[k]);
							
						
						//Non Termination Point	
							verifySiteOrderFields_NonterminationField(application);

							
						//Protected checkbox	
							verifySiteOrderFields_protected(application);
							

						//Device Name	
								verifySiteOrderField_deviceName(application);
						}	
						
				
				//Cyan
						else if(Technology[k].equalsIgnoreCase("Cyan")) {
							
							ExtentTestManager.getTest().log(LogStatus.INFO, "when technology 'Cyan' is selected under Technology"
									+ "list of fields should occur: "
									+ "Non Termination point checkbox");
							
							click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
							WebElement technologySelected = getwebelement(xml.getlocator("//locators/" + application + "/selectValueUnderTechnologyDropdown").replace("value", Technology[k]));  clickonTechnology(technologySelected, Technology[k]);
							
						//Non Termination Point	
							verifySiteOrderFields_NonterminationField(application);
						}
			}
		}else {
			
			Log.info("no values are available inside technology dropdown for Add site order");
			ExtentTestManager.getTest().log(LogStatus.FAIL,"no values are available inside technology dropdown for Add site order");
		}
		
}


public void technologyDropdownFor1GigE_EPNEOSDHselected_Primary(String application) throws InterruptedException, DocumentException {
	
	String[] Technology = { "Actelis", "Atrica", "Overture","Alu", "Accedian-1G", "Cyan" };
	
	boolean technology;
	
	// Technology dropdown
			technology = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology"))
					.isDisplayed();
			sa.assertTrue(technology, "Technology dropdown is not displayed");

			click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
			List<WebElement> listoftechnology = getwebelements(xml.getlocator("//locators/" + application + "/ClassNameForDropdowns"));
			
		if(listoftechnology.size()>=1) {	
			for (WebElement technologytypes : listoftechnology) {

						Log.info("list of technology are : " + technologytypes.getText());
						ExtentTestManager.getTest().log(LogStatus.PASS,"The list of technology  inside dropdown while  adding site order is: "+technologytypes.getText());
						Log.info("The list of technology  inside dropdown while  adding site order is: "+technologytypes.getText());
						String technologyValue=technologytypes.getText();
			}
			
			for(int k=0;k<Technology.length;k++) {
						
				//Actelis
						if(Technology[k].equalsIgnoreCase("Actelis")) {
							ExtentTestManager.getTest().log(LogStatus.INFO, "when technology 'Actelis' is selected under Technology"
									+ "no additional fields displays");
							
							click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
							WebElement technologySelected = getwebelement(xml.getlocator("//locators/" + application + "/selectValueUnderTechnologyDropdown").replace("value", Technology[k]));  clickonTechnology(technologySelected, Technology[k]);
						}
						
						
				//Atrica		
						else if(Technology[k].equalsIgnoreCase("Atrica")) {
							
							ExtentTestManager.getTest().log(LogStatus.INFO, "when technology 'Atrica' is selected under Technology"
									+ "list of fields should occur: "
									+ " Non Termination point checkbox"
									+ " Protected checkbox"
									+ " Device Name text field");
							
							click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
							WebElement technologySelected = getwebelement(xml.getlocator("//locators/" + application + "/selectValueUnderTechnologyDropdown").replace("value", Technology[k]));  clickonTechnology(technologySelected, Technology[k]);
							
						
						//Non Termination Point	
							verifySiteOrderFields_NonterminationField(application);

						//Protected checkbox	
							verifySiteOrderFields_protected(application);
							
						//Device Name Text Field
							verifySiteOrderField_deviceName(application);
						
						}
						
						
					//Overture	
						else if(Technology[k].equalsIgnoreCase("Overture")) {
							
							ExtentTestManager.getTest().log(LogStatus.INFO, "when technology 'Overture' is selected under Technology"
									+ "list of fields should occur: "
									+ "Non Termination point checkbox"
									+ "Device Name text Field"
									+ "Protected checkbox"
									+  " should display");
							
							click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
							WebElement technologySelected = getwebelement(xml.getlocator("//locators/" + application + "/selectValueUnderTechnologyDropdown").replace("value", Technology[k]));  clickonTechnology(technologySelected, Technology[k]);
							
						//Non Termination Point	
							verifySiteOrderFields_NonterminationField(application);
							
						//Protected checkbox	
							verifySiteOrderFields_protected(application);

							//VLAN Text field
						boolean Vlan=false;	
							try {
						Vlan=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_VLAN")).isDisplayed();
						sa.assertTrue(Vlan, " 'VLAN' text field is not available");
						if(Vlan) {
							ExtentTestManager.getTest().log(LogStatus.PASS, " 'VLAN' field is displaying as expected, when 'Technology' is selected as 'Overture' and 'IV Reference' selected as 'Access'");
						}else {
							ExtentTestManager.getTest().log(LogStatus.FAIL, " 'VLAN' field is not displaying, when 'Technology' is selected as 'Overture' and 'IV Reference' selected as 'Access'");
						}
							}catch(Exception e) {
								e.printStackTrace();
								ExtentTestManager.getTest().log(LogStatus.FAIL, " 'VLAN' text field is not Available under 'Add Site order' page");
							}
						
						
					//VLAN Ether Type
							boolean VlanetherType=false;
							try {
						VlanetherType = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_VLANEtherTypeDropdown")).isDisplayed();
						sa.assertTrue(VlanetherType, " 'VLAN Ether Type' dropdown is not Available");
						if(VlanetherType) {
						ExtentTestManager.getTest().log(LogStatus.PASS, " 'VLAN Ether Type' drodpown field is displaying under 'Add Site Order' page as expected");
							
						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_VLANEtherTypeDropdown")));
						List<WebElement> listofVLANethertype = getwebelements(xml.getlocator("//locators/" + application + "/ClassNameForDropdowns"));

						if(listofVLANethertype.size()>=1) {
						for (WebElement VLANEthertypes : listofVLANethertype) {
									Log.info("list of 'VLAN Ether Type' are : " + VLANEthertypes.getText());
									ExtentTestManager.getTest().log(LogStatus.PASS,"The list of 'VLAN Ether Type' inside dropdown is: "+VLANEthertypes.getText());
								
						}
					}else {
						Log.info("no values are available inside 'VLAN Ether Type' dropdown for Add site order");
						ExtentTestManager.getTest().log(LogStatus.FAIL,"no values are available inside 'VLAN Ether Type' dropdown for Add site order");
				}
					}else {
						ExtentTestManager.getTest().log(LogStatus.FAIL, " 'VLAN Ether Type' dropdown is not available under 'Add Site order' page");
					}	
							}catch(Exception e) {
								e.printStackTrace();
								ExtentTestManager.getTest().log(LogStatus.FAIL, " 'VLAN Ether type' dropdown is not Available under 'Add Site order' page");
							}
				}		

							
						
					//Alu	
						else if(Technology[k].equals("Alu")) {
							
							ExtentTestManager.getTest().log(LogStatus.INFO, "when technology 'Alu' is selected under Technology"
									+ " Device Name Text Field should display");
							
							click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
							WebElement technologySelected = getwebelement(xml.getlocator("//locators/" + application + "/selectValueUnderTechnologyDropdown").replace("value", Technology[k]));  clickonTechnology(technologySelected, Technology[k]);
					
						//Device Name	
								verifySiteOrderField_deviceName(application);
					
						}	
						
						
				//Accedian		
						else if((Technology[k].equalsIgnoreCase("Accedian-1G"))  ||  (Technology[k].equalsIgnoreCase("Accedian"))) {
							
							ExtentTestManager.getTest().log(LogStatus.INFO, "when technology 'Accedian' is selected under Technology"
									+ " Non Termination point checkbox"
									+ " Protected checkbox"
									+ " Device Name text Field should display");
							
							click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
							WebElement technologySelected = getwebelement(xml.getlocator("//locators/" + application + "/selectValueUnderTechnologyDropdown").replace("value", Technology[k]));  clickonTechnology(technologySelected, Technology[k]);
							
						
						//Non Termination Point	
							verifySiteOrderFields_NonterminationField(application);

							
						//Protected checkbox	
							verifySiteOrderFields_protected(application);
							

						//Device Name	
//								verifySiteOrderField_deviceName(application);
						}	
						
				
				//Cyan
						else if(Technology[k].equalsIgnoreCase("Cyan")) {
							
							ExtentTestManager.getTest().log(LogStatus.INFO, "when technology 'Cyan' is selected under Technology"
									+ "list of fields should occur: "
									+ "Non Termination point checkbox");
							
							click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
							WebElement technologySelected = getwebelement(xml.getlocator("//locators/" + application + "/selectValueUnderTechnologyDropdown").replace("value", Technology[k]));  clickonTechnology(technologySelected, Technology[k]);
							
						//Non Termination Point	
							verifySiteOrderFields_NonterminationField(application);
						}
			}
		}else {
			
			Log.info("no values are available inside technology dropdown for Add site order");
			ExtentTestManager.getTest().log(LogStatus.FAIL,"no values are available inside technology dropdown for Add site order");
		}
		
}


public void technologyDropdownFor10GigE_EPN(String application) throws InterruptedException, DocumentException {
	
	String[] Technology = {"Accedian"};

	boolean technology, Nonterminationpointcheckbox, portectedcheckbox;
	
	// Technology dropdown
			technology = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology"))
					.isDisplayed();
			sa.assertTrue(technology, "Technology dropdown is not displayed");

			click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
			List<WebElement> listoftechnology = getwebelements(xml.getlocator("//locators/" + application + "/ClassNameForDropdowns"));
			
		if(listoftechnology.size()>=1) {	
			for (WebElement technologytypes : listoftechnology) {

				boolean match = false;
				for (int i = 0; i < Technology.length; i++) {
					if (technologytypes.getText().equals(Technology[i])) {
						match = true;
						Log.info("list of technology are : " + technologytypes.getText());
						ExtentTestManager.getTest().log(LogStatus.PASS,"The list of technology  inside dropdown while  adding site order is: "+technologytypes.getText());
					}
				}
				sa.assertTrue(match);
			}
		}else {
			
			Log.info("no values are available inside technology dropdown for Add site order");
			ExtentTestManager.getTest().log(LogStatus.FAIL,"no values are available inside technology dropdown for Add site order");
		}
		
	ExtentTestManager.getTest().log(LogStatus.INFO, "when technology 'Accedian' is selected under Technology"
					+ "list of fields should occur: "
					+ "Non Termination point checkbox"
					+ "Protected checkbox");
			
			click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
			Clickon(getwebelement("//div[text()='" + Technology + "']"));
			
			
		//Non Termination Point	
			try {
			Nonterminationpointcheckbox=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_nonterminationpoint")).isDisplayed();
			sa.assertTrue(Nonterminationpointcheckbox, "On selecting 'Accedian' under Technology, Non termination point checkbox is not available");
			if(Nonterminationpointcheckbox) {
				ExtentTestManager.getTest().log(LogStatus.PASS, " 'Non Termination Point' checkbox is displayed under 'Add Site order' page as expected");
			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Non Termination Point' checkbox is not Available under 'Add Site order' page");
			}
			
			boolean nonTerminaionpointselection=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_nonterminationpoint")).isSelected();
			sa.assertFalse(nonTerminaionpointselection,"Non-termination point checbox under Add site is selected by default");
			if(nonTerminaionpointselection) {
				ExtentTestManager.getTest().log(LogStatus.PASS, " 'Non-Termination Point' checkbox is not selected by default as expected");
			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Non-Termination Point' checkbox is selected by default");
			}
			}catch(Exception e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Non Termination Point' checkbox is not Available under 'Add Site order' page");
			}

			
		//Protected checkbox
			try {
			portectedcheckbox=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_protected")).isDisplayed();
			sa.assertTrue(portectedcheckbox, "On selecting 'Accedian' under Technology, protected checkbox is not available");
			if(portectedcheckbox) {
				ExtentTestManager.getTest().log(LogStatus.PASS, " 'Protected' checkbox is displayed under 'Add Site order' page as expected");
			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Protected' checkbox is not available under 'Add Site order' page");
			}
			boolean protectedSelection=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_protected")).isSelected();
			sa.assertFalse(protectedSelection,"Protected checbox under Add site is selected by default");
			if(protectedSelection) {
				ExtentTestManager.getTest().log(LogStatus.PASS, " 'Protected' checkbox is not selected by default as expected");
			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Protected' checkbox is selected by default");
			}
			}catch(Exception e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Protected' checkbox is not Available under 'Add Site order' page");
			}

		}
	
	public void selectRowForsiteorder(String Application, String siteordernumber, String interfaceSpeed)
			throws InterruptedException, DocumentException, IOException {

		 
		waitforPagetobeenable();
		
		Log.info("-----------------------------" + siteordernumber + "---------------------");
		int TotalPages;
 
		scrolltoend();
		Thread.sleep(3000);
			List<WebElement> results = null;
			
				if(interfaceSpeed.equals("10GigE")) {
					results=getwebelements("//div[div[text()='Site Orders']]//following-sibling::div//div[text()='"+ siteordernumber + "']");
				}else if(interfaceSpeed.equals("1GigE")) {
					results=getwebelements("//div[div[text()='Site Orders']]//following-sibling::div//div[text()='"+ siteordernumber + "']");
				}
		
			int numofrows = results.size();
			Log.info("no of results: " + numofrows);
			boolean resultflag;

						resultflag = results.get(0).isDisplayed();
						Log.info("status of result: " + resultflag);
						if (resultflag) {
							Log.info(results.get(0).getText());
							results.get(0).click();
							Thread.sleep(5000);
							click_commonMethod(Application, "Action", "Actiondropdown_siteorder", xml);

							Thread.sleep(5000);

						}
	}

	public void PageNavigation_NextPage(String Application) throws InterruptedException, DocumentException {

		Clickon(getwebelement(xml.getlocator("//locators/" + Application + "/Pagenavigationfornextpage")));
		Thread.sleep(3000);

	}

	public void AddCPEdevice(String application, String cpename, String vender, String snmpro, String managementAddress,
			String Mepid, String poweralarm, String Mediaselection, String Macaddress, String serialNumber,
			String hexaSerialnumber, String linkLostForwarding)
			throws InterruptedException, DocumentException, IOException {

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/CPEdevice_adddevicelink")));
		Thread.sleep(3000);

//			 driver.navigate().to("http://172.30.246.170:4400/#/addLanLinkDirectFiberSiteDevice");
		Thread.sleep(3000);

		Log.info("enter details to add CPE device");

		Log.info("Adding details to the fields to create a CPE device");

		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_Name")), cpename);

//      	Select(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_vender")), vender);

		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_snmpro")), snmpro);

		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_manageaddress")),
				managementAddress);

		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_mepid")), Mepid);

//      	Select(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_poweralarm")), poweralarm);

//      	Select(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_mediaselection")), Mediaselection);

		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_macaddress")), Macaddress);

		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_serialnumber")),
				serialNumber);

		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_hexaserialnumber")),
				hexaSerialnumber);

		if (linkLostForwarding.equalsIgnoreCase("yes")) {

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_linklostforowarding")));
		}

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Next_Button")));

		Thread.sleep(3000);

	}

	public void viewCPEdevice(String application, String cpename, String vender, String snmpro,
			String managementAddress, String Mepid, String poweralarm, String Mediaselection, String Macaddress,
			String serialNumber, String hexaSerialnumber, String linkLostForwarding)
			throws InterruptedException, DocumentException, IOException {

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/viewCPEdevicelink")));
		Thread.sleep(3000);

		String name = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/viewCPEdevice_name")));
		sa.assertEquals(name, cpename, "name is displaying as expected");

		String vendor_model = Gettext(
				getwebelement(xml.getlocator("//locators/" + application + "/viewCPEdevice_vendor")));
		sa.assertEquals(vendor_model, vender, "Vendor name is displaying as expected");

		String snmPro = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/viewCPEdevice_snmpro")));
		sa.assertEquals(snmPro, snmpro, "SNM pro name is displaying as expected");

		String manageaddress = Gettext(
				getwebelement(xml.getlocator("//locators/" + application + "/viewCPEdevice_managementAddress")));
		sa.assertEquals(manageaddress, managementAddress, "management address is displaying as expected");

		String mEPid = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/viewCPEdevice_mepid")));
		sa.assertEquals(mEPid, Mepid, "MEP Id is displaying as expected");

		String powerAlarm = Gettext(
				getwebelement(xml.getlocator("//locators/" + application + "/viewCPEdevice_poweralarm")));
		sa.assertEquals(powerAlarm, poweralarm, "power alarm is displaying as expected");

		String mediaSelection = Gettext(
				getwebelement(xml.getlocator("//locators/" + application + "/viewCPEdevice_mediaselection")));
		sa.assertEquals(mediaSelection, Mediaselection, "Media selection is displaying as expected");

		String linkLostforwarding = Gettext(
				getwebelement(xml.getlocator("//locators/" + application + "/viewCPEdevice_linklostforwarding")));
		sa.assertEquals(linkLostforwarding, linkLostForwarding, "link lost forwarding is displaying as expected");

		String macAddress = Gettext(
				getwebelement(xml.getlocator("//locators/" + application + "/viewCPEdevice_macaddress")));
		sa.assertEquals(macAddress, Macaddress, "mac address is displaying as expected");

		String Serialnumber = Gettext(
				getwebelement(xml.getlocator("//locators/" + application + "/viewCPEdevice_serialnumber")));
		sa.assertEquals(Serialnumber, serialNumber, "Serial number is displaying as expected");

		String hexaSerialNumber = Gettext(
				getwebelement(xml.getlocator("//locators/" + application + "/viewCPEdevice_hexaserialnumber")));
		sa.assertEquals(hexaSerialNumber, hexaSerialnumber, "Hexa serial number is displaying as expected");

		sa.assertAll();

	}

	public void eDITCPEdevice(String application, String cpename, String vender, String snmpro,
			String managementAddress, String Mepid, String poweralarm, String Mediaselection, String Macaddress,
			String serialNumber, String hexaSerialnumber, String linkLostForwarding)
			throws InterruptedException, DocumentException, IOException {

		click_commonMethod(application, "Action", "viewPCEdevice_Actiondropdown", xml);
		Thread.sleep(1000);

		click_commonMethod(application, "EditCPEdevice link", "EditCPEdevicelink", xml);
		waitforPagetobeenable();

//		  Clickon(getwebelement(xml.getlocator("//locators/" + application + "/EditCPEdevlielink_underEquipment")));
//	     Thread.sleep(3000);

		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_Name")), cpename);

//      	Select(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_vender")), vender);

		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_snmpro")), snmpro);

		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_manageaddress")),
				managementAddress);

		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_mepid")), Mepid);

//      	Select(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_poweralarm")), poweralarm);

//      	Select(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_mediaselection")), Mediaselection);

		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_macaddress")), Macaddress);

		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_serialnumber")),
				serialNumber);

		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_hexaserialnumber")),
				hexaSerialnumber);

		if (linkLostForwarding.equalsIgnoreCase("yes")) {

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_linklostforowarding")));
		}

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Next_Button")));
		Thread.sleep(2000);

		Log.info("Next buttton is not working under Add cpe device...... so gonnah select CAncel button");
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/cancelButton")));

	}

	public void addCPEdeviceforIntermediateequipment(String application, String cpename, String vender, String snmpro,
			String managementAddress, String Mepid, String poweralarm, String Mediaselection, String Macaddress,
			String serialNumber, String hexaSerialnumber, String linkLostForwarding)
			throws InterruptedException, DocumentException, IOException {

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/CPEdevice_adddevicelink")));
		Thread.sleep(3000);

		Log.info("enter details to add CPE device for intermediate equipment");

		Log.info("Adding details to the fields to create a CPE device");

		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_Name")), cpename);

//    	Select(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_vender")), vender);

		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_snmpro")), snmpro);

		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_manageaddress")),
				managementAddress);

		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_mepid")), Mepid);

//    	Select(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_poweralarm")), poweralarm);

//    	Select(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_mediaselection")), Mediaselection);

		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_macaddress")), Macaddress);

		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_serialnumber")),
				serialNumber);

		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_hexaserialnumber")),
				hexaSerialnumber);

		if (linkLostForwarding.equalsIgnoreCase("yes")) {

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_linklostforowarding")));
		}

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Next_Button")));

		Log.info("Next buttton is not working under Add cpe device...... so gonnah select CAncel button");
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/cancelButton")));

		Thread.sleep(3000);

	}
	public void verifyFieldsandAddCPEdevicefortheserviceselected_1G(String application,String interfaceSpeed,  String cpename, String vender, String snmpro,
			String managementAddress, String Mepid, String poweralarm, String mediaSelection, String Macaddress,String serialNumber, 
			String hexaSerialnumber, String linkLostForwarding, String newmanagementAddress, String existingmanagementAddress,
			String manageaddressdropdownvalue, String technologySelected, String vpntopology)
			throws InterruptedException, DocumentException, IOException {
		
		scrolltoend();
		Thread.sleep(3000);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying fields for CPE device under Equipment");
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/CPEdevice_adddevicelink")));
		Thread.sleep(1000);
		waitforPagetobeenable();
		
		if(technologySelected.equalsIgnoreCase("Accedian-1G")) {
			equip_adddevi_Accedian1G(application, interfaceSpeed, cpename, vender, snmpro, managementAddress, Mepid, poweralarm, mediaSelection, 
				Macaddress, serialNumber, hexaSerialnumber, linkLostForwarding, newmanagementAddress, existingmanagementAddress,
				manageaddressdropdownvalue);
			
		}else {
			
			if(technologySelected.equalsIgnoreCase("Atrica") && vpntopology.equals("Hub&Spoke")) {
				selectTechnology_HubAndSpoke(application);
			}
			
		equip_addDevice_1G(application, interfaceSpeed, cpename, vender, snmpro, managementAddress, Mepid, poweralarm, mediaSelection, 
				Macaddress, serialNumber, hexaSerialnumber, linkLostForwarding, newmanagementAddress, existingmanagementAddress,
				manageaddressdropdownvalue);
		}
		
		}
	
	
	public void equip_adddevi_Accedian1G(String application,String interfaceSpeed,  String cpename, String vender, String snmpro,
			String managementAddress, String Mepid, String poweralarm, String mediaSelection, String Macaddress,String serialNumber, 
			String hexaSerialnumber, String linkLostForwarding, String newmanagementAddress, String existingmanagementAddress,
			String manageaddressdropdownvalue) throws InterruptedException, DocumentException, IOException {
		
		
		click_commonMethod(application, "OK", "obutton_spanTag", xml);
		Thread.sleep(3000);
		
		try {
			
		String linklostForwardingcheckboxstate="disabled"; 
			
		String[] Vender= {"Accedian-1G 1GigE-MetroNID-GT", "Accedian-1G 1GigE-MetroNID-GT-S", "Accedian-1G GX"};
		
		String[] powerAlarm= {"DC Single Power Supply - Feed A", "DC Dual Power Supply - Feed-A+B", "AC Single Power Supply - Feed A", "AC Dual Power Supply -Feed A+B"};
		
		String expectedDeviceNameFieldAutopopulatedValue="<Device>.lanlink.dcn.colt.net";
		
		String expectedValueForSnmpro= "JdhquA5";
		
		
		
			// Vendor/Model Error Message
			device_vendorModelWarningMessage(application);

			// Management Address Error Message
			device_managementAddressWarningMessage(application);

			// Power Alarm Error Message
			device_powerAlarmWarningMessage(application);

			//serial number Eror Message
			device_serialNumberWarningMessage(application);
			
			//Hexa serial Number
			device_hexaSerialNumberWarningMessage(application);
	
			
			//Vendor/Model
			device_vendorModel(application, Vender, vender);      
		
			//Snmpro
			device_snmPro(application, snmpro);
			
			//Management Address dropdown
			device_managementAddress(application, existingmanagementAddress, newmanagementAddress, managementAddress);
			
			//MEP Id
			device_mepID(application, Mepid);
		
			//Power Alarm	
			device_powerAlarm(application, powerAlarm, poweralarm);
			
			//Serial Number
			device_serialNumber(application,serialNumber);
		
		    //Link lost Forwarding
			device_linklostForwarding(application, linkLostForwarding, linklostForwardingcheckboxstate);
		    
		scrolltoend();
		Thread.sleep(3000);
			//OK button
			device_okbutton(application);
			
			//cancel button
			device_cancelButton(application);
			
			click_commonMethod(application, "OK", "AddCPEdevice_OKbutton", xml);
			
			scrollToTop();
			Thread.sleep(1000);
			
			
			warningMessage_commonMethod(application, "warningmEssage1_devicename", "Device Name", xml);
			warningMessage_commonMethod(application, "warningmEssage2_devicename", "Device Name", xml);
			warningMessage_commonMethod(application, "warningmEssage3_devicename", "Device Name", xml);
			
			//Name
			device_nameField(application, cpename, expectedDeviceNameFieldAutopopulatedValue);
			
			scrolltoend();
			Thread.sleep(1000);
			click_commonMethod(application, "OK", "AddCPEdevice_OKbutton", xml);
			
		    
		sa.assertAll();
		
		}catch(AssertionError e) {
			
			e.printStackTrace();
			////ExtentTestManager.getTest().log(LogStatus.FAIL, "FAiled while verify the fields for Add CPE device");
			
		}

	}
	
	public void equip_addDevice_1G(String application,String interfaceSpeed,  String cpename, String vender, String snmpro,
			String managementAddress, String Mepid, String poweralarm, String mediaSelection, String Macaddress,
			String serialNumber, String hexaSerialnumber, String linkLostForwarding, String newmanagementAddress, String existingmanagementAddress, String manageaddressdropdownvalue) throws InterruptedException, DocumentException {
		
		try {
			
		String linklostForwardingcheckboxstate="enabled"; 
		
		String[] Vender= {"Overture ISG-26", "Overture ISG-26R", "Overture ISG-26S", "Overture ISG140", "Overture ISG180", "Overture ISG6000"};
		
		String[] powerAlarm= {"AC", "DC"};
		
		String[] Mediaselection= {"SFP-A with SFP-B","RJ45-A with SFP-B"};	
		
		String expectedDeviceNameFieldAutopopulatedValue="<Device>.lanlink.dcn.colt.net";
		
		String mepValue="null";
		
		
		click_commonMethod(application, "OK", "obutton_spanTag", xml);
		Thread.sleep(5000);
		
			// Vendor/Model Error Message
			device_vendorModelWarningMessage(application);

			// Management Address Error Message
			device_managementAddressWarningMessage(application);

			// Power Alarm Error Message
			device_powerAlarmWarningMessage(application);
			
		//Media Selection Error Message
//		device_mediaSelectionWarningMessage(application);
			
			
		//MAC Address Error Message
		device_macAddressWarningMessage(application);
			

		//Vendor/Model
		device_vendorModel(application, Vender, vender);      
	
		//Snmpro
		device_snmPro(application, snmpro);
		
		//Management Address dropdown
		device_managementAddress(application, existingmanagementAddress, newmanagementAddress, managementAddress);
		
		//MEP Id
		device_mepID(application, Mepid);
	
		//Power Alarm	
		device_powerAlarm(application, powerAlarm, poweralarm);
	
	scrolltoend();
	Thread.sleep(3000);
	
		//Media Selection
		device_mediaSelection(application, Mediaselection, mediaSelection);
		
		//MAC Address
		device_MAcaddress(application, Macaddress);
	    
	    //Link lost Forwarding
		device_linklostForwarding(application, linkLostForwarding, linklostForwardingcheckboxstate);
	    
		scrolltoend();
		Thread.sleep(3000);
	    
	    //OK button
		device_okbutton(application);
		
		//cancel button
		device_cancelButton(application);
			
		
		click_commonMethod(application, "OK", "AddCPEdevice_OKbutton", xml);
		
//		scrollToTop();
//		Thread.sleep(1000);
		
		
		warningMessage_commonMethod(application, "warningmEssage1_devicename", "Device Name", xml);
		warningMessage_commonMethod(application, "warningmEssage2_devicename", "Device Name", xml);
		warningMessage_commonMethod(application, "warningmEssage3_devicename", "Device Name", xml);
		
		//Name
		device_nameField(application, cpename, expectedDeviceNameFieldAutopopulatedValue);
		
		scrolltoend();
		Thread.sleep(1000);
		click_commonMethod(application, "OK", "AddCPEdevice_OKbutton", xml);
		
		sa.assertAll();
		
		}catch(AssertionError e) {
			
			e.printStackTrace();
			////ExtentTestManager.getTest().log(LogStatus.FAIL, "FAiled while verify the fields for Add CPE device");
			
		}
		
		
	}
	
	public void verifyFieldsandAddCPEdevicefortheserviceselected_10G(String application,String interfaceSpeed,  String cpename, String vender, String snmpro,
			String managementAddress, String Mepid, String poweralarm, String mediaSelection, String Macaddress,
			String serialNumber, String hexaSerialnumber, String linkLostForwarding, String newmanagementAddress, String existingmanagementAddress, String manageaddressdropdownvalue)
			throws InterruptedException, DocumentException, IOException {
		
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying fields for CPE device under Equipment");
	scrolltoend();
	Thread.sleep(3000);
	
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/CPEdevice_adddevicelink")));
		Thread.sleep(9000);
		
		
		click_commonMethod(application, "OK", "obutton_spanTag", xml);
		Thread.sleep(3000);
		
		try {
			
		String linklostForwardingcheckboxstate="disabled"; 
			
		String[] Vender= {"Accedian 10GigE-MetroNode-CE-2Port"};
		
		String[] powerAlarm= {"DC Single Power Supply - PSU A", "DC Dual Power Supply - PSU-A+B"};
		
		String expectedDeviceNameFieldAutopopulatedValue="<Device>-10G.lanlink.dcn.colt.net";
		
		String MEPid="5555";
		
		String expectedValueForSnmpro= "JdhquA5";
		
		
		
			// Vendor/Model Error Message
			device_vendorModelWarningMessage(application);

			// Management Address Error Message
			device_managementAddressWarningMessage(application);

			// Power Alarm Error Message
			device_powerAlarmWarningMessage(application);

			//serial number Eror Message
			device_serialNumberWarningMessage(application);
			
			//Hexa serial Number
			device_hexaSerialNumberWarningMessage(application);
	

			//Vendor/Model
			device_vendorModel(application, Vender, vender);      
		
			//Snmpro
			device_snmPro(application, snmpro);
			
			//Management Address dropdown
			device_managementAddress(application, existingmanagementAddress, newmanagementAddress, managementAddress);
			
			//MEP Id
			device_mepID(application, Mepid);
		
			//Power Alarm	
			device_powerAlarm(application, powerAlarm, poweralarm);
			
			//Serial Number
			device_serialNumber(application,serialNumber);
		
		    //Link lost Forwarding
			device_linklostForwarding(application, linkLostForwarding, linklostForwardingcheckboxstate);
		    
		scrolltoend();
		Thread.sleep(3000);
			//OK button
			device_okbutton(application);
			
			//cancel button
			device_cancelButton(application);
			
			click_commonMethod(application, "OK", "AddCPEdevice_OKbutton", xml);
			
			scrollToTop();
			Thread.sleep(1000);
			
			
			warningMessage_commonMethod(application, "warningmEssage1_devicename", "Device Name", xml);
			warningMessage_commonMethod(application, "warningmEssage2_devicename", "Device Name", xml);
			warningMessage_commonMethod(application, "warningmEssage3_devicename", "Device Name", xml);
			
			//Name
			device_nameField(application, cpename, expectedDeviceNameFieldAutopopulatedValue);
			
			scrolltoend();
			Thread.sleep(1000);
			click_commonMethod(application, "OK", "AddCPEdevice_OKbutton", xml);
			
		    
		sa.assertAll();
		
		}catch(AssertionError e) {
			
			e.printStackTrace();
			//ExtentTestManager.getTest().log(LogStatus.FAIL, "FAiled while verify the fields for Add CPE device");
			
		}
			 
		}

	
	
	public void AddCPEdevicefortheserviceselected(String application, String cpename, String vender, String snmpro,
			String managementAddress, String Mepid, String poweralarm, String Mediaselection, String Macaddress,
			String serialNumber, String hexaSerialnumber, String linkLostForwarding, String newmanagementAddress, String existingmanagementAddress, String manageaddressdropdownvalue)
			throws InterruptedException, DocumentException, IOException {

		ExtentTestManager.getTest().log(LogStatus.INFO, "Adding device for equipment");
		
		Thread.sleep(5000);
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/CPEdevice_adddevicelink")));
		
		Thread.sleep(3000);

		Log.info("Adding details to the fields to create a CPE device");

	

	//vender/model	
		try {
			if(vender.equalsIgnoreCase("null")) {
				
				ExtentTestManager.getTest().log(LogStatus.FAIL, "No values has been passed for Mandatory 'Vendor/Model' dropdown for adding device under Equipment");
			}
		
			else {	
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_vender")));
				
			}
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "add cpe device  vender/model dropdown field not avaialble");
		}
		
		
		try {
			if(vender.equalsIgnoreCase("null")) {
				
				Log.info("No values has been passed for Mandatory 'Vendor/Model' dropdown for adding device under Equipment");
				
			}
		
			else {	
		Clickon(getwebelement("//div[label[text()='Vender/Model']]//div[text()='"+vender +"']"));
		ExtentTestManager.getTest().log(LogStatus.PASS, vender + " is the value passed for Mandatory 'Vendor/Model' dropdown for adding device under Equipment");
			}
			}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "FAilure at Vender/model dropdown. It does not have the value provided as input");
		}
	
	
	//snmpro	
		try {
		if(snmpro.equalsIgnoreCase("null"))	{
			
			ExtentTestManager.getTest().log(LogStatus.FAIL, "No values has been passed for Mandatory field 'snmpro' for adding device under Equipment");
			
		}else {
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_snmpro")), snmpro);
			ExtentTestManager.getTest().log(LogStatus.PASS, snmpro + " is the value passed for Mandatory 'Snmpro' field for adding device under Equipment");
		}
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Snm pro' field is not available");
		}catch(Exception err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Not able to enter value inside the 'Snm pro' field");
		}

		
//Manage address text field	
		
	if(newmanagementAddress.equalsIgnoreCase("yes") && existingmanagementAddress.equalsIgnoreCase("no")) {	
		try {
			if(managementAddress.equalsIgnoreCase("null")) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "No values has been passed for Mandatory field 'Manage Address' for adding device under Equipment");
			}else {
		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_manageaddress")),
				managementAddress);
		ExtentTestManager.getTest().log(LogStatus.PASS, managementAddress + " is the value passed for Mandatory 'Management Address' field for adding device under Equipment");
			}
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Manage Address' field is not available");
		}catch(Exception err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Not able to enter value inside the 'Manage Address' field");
		}
	}
//Manage address dropdown
	else if(newmanagementAddress.equalsIgnoreCase("no") && existingmanagementAddress.equalsIgnoreCase("yes")) {	
		try {
		if(manageaddressdropdownvalue.equalsIgnoreCase("null")) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "No values has been passed for Mandatory field 'Manage Address' dropdownfor adding device under Equipment");
		}else {
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_selectSubnettogglebutton")));
			Thread.sleep(3000);
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_manageaddressdropdown")));
			Thread.sleep(3000);
		}

		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "add cpe device  'Management Address' dropdown field not avaialble");
		}
		
			
			
		try {
			if(manageaddressdropdownvalue.equalsIgnoreCase("null")) {
					
				Log.info("No values has been passed for Mandatory 'Management Address' dropdown for adding device under Equipment");
					
			}else {	
			Clickon(getwebelement("//div[label[text()='Management Address']]//div[text()='"+ manageaddressdropdownvalue+" ']]"));
			ExtentTestManager.getTest().log(LogStatus.PASS, manageaddressdropdownvalue + " is the value passed for Mandatory 'Management Address' dropdown for adding device under Equipment");
				}
				}catch(Exception e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, "FAilure at 'Management Address' dropdown. It does not have the value provided as input");
			}
	}

	//Mepid	
		try {
			
			if(Mepid.equalsIgnoreCase("null")) {
				ExtentTestManager.getTest().log(LogStatus.INFO, "No values has been passed for 'mepid' field for adding device under Equipment");
				
			}else {
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_mepid")), Mepid);
				ExtentTestManager.getTest().log(LogStatus.PASS, Mepid + " is the value passed for 'Mepid' field for adding device under Equipment");
				
			}
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Mep Id' field is not available");
		}catch(Exception err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Not able to enter value inside the 'Mep Id' field");
		}
		
	
	//Power alarm	
		try {
			
			if(poweralarm.equalsIgnoreCase("null")) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "No values has been passed for Mandatory dropdown 'Power alarm' for adding device under Equipment");
			}else {
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_poweralarm")));
			}
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Power Alarm' dropdown is not available ");
		}
		
		
		try {
			
			if(poweralarm.equalsIgnoreCase("null")) {
				
				Log.info("power alarm dropdown selected");
			}else {
				Clickon(getwebelement("//div[label[text()='Power Alarm']]//div[text()='"+poweralarm +"']"));
				ExtentTestManager.getTest().log(LogStatus.PASS, poweralarm + " is the value passed for Mandatory 'Power Alarm' dropdown field for adding device under Equipment");
				
			}
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "FAilure at power alarm dropdown. It does not have the value provided as input ");
		}
		

	//Media selection	
		try {
			
			if(Mediaselection.equalsIgnoreCase("null")) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "No values has been passed for 'Media Selection' mandatory field for adding device under Equipment");
			}else {
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_mediaselection")));
			}
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Media selection' dropdown is not available ");
		}
		
		try {
			
			if(Mediaselection.equalsIgnoreCase("null")) {
				Log.info("Media selection dropdown selected");
				
			}else {
				Clickon(getwebelement("//div[label[text()='Media Selection']]//div[text()='"+Mediaselection +"']"));
				ExtentTestManager.getTest().log(LogStatus.PASS, Mediaselection + " is the value passed for Mandatory 'Media Selection' field for adding device under Equipment");
			}
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "FAilure at Media selection dropdown. It does not have the value provided as input");
		}
				

	//Mac address	
		try {
			
			if(Macaddress.equalsIgnoreCase("null")) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, " No values has been passed for 'mac address' mandatory field for adding device under Equipment");
			}else {
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_macaddress")), Macaddress);
				ExtentTestManager.getTest().log(LogStatus.PASS, Macaddress + " is the value passed for 'Macaddress' field for adding device under Equipment");
			}
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Mac Address' field is not available");
		}catch(Exception err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Not able to enter value inside the 'Mac Address' field");
		}

		
		
		
	//Serial number	
		try {
			
			if(serialNumber.equalsIgnoreCase("null")) {
				ExtentTestManager.getTest().log(LogStatus.INFO, " No values has been passed for 'Serial number' field for adding device under Equipment");
			}else {
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_serialnumber")), serialNumber);
				ExtentTestManager.getTest().log(LogStatus.PASS,serialNumber + " is the value passed for Mandatory 'Serial Number' field for adding device under Equipment");
			}
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Serial Number' field is not available");
		}catch(Exception err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Not able to enter value inside the 'Serial Number' field");
		}
		
//
//		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_hexaserialnumber")),
//				hexaSerialnumber);

		if (linkLostForwarding.equalsIgnoreCase("yes")) {
		
			try {
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_linklostforowarding")));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Link lost forwarding checkbox is selected for adding device under Equipment");
			}catch(Exception e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Link  lost forwarding checkbox is not avaialble");
			}
			
		} else {
			
			Log.info("link lost forwarding is not selected");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Link lost forwarding checkbox is not selected for adding device under Equipment");
			
			
		}

		
		click_commonMethod(application, "OK", "okbutton", xml);

		Thread.sleep(3000);

	}

	public void verifydetailsEnteredforCPEdevice_1G(String application, String cpename, String vender, String snmpro,
			String managementAddress, String Mepid, String poweralarm, String Mediaselection, String Macaddress,
			String serialNumber, String hexaSerialnumber, String linkLostForwarding,
			String newmanagementAddress, String existingmanagementAddress, String manageaddressdropdownvalue, String technologySelected)
			throws InterruptedException, DocumentException, IOException {
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "verify the details entered for creating device");
	
		clickOnBankPage();
		Thread.sleep(1000);
		
		scrolltoend();
		Thread.sleep(2000);
		
		Clickon(getwebelement("//div[div[div[text()='Equipment']]]//div[div[div[contains(@title,'"+ cpename +"')]]]//span[text()='View']"));
		Thread.sleep(3000);
		
		
		String[] RouterId=new String[2];
		RouterId=cpename.split(".lanlink");
		
		String RouterIdValue=RouterId[0];
		
		
		String mediaSelectionValueInViewDevicePage="no";
		if(Mediaselection.equalsIgnoreCase("null")) {
			Mediaselection=mediaSelectionValueInViewDevicePage;
		}else {
			Mediaselection=mediaSelectionValueInViewDevicePage;
		}
	  
	  verifyEnteredvalues_deviceName("Name", RouterIdValue,cpename );
	  
	  verifyEnteredvalues("Router Id", RouterIdValue);
	  
	  verifyEnteredvalues("Vendor/Model", vender);
	  
	  verifyEnteredvalues("Snmpro", snmpro);
	  
	  
		//Management Address  
		  	if((existingmanagementAddress.equalsIgnoreCase("Yes")) && (newmanagementAddress.equalsIgnoreCase("no"))) {
			  verifyEnteredvalues("Management Address", manageaddressdropdownvalue);
			 }
			 else if((existingmanagementAddress.equalsIgnoreCase("no")) && (newmanagementAddress.equalsIgnoreCase("Yes"))) {
				 verifyEnteredvalues("Management Address", managementAddress);
			 } 
	  
//	  verifyEnteredvalues("MEP Id", Mepid);
	  
	  verifyEnteredvalues("Power Alarm", poweralarm);
	  
	if(technologySelected.equalsIgnoreCase("Accedian-1G")) {  
		
	  verifyEnteredvalues("Serial Number", serialNumber);
	  
	}else {
	  
	  verifyEnteredvalues("Media Selection", Mediaselection);
	  
	  verifyEnteredvalues("MAC Address", Macaddress);
	}
	  
	  
  

	}
	
	
	public void verifydetailsEnteredforCPEdevice_10G(String application, String cpename, String vender, String snmpro,
			String managementAddress, String Mepid, String poweralarm, String Mediaselection, String Macaddress,
			String serialNumber, String hexaSerialnumber, String linkLostForwarding, String newmanagementAddress, 
			String existingmanagementAddress, String manageaddressdropdownvalue)
			throws InterruptedException, DocumentException, IOException {
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "verify the details entered for creating device");
		
		clickOnBankPage();
		Thread.sleep(1000);
		
		Thread.sleep(1000);
		
		scrolltoend();
		Thread.sleep(2000);
		
		Clickon(getwebelement("//div[div[div[text()='Equipment']]]//div[div[div[contains(@title,'"+ cpename +"')]]]//span[text()='View']"));
		Thread.sleep(3000);
		
		scrollToTop();
		Thread.sleep(2000);
		
	//Splitting device name	
		String[] RouterId=new String[2];
		RouterId=cpename.split(".lanlink");
		
		String RouterIdValue=RouterId[0];
		
		Mediaselection="no";
		
	  verifyEnteredvalues_deviceName("Name", RouterIdValue,cpename );
		  
	  verifyEnteredvalues("Router Id", RouterIdValue);
	  
	  verifyEnteredvalues("Vendor/Model", vender);
	  
	  verifyEnteredvalues("Snmpro", snmpro);
	  
	//Management Address  
	  	if((existingmanagementAddress.equalsIgnoreCase("Yes")) && (newmanagementAddress.equalsIgnoreCase("no"))) {
		  verifyEnteredvalues("Management Address", manageaddressdropdownvalue);
		 }
		 else if((existingmanagementAddress.equalsIgnoreCase("no")) || (newmanagementAddress.equalsIgnoreCase("Yes"))) {
			 verifyEnteredvalues("Management Address", managementAddress);
		 } 
	  
	  verifyEnteredvalues("Power Alarm", poweralarm);
	  
	  verifyEnteredvalues("Media Selection", Mediaselection);
	  
	  verifyEnteredvalues("Link Lost Forwarding", "yes");
	  
	  verifyEnteredvalues("Serial Number", serialNumber);
	  
  

	}

	public void eDITCPEdevicedetailsentered_1G(String application, String cpename, String vender, String snmpro,
			String managementAddress, String Mepid, String poweralarm, String Mediaselection, String Macaddress,
			String serialNumber, String hexaSerialnumber, String linkLostForwarding,String createddevicename, String technologySelected)
			throws InterruptedException, DocumentException, IOException {
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "edit CPE device under Equipment");
		
		Log.info("Entered edit functionalitty");

		scrollToTop();
		Thread.sleep(3000);
		
		click_commonMethod(application, "Action", "viewPCEdevice_Actiondropdown", xml);
		
		Thread.sleep(3000);
		
		click_commonMethod(application, "Edit CPE link", "EditCPEdevicelinkunderviewpage", xml);
		Thread.sleep(3000);
		Log.info("edit functionality worked");
		
	     
	     
	//Name field
		device_editnamefield(application, cpename);
		
	//vendor/model
		device_editVendorModelField(application, vender);
		
	//Snmpro
		device_editSnmproField(application);

	//Management address
		device_editManagementAddressField(application, managementAddress);
		
	//Mepid	
		device_editMEPIdField(application, Mepid);
		
		scrolltoend();
		Thread.sleep(3000);
		
	//power alarm	
		device_editPowerAlarm(application, poweralarm);
	
	if(technologySelected.equalsIgnoreCase("Accedian-1G")) {
		
		//Serial Number
				device_editserialnumber(application, serialNumber);
				
	}else {
		
		//Media Selection   
				device_editMediaselection(application, Mediaselection);
			    
				Thread.sleep(3000);
			    
			//Mac address  
				device_editMACaddress(application, Macaddress);
			    
			//linklost forwarding	
				device_editlinkLostforwarding(application, linkLostForwarding);
	}
	
	    
scrolltoend();
Thread.sleep(3000);

		click_commonMethod(application, "OK", "okbutton", xml);
		Thread.sleep(2000);
		
	}
	
	
	public void eDITCPEdevicedetailsentered_10G(String application, String cpename, String vender, String snmpro,
			String managementAddress, String Mepid, String poweralarm, String Mediaselection, String Macaddress,
			String serialNumber, String hexaSerialnumber, String linkLostForwarding,String devicename)
			throws InterruptedException, DocumentException, IOException {
		

		
		ExtentTestManager.getTest().log(LogStatus.INFO, "edit CPE device under Equipment");
		
		Log.info("Entered edit functionalitty");

		scrollToTop();
		Thread.sleep(1000);
		
		click_commonMethod(application, "Action", "viewPCEdevice_Actiondropdown", xml);
		
		Thread.sleep(3000);
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/EditCPEdevicelinkunderviewpage")));
		Thread.sleep(3000);
		Log.info("edit functionality worked");
	     
	     
	//Name field
		device_editnamefield(application, cpename);
		
	//vendor/model
		device_editVendorModelField(application, vender);
		
	//Snmpro
		device_editSnmproField(application);

	//Management address
		device_editManagementAddressField(application, managementAddress);
		
	//Mepid	
		device_editMEPIdField(application, Mepid);
		
	//power alarm	
		device_editPowerAlarm(application, poweralarm);
		
	//serial Number
		device_editserialnumber(application, serialNumber);
		
	//linklost forwarding	
		device_editlinklostforwarding_10G(application);
	  
		scrolltoend();
	    
		click_commonMethod(application, "OK", "okbutton", xml);
		Thread.sleep(2000);
		
	}
	
	
	public void eDITCPEdevicedetailsentered_1G_Overture(String application, String cpedevicename, String vender, String snmpro,
			String managementAddress, String Mepid, String poweralarm, String Mediaselection, String Macaddress,
			String serialNumber, String hexaSerialnumber, String linkLostForwarding, String Country,
			String ExistingCitySelection, String NewCitySelection, String existingCity, String newCityName, String newCityCode,
			String ExistingSiteSelection, String NewSiteSelection, String ExistingSite, String NewSiteName, String NewSiteCode,
			String ExistingPremiseSelection, String newPremiseselection, String existingPremise, String newPremiseName, String newPremiseCode)
			throws InterruptedException, DocumentException, IOException {
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "edit CPE device");
		
		Log.info("Entered edit functionalitty");

		scrollToTop();
		
		click_commonMethod(application, "Action", "viewPCEdevice_Actiondropdown", xml);
		
		Thread.sleep(3000);
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/EditCPEdevicelinkunderviewpage")));
		Thread.sleep(3000);
		Log.info("edit functionality worked");
		
	     
	//Name field
		device_editnamefield(application, cpedevicename);
		
	//vendor/model
		device_editVendorModelField(application, vender);
		
	//Snmpro
		device_editSnmproField(application);

	//Management address
		device_editManagementAddressField(application, managementAddress);
		
	//Mepid	
		device_editMEPIdField(application, Mepid);
		
	//power alarm	
		device_editPowerAlarm(application, poweralarm);
		
		//Media Selection  
		device_editMediaselection(application, Mediaselection);
	    
		
		WebElement countrylabelname=getwebelement(xml.getlocator("//locators/" + application + "/countrylabelname_IntEquipment"));
		ScrolltoElement(countrylabelname);
	Thread.sleep(3000);
	
	    
	//Mac address  
		device_editMACaddress(application, Macaddress);
	    
	//linklost forwarding	
		device_editlinkLostforwarding(application, linkLostForwarding);
	
		scrolltoend();
		Thread.sleep(1000);
		
		//Country
				if(!Country.equalsIgnoreCase("Null")) {
					
					selectValueInsideDropdown(application, "countryDropdown_selectTag", "Country", Country, xml);
					
					//New City		
					if(ExistingCitySelection.equalsIgnoreCase("no") & NewCitySelection.equalsIgnoreCase("yes")) {
						Clickon_addToggleButton(application, "addcityswitch");
					   //City name
						addtextFields_commonMethod(application, "City Name", "citynameinputfield", newCityName, xml);
					   //City Code	
						addtextFields_commonMethod(application, "City Code", "citycodeinputfield", newCityCode, xml);
					   //Site name
						addtextFields_commonMethod(application, "Site Name", "sitenameinputfield_addCityToggleSelected", NewSiteName, xml);
					   //Site Code
						addtextFields_commonMethod(application, "Site Code", "sitecodeinputfield_addCityToggleSelected", NewSiteCode, xml);
					   //Premise name	
						addtextFields_commonMethod(application, "Premise Name", "premisenameinputfield_addCityToggleSelected", newPremiseName, xml);
					   //Premise Code	
						addtextFields_commonMethod(application, "Premise Code", "premisecodeinputfield_addCityToggleSelected", newPremiseCode, xml);
							
					}	
				
				//Existing City	
					else if(ExistingCitySelection.equalsIgnoreCase("yes") & NewCitySelection.equalsIgnoreCase("no")) {
						
					   selectValueInsideDropdown(application, "cityDropdown_selectTag", "City", existingCity, xml);
						
						
					 //Existing Site
						  if(ExistingSiteSelection.equalsIgnoreCase("yes") & NewSiteSelection.equalsIgnoreCase("no")) {
							  selectValueInsideDropdown(application, "siteDropdown_selectTag", "Site", ExistingSite, xml);
							  
						 //Existing Premise
							  if(ExistingPremiseSelection.equalsIgnoreCase("yes") & newPremiseselection.equalsIgnoreCase("no")) {
								  selectValueInsideDropdown(application, "premiseDropdown_selectTag", "Premise", existingPremise, xml);
					          	 }
							  
							//New Premise  
							  else if(ExistingPremiseSelection.equalsIgnoreCase("no") & newPremiseselection.equalsIgnoreCase("yes")) {
								  
								  Clickon_addToggleButton(application, "addpremiseswitch");
								  //Premise name	
									addtextFields_commonMethod(application, "Premise Name", "premisenameinputfield_addPremiseToggleSelected", newPremiseName, xml);
								   //Premise Code	
									addtextFields_commonMethod(application, "Premise Code", "premisecodeinputfield_addPremiseToggleSelected", newPremiseCode, xml);
							  } 
				         	}
			  		
					  else if(ExistingSiteSelection.equalsIgnoreCase("no") & NewSiteSelection.equalsIgnoreCase("yes")) {
						  	
						  Clickon_addToggleButton(application, "addsiteswitch");
						  //Site name
							addtextFields_commonMethod(application, "Site Name", "sitenameinputfield_addSiteToggleSelected", NewSiteName, xml);
						   //Site Code
							addtextFields_commonMethod(application, "Site Code", "sitecodeinputfield_addSiteToggleSelected", NewSiteCode, xml);
							
						   //Premise name	
							addtextFields_commonMethod(application, "Premise Name", "premisenameinputfield_addSiteToggleSelected", newPremiseName, xml);
						   //Premise Code	
							addtextFields_commonMethod(application, "Premise Code", "premisecodeinputfield_addSiteToggleSelected", newPremiseCode, xml);
						  }
					}
					
				}
				else if(Country.equalsIgnoreCase("Null")) {
					
					ExtentTestManager.getTest().log(LogStatus.PASS, " No changes made for 'Country' dropdown");
				
				//City	
					editCity(application, ExistingCitySelection, NewCitySelection, "cityDropdown_selectTag", "selectcityswitch", "addcityswitch",
							existingCity, newCityName, newCityCode, "City");
					
					
				//Site	
					editSite(application, ExistingSiteSelection, NewSiteSelection, "siteDropdown_selectTag", "selectsiteswitch",
							"addsiteswitch", ExistingSite , NewSiteName, NewSiteCode, "Site");
					
				//Premise
					editPremise(application, ExistingPremiseSelection, newPremiseselection, "premiseDropdown_selectTag", "selectpremiseswitch",
							"addpremiseswitch", existingPremise, newPremiseName, newPremiseCode, "Premise");
					
				}

				scrolltoend();
		
	    
		click_commonMethod(application, "OK", "okbutton", xml);
		Thread.sleep(2000);
	}
	
	
		
	
	public void eDITCPEdevicedetailsentered_1G_Accedian(String application, String cpedevicename, String vender, String snmpro,
			String managementAddress, String Mepid, String poweralarm, String Mediaselection, String Macaddress,
			String serialNumber, String hexaSerialnumber, String linkLostForwarding, String Country,
			String ExistingCitySelection, String NewCitySelection, String existingCity, String newCityName, String newCityCode,
			String ExistingSiteSelection, String NewSiteSelection, String ExistingSite, String NewSiteName, String NewSiteCode,
			String ExistingPremiseSelection, String newPremiseselection, String existingPremise, String newPremiseName, String newPremiseCode)
			throws InterruptedException, DocumentException, IOException {
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "edit CPE device");
		
		Log.info("Entered edit functionalitty");

		scrollToTop();
		
		click_commonMethod(application, "Action", "viewPCEdevice_Actiondropdown", xml);
		
		Thread.sleep(3000);
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/EditCPEdevicelinkunderviewpage")));
		Thread.sleep(3000);
		Log.info("edit functionality worked");
		
	     
	//Name field
		device_editnamefield(application, cpedevicename);
		
	//vendor/model
		device_editVendorModelField(application, vender);
		
	//Snmpro
		device_editSnmproField(application);

	//Management address
		device_editManagementAddressField(application, managementAddress);
		
	//Mepid	
		device_editMEPIdField(application, Mepid);
		
	//power alarm	
		device_editPowerAlarm(application, poweralarm);
	
	//Serial Number
		device_editserialnumber(application, serialNumber);
	    
	    
	//linklost forwarding	
//		device_editlinkLostforwarding(application, linkLostForwarding);
	    
	
		scrolltoend();
		Thread.sleep(3000);
		
		//Country
		if(!Country.equalsIgnoreCase("Null")) {
			
			selectValueInsideDropdown(application, "countryDropdown_selectTag", "Country", Country, xml);
			
			//New City		
			if(ExistingCitySelection.equalsIgnoreCase("no") & NewCitySelection.equalsIgnoreCase("yes")) {
				Clickon_addToggleButton(application, "addcityswitch");
			   //City name
				addtextFields_commonMethod(application, "City Name", "citynameinputfield", newCityName, xml);
			   //City Code	
				addtextFields_commonMethod(application, "City Code", "citycodeinputfield", newCityCode, xml);
			   //Site name
				addtextFields_commonMethod(application, "Site Name", "sitenameinputfield_addCityToggleSelected", NewSiteName, xml);
			   //Site Code
				addtextFields_commonMethod(application, "Site Code", "sitecodeinputfield_addCityToggleSelected", NewSiteCode, xml);
			   //Premise name	
				addtextFields_commonMethod(application, "Premise Name", "premisenameinputfield_addCityToggleSelected", newPremiseName, xml);
			   //Premise Code	
				addtextFields_commonMethod(application, "Premise Code", "premisecodeinputfield_addCityToggleSelected", newPremiseCode, xml);
					
			}	
		
		//Existing City	
			else if(ExistingCitySelection.equalsIgnoreCase("yes") & NewCitySelection.equalsIgnoreCase("no")) {
				
			   selectValueInsideDropdown(application, "cityDropdown_selectTag", "City", existingCity, xml);
				
				
			 //Existing Site
				  if(ExistingSiteSelection.equalsIgnoreCase("yes") & NewSiteSelection.equalsIgnoreCase("no")) {
					  selectValueInsideDropdown(application, "siteDropdown_selectTag", "Site", ExistingSite, xml);
					  
				 //Existing Premise
					  if(ExistingPremiseSelection.equalsIgnoreCase("yes") & newPremiseselection.equalsIgnoreCase("no")) {
						  selectValueInsideDropdown(application, "premiseDropdown_selectTag", "Premise", existingPremise, xml);
			          	 }
					  
					//New Premise  
					  else if(ExistingPremiseSelection.equalsIgnoreCase("no") & newPremiseselection.equalsIgnoreCase("yes")) {
						  
						  Clickon_addToggleButton(application, "addpremiseswitch");
						  //Premise name	
							addtextFields_commonMethod(application, "Premise Name", "premisenameinputfield_addPremiseToggleSelected", newPremiseName, xml);
						   //Premise Code	
							addtextFields_commonMethod(application, "Premise Code", "premisecodeinputfield_addPremiseToggleSelected", newPremiseCode, xml);
					  } 
		         	}
	  		
			  else if(ExistingSiteSelection.equalsIgnoreCase("no") & NewSiteSelection.equalsIgnoreCase("yes")) {
				  	
				  Clickon_addToggleButton(application, "addsiteswitch");
				  //Site name
					addtextFields_commonMethod(application, "Site Name", "sitenameinputfield_addSiteToggleSelected", NewSiteName, xml);
				   //Site Code
					addtextFields_commonMethod(application, "Site Code", "sitecodeinputfield_addSiteToggleSelected", NewSiteCode, xml);
					
				   //Premise name	
					addtextFields_commonMethod(application, "Premise Name", "premisenameinputfield_addSiteToggleSelected", newPremiseName, xml);
				   //Premise Code	
					addtextFields_commonMethod(application, "Premise Code", "premisecodeinputfield_addSiteToggleSelected", newPremiseCode, xml);
				  }
			}
			
		}
		else if(Country.equalsIgnoreCase("Null")) {
			
			ExtentTestManager.getTest().log(LogStatus.PASS, " No changes made for 'Country' dropdown");
		
		//City	
			editCity(application, ExistingCitySelection, NewCitySelection, "cityDropdown_selectTag", "selectcityswitch", "addcityswitch",
					existingCity, newCityName, newCityCode, "City");
			
			
		//Site	
			editSite(application, ExistingSiteSelection, NewSiteSelection, "siteDropdown_selectTag", "selectsiteswitch",
					"addsiteswitch", ExistingSite , NewSiteName, NewSiteCode, "Site");
			
		//Premise
			editPremise(application, ExistingPremiseSelection, newPremiseselection, "premiseDropdown_selectTag", "selectpremiseswitch",
					"addpremiseswitch", existingPremise, newPremiseName, newPremiseCode, "Premise");
			
		}
		
		scrolltoend();

		click_commonMethod(application, "OK", "okbutton", xml);
		Thread.sleep(2000);
	}
	
	 public void editPremise(String application, String editExistingPremise, String editNewPremise, String dropdown_xpath, String selectPremiseToggleButton,
	    		String addPremisetoggleButton, String dropdownValue, String editNewPremiseName, String editNewPremiseCode, String labelname) throws InterruptedException, DocumentException, IOException {
	    	
	    	if(editExistingPremise.equalsIgnoreCase("yes") & editNewPremise.equalsIgnoreCase("no")) {
				
	    		existingPremise(application, dropdown_xpath, dropdownValue, selectPremiseToggleButton, labelname);
	    		
			}
	    	
	    	else if(editExistingPremise.equalsIgnoreCase("yes") & editNewPremise.equalsIgnoreCase("null")) {
				
	    		existingPremise(application, dropdown_xpath, dropdownValue, selectPremiseToggleButton, labelname);
	    		
			}
	    	
			else if(editExistingPremise.equalsIgnoreCase("no") & editNewPremise.equalsIgnoreCase("Yes")) {
				
				newPremise(application, dropdown_xpath, addPremisetoggleButton, editNewPremiseName, editNewPremiseCode, labelname);
				
			}
	    	
			else if(editExistingPremise.equalsIgnoreCase("null") & editNewPremise.equalsIgnoreCase("Yes")) {
				
				newPremise(application, dropdown_xpath, addPremisetoggleButton, editNewPremiseName, editNewPremiseCode, labelname);
				
			}
	    	
			else if(editExistingPremise.equalsIgnoreCase("null") & editNewPremise.equalsIgnoreCase("null")) {
				
				ExtentTestManager.getTest().log(LogStatus.PASS, "No changes made under 'Premise' field");
				Log.info("No changes made under 'Premise' field");
				
			}
	    	
	    }
	    
	 public void existingPremise(String application, String dropdown_xpath, String dropdownValue, String selectPremiseToggleButton, String labelname) throws InterruptedException, DocumentException, IOException {
	    	
	    	boolean premiseDisplayed=false;
	    try {	
	    	premiseDisplayed=getwebelement(xml.getlocator("//locators/" + application + "/"+ dropdown_xpath +"")).isDisplayed();
	    	
	    	if(premiseDisplayed) {
	    		
	    		selectValueInsideDropdown(application, dropdown_xpath, labelname, dropdownValue, xml);
	    		
	    	}else {
	    		
	    		click_commonMethod(application, "Select Premise toggle button", selectPremiseToggleButton, xml);
	    		Thread.sleep(1000);
				
	    		selectValueInsideDropdown(application, dropdown_xpath, labelname, dropdownValue, xml);
	    		
	    	}
	    }catch(Exception e) {
	    	e.printStackTrace();
	    	
	    	click_commonMethod(application, "Select Premise toggle button", selectPremiseToggleButton, xml);
			
	    	selectValueInsideDropdown(application, dropdown_xpath, labelname, dropdownValue, xml);
	    }
	    	
	    }
	    
	    
	    public void newPremise(String application, String dropdown_xpath, String addPremisetoggleButton, String editNewPremiseName,
	    		String editNewPremiseCode, String labelname) throws InterruptedException, DocumentException, IOException {
	    	
	    	boolean premiseDisplayed=false;
	    try {	
	    	premiseDisplayed=getwebelement(xml.getlocator("//locators/" + application + "/"+ dropdown_xpath +"")).isDisplayed();
	    	
	    	if(premiseDisplayed) {
	    		
	    		click_commonMethod(application, "Select Premise toggle button", addPremisetoggleButton, xml);
	    		Thread.sleep(1000);
				
				//Premise name
				edittextFields_commonMethod(application, "Premise Name", "premisenameinputfield_addPremiseToggleSelected", editNewPremiseName, xml);
				
			   //Premise Code	
				edittextFields_commonMethod(application, "Premise Code", "premisecodeinputfield_addPremiseToggleSelected", editNewPremiseCode, xml);
	    		
	    	}else {
	    		
	    		//Premise name
				edittextFields_commonMethod(application, "Premise Name", "premisenameinputfield_addCityToggleSelected", editNewPremiseName, xml);
				
			   //Premise Code	
				edittextFields_commonMethod(application, "Premise Code", "premisecodeinputfield_addCityToggleSelected", editNewPremiseCode, xml);
	    		
	    	}
	    }catch(Exception e) {
	    	e.printStackTrace();
	    	
	    	//Premise name
			edittextFields_commonMethod(application, "Premise Name", "premisenameinputfield_addCityToggleSelected", editNewPremiseName, xml);
			
		   //Premise Code	
			edittextFields_commonMethod(application, "Premise Code", "premisecodeinputfield_addCityToggleSelected", editNewPremiseCode, xml);
	    	
	    }
	    	
	    }
	    
	    
	    public void editSite(String application, String editExistingCity, String editNewCity, String dropdown_xpath, String selectSiteToggleButton,
	    		String addSitetoggleButton, String dropdownValue, String editNewSiteName, String editNewSiteCode, String labelname) throws InterruptedException, DocumentException, IOException {
	    	
	    	if(editExistingCity.equalsIgnoreCase("yes") & editNewCity.equalsIgnoreCase("no")) {
				
	    		existingSite(application, dropdown_xpath, dropdownValue, selectSiteToggleButton, labelname);
	    		
			}
	    	
	    	else if(editExistingCity.equalsIgnoreCase("yes") & editNewCity.equalsIgnoreCase("null")) {
				
	    		existingSite(application, dropdown_xpath, dropdownValue, selectSiteToggleButton, labelname);
	    		
			}
	    	
			else if(editExistingCity.equalsIgnoreCase("no") & editNewCity.equalsIgnoreCase("Yes")) {
				
				newSite(application, dropdown_xpath, addSitetoggleButton, editNewSiteName, editNewSiteCode, labelname);
				
			}
	    	
			else if(editExistingCity.equalsIgnoreCase("null") & editNewCity.equalsIgnoreCase("Yes")) {
				
				newSite(application, dropdown_xpath, addSitetoggleButton, editNewSiteName, editNewSiteCode, labelname);
				
			}
	    	
			else if(editExistingCity.equalsIgnoreCase("null") & editNewCity.equalsIgnoreCase("null")) {
				
				ExtentTestManager.getTest().log(LogStatus.PASS, "No changes made under 'Site' field");
				Log.info("No changes made under 'Site' field");
				
			}
	    	
	    }
	    
	 public void existingSite(String application, String dropdown_xpath, String dropdownValue, String selectSiteToggleButton, String labelname) throws InterruptedException, DocumentException, IOException {
	    	
	    	boolean siteDisplayed=false;
	    try {	
	    	siteDisplayed=getwebelement(xml.getlocator("//locators/" + application + "/"+ dropdown_xpath +"")).isDisplayed();
	    	
	    	if(siteDisplayed) {
	    		
	    		selectValueInsideDropdown(application, dropdown_xpath, labelname, dropdownValue, xml);
	    		
	    	}else {
	    		
	    		click_commonMethod(application, "Select Site toggle button", selectSiteToggleButton, xml);
	    		Thread.sleep(1000);
				
	    		selectValueInsideDropdown(application, dropdown_xpath, labelname, dropdownValue, xml);
	    		
	    	}
	    }catch(Exception e) {
	    	e.printStackTrace();
	    	
	    	click_commonMethod(application, "Select Site toggle button", selectSiteToggleButton, xml);
			
	    	selectValueInsideDropdown(application, dropdown_xpath, labelname, dropdownValue, xml);
	    }
	    	
	    }
	    
	    
	    public void newSite(String application, String dropdown_xpath, String addSitetoggleButton, String editNewSiteName,
	    		String editNewSiteCode, String labelname) throws InterruptedException, DocumentException, IOException {
	    	
	    	boolean siteDisplayed=false;
	    try {	
	    	siteDisplayed=getwebelement(xml.getlocator("//locators/" + application + "/"+ dropdown_xpath +"")).isDisplayed();
	    	
	    	if(siteDisplayed) {
	    		
	    		click_commonMethod(application, "Select City toggle button", addSitetoggleButton, xml);
	    		Thread.sleep(1000);
				
				//Site name
				edittextFields_commonMethod(application, "Site Name", "sitenameinputfield_addSiteToggleSelected", editNewSiteName, xml);
				
			   //Site Code	
				edittextFields_commonMethod(application, "Site Code", "sitecodeinputfield_addSiteToggleSelected", editNewSiteCode, xml);
	    		
	    	}else {
	    		
	    		//Site name
				edittextFields_commonMethod(application, "Site Name", "sitenameinputfield_addCityToggleSelected", editNewSiteName, xml);
				
			   //Site Code	
				edittextFields_commonMethod(application, "Site Code", "sitenameinputfield_addCityToggleSelected", editNewSiteCode, xml);
	    		
	    	}
	    }catch(Exception e) {
	    	e.printStackTrace();
	    	
	    	//Site name
			edittextFields_commonMethod(application, "Site Name", "sitenameinputfield_addCityToggleSelected", editNewSiteName, xml);
			
		   //Site Code	
			edittextFields_commonMethod(application, "Site Code", "sitecodeinputfield_addCityToggleSelected", editNewSiteCode, xml);
	    	
	    }
	    	
	    }
	    
	    
	    public void editCity(String application, String editExistingCity, String editNewCity, String dropdown_xpath, String selectCityToggleButton,
	    		String addCityToggleButton, String dropdownValue, String editNewCityName, String editNewCityCode, String labelname) throws InterruptedException, DocumentException, IOException {
	    	
	    	if(editExistingCity.equalsIgnoreCase("yes") & editNewCity.equalsIgnoreCase("no")) {
				
	    		existingCity(application, dropdown_xpath, dropdownValue, selectCityToggleButton, labelname);
	    		
			}
	    	
	    	else if(editExistingCity.equalsIgnoreCase("yes") & editNewCity.equalsIgnoreCase("null")) {
				
	    		existingCity(application, dropdown_xpath, dropdownValue, selectCityToggleButton, labelname);
	    		
			}
	    	
			else if(editExistingCity.equalsIgnoreCase("no") & editNewCity.equalsIgnoreCase("Yes")) {
				
				newCity(application, dropdown_xpath, addCityToggleButton, editNewCityName, editNewCityCode, labelname);
				
			}
	    	
			else if(editExistingCity.equalsIgnoreCase("null") & editNewCity.equalsIgnoreCase("Yes")) {
				
				newCity(application, dropdown_xpath, addCityToggleButton, editNewCityName, editNewCityCode, labelname);
				
			}
	    	
			else if(editExistingCity.equalsIgnoreCase("null") & editNewCity.equalsIgnoreCase("null")) {
				
				ExtentTestManager.getTest().log(LogStatus.PASS, "No chnges made under 'City' field");
				Log.info("No chnges made under 'City' field");
			}
	    	
	    }
	    
	    
	    public void existingCity(String application, String dropdown_xpath, String dropdownValue, String selectCityToggleButton, String labelname) throws InterruptedException, DocumentException, IOException {
	    	
	    	boolean cityDisplayed=false;
	    try {	
	    	cityDisplayed=getwebelement(xml.getlocator("//locators/" + application + "/"+ dropdown_xpath +"")).isDisplayed();
	    	
	    	if(cityDisplayed) {
	    		
	    		selectValueInsideDropdown(application, dropdown_xpath, labelname, dropdownValue, xml);
	    		
	    	}else {
	    		
	    		click_commonMethod(application, "Select City toggle button", selectCityToggleButton, xml);
	    		Thread.sleep(1000);
				
	    		selectValueInsideDropdown(application, dropdown_xpath, labelname, dropdownValue, xml);
	    		
	    	}
	    }catch(Exception e) {
	    	e.printStackTrace();
	    	
	    	click_commonMethod(application, "Select City toggle button", selectCityToggleButton, xml);
			
	    	selectValueInsideDropdown(application, dropdown_xpath, labelname, dropdownValue, xml);
	    }
	    	
	    }
	    
	    
	    public void newCity(String application, String dropdown_xpath, String addCitytoggleButton, String editNewCityName,
	    		String editNewCityCode, String labelname) throws InterruptedException, DocumentException, IOException {
	    	
	    	boolean cityDisplayed=false;
	    try {	
	    	cityDisplayed=getwebelement(xml.getlocator("//locators/" + application + "/"+ dropdown_xpath +"")).isDisplayed();
	    	
	    	if(cityDisplayed) {
	    		
	    		click_commonMethod(application, "Select City toggle button", addCitytoggleButton, xml);
	    		Thread.sleep(1000);
				
				//City name
				edittextFields_commonMethod(application, "City Name", "citynameinputfield", editNewCityName, xml);
				
			   //City Code	
				edittextFields_commonMethod(application, "City Code", "citycodeinputfield", editNewCityCode, xml);
	    		
	    	}else {
	    		
				//City name
				edittextFields_commonMethod(application, "City Name", "citynameinputfield", editNewCityName, xml);
				
			   //City Code	
				edittextFields_commonMethod(application, "City Code", "citycodeinputfield", editNewCityCode, xml);
	    	}
	    }catch(Exception e) {
	    	e.printStackTrace();
	    	
			//City name
			edittextFields_commonMethod(application, "City Name", "citynameinputfield", editNewCityName, xml);
			
		   //City Code	
			edittextFields_commonMethod(application, "City Code", "citycodeinputfield", editNewCityCode, xml);
	    	
	    }
	    	
	    }	
	
	public void AddCPEdevicefortheLAnlinkNationalservice(String application, String cpename, String vender, String snmpro,
			String managementAddress, String Mepid, String poweralarm, String Mediaselection, String Macaddress,
			String serialNumber, String hexaSerialnumber, String linkLostForwarding)
			throws InterruptedException, DocumentException, IOException {

		
		Thread.sleep(5000);
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/CPEdevice_adddevicelink")));
		Thread.sleep(3000);

		Log.info("Adding details to the fields to create a CPE device");

		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_Name")), cpename);

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_vender")));
		Clickon(getwebelement("//div[label[text()='Vender/Model']]//div[text()='"+vender +"']"));
	
		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_snmpro")), snmpro);

		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_manageaddress")),
				managementAddress);

		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_mepid")), Mepid);
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_poweralarm")));
	    Clickon(getwebelement("//div[label[text()='Power Alarm']]//div[text()='"+poweralarm +"']"));
		

		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_mediaselection")));
		Clickon(getwebelement("//div[label[text()='Media Selection']]//div[text()='"+Mediaselection +"']"));
				

		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_macaddress")), Macaddress);

//		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_serialnumber")),
//				serialNumber);
//
//		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_hexaserialnumber")),
//				hexaSerialnumber);

		if (linkLostForwarding.equalsIgnoreCase("yes")) {

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_linklostforowarding")));
		} else {
			
		}

		click_commonMethod(application, "OK", "okbutton", xml);

		Thread.sleep(3000);

	}
	
	
	public void addDevice_IntEquipment(String application) throws InterruptedException, DocumentException {
		
		scrolltoend();
		Thread.sleep(2000);
		
		//click on Add device link	
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_adddevicelink")));
		Thread.sleep(3000);
	}

	public void selectTechnology(String application, String technologyToBeSelected) throws InterruptedException, DocumentException {
		
			//verify Technology popup
				boolean technologypopup=false;
				technologypopup=getwebelement(xml.getlocator("//locators/" + application + "/technologyPopup")).isDisplayed();
				if(technologypopup) {
					Log.info("Technology popup is displaying as expected");
					ExtentTestManager.getTest().log(LogStatus.PASS, " Technology popup is displaying as expected");
				}else {
					Log.info("Technology popup is not displaying");
					ExtentTestManager.getTest().log(LogStatus.FAIL, " Technology popup is notdisplaying");
				}
				
			//Dropdown values inside popup
				boolean technologyDropdown=false;
				technologyDropdown=getwebelement(xml.getlocator("//locators/" + application + "/technologypopup_dropdown")).isDisplayed();
				if(technologyDropdown) {
					Log.info("Technology dropdown is displaying as expected");
					ExtentTestManager.getTest().log(LogStatus.PASS, " Technology dropdown is displaying as expected");
					
					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/technologypopup_dropdown")));
					Thread.sleep(3000);
					
					//verify list of values inside technology dropdown
					 List<WebElement> listofTechnololgy = getwebelements(xml.getlocator("//locators/" + application + "/ClassNameForDropdowns"));
						
						if(listofTechnololgy.size()>0) {
				
						for (WebElement technoloyTypes : listofTechnololgy) {
							ExtentTestManager.getTest().log(LogStatus.PASS, "List of values available under 'Technology' dropdown are: "+technoloyTypes.getText());
							Log.info("List of values available under 'Technology' dropdown are: "+technoloyTypes.getText());
						}
					}
						
						
				  //Select the Technology
						Clickon(getwebelement("//div[@class='modal-body']//div[contains(text(),'"+ technologyToBeSelected +"')]"));
						Thread.sleep(3000);
						String actualValue=getwebelement(xml.getlocator("//locators/" + application + "/tchnologyPopup_dropdownValues")).getText();
						ExtentTestManager.getTest().log(LogStatus.PASS, " 'Technology' selected is: "+actualValue);
						Log.info( " 'Technology' selected is: "+actualValue);
						
				}else {
					Log.info("Technology dropdown is not displaying");
					ExtentTestManager.getTest().log(LogStatus.FAIL, " Technology dropdown is notdisplaying");
				}
				
				
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/IntermediateEquipment_OKbuttonforpopup")));
				Thread.sleep(3000);
				
	}
	
	
	public void selectTechnology_HubAndSpoke(String application) throws InterruptedException, DocumentException {
		
		//verify Technology popup
			boolean technologypopup=false;
			technologypopup=getwebelement(xml.getlocator("//locators/" + application + "/technologyPopup")).isDisplayed();
			if(technologypopup) {
				Log.info("Technology popup is displaying as expected");
				ExtentTestManager.getTest().log(LogStatus.PASS, " Technology popup is displaying as expected");
			}else {
				Log.info("Technology popup is not displaying");
				ExtentTestManager.getTest().log(LogStatus.FAIL, " Technology popup is notdisplaying");
			}
			
		//Dropdown values inside popup
			boolean technologyDropdown=false;
			technologyDropdown=getwebelement(xml.getlocator("//locators/" + application + "/technologypopup_dropdown")).isDisplayed();
			if(technologyDropdown) {
				Log.info("Technology dropdown is displaying as expected");
				ExtentTestManager.getTest().log(LogStatus.PASS, " Technology dropdown is displaying as expected");
				
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/technologypopup_dropdown")));
				Thread.sleep(3000);
				
				//verify list of values inside technology dropdown
				 List<WebElement> listofTechnololgy = getwebelements(xml.getlocator("//locators/" + application + "/ClassNameForDropdowns"));
					
					if(listofTechnololgy.size()>0) {
			
					for (WebElement technoloyTypes : listofTechnololgy) {
						ExtentTestManager.getTest().log(LogStatus.PASS, "List of values available under 'Technology' dropdown are: "+technoloyTypes.getText());
						Log.info("List of values available under 'Technology' dropdown are: "+technoloyTypes.getText());
					}
				}
					
			  //Select the Technology
					Clickon(getwebelement("//div[@class='modal-body']//div[contains(text(),'Overture')]"));
					Thread.sleep(3000);
					String actualValue=getwebelement(xml.getlocator("//locators/" + application + "/tchnologyPopup_dropdownValues")).getText();
					ExtentTestManager.getTest().log(LogStatus.PASS, " 'Technology' selected is: "+actualValue);
					Log.info( " 'Technology' selected is: "+actualValue);
					
			}else {
				Log.info("Technology dropdown is not displaying");
				ExtentTestManager.getTest().log(LogStatus.FAIL, " Technology dropdown is notdisplaying");
			}
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/IntermediateEquipment_OKbuttonforpopup")));
			Thread.sleep(3000);
			
}
	
	public void verifyFieldsandAddCPEdevicefortheserviceselected_IntEquip_1G(String application, String cpename, String vender_Overture, String vender_Accedian, String snmpro,
			String managementAddress, String Mepid, String poweralarm_Overture, String powerAlarm_Accedian, String MediaselectionActualValue, String Macaddress,
			String serialNumber, String hexaSerialnumber, String linkLostForwarding, String country, String City,
			String Site, String Premise, String newmanagementAddress, String existingmanagementAddress, String manageaddressdropdownvalue,
			String existingcityselectionmode, String newcityselectionmode, String cityname, String citycode, String existingsiteselectionmode, String newsiteselectionmode,
			String sitename, String sitecode, String existingpremiseselectionmode, String newpremiseselectionmode, String premisename,
			String premisecode, String technologySelectedfordevicecreation) throws InterruptedException, DocumentException, IOException {

	
			
			if(technologySelectedfordevicecreation.equalsIgnoreCase("Overture")) {
				deviceCreatoin_Overture(application, cpename, vender_Overture, snmpro, managementAddress, Mepid, poweralarm_Overture, MediaselectionActualValue,
						Macaddress, serialNumber, hexaSerialnumber, linkLostForwarding, country, City, Site, Premise, newmanagementAddress, 
						existingmanagementAddress, manageaddressdropdownvalue, existingcityselectionmode, newcityselectionmode, cityname, 
						citycode, existingsiteselectionmode, newsiteselectionmode, sitename, sitecode, existingpremiseselectionmode, 
						newpremiseselectionmode, premisename, premisecode);
				
			}
			
			if((technologySelectedfordevicecreation.equalsIgnoreCase("Accedian")) || (technologySelectedfordevicecreation.equalsIgnoreCase("Accedian-1G"))) {
			deviceCreatoin_Accedian(application, cpename, vender_Accedian, snmpro, managementAddress, Mepid, powerAlarm_Accedian, MediaselectionActualValue,
					Macaddress, serialNumber, hexaSerialnumber, linkLostForwarding, country, City, Site, Premise, newmanagementAddress, 
					existingmanagementAddress, manageaddressdropdownvalue, existingcityselectionmode, newcityselectionmode, cityname, 
					citycode, existingsiteselectionmode, newsiteselectionmode, sitename, sitecode, existingpremiseselectionmode, 
					newpremiseselectionmode, premisename, premisecode);
		}
			
	}
	
	
	public void verifyFieldsandAddCPEdevicefortheserviceselected_IntEquip_10G(String application, String cpename, String vender, String snmpro,
			String managementAddress, String Mepid, String poweralarm, String MediaselectionActualValue, String Macaddress,
			String serialNumber, String hexaSerialnumber, String linkLostForwarding, String country, String City,
			String Site, String Premise, String newmanagementAddress, String existingmanagementAddress, String manageaddressdropdownvalue,
			String existingcityselectionmode, String newcityselectionmode, String cityname, String citycode, String existingsiteselectionmode, String newsiteselectionmode,
			String sitename, String sitecode, String existingpremiseselectionmode, String newpremiseselectionmode, String premisename, String premisecode, String technologySelectedfordevicecreation) throws InterruptedException, DocumentException, IOException {

		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying fields for CPE device under Intermediate Equipment");
		
		try {
			
		String linklostForwardingcheckboxstate="disabled"; 
			
		String[] Vender= {"Accedian 10GigE-MetroNode-CE-2Port", "Accedian 10GigE-MetroNode-CE-4Port"};
		
		String[] powerAlarm= {"DC Single Power Supply - PSU A", "DC Dual Power Supply - PSU-A+B"};
		
		String expectedDeviceNameFieldAutopopulatedValue="<Device>-10G.lanlink.dcn.colt.net";
		
		String MEPid="5555";
		
		String expectedValueForSnmpro= "JdhquA5";
		
		scrolltoend();
		Thread.sleep(3000);
		
			click_commonMethod(application, "OK", "obutton_spanTag", xml);
			Thread.sleep(3000);
		
		//Country Error Message
				device_countrywarningMessage(application);
				
				
			ScrolltoElement(getwebelement("//label[text()='Name']"));
			Thread.sleep(3000);

		
			// Vendor/Model Error Message
			device_vendorModelWarningMessage(application);

			// Management Address Error Message
			device_managementAddressWarningMessage(application);

			// Power Alarm Error Message
			device_powerAlarmWarningMessage(application);

			//serial number Eror Message
			device_serialNumberWarningMessage(application);
			
			//Hexa serial Number
			device_hexaSerialNumberWarningMessage(application);
	
			
			//Vendor/Model
			device_vendorModel(application, Vender, vender);      
		
			//Snmpro
			device_snmPro(application, snmpro);
			
			//Management Address dropdown
			device_managementAddress(application, existingmanagementAddress, newmanagementAddress, managementAddress);
			
			//MEP Id
			device_mepID(application, Mepid);
		
			//Power Alarm	
			device_powerAlarm(application, powerAlarm, poweralarm);
			
			//Serial Number
			device_serialNumber(application,serialNumber);
		
		    //Link lost Forwarding
			device_linklostForwarding(application, linkLostForwarding, linklostForwardingcheckboxstate);
			
			//Country
			device_country(application, country);
		
			//City		
			if(existingcityselectionmode.equalsIgnoreCase("no") & newcityselectionmode.equalsIgnoreCase("yes")) {
				addCityToggleButton(application);
			   //New City	
				newcity(application, newcityselectionmode, cityname, citycode);
			      // New Site
				newSite(application, newsiteselectionmode, sitename, sitecode);
				  //New Premise	
				newPremise(application, newpremiseselectionmode, premisename, premisecode);
					
			}	
				
			else if(existingcityselectionmode.equalsIgnoreCase("yes") & newcityselectionmode.equalsIgnoreCase("no")) {
			//Existing City		
			   existingCity(application,  City);
				
				//Site
			 
				  if(existingsiteselectionmode.equalsIgnoreCase("yes") & newsiteselectionmode.equalsIgnoreCase("no")) {
					//Existing Site 
					  existingSite(application,  Site);
					  
					   //Premise
					  if(existingpremiseselectionmode.equalsIgnoreCase("yes") & newpremiseselectionmode.equalsIgnoreCase("no")) {
						  existingPremise(application, Premise);
					  
			          	 }
					  else if(existingpremiseselectionmode.equalsIgnoreCase("no") & newpremiseselectionmode.equalsIgnoreCase("yes")) {
						  //New Premise
						    addPremiseTogglebutton(application);
						    newPremise_clickOnPremisetoggleButton(application, newpremiseselectionmode, premisename, premisecode);
					  } 
		         	}
	  		
			  else if(existingsiteselectionmode.equalsIgnoreCase("no") & newsiteselectionmode.equalsIgnoreCase("yes")) {
				  	//New Site 
				  	addSiteToggleButton(application);
				  	newSite_ClickOnSiteTogglebutton(application, newsiteselectionmode, sitename, sitecode); 
				  	
				  	//New Premise
				  	newPremise_clickonSiteToggleButton(application, newpremiseselectionmode, premisename, premisecode);
				  }
			}
			
			
			//OK button
			device_okbutton(application);
			
			//cancel button
			device_cancelButton(application);
			
			click_commonMethod(application, "OK", "AddCPEdevice_OKbutton", xml);
			
			scrollToTop();
			Thread.sleep(1000);
			
			
			warningMessage_commonMethod(application, "warningmEssage1_devicename", "Device Name", xml);
			warningMessage_commonMethod(application, "warningmEssage2_devicename", "Device Name", xml);
			warningMessage_commonMethod(application, "warningmEssage3_devicename", "Device Name", xml);
			
			//Name
			device_nameField(application, cpename, expectedDeviceNameFieldAutopopulatedValue);
			
			scrolltoend();
			Thread.sleep(1000);
			click_commonMethod(application, "OK", "AddCPEdevice_OKbutton", xml);
			
		    
		sa.assertAll();
		
		}catch(AssertionError e) {
			
			e.printStackTrace();
			//ExtentTestManager.getTest().log(LogStatus.FAIL, "FAiled while verify the fields for Add CPE device");
			
		}
		

			 
			
	}

	
	
	public void verifyCPEdevicedataenteredForIntermediateEquipment_1G(String application, String cpename, String vender_Overture, String vender_Accedian, String snmpro,
			String managementAddress, String Mepid, String poweralarm_Overture, String powerAlarm_Accedian, String MediaselectionActualValue, String Macaddress,
			String serialNumber, String hexaSerialnumber, String linkLostForwarding, String country, String existingCity,
			String existingSite, String existingPremise, String newmanagementAddressSelection, String existingmanagementAddressSelection, String manageaddressdropdownvalue,
			String existingcityselectionmode, String newcityselectionmode, String cityname, String citycode, String existingsiteselectionmode, String newsiteselectionmode,
			String sitename, String sitecode, String existingpremiseselectionmode, String newpremiseselectionmode, String premisename,
			String premisecode, String technologySelectedfordevicecreation) throws InterruptedException, DocumentException, IOException {
		
		clickOnBankPage();
		Thread.sleep(1000);
		
		scrolltoend();
		Thread.sleep(1000);
		
		Clickon(getwebelement("//div[div[div[text()='Intermediate Equipment']]]//div[div[div[contains(@title,'"+ cpename +"')]]]//span[text()='View']"));
	
		Thread.sleep(2000);
		
		scrollToTop();
		Thread.sleep(1000);
		
	if(technologySelectedfordevicecreation.equalsIgnoreCase("Overture")) {
		
		viewdevice_Overture(application, cpename, vender_Overture, snmpro, managementAddress, Mepid, poweralarm_Overture, MediaselectionActualValue,
				Macaddress,linkLostForwarding, country, existingCity, cityname, existingSite, sitename, existingPremise, premisename,existingcityselectionmode,
				newcityselectionmode, existingsiteselectionmode, newsiteselectionmode, newmanagementAddressSelection, existingmanagementAddressSelection,
				manageaddressdropdownvalue, existingpremiseselectionmode, newpremiseselectionmode);
		
	}
	
	
	if((technologySelectedfordevicecreation.equalsIgnoreCase("Accedian")) || (technologySelectedfordevicecreation.equalsIgnoreCase("Accedian-1G"))) {
		
		viewdevice_Accedian(application, cpename, vender_Accedian, snmpro, managementAddress, Mepid, powerAlarm_Accedian, MediaselectionActualValue,
				Macaddress, serialNumber, hexaSerialnumber, linkLostForwarding,country, existingCity, cityname, existingSite, sitename, existingPremise, premisename,existingcityselectionmode,
				newcityselectionmode, existingsiteselectionmode, newsiteselectionmode, newmanagementAddressSelection, existingmanagementAddressSelection,
				manageaddressdropdownvalue, existingpremiseselectionmode, newpremiseselectionmode);
	}
		

	
	}
	
	
	private void click_commonMethod_PassingWebelementDirectly(String application, String labelname, WebElement viewlink,
			XMLReader xml2) {
		// TODO Auto-generated method stub
		
	}

	public void verifyCPEdevicedataenteredForIntermediateEquipment_10G(String application, String cpename, String vender, String snmpro,
			String managementAddress, String Mepid, String poweralarm, String MediaselectionActualValue, String Macaddress,
			String serialNumber, String hexaSerialnumber, String linkLostForwarding, String existingcountry, String existingCity,
			String existingSite, String existingPremise, String newmanagementAddress, String existingmanagementAddress, String manageaddressdropdownvalue,
			String existingcityselectionmode, String newcityselectionmode, String cityname, String citycode, String existingsiteselectionmode, String newsiteselectionmode,
			String sitename, String sitecode, String existingpremiseselectionmode, String newpremiseselectionmode, String premisename, String premisecode, String technologySelectedfordevicecreation) throws InterruptedException, DocumentException, IOException {
		
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "verify the details entered while creating device");
		
		clickOnBankPage();
		Thread.sleep(1000);
		
		scrolltoend();
		Thread.sleep(1000);
		
		Clickon(getwebelement("//div[div[div[text()='Intermediate Equipment']]]//div[div[div[contains(@title,'"+ cpename +"')]]]//span[text()='View']"));
	
		Thread.sleep(4000);
		scrollToTop();
		
		
		
		String[] RouterId=new String[2];
		RouterId=cpename.split(".lanlink");
		
		String RouterIdValue=RouterId[0];
		
		
		String mediaSelectionValueInViewDevicePage="no";
		if(MediaselectionActualValue.equalsIgnoreCase("null")) {
			MediaselectionActualValue=mediaSelectionValueInViewDevicePage;
		}else {
			MediaselectionActualValue=mediaSelectionValueInViewDevicePage;
		}
	  
	  verifyEnteredvalues_deviceName("Name", RouterIdValue,cpename );
	  
	  verifyEnteredvalues("Router Id", RouterIdValue);
	  
	  verifyEnteredvalues("Vendor/Model", vender);
	  
	  verifyEnteredvalues("Snmpro", snmpro);
	  
	//Management Address  
	  	if((existingmanagementAddress.equalsIgnoreCase("Yes")) && (newmanagementAddress.equalsIgnoreCase("no"))) {
		  verifyEnteredvalues("Management Address", manageaddressdropdownvalue);
		 }
		 else if((existingmanagementAddress.equalsIgnoreCase("no")) && (newmanagementAddress.equalsIgnoreCase("Yes"))) {
			 verifyEnteredvalues("Management Address", managementAddress);
		 } 
	  
	  
	  verifyEnteredvalues("Power Alarm", poweralarm);
	  
	  verifyEnteredvalues("Media Selection", MediaselectionActualValue);
	  
	  verifyEnteredvalues("Serial Number", serialNumber);
	  
	  verifyEnteredvalues("Link Lost Forwarding", linkLostForwarding);
	  
	  verifyEnteredvalues("Country", existingcountry);
	  
	//City  
		 if((existingcityselectionmode.equalsIgnoreCase("Yes")) && (newcityselectionmode.equalsIgnoreCase("no"))) {
			 verifyEnteredvalues("City", existingCity);
		 }
		 else if((existingcityselectionmode.equalsIgnoreCase("no")) && (newcityselectionmode.equalsIgnoreCase("Yes"))) {
			 verifyEnteredvalues("City", cityname);
		 } 
		 
		 
		//Site
		 if((existingsiteselectionmode.equalsIgnoreCase("Yes")) && (newsiteselectionmode.equalsIgnoreCase("no"))) {
			 verifyEnteredvalues("Site", existingSite);
		 }
		 else if((existingsiteselectionmode.equalsIgnoreCase("no")) && (newsiteselectionmode.equalsIgnoreCase("Yes"))) {
			 verifyEnteredvalues("Site", sitename);
		 } 
		 
		 
		//Premise
		 if((existingpremiseselectionmode.equalsIgnoreCase("Yes")) && (newpremiseselectionmode.equalsIgnoreCase("no"))) {
			 verifyEnteredvalues("Premise", existingPremise);
		 }
		 else if((existingpremiseselectionmode.equalsIgnoreCase("no")) && (newpremiseselectionmode.equalsIgnoreCase("Yes"))) {
			 verifyEnteredvalues("Premise", premisename);
		 } 

	}

	public void EDITCPEdevicedforIntermediateEquipment_1G(String application, String Cpename , String vender_1G_Overture,String vender_1G_Accedian,
			String vender_10G_Accedian,String snmpro,String managementAddress, String Mepid, String poweralarm_Overture, String poweralarm_Accedian,String Mediaselection, String Macaddress,
			String serialNumber, String hexaSerialnumber, String linkLostForwarding, String Country, 
			String ExistingCitySelection, String newCitySelection, String existingCity, String newCityName, String newCityCode,
			String ExistingSiteSelection, String NewSiteSelection, String ExistingSite, String NewSiteName, String NewSiteCode,
			String ExistingPremiseSelection, String newPremiseselection, String existingPremise, String newPremiseName, String newPremiseCode,
			String technologySelected) throws InterruptedException, DocumentException, IOException {
		
		scrollToTop();
		Thread.sleep(3000);
		
		if(technologySelected.equalsIgnoreCase("Overture")) {
			
			eDITCPEdevicedetailsentered_1G_Overture(application, Cpename, vender_1G_Overture, snmpro, managementAddress, Mepid, poweralarm_Overture, 
					Mediaselection, Macaddress, serialNumber, hexaSerialnumber, linkLostForwarding, Country,
					ExistingCitySelection, newCitySelection, existingCity, newCityName, newCityCode,
					ExistingSiteSelection, NewSiteSelection, ExistingSite, NewSiteName, NewSiteCode,
					ExistingPremiseSelection, newPremiseselection, existingPremise, newPremiseName, newPremiseCode);
		}
		else if((technologySelected.equalsIgnoreCase("Accedian")) || (technologySelected.equalsIgnoreCase("Accedian-1G"))) {
			
			eDITCPEdevicedetailsentered_1G_Accedian(application, Cpename, vender_1G_Accedian, snmpro, managementAddress, Mepid, poweralarm_Accedian,
					Mediaselection, Macaddress, serialNumber, hexaSerialnumber, linkLostForwarding, Country,
					ExistingCitySelection, newCitySelection, existingCity, newCityName, newCityCode,
					ExistingSiteSelection, NewSiteSelection, ExistingSite, NewSiteName, NewSiteCode,
					ExistingPremiseSelection, newPremiseselection, existingPremise, newPremiseName, newPremiseCode);
		}
		
	}
	
	
	public void EDITCPEdevice_IntermediateEquipment_10G(String application, String Cpename, String vender_10G_Accedian,String snmpro,String managementAddress,
			String Mepid, String poweralarm, String Mediaselection, String Macaddress,
			String serialNumber, String hexaSerialnumber, String linkLostForwarding, String Country,
			String ExistingCitySelection, String NewCitySelection, String existingCity, String newCityName, String newCityCode,
			String ExistingSiteSelection, String NewSiteSelection, String ExistingSite, String NewSiteName, String NewSiteCode,
			String ExistingPremiseSelection, String newPremiseselection, String existingPremise, String newPremiseName, String newPremiseCode,
			String technologySelected) throws InterruptedException, DocumentException, IOException {
		

		scrollToTop();
		Thread.sleep(3000);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "edit CPE device");
		
		Log.info("Entered edit functionalitty");

		
		click_commonMethod(application, "Action", "viewPCEdevice_Actiondropdown", xml);
		
		Thread.sleep(3000);
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/EditCPEdevicelinkunderviewpage")));
		Thread.sleep(3000);
		Log.info("edit functionality worked");
		
	     
	//Name field
		device_editnamefield(application, Cpename);
		
	//vendor/model
		device_editVendorModelField(application, vender_10G_Accedian);
		
	//Snmpro
		device_editSnmproField(application);

	//Management address
		device_editManagementAddressField(application, managementAddress);
		
	//Mepid	
		device_editMEPIdField(application, Mepid);
		
	//power alarm	
		device_editPowerAlarm(application, poweralarm);
	
	//Serial Number
		device_editserialnumber(application, serialNumber);
	    
	    
	//linklost forwarding	
		device_editlinkLostforwarding(application, linkLostForwarding);
	    
	    
		scrolltoend();
		Thread.sleep(3000);
		
		//Country
				if(!Country.equalsIgnoreCase("Null")) {
					
					selectValueInsideDropdown(application, "countryDropdown_selectTag", "Country", Country, xml);
					
					//New City		
					if(ExistingCitySelection.equalsIgnoreCase("no") & NewCitySelection.equalsIgnoreCase("yes")) {
						Clickon_addToggleButton(application, "addcityswitch");
					   //City name
						addtextFields_commonMethod(application, "City Name", "citynameinputfield", newCityName, xml);
					   //City Code	
						addtextFields_commonMethod(application, "City Code", "citycodeinputfield", newCityCode, xml);
					   //Site name
						addtextFields_commonMethod(application, "Site Name", "sitenameinputfield_addCityToggleSelected", NewSiteName, xml);
					   //Site Code
						addtextFields_commonMethod(application, "Site Code", "sitecodeinputfield_addCityToggleSelected", NewSiteCode, xml);
					   //Premise name	
						addtextFields_commonMethod(application, "Premise Name", "premisenameinputfield_addCityToggleSelected", newPremiseName, xml);
					   //Premise Code	
						addtextFields_commonMethod(application, "Premise Code", "premisecodeinputfield_addCityToggleSelected", newPremiseCode, xml);
							
					}	
				
				//Existing City	
					else if(ExistingCitySelection.equalsIgnoreCase("yes") & NewCitySelection.equalsIgnoreCase("no")) {
						
					   selectValueInsideDropdown(application, "cityDropdown_selectTag", "City", existingCity, xml);
						
						
					 //Existing Site
						  if(ExistingSiteSelection.equalsIgnoreCase("yes") & NewSiteSelection.equalsIgnoreCase("no")) {
							  selectValueInsideDropdown(application, "siteDropdown_selectTag", "Site", ExistingSite, xml);
							  
						 //Existing Premise
							  if(ExistingPremiseSelection.equalsIgnoreCase("yes") & newPremiseselection.equalsIgnoreCase("no")) {
								  selectValueInsideDropdown(application, "premiseDropdown_selectTag", "Premise", existingPremise, xml);
					          	 }
							  
							//New Premise  
							  else if(ExistingPremiseSelection.equalsIgnoreCase("no") & newPremiseselection.equalsIgnoreCase("yes")) {
								  
								  Clickon_addToggleButton(application, "addpremiseswitch");
								  //Premise name	
									addtextFields_commonMethod(application, "Premise Name", "premisenameinputfield_addPremiseToggleSelected", newPremiseName, xml);
								   //Premise Code	
									addtextFields_commonMethod(application, "Premise Code", "premisecodeinputfield_addPremiseToggleSelected", newPremiseCode, xml);
							  } 
				         	}
			  		
					  else if(ExistingSiteSelection.equalsIgnoreCase("no") & NewSiteSelection.equalsIgnoreCase("yes")) {
						  	
						  Clickon_addToggleButton(application, "addsiteswitch");
						  //Site name
							addtextFields_commonMethod(application, "Site Name", "sitenameinputfield_addSiteToggleSelected", NewSiteName, xml);
						   //Site Code
							addtextFields_commonMethod(application, "Site Code", "sitecodeinputfield_addSiteToggleSelected", NewSiteCode, xml);
							
						   //Premise name	
							addtextFields_commonMethod(application, "Premise Name", "premisenameinputfield_addSiteToggleSelected", newPremiseName, xml);
						   //Premise Code	
							addtextFields_commonMethod(application, "Premise Code", "premisecodeinputfield_addSiteToggleSelected", newPremiseCode, xml);
						  }
					}
					
				}
				else if(Country.equalsIgnoreCase("Null")) {
					
					ExtentTestManager.getTest().log(LogStatus.PASS, " No changes made for 'Country' dropdown");
				
				//City	
					editCity(application, ExistingCitySelection, NewCitySelection, "cityDropdown_selectTag", "selectcityswitch", "addcityswitch",
							existingCity, newCityName, newCityCode, "City");
					
					
				//Site	
					editSite(application, ExistingSiteSelection, NewSiteSelection, "siteDropdown_selectTag", "selectsiteswitch",
							"addsiteswitch", ExistingSite , NewSiteName, NewSiteCode, "Site");
					
				//Premise
					editPremise(application, ExistingPremiseSelection, newPremiseselection, "premiseDropdown_selectTag", "selectpremiseswitch",
							"addpremiseswitch", existingPremise, newPremiseName, newPremiseCode, "Premise");
					
				}
		
				scrolltoend();
		
		click_commonMethod(application, "OK", "okbutton", xml);
		Thread.sleep(2000);
		
	
	}
	
	
	public void EDITCPEdevicedforIntEquipment_1G_Overture(String application, String Cpename, String vender, String snmpro,
			String managementAddress, String Mepid, String poweralarm, String Mediaselection, String Macaddress,
			String serialNumber, String hexaSerialnumber, String linkLostForwarding, String Country, String City,
			String Site, String Premise, String technologySelected) throws InterruptedException, DocumentException, IOException {
		

		
		ExtentTestManager.getTest().log(LogStatus.INFO, "edit CPE device under Equipment");
		
		Log.info("Entered edit functionalitty");

		scrollToTop();
		
		click_commonMethod(application, "Action", "viewPCEdevice_Actiondropdown", xml);
		
		Thread.sleep(3000);
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/EditCPEdevicelinkunderviewpage")));
		Thread.sleep(3000);
		Log.info("edit functionality worked");
		
//		Clickon(getwebelement("(//div[div[div[text()='Equipment']]]//div[div[text()='"+ devicename+"']]/div/a/span[text()='Edit'])[2]"));

//		  Clickon(getwebelement(xml.getlocator("//locators/" + application + "/EditCPEdevlielink_underEquipment")));

	     
	     
	//Name field
		device_editnamefield(application, Cpename);
		
	//vendor/model
		device_editVendorModelField(application, vender);
		
	//Snmpro
		device_editSnmproField(application);

	//Management address
		device_editManagementAddressField(application, managementAddress);
		
	//Mepid	
		device_editMEPIdField(application, Mepid);
		
	//power alarm	
		device_editPowerAlarm(application, poweralarm);
		
	//Media Selection   
		device_editMediaselection(application, Mediaselection);
	    
	    
	//Mac address  
		device_editMACaddress(application, Macaddress);
	    
	//linklost forwarding	
		device_editlinkLostforwarding(application, linkLostForwarding);
	  
		scrolltoend();
	    
		click_commonMethod(application, "OK", "okbutton", xml);
		Thread.sleep(2000);
		
	
	}
		
	
	public void deleteDeviceFromServiceForequipment(String application, String devicename) throws InterruptedException, DocumentException {
		
		 
		waitforPagetobeenable();
		
		clickOnBankPage();
		Thread.sleep(1000);
		
		scrolltoend();
		Thread.sleep(1000);
		
		Clickon(getwebelement("//div[div[div[text()='Equipment']]]//div[div[div[contains(@title,'"+ devicename +"')]]]//span[text()='Delete from Service']"));
		Log.info(" 'Delete From Service' link has been clicked for cpe device under Equipment");
		Log.info(" 'Delete From Service' link has been clicked for cpe device under Equipment");
		ExtentTestManager.getTest().log(LogStatus.PASS, " 'Delete From Service' link has been clicked for cpe device under Equipment");


		boolean deletemessage=getwebelement(xml.getlocator("//locators/" + application + "/deleteMessage_equipment")).isDisplayed();
		while(deletemessage) {
			Log.info("Are you sure want to delete - message is getting displayed on clicking DeletefromService link");
			Log.info("Delete popup message is getting displayed");
			String actualMessage=getwebelement(xml.getlocator("//locators/" + application + "/deleteMessage_equipment")).getText();
			ExtentTestManager.getTest().log(LogStatus.PASS, "Delete device popup is displaying and popup message displays as: "+ actualMessage);
			Log.info( "Delete device popup is displaying and popup message displays as: "+ actualMessage);
			break;
		} 
		
		
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/deletebutton_deletefromsrviceforequipment")));
			ExtentTestManager.getTest().log(LogStatus.PASS, "clicked on 'delete' button");
			Thread.sleep(3000);
			ExtentTestManager.getTest().log(LogStatus.PASS, devicename + " device has got deleted from service");
			Log.info("Device got deleted from service as expected");

	}
	

public void deleteDeviceFromService_EquipmentConfig_Actelis(String application,String devicename) throws InterruptedException, DocumentException {
	
	
  
 waitforPagetobeenable();
 
 scrolltoend();
	
	click_commonMethod(application, "delete_link", "actelis_EquipConfig_deleteLink", xml);
	
	Log.info(" 'Delete From Service' link has been clicked for cpe device under 'Equipment Configuration' panel");
	ExtentTestManager.getTest().log(LogStatus.PASS, " 'Delete From Service' link has been clicked for cpe device under 'Equipment Configuration' panel");


	boolean deletemessage=getwebelement(xml.getlocator("//locators/" + application + "/deleteMessage_equipment")).isDisplayed();
	while(deletemessage) {
		Log.info("Are you sure want to delete - message is getting displayed on clicking DeletefromService link");
		Log.info("Delete popup message is getting displayed");
		String actualMessage=getwebelement(xml.getlocator("//locators/" + application + "/deleteMessage_equipment")).getText();
		ExtentTestManager.getTest().log(LogStatus.PASS, "Delete device popup is displaying and popup message displays as: "+ actualMessage);
		Log.info( "Delete device popup is displaying and popup message displays as: "+ actualMessage);
		break;
	} 
	
	
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/deletebutton_deletefromsrviceforequipment")));
		ExtentTestManager.getTest().log(LogStatus.PASS, "click on 'delete' button");
		Thread.sleep(3000);
		ExtentTestManager.getTest().log(LogStatus.PASS, devicename + " device has got deleted from service");
		Log.info("Device got deleted from service as expected");

}


	
	public void successMessage_deleteFromService(String application) throws InterruptedException, DocumentException {
		
		boolean successMessage=false;
	try {	
		successMessage=getwebelement(xml.getlocator("//locators/" + application + "/deleteDevice_successmEssage")).isDisplayed();
		
		if(successMessage) {
			
			String actualmessage=getwebelement(xml.getlocator("//locators/" + application + "/deleteDevice_successmEssage")).getText();
			
			ExtentTestManager.getTest().log(LogStatus.PASS, " Success Message for device deletion is dipslaying as expected");
			Log.info( " Success Message for device deletion is dipslaying as expected");
			
			ExtentTestManager.getTest().log(LogStatus.PASS, "Message displays as: "+actualmessage);
			Log.info("Message displays as: "+actualmessage);
			
		}else {
			ExtentTestManager.getTest().log(LogStatus.PASS, " Success Message for device deletion is not dipslaying");
			Log.info( " Success Message for device deletion is not dipslaying");
		}
		
	}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.PASS, " Success Message for device deletion is not dipslaying");
			Log.info( " Success Message for device deletion is not dipslaying");
		}
	}


	
public void deleteDeviceFromServiceForIntermediateequipment(String application, String devicename) throws InterruptedException, DocumentException {
	
	scrolltoend();
	Thread.sleep(2000);
	
	Clickon(getwebelement("//div[div[div[text()='Intermediate Equipment']]]//div[div[div[contains(@title,'"+ devicename +"')]]]//span[text()='Delete from Service']"));
	Log.info(" 'Delete From Service' link has been clicked for cpe device under Intermediate Equipment");
	Log.info(" 'Delete From Service' link has been clicked for cpe device under Intermediate Equipment");
	ExtentTestManager.getTest().log(LogStatus.PASS, " 'Delete From Service' link has been clicked for cpe device under Intermediate Equipment");


	boolean deletemessage=getwebelement(xml.getlocator("//locators/" + application + "/deleteMessage_equipment")).isDisplayed();
	while(deletemessage) {
		Log.info("Are you sure want to delete - message is getting displayed on clicking DeletefromService link");
		Log.info("Delete popup message is getting displayed");
		String actualMessage=getwebelement(xml.getlocator("//locators/" + application + "/deleteMessage_equipment")).getText();
		ExtentTestManager.getTest().log(LogStatus.PASS, "Delete device popup is displaying and popup message displays as: "+ actualMessage);
		Log.info("Delete device popup is displaying and popup message displays as: "+ actualMessage);
		break;
	} 
	
	
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/deletebutton_deletefromsrviceforequipment")));
		ExtentTestManager.getTest().log(LogStatus.PASS, "clicked on 'delete' button");
		Thread.sleep(3000);
		ExtentTestManager.getTest().log(LogStatus.PASS, devicename + " device has got deleted from service");
		Log.info("Device got deleted from service as expected");

	
}


   public void VerifythefieldsforProviderEquipment(String application) throws InterruptedException, DocumentException {
	 
	try {
		   
	  boolean IMSlocation= getwebelement(xml.getlocator("//locators/" + application + "/AddPElink_LANlinkoutband")).isDisplayed();
	  sa.assertTrue(IMSlocation," IMS location dropdown is not displaying under Provider Equipment ");
	  
	  
	 boolean togglebutton= getwebelement(xml.getlocator("//locators/" + application + "/selectdevice_ToggleButton")).isDisplayed();
	 sa.assertTrue(togglebutton, "Toggle button is not disaplaying under Provider Equipment");
	  
	 boolean name= getwebelement(xml.getlocator("//locators/" + application + "/Name")).isDisplayed();
	 sa.assertTrue(name, "NAme field is not displaying under Provider Equipment");
	 
	 boolean vendor= getwebelement(xml.getlocator("//locators/" + application + "/VenderModel")).isDisplayed();
	 sa.assertTrue(vendor, "Vendor/Model dropdown ");
	 
	  
	 boolean address= getwebelement(xml.getlocator("//locators/" + application + "/managementaddress")).isDisplayed();
	 sa.assertTrue(address, "Management Address field ");
	  
	  boolean snmpro=getwebelement(xml.getlocator("//locators/" + application + "/Snmpro")).isDisplayed();
	  sa.assertTrue(snmpro, "SNM pro field");
	  
	  boolean Country=getwebelement(xml.getlocator("//locators/" + application + "/country")).isDisplayed();
	  sa.assertTrue(Country, "Country dropdown");
	  
	  boolean city=getwebelement(xml.getlocator("//locators/" + application + "/city")).isDisplayed();
	  sa.assertTrue(city, "City dropdown");
	  
	  boolean Site=getwebelement(xml.getlocator("//locators/" + application + "/site")).isDisplayed();
	  sa.assertTrue(Site, "Sitedropdown");
	  
	  boolean Premise=getwebelement(xml.getlocator("//locators/" + application + "/premise")).isDisplayed();
	  sa.assertTrue(Premise, "Premise dropdown");
	  
	  boolean Nextbutton= getwebelement(xml.getlocator("//locators/" + application + "/Next_Button")).isDisplayed();
	  sa.assertTrue(Nextbutton, "Next button");
	  
	  boolean cancelbutton= getwebelement(xml.getlocator("//locators/" + application + "/cancelButton")).isDisplayed();
	  sa.assertTrue(cancelbutton, "Cancel button");
	  
	  
	  sa.assertAll();
	   
	   }catch(AssertionError e) {
		   e.printStackTrace();
	   }
	   
   }

 	

	public void providerEquipment(String application, String IMSlocation,
			String selectOrclicktogglebutttontocreateDevice, String name, String VendorModel, String ManagementAddress,
			String Snmpro, String Country, String City, String Site, String Premise)
			throws InterruptedException, DocumentException, IOException {

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddPElink_LANlinkoutband")));

		Log.info("----- Going to perform add PE device actions------------");

		if (selectOrclicktogglebutttontocreateDevice.equalsIgnoreCase("create")) {

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/selectdevice_ToggleButton")));

			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Name")), name);

//					Select(getwebelement(xml.getlocator("//locators/"+application+"/VenderModel")), VendorModel);

			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/managementaddress")),
					ManagementAddress);

			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Snmpro")), Snmpro);

//					Select(getwebelement(xml.getlocator("//locators/"+application+"/country")), Country);
//					
//					Select(getwebelement(xml.getlocator("//locators/"+application+"/city")), City);
//					
//					Select(getwebelement(xml.getlocator("//locators/"+application+"/site")), Site);
//					
//					Select(getwebelement(xml.getlocator("//locators/"+application+"/premise")), Premise);
//					
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Next_Button")));

		}

		else {

			Select(getwebelement(xml.getlocator("//locators/" + application + "/SelectIMSlocation")), IMSlocation);

		}

	}

	public void CustomerPremiseEquipment(String application, String IMSlocation,
			String selectOrclicktogglebutttontocreateDevice, String name, String VendorModel, String ManagementAddress,
			String Snmpro, String Country, String City, String Site, String Premise)
			throws InterruptedException, DocumentException, IOException {

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPElink_LANlinkoutband")));

		Log.info("----- Going to perform add PE device actions------------");

		if (selectOrclicktogglebutttontocreateDevice.equalsIgnoreCase("create")) {

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/selectdevice_ToggleButton")));

			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Name")), name);

//					Select(getwebelement(xml.getlocator("//locators/"+application+"/VenderModel")), VendorModel);

			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/managementaddress")),
					ManagementAddress);

			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Snmpro")), Snmpro);

//					Select(getwebelement(xml.getlocator("//locators/"+application+"/country")), Country);
//					
//					Select(getwebelement(xml.getlocator("//locators/"+application+"/city")), City);
//					
//					Select(getwebelement(xml.getlocator("//locators/"+application+"/site")), Site);
//					
//					Select(getwebelement(xml.getlocator("//locators/"+application+"/premise")), Premise);
//					
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Next_Button")));

		}

		else {

			Select(getwebelement(xml.getlocator("//locators/" + application + "/SelectIMSlocation")), IMSlocation);

		}

	}

	public void verifyPEdeviceEnteredvalue(String application, String name, String VendorModel, String ManagementAddress,
			String Snmpro, String Country, String City, String Site, String Premise)
			throws IOException, InterruptedException, DocumentException {

		try {
		String nAME = Gettext(
				getwebelement(xml.getlocator("//locators/" + application + "/verifyPEname_lanlinkoutband")));
		sa.assertEquals(nAME, name, "provider equipment Name is displaying as expected");

		String vendor = Gettext(
				getwebelement(xml.getlocator("//locators/" + application + "/verifyPEvendor_lanlinkoutband")));
		sa.assertEquals(vendor, VendorModel, "VendorModel is displaying as expectd");

		String address = Gettext(
				getwebelement(xml.getlocator("//locators/" + application + "/verifyPEaddress_lanlinkoutband")));
		sa.assertEquals(address, ManagementAddress, "ManagementAddress is displaying as expectd");

		String snmpro = Gettext(
				getwebelement(xml.getlocator("//locators/" + application + "/verifyPEsnmpro_lanlinkoutband")));
		sa.assertEquals(snmpro, Snmpro, "Snmpro is displaying as expectd");

		String country = Gettext(
				getwebelement(xml.getlocator("//locators/" + application + "/verifyPEcountry_lanlinkoutband")));
		sa.assertEquals(country, Country, "Country is displaying as expectd");

		String city = Gettext(
				getwebelement(xml.getlocator("//locators/" + application + "/verifyPECity_lanlinkoutband")));
		sa.assertEquals(city, City, "City is displaying as expectd");

		String sity = Gettext(
				getwebelement(xml.getlocator("//locators/" + application + "/verifyPESite_lanlinkoutband")));
		sa.assertEquals(sity, Site, "Site is displaying as expectd");

		String premise = Gettext(
				getwebelement(xml.getlocator("//locators/" + application + "/verifyPEPremise_lanlinkoutband")));
		sa.assertEquals(premise, Premise, "Premise is displaying as expectd");
		
		sa.assertAll();
		
		}catch(AssertionError e) {
			e.printStackTrace();
		}

	}
	
	public void EditProviderEquipment(String application, String IMSlocation,
			String selectOrclicktogglebutttontocreateDevice, String name, String VendorModel, String ManagementAddress,
			String Snmpro, String Country, String City, String Site, String Premise) throws InterruptedException, DocumentException, IOException {
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/providerEquipment_actiondropdownfromviewpage")));
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/providerEquipment_Editlinkunderviewdevicepage")));
		
		

		Log.info("----- Going to perform Edit PE device actions------------");

		if (selectOrclicktogglebutttontocreateDevice.equalsIgnoreCase("create")) {

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/selectdevice_ToggleButton")));

			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Name")), name);

//					Select(getwebelement(xml.getlocator("//locators/"+application+"/VenderModel")), VendorModel);

			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/managementaddress")),
					ManagementAddress);

			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Snmpro")), Snmpro);

//					Select(getwebelement(xml.getlocator("//locators/"+application+"/country")), Country);
//					
//					Select(getwebelement(xml.getlocator("//locators/"+application+"/city")), City);
//					
//					Select(getwebelement(xml.getlocator("//locators/"+application+"/site")), Site);
//					
//					Select(getwebelement(xml.getlocator("//locators/"+application+"/premise")), Premise);
//					
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Next_Button")));

		}

		else {

			Select(getwebelement(xml.getlocator("//locators/" + application + "/SelectIMSlocation")), IMSlocation);

		}
		
		
		Thread.sleep(3000);
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Backbutton")));


	}

	
	public void verifyAddDSLAMandHSLlink(String application, String DSLMdevice) throws InterruptedException, DocumentException {
		
		boolean actelisConfigurationPanel=false;
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying 'Add DSLAM and HSL'");
		
		 
		waitforPagetobeenable();
		
		scrolltoend();
		
		actelisConfigurationPanel=getwebelement(xml.getlocator("//locators/" + application + "/ActelisConfigurationPanel")).isDisplayed();
		
		if(actelisConfigurationPanel) {
			Log.info(" 'Actelis Configuration' panel is displaying as expected");
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Actelis Configuration' panel is displaying as expected");
			
			boolean actelisLink=false;
		try {	
			actelisLink=getwebelement(xml.getlocator("//locators/" + application + "/Actelisconfig_addDSLAM")).isDisplayed();
			if(actelisLink) {
				ExtentTestManager.getTest().log(LogStatus.PASS, " 'Add DSLAM and HSL' link is displaying as expected");
				
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Actelisconfig_addDSLAM")));
				Thread.sleep(3000);
				
			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Add DSLAM and HSL' link is not displaying under 'Actelis Configuration' panel");
			}
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " ''Add DSLAM and HSL' link is not displaying under 'Actelis Configuration' panel ");
		}catch(Exception err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " NOt able to click on 'Add DSLAM and HSL' link");
		}
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Actelis Configuration' panel is not displaying");
		}
		
}


	
	public void selectInterfacelinkforEqipment(String Application, String devicename) throws InterruptedException, DocumentException {

		ExtentTestManager.getTest().log(LogStatus.INFO, "check 'Select Interface' link");
		scrolltoend();
		Thread.sleep(3000);
		
		Clickon(getwebelement("//div[div[div[text()='Equipment']]]//div[div[div[contains(@title,'"+ devicename +"')]]]//span[text()='Select Interfaces']"));
		Log.info("SelectInterface link for Equipment is selected");
		Log.info("Select an inertface to add with the service under equipment");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Select Interface link has been clicked for cpe device under Equipment");
		
		Thread.sleep(3000);
	}
	
	//div[div[div[text()='Equipment']]]//div[div[div[contains(@title,'67bts.lanlink.dcn.colt.net')]]]//span[text()='Select Interfaces']
	
	public void selectInterfacelinkforProviderEqipment(String Application) throws InterruptedException, DocumentException {
		Thread.sleep(5000);

		Clickon(getwebelement(xml.getlocator("//locators/" + Application + "/Providerequipment_selectinterface")));
		Log.info("SelectInterface link for Equipment is selected");
		Log.info("Select an inertface to add with the service under equipment");

	}

	public void selectInterfacelinkforIntermediateEqipment(String Application, String devicename)
			throws InterruptedException, DocumentException {

		try {
			Thread.sleep(2000);
			 
			waitforPagetobeenable();
			scrolltoend();
		safeJavaScriptClick(getwebelement("//div[div[div[text()='Intermediate Equipment']]]//div[div[div[contains(@title,'"+ devicename +"')]]]//span[text()='Select Interfaces']"));
		Log.info("SelectInterface link for Intermediate Equipment is selected");
		Log.info("Select an interface to add with the service under Intermediate equipment");
		ExtentTestManager.getTest().log(LogStatus.PASS, "For " +devicename + " 'Select Interface' link has been clicked for cpe device under Intermediate Equipment");
	}catch(Exception e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, "Select Interface link is not available under Intermediate Equipment");
	}

	}

	public void SelectInterfacetoaddwithservcie(String Application, String interfacenumber)
			throws InterruptedException, DocumentException, IOException {

		scrolltoend();
		Thread.sleep(2000);
		
		selectrowforInterfaceToselecttable(Application, interfacenumber);

		
	}

	public void SelectInterfacetoremovefromservice(String Application, String interfacename)
			throws IOException, InterruptedException, DocumentException {

		WebElement manageAddress=getwebelement(xml.getlocator("//locators/" + Application + "/labelname_managementAddresss"));
		scrolltoview(manageAddress);
		Thread.sleep(1000);
		
		selectRowforInterfaceInService(Application, interfacename);

	
	}

	public void verifyInterfaceaddedToService(String application, String interfacenumber) {

		try {
		boolean result = driver
				.findElement(By.xpath("//div[div[contains(text(),'Interfaces in Service')]]/following-sibling::div[1]//div[div[text()='"+ interfacenumber+"']]//input"))
				.isDisplayed();
		
		if(result) {
			
			ExtentTestManager.getTest().log(LogStatus.PASS, "Verified: " +interfacenumber+ " has been added to service");
			
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Verified: "+interfacenumber+ " is not added to service");
		}
		} catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Failure while verifying whether interface got added to service");
		}

	}

	public void verifyInterfaceremovedFromService(String application, String interfacenumber)
			throws InterruptedException, DocumentException {

		try {
		boolean result = driver
				.findElement(By.xpath(""))
				.isDisplayed();
		//needs to star scripting from here
		if(result) {
			
			ExtentTestManager.getTest().log(LogStatus.PASS, "Verified: "+ interfacenumber + " has been removed from the service service");
			
		}else {
			
			ExtentTestManager.getTest().log(LogStatus.FAIL, "The selected interface " + interfacenumber +" is not removed from the service");
			
		}
		
		}catch(Exception e) {
			e.printStackTrace();
			Log.info("Failure while verifying the removed interface from the service");
		}

	}

	public void selectrowforInterfaceToselecttable(String Application, String interfacenumber)
			throws IOException, InterruptedException, DocumentException {

		int TotalPages;

		String TextKeyword = Gettext(
				getwebelement(xml.getlocator("//locators/" + Application + "/InterfaceToselect_totalpage")));

		TotalPages = Integer.parseInt(TextKeyword);

		Log.info("Total number of pages in Interface to select table is: " + TotalPages);

		ab:

		if (TotalPages != 0) {
			for (int k = 1; k <= TotalPages; k++) {

				// Current page
				String CurrentPage = Gettext(
						getwebelement(xml.getlocator("//locators/" + Application + "/InterfaceToselect_currentpage")));
				int Current_page = Integer.parseInt(CurrentPage);

				assertEquals(k, Current_page);

				Log.info("Currently we are in page number: " + Current_page);

				List<WebElement> results = getwebelements("//div[div[contains(text(),'Interfaces to Select')]]/following-sibling::div[1]//div[div[text()='"+ interfacenumber  +"']]//span[@class='ag-icon ag-icon-checkbox-unchecked']");
						
				int numofrows = results.size();
				Log.info("no of results: " + numofrows);
				boolean resultflag;

				if (numofrows == 0) {

					PageNavigation_NextPageForInterfaceToselect(Application);

				}

				else {

					for (int i = 0; i < numofrows; i++) {

						try {

							resultflag = results.get(i).isDisplayed();
							Log.info("status of result: " + resultflag);
							if (resultflag) {
								Log.info(results.get(i).getText());
								results.get(i).click();
								ExtentTestManager.getTest().log(LogStatus.PASS, interfacenumber + " is selected under 'Interface to select' table");
								Thread.sleep(3000);
								Clickon(getwebelement(xml.getlocator(
										"//locators/" + Application + "/InterfaceToselect_Actiondropdown")));

								Thread.sleep(2000);
								
								click_commonMethod(Application, "Add", "InterfaceToselect_addbuton", xml);
//								Clickon(getwebelement(xml.getlocator("//locators/" + Application + "/InterfaceToselect_addbuton")));
								Thread.sleep(3000);
								ExtentTestManager.getTest().log(LogStatus.PASS, interfacenumber + " is added to service");
								break ab;

							}

						} catch (StaleElementReferenceException e) {
							// TODO Auto-generated catch block
							// e.printStackTrace();
							results = driver.findElements(By.xpath("(//div[@class='row'])[8]//div[div[contains(text(),'"
									+ interfacenumber + "')]]//input"));
							numofrows = results.size();
							// results.get(i).click();
							Log.info("selected row is : " + i);
							ExtentTestManager.getTest().log(LogStatus.FAIL, " Failure on selecting an Interface to ad with service ");


						}

					}

					break ab;

				}

			}

		} else {

			Log.info("No values found inside the table");
			Log.info("No values available inside the Interfacetoselect table");
		}

	}

	public void selectRowforInterfaceInService(String Application, String interfacenumber)
			throws IOException, InterruptedException, DocumentException {

		int TotalPages;

		String TextKeyword = Gettext(
				getwebelement(xml.getlocator("//locators/" + Application + "/InterfaceInselect_totalpage")));

		TotalPages = Integer.parseInt(TextKeyword);

		Log.info("Total number of pages in table is: " + TotalPages);

		ab:

		if (TotalPages != 0) {
			for (int k = 1; k <= TotalPages; k++) {

				// Current page
				String CurrentPage = Gettext(
						getwebelement(xml.getlocator("//locators/" + Application + "/InterfaceInselect_currentpage")));
				int Current_page = Integer.parseInt(CurrentPage);

				assertEquals(k, Current_page);

				Log.info("Currently we are in page number: " + Current_page);

				List<WebElement> results = driver.findElements(By.xpath("//div[div[contains(text(),'Interfaces in Service')]]/following-sibling::div[1]//div[div[text()='" + interfacenumber +"']]//span[@class='ag-icon ag-icon-checkbox-unchecked']"));
				
				int numofrows = results.size();
				Log.info("no of results: " + numofrows);
				boolean resultflag;

				if (numofrows == 0) {

					PageNavigation_NextPageForInterfaceInService(Application);

				}

				else {

					for (int i = 0; i < numofrows; i++) {

						try {

							resultflag = results.get(i).isDisplayed();
							Log.info("status of result: " + resultflag);
							if (resultflag) {
								Log.info(results.get(i).getText());
								results.get(i).click();
								ExtentTestManager.getTest().log(LogStatus.PASS, interfacenumber + " is selected under 'Interface in Service' table");
								click_commonMethod(Application, "Action", "InterfaceInselect_Actiondropdown", xml);

								Thread.sleep(1000);
								
								click_commonMethod(Application, "Remove", "InterfaceInselect_removebuton", xml);

								ExtentTestManager.getTest().log(LogStatus.PASS, interfacenumber + " has been removed from service");
								break ab;
							}

						} catch (StaleElementReferenceException e) {
							// TODO Auto-generated catch block
							// e.printStackTrace();
							results = driver.findElements(By.xpath("//div[div[contains(text(),'Interfaces in Service')]]/following-sibling::div[1]//div[div[text()='"+ interfacenumber+"']]//input"));
							numofrows = results.size();
							// results.get(i).click();
							Log.info("selected row is : " + i);
							ExtentTestManager.getTest().log(LogStatus.FAIL, "failure while selecting interface to remove from service");

						}

					}

					break ab;

				}

			}

		} else {

			Log.info("No values available in table");
			Log.info("No values available inside the InterfaceInService table");
		}

	}

	public void PageNavigation_NextPageForInterfaceToselect(String Application)
			throws InterruptedException, DocumentException {

		Clickon(getwebelement(xml.getlocator("//locators/" + Application + "/InterfaceToselect_nextpage")));
		Thread.sleep(3000);

	}

	public void PageNavigation_NextPageForInterfaceInService(String Application)
			throws InterruptedException, DocumentException {

		Clickon(getwebelement(xml.getlocator("//locators/" + Application + "/InterfaceInselect_nextpage")));
		Thread.sleep(3000);

	}

	
	

	public void selectconfigurelinkAndverifyEditInterfacefield(String application,String devicename, String interfacename) throws InterruptedException, DocumentException, IOException {
		
		try {
		
		Clickon(getwebelement("//div[div[div[text()='Equipment']]]//div[div[div[contains(@title,'"+ devicename +"')]]]//span[text()='Configure']"));
		
		
		
		Thread.sleep(3000);
		
		selectRowForconfiglinkunderEquipmentconfig(application, interfacename);
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureActiondropdown")));
        Thread.sleep(1000);	 	
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureEditlink")));
		Thread.sleep(3000);		
		
	    
		boolean XNGcircuitID=getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureXNGCircuitID")).isDisplayed();
		sa.assertTrue(XNGcircuitID, "Circuit id is not displaying");
		
		Log.info("Entering bearer type dropdown");
		boolean BearerTypedropdown=getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureBearerType")).isDisplayed();
		sa.assertTrue(BearerTypedropdown, "Circuit bearer type dropdown is not displaying");
		
		boolean Bearerspeed=getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureBearerSpeed")).isDisplayed();
		sa.assertTrue(Bearerspeed, "Circut bearer speed dropdown is not displaying");
		
		boolean bandwidth=getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureTotalcircuitbandwidth")).isDisplayed();
		sa.assertTrue(bandwidth, "Total circuit bandwidth dropdown is not displaying");
		
		boolean vlan=getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureVLANid")).isDisplayed();
		sa.assertTrue(vlan, "VLAN ID field is not displaying");
		
		boolean vlantype=getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureVlanType")).isDisplayed();
		sa.assertTrue(vlantype, "VLANtype dropdown is not displaying");
		Log.info("vlan type failed");
		
		boolean ok=getwebelement(xml.getlocator("//locators/" + application + "/okbutton")).isDisplayed();
		sa.assertTrue(ok, "Ok Button is not displaying");
		
		boolean CANCEL=getwebelement(xml.getlocator("//locators/" + application + "/cancelButton")).isDisplayed();
		sa.assertTrue(CANCEL, "Cancel Button is not displaying");
		
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/cancelButton")));
		Thread.sleep(3000);
		
		sa.assertAll();
		
		ExtentTestManager.getTest().log(LogStatus.PASS, "Fields are verified for 'Edit Interface' under config link");
		}catch(AssertionError e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Field validation failed for 'Edit Interface' page");
		}
		
	}
	
public void selectconfigurelinkAndverifyEditInterfacefield__Equipment(String application, String devicename) throws InterruptedException, DocumentException, IOException {
	
	 
	waitforPagetobeenable();
	
	Thread.sleep(1000);
	clickOnBankPage();
	Thread.sleep(1000);
	
	scrolltoend();
	Thread.sleep(2000);
	
	Clickon(getwebelement("//div[div[div[text()='Equipment']]]//div[div[div[contains(@title,'"+ devicename +"')]]]//span[text()='Configure']"));
	  
waitforPagetobeenable();	
	WebElement searialnumber=getwebelement(xml.getlocator("//locators/" + application + "/labelName_searialnumber"));
	scrolltoview(searialnumber);
	Thread.sleep(2000);
	
		
	//Try to edit without selecting the interface
		click_commonMethod(application, "Action", "Equipment_configureActiondropdown", xml);
        Thread.sleep(1000);		
		
        click_commonMethod(application, "Edit", "Equipment_configureEditlink", xml);
		Thread.sleep(3000);	
		
		//check whether Alert message displays
		boolean alertMessage=false;
	try {	
		alertMessage=getwebelement(xml.getlocator("//locators/" + application + "/configure_alertPopup")).isDisplayed();
		if(alertMessage) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "Alert popup displays, if we click on 'Edit' without selected the interface");
			Log.info("Alert popup displays, if we click on 'Edit' without selected the interface");
			
			String alertMsg=getwebelement(xml.getlocator("//locators/" + application + "/configure_alertMessage")).getText();
			ExtentTestManager.getTest().log(LogStatus.PASS, "when we do not select any interface, Alert message displays as: "+alertMsg);
			Log.info(" Alert message displays as: "+alertMsg);
			
			click_commonMethod(application, "Alert popup 'X' button", "configure_alertPopup_xbutton", xml);
			Thread.sleep(2000);
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'Alert' popup is not displaying when we click on 'edit' without selecting an interface");
			Log.info("'Alert' popup is not displaying when we click on 'edit' without selecting an interface");
			
			boolean editInterface_popuptitleName=false;
		try {	
			editInterface_popuptitleName=getwebelement(xml.getlocator("//locators/" + application + "/Editinterface_popupTitlename")).isDisplayed();
			if(editInterface_popuptitleName) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Edit interface popup is displaying without selected an interface");
				Log.info("Edit interface popup is displaying without selected an interface");
				
				click_commonMethod(application, "Alert popup 'x' button", "EditInterfacepopup_xbutton", xml);
				Thread.sleep(2000);
			}
			}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Edit interface' popup is not displaying, without selecting an Interface");
			Log.info(" 'Edit interface' popup is not displaying, without selecting an Interface");
			}
		
		
		}}catch(NoSuchElementException e) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'Alert' popup is not displaying when we click on 'edit' without selecting an interface");
			Log.info("'Alert' popup is not displaying when we click on 'edit' without selecting an interface");
			
			
		}catch(Exception ee) {
			ee.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'Alert' popup is not displaying when we click on 'edit' without selecting an interface");
			Log.info("'Alert' popup is not displaying when we click on 'edit' without selecting an interface");
		}
		
		
	}
	
	
	
	
	public String SelectShowInterfacelinkAndVerifyEditInterfacePage(String application, String interfacename, String devicename) throws InterruptedException, DocumentException, IOException {
		
		waitforPagetobeenable();
		scrolltoend();
		Thread.sleep(2000);
		
		click_commonMethod(application, "showInterface_Link", "CPEdevice_showinterfaceslink", xml);
		Thread.sleep(1000);
		
		WebElement devinameRow=getwebelement("//div[div[div[text()='Equipment']]]//div[div[div[contains(@title,'"+ devicename +"')]]]");
		scrolltoview(devinameRow);
		Thread.sleep(2000);
		
		String interfaceAvailability = selectRowForEditingInterface_showInterface(application, interfacename, devicename);
		
		if(interfaceAvailability.equalsIgnoreCase("Yes")) {
			click_commonMethod(application, "Edit", "Equipment_showintefaceedit", xml);
			Thread.sleep(1000);	
		}
		
		return interfaceAvailability;	
		
	}
	
	/**
	 * click on hide interface link under device panel
	 * @param application
	 * @throws InterruptedException
	 * @throws DocumentException
	 */
	public void hideInterfaceLink_Equipment(String application) throws InterruptedException, DocumentException {
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/CPEdevice_hideInterfaeLink_eqiupment")));
		Thread.sleep(3000);
	}
	
	
	public void SelectShowInterfacelink_IntermediateequipmentAndVerifyEditInterfacePage(String application, String interfacename, String devicename) throws InterruptedException, DocumentException, IOException {
		
		scrolltoend();
		Thread.sleep(3000);
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/CPEdevice_showinterfaceslink_intEquip")));
		Thread.sleep(3000);
		
		selectRowForEditingInterface_showInterface(application, interfacename, devicename);
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Equipment_showintefaceedit")));
		Thread.sleep(3000);	
		
	}
	
	/**
	 * Click on hide interface link under Intermediate Equipment panel
	 * @param application
	 * @throws InterruptedException
	 * @throws DocumentException
	 */
	public void hideInterface_IntEquipment(String application) throws InterruptedException, DocumentException {
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/CPEdevoce_hideInterfacelink_intEquip")));
		Thread.sleep(3000);
		
	}
	
	
public void SelectShowInterfacelink_CustomerPremiseeequipmentAndVerifyEditInterfacePage(String application, String interfacename) throws InterruptedException, DocumentException, IOException {
		
		try {
		Thread.sleep(3000);
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/showInterface_ForIntermediateEquipment")));
		Thread.sleep(3000);
		
		selectRowUnderIntermediateEquipment(application, interfacename);
		
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/IntermediateEquipment_Actiondropdown")));
		Thread.sleep(3000);		
		
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/IntermediateEquipment_Editlink")));
		Thread.sleep(3000);		
		
		
		boolean XNGcircuitID=getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureXNGCircuitID")).isDisplayed();
		sa.assertTrue(XNGcircuitID, "Circuit id is not displaying");
		Log.info("circuit id is fetched");
		
		Log.info("Entering bearer type dropdown");
		boolean BearerTypedropdown=getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureBearerType")).isDisplayed();
		sa.assertTrue(BearerTypedropdown, "Circuit bearer type dropdown is not displaying");
		Log.info("bearer type dropdown is fetchecd");
		
		boolean Bearerspeed=getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureBearerSpeed")).isDisplayed();
		sa.assertTrue(Bearerspeed, "Circut bearer speed dropdown is not displaying");
		
		
		boolean bandwidth=getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureTotalcircuitbandwidth")).isDisplayed();
		sa.assertTrue(bandwidth, "Total circuit bandwidth dropdown is not displaying");
		
		boolean vlan=getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureVLANid")).isDisplayed();
		sa.assertTrue(vlan, "VLAN ID field is not displaying");
		
		boolean vlantype=getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureVlanType")).isDisplayed();
		sa.assertTrue(vlantype, "VLANtype dropdown is not displaying");
		
		
		boolean ok=getwebelement(xml.getlocator("//locators/" + application + "/okbutton")).isDisplayed();
		sa.assertTrue(ok, "Ok Button is not displaying");
		
		boolean CANCEL=getwebelement(xml.getlocator("//locators/" + application + "/cancelButton")).isDisplayed();
		sa.assertTrue(CANCEL, "Cancel Button is not displaying");
		
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/cancelButton")));
		Thread.sleep(3000);
		
		
		sa.assertAll();
		}catch(AssertionError e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	public void enterdataInsideEditInterfacepage(String application) {
		
	}



public String selectRowForEditingInterface_showInterface(String Application, String interfacename, String devicename) throws InterruptedException, DocumentException, IOException {


	int TotalPages;

	String TextKeyword = getwebelement("(//div[div[div[contains(@title,'"+ devicename +"')]]]/following-sibling::div)[1]//span[@ref='lbTotal']").getText();

	TotalPages = Integer.parseInt(TextKeyword);

	Log.info("Total number of pages in table is: " + TotalPages);
	
	String interfaceAvailability = "yes";
	ab:
	if (TotalPages != 0) {

		for (int k = 1; k <= TotalPages; k++) {

		// Current page
		String CurrentPage = getwebelement("(//div[div[div[contains(@title,'"+ devicename +"')]]]/following-sibling::div)[1]//span[@ref='lbCurrent']").getText();
		int Current_page = Integer.parseInt(CurrentPage);
		Log.info("The current page is: " + Current_page);

		assertEquals(k, Current_page);

		Log.info("Currently we are in page number: " + Current_page);

		List<WebElement> results = getwebelements("(//div[div[div[contains(@title,'"+ devicename +"')]]]/following-sibling::div)[1]//div[text()='"+ interfacename +"']");
		
			
		int numofrows = results.size();
		Log.info("no of results: " + numofrows);
		boolean resultflag;

	
		if ((numofrows == 0)) {
			
			Clickon(getwebelement("(//div[div[div[contains(@title,'"+ devicename +"')]]]/following-sibling::div)[1]//button[text()='Next']"));
			Thread.sleep(3000);

		}

		else {
			for (int i = 0; i < numofrows; i++) {
				try {

					resultflag = results.get(i).isDisplayed();
					Log.info("status of result: " + resultflag);
					if (resultflag) {
						Log.info(results.get(i).getText());
						results.get(i).click();
						ExtentTestManager.getTest().log(LogStatus.PASS, interfacename + " is selected under 'show Interface' ");
						Thread.sleep(3000);
						Clickon(getwebelement("(//div[div[div[contains(@title,'"+ devicename +"')]]]/following-sibling::div)[1]//button[text()='Action']"));
						break ab;
					}

				} catch (StaleElementReferenceException e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
					ExtentTestManager.getTest().log(LogStatus.FAIL, interfacename + " Interface name is not displaying");
					
					results = driver.findElements(
							By.xpath("(//div[@class='ag-root-wrapper ag-layout-auto-height ag-ltr'])[2]//div[text()='"+interfacename +"']"));
					numofrows = results.size();
					// results.get(i).click();
					Log.info("selected row is : " + i);

				}

			}
		}
	}
	}else {
		ExtentTestManager.getTest().log(LogStatus.FAIL, interfacename + " Interface name is not displaying");
		Log.info("No values available in table");
		Log.info("No values available inside the InterfaceInService table");
		ExtentTestManager.getTest().log(LogStatus.FAIL, "No value available inside 'show Interface' panel");
		interfaceAvailability = "No";
	}
	
	return interfaceAvailability;
	
}


public void selectRowForshowInterfaceunderProviderEquipment(String Application, String interfacename) throws IOException, InterruptedException, DocumentException {



	int TotalPages;

	// scroll down the page
//	  JavascriptExecutor js = ((JavascriptExecutor) driver);
//	  js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
//	  Thread.sleep(3000);

	String TextKeyword = Gettext(
			getwebelement(xml.getlocator("//locators/" + Application + "/providerEquipment_showinterfaceTatoalpage")));

	TotalPages = Integer.parseInt(TextKeyword);

	Log.info("Total number of pages in table is: " + TotalPages);

	ab:

	for (int k = 1; k <= TotalPages; k++) {

		// Current page
		String CurrentPage = Gettext(
				getwebelement(xml.getlocator("//locators/" + Application + "/providerEquipment_showinterfaceCurrentpage")));
		int Current_page = Integer.parseInt(CurrentPage);
		Log.info("The current page is: " + Current_page);

		assertEquals(k, Current_page);

		Log.info("Currently we are in page number: " + Current_page);

		List<WebElement> results = driver
				.findElements(By.xpath("(//div[@class='ag-body-viewport ag-layout-normal'])[1]//div[div[text()='"+interfacename +"']]//input"));
		
		
			
		int numofrows = results.size();
		Log.info("no of results: " + numofrows);
		boolean resultflag;

		if (numofrows == 0) {

			Clickon(getwebelement(xml.getlocator("//locators/" + Application + "/providerEquipment_showinterfaceNextpage")));

		}

		else {

			for (int i = 0; i < numofrows; i++) {

				try {

					resultflag = results.get(i).isDisplayed();
					Log.info("status of result: " + resultflag);
					if (resultflag) {
						Log.info(results.get(i).getText());
						results.get(i).click();
						Thread.sleep(3000);									
						break ab;
					}

				} catch (StaleElementReferenceException e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
					results = driver.findElements(
							By.xpath("(//div[@class='ag-body-viewport ag-layout-normal'])[1]//div[div[text()='"+interfacename +"']]//input"));
					numofrows = results.size();
					// results.get(i).click();
					Log.info("selected row is : " + i);

				}
			}
		}
	}
}



public void selectRowForshowInterfaceunderCustomerPremiseEquipment(String Application, String interfacename) throws IOException, InterruptedException, DocumentException {



	int TotalPages;

	// scroll down the page
//	  JavascriptExecutor js = ((JavascriptExecutor) driver);
//	  js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
//	  Thread.sleep(3000);

	String TextKeyword = Gettext(
			getwebelement(xml.getlocator("//locators/" + Application + "/providerEquipment_showinterfaceTatoalpage")));

	TotalPages = Integer.parseInt(TextKeyword);

	Log.info("Total number of pages in table is: " + TotalPages);

	ab:

	for (int k = 1; k <= TotalPages; k++) {

		// Current page
		String CurrentPage = Gettext(
				getwebelement(xml.getlocator("//locators/" + Application + "/providerEquipment_showinterfaceCurrentpage")));
		int Current_page = Integer.parseInt(CurrentPage);
		Log.info("The current page is: " + Current_page);

		assertEquals(k, Current_page);

		Log.info("Currently we are in page number: " + Current_page);

		List<WebElement> results = driver
				.findElements(By.xpath("(//div[@class='ag-body-viewport ag-layout-normal'])[1]//div[div[text()='"+interfacename +"']]//input"));
		
		
			
		int numofrows = results.size();
		Log.info("no of results: " + numofrows);
		boolean resultflag;

		if (numofrows == 0) {

			Clickon(getwebelement(xml.getlocator("//locators/" + Application + "/providerEquipment_showinterfaceNextpage")));

		}

		else {

			for (int i = 0; i < numofrows; i++) {

				try {

					resultflag = results.get(i).isDisplayed();
					Log.info("status of result: " + resultflag);
					if (resultflag) {
						Log.info(results.get(i).getText());
						results.get(i).click();
						Thread.sleep(3000);									
						break ab;
					}

				} catch (StaleElementReferenceException e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
					results = driver.findElements(
							By.xpath("(//div[@class='ag-body-viewport ag-layout-normal'])[1]//div[div[text()='"+interfacename +"']]//input"));
					numofrows = results.size();
					// results.get(i).click();
					Log.info("selected row is : " + i);

				}

			}


		}

	}

}



public void selectRowForconfiglinkunderEquipmentconfig(String Application, String interfacename) throws InterruptedException, DocumentException, IOException {


	int TotalPages;

	// scroll down the page
//	  JavascriptExecutor js = ((JavascriptExecutor) driver);
//	  js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
//	  Thread.sleep(3000);

	String TextKeyword = Gettext(
			getwebelement(xml.getlocator("//locators/" + Application + "/Equipmentconfig_Totalpage")));

	TotalPages = Integer.parseInt(TextKeyword);

	Log.info("Total number of pages in table is: " + TotalPages);

	ab:

	if (TotalPages != 0) {	
		for (int k = 1; k <= TotalPages; k++) {

		// Current page
		String CurrentPage = Gettext(
				getwebelement(xml.getlocator("//locators/" + Application + "/Equipmentconfig_Currentpage")));
		int Current_page = Integer.parseInt(CurrentPage);
		Log.info("The current page is: " + Current_page);

		assertEquals(k, Current_page);

		Log.info("Currently we are in page number: " + Current_page);

		List<WebElement> results = driver
				.findElements(By.xpath("//div[div[text()='Interfaces']]/following-sibling::div[1]//div[text()='"+interfacename +"']"));
		
		
			
		int numofrows = results.size();
		Log.info("no of results: " + numofrows);
		boolean resultflag;

		if (numofrows == 0) {

            Clickon(getwebelement(xml.getlocator("//locators/" + Application + "/Equipmentconfig_nextpage")));		
            Thread.sleep(3000);
		}

		else {

			for (int i = 0; i < numofrows; i++) {

				try {

					resultflag = results.get(i).isDisplayed();
					Log.info("status of result: " + resultflag);
					if (resultflag) {
						Log.info(results.get(i).getText());
						results.get(i).click();
						ExtentTestManager.getTest().log(LogStatus.PASS, interfacename + " is selected");
						Thread.sleep(3000);										
						break ab;
					}

				} catch (StaleElementReferenceException e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
					results = driver.findElements(
							By.xpath("//div[div[text()='Interfaces']]/following-sibling::div[1]//div[text()='\"+interfacename +\"']"));
					numofrows = results.size();
					// results.get(i).click();
					Log.info("selected row is : " + i);

				}

			}


		}

	 }
	}else {
		Log.info("No values available in table");
		Log.info("No values available inside the InterfaceInService table");
		ExtentTestManager.getTest().log(LogStatus.FAIL, "No value available inside the table for configure ");
	}
}


public String selectRowForconfiglinkunderIntermediateEquipment(String Application, String interfacename) throws InterruptedException, DocumentException, IOException {


	int TotalPages;

	// scroll down the page
//	  JavascriptExecutor js = ((JavascriptExecutor) driver);
//	  js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
//	  Thread.sleep(3000);

	String interfaceAvailability = "Yes";
	String TextKeyword = Gettext(
			getwebelement(xml.getlocator("//locators/" + Application + "/configure_totalPage")));

	TotalPages = Integer.parseInt(TextKeyword);

	Log.info("Total number of pages in table is: " + TotalPages);

	ab:
if (TotalPages != 0) {	
	  for (int k = 1; k <= TotalPages; k++) {

		// Current page
		String CurrentPage = Gettext(
				getwebelement(xml.getlocator("//locators/" + Application + "/configure_currentpage")));
		int Current_page = Integer.parseInt(CurrentPage);
		Log.info("The current page is: " + Current_page);

		assertEquals(k, Current_page);

		Log.info("Currently we are in page number: " + Current_page);

		List<WebElement> results = driver
				.findElements(By.xpath("//div[@role='row']//div[text()='"+ interfacename +"']"));
		
		
			
		int numofrows = results.size();
		Log.info("no of results: " + numofrows);
		boolean resultflag;

		if (numofrows == 0) {

			Clickon(getwebelement(xml.getlocator("//locators/" + Application + "/intermediateequip_nextpage")));

		}

		else {

			for (int i = 0; i < numofrows; i++) {

				try {

					resultflag = results.get(i).isDisplayed();
					Log.info("status of result: " + resultflag);
					if (resultflag) {
						Log.info(results.get(i).getText());
						results.get(i).click();
						ExtentTestManager.getTest().log(LogStatus.PASS, interfacename + " is selected");
						Thread.sleep(3000);
						break ab;
					}

				} catch (StaleElementReferenceException e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
					results = driver.findElements(
							By.xpath("//div[@role='row']//div[text()='"+ interfacename +"']"));
					numofrows = results.size();
					// results.get(i).click();
					Log.info("selected row is : " + i);

				}

			}


		}

	}
}else {
	Log.info("No values present inside the table");
	ExtentTestManager.getTest().log(LogStatus.FAIL, "No interface present inside the table");
	interfaceAvailability = "No";
}
	
	return interfaceAvailability;
	
}



public void selectRowUnderIntermediateEquipment(String Application, String interfacename) throws InterruptedException, DocumentException, IOException {

	int TotalPages;
		
	String TextKeyword = Gettext(
			getwebelement(xml.getlocator("//locators/" + Application + "/totalpage_intermeiateshowinterfacelink")));

	TotalPages = Integer.parseInt(TextKeyword);

	Log.info("Total number of pages in table is: " + TotalPages);

	ab:

	if (TotalPages != 0) {	
		for (int k = 1; k <= TotalPages; k++) {

		// Current page
		String CurrentPage = Gettext(
				getwebelement(xml.getlocator("//locators/" + Application + "/currentpage_intermeduateshowinterfacelink")));
		int Current_page = Integer.parseInt(CurrentPage);
		Log.info("The current page is: " + Current_page);

		assertEquals(k, Current_page);

		Log.info("Currently we are in page number: " + Current_page);

		List<WebElement> results = driver
				.findElements(By.xpath("(//div[@class='ag-root-wrapper ag-layout-auto-height ag-ltr'])[8]//div[div[text()='"+interfacename +"']]//input"));

		int numofrows = results.size();
		Log.info("no of results: " + numofrows);
		boolean resultflag;

		if (numofrows == 0) {

			Clickon(getwebelement(xml.getlocator("//locators/" + Application + "/pagenavigateforIntermediate")));

		}

		else {

			for (int i = 0; i < numofrows; i++) {

				try {

					resultflag = results.get(i).isDisplayed();
					Log.info("status of result: " + resultflag);
					if (resultflag) {
						Log.info(results.get(i).getText());
						results.get(i).click();
						ExtentTestManager.getTest().log(LogStatus.PASS, interfacename + " is selected after clicking on 'show Interface' ");
						Thread.sleep(3000);
						break ab;
					}

				} catch (StaleElementReferenceException e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
					results = driver.findElements(
							By.xpath(""));
					numofrows = results.size();
					// results.get(i).click();
					Log.info("selected row is : " + i);

				}

		   	}

	    	}

    	} 
	}else {
		
		Log.info("No values available in table");
		Log.info("No values available inside the InterfaceInService table");
		ExtentTestManager.getTest().log(LogStatus.FAIL, "No value available inside 'show Interface' panel");
	}

}


    public void VerifyDataEnteredForSiteOrder(String application, String interfaceSpeed, String VPNtopology, String circuitType,
			String country, String city, String CSR_Name, String site,
			String performReport, String ProactiveMonitor, String smartmonitor, String technology, String siteallias,
			String VLANid, String DCAenabledsite, String cloudserviceprovider, String sitevalue, String remark,
			String xngcityname, String xngcitycode,String devicename, String nonterminatepoinr, String Protected, String newcityselection, String existingcityselection,
			String existingsiteselection, String newsiteselection,
			String siteOrderNumber, String circuitref, String offnetSelection, String IVReference,
  			String GCRolo, String Vlan, String Vlanether, String primaryVlan, String primaryVlanether,String EPNoffnet,String EPNEOSDH)
			throws InterruptedException, DocumentException, IOException{
    	
    	ExtentTestManager.getTest().log(LogStatus.INFO, "Verify entered value for 'Site Order'");
    	
    	if(VPNtopology.equals("Point-to-Point")) {	
	    	VerifyDataEnteredForSiteOrder_viewSiteOrder_P2P(application, country, city, CSR_Name, site, performReport, ProactiveMonitor, smartmonitor,
	    			technology, siteallias, VLANid, DCAenabledsite, cloudserviceprovider, sitevalue, remark, xngcityname, xngcitycode, devicename,
	    			nonterminatepoinr, Protected, newcityselection, existingcityselection, existingsiteselection, newsiteselection);
    	}
    	
    	else if(IVReference.equals("Primary")) {
    		
    		if(VPNtopology.equals("Hub&Spoke")) {
    		
    		VerifyDataEnteredForSiteOrder_viewSiteOrder_hubAndSpoke_Primay(application, country, city, CSR_Name, site, performReport, ProactiveMonitor, smartmonitor,
        			technology, siteallias, VLANid, DCAenabledsite, cloudserviceprovider, sitevalue, remark, xngcityname, xngcitycode, devicename,
        			nonterminatepoinr, Protected, newcityselection, existingcityselection, existingsiteselection, newsiteselection, siteOrderNumber, 
        			circuitref, offnetSelection, IVReference, GCRolo, Vlan, Vlanether, primaryVlan, primaryVlanether, EPNoffnet, EPNEOSDH);
    		}
    		else if(VPNtopology.equals("E-PN (Any-to-Any)")) {
    			VerifyDataEnteredForSiteOrder_viewSiteOrder_EPN_Primay(application, country, city, CSR_Name, site, performReport, ProactiveMonitor, smartmonitor,
            			technology, siteallias, VLANid, DCAenabledsite, cloudserviceprovider, sitevalue, remark, xngcityname, xngcitycode, devicename,
            			nonterminatepoinr, Protected, newcityselection, existingcityselection, existingsiteselection, newsiteselection, siteOrderNumber, 
            			circuitref, offnetSelection, IVReference, GCRolo, Vlan, Vlanether, primaryVlan, primaryVlanether, EPNoffnet, EPNEOSDH);
    			
    		}
    		
    	}
    	
    	else if(IVReference.equals("Access")) {
    		
    		if(VPNtopology.equals("Hub&Spoke")) {
    			
    			VerifyDataEnteredForSiteOrder_viewSiteOrder_hubAndSpoke_Access(application, country, city, CSR_Name, site, performReport, ProactiveMonitor, smartmonitor,
            			technology, siteallias, VLANid, DCAenabledsite, cloudserviceprovider, sitevalue, remark, xngcityname, xngcitycode, devicename,
            			nonterminatepoinr, Protected, newcityselection, existingcityselection, existingsiteselection, newsiteselection, siteOrderNumber, 
            			circuitref, offnetSelection, IVReference, GCRolo, Vlan, Vlanether, primaryVlan, primaryVlanether, EPNoffnet, EPNEOSDH);
    			
    		}
    		else if(VPNtopology.equals("E-PN (Any-to-Any)")) {
    			
    			VerifyDataEnteredForSiteOrder_viewSiteOrder_EPN_Access(application, country, city, CSR_Name, site, performReport, ProactiveMonitor, smartmonitor,
            			technology, siteallias, VLANid, DCAenabledsite, cloudserviceprovider, sitevalue, remark, xngcityname, xngcitycode, devicename,
            			nonterminatepoinr, Protected, newcityselection, existingcityselection, existingsiteselection, newsiteselection, siteOrderNumber, 
            			circuitref, offnetSelection, IVReference, GCRolo, Vlan, Vlanether, primaryVlan, primaryVlanether, EPNoffnet, EPNEOSDH);
    			
    		}
    	}	
    }
    
    
    public void VerifyDataEnteredForSiteOrder_viewSiteOrder_P2P(String application, String country, String city, String CSR_Name, String site,
			String performReport, String ProactiveMonitor, String smartmonitor, String technology, String siteallias,
			String VLANid, String DCAenabledsite, String cloudserviceprovider, String sitevalue, String remark,
			String xngcityname, String xngcitycode,String devicename, String nonterminatepoinr, String Protected, String newcityselection, String existingcityselection,
			String existingsiteselection, String newsiteselection)
			throws InterruptedException, DocumentException, IOException{
    	
    //Country	
    	verifyEnteredvalues("Device Country", country);
    	
    //City
    	
    	if((existingcityselection.equalsIgnoreCase("yes")) & (newcityselection.equalsIgnoreCase("no"))) {
    		
    	//City	
    		verifyEnteredvalues("Device Xng City", city);
    		
    	//Site
    		if((existingsiteselection.equalsIgnoreCase("yes")) & (newsiteselection.equalsIgnoreCase("no"))) {
    			String siteValue=splitPhysicalSitealue(application, site);
    			verifyEnteredvalues("Physical Site: CSR Name", siteValue);
    		}
    		
    		else if((existingsiteselection.equalsIgnoreCase("no")) & (newsiteselection.equalsIgnoreCase("yes"))) {
    			verifyEnteredvalues("Physical Site: CSR Name", CSR_Name);
    		}
    		
    	}
    	else if((existingcityselection.equalsIgnoreCase("no")) & (newcityselection.equalsIgnoreCase("yes"))) {
    		
    		//New City
    		verifyEnteredvalues("Device Xng City", xngcitycode);
    		
    		//New Site
    		verifyEnteredvalues("Physical Site: CSR Name", CSR_Name);
    		
    	}
    	
    	
    //Performance Reporting
    	verifyEnteredvalues("Performance Reporting", performReport);
    	
    //Proactive Monitoring
    	verifyEnteredvalues("Proactive Monitoring", ProactiveMonitor);
    	
    //Smarts Monitoring
    	verifyEnteredvalues("Smarts Monitoring", smartmonitor);
    	
    //Technology
    	verifyEnteredvalues("Technology", technology);
    	
    	
    //Site Alias
    	verifyEnteredvalues("Site Alias", siteallias);
    	
    //VLAN Id
//    	verifyEnteredvalues("VLAN Id", VLANid);
    	
    //DCA Enabled Site
    	verifyEnteredvalues("DCA Enabled Site", DCAenabledsite);
    	
    	if(DCAenabledsite.equalsIgnoreCase("Yes")) {
    		
    		//Cloud Service Provider
    		     verifyEnteredvalues("Cloud Service Provider", cloudserviceprovider);
    		
    	}
    	
    //Remark
    	compareText(application, "Remark", "remark_viewPage", remark, xml);
    	
    	
    	if(technology.equals("Atrica")) {
    		//Non_termination Point checkbox
    			verifyEnteredvalues("Non Termination Point", nonterminatepoinr);
    		
    		//Device Name text field
    			verifyEnteredvalues("Device Name", devicename);
    		
    		
    		//Protected checkbox
    			verifyEnteredvalues("Protected", Protected);
    	}
    	
    	
    	if(technology.equals("Overture")) {
    		//Non_termination Point checkbox
    			verifyEnteredvalues("Non Termination Point", nonterminatepoinr);
    		
    		//Protected checkbox
    			verifyEnteredvalues("Protected", Protected);
    	}
    	
    	
    	if(technology.equals("Alu")) {
    		//Device Name text field
			verifyEnteredvalues("Device Name", devicename);
    	}
    	
    	if(technology.equals("Accedian-1G")) {
    		//Non_termination Point checkbox
			verifyEnteredvalues("Non Termination Point", nonterminatepoinr);

			//Protected checkbox
			verifyEnteredvalues("Protected", Protected);
    	}
    	
    	if(technology.equals("Cyan")) {
    		//Non_termination Point checkbox
			verifyEnteredvalues("Non Termination Point", nonterminatepoinr);
    	}
    }
    
    
    
    public void VerifyDataEnteredForSiteOrder_viewSiteOrder_hubAndSpoke_Primay(String application, String country, String city, String CSR_Name, String site,
			String performReport, String ProactiveMonitor, String smartmonitor, String technology, String siteallias,
			String VLANid, String DCAenabledsite, String cloudserviceprovider, String sitevalue, String remark,
			String xngcityname, String xngcitycode,String devicename, String nonterminatepoinr, String Protected, String newcityselection, String existingcityselection,
			String existingsiteselection, String newsiteselection,
			String siteOrderNumber, String circuitref, String offnetSelection, String IVReference,
  			String GCRolo, String Vlan, String Vlanether, String primaryVlan, String primaryVlanether,String EPNoffnet,String EPNEOSDH)
			throws InterruptedException, DocumentException, IOException{
    	
    	
    //Site order Number
    	verifyEnteredvalues("Site Order Number", siteOrderNumber);
    	
    //Country	
    	verifyEnteredvalues("Device Country", country);
    	
    //City
    	
    	if((existingcityselection.equalsIgnoreCase("yes")) & (newcityselection.equalsIgnoreCase("no"))) {
    		
    	//City	
    		verifyEnteredvalues("Device Xng City", city);
    		
    	//Site
    		if((existingsiteselection.equalsIgnoreCase("yes")) & (newsiteselection.equalsIgnoreCase("no"))) {
    			String siteValue=splitPhysicalSitealue(application, site);
    			verifyEnteredvalues("Physical Site: CSR Name", siteValue);
    		}
    		
    		else if((existingsiteselection.equalsIgnoreCase("no")) & (newsiteselection.equalsIgnoreCase("yes"))) {
    			verifyEnteredvalues("Physical Site: CSR Name", CSR_Name);
    		}
    		
    	}
    	else if((existingcityselection.equalsIgnoreCase("no")) & (newcityselection.equalsIgnoreCase("yes"))) {
    		
    		//New City
    		verifyEnteredvalues("Device Xng City", xngcitycode);
    		
    		//New Site
    		verifyEnteredvalues("Physical Site: CSR Name", CSR_Name);
    		
    	}
    	
    	
    //Performance Reporting
    	verifyEnteredvalues("Performance Reporting", performReport);
    	
    //Proactive Monitoring
    	verifyEnteredvalues("Proactive Monitoring", ProactiveMonitor);
    	Thread.sleep(3000);
    	
    //Smarts Monitoring
    	verifyEnteredvalues("Smarts Monitoring", smartmonitor);
    	
    //Technology
    	verifyEnteredvalues("Technology", technology);
    	
    //Site Alias
    	verifyEnteredvalues("Site Alias", siteallias);
    
    //IV Reference
    	verifyEnteredvalues("IV Reference", IVReference);
    	
    	
    //Remark
    	compareText(application, "Remark", "remark_viewPage", remark, xml);
    	
    	
    	if(technology.equals("Atrica")) {
    		//Non_termination Point checkbox
    			verifyEnteredvalues("Non Termination Point", nonterminatepoinr);
    		
    		//Device Name text field
    			verifyEnteredvalues("Device Name", devicename);
    		
    		//Protected checkbox
    			verifyEnteredvalues("Protected", Protected);
    	}
    	
    	
    	if(technology.equals("Overture")) {
    		//Non_termination Point checkbox
    			verifyEnteredvalues("Non Termination Point", nonterminatepoinr);
    		
    		//Protected checkbox
    			verifyEnteredvalues("Protected", Protected);
    	}
    	
    	
    	if(technology.equals("Alu")) {
    		//Device Name text field
			verifyEnteredvalues("Device Name", devicename);
    	}
    	
    	if(technology.equals("Accedian-1G")) {
    		//Non_termination Point checkbox
			verifyEnteredvalues("Non Termination Point", nonterminatepoinr);

			//Protected checkbox
			verifyEnteredvalues("Protected", Protected);
    	}
    	
    	if(technology.equals("Cyan")) {
    		//Non_termination Point checkbox
			verifyEnteredvalues("Non Termination Point", nonterminatepoinr);
    	}
    	
    	
    	
    }
    
    
    public void VerifyDataEnteredForSiteOrder_viewSiteOrder_EPN_Primay(String application, String country, String city, String CSR_Name, String site,
			String performReport, String ProactiveMonitor, String smartmonitor, String technology, String siteallias,
			String VLANid, String DCAenabledsite, String cloudserviceprovider, String sitevalue, String remark,
			String xngcityname, String xngcitycode,String devicename, String nonterminatepoinr, String Protected, String newcityselection, String existingcityselection,
			String existingsiteselection, String newsiteselection,
			String siteOrderNumber, String circuitref, String offnetSelection, String IVReference,
  			String GCRolo, String Vlan, String Vlanether, String primaryVlan, String primaryVlanether,String EPNoffnet,String EPNEOSDH)
			throws InterruptedException, DocumentException, IOException{
    	
    	
    //Site order Number
    	verifyEnteredvalues("Site Order Number", siteOrderNumber);
    	
    //Country	
    	verifyEnteredvalues("Device Country", country);
    	
    //City
    	
    	if((existingcityselection.equalsIgnoreCase("yes")) & (newcityselection.equalsIgnoreCase("no"))) {
    		
    	//City	
    		verifyEnteredvalues("Device Xng City", city);
    		
    	//Site
    		if((existingsiteselection.equalsIgnoreCase("yes")) & (newsiteselection.equalsIgnoreCase("no"))) {
    			String siteValue=splitPhysicalSitealue(application, site);
    			verifyEnteredvalues("Physical Site: CSR Name", siteValue);
    		}
    		
    		else if((existingsiteselection.equalsIgnoreCase("no")) & (newsiteselection.equalsIgnoreCase("yes"))) {
    			verifyEnteredvalues("Physical Site: CSR Name", CSR_Name);
    		}
    		
    	}
    	else if((existingcityselection.equalsIgnoreCase("no")) & (newcityselection.equalsIgnoreCase("yes"))) {
    		
    		//New City
    		verifyEnteredvalues("Device Xng City", xngcitycode);
    		
    		//New Site
    		verifyEnteredvalues("Physical Site: CSR Name", CSR_Name);
    		
    	}
    	
    	
    //Performance Reporting
    	verifyEnteredvalues("Performance Reporting", performReport);
    	
    //Proactive Monitoring
    	verifyEnteredvalues("Proactive Monitoring", ProactiveMonitor);
    	
    //Smarts Monitoring
    	verifyEnteredvalues("Smarts Monitoring", smartmonitor);
    	
    //Technology
    	verifyEnteredvalues("Technology", technology);
    	
    //Site Alias
    	verifyEnteredvalues("Site Alias", siteallias);
    
    //IV Reference
    	verifyEnteredvalues("IV Reference", IVReference);
    	
    	
    //Remark
    	compareText(application, "Remark", "remark_viewPage", remark, xml);
    	
    	
    	if(technology.equals("Atrica")) {
    		//Non_termination Point checkbox
    			verifyEnteredvalues("Non Termination Point", nonterminatepoinr);
    		
    		//Device Name text field
    			verifyEnteredvalues("Device Name", devicename);
    		
    		
    		//Protected checkbox
    			verifyEnteredvalues("Protected", Protected);
    	}
    	
    	
    	if(technology.equals("Overture")) {
    		//Non_termination Point checkbox
    			verifyEnteredvalues("Non Termination Point", nonterminatepoinr);
    			
    		//Device Name text field
    			verifyEnteredvalues("Device Name", devicename);
    		
    		//Protected checkbox
    			verifyEnteredvalues("Protected", Protected);
    			
    		//EPN Offnet
    			if(EPNoffnet.equalsIgnoreCase("yes")) {
    				verifyEnteredvalues("EPN Offnet", EPNoffnet);
    			}else{
    				Log.info("EPN offnet will not display, if it is selected");
    			}
    			
    			
    		//EPN EOSDH
    			if(EPNEOSDH.equalsIgnoreCase("Yes")) {
    				verifyEnteredvalues("EPN EOSDH", EPNEOSDH);
    			}else {
    				Log.info("EPN EOSDH will not display, if it is not selected");
    			}
    			
    	}
    	
    	
    	if(technology.equals("Alu")) {
    		//Device Name text field
			verifyEnteredvalues("Device Name", devicename);
    	}
    	
    	if(technology.equals("Accedian-1G")) {
    		//Non_termination Point checkbox
			verifyEnteredvalues("Non Termination Point", nonterminatepoinr);
			
			//Device Name text field
//			verifyEnteredvalues("Device Name", devicename);

			//Protected checkbox
			verifyEnteredvalues("Protected", Protected);
    	}
    	
    	if(technology.equals("Cyan")) {
    		//Non_termination Point checkbox
			verifyEnteredvalues("Non Termination Point", nonterminatepoinr);
    	}
    	
    	
    	
    }
    
    
    
    public void VerifyDataEnteredForSiteOrder_viewSiteOrder_EPN_Access(String application, String country, String city, String CSR_Name, String site,
			String performReport, String ProactiveMonitor, String smartmonitor, String technology, String siteallias,
			String VLANid, String DCAenabledsite, String cloudserviceprovider, String sitevalue, String remark,
			String xngcityname, String xngcitycode,String devicename, String nonterminatepoinr, String Protected, String newcityselection, String existingcityselection,
			String existingsiteselection, String newsiteselection,
			String siteOrderNumber, String circuitref, String offnetSelection, String IVReference,
  			String GCRolo, String Vlan, String Vlanether, String primaryVlan, String primaryVlanether,String EPNoffnet,String EPNEOSDH)
			throws InterruptedException, DocumentException, IOException{
    	
    	
    //Site order Number
    	verifyEnteredvalues("Site Order Number", siteOrderNumber);
    	
    //Country	
    	verifyEnteredvalues("Device Country", country);
    	
    //City
    	
    	if((existingcityselection.equalsIgnoreCase("yes")) & (newcityselection.equalsIgnoreCase("no"))) {
    		
    	//City	
    		verifyEnteredvalues("Device Xng City", city);
    		
    	//Site
    		if((existingsiteselection.equalsIgnoreCase("yes")) & (newsiteselection.equalsIgnoreCase("no"))) {
    			String siteValue=splitPhysicalSitealue(application, site);
    			verifyEnteredvalues("Physical Site: CSR Name", siteValue);
    		}
    		
    		else if((existingsiteselection.equalsIgnoreCase("no")) & (newsiteselection.equalsIgnoreCase("yes"))) {
    			verifyEnteredvalues("Physical Site: CSR Name", CSR_Name);
    		}
    		
    	}
    	else if((existingcityselection.equalsIgnoreCase("no")) & (newcityselection.equalsIgnoreCase("yes"))) {
    		
    		//New City
    		verifyEnteredvalues("Device Xng City", xngcitycode);
    		
    		//New Site
    		verifyEnteredvalues("Physical Site: CSR Name", CSR_Name);
    		
    	}
    	
    	
    //Performance Reporting
    	verifyEnteredvalues("Performance Reporting", performReport);
    	
    //Proactive Monitoring
    	verifyEnteredvalues("Proactive Monitoring", ProactiveMonitor);
    	
    //Smarts Monitoring
    	verifyEnteredvalues("Smarts Monitoring", smartmonitor);
    	
    //Technology
    	verifyEnteredvalues("Technology", technology);
    	
    //Site Alias
    	verifyEnteredvalues("Site Alias", siteallias);
    
    //IV Reference
    	verifyEnteredvalues("IV Reference", IVReference);
    	
    	
    //Remark
    	compareText(application, "Remark", "remark_viewPage", remark, xml);
    	
    	
    	if(technology.equals("Atrica")) {
    		//Non_termination Point checkbox
    			verifyEnteredvalues("Non Termination Point", nonterminatepoinr);
    		
    		//Device Name text field
    			verifyEnteredvalues("Device Name", devicename);
    		
    		
    		//Protected checkbox
    			verifyEnteredvalues("Protected", Protected);
    	}
    	
    	
    	if(technology.equals("Overture")) {
    		//Non_termination Point checkbox
    			verifyEnteredvalues("Non Termination Point", nonterminatepoinr);
    			
    		//Device Name text field
    			verifyEnteredvalues("Device Name", devicename);
    		
    		//Protected checkbox
    			verifyEnteredvalues("Protected", Protected);
    			
    		//EPN Offnet
    			verifyEnteredvalues("EPN Offnet", EPNoffnet);
    			
    		//EPN EOSDH
    			
    	}
    	
    	
    	if(technology.equals("Alu")) {
    	//Device Name text field
			verifyEnteredvalues("Device Name", devicename);
    	}
    	
    	if(technology.equals("Accedian-1G")) {
    		//Non_termination Point checkbox
			verifyEnteredvalues("Non Termination Point", nonterminatepoinr);
			
			//Device Name text field
//			verifyEnteredvalues("Device Name", devicename);

			//Protected checkbox
			verifyEnteredvalues("Protected", Protected);
    	}
    	
    	if(technology.equals("Cyan")) {
    		//Non_termination Point checkbox
			verifyEnteredvalues("Non Termination Point", nonterminatepoinr);
    	}
    	
    	
    	
    }
    
    
    
    public void VerifyDataEnteredForSiteOrder_viewSiteOrder_hubAndSpoke_Access(String application, String country, String city, String CSR_Name, String site,
			String performReport, String ProactiveMonitor, String smartmonitor, String technology, String siteallias,
			String VLANid, String DCAenabledsite, String cloudserviceprovider, String sitevalue, String remark,
			String xngcityname, String xngcitycode,String devicename, String nonterminatepoinr, String Protected, String newcityselection, String existingcityselection,
			String existingsiteselection, String newsiteselection,
			String siteOrderNumber, String circuitref, String offnetSelection, String IVReference,
  			String GCRolo, String Vlan, String Vlanether, String primaryVlan, String primaryVlanether,String EPNoffnet,String EPNEOSDH)
			throws InterruptedException, DocumentException, IOException{
    	
    	
    //Site order Number
    	verifyEnteredvalues("Site Order Number", siteOrderNumber);
    	
    //Country	
    	verifyEnteredvalues("Device Country", country);
    	
    //City
    	
    	if((existingcityselection.equalsIgnoreCase("yes")) & (newcityselection.equalsIgnoreCase("no"))) {
    		
    	//City	
    		verifyEnteredvalues("Device Xng City", city);
    		
    	//Site
    		if((existingsiteselection.equalsIgnoreCase("yes")) & (newsiteselection.equalsIgnoreCase("no"))) {
    			String siteValue=splitPhysicalSitealue(application, site);
    			verifyEnteredvalues("Physical Site: CSR Name", siteValue);
    		}
    		
    		else if((existingsiteselection.equalsIgnoreCase("no")) & (newsiteselection.equalsIgnoreCase("yes"))) {
    			verifyEnteredvalues("Physical Site: CSR Name", CSR_Name);
    		}
    		
    	}
    	else if((existingcityselection.equalsIgnoreCase("no")) & (newcityselection.equalsIgnoreCase("yes"))) {
    		
    		//New City
    		verifyEnteredvalues("Device Xng City", xngcitycode);
    		
    		//New Site
    		verifyEnteredvalues("Physical Site: CSR Name", CSR_Name);
    		
    	}
    	
    	
    //Performance Reporting
    	verifyEnteredvalues("Performance Reporting", performReport);
    	
    //Proactive Monitoring
    	verifyEnteredvalues("Proactive Monitoring", ProactiveMonitor);
    	
    //Smarts Monitoring
    	verifyEnteredvalues("Smarts Monitoring", smartmonitor);
    	
    //Technology
    	verifyEnteredvalues("Technology", technology);
    	
    //Site Alias
    	verifyEnteredvalues("Site Alias", siteallias);
    
    //IV Reference
    	verifyEnteredvalues("IV Reference", IVReference);
    	
    	
    //Remark
    	compareText(application, "Remark", "remark_viewPage", remark, xml);
    	
    //Based on Technology	
    	if(technology.equals("Atrica")) {
    		//Non_termination Point checkbox
    			verifyEnteredvalues("Non Termination Point", nonterminatepoinr);
    		
    		//Protected checkbox
    			verifyEnteredvalues("Protected", Protected);
    	}
    	
    	
    	if(technology.equals("Overture")) {
    		//Non_termination Point checkbox
    			verifyEnteredvalues("Non Termination Point", nonterminatepoinr);
    		
    		//Protected checkbox
    			verifyEnteredvalues("Protected", Protected);
    			
    		
    		//GCR OLO Type dropdown
    			verifyEnteredvalues("GCR OLO Type", GCRolo);
    			
    		//VLAN tetx field
    			verifyEnteredvalues("VLAN", Vlan);
    			
    		//VLAN Ether type dropdown
    			verifyEnteredvalues("VLAN Ether Type", Vlanether);
    			
    		//Primary VLAN Text field
    			verifyEnteredvalues("Primary VLAN", primaryVlan);
    			
    		//Primary VLAN Ether Type
    			verifyEnteredvalues("Primary VLAN Ether Type", primaryVlanether);
    	}
    	
    	
    	if(technology.equals("Alu")) {
    		
    		Log.info("No additional Fields");
    	}
    	
    	if(technology.equals("Accedian-1G")) {
    		//Non_termination Point checkbox
			verifyEnteredvalues("Non Termination Point", nonterminatepoinr);

			//Protected checkbox
			verifyEnteredvalues("Protected", Protected);
    	}
    	
    	if(technology.equals("Cyan")) {
    		//Non_termination Point checkbox
			verifyEnteredvalues("Non Termination Point", nonterminatepoinr);
    	}
    	
    	
    	
    }
    
    
    
    public void VerifySiteOrderdetailsInTable(String Application, String siteordernumber) throws InterruptedException, DocumentException, IOException {

    	Thread.sleep(5000);
    	
    	
    	Log.info("Entererd inside the table");
    	
		int TotalPages;

		// scroll down the page
//		  JavascriptExecutor js = ((JavascriptExecutor) driver);
//		  js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
//		  Thread.sleep(3000);

		String TextKeyword = Gettext(
				getwebelement(xml.getlocator("//locators/" + Application + "/TotalPagesforsiteorder")));

		TotalPages = Integer.parseInt(TextKeyword);

		Log.info("Total number of pages in table is: " + TotalPages);

		ab:

  if(TotalPages>=1) {			
		for (int k = 1; k <= TotalPages; k++) {

			// Current page
			String CurrentPage = Gettext(
					getwebelement(xml.getlocator("//locators/" + Application + "/Currentpageforsiteorder")));
			int Current_page = Integer.parseInt(CurrentPage);

			
			Log.info("Checking whether next page button is disabled or not");
	
				
				boolean nextpage= getwebelement(xml.getlocator("//locators/" + Application + "/Pagenavigationfornextpage")).isEnabled();
	
				Log.info("Entered while loop");
  while(nextpage)
  {
	  Log.info("its enabled");
	  break;
  }
			
		
			assertEquals(k, Current_page);


			List<WebElement> results = driver
					.findElements(By.xpath("//div[div[contains(text(),'" + siteordernumber + "')]]"));

			int numofrows = results.size();
			Log.info("no of results: " + numofrows);
			boolean resultflag;

			if (numofrows == 0) {

				PageNavigation_NextPage(Application);

			}

			else {

				for (int i = 0; i < numofrows; i++) {

					try {

						resultflag = results.get(i).isDisplayed();
						Log.info("status of result: " + resultflag);
						if (resultflag) {
							Log.info("The field values are: "+results.get(i).getText());
							Log.info("The values stored in the table for adding site order are: "+results.get(i).getText());
							
						}

					} catch (StaleElementReferenceException e) {
						// TODO Auto-generated catch block
						// e.printStackTrace();
						results = driver.findElements(
								By.xpath("//div[div[contains(text(),'" + siteordernumber + "')]]"));
						numofrows = results.size();
						// results.get(i).click();
						Log.info("selected row is : " + i);

					}

				}

				break ab;

			}

		}
		
      }else {
    	  Log.info("The data entered for adding site order is not getting displyed inside the site order table.");
    	  Log.info("No values inside the site order table");
      }

	}
    
    public void returnbacktoviewsiteorderpage(String application) throws InterruptedException, DocumentException {
    	
//    	 
    	waitforPagetobeenable();
    	
    	scrolltoend();
    	Thread.sleep(2000);
    	
    	click_commonMethod(application, "Back", "Backbutton", xml);
    	Thread.sleep(3000);
    }
    
    
    public void EnterdataForEditInterfaceforShowInterfacelinkunderEquipment(String application, String interfacename, String circuitID, String bearertype, String bearerspeed, String totalbandwidth,
    		String vlanid, String vlantype) throws InterruptedException, DocumentException, IOException {
    	

			//verify Edit Interface page	
				boolean editInterface_popuptitleName=false;
				try {	
					editInterface_popuptitleName=getwebelement(xml.getlocator("//locators/" + application + "/Editinterface_popupTitlename")).isDisplayed();
					if(editInterface_popuptitleName) {
						ExtentTestManager.getTest().log(LogStatus.PASS, "Edit interface popup is displaying as expected");
						Log.info("Edit interface popup is displaying as expected");
						
						
					//Interface name
				    	verifyenteredvaluesForEditPage_noneditableFields(application, "Interface", interfacename);
				    	
				    //Circuit ID	
					     configure_circuitId(application, circuitID );
					
					 //Bearer type	
					     addDropdownValues_bearerType(application, "Bearer Type", "Equipment_configureBearerType", bearertype, xml);
					 
					 //Bearerspeed
					     addDropdownValues_commonMethod(application, "Bearer Speed", "Equipment_configureBearerSpeed", bearerspeed, xml);
						
					//Total Circuit bandwidth	
					     addDropdownValues_totalCircuitBandwidth(application, "Total Circuit Bandwidth", "Equipment_configureTotalcircuitbandwidth", totalbandwidth, xml);
					     
					//VLAN type 
					     addDropdownValues_commonMethod(application, "VLAN Type", "Equipment_configureVlanType", vlantype, xml);
					
					//vlan	
					     addtextFields_commonMethod(application, "VLAN Id", "Equipment_configureVLANid", vlanid, xml);
					 Thread.sleep(3000);
								
					
					}
					}catch(Exception e) {
					e.printStackTrace();
					ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Edit interface' popup is not displaying");
					Log.info(" 'Edit interface' popup is not displaying");
					}
				
		
	ExtentTestManager.getTest().log(LogStatus.PASS, "Data has been entered inside Edit Interface page by selecting configure link under Intermediate Equipment");	
		
		click_commonMethod(application, "OK", "okbutton", xml);
		Thread.sleep(3000);
	
		   
    
    }


    public void EnterdataForEditInterfaceforShowInterfacelinkunderProviderEquipment(String application, String interfacename, String circuitID, String bearertype, String bearerspeed, String totalbandwidth,
    		String vlanid, String vlantype) throws InterruptedException, DocumentException, IOException {
    	

    	
    	Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Equipment_showInterfaceActiondropdown")));
		Thread.sleep(3000);		
		
    	
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureEditlink")));
		Thread.sleep(3000);		
		
		
		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureXNGCircuitID")), circuitID);
		

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureBearerType")));
		Clickon(getwebelement("//div[text()='"+bearertype +"']"));
		
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureBearerSpeed")));
		Clickon(getwebelement("//div[text()='"+bearerspeed +"']"));
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureTotalcircuitbandwidth")));
		Clickon(getwebelement("//div[text()='"+totalbandwidth +"']"));
		
		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureVLANid")), vlanid);
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureVlanType")));
		Clickon(getwebelement("//div[text()='"+vlantype +"']"));
		
		
		click_commonMethod(application, "OK", "okbutton", xml);
		Thread.sleep(5000);
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/CPEdevice_hideinterfaceslinkforequipment")));
		Thread.sleep(3000);
		   
    }


    public void EnterdataForEditInterfaceforShowInterfacelinkunderIntermediateEquipment(String application, String interfacename, String circuitID, String bearertype, String bearerspeed, String totalbandwidth,
    		String vlanid, String vlantype) throws InterruptedException, DocumentException, IOException {
    	

			//verify Edit Interface page	
				boolean editInterface_popuptitleName=false;
				try {	
					editInterface_popuptitleName=getwebelement(xml.getlocator("//locators/" + application + "/Editinterface_popupTitlename")).isDisplayed();
					if(editInterface_popuptitleName) {
						ExtentTestManager.getTest().log(LogStatus.PASS, "Edit interface popup is displaying as expected");
						Log.info("Edit interface popup is displaying as expected");
						
						
					//Interface name
				    	verifyenteredvaluesForEditPage_noneditableFields(application, "Interface", interfacename);
				    	
				    //Circuit ID	
					     configure_circuitId(application, circuitID );
					
					 //Bearer type	
					     addDropdownValues_bearerType(application, "Bearer Type", "Equipment_configureBearerType", bearertype, xml);
					 
					 //Bearerspeed
					     addDropdownValues_commonMethod(application, "Bearer Speed", "Equipment_configureBearerSpeed", bearerspeed, xml);
						
					//Total Circuit bandwidth	
					     addDropdownValues_totalCircuitBandwidth(application, "Total Circuit Bandwidth", "Equipment_configureTotalcircuitbandwidth", totalbandwidth, xml);
					     
					//VLAN type 
					     addDropdownValues_commonMethod(application, "VLAN Type", "Equipment_configureVlanType", vlantype, xml);
					
					//vlan	
					     addtextFields_commonMethod(application, "VLAN Id", "Equipment_configureVLANid", vlanid, xml);
					 Thread.sleep(3000);
								
					
					}
					}catch(Exception e) {
					e.printStackTrace();
					ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Edit interface' popup is not displaying");
					Log.info(" 'Edit interface' popup is not displaying");
					}
				
		
	ExtentTestManager.getTest().log(LogStatus.PASS, "Data has been entered inside Edit Interface page by selecting configure link under Intermediate Equipment");	
		
		click_commonMethod(application, "OK", "okbutton", xml);
		Thread.sleep(3000);
	
		   
    
    }

    
    public void EnterdataForEditInterfaceforConfigurelinkunderEquipment(String application, String interfacename, String circuitID, String bearertype, 
    		String bearerspeed, String totalbandwidth,String vlanid, String vlantype) throws InterruptedException, DocumentException, IOException {
    	

    	 
	    Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureActiondropdown")));
        Thread.sleep(1000);		
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureEditlink")));
		Thread.sleep(3000);			
		
	//Circuit ID	
	     configure_circuitId(application, circuitID );
	
	 //Bearer type	
	     addDropdownValues_bearerType(application, "Bearer Type", "Equipment_configureBearerType", bearertype, xml);
	 
	 //Bearerspeed
	     addDropdownValues_commonMethod(application, "Bearer Speed", "Equipment_configureBearerSpeed", bearerspeed, xml);
		
	//Circuit bandwidth	
	     addDropdownValues_totalCircuitBandwidth(application, "Total Circuit Bandwidth", "Equipment_configureTotalcircuitbandwidth", totalbandwidth, xml);
	     
	//VLAN type 
	     addDropdownValues_commonMethod(application, "VLAN Type", "Equipment_configureVlanType", vlantype, xml);
	
//vlan	
	 try {
		if(vlanid.equalsIgnoreCase("null")) {
			
			ExtentTestManager.getTest().log(LogStatus.PASS, " No input provided for 'Vlan id'");
			
		}else {
		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureVLANid")), vlanid);
		ExtentTestManager.getTest().log(LogStatus.PASS, vlanid + " is the value passed for 'Vlan ID' field");
		}
		
	 }catch(NoSuchElementException e) {
		 e.printStackTrace();
		 ExtentTestManager.getTest().log(LogStatus.FAIL, "'Vlan id' dropdown under 'Edit Interface' page is not available");
	 }catch(Exception err) {
		 err.printStackTrace();
		 ExtentTestManager.getTest().log(LogStatus.FAIL, "Not able to enter value for 'Vlan id' field");
	 }
	 Thread.sleep(3000);
		
		
		
		
		
		ExtentTestManager.getTest().log(LogStatus.PASS, "Data has been entered inside Edit Interfaec page by selecting configure link under Equipment");
		
		click_commonMethod(application, "OK", "okbutton", xml);
		Thread.sleep(3000);
		   
    }
   
	
    
    public String EnterdataForEditInterfaceforConfigurelinkunderIntermediateEquipment(String application, String interfacename, String circuitID, String bearertype, String bearerspeed, String totalbandwidth,
    		String vlanid, String vlantype) throws InterruptedException, DocumentException, IOException {
    	

    	//select the Interface	
			String interfaceavailability = selectRowForconfiglinkunderIntermediateEquipment(application, interfacename);
			
		if(interfaceavailability.equalsIgnoreCase("Yes")) {	
			//Perform edit Interface
				click_commonMethod(application, "Action","Equipment_configureActiondropdown", xml);
		        Thread.sleep(1000);		
				
		        click_commonMethod(application, "Edit", "Equipment_configureEditlink", xml);
				Thread.sleep(1000);	
				
			//verify Edit Interface page	
				boolean editInterface_popuptitleName=false;
				try {	
					editInterface_popuptitleName=getwebelement(xml.getlocator("//locators/" + application + "/Editinterface_popupTitlename")).isDisplayed();
					if(editInterface_popuptitleName) {
						ExtentTestManager.getTest().log(LogStatus.PASS, "Edit interface popup is displaying as expected");
						Log.info("Edit interface popup is displaying as expected");
						
						
					//Interface name
				    	verifyenteredvaluesForEditPage_noneditableFields(application, "Interface", interfacename);
				    	Thread.sleep(3000);
				    	
				    //Circuit ID	
					     configure_circuitId(application, circuitID );
					
					 //Bearer type	
					     addDropdownValues_bearerType(application, "Bearer Type", "Equipment_configureBearerType", bearertype, xml);
					 Thread.sleep(1000);
					     
					     
					 //Bearerspeed
					     addDropdownValues_commonMethod(application, "Bearer Speed", "Equipment_configureBearerSpeed", bearerspeed, xml);
					     Thread.sleep(1000);
						
					//Total Circuit bandwidth	
					     addDropdownValues_totalCircuitBandwidth(application, "Total Circuit Bandwidth", "Equipment_configureTotalcircuitbandwidth", totalbandwidth, xml);
					     Thread.sleep(1000);
					     
					//VLAN type 
					     addDropdownValues_commonMethod(application, "VLAN Type", "Equipment_configureVlanType", vlantype, xml);
					     Thread.sleep(1000);
					
					//vlan	
					     addtextFields_commonMethod(application, "VLAN Id", "Equipment_configureVLANid", vlanid, xml);
					 Thread.sleep(3000);
								
					
					}
					}catch(NoSuchElementException e) {
					e.printStackTrace();
					ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Edit interface' popup is not displaying");
					Log.info(" 'Edit interface' popup is not displaying");
					}
				
		
	ExtentTestManager.getTest().log(LogStatus.PASS, "Data has been entered inside Edit Interface page by selecting configure link under Intermediate Equipment");	
		
		click_commonMethod(application, "OK", "okbutton", xml);
	
		} 
		
		return interfaceavailability;
    }
    
    
    
    
    public void AddInterfaceToserviceforProviderEquipment(String Application, String interfacenumber) throws IOException, InterruptedException, DocumentException {
    	
    	int TotalPages;

		String TextKeyword = Gettext(
				getwebelement(xml.getlocator("//locators/" + Application + "/providerEquipment_InterfaceToselectTotalpage")));

		TotalPages = Integer.parseInt(TextKeyword);

		Log.info("Total number of pages in Interface to select table is: " + TotalPages);

		ab:

		if (TotalPages != 0) {
			for (int k = 1; k <= TotalPages; k++) {

				// Current page
				String CurrentPage = Gettext(
						getwebelement(xml.getlocator("//locators/" + Application + "/providerEquipment_currentpageInterfaceToselect")));
				int Current_page = Integer.parseInt(CurrentPage);

				assertEquals(k, Current_page);

				Log.info("Currently we are in page number: " + Current_page);

				List<WebElement> results = driver.findElements(By.xpath("(//div[@class='row'][2]//div[div[contains(text(),'"+interfacenumber +"')]])//input"));
				
				int numofrows = results.size();
				Log.info("no of results: " + numofrows);
				boolean resultflag;

				if (numofrows == 0) {

					Clickon(getwebelement(xml.getlocator(
							"//locators/" + Application + "/providerEquipment_InterfaceToselectnextpage")));


				}
				
				else {

					for (int i = 0; i < numofrows; i++) {

						try {

							resultflag = results.get(i).isDisplayed();
							Log.info("status of result: " + resultflag);
							if (resultflag) {
								Log.info(results.get(i).getText());
								results.get(i).click();
								Thread.sleep(3000);
								Clickon(getwebelement(xml.getlocator(
										"//locators/" + Application + "/providerEquipment_InterfaceToselectActiondropdown")));

								Thread.sleep(5000);
								
								Clickon(getwebelement(xml.getlocator("//locators/" + Application + "/providerEquipment_InterfaceToselectAddbutton")));


							}

						} catch (StaleElementReferenceException e) {
							// TODO Auto-generated catch block
							// e.printStackTrace();
							results = driver.findElements(By.xpath("(//div[@class='row'][2]//div[div[contains(text(),'"+interfacenumber +"')]])//input"));
							numofrows = results.size();
							// results.get(i).click();
							Log.info("selected row is : " + i);

						}

					}

					break ab;

				}

			}

		} else {

			Log.info("No values found inside the table");
			Log.info("No values available inside the Interfacetoselect table");
		}

    }

    
    public void verifyInterfaceaddedToServiceForProviderEquipment(String application, String interfacenumber) {

		try {
		boolean result = driver
				.findElement(By.xpath("(//div[@class='row'][1]//div[div[contains(text(),'"+interfacenumber +"')]])"))
				.isDisplayed();
		sa.assertTrue(result, "Verified: Interface got added to service");
		} catch(Exception e) {
			Log.info("No values available inside the table");
		}

	}
    
    
    public void ProviderEquipmentInterfaceInService(String Application, String interfacenumber) throws IOException, InterruptedException, DocumentException {
    	


		int TotalPages;

		String TextKeyword = Gettext(
				getwebelement(xml.getlocator("//locators/" + Application + "/providerEquipment_InterfaceInselectTotalpage")));

		TotalPages = Integer.parseInt(TextKeyword);

		Log.info("Total number of pages in table is: " + TotalPages);

		ab:

		if (TotalPages != 0) {
			for (int k = 1; k <= TotalPages; k++) {

				// Current page
				String CurrentPage = Gettext(
						getwebelement(xml.getlocator("//locators/" + Application + "/providerEquipment_currentpageInterfaceInselect")));
				int Current_page = Integer.parseInt(CurrentPage);

				assertEquals(k, Current_page);

				Log.info("Currently we are in page number: " + Current_page);

				List<WebElement> results = driver.findElements(By.xpath("(//div[@class='row'][1]//div[div[contains(text(),'"+interfacenumber +"')]])//input"));

				int numofrows = results.size();
				Log.info("no of results: " + numofrows);
				boolean resultflag;

				if (numofrows == 0) {

					getwebelement(xml.getlocator("//locators/" + Application + "/providerEquipment_InterfaceInselectnextpage"));
				}

				else {

					for (int i = 0; i < numofrows; i++) {

						try {

							resultflag = results.get(i).isDisplayed();
							Log.info("status of result: " + resultflag);
							if (resultflag) {
								Log.info(results.get(i).getText());
								results.get(i).click();
								Thread.sleep(3000);
								Clickon(getwebelement(xml.getlocator(
										"//locators/" + Application + "/InterfaceInselect_Actiondropdown")));

								Thread.sleep(5000);
								
								Clickon(getwebelement(xml.getlocator("//locators/" + Application + "/InterfaceToselect_removebuton")));


							}

						} catch (StaleElementReferenceException e) {
							// TODO Auto-generated catch block
							// e.printStackTrace();
							results = driver.findElements(By.xpath("(//div[@class='row'][1]//div[div[contains(text(),'\"+interfacenumber +\"')]])//input"));
							numofrows = results.size();
							// results.get(i).click();
							Log.info("selected row is : " + i);

						}

					}

					break ab;

				}

			}

		} else {

			Log.info("No values available in table");
			Log.info("No values available inside the InterfaceInService table");
		}

	
    }
    
    
    public void verifyInterfaceremovedFromServiceforProviderEquipment(String application, String interfacenumber)
			throws InterruptedException, DocumentException {

		try {
		boolean result = driver
				.findElement(By.xpath("(//div[@class='row'][2]//div[div[contains(text(),'"+interfacenumber +"')]])//input"))
				.isDisplayed();
		sa.assertTrue(result, "Verified: Interface got removed from the service");
		sa.assertAll();
		}catch(Exception e) {
			Log.info("No values found inside the table");
		}

	}
    
    
    public void SelectShowInterfacelinkAndVerifyEditInterfacePageforProviderEquipment(String application, String interfacename) throws InterruptedException, DocumentException, IOException {
		
    	try {
    		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/ProviderEquipment_showInterfacelink")));
    		Thread.sleep(3000);
    		     		
    		selectRowForshowInterfaceunderProviderEquipment(application, interfacename);
    		
    	
    		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Equipment_showInterfaceActiondropdown")));
    		Thread.sleep(3000);		
    		
    		
    		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureEditlink")));
    		Thread.sleep(3000);		
    		
    		
    		boolean XNGcircuitID=getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureXNGCircuitID")).isDisplayed();
    		sa.assertTrue(XNGcircuitID, "Circuit id is not displaying");
    		Log.info("circuit id is fetched");
    		
    		Log.info("Entering bearer type dropdown");
    		boolean BearerTypedropdown=getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureBearerType")).isDisplayed();
    		sa.assertTrue(BearerTypedropdown, "Circuit bearer type dropdown is not displaying");
    		Log.info("bearer type dropdown is fetchecd");
    		
    		boolean Bearerspeed=getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureBearerSpeed")).isDisplayed();
    		sa.assertTrue(Bearerspeed, "Circut bearer speed dropdown is not displaying");
    		
    		
    		boolean bandwidth=getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureTotalcircuitbandwidth")).isDisplayed();
    		sa.assertTrue(bandwidth, "Total circuit bandwidth dropdown is not displaying");
    		
    		boolean vlan=getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureVLANid")).isDisplayed();
    		sa.assertTrue(vlan, "VLAN ID field is not displaying");
    		
    		boolean vlantype=getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureVlanType")).isDisplayed();
    		sa.assertTrue(vlantype, "VLANtype dropdown is not displaying");
    		
    		
    		boolean ok=getwebelement(xml.getlocator("//locators/" + application + "/okbutton")).isDisplayed();
    		sa.assertTrue(ok, "Ok Button is not displaying");
    		
    		boolean CANCEL=getwebelement(xml.getlocator("//locators/" + application + "/cancelButton")).isDisplayed();
    		sa.assertTrue(CANCEL, "Cancel Button is not displaying");
    		
    		
    		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/cancelButton")));
    		Thread.sleep(3000);
    		
    		sa.assertAll();
    		
    	}catch(AssertionError e) {
    		e.printStackTrace();
    	}
    		
    	}
    
    
 public void SelectShowInterfacelinkAndVerifyEditInterfacePageforCustomerPremiseEquipment(String application, String interfacename) throws InterruptedException, DocumentException, IOException {
		
    	try {
    		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/customerpremiseEQuipment_showinetrfacelink")));
    		Thread.sleep(3000);
    		     		
    		selectRowForshowInterfaceunderCustomerPremiseEquipment(application, interfacename);
    		
    		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Equipment_showInterfaceActiondropdown")));
    		Thread.sleep(3000);		
    		
    		
    		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureEditlink")));
    		Thread.sleep(3000);		
    		
    		
    		boolean XNGcircuitID=getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureXNGCircuitID")).isDisplayed();
    		sa.assertTrue(XNGcircuitID, "Circuit id is not displaying");
    		Log.info("circuit id is fetched");
    		
    		Log.info("Entering bearer type dropdown");
    		boolean BearerTypedropdown=getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureBearerType")).isDisplayed();
    		sa.assertTrue(BearerTypedropdown, "Circuit bearer type dropdown is not displaying");
    		Log.info("bearer type dropdown is fetchecd");
    		
    		boolean Bearerspeed=getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureBearerSpeed")).isDisplayed();
    		sa.assertTrue(Bearerspeed, "Circut bearer speed dropdown is not displaying");
    		
    		
    		boolean bandwidth=getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureTotalcircuitbandwidth")).isDisplayed();
    		sa.assertTrue(bandwidth, "Total circuit bandwidth dropdown is not displaying");
    		
    		boolean vlan=getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureVLANid")).isDisplayed();
    		sa.assertTrue(vlan, "VLAN ID field is not displaying");
    		
    		boolean vlantype=getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureVlanType")).isDisplayed();
    		sa.assertTrue(vlantype, "VLANtype dropdown is not displaying");
    		
    		
    		boolean ok=getwebelement(xml.getlocator("//locators/" + application + "/okbutton")).isDisplayed();
    		sa.assertTrue(ok, "Ok Button is not displaying");
    		
    		boolean CANCEL=getwebelement(xml.getlocator("//locators/" + application + "/cancelButton")).isDisplayed();
    		sa.assertTrue(CANCEL, "Cancel Button is not displaying");
    		
    		
    		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/cancelButton")));
    		Thread.sleep(3000);
    		
    		sa.assertAll();
    		
    	}catch(AssertionError e) {
    		e.printStackTrace();
    	}
    		
    	}

    
    
public void selectconfigurelinkAndverifyForProviderEquipment(String application, String interfacename) throws InterruptedException, DocumentException, IOException {
		
		try {
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Providerequipment_configurelink")));
		Thread.sleep(3000);
		
		
		boolean GenerateConfigForStaticRoutes=getwebelement(xml.getlocator("//locators/" + application + "/ProviderEquipment_Generateconfigforstaticlink")).isDisplayed();
		sa.assertTrue(GenerateConfigForStaticRoutes, "generate configuration for static routes link is not displayed");
		
		boolean SaveConfigurationlink=getwebelement(xml.getlocator("//locators/" + application + "/providerEquipment_saveconfigurationlink")).isDisplayed();
		sa.assertTrue(SaveConfigurationlink, "Save configuration link is not displayed");
		
		boolean ExecuteconfigOndevice=getwebelement(xml.getlocator("//locators/" + application + "/providerEquipment_ExecuteconfigurationOndevicelink")).isDisplayed();
		sa.assertTrue(ExecuteconfigOndevice, "Execute configuration on device link is not displayed");
		
		sa.assertAll();
		
		}catch(AssertionError e) {
			e.printStackTrace();
		}
		
	}


	public void deleteDeviceFromServiceForProviderequipment(String application, String deleteDevice)
			throws InterruptedException, DocumentException {

		scrolltoend();
		Thread.sleep(2000);
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Providerequipment_deletefromservice")));
		Thread.sleep(3000);

		boolean deletemessage = getwebelement(xml.getlocator("//locators/" + application + "/deleteMessage_equipment"))
				.isDisplayed();
		while (deletemessage) {
			Log.info(
					"Are you sure want to delete ? - message is getting displayed on clicking DeletefromService link");
			Log.info("Delete popup message is getting displayed");
			break;
		}

		if (deleteDevice.equalsIgnoreCase("yes")) {

			Clickon(getwebelement(
					xml.getlocator("//locators/" + application + "/deletebutton_deletefromsrviceforequipment")));
			Thread.sleep(3000);

			Log.info("Device got deleted from service as expected");

		} else {

			Clickon(getwebelement(
					xml.getlocator("//locators/" + application + "/xbuttonfordeletefromservicepopup_Equipment")));
			Thread.sleep(3000);

			Log.info("Device is not deleted from service as expected");

		}

	}
	
	
	public void verifyAddnewlinkunderPE2CPEtable(String application) throws InterruptedException, DocumentException, IOException {
	
		
		String HeaderNameExpected="Add New Link";
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Actionbutton_PE2CPE")));
		Thread.sleep(3000);
		
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addnewlink_Pe2CPE")));
		Thread.sleep(3000);
	
		String headername=Gettext(getwebelement(xml.getlocator("//locators/" + application + "/Headername_PE2CPElink")));
		sa.assertEquals(headername, HeaderNameExpected, "Header name is not displaying as expected");
		
		boolean CircuitId=getwebelement(xml.getlocator("//locators/" + application + "/AddnewlinkPe2CPE_LinkorCircuitId")).isDisplayed();
		sa.assertTrue(CircuitId, "circuit id is not displaying");
		
		
		boolean sourceDevice=getwebelement(xml.getlocator("//locators/" + application + "/AddnewlinkPe2CPE_SourceDevice")).isDisplayed();
		sa.assertTrue(sourceDevice, "Source Device dropdown is not displaying");
		
		
		boolean Sourceinterface=getwebelement(xml.getlocator("//locators/" + application + "/AddnewlinkPe2CPE_SourceInterface")).isDisplayed();
		sa.assertTrue(Sourceinterface, "Source Interface dropdown is not displaying");
		
		boolean targetdevice=getwebelement(xml.getlocator("//locators/" + application + "/AddnewlinkPe2CPE_TargetDevice")).isDisplayed();
		sa.assertTrue(targetdevice, "Target device dropdown is not displaying");
		
		boolean targetInterface=getwebelement(xml.getlocator("//locators/" + application + "/AddnewlinkPe2CPE_TargetInterface")).isDisplayed();
		sa.assertTrue(targetInterface, "Target Interface dropdown is not displaying");
		
		boolean next=getwebelement(xml.getlocator("//locators/" + application + "/AddnewlinkPe2CPE_Nextbutton")).isDisplayed();
		sa.assertTrue(next, "Next button is not displaying");
		
		boolean cancel=getwebelement(xml.getlocator("//locators/" + application + "/cancelButton")).isDisplayed();
		sa.assertTrue(cancel, "CAncel button is not displaying");
		
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/cancelButton")));
		Thread.sleep(3000);
	
		
		
	}

	public void enterdataforAddNewlinkunderPEtoCPEtable(String application, String CircuitId, String sourcedevice, String sourceinterfacce, String targetDevice,String targetInterface, String interfacename) throws InterruptedException, DocumentException, IOException {
		
	
		
		selecttherowforPEtoCPElinktable(application, interfacename);
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addnewlink_Pe2CPE")));

		
        SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddnewlinkPe2CPE_LinkorCircuitId")), CircuitId);
        
        Select(getwebelement(xml.getlocator("//locators/" + application + "/AddnewlinkPe2CPE_SourceDevice")), sourcedevice);
        
        Select(getwebelement(xml.getlocator("//locators/" + application + "/AddnewlinkPe2CPE_SourceInterface")), sourceinterfacce);
        
        Select(getwebelement(xml.getlocator("//locators/" + application + "/AddnewlinkPe2CPE_TargetDevice")), targetDevice);
        
        Select(getwebelement(xml.getlocator("//locators/" + application + "/AddnewlinkPe2CPE_TargetInterface")), targetInterface);
        
        Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddnewlinkPe2CPE_Nextbutton")));
		Thread.sleep(3000);
	

		
	}
	
	
	
	public void enterdataforeditNewlinkunderPEtoCPEtable(String application) {
		
	}

	
	
	 public void selecttherowforPEtoCPElinktable(String Application, String interfacename) throws IOException, InterruptedException, DocumentException {
		 

			int TotalPages;

			String TextKeyword = Gettext(
					getwebelement(xml.getlocator("//locators/" + Application + "/Pe2CPElinktable_totalpage")));

			TotalPages = Integer.parseInt(TextKeyword);

			Log.info("Total number of pages in table is: " + TotalPages);

			ab:

			if (TotalPages != 0) {
				for (int k = 1; k <= TotalPages; k++) {

					// Current page
					String CurrentPage = Gettext(
							getwebelement(xml.getlocator("//locators/" + Application + "/Pe2CPElinktable_currentpage")));
					int Current_page = Integer.parseInt(CurrentPage);

					assertEquals(k, Current_page);

					Log.info("Currently we are in page number: " + Current_page);

					List<WebElement> results = driver.findElements(By.xpath("(//div[@class='ag-div-margin row']//div[div[contains(text(),'"+interfacename +"')]])//input"));

					int numofrows = results.size();
					Log.info("no of results: " + numofrows);
					boolean resultflag;

					if (numofrows == 0) {

						getwebelement(xml.getlocator("//locators/" + Application + "/Pe2CPElinktable_nextpage"));
					}

					else {

						for (int i = 0; i < numofrows; i++) {

							try {

								resultflag = results.get(i).isDisplayed();
								Log.info("status of result: " + resultflag);
								if (resultflag) {
									Log.info(results.get(i).getText());
									results.get(i).click();
									Thread.sleep(3000);
									Clickon(getwebelement(xml.getlocator(
											"//locators/" + Application + "/Actionbutton_PE2CPE")));

									Thread.sleep(5000);
									
									

								}

							} catch (StaleElementReferenceException e) {
								// TODO Auto-generated catch block
								// e.printStackTrace();
								results = driver.findElements(By.xpath("(//div[@class='ag-div-margin row']//div[div[contains(text(),'"+interfacename +"')]])//input"));
								numofrows = results.size();
								// results.get(i).click();
								Log.info("selected row is : " + i);

							}

						}

						break ab;

					}

				}

			} else {

				Log.info("No values available in table");
				Log.info("No values available inside the PEtoCPElink table");
			}
		 
	 }
	 
	 
	 
	 public void selectInterfacelinkforCustomerpremiseequipment(String application) throws InterruptedException, DocumentException {
		 
				Thread.sleep(5000);

				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/cusomerpremiseequipment_SelectInertafeceslink")));
				Log.info("SelectInterface link for Customer premise Equipment is selected");
				Log.info("Select an inertface to add with the service under customer premise equipment");

			}
	 
	 
	 public void AddInterfacetoserviceforcustomerpremiseEquipment(String Application, String interfacenumber) throws IOException, InterruptedException, DocumentException {
		 
 	    	
	    	int TotalPages;

			String TextKeyword = Gettext(
					getwebelement(xml.getlocator("//locators/" + Application + "/providerEquipment_InterfaceToselectTotalpage")));

			TotalPages = Integer.parseInt(TextKeyword);

			Log.info("Total number of pages in Interface to select table is: " + TotalPages);

			ab:

			if (TotalPages != 0) {
				for (int k = 1; k <= TotalPages; k++) {

					// Current page
					String CurrentPage = Gettext(
							getwebelement(xml.getlocator("//locators/" + Application + "/providerEquipment_currentpageInterfaceToselect")));
					int Current_page = Integer.parseInt(CurrentPage);

					assertEquals(k, Current_page);

					Log.info("Currently we are in page number: " + Current_page);

					List<WebElement> results = driver.findElements(By.xpath("(//div[@class='row'][2]//div[div[contains(text(),'"+interfacenumber +"')]])//input"));
					
					int numofrows = results.size();
					Log.info("no of results: " + numofrows);
					boolean resultflag;

					if (numofrows == 0) {

						Clickon(getwebelement(xml.getlocator(
								"//locators/" + Application + "/providerEquipment_InterfaceToselectnextpage")));


					}
					
					else {

						for (int i = 0; i < numofrows; i++) {

							try {

								resultflag = results.get(i).isDisplayed();
								Log.info("status of result: " + resultflag);
								if (resultflag) {
									Log.info(results.get(i).getText());
									results.get(i).click();
									Thread.sleep(3000);
									Clickon(getwebelement(xml.getlocator(
											"//locators/" + Application + "/providerEquipment_InterfaceToselectActiondropdown")));

									Thread.sleep(5000);
									
									Clickon(getwebelement(xml.getlocator("//locators/" + Application + "/providerEquipment_InterfaceToselectAddbutton")));


								}

							} catch (StaleElementReferenceException e) {
								// TODO Auto-generated catch block
								// e.printStackTrace();
								results = driver.findElements(By.xpath("(//div[@class='row'][2]//div[div[contains(text(),'"+interfacenumber +"')]])//input"));
								numofrows = results.size();
								// results.get(i).click();
								Log.info("selected row is : " + i);

							}

						}

						break ab;

					}

				}

			} else {

				Log.info("No values found inside the table");
				Log.info("No values available inside the Interfacetoselect table");
			}

	    
	 }

	 
	 public void pageRefresh() throws InterruptedException {
		 
		 Pagerefresh();
	 }
	 
	 
	 
	 /**
	  * 
	  * 04-Dec
	  * 
	  */
	 
	 
	 
	 public void Edit_DirectFibre10G(String application, String ServiceIdentificationNumber, String SelectSubService,
				String Interfacespeed, String EndpointCPE, String Email, String PhoneContact, String remark,
				String performanceReporting, String ProactiveMonitoring, String deliveryChannel, String ManagementOrder,
				String vpnTopology, String intermediateTechnology, String CircuitReference, String CircuitType, String notificationManagement)
				throws InterruptedException, IOException, DocumentException {

		
		//Service Identification
		 editService_serviceIdentification(application, ServiceIdentificationNumber);
		
	
	//Endpoint CPE	
		 editService_singleEndPointCPE(application, EndpointCPE, vpnTopology);
		 
	//Email	
		 editService_Email(application, Email);
		
	
	//Phone contact	
		 editService_phoneContact(application, PhoneContact);
		
	//Remark	
		 editService_remark(application, remark);
		
	scrolltoend();
	Thread.sleep(2000);
	
	//Performance Reporting	
		 editService_performancereporting_10G(application, performanceReporting);


	//Proactive monitoring
		 editService_proactiveMonitoring(application, ProactiveMonitoring, notificationManagement);		

	//Delivery channel
		 editService_deliveryChannel(application, deliveryChannel);
	
			
	//Management order
		 editService_managementOrder(application, ManagementOrder);	

	//Scroll to bottom
	scrolltoend();
	Thread.sleep(3000);
	
	
	//VPN topology	
			boolean vpnDisplayedValues=getwebelement("//div[contains(text(),'"+ vpnTopology +"')]").isDisplayed();
			if(vpnDisplayedValues) {
				ExtentTestManager.getTest().log(LogStatus.PASS, vpnTopology + " is displaying under 'VPN Topology' as expected");
			
			if(vpnTopology.equals("Point-to-Point")) {
				
			//Intermediate Technologies	
				editService_IntermediateTechnology(application, intermediateTechnology);
			
			//Circuit reference
				editService_circuitreference(application, CircuitReference);
				
			//Circuit Type
				editService_circuitType(application, CircuitType);
				
			}
			
			else if(vpnTopology.equals("Hub&Spoke") || vpnTopology.equals("E-PN (Any-to-Any)")) {

			//Circuit reference
				editService_circuitreference(application, CircuitReference);
				
			}
			
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'VPN Topology' value is not displaying as expected."
					+ "  Expected vlue is: "+ vpnTopology     
					+ "  Actual values displaying is: " + getwebelementscount("//div[div[label[contains(text(),'VPN Topology')]]]//div[@class='customLabelValue form-label']"));
		}

			
		ExtentTestManager.getTest().log(LogStatus.PASS,"Values has been Edited for Direct Fiber subtype under lanlink Service");

		 //click on "Ok button to update
		click_commonMethod(application, "OK", "okbutton", xml);
		Thread.sleep(3000);

	 }
	 
	 
	 
	 public void Edit_DirectFibre1G(String application, String ServiceIdentificationNumber, String SelectSubService,
				String Interfacespeed, String EndpointCPE, String Email, String PhoneContact, String remark,
				String performanceReporting, String ProactiveMonitoring, String deliveryChannel, String ManagementOrder,
				String vpnTopology, String intermediateTechnology, String CircuitReference, String CircuitType, String notificationManagement,
				String perCoSperformanceReport, String actelisBased, String standardCIR, String standardEIR, String premiumCIR, String premiumEIR)
				throws InterruptedException, IOException, DocumentException {

		
	//Service Identification
		 editService_serviceIdentification(application, ServiceIdentificationNumber);
		
	//Endpoint CPE	
		 editService_singleEndPointCPE(application, EndpointCPE, vpnTopology);
		 
		 
	//Email	
		 editService_Email(application, Email);
		
	
	//Phone contact	`
		 editService_phoneContact(application, PhoneContact);
		
	//Remark	
		 editService_remark(application, remark);
		
		
	//Performance Reporting	
	  boolean perfrmReportAvailability=false;
	  try {
		  perfrmReportAvailability=getwebelement(xml.getlocator("//locators/" + application + "/performanceReportingcheckbox")).isDisplayed();
	  
	  if(perfrmReportAvailability) {
		  
		  ExtentTestManager.getTest().log(LogStatus.PASS, " 'Performance reporting' checkbox is displaying in 'Edit Service' page as expected");
		  
		if(!performanceReporting.equalsIgnoreCase("null")) {
			
			boolean perfReport=getwebelement(xml.getlocator("//locators/" + application + "/performanceReportingcheckbox")).isSelected();
			Thread.sleep(2000);
			
			if (performanceReporting.equalsIgnoreCase("yes")) {

				if(perfReport) {
					
					ExtentTestManager.getTest().log(LogStatus.PASS, "Performance Reporting checkbox is not edited and it is already Selected while creating");
					
				}else {
					
					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/performanceReportingcheckbox")));
					Log.info("Performance Reporting check box is selected");
					ExtentTestManager.getTest().log(LogStatus.PASS,"Performance Reporting checkbox is selected");
				}
			}
			else if (performanceReporting.equalsIgnoreCase("no")) {
				
				if(perfReport) {
					
					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/performanceReportingcheckbox")));
					Log.info("Performance Reporting check box is unselected");
					ExtentTestManager.getTest().log(LogStatus.PASS,"Performance Reporting is edited and gets unselected");
					
				}else {
					ExtentTestManager.getTest().log(LogStatus.PASS, "Performance Reporting is not edited and it remains unselected");
				}
				
			}
		}else {
			ExtentTestManager.getTest().log(LogStatus.PASS,"No changes made for Performance Reporting chekbox");
		}
	  }else {
		  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Performance reporting' checkbox is not available in 'Edit Service' page");
	  }

		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " ' Performance Reporting' checkbox is not displaying under 'Edit service' page");
			Log.info(" ' Performance Reporting' checkbox is not displaying under 'Edit service' page");
		}catch(Exception err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to click on 'Performance Reporting' checkbox");
			Log.info(" Not able to click on 'erformance Reporting' checkbox");
		}
		
	//Per CoS Performance Reporting
	if(performanceReporting.equalsIgnoreCase("yes")) {
		boolean perCoSdisplay=false;
		try {
		perCoSdisplay=getwebelement(xml.getlocator("//locators/" + application + "/perCoSperformncereport")).isDisplayed();
		Thread.sleep(3000);
		
		if(perCoSdisplay) {
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Per CoS Performance Reporting' checkbox is displaying in 'Edit Service' page");
		if(!perCoSperformanceReport.equalsIgnoreCase("null")) {
			
			boolean perCoSreport=getwebelement(xml.getlocator("//locators/" + application + "/perCoSperformncereport")).isSelected();
			Thread.sleep(2000);
			
			if (perCoSperformanceReport.equalsIgnoreCase("yes")) {

				if(perCoSreport) {
					
					ExtentTestManager.getTest().log(LogStatus.PASS, "Per CoS Performance Reporting checkbox is not edited and it is already Selected while creating");
					
				}else {
					
					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/perCoSperformncereport")));
					Log.info("Per CoS Performance Reporting check box is selected");
					ExtentTestManager.getTest().log(LogStatus.PASS,"Per CoS Performance Reporting is selected");
				}
			}

			else if (perCoSperformanceReport.equalsIgnoreCase("no")) {
				
				if(perCoSreport) {
					
					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/perCoSperformncereport")));
					Log.info("Per CoS Performance Reporting check box is unselected");
					ExtentTestManager.getTest().log(LogStatus.PASS,"PPer CoS Performance Reporting is edited and gets unselected");
					
				}else {
					ExtentTestManager.getTest().log(LogStatus.PASS, "Per CoS Performance Reporting is not edited and it remains unselected");
				}
				
			}
		}else {
			ExtentTestManager.getTest().log(LogStatus.PASS," 'Per CoS Performance Reporting' chekbox is not edited");
		}
	}else {
		ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Per CoS Performance Reporting' checkbox is not displaying under 'Edit service' page");
	}
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Per CoS Performance Reporting' checkbox is not displaying under 'Edit service' page");
			Log.info(" 'Per CoS Performance Reporting' checkbox is not displaying under 'Edit service' page");
		}catch(Exception err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to click on 'Per CoS Performance Reporting' checkbox");
			Log.info(" Not able to click on 'Per CoS Performance Reporting' checkbox");
		}
	}	
		
		//Actelis Based
		if(perCoSperformanceReport.equalsIgnoreCase("yes")) {
			boolean actelisbased=false;
			if(vpnTopology.equalsIgnoreCase("Point-to-Point")) {
			try {
				actelisbased=getwebelement(xml.getlocator("//locators/" + application + "/ActelisBasedcheckbox")).isDisplayed();
				Thread.sleep(3000);
				if(actelisbased) {
					ExtentTestManager.getTest().log(LogStatus.PASS, " Actelis Based checkbox is dipslaying in 'Edit Serivce' page");
				if(!actelisBased.equalsIgnoreCase("null")) {
					
					
					boolean actelsBased=getwebelement(xml.getlocator("//locators/" + application + "/ActelisBasedcheckbox")).isSelected();
					Thread.sleep(2000);
					
					if (actelisBased.equalsIgnoreCase("yes")) {

						if(actelsBased) {
							
							ExtentTestManager.getTest().log(LogStatus.PASS, "Actelis Based checkbox is not edited and it is already Selected while creating");
							
						}else {
							
							Clickon(getwebelement(xml.getlocator("//locators/" + application + "/ActelisBasedcheckbox")));
							Log.info("Actelis Based check box is selected");
							ExtentTestManager.getTest().log(LogStatus.PASS,"Actelis Based checkbox is selected");
						}
					}

					else if (actelisBased.equalsIgnoreCase("no")) {
						
						if(actelsBased) {
							
							Clickon(getwebelement(xml.getlocator("//locators/" + application + "/ActelisBasedcheckbox")));
							Log.info("Per CoS Performance Reporting check box is unselected");
							ExtentTestManager.getTest().log(LogStatus.PASS,"Actelis Based is edited and gets unselected");
							
						}else {
							ExtentTestManager.getTest().log(LogStatus.PASS, "Actelis Based checkbox is not edited and it remains unselected");
						}
						
					}
				}else {
					ExtentTestManager.getTest().log(LogStatus.PASS,"No changes made for Actelis Based chekbox");
				}
			}
			}catch(NoSuchElementException e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, " Actelis Based checkbox is not dipslaying in 'Edit Serivce' page");
			}catch(Exception err) {
				err.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to click on 'Acteis' checkbox");
				Log.info(" Not able to click on 'Acteis' checkbox");
			}
		  }	else {
				Log.info(" when topology selected as 'Hub&Spoke' or 'E-PN (Any to Any)', 'Actelis' checkbox will not display");
				ExtentTestManager.getTest().log(LogStatus.PASS, " when topology selected as 'Hub&Spoke' or 'E-PN (Any to Any)', 'Actelis' checkbox will not display");
			}
		}		
				
				
	if(actelisBased.equalsIgnoreCase("yes")) {
		
	if(vpnTopology.equalsIgnoreCase("Point-to-Point")) {	
		
		//Standard CIR
			editService_standardCIR(application, standardCIR);			
			
		//Standard EIR
			editService_standardEIR(application, standardEIR);			
			
		//Premium CIR
			editService_premiumCIR(application, premiumCIR);			
	
		//Premium EIR
			editService_premiumEIR(application, premiumEIR);
	}else {
		Log.info(" when topology selected as 'Hub&Spoke' or 'E-PN (Any to Any)', Standard CIR_Eir Premium CIR_EIR fields will not display");
		ExtentTestManager.getTest().log(LogStatus.PASS, " when topology selected as 'Hub&Spoke' or 'E-PN (Any to Any)', Standard CIR_Eir Premium CIR_EIR fields will not display");
	}
}
			
		//Proactive monitoring
			editService_proactiveMonitoring(application, ProactiveMonitoring, notificationManagement);		
		
		//Delivery channel
		  	editService_deliveryChannel(application, deliveryChannel);
	
		//management order
			editService_managementOrder(application, ManagementOrder);

		
	//VPN topology
	scrolltoend();
	Thread.sleep(3000);
	
	boolean vpnDisplayedValues=false;
	
		vpnDisplayedValues=getwebelement("//div[contains(text(),'"+ vpnTopology +"')]").isDisplayed();
	
		if(vpnDisplayedValues) {
			ExtentTestManager.getTest().log(LogStatus.PASS, vpnTopology + " is displaying under 'VPN Topology' as expected");
			if(vpnTopology.equals("Point-to-Point")) {
				
		//Intermediate technologies
		        editService_IntermediateTechnology(application, intermediateTechnology);
			
		//Circuit reference		
				editService_circuitreference(application, CircuitReference);
				
				
		//Circuit type
				editService_circuitType(application, CircuitType);
				
			}
			
			else if(vpnTopology.equals("Hub&Spoke") || vpnTopology.equals("E-PN (Any-to-Any)")) {

				editService_circuitreference(application, CircuitReference);
				Thread.sleep(3000);
			}
			
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'VPN Topology' value is not displaying as expected."
					+ "  Expected vlue is: "+ vpnTopology
					+ "  Actual values displaying is: " + getwebelementscount("//div[div[label[contains(text(),'VPN Topology')]]]//div[@class='customLabelValue form-label']"));
		}
			
	
		Log.info("going to click on OK buttto");
		scrolltoend();
		Thread.sleep(3000);
		
	 //click on "Ok button to update
		click_commonMethod(application, "OK", "okbutton", xml);
		Thread.sleep(3000);
	 
	 }
	 
	 
   public void syncservices(String application) throws InterruptedException, DocumentException {
	   
	   ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying 'Sync Service'");
	   
	   WebElement orderPanel= getwebelement(xml.getlocator("//locators/" + application + "/viewServicepage_OrderPanel"));
		ScrolltoElement(orderPanel);
		Thread.sleep(3000);
	   
	   click_commonMethod(application, "Action", "Editservice_actiondropdown", xml);
	   Thread.sleep(2000);
	   click_commonMethod(application, "Synchronize link", "Editservice_sysnchronizelink", xml);
	   Thread.sleep(3000);
	   
	   scrollToTop();
	   Thread.sleep(3000);
	   
	   verifysuccessmessage(application, "Sync started successfully. Please check the sync status of this service.");
	   
   }
   
   
   	public void shownewInfovista(String application) throws Exception {
	  
   		WebElement orderPanel= getwebelement(xml.getlocator("//locators/" + application + "/viewServicepage_OrderPanel"));
		ScrolltoElement(orderPanel);
		Thread.sleep(3000);
   		
	   click_commonMethod(application, "Action", "Editservice_actiondropdown", xml);
	   Thread.sleep(2000);
	   click_commonMethod(application, "Show infovista link", "Editservice_infovistareport", xml);
	   Thread.sleep(6000);
	   
	   String expectedPageName= "SSO login Page";
	   
	   //Switch to new tab
	   List<String> browserTabs = new ArrayList<String> (driver.getWindowHandles());
	   driver.switchTo().window(browserTabs .get(1));
	   Thread.sleep(10000);

	  try { 
	   // Get Tab name
	   String pageTitle=driver.switchTo().window(browserTabs .get(1)).getTitle();
	   Log.info("page title displays as: "+pageTitle);
	  
	   
	   Thread.sleep(3000);
	   driver.switchTo().window(browserTabs.get(0)); 
	   
	   sa.assertEquals(pageTitle, expectedPageName, " on clicking 'Show Infovista link', it got naviagted to "+pageTitle);
	   
	   sa.assertAll();
	   
	   ExtentTestManager.getTest().log(LogStatus.PASS, "on clicking 'Show Infovista link', it got naviagted to "+ pageTitle + " as expected");
	   Thread.sleep(3000);
	   
	  }catch(AssertionError e) {
		  
		  e.printStackTrace();
		  
		  Thread.sleep(3000);
		  driver.switchTo().window(browserTabs.get(0));
		  
		  ExtentTestManager.getTest().log(LogStatus.FAIL, expectedPageName + " page is not displaying");
		  
	  }
   		
   }
   	
   	
   
	public void manageService(String application, String orderNumber, String SerivceId) throws InterruptedException, DocumentException {
		   
		WebElement orderPanel= getwebelement(xml.getlocator("//locators/" + application + "/viewServicepage_OrderPanel"));
		ScrolltoElement(orderPanel);
		Thread.sleep(3000);
		
		   Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Editservice_actiondropdown")));
		   Thread.sleep(1000);
		   Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Editservice_managelink")));
		   
		   Thread.sleep(2000);
	
		   boolean manageSericePage=false;
		try {   
		   manageSericePage=getwebelement(xml.getlocator("//locators/" + application + "/ManageServicesPage")).isDisplayed();   
	
				if(manageSericePage) {
					ExtentTestManager.getTest().log(LogStatus.PASS, "Manage Service page is displaying");
					Log.info("Manage Service page is displaying");
					
					
			//Verifying Status table
				ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying Column name and value under 'Status' table");
				
				//Verify column names under 'Status' column 
				   checkManageServiceColumnnames_Status(application, orderNumber);
				  
				 //compare Service id Status column  
				   compareText(application, "Service Id", "statusPanel_serviceId", SerivceId, xml);

				//compare Service Type
				   compareText(application, "Service Type", "statusColumn_serviceType" , "LANLink" , xml);
				   
				//Compare Detail
				   compareValue_manageService(application, "Detail", "statusPanel_detailColumn", "", xml);
				   
				//compare Status
				   compareText(application, "Status", "statusPanel_statusColumn", "In Service" , xml);
				   
				//Compare Date
				   ExtentTestManager.getTest().log(LogStatus.PASS, "Last Modification date value is "+ getCurrentDate());
				   
				
			//Verifying Synchronization panel
				   ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying Column name and value under 'Synchronisation' table"); 
				   
			   //Verify column names under 'Synchronisation'  table  
				   checkManageServiceColumnnames_Synchronisation(application, orderNumber);
				   
					
				}else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Manage Services page is not displaying");
					Log.info( "Manage Service page is not displaying");
				}
			
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Manage Services page is not displaying");
			Log.info( "Manage Service page is not displaying");
		}
		
	   }
	
	

	public void checkManageServiceColumnnames_Status(String application, String ordernumber) throws InterruptedException, DocumentException {
		
		String[] statusColumnNamesExpected= {"Service", "Service Type", "Details", "Status", "Last Modification"};
		List<String> ls = new ArrayList<String>();
		
		String expectedvalues="[Service, Service Type, Details, Status, Last Modification]";
		ExtentTestManager.getTest().log(LogStatus.PASS, "Expected column names are: "+ expectedvalues);
		
	
	//Fetches list of common label names	
		List<WebElement> statusColumnLabelnames = getwebelements(xml.getlocator("//locators/" + application + "/status_columnNames"));
		for (WebElement statusColumnName : statusColumnLabelnames) {
			
			if(statusColumnName.getText().startsWith("Order")) {
				
				String[]  actualOrderNumber=statusColumnName.getText().split(":");
				if(ordernumber.equalsIgnoreCase(actualOrderNumber[1])) {
					ExtentTestManager.getTest().log(LogStatus.PASS, "Order Number is displaying as expected. It is displaying as: "+ordernumber);
				}else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Order Number value is mismatching. It is displaying as: "+actualOrderNumber[1] + "Expected value is: "+ ordernumber);
				}
			}
			boolean match = false;
			for (int i = 0; i < statusColumnNamesExpected.length; i++) {
				if (statusColumnName.getText().equals(statusColumnNamesExpected[i])) {
					match = true;
					ls.add(statusColumnName.getText());
				}
			}
		}
		
		ExtentTestManager.getTest().log(LogStatus.PASS, "Actual column names under 'Status' column displaying are: "+ ls);  //printing list of column names displaying
		Log.info("Actual column names under 'Status' column name displaying are: "+ ls);
	
	}
	
	
	
	public void checkManageServiceColumnnames_Synchronisation(String application, String ordernumber) throws InterruptedException, DocumentException {
		
		String[] statusColumnNamesExpected= {"Service", "Service Type", "Details", "Sync Status"};
		List<String> ls = new ArrayList<String>();
		
		String expectedvalues="[Service, Service Type, Details, Sync Status]";
		ExtentTestManager.getTest().log(LogStatus.PASS, "Expected column names are: "+ expectedvalues);
		
	
	//Fetches list of common label names	
		List<WebElement> statusColumnLabelnames = getwebelements(xml.getlocator("//locators/" + application + "/status_columnNames"));
		for (WebElement statusColumnName : statusColumnLabelnames) {

			if(statusColumnName.getText().startsWith("Order")) {
				
				String[]  actualOrderNumber=statusColumnName.getText().split(":");
				if(ordernumber.equalsIgnoreCase(actualOrderNumber[1])) {
					ExtentTestManager.getTest().log(LogStatus.PASS, "Order Number is displaying as expected. It is displaying as: "+ordernumber);
				}else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Order Number value is mismatching. It is displaying as: "+actualOrderNumber[1] + "Expected value is: "+ ordernumber);
				}
			}

			boolean match = false;
			for (int i = 0; i < statusColumnNamesExpected.length; i++) {
				if (statusColumnName.getText().equals(statusColumnNamesExpected[i])) {
					match = true;
					ls.add(statusColumnName.getText());
				}
			}
		}
		
		ExtentTestManager.getTest().log(LogStatus.PASS, "Actual column names under 'Synchronisation' column displaying are: "+ ls);  //printing list of column names displaying
		Log.info("Actual column names under 'Synchronisation' column name displaying are: "+ ls);
	
	}
	
	
	

	public void manageSubnets(String application) throws InterruptedException, DocumentException {
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying 'Manage Subnets'");
		
		 WebElement orderPanel= getwebelement(xml.getlocator("//locators/" + application + "/viewServicepage_OrderPanel"));
			ScrolltoElement(orderPanel);
			Thread.sleep(3000);
		   
		   Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Editservice_actiondropdown")));
		   Thread.sleep(3000);
		   click_commonMethod(application, "Manage Subnet", "Editservice_managesubnets", xml);
		   Thread.sleep(2000);
		   
		   boolean manageSubnetPage=false;
		  try { 
		   manageSubnetPage=getwebelement(xml.getlocator("//locators/" + application + "/manageSubnetPage_header")).isDisplayed();
		   if(manageSubnetPage) {
			   ExtentTestManager.getTest().log(LogStatus.PASS, "'Manage Subnet' page is displaying");
			   Log.info("'Manage Subnet' page is displaying");
			   
			   String errMsg=getwebelement(xml.getlocator("//locators/" + application + "/manageSubnet_errMsg")).getText();
			   if(errMsg.isEmpty()) {
				   
				   ExtentTestManager.getTest().log(LogStatus.FAIL, "No messages displays under 'manage Subnet' page");
				   Log.info("No messages displays under 'manage Subnet' page");
			   }else {
				   ExtentTestManager.getTest().log(LogStatus.PASS, "Message in 'Manage Subnet' page displays as "+errMsg);
				   Log.info(" Message in 'Manage Subnet' page displays as "+errMsg);
			   }
			   
		   }else {
			   ExtentTestManager.getTest().log(LogStatus.FAIL, "'Manage Subnet' page is not displaying");
			   Log.info("'Manage Subnet' page is not displaying");
		   }
		  }catch(Exception e) {
			  e.printStackTrace();
			  ExtentTestManager.getTest().log(LogStatus.FAIL, "'Manage Subnet' page is not displaying");
			  Log.info("'Manage Subnet' page is not displaying");
		  }
		   
		  click_commonMethod(application, "Cancel", "cancelButton", xml); 
		  Thread.sleep(1000);
	   }
	
	public void verifyAddcpedevicepageforIntermediatEquipmetn(String application) throws InterruptedException, DocumentException {
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying fields for CPE device under Intermediate Equipment");
		
		Clickon(getwebelement(
				xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_adddevicelink")));
		Thread.sleep(3000);

		Thread.sleep(3000);
		try {
		
		String[] Vender= {"AC", "DC"};
		
		String[] powerAlarm= {"AC", "DC"};
		
		String[] Mediaselection= {"Type A","Type B","Type C"};	
		
		
		click_commonMethod(application, "OK", "obutton_spanTag", xml);
		Thread.sleep(3000);
			
		//Snmpro Error Message
			boolean snmproErr = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEdevice_snmproerrmsg")).isDisplayed();
			sa.assertTrue(snmproErr, "Snmpro warning message is not displayed ");
			String snmproErrMsg = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEdevice_snmproerrmsg")).getText();
			Log.info(
					"Snmpro  message displayed as : " + snmproErrMsg);
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" validation message for Snmpro field displayed as : " + snmproErrMsg);
			Log.info("Snmpro warning message displayed as : " + snmproErrMsg);
			
			
		//Management Address Error Message
			boolean mangadrsErr = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEdevice_managementAddresserrmsg")).isDisplayed();
			sa.assertTrue(mangadrsErr, "Management Addres warning message is not displayed ");
			String mngadresErrMsg = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEdevice_managementAddresserrmsg")).getText();
			Log.info(
					"Management Addres  message displayed as : " + mngadresErrMsg);
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" validation message for Management Addres field displayed as : " + mngadresErrMsg);
			Log.info("Management Addres warning message displayed as : " + mngadresErrMsg);
			
			
		//Power Alarm Error Message
			boolean pwralrmErr = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEdevice_powerAlarmerrmsg")).isDisplayed();
			sa.assertTrue(pwralrmErr, "Power Alarm warning message is not displayed ");
			String pwralarmErrMsg = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEdevice_powerAlarmerrmsg")).getText();
			Log.info(
					"Power Alarm  message displayed as : " + pwralarmErrMsg);
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" validation message for Power Alarm field displayed as : " + pwralarmErrMsg);
			Log.info("Power Alarm warning message displayed as : " + pwralarmErrMsg);
			
			
		//Media Selection Error Message
			boolean mediaErr = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEdevice_mediaselectionerrmsg")).isDisplayed();
			sa.assertTrue(mediaErr, "Media Selection warning message is not displayed ");
			String mediaselectionErrMsg = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEdevice_mediaselectionerrmsg")).getText();
			Log.info(
					"Media Selection  message displayed as : " + mediaselectionErrMsg);
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" validation message for Media Selection field displayed as : " + mediaselectionErrMsg);
			Log.info("Media Selection warning message displayed as : " + mediaselectionErrMsg);
			
			
	//MAC Address Error Message
			boolean macErr = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEdevice_macAdressErrmsg")).isDisplayed();
			sa.assertTrue(macErr, "MAC Address warning message is not displayed ");
			String macadresErrMsg = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEdevice_macAdressErrmsg")).getText();
			Log.info(
					"MAC Address  message displayed as : " + macadresErrMsg);
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" validation message for MAC Address field displayed as : " + macadresErrMsg);
			Log.info("MAC Address warning message displayed as : " + macadresErrMsg);
			
			
		//Country Error Message
			boolean countryErr = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEdevice_macAdressErrmsg")).isDisplayed();
			sa.assertTrue(countryErr, "MAC Address warning message is not displayed ");
			String countryErrMsg = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEdevice_macAdressErrmsg")).getText();
			Log.info(
					"Country  message displayed as : " + countryErrMsg);
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" validation message for Country field displayed as : " + countryErrMsg);
			Log.info("Country warning message displayed as : " + countryErrMsg);	
		
		
		boolean name=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_Name")).isDisplayed();
		sa.assertTrue(name, "name field is not available under create device for Equipment");
		
		boolean vender=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_vender")).isDisplayed();
		sa.assertTrue(vender, "Vender/Model dropdown is not available");
			
	
	  try {
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_vender")));
	  }catch (Exception e) {
		  e.printStackTrace();
		  ExtentTestManager.getTest().log(LogStatus.FAIL, "AddCPE device not available");  
	}
			
	  try {
	  List<WebElement> listofvender = getwebelements(xml.getlocator("//locators/" + application + "/ClassNameForDropdowns"));
			
			if(listofvender.size()>0) {
	
			for (WebElement vendertypes : listofvender) {

				boolean match = false;
				for (int i = 0; i < Vender.length; i++) {
					if (vendertypes.getText().equals(Vender[i])) {
						match = true;
						Log.info("list of vendor under add devices are : " + vendertypes.getText());
						ExtentTestManager.getTest().log(LogStatus.PASS,"The list of vender/Model under Add device are: "+vendertypes.getText());
						Log.info("list of vendor under add devices are : " + vendertypes.getText());
						

					}
					}
				sa.assertTrue(match);
				}
				
			}else {
				Log.info("dropdown value inside Vender/Model is empty");
				ExtentTestManager.getTest().log(LogStatus.FAIL, "No values available inside Vender/Model dropdown for adding devices");
			}
			
	  }catch(Exception e) {
		  
		  e.printStackTrace();
		  ExtentTestManager.getTest().log(LogStatus.FAIL, "Failure at vendor dropdown");
		  
	  }
      
			
			boolean snmpro=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_snmpro")).isDisplayed();
			sa.assertTrue(snmpro, "Snmpro field under add device is not available");
			
			boolean managementaddres=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_manageaddress")).isDisplayed();
			sa.assertTrue(managementaddres, "Management Address field under add device is not available");
			
			boolean mepid=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_mepid")).isDisplayed();
			sa.assertTrue(mepid, "Mepid field under add device is not available");
			
			boolean powralrm=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_poweralarm")).isDisplayed();
			sa.assertTrue(powralrm, "The poweralarm dropdown under add device is not available");
			
			
		try {	
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_poweralarm")));
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Poweralarm dropdown field is not available");
		}
			
		try {
		List<WebElement> listofalarm = getwebelements(xml.getlocator("//locators/" + application + "/ClassNameForDropdowns"));

		if(listofalarm.size()>0) {	
			for (WebElement alarmtypes : listofalarm) {

				boolean match = false;
				for (int i = 0; i < powerAlarm.length; i++) {
					if (alarmtypes.getText().equals(powerAlarm[i])) {
						match = true;
						Log.info("list of power alarm under add devices are : " + alarmtypes.getText());
						ExtentTestManager.getTest().log(LogStatus.PASS,"The list of powerAlarm under Add device are: "+alarmtypes.getText());

					}
					}
				 sa.assertTrue(match);
				}
			   
			}else {
				Log.info("dropdown value inside Vender/Model is empty");
				ExtentTestManager.getTest().log(LogStatus.FAIL, "No values available inside power alarm dropdown for adding devices");
			}
		}catch(Exception e) {
			  
			  e.printStackTrace();
			  ExtentTestManager.getTest().log(LogStatus.FAIL, "value mismatch for poweralarm dropdown");
			  
		  }
			
			boolean media=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_mediaselection")).isDisplayed();
			sa.assertTrue(media, "Media selection dropdwon under add devices is not available");
			
		try {
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_mediaselection")));
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Mediaselection dropdown is not available");
		}
		try {	
		List<WebElement> listofmedia = getwebelements(xml.getlocator("//locators/" + application + "/ClassNameForDropdowns"));

		if(listofmedia.size()>0) {
			for (WebElement mediatypes : listofmedia) {

				boolean match = false;
				for (int i = 0; i < Mediaselection.length; i++) {
					if (mediatypes.getText().equals(Mediaselection[i])) {
						match = true;
						Log.info("list of Media selection under add devices are : " + mediatypes.getText());
						ExtentTestManager.getTest().log(LogStatus.PASS,"The list of media selection under Add device are: "+mediatypes.getText());
					}
					}
				sa.assertTrue(match);
				}
				
			}else {
				Log.info("dropdown value inside Vender/Model is empty");
				ExtentTestManager.getTest().log(LogStatus.FAIL, "No values available inside Media selection dropdown for adding devices");
			}
		}catch(Exception e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, "FAilure at Media selection dropdown");
			}
			
			
			boolean masAddrss=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_macaddress")).isDisplayed();
			sa.assertTrue(masAddrss, "MAC Address field under add device is not available");
			
//		try {
//			boolean serial=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_serialnumber")).isDisplayed();
//			sa.assertTrue(serial, "Serial number field is not available");
//			sa.assertAll();
//			ExtentTestManager.getTest().log(LogStatus.PASS,"serial number field is available under create device for Equipment" );
//		}catch(AssertionError e) {
//			e.printStackTrace();
//			ExtentTestManager.getTest().log(LogStatus.FAIL,"serial number field is not available under create device for Equipment" );
//		}
			
//		    try {
//			boolean lanlink=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_linklostforowarding")).isDisplayed();
//			sa.assertTrue(lanlink, "Lanlink forwarding chckbox under add device is not available");
//			sa.assertAll();
//			ExtentTestManager.getTest().log(LogStatus.PASS,"link lost forwarding checkbox is available under create device for Equipment" );
//			}catch(AssertionError e) {
//				e.printStackTrace();
//				ExtentTestManager.getTest().log(LogStatus.FAIL,"link lost forwarding checkbox is not available under create device for Equipment" );
//			}
			
			
		//Country dropdown
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_countrydiv")));
			Thread.sleep(5000);
			Log.info("Clicked on Country dropdown");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on Country dropdown");
			Log.info("Clicked on Country dropdown");

			List<WebElement> cntrylist = getwebelements(xml.getlocator("//locators/" + application + "/ClassNameForDropdowns"));
			for (WebElement countrylist : cntrylist) {

				Log.info("Available Country name is : " + countrylist.getText().toString());
				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Available Country name is : " + countrylist.getText().toString());
				Log.info("Available Country name is :" + countrylist.getText().toString());
			}

		//City dropdown
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip__citydiv")));
			Thread.sleep(5000);
			Log.info("Clicked on City dropdown");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on City dropdown");
			Log.info("Clicked on City dropdown");

			List<WebElement> citylist = getwebelements(xml.getlocator("//locators/" + application + "/ClassNameForDropdowns"));
			for (WebElement ctylist : citylist) {

				Log.info("Available City name is : " + ctylist.getText().toString());
				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Available City name is : " + ctylist.getText().toString());
				Log.info("Available City name is :" + ctylist.getText().toString());
			}
			
		//Select City toggle button
			try {
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_citytogglebutton")));
			ExtentTestManager.getTest().log(LogStatus.PASS, "cliked on 'Select City' toggle button");
			}catch(Exception e) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Select City' toggle button is not available");
			}
			
		//City name
			boolean cityname=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_citynamefield")).isDisplayed();
			sa.assertTrue(cityname, " 'City name' field is not getting displyed");
			
		//City Code
			boolean citycode=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_citycodefield")).isDisplayed();
			sa.assertTrue(citycode, " 'City Code' field is not getting displyed");
				
				
		//Site name
			boolean sitename=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_sitenamefield")).isDisplayed();
			sa.assertTrue(sitename, " 'Site name' field is not getting displyed");
					
		//Site Code
			boolean sitecode=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_sitecodefield")).isDisplayed();
			sa.assertTrue(sitecode, " 'Site Code' field is not getting displyed");
						
						
		//Premise name
			boolean premisename=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_premisenamefield")).isDisplayed();
			sa.assertTrue(premisename, " 'Premise name' field is not getting displyed");
							
		//Premise Code
			boolean premisecode=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_premisecodefield")).isDisplayed();
			sa.assertTrue(premisecode, " 'Premise Code' field is not getting displyed");
								
		//Select City toggle button to get site dropdown and premise dropdown
				try {
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_disabledCityToggleButton")));
//				ExtentTestManager.getTest().log(LogStatus.PASS, "cliked on 'Select City' toggle button");
				}catch(Exception e) {
					ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Select City' toggle button is not available");
				}	
			
		//Site dropdown
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_sitediv")));
				Thread.sleep(5000);
				Log.info("Clicked on Site dropdown");
				ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on Site dropdown");
				Log.info("Clicked on Site dropdown");

				List<WebElement> stelist = getwebelements(xml.getlocator("//locators/" + application + "/ClassNameForDropdowns"));
				for (WebElement sitelist : stelist) {

					Log.info("Available site name is : " + sitelist.getText().toString());
					ExtentTestManager.getTest().log(LogStatus.PASS,
							"Available Site name is : " + sitelist.getText().toString());
					Log.info("Available Site name is :" + sitelist.getText().toString());
				}
				
				
				
		//Premise dropdown
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_premisediv")));
				Thread.sleep(5000);
				Log.info("Clicked on Premise dropdown");
				ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on Premise dropdown");
				Log.info("Clicked on Premise dropdown");

				List<WebElement> prmlist = getwebelements(xml.getlocator("//locators/" + application + "/ClassNameForDropdowns"));
					for (WebElement premiselist : prmlist) {

					Log.info("Available Premise name is : " + premiselist.getText().toString());
					ExtentTestManager.getTest().log(LogStatus.PASS,
							"Available Premise name is : " + premiselist.getText().toString());
					Log.info("Available Premise name is :" + premiselist.getText().toString());
				}		
			
				
			boolean Ok=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_OKbutton")).isDisplayed();
			sa.assertTrue(Ok, "OK button under add device is not available");
			
			boolean cancel=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_cancelbutton")).isDisplayed();
			sa.assertTrue(cancel, "cancel button under add device is not available");
	
			
			
			
		
		sa.assertAll();
		
		ExtentTestManager.getTest().log(LogStatus.PASS, "Fields successfully verified for Add site order");
		}catch(AssertionError e) {
			
			e.printStackTrace();
			//ExtentTestManager.getTest().log(LogStatus.FAIL, "FAiled while verify the fields for Add CPE device");
			
		}
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_cancelbutton")));
			 
		
	}
	
	public void existingCity(String application, String city) throws InterruptedException, DocumentException, IOException {
		
		selectValueInsideDropdown(application, "cityDropdown_selectTag", "City", city, xml);
		
	}
	
	
	public void newcity(String application, String newcityselectionmode, String cityname, String citycode) throws InterruptedException, IOException, DocumentException {
		
		//New City	
				if(newcityselectionmode.equalsIgnoreCase("yes")) {
					
					
					if(cityname.equalsIgnoreCase("null")) {
						ExtentTestManager.getTest().log(LogStatus.PASS, " value for City name field  is not added");
					}else {
						//City Name Field	
						SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_citynamefield")), cityname);
						Thread.sleep(5000);
						String citynme=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_citynamefield")).getAttribute("value");
						Thread.sleep(3000);
						Log.info("Entered City Name is : " + citynme);
						ExtentTestManager.getTest().log(LogStatus.PASS,
								"Entered City Name is : " + citynme);
					}
					
					
					if(citycode.equalsIgnoreCase("null")) {
						ExtentTestManager.getTest().log(LogStatus.PASS, " value for City Code field  is not added");
					}else {
						
						//City Code Field	
							SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_citycodefield")), citycode);
							Thread.sleep(5000);
							String citycde=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_citycodefield")).getAttribute("value");
							Thread.sleep(3000);
							Log.info("Entered City Code is : " + citycde);
							ExtentTestManager.getTest().log(LogStatus.PASS,
									"Entered City Code is : " + citycde);
							
					}	
					
				}
				else{
					ExtentTestManager.getTest().log(LogStatus.PASS, " Add new city is not selected");
				}
				
	}
	
	public void addCityToggleButton(String application) throws InterruptedException, DocumentException {
		
		//Add city Toggle button
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/addcityswitch")));
		Thread.sleep(5000);
	}

	
	public void existingSite(String application, String site) throws InterruptedException, DocumentException, IOException {
		
		selectValueInsideDropdown(application, "siteDropdown_selectTag", "Site", site, xml);
		
	}
	
	
	public void newSite(String application, String newsiteselectionmode, String sitename, String sitecode) throws InterruptedException, IOException, DocumentException {
		
		//New site 
		 if(newsiteselectionmode.equalsIgnoreCase("yes")) {
			 
			if(sitename.equalsIgnoreCase("null")) {
				ExtentTestManager.getTest().log(LogStatus.PASS, " value for Site name field  is not entered");
			}else {
				//Site Name Field	
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_sitenamefield")), sitename);
				Thread.sleep(5000);
				String sitenme=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_sitenamefield")).getAttribute("value");
				Thread.sleep(3000);
				Log.info("Entered Site Name is : " + sitenme);
				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Entered Site Name is : " + sitenme);
			}
			
			if(sitecode.equalsIgnoreCase("null")) {
				ExtentTestManager.getTest().log(LogStatus.PASS, " value for Site code field  is not entered");
			}else {
				
				//Site Code Field	
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_sitecodefield")), sitecode);
				Thread.sleep(5000);
				String sitecde=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_sitecodefield")).getAttribute("value");
				Thread.sleep(3000);
				Log.info("Entered Site Code is : " + sitecde);
				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Entered Site Code is : " + sitecde);
				
				
			}	
			 
		 }else {
			 ExtentTestManager.getTest().log(LogStatus.PASS, " Add new city is not selected");
		 }
	}
	
	
public void newSite_ClickOnSiteTogglebutton(String application, String newsiteselectionmode, String sitename, String sitecode) throws InterruptedException, IOException, DocumentException {
		
		//New site 
		 if(newsiteselectionmode.equalsIgnoreCase("yes")) {
			 
			if(sitename.equalsIgnoreCase("null")) {
				ExtentTestManager.getTest().log(LogStatus.PASS, " value for Site name field  is not entered");
			}else {
				Thread.sleep(5000);
				//Site Name Field	
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_sitenamefield_sitetogglebutton")), sitename);
				Thread.sleep(5000);
				String sitenme=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_sitenamefield_sitetogglebutton")).getAttribute("value");
				Thread.sleep(3000);
				Log.info("Entered Site Name is : " + sitenme);
				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Entered Site Name is : " + sitenme);
			}
			
			if(sitecode.equalsIgnoreCase("null")) {
				ExtentTestManager.getTest().log(LogStatus.PASS, " value for Site code field  is not entered");
			}else {
				
				//Site Code Field	
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_sitecodefield_sitetogglebutton")), sitecode);
				Thread.sleep(5000);
				String sitecde=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_sitecodefield_sitetogglebutton")).getAttribute("value");
				Thread.sleep(3000);
				Log.info("Entered Site Code is : " + sitecde);
				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Entered Site Code is : " + sitecde);
				
				
			}	
			 
		 }else {
			 ExtentTestManager.getTest().log(LogStatus.PASS, " Add new city is not selected");
		 }
	}
	
	public void addSiteToggleButton(String application) throws InterruptedException, DocumentException {
		
		scrolltoend();
		Thread.sleep(1000);
		
		//Add Site Toggle button
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/addsiteswitch")));
		Thread.sleep(2000);
	}
	
	
	public void existingPremise(String application,  String premise) throws InterruptedException, DocumentException, IOException {
		
		selectValueInsideDropdown(application, "premiseDropdown_selectTag", "Premise", premise, xml);
		
	}
	
	
	public void newPremise(String application, String newpremiseselectionmode, String premisename, String premisecode) throws InterruptedException, IOException, DocumentException {
		
		//New premise 
		 if(newpremiseselectionmode.equalsIgnoreCase("yes")) {
			 
			if(premisename.equalsIgnoreCase("null")) {
				ExtentTestManager.getTest().log(LogStatus.PASS, " value for Premise Name field  is not entered");
			}else {
				//Premise Name Field	
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_premisenamefield")), premisename);
				Thread.sleep(5000);
				String prmsenme=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_premisenamefield")).getAttribute("value");
				Thread.sleep(3000);
				Log.info("Entered Premise Name is : " + prmsenme);
				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Entered Premise Name is : " + prmsenme);
			}
			
			if(premisecode.equalsIgnoreCase("null")) {
				ExtentTestManager.getTest().log(LogStatus.PASS, " value for Premise code field  is not entered");
			}else {
				//Premise Code Field	
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_premisecodefield")), premisecode);
				Thread.sleep(5000);
				String premisecde=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_premisecodefield")).getAttribute("value");
				Thread.sleep(3000);
				Log.info("Entered Premise Code is : " + premisecde);
				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Entered Premise Code is : " + premisecde);
			}	
			 
			 
		 }else {
			 ExtentTestManager.getTest().log(LogStatus.PASS, " Add new Premise is not selected");
		 }
		 
	}
	
	
public void newPremise_clickonSiteToggleButton(String application, String newpremiseselectionmode, String premisename, String premisecode) throws InterruptedException, IOException, DocumentException {
		
		//New premise 
		 if(newpremiseselectionmode.equalsIgnoreCase("yes")) {
			 
			if(premisename.equalsIgnoreCase("null")) {
				ExtentTestManager.getTest().log(LogStatus.PASS, " value for Premise Name field  is not entered");
			}else {
				//Premise Name Field	
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_premisenamefield_sitetogglebutton")), premisename);
				Thread.sleep(5000);
				String prmsenme=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_premisenamefield_sitetogglebutton")).getAttribute("value");
				Thread.sleep(3000);
				Log.info("Entered Premise Name is : " + prmsenme);
				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Entered Premise Name is : " + prmsenme);
			}
			
			if(premisecode.equalsIgnoreCase("null")) {
				ExtentTestManager.getTest().log(LogStatus.PASS, " value for Premise code field  is not entered");
			}else {
				//Premise Code Field	
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_premisecodefield_sitetogglebutton")), premisecode);
				Thread.sleep(5000);
				String premisecde=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_premisecodefield_sitetogglebutton")).getAttribute("value");
				Thread.sleep(3000);
				Log.info("Entered Premise Code is : " + premisecde);
				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Entered Premise Code is : " + premisecde);
			}	
			 
			 
		 }else {
			 ExtentTestManager.getTest().log(LogStatus.PASS, " Existing Premise value cannot be selected, If new Site is added");
			 Log.info(" Existing Premise value cannot be selected, If new Site is added");
		 }
	}
	

public void newPremise_clickOnPremisetoggleButton(String application, String newpremiseselectionmode, String premisename, String premisecode) throws InterruptedException, IOException, DocumentException {
	
	//New premise 
	 if(newpremiseselectionmode.equalsIgnoreCase("yes")) {
		 
		if(premisename.equalsIgnoreCase("null")) {
			ExtentTestManager.getTest().log(LogStatus.PASS, " value for Premise Name field  is not entered");
		}else {
			//Premise Name Field	
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_premisenamefield_premisetogglebutton")), premisename);
			Thread.sleep(5000);
			String prmsenme=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_premisenamefield_premisetogglebutton")).getAttribute("value");
			Thread.sleep(3000);
			Log.info("Entered Premise Name is : " + prmsenme);
			ExtentTestManager.getTest().log(LogStatus.PASS,
					"Entered Premise Name is : " + prmsenme);
		}
		
		if(premisecode.equalsIgnoreCase("null")) {
			ExtentTestManager.getTest().log(LogStatus.PASS, " value for Premise code field  is not entered");
		}else {
			//Premise Code Field	
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_premisecodefield_premisetogglebutton")), premisecode);
			Thread.sleep(5000);
			String premisecde=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_premisecodefield_premisetogglebutton")).getAttribute("value");
			Thread.sleep(3000);
			Log.info("Entered Premise Code is : " + premisecde);
			ExtentTestManager.getTest().log(LogStatus.PASS,
					"Entered Premise Code is : " + premisecde);
		}	
		 
		 
	 }else {
		 ExtentTestManager.getTest().log(LogStatus.PASS, " Add new Premise is not selected");
	 }
	 
}

	public void addPremiseTogglebutton(String application) throws InterruptedException, DocumentException {
		
		//Add Premise Toggle button
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/addpremiseswitch")));
		Thread.sleep(5000);
	}

	
	
public void addOverture(String application, String serviceName) throws InterruptedException, DocumentException, IOException {
	
	//Click on Action dropdown
	Clickon(getwebelement(xml.getlocator("//locators/" + application + "/circuit_10GigeActiondropdown")));
	Thread.sleep(3000);
	
	//Click on Add Overture Link
	Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Circuit_10GigEaddOverturelink")));
	Thread.sleep(3000);
	
	
	//Click on Next buttton to find mandatory message
	click_commonMethod(application, "OK", "okbutton", xml);
	Thread.sleep(3000);
	
	
//Service name warning message
	boolean nameerr = getwebelement(
			xml.getlocator("//locators/" + application + "/Overture_servicenameErrmsg")).isDisplayed();
	sa.assertTrue(nameerr, "Service name mandatory warning under 'Overture Circuit'page is not displayed ");
	String nameErrorwarning = getwebelement(
			xml.getlocator("//locators/" + application + "/Overture_servicenameErrmsg")).getText();
	Log.info(
			"Name validation message displayed as : " + nameErrorwarning);
	ExtentTestManager.getTest().log(LogStatus.PASS,
			"Service Name validation message displayed as : " + nameErrorwarning);
	Log.info("Name validation message displayed as : " + nameErrorwarning);
	
	
//service name fields
	boolean nameField = getwebelement(
			xml.getlocator("//locators/" + application + "/Overture_ServiceNameField")).isDisplayed();
	
	if(nameField) {
		Log.info("Service Name is displaying as expected under Overture page");
		ExtentTestManager.getTest().log(LogStatus.PASS, " 'Service Name' is field is displaying ");
		
		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Overture_ServiceNameField")), serviceName);
		Thread.sleep(3000);
		WebElement name=getwebelement(xml.getlocator("//locators/" + application + "/Overture_ServiceNameField"));
		
		ExtentTestManager.getTest().log(LogStatus.PASS, "The value entered under Service name Field is: "+name.getAttribute("value"));
	}else {
		
		Log.info("Service Name is not displaying under Overture page");
		ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Service Name' is field is not displaying ");
		
	}
	
//click on Search button
	Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Overture_searchButton")));
	
	
}


public void City_AddSiteorder(String application, String existingcityselection, String city, String newcityselection,  String xngcityname, String xngcitycode,
		String sitevalue, String CSR_Name, String existingsiteselection, String newsiteselection) throws InterruptedException, 
DocumentException, IOException {
	
	//Existing City
			if((existingcityselection.equalsIgnoreCase("yes")) & (newcityselection.equalsIgnoreCase("no"))) {

//				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_disabledCitytogglebutton")));
//				 Thread.sleep(5000);
				 
				addDropdownValues_commonMethod(application, "Device Xng City", "Addsiteorder_City", city, xml);
				
				ExtentTestManager.getTest().log(LogStatus.PASS, city+ " is selected under Device Xng City dropdown");
				
			//Existing Site	
				if((existingsiteselection.equalsIgnoreCase("yes")) & (newsiteselection.equalsIgnoreCase("no"))) {
	    			
					click_commonMethod(application, "Select Site toggle", "Addsiteorder_Sitetogglebutton", xml);
					Thread.sleep(3000);
					
					addDropdownValues_forSpantag(application, "Physical Site", "Addsiteorder_sitedropdown", sitevalue, xml);
	    		}
				
			//New site
				if((existingsiteselection.equalsIgnoreCase("no")) & (newsiteselection.equalsIgnoreCase("yes"))) {
					
//					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddsiteOrdr_disabledSitetogglebutton")));
//					Thread.sleep(3000);
					
					if(CSR_Name.equalsIgnoreCase("null")){
						ExtentTestManager.getTest().log(LogStatus.FAIL, "CSR name field is mandatory and no values are provided");
						Log.info("No values provided for mandatory field 'CSR Name'");
						
					}else {
					
						addtextFields_commonMethod(application, "Physical Site: CSR Name", "Addsiteorder_CSRname", CSR_Name, xml);
					}
				}
				
			}
			else if((existingcityselection.equalsIgnoreCase("no")) & (newcityselection.equalsIgnoreCase("yes"))) {
				
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Citytogglebutton")));
				Thread.sleep(3000);
				
				//City name
				 if(xngcityname.equalsIgnoreCase("null")) {
					 ExtentTestManager.getTest().log(LogStatus.FAIL, "City name field is a mandatory field and the value is not provided");
				 }else {
				 SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_xngcityname")), xngcityname);
				 Thread.sleep(3000);
				 ExtentTestManager.getTest().log(LogStatus.PASS, xngcityname+ " is entered in City name field");
				 Log.info(xngcityname+ " is entered in City name field");
				 Thread.sleep(3000);
				 }
				 
				 //City code
				 if(xngcitycode.equalsIgnoreCase("null")) {
					 ExtentTestManager.getTest().log(LogStatus.FAIL, "City Code field is a mandatory field and the value is not provided");
					 Log.info("no values provided for city code text field");
				 }else {
				 SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_XNGcitycode")), xngcitycode);
				 Thread.sleep(3000);
				 ExtentTestManager.getTest().log(LogStatus.PASS, xngcitycode+" is entered in City Code field" );
				 }
				 Thread.sleep(3000);
				 
			//Add New Site	 
				 
//				try {
//				 Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddsiteOrdr_disabledSitetogglebutton")));
//					Thread.sleep(3000);
//				}catch(Exception e) {
//					e.printStackTrace();
//					ExtentTestManager.getTest().log(LogStatus.FAIL, " Site disabled toggle button not available");
//				}
				
				try {
					
					if(CSR_Name.equalsIgnoreCase("null")){
						ExtentTestManager.getTest().log(LogStatus.FAIL, "CSR name field is mandatory and no values are provided");
						Log.info(" no values provided for 'CSR Name' text field");
						
					}else {
						
					SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_CSRname")), CSR_Name);
					Thread.sleep(3000);
					ExtentTestManager.getTest().log(LogStatus.PASS, CSR_Name+ " is entered under CSR Name field");
					Log.info(CSR_Name+ " is entered under CSR Name field");
					}
					
				}catch(Exception e) {
					e.printStackTrace();
					ExtentTestManager.getTest().log(LogStatus.FAIL, " CSR NAme not performed");
				}
			
			}
			
}


public void validateCity_AddSiteOrder(String application) throws InterruptedException, DocumentException {
	
	
	// City dropdown
	boolean CIty = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_City")).isDisplayed();
	sa.assertTrue(CIty, "City dropdown is not displayed");
	if(CIty) {
		ExtentTestManager.getTest().log(LogStatus.PASS, " 'City' mandatory dropdown is displaying under 'Add Site Order' page as expected");
	
}else {
ExtentTestManager.getTest().log(LogStatus.FAIL, " 'City' mandatory dropdown is not available under 'Add Site Order' page");
}

		
	//select city toggle button
	boolean selectcitytoggle=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Citytogglebutton")).isDisplayed();
	sa.assertTrue(selectcitytoggle, "Select city toggle button for Add Site is not available");
	if(selectcitytoggle) {
		ExtentTestManager.getTest().log(LogStatus.PASS, " 'Select City' toggle button is displaying under 'Add Site Order' page as expected");
		Log.info(" 'Select City' toggle button is displaying under 'Add Site Order' page as expected");
	}else {
		ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Select City' toggle button is not avilable under 'Add Site Order' page ");
		Log.info(" 'Select City' toggle button is not avilable under 'Add Site Order' page ");
	}
	
	
	Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Citytogglebutton")));
	Thread.sleep(5000);
	
	scrolltoend();
	Thread.sleep(3000);
	
	Log.info("Scrolling down to validate error messgae for City name and city code");
	//Click on Next button to get warning message for XNG City name and XNG City Code text fields
	click_commonMethod(application, "OK", "obutton_spanTag", xml);
	Thread.sleep(5000);
	
	WebElement deviceCountry=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Country"));
	ScrolltoElement(deviceCountry);
	Thread.sleep(3000);
	
	Log.info("scrolling above till device country for validating error message for 'city name ' and 'city code'");
	//XNG City Name Error message	
	warningMessage_commonMethod(application, "Addsiteorder_xngcitynameerrmsg", "XNG City Name", xml);


	
	//XNG City Code Error message	
	warningMessage_commonMethod(application, "Addsiteorder_xngcityCodeerrmsg", "XNG City Code", xml);


		//xng city name
		boolean XNGcityname=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_xngcityname")).isDisplayed();
		sa.assertTrue(XNGcityname, "XNG city name field for Add Site is not available");
		if(XNGcityname) {
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'XNG City name field is displaying under 'Add Site order' as expected");
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'XNG City name field is not available under 'Add Site order'");
		}
		
		//xng city code
		boolean XNGcitycode=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_XNGcitycode")).isDisplayed();
		sa.assertTrue(XNGcitycode, "XNG city code field for Add Site is not available");
		if(XNGcitycode) {
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'XNG City code field is displaying under 'Add Site order' as expected");
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'XNG City code field is not available under 'Add Site order'");
		}

	
}


public void validateCountry_AddSiteorder(String application) throws InterruptedException, DocumentException {
	
	boolean COuntry=false;
	
	COuntry = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Country")).isDisplayed();
	sa.assertTrue(COuntry, "Country dropdown is not displayed");
	if(COuntry) {
		ExtentTestManager.getTest().log(LogStatus.PASS, " 'Country' mandatory dropdown is displaying under 'Add Site Order' page as expected");	
		Log.info("Country dropdown is displaying");
		
	Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Country")));
	List<WebElement> listofcountry = getwebelements(xml.getlocator("//locators/" + application + "/ClassNameForDropdowns"));

	if(listofcountry.size()>=1) {
	for (WebElement countrytypes : listofcountry) {
		
				ExtentTestManager.getTest().log(LogStatus.PASS,"The list of country inside dropdown is: "+countrytypes.getText());
		
	}
}else {
	Log.info("no values are available inside Country dropdown for Add site order");
//	ExtentTestManager.getTest().log(LogStatus.FAIL,"no values are available inside Country dropdown for Add site order");
}

//click on Blank page	
	clickOnBankPage();
	Thread.sleep(3000);
	
}else {
ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Country' mandatory dropdown is not available under 'Add Site Order' page");
}
	
}



public void Site_AddSiteOrder(String application, String existingsiteselection, String sitevalue, String newsiteselection, String CSR_Name ) throws InterruptedException, IOException, DocumentException {
	
	
	//Existing Site	
		if((existingsiteselection.equalsIgnoreCase("yes")) & (newsiteselection.equalsIgnoreCase("no"))) {

			
			
			if(sitevalue.equalsIgnoreCase("null")) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Physical Site field is mandatory and no values are provided" );
			}else {
				
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_sitedropdown")));
			Thread.sleep(3000);	
			Clickon(getwebelement("//div[text()='" + sitevalue + "']"));
			Thread.sleep(3000);
			ExtentTestManager.getTest().log(LogStatus.PASS, sitevalue+  " is selected under Physical Site dropdown");
			}
		}

		else if ((existingsiteselection.equalsIgnoreCase("no")) & (newsiteselection.equalsIgnoreCase("yes"))) {
				
			click_commonMethod(application, "Site Order Toggle button", "Addsiteorder_Sitetogglebutton", xml);
			Thread.sleep(3000);
			
			if(CSR_Name.equalsIgnoreCase("null")){
				ExtentTestManager.getTest().log(LogStatus.FAIL, "CSR name field is mandatory and no values are provided");
				
			}else {
				
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_CSRname")), CSR_Name);
			Thread.sleep(3000);
			ExtentTestManager.getTest().log(LogStatus.PASS, CSR_Name+ " is entered under CSR Name field");
			}
			} 

}

     public void Countyr_AddSiteOrder(String application, String country) throws InterruptedException, DocumentException {
    	 
    	//Select Existing Country
    	 addDropdownValues_commonMethod(application, "Device Country", "Addsiteorder_Country", country, xml);
    	 
     }

	
     public void validateSite_AddSiteOrder(String application) throws InterruptedException, DocumentException {
    	 
    	 
    	// CSR name field
    	 boolean csr_name=false;
			csr_name = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_CSRname")).isDisplayed();
			sa.assertTrue(csr_name, "CSR_Name field is not displayed");
			if(csr_name) {
				ExtentTestManager.getTest().log(LogStatus.PASS, " 'CSR Name' text field is displaying under 'Add Site order' page as expected");
				Log.info("CSR name field is dipslaying as expected");
			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, " 'CSR Name' text field is not available under 'Add Site order' page");
			}

		// click on site toggle button to check Physical site dropdown
			boolean sitetogglebutton=false;
			sitetogglebutton = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Sitetogglebutton"))
					.isDisplayed();
			sa.assertTrue(sitetogglebutton, "select Site toggle button is not displayed");
			if(sitetogglebutton) {
				Log.info("site order toggle button is displaying as expected");
				ExtentTestManager.getTest().log(LogStatus.PASS, " 'Select Site' toggle button is displaying under 'Add Site Order' page as expected");
			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Select Site' toggle button is not avilable under 'Add Site Order' page");
			}

			
			click_commonMethod(application, "Site Order Toggle button", "Addsiteorder_Sitetogglebutton", xml);
			Thread.sleep(3000);

	//Check for Error message for physical Site
			scrolltoend();
			Thread.sleep(3000);
			Log.info("scrolling down to click n OK button to find eror message for site Dropdown");
			click_commonMethod(application, "OK", "obutton_spanTag", xml);
			Thread.sleep(5000);
			
			WebElement deviceCountry=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Country"));
			ScrolltoElement(deviceCountry);
			Thread.sleep(3000);
			
			Log.info("scrolling up back till device country dropodwn to find error message validation for physical site");
			
		//warning message_physical Site	
			warningMessage_commonMethod(application, "Addsiteorder_physicalsiteErrmsg", "Physical Site dropdown", xml);
			
		

	//Physical Site dropdown
			boolean SIte=false;
			SIte = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_sitedropdown")).isDisplayed();
			sa.assertTrue(SIte, "PhysicalSite dropdown is not displayed");
			if(SIte) {
				Log.info("Physical Site dropdown is displaying as expected");
				ExtentTestManager.getTest().log(LogStatus.PASS, " 'physical Site' dropdown is displaying under 'Add Site order' page as expected");

			}else {
		ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Physical Site' dropdown is not available under 'Add Site Order' page");
	}
     }
     
     
     
     public void validatePerformancereporting_AddSiteOrder(String application) throws InterruptedException, DocumentException, IOException {
    	 
    	 
    	 String[] Performancereporting = { "Follow Service", "no" };

    	// Performance reporting dropdown
    	 boolean performancereport=false;
			performancereport = getwebelement(
					xml.getlocator("//locators/" + application + "/Addsiteorder_performancereporting")).isDisplayed();
			sa.assertTrue(performancereport, "performance reporting dropdown is not displayed");
			if(performancereport) {
				ExtentTestManager.getTest().log(LogStatus.PASS, " 'Performance reporting' dropdown is displaying under 'Add Site order' as expected");
				Thread.sleep(3000);
				
				//check default value
				String performanceRprtDefaultValues=Gettext(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_performancereportingdefaultvalue")));
				ExtentTestManager.getTest().log(LogStatus.PASS, performanceRprtDefaultValues+ " is displaying under 'Performance reporting' dropdown by default");
	
				//check list of values inside Performance Reporting drodpown		
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_performancereporting")));
			List<WebElement> listofperformancereporting = driver
					.findElements(By.xpath("//div[@class='sc-ifAKCX oLlzc']"));
			
		if(listofperformancereporting.size()>=1) {	
			for (WebElement perfoemancereportingtypes : listofperformancereporting) {
				boolean match = false;
				for (int i = 0; i < Performancereporting.length; i++) {
					if (perfoemancereportingtypes.getText().equals(Performancereporting[i])) {
						match = true;
						Log.info("list of performance reporting : " + perfoemancereportingtypes.getText());
						ExtentTestManager.getTest().log(LogStatus.PASS,"list of performance reporting for AddSite order : " + perfoemancereportingtypes.getText());
					}
					
				}
				
				sa.assertTrue(match);

			}
		}else {
			Log.info("no values are available inside performance reporting dropdown for Add site order");
			ExtentTestManager.getTest().log(LogStatus.FAIL,"no values are available inside performance reporting dropdown for Add site order");
		}
	}else {
		ExtentTestManager.getTest().log(LogStatus.PASS, " 'Performance reporting' dropdown is not availble under 'Add Site order' ");
	}
     }
     
     
     public void performancereporting_AddSiteOrder(String application, String performReport) throws InterruptedException, DocumentException {
    	 
    //Perfomance Reporting	
 		if(performReport.equalsIgnoreCase("Null")) {
 			
 			Log.info("NO changes in 'Performance Reporting' dropdown");
 			ExtentTestManager.getTest().log(LogStatus.PASS, "Performance reporting value is not provided. 'Follow Service' is selected by default");
 			
 		}else {
 	try {		
 		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_performancereporting_xbutton")));
 		Thread.sleep(3000);
 		Clickon(getwebelement("//div[label[text()='Performance Reporting']]//div[text()='"+ performReport +"']"));
 		Thread.sleep(3000);
 		
 		String actualvalue=getwebelement("(//div[label[text()='Performance Reporting']]//span)[2]").getText();
 		ExtentTestManager.getTest().log(LogStatus.PASS, actualvalue + " is selected under Performance reporting dropdown");
 	}catch(NoSuchElementException e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Performance reporting' dropdown is not displaying under 'Add Site order' page");
		Log.info( " 'Performance reporting' dropdown is not displaying under 'Add Site order' page");
	}catch(Exception err) {
		err.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, " not able to select value under 'Performance reporting' checkbox");
		Log.info(" not able to select value under 'Performance Reporting' checkbox");
	}
 		}
     }

     
     
     public void validateProactiveMonitoring_AddSiteOrder(String application) throws InterruptedException, DocumentException, IOException {
    	 
    	 String[] Proactivemonitoring = { "Follow Service", "no" };

    	// pro active monitoring
    	 boolean proactivemonitoring=false;
			proactivemonitoring = getwebelement(
					xml.getlocator("//locators/" + application + "/Addsiteorder_proactivemonitoring")).isDisplayed();
			sa.assertTrue(proactivemonitoring, "pro active monitoring dropdown is not displayed");
			if(proactivemonitoring) {
				ExtentTestManager.getTest().log(LogStatus.PASS, " 'Proactie Monitoring' dropdown is displaying under 'Add Site Order' page as Expected");
				
				//check default value
				String proactiveMonitorDefaultValues=Gettext(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_proactivemonitoringdefaultvalue")));
				ExtentTestManager.getTest().log(LogStatus.PASS, proactiveMonitorDefaultValues+ " is displaying under 'roactive Monitoring' dropdown by default");
	
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_proactivemonitoring")));
			List<WebElement> listofproactivemonitoring = driver
					.findElements(By.xpath("//div[@class='sc-ifAKCX oLlzc']"));
			
		if(listofproactivemonitoring.size()>=1) {	
			for (WebElement proactivemonitoringtypes : listofproactivemonitoring) {

				boolean match = false;
				for (int i = 0; i < Proactivemonitoring.length; i++) {
					if (proactivemonitoringtypes.getText().equals(Proactivemonitoring[i])) {
						match = true;
						Log.info("list of pro active monitoring : " + proactivemonitoringtypes.getText());
						
						ExtentTestManager.getTest().log(LogStatus.PASS,"The list of proactive monitoring inside dropdown while  adding site order is: "+proactivemonitoringtypes.getText());
					}
			}
				sa.assertTrue(match);

			}
		}else {
			
			Log.info("no values are available inside pro active monitoring dropdown for Add site order");
			ExtentTestManager.getTest().log(LogStatus.FAIL,"no values are available inside pro active monitoring dropdown for Add site order");
		}
	}else {
		ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Proactie Monitoring' dropdown is not available under 'Add Site Order' page ");
	}
     }
     
     
     public void proactiveMonitoring_AddSiteOrder(String application, String ProactiveMonitor) throws InterruptedException, DocumentException {
    	 
    	//Pro active monitoring	
  		if(ProactiveMonitor.equalsIgnoreCase("Null")) {
  			ExtentTestManager.getTest().log(LogStatus.PASS, "Pro active monitoring value is not provided. 'Follow Service' is selected by default");
  			
  		}else {
  	try {		
  		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsitorder_proactivemonitoring_xbutton")));
  		Thread.sleep(3000);
  		Clickon(getwebelement("//div[label[text()='Proactive Monitoring']]//div[text()='"+ ProactiveMonitor +"']"));
  		Thread.sleep(3000);
  		
  		String actualvalue=getwebelement("(//div[label[text()='Proactive Monitoring']]//span)[2]").getText();
  		ExtentTestManager.getTest().log(LogStatus.PASS, actualvalue+ " is selected under proactive monitoring dropdown");
  		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Pro active Monitoring' dropdown is not displaying under 'Add Site order' page");
			Log.info( " 'pro active Monitoring' dropdown is not displaying under 'Add Site order' page");
		}catch(Exception err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " not able to select value under 'Proactive Monitoring' dropdown");
			Log.info(" not able to select value under 'pro active Monitoring' dropdown");
		}
  		} 
     }
     
     
     
     public void validateSmartsMOnitoring_AddSiteOrder(String application) throws InterruptedException, DocumentException, IOException {
    	 
    	 String[] Smartmonitoring = { "Follow Service", "no" };

    	// smarts monitoring
    	 boolean smartmonitoring=false;
			smartmonitoring = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_smartmonitoring"))
					.isDisplayed();
			sa.assertTrue(smartmonitoring, "Smart monitoring dropdown is not displayed");
			if(smartmonitoring) {
				ExtentTestManager.getTest().log(LogStatus.PASS, " 'Smart Monitoring' dropdown is displaying under 'Add Site Order' page as expected");
				//check default value
				String smartmonitorDefaultValues=Gettext(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_smartmonitoringdefaultvalue")));
				ExtentTestManager.getTest().log(LogStatus.PASS, smartmonitorDefaultValues+ " is displaying under 'Smart Monitoring' dropdown by default");
	
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_smartmonitoring")));
			List<WebElement> listofsmartmonitoring = driver
					.findElements(By.xpath("//div[@class='sc-ifAKCX oLlzc']"));

		if(listofsmartmonitoring.size()>=1) {	
			for (WebElement smartmonitoringtypes : listofsmartmonitoring) {

				boolean match = false;
				for (int i = 0; i < Smartmonitoring.length; i++) {
					if (smartmonitoringtypes.getText().equals(Smartmonitoring[i])) {
						match = true;
						Log.info("list of smart monitoring are : " + smartmonitoringtypes.getText());
						ExtentTestManager.getTest().log(LogStatus.PASS,"The list of smart monitoring  inside dropdown while  adding site order is: "+smartmonitoringtypes.getText());
					}
				}
				
				sa.assertTrue(match);
			}
		}else {

			Log.info("no values are available inside smart monitoring dropdown for Add site order");
			ExtentTestManager.getTest().log(LogStatus.FAIL,"no values are available inside smart monitoring dropdown for Add site order");
		}
			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Smart Monitoring' dropdown is not avilable under 'Add Site Order' page");
			}
     }
     
     
     public void smartsMonitoring_AddSiteOrder(String application, String smartmonitor) throws InterruptedException, DocumentException {
    	 
    			if(smartmonitor.equalsIgnoreCase("null")) {
    				ExtentTestManager.getTest().log(LogStatus.PASS, "Smart monitoring value is not provided. 'Follow Service' is selected by default");
    			}else {
    		try {		
    			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_smartmonitoring_xbutton")));
    			Thread.sleep(3000);
    			Clickon(getwebelement("//div[label[text()='Smarts Monitoring']]//div[text()='"+ smartmonitor +"']"));
    			Thread.sleep(3000);
    			
    			String actualValue=getwebelement("(//div[label[text()='Smarts Monitoring']]//span)[2]").getText();
    			ExtentTestManager.getTest().log(LogStatus.PASS, actualValue+ " is selected under Smart monitoring dropdown");
    		}catch(NoSuchElementException e) {
    			e.printStackTrace();
    			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Smart Monitoring' dropdown is not displaying under 'Add Site order' page");
    			Log.info( " 'Smart Monitoring' dropdown is not displaying under 'Add Site order' page");
    		}catch(Exception err) {
    			err.printStackTrace();
    			ExtentTestManager.getTest().log(LogStatus.FAIL, " not able to select value under 'Smart Monitoring' dropdown");
    			Log.info(" not able to select value under 'Smart Monitoring' dropdown");
    		}
    			}

     }
     
     
     public void validateSiteAlias_AddSiteOrder(String application) throws InterruptedException, DocumentException {
    		
 		// Site alias Field
    	 boolean sitealias=false; 
try { 				
	sitealias = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_sitealias"))
 						.isDisplayed();
 				sa.assertTrue(sitealias, "Site alias field is not displayed");
 				if(sitealias) {
 					ExtentTestManager.getTest().log(LogStatus.PASS, " 'Site Alias' text field is displaying under 'Add Site order' page as expected");
 				}else {
 					ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Site Alias' text field is not displaying under 'Add Site order' page");
 				}
     }catch	(Exception e) {
    	 e.printStackTrace();
    	 ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Site Alias' text field is not displaying under 'Add Site order' page");
     }
     }
     
     
     public void SiteAlias_AddSiteOrder(String application, String siteallias) throws InterruptedException, IOException, DocumentException {
    
    	 if(siteallias.equalsIgnoreCase("null")) {
 					ExtentTestManager.getTest().log(LogStatus.PASS, "No values entered for 'Site Alias' field");
 				}else {
 			try {		
 				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_sitealias")), siteallias);
 				Thread.sleep(3000);
 				
 				String actualvalue=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_sitealias")).getAttribute("value");
 				ExtentTestManager.getTest().log(LogStatus.PASS, actualvalue+ " is entered under 'Site Alias' field");
 			}catch(NoSuchElementException e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Site Alias' field is not displaying under 'Add Site order' page");
				Log.info(" 'Site Alias' field is not dispyating under 'Add Site order' page");
			}catch(Exception err) {
				err.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to enter value under 'Site Alias' field");
				Log.info(" Not able to enter value under 'Site Alias' field");
			}
 		}

     }
     
     
     public void validateVlanID_AddSiteOrder(String application) throws InterruptedException, DocumentException {
    	 boolean vlanid=false;
    	 try {
    				vlanid = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Vlanid")).isDisplayed();
    				sa.assertTrue(vlanid, "VLAN id field is not displayed");
    				if(vlanid) {
    					ExtentTestManager.getTest().log(LogStatus.PASS, "'VLAN ID' text field is displaying under 'Add Site order' page as expected");
    				}else {
    					ExtentTestManager.getTest().log(LogStatus.FAIL, " 'VLAN ID' text field is not displaying under 'Add Site order' page");
    				}
    	 }catch(Exception e) {
    		 e.printStackTrace();
    		 ExtentTestManager.getTest().log(LogStatus.FAIL, " 'VLAN ID' text field is not displaying under 'Add Site order' page");
    	 }
     }
     
     
     public void VLANid_AddSiteOrder(String application, String VLANid) throws InterruptedException, IOException, DocumentException {
    	 
			if(VLANid.equalsIgnoreCase("null")) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "No values entered for 'Vlan id' field");
			}else {
			try {	
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Vlanid")), VLANid);
			Thread.sleep(3000);
			
			String actualvalue=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Vlanid")).getAttribute("value");
			ExtentTestManager.getTest().log(LogStatus.PASS, actualvalue + " is entered under Vlan id field");
			}catch(NoSuchElementException e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Vlan Id' field is not displating under 'Add Site order' page");
				Log.info(" 'Vlan Id' field is not displating under 'Add Site order' page");
			}catch(Exception err) {
				err.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to enter value under 'Vlan Id' field");
				Log.info(" Not able to enter value under 'Vlan Id' field");
			}
			}

     }
     
     
     public void valiadateDCAEnabledsite_AddSieOrder(String application) throws InterruptedException, DocumentException {
    	 
    	 String[] cloudServiceprovider = { "Amazon Web Service", "Microsoft Azure" };
    	 boolean DCAEnabledsite=false;

    	// DCA Enabled site
    				DCAEnabledsite = getwebelement(
    						xml.getlocator("//locators/" + application + "/Addsiteorder_DCAenabledsitecheckbox")).isDisplayed();
    				sa.assertTrue(DCAEnabledsite, "DCA enabled site is not displayed ");
    				if(DCAEnabledsite) {
    					
    				ExtentTestManager.getTest().log(LogStatus.PASS, " 'DCA Enabled Site' checkbox is displaying under 'Add Site order' page as expected");	
    				boolean DCAselection=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_DCAenabledsitecheckbox")).isSelected();
    				sa.assertFalse(DCAselection,"DCA checkbox under Addsite order is selected by default");
    				if(DCAselection) {
    					ExtentTestManager.getTest().log(LogStatus.FAIL, " ' DCA Enabled Site' checkbox should not be selected by default");
    				}else {
    					ExtentTestManager.getTest().log(LogStatus.PASS, " 'DCA Enabled Site' checkbox is not selected by default as expected");
    							
    				ExtentTestManager.getTest().log(LogStatus.INFO,"when DCA Enabled site checkbox is selected, Cloud service provider dropdown should occur"
    						+ " Cloud service provider dropdown should have values: "
    						+ "  1) Amazon Web Service "
    						+ "  2) Microsoft Azure");	
    				
    				Thread.sleep(5000);
    			// For Cloud service provider
    				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_DCAenabledsitecheckbox")));
    				Log.info("DCA site is enabled to add cloud service provider details");
    				Thread.sleep(3000);
    				
    				boolean DCAafterSelection=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_DCAenabledsitecheckbox")).isSelected();
    				if(DCAafterSelection) {
    				ExtentTestManager.getTest().log(LogStatus.PASS,"DCA site is selected to add cloud service provider details");
    				Thread.sleep(3000);

    				boolean cloudservice = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_cloudserviceProvider"))
    						.isDisplayed();
    				sa.assertTrue(cloudservice, "cloud service provider dropdown is not displayed");
    				if(cloudservice) {
    					ExtentTestManager.getTest().log(LogStatus.PASS, " 'Cloud Service Provider' dropdown is displaying when 'DCA Enabled Site' checkbox is selected as expected");

    				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_cloudserviceProvider")));
    				List<WebElement> listofcloudservices = driver
    						.findElements(By.xpath("//div[@class='sc-ifAKCX oLlzc']"));
    				
    			if(listofcloudservices.size()>0) {	
    				for (WebElement cloudserviceprovidertypes : listofcloudservices) {

    					boolean match = false;
    					for (int i = 0; i < cloudServiceprovider.length; i++) {
    						if (cloudserviceprovidertypes.getText().equals(cloudServiceprovider[i])) {
    							match = true;
    							Log.info("list of cloud service providers are : " + cloudserviceprovidertypes.getText());
    							ExtentTestManager.getTest().log(LogStatus.PASS,"The list of cloudservice provider inside dropdown while  adding site order is: "+cloudserviceprovidertypes.getText());
    						}
    					}
    					sa.assertTrue(match);
    				}
    			}else {
    				Log.info("no values are available inside cloudservice provider dropdown for Add site order");
    				ExtentTestManager.getTest().log(LogStatus.FAIL,"no values are available inside cloudservice provider dropdown for Add site order");
    				
    			}
    				}else {
    					ExtentTestManager.getTest().log(LogStatus.PASS, " 'Cloud Service Provider' dropdown is not available when 'DCA Enabled Site' checkbox is selected");
    				}
    				
    				}else {
    					ExtentTestManager.getTest().log(LogStatus.FAIL, " 'DCA Enabled Site' checkbox is not getting selected");
    				}
    		  }
    			
    				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_DCAenabledsitecheckbox")));
    				Thread.sleep(5000);
    				
    		}else {
    			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'DCA Enabled Site' checkbox is not available under 'Add Site order' page");
    		}
     }
     
     
     public void DCAEnabledSite_AddSiteOrder(String application, String DCAenabledsite, String cloudserviceprovider) throws InterruptedException, DocumentException {
    	 
    	//DCA Enabled Site	
			if (DCAenabledsite.equalsIgnoreCase("yes")) {

				Clickon(getwebelement(
						xml.getlocator("//locators/" + application + "/Addsiteorder_DCAenabledsitecheckbox")));
				ExtentTestManager.getTest().log(LogStatus.PASS, "DCA enabled checkbox is selected");
				
				
				
			 //Cloud Service provider
				if(cloudserviceprovider.equalsIgnoreCase("null")) {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "DCA cloud service provider dropdown is mandatory. No values are provided");
				}else {
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_cloudserviceProvider")));
				Thread.sleep(3000);
				Clickon(getwebelement("//div[text()='" + cloudserviceprovider + "']"));
				Thread.sleep(3000);
				ExtentTestManager.getTest().log(LogStatus.PASS, cloudserviceprovider +  " is selected under 'cloud service provider' dropdown");
				}

			} else {
				Log.info("DCA site is not selected");
				ExtentTestManager.getTest().log(LogStatus.PASS, "DCA enabled checkbox is not selected");
			}	

     }
     
     
     public void validateRemark_AddSiteOrder(String application) throws InterruptedException, DocumentException {
    	 boolean REmark=false;
    	 try {
			REmark = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_remark"))
			.isDisplayed();
			sa.assertTrue(REmark, " Remark field is not displayed");
			if(REmark) {
				ExtentTestManager.getTest().log(LogStatus.PASS, " 'Remak' field is displaying under 'Add Site order' page as expected");
			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Remak' field is not displaying under 'Add Site order' page");
			}
    	 }catch(Exception e) {
    		 e.printStackTrace();
    		 ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Remak' field is not displaying under 'Add Site order' page");
    		 
    	 }

     }
     
     
     public void remark_AddSiteOrder(String application, String remark) throws InterruptedException, IOException, DocumentException {
    	 
			if(remark.equalsIgnoreCase("null")) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "No values entered under remark ");
			}else {
		try {		
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_remark")), remark);
			Thread.sleep(3000);
			
			String actualvalue=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_remark")).getAttribute("value");
			ExtentTestManager.getTest().log(LogStatus.PASS, actualvalue + " is entered under 'remark' field");
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Remark' field is not displating under 'Add Site order' page");
			Log.info(" 'Remark' field is not displating under 'Add Site order' page");
		}catch(Exception err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to enter value under 'Remark' field");
			Log.info(" Not able to enter value under 'Remark' field");
		}
			}

     }
     
     
     public void nontermination_AddSiteorder(String application, String nonterminatepoinr) throws InterruptedException, DocumentException {
    	 
    	//Non- termination point	
			if(nonterminatepoinr.equalsIgnoreCase("yes")) {
			try {	
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_nonterminationpoint")));
				Thread.sleep(3000);
				
				boolean nonTerminationSelection=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_nonterminationpoint")).isSelected();
				if(nonTerminationSelection) {
					ExtentTestManager.getTest().log(LogStatus.PASS, " 'Non-Termination point' checkbox is selected as expected");
					Log.info(" 'Non-Termination point' checkbox is selected as expected");
				}else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Non-Termination point' checkbox is not selected");
					Log.info(" 'Non-Termination point' checkbox is not selected");
				}
				
				ExtentTestManager.getTest().log(LogStatus.PASS, "Non-termination point checkbox is selected");
			}catch(NoSuchElementException e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, " Non-Termination point' checkbox is not dipslaying under 'Add Site order' page");
				Log.info(" Non-Termination point' checkbox is not dipslaying under 'Add Site order' page");
			}catch(Exception err) {
				err.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to click on 'non-Termination point' checkbox");
				Log.info(" Not able to click on 'non-Termination point' checkbox");
			}
			}else {
				Log.info("Non termination point checkbox is not selected as expected");
				ExtentTestManager.getTest().log(LogStatus.PASS, "Non-termination point chekbox is not selected");
			}

     }
     
     
     public void protected_AddSiteOrder(String application, String Protected) throws InterruptedException, DocumentException {
    	 
			//Protected	
				if(Protected.equalsIgnoreCase("yes")) {
				try {	
					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_protected")));
					Thread.sleep(3000);
					
					boolean protectedSelection=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_protected")).isSelected();
					if(protectedSelection) {
						ExtentTestManager.getTest().log(LogStatus.PASS, " 'Protected' checkbox is selected as expected");
						Log.info(" 'Protected' checkbox is selected as expected");
					}else {
						ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Protected' checkbox is not selected");
						Log.info(" 'Non-Termination point' checkbox is not selected");
					}
					ExtentTestManager.getTest().log(LogStatus.PASS, "Protected checkbox is selected");
				  }catch(NoSuchElementException e) {
						e.printStackTrace();
						ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Protected' checkbox is not displaying under 'Add Site order' page");
						Log.info(" 'Protected' checkbox is not displaying under 'Add Site order' page");
					}catch(Exception err) {
						err.printStackTrace();
						ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to click on 'non-Termination point' checkbox");
						Log.info(" Not able to click on 'Protected' checkbox");
					}
					
				}else {
					Log.info("Protected checkbox is not selecetd as expected");
					ExtentTestManager.getTest().log(LogStatus.PASS, "Protected checkbox is not selected");
				}
				
			}
     
     
     public void devicename_AddSiteOrder(String application, String devicename) throws InterruptedException, IOException, DocumentException {

			//Device name
					if(devicename.equalsIgnoreCase("null")) {
						ExtentTestManager.getTest().log(LogStatus.FAIL, "device name field is mandatory. No values entered under 'device name' field");
					}else {
					  try {
						SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Devicenamefield")), devicename);
						Thread.sleep(3000);
						
						String actualvalue=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Devicenamefield")).getAttribute("value");
						
						ExtentTestManager.getTest().log(LogStatus.PASS, actualvalue + " is entered under 'device name' field");
					  }catch(NoSuchElementException e) {
						  e.printStackTrace();
						  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Device name' field is not displaying under 'Add Site Order' page");
						  Log.info(" 'Device name' field is not displaying under 'Add Site Order' page");
					  }catch(Exception err) {
						  err.printStackTrace();
						  ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to enter value in 'Device name' field");
						  Log.info(" Not able to enter value in 'Device name' field");
					  }
					}
			
	       	}


    	 
     
     
     public void addSiteOrderValues_point2point(String application,  String interfaceSpeed,
 			String country, String city, String CSR_Name, String site,
 			String performReport, String ProactiveMonitor, String smartmonitor, String technology, String siteallias,
 			String VLANid, String DCAenabledsite, String cloudserviceprovider, String sitevalue, String remark,
 			String xngcityname, String xngcitycode,String devicename, String nonterminatepoinr, String Protected, String newcityselection, String existingcityselection,
 			String existingsiteselection, String newsiteselection) throws InterruptedException, DocumentException, IOException {
    	
    	 scrollToTop();
    	 Thread.sleep(2000);
    	 
    	 Countyr_AddSiteOrder(application, country);
    	 
    	 City_AddSiteorder(application, existingcityselection, city, newcityselection, xngcityname, xngcitycode, sitevalue, CSR_Name, existingsiteselection, newsiteselection);

//    	 Site_AddSiteOrder(application, existingsiteselection, sitevalue, newsiteselection, CSR_Name);

    	
    	 scrolltoend();
    	 Thread.sleep(1000);
    	 
    	 performancereporting_AddSiteOrder(application, performReport);

    	 proactiveMonitoring_AddSiteOrder(application, ProactiveMonitor);

    	 smartsMonitoring_AddSiteOrder(application, smartmonitor);

    scrolltoend();
    Thread.sleep(2000);
    
    	 SiteAlias_AddSiteOrder(application, siteallias);
    	 
    	 VLANid_AddSiteOrder(application, VLANid);	
    	 
    	 DCAEnabledSite_AddSiteOrder(application, DCAenabledsite, cloudserviceprovider);
    	 
    	 remark_AddSiteOrder(application, remark);
    	 
    	 technologyP2P_AddSiteOrder(application, technology, interfaceSpeed, devicename, nonterminatepoinr, Protected);
    	 
    	 Thread.sleep(3000);
    	 scrolltoend();
    	 Thread.sleep(3000);
 		click_commonMethod(application, "OK", "okbutton", xml);
    	 

     }
     
     
     public void addSiteOrderValues_point2point_extendedCircuit(String application,  String interfaceSpeed,
  			String country, String city, String CSR_Name, String site,
  			String performReport, String ProactiveMonitor, String smartmonitor, String technology, String siteallias,
  			String VLANid, String DCAenabledsite, String cloudserviceprovider, String sitevalue, String remark,
  			String xngcityname, String xngcitycode,String devicename, String nonterminatepoinr, String Protected, String newcityselection, String existingcityselection,
  			String existingsiteselection, String newsiteselection, String siteOrder_sitePreferenceType) throws InterruptedException, DocumentException, IOException {
     	
     	 scrollToTop();
     	 Thread.sleep(2000);
     	 
     	 Countyr_AddSiteOrder(application, country);
     	 
     	 City_AddSiteorder(application, existingcityselection, city, newcityselection, xngcityname, xngcitycode, sitevalue, CSR_Name, existingsiteselection, newsiteselection);

//     	 Site_AddSiteOrder(application, existingsiteselection, sitevalue, newsiteselection, CSR_Name);

     	 scrolltoend();
     	 Thread.sleep(1000);
     	 
     	 performancereporting_AddSiteOrder(application, performReport);

     	 proactiveMonitoring_AddSiteOrder(application, ProactiveMonitor);

     	 smartsMonitoring_AddSiteOrder(application, smartmonitor);

     scrolltoend();
     Thread.sleep(2000);
     
     	 SiteAlias_AddSiteOrder(application, siteallias);
     	 
     	 VLANid_AddSiteOrder(application, VLANid);	
     	 
     	 addDropdownValues_commonMethod(application, "Site Preference Type" , "AddSiteOrder_sitePreferenceType_Dropodwn" , siteOrder_sitePreferenceType, xml);
     	 
     	 
     	 DCAEnabledSite_AddSiteOrder(application, DCAenabledsite, cloudserviceprovider);
     	 
     	 remark_AddSiteOrder(application, remark);
     	 
     	 technologyP2P_AddSiteOrder(application, technology, interfaceSpeed, devicename, nonterminatepoinr, Protected);
     	 
     	 Thread.sleep(3000);
     	 scrolltoend();
     	 Thread.sleep(3000);
  		click_commonMethod(application, "OK", "okbutton", xml);
     	 

      }

     
     
     
     public void technologyP2P_AddSiteOrder(String application, String technology, String interfaceSpeed, String devicename, String nonterminatepoinr, String Protected) throws InterruptedException, IOException, DocumentException {
    	 
    	//Technology
 		if(technology.equalsIgnoreCase("null")) {
 			ExtentTestManager.getTest().log(LogStatus.FAIL, "Technology dropdown is a mandatory field and no values are provided"); 
 		}else {
 		
 			if(interfaceSpeed.equals("1GigE")) {	
 			
 		if(technology.equals("Actelis") || technology.equals("Atrica") || technology.equals("Overture") || technology.equals("Accedian-1G") || technology.equals("Cyan" ) || technology.equals("Alu"))	{
 			
 			click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
 			Thread.sleep(3000);
 			WebElement technologySelected = getwebelement(xml.getlocator("//locators/" + application + "/selectValueUnderTechnologyDropdown").replace("value", technology)); clickonTechnology(technologySelected, technology);
 			Thread.sleep(3000);
 			ExtentTestManager.getTest().log(LogStatus.PASS, technology + " is selected under technology dropdown");
 			
 			
 			
 			if(technology.equals("Actelis")) {	
 				
 			     Log.info("No additional fields displays");
 			}
 			

 			else if(technology.equals("Atrica")) {
 				
 				//Device name
 					devicename_AddSiteOrder(application, devicename);

 	  			//Non- termination point	
 	  				nontermination_AddSiteorder(application, nonterminatepoinr);
 	  				
 	  			//Protected	
 	  				protected_AddSiteOrder(application, Protected);
 			
 			}
 			
 			

 			else if(technology.equals("Overture") || technology.equals("Accedian-1G")) {	

 				//Non- termination point	
	  				nontermination_AddSiteorder(application, nonterminatepoinr);
	  				
	  			//Protected	
	  				protected_AddSiteOrder(application, Protected);
 				

 			}
 			

 			else if(technology.equals("Cyan")) {	

 				//Non- termination point	
	  				nontermination_AddSiteorder(application, nonterminatepoinr);
	  				
 			}
 			
 			else if(technology.equals("Alu")) {
 				
 				//Device name
					devicename_AddSiteOrder(application, devicename);
 				
 		       	}
 		}
 	}
 			
 			if(interfaceSpeed.equals("10GigE")) {	
 				
 				if(technology.equals("Accedian"))	{
 					
 					click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
 					Thread.sleep(3000);
 					WebElement technologySelected = getwebelement(xml.getlocator("//locators/" + application + "/selectValueUnderTechnologyDropdown").replace("value", technology)); clickonTechnology(technologySelected, technology);
 					
 					Thread.sleep(3000);
 					ExtentTestManager.getTest().log(LogStatus.PASS, technology + " is selected under technology dropdown");
 						
 				//Non- termination point	
 	  				nontermination_AddSiteorder(application, nonterminatepoinr);
 	  				
 	  			//Protected	
 	  				protected_AddSiteOrder(application, Protected);
 						}else {
 							ExtentTestManager.getTest().log(LogStatus.FAIL, technology + "is not available under 'Technology' dropdown for '10GigE' interface speed");
 						}
 					
 				}
 			}	
 	
 	 }
     
     
     
     
     public void technologyHubAndSpoke1G_IVRefrencePrimary_AddSiteOrder(String application, String technology, String interfaceSpeed, String devicename, 
    		 String nonterminatepoinr, String Protected) throws InterruptedException, IOException, DocumentException {
    	 
     	//Technology
  		if(technology.equalsIgnoreCase("null")) {
  			ExtentTestManager.getTest().log(LogStatus.FAIL, "Technology dropdown is a mandatory field and no values are provided"); 
  		}else {
  		
  		if(technology.equals("Actelis") || technology.equals("Atrica") || technology.equals("Overture") || technology.equalsIgnoreCase("Accedian-1G") || technology.equals("Cyan" ) || technology.equals("Alu"))	{
  			
  			click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
  			Thread.sleep(3000);
  			WebElement technologySelected = getwebelement(xml.getlocator("//locators/" + application + "/selectValueUnderTechnologyDropdown").replace("value", technology)); clickonTechnology(technologySelected, technology);
  			Thread.sleep(3000);
  			ExtentTestManager.getTest().log(LogStatus.PASS, technology + " is selected under technology dropdown");
  			
  			
  			if(technology.equals("Actelis")) {	
  			     Log.info("No additional fields displays");
  			}
  			

  			else if((technology.equals("Atrica")) ) {
  			//Device name
  				devicename_AddSiteOrder(application, devicename);
  			
  			//Non- termination point	
  				nontermination_AddSiteorder(application, nonterminatepoinr);
  				
  			//Protected	
  				protected_AddSiteOrder(application, Protected);
  			}
  			
  			
  			else if(technology.equals("Overture") || technology.equalsIgnoreCase("Accedian-1G")) {	
  			//Non- termination point	
  				nontermination_AddSiteorder(application, nonterminatepoinr);
  				
  			//Protected	
  				protected_AddSiteOrder(application, Protected);
  			}
  			
  			else if(technology.equals("Cyan")) {	
  			//Non- termination point	
  				nontermination_AddSiteorder(application, nonterminatepoinr);
  			
  			}
  			
  			else if(technology.equals("Alu")) {
  			//Device name
  				devicename_AddSiteOrder(application, devicename);
  				
  		       	}
  		    }
  		}
  	}	
     

     public void technologyHubAndSpoke1G_IVRefrencePrimary_OffnetSelected_AddSiteOrder(String application, String technology, String interfaceSpeed, String devicename, 
    		 String nonterminatepoinr, String Protected) throws InterruptedException, IOException, DocumentException {
    	 
     	//Technology
  		if(technology.equalsIgnoreCase("null")) {
  			ExtentTestManager.getTest().log(LogStatus.FAIL, "Technology dropdown is a mandatory field and no values are provided"); 
  		}else {
  		
  		if(technology.equals("Overture") || technology.equalsIgnoreCase("Accedian-1G") || technology.equalsIgnoreCase("Accedian"))	{
  			
  			click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
  			Thread.sleep(3000);
  			WebElement technologySelected = getwebelement(xml.getlocator("//locators/" + application + "/selectValueUnderTechnologyDropdown").replace("value", technology)); clickonTechnology(technologySelected, technology);
  			Thread.sleep(3000);
  			ExtentTestManager.getTest().log(LogStatus.PASS, technology + " is selected under technology dropdown");
  			
  			
  			if(technology.equals("Overture") || technology.equalsIgnoreCase("Accedian-1G")) {	
  			//Non- termination point	
  				nontermination_AddSiteorder(application, nonterminatepoinr);
  				
  			//Protected	
  				protected_AddSiteOrder(application, Protected);
  			}
  		  }	
  		}
  	}	

     
     public void technologyEPN1G_IVRefrencePrimary_AddSiteOrder(String application, String technology, String interfaceSpeed, String devicename, 
    		 String nonterminatepoinr, String Protected) throws InterruptedException, IOException, DocumentException {
    	 
     	//Technology
  		if(technology.equalsIgnoreCase("null")) {
  			ExtentTestManager.getTest().log(LogStatus.FAIL, "Technology dropdown is a mandatory field and no values are provided"); 
  		}else {
  		
  		if(technology.equals("Actelis") || technology.equals("Atrica") || technology.equals("Overture") || technology.equals("Accedian-1G") || technology.equals("Cyan" ) || technology.equals("Alu"))	{
  			
  			click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
  			Thread.sleep(3000);
  			WebElement technologySelected = getwebelement(xml.getlocator("//locators/" + application + "/selectValueUnderTechnologyDropdown").replace("value", technology)); clickonTechnology(technologySelected, technology);
  			Thread.sleep(3000);
  			ExtentTestManager.getTest().log(LogStatus.PASS, technology + " is selected under technology dropdown");
  			
  			
  			if(technology.equals("Actelis")) {	
  			     Log.info("No additional fields displays");
  			}
  			

  			else if((technology.equals("Atrica")) || (technology.equals("Overture")) || (technology.equals("Accedian-1G"))) {
  			//Device name
  				devicename_AddSiteOrder(application, devicename);
  			
  			//Non- termination point	
  				nontermination_AddSiteorder(application, nonterminatepoinr);
  				
  			//Protected	
  				protected_AddSiteOrder(application, Protected);
  			}
  			
  			else if(technology.equals("Cyan")) {	
  			//Non- termination point	
  				nontermination_AddSiteorder(application, nonterminatepoinr);
  			
  			}
  			
  			else if(technology.equals("Alu")) {
  			//Device name
  				devicename_AddSiteOrder(application, devicename);
  				
  		       	}
  		    }
  		}
  	}	
  	
  	 
     public void technologyHubAndSpoke1G_IVRefrenceAccess_AddSiteOrder(String application, String technology, String interfaceSpeed, String devicename, 
    		 String nonterminatepoinr, String Protected, String GCRolo, String Vlan, String Vlanether, String primaryVlan, String primaryVlanether) throws InterruptedException, IOException, DocumentException {  
    	 
     	//Technology
  		if(technology.equalsIgnoreCase("null")) {
  			ExtentTestManager.getTest().log(LogStatus.FAIL, "Technology dropdown is a mandatory field and no values are provided"); 
  		}else {
  		
  			if(interfaceSpeed.equals("1GigE")) {	
  			
  		if(technology.equals("Actelis") || technology.equals("Atrica") || technology.equals("Overture") || technology.equalsIgnoreCase("Accedian-1G") || technology.equals("Cyan" ) || technology.equals("Alu"))	{
  			
  			click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
  			Thread.sleep(3000);
  			WebElement technologySelected = getwebelement(xml.getlocator("//locators/" + application + "/selectValueUnderTechnologyDropdown").replace("value", technology)); clickonTechnology(technologySelected, technology);
  			Thread.sleep(3000);
  			ExtentTestManager.getTest().log(LogStatus.PASS, technology + " is selected under technology dropdown");
  			
  			
  			
  			if(technology.equals("Actelis")) {	
  				
  			     Log.info("No additional fields displays");
  			}
  			

  			else if((technology.equals("Atrica")) || (technology.equalsIgnoreCase("Accedian-1G")) ) {
  				
  				//Non-Termination Point
  				nontermination_AddSiteorder(application, nonterminatepoinr);
  				
  				//Protected
  				protected_AddSiteOrder(application, Protected);
  			}
  			
  			

  			else if(technology.equals("Overture")) {	
  			//Non- termination point checkbox
  				nontermination_AddSiteorder(application, nonterminatepoinr);
  				
  			//Protected checkbox	
  				protected_AddSiteOrder(application, Protected);
  				
  			//GCR OLO Type dropdown
  				GCROLOType_AddSiteorder(application, GCRolo);
  				
  			//VLAN Text field
  				Vlan_AddSiteorder(application, Vlan);
  				
  			//VLAN Ether Type dropdown
  				Vlanethertype_AddSiteorder(application, Vlanether);
  				
  			//Primary VLAN Text Field
  				primaryVlan_AddSiteorder(application, primaryVlan);
  				
  			//Primary Ether Vlan dropdown
  				primaryVlanethertype_AddSiteorder(application, primaryVlanether);
  				
  			}
  			
  			else if(technology.equals("Cyan")) {	
  			//Non- termination point checkbox
  				nontermination_AddSiteorder(application, nonterminatepoinr);
  			
  			}
  			
  			
  			else if(technology.equals("Alu")) {
  				Log.info("No Additional fields display for 'Alu' technology");
  				
  			}
  		}
  	}
  			
  	}	
  	

  	 }
     
     
     public void technologyHubAndSpoke1G_IVRefrenceAccess_offnetSelected_AddSiteOrder(String application, String technology, String interfaceSpeed, String devicename, 
    		 String nonterminatepoinr, String Protected, String GCRolo, String Vlan, String Vlanether, String primaryVlan, String primaryVlanether) throws InterruptedException, IOException, DocumentException {
    	 
     	//Technology
  		if(technology.equalsIgnoreCase("null")) {
  			ExtentTestManager.getTest().log(LogStatus.FAIL, "Technology dropdown is a mandatory field and no values are provided"); 
  		}else {
  		
  			if(interfaceSpeed.equals("1GigE")) {	
  			
  		if(technology.equals("Overture") || technology.equalsIgnoreCase("Accedian-1G") || technology.equals("Accedian"))	{
  			
  			click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
  			Thread.sleep(3000);
  			WebElement technologySelected = getwebelement(xml.getlocator("//locators/" + application + "/selectValueUnderTechnologyDropdown").replace("value", technology)); clickonTechnology(technologySelected, technology);
  			Thread.sleep(3000);
  			ExtentTestManager.getTest().log(LogStatus.PASS, technology + " is selected under technology dropdown");
  			

  			if((technology.equalsIgnoreCase("Accedian-1G")) ) {
  				
  				//Non-Termination Point
  				nontermination_AddSiteorder(application, nonterminatepoinr);
  				
  				//Protected
  				protected_AddSiteOrder(application, Protected);
  			}
  			
  			else if(technology.equals("Overture")) {
  				
  			//Non- termination point checkbox
  				nontermination_AddSiteorder(application, nonterminatepoinr);
  				
  			//Protected checkbox
  				protected_AddSiteOrder(application, Protected);
  				
  			//GCR OLO Type dropdown
  				GCROLOType_AddSiteorder(application, GCRolo);
  				
  			//VLAN Text field
  				Vlan_AddSiteorder(application, Vlan);
  				
  			//VLAN Ether Type dropdown
  				Vlanethertype_AddSiteorder(application, Vlanether);
  				
  			//Primary VLAN Text Field
  				primaryVlan_AddSiteorder(application, primaryVlan);
  				
  			//Primary Ether Vlan dropdown
  				primaryVlanethertype_AddSiteorder(application, primaryVlanether);
  				
  			}
  			
  		}
  	}
  			}	

  	 }

     
     
     public void technologyEPN1G_IVRefrenceAccess_AddSiteOrder(String application, String technology, String interfaceSpeed, String devicename, 
    		 String nonterminatepoinr, String Protected, String GCRolo, String Vlan, String Vlanether,
    		 String mappingmode, String portBased, String vlanBased) throws InterruptedException, IOException, DocumentException {
    	 
     	//Technology
  		if(technology.equalsIgnoreCase("null")) {
  			ExtentTestManager.getTest().log(LogStatus.FAIL, "Technology dropdown is a mandatory field and no values are provided"); 
  		}else {
  		
  			if(interfaceSpeed.equals("1GigE")) {	
  			
  		if(technology.equals("Actelis") || technology.equals("Atrica") || technology.equals("Overture") || technology.equals("Accedian-1G") || technology.equals("Cyan" ) || technology.equals("Alu"))	{
  			
  			click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
  			Thread.sleep(3000);
  			WebElement technologySelected = getwebelement(xml.getlocator("//locators/" + application + "/selectValueUnderTechnologyDropdown").replace("value", technology)); clickonTechnology(technologySelected, technology);
  			Thread.sleep(3000);
  			ExtentTestManager.getTest().log(LogStatus.PASS, technology + " is selected under technology dropdown");
  			
  			
  			
  			if(technology.equals("Actelis")) {	
  				
  			     Log.info("No additional fields displays");
  			}
  			

  			else if((technology.equals("Atrica"))) {
  				
  				//Non-Termination Point
  				nontermination_AddSiteorder(application, nonterminatepoinr);
  				
  				//Protected
  				protected_AddSiteOrder(application, Protected);
  				
  				//Device Name
  				devicename_AddSiteOrder(application, devicename);
  				
  				//Mapping Mode
  				addMappingMode(application, mappingmode);
  				
  				if(mappingmode.equalsIgnoreCase("port Based")) {
  					addtextFields_commonMethod(application, "Port Name", "portname_textField", portBased, xml);
  					
  				}
  				else if(mappingmode.equalsIgnoreCase("Vlan Based")) {
  					
  					addtextFields_commonMethod(application, "VLAN Id", "vlanid_textfield", vlanBased, xml);
  				}
  			}
  			
  			

  			else if(technology.equals("Overture")) {	
  			//Non- termination point checkbox
  				nontermination_AddSiteorder(application, nonterminatepoinr);
  				
  			//Protected checkbox	
  				protected_AddSiteOrder(application, Protected);
  				
  			//Device Name
  				devicename_AddSiteOrder(application, devicename);
  				
  			//GCR OLO Type dropdown
  				GCROLOType_AddSiteorder(application, GCRolo);
  				
  			//VLAN Text field
  				Vlan_AddSiteorder(application, Vlan);
  				
  			//VLAN Ether Type dropdown
  				Vlanethertype_AddSiteorder(application, Vlanether);
  				
  			}
  			
  			else if(technology.equals("Cyan")) {	
  			//Non- termination point checkbox
  				nontermination_AddSiteorder(application, nonterminatepoinr);
  			
  			}
  			
  			else if(technology.equals("Accedian-1G")) {
  				
  				//Non-Termination Point
  				nontermination_AddSiteorder(application, nonterminatepoinr);
  				
  				//Protected
  				protected_AddSiteOrder(application, Protected);
  				
  				//Device Name
  				devicename_AddSiteOrder(application, devicename);
  			}
  			
  			else if(technology.equals("Alu")) {
  				
  				//Device Name
  				devicename_AddSiteOrder(application, devicename);
  				
  				//Mappin Mode
  				addMappingMode(application, mappingmode);
  				
  				if(mappingmode.equalsIgnoreCase("port Based")) {
  					addtextFields_commonMethod(application, "Port Name", "portname_textField", portBased, xml);
  					
  				}
  				else if(mappingmode.equalsIgnoreCase("Vlan Based")) {
  					
  					addtextFields_commonMethod(application, "VLAN Id", "vlanid_textfield", vlanBased, xml);
  				}
  				
  			}
  		}
  	}
  			}	

  	 }
     
     
     
     public void technologyHubANdSpoke10G_AddSiteOrder(String application, String interfaceSpeed, String technology, String nonterminatepoinr, String Protected) throws InterruptedException, DocumentException { 
    	 
				if(technology.equals("Accedian"))	{
					
					click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
					Thread.sleep(3000);
					WebElement technologySelected = getwebelement(xml.getlocator("//locators/" + application + "/selectValueUnderTechnologyDropdown").replace("value", technology)); clickonTechnology(technologySelected, technology);
					Thread.sleep(3000);
					ExtentTestManager.getTest().log(LogStatus.PASS, technology + " is selected under technology dropdown");
						
						//Non- termination point	
								if(nonterminatepoinr.equalsIgnoreCase("yes")) {
									
									Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_nonterminationpoint")));
									Thread.sleep(3000);
									ExtentTestManager.getTest().log(LogStatus.PASS, "Non-termination point checkbox is selected");
								}else {
									Log.info("Non termination point checkbox is not selected as expected");
									ExtentTestManager.getTest().log(LogStatus.PASS, "Non-termination point chekbox is not selected");
								}
								
								
							//Protected	
								if(Protected.equalsIgnoreCase("yes")) {
									
									Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_protected")));
									Thread.sleep(3000);
									ExtentTestManager.getTest().log(LogStatus.PASS, "Protected checkbox is selected");
								}else {
									Log.info("Protected checkbox is not selecetd as expected");
									ExtentTestManager.getTest().log(LogStatus.PASS, "Protected checkbox is not selected");
								}
						}else {
							ExtentTestManager.getTest().log(LogStatus.FAIL, technology + "is not available under 'Technology' dropdown for '10GigE' interface speed");
						}
     }
     
     
     
     public void technologyEPN10G_AddSiteOrder(String application, String interfaceSpeed, String technology, String nonterminatepoinr, String Protected) throws InterruptedException, DocumentException {
    	 
				if(technology.equals("Accedian"))	{
					
					click_commonMethod(application, "Technology dropdown", "Addsiteorder_Technology", xml);
					Thread.sleep(3000);
					WebElement technologySelected = getwebelement(xml.getlocator("//locators/" + application + "/selectValueUnderTechnologyDropdown").replace("value", technology)); clickonTechnology(technologySelected, technology);
					Thread.sleep(3000);
					ExtentTestManager.getTest().log(LogStatus.PASS, technology + " is selected under technology dropdown");
						
						//Non- termination point	
								if(nonterminatepoinr.equalsIgnoreCase("yes")) {
									
									Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_nonterminationpoint")));
									Thread.sleep(3000);
									ExtentTestManager.getTest().log(LogStatus.PASS, "Non-termination point checkbox is selected");
								}else {
									Log.info("Non termination point checkbox is not selected as expected");
									ExtentTestManager.getTest().log(LogStatus.PASS, "Non-termination point chekbox is not selected");
								}
								
								
							//Protected	
								if(Protected.equalsIgnoreCase("yes")) {
									
									Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_protected")));
									Thread.sleep(3000);
									ExtentTestManager.getTest().log(LogStatus.PASS, "Protected checkbox is selected");
								}else {
									Log.info("Protected checkbox is not selecetd as expected");
									ExtentTestManager.getTest().log(LogStatus.PASS, "Protected checkbox is not selected");
								}
						}else {
							ExtentTestManager.getTest().log(LogStatus.FAIL, technology + "is not available under 'Technology' dropdown for '10GigE' interface speed");
						}
     }
     
     
     public void validatesiteOrderNumber_AddSiteOrder(String application) throws InterruptedException, DocumentException {
    	 
    		
  		// Site Order Number Field
  				boolean siteorderNmber = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_siteordernumbertextfield"))
  						.isDisplayed();
  				sa.assertTrue(siteorderNmber, " 'site order number' field is not displayed");
  				if(siteorderNmber) {
  					ExtentTestManager.getTest().log(LogStatus.PASS, " 'Site Order Number (Siebel Service ID)' text field is displaying under 'Add Site order' page as expected");
  				}else {
  					ExtentTestManager.getTest().log(LogStatus.FAIL, " 'site order number (Siebel Service ID)' text field is not displaying under 'Add Site order' page");
  				}
    	 
     }
     
     
     
     public void siteOrderNumber_AddSiteOrder(String application, String siteOrderNumber) throws InterruptedException, IOException, DocumentException {
    	 
    	 if(siteOrderNumber.equalsIgnoreCase("null")) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "'Site Order Number' field is a mandatory field and no values are provided");
			}else {
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_siteordernumbertextfield")), siteOrderNumber);
			Thread.sleep(3000);
			ExtentTestManager.getTest().log(LogStatus.PASS, siteOrderNumber+ " is entered under 'Site Order Number' field");
			}
    	 
     }
     
     public void validateIVReference_AddSiteorder(String application) throws InterruptedException, DocumentException {
    	 
 			boolean IVReference = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_IVreferencedropdown")).isDisplayed();
 			sa.assertTrue(IVReference, " 'IV reference' dropdown is not displayed");
 			if(IVReference) {
 				
 			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_IVreferencedropdown")));
 			List<WebElement> listofIVreference= getwebelements(xml.getlocator("//locators/" + application + "/ClassNameForDropdowns"));
 			
 		if(listofIVreference.size()>=1) {	
 			for (WebElement IVreferencetypes : listofIVreference) {

 						Log.info("list of IV References : " + IVreferencetypes.getText());
 						Log.info("list of IV References for AddSite order are: "+IVreferencetypes.getText());
 						ExtentTestManager.getTest().log(LogStatus.PASS,"list of IV References for AddSite order are: "+IVreferencetypes.getText());
 					
 			}
 					
 		}else {
 			Log.info("no values are available inside 'IV reference' dropdown for Add site order");
 			ExtentTestManager.getTest().log(LogStatus.FAIL,"no values are available inside 'IV reference' dropdown for Add site order");
 		}
 		
 	}else {
 		ExtentTestManager.getTest().log(LogStatus.FAIL, "Mandatory field 'IV Reference' dropdown is not available under 'Add Site ORder' page");
 	}
     }
     
     
     public void validateCircuitreference_AddSiteOrder(String application) throws InterruptedException, DocumentException {
    	 
    				boolean circuitReference = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_circuitReferenceField"))
    						.isDisplayed();
    				sa.assertTrue(circuitReference, "Circuit Reference field is not displayed");	
    				if(circuitReference) {
    					ExtentTestManager.getTest().log(LogStatus.PASS, "Mandatory field 'Circuit Reference' text field is displaying under 'Add Site order' page as expected");
    				}else {
    					ExtentTestManager.getTest().log(LogStatus.FAIL, "Mandatory field 'Circuit Reference' text field is not displaying under 'Add Site order' page");
    				}
     }
     
     public void circuitreference_AddSiteorder(String application, String circuitref) throws InterruptedException, IOException, DocumentException {
    	try { 
    	 if(circuitref.equalsIgnoreCase("null")) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "'Circuit Reference' field is a mandatory field and no values are provided");
			}else {
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_circuitReferenceField")), circuitref);
			Thread.sleep(3000);
			
			String actualvalue=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_circuitReferenceField")).getAttribute("value");
			ExtentTestManager.getTest().log(LogStatus.PASS, actualvalue+ " is entered under 'Circuit Reference' field");
			}
    	}catch(Exception e) {
    		e.printStackTrace();
    		ExtentTestManager.getTest().log(LogStatus.FAIL, "'Circuit reference' field is not displaying");
    	}
     }
     
     
     public void validateoffnet_AddSiteOrder(String application) throws InterruptedException, DocumentException {
    	 
    	//Offnet checkbox
    	 
			boolean offnet = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_offnetCheckbox"))
					.isDisplayed();
			sa.assertTrue(offnet, "Offnet field is not displayed");	
			boolean offnetcheckbox = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_offnetCheckbox")).isSelected();
			sa.assertFalse(offnetcheckbox, " Offnet checkbox is selected");
			
			if(offnet) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "'Offnet' text field is displaying under 'Add Site order' page as expected");
			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "'Offent' text field is not displaying under 'Add Site order' page");
			}
			
			if(offnetcheckbox) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Offnet' checkbox is selected by default");
			}else {
				ExtentTestManager.getTest().log(LogStatus.PASS, " 'Offnet' checkbox is not selected by default as expected");
			}
     }
     
     public void validateEPNoffnet_AddSiteOrder(String application) throws InterruptedException, DocumentException {
    	 
     	//EPN Offnet checkbox
 			boolean EPNoffnet = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_EPNoffnetCheckbox"))
 					.isDisplayed();
 			sa.assertTrue(EPNoffnet, " EPN Offnet checkbox is not displayed");	
 			boolean EPNoffnetcheckbox = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_EPNoffnetCheckbox")).isSelected();
 			sa.assertFalse(EPNoffnetcheckbox, "  EPN Offnet checkbox is selected");
 			
 			if(EPNoffnet) {
 				ExtentTestManager.getTest().log(LogStatus.PASS, "'EPN Offnet' text field is displaying under 'Add Site order' page as expected");
 			}else {
 				ExtentTestManager.getTest().log(LogStatus.FAIL, "'EPN Offnet' text field is not displaying under 'Add Site order' page");
 			}
 			
 			if(EPNoffnetcheckbox) {
 				ExtentTestManager.getTest().log(LogStatus.FAIL, " 'EPN Offnet' checkbox is selected by default");
 			}else {
 				ExtentTestManager.getTest().log(LogStatus.PASS, " 'EPN Offnet' checkbox is not selected by default as expected");
 			}
      }
     
     
     public void validateEPNEOSDH_AddSiteOrder(String application) throws InterruptedException, DocumentException {
    	 
      	//EPN EOSDH checkbox
  			boolean EPNEOSDH = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_EPNEOSDHCheckbox"))
  					.isDisplayed();
  			sa.assertTrue(EPNEOSDH, " EPN EOSDH checkbox is not displayed");	
  			boolean EPNEOSDHcheckbox = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_EPNEOSDHCheckbox")).isSelected();
  			sa.assertFalse(EPNEOSDHcheckbox, "  EPN EOSDH checkbox is selected");
  			
  			if(EPNEOSDH) {
  				ExtentTestManager.getTest().log(LogStatus.PASS, "'EPN EOSDH' text field is displaying under 'Add Site order' page as expected");
  			}else {
  				ExtentTestManager.getTest().log(LogStatus.FAIL, "'EPN EOSDH' text field is not displaying under 'Add Site order' page");
  			}
  			
  			if(EPNEOSDHcheckbox) {
  				ExtentTestManager.getTest().log(LogStatus.FAIL, " 'EPN EOSDH' checkbox is selected by default");
  			}else {
  				ExtentTestManager.getTest().log(LogStatus.PASS, " 'EPN EOSDH' checkbox is not selected by default");
  			}
       }
     
     
     public void EPNoffnet_AddSiteOrder(String application, String EPNoffnetSelection) throws InterruptedException, DocumentException {
    	 
    	 if(EPNoffnetSelection.equalsIgnoreCase("yes")) {

    		 Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_EPNoffnetCheckbox")));
    		 ExtentTestManager.getTest().log(LogStatus.PASS, " 'EPN Offnet' checkbox is selected as expected");
    	 }
    	 else if(EPNoffnetSelection.equalsIgnoreCase("no")) {
    		 
    		 ExtentTestManager.getTest().log(LogStatus.PASS, " 'EPN Offnet' checkbox is not selected as expected");
    	 }
     }
     
     
     public void EPNEOSDH_AddSiteOrder(String application, String EPNEOSDHSelection) throws InterruptedException, DocumentException {
    	 
    	 if(EPNEOSDHSelection.equalsIgnoreCase("yes")) {

    		 Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_EPNEOSDHCheckbox")));
    		 ExtentTestManager.getTest().log(LogStatus.PASS, " 'EPN EOSDH' checkbox is selected as expected");
    	 }
    	 else if(EPNEOSDHSelection.equalsIgnoreCase("no")) {
    		 
    		 ExtentTestManager.getTest().log(LogStatus.PASS, " 'EPN EOSDH' checkbox is not selected as expected");
    	 }
     }
     
     
     public void validatespokeId_AddSiteOrder(String application) throws InterruptedException, DocumentException {
    	 try {
			boolean spokeIdlabel = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_spokeIdField")).isDisplayed();
			if(spokeIdlabel) {
				boolean spokeIDvalue = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_spokeId"))
						.isDisplayed();
				if(spokeIDvalue) {
					ExtentTestManager.getTest().log(LogStatus.PASS, " 'Spoke Id' value is displaying as '0' by default as expected");
				}else {
					ExtentTestManager.getTest().log(LogStatus.FAIL," 'Spoke Id' is not displaying as expected" );
				}
			}
			else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Spoke Id' field is not displaying under 'Add Site Order' page");
			}
    	 }catch(Exception e) {
    		 e.printStackTrace();
    		 ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Spoke Id' field is not displaying under 'Add Site Order' page");
    	 }
			
     }
     
     
     public void OKbutton_AddSiteOrder(String application) throws InterruptedException, DocumentException {

    	 boolean ok=false;
    	try { 
    	ok = getwebelement(xml.getlocator("//locators/" + application + "/okbutton")).isDisplayed();
			sa.assertTrue(ok, "OK button is not displayed");
			if(ok) {
				ExtentTestManager.getTest().log(LogStatus.PASS, " 'OK' button is displaying as expected");
			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, " 'OK' button is not displaying");
			}
			
    	}catch(Exception e) {
    		ExtentTestManager.getTest().log(LogStatus.FAIL, " 'OK' button is not displaying");
    		Log.info(" 'OK' button is not displaying");
    	}
     }
     
     
     public void cancelbutton_AddSiteOrder(String application) throws InterruptedException, DocumentException {
    	 
    	 boolean cancel=false;
    	 try {
    		 cancel = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_cancel")).isDisplayed();
			sa.assertTrue(cancel, "Cancel button is not "
					+ "displayed");
			if(cancel) {
				ExtentTestManager.getTest().log(LogStatus.PASS, " 'Cancel' button is displaying as expected");
			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Cancel' button is not displaying");
			}
    	 }catch(Exception e) {
     		ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Cancel' button is not displaying");
     		Log.info(" 'Cancel' button is not displaying");
     	}
     }
     
     
     public void Ivrefrence_AddSiteOrder(String application, String iVReference) throws InterruptedException, DocumentException {
    	 
    	if(iVReference.equalsIgnoreCase("null")){
    		
    		ExtentTestManager.getTest().log(LogStatus.FAIL, " 'IV Reference' dropdown field is a mandatory field and no values has been passed");
    	}else {
    		
    		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_IVreferencedropdown")));
    		Thread.sleep(3000);
    		Clickon(getwebelement("//div[text()='" + iVReference +"']"));
    		ExtentTestManager.getTest().log(LogStatus.PASS, iVReference + " is selected under 'IV Reference' dropdown");
    	}
    	 
     }
     
     public void GCROLOType_AddSiteorder(String application, String gcrOlo) throws InterruptedException, DocumentException {
    	try { 
    	 if(gcrOlo.equalsIgnoreCase("null")){
     		
     		ExtentTestManager.getTest().log(LogStatus.PASS, "  No values has been selected under 'GCR OLO Type' dropdown field");
     	}else {
     		
     		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_GCRoloTypeDropdown")));
     		Thread.sleep(3000);
     		Clickon(getwebelement("//div[text()='" + gcrOlo +"']"));
     		
     		String actualvalue=getwebelement("//div[label[text()='GCR OLO Type']]//span").getText();
     		ExtentTestManager.getTest().log(LogStatus.PASS, actualvalue + " is selected under 'GCR OLO Type' dropdown");
     	}
    	}catch(NoSuchElementException e) {
    		e.printStackTrace();
    		ExtentTestManager.getTest().log(LogStatus.FAIL, " 'GCR OLO Type' dropdown is not available under 'Add Site Order' page");
    		Log.info(" 'GCR OLO Type' dropdown is not available under 'Add Site Order' page");
    	}catch(Exception ee) {
    		ee.printStackTrace();
    		ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to select value under 'GCR olo Dropdown'");
    		Log.info( " Not able to select value under 'GCR olo Dropdown'");
    	}
     }
     
     public void Vlanethertype_AddSiteorder(String application, String VlanEthertype) throws InterruptedException, DocumentException {
    try {	 
    	 if(VlanEthertype.equalsIgnoreCase("null")){
      		
      		ExtentTestManager.getTest().log(LogStatus.PASS, " No values has been selected under 'VLAN Ether Type' dropdown field");
      	}else {
      		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_VLANEtherTypeDropdown")));
      		Thread.sleep(3000);
      		Clickon(getwebelement("//div[label[text()='VLAN Ether Type']]//div[text()='"+ VlanEthertype +"']"));
      		
      		String actualvalue=getwebelement("//div[label[text()='VLAN Ether Type']]//span").getText();
      		ExtentTestManager.getTest().log(LogStatus.PASS, actualvalue + " is selected under 'VLAN Ether Type' dropdown");
      	}
    }catch(NoSuchElementException e){
    	e.printStackTrace();
    	ExtentTestManager.getTest().log(LogStatus.FAIL, " 'VLAN Ether Type' dropdown is not displaying under 'Add Site Oder' page");
    	Log.info(" 'VLAN Ether Type' dropdown is not displaying under 'Add Site Oder' page");
    }catch(Exception ee) {
    	ee.printStackTrace();
    	ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to selected value under 'VLAN Ether Type' dropdown");
    	Log.info(" Not able to selected value under 'VLAN Ether Type' dropdown");
    }
    	 
     }
     
     public void primaryVlanethertype_AddSiteorder(String application, String primaryVlanEthertype) throws InterruptedException, DocumentException {
    
    	 try {
    	 if(primaryVlanEthertype.equalsIgnoreCase("null")){
      		
      		ExtentTestManager.getTest().log(LogStatus.PASS, " No values has been selected under 'Primary VLAN Ether Type' dropdown field");
      	}else {
      		
      		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_primaryVLANEtherTypeDropdown")));
      		Thread.sleep(3000);
      		Clickon(getwebelement("//div[label[text()='Primary VLAN Ether Type']]//div[text()='"+ primaryVlanEthertype +"']"));
      		
      		String actualvalue=getwebelement("//div[label[text()='Primary VLAN Ether Type']]//span").getText();
      		ExtentTestManager.getTest().log(LogStatus.PASS, actualvalue + " is selected under 'Primary VLAN Ether Type' dropdown");
      	}
    	 }catch(NoSuchElementException e) {
    		 e.printStackTrace();
    		 ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Primary VLAN Ether type' dropdown is not displaying in 'Add Site Order' page");
    		 Log.info(" 'Primary VLA Ether type' dropdown is not displaying in 'Add Site Order' page");
    		 
    	 }catch(Exception ee) {
    		 ee.printStackTrace();
    		 ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to select value under 'Primary VLAN Ether type' dropdown");
    		 Log.info(" Not able to select value under 'Primary VLAN Ether type' dropdown");
    	 }
    	 
     }
     
     public void Vlan_AddSiteorder(String application, String vlan) throws InterruptedException, IOException, DocumentException {
    	try { 
    	 if(vlan.equalsIgnoreCase("null")) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "No values has been passed for 'VLAN' text field");
			}else {
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_VLAN")), vlan);
			Thread.sleep(3000);
			
			String actualvalue=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_VLAN")).getAttribute("value");
			ExtentTestManager.getTest().log(LogStatus.PASS, actualvalue+ " is entered under 'VLAN' text field");
			}
    	}catch(NoSuchElementException e) {
    		e.printStackTrace();
    		ExtentTestManager.getTest().log(LogStatus.FAIL, " 'VLAN' text field is not displying under 'Add Site Order'");
    		Log.info(" 'VLAN' text field is not displying under 'Add Site Order'");
    		
    	}catch(Exception ee) {
    		ee.printStackTrace();
    		ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to enter value under 'VLAN' text field");
    		Log.info( " Not able to enter value under 'VLAN' text field");
    	}
     }
     
     
     public void primaryVlan_AddSiteorder(String application, String primaryvlan) throws InterruptedException, IOException, DocumentException {
    	try { 
    	 if(primaryvlan.equalsIgnoreCase("null")) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "No values has been passed for 'Primary VLAN' text field");
			}else {
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_PrimaryVLAN")), primaryvlan);
			Thread.sleep(3000);
			
			String actualvalue=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_PrimaryVLAN")).getAttribute("value");
			ExtentTestManager.getTest().log(LogStatus.PASS, actualvalue+ " is entered under 'Primary VLAN' text field");
			}
    	}catch(NoSuchElementException e) {
    		e.printStackTrace();
    		ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Primary VLAN' text field is not displaying under 'Add Site Order' page");
    		Log.info(" 'Primary VLAN' text field is not displaying under 'Add Site Order' page");
    	}catch(Exception ee) {
    		ee.printStackTrace();
    		ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to enter value in 'Primary VLAN type' text field");
    		Log.info(" Not able to enter value in 'Primary VLAN type' text field");
    	}
     }
     

     public void addSiteOrderValues_HubAndSPoke(String application,  String interfaceSpeed,
  			String country, String city, String CSR_Name, String site,
  			String performReport, String ProactiveMonitor, String smartmonitor, String technology, String siteallias,
  			String VLANid, String DCAenabledsite, String cloudserviceprovider, String sitevalue, String remark,
  			String xngcityname, String xngcitycode,String devicename, String nonterminatepoinr, String Protected, String newcityselection, String existingcityselection,
  			String existingsiteselection, String newsiteselection,
  			String siteOrderNumber, String circuitref, String offnetSelection, String IVReference,
  			String GCRolo, String Vlan, String Vlanether, String primaryVlan, String primaryVlanether) throws InterruptedException, DocumentException, IOException { 
     	 
		siteOrderNumber_AddSiteOrder(application, siteOrderNumber);

		Countyr_AddSiteOrder(application, country);

		City_AddSiteorder(application, existingcityselection, city, newcityselection, xngcityname, xngcitycode, sitevalue, CSR_Name, existingsiteselection, newsiteselection);

//		Site_AddSiteOrder(application, existingsiteselection, sitevalue, newsiteselection, CSR_Name);

		performancereporting_AddSiteOrder(application, performReport);

		proactiveMonitoring_AddSiteOrder(application, ProactiveMonitor);

		smartsMonitoring_AddSiteOrder(application, smartmonitor);

	scrolltoend();
	Thread.sleep(3000);
		
		circuitreference_AddSiteorder(application, circuitref);

		SiteAlias_AddSiteOrder(application, siteallias);
		
		addCheckbox_commonMethod(application, "Addsiteorder_offnetCheckbox", "Offnet", offnetSelection, "no", xml);

		remark_AddSiteOrder(application, remark);

	if((interfaceSpeed.equals("1GigE")) &  (IVReference.equals("Primary")))	{
		
		Ivrefrence_AddSiteOrder(application, IVReference); 
		technologyHubAndSpoke1G_IVRefrencePrimary_AddSiteOrder(application, technology, interfaceSpeed, devicename, nonterminatepoinr, Protected);
		
	}
	
	if((interfaceSpeed.equals("1GigE")) &  (IVReference.equals("Access")))	{
		
		Ivrefrence_AddSiteOrder(application, IVReference); 
		technologyHubAndSpoke1G_IVRefrenceAccess_AddSiteOrder(application, technology, interfaceSpeed, devicename, nonterminatepoinr, Protected,
				 GCRolo,  Vlan,  Vlanether,  primaryVlan, primaryVlanether);
		
	}
	
	if(interfaceSpeed.equals("10GigE")){
		Ivrefrence_AddSiteOrder(application, IVReference);
		technologyHubANdSpoke10G_AddSiteOrder(application, interfaceSpeed, technology, nonterminatepoinr, Protected);

      }
	
	    ExtentTestManager.getTest().log(LogStatus.PASS, "Data has been entered for add site order");
	    Log.info("date has been entered for 'Add Site order'");
  	
	   scrolltoend();
	   Thread.sleep(3000);
		click_commonMethod(application, "OK", "okbutton", xml);

     
     }
     
     
     public void addSiteOrderValues_HubAndSPoke_OffnetSelected(String application,  String interfaceSpeed,
   			String country, String city, String CSR_Name, String site,
   			String performReport, String ProactiveMonitor, String smartmonitor, String technology, String siteallias,
   			String VLANid, String DCAenabledsite, String cloudserviceprovider, String sitevalue, String remark,
   			String xngcityname, String xngcitycode,String devicename, String nonterminatepoinr, String Protected, String newcityselection, String existingcityselection,
   			String existingsiteselection, String newsiteselection,
   			String siteOrderNumber, String circuitref, String offnetSelection, String IVReference,
   			String GCRolo, String Vlan, String Vlanether, String primaryVlan, String primaryVlanether) throws InterruptedException, DocumentException, IOException {
      	 
 		siteOrderNumber_AddSiteOrder(application, siteOrderNumber);

 		Countyr_AddSiteOrder(application, country);

 		City_AddSiteorder(application, existingcityselection, city, newcityselection, xngcityname, xngcitycode, sitevalue, CSR_Name, existingsiteselection, newsiteselection);

// 		Site_AddSiteOrder(application, existingsiteselection, sitevalue, newsiteselection, CSR_Name);

 		performancereporting_AddSiteOrder(application, performReport);

 		proactiveMonitoring_AddSiteOrder(application, ProactiveMonitor);

 		smartsMonitoring_AddSiteOrder(application, smartmonitor);

 	scrolltoend();
 	Thread.sleep(3000);
 	
 		circuitreference_AddSiteorder(application, circuitref);

 		SiteAlias_AddSiteOrder(application, siteallias);
 		
 		addCheckbox_commonMethod(application, "Addsiteorder_offnetCheckbox", "Offnet", offnetSelection, "no", xml);
 		
 		remark_AddSiteOrder(application, remark);

 	if((interfaceSpeed.equals("1GigE")) &  (IVReference.equals("Primary")))	{
 		
 		Ivrefrence_AddSiteOrder(application, IVReference); 
 		technologyHubAndSpoke1G_IVRefrencePrimary_OffnetSelected_AddSiteOrder(application, technology, interfaceSpeed, devicename, nonterminatepoinr, Protected);
 		
 	}
 	
 	if((interfaceSpeed.equals("1GigE")) &  (IVReference.equals("Access")))	{
 		
 		Ivrefrence_AddSiteOrder(application, IVReference); 
 		technologyHubAndSpoke1G_IVRefrenceAccess_offnetSelected_AddSiteOrder(application, technology, interfaceSpeed, devicename, nonterminatepoinr, Protected,
 				 GCRolo,  Vlan,  Vlanether,  primaryVlan, primaryVlanether);
 		
 	}
 	
 	if(interfaceSpeed.equals("10GigE")){
 		
 		Ivrefrence_AddSiteOrder(application, IVReference); 
 		
 		ExtentTestManager.getTest().log(LogStatus.FAIL, " If 'Offnet' is selected for '10GigE', 'No value displays under 'Technology' dropdown");

       }
 	
 	    ExtentTestManager.getTest().log(LogStatus.PASS, "Data has been entered for add site order");
   	
 	    scrolltoend();
 	    Thread.sleep(3000);
 		click_commonMethod(application, "OK", "okbutton", xml);

      
      }
     
     
     public void addSiteOrderValues_EPN(String application,  String interfaceSpeed,
   			String country, String city, String CSR_Name, String site,
   			String performReport, String ProactiveMonitor, String smartmonitor, String technology, String siteallias,
   			String VLANid, String DCAenabledsite, String cloudserviceprovider, String sitevalue, String remark,
   			String xngcityname, String xngcitycode,String devicename, String nonterminatepoinr, String Protected, String newcityselection, String existingcityselection,
   			String existingsiteselection, String newsiteselection,String siteOrderNumber, String circuitref, String offnetSelection, String IVReference,
   			String GCRolo, String Vlan, String Vlanether, String EPNoffnetSelection, String EPNEOSDHSelection,
   			String mappingmode, String portBased, String vlanBased) throws InterruptedException, DocumentException, IOException {
      	
    	 
 		siteOrderNumber_AddSiteOrder(application, siteOrderNumber);

 		Countyr_AddSiteOrder(application, country);

 		City_AddSiteorder(application, existingcityselection, city, newcityselection, xngcityname, xngcitycode, sitevalue, CSR_Name, existingsiteselection, newsiteselection);

// 		Site_AddSiteOrder(application, existingsiteselection, sitevalue, newsiteselection, CSR_Name);

 		performancereporting_AddSiteOrder(application, performReport);

 		proactiveMonitoring_AddSiteOrder(application, ProactiveMonitor);

 		smartsMonitoring_AddSiteOrder(application, smartmonitor);

 	scrolltoend();
 	Thread.sleep(3000);
 	
 		circuitreference_AddSiteorder(application, circuitref);

 		SiteAlias_AddSiteOrder(application, siteallias);
 		
 		EPNoffnet_AddSiteOrder(application, EPNoffnetSelection);
 		
 		if(interfaceSpeed.equals("1GigE")) {
 			EPNEOSDH_AddSiteOrder(application, EPNEOSDHSelection);
 		}
 		

 		remark_AddSiteOrder(application, remark);

 	if((interfaceSpeed.equals("1GigE")) &  (IVReference.equals("Primary")))	{
 		
 		Ivrefrence_AddSiteOrder(application, IVReference); 
 		technologyEPN1G_IVRefrencePrimary_AddSiteOrder(application, technology, interfaceSpeed, devicename, nonterminatepoinr, Protected);
 		
 	}
 	
 	if((interfaceSpeed.equals("1GigE")) &  (IVReference.equals("Access")))	{
 		
 		Ivrefrence_AddSiteOrder(application, IVReference); 
 		technologyEPN1G_IVRefrenceAccess_AddSiteOrder(application, technology, interfaceSpeed, devicename, nonterminatepoinr, Protected,
 				 GCRolo,  Vlan,  Vlanether, mappingmode, portBased, vlanBased);
 		
 	}
 	
 	if(interfaceSpeed.equals("10GigE")){
 		Ivrefrence_AddSiteOrder(application, IVReference);
 		technologyEPN10G_AddSiteOrder(application, interfaceSpeed, technology, nonterminatepoinr, Protected);

       }
 	
 	    ExtentTestManager.getTest().log(LogStatus.PASS, "Data has been entered for add site order");
   	
 	    scrolltoend();
 	   Thread.sleep(3000); 
 		click_commonMethod(application, "OK", "okbutton", xml);

      
      }

     
     public void device_nameFieldWarningMessage(String application) throws InterruptedException, DocumentException {
     	boolean name=false;
     	//Name field Error Message
     			try {
     				name = getwebelement(
     					xml.getlocator("//locators/" + application + "/AddCPEdevice_nameerrmsg")).isDisplayed();
     				Thread.sleep(3000);
     			sa.assertTrue(name, "name field warning message is not displayed ");
     			if(name) {
     			String nameErrMsg = getwebelement(
     					xml.getlocator("//locators/" + application + "/AddCPEdevice_nameerrmsg")).getText();
     			Log.info(
     					"Name field warning  message displayed as : " + nameErrMsg);
     			ExtentTestManager.getTest().log(LogStatus.PASS,
     					" validation message for 'Name' text field displayed as : " + nameErrMsg);
     			Log.info(" Name field warning message displayed as : " + nameErrMsg);
     			}
     			}catch(NoSuchElementException e) {
     				e.printStackTrace();
     				Log.info(" Name field warning message is not dipslaying");
     				ExtentTestManager.getTest().log(LogStatus.FAIL, " name field warning message is not displaying");
     			}catch(Exception ed) {
     				ed.printStackTrace();
     			}
     }
     
    public void device_vendorModelWarningMessage(String application) throws InterruptedException, DocumentException {
    	boolean vendorErr=false;
    	//Vendor/Model Error Message
    			try {
    				vendorErr = getwebelement(
    					xml.getlocator("//locators/" + application + "/AddCPEdevice_vendorErrmsg")).isDisplayed();
    			sa.assertTrue(vendorErr, "Vendor/Model warning message is not displayed ");
    			if(vendorErr) {
    			String vendorErrMsg = getwebelement(
    					xml.getlocator("//locators/" + application + "/AddCPEdevice_vendorErrmsg")).getText();
    			Log.info(
    					"Vendor/Model  message displayed as : " + vendorErrMsg);
    			ExtentTestManager.getTest().log(LogStatus.PASS,
    					" validation message for Vendor/Model field displayed as : " + vendorErrMsg);
    			Log.info("Vendor/Model warning message displayed as : " + vendorErrMsg);
    			}
    			}catch(NoSuchElementException e) {
    				e.printStackTrace();
    				Log.info("'Vendor/Model is required' warning message is not displaying");
    				ExtentTestManager.getTest().log(LogStatus.FAIL, "'Vendor/Model is required' warning message is not displaying");
    			}catch(Exception ed) {
    				ed.printStackTrace();
    				Log.info("'Vendor/Model is required' warning message is not displaying");
    				ExtentTestManager.getTest().log(LogStatus.FAIL,"'Vendor/Model is required' warning message is not displaying");
    			}
    }

   public void device_managementAddressWarningMessage(String application) throws InterruptedException, DocumentException {
	   
	   boolean mangadrsErr=false;
	   try {
		   mangadrsErr = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEdevice_managementAddresserrmsg")).isDisplayed();
			sa.assertTrue(mangadrsErr, "Management Addres warning message is not displayed ");
			if(mangadrsErr) {
			String mngadresErrMsg = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEdevice_managementAddresserrmsg")).getText();
			Log.info(
					"Management Addres  message displayed as : " + mngadresErrMsg);
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" validation message for Management Addres field displayed as : " + mngadresErrMsg);
			Log.info("Management Addres warning message displayed as : " + mngadresErrMsg);
			}
		 }catch(NoSuchElementException e) {
			 e.printStackTrace();
			 Log.info("management Address warning message is not found");
			 ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Management Address is required' warning message is not displaying");
		 }catch(Exception ed) {
			 ed.printStackTrace();
			 Log.info("management Address warning message is not found");
			 ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Management Address wa is required' warning message is not displaying");
		 }
   }

   public void device_powerAlarmWarningMessage(String application) throws InterruptedException, DocumentException {
	   boolean pwralrmErr=false;
	   try {
		   pwralrmErr = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEdevice_powerAlarmerrmsg")).isDisplayed();
			sa.assertTrue(pwralrmErr, "Power Alarm warning message is not displayed ");
			if(pwralrmErr) {
			String pwralarmErrMsg = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEdevice_powerAlarmerrmsg")).getText();
			Log.info(
					"Power Alarm  message displayed as : " + pwralarmErrMsg);
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" validation message for Power Alarm field displayed as : " + pwralarmErrMsg);
			Log.info("Power Alarm warning message displayed as : " + pwralarmErrMsg);
			}
		 }catch(NoSuchElementException e) {
			 e.printStackTrace();
			 Log.info("Power Alarm warning message is not dipslaying");
			 ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Power Alarm is required' warning message is not displaying");
		 }catch(Exception er) {
			 er.printStackTrace();
			 Log.info("Power Alarm warning message is not dipslaying");
			 ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Power Alarm is required' warning message is not displaying");
		 }
   }
   
   
   public void device_mediaSelectionWarningMessage(String application) throws InterruptedException, DocumentException {
	   boolean mediaErr=false;
	   try {
			mediaErr = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEdevice_mediaselectionerrmsg")).isDisplayed();
			sa.assertTrue(mediaErr, "Media Selection warning message is not displayed ");
			if(mediaErr) {
			String mediaselectionErrMsg = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEdevice_mediaselectionerrmsg")).getText();
			Log.info(
					"Media Selection  message displayed as : " + mediaselectionErrMsg);
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" validation message for Media Selection field displayed as : " + mediaselectionErrMsg);
			Log.info("Media Selection warning message displayed as : " + mediaselectionErrMsg);
			}
		 }catch(Exception e) {
			 e.printStackTrace();
			 Log.info("Media selection waning message is not displaying");
			 ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Media Selection is required' warning message is not displaying");
		 }
   }

   public void device_macAddressWarningMessage(String application) throws InterruptedException, DocumentException {
	   boolean macErr=false;
	   try {
		   macErr = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEdevice_macAdressErrmsg")).isDisplayed();
			sa.assertTrue(macErr, "MAC Address warning message is not displayed ");
			if(macErr) {
			String macadresErrMsg = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEdevice_macAdressErrmsg")).getText();
			Log.info(
					"MAC Address  message displayed as : " + macadresErrMsg);
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" validation message for MAC Address field displayed as : " + macadresErrMsg);
			Log.info("MAC Address warning message displayed as : " + macadresErrMsg);
			}else{
				ExtentTestManager.getTest().log(LogStatus.FAIL, " 'MAC Address' warning message is not displaying under 'Add cpe device' page");
			}
		 }catch(Exception e) {
			 e.printStackTrace();
			 Log.info("Mac Adress warning message is not dipslaying");
			 ExtentTestManager.getTest().log(LogStatus.FAIL, " 'MAC Address is required' warning message is not displaying");
		 } 
   }
   
   
   public void device_serialNumberWarningMessage(String application) throws InterruptedException, DocumentException {
	 //Serial Number Error Message
	   boolean serialNumberErr=false;
	   
	   try {
		serialNumberErr = getwebelement(
				xml.getlocator("//locators/" + application + "/AddCPEdevice_serialNumberErrmsg")).isDisplayed();
		sa.assertTrue(serialNumberErr, "Serial Number warning message is not displayed ");
	if(serialNumberErr)	{
		Log.info(" 'Serial number; warning message is dipslaying as expected");
		String serialnumberErrMsg = getwebelement(
				xml.getlocator("//locators/" + application + "/AddCPEdevice_serialNumberErrmsg")).getText();
		Log.info(
				"Serial Number  message displayed as : " + serialnumberErrMsg);
		ExtentTestManager.getTest().log(LogStatus.PASS,
				" validation message for Serial Number field displayed as : " + serialnumberErrMsg);
		Log.info("Serial Number warning message displayed as : " + serialnumberErrMsg);
	}else {
		Log.info("Serial Number warning message is not dipslaying");
		ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Serial Number is required' validation mesage is not displaying");
	}
	   }catch(Exception e) {
		   e.printStackTrace();
		   Log.info("Serial Number Warning message is not diplsying");
		   ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Serial Number is required' warning message is not displaying");
	   }
   }
   
   
   public void device_hexaSerialNumberWarningMessage(String application) throws InterruptedException, DocumentException {
		 //Serial Number Error Message
		   boolean HexaserialNumberErr=false;
		   
		   try {
			   HexaserialNumberErr = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEdevice_hexaSerialNumnerErrmsg")).isDisplayed();
			sa.assertTrue(HexaserialNumberErr, "Hexa Serial Number warning message is not displayed ");
			if(HexaserialNumberErr)	{
				Log.info(" 'Hexa Serial number' warning message is dipslaying as expected");
				String hexaserialnumberErrMsg = getwebelement(
						xml.getlocator("//locators/" + application + "/AddCPEdevice_hexaSerialNumnerErrmsg")).getText();
				Log.info(
						"Hexa Serial Number  message displayed as : " + hexaserialnumberErrMsg);
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" validation message for Hexa Serial Number field displayed as : " + hexaserialnumberErrMsg);
				Log.info("Hexa Serial Number warning message displayed as : " + hexaserialnumberErrMsg);
			}else {
				Log.info("Hexa Serial Number warning message is not dipslaying");
				ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Hexa Serial Number' validation mesage is not displaying");
			}
		   }catch(Exception e) {
			   e.printStackTrace();
			   Log.info(" 'Serial Number is required' Warning message is not diplsying");
			   ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Hexa Serial Number is required' warning message is not displaying");
		   }
	   }
   
   
   public void device_countrywarningMessage(String application) throws InterruptedException, DocumentException {
		
		//Country Error Message
	   boolean countryErr=false;
	   try {
			countryErr = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEdevice_countryErrmsg")).isDisplayed();
			sa.assertTrue(countryErr, "Country warning message is not displayed ");
			
		if(countryErr) {
			Log.info("country warning message is displaying as expected");
			String countryErrMsg = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEdevice_countryErrmsg")).getText();
			Log.info(
					"Country  message displayed as : " + countryErrMsg);
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" validation message for Country field displayed as : " + countryErrMsg);
			Log.info("Country warning message displayed as : " + countryErrMsg);	
		}else {
			Log.info("Country warning message is not displaying");
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Validation message for Country dropdown is not displaying");
		}
	   }catch(Exception e) {
		   e.printStackTrace();
		   Log.info(" 'Country is required' warning message is not displaying");
		   ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Country is required' warning message is not displaying");
	   }
   }


   public void device_nameField(String application, String cpename, String expectedDeviceNameFieldAutopopulatedValue) {
	   boolean name=false;
		try { 
		 name=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_Name")).isDisplayed();
		 sa.assertTrue(name, "Name text field is not available under create device for Equipment");
		
		 if(name) {
	          if(cpename.equalsIgnoreCase("null")) {
	        	  Log.info("No values has been assed for 'Name' text field");
					ExtentTestManager.getTest().log(LogStatus.FAIL, "No values has been passed for Mandatory 'Name' field under 'Add CPE Device' page");
				}
				
			   else {
				   String deviceNameActualPopulatedvalue=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_Name")).getAttribute("value");
				   sa.assertEquals(deviceNameActualPopulatedvalue, expectedDeviceNameFieldAutopopulatedValue, "Device Name field Auto Populated value is not displaying as expected");
				   ExtentTestManager.getTest().log(LogStatus.PASS, " Under 'Name' text field, value displaying by default is: "+deviceNameActualPopulatedvalue);
				   getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_Name")).clear();
				   Thread.sleep(3000);
					SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_Name")), cpename);
					Thread.sleep(3000);
					ExtentTestManager.getTest().log(LogStatus.PASS, cpename + " is the value passed for Mandatory 'Cpe name' text field");
					}
		 		  }else {
		 			  ExtentTestManager.getTest().log(LogStatus.FAIL, " Name text field is not displaying under 'Add CPE Device' page");
		 		  }
				}catch(NoSuchElementException e) {
					e.printStackTrace();
					ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Name' text field is not available");
				}catch(Exception err) {
					err.printStackTrace();
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Not able to enter value inside the 'Name' field");
				}
		
   }
   
   
   public void device_vendorModel(String application, String[] Vender, String vender) throws InterruptedException, DocumentException {
	   boolean vendor=false;
		try {	
			vendor=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_vender")).isDisplayed();
			sa.assertTrue(vendor, "Vender/Model dropdown is not available");
				
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_vender")));
				
		  try {
		  List<WebElement> listofvender = getwebelements(xml.getlocator("//locators/" + application + "/ClassNameForDropdowns"));
				
				if(listofvender.size()>0) {
		
				for (WebElement vendertypes : listofvender) {

					boolean match = false;
					for (int i = 0; i < Vender.length; i++) {
						if (vendertypes.getText().equals(Vender[i])) {
							match = true;
							Log.info("list of vendor under add devices are : " + vendertypes.getText());
							ExtentTestManager.getTest().log(LogStatus.PASS,"The list of vender/Model under Add device are: "+vendertypes.getText());
							Log.info("list of vendor under add devices are : " + vendertypes.getText());

						}
						}
					sa.assertTrue(match);
					}
					
				}else {
					Log.info("dropdown value inside Vender/Model is empty");
					ExtentTestManager.getTest().log(LogStatus.FAIL, "No values available inside Vender/Model dropdown for adding devices");
				}
				
		  }catch(Exception e) {
			  e.printStackTrace();
			  ExtentTestManager.getTest().log(LogStatus.FAIL, "Failure at vendor dropdown");
	 	  }
		  
		  
		  //Entering value inside Vendor/Model dropdown
		  try {
				if(vender.equalsIgnoreCase("null")) {
					
					Log.info("No values has been passed for Mandatory 'Vendor/Model' dropdown for adding device");
					ExtentTestManager.getTest().log(LogStatus.FAIL, "No values has been passed for Mandatory 'Vendor/Model' dropdown for adding device");
					
				}
			else {	
			Clickon(getwebelement("//div[label[text()='Vendor/Model']]//div[text()='"+vender +"']"));
			ExtentTestManager.getTest().log(LogStatus.PASS, vender + " is the value passed for Mandatory 'Vendor/Model' dropdown for adding device");
			Log.info(vender+" is the value passed for Mandatory 'Vendor/Model' dropdown for adding device");	
			}
				}catch(Exception e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, "FAilure at Vender/model dropdown. It does not have the value provided as input"
						+ " Value provided is: "+ vender);
			}
		  
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			Log.info("vendor/Model dropdown is not dipslaying under 'Add CPE device' page");
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Vendor/Model' dropdown is not displayind in 'Add CPE Device' page");
		}
   }
   
  
   	public void device_snmPro(String application, String snmproValueToBeChanged) {
   		boolean sNmpro=false;
   		try {
   			
   		  sNmpro=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_snmpro")).isDisplayed();
   		  
   		 if(sNmpro) { 
   			 ExtentTestManager.getTest().log(LogStatus.PASS, " ' Snmpro' field is displaying in 'Add CPE Device' page as expected");
   			 Log.info("Smpro text field is displaying as expected");
   			 
   			  boolean actualValue_snmpro=getwebelement(xml.getlocator("//locators/" + application + "/CPEdevice_snmpro_autoPopulatedValue")).isDisplayed();
   			  if(actualValue_snmpro) {
   				  ExtentTestManager.getTest().log(LogStatus.PASS, " 'Snmpro' field value is displaying as expected."
   				  		+ " It is displaying as: "+getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_snmpro")).getAttribute("value"));
   				  
   			  }else {
   				  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Snmpro' value is not displaying as expected."
   				  		+ " It is displaying as: "+getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_snmpro")).getAttribute("value"));
   			  }
   			  
   			  if(snmproValueToBeChanged.equalsIgnoreCase("null")) {
   				 Log.info("No changes has been made to 'Snmpro' field"); 
   				 ExtentTestManager.getTest().log(LogStatus.PASS, " 'Snmpro' field value is not changed");
   				 Log.info(" 'Snmpro' field is displaying as: "+getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_snmpro")).getAttribute("value"));
   			  }else {
   				getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_snmpro")).clear();
   				Thread.sleep(3000);
   				
   				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_snmpro")), snmproValueToBeChanged);
   				Thread.sleep(3000);
   				
   				ExtentTestManager.getTest().log(LogStatus.PASS, " 'Snmro' field value has been changes "
   						+ "And it is displaying as: "+ getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_snmpro")).getAttribute("value"));
   			  }
   			  
   		 }else {
   			 ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Snmpro' field is not available in 'Add CPE Device' page");
   		 }
   		}catch(NoSuchElementException e) {
   			e.printStackTrace();
   			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Snm pro' field is not available ");
   		}catch(Exception err) {
   			err.printStackTrace();
   			ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to enter value in 'Snm pro' field");
   		}
   			
   	}
   	
   	
   	public void device_mepID(String application, String Mepid) {
   		
   		String mepValue="null";
   		boolean mepid=false;
   		try {
   			mepid=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_mepid")).isDisplayed();
   			ExtentTestManager.getTest().log(LogStatus.PASS, " ' Snmpro' field is displaying in 'Add CPE Device' page as expected");
   			
   				sa.assertTrue(mepid, "Mepid field under 'Add CPE device' page is not available");
   				
   				if(mepid) {
   					
   					Log.info("MEP Id  text field is displaying as expected");
   						
   						mepValue=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_mepid")).getAttribute("value");
   						if(mepValue.equalsIgnoreCase("null")) {
   							ExtentTestManager.getTest().log(LogStatus.FAIL, " No values are displaying under 'MEP ID' field. It should be auto populated by default");
   							Log.info(" No values are displaying under 'MEP ID' field. It should be auto populated by default");
   						}else {
   							ExtentTestManager.getTest().log(LogStatus.PASS, " MEP ID field is auto populated and it is displaying as : "+mepValue);
   							Log.info( " MEP ID field is auto populated and it is displaying as : "+mepValue);
   						}
   						
   				}	
   			}catch(NoSuchElementException e) {
   				e.printStackTrace();
   				ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Mep Id' field is not available");
   			}catch(Exception err) {
   				err.printStackTrace();
   				ExtentTestManager.getTest().log(LogStatus.FAIL, "Not able to enter value inside the 'Mep Id' field");
   			}
   	}
   	
   	
   	public void device_powerAlarm(String application, String[] powerAlarm, String poweralarm ) throws InterruptedException, DocumentException {
   		
   		addDropdownValues_commonMethod(application, "Power Alarm", "AddCPEdevice_poweralarm", poweralarm, xml);
   		
   
   	}
   	
   	
   	public void device_mediaSelection(String application, String Mediaselection[], String mediaSelection) throws InterruptedException, 
   	DocumentException {
   		
   		addDropdownValues_commonMethod(application, "Media Selection", "AddCPEdevice_mediaselection", mediaSelection, xml);
   		
   	}


   	public void device_linklostForwarding(String application, String linkLostForwarding, String state) throws InterruptedException, DocumentException {
   	
   	 boolean linklostenable=false;	
   	 boolean linklost=false;
	    try {
	    	linklost=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_linklostforowarding")).isDisplayed();
			sa.assertTrue(linklost, "Link lost Forwarding checkbox under add device is not available");
	    if(linklost) {
	    	ExtentTestManager.getTest().log(LogStatus.PASS, " 'Link lost Forwarding' checkbox is displaying under 'Add CPE device' page as expected");
	    //Find whether it enabled or disabled
	    	linklostenable=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_linklostforowarding")).isEnabled();
	    	if(state.equalsIgnoreCase("disabled")){
	    		if(linklostenable) {
	    			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Link lost Forwarding' checkbox is enabled under 'Add CPE device' page");
	    		}else {
	    			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Link lost Forwarding' checkbox is disabled under 'Add CPE device' page");
	    		}
	    	}
	    	else if(state.equalsIgnoreCase("enabled")) {
	    		if(linklostenable) {
	    			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Link Lost Forwarding' checkbox is enabled under 'Add CPE device' page");
	    			
	    			//select the checkbox as per input	
	    			boolean linklostSelection=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_linklostforowarding")).isSelected();
    				if(linklostSelection) {
    					ExtentTestManager.getTest().log(LogStatus.PASS, " 'link lostforwarding' is selected by default as expected");
    				
    				//click on link lost checkbox	
    					if(linkLostForwarding.equalsIgnoreCase("Yes")) {
    	    				
    	    				ExtentTestManager.getTest().log(LogStatus.PASS, " No changes made for 'Link lost Forwarding' checkbox");
    	    			}else{
    	    				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_linklostforowarding")));
    	    				Thread.sleep(3000);
    	    				ExtentTestManager.getTest().log(LogStatus.PASS, " Link Lost Forwarding is unselected as expected");
    	    				Log.info(" Link Lost Forwarding is unselected as expected");
    	    			}
    				}else {
    					ExtentTestManager.getTest().log(LogStatus.FAIL, " 'link lostforwarding' is not selected by default");
    					Log.info(" 'link lostforwarding' is not selected by default");
    				}
	    			
	    			
	    		}else {
	    			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Link lost Forwarding' checkbox is disabled under 'Add CPE device' page");
	    		}

	    
	    } 	
	    }else {
	    	ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Link Lost Forwarding' checkbox is not dipslaying under 'Add CPE device' page");
	    	Log.info(" 'Link Lost Forwarding' checkbox is not displaying under 'Add CPE device' page");
	    }
	    }catch(NoSuchElementException e) {
	    	e.printStackTrace();
	    	ExtentTestManager.getTest().log(LogStatus.FAIL, " Link LOst Forwarding checkbox is not displaying in 'Add CPE Device' page");
	    }
   	}


   	public void device_managementAddress(String application, String existingmanagementAddress, String newmanagementAddress, String managementAddress) throws InterruptedException, DocumentException { 
   		boolean managementaddresdropdown=false;
   		boolean manageAddresstextField=false;
   		String manageAddresspopulatedValue="null";
   		
   		if((existingmanagementAddress.equalsIgnoreCase("Yes")) & (newmanagementAddress.equalsIgnoreCase("No")))
   		{
   			try {
   				managementaddresdropdown=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_manageaddressdropdown")).isDisplayed();
   				sa.assertTrue(managementaddresdropdown, "Management Address dropdown under add device is not available");
   				
   				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_getSubnetbutton")));
   				Thread.sleep(5000);
   				manageAddresspopulatedValue=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_manageaddressdropdown")).getAttribute("value");
   				if(manageAddresspopulatedValue.equalsIgnoreCase("null")) {
   					ExtentTestManager.getTest().log(LogStatus.FAIL, " No values gets populates in 'Manage Address' dropdown, on clicking 'get Subnets' button");
   				}else {
   					ExtentTestManager.getTest().log(LogStatus.PASS, " Values displaying under 'Manage Addres' dropodwn is : "+ manageAddresspopulatedValue);
   				}
   				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_manageaddressdropdown")));
   				Thread.sleep(3000);
   			}catch(Exception e) {
   				e.printStackTrace();
   				ExtentTestManager.getTest().log(LogStatus.FAIL, " Management Address dropdown is not available in 'Add CPE Device' page");
   			}
   			
   		}
   		
   		
   		else if((existingmanagementAddress.equalsIgnoreCase("No")) & (newmanagementAddress.equalsIgnoreCase("Yes"))) {
   			
   			click_commonMethod(application, "change", "changeButton_managementAddress", xml);	//click on change button for getting management address text field
				Thread.sleep(1000);
   		try {	
   			manageAddresstextField=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_manageaddress")).isDisplayed();
   			sa.assertTrue(manageAddresstextField, "Manage Address text Field is not displaying in 'Add CPE Device' page");
   			if(manageAddresstextField) {
   					if(managementAddress.equalsIgnoreCase("null")) {
   						ExtentTestManager.getTest().log(LogStatus.FAIL, "No values has been passed for Mandatory field 'Manage Address' for adding device");
   					}else {
   						
   				
   				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_manageaddress")),
   						managementAddress); 
   				Thread.sleep(3000);
   				ExtentTestManager.getTest().log(LogStatus.PASS, managementAddress + " is the value passed for Mandatory 'Management Address' field in 'Add CPE Device' page");
   					}
   			}else {
   				ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Management Address' text field is not dipslaying under 'Add CPE device' page");
   			}
   				
   		}catch(NoSuchElementException e) {
   			e.printStackTrace();
   			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Manage Address' text field is not available in 'Add CPE Device' page");
   		}catch(Exception err) {
   			err.printStackTrace();
   			ExtentTestManager.getTest().log(LogStatus.FAIL, "Not able to enter value inside the 'Manage Address' field");
   		}
   	}
   	}


public void device_MAcaddress(String application, String macAdressInput) {
   		
   		boolean macAdres=false;
   		try {
   			macAdres=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_macaddress")).isDisplayed();
   				sa.assertTrue(macAdres, "Mepid field under 'Add CPE device' page is not available");
   				
   				if(macAdres) {
   					ExtentTestManager.getTest().log(LogStatus.PASS, " ' MAC Address' field is displaying in 'Add CPE Device' page as expected");
   					Log.info(" 'MAC Address'  text field is displaying as expected");
   					
   					if(macAdressInput.equalsIgnoreCase("null")) {
   						ExtentTestManager.getTest().log(LogStatus.FAIL, "No values has been passed for 'MAC Address' text field for adding device");
   						Log.info("No values has been passed for 'MAC Address' mandaotyr field");
   						
   					}else {
   						
   						SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_macaddress")), macAdressInput);
   						Thread.sleep(3000);
   						
   						ExtentTestManager.getTest().log(LogStatus.PASS, macAdressInput+ " is entered under 'MAc Address' text field" );
   						Log.info(macAdressInput+ " is entered under 'MAc Address' text field");
   						
   					}
   				}	
   			}catch(NoSuchElementException e) {
   				e.printStackTrace();
   				ExtentTestManager.getTest().log(LogStatus.FAIL, " 'MAC Address' text field is not available");
   			}catch(Exception err) {
   				err.printStackTrace();
   				ExtentTestManager.getTest().log(LogStatus.FAIL, "Not able to enter value inside the 'MAC Address' text field");
   			}
   	}


	public void device_okbutton(String application) throws InterruptedException, DocumentException {
		
	//OK button
	    boolean Ok=false;
	    try {
			Ok=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_OKbutton")).isDisplayed();
			sa.assertTrue(Ok, "OK button under add device is not available");
			
			if(Ok) {
				Log.info(" 'OK' button is displaying under 'Add CPE deivce' page as expected");
				ExtentTestManager.getTest().log(LogStatus.PASS, " 'OK' button is displaying under 'Add CPE deivce' page as expected");
			}else {
				Log.info(" 'OK' button is not displaying under 'Add CPE device' page");
				ExtentTestManager.getTest().log(LogStatus.FAIL, " 'OK' button is not displaying under 'Add CPE device' page");
			}
	    }catch(NoSuchElementException e) {
	    	e.printStackTrace();
	    	ExtentTestManager.getTest().log(LogStatus.FAIL, " OK button is not available in 'Add CPE Device' page");
	    }
	    
	}
	
	
	public void device_cancelButton(String application) throws InterruptedException, DocumentException {
		
		 //Cancel button
	    boolean cancel=false;
	    try {
	    cancel=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_cancelbutton")).isDisplayed();
			sa.assertTrue(cancel, "cancel button under add device is not available");
			
			if(cancel) {
				Log.info(" 'Cancel' button is displaying under 'Add CPE deivce' page as expected");
				ExtentTestManager.getTest().log(LogStatus.PASS, " 'Cancel' button is displaying under 'Add CPE deivce' page as expected");
			}else {
				Log.info(" 'Cancel' button is not displaying under 'Add CPE device' page");
				ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Cancel' button is not displaying under 'Add CPE device' page");
			}
	    }catch(NoSuchElementException e) {
	    	e.printStackTrace();
	    	ExtentTestManager.getTest().log(LogStatus.FAIL, " Cancel button is not available in 'Add CPE Device' page");
	    }
	}
	
	
	public void device_serialNumber(String application, String serialNumber) throws InterruptedException, IOException, DocumentException {
		
		 //Serial Number
		   boolean serialNmber=false;
		   try {
			   serialNmber=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_serialnumber")).isDisplayed();
			   sa.assertTrue(serialNmber, "Serial Number is not available in 'Add CPE Device' page");
			 if(serialNmber) {  
			   if(serialNumber.equalsIgnoreCase("null")) {
				   ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Serial Number' is a mandatory field. no values has been passed as an input");
			   }else {
				   
				   SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_serialnumber")), serialNumber);
				   Thread.sleep(3000);
				   String SNactualValue=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_serialnumber")).getAttribute("value");
				   ExtentTestManager.getTest().log(LogStatus.PASS, SNactualValue + " is the value entered in 'Serial number' field in 'Add CPE Device' page");
			   }
			 }  
		   }catch(NoSuchElementException e) {
			   e.printStackTrace();
			   Log.info(" 'Serial Number' text field is not displaying");
			   ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Serial Number' text field is not dipslaying under 'Add CPE device' page");
		   }catch(Exception er) {
			   er.printStackTrace();
			   Log.info("not able to enter value under 'Serial number' textfield");
			   ExtentTestManager.getTest().log(LogStatus.FAIL, "Not able to enter value under 'Serial Number' text field");
		   }
	}
	
	
	public void device_country(String application, String country) throws InterruptedException, DocumentException, IOException {
		
		selectValueInsideDropdown(application, "countryDropdown_selectTag" , "Coutry", country, xml);
		
	}
	

	

	public void deviceCreatoin_Overture(String application, String cpename, String vender, String snmpro,
			String managementAddress, String Mepid, String poweralarm, String MediaselectionActualValue, String Macaddress,
			String serialNumber, String hexaSerialnumber, String linkLostForwarding, String country, String City,
			String Site, String Premise, String newmanagementAddress, String existingmanagementAddress, String manageaddressdropdownvalue,
			String existingcityselectionmode, String newcityselectionmode, String cityname, String citycode, String existingsiteselectionmode, String newsiteselectionmode,
			String sitename, String sitecode, String existingpremiseselectionmode, String newpremiseselectionmode, String premisename, String premisecode) throws InterruptedException, DocumentException, IOException {
	
	try {	
		String linklostForwardingcheckboxstate="enabled"; 
		
		String[] Vender= {"Overture ISG-26", "Overture ISG-26R", "Overture ISG-26S", "Overture ISG140", "Overture ISG180", "Overture ISG6000"};
		
		String[] powerAlarm= {"AC", "DC"};
		
		String[] MediaSelectionExpectedValue= {"SFP-A with SFP-B","RJ45-A with SFP-B"};	
		
		String expectedDeviceNameFieldAutopopulatedValue="<Device>.lanlink.dcn.colt.net";
		
	scrolltoend();
	Thread.sleep(3000);
	
		click_commonMethod(application, "OK", "obutton_spanTag", xml);
		Thread.sleep(3000);
		
		//Country Error Message
		device_countrywarningMessage(application);
		
		//Media Selection Error Message
//		device_mediaSelectionWarningMessage(application);
		
	ScrolltoElement(getwebelement("//label[text()='Name']"));
	Thread.sleep(3000);
	
		
		// Vendor/Model Error Message
		device_vendorModelWarningMessage(application);

		// Management Address Error Message
		device_managementAddressWarningMessage(application);

		// Power Alarm Error Message
		device_powerAlarmWarningMessage(application);
			
		//MAC Address Error Message
		device_macAddressWarningMessage(application);

		//Vendor/Model
		device_vendorModel(application, Vender, vender);      
	
		//Snmpro
		device_snmPro(application, snmpro);
		
		//Management Address dropdown
		device_managementAddress(application, existingmanagementAddress, newmanagementAddress, managementAddress);
		
		//MEP Id
		device_mepID(application, Mepid);
	
		//Power Alarm	
		device_powerAlarm(application, powerAlarm, poweralarm);
		
		//MAC Address
		device_MAcaddress(application, Macaddress);
		
		//Media Selection
				device_mediaSelection(application, MediaSelectionExpectedValue, MediaselectionActualValue);
	
	scrolltoend();
	Thread.sleep(3000);
	
		
	    //Link lost Forwarding
		device_linklostForwarding(application, linkLostForwarding, linklostForwardingcheckboxstate);
	    
	
		//Country
		device_country(application, country);
	

		//City		
		if(existingcityselectionmode.equalsIgnoreCase("no") & newcityselectionmode.equalsIgnoreCase("yes")) {
			addCityToggleButton(application);
		   //New City	
			newcity(application, newcityselectionmode, cityname, citycode);
		      // New Site
			newSite(application, newsiteselectionmode, sitename, sitecode);
			  //New Premise	
			newPremise(application, newpremiseselectionmode, premisename, premisecode);
				
		}	
			
		else if(existingcityselectionmode.equalsIgnoreCase("yes") & newcityselectionmode.equalsIgnoreCase("no")) {
		//Existing City		
		   existingCity(application, City);
			
			//Site
			  if(existingsiteselectionmode.equalsIgnoreCase("yes") & newsiteselectionmode.equalsIgnoreCase("no")) {
				//Existing Site 
				  existingSite(application, Site);
				  
				   //Premise
				  if(existingpremiseselectionmode.equalsIgnoreCase("yes") & newpremiseselectionmode.equalsIgnoreCase("no")) {
					  existingPremise(application, Premise);
				  
		          	 }
				  else if(existingpremiseselectionmode.equalsIgnoreCase("no") & newpremiseselectionmode.equalsIgnoreCase("yes")) {
					  //New Premise
					    addPremiseTogglebutton(application);
					    newPremise_clickOnPremisetoggleButton(application, newpremiseselectionmode, premisename, premisecode);
				  } 
	         	}
  		
		  else if(existingsiteselectionmode.equalsIgnoreCase("no") & newsiteselectionmode.equalsIgnoreCase("yes")) {
			  	//New Site 
			  	addSiteToggleButton(application);
			  	newSite_ClickOnSiteTogglebutton(application, newsiteselectionmode, sitename, sitecode); 
			  	
			  	//New Premise
			  	newPremise_clickonSiteToggleButton(application, newpremiseselectionmode, premisename, premisecode);
			  }
		}
		
		 //OK button
		device_okbutton(application);
		
		//cancel button
		device_cancelButton(application);
		
		scrolltoend();
		Thread.sleep(1000);
		click_commonMethod(application, "OK", "AddCPEdevice_OKbutton", xml);
		
		scrollToTop();
		Thread.sleep(1000);
		
		
		warningMessage_commonMethod(application, "warningmEssage1_devicename", "Device Name", xml);
		warningMessage_commonMethod(application, "warningmEssage2_devicename", "Device Name", xml);
		warningMessage_commonMethod(application, "warningmEssage3_devicename", "Device Name", xml);
		
		//Name
		device_nameField(application, cpename, expectedDeviceNameFieldAutopopulatedValue);
		
		scrolltoend();
		Thread.sleep(1000);
		click_commonMethod(application, "OK", "AddCPEdevice_OKbutton", xml);
		
		sa.assertAll();
		
	}catch(AssertionError e) {
		
		e.printStackTrace();
		//ExtentTestManager.getTest().log(LogStatus.FAIL, "FAiled while verify the fields for Add CPE device");
		
	}
	ExtentTestManager.getTest().log(LogStatus.INFO, "Input data has been passed for creating device");
	Thread.sleep(3000);

	}
	
	
	public void deviceCreatoin_Accedian(String application, String cpename, String vender, String snmpro,
			String managementAddress, String Mepid, String poweralarm, String MediaselectionActualValue, String Macaddress,
			String serialNumber, String hexaSerialnumber, String linkLostForwarding, String country, String City,
			String Site, String Premise, String newmanagementAddress, String existingmanagementAddress, String manageaddressdropdownvalue,
			String existingcityselectionmode, String newcityselectionmode, String cityname, String citycode, String existingsiteselectionmode, String newsiteselectionmode,
			String sitename, String sitecode, String existingpremiseselectionmode, String newpremiseselectionmode, String premisename, String premisecode) throws InterruptedException, DocumentException, IOException {
	
	try {	  
		String linklostForwardingcheckboxstate="disabled"; 
		
		String[] Vender= {"Accedian-1G 1GigE-MetroNID-GT", "Accedian-1G 1GigE-MetroNID-GT-S", "Accedian-1G GX"};
		
		String[] powerAlarm= {"AC", "DC"};
		
		String expectedDeviceNameFieldAutopopulatedValue="<Device>.lanlink.dcn.colt.net";
		

	scrolltoend();
	Thread.sleep(3000);
	
		click_commonMethod(application, "OK", "obutton_spanTag", xml);
		Thread.sleep(3000);
		
		//Country Error Message
		device_countrywarningMessage(application);
		
		//serial Number Error Message
		device_serialNumberWarningMessage(application);
		
		//Hexa Serial Number Error Message
		device_hexaSerialNumberWarningMessage(application);
		
	ScrolltoElement(getwebelement("//label[text()='Name']"));
	Thread.sleep(3000);
	
		
		// Vendor/Model Error Message
		device_vendorModelWarningMessage(application);

		// Management Address Error Message
		device_managementAddressWarningMessage(application);

		// Power Alarm Error Message
		device_powerAlarmWarningMessage(application);
			
		
		//Vendor/Model
		device_vendorModel(application, Vender, vender);      
	
		//Snmpro
		device_snmPro(application, snmpro);
		
		//Management Address dropdown
		device_managementAddress(application, existingmanagementAddress, newmanagementAddress, managementAddress);
		
		//MEP Id
		device_mepID(application, Mepid);
	
		//Power Alarm	
		device_powerAlarm(application, powerAlarm, poweralarm);
		
		//serial number
		device_serialNumber(application, serialNumber);
		
	scrolltoend();
	Thread.sleep(3000);
	
	    //Link lost Forwarding
//		device_linklostForwarding(application, linkLostForwarding, linklostForwardingcheckboxstate);
	    
	
		//Country
		device_country(application, country);

		//City		
		if(existingcityselectionmode.equalsIgnoreCase("no") & newcityselectionmode.equalsIgnoreCase("yes")) {
			addCityToggleButton(application);
		   //New City	
			newcity(application, newcityselectionmode, cityname, citycode);
		      // New Site
			newSite(application, newsiteselectionmode, sitename, sitecode);
			  //New Premise	
			newPremise(application, newpremiseselectionmode, premisename, premisecode);
				
		}	
			
		else if(existingcityselectionmode.equalsIgnoreCase("yes") & newcityselectionmode.equalsIgnoreCase("no")) {
		//Existing City		
		   existingCity(application, City);
			
			//Site
		 
			  if(existingsiteselectionmode.equalsIgnoreCase("yes") & newsiteselectionmode.equalsIgnoreCase("no")) {
				//Existing Site 
				  existingSite(application, Site);
				  
				   //Premise
				  if(existingpremiseselectionmode.equalsIgnoreCase("yes") & newpremiseselectionmode.equalsIgnoreCase("no")) {
					  existingPremise(application, Premise);
				  
		          	 }
				  else if(existingpremiseselectionmode.equalsIgnoreCase("no") & newpremiseselectionmode.equalsIgnoreCase("yes")) {
					  //New Premise
					    addPremiseTogglebutton(application);
					    newPremise_clickOnPremisetoggleButton(application, newpremiseselectionmode, premisename, premisecode);
				  } 
	         	}
  		
		  else if(existingsiteselectionmode.equalsIgnoreCase("no") & newsiteselectionmode.equalsIgnoreCase("yes")) {
			  	//New Site 
			  	addSiteToggleButton(application);
			  	newSite_ClickOnSiteTogglebutton(application, newsiteselectionmode, sitename, sitecode); 
			  	
			  	//New Premise
			  	newPremise_clickonSiteToggleButton(application, newpremiseselectionmode, premisename, premisecode);
			  }
		}
				
		 //OK button
		device_okbutton(application);
		
		//cancel button
		device_cancelButton(application);
		
		click_commonMethod(application, "OK", "AddCPEdevice_OKbutton", xml);
		
		scrollToTop();
		Thread.sleep(1000);
		
		
		warningMessage_commonMethod(application, "warningmEssage1_devicename", "Device Name", xml);
		warningMessage_commonMethod(application, "warningmEssage2_devicename", "Device Name", xml);
		warningMessage_commonMethod(application, "warningmEssage3_devicename", "Device Name", xml);
		
		//Name
		device_nameField(application, cpename, expectedDeviceNameFieldAutopopulatedValue);
		
		scrolltoend();
		Thread.sleep(1000);
		click_commonMethod(application, "OK", "AddCPEdevice_OKbutton", xml);
		
			
		sa.assertAll();
		
	}catch(AssertionError e) {
		
		e.printStackTrace();
		//ExtentTestManager.getTest().log(LogStatus.FAIL, "FAiled while verify the fields for Add CPE device");
		
		
	}
	ExtentTestManager.getTest().log(LogStatus.INFO, "Input data has been passed for creating device");
	Thread.sleep(3000);

	}
	
	
	public void viewdevice_Overture(String application, String cpename, String vender,
			String snmpro, String managementAddress, String Mepid, String poweralarm, String Mediaselection,
			String Macaddress, String linkLostForwarding, String existingcountry, String existingCity,
			String newCity, String existingSite, String newSite, String existingPremise, String newPremise, 
			String existingcityselectionmode, String newcityselectionmode, String existingsiteselectionmode, 
			String newsiteselectionmode, String newmanagementAddress, String existingmanagementAddress, String manageaddressdropdownvalue,
			String existingpremiseselectionmode, String newpremiseselectionmode) throws InterruptedException {
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "verify the details entered while creating device");
		
		String[] RouterId=new String[2];
		RouterId=cpename.split(".lanlink");
		
		String RouterIdValue=RouterId[0];
		
		
		String mediaSelectionValueInViewDevicePage="no";
		if(Mediaselection.equalsIgnoreCase("null")) {
			Mediaselection=mediaSelectionValueInViewDevicePage;
		}else {
			Mediaselection=mediaSelectionValueInViewDevicePage;
		}
	  
	  verifyEnteredvalues_deviceName("Name", RouterIdValue,cpename );
	  
	  verifyEnteredvalues("Router Id", RouterIdValue);
	  
	  verifyEnteredvalues("Vendor/Model", vender);
	  
	  verifyEnteredvalues("Snmpro", snmpro);
	  
	//Management Address  
	  	if((existingmanagementAddress.equalsIgnoreCase("Yes")) && (newmanagementAddress.equalsIgnoreCase("no"))) {
		  verifyEnteredvalues("Management Address", manageaddressdropdownvalue);
		 }
		 else if((existingmanagementAddress.equalsIgnoreCase("no")) && (newmanagementAddress.equalsIgnoreCase("Yes"))) {
			 verifyEnteredvalues("Management Address", managementAddress);
		 } 
	  
	  
//	  verifyEnteredvalues("MEP Id", Mepid);
	  
	  verifyEnteredvalues("Power Alarm", poweralarm);
	  
	  verifyEnteredvalues("Media Selection", Mediaselection);
	  
	  verifyEnteredvalues("MAC Address", Macaddress);
	  
	  verifyEnteredvalues("Link Lost Forwarding", linkLostForwarding);
	  
	  verifyEnteredvalues("Country", existingcountry);
	
	//City  
	 if((existingcityselectionmode.equalsIgnoreCase("Yes")) && (newcityselectionmode.equalsIgnoreCase("no"))) {
		 verifyEnteredvalues("City", existingCity);
	 }
	 else if((existingcityselectionmode.equalsIgnoreCase("no")) && (newcityselectionmode.equalsIgnoreCase("Yes"))) {
		 verifyEnteredvalues("City", newCity);
	 } 
	 
	 
	//Site
	 if((existingsiteselectionmode.equalsIgnoreCase("Yes")) && (newsiteselectionmode.equalsIgnoreCase("no"))) {
		 verifyEnteredvalues("Site", existingSite);
	 }
	 else if((existingsiteselectionmode.equalsIgnoreCase("no")) && (newsiteselectionmode.equalsIgnoreCase("Yes"))) {
		 verifyEnteredvalues("Site", newSite);
	 } 
	 
	 
	//Premise
	 if((existingpremiseselectionmode.equalsIgnoreCase("Yes")) && (newpremiseselectionmode.equalsIgnoreCase("no"))) {
		 verifyEnteredvalues("Premise", existingPremise);
	 }
	 else if((existingpremiseselectionmode.equalsIgnoreCase("no")) && (newpremiseselectionmode.equalsIgnoreCase("Yes"))) {
		 verifyEnteredvalues("Premise", newPremise);
	 } 
	  
	  
	  

	}


	public void viewdevice_Accedian(String application, String cpename, String vender,
			String snmpro, String managementAddress, String Mepid, String poweralarm, String Mediaselection,
			String Macaddress, String serialNumber, String hexaSerialnumber, String linkLostForwarding, String existingcountry, String existingCity,
			String newCity, String existingSite, String newSite, String existingPremise, String newPremise, 
			String existingcityselectionmode, String newcityselectionmode, String existingsiteselectionmode, 
			String newsiteselectionmode, String newmanagementAddress, String existingmanagementAddress, String manageaddressdropdownvalue,
			String existingpremiseselectionmode, String newpremiseselectionmode) throws InterruptedException {
		
			ExtentTestManager.getTest().log(LogStatus.INFO, "verify the details entered while creating device");
			
			String[] RouterId=new String[2];
			RouterId=cpename.split(".lanlink");
			
			String RouterIdValue=RouterId[0];
			
			
			String mediaSelectionValueInViewDevicePage="no";
			if(Mediaselection.equalsIgnoreCase("null")) {
				Mediaselection=mediaSelectionValueInViewDevicePage;
			}else {
				Mediaselection=mediaSelectionValueInViewDevicePage;
			}
		  
		  verifyEnteredvalues_deviceName("Name", RouterIdValue,cpename );
		  
		  verifyEnteredvalues("Router Id", RouterIdValue);
		  
		  verifyEnteredvalues("Vendor/Model", vender);
		  
		  verifyEnteredvalues("Snmpro", snmpro);
		  
		//Management Address  
		  	if((existingmanagementAddress.equalsIgnoreCase("Yes")) && (newmanagementAddress.equalsIgnoreCase("no"))) {
			  verifyEnteredvalues("Management Address", manageaddressdropdownvalue);
			 }
			 else if((existingmanagementAddress.equalsIgnoreCase("no")) && (newmanagementAddress.equalsIgnoreCase("Yes"))) {
				 verifyEnteredvalues("Management Address", managementAddress);
			 } 
		  
		  verifyEnteredvalues("Power Alarm", poweralarm);
		  
		  verifyEnteredvalues("Media Selection", Mediaselection);
		  
//		  verifyEnteredvalues("MAC Address", hexaSerialnumber);
		  
		  verifyEnteredvalues("Serial Number", serialNumber);
		  
//		  verifyEnteredvalues("Link Lost Forwarding", linkLostForwarding);

		//City  
			 if((existingcityselectionmode.equalsIgnoreCase("Yes")) && (newcityselectionmode.equalsIgnoreCase("no"))) {
				 verifyEnteredvalues("City", existingCity);
			 }
			 else if((existingcityselectionmode.equalsIgnoreCase("no")) && (newcityselectionmode.equalsIgnoreCase("Yes"))) {
				 verifyEnteredvalues("City", newCity);
			 } 
			 
			 
			//Site
			 if((existingsiteselectionmode.equalsIgnoreCase("Yes")) && (newsiteselectionmode.equalsIgnoreCase("no"))) {
				 verifyEnteredvalues("Site", existingSite);
			 }
			 else if((existingsiteselectionmode.equalsIgnoreCase("no")) && (newsiteselectionmode.equalsIgnoreCase("Yes"))) {
				 verifyEnteredvalues("Site", newSite);
			 } 
			 
			 
			//Premise
			 if((existingpremiseselectionmode.equalsIgnoreCase("Yes")) && (newpremiseselectionmode.equalsIgnoreCase("no"))) {
				 verifyEnteredvalues("Premise", existingPremise);
			 }
			 else if((existingpremiseselectionmode.equalsIgnoreCase("no")) && (newpremiseselectionmode.equalsIgnoreCase("Yes"))) {
				 verifyEnteredvalues("Premise", newPremise);
			 } 
	}
	
	
	public void device_editnamefield(String application, String cpename) {
		
		 boolean name=false;
			try {
				
				name=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_Name")).isDisplayed();
				Thread.sleep(3000);
				 
		if(name) {	
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Name' field is displaying under 'Edit CPE device' page");
			
			if(cpename.equalsIgnoreCase("null")) {
				
				ExtentTestManager.getTest().log(LogStatus.PASS, "No changes made for 'Name' field while editing");
				
			}else {
				getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_Name")).clear();
				Thread.sleep(3000);
				
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_Name")), cpename);
				Thread.sleep(3000);
				 
				String actualValue_Name=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_Name")).getAttribute("value");
				ExtentTestManager.getTest().log(LogStatus.PASS, actualValue_Name+ " is the edited value for 'Name' field");
			}
			
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Name' field is not available under 'Edit CPE device' page");
		}
			}catch(NoSuchElementException e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, " CPE Name field is not available ");
			}catch(Exception err) {
				err.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to enter value in CPE name field");
			}
	}
	
	
	public void device_editVendorModelField(String application, String vender) {
		
		boolean vend0r=false;
		try {	
			
			vend0r=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_vender")).isDisplayed();
			Thread.sleep(3000);
		if(vend0r) {
			
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Vendor/Model' dropdown is displaying in 'Edit CPE Device' page");
			
			if(vender.equalsIgnoreCase("null")) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "No changes made for 'Vender/Model' dropdown while editing");
				
			}else {
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/EditCPEdevice_vendoModel_xbutton")));
				Thread.sleep(3000);
				
				Clickon(getwebelement("//div[label[text()='Vendor/Model']]//div[text()='"+vender +"']"));
				ExtentTestManager.getTest().log(LogStatus.PASS, vender+ " is the edited value for 'vender/Model' field");
			}
		  }else {
			  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Vendor/Model' dropdown is not available in 'Edit CPE Device' page");
		  }
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'Vender/Model' dropdown is not available");
		}
	}
	
	
	public void device_editSnmproField(String application) {
		
		boolean sNmpro=false;
		try {
			
		  sNmpro=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_snmpro")).isDisplayed();
		  
		 if(sNmpro) { 
			 ExtentTestManager.getTest().log(LogStatus.PASS, " ' Snmpro' field is displaying in 'Edit CPE Device' page as expected");
			 
			  boolean actualValue_snmpro=getwebelement(xml.getlocator("//locators/" + application + "/CPEdevice_snmpro_autoPopulatedValue")).isDisplayed();
			  if(actualValue_snmpro) {
				  ExtentTestManager.getTest().log(LogStatus.PASS, " 'Snmpro' field value is displaying as expected."
				  		+ " It is displaying as: "+getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_snmpro")).getAttribute("value"));
				  
			  }else {
				  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Snmpro' value is not displaying as expected."
				  		+ " It is displaying as: "+getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_snmpro")).getAttribute("value"));
			  }
			  
			  ExtentTestManager.getTest().log(LogStatus.PASS, "No changes made for 'snmpro' field while editing cpe device under Equipment");
			  
		 }else {
			 ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Snmpro' field is not available in 'Edit CPE Device' page");
		 }
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Snm pro' field is not available ");
		}catch(Exception err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to enter value in 'Snm pro' field");
		}
	}
	
	
	public void device_editManagementAddressField(String application, String managementAddress) {
		
		boolean manageAddressAvailability=false;
		try {
			
//			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_selectSubnettogglebutton")));
//			Thread.sleep(3000);
			
			manageAddressAvailability=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_manageaddress")).isDisplayed();
			Thread.sleep(3000);
			
			if(manageAddressAvailability) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "' Management Address' text field is displaying in 'Edit CPE Device' page as expected");
				
				if(managementAddress.equalsIgnoreCase("null")) {
					
					ExtentTestManager.getTest().log(LogStatus.PASS, "No changes made for 'Manage address' field while editing cpe device under Equipment");
					
				}else {
					
					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/changeButton_managementAddress")));
					Thread.sleep(3000);
					
					getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_manageaddress")).clear();
					Thread.sleep(3000);
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_manageaddress")),
					managementAddress);
			Thread.sleep(3000);
			ExtentTestManager.getTest().log(LogStatus.PASS, managementAddress+ " is the edited value for 'Manage address' field");
				}
				
			}
			
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'Manage Address' field is not available ");
		}catch(Exception err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to enter value in 'Manage Addres' field");
		}
	}
	
	
	public void device_editMEPIdField(String application, String Mepid) {
		
		boolean mepIdavailability=false;
		try {
			mepIdavailability =getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_mepid")).isDisplayed();
		if(mepIdavailability) {	
			
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Mep Id' field is displaying under 'Edit CPE device' page as expected");
			if(Mepid.equalsIgnoreCase("null")) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "No changes made for 'mepid' field while editing cpe device under Equipment");
			}else {
				getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_mepid")).clear();
				Thread.sleep(3000);
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_mepid")), Mepid);
				Thread.sleep(3000);
				ExtentTestManager.getTest().log(LogStatus.PASS,  Mepid+ " is the edited value for 'Mepid' field");
			}
		  }
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Mep id' field is not available ");
		}catch(Exception err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to enter value in 'Mep Id' field");
		}
	}
	
	
	public void device_editPowerAlarm(String application, String poweralarm) throws InterruptedException, DocumentException {
		
		addDropdownValues_commonMethod(application, "Power Alarm", "AddCPEdevice_poweralarm", poweralarm, xml);
	    
	}
	
	
	public void device_editMediaselection(String application, String Mediaselection) throws InterruptedException, DocumentException {
		
		boolean mediaSelection1=false;
		try {
			
			mediaSelection1=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_mediaselection")).isDisplayed();
		if(mediaSelection1)	{
			
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Media Selection' dropdown is displaying in 'Edit CPE device' page as expected");
			if(Mediaselection.equalsIgnoreCase("null")) {
				
				ExtentTestManager.getTest().log(LogStatus.PASS, "No changes made for 'Media selection' dropdown while editing cpe device under Equipment");
				
			}else {
				
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/EditCPEdevice_mediaselection_xbutton")));
				Thread.sleep(5000);
				
				Clickon(getwebelement("//div[label[text()='Media Selection']]//div[text()='"+Mediaselection +"']"));
				Thread.sleep(3000);
				ExtentTestManager.getTest().log(LogStatus.PASS, Mediaselection+ " is the edited value for 'Media Selection' field");
			}
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Media selection' dropdown is not avilable in 'Edit CPE device' page");
		}
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'Media selection' mandatory dropdown is not available");
		}catch(Exception er) {
			er.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to enter value in 'Media Selection' field");
			
		}
	}
	
	
	public void device_editMACaddress(String application, String Macaddress) {
		
		 boolean macAddress=false;
		    try {
		    	macAddress=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_macaddress")).isDisplayed();
		    	
		    	if(macAddress) {
		    		ExtentTestManager.getTest().log(LogStatus.PASS, " 'MAC Address' field is displaying in 'Edit CPE device' page as expected");
		    	if(Macaddress.equalsIgnoreCase("null")) {
		    		ExtentTestManager.getTest().log(LogStatus.PASS, "No changes made for 'Mac address' field while editing cpe device under Equipment");
				}else {
					getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_macaddress")).clear();
					Thread.sleep(3000);
					SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_macaddress")), Macaddress);
					String actualValue_MacAddress=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_macaddress")).getAttribute("value");
					ExtentTestManager.getTest().log(LogStatus.PASS, actualValue_MacAddress+ " is the edited value for 'Macaddress' field");
				}
		    	}else {
		    		ExtentTestManager.getTest().log(LogStatus.FAIL, " 'MAC Address' field is not available in 'Edit CPE device' page");
		    	}
		    }catch(NoSuchElementException e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Mac Address' field is not available ");
			}catch(Exception err) {
				err.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to enter value in 'Mac Address' field");
			}
	}
	
	
	public void device_editlinkLostforwarding(String application, String linkLostForwarding) {
		
		 boolean linklostcheckbox=false;
		    try { 
		    	linklostcheckbox=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_linklostforowarding")).isDisplayed();
		    	
		    if(linklostcheckbox) {	
		    	
		    	ExtentTestManager.getTest().log(LogStatus.PASS,  " 'Link lost Forwarding' checkbox is displaying in 'Edit CPE device' page as expected");
		    	
		    	if (linkLostForwarding.equalsIgnoreCase("null")) {
		    		
		    		ExtentTestManager.getTest().log(LogStatus.PASS, "No changes made for linklost forwarding while editing cpe device under equipment");
		    	}else {
		    
		    		boolean linklost=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_linklostforowarding")).isSelected();
		    		
		    		if (linkLostForwarding.equalsIgnoreCase("yes")) {
				
		    			if(linklost) {
		    				ExtentTestManager.getTest().log(LogStatus.PASS, "linklost forwarding has been edited for cpe device under Equipment");
		    				
		    			}else {
		    				
		    				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_linklostforowarding")));
		    				ExtentTestManager.getTest().log(LogStatus.PASS, "linklost forwarding has been edited for cpe device under Equipment");
		    			}
		    			
				
		    		}else if(linkLostForwarding.equalsIgnoreCase("no")) {
		    			
		    			if(linklost) {
		    				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_linklostforowarding")));
		    				ExtentTestManager.getTest().log(LogStatus.PASS, "linklost forwarding has been edited for cpe device under Equipment");
		    				
		    			}else {
		    				
		    				ExtentTestManager.getTest().log(LogStatus.PASS, "linklost forwarding has been edited for cpe device under Equipment");
		    			}
		    			
		    		}
		    	}	
		    }else {
		    	ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Link Lost Forwarding' checkbox is not available in 'Edit CPE device' page");
		    }
				}catch (NoSuchElementException e) {
				    e.printStackTrace();
				    ExtentTestManager.getTest().log(LogStatus.FAIL, "'linklost forwarding'  checkbox is ont available under 'Edit CPE device' page");
				}catch(Exception er) {
					er.printStackTrace();
					ExtentTestManager.getTest().log(LogStatus.FAIL, " NOt able to click on 'link lost forwarding' checkbox in 'Edit CPE device' page");
				}
	}
	
	
	public void device_editlinklostforwarding_10G(String application) {
		
		 boolean linklostcheckboxAvailability=false;
		 boolean linklostcheckboxEnabled=false;
		    try { 
		    	linklostcheckboxAvailability=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_linklostforowarding")).isDisplayed();
		    	
		    if(linklostcheckboxAvailability) {	
		    	ExtentTestManager.getTest().log(LogStatus.PASS,  " 'Link lost Forwarding' checkbox is displaying in 'Edit CPE device' page as expected");
		    	
		    	linklostcheckboxEnabled=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_linklostforowarding")).isEnabled();
		    	if(linklostcheckboxEnabled) {
		    		Log.info(" 'link lostforwarding is enabled for 10G");
		    		ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Link Lost forwarding' is enabled");
		    		
		    	}else {
		    		Log.info("link lost checkbox is disabled as expected");
		    		ExtentTestManager.getTest().log(LogStatus.PASS, " 'Link lost Forwarding' checkbox is disabled as expected");
		    	}
		    	
		    }else {
		    	ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Link Lost Forwarding' checkbox is not available in 'Edit CPE device' page");
		    }
				}catch (NoSuchElementException e) {
				    e.printStackTrace();
				    ExtentTestManager.getTest().log(LogStatus.FAIL, "'linklost forwarding'  checkbox is ont available under 'Edit CPE device' page");
				}catch(Exception er) {
					er.printStackTrace();
					ExtentTestManager.getTest().log(LogStatus.PASS, " 'link lost forwarding' checkbox is disabled 'Edit CPE device' page");
				}
	
	}
	
	
	public void device_editserialnumber(String application, String serialNumber) {
		
		 boolean serialunmberAvailability=false;
			try {
				
				serialunmberAvailability=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_serialnumber")).isDisplayed();
				Thread.sleep(3000);
				 
		if(serialunmberAvailability) {	
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Serial Number' field is displaying under 'Edit CPE device' page");
			
			if(serialNumber.equalsIgnoreCase("null")) {
				
				ExtentTestManager.getTest().log(LogStatus.PASS, "No changes made for 'Serial Number' field while editing");
				
			}else {
				getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_serialnumber")).clear();
				Thread.sleep(3000);
				
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_serialnumber")), serialNumber);
				Thread.sleep(3000);
				 
				String actualValue_Name=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_serialnumber")).getAttribute("value");
				ExtentTestManager.getTest().log(LogStatus.PASS, actualValue_Name+ " is the edited value for 'Serial Number' field");
			}
			
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Serial Number' field is not available under 'Edit CPE device' page");
		}
			}catch(NoSuchElementException e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, " Serial Number field is not available ");
			}catch(Exception err) {
				err.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to enter value in Serial Number field");
			}
	}
	
	public void createService_ServiceIdentification(String application, String ServiceIdentificationNumber) throws InterruptedException, IOException, DocumentException {
		//Service Identification
		
		boolean serviceIdentificationField = getwebelement(xml.getlocator("//locators/" + application + "/ServiceIdentification")).isDisplayed();
		if(serviceIdentificationField) {
			
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Service Identification' text field is displaying as exepected");
		if(!ServiceIdentificationNumber.equalsIgnoreCase("null")) {
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/ServiceIdentification")),
					ServiceIdentificationNumber);
			Thread.sleep(3000);
			ExtentTestManager.getTest().log(LogStatus.PASS, ServiceIdentificationNumber+ " is entered under 'Service Identification' field");
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL,"It is a mandatory field. No values entered for service identification"); 
		}
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " ' Service Identfication' mandatory field is not available under 'Add Service' page");
		}
	
	}
	
	
	public void createService_singleEndPointCPE(String application, String EndpointCPE) throws InterruptedException, DocumentException {
		
		addCheckbox_commonMethod(application, "EndpointCPE", "Single Endpoint CPE", EndpointCPE, "No", xml);
		
	}
	
	
	public void createSerivce_email(String application, String Email) throws InterruptedException, DocumentException, IOException {
		
		boolean email=false;
		 try {
				email = getwebelement(xml.getlocator("//locators/" + application + "/Email")).isDisplayed();
				if(email) {
					ExtentTestManager.getTest().log(LogStatus.PASS, " 'Email' text field is displaying as expected");
				
				if(!Email.equalsIgnoreCase("null")) {
				
					SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Email")), Email);
					Thread.sleep(3000);
					ExtentTestManager.getTest().log(LogStatus.PASS, Email + " is entered under 'Email' field");

				}else {
					ExtentTestManager.getTest().log(LogStatus.PASS,"value not entered under 'Email' field"); 
				}
				}else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Email' field is not available under 'Create Service' page");
				}
		 }catch(NoSuchElementException e) {
			 e.printStackTrace();
			 Log.info("Email field is not available");
			 ExtentTestManager.getTest().log(LogStatus.PASS, " 'Email' field is not available under 'create Service' page");
		 }catch(Exception er) {
			 er.printStackTrace();
			 Log.info("Not able to enter value in 'Email' field");
			 ExtentTestManager.getTest().log(LogStatus.FAIL, "Not able to enter value in 'Email' field");
		 }
	}
	
	
	public void createService_phone(String application, String PhoneContact) throws InterruptedException, DocumentException, IOException {
		
		boolean phone=false;
	try {	
		phone = getwebelement(xml.getlocator("//locators/" + application + "/PhoneContact")).isDisplayed();
		sa.assertTrue(phone, "phone contact field is not displayed");
		if(phone) {
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Phone Contact' text field is displaying as expected");
			
		if(!PhoneContact.equalsIgnoreCase("null")) {
			
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/PhoneContact")), PhoneContact);
			Thread.sleep(3000);
			ExtentTestManager.getTest().log(LogStatus.PASS, PhoneContact + " is entered under 'Phone contact' field" );
		}else {
			ExtentTestManager.getTest().log(LogStatus.PASS,"Value not entered under 'Phone contact' field");
		}
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Phone Contact' text field is not available under 'Create Service' page");
		}
	  }catch(NoSuchElementException e) {
		  e.printStackTrace();
		  Log.info("Phone contact text field is not available");
		  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Phone Contact' text field is not available under 'Create Service' page");
	  }catch(Exception err) {
		  err.printStackTrace();
		  ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to enter value under 'hone Contact' field");
		  Log.info("Not able to enter value under 'Phone Contact' field");
	  }
	}
	
	
	public void createService_remark(String application, String remark) throws InterruptedException, DocumentException, IOException {
		boolean Remark=false;
	try {	
		Remark = getwebelement(xml.getlocator("//locators/" + application + "/Remark")).isDisplayed();
		if(Remark) {
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Remark' text field is displaying as expected");

		if(!remark.equalsIgnoreCase("null")) {
			
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Remark")), remark);
			Thread.sleep(3000);
			ExtentTestManager.getTest().log(LogStatus.PASS, remark + " is entered under 'Remark' field");
			
		}else {
			
			ExtentTestManager.getTest().log(LogStatus.PASS,"value not entered under 'Remark' field");
		}
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Remark' text field is not available under 'Create Service' page");
		}
	}catch(NoSuchElementException e) {
		e.printStackTrace();
		Log.info("Remak text field is not availeble");
		ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Remark' text field is not available under 'Create Service' page");
	}catch(Exception err) {
		err.printStackTrace();
		Log.info(" Not able t enter value in 'remark' text field");
		 Log.info("Not able to enter value under 'Remark' field");
	}
		
	}
	
	
	public void createService_performancereporting_10G(String application, String PerformanceReporting) throws InterruptedException, DocumentException {
		
		boolean performancereoprting=false;
	try {	
		performancereoprting = getwebelement(xml.getlocator("//locators/" + application + "/performanceReportingcheckbox"))
				.isDisplayed();
			if(performancereoprting) {
				ExtentTestManager.getTest().log(LogStatus.PASS, " 'Performance Reporting' checkbox is displaying as expected");
			
			if(!PerformanceReporting.equalsIgnoreCase("null")) {
				
				if (PerformanceReporting.equalsIgnoreCase("yes")) {
			
					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/performanceReportingcheckbox")));
					Log.info("performance Reporting check box is selected");
					
					boolean prfrmselection = getwebelement(xml.getlocator("//locators/" + application + "/performanceReportingcheckbox")).isSelected();
					if(prfrmselection) {
						ExtentTestManager.getTest().log(LogStatus.PASS, "Performance Reporting checkbox is selected as expected");
					}else {
						ExtentTestManager.getTest().log(LogStatus.FAIL, "Performance Reporting checkbox is not selected");
					}
				}
				else {
			
					Log.info("Performance Reporting is not selected");
					ExtentTestManager.getTest().log(LogStatus.PASS,"performance Reporting checkbox is not selected");
				}
			}else {
				ExtentTestManager.getTest().log(LogStatus.PASS, " 'Performance Reporting' checkbox is not selected");
			}
			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Performance Reporting' checkbox is not displaying under 'create service' page");
			}
		   }catch(NoSuchElementException e) {
			   e.printStackTrace();
			   Log.info(" 'Perormance reporting' checkbox is not selected");
			   ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Performance Reporting' checkbox is not displaying under 'create service' page");
		   }catch(Exception err) {
			   err.printStackTrace();
		   }
		}
	
	
	public void createService_deliveryChannel(String application, String deliveryChannel) throws InterruptedException, DocumentException {
		
		boolean deliveryChanel=false;
	try {	
		deliveryChanel = getwebelement(xml.getlocator("//locators/" + application + "/deliverychannel_withclasskey")).isDisplayed();
		sa.assertTrue(deliveryChanel, "delivery channel dropdown is not displayed");
		if(deliveryChanel) {
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Delivery Channel' dropdown is displaying as expected");
		if (!deliveryChannel.equalsIgnoreCase("null")) {

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/deliverychannel_withclasskey")));
			Thread.sleep(3000);
			Clickon(getwebelement("//div[contains(text(),'" + deliveryChannel + "')]"));
			Thread.sleep(3000);
			ExtentTestManager.getTest().log(LogStatus.PASS, deliveryChannel + " is selected under 'Delivery channel' dropdown");

		}else {
			ExtentTestManager.getTest().log(LogStatus.PASS,"No value selected under 'Delivery channel' dropdown"); 
		}
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Delivery channel' dropdown is not dispalying under 'create Serice' page");
		}
	  }catch(NoSuchElementException e) {
		  e.printStackTrace();
		  Log.info(" 'Delivery channel' dropdown is not dispalying");
		  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Delivery channel' dropdown is not dispalying under 'create Serice' page");
	  }catch(Exception err) {
		  err.printStackTrace();
		  ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to selected value under 'Delivery channel' dropodwn");
	  }
	}

	
	public void createService_managementOptions(String application, String ManagementOrder) throws InterruptedException, DocumentException {
		
		boolean Managementorder=false;
	try {	
		Managementorder = getwebelement(xml.getlocator("//locators/" + application + "/managementOrder")).isDisplayed();
		sa.assertTrue(Managementorder, "Management order field is not displayed");
		if(Managementorder) {
			ExtentTestManager.getTest().log(LogStatus.PASS, " ' Management Order' dropdown is displaying as expected");
		if (!ManagementOrder.equalsIgnoreCase("null")) {
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/managementOrder")));
			Thread.sleep(3000);
			Clickon(getwebelement("//div[contains(text(),'" + ManagementOrder + "')][1]"));
			Thread.sleep(3000);
			
			ExtentTestManager.getTest().log(LogStatus.PASS, ManagementOrder + " is selected under 'management Order' field");
		
		}else {
			ExtentTestManager.getTest().log(LogStatus.PASS,"Values not entered under 'Management Order' field");
		}
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " ' Management Order' dropdown is not displaying under 'Create Service' page");
		}
	  }catch(NoSuchElementException e) {
		  e.printStackTrace();
		  Log.info(" 'Management order' dropdown is not displaying");
		  ExtentTestManager.getTest().log(LogStatus.FAIL, " ' Management Order' dropdown is not displaying under 'Create Service' page");
	  }catch(Exception err) {
		  err.printStackTrace();
		  ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to select value under 'management Order' dropdown");
	  }
	}
	
	
	public void createService_proactivemonitoring(String application,String ProactiveMonitoring, String notificationManagement) throws InterruptedException, DocumentException {
		
		boolean proactiveMonitor=false;
		proactiveMonitor = getwebelement(xml.getlocator("//locators/" + application + "/proactiveMonitoring"))
				.isDisplayed();
		sa.assertTrue(proactiveMonitor, "pro active monitoring checkbox is not displayed");
		if(proactiveMonitor) {
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Pro active Monitoring' text field is displaying as expected");
		
		if (!ProactiveMonitoring.equalsIgnoreCase("null")) {
			if (ProactiveMonitoring.equalsIgnoreCase("yes")) {

				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/proactiveMonitoring")));
				Log.info("Pro active monitoring check box is selected");
				
				boolean proactiveSelection=getwebelement(xml.getlocator("//locators/" + application + "/proactiveMonitoring")).isSelected();
				if(proactiveSelection) {
					ExtentTestManager.getTest().log(LogStatus.PASS, " 'pro active monitoring' checkbox is selected as expected");
				}else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, " 'pro active monitoring' checkbox is not selected");
				}
				
			  //Notification management	
			try {	
				ExtentTestManager.getTest().log(LogStatus.INFO, "Notification Management dropdown displays when pro active monitoring is selected");
				
				if (!notificationManagement.equalsIgnoreCase("null")) {
				Log.info("Notificationan Management dropdown displays when pro active monitoring is selected");
				
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/notificationmanagement")));
				Thread.sleep(3000);
				Clickon(getwebelement("//div[contains(text(),'" + notificationManagement + "')]"));
				Thread.sleep(3000);
				ExtentTestManager.getTest().log(LogStatus.PASS, notificationManagement + " is selected under 'Notification management' dropdown");
				
				}else {
					ExtentTestManager.getTest().log(LogStatus.PASS,"No values selected under Notification management dropdown"); 
					Log.info("No values selected under Notification management dropdown");
					
				}
				
			}catch(NoSuchElementException e) {
				Log.info(" 'Notification management' dropodwn is not displaying under 'create Service' page");
				ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Notification management' dropodwn is not displaying under 'create Service' page");
			}catch(Exception err) {
				err.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL,"Not able to select values under Notification management dropdown"); 
			}
			} else {
				Log.info("Pro active monitoring is not selected");
				Log.info("pro active monitoring is not selected");
				ExtentTestManager.getTest().log(LogStatus.PASS,"performance monitor checkbox is not selected "); 
			}

		}
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Pro active monitoring' checkbox is not displaying");
		}
	}
	
	
	public void createService_intermediateTechnology(String application, String intermediateTechnology) throws InterruptedException, DocumentException, IOException {
		boolean intrTech=false;
	try {	
		intrTech=getwebelement(xml.getlocator("//locators/" + application + "/IntermediateTechnology")).isDisplayed();
		if(intrTech) {
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Intermediate Technology' text field is displying as expected under 'create service' page");
		if (!intermediateTechnology.equalsIgnoreCase("null")) {
		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/IntermediateTechnology")),
				intermediateTechnology);
		
		String actualvalue=getwebelement(xml.getlocator("//locators/" + application + "/IntermediateTechnology")).getAttribute("value");
		Thread.sleep(3000);
		ExtentTestManager.getTest().log(LogStatus.PASS, actualvalue + " is entered under 'Intermediate technology' field");
		}else {
			ExtentTestManager.getTest().log(LogStatus.PASS,"No value entered under 'Intermediate  Technology' field");
		}
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Intermediate Technology' text field is not displying under 'create service' page");
		}
	}catch(NoSuchElementException e) {
		e.printStackTrace();
		Log.info("'Intermediate Technology' text field is not displying");
		ExtentTestManager.getTest().log(LogStatus.FAIL," 'Intermediate Technology' text field is not displying under 'create service' page");
	}catch(Exception err) {
		err.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL," Not able to enter value under 'Intermediate Technology' text field in 'create service' page");
		Log.info("Not able to enter value under 'Intermediate Technology' text field in 'create service' page");
	}
	}
	
	
	public void createService_circuitreference(String application, String CircuitReference) throws InterruptedException, DocumentException, IOException {
		boolean crctRef=getwebelement(xml.getlocator("//locators/" + application + "/circuitReference")).isDisplayed();
		if(crctRef) {
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Circuit Reference' text field is displying as expected");
		if (!CircuitReference.equalsIgnoreCase("null")) {
		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/circuitReference")), CircuitReference);
		Thread.sleep(3000);
		
		String actualvalue=getwebelement(xml.getlocator("//locators/" + application + "/circuitReference")).getAttribute("value");
		
		ExtentTestManager.getTest().log(LogStatus.PASS, actualvalue + " is entered under 'Circuit Reference' field");
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL,"Circuit reference field is mandatory, but no inputs has been provided");
		}
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Circuit Reference' text field is not displying");
		}
	}
	
	
	public void createSerivce_circuitType(String application, String CircuitType) throws InterruptedException {
       
		if (!CircuitType.equalsIgnoreCase("null")) {
		Clickon(getwebelement("//div[span[contains(text(),'" + CircuitType + "')]]//input"));
		Thread.sleep(3000);
		ExtentTestManager.getTest().log(LogStatus.PASS, CircuitType + " is selected under 'Circuit Type'");
		}else {
			ExtentTestManager.getTest().log(LogStatus.PASS," No changes made. 'Default' is selected under'Circuit type' as no input provided");
			
		}
	}
	
	
	public void createService_standardCIR(String application, String standrdCir) throws InterruptedException, DocumentException, IOException {
		boolean standrdCiR=false;
		
	try {	
		standrdCiR=getwebelement(xml.getlocator("//locators/" + application + "/standardCIRtextfield")).isDisplayed();
		if(standrdCiR) {
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Standard CIR' text field displaying, when 'Actelis Based' checkbox is selected");
			if(standrdCir.equalsIgnoreCase("null")) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "No Values entered in 'Standard CIR' text field");
			}else {
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/standardCIRtextfield")), standrdCir);
				
				String valuesForSCIR=getwebelement(xml.getlocator("//locators/" + application + "/standardCIRtextfield")).getAttribute("value");
				ExtentTestManager.getTest().log(LogStatus.PASS, valuesForSCIR +" is enerted under 'Standard CIR' text field" );
			}
			
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Standard CIR' Text Field is not displaying, when 'Actelis Based' checkbox is selected");
		}
	}catch(NoSuchElementException e) {
		e.printStackTrace();
		Log.info("'Standard CIR' Text Field is not displaying, when 'Actelis Based' checkbox is selected");
		ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Standard CIR' Text Field is not displaying, when 'Actelis Based' checkbox is selected");
	}catch(Exception err) {
		err.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to enter value under 'Standard CIR' field in 'create Service' page");
	}
	}
	
	
	public void createService_standardEIR(String application, String standrdEir) throws InterruptedException, DocumentException, IOException {
		boolean standrdEiR=false;
	try {	
		standrdEiR=getwebelement(xml.getlocator("//locators/" + application + "/standardEIRtextfield")).isDisplayed();
		if(standrdEiR) {
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Standard EIR' text field displaying, when 'Actelis Based' checkbox is selected");
			if(standrdEir.equalsIgnoreCase("null")) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "No Values entered in 'Standard EIR' text field");
			}else {
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/standardEIRtextfield")), standrdEir);
				
				String valuesForSEIR=getwebelement(xml.getlocator("//locators/" + application + "/standardEIRtextfield")).getAttribute("value");
				ExtentTestManager.getTest().log(LogStatus.PASS, valuesForSEIR +" is enerted under 'Standard EIR' text field" );
			}
			
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Standard EIR' Text Field is not displaying, when 'Actelis Based' checkbox is selected");
		}
	 }catch(NoSuchElementException e) {
			e.printStackTrace();
			Log.info("'Standard EIR' Text Field is not displaying, when 'Actelis Based' checkbox is selected");
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Standard EIR' Text Field is not displaying, when 'Actelis Based' checkbox is selected");
		}catch(Exception err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Not albe to enter value under 'Standard EIR' field in 'create Service' page");
			Log.info(" Not able to enter value under 'Standard EIR' field in 'create Service' page");
		}
	}
	
	
	public void createService_premiumCIR(String application, String prmiumCir) throws InterruptedException, DocumentException, IOException {
		boolean premiumCiR=false;
	try {	
		premiumCiR=getwebelement(xml.getlocator("//locators/" + application + "/premiumCIRtextfield")).isDisplayed();
		if(premiumCiR) {
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Premium CIR' text field displaying, when 'Actelis Based' checkbox is selected");
			if(prmiumCir.equalsIgnoreCase("null")) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "No Values entered in 'Premium CIR' text field");
			}else {
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/premiumCIRtextfield")), prmiumCir);
				
				String valuesForPCIR=getwebelement(xml.getlocator("//locators/" + application + "/premiumCIRtextfield")).getAttribute("value");
				ExtentTestManager.getTest().log(LogStatus.PASS, valuesForPCIR +" is enerted under 'Premium CIR' text field" );
			}
			
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Premium CIR' Text Field is not displaying, when 'Actelis Based' checkbox is selected");
		}	
	}catch(NoSuchElementException e) {
		e.printStackTrace();
		Log.info("'Premium CIR' Text Field is not displaying, when 'Actelis Based' checkbox is selected");
		ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Premium CIR' Text Field is not displaying, when 'Actelis Based' checkbox is selected");
	}catch(Exception err) {
		err.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to enter value under 'Premium CIR' field in 'create Service' page");
	}	
	}
	
	
	public void createService_premiumEIR(String application, String prmiumEir) throws InterruptedException, DocumentException, IOException {
		boolean premiumEiR=false;
	try {	
		premiumEiR=getwebelement(xml.getlocator("//locators/" + application + "/premiumEIRtextfield")).isDisplayed();
		if(premiumEiR) {
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Standard EIR' text field displaying, when 'Actelis Based' checkbox is selected");
			if(prmiumEir.equalsIgnoreCase("null")) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "No Values entered in 'Premium EIR' text field");
			}else {
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/premiumEIRtextfield")), prmiumEir);
				
				String valuesForPEIR=getwebelement(xml.getlocator("//locators/" + application + "/premiumEIRtextfield")).getAttribute("value");
				ExtentTestManager.getTest().log(LogStatus.PASS, valuesForPEIR +" is enerted under 'Premium EIR' text field" );
			}
			
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Premium EIR' Text Field is not displaying, when 'Actelis Based' checkbox is selected");
		}
	  }catch(NoSuchElementException e) {
			e.printStackTrace();
			Log.info("'Premium EIR' Text Field is not displaying, when 'Actelis Based' checkbox is selected");
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Premium EIR' Text Field is not displaying, when 'Actelis Based' checkbox is selected");
		}catch(Exception err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to enter value under 'Premium EIR' field in 'create Service' page");
		}		
	}
	
	
	public void editService_serviceIdentification(String application, String ServiceIdentificationNumber) throws InterruptedException, DocumentException, IOException {
		
		 boolean serviceAvailability=false;
		 serviceAvailability=getwebelement(xml.getlocator("//locators/" + application + "/ServiceIdentification")).isDisplayed();
		 
	if(serviceAvailability) {
		
		ExtentTestManager.getTest().log(LogStatus.PASS, " 'Service Identification' field is displaying in 'Edit Service'page as expected");
		
		if(!ServiceIdentificationNumber.equalsIgnoreCase("null")) {
			
			getwebelement(xml.getlocator("//locators/" + application + "/ServiceIdentification")).clear();
			Thread.sleep(2000);
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/ServiceIdentification")),
					ServiceIdentificationNumber);
			
			String Actualvalue=getwebelement(xml.getlocator("//locators/" + application + "/ServiceIdentification")).getAttribute("value");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Edited value for 'Service Identification' field is "+ Actualvalue);
			
		}else {
			ExtentTestManager.getTest().log(LogStatus.PASS, " Service Identification field value is not edited");
			Log.info(" Service Identification field value is not edited");
		}
	}else {
		ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Service Identification' field is not availale in 'Edit Service'page");
	}
	}
	
	
	public void editService_singleEndPointCPE(String application, String EndpointCPE, String Topology) throws InterruptedException, DocumentException {
		boolean CPEAvailability=false;
		
	if(Topology.equalsIgnoreCase("Point-to-Point"))	{
		
		try {
		CPEAvailability=getwebelement(xml.getlocator("//locators/" + application + "/EndpointCPE")).isDisplayed();
		if(CPEAvailability) {
			
			ExtentTestManager.getTest().log(LogStatus.PASS, "Single EndPoint CPE checkbox is displaying under 'Edit Service' page");
			
			if(!EndpointCPE.equalsIgnoreCase("null")) {
				
				boolean endpoint=getwebelement(xml.getlocator("//locators/" + application + "/EndpointCPE")).isSelected();
				Thread.sleep(2000);
				
				if (EndpointCPE.equalsIgnoreCase("yes")) {

					if(endpoint) {
						
						ExtentTestManager.getTest().log(LogStatus.PASS, "Endpoint CPE is not edited. It is already Selected while creating.");
						
					}else {
						
						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/EndpointCPE")));
						Log.info("End point CPE check box is edited and it got selected");
						ExtentTestManager.getTest().log(LogStatus.PASS,"Endpoint CE is edited and it got selected");
					}
				}
				else if (EndpointCPE.equalsIgnoreCase("no")) {
					if(endpoint) {
						
						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/EndpointCPE")));
						Log.info("End point CPE check box is unselected");
						ExtentTestManager.getTest().log(LogStatus.PASS,"Endpoint CE is edited and it is unselected as Expected");
						
					}else {
						ExtentTestManager.getTest().log(LogStatus.PASS, "Endpoint CPE is not edited. It was not selected during service creation and it remains unselected as expected");
					}
					
				}
			}else {
				ExtentTestManager.getTest().log(LogStatus.PASS,"No changes made for EndPoint CPE chekbox as expected");
			}
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Single EndPoint CPE checkbox is not available under 'Edit Service' page");
		}
	  }catch(NoSuchElementException e) {
		  e.printStackTrace();
		  ExtentTestManager.getTest().log(LogStatus.FAIL, "Single EndPoint CPE checkbox is not available under 'Edit Service' page");
		  Log.info("Single EndPoint CPE checkbox is not available under 'Edit Service' page");
	  }catch(Exception err) {
		  err.printStackTrace();
		  ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to click on 'Single endpoint CPE' checkbox");
		  Log.info("Not able to click on 'Single endpoint CPE' checkbox");
	  }
	
	}	else{
	
		ExtentTestManager.getTest().log(LogStatus.PASS, " 'Single Endpoint CPE' checkbox is not displaying as expected,"
				+ " when 'VPN Topology' selected as 'hub&spoke' or 'E-PN(Any to Any)");
	
	}
	}	
	
	
	public void editService_Email(String application, String Email) throws InterruptedException, DocumentException, IOException {
		
		 boolean emailAvailability=false;
		 try {
			 emailAvailability= getwebelement(xml.getlocator("//locators/" + application + "/Email")).isDisplayed();
		 
		 if(emailAvailability) {
			 
			 ExtentTestManager.getTest().log(LogStatus.PASS, " 'Email' text field is displaying in 'Edit Service' page");
			 
			if(!Email.equalsIgnoreCase("null")) {
				
				getwebelement(xml.getlocator("//locators/" + application + "/Email")).clear();
				Thread.sleep(2000);
				
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Email")), Email);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Edited value for 'Email' field is "+ Email);
				
				String Actualvalue=getwebelement(xml.getlocator("//locators/" + application + "/Email")).getAttribute("value");
				ExtentTestManager.getTest().log(LogStatus.PASS, " 'Email' field is edited as: "+Actualvalue );
				Log.info("'Email' field is edited as: "+Actualvalue);

			}else {
				ExtentTestManager.getTest().log(LogStatus.PASS, "'Email' field is not edited");
			}
		 }else {
			 ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Email' text field is not available in 'Edit Service' page");
		 }
		 }catch(NoSuchElementException e) {
			 e.printStackTrace();
			 ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Email' text field is not available in 'Edit Service' page");
			 Log.info(" 'Email' text field is not available in 'Edit Service' page");
		 } catch(Exception err) {
			 err.printStackTrace();
			 ExtentTestManager.getTest().log(LogStatus.FAIL, " not able to edit 'Email' field");
		 }
		 
	}
	
	
	public void editService_phoneContact(String application, String PhoneContact) throws InterruptedException, DocumentException, IOException {
		
		 boolean phoneAvailability=false;
	     try {
	    	 phoneAvailability=getwebelement(xml.getlocator("//locators/" + application + "/PhoneContact")).isDisplayed();
	    
	   if(phoneAvailability) { 
		   
		   ExtentTestManager.getTest().log(LogStatus.PASS, " Phone Contact field is displaying in 'Edit Service' page as expected");
		   
		if(!PhoneContact.equalsIgnoreCase("null")) {
			
			getwebelement(xml.getlocator("//locators/" + application + "/PhoneContact")).clear();
			Thread.sleep(2000);
			
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/PhoneContact")), PhoneContact);
			Thread.sleep(3000);
			String actualValue=getwebelement(xml.getlocator("//locators/" + application + "/PhoneContact")).getAttribute("value");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Edited value for 'Phone contact' field is " + actualValue);
		}else {
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Phone contact' field is not edited");
		}
	   }else {
		   ExtentTestManager.getTest().log(LogStatus.FAIL, " Phone Contact field is not available in 'Edit Service' page");
	   }
	     }catch(NoSuchElementException e) {
	    	 e.printStackTrace();
	    	 ExtentTestManager.getTest().log(LogStatus.FAIL, " Phone Contact field is not available in 'Edit Service' page");
	    	 Log.info("Phone Contact field is not available in 'Edit Service' page");
	     }catch(Exception err) {
	    	 err.printStackTrace();
	    	 ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to edit 'phone Contact' field");
	    	 Log.info(" Not able to edit 'phone Contact' field");
	     }
	}
	
	
	public void editService_remark(String application, String remark) throws InterruptedException, DocumentException, IOException {
		
		 boolean remarkAvailability=false;
		   try {
			   remarkAvailability=getwebelement(xml.getlocator("//locators/" + application + "/Remark")).isDisplayed();
		   
		  if(remarkAvailability) {
			  
			  ExtentTestManager.getTest().log(LogStatus.PASS, " 'Remark' field is displaying in 'Edit Service' page as expected");
			  
			if(!remark.equalsIgnoreCase("null")) {
				
				getwebelement(xml.getlocator("//locators/" + application + "/Remark")).clear();
				Thread.sleep(2000);
				
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Remark")), remark);
				
				String actualvalue=getwebelement(xml.getlocator("//locators/" + application + "/Remark")).getAttribute("value");
				ExtentTestManager.getTest().log(LogStatus.PASS, "Edited value for 'Remark' field is "+ actualvalue);
				
		}else {
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Remark' field is not edited");
		}
		  }else {
			  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Remark' field is not available in 'Edit Service' page");
		  }
		   }catch(NoSuchElementException e) {
		    	 e.printStackTrace();
		    	 ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Remark' field is not available in 'Edit Service' page");
		    	 Log.info(" 'remark' field is not available in 'Edit Service' page");
		     }catch(Exception err) {
		    	 err.printStackTrace();
		    	 ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to edit 'Remark' field");
		    	 Log.info(" Not able to edit 'Remark' field");
		     }
	}
	
	
	public void editService_performancereporting_10G(String application, String performanceReporting) throws InterruptedException, DocumentException {
		
		if(!performanceReporting.equalsIgnoreCase("null")) {
			
			boolean perfmmonitr=getwebelement(xml.getlocator("//locators/" + application + "/performanceReportingcheckbox")).isSelected();
			Thread.sleep(2000);
			
			if (performanceReporting.equalsIgnoreCase("yes")) {

				if(perfmmonitr) {
					
					ExtentTestManager.getTest().log(LogStatus.PASS, "Performance Reporting checkbox is not edited and it is already Selected while creating");
					
				}else {
					
					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/performanceReportingcheckbox")));
					Log.info("Performance monitor check box is selected");
					ExtentTestManager.getTest().log(LogStatus.PASS,"Performance Reporting is edited and it is selected");
				}
			}
			else if (performanceReporting.equalsIgnoreCase("no")) {
				if(perfmmonitr) {
					
					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/performancemonitoring")));
					Log.info("Performance Reporting check box is unselected");
					ExtentTestManager.getTest().log(LogStatus.PASS,"Performance Reporting is edited and gets unselected");
					
				}else {
					ExtentTestManager.getTest().log(LogStatus.PASS, "Performance Reporting is not edited and it remains unselected");
				}
			}
		}else {
			ExtentTestManager.getTest().log(LogStatus.PASS," 'Performance reporting' checkbox is not edited");
		}
	}

		public void editService_proactiveMonitoring(String application, String ProactiveMonitoring, String notificationManagement) throws InterruptedException, DocumentException {
			if(!ProactiveMonitoring.equalsIgnoreCase("null")) {
				
				boolean proactivemonitor=getwebelement(xml.getlocator("//locators/" + application + "/proactiveMonitoring")).isSelected();
				Thread.sleep(2000);
				
				if (ProactiveMonitoring.equalsIgnoreCase("yes")) {

					if(proactivemonitor) {
						
						ExtentTestManager.getTest().log(LogStatus.PASS, "Proactive monitoring is not edited and it is already Selected while creating");
						

						if(notificationManagement.equalsIgnoreCase("null")) {
							
							ExtentTestManager.getTest().log(LogStatus.PASS,"No changes made to notification management");
							
						}else {
							
							Clickon(getwebelement(xml.getlocator("//locators/" + application + "/notigymanagement_xbutton")));
							Thread.sleep(4000);
							
							Clickon(getwebelement(xml.getlocator("//locators/" + application + "/notificationmanagement")));
							Thread.sleep(3000);
							Clickon(getwebelement("//div[contains(text(),'" + notificationManagement + "')]"));
							Thread.sleep(3000);
							ExtentTestManager.getTest().log(LogStatus.PASS,"Edited value for 'Notification Management' field is "+notificationManagement);
						}
						
					}else {
						
						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/proactiveMonitoring")));
						Log.info("proactive monitoring check box is selected");
						ExtentTestManager.getTest().log(LogStatus.PASS,"proactive monitoring is edited and gets selected");
						
						
						if(notificationManagement.equalsIgnoreCase("null")) {
							
							ExtentTestManager.getTest().log(LogStatus.PASS,"No changes made to notification management");
							
						}else {
							
							Clickon(getwebelement(xml.getlocator("//locators/" + application + "/notigymanagement_xbutton")));
							Thread.sleep(2000);
							
							Clickon(getwebelement(xml.getlocator("//locators/" + application + "/notificationmanagement")));
							Clickon(getwebelement("//div[contains(text(),'" + notificationManagement + "')]"));
							ExtentTestManager.getTest().log(LogStatus.PASS,"Edited value for 'Notification Management' field is "+notificationManagement);
						}
					}
				}

				else if (ProactiveMonitoring.equalsIgnoreCase("no")) {
					
					if(proactivemonitor) {
						
						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/proactiveMonitoring")));
						Log.info("Proactive monitoring check box is unselected");
						ExtentTestManager.getTest().log(LogStatus.PASS,"proactive monitoring is edited and gets unselected");
						
					}else {
						ExtentTestManager.getTest().log(LogStatus.PASS, "proactive monitoring was not selected during service creation and it remains unselected");
					}
				}
			}else {
				ExtentTestManager.getTest().log(LogStatus.PASS,"No changes made for 'Proactive monitoring' chekbox");
			}
		}
		
		
		public void editService_deliveryChannel(String application, String deliveryChannel) throws InterruptedException, DocumentException {
			
			 boolean deliveryChannelAvailability=false;
			   try {
				   deliveryChannelAvailability= getwebelement(xml.getlocator("//locators/" + application + "/deliverychannel_withclasskey")).isDisplayed();
			   
			  if(deliveryChannelAvailability) { 
				  
				  ExtentTestManager.getTest().log(LogStatus.PASS, " 'Delivery channel' dropdown is displaying in 'Edit Service' page as expected");
				  
				if (!deliveryChannel.equalsIgnoreCase("null")) {
					
					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/deliverychannel_xbutton")));
					Thread.sleep(3000);

					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/deliverychannel_withclasskey")));
					Thread.sleep(3000);
					Clickon(getwebelement("//div[contains(text(),'" + deliveryChannel + "')]"));
					
					ExtentTestManager.getTest().log(LogStatus.PASS,"Edited value for 'Delivery Channel' dropdown is "+deliveryChannel);
					Log.info("Delivery channel dropdown value is edited as expected");

				}else {
					ExtentTestManager.getTest().log(LogStatus.PASS, "Delivery channel dropdown value is not edited");
				}
			  }else {
				  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Delivery channel' dropdown is not available in 'Edit Service' page");
			  }
			   }catch(NoSuchElementException e) {
				   e.printStackTrace();
				   ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Delivery channel' dropdown is not available in 'Edit Service' page");
				   Log.info(" 'Delivery channel' dropdown is not available in 'Edit Service' page");
			   }catch(Exception err){
				   err.printStackTrace();
				   ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to edit 'delvery Channel' dropdown");
			   }
		}
		
		
		public void editService_managementOrder(String application, String ManagementOrder) throws InterruptedException, DocumentException {
			
			boolean managemenOrderAvailabiliy=false;
			try {
				managemenOrderAvailabiliy=getwebelement(xml.getlocator("//locators/" + application + "/managementOrder")).isDisplayed();
			
		
	if(managemenOrderAvailabiliy) {	
		ExtentTestManager.getTest().log(LogStatus.PASS, " 'Management Order' dropdown is displaying in 'Edit Service' page as expected");
		
		if (!ManagementOrder.equalsIgnoreCase("null")) {

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/managementOrder_xButton")));
			Thread.sleep(3000);

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/managementOrder")));
			Thread.sleep(3000);
			Clickon(getwebelement("//div[contains(text(),'" + ManagementOrder + "')]"));
			Thread.sleep(3000);
			
			ExtentTestManager.getTest().log(LogStatus.PASS, "Edited value for 'management Order' field is " + ManagementOrder);
			Log.info("Edited value for 'management Order' field is " + ManagementOrder);
			
		}else {
			
			ExtentTestManager.getTest().log(LogStatus.PASS, "No changes has been made to 'Management order' field");
			Log.info("No changes has been made to 'Management order' field");
		}
	}else {
		ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Management Order' dropdown is not available in 'Edit Service' page");
	}
			}catch(NoSuchElementException e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Management Order' dropdown is not available in 'Edit Service' page");
				Log.info(" 'Management Order' dropdown is not available in 'Edit Service' page");
			}catch(Exception err) {
				err.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to edit 'management order' dropodwn");
				Log.info(" Not able to edit 'management order' dropodwn");
			}
		}
		
		
	public void editService_circuitreference(String application, String CircuitReference) throws InterruptedException, DocumentException, IOException {
			
			boolean circuitRefAvailability=false;
			
			circuitRefAvailability=getwebelement(xml.getlocator("//locators/" + application + "/circuitReference")).isDisplayed();
		if(circuitRefAvailability) {	
			if(CircuitReference.equalsIgnoreCase("null")) {
				ExtentTestManager.getTest().log(LogStatus.PASS, " 'Circuit reference' field is not edited");
			}else {
				
				getwebelement(xml.getlocator("//locators/" + application + "/circuitReference")).clear();
				Thread.sleep(3000);

				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/circuitReference")), CircuitReference);
				Thread.sleep(3000);
				String actualvalue=getwebelement(xml.getlocator("//locators/" + application + "/circuitReference")).getAttribute("value");
				ExtentTestManager.getTest().log(LogStatus.PASS,"Edited value for 'Circuit reference' field is "+actualvalue);
			}
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Circuit Reference' field is not displaying in 'Edit CPE device' page");
		}
	}	
	
	
	public void editService_IntermediateTechnology(String application, String intermediateTechnology) throws InterruptedException, DocumentException, IOException {
		
		boolean intTechAvilability=false;
	try {
		intTechAvilability=getwebelement(xml.getlocator("//locators/" + application + "/IntermediateTechnology")).isDisplayed();
	if(intTechAvilability) {
		ExtentTestManager.getTest().log(LogStatus.PASS, "Intermediate Technologies' field is displaying under 'Edit Service' page as expected");
		if(intermediateTechnology.equalsIgnoreCase("null")) {
			
			ExtentTestManager.getTest().log(LogStatus.PASS, "No changes has made for 'Intermediate Technology' field");
			
		}else {
			
			getwebelement(xml.getlocator("//locators/" + application + "/IntermediateTechnology")).clear();
			Thread.sleep(3000);
			
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/IntermediateTechnology")),
					intermediateTechnology);
			Thread.sleep(3000);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Edited value for 'Intermediate Technology' field is "+ intermediateTechnology);
			
		}
	  }else {
		  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Intermediate technologies' field is not displaying under 'edit Sevice' page");
	  }
	}catch(NoSuchElementException e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Intermediate technologies' field is not displaying under 'edit Sevice' page");
		Log.info(" 'Intermediate technologies' field is not displaying under 'edit Sevice' page");
	}catch(Exception err) {
		err.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to edit 'Intermediate technologies' field");
	}
	}
	
	
		public void editService_circuitType(String application, String CircuitType) throws InterruptedException {
		
			try {
			if(CircuitType.equalsIgnoreCase("null")) {
				
				ExtentTestManager.getTest().log(LogStatus.PASS, " 'Circuit type' field is not edited");
				
			}else {
				
			//verify whether it is selected	
				boolean circuitType=getwebelement("//div[span[contains(text(),'" + CircuitType + "')]]//input").isSelected();
				if(circuitType) {
					
					ExtentTestManager.getTest().log(LogStatus.PASS, " 'Circuit Type' value selected as : "+ CircuitType +" as expected");
					
				}else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Circuit Type' value is not selected as : "+ CircuitType );
				}
				
			//verify whether it is editable
//				boolean circuitTypeEditable=getwebelement("//div[span[contains(text(),'" + CircuitType + "')]]//input").isEnabled();
//				if(circuitType) {
//					
//					ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Circuit Type' is editable");
//					
//				}else {
//					ExtentTestManager.getTest().log(LogStatus.PASS, " 'Circuit Type' value is not editable as expected" );
//				}
			}
			}catch(NoSuchElementException e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Circuit type' "+ CircuitType +" is not dipslaying under 'Edit Service page");
				Log.info(" 'Circuit type' "+ CircuitType +" is not dipslaying under 'Edit Service page");
			}catch(Exception er) {
				er.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.PASS, " 'Circuit type' "+ CircuitType +" is disabled under 'Edit Service page");
				Log.info(" 'Circuit type' "+ CircuitType +" is disabled under 'Edit Service page");
			}
		}
		
		
	  public void editService_premiumEIR(String application, String premiumEIR) throws InterruptedException, DocumentException, IOException {
		  
		  boolean premumEIR=false;
		try {  
		  premumEIR=getwebelement(xml.getlocator("//locators/" + application + "/premiumEIRtextfield")).isDisplayed();
			if(premumEIR) {
				ExtentTestManager.getTest().log(LogStatus.PASS, " Premise EIR' field is displaying in 'Edit Service' page as expected");
			if(!premiumEIR.equalsIgnoreCase("null")) {
					
				getwebelement(xml.getlocator("//locators/" + application + "/premiumEIRtextfield")).clear();
				Thread.sleep(2000);
					
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/premiumEIRtextfield")), premiumEIR);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Edited value for 'Premium EIR' field is "+ premiumEIR);

			}else {
				ExtentTestManager.getTest().log(LogStatus.PASS, "  'Premium EIR' field is not edited");
			}	
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Premise EIR' field is not displaying in 'Edit Service' page ");
		}
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Premise EIR' field is not displaying in 'Edit Service' page ");
			Log.info(" Premise EIR' field is not displaying in 'Edit Service' page");
		}catch(Exception err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to edit 'Premise EIR' field");
			Log.info("Not able to edit 'Premise EIR' field");
			
		}
	  }
	  
	  
	  public void editService_premiumCIR(String application, String premiumCIR) throws InterruptedException, DocumentException, IOException {
		  
		  boolean premumCIR=false;
		try {  
		  premumCIR=getwebelement(xml.getlocator("//locators/" + application + "/premiumCIRtextfield")).isDisplayed();
			if(premumCIR) {
				ExtentTestManager.getTest().log(LogStatus.PASS, " Premise CIR' field is displaying in 'Edit Service' page as expected");
			if(!premiumCIR.equalsIgnoreCase("null")) {
					
				getwebelement(xml.getlocator("//locators/" + application + "/premiumCIRtextfield")).clear();
				Thread.sleep(2000);
					
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/premiumCIRtextfield")), premiumCIR);
				
				String actualValue=getwebelement(xml.getlocator("//locators/" + application + "/premiumCIRtextfield")).getAttribute("value");
				ExtentTestManager.getTest().log(LogStatus.PASS, "Edited value for 'Premium CIR' field is "+ actualValue);

			}else {
				ExtentTestManager.getTest().log(LogStatus.PASS, " 'Premium CIR' field is not edited");
			}	
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Premise CIR' field is not displaying in 'Edit Service' page ");
		}
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Premise CIR' field is not displaying in 'Edit Service' page ");
			Log.info(" Premise CIR' field is not displaying in 'Edit Service' page ");
		}catch(Exception err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to edit 'Premise CIR' field");
			Log.info("Not able to edit 'Premise CIR' field");
			
		}
	  }
	  
	  
	  public void editService_standardEIR(String application, String standardEIR) throws InterruptedException, DocumentException, IOException {
		  
		  boolean stndrdEIR=false;
		try {  
		  stndrdEIR=getwebelement(xml.getlocator("//locators/" + application + "/standardEIRtextfield")).isDisplayed();
			if(stndrdEIR) {
				ExtentTestManager.getTest().log(LogStatus.PASS, " Standard EIR' field is displaying in 'Edit Service' page as expected");
			if(!standardEIR.equalsIgnoreCase("null")) {
					
				getwebelement(xml.getlocator("//locators/" + application + "/standardEIRtextfield")).clear();
				Thread.sleep(3000);
					
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/standardEIRtextfield")), standardEIR);
				
				String actualvalue=getwebelement(xml.getlocator("//locators/" + application + "/standardEIRtextfield")).getAttribute("value");
				ExtentTestManager.getTest().log(LogStatus.PASS, "Edited value for 'Standard EIR' field is "+ actualvalue);

			}else {
				ExtentTestManager.getTest().log(LogStatus.PASS, " 'Standard EIR' field is not edited");
			}	
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Standard EIR' field is not displaying in 'Edit Service' page ");
		}
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Standard EIR' field is not displaying in 'Edit Service' page ");
			Log.info(" 'Standard EIR' field is not displaying in 'Edit Service' page");
		}catch(Exception err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to edit 'Standard EIR' field");
			Log.info(" Not able to edit 'Standard EIR' field");
			
		}
	  }
		
	  
	  public void editService_standardCIR(String application, String standardCIR) throws InterruptedException, DocumentException, IOException {
		  
		try {  
		  boolean stndrdCIR=getwebelement(xml.getlocator("//locators/" + application + "/standardCIRtextfield")).isDisplayed();
			if(stndrdCIR) {
				ExtentTestManager.getTest().log(LogStatus.PASS, " 'Standard CIR' field is displaying in 'Edit Service' page ");
			if(!standardCIR.equalsIgnoreCase("null")) {
					
				getwebelement(xml.getlocator("//locators/" + application + "/standardCIRtextfield")).clear();
				Thread.sleep(3000);
					
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/standardCIRtextfield")), standardCIR);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Edited value for 'Standard CIR' field is "+ standardCIR);

			}else {
				ExtentTestManager.getTest().log(LogStatus.PASS, " 'Standard CIR' field is not edited");
			}	
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Standard CIR' field is not displaying in 'Edit Service' page ");
		}
		}catch(NoSuchElementException e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Standard CIR' field is not displaying in 'Edit Service' page ");
				Log.info(" 'Standard CIR' field is not displaying in 'Edit Service' page");
			}catch(Exception err) {
				err.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to edit 'Standard CIR' field");
				Log.info(" Not able to edit 'Standard CIR' field");
				
			}
	  }
	  
	  
	  public void verifyEnteredvalueForEmail_serviceCreationpage(String label, String expectedValue) throws InterruptedException {

			try {
				
				WebElement ele = getwebelement("(//div[div[label[contains(text(),'"+ label +"')]]]/div[2])[2]");
				String valueinfo = ele.getText().toString();
				if ((valueinfo.equals("")) || (valueinfo.equalsIgnoreCase(null))) {

					Log.info("value not displayed for " + label);
					valueinfo= "Null";
					
					sa.assertEquals(valueinfo, expectedValue, label + " value is not displaying as expected");
					
//					ExtentTestManager.getTest().log(LogStatus.PASS, "No value displaying for : " + label);
					
					
				} else {
					
					Log.info("value displayed for " + label + " is : " + valueinfo);
					
					Log.info("value displayed for" + label + "is : " + valueinfo);
					
					sa.assertEquals(valueinfo, expectedValue, label + " value is not displaying as expected");

					if(valueinfo.equalsIgnoreCase(expectedValue)) {
						Log.info("The valus is dipslaying as expected");
						ExtentTestManager.getTest().log(LogStatus.PASS, " Value is displaying as expected in 'view' page for "+label);
						ExtentTestManager.getTest().log(LogStatus.PASS, "value displayed for" + label + "is : " + valueinfo);
					}else {
						Log.info("the values are not dipslaying as expected for label: "+label);
						ExtentTestManager.getTest().log(LogStatus.FAIL, " Value is not displaying as expected in 'view' page for "+ label);
						ExtentTestManager.getTest().log(LogStatus.FAIL, "value displayed for " + label + "is : " + valueinfo);
						
					}
					
				} 
			} catch(AssertionError err) {
				err.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, label + " value is not displaying as expected ");
			} catch (NoSuchElementException e) {
				Log.info("value not displayed for " + label);
				ExtentTestManager.getTest().log(LogStatus.FAIL, "" + label +" is not displaying");
				
			}
		}

	  public void edittextFields_commonMethod(String application, String labelname, String xpathname, String expectedValueToEdit) throws InterruptedException, DocumentException, IOException {
			boolean availability=false;
		try {	
			availability=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpathname +"")).isDisplayed();
			if(availability) {
				ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " text field is displaying as expected");
				Log.info(labelname + " text field is displaying as expected");
				
				if(expectedValueToEdit.equalsIgnoreCase("null")) {
					ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " text field is not edited as expected");
					Log.info(labelname + " text field is not edited as expected");
				}else {
					
					getwebelement(xml.getlocator("//locators/" + application + "/"+ xpathname +"")).clear();
					Thread.sleep(3000);
					
					SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/"+ xpathname +"")), expectedValueToEdit);
					Thread.sleep(3000);
					
					String actualvalue=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpathname +"")).getAttribute("value");
					ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " text field is edited as: "+ actualvalue);
				}
				
			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " text field is not displaying");
				Log.info(labelname + " text field is not displaying");
			}
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " text field is not displaying");
			Log.info(labelname + " text field is not displaying");
		}catch(Exception ee) {
			ee.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to edit"+ labelname + " text field");
			Log.info(" Not able to edit"+ labelname + " text field");
		}
	}
	  
	  
	  /**
	   * Add Mapping Mode under Site creation
	   * @param application
	   * @param mappingMode
	   */
	  public void addMappingMode(String application, String mappingMode) {
		  
		  String labelname="Mapping Mode";
		  boolean availability=false;
		try {  
		  availability=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_mappingModedropdown")).isDisplayed();
		  if(availability) {
			  ExtentTestManager.getTest().log(LogStatus.PASS, "Mapping mode dropdown is displaying as expected");
			  Log.info(labelname + " is displaying as expected");
			  
			  if(mappingMode.equalsIgnoreCase("null")) {
				  
				  ExtentTestManager.getTest().log(LogStatus.PASS, " No values selected under "+ labelname + " dropdown");
				  Log.info(" No values selected under "+ labelname + " dropdown");
			  }else {
				  
				  Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_mappingModedropdown_xbutton")));
				  Thread.sleep(3000);
				  
				  Clickon(getwebelement("//div[text()='"+ mappingMode +"']"));
				  Thread.sleep(3000);
				  
				  String actualValue=getwebelement("//div[label[text()='"+ labelname +"']]//span").getText();
				  ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " dropdown value selected as: "+ actualValue );
				  Log.info( labelname + " dropdown value selected as: "+ actualValue);
				  
			  }
		  }else {
			  ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " dropdown is not displaying");
			  Log.info(labelname + " is not displaying");
		  }
		}catch(NoSuchElementException e) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " dropdown is not displaying");
			  Log.info(labelname + " is not displaying");
		}catch(Exception ee) {
			ee.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " NO value selected under "+ labelname + " dropdown");
			Log.info(" NO value selected under "+ labelname + " dropdown");
		}
		  
	  }

	  
	  public boolean findPanelHeader(String application, String devicePanelName) throws InterruptedException, DocumentException {

		  scrolltoend();
		  WebElement el=null;
		 boolean panelheader=false;
		try { 
			
			el=getwebelementNoWait(xml.getlocator("//locators/" + application + "/devicePanelHeaders_InViewSiteOrderPage").replace("value", devicePanelName));
			panelheader=el.isDisplayed();
			
		 if(panelheader) {
			 ExtentTestManager.getTest().log(LogStatus.INFO, devicePanelName +" panel is displaying under 'view site order' page");
			 Log.info(" 'Equipment' panel is displaying under 'view site order' page");
			 panelheader=true;
			 
		 }else {
			 ExtentTestManager.getTest().log(LogStatus.INFO, devicePanelName + "  panel is not displaying under 'view site order' page");
			 Log.info(" 'Equipment' panel is not displaying under 'view site order' page");
			 panelheader=false;
			 
		 }}catch(NoSuchElementException e) {
			 e.printStackTrace();
			 ExtentTestManager.getTest().log(LogStatus.INFO, devicePanelName + " panel is not displaying under 'view site order' page");
			 Log.info(" 'Equipment' panel is not displaying under 'view site order' page");
			 panelheader=false;
			 
		 }
		 
		  return panelheader;
	  }
	  
	  
	  public void findlistofInterfaces_Equipment_viewdevicePage(String application) throws InterruptedException, DocumentException {
		 
		 List<WebElement> interfacelist= getwebelements(xml.getlocator("//locators/" + application + "/findlistofInterfacesNames"));
		 
		 int interfacelistSize=interfacelist.size();
		 
		 if(interfacelistSize==0) {
			 ExtentTestManager.getTest().log(LogStatus.FAIL, "No interfaces are displaying");
			 Log.info("no Interfaces are displaying");
		 }else {
			 Log.info("list of interfaces displaying are:"+ interfacelistSize);
			 ExtentTestManager.getTest().log(LogStatus.PASS, " size of interfaces displaying is: "+ interfacelistSize);
			 
			 
			 for (WebElement interfaceName : interfacelist) {
				 
				 ExtentTestManager.getTest().log(LogStatus.INFO, " "+ interfaceName.getText());
				 Log.info("Interface names are: "+ interfaceName.getText());
			 }
		 }
		 
	  }
	  
	  
	  public void findlistofInterfaces_IntEquipment_viewdevicePage(String application) throws InterruptedException, DocumentException {
		
		  scrolltoend();
		  Thread.sleep(3000);
		 List<WebElement> interfacelist= getwebelements(xml.getlocator("//locators/" + application + "/findlistofInterfacesNames"));
		 int interfacelistSize=interfacelist.size();
		 
		 if(interfacelistSize==0) {
			 ExtentTestManager.getTest().log(LogStatus.FAIL, "No interfaces are displaying");
			 Log.info("no Interfaces are displaying");
		 }else {
			 Log.info("list of interfaces displaying are:"+ interfacelistSize);
			 ExtentTestManager.getTest().log(LogStatus.PASS, " size of interfaces displaying is: "+ interfacelistSize);
			 
			 
			 for (WebElement interfaceName : interfacelist) {
				 
				 ExtentTestManager.getTest().log(LogStatus.INFO, " "+ interfaceName.getText());
				 Log.info("Interface names are: "+ interfaceName.getText());
			 }
		 }
	  }
	  
	  
	  public void configure_circuitId(String application, String circuitID) throws InterruptedException {
		  
		  try {	
				if(circuitID.equalsIgnoreCase("null")) {
					
					ExtentTestManager.getTest().log(LogStatus.PASS, " No input provided for 'Circuit ID' field");
					Log.info(" No input provided for 'Circuit ID' field");
					
				}else {
					
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/selectCircuit_togglebutton")));
				Thread.sleep(3000);
				
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureXNGCircuitID")), circuitID);
				ExtentTestManager.getTest().log(LogStatus.PASS, circuitID + " is the value passed for 'Circuit ID' field");
				
				String actualvalue=getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureXNGCircuitID")).getAttribute("value");
				ExtentTestManager.getTest().log(LogStatus.PASS, " Vallue entered in 'Circuit Id' field is: "+actualvalue);
				}
			 }catch(NoSuchElementException e) {
				 e.printStackTrace();
				 ExtentTestManager.getTest().log(LogStatus.FAIL, "'circuit Id' field under 'Edit Interface' page is not available ");
			 }catch(Exception err) {
				 err.printStackTrace();
				 ExtentTestManager.getTest().log(LogStatus.FAIL, "No values entered inside 'Circuit Id' field");
			 }
			 Thread.sleep(3000);
	  }
	  
	  
	 public boolean fetchDeviceInterface_viewdevicepage(String application, String devicename) throws InterruptedException, DocumentException {
	
		 boolean clickLink=false;
		 
		 scrollToTop();
		 Thread.sleep(3000);
		 
		 click_commonMethod(application, "Action", "viewPCEdevice_Actiondropdown", xml);
			Thread.sleep(1000);
			
			click_commonMethod(application, "fetchDeviceInterfaceLink", "fetchDeviceinterfacelink_viewDevicePage", xml);
			Thread.sleep(2000);
			
			waitforPagetobeenable();
			
			
		//verify success Message
			String expectedValue="Fetch interfaces started successfully. Please check the sync status of this device";
			boolean successMessage=false;
		try {	
			successMessage=getwebelement(xml.getlocator("//locators/" + application + "/fetchDeviceInterace_SuccessMessage")).isDisplayed();
			String actualMessage=getwebelement(xml.getlocator("//locators/" + application + "/fetchdeviceInterface_successtextMessage")).getText();
			if(successMessage) {
				
				if(actualMessage.isEmpty()) {
					Log.info("No messages displays");
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Success message is not displaying");
				}
				if(actualMessage.contains(expectedValue)) {
					
				ExtentTestManager.getTest().log(LogStatus.PASS, " After clicking on 'Fetch Device Interface' link, success Message is displaying");
				Log.info(" After clicking on 'Fetch Device Interface' link, success Message is displaiyng as expected");
				
				ExtentTestManager.getTest().log(LogStatus.PASS, " Success Message displays as: "+actualMessage);
				Log.info(" Success Message displays as: "+actualMessage);
				
				//click on the 'click here' link
//				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/ClickhereLink_fetchInterface")));
//				Thread.sleep(3000);
				
				clickLink=true;
				
				}
				else if (actualMessage.equalsIgnoreCase(expectedValue)) {
					
					ExtentTestManager.getTest().log(LogStatus.PASS, " After clicking on 'Fetch Device Interface' link, success Message is displaiyng as expected");
					Log.info(" After clicking on 'Fetch Device Interface' link, success Message is displaiyng as expected");
					
					ExtentTestManager.getTest().log(LogStatus.PASS, " Success Message displays as: "+actualMessage);
					Log.info(" Success Message displays as: "+actualMessage);
					
					//click on the 'click here' link
//					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/ClickhereLink_fetchInterface")));
//					Thread.sleep(3000);
					
					clickLink=true;
				}
				else {
					ExtentTestManager.getTest().log(LogStatus.PASS, "After clicking on 'Fetch Device Interface' link, message displays as "+actualMessage);
					Log.info("After clicking on 'Fetch Device Interface' link, message displays as "+actualMessage);
				}
				
			}
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "After clicking on 'Fetch Device Interface' link, success message is not displaying");
			Log.info("After clicking on 'Fetch Device Interface' link, success message is not displaying");
			
		}
			return clickLink;
	 }

	 
	 public void manageNetwork(String application) throws InterruptedException, DocumentException {
		 
		 boolean manageNetworkHeader=false;
		 manageNetworkHeader= getwebelement(xml.getlocator("//locators/" + application + "/ManageNetworkHeaderName")).isDisplayed();
		 if(manageNetworkHeader) {
			 ExtentTestManager.getTest().log(LogStatus.PASS, " 'Manage Network' header name is displaying as expected");
		 }
	 }
	

	 public void verifyeditedinterfacevalue(String application, String interfaceName) throws InterruptedException {
		 
		 waitforPagetobeenable();
		 
		 List<WebElement> interfacedetails=getwebelements("//div[div[text()='"+ interfaceName +"']]/div");
		 
		 for(WebElement listOfInterfacenames : interfacedetails) {
			 
			 String ColumnNames=listOfInterfacenames.getAttribute("col-id");
			 String values= listOfInterfacenames.getText();
			 
			 ExtentTestManager.getTest().log(LogStatus.PASS, "After Editing interface, list of values displaying in interface table are: ");
			 ExtentTestManager.getTest().log(LogStatus.PASS, "value displaying for "+ ColumnNames + " is: "+values);
			 
			 Log.info("After Editing interface, list of values displaying in interface table are: ");
			 Log.info("value displaying for "+ ColumnNames + " is: "+values);
			 
		 }
	 }
	 
	 public boolean EquipmentCOnfigurationPanel(String application) throws InterruptedException, DocumentException {
		 
		 boolean EquipConfigPanel=false;
		 EquipConfigPanel = getwebelement(xml.getlocator("//locators/" + application + "/EquipementConfigurationPanel")).isDisplayed();
		 if(EquipConfigPanel) {
			 ExtentTestManager.getTest().log(LogStatus.PASS, "In 'view Site Order' page, 'Equipment Configuration' panel is displaying as expected for 'Actelis' Technology");
		Log.info( "In 'view Site Order' page, 'Equipment Configuration' panel is displaying as expected for 'Actelis' Technology");
		 }else {
			 ExtentTestManager.getTest().log(LogStatus.FAIL, "In 'view Site Order' page, 'Equipment Configuration' panel is not displaying for 'Actelis' Technology");
			 Log.info("In 'view Site Order' page, 'Equipment Configuration' panel is not displaying for 'Actelis' Technology");
			 
		 }
		return EquipConfigPanel;
		 
	 }
	 
	 public void equipConfiguration_Actelis_AddDevice(String application, String devicename, String vendor, String routerId, 
			 String manageAddress, String MEPid, String ETH_Port) throws InterruptedException, DocumentException, IOException {
		
		 ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying 'Add Actelis CPE device' ");
		 
		 scrolltoend();
		 Thread.sleep(3000);
		 Clickon(getwebelement(xml.getlocator("//locators/" + application + "/equipConfig_addCPEdevice")));
		 Thread.sleep(3000);
		 
		 boolean addActelisHeader=false; 
		 addActelisHeader=getwebelement(xml.getlocator("//locators/" + application + "/addActelisCPEpage_headerName")).isDisplayed();
		 if(addActelisHeader) {
			 ExtentTestManager.getTest().log(LogStatus.PASS, " 'Add Actelis CPE device' page is displaying as expected");
			 Log.info(" 'Add Actelis CPE device' page is displaying as expected");
			
			 Clickon(getwebelement("//span[text()='OK']"));
			 Thread.sleep(2000);
			 
			//Validate Warning Messages_Name Field
			 warningMessage_commonMethod(application, "devicenameFieldErrMSg_addCPE_Actelis" , "Name", xml);
			 
			//Validate Warning Messages_Router ID Field
			 warningMessage_commonMethod(application, "RouterIDFieldErrMSg_addCPE_Actelis",  "Router Id", xml);
			 
			//Validate Warning Messages_Management Address Field 
			 warningMessage_commonMethod(application, "manageAddressFieldErrMSg_addCPE_Actelis", "Management Address", xml);
			 
			//Name 
			 addtextFields_commonMethod(application, "Name", "nameField_addCPE_Actelis", devicename, xml);
			 
			//vendor/Model
			 addDropdownValues_commonMethod(application, "Vendor/Model", "vendorField_addCPE_Actelis", vendor, xml);
			 
			//Router Id
			 addtextFields_commonMethod(application, "Router Id", "RouterIdField_addCPE_Actelis", routerId, xml);
			 
			//Management Address
			 addtextFields_commonMethod(application, "Management Address", "managementAddressField_addCPE_Actelis", manageAddress, xml);
			 
			//MEP Id
			 addtextFields_commonMethod(application, "MEP Id", "MEPidField_addCPE_Actelis", MEPid, xml);
			 
			//ETH Port
			 addDropdownValues_commonMethod(application, "ETH Port", "ETHportField_addCPE_Actelis", ETH_Port, xml);
			 
			//OK button
			 OKbutton_AddSiteOrder(application);
			 
			//CAncel button 
		     cancelbutton_AddSiteOrder(application);
		     
		     
		 click_commonMethod(application, "OK", "okbutton", xml);
		 Thread.sleep(4000);
		 

		 }else {
			 ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Add Actelis CPE device' page is not displaying");
		 }
	 }
	 
	 
	 
	 public void verifyDataEnteredFordeviceCreation_Actelis(String application, String devicename, String vendorModel, String RouterID,
			 String manageAddress, String mepID, String ETH_Port) throws InterruptedException, DocumentException {
		
		  
		 waitforPagetobeenable();
		 
		 scrolltoend();
		 Thread.sleep(3000);
		 
		 
		 click_commonMethod(application, "view_Link", "actelis_EquipConfig_viewLink", xml);
		 Thread.sleep(5000);
		 
		 verifyEnteredvalues( "Name", devicename);
		 
//		 verifyEnteredvalues("Vendor/Model", vendorModel);
		 
		 verifyEnteredvalues("Router Id", RouterID);
		 
		 verifyEnteredvalues("Management Address", manageAddress);
		 
		 verifyEnteredvalues("MEP Id", mepID);
		 
		 verifyEnteredvalues("ETH Port", ETH_Port);
		 
	 }
	 
	 
	 public void AddDSLAMandHSL(String application, String DSLMdevice, String HSlname) throws InterruptedException, DocumentException, IOException {
		 
		  
		 waitforPagetobeenable();
		 
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/addDSLAMandHSL_xButton")));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on 'X' button under 'DSLAM device' dropdown");
			Log.info("Clicked on 'X' button under 'DSLAM device' dropdown");

			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/DSLM_Device_Select")),DSLMdevice);
			ExtentTestManager.getTest().log(LogStatus.PASS, DSLMdevice + " is entered under 'DSLAM device' dropdown");
			
			WebElement valueToSElect=getwebelement(xml.getlocator("//locators/" + application + "/selectDSLAMdeviceValue").replace("value", DSLMdevice));
			
			try {
				if(valueToSElect.isDisplayed()) {
					ExtentTestManager.getTest().log(LogStatus.PASS, DSLMdevice + " is displaying under 'DSLAM device' dropdown");
					Log.info(DSLMdevice + " is displaying under 'DSLAM device' dropdown");
					
					Clickon(valueToSElect);
					ExtentTestManager.getTest().log(LogStatus.PASS, DSLMdevice + " is selected under 'DSLAM device' dropdown");
					Log.info(DSLMdevice + " is selected under 'DSLAM device' dropdown");
					
					
					 
					waitforPagetobeenable();
					
					click_commonMethod(application, "List_HSL", "List_HSL_Link", xml);		//click on "List HSL" button

					selectRowForAddingInterface_Actelis(application, HSlname);		// select the Interface
					
				}else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, DSLMdevice + " is not displaying under 'DSLAM device' dropdown");
					Log.info(DSLMdevice + " is not displaying under 'DSLAM device' dropdown");
				}
				
			}catch(Exception e){
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, DSLMdevice + " is not displaying under 'DSLAM device' dropdown");
				Log.info(DSLMdevice + " is not displaying under 'DSLAM device' dropdown");
			}
			
			
	 }
	 
	 
	 public void selectRowForAddingInterface_Actelis(String Application, String interfacenumber)
				throws IOException, InterruptedException, DocumentException {
scrolltoend();
Thread.sleep(3000);

		 Log.info("check second time");
			int TotalPages;

			String TextKeyword = Gettext(
					getwebelement(xml.getlocator("//locators/" + Application + "/InterfaceToSelect_actelis_totalpage")));

			TotalPages = Integer.parseInt(TextKeyword);

			Log.info("Total number of pages in table is: " + TotalPages);

			ab:

			if (TotalPages != 0) {
				for (int k = 1; k <= TotalPages; k++) {

					// Current page
					String CurrentPage = Gettext(
							getwebelement(xml.getlocator("//locators/" + Application + "/InterfaceToSelect_actelis_currentpage")));
					int Current_page = Integer.parseInt(CurrentPage);


					Log.info("Currently we are in page number: " + Current_page);

					List<WebElement> results = getwebelements("//div[contains(text(),'"+ interfacenumber +"')]");
					
					int numofrows = results.size();
					Log.info("no of results: " + numofrows);
					boolean resultflag;

					if (numofrows == 0) {

						Clickon(getwebelement("//button[text()='Next']"));
						Thread.sleep(3000);

					}

					else {

						for (int i = 0; i < numofrows; i++) {

							try {

								resultflag = results.get(i).isDisplayed();
								Log.info("status of result: " + resultflag);
								if (resultflag) {
									Log.info(results.get(i).getText());
									results.get(i).click();
									ExtentTestManager.getTest().log(LogStatus.PASS, interfacenumber + " is selected under 'Add DSLAM and Device' page");
									
									Clickon(getwebelement("//span[text()='Next']"));
									Thread.sleep(3000);
									break ab;
								}

							} catch (StaleElementReferenceException e) {
								// TODO Auto-generated catch block
								// e.printStackTrace();
								results = driver.findElements(By.xpath("//div[contains(text(),'"+ interfacenumber +"')]"));
								numofrows = results.size();
								// results.get(i).click();
								Log.info("selected row is : " + i);
								ExtentTestManager.getTest().log(LogStatus.FAIL, "failure while selecting interface to add with service");
							}
						}
						break ab;
					}
				}
			} else {

				Log.info("No values available in table");
				Log.info("No Interfaces got fetched");
				ExtentTestManager.getTest().log(LogStatus.FAIL, " NO interfaces got fetched");
			}

		}

	 
	 public void showInterface_ActelisConfiguuration(String application) throws InterruptedException, DocumentException {
		 
		 Clickon(getwebelement(xml.getlocator("//locators/" + application + "/showInterface_ActelisCnfiguration")));
		 Thread.sleep(3000);
		 
	 }
	 
	 
	 public void deletInterface_ActelisConfiguration(String application, String interfaceName) throws InterruptedException, DocumentException {
		 
		//select the interface
		 Clickon(getwebelement("//div[text()='"+ interfaceName +"']"));
		 
		 //click on Action button
		 Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AcionButton_ActelisConfiguration")));
		 
		 //Remove Button
		 Clickon(getwebelement(xml.getlocator("//locators/" + application + "/removeButton_ActelisConfiguration")));
		 
		 boolean popupMessage=false;
		 popupMessage=getwebelement(xml.getlocator("//locators/" + application + "/popupMessage_forRemove_ActelisConfiguration")).isDisplayed();
		 
		 if(popupMessage) {
			 String actualmsg=getwebelement(xml.getlocator("//locators/" + application + "/popupMessage_forRemove_ActelisConfiguration")).getText();
			 ExtentTestManager.getTest().log(LogStatus.PASS, " On clicking remoe button, popup message displays as: "+ actualmsg);
			 Log.info(" On clicking remoe button, popup message displays as: "+ actualmsg);
			 
				 Clickon(getwebelement("//button[@class='btn btn-danger']"));
				 Thread.sleep(3000);
		 }else {
			 
			 ExtentTestManager.getTest().log(LogStatus.FAIL, " popup message does not display after clicking on 'Remove' button");
		 }
	 }
	
	 
	 public void successMessage_deleteInterfaceFromDevice_ActelisConfiguration(String application) throws InterruptedException, DocumentException {
			
			boolean successMessage=getwebelement(xml.getlocator("//locators/" + application + "/successMessage_ActelisConfiguration_removeInterface")).isDisplayed();
			String actualmessage=getwebelement(xml.getlocator("//locators/" + application + "/successMessage_ActelisConfiguration_removeInterface")).getText();
			if(successMessage) {
				
				ExtentTestManager.getTest().log(LogStatus.PASS, " Success Message for removing Interface is dipslaying as expected");
				Log.info( " Success Message for removing interface is dipslaying as expected");
				
				ExtentTestManager.getTest().log(LogStatus.PASS, "Message displays as: "+actualmessage);
				Log.info("Message displays as: "+actualmessage);
				
			}else {
				ExtentTestManager.getTest().log(LogStatus.PASS, " Success Message for removing Interface is not dipslaying");
				Log.info( " Success Message for removing Interface is not dipslaying");
			}
		}
	 
	 
	 
	/**
	 * verify the value used for creating service or site order or device in view page 
	 * @param label
	 * @param expectedValue
	 * @throws InterruptedException
	 */
		@SuppressWarnings("unused")
		public void verifyEnteredvalues(String labelname, String ExpectedText) throws InterruptedException {

			String text = null;
			WebElement element = null;

			try {
				Thread.sleep(1000);
				element = getwebelement("//div[div[label[contains(text(),'"+ labelname + "')]]]/div[2]");
				String emptyele = element.getText().toString();

				if(element==null)
				{
					ExtentTestManager.getTest().log(LogStatus.FAIL, labelname+" not found");
					Log.info(labelname+" not found");
				}
				else if (emptyele!=null && emptyele.isEmpty()) {
//					ExtentTestManager.getTest().log(LogStatus.PASS,  labelname + "' value is empty");
					
					emptyele= "Null";
					
					sa.assertEquals(emptyele, ExpectedText, labelname + " value is not displaying as expected");
					
					if(emptyele.equalsIgnoreCase(ExpectedText)) {
						
						ExtentTestManager.getTest().log(LogStatus.PASS, "The Expected value for '"+ labelname +"' field  is same as the Acutal value. It is id displaying blank");
						Log.info("The Expected value for '\"+ labelname +\"' field  is same as the Acutal value. It is displaying blank");
						
					}else {
						ExtentTestManager.getTest().log(LogStatus.FAIL,"The Expected value '"+ExpectedText+"' is not same as the Acutal value '"+text+"'");
						Log.info(" The Expected value '"+ExpectedText+"' is not same as the Acutal value '"+text+"'");
					}
					

				}else 
				{   
					text = element.getText();
					if( text.contains("-")) {
						
						String[] actualTextValue=text.split(" ");
						String[] expectedValue =ExpectedText.split(" ");
						
						if(expectedValue[0].equalsIgnoreCase(actualTextValue[0])) {
							ExtentTestManager.getTest().log(LogStatus.PASS," The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
							Log.info(" The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
						}
						else if(expectedValue[0].contains(actualTextValue[0])) {
							ExtentTestManager.getTest().log(LogStatus.PASS,"The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
							Log.info("The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
						
						}
						else
						{
							ExtentTestManager.getTest().log(LogStatus.FAIL,"The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is not same as the Acutal value '"+text+"'");
							Log.info("The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is not same as the Acutal value '"+text+"'");
						}
						
					}else {
						if(ExpectedText.equalsIgnoreCase(text)) {
							ExtentTestManager.getTest().log(LogStatus.PASS," The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
							Log.info(" The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
						}
						else if(ExpectedText.contains(text)) {
							ExtentTestManager.getTest().log(LogStatus.PASS,"The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
							Log.info("The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
						
						}
						else
						{
							ExtentTestManager.getTest().log(LogStatus.FAIL,"The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is not same as the Acutal value '"+text+"'");
							Log.info("The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is not same as the Acutal value '"+text+"'");
						}
					}
					
					
				}
			}catch (Exception e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " field is not displaying");
				Log.info(labelname + " field is not displaying");
			}

		}
		
		
		/**
		 * verify the entered device name value 
		 * @param label
		 * @param expectedValue
		 * @param devicename
		 * @throws InterruptedException
		 */
		public void verifyEnteredvalues_deviceName(String label, String expectedValue, String devicename) throws InterruptedException {

			try {
				
				boolean deviceName = getwebelement("//div[div[label[text()='Name']]]//div[contains(text(),'"+ expectedValue +"')]").isDisplayed();
				
				if(deviceName) {
					Log.info("device name is displaying as expected");
					
					ExtentTestManager.getTest().log(LogStatus.PASS, "Device name is displaying as:  "+ devicename + "as expected");
				}else {
					
					WebElement Actualvalue=getwebelement("//div[div[label[text()='Name']]]//div[2]");
					Log.info("Device name is not displaying as expected");
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Device name is displaying as:  "+ Actualvalue.getText());
				}
			} catch(AssertionError err) {
				err.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, label + " value is not displaying as expected ");
			} catch (NoSuchElementException e) {
				Log.info("value not displayed for " + label);
				
				ExtentTestManager.getTest().log(LogStatus.FAIL, "value not displayed for : " + label);
				
			}
		}
		
		
		public void verifyenteredvaluesForEditPage_textField(String application, String labelname, String ExpectedValue) throws InterruptedException {
			
		 boolean actualvalueToBeDisplayed=false;	
		 try {
		   actualvalueToBeDisplayed=getwebelement("//div[label[text()='"+ labelname +"']]//input[@value='"+ ExpectedValue +"']").isDisplayed();
		   
		   if(actualvalueToBeDisplayed) {
			   
			   Log.info("Value is displaying as expected for the field: "+labelname);
			   ExtentTestManager.getTest().log(LogStatus.PASS, " Value displaying for "+labelname + " text field is: "+ExpectedValue +" . It is dipslaying as expected");
			   
		   }else {
			   
			   Log.info("Value is not displaying as expected for the text field: "+labelname);
			   ExtentTestManager.getTest().log(LogStatus.FAIL, "Value is not displaying as expected for the text field: "+labelname);
		   }
		 }catch(NoSuchElementException e) {
			 e.printStackTrace();
			 ExtentTestManager.getTest().log(LogStatus.FAIL, "Value is not displaying as expected for the text field: "+labelname);
		 }
		}
		
		
		public void verifyenteredvaluesForEditPage_Dropdown(String application, String labelname, String ExpectedValue) throws InterruptedException {
		
			boolean actualvalue=false;
		try {	
			actualvalue=getwebelement("//div[label[text()='"+ labelname +"']]//div[text()='"+ ExpectedValue +"']").isDisplayed();
			
			 if(actualvalue) {
				   
				   Log.info("Value is displaying as expected for the field: "+labelname);
				   ExtentTestManager.getTest().log(LogStatus.PASS, " Value displaying for "+labelname + " dropdown field is: "+ExpectedValue+" . And it is displaying as expected");
				   
			   }else {
				   
				   Log.info("Value is not displaying as expected for the text field: "+labelname);
				   ExtentTestManager.getTest().log(LogStatus.FAIL, "Value is not displaying as expected for the text field: "+labelname);
			   }
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Value is not displaying as expected for the text field: "+labelname);
		}
		}
		
		
		/**
		 *  verify value for non-editable fields under Edit Interface
		 * @param application
		 * @param labelname
		 * @param ExpectedValue
		 * @throws InterruptedException
		 */
		public void verifyenteredvaluesForEditPage_noneditableFields(String application, String labelname, String ExpectedValue) throws InterruptedException {
			boolean actualvalue=false;
		try {	
			actualvalue=getwebelement("//div[div[label[text()='" + labelname + "']]]//div[text()='"+ ExpectedValue +"']").isDisplayed();
			if(actualvalue) {
				   
				   Log.info("Value is displaying as expected for the field: "+labelname);
				   ExtentTestManager.getTest().log(LogStatus.PASS, " Value displaying for "+labelname + " field is: "+ ExpectedValue +" . And it is displaying as expected");
				   
			   }else {
				   
				   Log.info("Value is not displaying as expected for the text field: "+labelname);
				   ExtentTestManager.getTest().log(LogStatus.FAIL, "Value is not displaying as expected for the text field: "+labelname);
			   }
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Value is not displaying as expected for the text field: "+labelname);
		}
			
		}
		
		
		public void amnvalidator_viewServicepage(String application) throws InterruptedException, DocumentException {
			
			   click_commonMethod(application, "Action", "Editservice_actiondropdown", xml);
			   Thread.sleep(3000);
			   click_commonMethod(application, "Manage Subnet", "Editservice_AMNvalidator", xml);
			   Thread.sleep(2000);	
		}

		
		public void dump_viewServicepage(String application) throws InterruptedException, DocumentException, IOException {
			
			
			ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying 'dump'");
			
			 WebElement orderPanel= getwebelement(xml.getlocator("//locators/" + application + "/viewServicepage_OrderPanel"));
				ScrolltoElement(orderPanel);
				Thread.sleep(3000);
			
				click_commonMethod(application, "Action", "Editservice_actiondropdown", xml);
			   Thread.sleep(3000);
			   click_commonMethod(application, "Dump", "Editservice_Dumplink", xml);
			   Thread.sleep(2000);
		
			    
			   
			   waitforPagetobeenable();
			   
			   WebElement dumpelement= getwebelement(xml.getlocator("//locators/" + application + "/dump_container"));
			   
			   String dumpvalue=dumpelement.getText();
			  
			   if(dumpvalue.isEmpty()) {
				   
				   ExtentTestManager.getTest().log(LogStatus.INFO, "NO values dipslaying under 'Dump' page");
				   Log.info("NO values dipslaying under 'Dump' page");
				   
			   }else{
				  ExtentTestManager.getTest().log(LogStatus.PASS, "Dump value is displaying as:   "+ dumpvalue); 
				  Log.info("Dump value is displaying as:   "+ dumpvalue);
			   }
			   
			   driver.navigate().back();
			   Thread.sleep(5000);
		
		}
		
		

		/**
		 *   Click on view device button under Equipment in view site order page	
		 * @param application
		 * @param devicename
		 * @throws InterruptedException
		 */
		
		public void IntEquip_clickonviewButton(String application , String devicename) throws InterruptedException, DocumentException {
				
			 
			waitforPagetobeenable();
			
				clickOnBankPage();
				scrollToTop();
				Thread.sleep(1000);
				boolean viewpage=false;
				try {	
					viewpage=getwebelement(xml.getlocator("//locators/" + application + "/viewCPEdevicepage_devices")).isDisplayed();
					
					if(viewpage) {
						Log.info("In view page");
					}else {
				
						scrolltoend();
						Thread.sleep(3000);
						
						Clickon(getwebelement("//div[div[div[text()='Intermediate Equipment']]]//div[div[div[contains(@title,'"+ devicename +"')]]]//span[text()='View']"));
						Thread.sleep(3000); 
						
						ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on view link under 'Intermediate Equipment' panel");
						Log.info("Clicked on view link under 'Intermediate Equipment' panel");
					}
				}catch(Exception e) {
					e.printStackTrace();
					
					scrolltoend();
					Thread.sleep(3000);
					
					Clickon(getwebelement("//div[div[div[text()='Intermediate Equipment']]]//div[div[div[contains(@title,'"+ devicename +"')]]]//span[text()='View']"));
					Thread.sleep(3000);
					
					ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on view link under 'Intermediate Equipment' panel");
					Log.info("Clicked on view link under 'Intermediate Equipment' panel");
				}
		}
		
		
	/**
	 *   Click on view device button under equipment in view site order page	
	 * @param application
	 * @param devicename
	 * @throws InterruptedException
	 */
		public void Equip_clickonviewButton(String application, String devicename) throws InterruptedException {
			
			clickOnBankPage();
			scrollToTop();
			Thread.sleep(1000);
			boolean viewpage=false;
			try {	
				viewpage=getwebelement(xml.getlocator("//locators/" + application + "/viewCPEdevicepage_devices")).isDisplayed();
				
				if(viewpage) {
					Log.info("In view page");
				}else {
			
					scrolltoend();
					Thread.sleep(3000);
					
					Clickon(getwebelement("//div[div[div[text()='Equipment']]]//div[div[div[contains(@title,'"+ devicename +"')]]]//span[text()='View']"));
					Thread.sleep(3000); 
				}
			}catch(Exception e) {
				e.printStackTrace();
				
				scrolltoend();
				Thread.sleep(3000);
				
				Clickon(getwebelement("//div[div[div[text()='Equipment']]]//div[div[div[contains(@title,'"+ devicename +"')]]]//span[text()='View']"));
				Thread.sleep(3000);
			}
			
		}
		
	//click on toggle button to add new country, city, site	
		public void Clickon_addToggleButton(String application, String xpath) throws InterruptedException, DocumentException {
			
			//Add Toggle button
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")));
			Thread.sleep(5000);
		}
		
	/**
	 * Splitting required value from complete input passed for CSR name in site Order page	
	 */
	 public String splitPhysicalSitealue(String application, String siteValue) {
		 
		 String value=null;
			int len=siteValue.length();
			
		if(siteValue.startsWith("(")) {
			value=siteValue.substring(1, len-1);
		}
		else if(siteValue.startsWith(" (")) {
			value=siteValue.substring(2, len-1);
		}
		else {
			value=siteValue;
		}
			return value;
	 }

	 /**
		 * Router Tool Panel
		 * @throws InterruptedException 
		 * @throws DocumentException 
	 * @throws IOException 
		 */
		public void routerPanel(String application, String commandIPv4, String ipAddress)
				throws InterruptedException, DocumentException, IOException {
			
			scrollToTop();
			Thread.sleep(1000);
			WebElement vendorModel=getwebelement(xml.getlocator("//locators/" + application + "/fetchVendorModelvalue"));
			String vendorValue=Gettext(vendorModel);
			
		if(vendorValue.startsWith("Overture")) {	
			
			scrolltoview(vendorModel);
			Thread.sleep(1000);
			
		//Command IPV4	
			addDropdownValues_commonMethod(application, "Command IPV4", "commandIPV4_dropdown" , commandIPv4, xml);
			
			hostnametextField_IPV4(application, commandIPv4, ipAddress);
			
			executeCommandAndFetchTheValue(application, "executebutton_Ipv4");
		}
		else {
			ExtentTestManager.getTest().log(LogStatus.INFO, " 'Router' panel displays only for Overture device");
		}
	}

		/**
		 * Fetches Vendor Model value from view device page
		 * @param application
		 * @return
		 * @throws InterruptedException
		 * @throws IOException
		 * @throws DocumentException
		 */
		public String fetchVendorModelValue(String application) throws InterruptedException, IOException, DocumentException {
			
			scrollToTop();
			Thread.sleep(1000);
			WebElement vendorModel=getwebelement(xml.getlocator("//locators/" + application + "/fetchVendorModelvalue"));
			String vendorValue=Gettext(vendorModel);
			
			Log.info("vendor value "+vendorValue);
			return vendorValue;
		}
		
		public void executeCommandAndFetchTheValue(String application, String executeButton) throws InterruptedException, DocumentException {
			
			click_commonMethod(application, "Execute", executeButton, xml);
			
		boolean resultField=false;	
	try {	
		resultField=getwebelement(xml.getlocator("//locators/" + application + "/result_textArea")).isDisplayed();
		if(resultField) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Result' text field is displaying");
			Log.info( "'Result' text field is displaying");
			
			String remarkvalue=getwebelement(xml.getlocator("//locators/" + application + "/result_textArea")).getText();
			ExtentTestManager.getTest().log(LogStatus.PASS, "value under 'Result' field displaying as "+ remarkvalue);
			Log.info("value under 'Result' field displaying as "+ remarkvalue);
		
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'Result' text field is not displaying");
			Log.info( "'Result' text field is not displaying");
		}
	}catch(Exception e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, "'Result' text field is not displaying");
		Log.info("'Result' text field is not displaying");
	}
			
		}
		
		
		public void hostnametextField_IPV4(String application, String command_ipv4, String ipAddress) {
			boolean IPV4availability=false;
			try {  
				IPV4availability=getwebelement(xml.getlocator("//locators/" + application + "/commandIPv4_hostnameTextfield")).isDisplayed();
			  
			  if(IPV4availability) {
				  
				  addtextFields_commonMethod(application, "IP Address or Hostname", "commandIPv4_hostnameTextfield", ipAddress, xml);
				  
			  }else {
				  	ExtentTestManager.getTest().log(LogStatus.INFO, "'Hostname or IpAddress' for 'IPv4' text field is not displaying for "+ command_ipv4);
					Log.info("'Hostname or IpAddress' for 'IPv4' text field is not displaying for "+ command_ipv4);
			  }
			}catch(Exception e) {
				e.printStackTrace();
				
				ExtentTestManager.getTest().log(LogStatus.INFO, "'Hostname or IpAddress' for 'IPv4' text field is not displaying for "+ command_ipv4);
				Log.info("'Hostname or IpAddress' for 'Ipv4' text field is not displaying for "+ command_ipv4);
			}
		}
	 
	
		/**
		 * 	
		 * @param application
		 * @throws DocumentException 
		 * @throws InterruptedException 
		 */
			public void clickOnBreadCrump(String application, String breadCrumpLink) throws InterruptedException, DocumentException {
				
				waitforPagetobeenable();
				
				scrollToTop();
				Thread.sleep(1000);
				WebElement breadcrumb=null;
		
				try {
				breadcrumb=getwebelementNoWait(xml.getlocator("//locators/" + application + "/breadcrump").replace("value", breadCrumpLink));
				
				if(breadcrumb.isDisplayed()) {
					click_commonMethod_PassingWebelementDirectly_forBreadcrumb(application, "Breadcrump", breadcrumb, xml);
					Thread.sleep(1000);
				}else {
					Log.info("Breadcrumb is not displaying for the element "+ breadcrumb);
				}
			}catch(Exception e) {
				e.printStackTrace();
				Log.info("Breadcrumb is not displaying for the element "+ breadcrumb);
			}
		}
			
			
			/**
			 *   For Dropdown Bearer Type method
			 * @param application
			 * @param labelname
			 * @param xpath
			 * @param expectedValueToAdd
			 * @param xml
			 * @throws InterruptedException
			 * @throws DocumentException
			 */
			public void addDropdownValues_bearerType(String application, String labelname, String xpath, String expectedValueToAdd, XMLReader xml) throws InterruptedException, DocumentException {
				  boolean availability=false;
				try {  
				  availability=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).isDisplayed();
				  if(availability) {
					  ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " dropdown is displaying");
					  Log.info(labelname + " dropdown is displaying");
					  
					  if(expectedValueToAdd.equalsIgnoreCase("null")) {
						  
						  ExtentTestManager.getTest().log(LogStatus.PASS, " No values selected under "+ labelname + " dropdown");
						  Log.info(" No values selected under "+ labelname + " dropdown");
					  }else {
						  
						  Clickon(getwebelement("//div[label[text()='"+ labelname +"']]//div[text()='�']"));
						  Thread.sleep(2000);
						  
						  //verify list of values inside dropdown
						  List<WebElement> listofvalues = driver
									.findElements(By.xpath("//div[@class='sc-ifAKCX oLlzc']"));
						  
						  ExtentTestManager.getTest().log(LogStatus.PASS, " List of values inside "+ labelname + " dropdown is:  ");
						  Log.info( " List of values inside "+ labelname + "dropdown is:  ");
						  
							for (WebElement valuetypes : listofvalues) {
										Log.info("service sub types : " + valuetypes.getText());
										ExtentTestManager.getTest().log(LogStatus.PASS," " + valuetypes.getText());
										Log.info(" " + valuetypes.getText());
							}
							
							Thread.sleep(2000);
						SendKeys(getwebelement("//div[label[text()='"+ labelname +"']]//input"), expectedValueToAdd);	
						Thread.sleep(4000);
							
						  Clickon(getwebelement("//div[label[contains(text(),'Bearer Type')]]//following-sibling::div//div[contains(text(),'"+expectedValueToAdd+"')]"));
						  Thread.sleep(2000);
						  
						  String actualValue=getwebelement("//label[text()='"+ labelname +"']/following-sibling::div//span").getText();
						  ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " dropdown value selected as: "+ actualValue );
						  Log.info( labelname + " dropdown value selected as: "+ actualValue);
						  
					  }
				  }else {
					  ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " is not displaying");
					  Log.info(labelname + " is not displaying");
				  }
				}catch(NoSuchElementException e) {
					ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " is not displaying");
					  Log.info(labelname + " is not displaying");
				}catch(Exception ee) {
					ee.printStackTrace();
					ExtentTestManager.getTest().log(LogStatus.FAIL, " NOt able to perform selection under "+ labelname + " dropdown");
					Log.info(" NO value selected under "+ labelname + " dropdown");
				}
			}
			
			
			// addDropdownValues_commonMethod(application, "Total Circuit Bandwidth", "Equipment_configureTotalcircuitbandwidth", totalbandwidth, xml);
			
			/**
			 *   For Dropdown Total Circuit Bandwidth method
			 * @param application
			 * @param labelname
			 * @param xpath
			 * @param expectedValueToAdd
			 * @param xml
			 * @throws InterruptedException
			 * @throws DocumentException
			 */
			public void addDropdownValues_totalCircuitBandwidth(String application, String labelname, String xpath, String expectedValueToAdd, XMLReader xml) throws InterruptedException, DocumentException {
				  boolean availability=false;
				try {  
				  availability=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).isDisplayed();
				  if(availability) {
					  ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " dropdown is displaying");
					  Log.info(labelname + " dropdown is displaying");
					  
					  if(expectedValueToAdd.equalsIgnoreCase("null")) {
						  
						  ExtentTestManager.getTest().log(LogStatus.PASS, " No values selected under "+ labelname + " dropdown");
						  Log.info(" No values selected under "+ labelname + " dropdown");
					  }else {
						  
						  Clickon(getwebelement("//div[label[text()='"+ labelname +"']]//div[text()='�']"));
						  Thread.sleep(2000);
						  
						  //verify list of values inside dropdown
						  List<WebElement> listofvalues = driver
									.findElements(By.xpath("//div[@class='sc-ifAKCX oLlzc']"));
						  
						  ExtentTestManager.getTest().log(LogStatus.PASS, " List of values inside "+ labelname + " dropdown is:  ");
						  Log.info( " List of values inside "+ labelname + "dropdown is:  ");
						  
							for (WebElement valuetypes : listofvalues) {
										Log.info("service sub types : " + valuetypes.getText());
										ExtentTestManager.getTest().log(LogStatus.PASS," " + valuetypes.getText());
										Log.info(" " + valuetypes.getText());
							}
							
							Thread.sleep(2000);
						SendKeys(getwebelement("//div[label[text()='"+ labelname +"']]//input"), expectedValueToAdd);	
						Thread.sleep(4000);
							
						  Clickon(getwebelement("//div[label[contains(text(),'Total Circuit Bandwidth')]]//following-sibling::div//div[contains(text(),'"+expectedValueToAdd+"')]"));
						  Thread.sleep(2000);
						  
						  String actualValue=getwebelement("//label[text()='"+ labelname +"']/following-sibling::div//span").getText();
						  ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " dropdown value selected as: "+ actualValue );
						  Log.info( labelname + " dropdown value selected as: "+ actualValue);
						  
					  }
				  }else {
					  ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " is not displaying");
					  Log.info(labelname + " is not displaying");
				  }
				}catch(NoSuchElementException e) {
					ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " is not displaying");
					  Log.info(labelname + " is not displaying");
				}catch(Exception ee) {
					ee.printStackTrace();
					ExtentTestManager.getTest().log(LogStatus.FAIL, " NOt able to perform selection under "+ labelname + " dropdown");
					Log.info(" NO value selected under "+ labelname + " dropdown");
				}
			}
			
			
			/**
			 * In view device page, this method is used to find the test commands and its status
			 * @param application
			 * @throws InterruptedException
			 */
			public void testStatus(String application) throws InterruptedException {
				
				Thread.sleep(10000);
				
				 
				waitforPagetobeenable();
				
				ExtentTestManager.getTest().log(LogStatus.INFO, "Checking Test Status");
				
				String element=null;
				String status=null;
				
				List<WebElement> testlist=getwebelements("//tbody/tr");
				int listSize=testlist.size();
				
				
				for(int i=1; i<=listSize; i++) {
				  try {	
					element=getwebelement("(//tbody/tr["+ i +"]/td)[1]").getText();
					
					if(element.isEmpty()) {
						
					}else {
						ExtentTestManager.getTest().log(LogStatus.PASS, "Test Name is displaying as: "+element);
						Log.info("Test Name is displaying as: "+element);
						
						
						status=getwebelement("(//tbody/tr["+ i +"]/td)[2]/div").getAttribute("class");
						Log.info("status displays as: "+status);
						
						if(status.contains("red")) {
							ExtentTestManager.getTest().log(LogStatus.PASS, element + " status colour dipslays as: red");
							Log.info(element + " status colour dipslays as: red");
						}
						else if(status.contains("green")) {
							ExtentTestManager.getTest().log(LogStatus.PASS, element + " status colour dipslays as: green");
							Log.info(element + " status colour dipslays as: green");
						}
					}
				  }catch(Exception e) {
					  e.printStackTrace();
					  ExtentTestManager.getTest().log(LogStatus.FAIL, "Failure while fetching test status");
				  }
				}
			}

			
			public void verifyFieldsandSelectCPEdevicefortheserviceselected_existingDevice(String application, String existingDeviceName,
					String technologySelected, String vpntopology, String interfacespeed)
					throws InterruptedException, DocumentException, IOException {
				
				scrolltoend();
				Thread.sleep(1000);
				
				ExtentTestManager.getTest().log(LogStatus.INFO,"Select existing CPE device");
				
				click_commonMethod(application, "Add Device", "CPEdevice_adddevicelink", xml);
				Thread.sleep(2000);
				waitforPagetobeenable();
				 
				if((technologySelected.equalsIgnoreCase("Atrica")) && (vpntopology.equals("Hub&Spoke")) && (interfacespeed.equals("1GigE"))) {
					selectTechnology_HubAndSpoke(application);
				}
				waitforPagetobeenable();
				
				click_commonMethod(application, "Select Device", "existingDevice_SelectDeviceToggleButton", xml);
				Thread.sleep(1000);
				waitforPagetobeenable();
				
				addDropdownValues_forExistingDevice(application, "Choose a Device", "chooseAdeviceDropdown", existingDeviceName, xml);
				waitforPagetobeenable();
				
				scrolltoend();
				click_commonMethod(application, "OK", "obutton_spanTag", xml);
				}
			
			
			/**
			 *   For Dropdown _  Add Existig device
			 * @param application
			 * @param labelname
			 * @param xpath
			 * @param expectedValueToAdd
			 * @param xml
			 * @throws InterruptedException
			 * @throws DocumentException
			 */
			public void addDropdownValues_forExistingDevice(String application, String labelname, String xpath, String expectedValueToAdd, XMLReader xml) throws InterruptedException, DocumentException {
				  boolean availability=false;
				try {  
					
					 
					waitforPagetobeenable();
					
				  availability=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).isDisplayed();
				  if(availability) {
					  ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " dropdown is displaying");
					  Log.info(labelname + " dropdown is displaying");
					  
					  if(expectedValueToAdd.equalsIgnoreCase("null")) {
						  
						  ExtentTestManager.getTest().log(LogStatus.PASS, " No values selected under "+ labelname + " dropdown");
						  Log.info(" No values selected under "+ labelname + " dropdown");
					  }else {
						  
						  Clickon(getwebelement("//div[label[text()='"+ labelname +"']]//div[text()='�']"));
						  Thread.sleep(2000);
						  
						   
						  waitforPagetobeenable();
						  
							Thread.sleep(2000);
						SendKeys(getwebelement("//div[label[text()='"+ labelname +"']]//input"), expectedValueToAdd);	
						Thread.sleep(4000);
							
						  Clickon(getwebelement("(//div[contains(text(),'"+ expectedValueToAdd +"')])[1]"));
						  Thread.sleep(2000);
						  
						  String actualValue=getwebelement("//label[text()='"+ labelname +"']/following-sibling::div//span").getText();
						  ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " dropdown value selected as: "+ actualValue );
						  Log.info( labelname + " dropdown value selected as: "+ actualValue);
						  
					  }
				  }else {
					  ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " is not displaying");
					  Log.info(labelname + " is not displaying");
				  }
				}catch(NoSuchElementException e) {
					ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " is not displaying");
					  Log.info(labelname + " is not displaying");
				}catch(Exception ee) {
					ee.printStackTrace();
					ExtentTestManager.getTest().log(LogStatus.FAIL, " NOt able to perform selection under "+ labelname + " dropdown");
					Log.info(" NO value selected under "+ labelname + " dropdown");
				}
			}
			
			
			public void verifyFieldsandSelectCPEdevice_existingDevice_IntEquipment(String application, String existingDeviceName)
					throws InterruptedException, DocumentException, IOException {
				
				ExtentTestManager.getTest().log(LogStatus.INFO,"Select existing CPE device");
				  
				 
				waitforPagetobeenable();
				
				scrollToTop();
				
				click_commonMethod(application, "Select Device", "existingDevice_SelectDeviceToggleButton", xml);
				Thread.sleep(7000);
				 
				waitforPagetobeenable();
				
				addDropdownValues_forExistingDevice(application, "Choose a Device", "chooseAdeviceDropdown", existingDeviceName, xml);
				waitforPagetobeenable();
				scrolltoend();
				click_commonMethod(application, "OK", "obutton_spanTag", xml);
				}
			
			
			
			 public void verifyValuesforCPEexistingdevice_1G_Equipment(String application) throws InterruptedException, DocumentException	{
		    	   
		    	   fetchValueFromViewPage(application, "Name");
		    	   fetchValueFromViewPage(application, "Router Id");
		    	   fetchValueFromViewPage(application, "Vendor/Model");
		    	   fetchValueFromViewPage(application, "Snmpro");
		    	   fetchValueFromViewPage(application, "Management Address");
		    	   fetchValueFromViewPage(application, "MEP Id");
		    	   fetchValueFromViewPage(application, "Power Alarm");
		    	   fetchValueFromViewPage(application, "Media Selection");
		    	   fetchValueFromViewPage(application, "Link Lost Forwarding");
		    	   fetchValueFromViewPage(application, "MAC Address");
//		    	   fetchValueFromViewPage(application, "Serial Number");
		       }
			 
			 
			 
			 /**
				 *  Fetching the value from View device page
				 * @param application
				 * @param labelname
				 * @throws InterruptedException
				 * @throws DocumentException
				 */
				@SuppressWarnings("unused")
				public void fetchValueFromViewPage(String application, String labelname) throws InterruptedException, DocumentException { 

					String text = null;
					WebElement element = null;

					try {
						element = getwebelementNoWait("//div[div[label[contains(text(),'"+ labelname + "')]]]/div[2]");
						String emptyele = element.getText().toString();

						if(element==null)
						{
							ExtentTestManager.getTest().log(LogStatus.FAIL, labelname+" not found");
							Log.info(labelname+" not found");
						}
						else if (emptyele!=null && emptyele.isEmpty()) {
							
							ExtentTestManager.getTest().log(LogStatus.PASS, "No value displaying under "+ labelname);
							Log.info("No value displaying under "+ labelname);
						}
//							
						else {
							element.getText();
							ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " value is displaying as: "+ emptyele);
							Log.info(labelname + " value is displaying as: "+ emptyele);
							
						} 
						
					}catch (Exception e) {
						e.printStackTrace();
						ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " field is not displaying");
						Log.info(labelname + " field is not displaying");
					}
				}
				
	public void verifyValuesforCPEexistingdevice_10G_Equipment(String application)
			throws InterruptedException, DocumentException {

		fetchValueFromViewPage(application, "Name");
		fetchValueFromViewPage(application, "Router Id");
		fetchValueFromViewPage(application, "Vendor/Model");
		fetchValueFromViewPage(application, "Snmpro");
		fetchValueFromViewPage(application, "Management Address");
		fetchValueFromViewPage(application, "MEP Id");
		fetchValueFromViewPage(application, "Power Alarm");
		fetchValueFromViewPage(application, "Media Selection");
		fetchValueFromViewPage(application, "Link Lost Forwarding");
		fetchValueFromViewPage(application, "Serial Number");
	}

	public String fetchdevicename_InviewPage(String application)
			throws InterruptedException, DocumentException, IOException {

		scrollToTop();
		Thread.sleep(1000);

		WebElement devicename = getwebelement(xml.getlocator("//locators/" + application + "/fetchDeviceValue"));
		String devieName = Gettext(devicename);
		Log.info("device name  "+ devicename);

		return devieName;
	}

	public String fetchManagementAddressValue_InviewPage(String application)
			throws InterruptedException, DocumentException, IOException {

		scrollToTop();
		Thread.sleep(1000);

		WebElement manageAdres = getwebelement(
				xml.getlocator("//locators/" + application + "/fetchmanagementAddressvalue"));
		String managementAddress = Gettext(manageAdres);

		return managementAddress;
	}

	public String fetchCountryValue_InviewPage(String application)
			throws InterruptedException, DocumentException, IOException {

		scrollToTop();
		Thread.sleep(1000);

		WebElement manageAdres = getwebelement(xml.getlocator("//locators/" + application + "/fetchCountryValue"));
		String managementAddress = Gettext(manageAdres);

		return managementAddress;
	}
	
	
	public void verifyValuesforCPEexistingdevice_1G_intEquipment(String application) throws InterruptedException, DocumentException	{
 	   
 	   fetchValueFromViewPage(application, "Name");
 	   fetchValueFromViewPage(application, "Router Id");
 	   fetchValueFromViewPage(application, "Vendor/Model");
 	   fetchValueFromViewPage(application, "Snmpro");
 	   fetchValueFromViewPage(application, "Management Address");
 	   fetchValueFromViewPage(application, "MEP Id");
 	   fetchValueFromViewPage(application, "Power Alarm");
 	   fetchValueFromViewPage(application, "Media Selection");
 	   fetchValueFromViewPage(application, "Link Lost Forwarding");
 	   fetchValueFromViewPage(application, "MAC Address");
 	   fetchValueFromViewPage(application, "Serial Number");
 	   fetchValueFromViewPage(application, "Country");
 	   fetchValueFromViewPage(application, "City");
 	   fetchValueFromViewPage(application, "Site");
 	   
    }
	
	
	public void verifyValuesforCPEexistingdevice_10G_intEquipment(String application) throws InterruptedException, DocumentException	{
 	   
 	   fetchValueFromViewPage(application, "Name");
 	   fetchValueFromViewPage(application, "Router Id");
 	   fetchValueFromViewPage(application, "Vendor/Model");
 	   fetchValueFromViewPage(application, "Snmpro");
 	   fetchValueFromViewPage(application, "Management Address");
 	   fetchValueFromViewPage(application, "MEP Id");
 	   fetchValueFromViewPage(application, "Power Alarm");
 	   fetchValueFromViewPage(application, "Media Selection");
 	   fetchValueFromViewPage(application, "Link Lost Forwarding");
 	   fetchValueFromViewPage(application, "Serial Number");
 	   fetchValueFromViewPage(application, "Country");
 	   fetchValueFromViewPage(application, "City");
 	   fetchValueFromViewPage(application, "Site");
    }
    
	
	
	public void pamTest(String application, String serviceID) throws InterruptedException, DocumentException {
		
		click_commonMethod(application, "PAMtest_Link", "PAMtest_Link" , xml);
		
		boolean pamTestPage=false;
		try {	
		pamTestPage=getwebelement(xml.getlocator("//locators/" + application + "/PAMtest_popupPage")).isDisplayed();
		if(pamTestPage) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'PAM Test' popup page is displaying");
			Log.info("'PAM Test' popup page is displaying");
			
		//Type Value	
			String typeValue=Gettext(getwebelement(xml.getlocator("//locators/" + application + "/PAMTest_TypeFieldValue")));
			if(typeValue.isEmpty()) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'Type' field");
				Log.info("No values displaying under 'Type' field");
			}else {
				ExtentTestManager.getTest().log(LogStatus.PASS, "Under 'Type' field, value is dispaying as: "+typeValue);
				Log.info("Under 'Type' field, value is dispaying as: "+typeValue);
			}
			
		//Service
			String serviceValue=Gettext(getwebelement(xml.getlocator("//locators/" + application + "/PAMTest_ServiceValue")));
			if(serviceValue.isEmpty()) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'Service' field");
				Log.info("No values displaying under 'Service' field");
			}else {
				compareText(application, "Service", "PAMTest_ServiceValue" , serviceID, xml);
			}
			
		//Tool Response
			String toolResponse=Gettext(getwebelement(xml.getlocator("//locators/" + application + "/PAMTest_ToolResponse")));
			if(toolResponse.isEmpty()) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'Tool Response' field");
				Log.info("No values displaying under 'Tool Response' field");
			}else {
				ExtentTestManager.getTest().log(LogStatus.PASS, "Under 'Tool Response' field, value is dispaying as: "+toolResponse);
				Log.info("Under 'Tool Response' field, value is dispaying as: "+toolResponse);
			}
			
			
		//click on "X"button to close the popup
			click_commonMethod(application, "X", "configure_alertPopup_xbutton" , xml);
			
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'PAM Test' popup page is not displaying");
			Log.info("'PAM Test' popup page is not displaying");
		}
	}catch(Exception e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, "'PAM Test' popup page is not displaying");
		Log.info("'PAM Test' popup page is not displaying");
		}
	}

	
	
	/**
	 *  For Comparing the values 
	 * @param application
	 * @param labelname
	 * @param xpath
	 * @param ExpectedText
	 * @param xml
	 * @throws InterruptedException
	 * @throws DocumentException
	 */
	public void compareValue_manageService(String application, String labelname, String xpath, String ExpectedText, XMLReader xml) throws InterruptedException, DocumentException {

		String text = null;
		WebElement element = null;

		try {
			Thread.sleep(1000);
			element= getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +""));
			String emptyele = getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).getAttribute("value");
			if(element==null)
			{
				ExtentTestManager.getTest().log(LogStatus.FAIL, " '"+labelname+"' not found");
			}
			else if (emptyele!=null && emptyele.isEmpty()) {

				sa.assertEquals(emptyele, ExpectedText, labelname + " value is not displaying as expected");
				
				if(emptyele.equalsIgnoreCase(ExpectedText)) {
					
					ExtentTestManager.getTest().log(LogStatus.PASS, " The Expected value for '"+ labelname +"' field is '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
					Log.info(" The Expected Text for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal Text '"+text+"'");
					
				}else {
					ExtentTestManager.getTest().log(LogStatus.FAIL,"The Expected value for '"+ labelname +"' field is  '"+ExpectedText+"' is not same as the Acutal value '"+text+"'");
					Log.info(" The Expected value '"+ExpectedText+"' is not same as the Acutal value '"+text+"'");
				}
				
			}else 
			{   
				text = element.getText();
				if(text.equals(ExpectedText)) {
					ExtentTestManager.getTest().log(LogStatus.PASS," The Expected Text for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal Text '"+text+"'");
					Log.info(" The Expected Text for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal Text '"+text+"'");
				}
				else if(ExpectedText.contains(text)) {
					ExtentTestManager.getTest().log(LogStatus.PASS," The Expected Text for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal Text '"+text+"'");
					Log.info(" The Expected Text for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal Text '"+text+"'");
				
				}
				else
				{
					ExtentTestManager.getTest().log(LogStatus.FAIL," The ExpectedText '"+ExpectedText+"' is not same as the Acutal Text '"+text+"'");
					Log.info(" The ExpectedText '"+ExpectedText+"' is not same as the Acutal Text '"+text+"'");
				
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " field is not displaying");
			Log.info(labelname + " field is not displaying");
		}
	}
	
	
	public String fetchServiceId(String application) throws IOException, InterruptedException, DocumentException {
		
		String serviceId=Gettext(getwebelement(xml.getlocator("//locators/" + application + "/viewServicePage_fetchServiceId")));
		return serviceId;
	}
	
	
	public String fetchOrderNuber(String application) throws IOException, InterruptedException, DocumentException {
		
		WebElement orderPanel= getwebelement(xml.getlocator("//locators/" + application + "/viewServicepage_OrderPanel"));
		ScrolltoElement(orderPanel);
		Thread.sleep(3000);
		
		String orderNumber=Gettext(getwebelement(xml.getlocator("//locators/" + application + "/viewServicePage_orderNumber")));
		return orderNumber;
		
	}
	
	
	public void AMNvalidator(String application, String siteOrderNumber, String deviceName, String csrSiteName, 
			String deviecXngCityName, String countryName) throws InterruptedException, DocumentException {
		
		boolean AMNvalidatorPanelHeader=false; 
		
		Thread.sleep(3000);
		
		 
		waitforPagetobeenable();
		
		Thread.sleep(4000);
    
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying AMN validator");
		Thread.sleep(2000);
		
		String[] statusColumnNamesExpected = {"Site Order", "CSR Site Name", "Device Country", "Device Xing City", "Smarts"};
		String[] deviceColumnNamesExpected = {"Device", "Smarts", "Fetch Interfaces" };
		
		List<String> ls = new ArrayList<String>();
		List<String> ls1 = new ArrayList<String>();
		
	try {	
		AMNvalidatorPanelHeader=getwebelement(xml.getlocator("//locators/" + application + "/AMNvalidator_panelHeader")).isDisplayed();
		
		if(AMNvalidatorPanelHeader) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'AMN Validator' panel is displaying");
			Log.info("'AMN Validator' panel is displaying");
		
		//check Status Panel column Header	
			List<WebElement> status_PanelHeaders = getwebelements(xml.getlocator("//locators/" + application + "/AMNvalidator_status_columnHeader"));
			for(WebElement panels : status_PanelHeaders) {
				
				boolean match = false;
				for (int i = 0; i < statusColumnNamesExpected.length; i++) {
					if (panels.getText().equals(statusColumnNamesExpected[i])) {
						match = true;
						ls.add(panels.getText());
					}else {
//						ExtentTestManager.getTest().log(LogStatus.FAIL, "Under 'Site Orders' column, Column name for " + panels.getText() + " is not displaying");
						Log.info("Under 'Site Orders' column, Column name for " + panels.getText() + " is not displaying");
					}
				}
			}
			
			
			ExtentTestManager.getTest().log(LogStatus.PASS, "Under 'Site Orders' Panel, list of column names dipslaying are: " + ls );
			Log.info("Under 'Site Orders' Panel, list of column names dipslaying are: " + ls );
			
			
		//verify values under "Status" panel
			compareText(application, "Site Order", "AMNvalidator_statusPanel_siteOrderColumnValue" , siteOrderNumber, xml);   //Site Order Column Name
			compareText(application, "CSR Site Name", "AMNvalidator_statusPanel_CSRsiteNameColumnValue", csrSiteName, xml);	  //CSR Site Name
			compareText(application, "Device Xng City", "AMNvalidator_statusPanel_deviceXngCityName" , deviecXngCityName, xml);   //Device XN City Name
			compareText(application, "Device Country", "AMNvalidator_statusPanel_countryName" , countryName, xml);   //CountryName
			
			
			scrolltoend();
			Thread.sleep(1000);
		//check Device panel column Header
			List<WebElement> device_PanelHeaders = getwebelements(xml.getlocator("//locators/" + application + "/AMNvalidator_device_columnHeader"));
			for(WebElement devicepanels : device_PanelHeaders) {
				
				boolean match = false;
				for (int i = 0; i < deviceColumnNamesExpected.length; i++) {
					if (devicepanels.getText().equals(deviceColumnNamesExpected[i])) {
						match = true;
						ls1.add(devicepanels.getText());
						
					}else {
						Log.info("Under 'Devices For Services' panel, Column name for " + devicepanels + " is not displaying");
					}
				}
			}
			
			ExtentTestManager.getTest().log(LogStatus.PASS, "Under 'Devices For Service' Panel, List Of column Names dispaying are: " + ls1);
			Log.info("Under 'Devices For Service' Panel, List Of column Names dispaying are: " + ls1);
			
		//verify vales under 'device' panel
			compareText_amnValidator(application, "DeviceValue", "AMNvalidator_devicePanel_deviceName", deviceName, xml);
			
		}else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'AMN Validator' panel is not displaying");
			Log.info("'AMN Validator' panel is not displaying");
		}
	 }catch(Exception e) {
		 e.printStackTrace();
		 ExtentTestManager.getTest().log(LogStatus.PASS, "'AMN Validator' panel is not displaying");
		 Log.info("'AMN Validator' panel is not displaying");
	 }
	}
	
	
	public void clickOnAMNvalidatorLink(String application) throws InterruptedException, DocumentException {
		
		scrollToTop();
		Thread.sleep(1000);
		
		click_commonMethod(application, "Action", "siteOrder_ActionButton", xml);   //click on Action button
		
		click_commonMethod(application, "AMNvalidator", "AMNvalidatorlink", xml);
	}

	
	public String fetchCSRsiteName(String application) throws InterruptedException, DocumentException, IOException {
		
		scrollToTop();
		
		WebElement CSrname=getwebelement(xml.getlocator("//locators/" + application + "/physicalSiteCSRName_viewSiteOrder"));
		String CSRname=Gettext(CSrname);
		
		return CSRname;
		
		
	}
	
	
	public String fetchDeviceCityName(String application) throws InterruptedException, DocumentException, IOException {
		
		scrollToTop();
		Thread.sleep(1000);
		
		WebElement cityName=getwebelement(xml.getlocator("//locators/" + application + "/deviceCityName_viewSiteOrder"));
		String dveiceCityName=Gettext(cityName);
		
		return dveiceCityName;
	}
	
	public String fetchSiteOrderCountryName(String application) throws InterruptedException, DocumentException, IOException {
		
		WebElement countryName=getwebelement(xml.getlocator("//locators/" + application + "/countryName_viewSiteOrder"));
		String siteOrder_countryName=Gettext(countryName);
		
		return siteOrder_countryName;
	} 
	
	
	public void deleteSiteOrder(String application) throws InterruptedException, DocumentException, IOException {
		
		click_commonMethod(application, "Delete", "deleteLink_common" , xml);
		
		WebElement DeleteAlertPopup= getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup"));
        if(DeleteAlertPopup.isDisplayed())
        {
      	 String deletPopUpMessage= Gettext(getwebelement(xml.getlocator("//locators/" + application + "/deleteMessages_textMessage")));
      	 ExtentTestManager.getTest().log(LogStatus.PASS, "Delete Pop up message displays as: "+ deletPopUpMessage);
      	 
           click_commonMethod(application, "Delete", "deletebutton", xml);
           
           scrollToTop();
           Thread.sleep(1000);
           
           verifysuccessmessage(application, "Site Order successfully deleted");
        }
        else
        {
              Log.info("Delete alert popup is not displayed");
              ExtentTestManager.getTest().log(LogStatus.FAIL, "Delete alert popup is not displayed");
        }
	}
	
	
	public void deleteService(String application) throws InterruptedException, DocumentException, IOException {
		
		WebElement orderPanel= getwebelement(xml.getlocator("//locators/" + application + "/viewServicepage_OrderPanel"));
		ScrolltoElement(orderPanel);
		Thread.sleep(3000);
		
		click_commonMethod(application, "Action", "Editservice_actiondropdown", xml);
		Log.info("Action dropdown is working");
		Thread.sleep(3000);
		
			//click on delete link
				click_commonMethod(application, "Delete", "deleteLink_common", xml);
				
				 WebElement DeleteAlertPopup= getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup"));
		         if(DeleteAlertPopup.isDisplayed())
		         {
		       	 String deletPopUpMessage= Gettext(getwebelement(xml.getlocator("//locators/" + application + "/deleteMessages_textMessage")));
		       	 ExtentTestManager.getTest().log(LogStatus.PASS, "Delete Pop up message displays as: "+ deletPopUpMessage);
		       	 
		            click_commonMethod(application, "Delete", "deletebutton", xml);
		            
		            scrollToTop();
		            Thread.sleep(1000);
		            
		            verifysuccessmessage(application, "Service successfully deleted");
		         }
		         else
		         {
		               Log.info("Delete alert popup is not displayed");
		               ExtentTestManager.getTest().log(LogStatus.FAIL, "Delete alert popup is not displayed");
		         }
	}

	
	public void cleartext(String application, String labelname, String xpath) throws InterruptedException, DocumentException {
		WebElement element= null;
		try {
			Thread.sleep(1000);
			element = getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +""));
			String value= getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).getAttribute("value");
			if(element==null)
			{
				ExtentTestManager.getTest().log(LogStatus.FAIL, " '"+labelname+"' not found");
			}
			else if(value!=null) {
				Thread.sleep(1000);
				element.clear();	
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void EnterTextValue(String application, String value, String labelname, String xpath) {
		WebElement element = null;

		try {
			Thread.sleep(1000);
			element= getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +""));
			if(element==null)
			{
				ExtentTestManager.getTest().log(LogStatus.FAIL, "'"+labelname+"' text field not found");
				Log.info("'"+labelname+"' text field not found");
			}
			else 
			{
				if(value.equalsIgnoreCase("null")) {
					ExtentTestManager.getTest().log(LogStatus.PASS, "No values entered in "+labelname + " text field");
				}else {
					element.sendKeys(value);
					ExtentTestManager.getTest().log(LogStatus.PASS, "Entered '"+value+"' into '"+labelname+"' text field");
				}
			}
		}catch(NoSuchElementException ep) {
			ep.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " field is not displaying");
			Log.info(labelname + " field is not displaying");
		}
		catch (Exception e) {
			ExtentTestManager.getTest().log(LogStatus.FAIL,"Not able to enter '"+value+"' into '"+labelname+"' text field");
			e.printStackTrace();
		}
	}
	
	
	public void selectAndRemoveValueFromRightDropdown(String application, String labelname, String xpath, String[] selectValue, String xpathForRemoveButton) {

		WebElement availability=null;
		List<String> ls = new ArrayList<String>();

		try{
			List<WebElement> elements= getwebelements(xml.getlocator("//locators/" + application + "/"+ xpath +""));
			int element_count= elements.size();

			if(element_count>=1) {

				//Print list of values inside Dropdown 
				for(WebElement a : elements) {
					ls.add(a.getText());
				}

				ExtentTestManager.getTest().log(LogStatus.PASS, "list of values displaying inside "+labelname+" available dropdown is: "+ls);
				Log.info("list of values dipslaying inside "+labelname+" dropdown is: "+ls);

				//select value inside the dropdown     
				for(int i=0; i<selectValue.length; i++)
				{
					Thread.sleep(2000);
					for(int j=0; j<ls.size() ; j++) {
						Log.info("ls value "+ ls.get(j));
						if(selectValue[i].equals(ls.get(j)))
						{
							elements.get(j).click();
							ExtentTestManager.getTest().log(LogStatus.PASS, elements.get(j) + " got selected" );
							Thread.sleep(1000);
							WebElement removeButton=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpathForRemoveButton +"").replace("value", "<<"));
							Clickon(removeButton);
							ExtentTestManager.getTest().log(LogStatus.PASS, "clicked on remove '<<' button");
							Thread.sleep(3000);
						}
					}
				}
			}else {
				ExtentTestManager.getTest().log(LogStatus.INFO, "No values displaying under " + labelname + " dropdown");
				Log.info("No values displaying under " + labelname + " available dropdown");
			}
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under "+labelname + " available dropdown");
			Log.info( "No values displaying under "+labelname + " available dropdown");
		}
	}
	
	
	public void verifySelectedValuesInsideRightDropdown(String application, String labelname, String xpath) {

		//getAllValuesInsideDropDown
		boolean availability=false;
		List<String> ls = new ArrayList<String>();

		try{

			List<WebElement> elements= getwebelements(xml.getlocator("//locators/" + application + "/"+ xpath +""));
			int element_count= elements.size();

			if(element_count>=1) {

				//Print list of values inside Dropdown 
				for(WebElement a : elements) {
					ls.add(a.getText());
				}

				ExtentTestManager.getTest().log(LogStatus.PASS, "list of values displaying inside "+labelname+" available dropdown is: "+ls);
				Log.info("list of values dipslaying inside "+labelname+" dropdown is: "+ls);
			}else {
				ExtentTestManager.getTest().log(LogStatus.INFO, "No values displaying under " + labelname + " dropdown");

				Log.info("No values displaying under " + labelname + " available dropdown");
			}
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under "+labelname + " available dropdown");
			Log.info( "No values displaying under "+labelname + " available dropdown");
		}
	}
	
	
	public void selectAndAddValueFromLeftDropdown(String application, String labelname, String xpath, String[] selectValue, String xpathForAddButton) {

		WebElement availability=null;
		List<String> ls = new ArrayList<String>();
		try{

			List<WebElement> elements= getwebelements(xml.getlocator("//locators/" + application + "/"+ xpath +""));
			int element_count= elements.size();

			if(element_count>=1) {

				//Print list of values inside Dropdown 
				for(WebElement a : elements) {
					ls.add(a.getText());
				}

				ExtentTestManager.getTest().log(LogStatus.PASS, "list of values displaying inside "+labelname+" available dropdown is: "+ls);
				Log.info("list of values dipslaying inside "+labelname+" dropdown is: "+ls);

				//select value inside the dropdown     
				for(int i=0; i<selectValue.length; i++)
				{
					Thread.sleep(5000);
					for(int j=0; j<ls.size() ; j++) {
						Log.info("ls value "+ ls.get(j));
						if(selectValue[i].equals(ls.get(j)))
						{
							elements.get(j).click();
							ExtentTestManager.getTest().log(LogStatus.PASS, elements.get(j) + " got selected" );
							Thread.sleep(1000);
							click_commonMethod(application, "Add", xpathForAddButton , xml);
							Thread.sleep(5000);
						}
					}
				}

			}else {
				ExtentTestManager.getTest().log(LogStatus.INFO, "No values displaying under " + labelname + " dropdown");

				Log.info("No values displaying under " + labelname + " available dropdown");
			}
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under "+labelname + " available dropdown");
			Log.info( "No values displaying under "+labelname + " available dropdown");
		}
	}


	
	public String GetText(String application, String labelname, String xpath) throws InterruptedException, DocumentException {

		String text = null;
		WebElement element = null;

		try {
			Thread.sleep(1000);
			element = getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")); 
			String ele = getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).getAttribute("value");
			if(element==null)
			{
				ExtentTestManager.getTest().log(LogStatus.PASS, "'"+ labelname +"' is not found");
			}
			else if (ele!=null && ele.isEmpty()) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "'"+ labelname +"' value is empty");
			}
			else {   

				text = element.getText();
				ExtentTestManager.getTest().log(LogStatus.PASS,"'"+ labelname +"' value is displayed as : '"+text+"'");

			}
		}catch (Exception e) {
			ExtentTestManager.getTest().log(LogStatus.FAIL,"'"+ labelname +"' value is not displaying");
			e.printStackTrace();
		}
		return text;
	}
	
	
	public void selectRowForsiteorder(String Application, String siteordernumber, String siteOrdernumber_P2P, String topology, 
			String interfaceSpeed, String siteOrderNumber_10G_p2p)
			throws InterruptedException, DocumentException, IOException {

		 
		waitforPagetobeenable();
		
		Log.info("-----------------------------" + siteordernumber + "---------------------");
		int TotalPages;
 
		scrolltoend();
		Thread.sleep(3000);
			List<WebElement> results = null;
			
			if(topology.equalsIgnoreCase("Point-to-Point")) {
				if(interfaceSpeed.equals("10GigE")) {
					results=getwebelements("//div[div[text()='Site Orders']]//following-sibling::div//div[text()='"+ siteOrderNumber_10G_p2p + "']");
				}else if(interfaceSpeed.equals("1GigE")) {
					results=getwebelements("//div[div[text()='Site Orders']]//following-sibling::div//div[text()='"+ siteOrdernumber_P2P + "']");
				}
					
			}else {
				results=getwebelements("//div[div[text()='Site Orders']]//following-sibling::div//div[text()='"+ siteordernumber + "']");
			}
		
			int numofrows = results.size();
			Log.info("no of results: " + numofrows);
			boolean resultflag;

						resultflag = results.get(0).isDisplayed();
						Log.info("status of result: " + resultflag);
						if (resultflag) {
							Log.info(results.get(0).getText());
							results.get(0).click();
							Thread.sleep(5000);
							click_commonMethod(Application, "Action", "Actiondropdown_siteorder", xml);

							Thread.sleep(5000);
						}
			}
	
	
	public void addOvertureCircuit(String application, String serviceName)
			throws InterruptedException, DocumentException, IOException {

		ExtentTestManager.getTest().log(LogStatus.INFO, "Circuit creation for 'Overture' device");
		
		scrolltoend();

		// Click on Add Overture Link
		click_commonMethod(application, "Add Overture", "addOvertureLink", xml);
		Thread.sleep(1000);
		   waitforPagetobeenable();


		boolean overturePanelHeader = getwebelement(xml.getlocator("//locators/" + application + "/addOverture_overturePanel")).isDisplayed();
		if(overturePanelHeader) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Overture' page is displaying");
			Log.info("'Overture' page is displaying");
			
			addtextFields_commonMethod(application, "Service Name", "addOverture_serviceNameTextField", serviceName , xml);	//Search ame text Field
			
			click_commonMethod(application, "Search", "addOverture_searchButton" , xml);   //Search Button
			Thread.sleep(2000);
			   waitforPagetobeenable();
			
			
			WebElement selectValueInTable = getwebelement(xml.getlocator("//locators/" + application + "/selectValueUnderAddOverturePage").replace("value", serviceName));
			try {
				selectValueInTable.isDisplayed();
				ExtentTestManager.getTest().log(LogStatus.PASS, "Records displays for the Service " + serviceName);
				Log.info("Records displays for the Service " + serviceName);
				
				Clickon(selectValueInTable);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Service selected under the table");
				Thread.sleep(1000);
				
				click_commonMethod(application, "OK", "addOverture_OKbutton", xml);
				Thread.sleep(1000);
				    waitforPagetobeenable();
				
			}catch(Exception e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, "No record displays for the Service " + serviceName);
				Log.info("No record displays for the Service " + serviceName);
				
				click_commonMethod(application, "cancel", "addOverture_cancelButton" , xml);
			}
		}
		else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'Overture' page is not displaying");
			Log.info("'Overture' page is not displaying");
		}
	}
	
	
	public void selectInterfaceForCircuits(String application, String interface1, String interface2, String selectEdgePointForInterface1,
			String selectEdgePointForInterface2) throws InterruptedException, DocumentException, IOException {
		
		
		if(getwebelement(xml.getlocator("//locators/" + application + "/addOverture_interfaceInServicePanel")).isDisplayed()){
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Interface in Service' page displays");
			Log.info("'Interface in Service' page displays");
			
			
		//Select row for 1st interface	
			click_commonMethod(application, "interface_filter", "interfaceFilterButton" , xml);
			
			addtextFields_commonMethod(application, "filter" , "interfacePage_filterText", interface1, xml);
			
			WebElement selectInterface	= getwebelement(xml.getlocator("//locators/" + application + "/interfaceInService_selectValueUnderTable").replace("value", interface1));
			Clickon(selectInterface);
			ExtentTestManager.getTest().log(LogStatus.PASS, interface1 + " is selected under 'Interface In Service' page");
			Log.info(interface1 + " is selected under 'Interface In Service' page");
			
			if(selectEdgePointForInterface1.equalsIgnoreCase("Yes")) {
				WebElement selectEdgePoint = getwebelement(xml.getlocator("//locators/" + application + "/interfaceinService_selectEdgePointforInterface").replace("value", interface1));
				Clickon(selectEdgePoint);
				ExtentTestManager.getTest().log(LogStatus.PASS, interface1 + " is selected under 'Interface In Service' page");
				Log.info(interface1 + " is selected under 'Interface In Service' page");
				
			}else {
				ExtentTestManager.getTest().log(LogStatus.PASS, "'Edge End Point' radio button is not selected for " +  interface1);
				Log.info("'Edge End Point' radio button is not selected for "+ interface1);
			}
			
			
		//select row for 2nd interface	
			click_commonMethod(application, "interface_filter", "interfaceFilterButton" , xml);
			
			edittextFields_commonMethod(application, "filter" , "interfacePage_filterText", interface2, xml);
			Thread.sleep(3000);
			
			WebElement selectInterface2	= getwebelement(xml.getlocator("//locators/" + application + "/interfaceInService_selectValueUnderTable").replace("value", interface2));
			Clickon(selectInterface2);
			ExtentTestManager.getTest().log(LogStatus.PASS, interface2 + " is selected under 'Interface In Service' page");
			Log.info(interface2 + " is selected under 'Interface In Service' page");
			
			if(selectEdgePointForInterface2.equalsIgnoreCase("Yes")) {
				WebElement selectEdgePoint2 = getwebelement(xml.getlocator("//locators/" + application + "/interfaceinService_selectEdgePointforInterface").replace("value", interface2));
				Clickon(selectEdgePoint2);
				ExtentTestManager.getTest().log(LogStatus.PASS, interface2 + " is selected under 'Interface In Service' page");
				Log.info(interface2 + " is selected under 'Interface In Service' page");
				
			}else {
				
				ExtentTestManager.getTest().log(LogStatus.PASS, "'Edge End Point' radio button is not selected for "+  interface2);
				Log.info("'Edge End Point' radio button is not selected for "+  interface2);
			}
			
			Thread.sleep(1000);
			click_commonMethod(application, "OK", "addOverture_OKbutton", xml);
			
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'Interface in Service' page not displays");
			Log.info("'Interface in Service' page not displays");
		}
	}
	
	
	public void addOveture_PAMtest_selectRow(String application,  String interface1) throws InterruptedException, DocumentException {
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Performing 'PAM Test'");
		
		   waitforPagetobeenable();
		
		scrolltoend();
		
		WebElement selectInterface	= getwebelement(xml.getlocator("//locators/" + application + "/PAMtest_selectinterface").replace("value", interface1));
		Clickon(selectInterface);
		ExtentTestManager.getTest().log(LogStatus.PASS, interface1 + " is selected for performing 'PAM Test'");
		Log.info(interface1 + " is selected for performing 'PAM Test'");
		
		
		click_commonMethod(application, "Action", "PAMtest_actionDropdown", xml);
		click_commonMethod(application, "PAMTest", "PAMtest_Link", xml);
		
		Thread.sleep(2000);
		   waitforPagetobeenable();
		
	}
	
	
	public void PAMtest_ForCircuitCreation(String application, String serviceName, String siteName) throws InterruptedException, DocumentException {
		
		boolean pamTestPage=false;
		try {	
		pamTestPage=getwebelement(xml.getlocator("//locators/" + application + "/PAMtest_popupPage")).isDisplayed();
		if(pamTestPage) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'PAM Test' popup page is displaying");
			Log.info("'PAM Test' popup page is displaying");
			
		//Type Value	
			String typeValue=Gettext(getwebelement(xml.getlocator("//locators/" + application + "/PAMTest_TypeFieldValue")));
			if(typeValue.isEmpty()) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'Type' field");
				Log.info("No values displaying under 'Type' field");
			}else {
				ExtentTestManager.getTest().log(LogStatus.PASS, "Under 'Type' field, value is dispaying as: "+typeValue);
				Log.info("Under 'Type' field, value is dispaying as: "+typeValue);
			}
			
		//Service
			String serviceValue=Gettext(getwebelement(xml.getlocator("//locators/" + application + "/PAMTest_ServiceValue")));
			if(serviceValue.isEmpty()) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'Service' field");
				Log.info("No values displaying under 'Service' field");
			}else {
				compareText(application, "Service", "PAMTest_ServiceValue" , serviceName, xml);
			}
			
			
		//site
			String siteValue=Gettext(getwebelement(xml.getlocator("//locators/" + application + "/PAMTest_siteValue")));
			if(siteValue.isEmpty()) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'Site' field");
				Log.info("No values displaying under 'Site' field");
			}else {
				compareText(application, "Site", "PAMTest_siteValue" , siteName, xml);
			}
			
		//Tool Response
			String toolResponse=Gettext(getwebelement(xml.getlocator("//locators/" + application + "/PAMTest_ToolResponse")));
			if(toolResponse.isEmpty()) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'Tool Response' field");
				Log.info("No values displaying under 'Tool Response' field");
			}else {
				ExtentTestManager.getTest().log(LogStatus.PASS, "Under 'Tool Response' field, value is dispaying as: "+toolResponse);
				Log.info("Under 'Tool Response' field, value is dispaying as: "+toolResponse);
			}
			
		//click on "X"button to close the popup
			click_commonMethod(application, "X", "configure_alertPopup_xbutton" , xml);
			
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'PAM Test' popup page is not displaying");
			Log.info("'PAM Test' popup page is not displaying");
		}
	}catch(Exception e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, "'PAM Test' popup page is not displaying");
		Log.info("'PAM Test' popup page is not displaying");
		}
	}

	
	public void deleteCircuit(String application) throws InterruptedException, DocumentException {
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "delete Circuit");
		
		scrolltoend();
		click_commonMethod(application, "deleteLink", "PAMTest_deleteButton", xml);
		
		click_commonMethod(application, "deleteCircuitpopup_deleteLink", "deleteCircuit_deleteButton", xml);
		Thread.sleep(1000);
		
		    waitforPagetobeenable();
		
		verifysuccessmessage(application, "Circuit deleted successfully");
		
	}
	
	public void addAccedianCircuit(String application, String serviceName)
			throws InterruptedException, DocumentException, IOException {

		// Click on Add Accedian-1G Link
		click_commonMethod(application, "Add Accedian-1G", "addCircuit_AccedianLink", xml);
		Thread.sleep(1000);
		   waitforPagetobeenable();


		boolean overturePanelHeader = getwebelement(xml.getlocator("//locators/" + application + "/addCircuit_Accedian1Gpage_panel")).isDisplayed();
		if(overturePanelHeader) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Accedian-1G' page is displaying");
			Log.info("'Accedian-1G' page is displaying");
			
			addtextFields_commonMethod(application, "Service Name", "addOverture_serviceNameTextField", serviceName , xml);	//Search ame text Field
			
			click_commonMethod(application, "Search", "addOverture_searchButton" , xml);   //Search Button
			Thread.sleep(2000);
			   waitforPagetobeenable();
			
			
			WebElement selectValueInTable = getwebelement(xml.getlocator("//locators/" + application + "/selectValueUnderAddOverturePage").replace("value", serviceName));
			try {
				selectValueInTable.isDisplayed();
				ExtentTestManager.getTest().log(LogStatus.PASS, "Records displays for the Service " + serviceName);
				Log.info("Records displays for the Service " + serviceName);
				
				Clickon(selectValueInTable);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Service selected under the table");
				Thread.sleep(1000);
				
				click_commonMethod(application, "OK", "addOverture_OKbutton", xml);
				Thread.sleep(1000);
				    waitforPagetobeenable();
				
			}catch(Exception e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, "No record displays for the Service " + serviceName);
				Log.info("No record displays for the Service " + serviceName);
				
				click_commonMethod(application, "cancel", "addOverture_cancelButton" , xml);
			}
			
		}
		else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'Overture' page is not displaying");
			Log.info("'Overture' page is not displaying");
		}
	}


	public void addAtricaCircuit(String application, String serviceName)
			throws InterruptedException, DocumentException, IOException {

		scrolltoend();
		
		// Click on Add Circuit Link
		click_commonMethod(application, "Add Circuit", "addCircuit_AtricaLink", xml);
		Thread.sleep(1000);
		   waitforPagetobeenable();


		boolean overturePanelHeader = getwebelement(xml.getlocator("//locators/" + application + "/addCircuit_Atricapage_Panel")).isDisplayed();
		if(overturePanelHeader) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Accedian-1G' page is displaying");
			Log.info("'Accedian-1G' page is displaying");
			
			addtextFields_commonMethod(application, "Service Name", "addOverture_serviceNameTextField", serviceName , xml);	//Search ame text Field
			
			click_commonMethod(application, "Search", "addOverture_searchButton" , xml);   //Search Button
			Thread.sleep(2000);
			   waitforPagetobeenable();
			
			
			WebElement selectValueInTable = getwebelement(xml.getlocator("//locators/" + application + "/selectValueUnderAddOverturePage").replace("value", serviceName));
			try {
				selectValueInTable.isDisplayed();
				ExtentTestManager.getTest().log(LogStatus.PASS, "Records displays for the Service " + serviceName);
				Log.info("Records displays for the Service " + serviceName);
				
				Clickon(selectValueInTable);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Service selected under the table");
				Thread.sleep(1000);
				
				click_commonMethod(application, "OK", "addOverture_OKbutton", xml);
				Thread.sleep(1000);
				    waitforPagetobeenable();
				
			}catch(Exception e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, "No record displays for the Service " + serviceName);
				Log.info("No record displays for the Service " + serviceName);
				
				click_commonMethod(application, "cancel", "addOverture_cancelButton" , xml);
			}
			
		}
		else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'Overture' page is not displaying");
			Log.info("'Overture' page is not displaying");
		}
	}
	
	

	 /**
		 *   For Dropdown common method _  For Span tag
		 * @param application
		 * @param labelname
		 * @param xpath
		 * @param expectedValueToAdd
		 * @param xml
		 * @throws InterruptedException
		 * @throws DocumentException
		 */
		public void addDropdownValues_forSpantag(String application, String labelname, String xpath, String expectedValueToAdd , XMLReader xml)
				throws InterruptedException, DocumentException {
			  boolean availability=false;
			  List<String> ls = new ArrayList<String>();
			  
			try {  
				
			  availability=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).isDisplayed();
			  if(availability) {
				  ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " dropdown is displaying");
				  Log.info(labelname + " dropdown is displaying");
				  
				  if(expectedValueToAdd.equalsIgnoreCase("null")) {
					  
					  ExtentTestManager.getTest().log(LogStatus.PASS, " No values selected under "+ labelname + " dropdown");
					  Log.info(" No values selected under "+ labelname + " dropdown");
				  }else {
					  
					  Clickon(getwebelement("//div[label[text()='"+ labelname +"']]//div[text()='�']"));
					  Thread.sleep(3000);
					  
					  //verify list of values inside dropdown
					  List<WebElement> listofvalues = driver
								.findElements(By.xpath("//span[@role='option']"));
					  
					  ExtentTestManager.getTest().log(LogStatus.PASS, " List of values inside "+ labelname + " dropdown is:  ");
					  Log.info( " List of values inside "+ labelname + "dropdown is:  ");
					  
						for (WebElement valuetypes : listofvalues) {
									Log.info("service sub types : " + valuetypes.getText());
									 ls.add(valuetypes.getText());
						}
						
						
						    ExtentTestManager.getTest().log(LogStatus.PASS, "list of values inside "+labelname+" dropdown is: "+ls);
				            Log.info("list of values inside "+labelname+" dropdown is: "+ls);
						
						Thread.sleep(2000);
					SendKeys(getwebelement("//div[label[text()='"+ labelname +"']]//input"), expectedValueToAdd);	
					Thread.sleep(2000);
						
					  Clickon(getwebelement("(//span[contains(text(),'"+ expectedValueToAdd +"')])[1]"));
					  Thread.sleep(3000);
					  
					  String actualValue=getwebelement("//label[text()='"+ labelname +"']/following-sibling::div//span").getText();
					  ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " dropdown value selected as: "+ actualValue );
					  Log.info( labelname + " dropdown value selected as: "+ actualValue);
					  
				  }
			  }else {
				  ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " is not displaying");
				  Log.info(labelname + " is not displaying");
			  }
			}catch(NoSuchElementException e) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " is not displaying");
				  Log.info(labelname + " is not displaying");
			}catch(Exception ee) {
				ee.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, " NOt able to perform selection under "+ labelname + " dropdown");
				Log.info(" NO value selected under "+ labelname + " dropdown");
			}
		}
		
		
		public String fetchProActiveMonitoringValue(String application) throws InterruptedException, DocumentException, IOException {
			
			String proactiveMonitor = "No";
			
			WebElement servicePanel= getwebelement(xml.getlocator("//locators/" + application + "/viewServicepage_Servicepanel"));
			ScrolltoElement(servicePanel);
			Thread.sleep(2000);
			
			proactiveMonitor = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/fetchProActiveMonitoringValue")));
			
			return proactiveMonitor;
		}

		
		public void clickonTechnology(WebElement el, String technology) throws InterruptedException {
			//Thread.sleep(3000);
			
			try {
			el.click();
			ExtentTestManager.getTest().log(LogStatus.INFO, "Under 'technology' dropdown, '"+ technology + "' is selected");
			Log.info("Under 'technology' dropdown, '"+ technology + "' is selected");
			}
			catch(Exception e)
			//Thread.sleep(3000);
			{
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, technology + " is not available under 'Technology' dropdown");
				Log.info( technology + " is not available under 'Technology' dropdown");
			}
		}
		
		
		public void compareText_amnValidator(String application, String labelname, String xpath, String ExpectedText, XMLReader xml) throws InterruptedException, DocumentException {

			String text = null;
			WebElement element = null;

			try {
				element= getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +""));
				String emptyele = getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).getAttribute("value");
				if(element==null)
				{
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Step:  '"+labelname+"' not found");
				}
				else if (emptyele!=null && emptyele.isEmpty()) {
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step : '"+ labelname +"' value is empty");
				}else 
				{
					   
					text = element.getText();
					if(text.contains("-")) {
						
						String[] actualTextValue=text.split(" ");
						String[] expectedValue =ExpectedText.split(" ");
						
						if(expectedValue[0].equalsIgnoreCase(actualTextValue[0])) {
							ExtentTestManager.getTest().log(LogStatus.PASS," The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
							Log.info(" The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
						}
						else if(expectedValue[0].contains(actualTextValue[0])) {
							ExtentTestManager.getTest().log(LogStatus.PASS,"The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
							Log.info("The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
						
						}
						else
						{
							ExtentTestManager.getTest().log(LogStatus.FAIL,"The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is not same as the Acutal value '"+text+"'");
							Log.info("The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is not same as the Acutal value '"+text+"'");
						}
						
					}else {
						if(text.equalsIgnoreCase(ExpectedText)) {
							ExtentTestManager.getTest().log(LogStatus.PASS," The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
							Log.info(" The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
						}
						else if(text.contains(ExpectedText)) {
							ExtentTestManager.getTest().log(LogStatus.PASS,"The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
							Log.info("The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
						
						}
						else
						{
							ExtentTestManager.getTest().log(LogStatus.FAIL,"The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is not same as the Acutal value '"+text+"'");
							Log.info("The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is not same as the Acutal value '"+text+"'");
						}
					}
				}
			}catch (Exception e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " field is not displaying");
				Log.info(labelname + " field is not displaying");
			}

		}

		
			
}