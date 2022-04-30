import java.util.ArrayList;


public class Wagon {


    private  int nbBourses=0;
    private int nbBijoux=0;//
    private int nbMagot = 0;
    private ArrayList<Bourse> btab;

public void addBijoux(){
    nbBijoux++;
}

    public Wagon(){
        int nbTresor = 1+(int)(Math.random()*4);
        while(nbTresor>0){
            double bourseOuBijoux = Math.random();
            if(bourseOuBijoux>0.5){

                nbBourses+=1;
                nbTresor--;
            }
            else{
               // System.out.println(bourseOuBijoux);
                nbBijoux++;
                nbTresor--;
            }

        }
        this.btab = new ArrayList<Bourse>();
        for(int k = 0; k<nbBourses;k++){
            btab.add(new Bourse());
        }
    }
    public void setNbBijoux(int nbBijoux){
        this.nbBijoux = nbBijoux;
    }

    public void setNbBourses(int nbBourses) {
        this.nbBourses = nbBourses;
    }
    public void addMagot(){
        this.nbMagot++;

    }
    public int getMagots(){
        return this.nbMagot;
    }

    public void setNbMagot(int nbMagot) {
        this.nbMagot = nbMagot;
    }

    public ArrayList<Bourse> getBtab(){
        return btab;
    }
    public int getNbBourses(){
        return nbBourses;
    }

    public int getNbBijoux() {
        return nbBijoux;
    }
    public void addTresor(Bourse b){
        nbBourses ++;
        //this.pushBtab();
        this.btab.add(b);
    }
    public int getCurrantTresor(Bandits b) {
        int c = this.nbBijoux + nbBourses+nbMagot;

        for (int k = 0; k < 3; k++) {
            if(b.getActions()[k]==Direction.Braque){
                c --;
            }


        }
        return c;
    }
}
