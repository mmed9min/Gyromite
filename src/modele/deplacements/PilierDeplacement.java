package modele.deplacements;

import modele.plateau.Pilier;
import modele.plateau.Entite;
import modele.plateau.EntiteDynamique;


public class PilierDeplacement extends RealisateurDeDeplacement {
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
                    case haut:  Entite eHaut = e.regarderDansLaDirection(Direction.haut);
                        if (eHaut == null || eHaut instanceof Pilier){
                        if (e.avancerDirectionChoisie(Direction.haut)){
                                ret = true;
                        }break;}
                    case bas:
                       Entite eBas = e.regarderDansLaDirection(Direction.bas);
            if (eBas == null || eBas.peutEtreEcrase()){
                            if(e.avancerDirectionChoisie(Direction.bas)) {
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
