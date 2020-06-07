/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patience;

/**
 *
 * @author Loes Immens
 */
public class Kolom
{
    private int nKaarten;
    private Kaart[] kolom = new Kaart[52];
    private Kaart bovensteKaart;
    
    public Kolom(int n, Kaart[] kaarten)
    {
        nKaarten = n;
        
        for(int i = 0; i < (n-1); i++)
        {
            kolom[i] = kaarten[i];
            kolom[i].setZichtbaar(false);
        }
        kolom[n-1] = kaarten[n-1];
        kolom[n-1].setZichtbaar(true);
        bovensteKaart = kolom[n-1];
    }
    
    public Kaart getBovensteKaart()
    {
        return bovensteKaart;
    }
    
    public void printKolom()
    {
        System.out.print("Kolom " + nKaarten + ": ");
        for(int i = 0; i < nKaarten; i++)
            System.out.print(kolom[i] + " ");
        System.out.println("\n");
    }
    
}
