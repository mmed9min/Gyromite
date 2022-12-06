package modele.plateau;

public class Corde extends EntiteStatique {
    public Corde(Jeu _jeu) { super(_jeu); }
    public boolean estDeSupport() { return true; }

        public boolean permettreDeMonterDescendre() { return true; };



}
