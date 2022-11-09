package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import page.WebLocates;
import utilities.AllureListener;
import java.lang.reflect.Method;

@Listeners({AllureListener.class})
public class HepsiBuradaTest extends WebLocates {

    @Story("Hepsiburada Test Senaryosu")
    @Test(description = "Urun Secimi ve Dogrulama Islemleri")
    @Description("Test Description:Login Olmadan Filtrele Özelligi Kullanılarak ürün Seçiminin ve Sepette Bu Ürünün Doğrulamasının Yapılması")
    public void HepsiburadaTest(Method method){
        anasayfa_ac("Hepsiburada");
        anasayfa_acildiginin_dogrulamasi();
        urun_sec("Elektronik","Bilgisayar/Tablet","Notebook");
        urun_secimi_dogrulamasi("Notebook");
        marka_filitrelemesi_yap("Apple");
        liste_siralamasi_yapma("Çok satanlar");
        urun_sec(2);
        sepete_uruneklenmesi_dogrulamasi();
        sepet_sayfasini_ac();
        sepet_sayfasinda_urun_dogrulamasi();
        alisverisi_tamamla_button_click();
        login_ekrani_dogrulamasi();
        browser_penceresini_kapat();

    }
}
