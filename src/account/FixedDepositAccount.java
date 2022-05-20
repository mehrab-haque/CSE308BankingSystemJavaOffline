package account;

import bank.Bank;
import config.Constants;
import exception.AccountCreationException;
import exception.DepositException;
import exception.RequestLoanException;
import exception.WithdrawException;

public class FixedDepositAccount extends Account {

    public FixedDepositAccount(String name, double deposit) throws AccountCreationException {
        super(name, deposit,0);
        if(deposit< Constants.FD_MIN_AMOUNT)
            throw new AccountCreationException(AccountCreationException.MSG_FD_MIN_AMOUNT);
    }

    @Override
    public void deposit(double amount) throws DepositException {
        if(amount<Constants.FD_MIN_DEPOSITE)
            throw new DepositException(DepositException.MSG_FD_MIN_DEPOSIT);
        super.deposit(amount);
    }

    @Override
    public void withdraw(double amount) throws WithdrawException {
        if(Bank.getInstance().getClock()<Constants.FD_MIN_MATURITY_YEARS)
            throw new WithdrawException(WithdrawException.FD_MIN_MATURITY);
        super.withdraw(amount);
    }

    @Override
    public void requestLoan(double amount) throws RequestLoanException {
        if(amount+getLoan()>Constants.LOAN_MAX_FD)
            throw new RequestLoanException(RequestLoanException.LOAN_MAX_AMOUNT);
        super.requestLoan(amount);
    }

    @Override
    public double getInterestPCT() {
        return Constants.INTEREST_PCT_FD;
    }

    @Override
    public void deductLoanInterest() {

    }
}
