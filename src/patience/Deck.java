/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patience;

import java.awt.Dimension;

/**
 * @author Loes Immens
 */
public final class Deck
{
    private static final String[] KLEUREN = {"C","D","S","H"};
    private static final int NKLEUREN = KLEUREN.length;
    private static final String[] WAARDEN = {"A", "2", "3", "4", "5", "6", "7", "8", "9","10","J", "Q", "K"};
    private static final int NWAARDEN = WAARDEN.length;
    
    private static final int NKAARTEN = NKLEUREN * NWAARDEN;
    
    private static final int CARDWIDTH = 100;
    private static final int CARDHEIGHT = 150;
    private static final Dimension CARDDIMENSION = new Dimension(CARDWIDTH,CARDHEIGHT);
    
    public Deck()
    {
        
    }
    
    static public Dimension getCardDimension()
    {
        return CARDDIMENSION;
    }
    
    static public int getCardWidth()
    {
        return CARDWIDTH;
    }
    
    static public int getCardHeight()
    {
        return CARDHEIGHT;
    }
    
    static public String[] getKleuren()
    {
        return KLEUREN;
    }
    
    static public int getNKleuren()
    {
        return NKLEUREN;
    }
    
    static public String[] getWaarden()
    {
        return WAARDEN;
    }
    
    static public int getNWaarden()
    {
        return NWAARDEN;
    }
    
    static public int getNKaarten()
    {
        return NKAARTEN;
    }
    
    static public int getHeightUnderlying()
    {
        return CARDHEIGHT / 5;
    }
    
    public static boolean preciesEenWaardeHoger(Kaart k1, Kaart k2)
    {
        if(k1 == null || k2 == null)
            return false;
        String w1 = k1.getWaarde();
        String w2 = k2.getWaarde();
        int pos1 = 0;
        int pos2 = 0;        
        for(int i = 0; i < NWAARDEN; i++)
        {
            if(w1.equals(WAARDEN[i]))
                pos1 = i;
            if(w2.equals(WAARDEN[i]))
                pos2 = i;
        }
        return (pos1 - pos2 == 1);
    }
}
