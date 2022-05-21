package employee;

import account.Account;
import exception.QueryException;

public class Cashier extends Employee {
    public Cashier(String name){
        super(name);
    }

    @Override
    public void approveLoan(String name) throws QueryException {
        throw new QueryException(QueryException.NOT_ALLOWED);
    }
}
