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
 * juiste kaarten in kolommen selecteren
 * dimension ipv height/width
 * waardes en kleuren als enum?
 * log in txt-bestand schrijven?
 * kaartenrij vervangen door list/pointer?
 * @author Loes Immens
 */
public class Patience extends JFrame implements MouseListener
{
    Stapel van;
    Stapel naar;
    
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
    
    private Kaart[] geselecteerdeKaarten;
    private int nKaartenGeselecteerd;
    private Stapel vanStapel;
    private Stapel naarStapel;
    
    public Patience()
    {
        trekStapel = new TrekStapel();
        trekStapel.addMouseListener(this);
        trekStapel.setVisible(true);
        
        BorderLayout totalLayout = new BorderLayout();
        FlowLayout stapelPanelLLayout = new FlowLayout();
        FlowLayout stapelPanelRLayout = new FlowLayout();
        BorderLayout stapelPanelTLayout = new BorderLayout();
        FlowLayout kolomPanelLayout = new FlowLayout();
        
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
        
        kolomPanel = new JPanel();
        kolomPanel.setLayout(kolomPanelLayout);
        kolomPanelLayout.setAlignOnBaseline(true);
        
        //kolommen initialiseren
        for(int i = 0; i < nKolommen; i++)
        {
            kolommen[i] = new Kolom(i+1,trekStapel.pakKaarten(i+1));
            kolomPanel.add(kolommen[i]);
            kolommen[i].setVisible(true);
            kolommen[i].addMouseListener(this);
            System.out.println("\nAangemaakt: " + kolommen[i].toString() + "\n met de volgende kaarten:");
            kolommen[i].printStapel();
        }
        
        //panels toevoegen
        holdsAll = new JPanel();
        holdsAll.setLayout(totalLayout);
        this.add(holdsAll);
        
        holdsAll.add(stapelPanelTotaal, BorderLayout.NORTH);
        holdsAll.add(kolomPanel, BorderLayout.CENTER);
        
        stapelPanelLinks.setBackground(Color.red);
        stapelPanelRechts.setBackground(Color.red);
        kolomPanel.setBackground(Color.red);
                
        aflegStapel = new AflegStapel();        
        aflegStapel.addMouseListener(this);
        stapelPanelLinks.add(aflegStapel);
        aflegStapel.setVisible(true);
        
        nGedraaid = 0;
        
        geselecteerdeKaarten = new Kaart[Deck.getNWaarden()];
        nKaartenGeselecteerd = 0;
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
                System.out.println(terug + " wordt teruggelegd. Zichtbaar? " + terug.getZichtbaar() + "\n");
            }
            
            System.out.print("\nTrekstapel bevat de volgende kaarten: ");
            trekStapel.printStapel();
            
            if(trekStapel.getNKaarten() == 0)
                return;
            
            trekStapel.schudStapel(trekStapel.getNKaarten());
        }
        else
        {
            nGedraaid++;

            Kaart getrokken = trekStapel.trekKaart();
            System.out.println("\nKaart " + nGedraaid + " gedraaid: " + getrokken);
            aflegStapel.addKaart(getrokken);
            
        }
        System.out.print("\nTrekstapel bevat de volgende kaarten: ");
        trekStapel.printStapel();
        System.out.print("\nAflegstapel bevat de volgende kaarten: ");
        aflegStapel.printStapel();
    }
    
    //legt bovenste kaart van stapel 'van' op eindstapel 'naar' als deze kaart op deze eindstapel past;
    //geeft anders een melding
    public void legOpEindStapel(Stapel van, EindStapel naar, Kaart getrokken)
    {
        if(magOpEindStapel(getrokken,naar))
        {
            naar.addKaart(van.trekKaart());
            System.out.println(getrokken + " van " + van + " naar " + naar);
        }
        else
        {
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
    
    public static void legOpKolom(Stapel van, Kolom naarKolom, Kaart[] getrokken, int nK)
    {
        if(magOpKolom(getrokken[0], naarKolom))
        {
            System.out.println("\n" + getrokken[0] + " mag op " + naarKolom);
            naarKolom.voegKaartenToe(van.pakKaarten(nK));
        }
        else
        {
            System.out.println("\n" + getrokken[0] + " mag niet op " + naarKolom);
            van.setAangeklikt(false);
        }
    }
    
    private static boolean magOpKolom(Kaart getrokken, Kolom naarKolom)
    {
        if(naarKolom.getNKaarten() == 0)
        {
            if(getrokken.getWaarde().equals("K"))
            {
                System.out.println("\n Een koning mag op een lege stapel");
                return true;
            }
            else
            {
                System.out.println("\n Een " + getrokken + " mag niet op een lege stapel");
                return false;
            }
        }
        if(getrokken.getKleurRZ().equals(naarKolom.getBovensteKaart().getKleurRZ()))
        {
            System.out.println("\n" + getrokken + " heeft kleur " + getrokken.getKleurRZ() + " en dat matcht niet");
            return false; 
        }
        if(Deck.preciesEenWaardeHoger(naarKolom.getBovensteKaart(), getrokken))
        {
            System.out.println("\n De waarde van " + getrokken + " is precies één waarde lager dan de naarKolom en de kleur is anders en dat matcht dus goed");
            return true;
        }
        
        System.out.println("\n" + getrokken + " heeft wel de juiste kleur, maar waarde is niet precies één lager en matcht dus niet");
        return false;
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        Object o = e.getSource();
        
        if(Stapel.class.isAssignableFrom(o.getClass())) //als een stapel is aangeklikt
        {
            Stapel s = (Stapel)o; //de aangeklikte stapel
            System.out.println("\n" + s + " aangeklikt");
            
            if(o == trekStapel) //als de trekstapel is aangeklikt
            {
                draaiKaart();
                aflegStapel.setAangeklikt(false);
                for(int i = 0; i < Deck.getNKleuren(); i++)
                    eindStapels[i].setAangeklikt(false);
                for(int i = 0; i < nKolommen; i++)
                    kolommen[i].setAangeklikt(false);
                nKaartenGeselecteerd = 0;
                vanStapel = null;
                naarStapel = null;
            }
            
            else //als een andere stapel dan de trekstapel is aangeklikt
            {
                if(s.getAangeklikt()) //als de aangeklikte stapel al aangeklikt was
                {
                    s.setAangeklikt(false);
                    if(s.equals(vanStapel))
                        if(naarStapel == null)
                            vanStapel = null;
                    if(s.equals(naarStapel))
                        naarStapel = null;
                }

                else //als de aangeklikte stapel nog niet aangeklikt was
                {
                    if(vanStapel == null) //als er nog geen andere stapel aangeklikt was
                    {
                        if(Kolom.class.isAssignableFrom(s.getClass())) //als er een kolom is aangeklikt
                        {
                            Kolom kol = (Kolom)s;
                            
                            for(int j = 0; j < kol.getNKaarten(); j++)
                            {
                                boolean aangeklikt = false;
                                System.out.println("\nY aangeklikt: " + e.getY() + " en Y " + kol.getKaarten()[j] + ": " + kol.getKaarten()[j].getY());
                                
                                //check of op deze kaart wordt geklikt
                                if(!kol.getKaarten()[j].equals(kol.getBovensteKaart()) //als er een andere kaart dan de bovenste wordt aangeklikt
                                        && (e.getY() > kol.getKaarten()[j].getY()) //als er onder de bovenkant van deze kaart wordt geklikt
                                        && (e.getY() < (kol.getKaarten()[j].getY() + Deck.getHeightUnderlying())) //als er boven de kaart eronder wordt geklikt
                                        && kol.getKaarten()[j].getZichtbaar()) //als de aangeklikte kaart met het plaatje naarboven ligt
                                    aangeklikt = true;
                                
                                else if(kol.getKaarten()[j].equals(kol.getBovensteKaart()) //als de bovensteKaart wordt aangeklikt
                                        && (e.getY() > kol.getKaarten()[j].getY()) ////als er onder de bovenkant van deze kaart wordt geklikt
                                        && (e.getY() < (kol.getKaarten()[j].getY() + Deck.getCardHeight())) //als er tussen de boven- en onderkant van de bovenste kaart wordt geklikt
                                        && kol.getKaarten()[j].getZichtbaar())//als de aangeklikte kaart met het plaatje naarboven ligt
                                    aangeklikt = true;
                                if(aangeklikt)
                                {
                                    vanStapel = s; //aangeklikte stapel is de eerste aangeklikte stapel
                                    System.out.println("van " + s);
                                    nKaartenGeselecteerd = kol.getNKaarten() - j;
                                    System.out.println("\n" + nKaartenGeselecteerd + " kaart(en) geselecteerd");
                                    Kaart[] gK = new Kaart[nKaartenGeselecteerd];
                                    System.out.print("\nGeselecteerde kaarten vanuit " + this + ":");
                                    for(int k = 0; k < nKaartenGeselecteerd; k++)
                                    {
                                        geselecteerdeKaarten[k] = kol.getKaarten()[j + k];
                                        System.out.println(geselecteerdeKaarten[k]);
                                    }
                                    kol.setAangeklikt(true, geselecteerdeKaarten, nKaartenGeselecteerd);
                                    break;
                                }
                            }
                            
                            if(vanStapel == null)
                            {
                                System.out.println("\nGeen geldige kolomkaarten aangeklikt");
                                vanStapel = null;
                                s.setAangeklikt(false);
                            }
                            
                            for(int i = 0; i < nKolommen; i++)
                                if (!kolommen[i].equals(kol))
                                    kolommen[i].setAangeklikt(false);
                        }

                        else if(o == aflegStapel)
                        {
                            vanStapel = s; //aangeklikte stapel is de eerste aangeklikte stapel
                            System.out.println("van " + s);
                            s.setAangeklikt(true); //aflegStapel wordt op aangeklikt gezet
                            geselecteerdeKaarten[0] = aflegStapel.getBovensteKaart();
                            nKaartenGeselecteerd = 1;
                        }
                    }
                    else //als de vanStapel al gevuld is
                        if(naarStapel == null) //als er nog geen naar Stapel is
                        {
                            naarStapel = s;
                            System.out.println(" naar " + s + "\nKaarten geselecteerd: ");
                            for(int i = 0; i < nKaartenGeselecteerd; i++)
                            {
                                System.out.println(geselecteerdeKaarten[i]);
                            }
                            boolean geldigeNaarStapel = false;
                            
                            if(Kolom.class.isAssignableFrom(s.getClass())) //als naarStapel een kolom is
                            {
                                Kolom naarKolom = (Kolom)s;
                                System.out.println("\nnaarStapel is: " + naarKolom);
                                legOpKolom(vanStapel, naarKolom, geselecteerdeKaarten, nKaartenGeselecteerd);
                                geldigeNaarStapel = true;
                            }
                            
                            else if(EindStapel.class.isAssignableFrom(s.getClass()))
                            {
                                EindStapel es = (EindStapel)s;
                                if(nKaartenGeselecteerd == 1)
                                    legOpEindStapel(vanStapel,es,geselecteerdeKaarten[0]);
                                geldigeNaarStapel = true;
                            }
                            
                            if(geldigeNaarStapel)
                            {
                                if(Kolom.class.isAssignableFrom(vanStapel.getClass())) //als vanStapel een Kolom is
                                {
                                    Kolom kVan = (Kolom)vanStapel;
                                    System.out.println("\nvanStapel " + kVan + " is een kolom, dus kolom wordt juist getoond");
                                    kVan.toonKolomJuist();
                                }

                                if(Kolom.class.isAssignableFrom(naarStapel.getClass())) //als naarStapel een Kolom is
                                {
                                    Kolom kNaar = (Kolom)naarStapel;
                                    System.out.println("\nnaarStapel " + kNaar + " is een kolom, dus kolom wordt juist getoond");
                                    kNaar.toonKolomJuist();
                                }
                            }
                            
                            vanStapel.setAangeklikt(false);
                            naarStapel.setAangeklikt(false);
                            nKaartenGeselecteerd = 0;
                            vanStapel = null;
                            naarStapel = null;
                        }      
                }
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
    
    @Override
    public String toString()
    {
        return "Patience";
    }
    
}
