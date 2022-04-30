import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Selection extends JFrame implements ActionListener   {
    boolean validated= false;
    JButton valider;
    JTextField nbPlayer;
    JTextField nbWagon;
    JTextField nbMunitions;
    Constantes c;
    JFrame inputs;
    JTextField NervoMarsh;
//

    public Selection(){
        c=new Constantes();
        inputs = new JFrame("selection Parametres");
        inputs.setSize(400,400);
        JPanel contentPane = (JPanel) inputs.getContentPane();
        contentPane.setLayout(new FlowLayout());
        Image icon = Toolkit.getDefaultToolkit().getImage("logo.gif");
        inputs.setIconImage(icon);
        inputs.setLocationRelativeTo(null);
        inputs.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.nbPlayer = new JTextField("nombre joueurs?");
        this.nbWagon = new JTextField("nombre wagon?");
        this.valider = new JButton("valider");
        this.nbMunitions = new JTextField("Nombre mun:");
        this.NervoMarsh = new JTextField("Nervosité marshall?");
        contentPane.add(nbPlayer);
        contentPane.add(nbWagon);
        contentPane.add(nbMunitions);
        contentPane.add(NervoMarsh);
        contentPane.add(valider);
        valider.addActionListener(this);
        inputs.setVisible(true);
        inputs.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent e){
        if(((JButton)e.getSource()).getText().equals("valider" )){
            int tamp = Integer.parseInt(nbPlayer.getText());
            Constantes.setNbBandits(tamp);
            tamp = Integer.parseInt(nbWagon.getText());
            c.setNB_Waggons( tamp);
           // c.bandits = new Bandits[c.getNbBandits()];
            c.setTailleBandits(c.getNbBandits());
            c.setNbBalles(Integer.parseInt(nbMunitions.getText()));
            c.setNervosite_Marshall(Float.parseFloat(NervoMarsh.getText()));
            System.out.println(c.getNbBalles());
            validated = true;
            inputs.dispose();
            //System.out.println(c.getNbBandits());

        }}

}
