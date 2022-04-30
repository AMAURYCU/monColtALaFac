import java.util.ArrayList;
//
public class Bandits extends Joueur{
public ArrayList<Bourse> butinBourse;
private int butinBijoux;
private int butinMago;
private int nbMunitions = c.getNbBalles();
public Bandits(){
    super();
    butinBourse = new ArrayList<Bourse>();
    butinBijoux = 0;
    butinMago = 0;
}
public int getNbMunitions(){
    return nbMunitions;
}
public void setNbMunitions(int d){
    this.nbMunitions= d;
}

    public int getButinBijoux() {
        return butinBijoux;
    }
   public void removeBijoux(){
    butinBijoux --;
   }
   public int getMago(){
    return butinMago;
   }

   public void removeMagot(){
    butinMago--;
   }

    public void braque(Wagon w){
    double b =Math.random();
    int a = (int)(Math.random()*(w.getNbBourses()-1));
    if(w.getNbBijoux()>0&&w.getMagots()>0&&w.getNbBourses()>0) {
        if (b < 1. / 3. && w.getNbBourses() > 0) {
            this.butinBourse.add(new Bourse(w.getBtab().get(a).getMontant()));
            w.setNbBourses(w.getNbBourses() - 1);
        }

        if (b > 2. / 3. && w.getNbBijoux() > 0) {
            w.setNbBijoux(w.getNbBijoux() - 1);
            this.butinBijoux++;
        }
        if (b >= 1. / 3. && b <= 2. / 3. && w.getMagots() > 0) {
            w.setNbMagot(w.getMagots() - 1);
            this.butinMago++;
        }
    }
    //w.pushBtab();
    else{ if((w.getMagots()>0 && w.getNbBijoux()>0)||(w.getNbBourses()>0&&w.getMagots()>0)||(w.getNbBourses()>0 && w.getNbBijoux()>0)){
        if(w.getMagots()>0 && w.getNbBijoux()>0){
            if (b >0.5) {
                w.setNbMagot(w.getMagots() - 1);
                this.butinMago++;
            }
            else{
                w.setNbBijoux(w.getNbBijoux() - 1);
                this.butinBijoux++;
            }
        }
        if(w.getNbBourses()>0&&w.getMagots()>0){
            if (b >0.5) {
                w.setNbMagot(w.getMagots() - 1);
                this.butinMago++;
            }
            else{
                this.butinBourse.add(new Bourse(w.getBtab().get(a).getMontant()));
                w.setNbBourses(w.getNbBourses() - 1);
            }
        }
        if(w.getNbBourses()>0 && w.getNbBijoux()>0){
            if(b>0.5){
                w.setNbBijoux(w.getNbBijoux() - 1);
                this.butinBijoux++;
            }
            else{
                this.butinBourse.add(new Bourse(w.getBtab().get(a).getMontant()));
                w.setNbBourses(w.getNbBourses() - 1);
            }
        }}
        else{
        if(w.getNbBourses()>0){

            this.butinBourse.add(new Bourse(w.getBtab().get(a).getMontant()));
            w.setNbBourses(w.getNbBourses()-1);

        }
        if (w.getNbBijoux() > 0) {
            w.setNbBijoux(w.getNbBijoux() - 1);
            this.butinBijoux++;

        }
        if(w.getMagots()>0){
            w.setNbMagot(w.getMagots() - 1);
            this.butinMago++;
        }
    }}


    }

    public void pushBtab(){
        ArrayList ne = new ArrayList();
        int c = 0;
        for(int k = 0; k< this.butinBourse.size(); k++){

            if(this.butinBourse.get(k)!=null){
                ne.add(butinBourse.get(k));
                c++;
            }

        }
        this.butinBourse = ne;
    }
    public void finisTour(){
    for(int k = 0;k<3;k++){
        this.setActions(k,Direction.FaisRien);
    }
    }
    public int getArgent(){
    int a= 0;
    int i = 0;
    for(int k = 0;k<butinBourse.size();k++){
        a=a+butinBourse.get(i).getMontant();
    }
    a=a+butinBijoux*500;
    a=a+butinMago*1000;
    return a;
    }

}
