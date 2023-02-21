package rsr_tp1_question5_client1;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class client1 {
	
	
	public static void main(String argv[]) {

		try {
			
			Socket socket = new Socket("localhost",1629);
			
			System.out.println("mon adress client: "+socket.getLocalAddress()+": "+socket.getLocalPort());
			System.out.println("mon serveur est: "+socket.getInetAddress()+": "+socket.getPort());
			
			Scanner scanner = new Scanner(System.in);
		    System.out.print("Entrez la 1er chaine : ");
		    String chaine1 = scanner.nextLine();
		    System.out.print("Entrez la 2eme chaine : ");
		    String chaine2 = scanner.nextLine();
		    
		    System.out.println("Les chaines envoyees :");
		    System.out.println(chaine1);
		    System.out.println(chaine2);
		    
		    ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			out.writeObject(chaine1);
			out.writeObject(chaine2);
			out.flush();
		    
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			String line = (String) in.readObject();
			int nbrClients = (int) in.readObject();
			System.out.println("message recu du serveur est: "+line);
			System.out.println("Le nembre des clients connectes :"+nbrClients);
			
			
			
			
			
		}
		catch (Exception e) {
			System.err.println("Erreur :"+e);
		}
	}
}
