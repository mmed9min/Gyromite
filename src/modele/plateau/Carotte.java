package modele.plateau;

public class Carotte extends EntiteStatique {
    public Carotte(Jeu _jeu) { super(_jeu); }
    
 
        public boolean estRamassable(){return true;};
        public boolean estDeSupport(){return false;}

}
