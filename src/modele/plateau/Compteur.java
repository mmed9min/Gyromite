package modele.plateau;
public class Compteur implements Runnable{

	// VARIABLES
	public final int PAUSE = 1000;	
	public int compteurDuTemps;
	public String str;
	
	
	// CONSTRUCTEUR
	public Compteur(){

		this.compteurDuTemps = 150;
		this.str = "TIME: 100";
		
		Thread compteur = new Thread(this);
		compteur.start();
	}

	
	// GETTERS	
	public int getCompteurDuTemps() {return compteurDuTemps;}
	
    public String getStr() {return str;}

	

	@Override
	public void run() {		
		
		while(true){ 										
		    try{Thread.sleep(PAUSE);}
			catch (InterruptedException e){}
			this.compteurDuTemps--;
			this.str = "TIME: " + this.compteurDuTemps;
		}		
	} 
	
}
