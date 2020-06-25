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
 *
 * @author Loes Immens
 */
public class Kolom extends Stapel
{
    
    public Kolom(int n, Kaart[] kaarten)
    {
        super();
        
        nKaarten = n;
        
        
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
            kaarten[i].repaint();
        }
    }
    
    public Kaart[] getKaarten()
    {
        return kaarten;
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
}
