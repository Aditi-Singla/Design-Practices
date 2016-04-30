import java.net.*;
import java.io.*;
import java.util.Scanner;

public class receive extends Thread
	{
		BufferedReader in;
		int me, receivedFrom, totalPlayers;
		public receive (BufferedReader in, int myplayerNum, int fromPlayerNum, int total)
		{
			this.in = in;
			me = myplayerNum;
			receivedFrom = fromPlayerNum;
			totalPlayers = total;
			start();
		}

		public void run()
		{
			String input;
			try
			{
				while ((input = in.readLine()) != null)
				{
					// String[] arguments = input.split("\\s+");
					System.out.println( receivedFrom+ input);

					// if (totalPlayers==2)
					// {
					// 	if (me==1)
					// 	{
								
					// 	}
					// 	else
					// 	{

					// 	}
					// }
					// else if (totalPlayers==3)
					// {
					// 	if (me==1)
					// 	{

					// 	}
					// 	else if(me==2)
					// 	{

					// 	}
					// 	else
					// 	{

					// 	}
					// }
					// else if (totalPlayers==4)
					// {

					// }
				}

			} catch (IOException e) {System.err.println("errrrrr");System.exit(1);}
			
		}
	}