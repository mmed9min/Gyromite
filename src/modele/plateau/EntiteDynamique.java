package modele.plateau;

import modele.deplacements.Direction;


public abstract class EntiteDynamique extends Entite {
    public EntiteDynamique(Jeu _jeu) { super(_jeu); }

    public boolean estRamassable(){return false;};
    public boolean vivant=true;

    public boolean avancerDirectionChoisie(Direction d) {
        return jeu.deplacerEntite(this, d);
    }
    public Entite regarderDansLaDirection(Direction d) {return jeu.regarderDansLaDirection(this, d);}
}
