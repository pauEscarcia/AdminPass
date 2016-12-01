/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.JFGenerator;

/**
 *
 * @author pauEscarcia
 */
public class ControladorGenerator implements ActionListener{
    JFGenerator vistaLoc = new JFGenerator();
    
    public ControladorGenerator( JFGenerator vistaLoc){
        this.vistaLoc = vistaLoc;
    }
    
    public void actionPerformed(ActionEvent e) {
        
    }
    
   
    
}
