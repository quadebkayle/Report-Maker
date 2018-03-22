import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.io.IOException;
import java.math.BigDecimal;

//A type of account used for tracking equity accounts.
//This class extends Account, meaning it has access to all the methods in Account plus the methods stated here.
public class Equity extends Account
{
	//Data Members
    ArrayList<stockPurchase> stockPurchases = new ArrayList<stockPurchase>();
    private BigDecimal totalValue =  new BigDecimal(0);

    //Constructor
    public Equity(int a, String f, String add, int m, int d, int y)
    {
        super(a, f, add, m, d, y);
    }

    //GETTERS AND SETTERS
    public void setstockPurchases(ArrayList<stockPurchase> s)
    {
        stockPurchases = s;
    }
    
    public ArrayList<stockPurchase> getstockPurchases()
    {
        return stockPurchases;
    }
    public void settotalValue(BigDecimal t)
    {
        totalValue = t;
    }
    public BigDecimal gettotalValue()
    {
        return totalValue;
    }
    //GETTERS AND SETTERS

    //adds a stock to the ArrayList of stocks.
    public void addStock(String n, String t, int number, int y, int m, int d) throws IOException
    {
    	
    	stockPurchase temp = new stockPurchase(n, t, number, y, m, d); //initializes a new stock object
    	getcreationDate();
		temp.getDate();
		if(getcreationDate().get(Calendar.YEAR) < temp.getDate().get(Calendar.YEAR))//checks if the object is not created before the creation date of the account
    	{
    		BigDecimal tempB = new BigDecimal(temp.getnumberOfShares()); //cast int to a BigDecimal
    		stockPurchases.add(temp); // adds the stockPurchases to the ArrayList
    		totalValue = totalValue.add(((tempB.multiply(temp.getpricePerShare())))); // calculates the total Value
    	}
    	else
    	{
    		System.out.println("Cannot add " + n + " to stock list."); // If stock can't be added
    	}
    }
    
    private void sortStocks() //sorts the stocks using a comparator and the collections.sort method based on date.
    {
    	Collections.sort(stockPurchases);
    }
    
    //Gets the amount of stocks we will be adding to the report.
    private int getAmountOfStocks(Calendar date)
    {
       	int counter = 0;
    	for(int i = 0; i < stockPurchases.size(); i++)
    	{
    		int check = 0;
    		check = stockPurchases.get(i).getDate().compareTo(date);
    		if(check < 0)
    		{
    			counter++;
    		}
    	}
    	return counter;
    }
    
    //creates the report for the Equity Account
    // YO HERES WHAT YOUR GOING TO DO:
    // YOUR GOING TO CHANGE THIS TO RETURN A STRING
    // WHICH IS THE HTML STRING YOU WILL PASS INTO A FILE
    // THAT WAY, ALL THE REPORTS WILL BE PRINTED TOGETHER
    // PRINTREPORT WILL INSTEAD PRINT THE REPORTS BASED ON AN ARRAYLIST OF REPORTS PASSED IN.
    @Override
    public void getReport(int y, int m, int d)
    {
    	sortStocks();
    	Report report = new Report(y, m, d);
    	report.setNameOfFile(getfullName() + "'s Report");
    	report.setHeader(getfullName(), "Equity");
    	report.setAccountNumber(getaccountNumber());
    	report.setcreationDate(getcreationDate());
    	report.setmailingAddress(getmailingAddress());
    	report.setotalValue(gettotalValue());
    	report.settableLayout();
    	report.createTableForEquity(stockPurchases,getAmountOfStocks(report.getReportDate()));
    	report.generateFile();
    }
    
    
	@Override
	public double getValue() 
	{
		double retval = totalValue.doubleValue();
		return retval;
	}
}
