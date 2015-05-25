/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traitement;

import cern.colt.matrix.DoubleFactory1D;
import cern.colt.matrix.DoubleFactory2D;
import cern.colt.matrix.DoubleMatrix1D;
import cern.colt.matrix.DoubleMatrix2D;
import cern.colt.matrix.linalg.EigenvalueDecomposition;
import edu.uci.ics.jung.algorithms.scoring.EigenvectorCentrality;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import graph.Lien;
import graph.MyGraph;
import graph.Noeud;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author IMRAN-DIALLO
 */
public class MyHashTable {
    
    private Graph<Noeud,Lien> graphe ;
    private int taille = 0;
    MyGraph mg ;
    ArrayList <Noeud> Leaders = new ArrayList<>();
    ArrayList <ArrayList <Noeud>> voisins = new ArrayList<>();
    ArrayList <Color> lesCouleurs = new ArrayList<>();

    public MyHashTable(MyGraph mg) {
        this.mg = mg;
    }
    private Color genererCouleur(){
        Color randomColour;
        do {
            Random randomGenerator = new Random();
            int red = randomGenerator.nextInt(256);
            int green = randomGenerator.nextInt(256);
            int blue = randomGenerator.nextInt(256);
            randomColour = new Color(red,green,blue);
            
            
        } while(lesCouleurs.contains(randomColour));
        return randomColour ;
    }
    
    private Graph<Noeud,Lien> grapheCommunauté ;
    int [][] matriceFinal ;

    public int[][] getMatriceFinal() {
        return matriceFinal;
    }
    
    public void setGrapheCommunauté (Graph<Noeud, Lien> grapheCommunauté) {
        this.grapheCommunauté = grapheCommunauté;
    }

    public Graph <Noeud, Lien> getGrapheCommunauté() {
        return grapheCommunauté;
    }
    
    public MyHashTable(Graph<Noeud, Lien> graphe, MyGraph mg) {
        this.mg = mg ;
        this.graphe = graphe;
        this.taille = graphe.getVertexCount() ;
        matriceFinal = new int[taille][taille] ;
        
    }
    
    /**
     * Reçoi en parametre une table de hachage non ordonnée
     * @param map et renvoi une table triée par ordre décroissante des 
     * valeurs de centralités des noeuds
     * @return map_apres
     */
    public HashMap <Noeud, Double> trierParValeur(HashMap <Noeud, Double> map ){
       List<Map.Entry<Noeud, Double>> list = new LinkedList<>( map.entrySet() );
      
       Collections.sort(list, new Comparator<Map.Entry<Noeud, Double>>(){
          @Override
          public int compare( Map.Entry<Noeud, Double> o1, Map.Entry<Noeud, Double> o2 ){
              return (o2.getValue()).compareTo( o1.getValue());
          }
       });
       
       HashMap<Noeud, Double> map_apres = new LinkedHashMap<>();
       for (Map.Entry<Noeud, Double> entry : list)
         map_apres.put( entry.getKey(), entry.getValue() );
       System.out.println("After sorting ...");
       this.afficherMap(map_apres);
       return map_apres;
    }
    
    public void afficheCom1(HashMap <Noeud, ArrayList <Noeud>> communauté){
        
        
        for (HashMap.Entry <Noeud, ArrayList <Noeud>> entry : communauté.entrySet())
        {
            if(entry.getValue().size() != 0){
                System.out.print("noued : "+entry.getKey()+". Ses voisins : ");
               for (Noeud col : entry.getValue()) {
                   System.out.print(col+" ");
                   
                }
                System.out.println(" ");
            }else{
                System.out.println("noued : "+entry.getKey()+" n'est pas un leader !");
            }
                
        }
        
        
    }
    
    /**
     * Reçoi en parametre les noeuds et leurs centralités
     * @param centralité ordonnés  par ordre décroissante des 
     * valeurs de leurs centralité pour renvoyé @return les noeuds leaders
     * accompagnés de leurs vois sous forme d'un hashtable
     * @return 
     */
    
    public HashMap <Noeud, ArrayList <Noeud>> communauté1 (HashMap <Noeud, Double> centralité){        
        
        
        HashMap <Noeud, ArrayList <Noeud>> touslesVoisins = new HashMap<>() ;
        
        ArrayList <Noeud> keys = new ArrayList<>();
        for (HashMap.Entry <Noeud, Double> entry : centralité.entrySet())
        {
           keys.add(entry.getKey());
        }
        System.out.println("=================================================");
        for (Noeud key : keys) {
            System.out.print(" "+key);
        }
        System.out.println("\n================================================");
        for (int ii = 0; ii < keys.size(); ii++)
        {
            
            //int i = 0 ;
            Noeud n = keys.get(ii);
            boolean trouvee = false ;
           
            for (HashMap.Entry <Noeud, ArrayList <Noeud>> entry : touslesVoisins.entrySet()){
                for (Noeud v1 : entry.getValue()) {
                    if(v1.equals(n))
                        trouvee =  true ;
                }
            } 
            
            if(trouvee == false){
                Collection <Noeud> lesVoisin = graphe.getNeighbors(keys.get(ii)) ;
                ArrayList <Noeud> v = new ArrayList <> (lesVoisin) ;
                if(v.size() !=0){
                    int i = 0 ;
                    for (HashMap.Entry<Noeud, ArrayList <Noeud>> entry : touslesVoisins.entrySet()){
                        for (Noeud v1 : entry.getValue()) {
                            if(i< v.size() && v1.equals(v.get(i)))
                                v.remove(v1) ;
                        }
                    } 
                touslesVoisins.put(keys.get(ii), v) ;
                }     
            }
        }

        System.out.println("Avant");
        this.afficheCom1(touslesVoisins);
        System.out.println("Après");
        return touslesVoisins ;
    }
    
    /**
     * Reçoi en parametre les communautés 
     * @param communauté et construite le réseau en fonction des communautés
     */
    
    public void formerCommunauté(HashMap <Noeud, ArrayList <Noeud>> communauté) {
         grapheCommunauté = new UndirectedSparseGraph<>();
        
        //int [][] mat = new MyGraph(matriceFinal);
        Noeud [] noeuds = new Noeud[mg.getMatriceAdjacence().length] ;
        int cpt = 0;
        for (HashMap.Entry <Noeud, ArrayList <Noeud>> entry : communauté.entrySet())
        {
            Noeud leader = entry.getKey() ;
            ArrayList <Noeud> followers = entry.getValue() ;
            Color couleur ;
            if(followers.size() == 0){
                couleur = Color.WHITE ;
                leader.setCouleur(couleur);
                grapheCommunauté.addVertex(leader);
                if(cpt<noeuds.length){
                    noeuds[cpt] = leader ;
                    cpt++;
                }
                
            }else{
                couleur = this.genererCouleur();
                leader.setCouleur(couleur);
                leader.setValue("leader");
                lesCouleurs.add(couleur);
                grapheCommunauté.addVertex(leader);
                if(cpt<noeuds.length){
                    noeuds[cpt] = leader ;
                    cpt++;
                }
                for (Noeud noeud : entry.getValue()) {
                    noeud.setCouleur(leader.getCouleur());
                    grapheCommunauté.addVertex(noeud) ;
                    if(cpt<noeuds.length){
                    noeuds[cpt] = leader ;
                        cpt++;
                    }
                }
            }
        }
        int i = 0;
        for (HashMap.Entry <Noeud, ArrayList <Noeud>> entry : lesLiens().entrySet())
        {
               for (Noeud noeud : entry.getValue()) {
                   if(!entry.getKey().isWhite()){
                       if(!noeud.isWhite())
                            grapheCommunauté.addEdge(new Lien("e"+i), entry.getKey(), noeud);
                   }
                }
        }
    }
    
    private HashMap <Noeud, ArrayList <Noeud>> lesLiens(){
        HashMap <Noeud, ArrayList <Noeud>> voisins = new HashMap<>() ;
        ArrayList <Noeud> noeuds = new ArrayList<>(graphe.getVertices()) ;
        
        for (Noeud noeud : noeuds) {
           voisins.put(noeud, new ArrayList<>(graphe.getNeighbors(noeud))) ;
        }
        
        return voisins ;
                
    }
    
    public Collection <Noeud> lesVoisins(Noeud n){
        Collection <Noeud> m = this.graphe.getNeighbors(n) ;
        System.out.print("noeud1 : "+n+", voisins :");
        for (Noeud m1 : m) {
            System.out.print(" "+m1);
        }
        System.out.println("");
        return m ;
    }
    
    public void afficherVoisins (Hashtable <Noeud, Collection <Noeud>> voisins) {
        
        System.out.println("--------------------------------");
        Set set1 = voisins.entrySet() ;
        Iterator it = set1.iterator();
            while(it.hasNext()) {
                Map.Entry me = (Map.Entry)it.next();
                System.out.print(me.getKey() + ": ");
                for (Noeud set11 : (Collection <Noeud>) me.getValue()) {
                    System.out.println(set11);
                }}
        System.out.println("--------------------------------");
    }
    
    public void afficherMap(HashMap <Noeud, Double> centralité) {
        Set set1 = centralité.entrySet() ;
        System.out.println("--------------------------------");
            Iterator it = set1.iterator();
            while(it.hasNext()) {
                Map.Entry me = (Map.Entry)it.next();
                System.out.print(me.getKey() + ": ");
                java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
                System.out.println(df.format(me.getValue()));
            }
        System.out.println("--------------------------------");
    }
   
    /**
     * Reçoi en parametre les noeuds du graphe courant
     * @param noeuds
     * pour renvoyé @return sous forme de hashtable 
     * la liste des noeuds accompagné de leurs degré de centralité 
     * @return 
     */
    
    public HashMap <Noeud, Double> calculCentralité(Noeud[] noeuds) {
         try {
            EigenvectorCentrality <Noeud, Noeud> evc;
            HashMap <Noeud, Double> lesCentralités = new HashMap<>() ;
            UndirectedSparseGraph <Noeud, Noeud> myGraph = (UndirectedSparseGraph) graphe ;
            evc = new EigenvectorCentrality <Noeud, Noeud>(myGraph);
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
		  
                Double score ;
                for(int ii=0; ii<myGraph.getVertexCount(); ii++){
                    score = evc.getVertexScore(noeuds[ii]) ;
                    lesCentralités.put(noeuds[ii], score) ;
                }
               System.out.println("=========== Avant le tri : ============");
               this.afficherMap(lesCentralités);
               System.out.println("=========== Après le tri : ============");
               lesCentralités = this.trierParValeur(lesCentralités);
               return lesCentralités ;
        } catch (Exception e) {
             System.out.println("erreur : "+e.getMessage());
            return  null ;
        }
        
    }
    
    public ArrayList<ArrayList<String>> tableCentralité(HashMap <Noeud, Double> centralité){
        
        ArrayList<ArrayList<String>> retour = new ArrayList<>() ;
        for (HashMap.Entry <Noeud, Double> entry : centralité.entrySet())
        {
            ArrayList<String> lignes = new ArrayList<>() ;
            lignes.add(entry.getKey().toString());
            java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
            lignes.add(df.format(entry.getValue())+"");
            
            retour.add(lignes) ;
        }
        
        return retour;
    }
    
    public ArrayList<ArrayList<String>> tableCommunauté(HashMap <Noeud, ArrayList <Noeud>> centralité){
        
        ArrayList<ArrayList<String>> retour = new ArrayList<>() ;
        for (HashMap.Entry <Noeud, ArrayList <Noeud>> entry : centralité.entrySet())
        {
            ArrayList<String> lignes = new ArrayList<>() ;
            lignes.add(entry.getKey().toString());
            String voisins = "" ;
            int i=0;
            for (Noeud ligne : entry.getValue()) {
                if(i != 0)
                    voisins += ", ";
                voisins += ligne.toString();
                
                i++;
            }
            lignes.add(voisins) ;
            retour.add(lignes) ;
        }
        
        return retour;
    }
}
