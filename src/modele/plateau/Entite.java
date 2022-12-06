/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.plateau;

public abstract class Entite {
    protected Jeu jeu;
    boolean surcorde =false;
    
    public Entite(Jeu _jeu) {
        jeu = _jeu;
    }
    
    
    public abstract boolean peutEtreEcrase(); // l'entité peut être écrasée (par exemple par une colonne ...)
    public abstract boolean estDeSupport(); // permet de stopper la gravité, prendre appui pour sauter
    public abstract boolean permettreDeMonterDescendre(); // si utilisation de corde (attention, l'environnement ne peut pour l'instant sotker qu'une entité par case (si corde : 2 nécessaires), améliorations à prévoir)
    public abstract boolean estRamassable();
    

}
