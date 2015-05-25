/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traitement ;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author hp
 */
public class MonModele extends AbstractTableModel{
    private int nbColonnes  = 0;
    private int nbLignes = 0;
    private String []titres ;
    private ArrayList <ArrayList <String>> mesLignes = new ArrayList <>();

    public MonModele(ArrayList <ArrayList<String>> data) {
        
        try {
            nbColonnes = data.get(0).size() ;
            
            titres = new String[nbColonnes] ;
            
            for (int i=0; i < nbColonnes; i++)
                titres[i] = data.get(0).get(i) ;
            
            ArrayList <String> ligne ;
            
            for(ArrayList <String> lignei: data){
                ligne = new ArrayList <>() ;
                for (int i = 1; i < nbColonnes; i++) {
                    
                    ligne.add(lignei.get(i)) ;
                }
                mesLignes.add(ligne) ;
                nbLignes ++ ;
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "une erreur est surnenue : " +e.getMessage());
        }
    }

    @Override
    public int getRowCount() {
        return nbLignes ;
    }

    @Override
    public int getColumnCount() {
        return nbColonnes ;
    }

    @Override
    public String getValueAt(int i, int i1) {
        return mesLignes.get(i).get(i1) ;
    }

    @Override
    public String getColumnName(int i) {
        return titres[i]; 
    } 
}
