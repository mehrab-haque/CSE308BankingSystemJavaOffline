package exception;

import config.Constants;

public class AccountCreationException extends Exception{
    public static String MSG_ACCOUNT_ALREADY_EXISTS="An account already exists with this name, try to use another name";
    public static String MSG_ACCOUNT_NAME_EMPTY="Name cannot be empty";
    public static String MSG_FD_MIN_AMOUNT="Initial fixed deposit amount must be at least $"+ Constants.FD_MIN_AMOUNT;
    public AccountCreationException(String message){
        super(message);
    }
}
