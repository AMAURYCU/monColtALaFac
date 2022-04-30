public class Bourse{
    private int montant = (int)(Math.random()*500);
    public Bourse(){

    }
    public Bourse(int mont){
        this.montant = mont;
    }
    public int getMontant(){
        return montant;
    }

}//