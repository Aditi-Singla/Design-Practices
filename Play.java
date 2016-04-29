import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Play
{
	public static void main (String[] args) throws IOException
	{
		Scanner scan = new Scanner(System.in);
		System.out.print("Are you the initiator?");
		String response = scan.next();
		int port;
		// System.out.print("Enter port number for initiator: ");
		// port = scan.nextInt();
		if (response.toLowerCase().equals("yes"))
		{
			System.out.print("Enter the number of players: ");
			int numPlayers = scan.nextInt();
			// Initiator initiator = new Initiator(port,numPlayers);
			Initiator initiator = new Initiator(8000,numPlayers);
		}
		else
		{
			// System.out.print("Enter hostname: ");
			// String hostname = scan.next();
			// Player player = new Player(hostname,port);
			Player player = new Player("10.192.39.3",8000);

			player.run();
		}
	}
}