import javax.swing.*;
import java.awt.*;

public class InfosBandits extends JFrame {
    private JFrame fenetre;
    private Constantes c;
    private JPanel contentPane;
    public InfosBandits(){
        c=new Constantes();
//
        fenetre = new JFrame("Tableau de bords");
        fenetre.setSize(new Dimension(100*c.getNbBandits(),200));
        Image icon = Toolkit.getDefaultToolkit().getImage("logo.gif");
        fenetre.setIconImage(icon);
       contentPane = (JPanel) fenetre.getContentPane();
       contentPane.setLayout(new GridLayout(3,c.getNbBandits()));

       createLabel();
       fenetre.setVisible(true);


       fenetre.setDefaultCloseOperation(DISPOSE_ON_CLOSE);



    }
    public void createLabel(){
        contentPane.removeAll();
        for(int k = 0; k<c.getNbBandits();k++){
            contentPane.add(new JLabel(c.getBandits(k).getNom()));

        }
        for(int k  = 0;k<c.getNbBandits();k++){
            contentPane.add(new JLabel("Argent: "+c.getBandits(k).getArgent()));
            //System.out.println("e");
        }
        for(int k = 0;k<c.getNbBandits();k++){
            contentPane.add(new JLabel("Munitions: "+c.getBandits(k).getNbMunitions()));
        }
        fenetre.revalidate();
        fenetre.repaint();

    }

}
