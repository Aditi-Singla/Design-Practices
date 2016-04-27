import java.net.*;
import java.io.*;
import java.util.Scanner;

public class send extends Thread
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