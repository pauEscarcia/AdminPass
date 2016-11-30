/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import adminpassword.Decryption;
import adminpassword.Encryption;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    Decryption decrypt = new Decryption();
    String idKey = null;
    
    public ControladorLogin (JFLogin vistaLoc){
        this.vistaLoc = vistaLoc;
        this.vistaLoc.btnCrear.addActionListener(this);
        this.vistaLoc.btnOk.addActionListener(this);
    }
    public ControladorLogin(){
    
    }
    public void ValidarMasterKey(String masterKeyInput){
        
           int numRegistros = modeloLoc.listLogMasterKey(masterKeyInput).size();
           System.out.println(numRegistros);
           //trae los registros que estan dados de alta con esta masterkey 
           //que pasa si esa masterkey esta dada de alta muchas veces, que solo traiga la ultima vez que la registro 
           if(numRegistros>1){
               //System.out.println( "ultimo registro de contraseña multiple:"+modeloLoc.listLogMasterKey(masterkeyInput).get(numRegistros-1).getIdKey());
               idKey= modeloLoc.listLogMasterKey(masterKeyInput).get(numRegistros-1).getIdKey().toString();
               System.out.println("idKey chida:" + idKey);
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
               System.out.println("Master Key input ==1 " + idKey);
               idKey= modeloLoc.listLogMasterKey(masterKeyInput).get(numRegistros-1).getIdKey().toString();
               System.out.println("idKey  registros ==1 :" + idKey);
               //abrir locket
               JFLocket vistaC = new JFLocket();
               AlmacenDAO modelC = new AlmacenDAO();
               ControladorLocket controlaC= new ControladorLocket(vistaC,modelC,idKey);
               vistaC.setVisible(true);
               vistaC.setLocationRelativeTo(null);
           
           }
         
    
    }
    
     public void ValidarMasterKeyDecifrado(String masterKeyInput) throws Exception{
           System.out.println(" masterKeyInput"+ masterKeyInput);
           int numRegistros = modeloLoc.listLogMasterKey(masterKeyInput).size();
           System.out.println("numReg"+numRegistros);
           //trae los registros que estan dados de alta con esta masterkey 
           //que pasa si esa masterkey esta dada de alta muchas veces, que solo traiga la ultima vez que la registro 
           if(numRegistros>1){
               //System.out.println( "ultimo registro de contraseña multiple:"+modeloLoc.listLogMasterKey(masterkeyInput).get(numRegistros-1).getIdKey());
               idKey= modeloLoc.listLogMasterKey(masterKeyInput).get(numRegistros-1).getIdKey().toString();
               System.out.println("idKey"+ idKey);
               idKey = decrypt.decrypt(idKey, masterKeyInput);
               System.out.println("Desencriptadp"+ idKey);
               //abrir locket
               JFLocket vistaC = new JFLocket();
               AlmacenDAO modelC = new AlmacenDAO();
               ControladorLocket controlaC= new ControladorLocket(vistaC,modelC,masterKeyInput);
               vistaC.setVisible(true);
               vistaC.setLocationRelativeTo(null);
           }
           if(numRegistros==0){
               JOptionPane.showMessageDialog(null, "Contraseña Incorrecta ",
  "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

           }
           
           if (numRegistros==1){
               //System.out.println("LA CLAVE ES CORRECTA");
               idKey= modeloLoc.listLogMasterKey(masterKeyInput).get(numRegistros-1).getIdKey().toString();
               idKey = decrypt.decrypt(idKey, masterKeyInput);
                //abrir locket
               JFLocket vistaC = new JFLocket();
               AlmacenDAO modelC = new AlmacenDAO();
               ControladorLocket controlaC= new ControladorLocket(vistaC,modelC,masterKeyInput);
               vistaC.setVisible(true);
               vistaC.setLocationRelativeTo(null);
           
           }
         
    
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaLoc.btnOk){
            String masterKeyInput = vistaLoc.txtKeyss.getText();
            try {
                String masterKeyDecrypt = decrypt.decrypt(masterKeyInput, idKey);
                ValidarMasterKey(masterKeyDecrypt);
            } catch (Exception ex) {
                Logger.getLogger(ControladorLogin.class.getName()).log(Level.SEVERE, null, ex);
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
