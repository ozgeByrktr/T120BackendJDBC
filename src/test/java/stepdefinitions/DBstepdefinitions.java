package stepdefinitions;

import com.github.javafaker.Faker;
import io.cucumber.java.en.Given;
import org.junit.Assert;
import utilities.DBUtils;
import utilities.Manage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBstepdefinitions {
    String query;
    ResultSet rs;
    int id;
    int user_id;
    int plan_id;
    String loan_number;
    Faker faker=new Faker();
    PreparedStatement preparedStatement;

    @Given("Database connection is established.")
    public void database_connection_is_established() {
        // DBUtils.createConnection();
    }

    @Given("Database baglantısı kurulur")
    public void database_baglantısı_kurulur() {
        DBUtils.createConnection();
    }

    @Given("subscribersQuery hazirlanir")
    public void subscribers_query_hazirlanir() throws SQLException {
        query = Manage.getSubscribersQuery();
        rs = DBUtils.getStatement().executeQuery(query);
    }

    @Given("data dogrulanır")
    public void data_dogrulanır() throws SQLException {
        List<String> aicerenEmail = new ArrayList<>();
        List<String> aicermeyenEmail = new ArrayList<>();

        while (rs.next()) {

            String email = rs.getString("email");
            if (email.contains("a")) {
                aicerenEmail.add(email);


            } else {
                aicermeyenEmail.add(email);
            }
            System.out.println(aicermeyenEmail);
        }
    }

    @Given("Database kapatilir")
    public void database_kapatilir() {
        DBUtils.closeConnection();
    }
    @Given("depositsAmountQuery hazirlanir")
    public void deposits_amount_query_hazirlanir() throws SQLException {
       query=Manage.getDepositsSumAmount();
       rs=DBUtils.getStatement().executeQuery(query);
    }
    @Given("totalAmountdata dogrulanır")
    public void total_amountdata_dogrulanır() throws SQLException {
        int expectedToatlAmount=91501709;
       rs.next();
        int actualToalAmount= rs.getInt("total_amount");
        System.out.println(expectedToatlAmount+"   ***   "+actualToalAmount);
        Assert.assertEquals(expectedToatlAmount,actualToalAmount);
    }
    @Given("loansInsertQuery hazirlanir")
    public void loans_ınsert_query_hazirlanir() throws SQLException {
    query=Manage.getLoansInsertQuery();
    loan_number=faker.internet().password();
    user_id=faker.number().numberBetween(10,1000);
    id=faker.number().numberBetween(700,1000);
    plan_id=faker.number().numberBetween(0,1);
    preparedStatement=DBUtils.getPraperedStatement(query);
    preparedStatement.setString(1,loan_number);
    preparedStatement.setInt(2,id);
    preparedStatement.setInt(3,user_id);
    preparedStatement.setInt(4,plan_id);
        System.out.println("**"+loan_number+"**");
        int rowCount=preparedStatement.executeUpdate();
        Assert.assertEquals(1,rowCount);

    }
    @Given("deleteLoansQuery hazırlanır")
    public void delete_loans_query_hazırlanır() throws SQLException {
        query=Manage.getLoansDeleteQuery();
        System.out.println(loan_number);
        preparedStatement=DBUtils.getPraperedStatement(query);
        preparedStatement.setString(1,loan_number);

    }
    @Given("Datanın silindigi dogrulanır")
    public void datanın_silindigi_dogrulanır() throws SQLException {
       int rowCount=preparedStatement.executeUpdate();
       Assert.assertEquals(1,rowCount);
    }



}
