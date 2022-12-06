/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.plateau;

import java.util.Random;


public class Bot extends EntiteDynamique {
    protected Random r = new Random();
    

    public Bot(Jeu _jeu) {
        super(_jeu);
    }
    
    public boolean peutEtreEcrase() { return true; }
    public boolean estDeSupport() { return true; }
    public boolean permettreDeMonterDescendre() { return false; };
    public boolean estRamassable(){return false;};
}
