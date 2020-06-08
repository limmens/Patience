/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patience;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.Random;

/**
 *
 * @author Loes Immens
 */
public class TrekStapel extends Stapel
{
    public TrekStapel()
    {
        nKaarten = Deck.getNKaarten();
        
        for(int i = 0; i < Deck.getNKleuren(); i++)
            for(int j = 0; j < Deck.getNWaarden(); j++)
                kaarten[i * 13 + j] = new Kaart(Deck.getKleuren()[i], Deck.getWaarden()[j], false);
        
        schudStapel(52);
        
        bovensteKaart = kaarten[nKaarten-1];
        
        //this.add(bovensteKaart);
    }
    
    //stapel wordt n keer geschud: dit betekent dat n keer een random kaart van plaats wisselt met een andere random kaart
    private void schudStapel(int n)
    {
        Random random = new Random();
        Kaart reserve;
        
        for(int i = 0; i < n; i++)
        {
            int r1 = random.nextInt(52);
            int r2 = random.nextInt(52);
            reserve = kaarten[r1];
            kaarten[r1] = kaarten[r2];
            kaarten[r2] = reserve;
        }
    }
    
    //haalt kaart van de stapel en geeft deze terug met een return
    public Kaart trekKaart()
    {
        Kaart getrokken = bovensteKaart;
        
        nKaarten--; 
        
        if(nKaarten > 0)
        {
            bovensteKaart = kaarten[nKaarten - 1];
            this.add(bovensteKaart);
        }
        else
            this.setVisible(false);
        return getrokken;
    }
    
    //pakt meerdere kaarten, nog geen effect op GUI
    public Kaart[] pakKaarten (int n)
    {
        Kaart[] gepakt = new Kaart[n];//n laatste kaarten van rij kaarten
        for(int i = 0; i < n; i++)
        {
            gepakt[i] = kaarten[nKaarten - 1];
            nKaarten --;
            bovensteKaart = kaarten[nKaarten - 1];
        }
        return gepakt;
    }
    
    

    
    
    
}
