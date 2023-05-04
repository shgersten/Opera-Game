import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.event.CellEditorListener;

import org.xml.sax.ext.EntityResolver2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.awt.event.*;

import javax.lang.model.util.ElementScanner6;
import javax.swing.*;
import java.io.FileInputStream; 
import java.util.concurrent.TimeUnit;

public class Game  
{
	private Avatar p;
	public static boolean quit = false;

	//constructor
	public Game()
	{
		p = new Avatar();
	}

	public boolean play() throws IOException, InterruptedException
	{
		
		while(!quit){
			int choice = 0;
			boolean upset = false;
			show("music_note.txt");
			displayAll();
			//CHANGE BACK TO 5 LATER ****************************
			TimeUnit.SECONDS.sleep(1);
			choice = caravanMarket();
			

			//HEAD TO THE CARAVAN
			if(choice == 1)
			{
				clearScreen(); 
				caravan();
				int nxt = next();
				if(nxt == 0){quit = true;}

				//TRADER NOT UPSET BECAUSE YOU
				//ARRIVED EARLY
		
				//TRADER NOT UPSET WITH YOU
				choice = notUpsetSinging();
				
				//TELL HIM ABOUT  JOURNEY
				if(choice == 1)
				{
					choice = tellJourney();
					
					if(choice == 1)
					{
						//GO WITH LASSELS
					 	goWithLassels();
						
					}
					else
					{
						stayWithCaravan();
					}
				}
				//DONT TELL HIM ABOUT JOURNEY
				else
				{
					choice = noTellJourney();
					p.updatePrestige(-1);
					
					
					if(choice == 1)
					{
						//GO WITH LASSELS
						goWithLassels();
						
					}
					else
					{
						//STAY WITH CARAVAN
						stayWithCaravan();
					}
				}


			}
			
				



			//HEAD TO THE MARKET
			else
			{
				clearScreen(); 
				upset = true;
				market();
				int nxt = next();
				if(nxt == 0)
				{quit = true;}
				
				//IF TRADER UPSET - ASK LASSELS
				if(upset)
				{
					choice = lasselsUpsetAskToPlay();
					
					//ASK HIM
					if(choice == 1)
					{
						//GO WITH HIM OR STAY WITH CARAVAN
						choice = askLasselsCaravan();
						
						if(choice == 1)
						{
							//GO WITH LASSELS
							goWithLassels();
							
						}
						else
						{
							stayWithCaravan();
						}

					}
					//NO ASK HIM
					else
					{
						//GO WITH HIM OR STAY WITH CARAVAN
						choice = noAskLasselsCaravan();
						
						if(choice == 1)
						{
							//GO WITH LASSELS
							goWithLassels();
							
						}
						else
						{
							stayWithCaravan();
						}

					}
				}
				
			}/*******END MARKET********* */

			/////////
			quit = true;
			/////////

		}
		return quit;
	}

	public int tellJourney()throws IOException
	{
		clearScreen();
		show("tellJourney.txt");
		displayAll();
		int choice = p.makeChoice("\nWould you like to: (press [1] or [2] and hit ENTER, type 'quit' to quit)\n[1] Join Richard Lassels on his journey \nOR\n[2] Stay with the caravan", 2);
		return choice;
	}

	public int noTellJourney()throws IOException
	{
		clearScreen();
		show("noTellJourney.txt");
		displayAll();
		int choice = p.makeChoice("\nWould you like to: (press [1] or [2] and hit ENTER, type 'quit' to quit)\n[1] Join Richard Lassels on his journey \nOR\n[2] Stay with the caravan", 2);
		return choice;
	}
	public int notUpsetSinging()throws IOException
	{
		clearScreen();
		show("notUpset.txt");
		p.updateCompositions(1);
		displayAll();
		int choice = p.makeChoice("\nWould you like to: (press [1] or [2] and hit ENTER, type 'quit' to quit)\n[1] Tell Richard Lassels about your journey \nOR\n[2] Don't tell him", 2);
		return choice;

	}

	public int whichPalace()throws IOException
	{
		clearScreen();
		show("joinLass.txt");
		p.updatePrestige(-1);
		displayAll();
		int choice = p.makeChoice("\nWould you like to: (press [1] or [2] and hit ENTER, type 'quit' to quit)\n[1] Should you look in the Palazzo Vecchio \nOR\n[2] Check the Palazzo Strozzi", 2);
		return choice;
	}
	public void goWithLassels()throws IOException
	{
		//ASK WHICH PALACE TO CHECK
		int palace = whichPalace();
		
		//vecchio
		if(palace == 1)
		{
			vecchio();
		}
		//strezzo
		else
		{
			strezzo();
		}
	}

	public void strezzo()throws IOException
	{
		int response = goStrozzi();
		//RUN
		if(response == 1)
		{
			response = runStrezzo();
			//RUN AWAY
			if(response == 1)
			{
				
				response = runRunV();
				//GO WITH TRADESWOMAN
				if(response == 1)
				{
					tradeswoman(0);
				}
				//SHE INSISTS
				else
				{
					tradeswoman(1);
				}
			}
			//PLAY
			else
			{
				response = playStrozzi();
				//GO WITH GUILIO
				if(response == 1)
				{
					response = goGuilio();
					barbaraGuilio();
				}
				//HE INSISTS YOU GO WITH HIM
				else
				{
					response = insistGuilio();
					barbaraGuilio();
				}
			}
		}
		//STAY AND EXPLAIN
		else
		{
			response = stayStrezzo();
			//ASK RICHARD CORROBORATE
			if(response == 1)
			{
				response = strozziCorr();
				int nxt = next();
				if(nxt == 0){quit = true;}
			//************************************************* NEXT SCREEN ********** */
				response = runRunV();
				//GO WITH TRADESWOMAN
				if(response == 1)
				{
					tradeswoman(0);
				}
				//SHE INSISTS
				else
				{
					tradeswoman(1);
				}
			}
			//STAND GROUND
			else
			{
				response = groundStrezzo();
				//PLAY
				if(response == 1)
				{
					response = playStrozzi();
					//GO WITH GUILIO
					if(response == 1)
					{
						response = goGuilio();
						//LET BARB HELP
						if(response == 1)
						{
							barbaraGuilio();
						}
						//SHE INSISTS
						else
						{
							barbaraGuilio();
						}
					}
					//HE INSISTS YOU GO WITH HIM
					else
					{
						response = insistGuilio();
						//LET BARB HELP
						if(response == 1)
						{
							barbaraGuilio();
						}
						//SHE INSISTS
						else
						{
							barbaraGuilio();
						}
					}
				}
				//RUN
				else
				{
					response = standRunStrezzo();
					//GO WITH HER
					if(response == 1)
					{
						tradeswoman(0);
					}
					//SHE INSISTS
					else
					{
						tradeswoman(1);
					}
					
				}
			}
			
		}
	}





	public void vecchio()throws IOException
	{
		int response = goVecchio();
		//RUN
		if(response == 1)
		{
			response = runVecchio();
			//RUN AWAY
			if(response == 1)
			{
				//RUN AFTER A RUN
				response = runRunV();
				//GO WITH TRADESWOMAN
				if(response == 1)
				{
					tradeswoman(0);
				}
				//SHE INSISTS
				else /******************** NEXT BUTTON************* */
				{
					tradeswoman(1);
				}
			}
			//PLAY
			else
			{
				response = playVecchio();
				//GO WITH BARBARA
				if(response == 1)
				{
					barbara(0);
				}
				//SHE INSISTS YOU GO WITH HER
				else
				{
					barbara(1);
				}
			}
		}
		//STAY AND EXPLAIN
		else
		{
			response = stayVecchio();
			//ASK RICHARD CORROBORATE
			if(response == 1)
			{
				response = vecchioCorr();
				//NEXT BUTTON
				int nxt = next();
				if(nxt == 0){quit = true;}
				//************************************************* NEXT SCREEN ********** */
				response = runRunV();
				//GO WITH TRADESWOMAN
				if(response == 1)
				{
					tradeswoman(0);
				}
				//SHE INSISTS
				else
				{
					tradeswoman(1);
				}
			}
			//STAND GROUND
			else
			{
				response = vecchioGround();
				//WAIT FOR VOICE
				if(response == 1)
				{
					response = voiceBarb();
					//NEXT BUTTON
					int nxt = next();
					if(nxt == 0){quit = true;}
					//************************************************* NEXT SCREEN ********** */							
					waitBarbara();
				}
				//DON'T WAIT FOR VOICE
				else
				{
					response = runRunV();
					//GO WITH TRADESWOMAN
					if(response == 1)
					{
						tradeswoman(0);
					}
					//SHE INSISTS
					else /******************** NEXT BUTTON************* */
					{
						tradeswoman(1);
					}
				}
			}
		}
	}


		
				




	public void tradeswoman(int insist)throws IOException
	{
		int response;
		int nxt;
		//TRADESWOMAN CLEANS YOU UP
		//GO WITH?
		switch(insist)
		{
		case 0:
			//no insist
			tradeNoInsist();
			nxt = next();
			if(nxt == 0){quit = true;}
			
			response = helpTrade();
			//DON'T STOP
			if(response == 1)
			{
				goStraightThere();
			}
			//STOP FOR FOOD
			else
			{
				stopForFood();
			}


		case 1:
			//insist
			tradeInsist();
			nxt = next();
			if(nxt == 0){quit = true;}
			
			response = helpTrade();
			//DON'T STOP
			if(response == 1)
			{
				goStraightThere();
			}
			//STOP FOR FOOD
			else
			{
				stopForFood();
			}

		case 2:
			//stayed with caravan
			stayedCaravn();
			nxt = next();
			if(nxt == 0){quit = true;}
			
			response = helpTrade();
			//DON'T STOP
			if(response == 1)
			{
				goStraightThere();
			}
			//STOP FOR FOOD
			else
			{
				stopForFood();
			}
		}
	}

	public void stopForFood()throws IOException
	{
		clearScreen();
		show("stopForFood.txt");
		p.updateWealth(-1);
		displayAll();
		int choice = p.makeChoice("\nWould you like to: (press [1] or [2] and hit ENTER, type 'quit' to quit)\n[1] Greet the Woman\nOR\n[2] Apologize for disturbing her", 2);

		//bassilica greet - WOMAN
		if(choice == 1)
		{
			choice = basilicaGreet();
			//GO WITH THE PAINTER
			if(choice == 1)
			{
				goWithPainter();
				int nxt = next();
				if(nxt == 0){quit = true;}
				travelWithPainter();
			}
			//PAINTER WITH CHAPERONE TRADESWOMAN
			else
			{
				painterChaperone();
				int nxt = next();
				if(nxt == 0){quit = true;}
				travelWithPainter();
			}
		}
		//bassilica apologize - WOMAN
		else
		{
			choice = basilicaApologize();
			//GO WiTH THE PAINTER
			if(choice == 1)
			{
				goWithPainter();
				int nxt = next();
				if(nxt == 0){quit = true;}
				travelWithPainter();
			}
			//PAINTER WITH CHAPERONE TRADESWOMAN
			else
			{
				painterChaperone();
				int nxt = next();
				if(nxt == 0){quit = true;}
				travelWithPainter();
			}
		}
	}

	public void goStraightThere()throws IOException
	{
		clearScreen();
		show("deliverEarly.txt");
		displayAll();
		int choice = p.makeChoice("\nWould you like to: (press [1] or [2] and hit ENTER, type 'quit' to quit)\n[1] Greet the Gentleman\nOR\n[2] Apologize for disturbing him", 2);

		//GREET
		if(choice == 1)
		{
			choice = greetGentleman();

			//BECOME STUDENT
			if(choice == 1)
			{
				becameStudent();
			}
			//MAKE AUDITION
			else
			{
				makeAudition();
			}
			
		}
		//APOLOGIZE
		else{
			choice = apologizeGentleman();
			//BECOME STUDENT
			if(choice == 1)
			{
				becameStudent();
			}
			//MAKE AUDITION
			else
			{
				makeAudition();
			}
		}
	}

	public void makeAudition()throws IOException
	{
		clearScreen();
		show("makeAudition.txt");
		p.updateCompositions(1);
		p.updatePrestige(10);
		p.updateWealth(5);
		displayAll();
		int choice = p.makeChoice("\nWould you like to: (press [1] or [2] and hit ENTER, type 'quit' to quit)\n[1] Go with Marco\nOR\n[2] Stay at the tavern", 2);

		//GO WITH MARCO
		if(choice == 1)
		{
			goWithMarco();
			int nxt = next();
			if(nxt == 0){quit = true;}
			barbIntro();
		}

		//CHAPERONE MARCO
		else
		{
			marcoChaperone();
			int nxt = next();
			if(nxt == 0){quit = true;}
			barbIntro();
		}
	}

	public void barbIntro()throws IOException
	{
		clearScreen();
		show("barbIntro.txt");
		p.updateDresses(1);
		displayAll();
		int nxt = next();
		if(nxt == 0){quit = true;}

		meal(true);
	}

	public void marcoChaperone()throws IOException
	{
		clearScreen();
		show("marcoChaperone.txt");
		p.updatePrestige(30);
		displayAll();
	}

	public void goWithMarco()throws IOException
	{
		clearScreen();
		show("goWithMarco.txt");
		p.updatePrestige(30);
		displayAll();
	}

	public void becameStudent()throws IOException
	{
		clearScreen();
		show("becameStudent.txt");
		displayAll();
		System.exit(0);
	}

	public int apologizeGentleman()throws IOException
	{
		clearScreen();
		show("apologizeGentleman.txt");
		displayAll();
		int choice = p.makeChoice("\nWould you like to: (press [1] or [2] and hit ENTER, type 'quit' to quit)\n[1] Become his protege\nOR\n[2] Decline and attend the audition", 2);
		return choice;
	}

	public int greetGentleman()throws IOException
	{
		clearScreen();
		show("greetGentleman.txt");
		displayAll();
		int choice = p.makeChoice("\nWould you like to: (press [1] or [2] and hit ENTER, type 'quit' to quit)\n[1] Become his protege\nOR\n[2] Decline and attend the audition", 2);
		return choice;
	}

	public void travelWithPainter()throws IOException
	{
		int choice;
		choice = inspiration();
		//INSPIRED BY CITY
		if(choice == 1)
		{
			System.out.println("You've been inspired by the city and created a new composition!");
			p.updateCompositions(1);
			displayAll();
			cityArtInspire();
			
		}
		//INSPIRED BY ART
		else
		{
			System.out.println("You've been inspired by the art and created a new composition!");
			p.updateCompositions(1);
			displayAll();
			cityArtInspire();
		}
	}

	public void cityArtInspire()throws IOException
	{
		clearScreen();
		show("cityArtInspire.txt");
		displayAll();
		
		int nxt = next();
		if(nxt == 0){quit = true;}

		afterPainting();
		nxt = next();
		if(nxt == 0){quit = true;}

		meal(true);
	}

	public void afterPainting()throws IOException
	{
		clearScreen();
		show("afterPainting.txt");
		p.updatePrestige(30);
		displayAll();
		
	}

	

	public int inspiration()throws IOException
	{
		clearScreen();
		show("inspiration.txt");
		displayAll();
		int choice = p.makeChoice("\nWould you like to: (press [1] or [2] and hit ENTER, type 'quit' to quit)\n[1] Be inspired by the city\nOR\n[2] Be inspired by her story", 2);
		return choice;
	}

	public void painterChaperone()throws IOException
	{
		clearScreen();
		show("painterChaperone.txt");
		p.updatePrestige(10);
		p.updateWealth(10);
		displayAll();
	}

	public void goWithPainter()throws IOException
	{
		clearScreen();
		show("goWithPainter.txt");
		p.updatePrestige(10);
		p.updateWealth(10);
		displayAll();		
	}

	public int basilicaApologize()throws IOException
	{
		clearScreen();
		show("basilicaApologize.txt");
		displayAll();
		int choice = p.makeChoice("\nWould you like to: (press [1] or [2] and hit ENTER, type 'quit' to quit)\n[1] Go with her\nOR\n[2] Politely decline", 2);
		return choice;
	}
	public int basilicaGreet()throws IOException
	{
		clearScreen();
		show("basilicaGreet.txt");
		displayAll();
		int choice = p.makeChoice("\nWould you like to: (press [1] or [2] and hit ENTER, type 'quit' to quit)\n[1] Go with her\nOR\n[2] Politely decline", 2);
		return choice;
	}

	public void stayedCaravn()throws IOException
	{
		clearScreen();
		show("stayedCaravn.txt");
		p.updateDresses(1);
		displayAll();
	}

	public int helpTrade()throws IOException
	{
		clearScreen();
		show("helpTrade.txt");
		p.updateDresses(1);
		displayAll();
		int choice = p.makeChoice("\nWould you like to: (press [1] or [2] and hit ENTER, type 'quit' to quit)\n[1] Complete the delivery\nOR\n[2] Stop for food before continuing", 2);
		return choice;
	}

	public void tradeNoInsist()throws IOException
	{
		clearScreen();
		show("tradeNoInsist.txt");
		p.updateDresses(1);
		displayAll();
	}

	public void tradeInsist()throws IOException
	{
		clearScreen();
		show("tradeInsist.txt");
		p.updateDresses(1);
		displayAll();
	}

	public void barbara(int insist)throws IOException
	{
		int response;
		boolean ignoreTrader = false;
		//BARB TELLS YOU TO COME WITH HER
		if(insist == 1)
		{
			clearScreen();
			show("barbara.txt");
			p.updatePrestige(10);
			displayAll();
			response = practiceOrLunch();
			if(response == 1)
			{
				//practice
				{
					response = practice();
					//GREET TRADERS WIFE
					if(response == 1)
					{
						response = greetTrader();
						//YOU EXPLAIN YOUR RELATIONSHIP
						if(response == 1)
						{
							explain();
							int nxt = next();
							if(nxt == 0){quit = true;}
							/*******************************  YOU AWKNOWLDGE TRADESWOMAN AND EMBRACE *
							 * 			SHE GETS INVITE TO AUDTIONERS BALL **********
							*/
							ignoreTrader = false;
							meal(ignoreTrader);
						}
						//YOU SAY YOU ACTUALLY DON'T KNOW HER
						{
							laughOFF();
							int nxt = next();
							if(nxt == 0){quit = true;}
							/*******************************  YOU IGNORE TRADESWOMAN **********
							*/
							ignoreTrader = true;
							meal(ignoreTrader);
						}
					}
					//IGNORE TRADERS WIFE
					else
					{
						ignoreTrader = true;
						laughOFF();
						int nxt = next();
						if(nxt == 0){quit = true;}
						/*******************************  YOU IGNORE TRADESWOMAN **********
							*/
						meal(ignoreTrader);
					}
				}

			}
			else
			{
				meal(true);
			}

			
			

		}
		//after playing vecchio barbara asks to go with her
		//you just chose to go with her
		clearScreen();
			show("vecPlayBarb.txt");
			p.updatePrestige(10);
			displayAll();
			response = practiceOrLunch();
			if(response == 1)
			{
				//practice
				{
					response = practice();
					//GREET TRADERS WIFE
					if(response == 1)
					{
						response = greetTrader();
						//YOU EXPLAIN YOUR RELATIONSHIP
						if(response == 1)
						{
							explain();
							int nxt = next();
							if(nxt == 0){quit = true;}
							/*******************************  YOU AWKNOWLDGE TRADESWOMAN AND EMBRACE *
							 * 			SHE GETS INVITE TO AUDTIONERS BALL **********
							*/
							ignoreTrader = false;
							meal(ignoreTrader);
						}
						//YOU SAY YOU ACTUALLY DON'T KNOW HER
						{
							laughOFF();
							int nxt = next();
							if(nxt == 0){quit = true;}
							/*******************************  YOU IGNORE TRADESWOMAN **********
							*/
							ignoreTrader = true;
							meal(ignoreTrader);
						}
					}
					//IGNORE TRADERS WIFE
					else
					{
						ignoreTrader = true;
						laughOFF();
						int nxt = next();
						if(nxt == 0){quit = true;}
						/*******************************  YOU IGNORE TRADESWOMAN **********
							*/
						meal(ignoreTrader);
					}
				}

			}
			else
			{
				meal(true);
			}
	}

	
	public void waitBarbara()throws IOException
	{
		barbaraGuilio();
	}

	public void barbaraGuilio()throws IOException
	{
		boolean ignoreTrader = false;
		//YOU JUST PLAYED FOR HER- SHE ASKS TO HELP
		int response = letBarbHelp();
		//INSISTING ACCOUNTED FOR
		if(!(response == 1)) 
		{
			barbHelpInsist();
		}

		response = practiceOrLunch();
		//PRACTICE
		if(response == 1)
		{
			response = practice();
			//GREET TRADERS WIFE
			if(response == 1)
			{
				response = greetTrader();
				//YOU EXPLAIN YOUR RELATIONSHIP
				if(response == 1)
				{
					explain();
					int nxt = next();
					if(nxt == 0){quit = true;}
					/*******************************  YOU AWKNOWLDGE TRADESWOMAN AND EMBRACE *
					 * 			SHE GETS INVITE TO AUDTIONERS BALL **********
					*/
					ignoreTrader = false;
					meal(ignoreTrader);
				}
				//YOU SAY YOU ACTUALLY DON'T KNOW HER
				{
					laughOFF();
					int nxt = next();
					if(nxt == 0){quit = true;}
					/*******************************  YOU IGNORE TRADESWOMAN **********
					*/
					ignoreTrader = true;
					meal(ignoreTrader);
				}
			}
			//IGNORE TRADERS WIFE
			else
			{
				ignoreTrader = true;
				laughOFF();
				int nxt = next();
				if(nxt == 0){quit = true;}
				/*******************************  YOU IGNORE TRADESWOMAN **********
					*/
				meal(ignoreTrader);
			}
		}
		//LUNCH
		else
		{
			response = lunch();
			//OPERA WITH GUILIO
			if(response == 1)
			{
				response = withFather();
				//INTERJECT
				if(response == 1)
				{
					interject();
					int nxt = next();
					if(nxt == 0){quit = true;}
					/****************************** POST RAPE **************** */
					if(!ignoreTrader)
				{rapeNoTrade();}
				else
				rapeTrade();
				}
				//GUARD TONGUE
				else 
				{
					guardTongue();
					int nxt = next();
					if(nxt == 0){quit = true;}
					/****************************** POST RAPE **************** */
					if(!ignoreTrader)
				{rapeNoTrade();}
				else
				rapeTrade();
				}
			}
			//OPERA WITH GIOVANI
			else
			{
				response = withGiovani();
				//INTERJECT
				if(response == 1)
				{
					interjectGV();
					int nxt = next();
					if(nxt == 0){quit = true;}
					/****************************** POST RAPE **************** */
					if(!ignoreTrader)
				{
					rapeNoTrade();
				}
				else
				rapeTrade();
				}
				//GUARD TONGUE
				else
				{
					guardTongueGV();
					int nxt = next();
					if(nxt == 0){quit = true;}
					/****************************** POST RAPE **************** */
					if(!ignoreTrader)
				{
					rapeNoTrade();
				}
				else
				rapeTrade();
				}
			}
		}

	}

	public void meal(boolean knowTradeswoman)throws IOException
	{
		int response;
		response = lunch();
		//OPERA WITH GUILIO
		if(response == 1)
		{
			response = withFather();
			//INTERJECT
			if(response == 1)
			{
				interject();
				int nxt = next();
				if(nxt == 0){quit = true;}
				/****************************** POST RAPE **************** */
				if(!knowTradeswoman)
				{rapeNoTrade();}
				else
				rapeTrade();
			}
			//GUARD TONGUE
			else 
			{
				guardTongue();
				int nxt = next();
				if(nxt == 0){quit = true;}
				/****************************** POST RAPE **************** */
				if(!knowTradeswoman)
				{rapeNoTrade();}
				else
				rapeTrade();
				
			}
		}
		//OPERA WITH GIOVANI
		else
		{
			response = withGiovani();
			//INTERJECT
			if(response == 1)
			{
				interjectGV();
				int nxt = next();
				if(nxt == 0){quit = true;}
				/****************************** POST RAPE **************** */
				if(!knowTradeswoman)
				{rapeNoTrade();}
				else
				rapeTrade();
			}
			//GUARD TONGUE
			else
			{
				guardTongueGV();
				int nxt = next();
				if(nxt == 0){quit = true;}
				/****************************** POST RAPE **************** */
				if(!knowTradeswoman)
				{rapeNoTrade();}
				else
				rapeTrade();
			}
		}
	}


	public void laughOFF()throws IOException
	{
		clearScreen();
		show("ignoreTrader.txt");
		p.updatePrestige(10);
		p.updateWealth(-3);
		displayAll();
	}

	public void explain()throws IOException
	{
		clearScreen();
		show("exaplin.txt");
		p.updatePrestige(-3);
		p.updateWealth(-1);
		displayAll();
		
	}
	public int greetTrader()throws IOException
	{
		clearScreen();
		show("greetTrader.txt");
		p.updatePrestige(-10);
		displayAll();
		int choice = p.makeChoice("\nWould you like to: (press [1] or [2] and hit ENTER, type 'quit' to quit)\n[1] Explain your relationship\nOR\n[2] Laugh it off as and say you don't really know her", 2);
		return choice;
	}

	public void rapeNoTrade()throws IOException
	{
		clearScreen();
		show("rapeNoTrade.txt");
		displayAll();
		System.exit(0);
	}

	public void rapeTrade()throws IOException
	{
		clearScreen();
		show("rapeTrade.txt");
		displayAll();
		System.exit(0);
	}


	public void guardTongueGV()throws IOException
	{
		clearScreen();
		show("guardTongueGV.txt");
		displayAll();
	}

	public void interjectGV()throws IOException
	{
		clearScreen();
		show("interjectGV.txt");
		p.updateDresses(-1);
		p.updateWealth(10);
		displayAll();
	}

	public int withGiovani()throws IOException
	{
		clearScreen();
		show("withGiovani.txt");
		p.updatePrestige(30);
		displayAll();
		int choice = p.makeChoice("\nWould you like to: (press [1] or [2] and hit ENTER, type 'quit' to quit)\n[1] Interject and offer to play for him\nOR\n[2] Guard your tongue", 2);
		return choice;
	}

	public void guardTongue()throws IOException
	{
		clearScreen();
		show("guardTongue.txt");
		displayAll();
	}

	public void interject()throws IOException
	{
		clearScreen();
		show("interject.txt");
		p.updatePrestige(30);
		displayAll();
	}

	public int withFather()throws IOException
	{
		clearScreen();
		show("withFather.txt");
		p.updatePrestige(30);
		displayAll();
		int choice = p.makeChoice("\nWould you like to: (press [1] or [2] and hit ENTER, type 'quit' to quit)\n[1] Interject and offer to play for him\nOR\n[2] Guard your tongue", 2);
		return choice;
	}

	public int practice()throws IOException
	{
		clearScreen();
		show("practice.txt");
		p.updateCompositions(1);
		displayAll();
		int choice = p.makeChoice("\nWould you like to: (press [1] or [2] and hit ENTER, type 'quit' to quit)\n[1] Greet the traders wife\nOR\n[2] Pretend you don't know her", 2);
		return choice;
	}

	public int lunch()throws IOException
	{
		clearScreen();
		show("lunch.txt");
		displayAll();
		int choice = p.makeChoice("\nWould you like to: (press [1] or [2] and hit ENTER, type 'quit' to quit)\n[1] See the opera with Barbara and Guilio\nOR\n[2] See the opera with Barbara and Giovani", 2);
		return choice;
	}

	public int practiceOrLunch()throws IOException
	{
		clearScreen();
		show("practiceOrLunch.txt");
		displayAll();
		int choice = p.makeChoice("\nWould you like to: (press [1] or [2] and hit ENTER, type 'quit' to quit)\n[1] Practice while you wait\nOR\n[2] Go out to lunch", 2);
		return choice;
	}

	public void barbHelpInsist()throws IOException
	{
		clearScreen();
		show("barbHelpInsist.txt");
		displayAll();
	}

	public int letBarbHelp()throws IOException
	{
		clearScreen();
		show("barbHelp.txt");
		displayAll();
		int choice = p.makeChoice("\nWould you like to: (press [1] or [2] and hit ENTER, type 'quit' to quit)\n[1] Let her help you\nOR\n[2] Politely decline", 2);
		return choice;
	}


	public int voiceBarb()throws IOException
	{
		clearScreen();
		show("voiceBarb.txt");
		p.updatePrestige(10);
		displayAll();
		return 1;
	}


	public int runRunV()throws IOException
	{
		clearScreen();
		show("runRunV.txt");
		p.updatePrestige(-10);
		displayAll();
		int choice = p.makeChoice("\nWould you like to: (press [1] or [2] and hit ENTER, type 'quit' to quit)\n[1] Let her help you\nOR\n[2] Politely decline", 2);
		return choice;
	}

	public int goGuilio()throws IOException
	{
		clearScreen();
		show("goGuilio.txt");
		p.updatePrestige(10);
		displayAll();
		int choice = p.makeChoice("\nWould you like to: (press [1] or [2] and hit ENTER, type 'quit' to quit)\n[1] Let her help you\nOR\n[2] Politely decline", 2);
		return choice;

	}

	public int insistGuilio()throws IOException
	{
		clearScreen();
		show("insistGuilio.txt");
		p.updatePrestige(10);
		displayAll();
		int choice = p.makeChoice("\nWould you like to: (press [1] or [2] and hit ENTER, type 'quit' to quit)\n[1] Let her help you\nOR\n[2] Politely decline", 2);
		return choice;
	}

	public int vecchioGround()throws IOException
	{
		clearScreen();
		show("vecchioGround.txt");
		p.updatePrestige(-10);
		displayAll();
		int choice = p.makeChoice("\nWould you like to: (press [1] or [2] and hit ENTER, type 'quit' to quit)\n[1] Wait for the person\nOR\n[2] Keep running away", 2);
		return choice;
	}

	public int standRunStrezzo()throws IOException
	{
		clearScreen();
		show("standRunStrezzo.txt");
		p.updatePrestige(-10);
		displayAll();
		int choice = p.makeChoice("\nWould you like to: (press [1] or [2] and hit ENTER, type 'quit' to quit)\n[1] Go with her\nOR\n[2] Stay crying", 2);
		return choice;
	}

	public int groundStrezzo()throws IOException
	{
		clearScreen();
		show("groundStrezzo.txt");
		p.updatePrestige(-10);
		displayAll();
		int choice = p.makeChoice("\nWould you like to: (press [1] or [2] and hit ENTER, type 'quit' to quit)\n[1] Wait for the person\nOR\n[2] Keep Running", 2);
		return choice;
	}

	public int strozziCorr()throws IOException
	{
		clearScreen();
		show("strozziCorr.txt");
		p.updatePrestige(-10);
		displayAll();
		int choice = p.makeChoice("\nWould you like to: (press [1] or [2] and hit ENTER, type 'quit' to quit)\n[1] Go with her\nOR\n[2] Stay crying", 2);
		return choice;
	}

	public int vecchioCorr()throws IOException
	{
		clearScreen();
		show("vecchioCorr.txt");
		p.updatePrestige(-10);
		displayAll();
		int choice = p.makeChoice("\nWould you like to: (press [1] or [2] and hit ENTER, type 'quit' to quit)\n[1] Go with her\nOR\n[2] Stay crying", 2);
		return choice;
	}

	public int stayStrezzo()throws IOException
	{
		clearScreen();
		show("stayStrozzi.txt");
		displayAll();
		int choice = p.makeChoice("\nWould you like to: (press [1] or [2] and hit ENTER, type 'quit' to quit)\n[1] Ask Richard Lassels to corroborate your story\nOR\n[2] Stand your ground", 2);
		return choice;
	}

	public int stayVecchio()throws IOException
	{
		clearScreen();
		show("stayVecchio.txt");
		displayAll();
		int choice = p.makeChoice("\nWould you like to: (press [1] or [2] and hit ENTER, type 'quit' to quit)\n[1] Ask Richard Lassels to corroborate your story\nOR\n[2] Stand your ground", 2);
		return choice;

	}
	public int playStrozzi()throws IOException
	{
		clearScreen();
		show("playStrozzi.txt");
		p.updatePrestige(30);
		p.updateWealth(10);
		displayAll();
		int choice = p.makeChoice("\nWould you like to: (press [1] or [2] and hit ENTER, type 'quit' to quit)\n[1] Go with Guilio\nOR\n[2] Stay with the others", 2);
		return choice;
	}

	public int playVecchio()throws IOException
	{
		clearScreen();
		show("playVecchio.txt");
		p.updatePrestige(30);
		p.updateWealth(10);
		displayAll();
		int choice = p.makeChoice("\nWould you like to: (press [1] or [2] and hit ENTER, type 'quit' to quit)\n[1] Go with Barbara Strozzi\nOR\n[2] Stay with the others", 2);
		return choice;
	}

	public int runVecchio()throws IOException
	{
		clearScreen();
		show("runVecchio.txt");
		p.updatePrestige(-10);
		displayAll();
		int choice = p.makeChoice("\nWould you like to: (press [1] or [2] and hit ENTER, type 'quit' to quit)\n[1] Do you keep running away\nOR\n[2] Go back and play", 2);
		return choice;
	}

	public int runStrezzo()throws IOException
	{
		clearScreen();
		show("runStrezzo.txt");
		p.updatePrestige(-10);
		displayAll();
		int choice = p.makeChoice("\nWould you like to: (press [1] or [2] and hit ENTER, type 'quit' to quit)\n[1] Do you keep running away\nOR\n[2] Go back and play", 2);
		return choice;
	}

	public int goVecchio()throws IOException
	{
		clearScreen();
		show("vecchio.txt");
		p.updatePrestige(-10);
		displayAll();
		int choice = p.makeChoice("\nWould you like to: (press [1] or [2] and hit ENTER, type 'quit' to quit)\n[1] Do you run away\nOR\n[2] Try to explain the situation", 2);
		return choice;
	}
	public int goStrozzi()throws IOException
	{
		clearScreen();
		show("strozzi.txt");
		p.updatePrestige(-10);
		displayAll();
		int choice = p.makeChoice("\nWould you like to: (press [1] or [2] and hit ENTER, type 'quit' to quit)\n[1] Do you run away\nOR\n[2] Try to explain the situation", 2);
		return choice;	
	}

	public void stayWithCaravan()throws IOException
	{
		clearScreen();
		show("toTheInn.txt");
		p.updateCompositions(1);
		p.updatePrestige(10);
		p.updateWealth(5);
		displayAll();
		int choice = p.makeChoice("\nWould you like to: (press [1] or [2] and hit ENTER, type 'quit' to quit)\n[1] Go with Marco\nOR\n[2] Stay at the tavern", 2);

		//GO WITH MARCO
		if(choice == 1)
		{
			goWithMarco();
			int nxt = next();
			if(nxt == 0){quit = true;}
			barbIntro();
		}

		//CHAPERONE MARCO
		else
		{
			marcoChaperone();
			int nxt = next();
			if(nxt == 0){quit = true;}
			barbIntro();
		}
	}

	public int lasselsUpsetAskToPlay()throws IOException
	{
		clearScreen();
		show("upset.txt");
		displayAll();
		int choice = p.makeChoice("\nWould you like to: (press [1] or [2] and hit ENTER, type 'quit' to quit)\n[1] Ask Richard Lassels if he would mind if you played \nOR\n[2] Don't ask him", 2);
		return choice;
	}
	public int noAskLasselsCaravan() throws IOException
	{
		clearScreen();
		show("noAskLasselsCaravan.txt");
		displayAll();
		int choice = p.makeChoice("\nWould you like to: (press [1] or [2] and hit ENTER, type 'quit' to quit)\n[1] Join Richard Lassels on his journey \nOR\n[2] Stay with the caravan", 2);
		return choice;

	}
	public int askLasselsCaravan()throws IOException
	{
		clearScreen();
		show("askLasselsCaravan.txt");
		displayAll();
		int choice = p.makeChoice("\nWould you like to: (press [1] or [2] and hit ENTER, type 'quit' to quit)\n[1] Join Richard Lassels on his journey \nOR\n[2] Stay with the caravan", 2);
		return choice;
	}

	
	public int next()
	{
		System.out.print("Next: ");
		Scanner scan = new Scanner(System.in);
		String nxt = scan.nextLine();
		nxt = nxt.toUpperCase();
		if(  nxt.equals("NEXT")  )
		{
			return 1;
		}
		else if (  nxt.equals("QUIT")   )
		{
			System.exit(0);
		}
		else{
			System.out.println("Incorrect Input. Try again: ");
			int x = next();
		}
		return 1;
	}

	public static void clearScreen() {  
		System.out.print("\033[H\033[2J");  
		System.out.flush();  
	}  

	public void printBreak()
	{
		System.out.println("----------------------------------------------------------------");
	}

	public boolean checkQuit(int choice)
	{
		if(choice == 0){
			return true;}
		return false;
	}

	

	public int caravanMarket()throws IOException
	{
		printBreak();
		int choice = p.makeChoice("Would you like to: (press [1] or [2] and hit ENTER, type 'quit' to quit)\n[1] Head to the Caravan straight away \nOR\n[2] Head to the market to buy some more provisions?", 2);
		return choice;
	}

	public void caravan()throws IOException
	{
		//printBreak();
		show("caravan.txt");
		p.updateWealth(1);
		p.updatePrestige(2);
		displayAll();


	}

	public void market()throws IOException
	{
		//printBreak();
		show("market.txt");
		p.updateWealth(-1);
		displayAll();
	}
	




	public void show(String filepath) throws IOException
	{
		try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		 }
			
  
		
	}

	public void displayAll()
	{
		System.out.println("-----------------------------------------------");
		System.out.println("\n\n" + p + " : " + p.getStats() + "\n");
		System.out.println("Possessions: \n");
		System.out.println(p.printingArray(p.getPosessions()) + "\n\n");
		System.out.println("-----------------------------------------------");
	}

	

	
}

