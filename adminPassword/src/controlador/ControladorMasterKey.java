/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.*;
import vista.*;


/**
 *
 * @author pauEscarcia
 */
public class ControladorMasterKey implements ActionListener {

    JFMasterKey vistaMK = new JFMasterKey ();
    LogDAO modeloMK = new LogDAO();
    
    public ControladorMasterKey(JFMasterKey vistaMK,LogDAO modeloMK ){
        this.modeloMK= modeloMK;
        this.vistaMK = vistaMK;
        this.vistaMK.btnOk.addActionListener(this);
        this.vistaMK.btnGenerar.addActionListener(this);
        
    }
    public void InicializarMK(){
    
    }
   
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaMK.btnOk){
            String keyss= vistaMK.txtKeyss.getText();
            String keyss2= vistaMK.txtKeyss2.getText();
            
            if (keyss.equals(keyss2)){
                String rptaRegistro = modeloMK.insertLog(keyss);
                if(keyss.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Ingresa la contraseña");
                }else
                {
                    if (rptaRegistro!= null){
                        JOptionPane.showMessageDialog(null,rptaRegistro);
                        //FALTA ENVIARLO A LOCKET
                    }else {
                        JOptionPane.showMessageDialog(null,"Error de Registro");
                    }
                }
            }else {
                JOptionPane.showMessageDialog(null, "Las contraseñas no son iguales ");
            }
        }
        if (e.getSource() == vistaMK.btnGenerar){
            System.out.println("GENERAR");
            JFGenerator vistaC= new JFGenerator();
            ControladorGenerator controlaC= new ControladorGenerator(vistaC);
            vistaC.setVisible(true);
            //vistaC.setLocationRelativeTo(null);
        }
       
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
