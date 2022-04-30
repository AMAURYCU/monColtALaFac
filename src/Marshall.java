public class Marshall extends Joueur{
    private double NERVOSITE_MARSHALL;//inferieur a 0.5
    private Constantes c;
    public Marshall(){
        super();
        c=new Constantes();

        NERVOSITE_MARSHALL= c.getNervosite_Marshall();

    }
    public void deplaceM(){


        for(int k = 0; k<3;k++){
            double a=Math.random();
            boolean b = false;
           if(a<NERVOSITE_MARSHALL&&this.getCurrantW()!=0&&!b){
               System.out.println("arriere"+k);
               this.setActions(k,Direction.Arriere);
               b=true;
           }

               if(a>2*NERVOSITE_MARSHALL&&this.getCurrantW()!=c.getNB_Waggons()&&!b){
                   System.out.println("avant"+k);//test
                   this.setActions(k,Direction.Avant);
                   b=true;
               }
               if(a>=NERVOSITE_MARSHALL&&a<=2*NERVOSITE_MARSHALL&&this.getCurrantW()!=c.getNB_Waggons()&&this.getCurrantW()!=0&&!b){
                       this.setActions(k,Direction.FaisRien);
                   System.out.println("rien"+k);
               b=true;}

               if(this.getCurrantW()==0&&!b){
                   if(a>NERVOSITE_MARSHALL&&!b){
                       this.setActions(k,Direction.FaisRien);
                       System.out.println("rien0"+k);
                       b=true;
                   }
                   if(a<=NERVOSITE_MARSHALL&&!b){
                       this.setActions(k,Direction.Avant);
                       System.out.println("avant0"+k);
                       b=true;
                   }
               }
            if(this.getCurrantW()==c.getNB_Waggons()&&!b){
                if(a>NERVOSITE_MARSHALL){
                    this.setActions(k,Direction.FaisRien);
                    System.out.println("rienNB"+k);
                    b=true;
                }
                if(a<=NERVOSITE_MARSHALL&&!b){
                    this.setActions(k,Direction.Arriere);
                    System.out.println("ArriereNB"+k);
                    b=true;
                }
            }

           }

        }
        public String[] stringAction(){
          String[] tab = new String[3];
        for(int k = 0;k<3;k++){
            tab[k]= "Marshall se déplace dans le waggon n°"+this.getCurrantW()+"etage n°"+this.getCurrantE()+" ";
        }
        return tab;
        }//
    }

