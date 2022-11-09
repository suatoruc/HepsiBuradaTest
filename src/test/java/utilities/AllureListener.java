package utilities;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class AllureListener implements ITestListener {
    private static WebDriver driver= Driver.getDriver();


private static String getTestMethodName(ITestResult iTestResult){
    return iTestResult.getMethod().getConstructorOrMethod().getName();
}
    @Attachment
    public byte[] saveFailureScreenShot(WebDriver driver){
        System.out.println(("ScreenShot alindi"));
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
}
    @Attachment(value = "{0}",type = "text/plain")
    public static String saveTextLog(String message){
    return message;
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        System.out.println((iTestContext.getName()));
        iTestContext.setAttribute("Webdriver", driver);
    }
    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println((iTestContext.getName()));

    }
    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println(("Test içindeki" + getTestMethodName(iTestResult) + " Testi Baslatildi."));

    }
    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println(("Test içindeki " + getTestMethodName(iTestResult) + " Testin Çalışmasi Başarili Bir Şekilde Sonladirildi"));
    }
    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println((getTestMethodName(iTestResult) + " test is failed."));
        Object testClass = iTestResult.getInstance();
        WebDriver driver= Driver.getDriver();

        if (driver instanceof WebDriver) {

            System.out.println((getTestMethodName(iTestResult) + " Testi için ScrrenShot Alindi "));

            saveFailureScreenShot(driver);
        }
        saveTextLog(getTestMethodName(iTestResult)+ " test fail oldu ve Screen Shot Alindi");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult){
        System.out.println(("getTestMethodName(iTestResult) + \"Bu Test Çalıştırılmadan Atlanıldı\""));

    }

       @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult){
           System.out.println((getTestMethodName(iTestResult) + "test başarısız Oldu Ancak Tanımlanmış Başarı Oranında"));
       }








}


