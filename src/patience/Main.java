/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patience;

import javax.swing.JFrame;

/**
 *
 * @author Loes Immens
 */
public class Main
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Patience p = new Patience();
        p.setTitle("Patience door Loes Immens");
        p.setSize(Deck.getCardWidth() * 8,Deck.getCardHeight() * 6);
        p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        p.setVisible(true);
    }
    
}
