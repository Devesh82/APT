package com.colt.qa.apttestscripts;

import java.util.Map;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.sun.org.glassfish.gmbal.Description;
import com.colt.qa.excellibrary.DataReader;
import com.colt.qa.reporter.ExtentTestManager;
import com.colt.qa.driverlibrary.DriverTestcase;
import com.colt.qa.scripthelpers.APT_LoginHelper;

public class DDI_Test extends DriverTestcase{

	
	APT_Login Login=new APT_Login();
	
	@Test(description = "TC-01",dataProviderClass = DataReader.class, dataProvider = "DataReader_DDIRange", priority=2)
	public void DDIRange(Map<String, String> map) throws Exception {
		
		
		
		setup();	
		Login.APT_Login_1(map.get("url"));
		
		
		
		System.out.println("TC-01");
		logger= ExtentTestManager.startTest ("SearchTrunkName");
		DDI_Helper.get().SearchTrunkName("DDIRange",map.get("TrunkValue"));
		ExtentTestManager.endTest();
		//extent.flush();
		
		
		System.out.println("TC-02");
		logger= ExtentTestManager.startTest ("selectTrunk");
		DDI_Helper.get().selectTrunk("DDIRange",map.get("TrunkValue"));
		ExtentTestManager.endTest();
		//extent.flush();
		
		
		System.out.println("TC-03");
		logger= ExtentTestManager.startTest ("VerifyDDIRangefields");
		DDI_Helper.get().VerifyDDIRangefields("DDIRange");
		ExtentTestManager.endTest();
		
	
		System.out.println("TC-04");
		logger= ExtentTestManager.startTest ("createDDIRange");
		DDI_Helper.get().createDDIRange("DDIRange",map.get("CountrycodeValue"),map.get("LACValue"),map.get("MainNumbervalue"),map.get("RangestartValue"),map.get("RangeEndValue"),
	    		  map.get("ExtensionDigits"),map.get("ActivateIncomingRouting"),map.get("InGeo"));
		
		
	
		System.out.println("TC-05");
		logger= ExtentTestManager.startTest ("ViewDDIRange");
		DDI_Helper.get().ViewDDIRange("DDIRange",map.get("CountrycodeValue"),map.get("LACValue"),map.get("MainNumbervalue"),map.get("RangestartValue"),map.get("RangeEndValue"),
	    		  map.get("ExtensionDigits"),map.get("ActivateIncomingRouting"),map.get("InGeo"), map.get("PSXconfigurationValue"));
		
		
		
		
		System.out.println("TC-06");
		logger= ExtentTestManager.startTest ("editDDIRange");
		DDI_Helper.get().SearchTrunkName("DDIRange",map.get("TrunkValue"));
        DDI_Helper.get().selectTrunk("DDIRange",map.get("TrunkValue"));
	    DDI_Helper.get().editDDIRange("DDIRange",map.get("LACValue"),map.get("ExtensionDigits"));
		
		
		
		System.out.println("TC-07");
		logger= ExtentTestManager.startTest ("showDDIRange");
		DDI_Helper.get().SearchTrunkName("DDIRange",map.get("TrunkValue"));
        DDI_Helper.get().selectTrunk("DDIRange",map.get("TrunkValue"));
        DDI_Helper.get().showDDIRange("DDIRange");
		
		
		
		System.out.println("TC-08");
		logger= ExtentTestManager.startTest ("UploadDDIRange");
		DDI_Helper.get().UploadDDIRange("DDIRange",map.get("expected"), map.get("filePath_forUploading"));//E:\\Pramod_Workspace\\APT_Automation_DDI\\src\\com\\saksoft\\qa\\datalibrary\\ddiRanges_Tgid_2298329479.csv
		
		
		
		System.out.println("TC-09");
		logger= ExtentTestManager.startTest ("downloadDDIRange");
		DDI_Helper.get().downloadDDIRange("DDIRange");
		
		
		
		System.out.println("TC-10");
		logger= ExtentTestManager.startTest ("duplicateDDIRange");
		DDI_Helper.get().SearchTrunkName("DDIRange",map.get("TrunkValue"));
		DDI_Helper.get().selectTrunk("DDIRange",map.get("TrunkValue"));
		DDI_Helper.get().duplicateDDIRange("DDIRange");
		
		
		
	
		System.out.println("TC-11");
		logger= ExtentTestManager.startTest ("searchDDIRange");
		DDI_Helper.get().searchDDIRange("DDIRange");
		
		
		
		
		System.out.println("TC-12");
		logger= ExtentTestManager.startTest ("fillfieldDDIRange");
		DDI_Helper.get().searchDDIRange("DDIRange");
		DDI_Helper.get().fillfieldDDIRange("DDIRange", map.get("LACValue"),map.get("CountrycodeValue"));
		
		
		
		
		System.out.println("TC-13");
		logger= ExtentTestManager.startTest ("viewSearchDDIRange");
		DDI_Helper.get().viewSearchDDIRange("DDIRange", map.get("LACValue"),map.get("CountrycodeValue"), map.get("TrunkValue"));
		
		
		
		
		System.out.println("TC-14");
		logger= ExtentTestManager.startTest ("deleteDDIRange");
		DDI_Helper.get().SearchTrunkName("DDIRange",map.get("TrunkValue"));
		DDI_Helper.get().selectTrunk("DDIRange",map.get("TrunkValue"));
		DDI_Helper.get().deleteDDIRange("DDIRange",map.get("LACValue"));
		
		
	
              
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	/////////////////////////////////////////////
//		//@Test(description = "TC-01", dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_DDIRange", priority=2)
//		public void verifySearchTrunkName(Map<String, String> map) throws Exception {
//			logger= ExtentTestManager.startTest ("verifySearchTrunkName");
//			
//		      DDI_Helper.get().SearchTrunkName("DDIRange",map.get("TrunkValue"));
//	}
//		
//		
//		
//		//@Test(description = "TC-02",dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_DDIRange", priority=3)
//		public void verifySelectTrunk(Map<String, String> map) throws Exception {
//			logger= ExtentTestManager.startTest ("verifySelectTrunk");
//			
//		      DDI_Helper.get().selectTrunk("DDIRange",map.get("TrunkValue"));
//	}
//	   
//	   
//		
//		/**
//		 * verify field label names under 'Add DDI' range page
//		 * @param map
//		 * @throws Exception
//		 */
//		//@Test(description = "TC-03",dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_DDIRange", priority=4)
//		public void verifyAddDDIRangeFields(Map<String, String> map) throws Exception {
//			logger= ExtentTestManager.startTest ("verifyAddDDIRangeFields");
//			
//		      DDI_Helper.get().VerifyDDIRangefields("DDIRange");
//		}
//	
//		
//		
//		/**
//		 * Add value under 'Add DDI Range' page
//		 * @param map
//		 * @throws Exception
//		 */
//		//@Test(description = "TC-04",dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_DDIRange", priority=5)
//		public void AddDDIRange(Map<String, String> map) throws Exception {
//			logger= ExtentTestManager.startTest ("AddDDIRange");
//			
//		      System.out.println("Fill Values in for DDI Range Creation");
//		      DDI_Helper.get().createDDIRange("DDIRange",map.get("CountrycodeValue"),map.get("LACValue"),map.get("MainNumbervalue"),map.get("RangestartValue"),map.get("RangeEndValue"),
//		    		  map.get("ExtensionDigits"),map.get("ActivateIncomingRouting"),map.get("InGeo"));
//		}
//		
//
//		
//
//		//@Test(description = "TC-05",dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_DDIRange", priority=6)
//		public void ViewDDIRange(Map<String, String> map) throws Exception{
//				logger= ExtentTestManager.startTest ("ViewDDIRange");
//			
//		        System.out.println("View DDI Range");
//			     DDI_Helper.get().ViewDDIRange("DDIRange",map.get("CountrycodeValue"),map.get("LACValue"),map.get("MainNumbervalue"),map.get("RangestartValue"),map.get("RangeEndValue"),
//			    		  map.get("ExtensionDigits"),map.get("ActivateIncomingRouting"),map.get("InGeo"), map.get("PSXconfigurationValue"));
//		}
//
//
//		
//		//@Test(description = "TC-06",dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_DDIRange", priority=7)
//		public void EditDDIRange(Map<String, String> map) throws Exception{
//			logger= ExtentTestManager.startTest ("EditDDIRange");
//			
//				DDI_Helper.get().SearchTrunkName("DDIRange",map.get("TrunkValue"));
//		        DDI_Helper.get().selectTrunk("DDIRange",map.get("TrunkValue"));
//			    DDI_Helper.get().editDDIRange("DDIRange",map.get("LACValue"),map.get("ExtensionDigits"));
//		}
//
//		
//		
//		//@Test(description = "TC-07",dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_DDIRange", priority=8)
//		public void verifyShowDDIRange(Map<String, String> map) throws Exception {
//			logger= ExtentTestManager.startTest ("verifyShowDDIRange");
//			
//		        System.out.println("Verifying Show DDI Range");
//		        DDI_Helper.get().SearchTrunkName("DDIRange",map.get("TrunkValue"));
//		        DDI_Helper.get().selectTrunk("DDIRange",map.get("TrunkValue"));
//		        DDI_Helper.get().showDDIRange("DDIRange");
//	}
//		
//		
//		
//		//@Test(description = "TC-08",dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_DDIRange", priority=9)
//		public void verifyUploadDDIRange(Map<String, String> map) throws Exception {
//			logger= ExtentTestManager.startTest ("verifyUploadDDIRange");
//			
//		        System.out.println("Verifying Upload DDI Range");
//		      DDI_Helper.get().UploadDDIRange("DDIRange",map.get("expected"), map.get("filePath_forUploading"));//E:\\Pramod_Workspace\\APT_Automation_DDI\\src\\com\\saksoft\\qa\\datalibrary\\ddiRanges_Tgid_2298329479.csv
//	}
//		
//		
//		
//
//		//@Test(description = "TC-09",dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_DDIRange", priority=10)
//		public void verifydownloadDDIRange(Map<String, String> map) throws Exception{
//			logger= ExtentTestManager.startTest ("verifydownloadDDIRange");
//
//			System.out.println("Verifying Download DDI Range");
//	      DDI_Helper.get().downloadDDIRange("DDIRange");
//	}
//		
//		
//		
//		
//		@Test(description = "TC-10",dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_DDIRange", priority=11)
//		public void verifyDuplicateDDIRange(Map<String, String> map) throws Exception {
//			logger= ExtentTestManager.startTest ("verifyDuplicateDDIRange");
//			
//		        System.out.println("Verifying Duplicate DDI Range");
//		      DDI_Helper.get().duplicateDDIRange("DDIRange");
//	}
//
//
//
//		//****@Test(description = "TC-11",dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_DDIRange", priority=21)
//		public void verifydeleteDDIRange(Map<String, String> map) throws Exception {
//			logger= ExtentTestManager.startTest ("verifyViewDDIRange");
//			
//		        System.out.println("Delete DDI Range");
//			      DDI_Helper.get().deleteDDIRange("DDIRange",map.get("LACValue"));
//		}
//		
//		
//		
//		
////		/**
////		 * click on Search for DDI Range ink
////		 * @param map
////		 * @throws Exception
////		 */
////		@Test(description = "TC-11",dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_DDIRange", priority=12)
////		public void enterSearchDDIRangePage(Map<String, String> map) throws Exception {
////			logger= ExtentTestManager.startTest ("enterSearchDDIRangePage");
////			
////             DDI_Helper.get().searchDDIRange("DDIRange");
////		}
////		
////		
////		
////		
////		/**
////		 * Search the DDI value
////		 * @param map
////		 * @throws Exception
////		 */
////		@Test(description = "TC-12",dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_DDIRange", priority=13)
////		public void verifySearchforDDIRangeFields(Map<String, String> map) throws Exception {
////			logger= ExtentTestManager.startTest ("verifySearchforDDIRangeFields");
////			
////			DDI_Helper.get().fillfieldDDIRange("DDIRange", map.get("LACValue"),map.get("CountrycodeValue"));
////		}
////		
////		
////		
////		
////		/**
////		 * Select DDI value
////		 * @param map
////		 * @throws Exception
////		 */
////		
////		@Test(description = "TC-13",dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_DDIRange", priority=14)
////		public void verifySearchdDDIRange(Map<String, String> map) throws Exception {
////			logger= ExtentTestManager.startTest ("verifySearchdDDIRange");
////			
////			DDI_Helper.get().viewSearchDDIRange("DDIRange", map.get("LACValue"),map.get("CountrycodeValue"), map.get("TrunkValue"));
////		}
////


		
	
	
	
}
