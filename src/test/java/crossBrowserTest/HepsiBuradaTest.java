package crossBrowserTest;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import org.testng.annotations.Test;



public class HepsiBuradaTest extends WebLocates {


   @Story("Hepsiburada Test Senaryosu")
    @Test(description = "Urun Secimi ve Dogrulama Islemleri")
     public void HepsiburadaTest(){
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
