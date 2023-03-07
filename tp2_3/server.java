package tp2_3;

import java.io.*;
import java.net.*;
import java.util.*;

import tp2_1.utilisateur;

public class server extends Thread{
	
	
	private Socket socket;
	static ArrayList<utilisateur> User = new ArrayList<utilisateur>(); 
	static boolean bool=false ;
	
	
	public  utilisateur trouverUtilisateur(String idUtilisateur) {
        for (utilisateur utilisateur : User) {
            if (utilisateur.getId().equals(idUtilisateur)) {
                return utilisateur;
            }
        }
        return null;
    }

    public utilisateur ajouterUtilisateur(String nomUtilisateur, String motDePasse) {
        utilisateur utilisateur = new utilisateur(nomUtilisateur, motDePasse);
        User.add(utilisateur);
        return utilisateur;
    }
    
    public static int[][] somme(int[][] A,int[][] B){
		int[][] Somme = new int[A.length][A[0].length];
		for(int i=0;i<A.length;i++) {
    		for(int j=0;j<A[i].length;j++) {
    			Somme[i][j] = A[i][j] + B[i][j];
    		}	
    	}
		return Somme;
	}
    public static int[][] multiplication(int[][] A,int[][] B){
		int[][] produit = new int[A.length][B[0].length];
		for(int i=0;i<A.length;i++) {
    		for(int j=0;j<B[0].length;j++) {
    			for(int k=0;k<A[0].length;k++)
    			produit[i][j] += A[i][k] * B[k][j];
    		}	
    	}
		return produit;
	}
    
    public static int[][] transpose(int[][] A){
		int[][] tmatrice = new int[A[0].length][A.length];
		for(int i=0;i<A[0].length;i++) {
    		for(int j=0;j<A.length;j++) {
    			tmatrice[j][i] = A[i][j];
    		}	
    	}
		return tmatrice;
	}




	

	public static void main(String argv[]) {
		
		User.add(new utilisateur("rahim","rahim"));
		
		
	
		
		try {
		
		ServerSocket serverSocket = new ServerSocket(1629);

		while(true) {
			
		System.out.println("Serveur en attente: ");
		
		Socket socketClient = serverSocket.accept();
		
		server serveur= new server(socketClient);
	
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
		
			ObjectOutputStream out = new ObjectOutputStream (socket.getOutputStream ());

			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

			utilisateur utili = (utilisateur) in.readObject();
			
			
			//System.out.println(utili.id);
			
			out.writeObject("welcome");
			out.flush();
			
			utilisateur auth = trouverUtilisateur(utili.id);
			
			if(auth!=null) {
				if(auth.getPass().equals(utili.pass)) {
					System.out.println("le client est authentifie");
					
					out.writeObject("choisissez une op�ration '*' '+' 't' ");
					out.flush();
					String op = (String) in.readObject();
					System.out.println(op);
					
					switch(op) {
					case "+":
						
						
						//out.writeObject(new MatrixObject(matrix.length, matrix[0].length, matrix));
						//lire les matrices
						int[][] matrice1= (int[][])in.readObject();
						
						for (int i = 0; i < matrice1.length; i++) {
				            for (int j = 0; j < matrice1[0].length; j++) {
				                System.out.print(matrice1[i][j] + " ");
				            }
				            System.out.println();
				        }
						
						
						
						int[][] matrice2= (int[][])in.readObject();
						
						//additionner les matrices
						//envoyer la matrice au client 
						out.writeObject(somme(matrice1,matrice2));
						out.flush();
						
						break;
					case "*":
						//lire les matrices
						int[][] matrice3= (int[][])in.readObject();
						int[][] matrice4= (int[][])in.readObject();
						
						//envoyer la matrices au client
						out.writeObject(multiplication(matrice3,matrice4));
						out.flush();
						break;
					case "t":
						//lire la matrice
						int[][] matrice5=(int[][])in.readObject();
						
						
						//faire la transpose et l'envoyer au client
						out.writeObject(transpose(matrice5));
						out.flush();
						break;
					default :break;
						
							}
							
						
						
						
						
						
						}
						
						
						

						

						
					
					
					}
			else {
				//ajouter client
				ajouterUtilisateur(utili.getId(), utili.getPass())	;
				System.out.println("le client est ajoute");
				
				
				
			}
					
					
					
					
					
					
					
					
				
				
				
			
				
			
			
			
			
		}catch(Exception e) {

			System.err.println("Erreur:"+e);

	}}

}
