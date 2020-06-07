/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patience;


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
    
    public void addKaart(Kaart k)
    {
        if(nKaarten > 0)
            bovensteKaart.setVisible(false);
            
        bovensteKaart = k;
        kaarten[nKaarten] = k;
        
        bovensteKaart.setZichtbaar(true);
        bovensteKaart.verversKaart();
        this.add(bovensteKaart);
        
        nKaarten++;
    }

}
