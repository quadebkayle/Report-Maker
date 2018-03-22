import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
public class Driver
{
    public static void main(String args[]) throws IOException
    {
        Savings quade = new Savings(123131, "Quade Kayle", "131 bool ave." , 3, 23, 2010, 2370.20, 0.034);
        Savings drew = new Savings(123131, "Drew Henegan", "poop toliet paper" , 8, 9, 2009, 4037.37, 0.041);


        quade.addWithdrawal(289.23, "Mark Mahoney Karate Class", 2014, 2, 13);
        quade.addWithdrawal(1021.21, "Carthage Tuition", 2013, 10, 2);
        quade.addWithdrawal(304.31, "Doctor Visit", 2013, 5, 29);
        quade.addDeposit(2901.13, "Illinois Lottery", 2011, 9, 2);
        quade.addDeposit(300.00, "Christmas Money", 2014, 11, 28);
        quade.addWithdrawal(2021.27, "Insurance Payment", 2014, 5, 2);
        quade.addDeposit(307.2, "Tax Return", 2015, 9, 21);
        quade.addWithdrawal(3213.2, "Car Payment", 2011, 9, 13);
        quade.addDeposit(1023.89, "Insurance Claim", 2013, 2, 21);
        
        drew.addWithdrawal(901.1, "Mark Mahoney's Cooking Class", 2015, 10, 2);
        drew.addWithdrawal(2003.2, "Insurance Premium", 2014, 1, 6);
        drew.addDeposit(731.32, "Extra Money from Check", 2013, 6, 19);
        drew.addDeposit(310.54, "Birthday Money", 2012, 9, 22);
        drew.addWithdrawal(1540.32, "Carthage tuition", 2012, 7, 15);
        drew.addDeposit(432.78, "Vegas Winnings", 2014, 2, 5);
        drew.addWithdrawal(2000.21, "JTERM Trip", 2013, 0, 3);
        drew.addDeposit(901.1, "Mark Mahoney Refund", 2015,11, 3);
        
        Equity dick = new Equity(31313, "Dick Richard" , "2001 Alford Park Drive", 11, 12, 2010);
        dick.addStock("Microsoft", "MSFT", 5 , 2011, 11, 23);
        dick.addStock("Apple", "AAPL", 10, 2013, 11, 12);
        dick.addStock("Transocean", "RIG", 30, 2011, 4, 4);
        dick.addStock("CVS Health Corp.", "CVS", 40, 2012, 9, 28);
        dick.addStock("AbbVie Inc.", "ABBV", 35, 2014, 7, 18);
        dick.addStock("Qorvo Inc.", "QRVO", 20, 2014, 2, 3);
        dick.addStock("Verizon Communications", "VZ", 30, 2012, 4, 15);
        
        
        ArrayList<Account> accounts = new ArrayList<Account>();
        
        accounts.add(quade);
        accounts.add(drew);
        accounts.add(dick);
        Collections.sort(accounts);
        for(int i = 0; i < accounts.size(); i++)
        {
        	accounts.get(i).getReport(2016, 3, 24);
        }



    }
}