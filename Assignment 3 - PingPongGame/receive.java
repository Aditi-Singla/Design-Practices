import java.net.*;
import java.io.*;
import java.util.Scanner;


public class receive extends Thread
	{
		BufferedReader in;
		int me, receivedFrom, totalPlayers;
		movingObjects movObj;
		public receive (BufferedReader in, int myplayerNum, int fromPlayerNum, int total, movingObjects movobj)
		{
			this.in = in;
			me = myplayerNum;
			receivedFrom = fromPlayerNum;
			totalPlayers = total;
			movObj = movobj;
			start();
		}

		public void run()
		{
			String input;
			try
			{
				while ((input = in.readLine()) != null)
				{
					

			// 		if (numPlayers==2)
			// 			data = movObj.paddle0.vel + " " + movObj.powerused + " "+ movObj.paddle2.x + movObj.w + " " + movObj.paddle3.y;
			// 		else if (numPlayers==3)
			// 			data = movObj.paddle0.vel + " " + movObj.powerused + " " + movObj.paddle2.x;	ImovObj.w + NITIATORRRRRR
			// 		else
			// 			data = movObj.paddle0.vel + " " + movObj.powerused;



					// data = movObj.paddle0.vel + " " + movObj.powerused;	PLAYERRRRRR

					String[] arguments = input.split("\\s+");
					// System.out.println(movObj.h + " " + movObj.w);

					// System.out.println(arguments[1]);

					switch(me) {
						case 1:	switch(receivedFrom) {
									case 2:	
											movObj.paddle1.y = movObj.h + (Double.parseDouble(arguments[0]));
											movObj.paddle1.vel = (Double.parseDouble(arguments[1]));
											movObj.paddle1.powerUsed = Integer.parseInt(arguments[2]);
											break;
									case 3:	movObj.paddle2.x = movObj.w + 600 - movObj.paddle2.l + -1 * (Double.parseDouble(arguments[0]));
											movObj.paddle2.vel = -1 * (Double.parseDouble(arguments[1]));
											movObj.paddle2.powerUsed = Integer.parseInt(arguments[2]);
											break;
									default:	movObj.paddle3.y = movObj.h + 600 - movObj.paddle2.l + -1 * (Double.parseDouble(arguments[0]));
												movObj.paddle3.vel = -1 * (Double.parseDouble(arguments[1]));
											movObj.paddle3.powerUsed = Integer.parseInt(arguments[2]);
								}
								break;
						case 2:	switch(receivedFrom) {
									case 1:	movObj.paddle3.y = movObj.h + 600 - movObj.paddle3.l + -1 * (Double.parseDouble(arguments[0]));
											movObj.paddle3.vel = -1 * (Double.parseDouble(arguments[1]));
											movObj.paddle3.powerUsed = Integer.parseInt(arguments[2]);
											
											if(arguments.length == 3) 
												movObj.paddle2.x = movObj.w + 600 - movObj.paddle2.l + -1 * (Double.parseDouble(arguments[3]) );
											else {
												movObj.paddle1.y = movObj.h + (Double.parseDouble(arguments[3]));
												movObj.paddle2.x = movObj.w + 600 - movObj.paddle2.l + -1 * (Double.parseDouble(arguments[4]) );
											}
											break;
									case 3:	movObj.paddle1.y = movObj.h + (Double.parseDouble(arguments[0]));
											movObj.paddle1.vel = (Double.parseDouble(arguments[1]));
											movObj.paddle1.powerUsed = Integer.parseInt(arguments[2]);
											break;
									default:movObj.paddle2.x = movObj.w + 600 - movObj.paddle2.l + -1 * (Double.parseDouble(arguments[0]));
											movObj.paddle2.vel = -1 * (Double.parseDouble(arguments[1]));
											movObj.paddle2.powerUsed = Integer.parseInt(arguments[2]);
								}
								break;
						case 3:	switch(receivedFrom) {
									case 1:	movObj.paddle2.x = movObj.w + 600 - movObj.paddle2.l + -1 * (Double.parseDouble(arguments[0]));
											movObj.paddle2.vel = -1 *(Double.parseDouble(arguments[1]));
											movObj.paddle2.powerUsed = Integer.parseInt(arguments[2]);
											
											if(arguments.length == 3) 
												movObj.paddle1.y = movObj.h + Double.parseDouble(arguments[3]);
											break;
									case 2:	movObj.paddle3.y = movObj.h + 600 - movObj.paddle3.l + -1 * (Double.parseDouble(arguments[0]));
											movObj.paddle3.vel = -1 * (Double.parseDouble(arguments[1]));
											movObj.paddle3.powerUsed = Integer.parseInt(arguments[2]);
											break;
									default:movObj.paddle1.y = movObj.h + (Double.parseDouble(arguments[0]));
											movObj.paddle1.vel = (Double.parseDouble(arguments[1]));
											movObj.paddle1.powerUsed = Integer.parseInt(arguments[2]);
								}
								break;
						default:switch(receivedFrom) {
									case 1:	movObj.paddle1.y = movObj.h + (Double.parseDouble(arguments[0]));
											movObj.paddle1.vel = (Double.parseDouble(arguments[1]));
											movObj.paddle1.powerUsed = Integer.parseInt(arguments[2]);
											break;
									case 2:	movObj.paddle2.x = movObj.w + 600 - movObj.paddle2.l + -1 * (Double.parseDouble(arguments[0]) );
											movObj.paddle2.vel = -1 * (Double.parseDouble(arguments[1]));
											movObj.paddle2.powerUsed = Integer.parseInt(arguments[2]);
											break;
									default:movObj.paddle3.y = movObj.h + 600 - movObj.paddle3.l + -1 *(Double.parseDouble(arguments[0]) );
											movObj.paddle3.vel = -1 * (Double.parseDouble(arguments[1]));
											movObj.paddle3.powerUsed = Integer.parseInt(arguments[2]);
								}
					}


				}

				// 	if (totalPlayers==2)
				// 	{
				// 		if (me==1)
				// 		{
				// 			movObj.paddle1.y = movObj.h + (Double.parseDouble(arguments[0]) +);
				// 			movObj.paddle1.powerUsed = Integer.parseInt(arguments[1]);
				// 		}
				// 		else
				// 		{
				// 			movObj.paddle1.y = movObj.h + (Double.parseDouble(arguments[0]) +);
				// 			movObj.paddle1.powerUsed = Integer.parseInt(arguments[1]);
							
				// 			movObj.paddle2.x = movObj.w + Double.parseDouble(arguments[2]);
							
				// 			movObj.paddle3.y = movObj.h + Double.parseDouble(arguments[3]);
				// 		}
				// 	}
				// 	else if (totalPlayers==3)
				// 	{
				// 		if (me==1)
				// 		{
				// 			switch(receivedFrom) {
				// 				case 2:		movObj.paddle1.y = movObj.h + (Double.parseDouble(arguments[0]) +);
				// 							movObj.paddle1.powerUsed = Integer.parseInt(arguments[1]);
				// 							break;
				// 				default:	movObj.paddle2.x = movObj.w + (Double.parseDouble(arguments[0]) +);
				// 							movObj.paddle2.powerUsed = Integer.parseInt(arguments[1]);
				// 			}
				// 		}
				// 		else if(me==2)
				// 		{
				// 			switch(receivedFrom) {
				// 				case 1:		movObj.paddle2.x = movObj.w + (Double.parseDouble(arguments[0]) +);
				// 							movObj.paddle2.powerUsed = Integer.parseInt(arguments[1]);

				// 							movObj.paddle3.y = movObj.h + (Double.parseDouble(arguments[0]) +);
				// 							break;
				// 				case 3:		
				// 			}
				// 		}
				// 		else
				// 		{

				// 		}
				// 	}
				// 	else if (totalPlayers==4)
				// 	{
				// 		if (me==1)
				// 		{

				// 		}
				// 		else if (me==2)
				// 		{

				// 		}
				// 		else if (me==3)
				// 		{

				// 		}
				// 		else
				// 		{

				// 		}
				// 	}
				// }

			} catch (IOException e) 
			{
				System.err.println("DISCONNECTED" + receivedFrom);
				// movObj.
			}
			
		}
	}