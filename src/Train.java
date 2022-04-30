import javax.swing.*;
import java.awt.*;

public class Train extends JPanel {
    public Constantes c = new Constantes();
    public JPanel cont;
    public JLabel[][] labelTrain;
    public JPanel[] toit;
    public JPanel[] infos;
    private JPanel inTrain;
    private PanelImage[] sprite;
    private static Wagon[]wagons;


    public static Wagon getWagon(int index){
        return wagons[index];
    }


//
    public Train() throws Exception {
        c.setMarshall(new Marshall());
        //System.out.println(c.marshall.getIndice());
        this.wagons = new Wagon[c.getNB_Waggons()+1];
        for(int k = 0; k<c.getNbBandits();k++){

            Bandits b = new Bandits();
            c.setBandits(k,b);
          //  System.out.println(c.getBandits(k).getIndice());


        }
        for(int k = 0; k<c.getNB_Waggons()+1;k++){
            this.wagons[k]= new Wagon();
        }
        this.wagons[c.getNB_Waggons()].addMagot();
        this.cont = new JPanel(new BorderLayout());
       // cont.setPreferredSize(new Dimension(400,400));
        this.toit = new JPanel[c.getNB_Waggons()+1];
        this.infos = new JPanel[c.getNB_Waggons()+1];
        this.labelTrain = new JLabel[5][c.getNB_Waggons() + 1];
        for(int k = 0;k<c.getNB_Waggons()+1;k++){
            for(int i = 0; i<4;i++){
                labelTrain[i][k]= new JLabel("");
            }
        }
        this.sprite = new PanelImage[c.getNB_Waggons() + 1];
        for(int k = 0; k<c.getNB_Waggons()+1;k++){
            toit[k]= new JPanel(new BorderLayout());
            infos[k]= new JPanel(new FlowLayout());
        }
        createSprite();
        createLabel();
        createTresature();
        //cont.setPreferredSize(new Dimension(500,500));

        createInTrain();



        cont.add(inTrain,BorderLayout.CENTER);


    }

    public void createInTrain() {
        this.inTrain = new JPanel();

        this.inTrain.setLayout(new GridLayout(3, c.getNB_Waggons() + 1));



            for (int j = 0; j < c.getNB_Waggons() + 1; j++) {
                toit[j].add(labelTrain[1][j],BorderLayout.SOUTH);
                inTrain.add(toit[j]);
            }
            for(int i = 0;i<c.getNB_Waggons()+1;i++){
                inTrain.add(sprite[i]);

        }

        for(int i = 0; i<c.getNB_Waggons()+1;i++){
            infos[i].add(labelTrain[2][i]);
            infos[i].add(labelTrain[3][i]);
            infos[i].add(labelTrain[4][i]);
            inTrain.add(infos[i]);
        }



           // inTrain.setPreferredSize(cont.getPreferredSize());


    }
    public void createTresature(){
        for(int j = 0; j<c.getNB_Waggons()+1;j++){
            JLabel lab = new JLabel("nb Bourses: "+wagons[j].getNbBourses());
            labelTrain[2][j] = lab;
            labelTrain[3][j]= new JLabel("nb Bijoux: "+wagons[j].getNbBijoux());
            labelTrain[4][j]= new JLabel("nb Magots: "+wagons[j].getMagots());
        }
    }
    public void createSprite()throws  Exception{
        for(int k = 0; k<c.getNB_Waggons();k++){
            this.sprite[k]= new PanelImage("Waggon.png");

        }
        this.sprite[c.getNB_Waggons()]=new PanelImage("Loco.png");

    }
    public void createLabel(){
        boolean b = false;
        for (int i = 0; i<2; i++){
            for(int j = 0;j<c.getNB_Waggons()+1;j++){
                String s="";
                for(int k = 0; k <c.getNbBandits();k++){
                    if(c.getBandits(k).getEtage()==i && c.getBandits(k).getWaggon()==j){
                        s=s+ c.getBandits(k).getNom();


                        b=true;

                    }

                }
                if(c.getMarshall().getEtage()==i && c.getMarshall().getWaggon() ==j){
                    s=s+"marshall";

                    b=true;

                }
                labelTrain[i][j]=new JLabel(s,JLabel.CENTER);

                if(!b){
                    labelTrain[i][j]=new JLabel("", JLabel.CENTER);

                }
                b=false;
            }
        }
        for(int i = 0; i<c.getNB_Waggons()+1;i++){

            sprite[i].add(this.labelTrain[0][i],BorderLayout.CENTER);
        }
        /*for(int j = 0;j<c.NB_Waggons+1;j++){
            labelTrain[2][j]= new JLabel(("tst"));
        }*/


    }
    public  void updateLabel()throws Exception{

        this.cont.removeAll();

        for(int k = 0;k< sprite.length;k++){
            sprite[k].removeAll();
            toit[k].removeAll();
            infos[k].removeAll();

        }

        createSprite();
        this.createLabel();

            this.createInTrain();
            cont.add(inTrain);}


    public int getNbTresor(){
        int c = 0;
        for(int k = 0; k< this.c.getNB_Waggons()+1 ;k++){
            c= c+ wagons[k].getMagots()+ wagons[k].getNbBijoux()+ wagons[k].getNbBourses();
        }
        return c;
    }


    }
    /*public static void main(String[] args)throws Exception{
        JFrame f = new JFrame();

        f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        f.setSize(800,400);
        f.setLocationRelativeTo(null);
        JPanel contentPane = (JPanel)f.getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.setPreferredSize(new Dimension(800,400));
        JPanel j = new Train().cont;
        j.setPreferredSize(new Dimension(400,400));
        contentPane.add(j,BorderLayout.WEST);
        f.setVisible(true);
    }*/
