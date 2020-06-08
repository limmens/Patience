/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patience;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Loes Immens
 */
public class Patience extends JFrame implements MouseListener
{
    private static TrekStapel trekStapel;
    private static AflegStapel aflegStapel;
    private static EindStapel[] eindStapels = new EindStapel[4];
    
    final private static int nKolommen = 7;
    private static Kolom[] kolommen = new Kolom[7];
    
    JPanel holdsAll;
    JPanel stapelPanel;
    JPanel kolomPanel;
    
    Dimension stapelPanelDimension = new Dimension (Deck.getCardWidth() * 2 + 50, Deck.getCardHeight() + 50);
    
    JTextArea kolomText;
    
    private int nGedraaid;
    
    public Patience()
    {
        nGedraaid = 0;
        
        trekStapel = new TrekStapel();
        trekStapel.addMouseListener(this);
        
        BorderLayout totalLayout = new BorderLayout();
        FlowLayout stapelPanelLayout = new FlowLayout();
        stapelPanelLayout.setAlignment(FlowLayout.LEFT);
        
        stapelPanel = new JPanel();
        stapelPanel.setLayout(stapelPanelLayout);
        stapelPanel.add(trekStapel);
        trekStapel.setLocation(50, 50);
        trekStapel.setVisible(true);
        
        kolomText = new JTextArea();
        
        kolomPanel = new JPanel();
        kolomPanel.add(kolomText);

        //panels toevoegen
        holdsAll = new JPanel();
        holdsAll.setLayout(totalLayout);
        this.add(holdsAll);
        
        holdsAll.add(stapelPanel, BorderLayout.NORTH);
        holdsAll.add(kolomPanel, BorderLayout.CENTER);
        
        stapelPanel.setBackground(Color.red);
        stapelPanel.setPreferredSize(stapelPanelDimension);
        kolomPanel.setBackground(Color.red);
        
        //eindstapels initialiseren
        eindStapels = new EindStapel[Deck.getNKleuren()];
        for(int i = 0; i < Deck.getNKleuren(); i++)
        {
            eindStapels[i] = new EindStapel(Deck.getKleuren()[i]);
        }
        
        //kolommen initialiseren
        vulKolommen();
                
        draaiKaart();
    }
    
    //haalt kaart van de trekstapel en legt hem op de aflegstapel
    private void draaiKaart()
    {
            nGedraaid++;

            Kaart getrokken = trekStapel.trekKaart();

            if(aflegStapel == null)
            {
                aflegStapel = new AflegStapel(getrokken);
                aflegStapel.addMouseListener(this);
                stapelPanel.add(aflegStapel);
                aflegStapel.setVisible(true);
                aflegStapel.setLocation(Deck.getCardWidth() + 200, 50);
            }
            else
            {
                aflegStapel.addKaart(getrokken);
            }
            //kolomText.setText("getrokken kaart nr. " + nGedraaid + ": " + getrokken.getKleur() + " " + getrokken.getWaarde() + 
            //            "\ntrekstapel:" + trekStapel.getBovensteKaart().getKleur() + " " + trekStapel.getBovensteKaart().getWaarde() +
            //            "\naflegstapel: " + aflegStapel.getBovensteKaart());
    }
    
    private static void vulKolommen()
    {
        for(int i = 0; i < nKolommen; i++)
        {
            kolommen[i] = new Kolom(i+1,trekStapel.pakKaarten(i+1));
        }
    }
        
    public static void legOpEindStapel(Kaart kaart)
    {
        String kleur = kaart.getKleur();
    }
    
    public static void legOpKolom(Kaart[] kaarten, Kolom kolom)
    {
        
    }

    private static boolean isZichtbareKaart(String inputKaart)
    {
        return true;
    }
    
    private static boolean magOpEindStapel(Kaart kaart, EindStapel es)
    {
        if(kaart.getKleur().equals(es.getKleur()) && Deck.preciesEenWaardeHoger(kaart, es.getBovensteKaart()))
            return true;
        return false;
    }
    
    private static boolean magOpKolom(Kaart kaart, Kolom kol)
    {
        if(kaart.getKleurRZ().equals(kol.getBovensteKaart().getKleurRZ()))
        {
           return false; 
        }
        if(Deck.preciesEenWaardeHoger(kol.getBovensteKaart(), kaart))
        {
            return true;
        }
        return false;
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        if(e.getSource() == trekStapel)
        {
            draaiKaart();
        }
        if(e.getSource() == aflegStapel)
        {
            //kolomText.setText("aflegstapel aangeklikt");
        }
        
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
        
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
        
    }
    
    
    /*
        private static void printTafel()
    {
        System.out.println("Trekstapel:");
        trekStapel.printStapel();
        
        System.out.println("AflegStapel:");
        aflegStapel.printStapel();        
        
        for(int i = 0; i < nKolommen; i++)
        {
            kolommen[i].printKolom();            
        }
        
        System.out.println("Eindstapels:");
        for(int i = 0; i < Deck.getNKleuren(); i++)
        {
            eindStapels[i].printStapel();
        }
    }
    */
    
}
