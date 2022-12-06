package modele.deplacements;

import modele.plateau.Entite;
import modele.plateau.EntiteDynamique;

public class IA extends RealisateurDeDeplacement {
     private Direction directionCourante;

    private static IA c3d;

    public static IA getInstance() {
        if (c3d == null) {
            c3d = new IA();
        }
        return c3d;
    }

    public void setDirectionCourante() {
       double rand = 0 + (Math.random() * (6));

        if(rand <1 ) directionCourante = Direction.bas;
        else if(rand < 2) directionCourante = Direction.haut;
        else if(rand < 4) directionCourante = Direction.gauche;
        else if(rand < 6) directionCourante = Direction.droite;

    }

    public boolean realiserDeplacement() {
       boolean ret = false;
        if(directionCourante == null) {
        
        setDirectionCourante();} 
        
        for (EntiteDynamique e : lstEntitesDynamiques) {
            if(e.vivant)
            {
            switch (directionCourante) {
                case haut:
                    Entite eHaut = e.regarderDansLaDirection(Direction.haut);
                if(eHaut ==null || eHaut.permettreDeMonterDescendre()){
                    if( (e.regarderDansLaDirection(Direction.haut) != null && e.regarderDansLaDirection(Direction.haut).estDeSupport()))
                    {
                        if (e.avancerDirectionChoisie(directionCourante))
                            ret = true;
                    }
                } 
                break;

                case bas: Entite eBas = e.regarderDansLaDirection(Direction.bas);
                        if (eBas == null|| eBas.permettreDeMonterDescendre()) {
                            if (e.avancerDirectionChoisie(Direction.bas)){
                        ret = true;}
                        }   break;
                case gauche:
                    Entite eGauche = e.regarderDansLaDirection(Direction.gauche);
                        if (eGauche == null || eGauche.permettreDeMonterDescendre()) {
                            if (e.avancerDirectionChoisie(Direction.gauche)){
                        ret = true;}
                         else {e.avancerDirectionChoisie(Direction.droite);}}
            break;
                        
                case droite:
                   Entite eDroite = e.regarderDansLaDirection(Direction.droite);
                        if (eDroite == null  || eDroite.permettreDeMonterDescendre()) {
                            if (e.avancerDirectionChoisie(Direction.droite)){
                        ret = true;}
                          else {e.avancerDirectionChoisie(Direction.gauche);}}
                        break;
            }}
        }
        return ret;}
    

    public void resetDirection() {
        directionCourante = null;
    }
  
    // fonction deplacement / Collision 
}
