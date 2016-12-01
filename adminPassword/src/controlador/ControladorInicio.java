/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.JFInicio;
import vista.JFLogin;

/**
 * @author pauEscarcia
 */
public class ControladorInicio implements ActionListener{
    JFInicio vistaLoc = new JFInicio();
  
     public ControladorInicio(JFInicio vistaLoc){
        
        this.vistaLoc = vistaLoc;
        this.vistaLoc.btnBienvenido.addActionListener(this);
    }
    
    public ControladorInicio(){
    
    }
    
    public void actionPerformed(ActionEvent e) {
       if (e.getSource() == vistaLoc.btnBienvenido){  
           JFLogin vistaC = new JFLogin();
           ControladorLogin controlaC= new ControladorLogin(vistaC);
           vistaC.setVisible(true);
           vistaC.setLocationRelativeTo(null);
       }
    }
    
}
