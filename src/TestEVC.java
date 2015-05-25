/**
 * 
 */

import edu.uci.ics.jung.graph.Graph;
import graph.GraphView;
import graph.Lien;
import graph.Noeud;
import graph.MyGraph ;
import traitement.MyHashTable;
/**
 * 
 * @author IMRAN-DIALLO
 */
public class TestEVC {

	
	public static void main(String[] args)
	{
            int [][] matr ={    {0,1,1,1,0,1,1},
                                {1,0,0,1,1,1,1},
                                {1,1,0,0,0,1,1}, 
                                {0,1,1,0,1,0,0},
                                {1,0,0,1,1,0,0},
                                {0,0,1,1,0,0,0},
                                {1,1,1,0,0,0,0}
                            } ;
            MyGraph g = new MyGraph(matr) ;
            
            
            Graph <Noeud, Lien> graph = g.createGraph();
       
            
            GraphView view = new GraphView(matr);
            
            
            MyHashTable hash = new MyHashTable (graph, g) ;
            
            //hash.communauté1(hash.trierParValeur(hash.calculCentralité(g.getNoeuds()))) ;
            hash.communauté1(hash.calculCentralité(g.getNoeuds())) ;
	}
}
