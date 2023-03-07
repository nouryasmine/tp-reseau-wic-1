package tp2_1;

public class utilisateur implements java.io.Serializable{

  
	

	public String id;
	public String pass;
	public utilisateur(String nom,String pass) {
		this.id=nom;
		this.pass=pass;
	     
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	
}

