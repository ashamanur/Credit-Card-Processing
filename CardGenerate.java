import java.util.*; 
import java.io.*;

/*
 * To process the transactions
 * "Add" - Create a new credit card for a given name, card number, and limit
 * 		   Card numbers are validated using Luhn 10
 *  	   New cards start with a $0 balance
 * "Charge" - Increase balance of the card associated with provided name by the amount specified
 * 			  Charges that would raise the balance over the limit are ignored as if they were declined
 * 			  Charges against Luhn 10 invalid cards are ignored
 * "Credit" - Decrease the balance of the card associated with the provided name by the amount specified
 * 		      Credits that would drop the balance below $0 will create a negative balance
 *            Credits against Luhn 10 invalid cards are ignored
 */

public class CardGenerate{
	
	static HashMap<String, CreditCard> mapper = new HashMap<String, CreditCard>();
	public static void main(String[] args) throws FileNotFoundException{
		String card;
		String name;
		String cnum;
		String rand;
		Double lim;
		Double charg;
		Double credit;
		Double balance;
		if(args.length>0)
		{
		try
		{
	    String content = new Scanner(new File(args[0])).useDelimiter("\\Z").next();
		Scanner scanner = new Scanner(content);
		while(scanner.hasNextLine()){
			System.out.println(scanner.nextLine());
			}
		String input[] = content.split("\n");
		String action[];
		CreditCard one = new CreditCard();
		
		for (int i=0; i<input.length; i++)
		{
		action=input[i].split(" ");
		String act = action[0];
		
		switch(act){
		
		case "Add": 
					rand=action[3].replaceAll("[^0-9]","");
					lim=Double.parseDouble(rand);
					mapper.put(action[1], new CreditCard(action[1],action[2],lim)); 
					break;
		case "Charge": 	
						rand=action[2].replaceAll("[^0-9]","");
						charg=Double.parseDouble(rand);
						one=mapper.get(action[1]);
						one.Charge(action[1],charg);
						break;
		case "Credit": 	
						rand=action[2].replaceAll("[^0-9]","");
						credit=Double.parseDouble(rand);
						one=mapper.get(action[1]);
						one.Credit(action[1],credit);
		}
	  
	  }
			Set<String> keys = mapper.keySet();
			String[] keyArray = keys.toArray(new String[keys.size()]);
			Arrays.sort(keyArray);
			System.out.println("````````````");
				for(int i=0;i<mapper.size();i++)
				{
					one=mapper.get(keyArray[i]);
					if(one.Luhn_test(one.card_num))
					System.out.println(one.name+": $"+one.balance);
					else
					System.out.println(one.name+": Error");
					
				}
			System.out.println("````````````");
		}
				
			catch(FileNotFoundException e)
			{
				System.out.println(e.getMessage());
			}
		}
		else
		System.out.println("Please enter a valid file name");
	}  
}