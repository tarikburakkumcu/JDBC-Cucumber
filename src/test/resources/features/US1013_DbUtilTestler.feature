Feature: US1013 Kullanici DB Util ile concort Hotel database'ini test eder
  @dbutill
  Scenario: TC19 DB Util ile Concort Hotel Database Read Test

    Given kullanici DBUtill ile CHQA database'ine baglanir
    And kullanici DBUtill ile "tHOTELROOM" tablosundaki "Price" verilerini alir
    And kullanici DBUtill ile "Price" sutunundaki 5. fiyatin 400 oldugunu test eder
