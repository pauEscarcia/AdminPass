/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import  java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author pauEscarcia
 */
public class AlmacenDAO {
    Conexion conexion;
    
    public AlmacenDAO(){
        conexion= new Conexion();
    }
       public String insertAlmacen(String titulo,String usuario, String pass, String url, String expira, String idKey){
        String rptaRegistro= null;
        try{
            Connection accesoDB = conexion.getConexion();
            CallableStatement cs= accesoDB.prepareCall("{call sp_almacen(?,?,?,?,?,?)}");
            cs.setString(1, titulo);
            cs.setString(2, usuario);
            cs.setString(3, pass);
            cs.setString(4, url);
            cs.setString(5, expira);
            cs.setString(6, idKey);
            
            
            int numFAfectas = cs.executeUpdate();
            if (numFAfectas>0){
                rptaRegistro= "Registro Exitoso";
            }
           
        }catch(Exception e){
        
        }
        return rptaRegistro;
    }
    
    
    public ArrayList<Almacen> listAlmacen(String idKey){
        ArrayList listaAlmancen = new ArrayList();
        Almacen alm ;
        try{
            Connection acceBD= conexion.getConexion();
            PreparedStatement ps= acceBD.prepareStatement("SELECT * FROM almacen WHERE idKey = ? ");
            ps.setString(1, idKey);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                alm = new Almacen();
                alm.setIdAlmacen(rs.getInt(1));
                alm.setTitulo(rs.getString(2));
                alm.setUsuario(rs.getString(3));
                alm.setPass(rs.getString(4));
                alm.setUrl(rs.getString(5));
                alm.setExpira(rs.getString(6));
                listaAlmancen.add(alm);
                
            }
        }catch(Exception e){
        
        }
        return listaAlmancen;
    }
    
    public int editAlmacen(Integer idAlmacen,String titulo, String usuario, String pass,String url, String expira, String idKey){
        int filAfectadas=0;
        try {
            Connection accesoDB = conexion.getConexion();
            CallableStatement cs = accesoDB.prepareCall("{call sp_editAlmacen(?,?,?,?,?,?,?)}");
            cs.setInt(1, idAlmacen);
            cs.setString(2, titulo);
            cs.setString(3, usuario);
            cs.setString(4, pass);
            cs.setString(5, url);
            cs.setString(6, expira);
            cs.setString(7, idKey);
            
            filAfectadas = cs.executeUpdate();
        } catch (Exception e) {
        }
        return filAfectadas;  
    }
    public void deleteAlmacen(Integer idAlmacen){
        try{
            Connection acceBD= conexion.getConexion();
            PreparedStatement ps= acceBD.prepareStatement("DELETE FROM almacen WHERE idAlmacen = ? ");
            ps.setInt(1, idAlmacen);
            int count = ps.executeUpdate();
            System.out.println("Borrado: "+count);
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    /*public int deleteAlmacen(Integer idAlmacen){
        int filAfectadas= 0;
        try {
            Connection accesoDB = conexion.getConexion();
            CallableStatement cs = accesoDB.prepareCall("{call sp_deleteAlmacen(?)}");
            cs.setInt(1, idAlmacen);
            filAfectadas = cs.executeUpdate();
        } catch (Exception e) {
        }
        
        return filAfectadas;
    }*/
    
}
