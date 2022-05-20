package account;

import config.Constants;
import exception.AccountCreationException;
import exception.DepositException;
import exception.RequestLoanException;
import exception.WithdrawException;

public class LoanAccount extends Account{
    public LoanAccount(String name, double loan) throws AccountCreationException {
        super(name,0,loan);
    }

    @Override
    public void deposit(double amount){
        reduceLoan(amount);
    }

    @Override
    public void withdraw(double amount) throws WithdrawException {
        throw new WithdrawException(WithdrawException.LOAN_NO_WITHDRAW);
    }

    @Override
    public void requestLoan(double amount) throws RequestLoanException {
        if(amount> Constants.LOAN_MAX_LOAN_PCT*getLoan()/100)
            throw new RequestLoanException(RequestLoanException.LOAN_MAX_AMOUNT);
        super.requestLoan(amount);
    }

    @Override
    public void deductServiceCharge(){

    }
}
