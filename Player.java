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
		// System.out.println("Attempting to connect to host " + hostname + " on port " + port);
	}

	@Override
	public void run()
	{
		try
		{
			Socket s = new Socket(hostname,port);	
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
					// System.out.println(playerNumber + " hjhghjghjg " + total + " jkdsfjksjd " + address);
					// objInp.close();

					BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
					PrintWriter outSERVER = new PrintWriter(s.getOutputStream(),true);
					BufferedReader inSERVER = new BufferedReader(new InputStreamReader(s.getInputStream()));
					new receive(inSERVER);

					if (total==2 && playerNumber==2)
					{
						new send(stdIn,outSERVER);
					}

					else if (total==3)
					{
						if (playerNumber==2)
						{
							try
							{
								ServerSocket s2for3 = new ServerSocket(8232);
								System.out.println("Waiting for player 3");
								Socket client3 = s2for3.accept();
								BufferedReader in3 = new BufferedReader (new InputStreamReader(client3.getInputStream()));
								new receive(in3);

								PrintWriter out3 = new PrintWriter(client3.getOutputStream(),true);
								ArrayList<PrintWriter> outs = new ArrayList<PrintWriter>();
								outs.add(outSERVER); outs.add(out3);
								new sendToAll(stdIn,outs);
							} catch(IOException e){e.printStackTrace();}
						}
						else if (playerNumber==3)
						{
							try
							{
								Socket s3to2 = new Socket(address,8232);
								BufferedReader in2 = new BufferedReader(new InputStreamReader(s3to2.getInputStream()));
								new receive (in2);
				
								PrintWriter out2 = new PrintWriter(s3to2.getOutputStream(),true);
								ArrayList<PrintWriter> outs = new ArrayList<PrintWriter>();
								outs.add(outSERVER); outs.add(out2);
								new sendToAll(stdIn,outs);
							} catch(IOException e){e.printStackTrace();}
						}
						else
						{System.out.println("Invalid Input");System.exit(1);}
					}

					else if (total==4)
					{
						// System.out.println("I AM " + playerNumber);
						if (playerNumber==2)
						{
							System.out.println("I AM 222222222");
							try
							{
								setPrivateServer serverFor2 = new setPrivateServer(8232,stdIn);
								serverFor2.start();
								Date date = new Date();
								// System.out.println("server for 2 started " );

								ArrayList<PrintWriter> outlist = new ArrayList<PrintWriter>();
								outlist.add(outSERVER);

								System.out.println(address);
								Socket s2to4 = new Socket(address,8712);
								System.out.println("connected to 4");
								BufferedReader in4 = new BufferedReader(new InputStreamReader(s2to4.getInputStream()));
								new receive (in4);

								PrintWriter out4 = new PrintWriter(s2to4.getOutputStream(),true);
								// serverFor2.update(out4);
								outlist.add(out4);
								Socket get2=null;
								PrintWriter out3=null;
								while(get2==null)
									{
										get2 = serverFor2.getSock();
										try{out3 = new PrintWriter(get2.getOutputStream(),true);} catch(Exception e){}
										
									}
								outlist.add(out3);
								new sendToAll(stdIn,outlist);
							} catch(IOException e1){System.out.println("yahan");e1.printStackTrace();}
						}

						else if (playerNumber==3)
						{
							try
							{
								ArrayList<PrintWriter> outlist = new ArrayList<PrintWriter>();
								outlist.add(outSERVER);
								setPrivateServer serverFor3 = new setPrivateServer(8313,stdIn);
								serverFor3.start();
								System.out.println("Server for 3 started");
								Date date = new Date();
								System.out.println("trying"+date.getTime());
								Socket s3to2 = new Socket(address,8232);
								System.out.println("connected to 2");
								BufferedReader in2 = new BufferedReader(new InputStreamReader(s3to2.getInputStream()));
								new receive (in2);

								PrintWriter out2 = new PrintWriter(s3to2.getOutputStream(),true);
								// serverFor3.update(out2);
								outlist.add(out2);
								Socket get3 = null;
								PrintWriter out4=null;
								while(get3==null)
								{
									get3 = serverFor3.getSock();
									try{out4 = new PrintWriter(get3.getOutputStream(),true);} catch(Exception e){}
								}
								
								outlist.add(out4);
								// outs.add(out2);
								new sendToAll(stdIn,outlist);
							} catch(IOException e2){System.out.println("yahaaaaaan");e2.printStackTrace();}
						}

						else if (playerNumber==4)
						{
							try
							{
								ArrayList<PrintWriter> outlist = new ArrayList<PrintWriter>();
								outlist.add(outSERVER);
								setPrivateServer serverFor4 = new setPrivateServer(8712,stdIn);	
								serverFor4.start();
								System.out.println("Server for 4 started");

								Socket s4to3 = new Socket(address,8313);
								System.out.println("connected to 3");
								BufferedReader in3 = new BufferedReader(new InputStreamReader(s4to3.getInputStream()));
								new receive (in3);

								PrintWriter out3 = new PrintWriter(s4to3.getOutputStream(),true);
								// serverFor4.update(out3);
								outlist.add(out3);
								Socket get4 = null;
								PrintWriter out2=null;
								while (get4 == null)
								{
									get4 = serverFor4.getSock();
									try{out2 = new PrintWriter(get4.getOutputStream(),true);} catch(Exception e){}
								}
								
								outlist.add(out2);
								new sendToAll(stdIn,outlist);
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
				// System.out.println()
				ServerSocket serverSocket = new ServerSocket(port);
				Date date = new Date();
				System.out.println("STARTED AT" + port + "at time   " + date.getTime());
				Socket client = serverSocket.accept();
				System.out.println("client accpeted");

				BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
				new receive(in);
			}
			catch (IOException e) {System.out.println("weird error");e.printStackTrace();}
		}
	}