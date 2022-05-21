package bank;

import account.Account;
import account.StudentAccount;
import config.Constants;
import employee.Employee;
import exception.AccountCreationException;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private static Bank instance=null;
    private static double internalFund;
    private int clock;
    private List<Account> accounts;
    private List<Employee> employees;

    private Bank(){
        clock=0;
        accounts=new ArrayList<>();
        employees=new ArrayList<>();
        internalFund= Constants.INITIAL_INTERNAL_FUND;
    }

    public static synchronized Bank getInstance(){
        if(instance==null)
            instance=new Bank();
        return instance;
    }

    public boolean nameExists(String name){
        //need to code
        return true;
    }

    public int getClock() {
        return clock;
    }


    private void deductServiceCharge(){
        for(Account account:accounts)
            account.deductServiceCharge();
    }

    public void deductLoanInterest(){
        for(Account account:accounts)
            account.deductLoanInterest();
    }

    public void addDepositInterest(){
        for(Account account:accounts)
            account.addProfitInterest();
    }

    public double getInternalFund(){
        return internalFund;
    }

    public void setInternalFund(double amount){
        internalFund+=amount;
    }

    public void incrementClock(){
        addDepositInterest();
        deductLoanInterest();
        deductServiceCharge();
        clock++;
    }

    public Account getAccountByName(String name){
        return accounts.get(0);
    }


}
