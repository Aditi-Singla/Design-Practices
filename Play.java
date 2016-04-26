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
		System.out.print("Enter port number for initiator: ");
		port = scan.nextInt();
		if (response.toLowerCase().equals("yes"))
		{
			Initiator initiator = new Initiator(port);
		}
		else
		{
			System.out.print("Enter hostname: ");
			String hostname = scan.next();
			Player player = new Player(hostname,port);
			player.start();
		}
	}
}