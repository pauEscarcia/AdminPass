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
import java.util.ArrayList;
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
    String idKey;
    Decryption de = new Decryption();
    
    public ControladorLogin (JFLogin vistaLoc){
        this.vistaLoc = vistaLoc;
        this.vistaLoc.btnCrear.addActionListener(this);
        this.vistaLoc.btnOk.addActionListener(this);
    }
    public ControladorLogin(){
    
    }
    public void ValidarMasterKey(String masterKeyInput){
           //realiza un select cuando masterkeyInput es igual a keyss (Encriptado)
           int numRegistros = modeloLoc.listLogMasterKey(masterKeyInput).size();
           //trae los registros que estan dados de alta con esta masterkey 
           //que pasa si esa masterkey esta dada de alta muchas veces, que solo traiga la ultima vez que la registro 
           // si existen elementos en la bd 
           if(numRegistros>1){
               //System.out.println( "ultimo registro de contraseña multiple:"+modeloLoc.listLogMasterKey(masterkeyInput).get(numRegistros-1).getIdKey());
               idKey= modeloLoc.listLogMasterKey(masterKeyInput).get(numRegistros-1).getIdKey().toString();
               //abrir locket
               JFLocket vistaC = new JFLocket();
               AlmacenDAO modelC = new AlmacenDAO();
               ControladorLocket controlaC= new ControladorLocket(vistaC,modelC,masterKeyInput);
               vistaC.setVisible(true);
               vistaC.setLocationRelativeTo(null);
           }// sino
           if(numRegistros==0){
               JOptionPane.showMessageDialog(null, "Contraseña Incorrecta ",
  "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

           }
           // si solo es un registro 
           if (numRegistros==1){
               //System.out.println("LA CLAVE ES CORRECTA");
               idKey= modeloLoc.listLogMasterKey(masterKeyInput).get(numRegistros-1).getIdKey().toString();
               //abrir locket
               JFLocket vistaC = new JFLocket();
               AlmacenDAO modelC = new AlmacenDAO();
               ControladorLocket controlaC= new ControladorLocket(vistaC,modelC,masterKeyInput);
               vistaC.setVisible(true);
               vistaC.setLocationRelativeTo(null);
           
           }
         
    
    }
    
    public void ValidarMasterKeyDesencriptar(String masterKeyInputTxt){ //eje: 13 
           ArrayList<String> listDesencriptada = new ArrayList<>();
           ArrayList<Integer> encontrado = new ArrayList<>();
           //realiza un select cuando masterkeyInput es igual a keyss (Encriptado)
           //hacer un select que me traiga todos los elementos cifrados y desifrarlo con masterKeyInputTxt
           int numRegTotEncr = modeloLoc.listLog().size();
           String hola= "Hola";
           String lista;
           int posicion=0;
           
           
               for (int i = 0; i <numRegTotEncr; i++) {
                   try {
                        listDesencriptada.add(i, de.decrypt(modeloLoc.listLog().get(i).getKey(), hola));
                         System.out.println("Lista desencriptada " + listDesencriptada.get(i));
                   } catch (Exception ex) {
                        Logger.getLogger(ControladorLogin.class.getName()).log(Level.SEVERE, null, ex);
                   } 
               }
               
               for (int i = 0; i < listDesencriptada.size(); i++) {
                   System.out.println("i" +i);
                   if(listDesencriptada.get(i).equals(masterKeyInputTxt)){
                       encontrado.add(i);
                       System.out.println("i iguales"+i);
                       
                   }
                   else {
                       System.out.println("no encontrado");
                   }
               }
               
               for (int i = 0; i < encontrado.size(); i++) {
                   System.out.println("encontrado"+encontrado.get(i));  
                  
               }
               
               
               if(encontrado.size()>=1){
                   System.out.println("encontrado final:"+encontrado.size());
                   System.out.println("yyaaa"+modeloLoc.listLog().get(encontrado.size()+1).getKey());
                   JFLocket vistaC = new JFLocket();
                   AlmacenDAO modelC = new AlmacenDAO();
                   ControladorLocket controlaC= new ControladorLocket(vistaC,modelC,modeloLoc.listLog().get(encontrado.size()+1).getKey());
                   vistaC.setVisible(true);
                   vistaC.setLocationRelativeTo(null);
               }
               
               
               
                
           /*
           int numRegistros = modeloLoc.listLogMasterKey(masterKeyInputTxt).size();
           //trae los registros que estan dados de alta con esta masterkey 
           //que pasa si esa masterkey esta dada de alta muchas veces, que solo traiga la ultima vez que la registro 
           // si existen elementos en la bd 
           if(numRegistros>1){
               //System.out.println( "ultimo registro de contraseña multiple:"+modeloLoc.listLogMasterKey(masterkeyInput).get(numRegistros-1).getIdKey());
               idKey= modeloLoc.listLogMasterKey(masterKeyInput).get(numRegistros-1).getIdKey().toString();
               //abrir locket
               JFLocket vistaC = new JFLocket();
               AlmacenDAO modelC = new AlmacenDAO();
               ControladorLocket controlaC= new ControladorLocket(vistaC,modelC,masterKeyInput);
               vistaC.setVisible(true);
               vistaC.setLocationRelativeTo(null);
           }// sino
           if(numRegistros==0){
               JOptionPane.showMessageDialog(null, "Contraseña Incorrecta ",
  "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

           }
           // si solo es un registro 
           if (numRegistros==1){
               //System.out.println("LA CLAVE ES CORRECTA");
               idKey= modeloLoc.listLogMasterKey(masterKeyInput).get(numRegistros-1).getIdKey().toString();
               //abrir locket
               JFLocket vistaC = new JFLocket();
               AlmacenDAO modelC = new AlmacenDAO();
               ControladorLocket controlaC= new ControladorLocket(vistaC,modelC,masterKeyInput);
               vistaC.setVisible(true);
               vistaC.setLocationRelativeTo(null);
           
           }*/
         
    
    }

    public void actionPerformed(ActionEvent e) {
        //falta decifrar 
        if (e.getSource() == vistaLoc.btnOk){
            String masterKeyInput = vistaLoc.txtKeyss.getText();
            //ValidarMasterKey( masterKeyInput);
            ValidarMasterKeyDesencriptar( masterKeyInput);
            
           
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
