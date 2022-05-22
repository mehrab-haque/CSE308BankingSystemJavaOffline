package bank;

import account.*;
import config.Constants;
import employee.Cashier;
import employee.Employee;
import employee.ManagingDirector;
import employee.Officer;
import exception.AccountCreationException;
import exception.QueryException;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private static Bank instance=null;
    private static double internalFund;
    private int clock;
    private List<Account> accounts;
    private List<Employee> employees;

    public void initializeEmployees(){
        employees.add(new ManagingDirector("MD"));
        employees.add(new Officer("S1"));
        employees.add(new Officer("S2"));
        employees.add(new Cashier("C1"));
        employees.add(new Cashier("C2"));
        employees.add(new Cashier("C3"));
        employees.add(new Cashier("C4"));
        employees.add(new Cashier("C5"));
    }

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
        boolean isFound=false;
        for(Account account : accounts)
            if(account.getName().trim().toLowerCase().equals(name.trim().toLowerCase())){
                isFound=true;
                break;
            }
        return isFound;
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
        internalFund=amount;
    }

    public void incrementClock(){
        addDepositInterest();
        deductLoanInterest();
        deductServiceCharge();
        clock++;
    }

    public Account getAccountByName(String name) throws QueryException {
        Account foundAccount=null;
        for(Account account :accounts){
            if(account.getName().trim().toLowerCase().equals(name.toLowerCase().trim())){
                foundAccount=account;
                break;
            }
        }
        if (foundAccount==null)throw new QueryException(QueryException.USER_NOT_FOUND);
        return foundAccount;
    }

    public Employee getEmployeeByName(String name) throws QueryException {
        Employee foundEmployee=null;
        for(Employee employee :employees){
            if(employee.getName().trim().toLowerCase().equals(name.toLowerCase().trim())){
                foundEmployee=employee;
                break;
            }
        }
        if (foundEmployee==null)throw new QueryException(QueryException.USER_NOT_FOUND);
        return foundEmployee;
    }

    public void addAccount(String type,String name, double amount) throws AccountCreationException{
        if(type.trim().toLowerCase().equals(Constants.AC_TYPE_STUDENT.trim().toLowerCase()))
            accounts.add(new StudentAccount(name,amount));
        else if(type.trim().toLowerCase().equals(Constants.AC_TYPE_SAVINGS.trim().toLowerCase()))
            accounts.add(new SavingsAccount(name,amount));
        else if(type.trim().toLowerCase().equals(Constants.AC_TYPE_FD.trim().toLowerCase()))
            accounts.add(new FixedDepositAccount(name,amount));
        else if(type.trim().toLowerCase().equals(Constants.AC_TYPE_LOAN.trim().toLowerCase()))
            accounts.add(new LoanAccount(name,amount));
        else throw new AccountCreationException(AccountCreationException.MSG_AC_TYPE_MISMATCH);
    }

    public boolean isLoanApprovalPending(){
        boolean result=false;
        for(Account account:accounts)
            if(account.getRequestedLoan()>0){
                result=true;
                break;
            }
        return result;
    }

    public List<Account> getAccounts(){
        return accounts;
    }


}
