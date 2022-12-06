package modele.plateau;


public abstract class EntiteStatique extends Entite {
    public EntiteStatique(Jeu _jeu) {
        super(_jeu);
    }

    public boolean peutEtreEcrase() { return false; }
    public boolean estDeSupport() { return true; }
    public boolean permettreDeMonterDescendre() { return false; };
    public boolean estRamassable(){return false;};
}