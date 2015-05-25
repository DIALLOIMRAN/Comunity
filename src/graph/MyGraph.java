package graph;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import cern.colt.matrix.DoubleFactory1D;
import cern.colt.matrix.DoubleFactory2D;
import cern.colt.matrix.DoubleMatrix1D;
import cern.colt.matrix.DoubleMatrix2D;
import cern.colt.matrix.linalg.EigenvalueDecomposition;
import edu.uci.ics.jung.algorithms.scoring.EigenvectorCentrality;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import java.awt.Color;
/**
 * 
 * @author IMRAN-DIALLO
 */
public class MyGraph {
private int [][] matriceAdjacence ;
    private Noeud [] noeuds ;

    public Noeud[] getNoeuds() {
        return noeuds;
    }

    public void setNoeuds(Noeud[] noeuds) {
        this.noeuds = noeuds;
    }

    
    public void setMatriceAdjacence(int[][] matriceAdjacence) {
        this.matriceAdjacence = matriceAdjacence;
        
    }

    public MyGraph() {
    }

    public int[][] getMatriceAdjacence() {
        return matriceAdjacence;
    }
    
    

    public MyGraph(int[][] matriceAdjacence) {
        this.matriceAdjacence = matriceAdjacence;
        noeuds = new Noeud[matriceAdjacence.length] ;
    }
    
    public Graph <Noeud,Lien> createGraph() {
        
        Graph <Noeud,Lien> g = new UndirectedSparseGraph<Noeud,Lien>();
//        String[] mIcons;
//        mIcons = new String[4];
//        mIcons[0] = "chaimae";
//        mIcons[1] = "hamza";
//        mIcons[2] = "lamine";
//        mIcons[3] = "ismael";
//      
        for(int i=0; i<matriceAdjacence.length; i++){
            
            
            Noeud n = new Noeud(i+1, Color.WHITE);
            g.addVertex(n) ;
            noeuds [i] = n ;
        }
        
        for(int i=0; i<matriceAdjacence.length; i++){
            for(int j=0; j<matriceAdjacence[0].length; j++){
                if(matriceAdjacence[i][j] != 0 && j>i){
                    g.addEdge(new Lien("e"+i*matriceAdjacence.length+j), noeuds[i], noeuds[j]);
                }
            }
        }
        return g;
	}
	
    public void afficheNoeud(){
        for (Noeud noeud : noeuds) {
            System.out.print(noeud+"  ");
        }
        System.out.println("  ");
    }
    
    public void calculCentralité() {
        Graph <Noeud,Lien> g = this.createGraph() ;
        EigenvectorCentrality<Noeud, Lien> evc;
        
        
        
        UndirectedSparseGraph <Noeud, Lien> myGraph = (UndirectedSparseGraph) g ;
	evc = new EigenvectorCentrality <Noeud, Lien>(myGraph);		
        evc.evaluate();
        
		
        int n, i, j;
		DoubleFactory2D Factory2D = DoubleFactory2D.sparse;
		DoubleFactory1D Factory1D = DoubleFactory1D.dense;
                
		DoubleMatrix2D adjMatrix, eigVectors;
		DoubleMatrix1D eigValues, myEvc;
                
		EigenvalueDecomposition eigDecomp;
		
		n = myGraph.getVertexCount();
		adjMatrix = Factory2D.make(n, n, 0.0d);
		myEvc = Factory1D.make(n, 0.0d);
		
		// Cheap way of putting together the adjacency matrix
		for(i = 0;i < n;++i)
		{
			//set diagonal
			adjMatrix.set(i, i, 1.0d);
			
			if(i < 8)
			{
				adjMatrix.set(0, i, 1.0d);
				adjMatrix.set(i, 0, 1.0d);
			}
			
			if(i >= 8)
			{
				adjMatrix.set(7, i, 1.0d);
				adjMatrix.set(i, 7, 1.0d);
			}
		}
		// Done with the adjacency matrix
		
		eigDecomp = new EigenvalueDecomposition(adjMatrix);
		eigValues = eigDecomp.getRealEigenvalues();
		eigVectors = eigDecomp.getV();
		double max = 0.0d;
		double tmpDouble;
		int idx = -1;
		for(i = 0;i < n;++i)
		{
			tmpDouble = Math.abs(eigValues.get(i));
			if(tmpDouble > max)
			{
				max = tmpDouble;
				idx = i;
			}
		}
		for(i = 0;i < n;++i)
		{
			myEvc.set(i, Math.abs(eigVectors.get(i, idx)));
		}
		
		
		/*Collection<String> nodes = myGraph.getVertices();
		Iterator<String> nodeItr = nodes.iterator();
		String tmp;
		Double score;
		System.out.println("Eigenvector Centrality (JUNG)");
		while(nodeItr.hasNext())
		{
			tmp = nodeItr.next();
			score = evc.getVertexScore(tmp);
			System.out.println("evc(" + tmp + ") = " + score);
		}*/
		
                    
                    Double jungScore, myScore;
                    
                    for(int ii=0; ii<myGraph.getVertexCount(); ii++){
                    
                    jungScore = evc.getVertexScore(noeuds[ii]) ;
                    //myScore = evc.get(ii-1) ;
                    System.out.println("centralité("+noeuds[ii]+") = "+jungScore);
                 }

    
}
    
    
}