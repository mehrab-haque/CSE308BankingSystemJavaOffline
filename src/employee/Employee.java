package employee;

import account.Account;
import bank.Bank;
import exception.QueryException;

public class Employee {
    private String name;

    public Employee(String name){
        this.name=name;
    }

    public Employee() {

    }

    public String getName(){
        return name;
    }

    public double lookup(String acName) throws QueryException{
        if(!Bank.getInstance().nameExists(acName))
            throw new QueryException(QueryException.USER_NOT_FOUND);
        Account account=Bank.getInstance().getAccountByName(acName);
        return account.getDeposit();
    }

    public void approveLoan(String name) throws QueryException{
        if(!Bank.getInstance().nameExists(name))
            throw new QueryException(QueryException.USER_NOT_FOUND);
        Account account= Bank.getInstance().getAccountByName(name);
        if(account.getRequestedLoan()>0){
            account.addRequestedLoan();
            account.resetRequestedLoan();
        }else throw new QueryException(QueryException.LOAN_NOT_REQUESTED);
    }

    public void declineLoan(String name) throws QueryException{
        if(!Bank.getInstance().nameExists(name))
            throw new QueryException(QueryException.USER_NOT_FOUND);
        Account account= Bank.getInstance().getAccountByName(name);
        if(account.getRequestedLoan()>0)
            account.resetRequestedLoan();
        else throw new QueryException(QueryException.LOAN_NOT_REQUESTED);
    }

    public void changeInterestRate(String type,double pct) throws QueryException{
        throw new QueryException(QueryException.NOT_ALLOWED);
    }

    public double getInternalFund() throws QueryException{
        throw new QueryException(QueryException.NOT_ALLOWED);
    }

    public String getType(){
        return null;
    }
}
