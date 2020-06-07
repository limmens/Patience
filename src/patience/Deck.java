/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patience;

import java.awt.Dimension;

/**
 *
 * @author Gebruiker
 */
public final class Deck
{
    private static final String[] kleuren = {"C","D","S","H"};
    private static final int nKleuren = kleuren.length;
    private static final String[] waarden = {"A", "2", "3", "4", "5", "6", "7", "8", "9","10","J", "Q", "K"};
    private static final int nWaarden = waarden.length;
    
    private static final int nKaarten = nKleuren * nWaarden;
    
    private static int cardWidth = 150;
    private static int cardHeight = 250;
    private static Dimension cardDimension = new Dimension(cardWidth,cardHeight);
    
    public Deck()
    {
        
    }
    
    static public Dimension getCardDimension()
    {
        return cardDimension;
    }
    
    static public int getCardWidth()
    {
        return cardWidth;
    }
    
    static public int getCardHeight()
    {
        return cardHeight;
    }
    
    static public String[] getKleuren()
    {
        return kleuren;
    }
    
    static public int getNKleuren()
    {
        return nKleuren;
    }
    
    static public String[] getWaarden()
    {
        return waarden;
    }
    
    static public int getNWaarden()
    {
        return nWaarden;
    }
    
    static public int getNKaarten()
    {
        return nKaarten;
    }
    
    public static boolean preciesEenWaardeHoger(Kaart k1, Kaart k2)
    {
        if(k1 == null || k2 == null)
            return false;
        String w1 = k1.getWaarde();
        String w2 = k2.getWaarde();
        int pos1 = 0;
        int pos2 = 0;        
        for(int i = 0; i < nWaarden; i++)
        {
            if(w1.equals(waarden[i]))
                pos1 = i;
            if(w2.equals(waarden[i]))
                pos2 = i;
        }
        return (pos1 - pos2 == 1);
    }
}
