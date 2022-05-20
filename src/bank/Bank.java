package bank;

import account.Account;
import employee.Employee;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private static Bank instance=null;

    private int clock;
    private List<Account> accounts;
    private List<Employee> employees;

    private Bank(){
        clock=0;
        accounts=new ArrayList<>();
        employees=new ArrayList<>();
    }

    public static synchronized Bank getInstance(){
        if(instance==null)
            instance=new Bank();
        return instance;
    }
}
