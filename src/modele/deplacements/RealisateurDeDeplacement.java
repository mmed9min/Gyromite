package modele.deplacements;

import modele.plateau.EntiteDynamique;

import java.util.ArrayList;


public abstract class RealisateurDeDeplacement {
    protected ArrayList<EntiteDynamique> lstEntitesDynamiques = new ArrayList<EntiteDynamique>();
    protected ArrayList<EntiteDynamique> lstEntitesDynamiques3 = new ArrayList<EntiteDynamique>();
    protected abstract boolean realiserDeplacement();

    public void addEntiteDynamique(EntiteDynamique ed)
    {
        lstEntitesDynamiques.add(ed);
    };
    public void addEntiteDynamique2(EntiteDynamique ed)
    {
        lstEntitesDynamiques3.add(ed);
    };


}
