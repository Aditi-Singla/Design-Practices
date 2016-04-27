import java.net.*;
import java.io.*;
import java.util.*;
public class Player extends Thread
{
	String hostname;
	int port;
	int playerNumber;
	int total;
	String address = "";

	public Player(String hostname, int port)
	{
		this.hostname = hostname;
		this.port = port;
	}

	@Override
	public void run()
	{
		try
		{
			Socket s = new Socket(hostname,port);	///Connects to the initiator
			try
			{
				ObjectInputStream objInp = new ObjectInputStream(s.getInputStream());
				try
				{
					Object object = objInp.readObject();
					playerClass playerData = (playerClass) object;
					playerNumber = playerData.playerNumber;
					total = playerData.total;
					address = playerData.address;
					///////////Received playernumber, total number of players, and address to connect to

					BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in)); ///// INPUT TO FEED
					
					BufferedReader inSERVER = new BufferedReader(new InputStreamReader(s.getInputStream()));
					new receive(inSERVER); //////Received game data from PLAYER 1

					PrintWriter outSERVER = new PrintWriter(s.getOutputStream(),true);

					if (total==2 && playerNumber==2) /////  2 player game
					{
						new send(stdIn,outSERVER);	///Sending game data to PLAYER 1
					}

					else if (total==3)		///   3 player game
					{
						if (playerNumber==2)		/// PLAYER 2
						{
							try
							{
								ServerSocket s2for3 = new ServerSocket(8232);
								Socket client3 = s2for3.accept();
								BufferedReader in3 = new BufferedReader (new InputStreamReader(client3.getInputStream()));
								new receive(in3);		//// Received game data from PLAYER 3

								PrintWriter out3 = new PrintWriter(client3.getOutputStream(),true);
								ArrayList<PrintWriter> outs = new ArrayList<PrintWriter>();
								outs.add(outSERVER); outs.add(out3);
								new sendToAll(stdIn,outs);	///// Sending game data to PLAYER 1 & 3
							} catch(IOException e){e.printStackTrace();}
						}
						else if (playerNumber==3)		////PLAYER 3
						{
							try
							{
								Socket s3to2 = new Socket(address,8232);
								BufferedReader in2 = new BufferedReader(new InputStreamReader(s3to2.getInputStream()));
								new receive (in2);		////Received game data from PLAYER 2
				
								PrintWriter out2 = new PrintWriter(s3to2.getOutputStream(),true);
								ArrayList<PrintWriter> outs = new ArrayList<PrintWriter>();
								outs.add(outSERVER); outs.add(out2);
								new sendToAll(stdIn,outs);	/////Sending game data to PLAYER 1 & 2
							} catch(IOException e){e.printStackTrace();}
						}
						else
						{System.out.println("Invalid Input");System.exit(1);}
					}

					else if (total==4)		//// 4 player game
					{
						if (playerNumber==2)	///   PLAYER 2
						{
							try
							{
								ArrayList<PrintWriter> outlist = new ArrayList<PrintWriter>();
								outlist.add(outSERVER);
								setPrivateServer serverFor2 = new setPrivateServer(8232,stdIn);
								serverFor2.start();		////  Server created for 3 and received data 

								try{sleep(100);}catch(Exception e){}

								Socket s2to4 = new Socket(address,8712);
								
								BufferedReader in4 = new BufferedReader(new InputStreamReader(s2to4.getInputStream()));
								new receive (in4);		//// Received game data from PLAYER 4

								try{sleep(100);}catch(Exception e){}

								PrintWriter out4 = new PrintWriter(s2to4.getOutputStream(),true);
								outlist.add(out4);
								Socket get2 = serverFor2.getSock();
								PrintWriter out3 = new PrintWriter(get2.getOutputStream(),true);
								outlist.add(out3);
								new sendToAll(stdIn,outlist);		/// Sending game data to all the other players
							} catch(IOException e1){System.out.println("yahan");e1.printStackTrace();}
						}

						else if (playerNumber==3)		////  PLAYER 3
						{
							try
							{
								ArrayList<PrintWriter> outlist = new ArrayList<PrintWriter>();
								outlist.add(outSERVER);
								setPrivateServer serverFor3 = new setPrivateServer(8313,stdIn);
								serverFor3.start();	////  Server created for 4 and received data

								try{sleep(100);}catch(Exception e){}

								Socket s3to2 = new Socket(address,8232);
								
								BufferedReader in2 = new BufferedReader(new InputStreamReader(s3to2.getInputStream()));
								new receive (in2);		//// Received game data from PLAYER 2

								try{sleep(100);}catch(Exception e){}

								PrintWriter out2 = new PrintWriter(s3to2.getOutputStream(),true);
								outlist.add(out2);
								Socket get3 = serverFor3.getSock();
								PrintWriter out3 = new PrintWriter(get3.getOutputStream(),true);
								outlist.add(out3);
								new sendToAll(stdIn,outlist);		////  Sending game data to all the other players
							} catch(IOException e2){System.out.println("yahaaaaaan");e2.printStackTrace();}
						}

						else if (playerNumber==4)		/// PLAYER 4
						{
							try
							{
								ArrayList<PrintWriter> outlist = new ArrayList<PrintWriter>();
								outlist.add(outSERVER);
								setPrivateServer serverFor4 = new setPrivateServer(8712,stdIn);	
								serverFor4.start();		////  Server created for 2 and received data

								try{sleep(100);}catch(Exception e){}

								Socket s4to3 = new Socket(address,8313);
								
								BufferedReader in3 = new BufferedReader(new InputStreamReader(s4to3.getInputStream()));
								new receive (in3);		//// Received game data from PLAYER 3

								try{sleep(100);}catch(Exception e){}

								PrintWriter out3 = new PrintWriter(s4to3.getOutputStream(),true);
								outlist.add(out3);
								Socket get4 = serverFor4.getSock();
								PrintWriter out2 = new PrintWriter(get4.getOutputStream(),true);
								
								outlist.add(out2);
								new sendToAll(stdIn,outlist);		////  Sending game data to all the other players
							} catch(IOException e3){System.out.println("yahan bro");e3.printStackTrace();}
						}
						else
							{System.out.println("Invalid Input");System.exit(1);}
					}

					else
					{
						System.err.println("Invalid");
					}

				} catch (ClassNotFoundException e) {System.out.println("Object not received"); e.printStackTrace();}
			} catch (IOException e) {System.out.println("InputStream problem"); e.printStackTrace();}
		}
		catch (UnknownHostException e)
		{
			System.err.println("Don't know about host: " + hostname);
            System.exit(1);
		}
		catch (IOException e)
		{
			System.err.println("Couldn't get I/O");
			e.printStackTrace();
            System.exit(1);
		}
	}
}

 class setPrivateServer extends Thread
	{
		////  Thread for server setup
		int port;
		BufferedReader stdIn;
		Socket client;

		public setPrivateServer(int port, BufferedReader stdIn)
		{
			this.port = port;
			this.stdIn = stdIn;
		}

		public Socket getSock ()
		{
			return client;
		}

		public void run()
		{
			try
			{
				ServerSocket serverSocket = new ServerSocket(port);
				client = serverSocket.accept();
				System.out.println("client accepted");

				BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
				new receive(in);
			}
			catch (IOException e) {System.out.println("weird error");e.printStackTrace();}
		}
	}