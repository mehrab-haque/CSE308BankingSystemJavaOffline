package account;

import config.Constants;
import exception.AccountCreationException;
import exception.RequestLoanException;
import exception.WithdrawException;

public class StudentAccount extends Account{
    public StudentAccount(String name, double amount) throws AccountCreationException {
        super(name,amount,0);
    }

    @Override
    public void withdraw(double amount) throws WithdrawException {
        if(amount> Constants.STUD_MAX_WITHDRAW)
            throw new WithdrawException(WithdrawException.WITHDRAW_MAX_LIMIT);
        super.withdraw(amount);
    }

    @Override
    public void requestLoan(double amount) throws RequestLoanException {
        if(amount+getLoan()>Constants.LOAN_MAX_STUDENT)
            throw new RequestLoanException(RequestLoanException.LOAN_MAX_AMOUNT);
        super.requestLoan(amount);
    }

    @Override
    public double getInterestPCT() {
        return Constants.INTEREST_PCT_STUDENT;
    }
}
