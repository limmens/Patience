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
    
    public EindStapel()
    {
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
    
}
