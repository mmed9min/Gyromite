package modele.deplacements;

import modele.plateau.Pilier;
import modele.plateau.Entite;
import modele.plateau.EntiteDynamique;


public class PilierDeplacementHoriz extends RealisateurDeDeplacement {
    private Direction currentDirection;

    private static PilierDeplacement c3D;

    public static PilierDeplacement getInstance() {
        if (c3D == null) {
            c3D = new PilierDeplacement();
        }
        return c3D;
    }

    public void setCurrentDirection(Direction _currentDirection) {
        currentDirection = _currentDirection;
    }

    public boolean realiserDeplacement() {
        boolean ret = false;
        for (EntiteDynamique e : lstEntitesDynamiques) {
            if (currentDirection != null)
                switch (currentDirection) {
                    case droite:  Entite eDroite = e.regarderDansLaDirection(Direction.droite);
                        if (eDroite == null || eDroite instanceof Pilier){
                            if (e.avancerDirectionChoisie(Direction.droite)){
                                ret = true;
                            }break;}
                    case gauche:
                        Entite eGauche = e.regarderDansLaDirection(Direction.gauche);
                        if (eGauche == null || eGauche.peutEtreEcrase()){
                            if(e.avancerDirectionChoisie(Direction.gauche)) {
                                ret = true;
                            } break;
                        }}
        }

        return ret;

    }

    public void resetDirection() {
        currentDirection = null;
    }
}
