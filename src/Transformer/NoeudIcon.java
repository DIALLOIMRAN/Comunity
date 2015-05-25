package Transformer;


import graph.Noeud;
import graph.Noeud;
import org.apache.commons.collections15.Transformer;

import javax.swing.*;

class NoeudIcon implements Transformer <Noeud, Icon>
{
    public int getHeight()
    {
        return (20);
    }
    public int getWidth()
    {
        return (20);
    }

    @Override
    public Icon transform(Noeud v)
    {
        return (new ImageIcon(v.getValue()+".jpg"));
    }
}
