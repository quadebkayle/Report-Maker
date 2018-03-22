import java.util.Calendar;
import java.util.GregorianCalendar;

//Transaction is abstract as it has two extending classes, withdrawal and deposit. Used for Savings Accounts
public abstract class Transaction implements Comparable<Transaction> //This implenter allows the Transaction object to be comparable 
{
	
	//Data Members
    private double amount;
    private Calendar time;
    private String name;

    
    //Constructor
    public Transaction(double a, String n, int y, int m, int d)
    {
        setAmount(a);
        setName(n);
        setDate(y, m, d);
    }

    //Getters and Setters
    public void setAmount(double a)
    {
        amount = a;
    }
    public double getAmount()
    {
        return amount;
    }
    public void setName(String n)
    {
        name = n;
    }
    public String getName()
    {
        return name;
    }
    public void setDate(int y, int m, int d)
    {
    	time = new GregorianCalendar(y, m, d);
    }
    public Calendar getDate()
    {
        return time;
    }
    
    //Overrides the CompareTo method to compare transactions based on their dates.
    public int compareTo(Transaction x)
    {
    	return getDate().compareTo(x.getDate());
    }
    
    
    public abstract String getType();
}