package dbStepdefinitions;

import io.cucumber.java.en.Given;

import java.sql.*;


public class CHQAstepdefinitions {

    String url= "jdbc:sqlserver://184.168.194.58:1433;databaseName=qaconcorthotel; user=Ahmet_User;password=Ahmet123!";
    String username="fhctipdb_user";
    String password="P2s@rt65";
    Connection connection;
    Statement statement;
    ResultSet resultSet;

    @Given("kullanici CHQA veritabanina baglanir")
    public void kullanici_chqa_veritabanina_baglanir() throws SQLException {
        connection= DriverManager.getConnection(url, username, password);
        statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

    }
    @Given("kullanici {string} tablosundaki {string} verilerini alir")
    public void kullanici_tablosundaki_verilerini_alir(String table, String field) throws SQLException {
        String readQuery= "SELECT "+field+" FROM dbo."+table;
        // database olusturan developer'lar bazen ekstra guvenlik onlemleri ekleyebilirler
        // bunu da devops veya database developer ekibinin bize vermesi lazim
        resultSet=statement.executeQuery(readQuery);
    }
    @Given("kullanici {string} sutunundaki verileri okur")
    public void kullanici_sutunundaki_verileri_okur(String field) throws SQLException {
        // tum price verilerim resultSet'e yuklendi

        resultSet.first(); // resultSet'i 1. dataya goturur onunde bekler
        Object ilkSatirdakiObje=resultSet.getObject(field);
        System.out.println(ilkSatirdakiObje.toString());

        for (int i=1; i<100 ;i++){
            resultSet.next();
            System.out.println(i + "-" + resultSet.getObject(field).toString());
        }
    }


}
