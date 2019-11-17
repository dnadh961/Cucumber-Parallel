package com.webtest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import cucumber.runtime.java.guice.ScenarioScoped;

@ScenarioScoped
public class SUT{
	
	private WebDriver driver = null;
	private Configuration config = null;
	private Browser browser = null;
	private WaitHandler waitHandler = null;
	private ScriptExecutor scriptExecutor = null;
	
	public SUT() {
		initializeDriver();
	}

	//useful for suppressing warnings (yellow background lines)
	@SuppressWarnings("deprecation")
	private void initializeDriver() {
		String browserName = getConfiguration().getBrowserName();
		if("firefox".equals(browserName) || "".equals(browserName))
		{
			System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
		}
		else if("chrome".equals(browserName))
		{
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("start-maximized"); //To maximize the browser
			options.addArguments("--test-type");  //To get rid off 'ignore certificate errors' message           options.addArguments("--kiosk");  //To enable full screen mode
			options.addArguments("chrome.switches","--disable-extensions");
			driver = new ChromeDriver(options);
		}
		else if("iexplore".equals(browserName))
		{
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			capabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
			capabilities.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, true);
			capabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
			capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, false);
			System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");
			driver = new InternetExplorerDriver(capabilities);
			driver.manage().window().maximize();
		}else if("edge".equals(browserName)) {
			System.setProperty("webdriver.edge.driver", "MicrosoftWebDriver.exe");
			driver = new EdgeDriver();
			driver.manage().window().maximize();
		}
	}

	public Browser getBrowser() {
		if(browser==null){
			browser = new Browser(this);
		}
		return browser;
	}

	public WebDriver getDriver() { 
		return driver;
	}
	
	public WaitHandler handleWaits(){
		if(waitHandler == null){
			waitHandler = new WaitHandler(this);
		}
		return waitHandler;
	}
	
	public void kill(){
		driver.quit();
	}

	public Configuration getConfiguration() {
		if(config == null){
			config = new Configuration();
		}
		return config;
	}
	
	public ScriptExecutor getScriptExecutor(){
		if(scriptExecutor == null){
			scriptExecutor = new ScriptExecutor(this);
		}
		return scriptExecutor;
	}

}
