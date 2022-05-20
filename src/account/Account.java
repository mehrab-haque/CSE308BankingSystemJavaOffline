package account;

import bank.Bank;
import config.Constants;
import exception.AccountCreationException;
import exception.DepositException;
import exception.RequestLoanException;
import exception.WithdrawException;

public class Account {
    private String name;
    private double deposit;
    private double loan;
    private double requestedLoan;
    private boolean isLoanApproved;

    public Account(){}

    public Account(String name,double deposit,double loan) throws AccountCreationException {
        if(name.trim().length()==0)
            throw new AccountCreationException(AccountCreationException.MSG_ACCOUNT_NAME_EMPTY);
        if(Bank.getInstance().nameExists(name))
            throw new AccountCreationException(AccountCreationException.MSG_ACCOUNT_ALREADY_EXISTS);
        this.name=name;
        this.deposit=deposit;
        this.loan=loan;
        this.requestedLoan=0;
        this.isLoanApproved=false;
    }

    public void deposit(double amount) throws DepositException {
        deposit+=amount;
    }

    public void reduceLoan(double amount){
        if(amount>loan){
            loan=0;
            deposit+=amount-loan;
        }else
            loan-=amount;
    }

    public void withdraw(double amount) throws WithdrawException {
        if(amount>deposit)
            throw new WithdrawException(WithdrawException.WITHDRAW_LIMIT_EXCEEDED);
        deposit-=amount;
    }

    public double getDeposit() {
        return deposit;
    }

    public void requestLoan(double amount) throws RequestLoanException{
        if(requestedLoan>0)throw new RequestLoanException(RequestLoanException.LOAN_ALREADY_REQUESTED);
        requestedLoan=amount;
        isLoanApproved=false;
    }

    public double getLoan() {
        return loan;
    }

    public double getInterestPCT(){
        return 0;
    }

    public void deductServiceCharge(){
        if(deposit<Constants.ANNUAL_SERVICE_CHARGE)
            deposit=0;
        else deposit-=Constants.ANNUAL_SERVICE_CHARGE;
    }

    public void deductLoanInterest(){}

    protected void setDeposit(double deposit) {
        this.deposit = deposit;
    }
}
