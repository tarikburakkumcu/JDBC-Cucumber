Feature: Db baglanma

  @db
  Scenario: TC_18 kullanici veri okur
    Given kullanici CHQA veritabanina baglanir
    And kullanici "tHOTELROOM" tablosundaki "Price" verilerini alir
    And kullanici "Price" sutunundaki verileri okur
