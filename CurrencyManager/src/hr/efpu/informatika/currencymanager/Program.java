/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.efpu.informatika.currencymanager;

import hr.efpu.informatika.currencymanager.view.CurrencyManager;

/**
 *
 * @author nikola
 */
public class Program {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new CurrencyManager().setVisible(true);
            }
        });
    }
}
