/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patience;

import java.awt.event.ActionListener;

/**
 * stapels waar per kleur van A t/m K wordt opgelegd met oplopende waarden in stappen van 1
 * to do: zo instellen dat er geen kaart vanaf kan worden gepakt
 * 
 * @author Loes Immens
 */
public class EindStapel extends Stapel
{
    private String kleur;
    private Kaart bovensteKaart;
    
    public EindStapel()
    {
        nKaarten = 0;
        kleur = "";
        this.add(leegLabel);
    }

    public String getKleur()
    {
        return kleur;
        
    }
    
    public void setKleur(String kleur)
    {
        this.kleur = kleur;
    }
    
    @Override
    public String toString()
    {
        return "Eindstapel " + kleur;
    }
    
}
