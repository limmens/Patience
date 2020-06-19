/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patience;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Loes Immens
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

    //haalt kaart van de stapel en geeft deze terug met een return
    public Kaart trekKaart()
    {
        bovensteKaart.setVisible(false);
        this.remove(bovensteKaart);
        
        Kaart getrokken = bovensteKaart;
        
        nKaarten--; 
        
        if(nKaarten > 0)
        {
            bovensteKaart = kaarten[nKaarten - 1];
            this.add(bovensteKaart);
            bovensteKaart.setVisible(true);
        }
        else
        {
            bovensteKaart = null;
            leegLabel.setVisible(true);
            this.add(leegLabel);
        }
        
        return getrokken;
    }
    
    //voegt kaart toe aan stapel en draait hem open
    public void addKaart(Kaart k)
    {
        if(nKaarten > 0)
            bovensteKaart.setVisible(false);
          
        bovensteKaart = k;
        setZichtbareKant(bovensteKaart);
        bovensteKaart.toonJuisteKant();
        
        kaarten[nKaarten] = bovensteKaart;
        
        this.add(bovensteKaart);
        bovensteKaart.setVisible(true);
        
        this.remove(leegLabel);
        leegLabel.setVisible(false);
        
        nKaarten++;
    }
    
    public boolean bevatDubbeleKaarten()
    {
        for(int i = 0; i < nKaarten; i++)
        {
            for(int j = i + 1; j < nKaarten; j++)
            {
                if((kaarten[i] == kaarten[j]) && (kaarten[i] != null))
                {
                    System.out.println("\nDUBBEL in " + this + ": " + kaarten[i] + " op plek " + i + " en " + kaarten[j] + " op plek " + j + "!");
                    return true;
                }
            }
        }
        return false;
    }
    
    //print stapel zonder GUI
    public void printStapel()
    {
        if(nKaarten == 0)
            System.out.print("leeg");
        for(int i = 0; i < nKaarten; i++)
        {
            System.out.print(kaarten[i]);
            if(i < nKaarten - 1)
                System.out.print(", ");
            else
                System.out.println(".");
        }
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
    
    public void setZichtbareKant(Kaart kaart)
    {
        kaart.setZichtbaar(true);
        System.out.println("Kaart " + kaart + " wordt " + kaart.getZichtbaarString() + " neergelegd.");
    }
    
    public String toString()
    {
        return "Stapel";
    }

    
}
