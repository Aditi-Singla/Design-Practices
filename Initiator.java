import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Initiator
{
	ServerSocket serverSocket;
	int port = 8000;

	public Initiator(int port)
	{
		this.port = port;
		try
		{
			serverSocket = new ServerSocket(port);
			System.out.println ("Chat initiating.");

			try
			{
				while (true)
				{
					System.out.println ("Waiting for connections");
					new Initiated(serverSocket.accept());
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


	private class Initiated extends Thread
	{
		private Socket clientSocket;

		public Initiated(Socket client)
		{
			clientSocket = client;
			start();
		}

		public void run()
		{
			System.out.println ("New communication thread started.");
			try
			{
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);
				//InputStreamReader inputstream = new InputStreamReader(System.in);
				BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

				BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
				String input;

				String inputline;


				new receive(in);
				new send(stdIn,out);
				// while ((inputline = in.readLine()) != null || stdIn.readLine()!=null)
				// {
				// 	System.out.println("Them: " + inputline);
				// 	// if ((System.in).available()!=0)
				// 		out.println(stdIn.readLine());
				// }

				// while ((input = stdIn.readLine()) != null)
				// {
				// 	out.println(input);
				// }
			}
			catch (IOException exx)
			{
				System.err.println("Some problem");
				System.exit(1);
			}
		}
	}
		private class receive extends Thread
	{
		BufferedReader in;
		public receive (BufferedReader in)
		{
			this.in = in;
			start();
		}

		public void run()
		{
			String input;
			try{
				while ((input = in.readLine()) != null)
			{
				System.out.println("Them: " + input);
			}

			} catch (IOException e) {System.err.println("errrrrr");System.exit(1);}
			
		}
	}

	private class send extends Thread
	{
		BufferedReader stdIn;
		PrintWriter out;
		public send (BufferedReader stdIn, PrintWriter out)
		{
			this.stdIn = stdIn;
			this.out = out;
			start();
		}

		public void run()
		{
			String input;
			try{
				while ((input = stdIn.readLine()) != null)
			{
				out.println(input);
			}

			} catch (IOException e) {System.err.println("errrrrr");System.exit(1);}
			
		}
	}
}