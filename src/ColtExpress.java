import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class ColtExpress {

    public static void  main(String[] args)throws Exception{
        Selection s = new Selection();
        while(!s.validated){

            System.out.print("");
        }
        UIManager.setLookAndFeel(new NimbusLookAndFeel());
        InterfaceGraphique i = new InterfaceGraphique();
        i.setVisible(true);
        i.revalidate();

        //InterfaceGraphique f = new InterfaceGraphique();

//
    }
}
