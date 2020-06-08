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
    JPanel stapelPanelTotaal;
    JPanel stapelPanelLinks;
    JPanel stapelPanelRechts;
    JPanel kolomPanel;
    
    Dimension stapelPanelDimension = new Dimension (Deck.getCardWidth() * 2 + 50, Deck.getCardHeight() + 50);
    
    JTextArea kolomText;
    
    
    public Patience()
    {
        trekStapel = new TrekStapel();
        trekStapel.addMouseListener(this);
        trekStapel.setLocation(50, 50);
        trekStapel.setVisible(true);
        
        BorderLayout totalLayout = new BorderLayout();
        FlowLayout stapelPanelLLayout = new FlowLayout();
        FlowLayout stapelPanelRLayout = new FlowLayout();
        BorderLayout stapelPanelTLayout = new BorderLayout();
        
        stapelPanelLLayout.setAlignment(FlowLayout.LEFT);
        stapelPanelRLayout.setAlignment(FlowLayout.RIGHT);
        
        stapelPanelLinks = new JPanel();
        stapelPanelLinks.setLayout(stapelPanelLLayout);
        stapelPanelLinks.add(trekStapel);
        
        stapelPanelRechts = new JPanel();
        stapelPanelRechts.setLayout(stapelPanelRLayout);
        
        //eindstapels initialiseren
        eindStapels = new EindStapel[Deck.getNKleuren()];
        for(int i = 0; i < Deck.getNKleuren(); i++)
        {
            eindStapels[i] = new EindStapel();
            stapelPanelRechts.add(eindStapels[i]); 
            eindStapels[i].setVisible(true);
            eindStapels[i].setMinimumSize(Deck.getCardDimension());
        }
                
        stapelPanelTotaal = new JPanel();
        stapelPanelTotaal.setLayout(stapelPanelTLayout);
        
        stapelPanelTotaal.add(stapelPanelLinks, BorderLayout.WEST);
        stapelPanelTotaal.add(stapelPanelRechts, BorderLayout.CENTER);
        
        kolomText = new JTextArea();
        
        kolomPanel = new JPanel();
        kolomPanel.add(kolomText);
        
        //panels toevoegen
        holdsAll = new JPanel();
        holdsAll.setLayout(totalLayout);
        this.add(holdsAll);
        
        holdsAll.add(stapelPanelTotaal, BorderLayout.NORTH);
        holdsAll.add(kolomPanel, BorderLayout.CENTER);
        
        stapelPanelLinks.setBackground(Color.red);
        stapelPanelLinks.setPreferredSize(stapelPanelDimension);
        stapelPanelRechts.setBackground(Color.red);
        kolomPanel.setBackground(Color.red);
        
        //kolommen initialiseren
        vulKolommen();
                
        aflegStapel = new AflegStapel();
        
        aflegStapel.addMouseListener(this);
        stapelPanelLinks.add(aflegStapel);
        aflegStapel.setVisible(true);
        aflegStapel.setLocation(Deck.getCardWidth() + 200, 50);
        
        draaiKaart();
    }
    
    //haalt kaart van de trekstapel en legt hem op de aflegstapel
    private void draaiKaart()
    {
            Kaart getrokken = trekStapel.trekKaart();

            aflegStapel.addKaart(getrokken);
    }
    
    private static void vulKolommen()
    {
        for(int i = 0; i < nKolommen; i++)
        {
            kolommen[i] = new Kolom(i+1,trekStapel.pakKaarten(i+1));
        }
    }
        
    public void legOpEindStapel(Kaart kaart, Stapel van, EindStapel naar)
    {
        String kleur = kaart.getKleur();
        if(naar.getKleur().equals("") && kaart.getWaarde().equals("A"))
        {
            //van.trekKaart();
            naar.setKleur(kaart.getKleur());
            naar.addKaart(kaart);
        }
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
        else if(e.getSource() == aflegStapel)
        {
            if(!aflegStapel.getAangeklikt())
            {
                aflegStapel.setAangeklikt(true);
            }
            else
            {
                aflegStapel.setAangeklikt(false);
            }
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
