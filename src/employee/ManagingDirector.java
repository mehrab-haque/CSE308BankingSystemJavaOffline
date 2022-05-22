package employee;

import bank.Bank;
import config.Constants;
import exception.QueryException;

public class ManagingDirector extends Employee {
    public ManagingDirector(String name){
        super(name);
    }

    @Override
    public void changeInterestRate(String type, double pct) throws QueryException {
        if(type.toLowerCase().equals(Constants.AC_TYPE_FD))
            Constants.INTEREST_PCT_FD=pct;
        else if(type.toLowerCase().equals(Constants.AC_TYPE_SAVINGS))
            Constants.INTEREST_PCT_SAVINGS=pct;
        else if(type.toLowerCase().equals(Constants.AC_TYPE_STUDENT))
            Constants.INTEREST_PCT_STUDENT=pct;
        else throw new QueryException(QueryException.INVALID_AC_TYPE);
    }

    @Override
    public double getInternalFund() throws QueryException {
        return Bank.getInstance().getInternalFund();
    }

    @Override
    public String getType() {
        return Constants.EMPLOYEE_TYPE_MD;
    }
}
