package rsr_tp1_question5_server;
import java.io.*;
	import java.net.*;
public class server extends Thread{
	


	

	private Socket socket;
	static int nbrClients=0;
	

	public static void main(String argv[]) {
		try {
		
		ServerSocket serverSocket = new ServerSocket(1629);

		while(true) {
			
		System.out.println("Serveur en attente: ");
		
		Socket socketClient = serverSocket.accept();
		
		server serveur= new server(socketClient);
		nbrClients++;
		serveur.start();
			}

		}catch(Exception e) {
			e.printStackTrace();
			}
		}
	
	public server(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		try {
			System.out.println("Le nombre des clients conectes: "+nbrClients);
			ObjectOutputStream out = new ObjectOutputStream (socket.getOutputStream ());

			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

			String chaine1 = (String) in.readObject();
			String chaine2 = (String) in.readObject();

			System.out.println("Les chaines recues sont : "+chaine1+" et "+chaine2);
			
			if(chaine1.contains(chaine2)) {
				out.writeObject(chaine1+" contient "+chaine2);
				out.writeObject(nbrClients);
				out.flush ();
				nbrClients--;
				
			}
			else {
				out.writeObject(chaine1+" ne contient pas "+chaine2);
				out.writeObject(nbrClients);
				out.flush ();
				nbrClients--;
			}
			
			System.out.println(" adresse client:"+socket.getRemoteSocketAddress() );
			
			
			
		}catch(Exception e) {

			System.err.println("Erreur:"+e);
			nbrClients--;

	}}
		}

