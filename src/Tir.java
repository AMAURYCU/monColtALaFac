import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tir extends JFrame implements ActionListener {

    private JButton haut;
    private JButton bas;
    private JButton droite;
    private JButton gauche;
    private JButton centre;
    private String selected;
    public JFrame inputs;
    public int CoupsJoues;
    public int JoueurJoues;
    public Constantes c = new Constantes();
    public InterfaceGraphique interfaceGraphique;

    public Tir( int coupsJoues,int JoueurJoue,InterfaceGraphique i){
        interfaceGraphique = i;
        this.JoueurJoues= JoueurJoue;
        this.CoupsJoues = coupsJoues;
        haut = new JButton("HAUT ^");
        haut.addActionListener(this);
        bas = new JButton("BAS v");
        bas.addActionListener(this);
        gauche = new JButton("< GAUCHE");
        gauche.addActionListener(this);
        droite = new JButton("DROITE >");
        droite.addActionListener(this);
        centre= new JButton("CENTRE");
        centre.addActionListener(this);
    inputs = new JFrame("selection Angle de tir");
        inputs.setSize(400,400);
        JPanel contentPane = (JPanel) inputs.getContentPane();
        contentPane.setLayout(new BorderLayout());
        haut.setPreferredSize(new Dimension(0,110));
        bas.setPreferredSize(new Dimension(0,110));
        droite.setPreferredSize(new Dimension(120,0));
        gauche.setPreferredSize(new Dimension(120,0));
        contentPane.add(haut,BorderLayout.NORTH);

        contentPane.add(bas,BorderLayout.SOUTH);
        contentPane.add(gauche,BorderLayout.WEST);
        contentPane.add(droite,BorderLayout.EAST);
        contentPane.add(centre,BorderLayout.CENTER);
        inputs.setVisible(true);
        inputs.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }
    public void actionPerformed(ActionEvent e){
        Bandits b = c.getBandits(JoueurJoues);
        if(((JButton)e.getSource()).getText().equals("HAUT ^")){
            if(this.CoupsJoues <3){
                b.setActions(this.CoupsJoues,Direction.TireHaut);
                CoupsJoues++; }
            this.selected = "haut";
            this.dispose();


    }
        if(((JButton)e.getSource()).getText().equals("BAS v" )){
            System.out.println("tir vers le bas");

            if(this.CoupsJoues <3){
                b.setActions(this.CoupsJoues,Direction.TireBas);
                CoupsJoues++;}
            this.selected = "bas";
            this.dispose();

        }

        if(((JButton)e.getSource()).getText().equals("< GAUCHE" )){
            System.out.println("tir vers la gauche");

            if(this.CoupsJoues <3){
                b.setActions(this.CoupsJoues,Direction.TireGauche);
                CoupsJoues++;}

            this.selected= "gauche";
            this.dispose();


        }

        if(((JButton)e.getSource()).getText().equals("DROITE >" )){
            System.out.println("tir vers la droite");

            if(this.CoupsJoues <3){
                b.setActions(this.CoupsJoues,Direction.TireDroite);
                CoupsJoues++;}
          this.selected = "droite";
            this.dispose();


        }
        if(((JButton)e.getSource()).getText().equals("CENTRE" )){
            System.out.println("tir dans le meme waggon");
            if(this.CoupsJoues <3){
                b.setActions(this.CoupsJoues,Direction.Tire);
                CoupsJoues++;}
            this.selected = "dans le meme wagon";
            this.dispose();



           // inputs.dispose();


//

        }
        interfaceGraphique.setVisible(true);
}

public void dispose(){
        this.inputs.dispose();
    }



}
