import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//
public  class InterfaceGraphique extends JFrame implements ActionListener {
    private int nbTours=0;
    private JPanel actionBar;
    private JPanel mooveBar;
    private JPanel infoBar;
    private int CoupsJoues;
    private int JoueurJoues;
    private Constantes c = new Constantes();
    private Train t ;
    private InfosBandits infoBandits;


    public InterfaceGraphique()throws Exception{

        super("COLT EXPRESS");
        t = new Train();

        Constantes c = new Constantes();
        this.CoupsJoues = 0;
        this.JoueurJoues = 0;
        Image icon = Toolkit.getDefaultToolkit().getImage("logo.gif");
        this.setIconImage(icon);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE );
        this.setSize(250*c.getNB_Waggons(),400);
        this.setLocationRelativeTo(null);
        JPanel contentPane = (JPanel)this.getContentPane();
        contentPane.setLayout(new BorderLayout());
        this.actionBar = createActionBar();
        contentPane.add(this.actionBar,BorderLayout.NORTH);
        this.mooveBar= createMooveBar();
        contentPane.add(mooveBar,BorderLayout.EAST);
       infoBar = createInfoBar("Selection Actions Bandit 1");
        infoBar.setPreferredSize(new Dimension(20,30));
        contentPane.add(infoBar,BorderLayout.SOUTH);
        contentPane.add(this.t.cont,BorderLayout.CENTER);
        infoBandits= new InfosBandits();
        calculeBouton(c.getBandits(0));
    }

    public void calculeBouton(Bandits b){
      calculMooveBar(b);
      calculActionBar(b);

    }
    public void calculActionBar(Bandits b){

        if(this.JoueurJoues!=c.getNbBandits()-1 ||this.CoupsJoues!=3){

        if(this.CoupsJoues == 0){
            for(int k = 0;k<5;k++){
                this.actionBar.getComponent(k).setVisible(true);
            }
            if(b.getNbMunitions()<=0) this.actionBar.getComponent(0).setVisible(false) ;
        this.actionBar.getComponent(1).setVisible(false);

            this.actionBar.getComponent(4).setVisible(false);
            this.actionBar.getComponent(3).setVisible(false);
            if(Train.getWagon(b.getCurrantW()).getCurrantTresor(b)==0||b.getCurrantE()==1){
                this.actionBar.getComponent(2).setVisible(false);

            }
            else{
                this.actionBar.getComponent(2).setVisible(true);
            }
        }
        if(this.CoupsJoues == 1||this.CoupsJoues ==2){
            if(b.getNbMunitions()<=0) this.actionBar.getComponent(0).setVisible(false) ;
            this.actionBar.getComponent(4).setVisible(true);
            if(Train.getWagon(b.getCurrantW()).getCurrantTresor(b)==0||b.getCurrantE()==1){
                this.actionBar.getComponent(2).setVisible(false);

            }
            else{
                this.actionBar.getComponent(2).setVisible(true);
            }
        }
        if(this.CoupsJoues == 3){

            for(int k = 0;k<5;k++){
                this.actionBar.getComponent(k).setVisible(k==1||k==4);
            }
            if(b.getNbMunitions()<=0) this.actionBar.getComponent(0).setVisible(false) ;

        }
            }
        else{for(int k = 0;k<5;k++){
            this.actionBar.getComponent(k).setVisible(k==1||k==4);
        }
            if(b.getNbMunitions()<=0) this.actionBar.getComponent(0).setVisible(false) ;
        }

       /* if(this.CoupsJoues==31){
            for(int k = 0;k<5;k++){
                this.actionBar.getComponent(k).setVisible(k==3);
            }
        }*/

    }
    public void calculMooveBar(Bandits b){

        for(int k = 0;k<5;k++){
            this.mooveBar.setVisible(true);
            this.mooveBar.getComponent(k).setVisible(true);
        }
        if(b.getCurrantW()==0){
            this.hideComponentMb(3);
        }
        if(b.getCurrantW()==c.getNB_Waggons()){
            this.hideComponentMb(2);
        }
        if(b.getCurrantE()==1){
            this.hideComponentMb(0);
        }
        if(b.getCurrantE()==0){
            this.hideComponentMb(1);
        }
        if(b.getNbMunitions()==0){
            this.hideComponentMb(4);
        }
        if(this.CoupsJoues==3){
            this.mooveBar.setVisible(false);
        }
    }
    public void hideComponentMb(int n){
        this.mooveBar.getComponent(n).setVisible(false);
    }

    private JPanel createActionBar(){
        JPanel j = new JPanel();
        j.setLayout(new FlowLayout());
        JButton tirer = new JButton("TIREZ");
        tirer.addActionListener(this);
        j.add(tirer);
        JButton action = new JButton("ACTION");
        action.addActionListener(this);
        //action.setVisible(false);
        JButton valider = new JButton("VALIDER");
        valider.addActionListener(this);
        //valider.setVisible(false);
        j.add(valider);
        JButton braquer = new JButton("BRAQUER");
        j.add(braquer);
        braquer.addActionListener(this);
        j.add(action);
        JButton annuler = new JButton("ANNULER");
        annuler.addActionListener(this);
        j.add(annuler);
        return j;

    }
    private JPanel createMooveBar(){
        JPanel j = new JPanel();
        j.setLayout(new BorderLayout(0,20));

        JButton monter = new JButton("MONTER");
        monter.setPreferredSize(new Dimension(70,70));
        monter.addActionListener(this);
        //monter.setVisible(false);
        j.add(monter,BorderLayout.NORTH);


        JButton dessendre = new JButton("DESSENDRE");
        dessendre.setPreferredSize(new Dimension(70,70));
        dessendre.addActionListener(this);
        j.add(dessendre,BorderLayout.SOUTH);

        JButton droite = new JButton("A DROITE");
        droite.addActionListener(this);
        j.add(droite,BorderLayout.EAST);

        JButton gauche = new JButton("A GAUCHE");
        gauche.addActionListener(this);
        j.add(gauche,BorderLayout.WEST);

        JButton tir = new JButton("TIREZ");
        tir.addActionListener(this);
        j.add(tir,BorderLayout.CENTER);

        return j;
    }
    private JPanel createInfoBar(String s){
        JPanel j = new JPanel();
        j.setLayout(new FlowLayout());
        JLabel lab = new JLabel(s);
        j.add(lab);
        return j;
    }


    public void actionPerformed(ActionEvent e){

        /*if(this.CoupsJoues ==2) {
            for(int k = 0;k<5;k++){
                this.actionBar.getComponent(k).setVisible(k==1||k==4);
            }
        }*/Bandits band = c.getBandits(JoueurJoues);
        String N = band.getNom();

        int E = band.getEtage();
        if(((JButton)e.getSource()).getText().equals("ANNULER" )){

            CoupsJoues = CoupsJoues -1;
           // System.out.println(CoupsJoues);
            band.setActions(CoupsJoues,null);
            this.calculeBouton(band);

        }

        if(((JButton)e.getSource()).getText().equals("TIREZ" )){



        Tir t = new Tir(CoupsJoues,JoueurJoues,this);
        this.setVisible(false);
        CoupsJoues++;
        updateInfoBar("action " +this.CoupsJoues +": "+ N+"tire ");
            this.calculeBouton(band);
            ;

            }


        if(((JButton)e.getSource()).getText().equals("BRAQUER")){

            if(this.CoupsJoues <3){

            band.setActions(this.CoupsJoues,Direction.Braque);
            CoupsJoues++;
                updateInfoBar("Selection action " +this.CoupsJoues +": "+ N+" braque le waggon n°"+
                        band.getCurrantW());
            }
            this.calculeBouton(band);
        }


        if(((JButton)e.getSource()).getText().equals("A GAUCHE" )){

            if(this.CoupsJoues <3){
                band.setActions(CoupsJoues,Direction.Arriere);

                CoupsJoues++;
                updateInfoBar("Selection action " +this.CoupsJoues +": "+ N+" se deplace waggon n°"+
                        band.getCurrantW()+" etage n°"+band.getCurrantE());}
            this.calculeBouton(band);

        }


        if(((JButton)e.getSource()).getText().equals("A DROITE" )){

            if(this.CoupsJoues <3){
                band.setActions(this.CoupsJoues,Direction.Avant);
                CoupsJoues++;
                updateInfoBar("Selection action " +this.CoupsJoues +": "+ N+" se deplace waggon n°"+
                        band.getCurrantW()+" etage n°"+band.getCurrantE());}
            this.calculeBouton(band);

        }


        if(((JButton)e.getSource()).getText().equals("MONTER" )){


            if(this.CoupsJoues <3){
           band.setActions(this.CoupsJoues,Direction.Haut);
            CoupsJoues++;
                updateInfoBar("Selection action " +this.CoupsJoues +": "+ N+" se deplace waggon n°"+
                        band.getCurrantW()+" etage n°"+band.getCurrantE());}
            this.calculeBouton(band);



        }


        if(((JButton)e.getSource()).getText().equals("ACTION" )){
            String s = "";
            Marshall marsh = c.getMarshall();


             if(nbTours ==0){

                marsh.deplaceM();


            }
                if(nbTours <3){


                    Direction m = marsh.getActions()[nbTours];
                    marsh.deplace(m);
                    s=s+marsh.stringAction()[nbTours];
                    marsh.setActions(nbTours,null);
                    for (int b = 0; b < c.getNbBandits(); b++) {
                        Bandits band2 = c.getBandits(b);
                        int Wband = band2.getWaggon();
                        int Eband2 = band2.getEtage();
                        if(Wband==marsh.getWaggon()&&Eband2==0){
                            marsh.tirer(band2,Direction.Tire);
                            band2.finisTour();
                          //  System.out.println("Marshall tire sur "+c.getBandits(b).getNom());



                        }
                        Direction a = band2.getActions()[nbTours];
                        if(a==Direction.Braque){
                            band2.braque(Train.getWagon(Wband));
                        }

                        if(a==Direction.Tire||a==Direction.TireBas||a==Direction.TireHaut||a==Direction.TireDroite
                        ||a==Direction.TireGauche){
                            band2.setNbMunitions(band2.getNbMunitions()-1);
                           for(int k2 = 0; k2<c.getNbBandits();k2++){
                               Bandits bandK2 = c.getBandits(k2);
                               int Wbandk2 = bandK2.getWaggon();
                               int Ebandk2 = bandK2.getEtage();



                               if(k2!=b && Wbandk2 ==Wband&&a==Direction.TireHaut&&Eband2==0&&Ebandk2==1){
                                   band2.tirer(bandK2,a);
                                   break;
                               }
                               if(k2!=b && Wbandk2 ==Wband&&Eband2==1&& Ebandk2==0&&a==Direction.TireBas){
                                   band2.tirer(bandK2,a);
                                   break;
                               }
                               if(k2!=b && Wbandk2 ==Wband-1&&a==Direction.TireGauche && Ebandk2==Eband2){
                                   //System.out.println("c bon");
                                   band2.tirer(bandK2,a);

                                   break;
                               }
                               if(k2!=b && Wbandk2 ==Wband+1&&a==Direction.TireDroite && Ebandk2==Eband2){
                                   //System.out.println("tireD");
                                   band2.tirer(bandK2,a);
                                   break;
                               }
                               if(k2!=b && Wbandk2 ==Wband&&a==Direction.Tire && Ebandk2==Eband2){
                                   band2.tirer(bandK2,a);
                                   break;
                               }

                           }
                           s=s+band2.getNom()+ "Tire ";
                        }



                        else{
                        band2.deplace(a);
                        s=s+band2.getNom()+"se déplace dans le waggon n°"+band2.getWaggon()+"etage"+band2.getEtage()+" ";}


                    }

                CoupsJoues = 0;
                JoueurJoues=0;
                for(int k = 0; k<c.getNbBandits();k++){


                        c.getBandits(k).setActions(nbTours,null);

                }
                t.createTresature();
            }
            nbTours++;

            try{
            t.updateLabel();}
            catch(Exception exception){

            }

            this.updateInfoBar(s);

            this.repaint();
            this.revalidate();
if(nbTours>=3){nbTours =0;
this.mooveBar.setVisible(true);
    this.calculeBouton(c.getBandits(JoueurJoues));
}
infoBandits.createLabel();
if(this.t.getNbTresor()==0){
    mooveBar.setVisible(false);
    actionBar.setVisible(false);
    updateInfoBar(Bandits.getPlusRiche()+"Vainqueur");

}


        }


        if(((JButton)e.getSource()).getText().equals("DESSENDRE" )){

            if(this.CoupsJoues <3){
                c.getBandits(JoueurJoues).setActions(this.CoupsJoues,Direction.Bas);
                CoupsJoues++;
                updateInfoBar("Selection action n°" +this.CoupsJoues +": "+ c.getBandits(JoueurJoues).getNom()+" se deplace waggon n°"+
                        c.getBandits(JoueurJoues).getCurrantW()+" etage n°"+c.getBandits(JoueurJoues).getCurrantE());}
            this.calculeBouton(c.getBandits(JoueurJoues));
        }
        if(((JButton)e.getSource()).getText().equals("VALIDER")){
            if(this.JoueurJoues<c.getNbBandits()-1){
                JoueurJoues++;
                this.CoupsJoues=0;
                this.calculeBouton(c.getBandits(JoueurJoues));
                updateInfoBar("Selection Action "+c.getBandits(JoueurJoues).getNom());
               /* for(int k = 0;k<5;k++){
                    this.actionBar.getComponent(k).setVisible(k!=1||k!=4);
                }*/
            }
            else{
           // this.actionBar.getComponent(3).setVisible(true);
            for(int k = 0; k<5;k++){
                this.actionBar.getComponent(k).setVisible(k==3);
                this.mooveBar.setVisible(false);
            }
            updateInfoBar("PHASE D'ACTIONS!");}


        }



    }
    public void updateInfoBar(String s){
        this.infoBar.removeAll();
        JLabel label = new JLabel(s);
        //System.out.println(s);
        this.infoBar.add(label);
        this.revalidate();
        this.repaint();


    }





    }



