public class Constantes {
  private static int NB_Waggons=4;
  private static int NB_BANDITS = 2;

  private static Bandits[] bandits = new Bandits[NB_BANDITS];
  private static Marshall marshall;
  private static  int NB_BALLES;
  private static float Nervosite_Marshall;
  public void setNervosite_Marshall(float n){

    if(n>0.5){
      Nervosite_Marshall = 0.5f;
    }
    else{
    Nervosite_Marshall = n;}
  }

  public float getNervosite_Marshall() {
    return Nervosite_Marshall;
  }

  public int getNbBandits(){
    return NB_BANDITS;
  }

  public static void setNbBandits(int nbBandits) {
    NB_BANDITS = nbBandits;
  }
  public int getNB_Waggons(){
    return NB_Waggons;
  }

  public void setNB_Waggons(int NB_Waggons) {
    Constantes.NB_Waggons = NB_Waggons;
  }

  public void setNbBalles(int nbBalles) {
    NB_BALLES = nbBalles;
  }

  public  int getNbBalles() {
    return NB_BALLES;
  }

  public Bandits getBandits(int k) {
    return bandits[k];
  }
  public void setBandits(int k,Bandits b){
    bandits[k]=b;
  }
  public void setTailleBandits(int k){
    bandits = new Bandits[k];
  }
  public Marshall getMarshall(){
    return marshall;
  }
  public void setMarshall(Marshall m){
    marshall = m;

  }
}//
