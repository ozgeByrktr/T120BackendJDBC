
Feature:Data Testing
  Background: Database connectivity
    * Database baglantısı kurulur
  Scenario: "Subscribers" tablosunda "email" datalarında  "a"  içermeyen dataları listeleyiniz.

    * subscribersQuery hazirlanir
    * data dogrulanır
    * Database kapatilir

    Scenario:Deposits total amount test

      * depositsAmountQuery hazirlanir
      * totalAmountdata dogrulanır
      * Database kapatilir
  @DB
    Scenario: Loans insert and delete
      * loansInsertQuery hazirlanir
      * deleteLoansQuery hazırlanır
      * Datanın silindigi dogrulanır
      * Database kapatilir


