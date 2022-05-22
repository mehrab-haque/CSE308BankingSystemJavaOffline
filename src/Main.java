import account.Account;
import bank.Bank;
import config.Commands;
import config.Constants;
import employee.Employee;
import exception.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        Account currentAccount=null;
        Employee currentEmployee=null;

        Bank.getInstance().initializeEmployees();
        System.out.println("Bank Created; MD, S1, S2, C1, C2, C3, C4, C5 created");
        //Scanner scanner=new Scanner(System.in);

        File file=new File("input.txt");
        Scanner scanner=new Scanner(file);
        while(true){
            try{
                String commandLine=scanner.nextLine();
                String[] commands=commandLine.split(" ");
                if(commands[0].trim().toLowerCase().equals(Commands.COMMAND_CREATE.toLowerCase())){
                    try {
                        Bank.getInstance().addAccount(commands[2],commands[1],Double.parseDouble(commands[3]));
                        currentAccount=Bank.getInstance().getAccountByName(commands[1]);
                        System.out.println(commands[2]+" account for "+commands[1]+" Created; initial balance $"+Double.parseDouble(commands[3]));
                    } catch (AccountCreationException e) {
                        System.out.println(e.getMessage());
                    } catch (QueryException e) {
                        throw new RuntimeException(e);
                    }
                }
                else if(commands[0].trim().toLowerCase().equals(Commands.COMMAND_DEPOSIT.toLowerCase())){
                    try{
                        currentAccount.deposit(Double.parseDouble(commands[1]));
                        System.out.println(Double.parseDouble(commands[1])+"$ deposited; current balance "+currentAccount.getDeposit()+"$");
                    } catch (DepositException e) {
                        System.out.println("Invalid transaction; current balance"+currentAccount.getDeposit()+"$");
                    }
                }
                else if(commands[0].trim().toLowerCase().equals(Commands.COMMAND_WITHDRAW.toLowerCase())){
                    try{
                        currentAccount.withdraw(Double.parseDouble(commands[1]));
                        System.out.println(Double.parseDouble(commands[1])+"$ withdrawn; current balance "+currentAccount.getDeposit()+"$");
                    } catch (WithdrawException e) {
                        System.out.println("Invalid transaction; current balance "+currentAccount.getDeposit()+"$");
                    }
                }
                else if(commands[0].trim().toLowerCase().equals(Commands.COMMAND_QUERY.toLowerCase())){
                    System.out.print("Current balance "+currentAccount.getDeposit()+"$");
                    if(currentAccount.getLoan()>0) System.out.println(", loan "+currentAccount.getLoan()+"$");
                    else System.out.println();
                }
                else if(commands[0].trim().toLowerCase().equals(Commands.COMMAND_REQUEST.toLowerCase())){
                    try {
                        currentAccount.requestLoan(Double.parseDouble(commands[1]));
                        System.out.println("Loan request successful, sent for approval");
                    } catch (RequestLoanException e) {
                        System.out.println(e.getMessage());
                    }
                }
                else if(commands[0].trim().toLowerCase().equals(Commands.COMMAND_LOGOUT.toLowerCase())){
                    if(currentAccount!=null)
                        System.out.println("Transaction Closed for "+currentAccount.getName());
                    else if(currentEmployee!=null)
                        System.out.println("Operations for "+currentEmployee.getName()+" closed");
                    currentAccount=null;
                    currentEmployee=null;
                }
                else if(commands[0].trim().toLowerCase().equals(Commands.COMMAND_OPEN.toLowerCase())){
                    try {
                        currentAccount=Bank.getInstance().getAccountByName(commands[1]);
                        System.out.println("Welcome back, "+commands[1]);
                    } catch (QueryException e) {

                    }
                    if(currentAccount==null){
                        try {
                            currentEmployee=Bank.getInstance().getEmployeeByName(commands[1]);
                            System.out.print(commands[1]+" active");
                            if((currentEmployee.getType()== Constants.EMPLOYEE_TYPE_MD || currentEmployee.getType()==Constants.EMPLOYEE_TYPE_OFFICER) && Bank.getInstance().isLoanApprovalPending())
                                System.out.println(", there are loan approvals pending");
                            else System.out.println();
                        } catch (QueryException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
                else if(commands[0].trim().toLowerCase().equals(Commands.COMMAND_APPROVE_LOAN.toLowerCase())){
                    try {
                        for(Account account : Bank.getInstance().getAccounts())
                            if(account.getRequestedLoan()>0) {
                                currentEmployee.approveLoan(account.getName());
                                System.out.println("Loan for "+account.getName()+" approved");
                            }
                    } catch (QueryException e) {
                        System.out.println(e.getMessage());
                    }
                }
                else if(commands[0].trim().toLowerCase().equals(Commands.COMMAND_CHANGE_RATE.toLowerCase())){
                    try {
                        currentEmployee.changeInterestRate(commands[1],Double.parseDouble(commands[2]));
                    } catch (QueryException e) {
                        System.out.println(e.getMessage());
                    }
                }
                else if(commands[0].trim().toLowerCase().equals(Commands.COMMAND_LOOKUP.toLowerCase())){
                    try {
                        double deposit=currentEmployee.lookup(commands[1]);
                        System.out.println(commands[1]+"'s current balance "+deposit+"$");
                    } catch (QueryException e) {
                        System.out.println(e.getMessage());
                    }
                }
                else if(commands[0].trim().toLowerCase().equals(Commands.COMMAND_SEE.toLowerCase())){
                    try {
                        double fund=currentEmployee.getInternalFund();
                        System.out.println("Internal fund is $"+fund);
                    } catch (QueryException e) {
                        System.out.println(e.getMessage());
                    }
                }
                else if(commands[0].trim().toLowerCase().equals(Commands.COMMAND_INC.toLowerCase())){
                    Bank.getInstance().incrementClock();
                    System.out.println(Bank.getInstance().getClock()+" year(s) passed");
                }
                else if(commands[0].trim().toLowerCase().equals(Commands.COMMAND_EXIT.toLowerCase())){
                    break;
                }
            }catch (Exception e){

            }
        }
    }
}
