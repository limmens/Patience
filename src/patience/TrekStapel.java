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
    
    //daadwerkelijke stapel kaarten
    
    private Kaart legeKaart;
    
    public TrekStapel()
    {
        
        nKaarten = Deck.getNKleuren() * Deck.getNWaarden();
        
        for(int i = 0; i < Deck.getNKleuren(); i++)
        {
            for(int j = 0; j < Deck.getNWaarden(); j++)
            {
                
                kaarten[i * 13 + j] = new Kaart(Deck.getKleuren()[i], Deck.getWaarden()[j], false);
                
            }
        }
        
        schudStapel(52);
        bovensteKaart = kaarten[nKaarten-1];
        this.add(bovensteKaart);
        bovensteKaart.setPreferredSize(Deck.getCardDimension());
    }
    
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
    
    public Kaart trekKaart()
    {
        Kaart getrokken = bovensteKaart;
        this.remove(bovensteKaart);
        kaarten[nKaarten - 1] = null;
        bovensteKaart = kaarten[nKaarten - 2];
        nKaarten--;   
        bovensteKaart.verversKaart();
        this.add(bovensteKaart);
        return getrokken;
    }

    
    
    
}
