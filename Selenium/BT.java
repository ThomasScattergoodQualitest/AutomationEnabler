package Selenium; // Generated by Selenium IDE
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class BT {
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    // Make a loop to iterate though all pages of BT reviews and retrieve name, date and reviews (32 pages total, 192 reviews total)
    @Test
    public void BTReviewsPg1() throws IOException {
        driver.get("https://www.fasterbroadband.co.uk/broadband-reviews/bt");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        JavascriptExecutor js = (JavascriptExecutor) driver;
// make a list containing the 3 WebElements
//        List<String> ReviewList = new ArrayList<>();
//        List<String> NameList = new ArrayList<>();
//        List<String> DateList = new ArrayList<>();
//        List<String> StarList = new ArrayList<>();

        List<String> list = new ArrayList<>();
        List<WebElement> review = new ArrayList<>();
        List<WebElement> name = new ArrayList<>();
        List<WebElement> date = new ArrayList<>();
        int page = 1;
        try {
            while (page < 5) {
               // reviews2 = driver.findElements(By.xpath("//*[@id='jreview-listing']"));
                review = driver.findElements(By.xpath("//div[@class='re-control-value']"));
                name = driver.findElements(By.xpath("//div[@class='re-author-name']"));
                date = driver.findElements(By.xpath("//div[@class='re-date']"));
                js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
                driver.findElement(By.linkText("Next")).click();
                page++;
            }

            for (int i = 0; i < review.size(); i++) {
                String reviews = review.get(i).getText();
                String names = name.get(i).getText();
                String dates = date.get(i).getText();

                list.add(dates);
                list.add(reviews);
                list.add(names);
                System.out.println(list);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(list);

        FileWriter writer = new FileWriter("BTAllPages.txt");
        for (String str : list) {
            writer.write(str + System.lineSeparator() + System.lineSeparator());
        }
        writer.close();

        File file = new File("BTAllPages.csv");
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("Name, Date, Review");
        bw.newLine();
        for (int i = 0; i < (list.size()); i++) {
            bw.write(list.get(i) + ", " );
            bw.newLine();
        }
        bw.close();
        fw.close();
    }

}

/*
        //reviews
        List<WebElement> reviews = driver.findElements(By.xpath("//div[@class='re-control-value']"));
        for (WebElement webElement : reviews) {
            String review = webElement.getText().replaceAll(",", "");
            System.out.println(review);
            ReviewList.add(review);
            AllList.add(review);
        }

        //Names
        List<WebElement> names = driver.findElements(By.xpath("//span[@class='re-author-name']"));
        for (WebElement webElement : names) {
            String review = webElement.getText();
            System.out.println(review);
            NameList.add(review);
            AllList.add(review);
        }

        //Dates
        List<WebElement> dates = driver.findElements(By.xpath("//span[@class='re-date']"));
        for (WebElement webElement : dates) {
            String review = webElement.getText();
            System.out.println(review);
            DateList.add(review);
            AllList.add(review);
        }

        //Stars
        List<WebElement> stars = driver.findElements(By.xpath("//div[@class='re_header']/.."));
        for (WebElement webElement : stars) {
            String review = webElement.getAttribute("data-rating");
            System.out.println(review);
            StarList.add(review);
            AllList.add(review);
        }

        System.out.println(AllList);

        FileWriter writer = new FileWriter("BTReviewsPg1.txt");
        for (String str : AllList) {
            writer.write(str + System.lineSeparator() + System.lineSeparator());
        }
        writer.close();

        File file = new File("BTPg1.csv");
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("Name, Date, Review");
        bw.newLine();
        for (int i = 0; i < (ReviewList.size()); i++) {
            bw.write(NameList.get(i) + ", " + DateList.get(i) + ", " + ReviewList.get(i));
            bw.newLine();
        }
        bw.close();
        fw.close();
    }


    @Test
    public void BTReviewsAllPages() throws IOException {

        driver.get("https://www.fasterbroadband.co.uk/broadband-reviews/bt");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

// make a list containing the 3 WebElements
        List<String> ReviewList = new ArrayList<>();
        List<String> NameList = new ArrayList<>();
        List<String> DateList = new ArrayList<>();
        List<String> StarList = new ArrayList<>();

        List<String> AllList = new ArrayList<>();


        //reviews
        List<WebElement> reviews = driver.findElements(By.xpath("//div[@class='re-control-value']"));
        for (WebElement webElement : reviews) {
            String review = webElement.getText().replaceAll(",", "");
            System.out.println(review);
            ReviewList.add(review);
            AllList.add(review);
        }


        //Names
        List<WebElement> names = driver.findElements(By.xpath("//span[@class='re-author-name']"));
        for (WebElement webElement : names) {
            String review = webElement.getText();
            System.out.println(review);
            NameList.add(review);
            AllList.add(review);
        }

        //Dates
        List<WebElement> dates = driver.findElements(By.xpath("//span[@class='re-date']"));
        for (WebElement webElement : dates) {
            String review = webElement.getText();
            System.out.println(review);
            DateList.add(review);
            AllList.add(review);
        }

        driver.findElement(By.xpath("//*[@id=\"jreview-pagination\"]/ul/li[7]/a")).click();

        try{
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //reviews
        List<WebElement> reviews2 = driver.findElements(By.xpath("//div[@class='re-control-value']"));
        for (WebElement webElement : reviews2) {
            String review = webElement.getText().replaceAll(",", "");
            System.out.println(review);
            ReviewList.add(review);
            AllList.add(review);
        }


        //Names
        List<WebElement> names2 = driver.findElements(By.xpath("//span[@class='re-author-name']"));
        for (WebElement webElement : names2) {
            String review = webElement.getText();
            System.out.println(review);
            NameList.add(review);
            AllList.add(review);
        }

        //Dates
        List<WebElement> dates2 = driver.findElements(By.xpath("//span[@class='re-date']"));
        for (WebElement webElement : dates2) {
            String review = webElement.getText();
            System.out.println(review);
            DateList.add(review);
            AllList.add(review);
        }

        driver.findElement(By.xpath("//*[@id=\"jreview-pagination\"]/ul/li[7]/a")).click();

        try{
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }

//reviews
        List<WebElement> reviews3 = driver.findElements(By.xpath("//div[@class='re-control-value']"));
        for (WebElement webElement : reviews3) {
            String review = webElement.getText().replaceAll(",", "");
            System.out.println(review);
            ReviewList.add(review);
            AllList.add(review);
        }


        //Names
        List<WebElement> names3 = driver.findElements(By.xpath("//span[@class='re-author-name']"));
        for (WebElement webElement : names3) {
            String review = webElement.getText();
            System.out.println(review);
            NameList.add(review);
            AllList.add(review);
        }

        //Dates
        List<WebElement> dates3 = driver.findElements(By.xpath("//span[@class='re-date']"));
        for (WebElement webElement : dates3) {
            String review = webElement.getText();
            System.out.println(review);
            DateList.add(review);
            AllList.add(review);
        }

        driver.findElement(By.xpath("//*[@id=\"jreview-pagination\"]/ul/li[7]/a")).click();




        System.out.println(AllList);

        FileWriter writer = new FileWriter("BTAllPages.txt");
        for (String str : ReviewList) {
            writer.write(str + System.lineSeparator() + System.lineSeparator());
        }
        writer.close();

        File file = new File("BTAllPages.csv");
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("Name, Date, Review");
        bw.newLine();
        for (int i = 0; i < (ReviewList.size()); i++) {
            bw.write(NameList.get(i) + ", " + DateList.get(i) + ", " + ReviewList.get(i));
            bw.newLine();
        }
        bw.close();
        fw.close();
    }

    }

 */