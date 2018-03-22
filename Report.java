import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Report 
{
	private StringBuilder page = new StringBuilder();
	private String nameOfFile;
	private Calendar reportDate;
	
	public Report(int y, int m, int d)
	{
		setReportDate(y, m, d);
	}
	public Calendar getReportDate() {
		return reportDate;
	}
	
	public String getNameOfFile() {
		return nameOfFile;
	}
	
	public void setNameOfFile(String nameOfFile) {
		this.nameOfFile = nameOfFile;
	}
	
	public void setReportDate(int y, int m, int d) 
	{
		this.reportDate = new GregorianCalendar(y, m, d);;
	}
	
	public void setHeader(String name, String accountType)
	{
		page.append("<div><h1>");
		page.append(name);
		page.append("'s ");
		page.append(accountType);
		page.append(" Report for ");
		page.append((reportDate.get(Calendar.MONTH) + 1) +"/"+ reportDate.get(Calendar.DATE)+"/" + reportDate.get(Calendar.YEAR));
		page.append("</div>");
	}
	
	public void setAccountNumber(int accountNumber)
	{
		page.append("<h2>");
		page.append("Account Number: ");
		page.append(accountNumber);
		page.append("</h2>");
		
	}
	
	public void setmailingAddress(String address)
	{
		page.append("<h2>");
		page.append("Account Address: ");
		page.append(address);
		page.append("</h2>");
	}
	
	public void setcreationDate(Calendar date)
	{
		page.append("<h2>");
		page.append("Account Created: ");
		page.append((date.get(Calendar.MONTH) + 1));
		page.append("/");
		page.append(date.get(Calendar.DATE));
		page.append("/");
		page.append(date.get(Calendar.YEAR));
		page.append("</h2>");
	}
	public void setstartingBalance(double bal)
	{
		page.append("<h2>");
		page.append("Starting Balance: $");
		page.append(bal);
		page.append("</h2>");
	}
	public void setAnnualInterestRate(double air)
	{
		page.append("<h2>");
		page.append("Interest Rate: %");
		page.append(air * 100.0);
		page.append("</h2>");
	}
	
	public void setotalValue(BigDecimal totalValue) 
	{
		page.append("<h2>");
		page.append("Total Value: $");
		page.append(totalValue);
		page.append("</h2>");
	}
	public void settableLayout() 
	{
	page.append("<head><style>table {");
    page.append("font-family: arial, sans-serif;");
    page.append("border-collapse: collapse;");
    page.append("width: 100%;}");

    page.append("td, th {");
    page.append("border: 1px solid #dddddd;");
    page.append("text-align: left;");
    page.append("padding: 8px;");
    page.append("}");

    page.append("tr:nth-child(even) {");
    page.append("background-color: #dddddd;}");
    page.append("</style></head><body>");
	}
	public void createTableForEquity(ArrayList<stockPurchase> stockPurchases, int amountOfStocks) 
	{
		startTable();
		startRow();
		addtableHeader("Stock Name");
		addtableHeader("Ticker Symbol");
		addtableHeader("Date");
		addtableHeader("Price Per Share");
		addtableHeader("Amount of Shares");
		addtableHeader("Total Value of Purchase");
		endRow();
		for(int i = 0; i < amountOfStocks; i++)
		{
			addStockPurchase(stockPurchases.get(i));
		}
		
	}
	
	private void addStockPurchase(stockPurchase stockPurchase)
	{
		startRow();
		setName(stockPurchase.getName());
		setName(stockPurchase.gettickerSymbol());
		setDate(stockPurchase.getDate());
		setAmount(stockPurchase.getpricePerShare());
		setNumber(stockPurchase.getnumberOfShares());
		setTotalValue(stockPurchase.getnumberOfShares(),stockPurchase.getpricePerShare());
		endRow();
	}
	private void setTotalValue(int numberOfShares, BigDecimal pricePerShare) 
	{
		BigDecimal stocks = new BigDecimal(numberOfShares);
		BigDecimal totalValue = stocks.multiply(pricePerShare);
		page.append("<td>");
		page.append(totalValue);
		page.append("</td>");
		
	}
	public void createTableForSavings(double balance, int year, ArrayList<Transaction> transactions, double air, int amount)
	{
		startTable();
		startRow();
		addtableHeader("Transaction Name");
		addtableHeader("Type");
		addtableHeader("Amount");
		addtableHeader("Date");
		addtableHeader("New Balance");
		endRow();
		int currentYear = year;
		Transaction lastUsed = null;
		double currentBalance = balance;
		for(int i = 0; i < amount; i++)
		{
			if((transactions.get(i).getDate().get(Calendar.YEAR) > currentYear))
			{ 
				do
				{
				currentBalance = addCompoundInterest(currentBalance, air, currentYear + 1);
				currentYear = currentYear + 1;
				}while(currentYear < transactions.get(i).getDate().get(Calendar.YEAR));
			}
			currentYear = transactions.get(i).getDate().get(Calendar.YEAR);
			currentBalance = addTransaction(transactions.get(i), currentBalance);
			lastUsed = transactions.get(i);
		}
		if(lastUsed.getDate().get(Calendar.YEAR) != reportDate.get(Calendar.YEAR))
		{
			for(int j = currentYear; j < reportDate.get(Calendar.YEAR); j++ )
			{
				currentBalance = addCompoundInterest(currentBalance, air, j + 1);
			}
		}	
		endTable();
		setEndingBalance(currentBalance);
	}
	private void setEndingBalance(double currentBalance) 
	{
		page.append("<h2>");
		page.append("Ending Balance: $");
		page.append(currentBalance);
		page.append("</h2>");
	}
	private double addCompoundInterest(double balance, double air, int y) 
	{
		double retval = 0;
		retval = balance * Math.pow(1 + (air / 1), 1 * 1);

        double interest = retval - balance;
        
        Transaction inter = new Deposit(interest, "Yearly Interest", y , 0, 1);
        
        retval = addTransaction(inter, balance);
        
        return retval;
        
        
	}
	private double addTransaction(Transaction transaction, double balance) 
	{
		startRow();
		setName(transaction.getName());
		setType(transaction.getType());
		setAmount(transaction.getAmount());
		setDate(transaction.getDate());
		double retval = setNewBalance(transaction.getAmount(), balance, transaction.getType());
		endRow();
		return retval;
	}
	private void setName(String name) 
	{
		page.append("<td>");
		page.append(name);
		page.append("</td>");
	}
	private void setType(String type) 
	{
		page.append("<td>");
		page.append(type);
		page.append("</td>");
	}
	private void setAmount(double amount) 
	{
		page.append("<td>");
		page.append("$" + amount);
		page.append("</td>");
	}
	private void setAmount(BigDecimal amount) 
	{
		page.append("<td>");
		page.append("$" + amount);
		page.append("</td>");
	}
	private void setNumber(int number)
	{
		page.append("<td>");
		page.append(number);
		page.append("</td>");
	}
	private void setDate(Calendar date) 
	{
		page.append("<td>");
		page.append((date.get(Calendar.MONTH) + 1));
		page.append("/");
		page.append(date.get(Calendar.DATE));
		page.append("/");
		page.append(date.get(Calendar.YEAR));
		page.append("</td>");
	}
	private double setNewBalance(double amount, double balance, String type)

	{
		double newbalance = 0;
		if(type.equals("Withdrawal"))
		{
			newbalance = balance - amount;
		}
		else if(type.equals("Deposit"))
		{
			newbalance = balance + amount;
		}
		else
		{
			System.out.println("Woops something went wrong");
		}
		page.append("<td>");
		page.append("$" + newbalance);
		page.append("</td>");
		return newbalance;
	
	}

	private void startTable()
	{
		page.append("<table>");
	}
	private void endTable()
	{
		page.append("</table>");
	}


	private void startRow()
	{
		page.append("<tr>");
	}
	private void addtableHeader(String header)
	{
		page.append("<th>");
		page.append(header);
		page.append("</th>");
	}
	private void endRow()
	{
		page.append("</tr>");
	}
	public void generateFile()
	{
    	File x =  new File("C:\\Users\\Quade Kayle\\" + nameOfFile + ".html");
    	try
    	{
    		BufferedWriter bw = new BufferedWriter(new FileWriter(x));   		
    		bw.write(page.toString());
    		bw.close();
    		System.out.println(nameOfFile + " has been created.");
    	} catch(IOException e) {
    		e.printStackTrace();
    	}
	}



}
