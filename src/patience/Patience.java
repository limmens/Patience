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
 *to do:
 * zichtbare kaart op trekstapel fixen
 * kaarten die op eindstapel liggen verschijnen ineens weer op trekstapel
 * verdwenen eindstapel fixen: gebeurt als bovenste kaart eindstapel ineens weer op trekstapel verschijnt
 * kaartenrij vervangen door pointer?
 * log in txt-bestand schrijven?
 * na schudden en daarna weer laatste kaart van trekstapel draaien, wordt trekstapel niet zichtbaar leeg
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
    JTextArea kolomText;
    
    private int nGedraaid;
    
    public Patience()
    {
        trekStapel = new TrekStapel();
        trekStapel.addMouseListener(this);
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
            eindStapels[i].addMouseListener(this);
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
        stapelPanelRechts.setBackground(Color.red);
        kolomPanel.setBackground(Color.red);
        
        //kolommen initialiseren
        //vulKolommen();
                
        aflegStapel = new AflegStapel();
        
        aflegStapel.addMouseListener(this);
        stapelPanelLinks.add(aflegStapel);
        aflegStapel.setVisible(true);
        
        nGedraaid = 0;
        draaiKaart();
    }
    
    //haalt kaart van de trekstapel en legt hem op de aflegstapel
    private void draaiKaart()
    {
        if(trekStapel.getNKaarten() == 0)
        {
            nGedraaid = 0;
            int n = aflegStapel.getNKaarten();
            for(int i = 0; i < n; i++)
            {
                Kaart terug = aflegStapel.trekKaart();
                trekStapel.addKaart(terug);
                System.out.println(terug + " wordt teruggelegd. Zichtbaar? " + terug.getZichtbaar());
            }
            trekStapel.schudStapel(trekStapel.getNKaarten());
            kolomText.setText(trekStapel.getNKaarten() + " kaarten op de trekstapel");
        }
        else
        {
            nGedraaid++;

            Kaart getrokken = trekStapel.trekKaart();
            aflegStapel.addKaart(getrokken);
            kolomText.setText("kaart " + nGedraaid + " gedraaid: " + getrokken);
            System.out.println("kaart " + nGedraaid + " gedraaid: " + getrokken);
        }
        
    }
    
    //legt bovenste kaart van stapel 'van' op eindstapel 'naar' als deze kaart op deze eindstapel past;
    //geeft anders een melding
    public void legOpEindStapel(Stapel van, EindStapel naar)
    {
        Kaart getrokken = van.getBovensteKaart();
        if(magOpEindStapel(getrokken,naar))
        {
            naar.addKaart(van.trekKaart());
            kolomText.setText(getrokken + " van " + van + " naar " + naar);
            System.out.println(getrokken + " van " + van + " naar " + naar);
        }
        else
        {
            kolomText.setText("deze kaart mag niet op deze eindstapel");
            van.setAangeklikt(false);
        }
        System.out.print("Eindstapel " + naar.getKleur() + " bevat de volgende kaarten: ");
        naar.printStapel();
    }
    
    //checkt of kaart 'kaart' op eindstapel 'es' gelegd mag worden, volgens de regel
    //dat elke eindstapel precies één kleur bevat en precies van A naar K oploopt
    //met elke volgende kaart precies één waarde hoger dan de vorige kaart
    private static boolean magOpEindStapel(Kaart kaart, EindStapel es)
    {
        if(es.getKleur().equals("") && kaart.getWaarde().equals("A"))
        {
            es.setKleur(kaart.getKleur());
            return true;
        }
        else if(kaart.getKleur().equals(es.getKleur()) && Deck.preciesEenWaardeHoger(kaart, es.getBovensteKaart()))
            return true;
        return false;
    }
       
    private static void vulKolommen()
    {
        for(int i = 0; i < nKolommen; i++)
        {
            kolommen[i] = new Kolom(i+1,trekStapel.pakKaarten(i+1));
        }
    }
    
    public static void legOpKolom(Kaart[] kaarten, Kolom kolom)
    {
        
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
        Object o = e.getSource();

        if(o == trekStapel)
        {
            draaiKaart();
            aflegStapel.setAangeklikt(false);
        }
        else if(o == aflegStapel)
        {
            if(!aflegStapel.getAangeklikt())
            {
                aflegStapel.setAangeklikt(true);
                //legOpEindStapel(aflegStapel,eindStapels[0]);
            }
            else
            {
                aflegStapel.setAangeklikt(false);
            }
        }
        for(int i = 0; i < 4; i++)
        {
            if(o == eindStapels[i])
            {
                kolomText.setText("eindstapel " + i + "aangeklikt");
                eindStapels[i].setAangeklikt(true);
            }
        }
        
        for(int i = 0; i < 4; i++)
        {
            if(aflegStapel.getAangeklikt())
            {
                if(eindStapels[i].getAangeklikt())
                {
                    legOpEindStapel(aflegStapel,eindStapels[i]);
                    aflegStapel.setAangeklikt(false);
                }
            }
            eindStapels[i].setAangeklikt(false);
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
