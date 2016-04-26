import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Player extends Thread
{
	String hostname;
	int port;

	public Player(String hostname, int port)
	{
		this.hostname = hostname;
		this.port = port;
		System.out.println("Attempting to connect to host " + hostname + " on port " + port);
	}

	@Override
	public void run()
	{
		try
		{
			Socket s = new Socket(hostname,port);
			PrintWriter out = new PrintWriter(s.getOutputStream(),true);
			BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
			//InputStreamReader inputstream = new InputStreamReader(s.getInputStream());
			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String inputline;
			String input;

			new receive(in);
			new send(stdIn,out);

			// while ((input = stdIn.readLine()) != null || in.readLine() != null)
			// {
			// 	out.println(input);
			// 	// if ((s.getInputStream()).available()!=0)
			// 		System.out.println("Them: " + in.readLine());
			// 	// while ((inputline = in.readLine()) != null)
			// 	// {
			// 	// 	System.out.println("Them: " + inputline);
			// 	// }
			// }

			
			

			// out.close();
			// stdIn.close();
			// s.close();
		}
		catch (UnknownHostException e)
		{
			System.err.println("Don't know about host: " + hostname);
            System.exit(1);
		}
		catch (IOException e)
		{
			System.err.println("Couldn't get I/O");
            System.exit(1);
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