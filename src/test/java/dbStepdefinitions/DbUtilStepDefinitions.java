package dbStepdefinitions;

import io.cucumber.java.en.Given;
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
        while (aktifSatirNumarasi<satirSayisi){

            Object satirdakiObje=DBUtils.getResultset().getObject(field);
            System.out.println(aktifSatirNumarasi + ".satirdaki fiyat :" + satirdakiObje.toString());
            DBUtils.getResultset().next();
            aktifSatirNumarasi++;
        }
    }
}
