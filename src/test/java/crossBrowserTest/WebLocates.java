package crossBrowserTest;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utilities.ConfigReader;
import java.util.List;

public class WebLocates extends TestBaseCross{


    WebElement siralamaIstemi;
    String actual;
    String expected;
    static String urunTitle;

    By cookie_accept_button=By.cssSelector("[id='onetrust-accept-btn-handler']");
    By mainpage_elektronik_button=By.xpath("//span[starts-with(@class,'sf-MenuItems')]/span[text()='Elektronik']");
    By mainpage_elektronikmenu_bilgisayarTablet_button=By.xpath("//div[starts-with(@class,'sf-ChildMenuItems')]//a[text()='Bilgisayar/Tablet']");
    By mainpage_bilgisayarTabletMenu_notebook_button=By.xpath("//span[text()='Notebook']");
    By filtre_bolumu_apple_checkbox=By.cssSelector("[value='apple']");
    By marka_filtre_field=By.xpath("//div[@data-test-id=\"filterbox-content-children\"]");
    By sortingBar_button=By.xpath("//div[starts-with(@id,\"SortingBox\")]/div/div/div");
    By productPage_product_list=By.xpath("(//li[starts-with(@class,'productListContent')])");
    By add_card_after_toastcontainer_mesaj=By.xpath("//div[@class='hb-toast-message']");
    By card_button=By.id("shoppingCart");
    By cardPage_products_names=By.xpath("//div[starts-with(@class,'product_name')]");
    By alisverisi_tamamla_button=By.cssSelector("[id='continue_step_btn']");
    By girisyap_ekrani_text=By.xpath("//div[@role='presentation']/div[contains(text(),'Giriş yap')]");






    public void anasayfa_ac(String sayfaAdi){
        driver.get(ConfigReader.getProperty(sayfaAdi));
        TestBaseCross.waitForPageToLoad(5);
      //  driver.findElement(cookie_accept_button).click();
    }

    public void anasayfa_acildiginin_dogrulamasi(){
        TestBaseCross.waitForPageToLoad(5);
        actual=driver.getCurrentUrl();
        expected="https://www.hepsiburada.com/";
        Assert.assertEquals(actual,expected);
    }


    public void urun_sec(String Katergori,String AltKategori,String Urun){
        TestBaseCross.hover(driver.findElement(By.xpath("//span[starts-with(@class,'sf-MenuItems')]/span[text()='"+Katergori+"']")));
        TestBaseCross.waitFor(3);
        TestBaseCross.hover(driver.findElement(By.xpath("//div[starts-with(@class,'sf-ChildMenuItems')]//a[text()='"+AltKategori+"']")));
       TestBaseCross.waitFor(3);
        driver.findElement(By.xpath("//span[text()='"+Urun+"']")).click();

    }

    public void urun_secimi_dogrulamasi(String urunAdi){
        TestBaseCross.waitForPageToLoad(5);
        actual=driver.getCurrentUrl();
        expected=urunAdi.toLowerCase();
        Assert.assertTrue(actual.contains(expected));

    }


    public void marka_filitrelemesi_yap(String Marka){
        driver.navigate().refresh();
        TestBaseCross.waitForPageToLoad(5);
        List<WebElement> markaList=driver.findElements(marka_filtre_field);
        TestBaseCross.scrollIntoView(markaList.get(0));
        WebElement marka=driver.findElement(By.cssSelector("[value='"+Marka.toLowerCase()+"']"));
        TestBaseCross.waitForClickablility(marka,10);
        marka.click();
        driver.navigate().refresh();


    }

    public void liste_siralamasi_yapma(String SiralamaSekli){
          TestBaseCross.waitForPageToLoad(10);
         TestBaseCross.scrollIntoView(driver.findElement(sortingBar_button));
       WebElement sortingMenu=driver.findElement(sortingBar_button);
         sortingMenu.click();
        String siralama=SiralamaSekli.replaceAll(" ","").
                replaceAll("Ç","c").replaceAll("Ş","s").
                replaceAll("Ü","u").replaceAll("I","i").
                replaceAll("ğ","g").
                replaceAll("ı","i").toLowerCase();
       switch (siralama){
           case "artanfiyat":
              siralamaIstemi=driver.findElement(By.xpath("//input[@value='artanfiyat']//ancestor::a[starts-with(@class,'horizontalSortingBar')]"));
              break;
           case "azalanfiyat":
               siralamaIstemi=driver.findElement(By.xpath("//input[@value='azalanfiyat']//ancestor::a[starts-with(@class,'horizontalSortingBar')]"));
               break;
           case "coksatanlar":
               siralamaIstemi=driver.findElement(By.xpath("//input[@value='coksatan']//ancestor::a[starts-with(@class,'horizontalSortingBar')]"));
               break;
           case "cokdegerlendirilenler":
               siralamaIstemi=driver.findElement(By.xpath("//input[@value='yorumsayisi']//ancestor::a[starts-with(@class,'horizontalSortingBar')]"));
               break;
           case "yüksekpuanlilar":
               siralamaIstemi=driver.findElement(By.xpath("//input[@value='degerlendirmepuani']//ancestor::a[starts-with(@class,'horizontalSortingBar')]"));
               break;
           case "indirimorani":
               siralamaIstemi=driver.findElement(By.xpath("//input[@value='indirimurunler']//ancestor::a[starts-with(@class,'horizontalSortingBar')]"));
               break;
           case "yenieklenenler":
               siralamaIstemi=driver.findElement(By.xpath("//input[@value='enyeni']//ancestor::a[starts-with(@class,'horizontalSortingBar')]"));
               break;
       }
       TestBaseCross.scrollIntoView(siralamaIstemi);
       TestBaseCross.waitForClickablility(siralamaIstemi,5);
        siralamaIstemi.click();
    }


    public void urun_sec(int UrunSirasi){
        driver.navigate().refresh();
        TestBaseCross.waitFor(3);
        WebElement urunp=driver.findElement(By.xpath("//li[starts-with(@class,'productListContent')]["+UrunSirasi+"]"));
     // WebElement urunp=driver.findElement(By.xpath(urun));
        TestBaseCross.scrollIntoView(urunp);
        TestBaseCross.hover(urunp);
        urunTitle=driver.findElement(By.xpath("//li[starts-with(@class,'productListContent')]["+UrunSirasi+"]//h3")).getText();
        WebElement uruns=driver.findElement(By.xpath("//li[starts-with(@class,'productListContent')]["+UrunSirasi+"]"+"//div[contains(text(),'Sepete ekle')]"));
        TestBaseCross.waitForClickablility(uruns,5);
        uruns.click();

    }


    public void sepete_uruneklenmesi_dogrulamasi(){
        TestBaseCross.fluentWait(driver.findElement(add_card_after_toastcontainer_mesaj),10);
        Assert.assertTrue(driver.findElement(add_card_after_toastcontainer_mesaj).isDisplayed());
    }

    public void sepet_sayfasini_ac(){
        TestBaseCross.scrollIntoView(driver.findElement(card_button));
        driver.findElement(card_button).click();
    }

    public void sepet_sayfasinda_urun_dogrulamasi(){
        TestBaseCross.waitForPageToLoad(5);
        List<WebElement>urunTitles=driver.findElements(cardPage_products_names);
        actual=urunTitles.get(0).getText();
        actual=actual.substring(0,urunTitle.length());
        System.out.println(actual + "\n" + urunTitle);
        Assert.assertEquals(actual,urunTitle);
    }

    public void alisverisi_tamamla_button_click(){
        driver.findElement(alisverisi_tamamla_button).click();
    }
    @Step("Alisverisi Tamamla Butonuna Basildiktan Sonra Login Ekrani Acildigi Dogrulanir")
    public void login_ekrani_dogrulamasi(){
        TestBaseCross.waitForPageToLoad(5);
        Assert.assertTrue(driver.findElement(girisyap_ekrani_text).isDisplayed());
    }

    public void browser_penceresini_kapat(){
        driver.quit();
    }
}
