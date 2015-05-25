package Transformer ;

import graph.Noeud;
import org.apache.commons.collections15.Transformer;

import java.util.Hashtable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author IMRAN-DIALLO
 */
public class labelNoeud implements Transformer <Noeud, String>
{
    private Hashtable <Noeud, Noeud> mNodeDataTable;

    // It is necessary to provide our hashTable to this function so that we can access our class specific data.

    public labelNoeud (Hashtable<Noeud, Noeud> mNodeDataTable) {
        this.mNodeDataTable = mNodeDataTable;
    }

    @Override
    public String transform(Noeud i) {
        if((i.getValue()).equalsIgnoreCase("leader"))
            return mNodeDataTable.get(i).toString()+ " : L";
        return mNodeDataTable.get(i).toString() ;
    }

    /**
     *
     * @param i
     * @return
     */
 

    

    
}
