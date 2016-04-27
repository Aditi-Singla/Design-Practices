import java.net.*;
import java.io.*;
import java.util.*;

public class Initiator
{
	ServerSocket serverSocket;
	int port = 8000;
	ArrayList<PrintWriter> allOutputStreams = new ArrayList<PrintWriter>();
	ArrayList<String> addresses = new ArrayList<String>();
	ArrayList<Socket> clients = new ArrayList<Socket>();
	ArrayList<Initiated> initiateds = new ArrayList<Initiated>();
	int numPlayers;
	int playerNumber = 1;

	public Initiator(int port, int numPlayers)
	{
		this.port = port;
		this.numPlayers = numPlayers;
		try
		{
			serverSocket = new ServerSocket(port);
			System.out.println ("Chat initiating.");
			BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
			try
			{
				while (true)
				{
					if (playerNumber==numPlayers)
					{
						for (int i=0;i<initiateds.size();i++)
							initiateds.get(i).start();
						break;
					}
					System.out.println ("Waiting for connections");
					Socket client = serverSocket.accept();		/////////// accepted client
					clients.add(client);				///////////// client added to clients list

					playerNumber++;						///////////// player number updated for client 
					String address = client.getInetAddress().getHostName().toString();
					addresses.add(address);				///////////// address of client added to address list

					Initiated started = new Initiated(client,stdIn); ///////communication thread of player 1 and recently added player
					initiateds.add(started);

					// started.start();
					// for (int i=0;i<initiateds.size();i++)
					// 	initiateds.get(i).updatenum(playerNumber);
				}
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


	public class Initiated extends Thread
	{
		private Socket clientSocket;
		private BufferedReader stdIn;

		public Initiated(Socket client, BufferedReader stdIn)
		{
			clientSocket = client;
			this.stdIn = stdIn;
			try
			{
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);
				allOutputStreams.add(out);
			} catch (IOException e) {e.printStackTrace();}			
		}


		public void run()
		{
			System.out.println ("New communication thread started.");
			ObjectOutputStream objectOutput,objectOutput2,objectOutput3,objectOutput4;
			objectOutput = null; objectOutput2=null; objectOutput3=null; objectOutput4 = null;
			try
			{
				//send address list to every client along
				if (numPlayers==2)
				{
					objectOutput = new ObjectOutputStream(clientSocket.getOutputStream());
					playerClass obj = new playerClass(2,2,"");
					objectOutput.writeObject(obj);	
					// objectOutput.flush(); objectOutput.close();
				}
				else if (numPlayers==3)
				{
					if (clientSocket == clients.get(0))
					{
						objectOutput2 = new ObjectOutputStream(clients.get(0).getOutputStream());
						playerClass obj = new playerClass(2,3,"");
						objectOutput2.writeObject(obj);
						// objectOutput2.flush(); objectOutput2.close();
					}
					else
					{
						objectOutput3 = new ObjectOutputStream(clients.get(1).getOutputStream());
						playerClass obj1 = new playerClass(3,3,addresses.get(0));
						objectOutput3.writeObject(obj1);
						// objectOutput3.flush(); objectOutput3.close();
					}
				}
				else if (numPlayers==4) 
				{
					if (clientSocket == clients.get(0))
					{
						objectOutput2 = new ObjectOutputStream(clients.get(0).getOutputStream());
						playerClass obj = new playerClass(2,4,addresses.get(2));
						objectOutput2.writeObject(obj);
						// objectOutput2.flush(); objectOutput2.close();
					}
					else if (clientSocket == clients.get(1))
					{
						objectOutput3 = new ObjectOutputStream(clients.get(1).getOutputStream());
						playerClass obj1 = new playerClass(3,4,addresses.get(0));
						objectOutput3.writeObject(obj1);
						// objectOutput3.flush(); objectOutput3.close();
					}	
					else
					{
						objectOutput4 = new ObjectOutputStream(clients.get(2).getOutputStream());
						playerClass obj2 = new playerClass(4,4,addresses.get(1));
						objectOutput4.writeObject(obj2);
						// objectOutput4.flush(); objectOutput4.close();
					}	
				}
				else
				{
					System.out.println("Number of players exceeded");
					System.exit(1);
				}

				try
				{	
					BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
					new receive(in);
					new sendToAll(stdIn,allOutputStreams);	
				}
				catch (IOException exx)
				{
					System.err.println("Some problem");
					exx.printStackTrace();
				}


			} catch (IOException k) {System.out.println("Error here");k.printStackTrace();}
				
		}
	}
}