import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;


import java.util.UUID;


public class TestWebsite {

    @Test
    void userRegistrationProcessTest() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        String URL = "http://automationpractice.com/index.php";

        driver.get(URL);
        driver.manage().window().maximize();

        // Click on Sign in
        driver.findElement(By.linkText("Sign in")).click();

        // Enter email address
        UUID uuid = UUID.randomUUID();
        driver
                .findElement(By.cssSelector("[name='email_create']"))
                .sendKeys("adna.torlo@" + uuid + ".com");

        // Click on "Create an account"
        driver.findElement(By.xpath("//button[@name=\"SubmitCreate\"]")).click();
        Thread.sleep(8000);
        // Select Title
        driver.findElement(By.id("id_gender2")).click();
        driver.findElement(By.name("customer_firstname")).sendKeys("Adna");
        driver.findElement(By.name("customer_lastname")).sendKeys("Torlo");
        driver.findElement(By.id("passwd")).sendKeys("AdnaTorlo123!");

        // Enter your address
        driver.findElement(By.id("company")).sendKeys("FIT");
        driver.findElement(By.id("address1")).sendKeys("S Blue Island Ave");
        driver.findElement(By.id("city")).sendKeys("Chicago");

        // Select State
        WebElement statedropdown = driver.findElement(By.name("id_state"));
        Select oSelect = new Select(statedropdown);
        oSelect.selectByValue("14");

        driver.findElement(By.name("postcode")).sendKeys("60608");

        // Select Country
        WebElement countrydropDown = driver.findElement(By.name("id_country"));
        Select oSelectC = new Select(countrydropDown);
        oSelectC.selectByVisibleText("United States");

        // Enter Mobile Number
        driver.findElement(By.id("phone_mobile")).sendKeys("38762002501");
        driver.findElement(By.xpath("//input[@name=\"alias\"]")).clear();
        driver.findElement(By.xpath("//input[@name=\"alias\"]")).sendKeys("Garfield Bvr");
        driver.findElement(By.name("submitAccount")).click();
        String userText = driver.findElement(By.id("my-account")).getText();
        String secondUserText =
                driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a")).getText();

        // Validate that user has created
        assert (secondUserText.contains("Adna"));
        assert (userText.contains(
                "Welcome to your account. Here you can manage all of your personal information and orders."));

        driver.close();
    }

    @Test
    void womanButtonClickTest() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        String URL = "http://automationpractice.com/index.php";

        driver.get(URL);
        driver.manage().window().maximize();

        // Click on Woman button and assert that it opens the correct page
        WebElement womenTab = driver.findElement(By.linkText("WOMEN"));
        womenTab.click();
        String text = driver.findElement(By.className("rte")).getText();
        System.out.println(text);
        assert (text.contains("You will find here all woman fashion collections."));

        // Close the browser
        driver.close();
    }

    @Test
    void endToEndBuyProduct() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        String URL = "http://automationpractice.com/index.php";

        // Open URL and Maximize browser window
        driver.get(URL);
        driver.manage().window().maximize();
        Thread.sleep(3000);

        // Click on Sign in
        driver.findElement(By.linkText("Sign in")).click();
        // Login
        driver.findElement(By.id("email")).sendKeys("adna.torlo@edu.fit.ba");
        driver.findElement(By.id("passwd")).sendKeys("AdnaTorlo123!");
        driver.findElement(By.id("SubmitLogin")).click();
        // Click on Women
        driver.findElement(By.linkText("WOMEN")).click();
        WebElement shortSleeveShirtElement =
                driver.findElement(By.xpath("//img[@alt='Faded Short Sleeve T-shirts']"));
        WebElement addToCart = driver.findElement(By.xpath("//a[@data-id-product='1']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(shortSleeveShirtElement).moveToElement(addToCart).click().perform();

        Thread.sleep(5000);
        driver.findElement(By.linkText("Proceed to checkout")).click();

        // Change quantity to 2
        driver.findElement(By.name("quantity_1_1_0_753736")).clear();
        driver.findElement(By.name("quantity_1_1_0_753736")).sendKeys("2");

        driver.findElement(By.linkText("Proceed to checkout")).click();
        driver.findElement(By.name("processAddress")).click();
        driver.findElement(By.id("uniform-cgv")).click();
        driver.findElement(By.name("processCarrier")).click();

        // Click on Payby Check
        driver
                .findElement(By.xpath("/html/body/div[1]/div[2]/div/div[3]/div/div/div[3]/div[2]/div/p/a"))
                .click();
        // Confirm the order
        driver
                .findElement(By.xpath("/html/body/div[1]/div[2]/div/div[3]/div/form/p/button/span"))
                .click();

        // Get Text
        String ConfirmationText =
                driver
                        .findElement(By.xpath("//div[@id='center_column']/p[@class='alert alert-success']"))
                        .getText();

        assert (ConfirmationText.contains("complete"));
        driver.close();
    }
}

