package exception;

import config.Constants;

public class DepositException extends Exception{
    public static String MSG_FD_MIN_DEPOSIT="Deposit amount for fixed deposite account must be at least $"+ Constants.FD_MIN_DEPOSITE;
    public DepositException(String message){
        super(message);
    }
}
