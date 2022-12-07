package modele.deplacements;

import modele.plateau.Pilier;
import modele.plateau.Entite;
import modele.plateau.EntiteDynamique;

import java.util.ArrayList;
import java.util.Collections;


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
        ArrayList<EntiteDynamique> lstEntitesDynamiques2 = new ArrayList<EntiteDynamique>();
        lstEntitesDynamiques2.addAll(lstEntitesDynamiques) ;
        ArrayList<EntiteDynamique> lstEntitesDynamiques4 = new ArrayList<EntiteDynamique>();
        lstEntitesDynamiques4.addAll(lstEntitesDynamiques3) ;
        boolean ret = false;
        if(currentDirection !=null && currentDirection.equals(Direction.haut)){
            Collections.reverse(lstEntitesDynamiques2);
            lstEntitesDynamiques.addAll(lstEntitesDynamiques2) ;
        }
        if(currentDirection !=null && currentDirection.equals(Direction.droite)){
            Collections.reverse(lstEntitesDynamiques4);
            lstEntitesDynamiques3.addAll(lstEntitesDynamiques4) ;
        }
        for (EntiteDynamique e : lstEntitesDynamiques) {
            if (currentDirection != null)
                switch (currentDirection) {
                    case haut:  Entite eHaut = e.regarderDansLaDirection(Direction.haut);
                        if (true){
                        if (e.avancerDirectionChoisie(Direction.haut)){
                                ret = true;
                        }break;}
                    case bas:
                       Entite eBas = e.regarderDansLaDirection(Direction.bas);
            if (true){
                            if(e.avancerDirectionChoisie(Direction.bas)) {
                                ret = true;
                            } break;
                }

            }
        }
        for (EntiteDynamique e : lstEntitesDynamiques3) {
            if (currentDirection != null)
                switch (currentDirection) {
                    case droite:  Entite eHaut = e.regarderDansLaDirection(Direction.droite);
                        if (true){
                            if (e.avancerDirectionChoisie(Direction.droite)){
                                ret = true;
                            }break;}
                    case gauche:
                        Entite eBas = e.regarderDansLaDirection(Direction.gauche);
                        if (true){
                            if(e.avancerDirectionChoisie(Direction.gauche)) {
                                ret = true;
                            } break;
                        }

                }
        }

        return ret;

    }

    public void resetDirection() {
        currentDirection = null;
    }
}
