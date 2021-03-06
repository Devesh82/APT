package com.saksoft.qa.driverlibrary;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.SessionId;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.saksoft.qa.scripthelpers.APT_AutomationHelper;
import com.saksoft.qa.scripthelpers.APT_CreateAccessCoreDevice_ManageNetworkHelper;
import com.saksoft.qa.scripthelpers.APT_DomainManagementHelper;
import com.saksoft.qa.scripthelpers.APT_LoginHelper;
//import com.saksoft.qa.scripthelpers.APT_MCN_SearchDeviceHelper;
import com.saksoft.qa.scripthelpers.APT_MCS_CreateAccessCoreDeviceHelper;
import com.saksoft.qa.scripthelpers.APT_MCS_CreateAccessSwitchCoreDeviceHelper;
import com.saksoft.qa.scripthelpers.APT_MCS_CreateCoreRouterDeviceHelper;
//import com.saksoft.qa.scripthelpers.APT_MCS_CreateCustomerHelper3Set;
import com.saksoft.qa.scripthelpers.APT_MCS_CreateCustomerSeparateHelper;
import com.saksoft.qa.scripthelpers.APT_MCS_CreateDCNDeviceHelper;
import com.saksoft.qa.scripthelpers.APT_MCS_CreateDSLAMDeviceHelper;
import com.saksoft.qa.scripthelpers.APT_MCS_CreateFirewallDeviceHelper;
import com.saksoft.qa.scripthelpers.APT_MCS_CreateKeyserverDeviceHelper;
import com.saksoft.qa.scripthelpers.APT_MCS_CreateLoadBalancerDeviceHelper;
import com.saksoft.qa.scripthelpers.APT_MCS_CreateMDFFirewallDeviceHelper;
import com.saksoft.qa.scripthelpers.APT_MCS_CreateMiniDSLAMDeviceHelper;
import com.saksoft.qa.scripthelpers.APT_MCS_CreatePrizmnetDeviceHelper;
import com.saksoft.qa.scripthelpers.APT_MCS_CreateTrafficAggregatorDeviceHelper;
import com.saksoft.qa.scripthelpers.APT_MCS_CreateVOIPAccessDASSwitchDeviceHelper;
import com.saksoft.qa.scripthelpers.APT_MCS_CreateVoiceGatewayDeviceHelper;
//import com.saksoft.qa.scripthelpers.APT_MCS_SearchCustomerHelper;
import com.saksoft.qa.scripthelpers.APT_MSPLatencyHelper;
import com.saksoft.qa.scripthelpers.APT_ManageNetworkHelper;
import com.saksoft.qa.scripthelpers.APT_NGINHelper;
import com.saksoft.qa.scripthelpers.APT_VoiceLineHelper;
import com.saksoft.qa.scripthelpers.APT_wholeSaleHelper;
import com.saksoft.qa.scripthelpers.Hss_Helper;
import com.saksoft.qa.scripthelpers.ImsNmbrTranslator_Helper;
import com.saksoft.qa.scripthelpers.Lanlink_DirectFiberHelpr;
import com.saksoft.qa.scripthelpers.Lanlink_InternationalHelper;
import com.saksoft.qa.scripthelpers.Lanlink_MetroHelper;
import com.saksoft.qa.scripthelpers.Lanlink_National;
import com.saksoft.qa.scripthelpers.Lanlink_OLOHelper;
import com.saksoft.qa.scripthelpers.Lanlink_Outbandmanagementhelper;
//import com.saksoft.qa.scripthelpers.PerformOrder_SupplyHelper;
import com.saksoft.qa.scripthelpers.manageNetwork_Lanlink;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.saksoft.qa.apttestscripts.APT_Login;



public class DriverTestcase {
	
	public static final ThreadLocal<WebDriver> WEB_DRIVER_THREAD_LOCAL = new InheritableThreadLocal<>();

		
	public static final ThreadLocal<APT_LoginHelper> APTLogin = new InheritableThreadLocal<>();
	public static final ThreadLocal<APT_MCS_CreateAccessCoreDeviceHelper> APT_CreateAccessCoreDeviceHelper = new InheritableThreadLocal<>();
	public static final ThreadLocal<APT_AutomationHelper> APT_Helper = new InheritableThreadLocal<>();
	public static final ThreadLocal<APT_NGINHelper> APT_NGIN = new InheritableThreadLocal<>();
	public static final ThreadLocal<APT_DomainManagementHelper> APT_DomainManageHelper = new InheritableThreadLocal<>();
	public static final ThreadLocal<APT_MSPLatencyHelper> APT_MSPLatencyHelper = new InheritableThreadLocal<>();
	public static final ThreadLocal<APT_ManageNetworkHelper> APT_MainManageNetworkHelper = new InheritableThreadLocal<>();
	public static final ThreadLocal<Hss_Helper> Hss = new InheritableThreadLocal<>();
	public static final ThreadLocal<ImsNmbrTranslator_Helper> ImsNmbrTranslator_Helper = new InheritableThreadLocal<>();
	public static final ThreadLocal<APT_CreateAccessCoreDevice_ManageNetworkHelper> APT_ManageNetworkHelpr = new InheritableThreadLocal<>();
	public static final ThreadLocal<APT_MCS_CreateAccessSwitchCoreDeviceHelper> APT_CreateAccessSwitchDeviceHelper = new InheritableThreadLocal<>();
	public static final ThreadLocal<APT_MCS_CreateCoreRouterDeviceHelper> APT_CreateCoreRouterDeviceHelper = new InheritableThreadLocal<>();
	public static final ThreadLocal<APT_MCS_CreateDCNDeviceHelper> APT_CreateDCNDeviceHelper = new InheritableThreadLocal<>();
	public static final ThreadLocal<APT_MCS_CreateDSLAMDeviceHelper> APT_CreateDSLAMDeviceHelper = new InheritableThreadLocal<>();
	public static final ThreadLocal<APT_MCS_CreateFirewallDeviceHelper> APT_CreateFirewallDeviceHelper = new InheritableThreadLocal<>();
	public static final ThreadLocal<APT_MCS_CreateKeyserverDeviceHelper> APT_CreateKeyserverDeviceHelper = new InheritableThreadLocal<>();
	public static final ThreadLocal<APT_MCS_CreateLoadBalancerDeviceHelper> APT_CreateLoadBalancerDeviceHelper = new InheritableThreadLocal<>();
	public static final ThreadLocal<APT_MCS_CreateMDFFirewallDeviceHelper> APT_CreateMDFFirewallDeviceHelper = new InheritableThreadLocal<>();
	public static final ThreadLocal<APT_MCS_CreateMiniDSLAMDeviceHelper> APT_CreateMiniDSLAMDeviceHelper = new InheritableThreadLocal<>();
	public static final ThreadLocal<APT_MCS_CreatePrizmnetDeviceHelper> APT_CreatePrizmnetDeviceHelper = new InheritableThreadLocal<>();
	public static final ThreadLocal<APT_MCS_CreateTrafficAggregatorDeviceHelper> APT_CreateTrafficAggregatorDeviceHelper = new InheritableThreadLocal<>();
	public static final ThreadLocal<APT_MCS_CreateVoiceGatewayDeviceHelper> APT_CreateVoiceGatewayDeviceHelper = new InheritableThreadLocal<>();
	public static final ThreadLocal<APT_MCS_CreateVOIPAccessDASSwitchDeviceHelper> APT_CreateVOIPAccessDASSwitchDeviceHelper = new InheritableThreadLocal<>();
	public static final ThreadLocal<APT_wholeSaleHelper> APT_wholesaleHelper = new InheritableThreadLocal<>();
//	public static final ThreadLocal<APT_MCS_CreateCustomerSeparateHelper>  createCustomerSeparateHelper = new InheritableThreadLocal<>();
	public static final ThreadLocal<APT_VoiceLineHelper> APT_VoiceLineHelper = new InheritableThreadLocal<>();
	public static final ThreadLocal<Lanlink_DirectFiberHelpr> DirectFiber = new ThreadLocal<>();
	public static final ThreadLocal<Lanlink_MetroHelper> Metro=new ThreadLocal<>();
	public static final ThreadLocal<Lanlink_OLOHelper> OLO=new ThreadLocal<>();
	public static final ThreadLocal<Lanlink_Outbandmanagementhelper> Outband = new InheritableThreadLocal<>();
	public static final ThreadLocal<Lanlink_InternationalHelper> International = new InheritableThreadLocal<>();
	public static final ThreadLocal<Lanlink_National> National= new InheritableThreadLocal<>();
	public static final ThreadLocal<manageNetwork_Lanlink> manageNetwork= new InheritableThreadLocal<>();
//	public static final ThreadLocal<APT_MCS_CreateCustomerHelper3Set> createCustomerHelper= new InheritableThreadLocal<>();
	public static final ThreadLocal<APT_MCS_CreateCustomerSeparateHelper> createCustomerSeparateHelper= new InheritableThreadLocal<>();
//		public static final ThreadLocal<APT_MCS_SearchCustomerHelper> searchCustomerHelper= new InheritableThreadLocal<>();
//		public static final ThreadLocal<PerformOrder_SupplyHelper> supply= new InheritableThreadLocal<>();
//		public static final ThreadLocal<APT_MCN_SearchDeviceHelper> APT_SearchDeviceHelper= new InheritableThreadLocal<>();
		
		
		
		public static com.saksoft.qa.listeners.TestListener Testlistener;
		public ThreadLocal<String> TestName=new ThreadLocal(); 
		public static SessionId session_id;
		public static ChromeDriver driver;
		public static int  itr;
		
		
		public static ExtentReports extent;
		public static ExtentTest logger;
		
		
		/**
		 * 
		 * @throws Exception 
		 * 
		 */
		
		@org.testng.annotations.Parameters({ "test-name" })
		@org.testng.annotations.BeforeSuite
		 
		public void BeforeSuite() throws Exception{
			
			String dateName1 = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			extent = new ExtentReports (System.getProperty("user.dir") +"/ExtentReports/"+"PK_ExtentReport-"+dateName1+".html", true);
			extent.addSystemInfo("Host Name", "APT_QA_Colt").addSystemInfo("Environment", "QA").addSystemInfo("User Name", "PramodKumar12345");
			
		itr=0;
		DOMConfigurator.configure("log4j.xml");	//For log
		Log.clearFile("C:\\Users\\SKathiresan-ADM\\APTAutomation_APTdemo_sanjeev\\APTAutomationProject_demoBranch\\Logs");
		
		// Open Browser
		  
		
		PropertyReader pr=new PropertyReader();
		String targatedbrowser=pr.readproperty("browser");	
		String url=pr.readproperty("URL");
		Log.info("URL");
		if(targatedbrowser.equals("chrome"))
		{ 
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			Map<String, Object> prefs = new HashMap<String, Object>();
			 // Set the notification setting it will override the default setting
			prefs.put("profile.default_content_setting_values.notifications", 2);

            // Create object of ChromeOption class
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", prefs);
			capabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "none");
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			System.setProperty("webdriver.chrome.driver",".\\lib\\chromedriver.exe");
			dr= new ChromeDriver(capabilities);
		}
		else if (targatedbrowser.equalsIgnoreCase("firefox"))
		{
			Log.info("For FF inprogress");
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			Map<String, Object> prefs = new HashMap<String, Object>();
			 // Set the notification setting it will override the default setting
			prefs.put("profile.default_content_setting_values.notifications", 2);
            // Create object of FirefoxOptions class
			FirefoxOptions options2 = new FirefoxOptions();
//			options2.setExperimentalOption("prefs", prefs);
			capabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "none");
//			capabilities.setCapability(FirefoxOptions.CAPABILITY, options2);
			System.setProperty("webdriver.gecko.driver",".\\lib\\geckodriver.exe");
			dr= new FirefoxDriver(capabilities);
		}
		else if (targatedbrowser.equalsIgnoreCase("ie"))
		{
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			Map<String, Object> prefs = new HashMap<String, Object>();
			 // Set the notification setting it will override the default setting
			prefs.put("profile.default_content_setting_values.notifications", 2);
            // Create object of ieOptions class
			InternetExplorerOptions options3 = new InternetExplorerOptions();
			//options3.ignoreZoomSettings();
			capabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "none");
			//capabilities.setCapability(InternetExplorerOptions.CAPABILITY, options3);
			System.setProperty("webdriver.ie.driver",".\\lib\\IEDriverServer.exe");
			dr= new InternetExplorerDriver(capabilities);
		}
		else
		{
			Log.info("For MS Edge is in progress");
		}
		
		
		
		dr.manage().window().maximize();
		WEB_DRIVER_THREAD_LOCAL.set(dr);
		Thread.sleep(3000);
		
		APT_LoginHelper apt=new APT_LoginHelper(getwebdriver());
		APTLogin.set(apt);
		
		APT_MCS_CreateAccessCoreDeviceHelper createdevice = new APT_MCS_CreateAccessCoreDeviceHelper(getwebdriver());
		APT_CreateAccessCoreDeviceHelper.set(createdevice);
		
		APT_NGINHelper ngin = new APT_NGINHelper(getwebdriver());
		APT_NGIN.set(ngin);
		
		APT_AutomationHelper aptautomation = new APT_AutomationHelper(getwebdriver());
		APT_Helper.set(aptautomation);
		
		APT_DomainManagementHelper DM = new APT_DomainManagementHelper(getwebdriver());
		APT_DomainManageHelper.set(DM);
		
		APT_MSPLatencyHelper msplatency = new APT_MSPLatencyHelper(getwebdriver());
		APT_MSPLatencyHelper.set(msplatency);
		
		APT_ManageNetworkHelper mainmanagenetwork = new APT_ManageNetworkHelper(getwebdriver());
		APT_MainManageNetworkHelper.set(mainmanagenetwork);
		
		Hss_Helper Hs = new Hss_Helper(getwebdriver());
		Hss.set(Hs);
		
		ImsNmbrTranslator_Helper imnt = new ImsNmbrTranslator_Helper(getwebdriver());
		ImsNmbrTranslator_Helper.set(imnt);
		
		APT_CreateAccessCoreDevice_ManageNetworkHelper  devicemanagenetwork= new APT_CreateAccessCoreDevice_ManageNetworkHelper(getwebdriver());
		APT_ManageNetworkHelpr.set(devicemanagenetwork);
		
		APT_MCS_CreateAccessSwitchCoreDeviceHelper createAccessSwitchdevice = new APT_MCS_CreateAccessSwitchCoreDeviceHelper(getwebdriver());
		APT_CreateAccessSwitchDeviceHelper.set(createAccessSwitchdevice);
		
		APT_MCS_CreateCoreRouterDeviceHelper createCoreRouterdevice = new APT_MCS_CreateCoreRouterDeviceHelper(getwebdriver());
		APT_CreateCoreRouterDeviceHelper.set(createCoreRouterdevice);
		
		APT_MCS_CreateDCNDeviceHelper createDCNdevice = new APT_MCS_CreateDCNDeviceHelper(getwebdriver());
		APT_CreateDCNDeviceHelper.set(createDCNdevice);
		
		APT_MCS_CreateDSLAMDeviceHelper createDSLAMdevice = new APT_MCS_CreateDSLAMDeviceHelper(getwebdriver());
		APT_CreateDSLAMDeviceHelper.set(createDSLAMdevice);
		
		APT_MCS_CreateFirewallDeviceHelper createFirewalldevice = new APT_MCS_CreateFirewallDeviceHelper(getwebdriver());
		APT_CreateFirewallDeviceHelper.set(createFirewalldevice);
		
		APT_MCS_CreateLoadBalancerDeviceHelper createLoadBalancerdevice = new APT_MCS_CreateLoadBalancerDeviceHelper(getwebdriver());
		APT_CreateLoadBalancerDeviceHelper.set(createLoadBalancerdevice);
		
		APT_MCS_CreateMDFFirewallDeviceHelper createMDFFirewalldevice = new APT_MCS_CreateMDFFirewallDeviceHelper(getwebdriver());
		APT_CreateMDFFirewallDeviceHelper.set(createMDFFirewalldevice);
		
		APT_MCS_CreateMiniDSLAMDeviceHelper createMiniDSLAMdevice = new APT_MCS_CreateMiniDSLAMDeviceHelper(getwebdriver());
		APT_CreateMiniDSLAMDeviceHelper.set(createMiniDSLAMdevice);
		
		APT_MCS_CreatePrizmnetDeviceHelper createPrizmnetdevice = new APT_MCS_CreatePrizmnetDeviceHelper(getwebdriver());
		APT_CreatePrizmnetDeviceHelper.set(createPrizmnetdevice);
		
		APT_MCS_CreateTrafficAggregatorDeviceHelper createTrafficAggregatordevice = new APT_MCS_CreateTrafficAggregatorDeviceHelper(getwebdriver());
		APT_CreateTrafficAggregatorDeviceHelper.set(createTrafficAggregatordevice);
		
		APT_MCS_CreateVoiceGatewayDeviceHelper createVoiceGatewaydevice = new APT_MCS_CreateVoiceGatewayDeviceHelper(getwebdriver());
		APT_CreateVoiceGatewayDeviceHelper.set(createVoiceGatewaydevice);
		
		APT_MCS_CreateVOIPAccessDASSwitchDeviceHelper createVOIPAccessDASSwitchdevice = new APT_MCS_CreateVOIPAccessDASSwitchDeviceHelper(getwebdriver());
		APT_CreateVOIPAccessDASSwitchDeviceHelper.set(createVOIPAccessDASSwitchdevice);
		
		APT_wholeSaleHelper wholeSale = new APT_wholeSaleHelper(getwebdriver());
		APT_wholesaleHelper.set(wholeSale);
		
		APT_MCS_CreateCustomerSeparateHelper customer = new APT_MCS_CreateCustomerSeparateHelper(getwebdriver());
		createCustomerSeparateHelper.set(customer);
		
		APT_VoiceLineHelper vlv= new APT_VoiceLineHelper(getwebdriver());
		APT_VoiceLineHelper.set(vlv);
		
		Lanlink_DirectFiberHelpr dirctFbr = new Lanlink_DirectFiberHelpr(getwebdriver());
		DirectFiber.set(dirctFbr);
		
		Lanlink_MetroHelper metro= new Lanlink_MetroHelper(getwebdriver());
		Metro.set(metro);
		
		Lanlink_OLOHelper olo=new Lanlink_OLOHelper(getwebdriver());
		OLO.set(olo);
		
		Lanlink_Outbandmanagementhelper out= new Lanlink_Outbandmanagementhelper(getwebdriver());
		Outband.set(out);
		
		Lanlink_InternationalHelper intnal = new Lanlink_InternationalHelper(getwebdriver());
		International.set(intnal);
		
		Lanlink_National natnal=new Lanlink_National(getwebdriver());
		National.set(natnal);
		
		manageNetwork_Lanlink manageNet=new manageNetwork_Lanlink(getwebdriver());
		manageNetwork.set(manageNet);
		
//		APT_MCS_CreateCustomerHelper3Set cc1=new APT_MCS_CreateCustomerHelper3Set(getwebdriver());
//		createCustomerHelper.set(cc1);
		
		APT_MCS_CreateCustomerSeparateHelper cc2=new APT_MCS_CreateCustomerSeparateHelper(getwebdriver());
		createCustomerSeparateHelper.set(cc2);

//		APT_MCS_SearchCustomerHelper sc=new APT_MCS_SearchCustomerHelper(getwebdriver());
//		searchCustomerHelper.set(sc);

		
//		PerformOrder_SupplyHelper sply= new PerformOrder_SupplyHelper(getwebdriver());
//		supply.set(sply);
		
//		APT_MCN_SearchDeviceHelper searchDevice= new APT_MCN_SearchDeviceHelper(getwebdriver());
//		APT_SearchDeviceHelper.set(searchDevice);

		
		apt.Login("APT_login_1");
		}
		
		
		
		
		
//		@org.testng.annotations.Parameters({ "test-name" })	
//		@org.testng.annotations.Parameters({"driver", "test-name"})
//		@BeforeTest
//		@org.testng.annotations.Parameters("browser")
//		 public void startReport() throws Exception{
//			
//		String dateName1 = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
//		extent = new ExtentReports (System.getProperty("user.dir") +"/ExtentReports/"+"PK_ExtentReport-"+dateName1+".html", true);
//		extent.addSystemInfo("Host Name", "APT_QA_Colt").addSystemInfo("Environment", "QA").addSystemInfo("User Name", "PramodKumar12345");
//		
//
//		 }
		
		
//		
//		 public static String getScreenhot(WebDriver driver, String screenshotName) throws Exception {
//		 String dateName2 = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
//		 TakesScreenshot ts = (TakesScreenshot) driver;
//		 File source = ts.getScreenshotAs(OutputType.FILE);
//		 String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/"+screenshotName+"-"+dateName2+".png";
//		 File finalDestination = new File(destination);
//		 FileUtils.copyFile(source, finalDestination);
//		 return destination;
//		 }
		 
		 
		 
		 
		 public WebDriver dr = null;
			@BeforeClass
			public void setup() throws Exception
			{
//				// Open Browser
//			  
//				
//				PropertyReader pr=new PropertyReader();
//				String targatedbrowser=pr.readproperty("browser");	
//				String url=pr.readproperty("URL");
//				Log.info("URL");
//				if(targatedbrowser.equals("chrome"))
//				{ 
//					DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//					Map<String, Object> prefs = new HashMap<String, Object>();
//					 // Set the notification setting it will override the default setting
//					prefs.put("profile.default_content_setting_values.notifications", 2);
//
//		            // Create object of ChromeOption class
//					ChromeOptions options = new ChromeOptions();
//					options.setExperimentalOption("prefs", prefs);
//					capabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "none");
//					capabilities.setCapability(ChromeOptions.CAPABILITY, options);
//					System.setProperty("webdriver.chrome.driver",".\\lib\\chromedriver.exe");
//					dr= new ChromeDriver(capabilities);
//				}
//				else if (targatedbrowser.equalsIgnoreCase("firefox"))
//				{
//					Log.info("For FF inprogress");
//					DesiredCapabilities capabilities = DesiredCapabilities.firefox();
//					Map<String, Object> prefs = new HashMap<String, Object>();
//					 // Set the notification setting it will override the default setting
//					prefs.put("profile.default_content_setting_values.notifications", 2);
//		            // Create object of FirefoxOptions class
//					FirefoxOptions options2 = new FirefoxOptions();
////					options2.setExperimentalOption("prefs", prefs);
//					capabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "none");
////					capabilities.setCapability(FirefoxOptions.CAPABILITY, options2);
//					System.setProperty("webdriver.gecko.driver",".\\lib\\geckodriver.exe");
//					dr= new FirefoxDriver(capabilities);
//				}
//				else if (targatedbrowser.equalsIgnoreCase("ie"))
//				{
//					DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
//					Map<String, Object> prefs = new HashMap<String, Object>();
//					 // Set the notification setting it will override the default setting
//					prefs.put("profile.default_content_setting_values.notifications", 2);
//		            // Create object of ieOptions class
//					InternetExplorerOptions options3 = new InternetExplorerOptions();
//					//options3.ignoreZoomSettings();
//					capabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "none");
//					//capabilities.setCapability(InternetExplorerOptions.CAPABILITY, options3);
//					System.setProperty("webdriver.ie.driver",".\\lib\\IEDriverServer.exe");
//					dr= new InternetExplorerDriver(capabilities);
//				}
//				else
//				{
//					Log.info("For MS Edge is in progress");
//				}
//				
//				
//				
//				dr.manage().window().maximize();
//				WEB_DRIVER_THREAD_LOCAL.set(dr);
//				Thread.sleep(3000);

				
				/**
				 * For APT projects
				 */
				APT_LoginHelper apt=new APT_LoginHelper(getwebdriver());
				APTLogin.set(apt);
				
			//LANLINK SERVICES
				
				

				
//				apt.Login("APT_login_1");
			}
		 
		 
		 
			@AfterMethod
			 public void getResult(ITestResult result) throws Exception{
			 if(result.getStatus() == ITestResult.FAILURE){
			 logger.log(LogStatus.FAIL, "Test Case Failed is : "+result.getName());
			 logger.log(LogStatus.FAIL, "Test Case Failed is : "+result.getThrowable());
			 String base64ScreenshotPath = "data:image/png;base64,"+((TakesScreenshot)getwebdriver()).getScreenshotAs(OutputType.BASE64);		 
			 logger.log(LogStatus.FAIL, logger.addScreenCapture(base64ScreenshotPath));
			 }else if(result.getStatus() == ITestResult.SKIP){
			 logger.log(LogStatus.SKIP, "Test Case Skipped is :"+result.getName());
			 												}
			 }
		 
		
		
		
		@AfterClass
		public void Teardown()
		{
//			dr.close();
		}
		
		public static WebDriver getwebdriver() {
			WebDriver dr = WEB_DRIVER_THREAD_LOCAL.get();
			return dr;
		}
		
		
		
		 @AfterTest
		 public void endReport(){
		extent.flush();
		extent.endTest(logger);
		//           extent.close();
			 
		    }
		 
		
		
		
		
	@BeforeMethod
	   public void BeforeMethod(Method method,ITestContext ctx, Object[] data) throws IOException, Exception{
	//	setup();
		
		Object[] st = null;
		
		try 
		
		{
	 	st=(Object[]) data[0];
		}
		catch(Exception e)
		{
			st=new Object[][] {{""}};
		}
	 
	 
	
	}

   

	
}
