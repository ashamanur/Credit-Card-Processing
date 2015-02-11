import java.util.*; 

/*
 * Java program to add new credit card accounts, process charges 
 * and credits against them, and display summary information.
 * 
 * When all input has been read and processed, a summary with names of the card holders in 
 * alphabetical order is generated and written to stdout
 * 
 * "error" is displayed instead of the balance if the credit card number does not pass Luhn 10
 * 
 * Input Assumptions:
 *	- all input will be space delimited
 * 	- credit card numbers may vary in length, up to 19 characters
 * 	- credit card numbers will always be numeric
 * 	- amounts will always be prefixed with "$" and will be in whole dollars (no decimals)
 * 
 * Sample Input:
 *  Add Joey 4111111111111111 $2000
 *  Add Phoebe 4716453519524332 $5000
 *  Add Chandler 1234567890123456 $4000
 *  Charge Joey $1500
 *  Charge Joey $800
 *  Charge Phoebe $500
 *  Credit Phoebe $900
 *  Credit Chandler $200 
 *  
 * Sample Output:
 *  	Chandler: Error
 *  	Joey: $500
 *  	Phoebe: $-400
 * 
 */

public class CreditCard
{
	String name, card_num;
	Double balance;
	Double limit;
	Double charge;
	Double credit;
	
	public CreditCard( )
	{}
	
	public CreditCard( String name, String card_num, Double limit)
	{
		this.name=name;
		this.limit=limit;
		this.card_num=card_num;
		this.balance=0.0;
	}
	
	public Boolean Luhn_test(String card_no){
				int sum = 0;
                boolean next = false;
                for (int i = card_no.length() - 1; i >= 0; i--)
                {
                    int n = Integer.parseInt(card_no.substring(i, i + 1));
                    if (next)
                    {
                        n *= 2;
                        if (n >= 10)
						{
                            n = (n % 10) + 1;
                        }
                    }
                    next = !next;
					sum += n;
                }
                return (sum % 10 == 0);          
	}
	
	public Double Charge(String name, Double charge)
	{
		if(Luhn_test(card_num))
		{
			if((charge+balance)<=limit)
			{
				balance=charge+balance;
				return balance;
			}
			else
			{
				return 0.0;
			}
		}
		else
			return 0.0;
	}
	
	public Double Credit(String name, Double credit)
	{
		if(Luhn_test(card_num))
		{
			balance=balance-credit;
			return balance;
		}
		else 
			return 0.0;
	}
}