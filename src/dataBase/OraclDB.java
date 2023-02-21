package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class OraclDB
{

	public static void main(String[] args) throws Exception
	{
		String dburl = "jdbc:Oracle:thin:@localhost:1521:xe";   // Oracle database URL
		String dnun	= "System";				// Oracle UserName
		String dbpw = "System";				// password
		
		Connection con = DriverManager.getConnection(dburl, dnun, dbpw);
		Statement st = con.createStatement();
		ResultSet result = st.executeQuery("SELECT * FROM HRMS_LOGIN"); 
		while(result.next())
		{
			String un = result.getString(1);
			System.out.println(un);           						     					  // Application UserName
			String pw = result.getString(2);
			System.out.println(pw);													    	// Application Password
		
		System.setProperty("webdriver.chrome.driver", "E:\\Software\\Webdrivers\\chromedriver.exe");
		System.out.println("Open Application");
		
		WebDriver driver= new ChromeDriver();
		driver.navigate().to("http://127.0.0.1/orangehrm-2.6/login.php");
		
		driver.findElement(By.name("txtUserName")).sendKeys(un);
		driver.findElement(By.name("txtPassword")).sendKeys(pw);
		Thread.sleep(3000);
		
		driver.findElement(By.name("Submit")).click();
		driver.findElement(By.linkText("Logout")).click();
		driver.close();
		
		}
		 
		result.close();
		st.close();
		con.close();
	}
}
