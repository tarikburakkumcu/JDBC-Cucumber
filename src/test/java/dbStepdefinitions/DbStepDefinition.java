package dbStepdefinitions;

import io.cucumber.java.en.Given;
import org.junit.Assert;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DbStepDefinition {
    String url="jdbc:sqlserver://184.168.194.58:1433;databaseName=qaconcorthotel; user=Ahmet_User;password=Ahmet123!";
    String username="Ahmet_User";
    String password="Ahmet123!";
    Connection connection;
    Statement statement;
    ResultSet resultSet;

    @Given("kullanici CHQA database'ine baglanir")
    public void kullanici_chqa_database_ine_baglanir() throws SQLException {
        connection=DriverManager.getConnection(url,username,password);
        statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    }
    @Given("kullanici {string} tablosundaki {string} verilerini alir")
    public void kullanici_tablosundaki_verilerini_alir(String table, String field) throws SQLException {
        // SELECT Price FROM tHOTELROOM
        String readQuery= "SELECT "+field+" FROM "+table;

        resultSet=statement.executeQuery(readQuery);
    }
    @Given("kullanici {string} sutunundaki verileri okur ve istedigi islemleri yapar")
    public void kullanici_sutunundaki_verileri_okur_ve_istedigi_islemleri_yapar(String field) throws SQLException {

        // resultset iterator gibi calisir
        resultSet.first();
        System.out.println(resultSet.getObject(field).toString());

        // resultSet.next() bir sonraki objeye gecer, sonraki eleman varsa true, yoksa false doner
        System.out.println(resultSet.next()); // true
        System.out.println(resultSet.getObject(field).toString());
        // son objeye gider ve sonra resultSet.next() calistirirsak
        // bir sonraki eleman olmayacagi icin bize false doner
        resultSet.last();
        System.out.println(resultSet.next()); // false

        // tum listeyi yazdirmak istersek while loop ile birlikte resultSet.next() kullanabiliriz
        // cunku resultSet.next() bir sonraki obje var oldugu muddetce bize true dondurecek
        // ve while loop calismaya devam edecek
        // son obje ulastigimizda resultSet.next() false donecek ve while loop bitecek
        // Ancak biz 40. satirda son objeye gittigimiz icin
        // while loop u calistirirsak hic bir sey yazdirmaz
        // while loop calistirmadan once ilk objeye gitmemiz gerekir

        /*
        resultSet.first(); // birinci objeye gittim
        while (resultSet.next()){ // usteki satir birinci objeye goturdu ama resultSet.next() sonraki objeye gecirdi
                                    // dolayisiyla liste 2.fiyattan basladi
            System.out.println(resultSet.getObject(field).toString());
        }

        */

        /* mehmet hoca da sona kadar gitmiyor, onun icin bu kismi yoruma aldik
        resultSet.absolute(0);
        while (resultSet.next()){
            System.out.println(resultSet.getObject(field).toString());
        }

        */
        // varsa ilk 100 fiyati double olarak bir liste seklinde kaydedelim

        int sayac=1;
        resultSet.absolute(0);
        List<Double> ilkYuzSayi= new ArrayList<>();

        while (sayac<=100 && resultSet.next()){


            ilkYuzSayi.add(resultSet.getDouble(field));
            sayac++;
        }

        System.out.println(ilkYuzSayi);
        System.out.println(ilkYuzSayi.size());


        // 7.fiyatin double olarak 620.0 oldugunu test edin

        resultSet.absolute(7);
        double yedinciSayi=resultSet.getDouble(field);

        Assert.assertTrue(yedinciSayi==620);
    }
}
