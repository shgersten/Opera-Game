import java.util.Arrays;
import java.util.Scanner;
import java.util.*;

public class Avatar {

	private String name;

        protected int wealth, prestige, compositions, dresses;
        protected String[] actions;
        protected String[] posessions;


        //constructor
        public Avatar(){
		name = "Artemisia Fontana";
                wealth = 5;
                prestige = 50;
                compositions = 3;
                dresses = 2;
                actions = new String[]{};
                posessions = new String[]{"Violin\t", "Compositions\t\t", "Dresses\t", "Travel Meals"};
        }



	public String toString() {return name;}
        public int getDresses() {return dresses;}
        public int getCompositions(){return compositions;}
        public int getWealth() {return wealth;}
        public int getPrestige() {return prestige;}

        protected void updateWealth(int amt){wealth += amt;}
        protected void updatePrestige(int amt){prestige += amt;}
        protected void updateCompositions(int amt){compositions += amt;}
        protected void updateDresses(int amt){dresses += amt;}

        public void doAction(int act) { }

        public String getStats(){ return "Wealth = " + wealth + " | Prestige = " + prestige + " | Compositions = " + compositions+ " | Dresses = " + dresses;}
        
        public String[] getPosessions(){ return posessions; }

        

        public String printingArray(String[] arr)
        {
                String ar = "";
                int count = 1;

		for(int i = 0; i < arr.length; i++)
		{
			ar += count + ": " + arr[i];
			count++;
		}
		
                return ar;
        }
        
        public int makeChoice(String question, int max)
        {
                System.out.println(question + "\n");
                System.out.print("CHOICE: ");
                Scanner scans = new Scanner(System.in);
                String choice = scans.nextLine();
                int quitCheck = checkInput(choice, max, question);
                if(quitCheck == 0){return 0;}
                return quitCheck;
        }

        public static boolean isNumeric(String str) {
                return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
              }

        public int checkInput(String choice, int max, String question)
	{
                int answer = 0;
                // if NOT  a number
                if( !isNumeric(choice) )
                {
                        choice = choice.toUpperCase();
                        //check if quit command
                        if( choice.equals("QUIT") )
                        {
                                System.out.println("\n\nTHANKS FOR PLAYING!!!\n\n");
                                 System.exit(0);
                        }
                        //esle not number or quit command
                        System.out.println("\nSorry, wrong input. Try again.\n");
			makeChoice(question, max);
                }

                //init if anumber
                answer = Integer.parseInt(choice);

                int len = choice.length();
		if(len > 1 || answer > max || answer  < 0)
		{
			System.out.println("\nSorry, wrong input. Try again.\n");
			makeChoice(question, max);

		}
                return answer;
	}
        

}
