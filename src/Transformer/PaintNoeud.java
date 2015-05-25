package Transformer ;

import graph.Noeud;
import org.apache.commons.collections15.Transformer;

import java.awt.*;
import java.util.Hashtable;
/**
 * 
 * @author IMRAN-DIALLO
 */
public class PaintNoeud implements Transformer <Noeud, Paint>
{
    private Hashtable <Noeud, Noeud> mNodeDataTable;

    public PaintNoeud(Hashtable<Noeud, Noeud> nodeDataTable)
    {
        mNodeDataTable = nodeDataTable;
    }
    public Paint transform (Noeud v) //So for each node that we draw...
    {
        return mNodeDataTable.get(v).getCouleur();
    }
}

