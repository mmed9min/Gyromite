package VueControleur;
import modele.plateau.Compteur;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import modele.deplacements.PilierDeplacement;

import modele.deplacements.Controle4Directions;
import modele.deplacements.Direction;
import modele.plateau.*;



public class VueControleurGyromite extends JFrame implements Observer {
    
     
    private Jeu jeu;


    private final int sizeX; 
    private final int sizeY;

    // icones affichées dans la grille
    private ImageIcon icoHero;
    private ImageIcon icoVictoire;
    private ImageIcon icoPerdu;
    private ImageIcon icoHeroSurCorde;
    private ImageIcon icoBombe;
    private ImageIcon icoCarotte;
    private ImageIcon icoVide;
    private ImageIcon icoMur;
    private ImageIcon icoHorizonSol;
    private ImageIcon icoVerticalSol;
    private ImageIcon icoPilier;
    private ImageIcon icoCorde;
    private ImageIcon icoEnnemi;
    private Image Fond;
    private Compteur compteur;
    //private Graphics g;
    
    private JLabel[][] tabJLabel; // cases graphique (au moment du rafraichissement, chaque case va être associée à une icône, suivant ce qui est présent dans le modèle)

 
    public VueControleurGyromite(Jeu _jeu) {
        sizeX = jeu.SIZE_X;
        sizeY = _jeu.SIZE_Y;
        jeu = _jeu;

        chargeIcons();
        placerComposants();
        eventsClavier();
    }

    private void eventsClavier() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode()) {
                    case KeyEvent.VK_DOWN : Controle4Directions.getInstance().setDirectionCourante(Direction.bas); break;
                    case KeyEvent.VK_UP : Controle4Directions.getInstance().setDirectionCourante(Direction.haut); break;
                    case KeyEvent.VK_LEFT : Controle4Directions.getInstance().setDirectionCourante(Direction.gauche); break;
                    case KeyEvent.VK_RIGHT : Controle4Directions.getInstance().setDirectionCourante(Direction.droite); break;
                    case KeyEvent.VK_C : PilierDeplacement.getInstance().setCurrentDirection(Direction.bas); break;
                    case KeyEvent.VK_X : PilierDeplacement.getInstance().setCurrentDirection(Direction.haut); break;

                }
            }
        });
    }


    private void chargeIcons() {
        icoHero = chargeIcon("Images/Hector.png");
        icoVictoire = chargeIcon("Images/Victoire.png");
        icoPerdu = chargeIcon("Images/Perdu.png");
        icoHeroSurCorde = chargeIcon("Images/HeroSurCorde.png");
        icoVide = chargeIcon("Images/Vide.png");
        icoPilier = chargeIcon("Images/Colonne.png");
        icoMur = chargeIcon("Images/Mur.png");
        icoHorizonSol = chargeIcon("Images/Sol.png");
        icoVerticalSol = chargeIcon("Images/SolVertical.png");
        icoBombe = chargeIcon("Images/Bombe.png");
        icoCarotte = chargeIcon("Images/Radis.png");
        icoCorde = chargeIcon("Images/Corde.png");
        icoEnnemi = chargeIcon("Images/Smick.png");
    }

    private ImageIcon chargeIcon(String urlIcone) {
        BufferedImage image = null;

        try {
            image = ImageIO.read(new File(urlIcone));
        } catch (IOException ex) {
            Logger.getLogger(VueControleurGyromite.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

        return new ImageIcon(image);
    }

    private void placerComposants() {
        setTitle("Gyromite");
        setSize(1800, 1000);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // permet de terminer l'application à la fermeture de la fenêtre

        JComponent grilleJLabels = new JPanel(new GridLayout(sizeY, sizeX)); // grilleJLabels va contenir les cases graphiques et les positionner sous la forme d'une grille
        tabJLabel = new JLabel[sizeX][sizeY];

        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                JLabel jlab = new JLabel();
                tabJLabel[x][y] = jlab; // on conserve les cases graphiques dans tabJLabel pour avoir un accès pratique à celles-ci (voir mettreAJourAffichage() )
                grilleJLabels.add(jlab);
            }
        }
        
      
        add(grilleJLabels);
 
        
       
        
        compteur = new Compteur();
       
    }
    

    
    /**
     * Il y a une grille du côté du modèle ( jeu.getGrille() ) et une grille du côté de la vue (tabJLabel)
     */
    private void updateAffichage() {

        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
              
                 if (jeu.getGrille()[x][y] instanceof Heros) { // si la grille du modèle contient un Pacman, on associe l'icône Pacman du côté de la vue
                    // System.out.println("Héros !");
                    if(jeu.statut=="VICTOIRE")
                    {tabJLabel[x][y].setIcon(icoVictoire);}
                    else if(jeu.statut=="Perdu")
                        {tabJLabel[x][y].setIcon(icoPerdu);}
                       else if(jeu.HeroSurCorde)
                        {tabJLabel[x][y].setIcon(icoHeroSurCorde);}
                    else
                    tabJLabel[x][y].setIcon(icoHero);
                } else if (jeu.getGrille()[x][y] instanceof Mur) {
                    tabJLabel[x][y].setIcon(icoMur);
                } else if (jeu.getGrille()[x][y] instanceof Pilier) {
                    tabJLabel[x][y].setIcon(icoPilier);
                } else if (jeu.getGrille()[x][y] instanceof HorizonSol) {
                    tabJLabel[x][y].setIcon(icoHorizonSol);}
                  else if (jeu.getGrille()[x][y] instanceof VerticalSol) {
                    tabJLabel[x][y].setIcon(icoVerticalSol);}
                 else if (jeu.getGrille()[x][y] instanceof Bombe){ 
                    tabJLabel[x][y].setIcon(icoBombe);
                 }
                   else if (jeu.getGrille()[x][y] instanceof Corde){ 
                    tabJLabel[x][y].setIcon(icoCorde);
                 }
                 else if (jeu.getGrille()[x][y] instanceof Carotte){
                    tabJLabel[x][y].setIcon(icoCarotte);
                 }
                 else if (jeu.getGrille()[x][y] instanceof Bot){
                    tabJLabel[x][y].setIcon(icoEnnemi);
                 }
                 else if (x==0 && y==0) {
                     String timer = this.compteur.getStr();
                     
               
                     tabJLabel[0][0].setText(timer);
                     tabJLabel[0][0].setForeground(Color.blue);
                                           
                 }
                   else if (x==1 && y==0) {
                    
               
                     tabJLabel[1][0].setText("Bombes :");
                      tabJLabel[1][0].setForeground(Color.blue);
                                           
                 }
                  else if (x==2 && y==0) {
                     int timer1 = jeu.compteur_bombes;
                     
               
                     tabJLabel[2][0].setText(String.valueOf(timer1));
                      tabJLabel[2][0].setForeground(Color.blue);
                                           
                 }
                    else if (x==3 && y==0) {
                     
                     
               
                     tabJLabel[3][0].setText(jeu.score);
                      tabJLabel[3][0].setForeground(Color.blue);
                                           
                 }    else if (x==4 && y==0) {
                     
                     
               
                     tabJLabel[4][0].setText(jeu.statut);
                     tabJLabel[4][0].setForeground(Color.green);
                                           
                 }
                else {
                    tabJLabel[x][y].setIcon(icoVide);
                }    
         }       
    }
  
    
    }
 

    @Override
    public void update(Observable o, Object arg) {
        //print
        //System.out.print(tabJLabel[6][9]);
                //System.out.println(this.compteARebours.getStr());
    if(jeu.encours)
    {
       
        
        updateAffichage();
        if (jeu.statut=="Perdu")
        {System.out.println("PERDU LOL");
            updateAffichage();
            jeu.encours=false;}
        if(jeu.compteur_bombes >=4 || this.compteur.getCompteurDuTemps()==0)
        {System.out.println("JEU FINI");
        jeu.scorechiffre=jeu.scorechiffre+(this.compteur.getCompteurDuTemps()*10);
        jeu.score="Score " + jeu.scorechiffre;
       
         jeu.statut="VICTOIRE";
        updateAffichage();
        jeu.encours=false;}
        
        if(this.compteur.getCompteurDuTemps()==0)
        {
            System.out.println("JEU FINI");
        jeu.scorechiffre=jeu.scorechiffre+(this.compteur.getCompteurDuTemps()*10);
        jeu.score="Score " + jeu.scorechiffre;
        jeu.statut="Perdu";
        updateAffichage();
        jeu.encours=false;
        }
        
           
    }
    
    
       /*
        SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        mettreAJourAffichage();
                    }
                }); 
        */

    }


}
;