/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patience;

import java.awt.event.ActionListener;

/**
 *
 * @author Gebruiker
 */
public class EindStapel extends Stapel
{
    private String kleur;
    private Kaart bovensteKaart;
    
    public EindStapel()//String kleur)
    {
        //this.kleur = kleur;
        nKaarten = 0;
        kleur = "";
    }

    public String getKleur()
    {
        return kleur;
        
    }
    
    public void setKleur(String kleur)
    {
        this.kleur = kleur;
    }
    
    public void addKaart(Kaart k)
    {
        k.setVisible(true);
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
