package modele.plateau;

public class Pilier extends EntiteDynamique {
    public Pilier(Jeu _jeu) { super(_jeu); }

    public boolean peutEtreEcrase() { return false; }
    public boolean estDeSupport() { return true; }
    public boolean estRamassable(){return false;};
    public boolean permettreDeMonterDescendre() { return false; };
}
