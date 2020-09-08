package coinsConverter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import coinsConverter.coinsFactory.Coin;
import coinsConverter.coinsFactory.CoinFactory;
import coinsConverter.coinsFactory.Coins;

public class Main {
	
	static Scanner scanner = new Scanner(System.in);
	static ArrayList<Double> resultsList = new ArrayList<Double>(); 
	
	static Coins getCoinFromUser()
	{
		int coinInput = 0;
		
		System.out.println("Please choose an option (1/2): ");
    	System.out.println("1. Dollars to Shekels");
    	System.out.println("2. Shekels to Dollars");	
    	
    	do
    	{
	    	coinInput = scanner.nextInt();
	    	if (coinInput!=1 && coinInput!=2)
	    	{
	    		System.out.println("Invalid Choice, please try again");
	    	}
    	}
    	while (coinInput!=1 && coinInput!=2);
    	
    	Coins coinType=Coins.ILS;
        switch(coinInput)
        {
            case 1: coinType=Coins.USD; break;
            case 2: coinType=Coins.ILS;break;

        }
        
        return coinType;
	}
	
	
	static double getAmountFromUser()
	{
		// get amount of money from user
        double amountOfMoney = 0;
        System.out.print("Please enter an amount to convert: ");
        do
    	{
        	amountOfMoney = scanner.nextDouble();
        	if (amountOfMoney<0)
	    	{
	    		System.out.println("Invalid Choice, please try again");
	    	}
    	}
    	while (amountOfMoney<0);
        
        return amountOfMoney;
	}
	
	static boolean askUserIfStartAgain()
	{
		// get amount of money from user
        char startAgain = 'n';
        System.out.print("Start over? (y/n): ");
        do
    	{
        	//startAgain = scanner.nextLine();
        	startAgain = scanner.next(".").charAt(0);
        	if (startAgain!='y' && startAgain != 'n')
	    	{
	    		System.out.println("Invalid Choice, please try again (y/n)");
	    	}
    	}
    	while (startAgain!='y' && startAgain != 'n' );
        
        return startAgain=='y';		
	}
	
	
	static void writeToFile(String text)
	{
		try {
		      FileWriter myWriter = new FileWriter("results.txt");
		      myWriter.write(text);
		      myWriter.close();
		      System.out.println("Successfully wrote to the file results.txt.");
	    } 
		catch (IOException e) 
		{
	      System.out.println("An error occurred while writing to file.");
	      e.printStackTrace();
	    }
	}
	
    public static void main(String[] args) {
        
    	
    	boolean startAgain = false;
    	
    	// Print screen title
		System.out.println("Welcome to currency converter\n");
		
    	do
    	{
	    	// Get coin type from user
	    	Coins coinType = getCoinFromUser();
	        
	    	// Get amount of money from user
	    	double amountOfMoney = getAmountFromUser();
	    	
	        // Get from CoinFactory the object which is the expert
	        // to handle the specific coin type
	        Coin expertForSpecificCoin = CoinFactory.getCoinInstance(coinType);
	        double result = expertForSpecificCoin.calculate(amountOfMoney);
	        System.out.println("Result: " + result);
	        
	        resultsList.add(result);
	        
	        System.out.println();
	        startAgain = askUserIfStartAgain();
	        System.out.println();
    	}
    	while (startAgain);
    	
    	System.out.println();
    	System.out.println("Thanks for using our currency converter.");
    	System.out.println();
    	System.out.println("Results History:");
    	String resultsAsStr = "";
    	for (double result : resultsList)
    	{
    		resultsAsStr += result + "\r\n";
    		System.out.println(result);
    	}
    	
    	System.out.println("Writing results to results.txt.");
    	writeToFile(resultsAsStr);
    }
}
