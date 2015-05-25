/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traitement;

/**
 *
 * @author IMRAN-DIALLO
 */
public class ValidationMatrice {
    
    public static String VerificationMatrice(int [][] tab) 
    {
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[0].length; j++) {
                if(i==j){
                    if(tab[i][j] != 0)
                        return "la diagonale de la matrice d'adjacence doit etre nulle" + tab[i][j];
                }
                if(tab[i][j] != 0 && tab[i][j] != 1){
                    return "la matrice ne doit avoir que 1 ou 0 comme valeur \n"
                            + "1 : pour dire qu'il ya de lien entre deux noeuds du réseau\n"
                            + "0 : pour dire qu'il n'ya pas de lien : " + tab[i][j];
                }
                if(tab[i][j] != tab[j][i]){
                    return "asymetrique" ;
                }
            }
        }
        return "ok";
    }
}
