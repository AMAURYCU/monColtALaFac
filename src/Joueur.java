import java.util.ArrayList;

public abstract class Joueur {
    protected String nom;
    private int indice;
    private int waggon;
    private int etage;

    public static int joueursCrees= 0;
    private Direction[] actions;
    Constantes c;
    public int getCurrantW(){
        int cW =(this.getWaggon());
        for(int k = 0; k<3;k++){
            if(actions[k]==Direction.Arriere){
                cW --;//
            }
            if(actions[k]==Direction.Avant){
                cW++;
            }
        }
        return cW;
    }
    public int getCurrantE(){
        int cE =(this.getEtage());
        for(int k = 0; k<3;k++){
            if(actions[k]==Direction.Haut){
                cE ++;
            }
            if(actions[k]==Direction.Bas){
                cE--;
            }

        }
        return cE;
    }

    public Joueur(){
        c = new Constantes();
        this.actions = new Direction[3];
        if(joueursCrees==0){

            this.indice = joueursCrees;
            this.nom = "Marshall";
            this.waggon = c.getNB_Waggons();
            this.etage = 0;
            joueursCrees++;
        }
        else{
            this.indice = joueursCrees;
            this.nom = "Bandit"+" "+this.indice;
            if((int)((joueursCrees+1)/2)-1>=0){
            this.waggon=((int)((joueursCrees+1)/2)-1) % c.getNB_Waggons();}
            else{
                this.waggon = 0;
            }
            this.etage =joueursCrees%2;

            joueursCrees++;
        }
    }
    public static String getPlusRiche(){
        int d = 0;
        int fort = 0;
        Constantes c = new Constantes();
        for(int k = 0; k<c.getNbBandits();k++){
            if(c.getBandits(k).getArgent()>fort){
                d = k;
            }

        }
        return c.getBandits(d).getNom();
    }



    public void deplace(Direction d){
        Constantes c = new Constantes();
        switch(d){
            case Bas: if(this.etage ==1){
                this.etage =0;
            }
            else{this.etage = 0;}
           // System.out.println(this.nom+" "+"dessends dans le wagon n°" +this.waggon);
            break;


            case Haut: if(this.indice !=0){
                if(this.etage ==0){
                this.etage = 1;
            }
            else{this.etage =1;}
              //  System.out.println(this.nom+" "+"monte sur le toit du waggon n°" +this.waggon);
                }

            break;


            case Avant:
                if(this.waggon<c.getNB_Waggons() ){
                this.waggon++;

            }

             //   System.out.println(this.nom+" "+"se déplace dans le waggon n°" +this.waggon+" "+"etage"+this.etage);
                break;


            case Arriere: if(this.waggon>0){
                this.waggon--;
            }


            break;

            case Braque:

               break;
            case Tire:

                break;
        }

    }

    public int getEtage() {
        return etage;
    }

    public int getIndice() {
        return indice;
    }

    public int getWaggon() {
        return waggon;
    }

    public String getNom() {
        return nom;
    }
    public Direction[] getActions(){
        return this.actions;
    }


    public void setActions(int c,Direction a){
        this.actions[c]= a;
    }

    public void tirer(Bandits j,Direction d) {

        if (this.getClass().getSimpleName().equals("Bandits")) {
//tocom

            //System.out.println(((Bandits) this).getNbMunitions());
            if (this.getWaggon() == j.getWaggon() && this.getEtage() == 0 && j.getEtage() == 0 && d == Direction.Tire) {
                factoTir(j);

            }

            if (this.getWaggon() == j.getWaggon() && this.getEtage() == 0 && j.getEtage() == 1 && d == Direction.TireHaut) {
                factoTir(j);

            }
            if (this.getWaggon() + 1 == j.getWaggon() && this.getEtage() == j.getEtage() && d == Direction.TireDroite) {
               factoTir(j);
            }

            if (this.getWaggon() == j.getWaggon() && this.getEtage() == 1 && j.getEtage() == 0 && d == Direction.TireBas) {
                factoTir(j);
            }


            if (this.getWaggon() - 1 == j.getWaggon() && this.getEtage() == j.getEtage() && d == Direction.TireGauche) {
                factoTir(j);
            }


        }
        if (this.getClass().getSimpleName().equals("Marshall")) {

            if (this.getWaggon() == j.getWaggon() && this.getEtage() == 0 && j.getEtage() == 0 && d == Direction.Tire) {
                factoTir(j);
            }
            j.deplace(Direction.Haut);

        }
    }


    public void factoTir(Bandits j){
        double a2 = Math.random();

        if(j.butinBourse.size()>0 && j.getButinBijoux()>0&&j.getMago()>0) {
            if (j.butinBourse.size() > 0 && a2 < 1. / 3.) {
                int a = (int) (Math.random() * (j.butinBourse.size() - 1));
                Bourse b = new Bourse(j.butinBourse.get(a).getMontant());
                j.butinBourse.remove(a);
                j.pushBtab();
                Train.getWagon(j.getWaggon()).addTresor(b);


            }
            if (j.getButinBijoux() > 0 && a2 >= 1. / 3. && a2 <= 2. / 3.) {
                j.removeBijoux();
                Train.getWagon(j.getWaggon()).addBijoux();
            }
            if (j.getMago() > 0 && a2 > 2. / 3.) {
                j.removeMagot();
                Train.getWagon(j.getWaggon()).addMagot();
            }
        }
        else{
            if((j.butinBourse.size()>0&&j.getMago()>0)||(j.butinBourse.size()>0&&j.getButinBijoux()>0)||(j.getMago()>0&&j.getButinBijoux()>0)){
                if(j.butinBourse.size()>0 &&j.getMago()>0){
                    if (j.butinBourse.size() > 0 && a2<=0.5) {
                        int a = (int) (Math.random() * (j.butinBourse.size() - 1));
                        Bourse b = new Bourse(j.butinBourse.get(a).getMontant());
                        j.butinBourse.remove(a);
                        j.pushBtab();
                        Train.getWagon(j.getWaggon()).addTresor(b);


                    }
                    if (j.getMago() > 0 && a2 > 0.5) {
                        j.removeMagot();
                        Train.getWagon(j.getWaggon()).addMagot();
                    }

                }
                if(j.butinBourse.size()>0 &&j.getButinBijoux()>0){
                    if (j.butinBourse.size() > 0 && a2<=0.5) {
                        int a = (int) (Math.random() * (j.butinBourse.size() - 1));
                        Bourse b = new Bourse(j.butinBourse.get(a).getMontant());
                        j.butinBourse.remove(a);
                        j.pushBtab();
                        Train.getWagon(j.getWaggon()).addTresor(b);


                    }
                    if (j.getButinBijoux() > 0 && a2 > 0.5) {
                        j.removeBijoux();
                        Train.getWagon(j.getWaggon()).addBijoux();
                    }

                }
                if(j.getButinBijoux()>0 &&j.getMago()>0){
                    if (j.getButinBijoux() > 0 && a2<=0.5) {
                        j.removeBijoux();
                        Train.getWagon(j.getWaggon()).addBijoux();


                    }
                    if (j.getMago() > 0 && a2 > 0.5) {
                        j.removeMagot();
                        Train.getWagon(j.getWaggon()).addMagot();
                    }


                }

            }
            else{
                if (j.getMago() > 0 ) {
                    j.removeMagot();
                    Train.getWagon(j.getWaggon()).addMagot();   }

                if (j.getButinBijoux() > 0 && a2<=0.5) {
                    j.removeBijoux();
                    Train.getWagon(j.getWaggon()).addBijoux();


                }
                if (j.butinBourse.size() > 0 && a2<=0.5) {
                    int a = (int) (Math.random() * (j.butinBourse.size() - 1));
                    Bourse b = new Bourse(j.butinBourse.get(a).getMontant());
                    j.butinBourse.remove(a);
                    j.pushBtab();
                    Train.getWagon(j.getWaggon()).addTresor(b);


                }
            }
        }


    }

}



