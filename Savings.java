
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

//A type of account used for tracking savings accounts
//extends Account, therefore has access to all methods used in Account plus Savings
public class Savings extends Account
{
	
	//Data Members
    private double balance;
    private double annualInterestRate;
    private ArrayList<Transaction> transactionlist = new ArrayList<Transaction>();

    //Constructor
    public Savings(int a, String f, String add, int m, int d, int y, double b, double air)
    {
        super(a, f, add, m, d, y);
        setbalance(b);
        setannualInterestRate(air);
        
    }
    
    //GETTERS AND SETTERS
    public void setbalance(double b)
    {
        balance = b;
    }
    public double getbalance()
    {
        return balance;
    }
    public void setannualInterestRate(double air)
    {
        annualInterestRate = air;
    }
    public double getannualInterestRate()
    {
        return annualInterestRate;
    }
    public void addelementArrayList(Transaction trans)
    {
    	transactionlist.add(trans);
    }
    public ArrayList<Transaction> getArrayList()
    {
    	return transactionlist;
    }
    //GETTERS AND SETTERS
    
    //Add transaction adds a transaction to the transaction list based on the type
    
    public void addWithdrawal(double a, String n, int y, int m, int d)
    {
    	Transaction temp = new Withdrawal(a, n, y, m, d); //initializes a temp withdrawal object
        getcreationDate();
    	temp.getDate(); 
    	if(getcreationDate().get(Calendar.YEAR) < temp.getDate().get(Calendar.YEAR)) // checks if the object was created after the creationdate
        {
            addelementArrayList(temp);
        }
    }
    
    public void addDeposit(double a, String n, int y, int m, int d)
    {
    	Transaction temp = new Deposit(a, n, y, m, d); // initializes a temp deposit object
        getcreationDate();
		temp.getDate();
		if(getcreationDate().get(Calendar.YEAR) < temp.getDate().get(Calendar.YEAR))//checks if the object was created after the creationdate
        {
        	addelementArrayList(temp);
        }
		
    }
    //Sorts the transactions in transactionlist based on the dates they were made
    private void sortTransactions()
    {
    	Collections.sort(transactionlist);
    }
    
    //gets the amount of transactions that happened before a date
    public int getAmountOfTransactions(Calendar date)
    {
    	int counter = 0;
    	for(int i = 0; i < transactionlist.size(); i++)
    	{
    		int check = 0;
    		check = transactionlist.get(i).getDate().compareTo(date);
    		if(check < 0)
    		{
    			counter++;
    		}
    	}
    	return counter;
    }
    
    
    // gets the report for the Savings Account
    @Override
    public void getReport(int y, int m, int d)
    {
    	sortTransactions();
    	Report report = new Report(y, m, d);
    	report.setNameOfFile(getfullName() + "'s Report");
    	report.setHeader(getfullName(), "Savings");
    	report.setAccountNumber(getaccountNumber());
    	report.setcreationDate(getcreationDate());
    	report.setmailingAddress(getmailingAddress());
    	report.setstartingBalance(getbalance());
    	report.setAnnualInterestRate(annualInterestRate);
    	report.settableLayout();
    	report.createTableForSavings(getbalance(), getcreationDate().get(Calendar.YEAR), transactionlist, annualInterestRate, getAmountOfTransactions(report.getReportDate()));
    	report.generateFile();
    }

	@Override
	public double getValue() 
	{
		return balance;
	}
    }

