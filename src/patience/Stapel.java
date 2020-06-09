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
    protected Kaart[] kaarten;
    protected int nKaarten;
    protected Kaart bovensteKaart;
    
    protected boolean aangeklikt;
    
    protected JLabel leegLabel;
    protected Image leegImage;
    protected ImageIcon icon;
    
    public Stapel()
    {
        kaarten = new Kaart[Deck.getNKleuren() * Deck.getNWaarden()];
        
        aangeklikt = false;
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        try 
        {
            ImageIcon ia = new ImageIcon(getClass().getResource("/resources/leegRood.png"));  
            leegImage = ia.getImage().getScaledInstance(Deck.getCardWidth(),Deck.getCardHeight(), Image.SCALE_SMOOTH);
            
        } 
        catch (Exception ex) 
        {
            System.out.println("plaatje niet gevonden");
        }
        
        icon = new ImageIcon(leegImage);
        
        leegLabel = new JLabel();
        leegLabel.setIcon(icon);
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
        {
            //this.setVisible(false);
            this.add(leegLabel);
        }
        return getrokken;
    }
    
    //voegt kaart toe aan aflegstapel en draait hem open
    public void addKaart(Kaart k)
    {
        k.setVisible(true);
        if(nKaarten > 0)
            bovensteKaart.setVisible(false);
        else
            this.remove(leegLabel);
            
        bovensteKaart = k;
        kaarten[nKaarten] = k;
        
        bovensteKaart.setZichtbaar(true);
        bovensteKaart.verversKaart();
        this.add(bovensteKaart);
        
        nKaarten++;
    }
}
