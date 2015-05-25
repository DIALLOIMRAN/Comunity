/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Transformer;

import edu.uci.ics.jung.visualization.decorators.AbstractVertexShapeTransformer;
import graph.Noeud;
import java.awt.Shape;
import java.util.Hashtable;
import org.apache.commons.collections15.Transformer;

/**
 *
 * @author IMRAN-DIALLO
 */
public class NoeudShape extends AbstractVertexShapeTransformer <Noeud> implements Transformer <Noeud, Shape>{

    private Hashtable <Noeud, Noeud> mNodeDataTable;

    public NoeudShape(Hashtable<Noeud, Noeud> mNodeDataTable) {
        this.mNodeDataTable = mNodeDataTable;
    }
    
    @Override
    public Shape transform(Noeud i) {
        
            if(mNodeDataTable.get(i).getValue() == "leader")
            {
                setSizeTransformer(new Transformer<Noeud, Integer>()
                {
                    public Integer transform(Noeud node)
                    {
                        return 40; //Set all nodes to size 50, which is the same as our icons.
                    }
                });
                return factory.getRegularPolygon(i, 4); 
            }
        setSizeTransformer(new Transformer<Noeud, Integer>()
                {
                    public Integer transform(Noeud node)
                    {
                        return 20; //Set all nodes to size 50, which is the same as our icons.
                    }
                });
        return factory.getEllipse(i);
        
    }
    
}
