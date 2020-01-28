package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class BaseFunction {

    private WebDriver browserDriver;
    private WebDriverWait wait;

    public BaseFunction() {
        System.setProperty("webdriver.chrome.driver", "c:/chromedriver.exe");
        browserDriver = new ChromeDriver();
        browserDriver.manage().window().maximize();
        browserDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(browserDriver, 15, 500);
    }

    public void goToUrl(String url) {
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "http://" + url;
        }
        browserDriver.get(url);
    }

    public void waitForVisibilityOfElement(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForVisibilityOfElementList(By locator) {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public WebElement getElement(By locator) {
        waitForVisibilityOfElement(locator);
        return browserDriver.findElement(locator);
    }

    public List<WebElement> getElementList(By locator) {
//        waitForVisibilityOfElementList(locator);
        return browserDriver.findElements(locator);
    }

    public String removeBrackets(String textWithBrackets) {
        return textWithBrackets.replaceAll("([()])", "").trim();
    }

    public void waitForElementIsClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void clickOnElement(WebElement element) {
        waitForElementIsClickable(element);
        element.click();
    }

    public void quit() {
        browserDriver.close();
    }

}
