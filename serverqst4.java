package rsr_tp1_question4_server;
import java.io.DataInputStream;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
public class server {
	public static void main(String argv[]) {
		try {
		
		ServerSocket s = new ServerSocket(5000);

		while(true) {
			
		System.out.println("Serveur en attente: ");
		
		Socket soc = s.accept();

		ObjectOutputStream out = new ObjectOutputStream (soc.getOutputStream ());

		ObjectInputStream in = new ObjectInputStream(soc.getInputStream());

		String chaine1 = (String) in.readObject();
		String chaine2 = (String) in.readObject();

		System.out.println("Les chaines recues sont : "+chaine1+" et "+chaine2);
		
		if(chaine1.contains(chaine2)) {
			out.writeObject(chaine1+" contient "+chaine2);
			out.flush ();
		}
		else {
			out.writeObject(chaine1+" ne contient pas "+chaine2);
			out.flush ();
		}
		
		


		System.out.println(" adresse client:"+soc.getRemoteSocketAddress() );

			}

		}catch(Exception e) {

		System.err.println("Erreur:"+e);
	
}}
}
