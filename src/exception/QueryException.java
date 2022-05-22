package exception;

public class QueryException extends Exception{

    public static String USER_NOT_FOUND="There is no user with this account name";
    public static String NOT_ALLOWED="You are not allowed to do this operation";
    public static String LOAN_NOT_REQUESTED="Loan was not requested by this user";
    public static String INVALID_AC_TYPE="Invalid account type";

    public QueryException(String message){
        super(message);
    }
}
