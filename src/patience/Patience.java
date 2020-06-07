
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patience;

import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author Loes Immens
 */
public class Patience extends JFrame implements MouseListener
{
    final private static int nKolommen = 7;
    private static Kolom[] kolommen = new Kolom[7];
    private static TrekStapel trekStapel;
    private static AflegStapel aflegStapel;
    private static EindStapel[] eindStapels = new EindStapel[4];
    
    private static Scanner scanner = new Scanner(System.in);
    
    JPanel holdsAll;
    JPanel stapelPanel;
    JPanel kolomPanel;
    
    Dimension stapelPanelDimension = new Dimension (Deck.getCardWidth() * 2, Deck.getCardHeight() * 2);
    
    JTextArea kolomText;
    
    private int nGedraaid;
    
    public Patience()
    {
        nGedraaid = 0;
        
        trekStapel = new TrekStapel();
        trekStapel.addMouseListener(this);
        
        BorderLayout totalLayout = new BorderLayout();
        FlowLayout stapelPanelLayout = new FlowLayout();
        
        stapelPanel = new JPanel();
        stapelPanel.setLayout(stapelPanelLayout);
        stapelPanel.add(trekStapel);
        
        kolomText = new JTextArea();
        
        kolomPanel = new JPanel();
        kolomPanel.add(kolomText);
        
        draaiKaart();

        //panels toevoegen
        holdsAll = new JPanel();
        holdsAll.setLayout(totalLayout);
        this.add(holdsAll);
        
        holdsAll.add(stapelPanel, BorderLayout.NORTH);
        holdsAll.add(kolomPanel, BorderLayout.CENTER);
        
        stapelPanel.setBackground(Color.red);
        stapelPanel.setPreferredSize(stapelPanelDimension);
        kolomPanel.setBackground(Color.red);
        
        
        /*
        //kolommen initialiseren
        vulKolommen();
        
        //eindstapels initialiseren
        for(int i = 0; i < Deck.getNKleuren(); i++)
        {
            eindStapels[i] = new EindStapel(Deck.getKleuren()[i],this);
        }
        */
        //do
        //{
        //}
        //while(interactie() && trekStapel.getNKaarten() > 0 && aflegStapel.getNKaarten() > 0);
    }
    
    private void draaiKaart()
    {
        nGedraaid++;
        
        Kaart getrokken = trekStapel.trekKaart();
        
        
        if(aflegStapel == null)
        {
            aflegStapel = new AflegStapel(getrokken);
            aflegStapel.addMouseListener(this);
            stapelPanel.add(aflegStapel);
        }
        else
        {
            aflegStapel.addKaart(getrokken);
            kolomText.setText("getrokken kaart nr. " + nGedraaid + ": " + getrokken.getKleur() + " " + getrokken.getWaarde() + 
                    "\ntrekstapel:" + trekStapel.getBovensteKaart().getKleur() + " " + trekStapel.getBovensteKaart().getWaarde() +
                    "\naflegstapel: " + aflegStapel.getBovensteKaart());
        }
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
            kolomText.setText("trekstapel aangeklikt");
            draaiKaart();
        }
        if(e.getSource() == aflegStapel)
        {
            kolomText.setText("aflegstapel aangeklikt");
        }
        
        trekStapel.ververs();
        aflegStapel.ververs();
    }
    
    /*
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == trekStapel.getBovensteKaart())
        {
            kolomText.setText("trekstapel aangeklikt");
            draaiKaart();
            //trekStapel.getBovensteKaart().setZichtbaar(true);
            //trekStapel.getBovensteKaart().verversKaart();
            //aflegStapel.legAf(trekStapel.draaiKaart());
            //aflegStapel.getBovensteKaart().verversKaart();
        }
        if(e.getSource() == aflegStapel.getBovensteKaart())
        {
            kolomText.setText("aflegstapel aangeklikt");
            //aflegStapel.legAf(trekStapel.draaiKaart());
            //aflegStapel.getBovensteKaart().verversKaart();
            //aflegStapel.getBovensteKaart().setText("hallo");
        }
    }
    */
    
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
    
    /*
    public static boolean interactie()
    {
        String input = "";
        int intInput = 99;
        String inputVan;
        String inputNaar;
        String inputN;
        int intVan = 99;
        int intNaar = 99;
        int intN = 0;
        
        System.out.println("Kies getal:\n"
            + "\nT: Trek kaart"
            + "\nV: Verplaats kaart"
            + "\nE: EXIT"
        );
        input = scanner.next();

        if(input.contains("E"))
        {
            return false;
        }
        else if(input.equals("T"))
        {
            aflegStapel.legAf(trekStapel.draaiKaart());
            return true;
        }
        else if(input.equals("V"))
        {
            System.out.println("Toets drie getallen in voor verplaatsing van de kaart (voorbeeld: 421): "
                + "\n\nVan: kolomnummer of 9 voor aflegstapel"
                + "\n\nNaar: kolomnummer of 9 voor eindstapel"
                + "\n\nHoeveel kaarten?"
                + "\n0: EXIT"
            );
            inputVan = scanner.next();
            inputNaar = scanner.next();
            inputN = scanner.next();

            try
            {
                // the String to int conversion happens here
                intVan = Integer.parseInt(inputVan.trim());
                intNaar = Integer.parseInt(inputNaar.trim());
                intN = Integer.parseInt(inputN.trim());
            }

            catch (NumberFormatException nfe)
            {
                System.out.println("Input is geen getal!");
            }
            
            if(intVan == 0)
                return false;
            else if(intNaar == 9)
                
            return true;
        }
     return true;       
    }
*/

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
}