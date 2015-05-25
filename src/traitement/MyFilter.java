/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traitement;

import java.io.File;

/**
 * 
 * @author IMRAN-DIALLO
 */
public class MyFilter extends javax.swing.filechooser.FileFilter {

        @Override
        public boolean accept(File file) {
            return file.isDirectory() || file.getAbsolutePath().endsWith(".csv");
        }

        @Override
        public String getDescription() {
            return "fichiers (*.csv)";
        }
    }