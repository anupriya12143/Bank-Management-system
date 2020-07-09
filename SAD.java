import java.util.Scanner;


class BankApp
{
public static void main(String[] args)
{
    Scanner s = new Scanner(System.in);
    Bank myBank = new Bank();

    int user_choice = 0;

    do
    {
        System.out.println();
        System.out.println("1) Open a new bank account");
        System.out.println("2) Deposit to a bank account");
        System.out.println("3) Withdraw bank account");
        System.out.println("4) Print short account information");
        System.out.println("5) Print the detailed account information including last transactions");
        System.out.println("6) Quit");
        System.out.println();
        System.out.print("Enter choice [1-6]: ");
        user_choice = s.nextInt();
        switch (user_choice) 
        {
            case 1: System.out.println("Enter a customer name");
                    String cn = s.next();
                    System.out.println("Enter a opening balance");
                    double d = s.nextDouble();
                    System.out.println("Account was created and it has the following number: " + myBank.openNewAccount(cn, d));
                    break;
            case 2: System.out.println("Enter a account number");
                    int an = s.nextInt();
                    System.out.println("Enter a deposit amount");
                    double da = s.nextDouble();
                    myBank.depositTo(an, da);
                    break;
            case 3: System.out.println("Enter a account number");
                    int acn = s.nextInt();
                    System.out.println("Enter a withdraw amount");
                    double wa = s.nextDouble();
                    myBank.withdrawFrom(acn, wa);
                    break;
            case 4: System.out.println("Enter a account number");
                    int anum = s.nextInt();
                    myBank.printAccountInfo(anum);
                    break;
           case 5:  System.out.println("Enter a account number");
                    anum = s.nextInt();
                    myBank.printTransactionInfo(anum);
                    break;
           case 6:  System.out.println("Thanks for using");
                    break;
          default:  System.out.println("Invalid input");
                    break;
        }
    }while (user_choice != '6');
}

static class Bank
{
private BankAccount[] accounts;     
private int numOfAccounts;    
public Bank()
{
    accounts = new BankAccount[100];
    numOfAccounts = 0;
}

public int openNewAccount(String customerName, double openingBalance)
{

    BankAccount b = new BankAccount(customerName, openingBalance);
    accounts[numOfAccounts] = b;
    numOfAccounts++;
    return b.getAccountNum();
}


public void withdrawFrom(int accountNum, double amount)
{
    for (int i =0; i<numOfAccounts; i++) 
    {
        if (accountNum == accounts[i].getAccountNum()  ) 
        {
            accounts[i].withdraw(amount);
            return;
        }
        else
          System.out.println("Enter the Account Number correctly");
    }
    
}
public void depositTo(int accountNum, double amount)
{
    for (int i =0; i<numOfAccounts; i++)
    {
        if (accountNum == accounts[i].getAccountNum()  )
        {
            accounts[i].deposit(amount);
            System.out.println("Amount deposited successfully");
            return;
        }
        else
           System.out.println("Enter the Account Number correctly");
    }
   
}

public void printAccountInfo(int accountNum)
{
    for (int i =0; i<numOfAccounts; i++) 
    {
      if (accountNum == accounts[i].getAccountNum()  ) 
      {
                    System.out.println(accounts[i].getAccountInfo());
                    return;
      }
      else
         System.out.println("Enter account no. correctly");
    }
}

public void printTransactionInfo(int accountNum)
{
    for (int i =0; i<numOfAccounts; i++)
    {
                if (accountNum == accounts[i].getAccountNum()  )
                {
                    System.out.println(accounts[i].getAccountInfo());
                    System.out.println("Last transaction: " + accounts[i].getTransactionInfo(accounts[i].getNumberOfTransactions()-1));
                    return;
                }
                else
                   System.out.println("Enter account no. correctly");
    }
   
}


public void printAccountInfo(int accountNum, int n)
{
    for (int i =0; i<numOfAccounts; i++) 
    {
                        if (accountNum == accounts[i].getAccountNum()  ) 
                        {
                            System.out.println(accounts[i].getAccountInfo());
                            System.out.println(accounts[i].getTransactionInfo(n));
                            return;
                        }
                        else
                          System.out.println("Enter account no. correctly");

    }
}

}
    static class BankAccount
    {

       private int accountNum;
       private String customerName;
       private double balance;
       private double[] transactions;
       private String[] transactionsSummary;
       private int numOfTransactions;
       private  static int noOfAccounts=0;

       public String getAccountInfo()
       {
           return "Account number: " + accountNum + "\nCustomer Name: " + customerName + "\nBalance:" + balance +"\n";
       }

       public String getTransactionInfo(int n)
       {
            String transaction = transactionsSummary[n];
            if (transaction == null)
            {
                return "No transaction exists with that number.";
            }
            else 
            {
                return transaction;
            }
        }

       public BankAccount(String abc, double xyz)
       {
         customerName = abc;
         balance = xyz;
         noOfAccounts ++;
         accountNum = noOfAccounts;
         transactions = new double[100];
         transactionsSummary = new String[100];
         transactions[0] = balance;
         transactionsSummary[0] = "A balance of : $" + Double.toString(balance) + " was deposited.";
         numOfTransactions = 1;
       }

    public int getAccountNum()
    {
        return accountNum;
    }

    public int getNumberOfTransactions()
    {
        return numOfTransactions;
    }

    public void deposit(double amount)
    {

        if (amount<=0) 
        {
            System.out.println("Amount to be deposited should be positive");
        }
        else 
        {
            balance = balance + amount;
            transactions[numOfTransactions] = amount;
            transactionsSummary[numOfTransactions] = "$" + Double.toString(amount) + " was deposited.";
            numOfTransactions++;
        }
    }
    public void withdraw(double amount)
    {
        if (amount<=0)
        {
             System.out.println("Amount to be withdrawn should be positive");
        }
        else
        {
            if (balance < amount)
            {
                System.out.println("Insufficient balance");
            } 
            else 
            {
                balance = balance - amount;
                System.out.println("Amount withdrawn successfully");
                transactions[numOfTransactions] = amount;
                transactionsSummary[numOfTransactions] = "$" + Double.toString(amount) + " was withdrawn.";
                numOfTransactions++;
            }
        }
    }

    }
    
    
}
