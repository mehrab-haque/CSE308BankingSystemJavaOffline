package exception;

public class RequestLoanException extends Exception{

    public static String LOAN_ALREADY_REQUESTED="You have already a loan issue going on";
    public static String LOAN_MAX_AMOUNT="You cannot request this amount for loan";

    public RequestLoanException(String message){
        super(message);
    }
}
