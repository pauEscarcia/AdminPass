/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adminpassword;
import modelo.*;
import vista.*;
import controlador.*;


/**
 *
 * @author pauEscarcia
 */
public class AdminPassword {
    JFLocket vistaLoc = new JFLocket ();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       JFInicio vistaC= new JFInicio();
       ControladorInicio controlaC= new ControladorInicio(vistaC);
       vistaC.setVisible(true);
       vistaC.setLocationRelativeTo(null);
       
    }
    
}
