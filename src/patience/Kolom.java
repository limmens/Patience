/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patience;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout.*;

/**
 * @author Loes Immens
 */
public class Kolom extends Stapel
{
    private final int kolomNr;
    
    public Kolom(int n, Kaart[] kaarten)
    {
        super();
        
        nKaarten = n;
        kolomNr = n;
        
        GroupLayout gl = new GroupLayout(this);
        this.setLayout(gl);
        ParallelGroup pg = gl.createParallelGroup(GroupLayout.Alignment.LEADING);
        gl.setHorizontalGroup(gl.createSequentialGroup().addGroup(pg));
        SequentialGroup sg = gl.createSequentialGroup();
        gl.setVerticalGroup(sg);

        for(int i = n - 1; i >= 0; i--)
        {
            this.kaarten[i] = kaarten[i];
            this.kaarten[i].setVisible(true);
            
            this.kaarten[i].setY(i * Deck.getHeightUnderlying());
            
            if(i == n - 1)
            {
                bovensteKaart = this.kaarten[i];
                bovensteKaart.setZichtbaar(true);
            }
            else
                this.kaarten[i].setZichtbaar(false);
            
            this.kaarten[i].toonJuisteKant();
            
            pg.addGroup(gl.createSequentialGroup().
            addComponent(kaarten[i]));
            sg.addGroup(gl.createParallelGroup().
            addComponent(kaarten[i]));
            
            //this.setComponentZOrder(kaarten[i], i);
        }
    }
    
    public Kaart[] getKaarten()
    {
        return kaarten;
    }
    
    public void setAangeklikt(boolean a, Kaart[] aangeklikteKaarten, int nAangeklikt)
    {
        aangeklikt = a;
        if(!aangeklikt)
        {
            for(int i = 0; i < nKaarten; i++)
                kaarten[i].setAangeklikt(false);
        }
        else
        {
            for(int i = 0; i < nKaarten; i++)
            {
                for(int j = 0; j < nAangeklikt; j++)
                {
                    if(kaarten[i] == aangeklikteKaarten[j])
                    {
                        kaarten[i].setAangeklikt(true);
                        System.out.println(kaarten[i] + " aangeklikt vanuit " + this);
                    }
                }
            }
        }
    }
    
    public void toonKolomJuist()
    {
        GroupLayout gl = new GroupLayout(this);
        this.setLayout(gl);
        
        ParallelGroup pg = gl.createParallelGroup(GroupLayout.Alignment.LEADING);
        gl.setHorizontalGroup(gl.createSequentialGroup().addGroup(pg));
        SequentialGroup sg = gl.createSequentialGroup();
        gl.setVerticalGroup(sg);
        
        if(nKaarten == 0)
        {
            pg.addGroup(gl.createSequentialGroup().
            addComponent(leegLabel));
            sg.addGroup(gl.createParallelGroup().
            addComponent(leegLabel));
        }
            
        for(int i = 0; i < nKaarten; i++)
        {
            kaarten[i].setY(i * Deck.getHeightUnderlying());
            kaarten[i].toonJuisteKant();
            System.out.println("\n" + kaarten[i] + " wordt opnieuw op " + this + " gelegd op positie Y = " + kaarten[i].getY());
            
            pg.addGroup(gl.createSequentialGroup().
            addComponent(kaarten[i]));
            sg.addGroup(gl.createParallelGroup().
            addComponent(kaarten[i]));
            
            System.out.println("Huidige Z-order van " + kaarten[i] + ": " + this.getComponentZOrder(kaarten[i]));
            
            this.setComponentZOrder(kaarten[i], nKaarten - 1 - i);
            
            System.out.println("Nieuwe Z-order van " + kaarten[i] + ": " + this.getComponentZOrder(kaarten[i]));
        }
    }
    
    @Override
    public Component.BaselineResizeBehavior getBaselineResizeBehavior()
    {
        return Component.BaselineResizeBehavior.CONSTANT_ASCENT;
    }

    @Override
    public int getBaseline(int width, int height)
    {
        return 0;
    }
    
    @Override
    public String toString()
    {
        return "Kolom " + kolomNr;
    }
    
}
