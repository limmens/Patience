/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patience;

import java.awt.Color;
import java.awt.Image;
import javax.swing.*;

/**
 *
 * @author Loes Immens
 */
public class Kolom extends Stapel
{
    
    private Kaart[] kolom = new Kaart[52];
    
    public Kolom(int n, Kaart[] kaarten)
    {
        this.remove(leegLabel);
        nKaarten = n;
        
        for(int i = 0; i < n; i++)
        {
            this.kaarten[i] = kaarten[i];
            
            if(i == (n - 1))
            {
                bovensteKaart = this.kaarten[i];
                bovensteKaart.setZichtbaar(true);
                
            }
        }
        
        bovensteKaart.toonJuisteKant();
        bovensteKaart.setVisible(true);
        this.add(bovensteKaart);
        
    }
    
    /*
    public void printKolom()
    {
        System.out.print("Kolom " + nKaarten + ": ");
        for(int i = 0; i < nKaarten; i++)
            System.out.print(kaarten[i] + " ");
        System.out.println("\n");
    }
*/
    
}
