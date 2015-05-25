 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

/**
 *
 * @author ZAHDI
 */
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.*;


public class PanelMatrice extends JPanel {
    

  public ArrayList <JTextField>  boutons( Integer l , Integer c , JPanel M ) 
  {      
        setLayout(null);
    
        ArrayList <Integer> n = new ArrayList();
        
        
        int nbrBtn = l.intValue()*c.intValue();
         ArrayList <JTextField> tab = new ArrayList(nbrBtn); 
         
         for(  int i=0 ; i<nbrBtn ; i++ )
        {     
            JTextField T = new JTextField(i);
            
            tab.add(T);
            
        }

         for( JTextField T : tab )
        {
           M.add(T);
        }
        ArrayList<JLabel> tabi = new ArrayList(nbrBtn); 
         
        for(  int i=0 ; i<l ; i++ )
        {     
            JLabel T=new JLabel("  N"+(i+1));
            T.setForeground(Color.WHITE);
            tabi.add(T);
        }

         for(  int i=0 ; i<c ; i++ )
        {     
            JLabel T=new JLabel("  N"+(i+1));
            T.setForeground(Color.WHITE);
            tabi.add(T);
        }
       
         for( JLabel T : tabi )
        {
           M.add(T);
        } 
        
        int k=0;
        int h=50;
        int dim=350;
        int g=0;
        for( int i=0  ; i<=c.intValue() && k<nbrBtn; i++ ){
            for( int j=0 ; j<=l.intValue() && k<nbrBtn ; j++){
                if(i==0){ if(j==0) continue;
                    int li=(dim/(2*(l.intValue()+1)))-18+ j*(dim/(l.intValue()+1));
                    tabi.get(g).setBounds( li , h , 35 , 25);  
                    g++;  
                    } else {
                    if (j==0){
                    int li=(dim/(2*(l.intValue()+1)))-18+ j*(dim/(l.intValue()+1));
                    tabi.get(g).setBounds( li , h , 35 , 25);  
                    g++; j++;
                    }
                    int li=(dim/(2*(l.intValue()+1)))-18+ j*(dim/(l.intValue()+1));
                    tab.get(k).setBounds( li , h , 35 , 25);  
                    k++; } 
                }
            h+=25;
            } 
      return tab;       
        }  
  
  
  
  }

  