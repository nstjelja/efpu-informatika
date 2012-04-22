/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.efpu.informatika.currencymanager.view;

import hr.efpu.informatika.currencymanager.domain.CurrencyCode;
import hr.efpu.informatika.currencymanager.domain.ValidationException;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;


/**
 *
 * @author nikola
 */
public class CurrencyManager extends javax.swing.JFrame {

    private GridLayout gridLayout = null;
    
    private JPanel pnlLeft = null;
    private JPanel pnlRight = null;
    
    private CurrencyManagerViewModel viewModel = null;
    
    /**
     * Creates new form CurrencyManager
     */
    public CurrencyManager() {
        viewModel = new CurrencyManagerViewModel();
        
        final JFrame self = this;
        final CurrencyManagerViewModel selfViewModel = viewModel;
        final HashMap<CurrencyCode, JTextField> amounts = new HashMap<CurrencyCode, JTextField>();
        final HashMap<CurrencyCode, JTextField> exchanges = new HashMap<CurrencyCode, JTextField>();
        
        initComponents();
        
        gridLayout = new GridLayout(1,2);
        getContentPane().setLayout(gridLayout);
        
        pnlLeft = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlRight = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        getContentPane().add(pnlLeft);
        getContentPane().add(pnlRight);
        
        
        HashMap<CurrencyCode, String> amountFields = new HashMap<CurrencyCode, String>(){{
          put(CurrencyCode.HRK,"kn");
          put(CurrencyCode.EUR,"€");
          put(CurrencyCode.USD,"$");
          put(CurrencyCode.YEN,"Y");
        }};
        
        for(Map.Entry<CurrencyCode,String> entry : amountFields.entrySet()){
            JTextField field = addField("0.00",entry, pnlLeft, new FocusAdapter() {
                public void focusGained(FocusEvent e) {

                }

                public void focusLost(FocusEvent e) {
                    JTextField field = (JTextField) e.getComponent();
                    
                    CurrencyCode codeSet = CurrencyCode.valueOf(field.getName());

                    try{ 
                        HashMap<CurrencyCode,String> result = selfViewModel.setAmount(codeSet, field.getText());
                    
                        for(Map.Entry<CurrencyCode,String> value : result.entrySet()){
                            JTextField txtAmountField = amounts.get(value.getKey());
                            txtAmountField.setText(value.getValue());
                    
                        }
                    }
                    catch(Exception ex){
                           JOptionPane.showMessageDialog(self, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                           field.setText("0.00");
                           focusLost(e);
                    }
                  
                } });
            amounts.put(entry.getKey(),field);
           
        }
        
         HashMap<CurrencyCode, String> exchangeFields = new HashMap<CurrencyCode, String>(){{
          put(CurrencyCode.EUR,"€//Kn");
          put(CurrencyCode.USD,"$//Kn");
          put(CurrencyCode.YEN,"Y//Kn");
        }};
        
        
        JEditorPane lblNotice  = new JEditorPane() {{
            setName("lblNotice");
            setText("Currency exchanges \nbased on HRK");
            setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
            setBackground(self.getBackground());
            setForeground(Color.BLACK);
            setEditable(false);
         
            setSize(150, 30);
        }};
        
        pnlRight.add(lblNotice);
         
        for(Map.Entry<CurrencyCode,String> entry : exchangeFields.entrySet()) {
           JTextField field =  addField("1.00",entry, pnlRight, new FocusAdapter() {
             public void focusGained(FocusEvent e) {
     
             }
             
             public void focusLost(FocusEvent e) {
                 JTextField field = (JTextField) e.getComponent();
                
                try{ 
                     CurrencyCode codeSet = CurrencyCode.valueOf(field.getName());
                    selfViewModel.setExchange(codeSet, field.getText());
                    for(Map.Entry<CurrencyCode,JTextField> item : amounts.entrySet()) item.getValue().setText("0.00");
                }
                catch(Exception ex){
                     JOptionPane.showMessageDialog(self, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                       field.setText("1.00");
                       focusLost(e);
                }
             }
        });
        
         exchanges.put(entry.getKey(),field);
        }
        
    }
    
    private JTextField addField(String defaultValue, Map.Entry<CurrencyCode,String> entry, JPanel masterPanel, FocusAdapter focusAdapter){
          JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JTextField field = new JTextField();
            field.setName(entry.getKey().toString());
            field.addFocusListener(focusAdapter);
            field.setColumns(10);
            field.setText(defaultValue);
            JLabel lbl = new JLabel(entry.getValue());
            
            panel.add(field);
            panel.add(lbl);
            
            masterPanel.add(panel);
            
            return field;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Currency Manager");
        setAlwaysOnTop(true);
        setMaximumSize(new java.awt.Dimension(350, 150));
        setName("frmCurrencyManager");
        setPreferredSize(new java.awt.Dimension(350, 150));
        setResizable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
