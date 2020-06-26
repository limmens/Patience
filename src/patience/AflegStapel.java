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
public class AflegStapel extends Stapel
{
    public AflegStapel()
    {
        nKaarten = 0;
        this.add(leegLabel);
    }
    
    @Override
    public void setAangeklikt(boolean a)
    {
        aangeklikt = a;
        if(nKaarten > 0)
            bovensteKaart.setAangeklikt(a);
    }
    
    @Override
    public String toString()
    {
        return "Aflegstapel";
    }
    
}
