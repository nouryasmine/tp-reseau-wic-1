package rsr_tp1_question3_server;

import java.io.*;
import java.net.*;
public class server {

	
		  public static void main(String[] args) {
		        try {
		            ServerSocket server = new ServerSocket(9002);
		            System.out.println("En attente de connexion...");
while (true) {
		            Socket socket = server.accept();

		            InetAddress clientAddress = socket.getInetAddress();
		            int clientPort = socket.getPort();

		            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
		            DataInputStream in = new DataInputStream(socket.getInputStream());

		            String chaine1 = in.readUTF();
		            String chaine2 = in.readUTF();

		            System.out.println("Adresse du client : " + clientAddress + " (port " + clientPort + ")");
		            System.out.println("Chaine 1 reçue : " + chaine1);
		            System.out.println("Chaine 2 reçue : " + chaine2);

		            
		            String resultat = contains(chaine1, chaine2) ? "La seconde chaine est presente dans la premiere chaine." : "La seconde chaine n'est pas presente dans la premiere chaine.";
		            out.writeUTF(resultat);
                    out.flush();
		            
		        }} catch (IOException e) {
		            e.printStackTrace();
		        }
		    
		         }
	

    public static boolean contains(String chaine1, String chaine2) {
        return chaine1.contains(chaine2);
        
    }
	
}
