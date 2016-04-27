import java.net.*;
import java.io.*;
import java.util.*;

public class sendToAll extends Thread
	{
		BufferedReader stdIn;
		ArrayList<PrintWriter> outs;
		public sendToAll (BufferedReader stdIn, ArrayList<PrintWriter> out)
		{
			this.stdIn = stdIn;
			outs = out;
			start();
		}

		public void run()
		{
			String input;
			int i;
			try{
				while ((input = stdIn.readLine()) != null)
			{
				for (i=0;i<outs.size();i++)
					outs.get(i).println(input);
			}

			} catch (IOException e) {System.err.println("errrrrr");System.exit(1);}
			
		}
	}