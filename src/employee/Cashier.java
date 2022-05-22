package employee;

import account.Account;
import config.Constants;
import exception.QueryException;

public class Cashier extends Employee {
    public Cashier(String name){
        super(name);
    }

    @Override
    public void approveLoan(String name) throws QueryException {
        throw new QueryException(QueryException.NOT_ALLOWED);
    }

    @Override
    public String getType() {
        return Constants.EMPLOYEE_TYPE_CASHIER;
    }
}
