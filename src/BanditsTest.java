import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BanditsTest extends Joueur {
    Bandits b=new Bandits();

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {

    }
    @Test
    void testConstructeur(){
        assertTrue(b.getIndice()>0);
        assertEquals(b.getEtage(),1);
        Bandits b2= new Bandits();
        assertEquals(b.getIndice(),b2.getIndice()-1);
        assertEquals(b2.getEtage(),0);
        assertEquals(b2.getWaggon(),0);
        assertEquals(b.getWaggon(),0);
        assertEquals(b.getArgent(),0);
        assertEquals(b.getNbMunitions(),6);
        assertEquals(b.getArgent(),0);
        assertNull(b.getActions()[0]);
        assertNull(b.getActions()[1]);
        assertNull(b.getActions()[2]);
        assertEquals(b.getActions().length,3);

    }


    @Test
    void testDeplace() {
        int W = b.getWaggon();
        int E = b.getEtage();
        b.deplace(Direction.Avant);
        assertEquals(b.getEtage(),E);
        assertEquals(b.getWaggon(),W+1);

        b.deplace(Direction.Arriere);
        assertEquals(b.getEtage(),E);
        assertEquals(b.getWaggon(),W);

        b.deplace(Direction.Bas);
        assertEquals(b.getEtage(),E-1);
        assertEquals(b.getWaggon(),W);

        b.deplace(Direction.Haut);
        assertEquals(b.getEtage(),E);
        assertEquals(b.getWaggon(),W);

        b.deplace(Direction.Avant);
        b.deplace(Direction.Arriere);
        b.deplace(Direction.Bas);
        b.deplace(Direction.Haut);
        assertEquals(b.getEtage(),E);
        assertEquals(b.getWaggon(),W);

    }
//






    @Test
    void braque()throws Exception {
        Train t = new Train();
        int T = Train.getWagon(b.getWaggon()).getMagots()+ Train.getWagon(b.getWaggon()).getNbBourses()+ Train.getWagon(b.getWaggon()).getNbBijoux();
        b.deplace(Direction.Bas);
        b.braque(Train.getWagon(b.getWaggon()));
        assertTrue(b.getArgent()>0);
        assertEquals(Train.getWagon(b.getWaggon()).getMagots()+ Train.getWagon(b.getWaggon()).getNbBourses()+ Train.getWagon(b.getWaggon()).getNbBijoux(),T-1);
    }
}