/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import adminpassword.AESDemo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.*;
import vista.*;
/**
 *
 * @author pauEscarcia
 */
public class ControladorLocket implements ActionListener {
    JFLocket vistaLoc = new JFLocket ();
    AlmacenDAO modeloLoc = new AlmacenDAO();
    ControladorLogin controladorLogin = new ControladorLogin();
    AESDemo d = new AESDemo();
    String idKey;
    
    public ControladorLocket(JFLocket vistaLoc,AlmacenDAO modeloLoc, String idKey ){
        this.modeloLoc= modeloLoc;
        this.vistaLoc = vistaLoc;
        this.vistaLoc.btnNuevo.addActionListener(this);
        this.vistaLoc.btnListar.addActionListener(this);
        this.idKey = idKey;
    }
     public void ControladorLocket(){
    
    }
     
    public void LlenarTabla(JTable tablaD){
        DefaultTableModel modelT = new DefaultTableModel();
        tablaD.setModel(modelT);
        modelT.addColumn("Titulo");
        modelT.addColumn("Usuario");
        modelT.addColumn("Password");
        modelT.addColumn("URL");
        modelT.addColumn("Expira");
        
        Object[] columna = new Object [5];
        
        int numRegistros = modeloLoc.listAlmacen(idKey).size();
        
        for (int i = 0; i < numRegistros; i++) {
            columna[0]= modeloLoc.listAlmacen(idKey).get(i).getTitulo();
            columna[1]= modeloLoc.listAlmacen(idKey).get(i).getUsuario();
            columna[2]= modeloLoc.listAlmacen(idKey).get(i).getPass();
            columna[3]= modeloLoc.listAlmacen(idKey).get(i).getUrl();
            columna[4]= modeloLoc.listAlmacen(idKey).get(i).getExpira();
            modelT.addRow(columna);
            
            
        }
        
        
    }
     
     public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaLoc.btnListar){
            LlenarTabla(vistaLoc.tabla);
        }
        if (e.getSource() == vistaLoc.btnNuevo){
            
            String titulo= vistaLoc.txtTitulo.getText();            
            String usuario= vistaLoc.txtUsuario.getText();
            String pass= vistaLoc.txtPass.getText();
            String pass2= vistaLoc.txtPass2.getText();
            String url= vistaLoc.txtURL.getText();
            String expira= vistaLoc.comboExpira.getSelectedItem().toString();
            
            System.out.println(idKey);
            
            if (pass.equals(pass2)){
                String tituloC = null;
                String usuarioC = null;
                String passC = null;
                String urlC = null;
                String expiraC = null;
                
                try {
                    tituloC = d.encrypt(titulo);
                    usuarioC = d.encrypt(usuario);
                    passC = d.encrypt(pass);
                    urlC = d.encrypt(url);
                    expiraC = d.encrypt(expira);
                } catch (Exception ex) {
                    Logger.getLogger(ControladorLocket.class.getName()).log(Level.SEVERE, null, ex);
                }
                String rptaRegistro = modeloLoc.insertAlmacen(tituloC,usuarioC,passC,urlC,expiraC,idKey);
                //CUANDO OK
                if (rptaRegistro!= null){
                    JOptionPane.showMessageDialog(null,rptaRegistro);
                    //FALTA ENVIARLO A LOCKET
                }else {
                    JOptionPane.showMessageDialog(null,"Error de Registro");
                }
            }else {
                JOptionPane.showMessageDialog(null, "¡Las contraseñas no son iguales! ");
            }
        }
     }
    
}
