import java.util.Calendar;
import java.util.GregorianCalendar;

//Parents class to the Equity and Savings objects. Holds information that is important to both objects
//Holds abstract methods that can be used by each object using override annotations
public abstract class Account implements Comparable<Account>
{
    private int accountNumber;
    private String fullName;
    private String mailingAddress;
    private Calendar creationDate;

    //Default constructor for Account
    public Account(int a, String f, String add, int m, int d, int y)
    {
        setaccountNumber(a);
        setfullName(f);
        setmailingAddress(add);
        setcreationDate(y, m, d);
    }
  
    //GETTERS AND SETTERS FOR PRIVATE DATA MEMBERS
    public void setaccountNumber(int a)
    {
        accountNumber = a;
    }
    public int getaccountNumber()
    {
        return accountNumber;
    }
    public void setfullName(String name)
    {
        fullName = name;
    }
    public String getfullName()
    {
        return fullName;
    }
    public void setmailingAddress(String address)
    {
        mailingAddress = address;
    }
    public String getmailingAddress()
    {
        return mailingAddress;
    }
    public void setcreationDate(int y, int m, int d)
    {
    	creationDate = new GregorianCalendar(y, m, d);
    }
    public Calendar getcreationDate()
    {
        return creationDate;
    }
    //END GETTERS AND SETTERS
    
    public int compareTo(Account x)
    {
    	return Double.compare(getValue(), x.getValue());
    }

	/*
    
    These are the abstract methods used in the program. They are abstract in
    they both can be used by different objects producing different results.
    
    */
    public abstract double getValue();
    public abstract void getReport(int y, int m ,int d);
    
}