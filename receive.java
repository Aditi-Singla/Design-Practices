import java.net.*;
import java.io.*;
import java.util.Scanner;

public class receive extends Thread
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