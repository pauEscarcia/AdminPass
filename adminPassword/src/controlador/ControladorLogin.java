/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.AlmacenDAO;
import modelo.LogDAO;
import vista.JFLocket;
import vista.JFLogin;
import vista.JFMasterKey;

/**
 *
 * @author pauEscarcia
 */
public class ControladorLogin implements ActionListener {
    JFLogin vistaLoc = new JFLogin ();
    LogDAO modeloLoc = new LogDAO();
    String idKey;
    
    public ControladorLogin (JFLogin vistaLoc){
        this.vistaLoc = vistaLoc;
        this.vistaLoc.btnCrear.addActionListener(this);
        this.vistaLoc.btnOk.addActionListener(this);
    }
    public ControladorLogin(){
    
    }

    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaLoc.btnOk){
            String masterkeyInput = vistaLoc.txtKeyss.getText();
            
            int numRegistros = modeloLoc.listLogMasterKey(masterkeyInput).size();
           //trae los registros que estan dados de alta con esta masterkey 
           //que pasa si esa masterkey esta dada de alta muchas veces, que solo traiga la ultima vez que la registro 
           if(numRegistros>1){
               //System.out.println( "ultimo registro de contraseña multiple:"+modeloLoc.listLogMasterKey(masterkeyInput).get(numRegistros-1).getIdKey());
               idKey= modeloLoc.listLogMasterKey(masterkeyInput).get(numRegistros-1).getIdKey().toString();
               //abrir locket
               JFLocket vistaC = new JFLocket();
               AlmacenDAO modelC = new AlmacenDAO();
               ControladorLocket controlaC= new ControladorLocket(vistaC,modelC,idKey);
               vistaC.setVisible(true);
               vistaC.setLocationRelativeTo(null);
           }
           if(numRegistros==0){
               JOptionPane.showMessageDialog(null, "Contraseña Incorrecta ",
  "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

           }
           
           if (numRegistros==1){
               //System.out.println("LA CLAVE ES CORRECTA");
               idKey= modeloLoc.listLogMasterKey(masterkeyInput).get(numRegistros-1).getIdKey().toString();
               //abrir locket
               JFLocket vistaC = new JFLocket();
               AlmacenDAO modelC = new AlmacenDAO();
               ControladorLocket controlaC= new ControladorLocket(vistaC,modelC,idKey);
               vistaC.setVisible(true);
               vistaC.setLocationRelativeTo(null);
           
           }
         
           
        }
        if (e.getSource() == vistaLoc.btnCrear){
            JFMasterKey vistaC = new JFMasterKey();
            LogDAO modelC= new LogDAO();
            ControladorMasterKey controlaC= new ControladorMasterKey(vistaC,modelC);
        
            vistaC.setVisible(true);
            vistaC.setLocationRelativeTo(null);
        }
        
    }

   
}
