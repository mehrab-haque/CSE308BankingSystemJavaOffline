package account;

import config.Constants;
import exception.AccountCreationException;
import exception.RequestLoanException;
import exception.WithdrawException;

public class SavingsAccount extends Account {
    public SavingsAccount(String name, double amount) throws AccountCreationException {
        super(name,amount,0);
    }

    @Override
    public void withdraw(double amount) throws WithdrawException {
        if(getDeposit()-amount< Constants.SAVINGS_MIN_DEPOSIT)
            throw new WithdrawException(WithdrawException.SAVINGS_MIN_DEPOSIT);
        super.withdraw(amount);
    }

    @Override
    public void requestLoan(double amount) throws RequestLoanException {
        if(amount+getLoan()>Constants.LOAN_MAX_SAVINGS)
            throw new RequestLoanException(RequestLoanException.LOAN_MAX_AMOUNT);
        super.requestLoan(amount);
    }

    @Override
    public double getInterestPCT() {
        return Constants.INTEREST_PCT_SAVINGS;
    }
}
