/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import Transformer.*;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Hashtable;
import javax.swing.JFrame;

/**
 *
 * @author IMRAN-DIALLO
 */
public class GraphView {
    private Hashtable <Noeud, Noeud> nodeTable = new Hashtable<>();
    private Hashtable <Noeud, Color> nodeCTable = new Hashtable<>();
    private int [][] matriceAdjacence ;
    private MyGraph mygraph ;
    Graph<Noeud, Lien> g ;

    public Graph<Noeud, Lien> getG() {
        return g;
    }

    public void setMygraph(MyGraph mygraph) {
        this.mygraph = mygraph;
    }
    
    
    public MyGraph getMygraph() {
        return mygraph;
    }
    
    public int[][] getMatriceAdjacence() {
        return matriceAdjacence;
    }

    public void setMatriceAdjacence(int[][] matriceAdjacence) {
        this.matriceAdjacence = matriceAdjacence;
    }
    
    
    public Hashtable <Noeud, Noeud> getNodeTable() {
        return nodeTable;
    }

    public void setNodeTable(Hashtable<Noeud, Noeud> nodeTable) {
        this.nodeTable = nodeTable;
    }


    private void PopulateNodeDataTable()
    {
        Noeud [] noeuds = mygraph.getNoeuds() ;
        for (Noeud noeud : noeuds) {
           nodeTable.put(noeud, noeud) ;
        }
    }

    private void constructFrame(BasicVisualizationServer <Noeud, Lien> panel){
            
        panel.getRenderContext().setVertexLabelTransformer(new labelNoeud(nodeTable));
        panel.getRenderContext().setVertexFillPaintTransformer(new PaintNoeud(nodeTable));
            
        panel.setBackground(new java.awt.Color(0, 162, 232));
        panel.setForeground(Color.WHITE);
        final JFrame frame = new JFrame("Graph View");
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setForeground(Color.WHITE);
        frame.setResizable(false);
        frame.setVisible(true);
    }
    public GraphView(Graph<Noeud, Lien> gr, MyGraph my) {
        this.mygraph = my ;
        PopulateNodeDataTable();
        Dimension viewArea = new Dimension(500, 450);

        Layout <Noeud,Lien> layout = new RandomLayout <>(gr, viewArea);
        BasicVisualizationServer <Noeud, Lien> panel = new BasicVisualizationServer<>(layout, viewArea);

        panel.getRenderContext().setVertexShapeTransformer(new NoeudShape(nodeTable));

        //panel.getRenderContext().setVertexIconTransformer(new NoeudIcon());

        this.constructFrame(panel);
    }
    
    public GraphView(int [][] matriceAdjacence) {
        this.matriceAdjacence = matriceAdjacence ;
        mygraph = new MyGraph(matriceAdjacence) ;
        g = mygraph.createGraph() ;
        
        
        PopulateNodeDataTable();
        Dimension viewArea = new Dimension(500, 450);
        
        
            //Graph <Noeud,Lien> graph = g.createGraph();
            Layout <Noeud,Lien> layout = new RandomLayout <>(g, viewArea);
            BasicVisualizationServer <Noeud, Lien> panel = new BasicVisualizationServer<>(layout, viewArea);
           
            
            //panel.getRenderContext().setVertexIconTransformer(new NoeudIcon());
            
            this.constructFrame(panel);
    } 
}
