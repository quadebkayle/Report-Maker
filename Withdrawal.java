//Withdrawal is an object extending a transaction.
public class Withdrawal extends Transaction
{
	//Constructor
    public Withdrawal(double a, String n, int y, int m, int d)
    {
        super(a, n, y, m, d);
    }
    
    //Abstract method to get the type
    @Override
    public String getType()
    {
    	return "Withdrawal";
    }

}