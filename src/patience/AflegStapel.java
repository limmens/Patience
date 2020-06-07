/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patience;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

/**
 *
 * @author Gebruiker
 */
public class AflegStapel extends Stapel
{
    public AflegStapel(Kaart k)
    {
        nKaarten = 0;
        addKaart(k);
    }
    
    public void legAf(Kaart k)
    {
        
        
    }

    public void addKaart(Kaart k)
    {
        if(nKaarten > 0 )
            this.remove(bovensteKaart);
        bovensteKaart = k;
        kaarten[nKaarten] = k;
        
            this.add(bovensteKaart);
            //this.remove(bovensteKaart);
        
        //{
            //this.add(bovensteKaart);
            //bovensteKaart.setPreferredSize(bovensteKaart.getCardDimension());
        //}
        bovensteKaart.setZichtbaar(true);
        bovensteKaart.verversKaart();
        
        nKaarten++;
    }

}
