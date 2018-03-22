import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;


//Stock purchase is an object used to keep track of information a stockPurchase has.
public class stockPurchase implements Comparable<stockPurchase> // implements stockpurchase to be comparable
{
	//Data Members
    private String name;
    private String tickerSymbol;
    private BigDecimal pricePerShare;
    private int numberOfShares;
    private Calendar time;

    //Constructor
    public stockPurchase(String n, String t, int number, int y, int m, int d) throws IOException 
    {
        setName(n);
        settickerSymbol(t);
        setnumberOfShares(number);
        setpricePerShare();
        setDate(y, m, d);
    }

    //Getters and Setters
    public void setName(String n)
    {
        name = n;
    }
    public String getName()
    {
        return name;
    }
    public void settickerSymbol(String t)
    {
        tickerSymbol = t;
    }
    public String gettickerSymbol()
    {
        return tickerSymbol;
    }
    public void setpricePerShare() throws IOException
    {
    	Stock temp = YahooFinance.get(tickerSymbol);
    	pricePerShare = temp.getQuote().getPrice();
    }
    public BigDecimal getpricePerShare()
    {
        return pricePerShare;
    }
    public void setnumberOfShares(int n)
    {
        numberOfShares = n;
    }
    public int getnumberOfShares()
    {
        return numberOfShares;
    }
    public void setDate(int y, int m, int d)
    {
    	time = new GregorianCalendar(y, m, d);
    }
    public Calendar getDate()
    {
        return time;
    }
    
    //Overrides compareTo function for stockpurchase objects to be compared based on their dates.
    public int compareTo(stockPurchase x)
    {
    	return getDate().compareTo(x.getDate());
    }

}