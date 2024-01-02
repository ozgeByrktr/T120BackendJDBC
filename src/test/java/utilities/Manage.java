package utilities;

public class Manage {
private static String subscribersQuery="Select * from subscribers Where email Not Like '%a%';";

    public static String getSubscribersQuery() {
        return subscribersQuery;
    }

    public static String getDepositsSumAmount() {
        return depositsSumAmount;
    }

    private static String depositsSumAmount="Select Sum(final_amo) AS total_amount from deposits Where status=1 And created_at between '2023-01-01' And '2023-12-25';";
private static String loansInsertQuery="Insert into loans (loan_number,id,user_id,plan_id)Values(?,?,?,?);";

    public static String getLoansInsertQuery() {
        return loansInsertQuery;
    }

    public static String getLoansDeleteQuery() {
        return loansDeleteQuery;
    }

    private static String loansDeleteQuery="Delete from loans Where loan_number=? ;";
}
