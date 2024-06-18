import com.github.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class NegativeTests extends BaseTest {

    @Test
    public void invalidEmailTest() {
        driver.get("https://github.com");

        WebElement signUpButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Sign up")));
        signUpButton.click();

        WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.id("email")));
        emailField.sendKeys("notValidEmail");

        WebElement validationError = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email-err")));
        wait.until(ExpectedConditions.textToBePresentInElement(validationError, "Email is invalid or already taken"));
    }


    @Test
    public void invalidPasswordTest() {
        driver.get("https://github.com");

        WebElement signUpButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Sign up")));
        signUpButton.click();

        WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.id("email")));
        emailField.sendKeys("didko@gmail.com");

        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-continue-to=password-container]")));
        continueButton.click();

        String[] passwords = {
                "abvgd",
                "1234567890",
                "haha",
                "azsumbe123"
        };

        String[] errors = {
                "Password needs a number and lowercase letter",
                "Password needs a number and lowercase letter",
                "Password is too short",
                "Password is too long",
        };

        for (int i = 0; i < passwords.length; i++) {
            WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.id("password")));
            passwordField.sendKeys(passwords[i]);

            WebElement validationError = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("password-err")));
            wait.until(ExpectedConditions.textToBePresentInElement(validationError, errors[i]));
            passwordField.clear();
        }
    }

    @Test
    public void invalidUsernameTest() {
        driver.get("https://github.com");

        WebElement signUpButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Sign up")));
        signUpButton.click();

        WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.id("email")));
        emailField.sendKeys("didko@gmail.com");

        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-continue-to=password-container]")));
        continueButton.click();

        WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.id("password")));
        passwordField.sendKeys("neshtositam123!");

        continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-continue-to=username-container]")));
        continueButton.click();

        WebElement usernameField = wait.until(ExpectedConditions.elementToBeClickable(By.id("login")));
        usernameField.sendKeys("1235");

        WebElement validationError = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("login-err")));
        wait.until(ExpectedConditions.textToBePresentInElement(validationError, "Username 1234 is not available"));
    }
}