package VueControleur;
import modele.plateau.Compteur;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.*;
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
    private ImageIcon iconHero;
    private ImageIcon iconVictoire;
    private ImageIcon iconPerdu;
    private ImageIcon iconHeroSurCorde;
    private ImageIcon iconBombe;
    private ImageIcon iconCarotte;
    private ImageIcon iconVide;
    private ImageIcon iconMur;
    private ImageIcon iconHorizonSol;
    private ImageIcon iconVerticalSol;
    private ImageIcon iconPilier;
    private ImageIcon iconCorde;
    private ImageIcon iconEnnemi;
    private Image Fond;
    private Compteur compteur;
    //private Graphics g;
    
    private JLabel[][] tabJLabel; // cases graphique (au moment du rafraichissement, chaque case va être associée à une icône, suivant ce qui est présent dans le modèle)
    File file = new File("score.txt");
 
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
                    case KeyEvent.VK_H: PilierDeplacement.getInstance().setCurrentDirection(Direction.droite); break;
                    case KeyEvent.VK_A: PilierDeplacement.getInstance().setCurrentDirection(Direction.gauche); break;


                }
            }
        });
    }


    private void chargeIcons() {
        iconHero = chargeIcon("Images/Hector.png");
        iconVictoire = chargeIcon("Images/Victoire.png");
        iconPerdu = chargeIcon("Images/Perdu.png");

        iconVide = chargeIcon("Images/Vide.png");
        iconMur = chargeIcon("Images/Mur.png");
        iconHorizonSol = chargeIcon("Images/Sol.png");
        iconVerticalSol = chargeIcon("Images/SolVertical.png");
        iconBombe = chargeIcon("Images/Bombe.png");
        iconCarotte = chargeIcon("Images/Radis.png");
        iconCorde = chargeIcon("Images/Corde.png");
        iconEnnemi = chargeIcon("Images/Smick.png");
        iconHeroSurCorde = chargeIcon("Images/HeroSurCorde.png");

        iconPilier = chargeIcon("Images/Colonne.png");

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

        int highScore = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null)                 // read the score file line by line
            {
                try {
                    int score = Integer.parseInt(line.trim());   // parse each line as an int
                    if (score > highScore)                       // and keep track of the largest
                    {
                        highScore = score;
                        jeu.score = "score "+highScore;
                        jeu.highestScore = highScore;
                    }
                } catch (NumberFormatException e1) {
                    // ignore invalid scores
                    System.out.println(e1);
                    //System.err.println("ignoring invalid score: " + line);
                }
                line = reader.readLine();
            }
            reader.close();

        } catch (IOException ex) {
            System.err.println("ERROR reading scores from file");
        }
       
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


    private void updateAffichage() {

        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
              
                 if (jeu.getGrille()[x][y] instanceof Heros) { // si la grille du modèle contient un Pacman, on associe l'icône Pacman du côté de la vue
                    // System.out.println("Héros !");
                    if(jeu.statut=="Victoire")
                    {tabJLabel[x][y].setIcon(iconVictoire);}
                    else if(jeu.statut=="Jeu Perdu !!")
                        {tabJLabel[x][y].setIcon(iconPerdu);}
                       else if(jeu.HeroSurCorde)
                        {tabJLabel[x][y].setIcon(iconHeroSurCorde);}
                    else
                    tabJLabel[x][y].setIcon(iconHero);
                } else if (jeu.getGrille()[x][y] instanceof Mur) {
                    tabJLabel[x][y].setIcon(iconMur);
                } else if (jeu.getGrille()[x][y] instanceof Pilier) {
                    tabJLabel[x][y].setIcon(iconPilier);
                } else if (jeu.getGrille()[x][y] instanceof HorizonSol) {
                    tabJLabel[x][y].setIcon(iconHorizonSol);}
                  else if (jeu.getGrille()[x][y] instanceof VerticalSol) {
                    tabJLabel[x][y].setIcon(iconVerticalSol);}
                 else if (jeu.getGrille()[x][y] instanceof Bombe){ 
                    tabJLabel[x][y].setIcon(iconBombe);
                 }
                   else if (jeu.getGrille()[x][y] instanceof Corde){ 
                    tabJLabel[x][y].setIcon(iconCorde);
                 }
                 else if (jeu.getGrille()[x][y] instanceof Carotte){
                    tabJLabel[x][y].setIcon(iconCarotte);
                 }
                 else if (jeu.getGrille()[x][y] instanceof Bot){
                    tabJLabel[x][y].setIcon(iconEnnemi);
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



                     tabJLabel[3][0].setText("Score "+jeu.NbrScore);
                      tabJLabel[3][0].setForeground(Color.blue);
                                           
                 }
                    else if (x==4 && y==0) {
                     
                     
               
                     tabJLabel[4][0].setText(jeu.statut);
                     tabJLabel[4][0].setForeground(Color.green);
                                           
                 }    else if (x==5 && y==0) {



                     tabJLabel[5][0].setText("Best "+jeu.highestScore);
                     tabJLabel[5][0].setForeground(Color.blue);

                 }

                else {
                    tabJLabel[x][y].setIcon(iconVide);
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
        if (jeu.statut=="Jeu Perdu !!")
        {
            updateAffichage();
            jeu.encours=false;}
        if(jeu.compteur_bombes >=4 || this.compteur.getCompteurDuTemps()==0)
        {System.out.println("Jeu Fini !!");
        jeu.scorechiffre=jeu.scorechiffre+(this.compteur.getCompteurDuTemps()*10);

       
         jeu.statut="Victoire !";
        updateAffichage();
        jeu.encours=false;}



        if(this.compteur.getCompteurDuTemps()==0)
        {

        jeu.scorechiffre=jeu.scorechiffre+(this.compteur.getCompteurDuTemps()*10);

        jeu.statut="Jeu Perdu!!";
        updateAffichage();
        jeu.encours=false;


        }

        if (jeu.NbrScore > jeu.highestScore)
        {
            System.out.println("You now have the new high score! The previous high score was " + jeu.highestScore);
            try {
                BufferedWriter output = new BufferedWriter(new FileWriter(file, true));
                output.newLine();
                output.append("" + jeu.NbrScore);
                output.close();

            } catch (IOException ex1) {
                System.out.printf("ERROR writing score to file: %s\n", ex1);
            }
        }
        } else if (jeu.NbrScore == jeu.highestScore) {
            System.out.println("You tied the high score!");
        } else {
            System.out.println("The all time high score was " + jeu.highestScore);
        }

    }




    
    


    }



;