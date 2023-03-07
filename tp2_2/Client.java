package tp2_2;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.*;

import tp2_1.utilisateur;

public class Client {
		static boolean bool=false ;

public static void main(String argv[]) {


		try {

			InetAddress adresse= InetAddress.getByName("localhost");
			Socket socket = new Socket(adresse,1629);
			
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

			Scanner scanner = new Scanner(System.in);
		    System.out.println("Entrez id : ");
		    String id = scanner.nextLine();
		    System.out.println("Entrez pass : ");
		    String pass = scanner.nextLine();
		    
		    utilisateur utili = new utilisateur(id,pass);

		    out.writeObject(utili);
		    out.flush();
		    
		    String test = (String) in.readObject();
		    System.out.println(test);
		    
		    String rep1 = (String) in.readObject();
		    System.out.println(rep1);

		    String op = scanner.nextLine();
		    System.out.println(op);

		    out.writeObject(op);

		    out.flush();
		    
		    switch(op) {
		    case "+":
		    	while (bool==false ) {
		    	System.out.println("entrez le nombre de lignes de la premiere  matrice");

		    	int ligne1 = scanner.nextInt();	
		   
		    	System.out.println("entrez le nombre de colones de la premiere matrice");
		    	int colone1 = scanner.nextInt();
		    	
		    	System.out.println("entrez le nombre de lignes de la deuxieme matrice");

		    	int ligne2 = scanner.nextInt();
		    	System.out.println("entrez le nombre de colones de la deuxieme matrice");
		    	 int colone2 = scanner.nextInt();
		    	 
		    	 if(ligne1!=ligne2 && colone1!=colone2) {
		    		        System.out.println("veillez entrer le nombre de lignes et colones identique a celle de la 1ere matrice");
		    		        System.out.println("entrez le nombre de lignes de la deuxieme matrice");
							
		    		        ligne2 = scanner.nextInt();
							System.out.println("entrez le nombre de colones de la deuxieme matrice");
							
						    colone2 = scanner.nextInt();	
						}
						else {
							bool =true;
		  //remplir la matrice aleatoirement (on peut demander a l'utilisateur de les remplir mais on a prefere le faire aleatoirement)
							int[][] matrice3 = new int[ligne1][colone1];
							int[][] matrice4 = new int[ligne1][colone1];
							Random random = new Random();
							for (int i = 0; i < ligne1; i++) {
					            for (int j = 0; j < colone1; j++) {
					                matrice3[i][j] = random.nextInt(10); 
					                matrice4[i][j] = random.nextInt(10); 
					            }
					        }
		// les afficher au client
							System.out.println("la premiere matrice : \n");

							for (int i = 0; i < ligne1; i++) {
					            for (int j = 0; j < colone1; j++) {
					                System.out.print(matrice3[i][j] + " ");
					            }
					            System.out.println();
					        }
							
							System.out.println("la deuxieme matrice : \n");

							for (int i = 0; i < ligne2; i++) {
					            for (int j = 0; j < colone2; j++) {
					                System.out.print(matrice4[i][j] + " ");
					            }
					            System.out.println();
					        }
							
			//envoyer les matrices au serveur
						out.writeObject(matrice3);
						out.flush();
						out.writeObject(matrice4);
						out.flush();
			//recevoir resultat
						int[][]result1=(int[][])in.readObject();
						
						System.out.println("la somme des matrices est : \n");

						for (int i = 0; i < ligne1; i++) {
				            for (int j = 0; j < colone1; j++) {
				                System.out.print(result1[i][j] + " ");
				            }
				            System.out.println();
				        }
						
		    
		    	}
		    		
		    	}break;
		    case "*":
		    	while (bool==false ) {
		    		System.out.println("entrez le nombre de lignes de la premiere  matrice");
			    	int ligne1 = scanner.nextInt();	
			    	System.out.println("entrez le nombre de colones de la premiere  matrice");
			    	int colone1 = scanner.nextInt();
			    	System.out.println("entrez le nombre de lignes de la deuxieme  matrice");
			    	int ligne2 = scanner.nextInt();
			    	System.out.println("entrez le nombre de colones de la premiere  matrice");
			    	 int colone2 = scanner.nextInt();
			    	 
			    	 if(ligne1!=colone2 && colone1!=ligne2) {
			    		        System.out.println("il faut que le nombre de colones de la premiere matrice soit egale au nombre de lignes de la deuxieme matrice "
			    		        		+ "et le nombre de lignes de la premiere  matrice soit identique a nombre de colones de la deuxieme matrice\n  ");
			    		        System.out.println("entrez le nombre de lignes de la deuxieme matrice");
								
								ligne2 =(int)in.readObject();
								System.out.println("entrez le nombre de colones de la deuxieme matrice");
								
							    colone2 =(int)in.readObject();	
							}
							else {
								bool =true;
			  //remplir la matrice aleatoirement (on peut demander a l'utilisateur de les remplir mais on a prefere le faire aleatoirement)
								int[][] matrice1 = new int[ligne1][colone1];
								int[][] matrice2 = new int[ligne2][colone2];
								Random random = new Random();
								for (int i = 0; i < ligne1; i++) {
						            for (int j = 0; j < colone1; j++) {
						                matrice1[i][j] = random.nextInt(10); 
						               
						            }
						        }
								for (int i = 0; i < ligne2; i++) {
						            for (int j = 0; j < colone2; j++) {
						                 
						                matrice2[i][j] = random.nextInt(10); 
						            }
						        }
			// les afficher au client
								System.out.println("la premiere matrice : \n");

								for (int i = 0; i < ligne1; i++) {
						            for (int j = 0; j < colone1; j++) {
						                System.out.print(matrice1[i][j] + " ");
						            }
						            System.out.println();
						        }
								
								System.out.println("la deuxieme matrice : \n");

								for (int i = 0; i < ligne2; i++) {
						            for (int j = 0; j< colone2; j++) {
						                System.out.print(matrice2[i][j] + " ");
						            }
						            System.out.println();
						        }
								
				//envoyer les matrices au serveur
							out.writeObject(matrice1);
							out.flush();
							out.writeObject(matrice2);
							out.flush();
							
							//recevoir resultat
							int[][]result2=(int[][])in.readObject();
							
							System.out.println("le produit des matrices est : \n");

							for (int i = 0; i < ligne1; i++) {
					            for (int j = 0; j < colone2; j++) {
					                System.out.print(result2[i][j] + " ");
					            }
					            System.out.println();
					        }
							
			    
			    	}
			    		

		    	
		    
		    
		    }
		    case "t":
		    	System.out.println("entrez le nombre de lignes de la matrice");
		    	int ligne = scanner.nextInt();	
		    	System.out.println("entrez le nombre de colones de la matrice");
		    	int colone = scanner.nextInt();
		    	int[][] matrice = new int[ligne][colone];
		    	Random random = new Random();
				
		    	//remplir la matrice 
		    	for (int i = 0; i < ligne; i++) {
		            for (int j = 0; j < colone; j++) {
		                matrice[i][j] = random.nextInt(10); 
		               
		            }
		        }
		    	// afficher au client
		    	for (int i = 0; i < ligne; i++) {
		            for (int j = 0; j < colone; j++) {
		                System.out.print(matrice[i][j] + " ");
		            }
		            System.out.println();
		        }
		    	//envoyer la matrice
		    	out.writeObject(matrice);
				out.flush();
				// recevoir resultat
				int[][]result3=(int[][])in.readObject();
				//afficher resultat
				System.out.println("la transpose des matrices est : \n");

				for (int i = 0; i < result3.length; i++) {
		            for (int j = 0; j < result3[0].length; j++) {
		                System.out.print(result3[i][j] + " ");
		            }
		            System.out.println();
		        }
				break;
				default: break;
		    } 
		    
		    
						
			
			
		}
		catch (Exception e) {
			System.err.println("Erreur :"+e);
		}
	}

}
