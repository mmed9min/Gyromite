package modele.plateau;

public class Bombe extends EntiteStatique {
    public Bombe(Jeu _jeu) { super(_jeu); }
    public boolean estDeSupport() { return false; }
        //public boolean peutEtreEcrase() { return true; }
         public boolean estRamassable(){return true;};


}
