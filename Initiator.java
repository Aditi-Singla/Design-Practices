import java.net.*;
import java.io.*;
import java.util.*;
import javax.swing.Timer;
import java.awt.event.*;

public class Initiator implements ActionListener
{
	ServerSocket serverSocket;
	int port = 8000;
	ArrayList<PrintWriter> allOutputStreams = new ArrayList<PrintWriter>();
	ArrayList<String> addresses = new ArrayList<String>();
	ArrayList<Socket> clients = new ArrayList<Socket>();
	ArrayList<Initiated> initiateds = new ArrayList<Initiated>();
	int numPlayers;
	int playerNumber = 1;

	print_message message;

	Timer t;
	movingObjects movObj;
	// int vel0;
	// int vel2, vel3;

	public Initiator(int numPlayers, print_message message, Timer t, movingObjects movObj)
	
	{
		this.numPlayers = numPlayers;
		this.message = message;
		this.t = t;
		this.movObj = movObj;
		try
		{
			serverSocket = new ServerSocket(port);
			try
			{
				while (true)
				{
					if (playerNumber==numPlayers)
					{
						for (int i=0;i<initiateds.size();i++)
							initiateds.get(i).start();			///Starting the threads for players in order 2 to 4
						break;
					}
					Socket client = serverSocket.accept();		/////////// accepted client
					clients.add(client);				///////////// client added to clients list

					playerNumber++;						///////////// player number updated for client 
					String address = client.getInetAddress().getHostName().toString();
					addresses.add(address);				///////////// address of client added to address list

					Initiated started = new Initiated(client,playerNumber); ///////communication thread of player 1 and recently added player
					initiateds.add(started);
				}

				this.t.start();

			}
			catch (IOException e)
			{
				System.err.println ("Accept failed.");
				System.exit(1);
			}
		}
		catch (IOException e)
		{
			System.err.println ("Could not listen on port: " + port);	
		}
		finally
		{
			try
			{
				serverSocket.close();
			}
			catch (IOException e)
			{System.err.println ("Could not close port: " + port);
						System.exit(1);}
		}
	}

	public void actionPerformed(ActionEvent e)
	{
		movObj.actionPerformed(e);
	}


	public class Initiated extends Thread
	{
		private Socket clientSocket;
		int player;
		public Initiated(Socket client, int playernumm)
		{
			clientSocket = client;
			player = playernumm;
			// this.stdIn = stdIn;
			try
			{
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);
				allOutputStreams.add(out);
			} catch (IOException e) {e.printStackTrace();}			
		}


		public void run()
		{
			ObjectOutputStream objectOutput,objectOutput2,objectOutput3,objectOutput4;
			objectOutput = null; objectOutput2=null; objectOutput3=null; objectOutput4 = null;
			/////Sending object to each player which contains their playernumber, total players and the address to which it has to be connected
			try
			{
				//send address list to every client along
				if (numPlayers==2)
				{
					objectOutput = new ObjectOutputStream(clientSocket.getOutputStream());
					playerClass obj = new playerClass(2,2,"");
					objectOutput.writeObject(obj);
				}
				else if (numPlayers==3)
				{
					if (clientSocket == clients.get(0))
					{
						objectOutput2 = new ObjectOutputStream(clients.get(0).getOutputStream());
						playerClass obj = new playerClass(2,3,"");
						objectOutput2.writeObject(obj);
					}
					else
					{
						objectOutput3 = new ObjectOutputStream(clients.get(1).getOutputStream());
						playerClass obj1 = new playerClass(3,3,addresses.get(0));
						objectOutput3.writeObject(obj1);
					}
				}
				else if (numPlayers==4) 
				{
					if (clientSocket == clients.get(0))
					{
						objectOutput2 = new ObjectOutputStream(clients.get(0).getOutputStream());
						playerClass obj = new playerClass(2,4,addresses.get(2));
						objectOutput2.writeObject(obj);
					}
					else if (clientSocket == clients.get(1))
					{
						objectOutput3 = new ObjectOutputStream(clients.get(1).getOutputStream());
						playerClass obj1 = new playerClass(3,4,addresses.get(0));
						objectOutput3.writeObject(obj1);
					}	
					else
					{
						objectOutput4 = new ObjectOutputStream(clients.get(2).getOutputStream());
						playerClass obj2 = new playerClass(4,4,addresses.get(1));
						objectOutput4.writeObject(obj2);
					}	
				}

				try
				{	
					////Inputstream to receive from the client
					BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
					new receive(in,1,player,numPlayers);
					
					java.util.Timer timer = new java.util.Timer();
					sendToAll sendd = new sendToAll(allOutputStreams);
					timer.schedule(sendd,250,10);	////Outputstream to send data to every other player starting after 250 ms recurring every 10 ms

				}
				catch (IOException exx)
				{
					System.err.println("Some problem");
					exx.printStackTrace();
				}


			} catch (IOException k) {System.out.println("Error here");k.printStackTrace();}
				
		}
	}


	public class sendToAll extends TimerTask
	{
		ArrayList<PrintWriter> outs;
		String data;
		public sendToAll (ArrayList<PrintWriter> out)
		{
			outs = out;
		}

		public void run()
		{
			if (numPlayers==2)
				data = movObj.paddle0.vel + " " + movObj.powerused + " "+ movObj.paddle2.vel + " " + movObj.paddle3.vel;
			else if (numPlayers==3)
				data = movObj.paddle0.vel + " " + movObj.powerused + " " + movObj.paddle2.vel;
			else
				data = movObj.paddle0.vel + " " + movObj.powerused;
			for (int i=0;i<outs.size();i++)
				outs.get(i).println(data);
		}
	}

}