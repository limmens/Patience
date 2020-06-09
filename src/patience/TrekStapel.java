/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patience;

import java.util.Random;

/**
 *
 * @author Loes Immens
 */
public class TrekStapel extends Stapel
{
    public TrekStapel()
    {
        this.remove(leegLabel);
        nKaarten = Deck.getNKaarten();
        
        for(int i = 0; i < Deck.getNKleuren(); i++)
            for(int j = 0; j < Deck.getNWaarden(); j++)
                kaarten[i * 13 + j] = new Kaart(Deck.getKleuren()[i], Deck.getWaarden()[j], false);
        
        schudStapel(52);
        
        bovensteKaart = kaarten[nKaarten-1];
        
        this.add(bovensteKaart);
    }
    
    //stapel wordt n keer geschud: dit betekent dat n keer een random kaart van plaats wisselt met een andere random kaart
    public void schudStapel(int n)
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
        
        System.out.println("Stapel met " + n + " kaarten wordt geschud");
    }
    
    @Override
    public String toString()
    {
        return "Trekstapel";
    }

    @Override
    public void setZichtbareKant(Kaart kaart)
    {
        kaart.setZichtbaar(false);
        System.out.println("Kaart " + kaart + " wordt " + kaart.getZichtbaarString() + " neergelegd.");
    }
    
    
}
