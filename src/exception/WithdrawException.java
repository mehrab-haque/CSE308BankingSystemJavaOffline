package exception;

import config.Constants;

public class WithdrawException extends Exception{

    public static String WITHDRAW_LIMIT_EXCEEDED="You cannot withdraw more than your deposit amount";
    public static String WITHDRAW_MAX_LIMIT="You cannot withdraw more than $"+Constants.STUD_MAX_WITHDRAW;
    public static String FD_MIN_MATURITY="You must have a maturity of"+Constants.FD_MIN_MATURITY_YEARS+" years";
    public static String SAVINGS_MIN_DEPOSIT="You must have a minimum deposit of $"+Constants.SAVINGS_MIN_DEPOSIT;
    public static String LOAN_NO_WITHDRAW="You cannot withdraw from a loan account";

    public WithdrawException(String message){
        super(message);
    }
}
