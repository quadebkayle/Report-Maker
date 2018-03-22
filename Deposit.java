
public class Deposit extends Transaction
{
	//Constructor
    public Deposit(double a, String n, int y, int m, int d)
    {
        super(a, n, y, m, d);
    }
    
    //abstract method to get the type
    @Override
    public String getType()
    {
    	return "Deposit";
    }
}