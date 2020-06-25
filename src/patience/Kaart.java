/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patience;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author Loes Immens
 */
public class Kaart extends JLabel 
{
    private String waarde;
    private String kleur;
    private String kleurRZ;
    
    private boolean zichtbaar;
    
    private Kaart vorige;
    private Kaart volgende;
    
    BufferedImage inputVK;
    BufferedImage inputAK;
    Image voorkant;
    Image achterkant;
    ImageIcon icon;
    private Graphics g;
    
    int x;
    int y;
    
    public Kaart(String kleur, String waarde, boolean zichtbaar)
    {
        this.kleur = kleur;
        this.waarde = waarde;
        this.zichtbaar = zichtbaar;
        
        if(kleur == "Klaveren" || kleur == "Schoppen")
            kleurRZ = "Zwart";
        else if(kleur == "Ruiten" || kleur == "Harten")
            kleurRZ = "Rood";
        else
            kleurRZ = "";
        
        x = 0;
        y = 0;
        
        try 
        {
            ImageIcon ia = new ImageIcon(getClass().getResource("/resources/blue_back.png"));  
            achterkant = ia.getImage().getScaledInstance(Deck.getCardWidth(),Deck.getCardHeight(), Image.SCALE_SMOOTH);
            
            ImageIcon iv = new ImageIcon(getClass().getResource("/resources/" + waarde + kleur + ".png"));  
            voorkant = iv.getImage().getScaledInstance(Deck.getCardWidth(),Deck.getCardHeight(), Image.SCALE_SMOOTH);

        } 
        catch (Exception ex) 
        {
            System.out.println("plaatje niet gevonden");
        }
        
        if(zichtbaar)
        {
            icon = new ImageIcon(voorkant);
        }
        else
        {
            icon = new ImageIcon(achterkant);
            
        }
        this.setIcon(icon);
    }
    
    public void toonJuisteKant()
    {
        if(!zichtbaar)
        {
            icon.setImage(achterkant);
            this.setIcon(icon);
        }
        else
        {
            icon.setImage(voorkant);
            this.setIcon(icon);
        }
    }
    
    @Override
    public int getX()
    {
        return x;
    }
    
    @Override
    public int getY()
    {
        return y;
    }
    
    public void setX(int x)
    {
        this.x = x;
    }
    
    public void setY(int y)
    {
        this.y = y;
    }
    
    public boolean getZichtbaar()
    {
        return zichtbaar;
    }
    
    public void setZichtbaar(boolean z)
    {
        zichtbaar = z;
    }
    
    public String getZichtbaarString()
    {
        if(zichtbaar)
            return "zichtbaar";
        else
            return "niet zichtbaar";
    }
    
    public String getKleur()
    {
        return kleur;
    }
    
    public String getKleurRZ()
    {
        return kleurRZ;
    }
    
    public String getWaarde()
    {
        return waarde;
    }
    
    @Override
    public String toString()
    {
        return kleur + waarde;
    }
    
}
