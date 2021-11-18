package dbStepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import utilities.DBUtils;

import java.sql.SQLException;

public class DbUtilStepDefinitions {

    @Given("kullanici DBUtill ile CHQA database'ine baglanir")
    public void kullanici_db_utill_ile_chqa_database_ine_baglanir() {
        DBUtils.getConnection();

    }
    @Given("kullanici DBUtill ile {string} tablosundaki {string} verilerini alir")
    public void kullanici_db_utill_ile_tablosundaki_verilerini_alir(String table, String field) {
        String readQuery= "SELECT "+field+" FROM "+table;
        DBUtils.executeQuery(readQuery);

    }
    @Given("kullanici DBUtill ile {string} sutunundaki {int}. fiyatin {int} oldugunu test eder")
    public void kullanici_db_utill_ile_sutunundaki_fiyatin_oldugunu_test_eder(String field, Integer satirNo, Integer expectedPrice) throws Exception {

      int satirSayisi=DBUtils.getRowCount();
        int aktifSatirNumarasi=1;
        System.out.println(satirSayisi);

        DBUtils.getResultset().first();

        while (aktifSatirNumarasi<100){

            Object satirdakiObje=DBUtils.getResultset().getObject(field);
            System.out.println(aktifSatirNumarasi + ".satirdaki fiyat :" + satirdakiObje.toString());
            DBUtils.getResultset().next();
            aktifSatirNumarasi++;
        }

        DBUtils.getResultset().absolute(satirNo);
        int actualDeger= (int) DBUtils.getResultset().getDouble(field);
        Assert.assertTrue(actualDeger==expectedPrice);
    }

    @Then("DBU IDHotel degeri {int} olan kaydin Email degerini {string} yapar")
    public void dbuIDHotelDegeriOlanKaydinEmailDegeriniYapar(int IDHotelNo, String yeniEmail) throws SQLException {

        String updateQuery="UPDATE tHOTEL SET Email='"+yeniEmail+"' WHERE IDHotel="+IDHotelNo;
        DBUtils.executeQuery(updateQuery);





        String readQuery= "SELECT Email FROM tHOTEL";

        DBUtils.executeQuery(readQuery);

        int aktifSatirNumarasi=1;
        DBUtils.getResultset().first();

        while (aktifSatirNumarasi<100){

            Object satirdakiObje=DBUtils.getResultset().getObject("Email");
            System.out.println(aktifSatirNumarasi + ".satirdaki Email :" + satirdakiObje.toString());
            DBUtils.getResultset().next();
            aktifSatirNumarasi++;
        }





    }
}
