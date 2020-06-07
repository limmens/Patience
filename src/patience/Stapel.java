/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patience;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

/**
 *
 * @author Gebruiker
 */
public class Stapel extends JPanel
{
    protected int nKaarten;
    protected Kaart bovensteKaart;
    
    //soorten kaarten
    
    protected Kaart[] kaarten;
    
    public Stapel()
    {
        kaarten = new Kaart[Deck.getNKleuren() * Deck.getNWaarden()];
        
    }
    
    public int getNKaarten()
    {
        return nKaarten;
    }
    
    public Kaart getBovensteKaart()
    {
        return bovensteKaart;
    }
    
    
    
    
    //print stapel zonder GUI
    public void printStapel()
    {
        if(nKaarten == 0)
            System.out.print("leeg");
        for(int i = 0; i < nKaarten; i++)
            System.out.print(kaarten[i] + " ");
        
        System.out.println("\n");
    }

    
}
