package com.example;

import java.io.FileInputStream;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    WebDriver driver;
    private static final Logger log=Logger.getLogger(AppTest.class);
    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.get("https://www.barnesandnoble.com/");
        driver.manage().window().maximize();
    }
    @Test
    public void testcaseone() throws Exception
    {

        Thread.sleep(5000);
        WebElement drop=driver.findElement(By.xpath("//*[@id=\"rhf_header_element\"]/nav/div/div[3]/form/div/div[1]"));
        drop.click();
        driver.findElement(By.linkText("Books")).click();
        Thread.sleep(5000);
        FileInputStream file=new FileInputStream("C:\\Users\\subas\\Downloads\\barnesandnoble.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sh = workbook.getSheet("search");
        XSSFRow r1 = sh.getRow(1);
        String searchkey = r1.getCell(0).getStringCellValue();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id='rhf_header_element']/nav/div/div[3]/form/div/div[2]/div/input[1]")).sendKeys(searchkey);
        driver.findElement(By.xpath("//*[@id='rhf_header_element']/nav/div/div[3]/form/div/span/button")).click();
        WebElement check=driver.findElement(By.xpath("//*[@id='searchGrid']/div/section[1]/section[1]/div/div[1]/div[1]/h1/span"));
        String str=check.getText();
        if(str.equals("Chetan Bhagat"))
        {
            System.out.println("Chetan Bhagat is present");
        }
        else{
            System.out.println("Chetan Bhagat is not present");
        }
        Thread.sleep(5000);
        workbook.close();
        log.info("Test case one executed successfully");
    }
    @Test
    public void testcasetwo() throws Exception{
        Actions action=new Actions(driver);
        action.moveToElement(driver.findElement(By.linkText("Audiobooks"))).perform();
        Thread.sleep(5000);
        driver.findElement(By.xpath("/html/body/div[2]/header/nav/div/div[4]/div/ul/li[5]/div/div/div[1]/div/div[2]/div[1]/dd/a[1]")).click();
        Thread.sleep(5000);
        driver.findElement(By.linkText("Funny Story")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"otherAvailFormats\"]/div/div/div[3]/a")).click();
        driver.findElement(By.xpath("//*[@id='prodInfoContainer']/div[3]/form[1]/input[11]")).click();
        Thread.sleep(5000);
        driver.switchTo().alert();
        Thread.sleep(5000);
        WebElement popup=driver.findElement(By.xpath("//*[@id='add-to-bag-main']/div[1]"));
        String pop=popup.getText();
        if(pop.contains("Successfully"))
        {
            System.out.println("Item Added to Cart");

        }
        else{
            System.out.println("Item Not added to Cart");
        }
    }
    @Test
    public void testcasethree()throws Exception{
        Thread.sleep(5000);
        driver.findElement(By.xpath("/html/body/main/div[3]/div[3]/div/section/div/div/div/div/div/a[1]/div")).click();
        driver.findElement(By.xpath("//*[@id='mb-landing']/div[1]/div[2]/div/div/div[2]/div/div[70]/div"));
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"rewards-modal-link\"]")).click();
        Thread.sleep(5000);
        driver.switchTo().alert();
        Thread.sleep(5000);
        WebElement signin=driver.findElement(By.xpath("//*[@id='dialog-title']"));
        String txt=signin.getText();
        if(txt.contains("Sigin ")){
            System.out.println("Text present");
        }
        else{
            System.out.println("Text not present");
        }
    }
    @AfterMethod
    public void closeMethod(){
        driver.quit();
    }
    @AfterClass
    public void log4j()throws Exception{
        PropertyConfigurator.configure("C:\\Users\\subas\\Documents\\cc2\\src");


    }
}
