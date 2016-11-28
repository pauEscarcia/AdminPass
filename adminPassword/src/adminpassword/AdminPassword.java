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
        // TODO code application logic here
        /*
       JFMasterKey vistaC = new JFMasterKey();
       LogDAO modelC= new LogDAO();
       ControladorMasterKey controlaC= new ControladorMasterKey(vistaC,modelC);
        
       vistaC.setVisible(true);
       vistaC.setLocationRelativeTo(null);
       */
       
       
       //JFLocket vistaC = new JFLocket();
       //AlmacenDAO modelC = new AlmacenDAO();
       //ControladorLocket controlaC= new ControladorLocket(vistaC,modelC);
       
       //vistaC.setVisible(true);
       //vistaC.setLocationRelativeTo(null);
       
       JFInicio vistaC= new JFInicio();
       ControladorInicio controlaC= new ControladorInicio(vistaC);
       vistaC.setVisible(true);
       vistaC.setLocationRelativeTo(null);
       
    }
    
}
