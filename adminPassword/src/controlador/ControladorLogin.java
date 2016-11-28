/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.LogDAO;
import vista.JFLogin;
import vista.JFMasterKey;

/**
 *
 * @author pauEscarcia
 */
public class ControladorLogin implements ActionListener {
    JFLogin vistaLoc = new JFLogin ();
    
    public ControladorLogin (JFLogin vistaLoc){
        this.vistaLoc = vistaLoc;
        this.vistaLoc.btnCrear.addActionListener(this);
    }
    public ControladorLogin(){
    
    }

    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaLoc.btnCrear){
            JFMasterKey vistaC = new JFMasterKey();
            LogDAO modelC= new LogDAO();
            ControladorMasterKey controlaC= new ControladorMasterKey(vistaC,modelC);
        
            vistaC.setVisible(true);
            vistaC.setLocationRelativeTo(null);
        }
    }
}
