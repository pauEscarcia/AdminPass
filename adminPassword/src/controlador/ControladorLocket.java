/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import adminpassword.AESDemo;
import adminpassword.Decryption;
import adminpassword.Encryption;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.System.console;
import java.util.ArrayList;
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
    Encryption en=new Encryption();
    Decryption de =new Decryption(); 
    String idKey;
    int filaEditar;
    
    public ControladorLocket(JFLocket vistaLoc,AlmacenDAO modeloLoc, String idKey ){
        this.modeloLoc= modeloLoc;
        this.vistaLoc = vistaLoc;
        this.vistaLoc.btnNuevo.addActionListener(this);
        this.vistaLoc.btnListar.addActionListener(this);
        this.vistaLoc.btnCopiar.addActionListener(this);
        this.vistaLoc.btnEditar.addActionListener(this);
        this.vistaLoc.btnEliminar.addActionListener(this);
        this.vistaLoc.btnOk.addActionListener(this);
        this.idKey = idKey;
    }
     public void ControladorLocket(){
    
    }
     
     
    public void LlenarTabla(JTable tablaD) throws Exception{
        //String tituloD = modeloLoc.listAlmacen(idKey).get(i).getTitulo();
        
        DefaultTableModel modelT = new DefaultTableModel();
        tablaD.setModel(modelT);
        
        modelT.addColumn("Titulo");
        modelT.addColumn("Usuario");
        modelT.addColumn("Password");
        modelT.addColumn("URL");
        modelT.addColumn("Expira");
        modelT.addColumn("idAlmacen");
        
        Object[] columna = new Object [6];
        
        int numRegistros = modeloLoc.listAlmacen(idKey).size();
        
        for (int i = 0; i < numRegistros; i++) {
            columna[0]= de.decrypt(modeloLoc.listAlmacen(idKey).get(i).getTitulo(),idKey);
            System.out.println("id Key is : " + idKey);
            columna[1]= de.decrypt(modeloLoc.listAlmacen(idKey).get(i).getUsuario(),idKey);
            columna[2]= modeloLoc.listAlmacen(idKey).get(i).getPass();
            columna[3]= de.decrypt(modeloLoc.listAlmacen(idKey).get(i).getUrl(),idKey);
            columna[4]= de.decrypt(modeloLoc.listAlmacen(idKey).get(i).getExpira(),idKey);
            columna[5]= modeloLoc.listAlmacen(idKey).get(i).getIdAlmacen();
            modelT.addRow(columna);          
        }
 
    }
    
     
    public void LimpiarCampos(){
        vistaLoc.txtTitulo.setText("");
        vistaLoc.txtUsuario.setText("");
        vistaLoc.txtPass.setText("");
        vistaLoc.txtURL.setText("");
    }
    
    public void Copiar(String seleccion) throws Exception{
        StringSelection stringSelection = new StringSelection (de.decrypt(seleccion,idKey));
        Clipboard clpbrd = Toolkit.getDefaultToolkit ().getSystemClipboard ();
        clpbrd.setContents (stringSelection, null);
        Component frame = null;
        JOptionPane.showMessageDialog(frame, "¡Contraseña copiada al portapapeles! ");
    }
     
    public int  validarEdicion(String titulo,String usuario, String pass, String url, String expira){
        int validador;
        System.out.println("titulo"+titulo);
        System.out.println("usuario"+usuario);
        System.out.println("pass"+pass);
        //System.out.println("pass2"+pass2);
        System.out.println("url"+url);
        System.out.println("expira"+expira);
        if(titulo.isEmpty() && usuario.isEmpty()&& url.isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Error! Ingresa valores ");
           validador=1;
        }
        else {
            validador=0;
        }
        if(pass.isEmpty()){
            JOptionPane.showMessageDialog(null,"Error! Password no puede ser nulo");
            validador = 1;
            
        }else{
             validador=0;
        }
        /*
        if(!pass.equals(pass2)){
            JOptionPane.showMessageDialog(null,"Error! Ingresa los password correctamente");
            validador = 1;
        }else{
            validador = 0;
        }
        */
        
        return validador;
    }
     public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaLoc.btnNuevo){
            
            String titulo= vistaLoc.txtTitulo.getText();            
            String usuario= vistaLoc.txtUsuario.getText();
            String pass= vistaLoc.txtPass.getText();
            String pass2= vistaLoc.txtPass2.getText();
            String url= vistaLoc.txtURL.getText();
            String expira= vistaLoc.comboExpira.getSelectedItem().toString();
            //String idkey = idKey;            
            
            //System.out.println(idKey);
            
            if (pass.equals(pass2)){
                String tituloC = null;
                String usuarioC = null;
                String passC = null;
                String urlC = null;
                String expiraC = null;
                //String idKeyC = null;
                
                try {
                    tituloC = en.encrypt(titulo,idKey);
                    usuarioC = en.encrypt(usuario,idKey);
                    passC = en.encrypt(pass,idKey);
                    urlC = en.encrypt(url,idKey);
                    expiraC = en.encrypt(expira,idKey);
                    //idKeyC = en.encrypt(idkey, idKey);
                } catch (Exception ex) {
                    Logger.getLogger(ControladorLocket.class.getName()).log(Level.SEVERE, null, ex);
                }
                String rptaRegistro = modeloLoc.insertAlmacen(tituloC,usuarioC,passC,urlC,expiraC,idKey);
                if (rptaRegistro!= null){
                    JOptionPane.showMessageDialog(null,rptaRegistro);
                }else {
                    JOptionPane.showMessageDialog(null,"Error de Registro");
                }
            }else {
                JOptionPane.showMessageDialog(null, "¡Las contraseñas no son iguales! ");
            }
        }
        
         if (e.getSource() == vistaLoc.btnListar){
            try {
                LlenarTabla(vistaLoc.tabla);
            } catch (Exception ex) {
                Logger.getLogger(ControladorLocket.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         if(e.getSource() == vistaLoc.btnEditar){
            
            int numfilas = vistaLoc.tabla.getSelectedRowCount();
            filaEditar = vistaLoc.tabla.getSelectedRow();
            if(filaEditar>=0 && numfilas==1){
                vistaLoc.txtTitulo.setText(String.valueOf(vistaLoc.tabla.getValueAt(filaEditar, 0)));
                vistaLoc.txtUsuario.setText(String.valueOf(vistaLoc.tabla.getValueAt(filaEditar, 1)));
                vistaLoc.txtPass.setText(String.valueOf(vistaLoc.tabla.getValueAt(filaEditar, 2)));
                vistaLoc.txtURL.setText(String.valueOf(vistaLoc.tabla.getValueAt(filaEditar, 3)));
                vistaLoc.comboExpira.setEnabled(true);
                vistaLoc.btnOk.setEnabled(true);
                vistaLoc.btnNuevo.setEnabled(false);
                vistaLoc.btnEliminar.setEnabled(false);
                vistaLoc.btnEditar.setEnabled(false);
            }else{
                    JOptionPane.showMessageDialog(null, "Seleccione una fila a editar");
            }
            
        }
       
        if (e.getSource() == vistaLoc.btnOk){
            String titulo = null;
            String usuario = null;
            String pass = null;
            String passNoCipher = null;
            String pass2 = null;
            String url = null;
            String expira = null;
            
            try {
                titulo = en.encrypt(vistaLoc.txtTitulo.getText(), idKey);
                usuario = en.encrypt(vistaLoc.txtUsuario.getText(), idKey);
                pass = en.encrypt(vistaLoc.txtPass.getText(), idKey);
                url = en.encrypt(vistaLoc.txtURL.getText(), idKey);
                expira = en.encrypt(vistaLoc.comboExpira.getSelectedItem().toString(), idKey);
                
            } catch (Exception ex) {
                Logger.getLogger(ControladorLocket.class.getName()).log(Level.SEVERE, null, ex);
            }
            int idAlmacen=   (int) vistaLoc.tabla.getValueAt(filaEditar, 5);
            pass2 = vistaLoc.txtPass2.getText();
            passNoCipher = vistaLoc.txtPass.getText();
            

            int rptEdit = modeloLoc.editAlmacen(idAlmacen,titulo, usuario, pass, url, expira,idKey);
            if(rptEdit>0){
                LimpiarCampos();
                int validacion = validarEdicion(titulo, usuario, pass, url, expira);
                System.out.println("validacion"+validacion);
                if(validacion==0){   
                     JOptionPane.showMessageDialog(null, "Edicion exitosa.");
                    try {
                        LlenarTabla(vistaLoc.tabla);
                    } catch (Exception ex) {
                        Logger.getLogger(ControladorLocket.class.getName()).log(Level.SEVERE, null, ex);
                    }
                
                }else {
                    //JOptionPane.showMessageDialog(null,"Error! Ingresa la contraseña correctamente");
                    System.out.println("Error");

                }
            }else{
                JOptionPane.showMessageDialog(null, "No se pudo realizar edicion.");
            }
            vistaLoc.btnOk.setEnabled(false);
            vistaLoc.btnEditar.setEnabled(true);
            vistaLoc.btnEliminar.setEnabled(true);
            vistaLoc.btnNuevo.setEnabled(true);
            vistaLoc.btnListar.setEnabled(true);
        
        }
           
        if(e.getSource() == vistaLoc.btnEliminar){
            int filInicio = vistaLoc.tabla.getSelectedRow();
            int numfilas = vistaLoc.tabla.getSelectedRowCount();
            ArrayList<Integer> listaKey  = new ArrayList<>();
            Integer dni;
            if(filInicio>=0){
                for(int i = 0; i<numfilas; i++){
                    dni = (Integer) (vistaLoc.tabla.getValueAt(i+filInicio, 5));
                    listaKey.add(i, dni);
                }
                for(int j = 0; j<numfilas; j++){
                    int rpta = JOptionPane.showConfirmDialog(null, "Desea eliminar registro del almacen: "+listaKey.get(j)+"? ");
                    if(rpta==0){
                        modeloLoc.deleteAlmacen(listaKey.get(j));
                        System.out.println("Elementos a borrar: " + listaKey.get(j));
                    }
                }
                try {
                    LlenarTabla(vistaLoc.tabla);
                } catch (Exception ex) {
                    Logger.getLogger(ControladorLocket.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                JOptionPane.showMessageDialog(null, "Elija al menos un registro para eliminar.");
            }
        }
        
        if (e.getSource() == vistaLoc.btnCopiar){
            int filInicio = vistaLoc.tabla.getSelectedRow();
            int numfilas = vistaLoc.tabla.getSelectedRowCount();
            ArrayList<String> listaKey  = new ArrayList<>();
            String dni;
            if(filInicio>=0){
                for(int i = 0; i<numfilas; i++){
                    dni = (String) vistaLoc.tabla.getValueAt(i+filInicio, 2);
                    try {
                        Copiar(dni);
                    } catch (Exception ex) {
                        Logger.getLogger(ControladorLocket.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    listaKey.add(i, dni);
                }
                /*
                for(int j = 0; j<numfilas; j++){
                    int rpta = JOptionPane.showConfirmDialog(null, "Desea visualizar: "+listaKey.get(j)+"? ");
                    if(rpta==0){
                        try {                      
                            Copiar(dni);
                        } catch (Exception ex) {
                            Logger.getLogger(ControladorLocket.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }*/                
            }else{
                JOptionPane.showMessageDialog(null, "Elija al menos un registro para eliminar.");
            }
            
        }
       
        
        
     }
    
}
