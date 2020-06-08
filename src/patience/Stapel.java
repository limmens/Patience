/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patience;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 *
 * @author Gebruiker
 */
public class Stapel extends JPanel
{
    protected int nKaarten;
    protected Kaart bovensteKaart;
    protected boolean aangeklikt;
    
    protected JLabel leegLabel;
    
    //soorten kaarten
    
    protected Kaart[] kaarten;
    
    public Stapel()
    {
        kaarten = new Kaart[Deck.getNKleuren() * Deck.getNWaarden()];
        aangeklikt = false;
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        leegLabel = new JLabel();
        
        leegLabel.setBackground(Color.BLUE);
        leegLabel.setMinimumSize(Deck.getCardDimension());
                
        this.add(leegLabel);
    }
    
    public int getNKaarten()
    {
        return nKaarten;
    }
    
    public Kaart getBovensteKaart()
    {
        return bovensteKaart;
    }
    
    public boolean getAangeklikt()
    {
        return aangeklikt;
    }
    
    public void setAangeklikt(boolean a)
    {
        aangeklikt = a;
        if(aangeklikt)
            this.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        else
            this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
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
